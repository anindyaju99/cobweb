package anindyaju99.cobweb

import java.io.File

import akka.actor.{ActorRef, ActorSystem}
import akka.pattern._
import akka.event.slf4j.Logger
import akka.util.Timeout
import anindyaju99.cobweb.config.CobwebConfig
import anindyaju99.cobweb.events.{PartitionList, Release, Own}
import anindyaju99.cobweb.factory.{ActorFactory, DefaultManagerFactory, ManagerFactory}
import com.typesafe.config.ConfigFactory
import scala.concurrent.Await
import scala.concurrent.duration._
import scala.util.Try

/**
 * Created by anindya.chakraborty on 03/11/16.
 */
object Cobweb extends App {
  lazy val log = Logger("cobweb")

  lazy val config = ConfigFactory.parseFile(new File(System.getProperty("app.config"))).resolve()

  implicit val system = ActorSystem("cobweb", config)


  val nodeManagerRef = init

  def init: ActorRef = {
    val cobwebConfig = CobwebConfig(config.getConfig("cobweb"))
    val actorFactory = new ActorFactory()
    ActorFactory.register(actorFactory)

    ManagerFactory.registerManagerFactory(new DefaultManagerFactory)
    val nodeManagerRef = ManagerFactory().createNodeManagerRef(cobwebConfig)

    if (cobwebConfig.debug) {
      test(nodeManagerRef)
    }
    nodeManagerRef
  }

  def test(nodeManagerRef: ActorRef) = {
    testOneMessge(Own(1), "Own", nodeManagerRef)
    testOneMessge(PartitionList, "Partitions", nodeManagerRef)
    testOneMessge(Release(1), "Release", nodeManagerRef)
    testOneMessge(PartitionList, "Partitions", nodeManagerRef)
    testOneMessge(Release(2), "Release", nodeManagerRef)
    testOneMessge(PartitionList, "Partitions", nodeManagerRef)
  }

  def testOneMessge(msg: Any, label: String, nodeManagerRef: ActorRef) = {
    try {
      implicit val timeout = 1000 milliseconds
      implicit val askTimeout = Timeout(timeout)

      val resp = nodeManagerRef ? msg
      log.info(label + ": " + Await.result(resp, timeout).toString)
    } catch {
      case ex: Exception => log.error("testOneMessge: " + ex.getMessage, ex)
    }
  }
}
