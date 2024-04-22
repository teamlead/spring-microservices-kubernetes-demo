package pro.teamlead.kubepay.user.api;

import pro.teamlead.kubepay.auth.sdk.user.JwtPrincipal;
import pro.teamlead.kubepay.auth.sdk.user.UserPrincipal;
import pro.teamlead.kubepay.auth.sdk.controller.PublicApiController;
import pro.teamlead.kubepay.user.api.domain.model.UserInfo;
import pro.teamlead.kubepay.user.application.UserApplicationService;
import io.micrometer.observation.annotation.Observed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RequiredArgsConstructor
@PublicApiController
@Observed
public class UserPublicApiController implements UserPublicApi {

    private final UserApplicationService userApplicationService;

    @Override
    public UserInfo getMyInfo(@JwtPrincipal UserPrincipal user) {
        return userApplicationService.getUserInfo(user);
    }
}
