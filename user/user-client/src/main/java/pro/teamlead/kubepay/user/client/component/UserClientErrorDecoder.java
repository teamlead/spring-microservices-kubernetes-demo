package pro.teamlead.kubepay.user.client.component;

import pro.teamlead.kubepay.auth.sdk.error.ApiClientErrorDecoder;
import pro.teamlead.kubepay.user.api.domain.exception.UserApiException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserClientErrorDecoder extends ApiClientErrorDecoder<UserApiException> {

    public UserClientErrorDecoder() {
        super(UserApiException.class);
    }

}
