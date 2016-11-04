package anindyaju99.cobweb.config

/**
 * Created by anindya.chakraborty on 01/11/16.
 */
object Names {
  def partitionManagerActorName(partitionNumber: Int) = "partitionManager_" + partitionNumber
  def nodeManagerActorName = "nodeManager"
  def vertexActorName(id: Long) = "v" + id
  def edgeActorName(id: Long) = "e" + id
}
