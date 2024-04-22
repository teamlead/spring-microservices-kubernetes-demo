package pro.teamlead.kubepay.auth.api;

import pro.teamlead.kubepay.auth.api.domain.model.AuthRequest;
import pro.teamlead.kubepay.auth.api.domain.model.AuthToken;
import pro.teamlead.kubepay.auth.application.AuthApplicationService;
import pro.teamlead.kubepay.auth.sdk.controller.UnprotectedApiController;
import io.micrometer.observation.annotation.Observed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;


@Slf4j
@RequiredArgsConstructor
@UnprotectedApiController
@Observed
public class AuthApiController implements AuthApi {

    private final AuthApplicationService authApplicationService;

    @Override
    public AuthToken login(@Validated @RequestBody AuthRequest request) {
        return authApplicationService.handleLogin(request);
    }

    @Override
    public AuthToken signup(@Validated @RequestBody AuthRequest request) {
        return authApplicationService.handleSignup(request);
    }

    @Override
    public AuthToken serviceToken(String key) {
        return authApplicationService.handleService(key);
    }
}
