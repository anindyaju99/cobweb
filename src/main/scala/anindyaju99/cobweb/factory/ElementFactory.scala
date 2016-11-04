package anindyaju99.cobweb.factory

import akka.actor.{ActorContext, ActorRef}
import anindyaju99.cobweb.graph.{Edge, Vertex}

/**
 * Created by anindya.chakraborty on 01/11/16.
 */
trait ElementFactory {
  def createVertex(id: Long): Vertex
  def createEdge(id: Long): Edge

  def createVertexRef(id: Long, partitionManager: ActorContext): ActorRef
  def createEdgeRef(id: Long, partitionManager: ActorContext): ActorRef
}

object ElementFactory {
  private var elementFactoryInst: ElementFactory = null

  def registerElementFactory(factory: ElementFactory) = {
    elementFactoryInst = factory
  }

  def elementFactory = elementFactoryInst
}
