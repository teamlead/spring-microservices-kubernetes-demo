package pro.teamlead.kubepay.wallet.client.component;

import pro.teamlead.kubepay.auth.sdk.error.ApiClientErrorDecoder;
import pro.teamlead.kubepay.wallet.api.domain.exception.WalletApiException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WalletClientErrorDecoder extends ApiClientErrorDecoder<WalletApiException> {

    public WalletClientErrorDecoder() {
        super(WalletApiException.class);
    }

}
