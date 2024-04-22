package pro.teamlead.kubepay.auth.configuration;

import pro.teamlead.kubepay.auth.component.ServiceTokenProvider;
import pro.teamlead.kubepay.auth.sdk.user.ServicePrincipal;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

@Slf4j
@Configuration
@AllArgsConstructor
public class ServicePrincipalConfiguration {

    private final ServiceTokenProvider serviceTokenProvider;

    @Bean
    @Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
    public ServicePrincipal servicePrincipal(@Value("${spring.application.name}") final String service) {

        var token = serviceTokenProvider.createSelfServiceToken();

        return new ServicePrincipal(service, token.token());
    }
}
