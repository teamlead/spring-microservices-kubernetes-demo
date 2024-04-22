package pro.teamlead.kubepay.user.api;

import pro.teamlead.kubepay.auth.sdk.api.ServiceApi;
import pro.teamlead.kubepay.auth.sdk.user.JwtPrincipal;
import pro.teamlead.kubepay.auth.sdk.user.ServicePrincipal;
import pro.teamlead.kubepay.user.api.domain.exception.UserNotFoundException;
import pro.teamlead.kubepay.user.api.domain.model.CreateUserRequest;
import pro.teamlead.kubepay.user.api.domain.model.UserInfo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@ServiceApi
public interface UserServiceApi {

    @PostMapping(UserApiMethodDictionary.CREATE_USER)
    UserInfo createUser(@JwtPrincipal ServicePrincipal service,
                        @Validated @RequestBody CreateUserRequest request);

    @GetMapping(UserApiMethodDictionary.GET_USER_INFO)
    UserInfo getUserInfo(@JwtPrincipal ServicePrincipal service,
                         @PathVariable("user") String user) throws UserNotFoundException;

    @GetMapping(UserApiMethodDictionary.GET_PASSWORD_HASH)
    String getPasswordHash(@JwtPrincipal ServicePrincipal service,
                           @PathVariable("user") String user) throws UserNotFoundException;
}
