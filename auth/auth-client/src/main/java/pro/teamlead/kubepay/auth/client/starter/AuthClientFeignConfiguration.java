package pro.teamlead.kubepay.auth.client.starter;

import pro.teamlead.kubepay.auth.client.AuthClient;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.context.annotation.Configuration;

@EnableFeignClients(
        basePackageClasses = {AuthClient.class}
)
@ImportAutoConfiguration({FeignAutoConfiguration.class})
@Configuration
public class AuthClientFeignConfiguration {

}
