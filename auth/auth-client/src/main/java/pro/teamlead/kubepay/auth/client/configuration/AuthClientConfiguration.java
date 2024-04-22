package pro.teamlead.kubepay.auth.client.configuration;

import org.zalando.logbook.Logbook;
import org.zalando.logbook.openfeign.FeignLogbookLogger;
import pro.teamlead.kubepay.auth.client.component.AuthClientErrorDecoder;
import feign.Logger;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;

public class AuthClientConfiguration {

    @Bean
    public Logger logger(Logbook logbook) {
        return new FeignLogbookLogger(logbook);
    }

    @Bean
    public Logger.Level loglevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public ErrorDecoder errorDecoder() {
        return new AuthClientErrorDecoder();
    }

}
