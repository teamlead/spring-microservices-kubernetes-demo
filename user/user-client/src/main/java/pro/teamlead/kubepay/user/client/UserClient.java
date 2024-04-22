package pro.teamlead.kubepay.user.client;

import pro.teamlead.kubepay.user.api.UserApi;
import pro.teamlead.kubepay.user.client.configuration.UserClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(
        value = "user-client",
        url = "${user.url}",
        configuration = {UserClientConfiguration.class}
)
public interface UserClient extends UserApi {

}
