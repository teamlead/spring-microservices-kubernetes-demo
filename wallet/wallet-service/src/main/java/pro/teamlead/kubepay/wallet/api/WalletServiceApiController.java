package pro.teamlead.kubepay.wallet.api;

import pro.teamlead.kubepay.auth.sdk.authority.CanCreateWallet;
import pro.teamlead.kubepay.auth.sdk.user.JwtPrincipal;
import pro.teamlead.kubepay.auth.sdk.user.ServicePrincipal;
import pro.teamlead.kubepay.auth.sdk.controller.ServiceApiController;
import pro.teamlead.kubepay.wallet.api.domain.model.CreateWalletRequest;
import pro.teamlead.kubepay.wallet.api.domain.model.WalletInfo;
import pro.teamlead.kubepay.wallet.application.WalletApplicationService;
import io.micrometer.observation.annotation.Observed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;


@Slf4j
@RequiredArgsConstructor
@ServiceApiController
@Observed
public class WalletServiceApiController implements WalletServiceApi {

    private final WalletApplicationService walletApplicationService;

    @Override
    @CanCreateWallet
    public WalletInfo createWallet(@JwtPrincipal ServicePrincipal principal,
                                   @Validated @RequestBody CreateWalletRequest request) {
        return walletApplicationService.createWallet(request);
    }

}
