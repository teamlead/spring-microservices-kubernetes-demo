package pro.teamlead.kubepay.auth.client.component;

import pro.teamlead.kubepay.auth.api.domain.exception.AuthApiException;
import pro.teamlead.kubepay.auth.sdk.error.ApiClientErrorDecoder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuthClientErrorDecoder extends ApiClientErrorDecoder<AuthApiException> {

    public AuthClientErrorDecoder() {
        super(AuthApiException.class);
    }

}
