package anindyaju99.cobweb.common

import akka.actor.SupervisorStrategy.{Escalate, Stop, Restart}
import akka.actor.{ActorKilledException, ActorInitializationException, OneForOneStrategy, Actor}

/**
 * Created by anindya.chakraborty on 03/11/16.
 */
abstract class CobwebActor extends Actor {
  override final val supervisorStrategy = OneForOneStrategy() {
    case _: ActorInitializationException => Restart
    case _: ActorKilledException => Stop
    case _: Exception => Restart
    case _ => Escalate
  }
}
