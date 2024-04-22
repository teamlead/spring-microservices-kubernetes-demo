package pro.teamlead.kubepay.common.service.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnWebApplication
@ComponentScan(basePackages = "pro.teamlead.kubepay.common.service.advice")
class ApiConfiguration {

}
