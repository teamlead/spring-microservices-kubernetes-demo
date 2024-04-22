package pro.teamlead.kubepay.gateway.component;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.util.concurrent.RateLimiter;
import reactor.core.publisher.Mono;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * WARNING: This code is a basic example and may not be production-ready.
 * Please review, test, and adapt as necessary before using in a production environment.
 */
public class SimpleInMemoryRateLimiter implements org.springframework.cloud.gateway.filter.ratelimit.RateLimiter<Void> {

    private final Cache<String, RateLimiter> rateLimiterCache = CacheBuilder.newBuilder()
            .expireAfterAccess(1, TimeUnit.MINUTES) // Evict entries 1 minute after last access
            .build();

    public Mono<Response> isAllowed(String routeId, String id) {
        try {
            RateLimiter limiter = rateLimiterCache.get(id, () -> RateLimiter.create(1.0/60));
            return Mono.just(new Response(limiter.tryAcquire(), Collections.emptyMap()));
        } catch (ExecutionException e) {
            return Mono.just(new Response(false, Collections.emptyMap()));
        }
    }

    @Override
    public Map<String, Void> getConfig() {
        return Collections.emptyMap();
    }

    @Override
    public Class<Void> getConfigClass() {
        return null;
    }

    @Override
    public Void newConfig() {
        return null;
    }
}
