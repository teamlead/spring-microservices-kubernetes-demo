package pro.teamlead.kubepay.auth.sdk.util;

import pro.teamlead.kubepay.auth.sdk.authority.Authority;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;


@Component
@AllArgsConstructor
public class JwtTokenProvider {

    private final JwtEncoder jwtEncoder;

    public String createToken(String user, Authority... scopes) {
        return createToken(user, -1, scopes);
    }

    public String createToken(String user, int lifeTimeSec, Authority... scopes) {

        var now = Instant.now();

        var builder = JwtClaimsSet.builder();
        var authorities = asList(scopes);

        builder.claim(JwtFields.SCOPE, authorities);
        builder.claim(JwtFields.USER, user);
        builder.claim(JwtFields.ISSUED_AT, now);

        if (lifeTimeSec > 0) {
            builder.claim(JwtFields.EXPIRE_AT, now.plusSeconds(lifeTimeSec));
        }

        JwtClaimsSet claims = builder.build();

        return jwtEncoder
                .encode(JwtEncoderParameters.from(claims))
                .getTokenValue();
    }

    private List<String> asList(Authority... scopes) {
        if (scopes == null) {
            return List.of();
        }
        return Arrays.stream(scopes)
                .map(Authority::getAuthority).toList();
    }
}

