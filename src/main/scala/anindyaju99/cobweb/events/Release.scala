package anindyaju99.cobweb.events

/**
 * Created by anindya.chakraborty on 01/11/16.
 */
case class Release(partitionNumber: Int)
case class Own(partitionNumber: Int)
case object PartitionList

case class GraphElementEvent(id: Long, from: Long, to: Long, msg: Any)