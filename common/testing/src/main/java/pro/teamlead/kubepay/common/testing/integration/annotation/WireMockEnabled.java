package pro.teamlead.kubepay.common.testing.integration.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import pro.teamlead.kubepay.common.testing.integration.configuration.WireMockTestConfiguration;
import org.springframework.boot.test.autoconfigure.properties.PropertyMapping;
import org.springframework.context.annotation.Import;

import static pro.teamlead.kubepay.common.testing.integration.configuration.Constants.ENV_PROPERTY_MAPPING_WIREMOCK;

@Documented
@Target({ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@PropertyMapping(ENV_PROPERTY_MAPPING_WIREMOCK)
@Import(WireMockTestConfiguration.class)
public @interface WireMockEnabled {
    boolean enabled() default true;
}
