package anindyaju99.cobweb.partition

import akka.actor.{ActorContext, ActorRef}
import anindyaju99.cobweb.config.PartitionManagerConfig
import anindyaju99.cobweb.events.GraphElementEvent

/**
 * Created by anindya.chakraborty on 31/10/16.
 */
trait PartitionManager {
  def partition: Int
  def vertex(id: Long)(implicit partitionManager: ActorContext): ActorRef
  def edge(id: Long)(implicit partitionManager: ActorContext): ActorRef
  def partitionManagerConfig: PartitionManagerConfig

  def toEdge(event: GraphElementEvent)(implicit partitionManager: ActorContext) // toId belongs to this partition
  def toVertex(event: GraphElementEvent)(implicit partitionManager: ActorContext) // toId belongs to this partition
}
