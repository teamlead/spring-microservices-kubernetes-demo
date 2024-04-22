package pro.teamlead.kubepay.user.client.starter;

import pro.teamlead.kubepay.user.client.UserClient;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.context.annotation.Configuration;

@EnableFeignClients(
        basePackageClasses = {UserClient.class}
)
@ImportAutoConfiguration({FeignAutoConfiguration.class})
@Configuration
public class UserClientFeignConfiguration {

}
