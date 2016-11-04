package anindyaju99.cobweb.graph

/**
 * Created by anindya.chakraborty on 31/10/16.
 */
trait Element {
  def update(data: Any)
  def load
  def process(event: Any)
  def get: Any
  def id: Long
  def propagate(e: Any) // propagates to all immediate out degree
}
