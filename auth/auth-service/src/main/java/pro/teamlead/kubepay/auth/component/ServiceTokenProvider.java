package pro.teamlead.kubepay.auth.component;

import pro.teamlead.kubepay.auth.api.domain.exception.InvalidCredentialsException;
import pro.teamlead.kubepay.auth.domain.model.JwtToken;
import pro.teamlead.kubepay.auth.sdk.util.JwtTokenProvider;
import pro.teamlead.kubepay.auth.sdk.authority.Authority;
import pro.teamlead.kubepay.common.util.MapUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.NoSuchElementException;


@Component
@RequiredArgsConstructor
@SuppressWarnings("squid:S6857")
public class ServiceTokenProvider {

    private final JwtTokenProvider jwtTokenProvider;

    @Value("#{${services}}")
    private Map<String, String> secrets;

    @Value("#{${scopes}}")
    private Map<String, Authority[]> scopes;

    @Value("${spring.application.name}")
    private String applicationName;

    public JwtToken createSelfServiceToken() {
        var token = jwtTokenProvider.createToken(applicationName, 120,
                scopes.get("auth")
        );
        return new JwtToken(token);
    }

    public JwtToken getServiceToken(String key) {

        try {

            var service = MapUtil.getKeyByValue(secrets, key);
            var token = jwtTokenProvider.createToken(service, 120,
                    scopes.get(service));
            return new JwtToken(token);

        } catch (NoSuchElementException e) {
            throw new InvalidCredentialsException();
        }
    }
}
