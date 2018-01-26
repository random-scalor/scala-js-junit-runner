package com.carrotgarden.sjs.junit

import sbt.testing.EventHandler
import sbt.testing.Event
import sbt.testing.Status

import org.junit.runner.notification.RunNotifier
import org.junit.runner.Description
import org.junit.runner.notification.Failure

class BasicHandler( description : Description, notifier : RunNotifier )
  extends EventHandler {

  def fireIgnored( event : Event ) = {
    notifier.fireTestIgnored( description )
  }

  def fireFailure( event : Event ) = {
    val error =
      if ( event.throwable.isDefined ) event.throwable.get
      else new Exception()
    notifier.fireTestFailure( new Failure( description, error ) )
  }

  override def handle( event : Event ) : Unit = {

    event.status match {
      case Status.Success  => // OK
      case Status.Error    => fireFailure( event )
      case Status.Failure  => fireFailure( event )
      case Status.Skipped  => fireIgnored( event )
      case Status.Ignored  => fireIgnored( event )
      case Status.Canceled => ??? // TODO
      case Status.Pending  => ??? // TODO
      case _               => ??? // TODO
    }

  }

}
