package anindyaju99.cobweb.partition

import akka.actor.{ActorContext, ActorRef}
import anindyaju99.cobweb.config.{PartitionManagerConfig, CobwebConfig}
import java.util.concurrent.ConcurrentHashMap
import anindyaju99.cobweb.factory.ElementFactory


/**
 * Created by anindya.chakraborty on 01/11/16.
 */
class DefaultPartitionManager(cobwebConfig: CobwebConfig, partitionNumber: Int) extends PartitionManager {
  private val edges = new ConcurrentHashMap[Long, ActorRef]() // look for a sparse list/map implementation instead
  private val vertices = new ConcurrentHashMap[Long, ActorRef]() // look for a sparse list/map implementation instead
  private val _partitionManagerConfig = cobwebConfig.partitionManagerConfig

  def partition: Int = partitionNumber

  // correctness of vertex/edge methods rely on the fact that an
  // attempt to create 2 actors for same ID will result into an exception!
  def vertex(id: Long, partitionManager: ActorContext): ActorRef = {
    var v = vertices.get(id)
    if (v == null) {
      v = ElementFactory.elementFactory.createVertexRef(id, partitionManager)
      vertices.put(id, v)
    }

    v
  }

  def edge(id: Long, partitionManager: ActorContext): ActorRef = {
    var e = edges.get(id)
    if (e == null) {
      e = ElementFactory.elementFactory.createEdgeRef(id, partitionManager)
      edges.put(id, e)
    }

    e
  }

  def partitionManagerConfig: PartitionManagerConfig = _partitionManagerConfig
}
