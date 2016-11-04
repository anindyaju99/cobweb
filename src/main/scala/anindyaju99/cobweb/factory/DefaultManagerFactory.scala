package anindyaju99.cobweb.factory

import akka.actor.{ActorContext, ActorRef}
import anindyaju99.cobweb.config.{Names, CobwebConfig}
import anindyaju99.cobweb.node.{NodeManagerActor, DefaultNodeManager, NodeManager}
import anindyaju99.cobweb.partition.{PartitionManagerActor, DefaultPartitionManager, PartitionManager}

/**
 * Created by anindya.chakraborty on 31/10/16.
 */
class DefaultManagerFactory extends ManagerFactory {

  def createPartitionManager(cobwebConfig: CobwebConfig, partitionNumber: Int): PartitionManager = {
    new DefaultPartitionManager(cobwebConfig, partitionNumber)
  }

  def createNodeManager(cobwebConfig: CobwebConfig): NodeManager = {
    new DefaultNodeManager(cobwebConfig)
  }

  def createPartitionManagerRef(cobwebConfig: CobwebConfig, partitionNumber: Int, nodeManager: ActorContext): ActorRef = {
    val partitionManager = createPartitionManager(cobwebConfig, partitionNumber)
    val partitionManagerConfig = partitionManager.partitionManagerConfig
    ActorFactory().register[PartitionManagerActor](Names.partitionManagerActorName(partitionNumber),
                            partitionManagerConfig.instances,
                            partitionManagerConfig.dispatcher,
                            nodeManager,
                            partitionManager)
  }

  def createNodeManagerRef(cobwebConfig: CobwebConfig): ActorRef = {
    val nodeManager = createNodeManager(cobwebConfig)
    val nodeManagerConfig = nodeManager.nodeManagerConfig
    ActorFactory().register[NodeManagerActor](Names.nodeManagerActorName,
                            nodeManagerConfig.instances,
                            nodeManagerConfig.dispatcher,
                            context = null,
                            nodeManager
                            )
  }
}
