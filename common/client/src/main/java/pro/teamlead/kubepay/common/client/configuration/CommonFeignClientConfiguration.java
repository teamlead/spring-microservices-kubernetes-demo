package pro.teamlead.kubepay.common.client.configuration;

import feign.Contract;
import feign.Logger;
import feign.RequestInterceptor;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.zalando.logbook.Logbook;
import org.zalando.logbook.openfeign.FeignLogbookLogger;
import pro.teamlead.kubepay.auth.sdk.feign.JwtPrincipalFeignContract;

public abstract class CommonFeignClientConfiguration {

    @Bean
    @SuppressWarnings("squid:S125")
    public Contract feignContract() {

        // The commented-out code below is an alternative way to create a Feign Contract
        // using individual argument resolvers, including the JwtPrincipalParameterProcessor.
        // It's an alternative approach to using the JwtPrincipalFeignContract.
        //
        // List<AnnotatedParameterProcessor> argumentResolvers = new ArrayList();
        // argumentResolvers.add(new RequestHeaderParameterProcessor());
        // argumentResolvers.add(new JwtPrincipalParameterProcessor());
        // ...
        // return new SpringMvcContract(argumentResolvers);

        return new JwtPrincipalFeignContract();
    }

    @Bean
    public Logger logger(Logbook logbook) {
        return new FeignLogbookLogger(logbook);
    }

    @Bean
    public Logger.Level loglevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public abstract ErrorDecoder errorDecoder();

    @Bean
    public abstract RequestInterceptor requestInterceptor();
}
