package anindyaju99.cobweb.common

import java.lang.reflect.Constructor

import anindyaju99.cobweb.config.CobwebConfig

/**
 * Created by anindya.chakraborty on 05/11/16.
 */
object ClassCreator {
  def apply[T](className: String, args:AnyRef*): T = {
    val cls = Class.forName(className)
    val argClasses: Array[Class[AnyRef]] = args.map{arg => arg.getClass}.toArray.asInstanceOf[Array[Class[AnyRef]]]
    val cons = cls.getConstructor(argClasses:_*)
    cons.newInstance(args:_*).asInstanceOf[T]
  }
}
