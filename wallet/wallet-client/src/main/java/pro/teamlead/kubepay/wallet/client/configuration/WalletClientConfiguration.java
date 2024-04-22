package pro.teamlead.kubepay.wallet.client.configuration;

import pro.teamlead.kubepay.common.client.configuration.CommonFeignClientConfiguration;
import pro.teamlead.kubepay.wallet.client.component.WalletClientErrorDecoder;
import pro.teamlead.kubepay.wallet.client.component.WalletClientRequestInterceptor;
import feign.codec.ErrorDecoder;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;

public class WalletClientConfiguration extends CommonFeignClientConfiguration {

    @Bean
    @Override
    public ErrorDecoder errorDecoder() {
        return new WalletClientErrorDecoder();
    }

    @Bean
    @Override
    public RequestInterceptor requestInterceptor() {
        return new WalletClientRequestInterceptor();
    }
}
