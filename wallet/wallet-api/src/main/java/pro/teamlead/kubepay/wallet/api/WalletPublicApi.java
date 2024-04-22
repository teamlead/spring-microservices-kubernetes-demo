package pro.teamlead.kubepay.wallet.api;

import pro.teamlead.kubepay.auth.sdk.api.PublicApi;
import pro.teamlead.kubepay.wallet.api.domain.model.TransferFundsRequest;
import pro.teamlead.kubepay.wallet.api.domain.model.WalletInfo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import pro.teamlead.kubepay.auth.sdk.user.JwtPrincipal;
import pro.teamlead.kubepay.auth.sdk.user.UserPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static pro.teamlead.kubepay.wallet.api.WalletApiMethodDictionary.*;

@PublicApi
public interface WalletPublicApi {

    @GetMapping(WALLET_INFO)
    WalletInfo getInfo(@JwtPrincipal UserPrincipal user);

    @GetMapping(WALLET_TOPUP)
    WalletInfo topUp(@JwtPrincipal UserPrincipal user);

    @PostMapping(WALLET_TRANSFER_FUNDS)
    WalletInfo transferFunds(@JwtPrincipal UserPrincipal user,
                             @Validated @RequestBody TransferFundsRequest request);
}
