package pro.teamlead.kubepay.user.api;

import pro.teamlead.kubepay.auth.sdk.api.PublicApi;
import pro.teamlead.kubepay.user.api.domain.model.UserInfo;
import org.springframework.web.bind.annotation.GetMapping;
import pro.teamlead.kubepay.auth.sdk.user.JwtPrincipal;
import pro.teamlead.kubepay.auth.sdk.user.UserPrincipal;

@PublicApi
public interface UserPublicApi {

    @GetMapping(UserApiMethodDictionary.GET_MY_INFO)
    UserInfo getMyInfo(@JwtPrincipal UserPrincipal user);
}
