package pro.teamlead.kubepay.gateway.filters;

import pro.teamlead.kubepay.gateway.component.SimpleInMemoryRateLimiter;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RateLimiter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Configuration
public class RateLimiterConfiguration {

    @Bean("customRateLimiter")
    RateLimiter<Void> customRateLimiter() {
        return new SimpleInMemoryRateLimiter();
    }

    @Bean("customKeyResolver")
    KeyResolver customKeyResolver() {
        return exchange -> Mono.just(
                Optional.ofNullable(exchange.getRequest().getHeaders().getFirst(HttpHeaders.USER_AGENT))
                        .orElse("default")
        );
    }
}
