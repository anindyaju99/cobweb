package anindyaju99.cobweb.graph

/**
 * Created by anindya.chakraborty on 31/10/16.
 */

/* Elements are the ones an application writer would change.
  So a job run on this framework or a value propagation usecase will have
  a different set of implementations for the Element trait.
 */

trait ElementDataAccessor {
  def save(id: Long, data: Any)
  def get(id: Long): Any
}

trait ElementProcessor {
  def process(id: Long, event: Any)
}

trait ElementAdjacentPropagator {
  def propagate(sourceId: Long, destId: Long, event: Any)
  def propagate(sourceId: Long, event: Any)
}

trait GraphElementProcessor extends ElementDataAccessor with ElementAdjacentPropagator with ElementProcessor
