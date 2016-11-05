package anindyaju99.cobweb.config

import com.typesafe.config.Config

import scala.util.Try

/**
 * Created by anindya.chakraborty on 03/11/16.
 */
case class NodeManagerConfig(cobwebConfig: CobwebConfig, config: Config) extends ManagerConfig(cobwebConfig, config)