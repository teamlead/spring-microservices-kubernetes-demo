package pro.teamlead.kubepay.user.client.starter;

import pro.teamlead.kubepay.user.client.UserClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ConditionalOnMissingBean({UserClient.class})
@Import({UserClientFeignConfiguration.class})
public class UserClientAutoConfiguration {
}
