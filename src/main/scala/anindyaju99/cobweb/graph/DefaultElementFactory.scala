package anindyaju99.cobweb.graph

import akka.actor.{ActorContext, ActorRef}
import anindyaju99.cobweb.factory.ElementFactory

/**
 * Created by anindya.chakraborty on 01/11/16.
 */
class DefaultElementFactory extends ElementFactory {
  def createVertex(id: Long): Vertex = {
    null
  }
  def createEdge(id: Long): Edge = {
    null
  }

  def createVertexRef(id: Long, partitionManager: ActorContext): ActorRef = {
    null
  }
  def createEdgeRef(id: Long, partitionManager: ActorContext): ActorRef = {
    null
  }
}
