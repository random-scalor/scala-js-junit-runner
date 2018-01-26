package com.carrotgarden.sjs.junit

import org.junit.runners.Suite
import org.junit.runners.model.RunnerBuilder
import org.junit.runner.Runner
import org.junit.runner.notification.RunNotifier
import sbt.testing.Framework
import sbt.testing.TaskDef
import sbt.testing.SuiteSelector
import sbt.testing.TestSelector
import sbt.testing.EventHandler
import sbt.testing.Event
import org.junit.runner.notification.Failure
import org.junit.runner.Description
import org.junit.runners.model.Statement
import scala.annotation.tailrec

/**
 * JUnit 4 suite runner for Scala.js.
 *
 * Invoke JUnit tests inside JS-VM.
 */
case class ScalaJS_Suite( klass : Class[ _ ], builder : RunnerBuilder ) extends Suite( klass, builder ) {

  lazy val framework = Context.defaultFramework

  def frameworkRunner = {
    framework.runner( Array(), Array(), null )
  }

  def fireNestedStareted( description : Description, notifier : RunNotifier ) : Unit = {
    description.getChildren.forEach( description => {
      notifier.fireTestStarted( description )
      fireNestedStareted( description, notifier )
    } )
  }

  def fireNestedFinished( description : Description, notifier : RunNotifier ) : Unit = {
    description.getChildren.forEach( description => {
      notifier.fireTestFinished( description )
      fireNestedFinished( description, notifier )
    } )
  }

  override def runChild( runner : Runner, notifier : RunNotifier ) {

    val description = runner.getDescription()

    try {

      notifier.fireTestStarted( description )

      val className = description.getTestClass.getName
      val classMark = framework.fingerprints()( 0 );

      val taskDef = new TaskDef(
        className,
        classMark,
        true,
        Array( new TestSelector( description.toString() ) )
      )

      val taskDefs = Array[ TaskDef ]( taskDef )

      val runnerJS = frameworkRunner

      val taskList = runnerJS.tasks( taskDefs )

      val handler = new EventHandler {
        import sbt.testing.Status
        def handle( event : Event ) = {
          def fireIgnored() = {
            notifier.fireTestIgnored( description )
          }
          def fireFailure() = {
            val error =
              if ( event.throwable.isDefined ) event.throwable.get
              else new Exception()
            notifier.fireTestFailure( new Failure( description, error ) )
          }
          event.status match {
            case Status.Success  => // OK
            case Status.Error    => fireFailure()
            case Status.Failure  => fireFailure()
            case Status.Skipped  => fireIgnored()
            case Status.Ignored  => fireIgnored()
            case Status.Canceled => ??? // TODO
            case Status.Pending  => ??? // TODO
            case _               => ??? // TODO
          }
        }
      }

      val logger = SimpleLogger()

      fireNestedStareted( description, notifier )

      taskList.foreach { task =>
        val result = task.execute( handler, Array( logger ) )
        require( result.length == 0 )
      }

      fireNestedFinished( description, notifier )

    } catch {
      case error : Throwable =>
        notifier.fireTestFailure( new Failure( description, error ) )
    } finally {
      notifier.fireTestFinished( description )
    }

  }

}
