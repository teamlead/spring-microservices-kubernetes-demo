package pro.teamlead.kubepay.user.api;

import pro.teamlead.kubepay.auth.sdk.authority.CanCreateUser;
import pro.teamlead.kubepay.auth.sdk.authority.CanIntroinspectUser;
import pro.teamlead.kubepay.auth.sdk.authority.CanViewUserPassword;
import pro.teamlead.kubepay.auth.sdk.user.JwtPrincipal;
import pro.teamlead.kubepay.auth.sdk.user.ServicePrincipal;
import pro.teamlead.kubepay.auth.sdk.controller.ServiceApiController;
import pro.teamlead.kubepay.user.api.domain.model.CreateUserRequest;
import pro.teamlead.kubepay.user.api.domain.model.UserInfo;
import pro.teamlead.kubepay.user.application.UserApplicationService;
import io.micrometer.observation.annotation.Observed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@RequiredArgsConstructor
@ServiceApiController
@Observed
public class UserServiceApiController implements UserServiceApi {

    private final UserApplicationService userApplicationService;

    @Override
    @CanCreateUser
    public UserInfo createUser(@JwtPrincipal ServicePrincipal service,
                               @Validated @RequestBody CreateUserRequest request) {
        return userApplicationService.createUser(request);
    }

    @Override
    @CanIntroinspectUser
    public UserInfo getUserInfo(ServicePrincipal service,
                                String user) {
        return userApplicationService.getUserInfo(user);
    }

    @Override
    @CanViewUserPassword
    public String getPasswordHash(ServicePrincipal service,
                                  String user) {
        return userApplicationService.getPasswordHash(user);
    }
}
