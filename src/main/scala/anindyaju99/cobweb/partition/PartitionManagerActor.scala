package anindyaju99.cobweb.partition

import akka.actor.Actor
import akka.event.slf4j.Logger
import anindyaju99.cobweb.common.CobwebActor

/**
 * Created by anindya.chakraborty on 01/11/16.
 */
class PartitionManagerActor(partitionManager: PartitionManager) extends CobwebActor {
  val log = Logger("PartitionManagerActor")

  override def preStart() = {
    log.info("preStart")
    super.preStart
  }

  override def postStop = {
    log.info("postStop")
    super.postStop
  }

  override def receive() = {
    case _ => // NYI, placeholder
  }
}
