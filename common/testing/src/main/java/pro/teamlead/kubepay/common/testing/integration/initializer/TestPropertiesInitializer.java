package pro.teamlead.kubepay.common.testing.integration.initializer;

import com.github.tomakehurst.wiremock.WireMockServer;
import java.util.Map;

import pro.teamlead.kubepay.common.testing.integration.configuration.WireMockTestConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;

import static pro.teamlead.kubepay.common.testing.integration.configuration.Constants.ENV_PROPERTY_IS_WIREMOCK_ENABLED;
import static pro.teamlead.kubepay.common.testing.integration.configuration.Constants.ENV_PROPERTY_WIREMOCK_URL;

@Slf4j
public class TestPropertiesInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        final ConfigurableEnvironment environment = applicationContext.getEnvironment();
        final MutablePropertySources propertySources = environment.getPropertySources();

        if (environment.getProperty(ENV_PROPERTY_IS_WIREMOCK_ENABLED, boolean.class, false)) {
            WireMockServer mockServer = WireMockTestConfiguration.getMockServer();
            Map<String, Object> map = Map.of(
                    ENV_PROPERTY_WIREMOCK_URL,
                    mockServer.baseUrl()
            );

            propertySources.addFirst(new MapPropertySource("testWireMockPropertySource", map));
        }
    }
}

