package pro.teamlead.kubepay.auth.sdk.configuration;

import pro.teamlead.kubepay.auth.sdk.user.JwtPrincipalExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthSdkAutoConfiguration {

    @Bean("JwtPrincipalExtractor")
    public JwtPrincipalExtractor extractor() {
        return new JwtPrincipalExtractor();
    }
}
