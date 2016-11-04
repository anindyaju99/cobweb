package anindyaju99.cobweb.config

import com.typesafe.config.Config

import scala.util.Try

/**
 * Created by anindya.chakraborty on 31/10/16.
 */
case class CobwebConfig(config: Config) {
  def debug = Try(config.getBoolean("debug")).getOrElse(false)
  def totalPartitionCount = Try(config.getInt("totalPartitionCount")).getOrElse(2000)
  def partitionManagerConfig = PartitionManagerConfig(this, config.getConfig("partitionManager"))
  def nodeManagerConfig = NodeManagerConfig(this, config.getConfig("nodeManager"))
}
