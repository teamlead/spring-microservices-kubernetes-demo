package pro.teamlead.kubepay.auth.client.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import pro.teamlead.kubepay.auth.client.AuthClient;
import pro.teamlead.kubepay.auth.sdk.user.ServicePrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;


@Configuration
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "security", name = "customServicePrincipal", havingValue = "false", matchIfMissing = true)
public class ServicePrincipalConfiguration {

    private final AuthClient authClient;

    /**
     * WARNING: This implementation is CONCEPTUAL and NOT production-ready.
     * You should implement proper caching mechanisms for the token to avoid
     * unnecessary calls and potential rate limits. Always ensure security
     * and efficiency when handling tokens in a real-world scenario.
     */
    @Bean
    @Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
    public ServicePrincipal servicePrincipal(@Value("${spring.application.name}") final String service,
                                             @Value("${service.key}") final String key) {
        var token = authClient.serviceToken(key);
        return new ServicePrincipal(service, token.token());
    }
}
