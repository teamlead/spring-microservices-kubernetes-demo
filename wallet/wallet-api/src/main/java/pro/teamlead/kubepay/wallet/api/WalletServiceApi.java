package pro.teamlead.kubepay.wallet.api;

import pro.teamlead.kubepay.auth.sdk.api.ServiceApi;
import pro.teamlead.kubepay.auth.sdk.user.JwtPrincipal;
import pro.teamlead.kubepay.auth.sdk.user.ServicePrincipal;
import pro.teamlead.kubepay.wallet.api.domain.exception.WalletAlreadyCreatedException;
import pro.teamlead.kubepay.wallet.api.domain.model.CreateWalletRequest;
import pro.teamlead.kubepay.wallet.api.domain.model.WalletInfo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@ServiceApi
public interface WalletServiceApi {

    @PostMapping(WalletApiMethodDictionary.WALLET_CREATE)
    WalletInfo createWallet(@JwtPrincipal ServicePrincipal user,
                            @Validated @RequestBody CreateWalletRequest request) throws WalletAlreadyCreatedException;
}
