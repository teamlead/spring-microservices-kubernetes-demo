package pro.teamlead.kubepay.wallet.api;

import pro.teamlead.kubepay.auth.sdk.user.JwtPrincipal;
import pro.teamlead.kubepay.auth.sdk.user.UserPrincipal;
import pro.teamlead.kubepay.auth.sdk.controller.PublicApiController;
import pro.teamlead.kubepay.wallet.api.domain.model.TransferFundsRequest;
import pro.teamlead.kubepay.wallet.api.domain.model.WalletInfo;
import pro.teamlead.kubepay.wallet.application.WalletApplicationService;
import io.micrometer.observation.annotation.Observed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

import java.math.BigDecimal;

@Slf4j
@RequiredArgsConstructor
@PublicApiController
@Observed
public class WalletPublicApiController implements WalletPublicApi {

    private final WalletApplicationService walletApplicationService;

    @Override
    public WalletInfo getInfo(@JwtPrincipal UserPrincipal user) {
        return walletApplicationService.getInfo(user);
    }

    @Override
    public WalletInfo topUp(@JwtPrincipal UserPrincipal user) {
        walletApplicationService.topUp(user, BigDecimal.TEN.pow(2));
        return getInfo(user);
    }

    @Override
    public WalletInfo transferFunds(@JwtPrincipal UserPrincipal user,
                                    @Validated @RequestBody TransferFundsRequest request) {
        walletApplicationService.transferFunds(user, request);
        return getInfo(user);
    }
}
