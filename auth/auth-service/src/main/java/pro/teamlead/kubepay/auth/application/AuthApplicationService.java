package pro.teamlead.kubepay.auth.application;

import pro.teamlead.kubepay.auth.api.domain.model.AuthRequest;
import pro.teamlead.kubepay.auth.api.domain.model.AuthToken;
import pro.teamlead.kubepay.auth.domain.model.JwtToken;
import pro.teamlead.kubepay.auth.domain.service.AuthService;
import pro.teamlead.kubepay.auth.component.ServiceTokenProvider;
import io.micrometer.observation.annotation.Observed;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
@Observed
public class AuthApplicationService {

    private final AuthService authService;

    private final ServiceTokenProvider serviceTokenProvider;

    public AuthToken handleLogin(AuthRequest request) {
        JwtToken token = authService.authUser(request.user(),
                request.password());
        return new AuthToken(token.token());
    }

    public AuthToken handleService(@NotNull String key) {
        var token = serviceTokenProvider.getServiceToken(key);
        return new AuthToken(token.token());
    }

    public AuthToken handleSignup(AuthRequest request) {
        JwtToken token = authService.createUser(request.user(),
                request.password());
        return new AuthToken(token.token());
    }
}
