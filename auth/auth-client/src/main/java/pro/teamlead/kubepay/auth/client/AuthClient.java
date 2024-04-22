package pro.teamlead.kubepay.auth.client;

import pro.teamlead.kubepay.auth.api.AuthApi;
import org.springframework.cloud.openfeign.FeignClient;
import pro.teamlead.kubepay.auth.client.configuration.AuthClientConfiguration;

@FeignClient(
        value = "auth-client",
        url = "${auth.url}",
        configuration = {AuthClientConfiguration.class}
)
public interface AuthClient extends AuthApi {

}
