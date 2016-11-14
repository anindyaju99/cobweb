package anindyaju99.cobweb.events

/**
 * Created by anindya.chakraborty on 11/11/16.
 */
object Vertex {
  case class Own(id: Long)
  case class Release(id: Long)
  case class AddEdge(id: Long, kind: String)
}
