package pro.teamlead.kubepay.user.client.configuration;

import pro.teamlead.kubepay.common.client.configuration.CommonFeignClientConfiguration;
import pro.teamlead.kubepay.user.client.component.UserClientErrorDecoder;
import pro.teamlead.kubepay.user.client.component.UserClientRequestInterceptor;
import feign.RequestInterceptor;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;

public class UserClientConfiguration extends CommonFeignClientConfiguration {

    @Bean
    @Override
    public ErrorDecoder errorDecoder() {
        return new UserClientErrorDecoder();
    }

    @Bean
    @Override
    public RequestInterceptor requestInterceptor() {
        return new UserClientRequestInterceptor();
    }
}
