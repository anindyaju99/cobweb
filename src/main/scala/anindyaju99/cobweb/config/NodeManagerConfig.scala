package anindyaju99.cobweb.config

import com.typesafe.config.Config

import scala.util.Try

/**
 * Created by anindya.chakraborty on 03/11/16.
 */
case class NodeManagerConfig(cobwebConfig: CobwebConfig, config: Config) {
  def instances = Try(config.getInt("instances")).getOrElse(1)
  def dispatcher = Try(config.getString("dispatcher")).getOrElse("akka.actor.default-dispatcher")
}
