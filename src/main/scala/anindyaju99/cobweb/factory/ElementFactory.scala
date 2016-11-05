package anindyaju99.cobweb.factory

import akka.actor.{ActorContext, ActorRef}
import anindyaju99.cobweb.graph.GraphElementProcessor

/**
 * Created by anindya.chakraborty on 01/11/16.
 */
trait ElementFactory {
  def createVertex(): GraphElementProcessor
  def createEdge(): GraphElementProcessor

  def createVertexRef(id: Long, context: ActorContext): ActorRef
  def createEdgeRef(id: Long, context: ActorContext): ActorRef
}

object ElementFactory {
  private var elementFactoryInst: ElementFactory = null

  def registerElementFactory(factory: ElementFactory) = {
    elementFactoryInst = factory
  }

  def apply() = elementFactoryInst
}
