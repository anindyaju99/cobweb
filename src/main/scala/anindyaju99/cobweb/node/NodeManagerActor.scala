package anindyaju99.cobweb.node

import akka.event.slf4j.Logger
import anindyaju99.cobweb.common.CobwebActor
import anindyaju99.cobweb.events.{Ok, PartitionList, Own, Release}

/**
 * Created by anindya.chakraborty on 01/11/16.
 */
class NodeManagerActor(partitionManager: NodeManager) extends CobwebActor {
  val log = Logger("NodeManagerActor")

  override def preStart() = {
    log.info("preStart")
    super.preStart
  }

  override def postStop = {
    log.info("postStop")
    super.postStop
  }

  override def receive() = {
    case release: Release =>
      partitionManager.release(release.partitionNumber)
      sender() ! Ok
    case own: Own =>
      partitionManager.own(own.partitionNumber, context)
      sender() ! Ok
    case PartitionList =>
      sender() ! partitionManager.list
    case x: Any =>
      log.info("Any msg " + x.toString)
  }
}
