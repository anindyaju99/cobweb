package anindyaju99.cobweb.factory

import java.util.concurrent.TimeUnit._
import akka.actor._
import akka.routing.SmallestMailboxPool
import akka.util.Timeout
import scala.concurrent.duration.Duration
import scala.reflect.ClassTag

/**
 * Created by anindya.chakraborty on 01/11/16.
 */
class ActorFactory(implicit val system: ActorSystem) {
  implicit val timeout: Timeout = Duration(60, SECONDS)

  def register[T <: Actor : ClassTag](name: String, instances: Int = 1,
                                      dispatcher: String = "akka.actor.default-dispatcher",
                                      context: ActorContext = null,
                                      args: Any = null) : ActorRef = {
    val scope = if (context == null) system else context
    val ref = instances match {
      case 1 =>
        scope.actorOf(props[T](args, dispatcher), name) // pool router not required
      case _ =>
        val p = props[T](args, dispatcher)
        scope.actorOf(SmallestMailboxPool(instances).props(p), name)
    }
    ref
  }

  private def props[T <: Actor : ClassTag](args: Any)(implicit clsTag: ClassTag[T]) : Props = {
    Props(clsTag.runtimeClass, args)
  }

  private def props[T <: Actor : ClassTag](args: Any, dispatcher: String) : Props = {
    if (args != null) {
      props(args).withDispatcher(dispatcher)
    } else {
      Props[T].withDispatcher(dispatcher)
    }
  }
}

object ActorFactory {
  private var factory: ActorFactory = null
  def register(f: ActorFactory) = {
    factory = f
  }

  def apply(): ActorFactory = factory
}
