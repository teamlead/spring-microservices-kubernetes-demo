package pro.teamlead.kubepay.common.testing.integration.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import pro.teamlead.kubepay.common.testing.integration.configuration.Constants;
import pro.teamlead.kubepay.common.testing.integration.configuration.JwtConfiguration;
import org.springframework.boot.test.autoconfigure.properties.PropertyMapping;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.AliasFor;
import org.springframework.test.context.ActiveProfiles;

import static pro.teamlead.kubepay.common.testing.integration.configuration.Constants.ENV_PROPERTY_MAPPING_ROOT;

@WireMockEnabled
@ActiveProfiles(Constants.SPRING_TEST_PROFILE_NAME)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@PropertyMapping(ENV_PROPERTY_MAPPING_ROOT)
@Import(JwtConfiguration.class)
public @interface IntegrationTest {

    @AliasFor(annotation = WireMockEnabled.class, attribute = "enabled")
    boolean wireMockEnable() default false;
}


