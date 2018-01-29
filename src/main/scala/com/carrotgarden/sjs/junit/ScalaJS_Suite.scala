package com.carrotgarden.sjs.junit

import org.junit.runners.Suite
import org.junit.runners.model.RunnerBuilder
import org.junit.runner.Runner
import org.junit.runner.notification.RunNotifier
import org.junit.runner.notification.Failure
import org.junit.runner.Description
import org.junit.runners.model.Statement

import sbt.testing.Framework
import sbt.testing.TaskDef
import sbt.testing.SuiteSelector
import sbt.testing.TestSelector
import sbt.testing.EventHandler
import sbt.testing.Event

import scala.annotation.tailrec
import scala.collection.JavaConverters._

/**
 * JUnit 4 suite runner for Scala.js.
 *
 * Directs invocations of JUnit 4 tests inside JS-VM.
 *
 * This runner requires a provisioning configuration
 * which describes how to start JS-VM testing environment.
 *
 * Default provisioning configuration location:
 * ${project.basedir}/target/scala-js-junit-tools/default.data
 *
 * Normally, provisioning configuration is prepared by an external plugin
 * before running a test framework which controls JUnit platform launcher.
 *
 * For example:
 * - scalor-maven-plugin:scala-js-env-prov-nodejs prepares configuration,
 * - maven-surefire-plugin:test controls JUnit platform launcher invocation.
 */
case class ScalaJS_Suite( klaz : Class[ _ ], builder : RunnerBuilder )
  extends Suite( klaz, builder ) with Reference {

  import ScalaJS_Suite._

  lazy val framework = Context.defaultFramework

  lazy val workerRunner = Context.defaultRunner

  // FIXME JUnit specific
  lazy val marker = framework.fingerprints()( 0 )

  lazy val invokerLogger = new InvokerLogger()

  /**
   * Invoke worker class testing execution inside JS-VM.
   */
  override def runChild( runner : Runner, notifier : RunNotifier ) {
    import referenceJUnitEvent._

    // Trigger JS-VM launch on first run.
    val args = workerRunner.args

    val rootMeta = runner.getDescription()
    val testKlaz = rootMeta.getTestClass.getName

    val handler = new Notificator( rootMeta, notifier )

    try {

      // TODO support nested suites.
      val taskDef = new TaskDef(
        testKlaz, marker, true, Array( new SuiteSelector() )
      )
      val taskDefs = Array[ TaskDef ]( taskDef )
      val taskList = workerRunner.tasks( taskDefs )
      require( taskList.length == 1 )

      if ( fireTest.fromRuns ) {
        if ( printDebug ) println( s"FIRE test started ${rootMeta}" )
        notifier.fireTestStarted( rootMeta )
      }

      val loggerArray : Array[ sbt.testing.Logger ] =
        if ( fireMethod.fromLogs ) {
          Array( invokerLogger, handler.logger )
        } else {
          Array( invokerLogger )
        }

      taskList.foreach { task =>

        if ( fireMethod.fromRuns ) {
          fireMethodStareted( rootMeta, notifier )
        }

        val result = task.execute( handler, loggerArray )
        require( result.length == 0 )

        if ( fireMethod.fromRuns ) {
          fireMethodFinished( rootMeta, notifier )
        }
      }

    } catch {
      case error : Throwable =>
        notifier.fireTestFailure( new Failure( rootMeta, error ) )
    } finally {
      if ( fireTest.fromRuns ) {
        if ( printDebug ) println( s"FIRE test finished ${rootMeta}" )
        notifier.fireTestFinished( rootMeta )
      }
    }
  }

}

object ScalaJS_Suite extends Reference {
  import referenceJUnitEvent._

  /**
   * Hack around Scala.js JUnit provider not reporting per-method events.
   */
  def fireMethodStareted( rootMeta : Description, notifier : RunNotifier ) : Unit = {
    rootMeta.getChildren.asScala.foreach( nodeMeta => {
      if ( printDebug ) println( s"FIRE method started ${nodeMeta}" )
      notifier.fireTestStarted( nodeMeta )
      fireMethodStareted( nodeMeta, notifier )
    } )
  }

  /**
   * Hack around Scala.js JUnit provider not reporting per-method events.
   */
  def fireMethodFinished( rootMeta : Description, notifier : RunNotifier ) : Unit = {
    rootMeta.getChildren.asScala.foreach( nodeMeta => {
      if ( printDebug ) println( s"FIRE method finished ${nodeMeta}" )
      notifier.fireTestFinished( nodeMeta )
      fireMethodFinished( nodeMeta, notifier )
    } )
  }

}
