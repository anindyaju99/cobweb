package anindyaju99.cobweb.key

/**
 * Created by anindya.chakraborty on 05/11/16.
 */
trait KeyGenerator {
  def key(data: String): Long
}

object KeyGenerator extends KeyGenerator {
  private var generator: KeyGenerator = null
  def registerKeyGenerator(keyGen: KeyGenerator) = {
    generator = keyGen
  }

  def key(data: String): Long = {
    generator.key(data)
  }
}
