package anindyaju99.cobweb.node

import java.util

import akka.actor.{ActorContext, PoisonPill, ActorRef}
import akka.event.slf4j.Logger
import akka.routing.Broadcast
import anindyaju99.cobweb.config.{NodeManagerConfig, CobwebConfig}
import anindyaju99.cobweb.events.Release
import anindyaju99.cobweb.factory.ManagerFactory
import anindyaju99.cobweb.partition.PartitionManager
import com.typesafe.config.Config

/**
 * Created by anindya.chakraborty on 31/10/16.
 */
class DefaultNodeManager(cobwebConfig: CobwebConfig) extends NodeManager {
  val log = Logger("DefaultNodeManager")
  private var currentPartitions = Set.empty[Int]
  private val partitions = new Array[ActorRef](cobwebConfig.totalPartitionCount)
  private val _nodeManagerConfig = cobwebConfig.nodeManagerConfig

  def nodeManagerConfig: NodeManagerConfig = _nodeManagerConfig

  def own(partitionNumber: Int, nodeManager: ActorContext) = {
    log.info("own msg " + partitionNumber)
    if (partitions(partitionNumber) == null) {
      this.synchronized {
        if (partitions(partitionNumber) == null) {
          log.info("own " + partitionNumber)
          partitions(partitionNumber) =
            ManagerFactory().createPartitionManagerRef(partitionNumber, nodeManager)
          currentPartitions = currentPartitions + partitionNumber
        }
      }
    }
  }
  def release(partitionNumber: Int) = {
    log.info("release msg " + partitionNumber)
    if (partitions(partitionNumber) != null) {
      this.synchronized {
        if (partitions(partitionNumber) != null) {
          log.info("release " + partitionNumber)
          partitions(partitionNumber) ! Broadcast(Release(partitionNumber))
          partitions(partitionNumber) = null
          currentPartitions = currentPartitions - partitionNumber
        }
      }
    }
  }
  def partition(partitionNumber: Int): ActorRef = {
    partitions(partitionNumber)
  }

  def list: Seq[Int] = currentPartitions.toSeq
}
