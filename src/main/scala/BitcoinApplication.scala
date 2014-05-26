import com.google.bitcoin.core.ECKey
import com.google.bitcoin.kits.WalletAppKit
import com.google.bitcoin.params.{MainNetParams, RegTestParams, TestNet3Params}
import java.io.File

class BitcoinApplication(configuration: ProgramConfiguration) {
  def run() {
    val kit = createWalletAppKit()
    kit.startAndWait()
    kit.stopAndWait()
  }

  private def createWalletAppKit() = {
    val networkParams = this.networkParams
    val kit = new WalletAppKit(networkParams._1, new File("."), networkParams._2) {
      override def onSetupCompleted() = {
        if (wallet().getKeychainSize() < 1)
          wallet().addKey(new ECKey());
      }
    }
    if (configuration.networkName == "regtest") {
      kit.connectToLocalHost()
    }
    kit
  }

  private def networkParams = {
    configuration.networkName match {
      case "testnet" => (TestNet3Params.get, "forwarding-service-testnet")
      case "regtest" => (RegTestParams.get, "forwarding-service-regtest")
      case "prod" => (MainNetParams.get, "forwarding-service")
      case _ => throw new Exception(s"Unknown network name: ${configuration.networkName}")
    }
  }
}
