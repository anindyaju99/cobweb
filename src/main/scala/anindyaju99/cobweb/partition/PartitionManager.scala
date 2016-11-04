package anindyaju99.cobweb.partition

import akka.actor.{ActorContext, ActorRef}
import anindyaju99.cobweb.config.PartitionManagerConfig

/**
 * Created by anindya.chakraborty on 31/10/16.
 */
trait PartitionManager {
  def partition: Int
  def vertex(id: Long, partitionManager: ActorContext): ActorRef
  def edge(id: Long, partitionManager: ActorContext): ActorRef
  def partitionManagerConfig: PartitionManagerConfig
}
