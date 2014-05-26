import com.typesafe.scalalogging.slf4j.LazyLogging
import scopt.OptionParser

object Program extends LazyLogging {
  def main(args: Array[String]) {
    try {
      val parser = this.createParser();
      parser.parse(args, createDefaultConfiguration()) map { configuration =>
        val application = new BitcoinApplication(configuration)
        application.run()
      } getOrElse {
        logger.warn("Unable to properly parse arguments, exiting...")
      }
    } catch {
      case t: Throwable =>
        logger.error("Uncaught exception in main thread", t)
        throw t
    }
  }

  private def createDefaultConfiguration(): ProgramConfiguration = {
    ProgramConfiguration()
  }

  private def createParser(): OptionParser[ProgramConfiguration] = {
    val parser = new OptionParser[ProgramConfiguration]("pokerbot") {
      head("bitcoin-scala", "1.0")
      opt[String]('n', "network-name") optional() action { (n, c) => c.copy(networkName = n) } text("the bitcoin network to connect to: { testnet, regtest, prod }")
      help("help") text("prints usage")
    }
    parser
  }
}
