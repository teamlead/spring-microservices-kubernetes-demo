package pro.teamlead.kubepay.common.testing.integration.configuration;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.common.Slf4jNotifier;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import static pro.teamlead.kubepay.common.testing.integration.configuration.Constants.ENV_PROPERTY_IS_WIREMOCK_ENABLED;

@Slf4j
@TestConfiguration
@ConditionalOnProperty(ENV_PROPERTY_IS_WIREMOCK_ENABLED)
public class WireMockTestConfiguration {

    @SuppressWarnings("squid:S3077")
    private static volatile WireMockServer mockServer;

    public static synchronized WireMockServer getMockServer() {
        if (mockServer != null) {
            if (!mockServer.isRunning()) {
                mockServer = null;
            } else {
                return mockServer;
            }
        }
        mockServer = new WireMockServer(
                WireMockConfiguration.options()
                        .dynamicPort()
                        .notifier(new Slf4jNotifier(true))
        );
        mockServer.start();

        return mockServer;
    }

    @Bean
    public WireMockServer wireMockServer() {
        return getMockServer();
    }
}

