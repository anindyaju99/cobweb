package anindyaju99.cobweb.graph.impl

import akka.actor.{ActorContext, ActorRef}
import anindyaju99.cobweb.config.CobwebConfig
import anindyaju99.cobweb.factory.ElementFactory
import anindyaju99.cobweb.graph.GraphElementProcessor

/**
 * Created by anindya.chakraborty on 01/11/16.
 */
class DefaultElementFactory(cobwebConfig: CobwebConfig) extends ElementFactory {
  def createVertex(): GraphElementProcessor = {
    null
  }

  def createEdge(): GraphElementProcessor = {
    null
  }

  def createVertexRef(id: Long, partitionManager: ActorContext): ActorRef = {
    null
  }
  def createEdgeRef(id: Long, partitionManager: ActorContext): ActorRef = {
    null
  }
}
