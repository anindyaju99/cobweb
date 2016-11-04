package anindyaju99.cobweb.node

import akka.actor.{ActorContext, ActorRef}
import anindyaju99.cobweb.config.NodeManagerConfig

/**
 * Created by anindya.chakraborty on 31/10/16.
 */
trait NodeManager {
  def own(partitionNumber: Int, nodeManager: ActorContext)
  def release(partitionNumber: Int)
  def partition(partitionNumber: Int): ActorRef
  def list: Seq[Int] // list the partitions owned by this NodeManager
  def nodeManagerConfig: NodeManagerConfig
}
