package anindyaju99.cobweb.factory

import akka.actor.{ActorContext, ActorRef}
import anindyaju99.cobweb.common.ClassCreator
import anindyaju99.cobweb.config.{Names, CobwebConfig}
import anindyaju99.cobweb.node.{NodeManagerActor, DefaultNodeManager, NodeManager}
import anindyaju99.cobweb.partition.{PartitionManagerActor, DefaultPartitionManager, PartitionManager}

/**
 * Created by anindya.chakraborty on 31/10/16.
 */
class DefaultManagerFactory(cobwebConfig: CobwebConfig) extends ManagerFactory {
  def createPartitionManager(partitionNumber: Int): PartitionManager = {
    val pmConfig = cobwebConfig.partitionManagerConfig
    val manager = ClassCreator[PartitionManager](pmConfig.manager, cobwebConfig, new Integer(partitionNumber))
    manager
  }

  def createNodeManager(): NodeManager = {
    val nmConfig = cobwebConfig.nodeManagerConfig
    val manager = ClassCreator[NodeManager](nmConfig.manager, cobwebConfig)
    manager
  }

  def createPartitionManagerRef(partitionNumber: Int, nodeManager: ActorContext): ActorRef = {
    val partitionManager = createPartitionManager(partitionNumber)
    val partitionManagerConfig = partitionManager.partitionManagerConfig
    ActorFactory().register[PartitionManagerActor](Names.partitionManagerActorName(partitionNumber),
                            partitionManagerConfig.instances,
                            partitionManagerConfig.dispatcher,
                            nodeManager,
                            partitionManager)
  }

  def createNodeManagerRef(): ActorRef = {
    val nodeManager = createNodeManager()
    val nodeManagerConfig = nodeManager.nodeManagerConfig
    ActorFactory().register[NodeManagerActor](Names.nodeManagerActorName,
                            nodeManagerConfig.instances,
                            nodeManagerConfig.dispatcher,
                            context = null,
                            nodeManager
                            )
  }
}
