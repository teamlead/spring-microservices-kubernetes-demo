package pro.teamlead.kubepay.wallet.client;

import pro.teamlead.kubepay.wallet.api.WalletApi;
import org.springframework.cloud.openfeign.FeignClient;
import pro.teamlead.kubepay.wallet.client.configuration.WalletClientConfiguration;

@FeignClient(
        value = "wallet-client",
        url = "${wallet.url}",
        configuration = {WalletClientConfiguration.class}
)
public interface WalletClient extends WalletApi {

}
