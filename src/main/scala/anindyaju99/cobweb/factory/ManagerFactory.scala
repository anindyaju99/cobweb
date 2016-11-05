package anindyaju99.cobweb.factory

import akka.actor.{ActorContext, ActorRef}
import anindyaju99.cobweb.config.CobwebConfig
import anindyaju99.cobweb.node.NodeManager
import anindyaju99.cobweb.partition.PartitionManager

/**
 * Created by anindya.chakraborty on 31/10/16.
 */

trait ManagerFactory {
  protected def createPartitionManager(partitionNumber: Int): PartitionManager
  def createPartitionManagerRef(partitionNumber: Int, nodeManager: ActorContext): ActorRef
  protected def createNodeManager: NodeManager
  def createNodeManagerRef: ActorRef
}

object ManagerFactory {
  private var managerFactoryInst: ManagerFactory = null


  def registerManagerFactory(factory: ManagerFactory) = {
    managerFactoryInst = factory
  }

  def apply() = managerFactoryInst
}
