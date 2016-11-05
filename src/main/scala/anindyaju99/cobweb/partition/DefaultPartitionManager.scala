package anindyaju99.cobweb.partition

import akka.actor.{ActorContext, ActorRef}
import anindyaju99.cobweb.config.{PartitionManagerConfig, CobwebConfig}
import java.util.concurrent.ConcurrentHashMap
import anindyaju99.cobweb.events.GraphElementEvent
import anindyaju99.cobweb.factory.ElementFactory


/**
 * Created by anindya.chakraborty on 01/11/16.
 */
class DefaultPartitionManager(cobwebConfig: CobwebConfig, partitionNumber: Integer) extends PartitionManager {
  /*
  Future considerations -
    - should we use a lock free sparse list implementation for indexing the edge, vertex actors?
    - is there such a data structure?
    - Should we use an actor pool instead of creating new ones? We still have to ensure one edge/vertex does not get
      more than an actor at a time.
    - We can also try assigning a set of ids to an actor instead of assigning one vertex/edge per actor.
   */
  private val edges = new ConcurrentHashMap[Long, ActorRef]() // look for a sparse list/map implementation instead
  private val vertices = new ConcurrentHashMap[Long, ActorRef]() // look for a sparse list/map implementation instead
  private val _partitionManagerConfig = cobwebConfig.partitionManagerConfig

  def partition: Int = partitionNumber

  // correctness of vertex/edge methods rely on the fact that an
  // attempt to create 2 actors for same ID will result into an exception!
  def vertex(id: Long)(implicit partitionManager: ActorContext): ActorRef = {
    var v = vertices.get(id)
    if (v == null) {
      v = ElementFactory().createVertexRef(id, partitionManager)
      vertices.put(id, v)
    }

    v
  }

  def edge(id: Long)(implicit partitionManager: ActorContext): ActorRef = {
    var e = edges.get(id)
    if (e == null) {
      e = ElementFactory().createEdgeRef(id, partitionManager)
      edges.put(id, e)
    }

    e
  }

  def toEdge(event: GraphElementEvent)(implicit partitionManager: ActorContext) = {
    val e = edge(event.to)
    e ! event
  }
  def toVertex(event: GraphElementEvent)(implicit partitionManager: ActorContext) = {
    val v = vertex(event.to)
    v ! event
  }

  def partitionManagerConfig: PartitionManagerConfig = _partitionManagerConfig
}
