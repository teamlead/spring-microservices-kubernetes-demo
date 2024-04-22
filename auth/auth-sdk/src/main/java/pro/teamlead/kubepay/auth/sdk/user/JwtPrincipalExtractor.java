package pro.teamlead.kubepay.auth.sdk.user;

import pro.teamlead.kubepay.auth.sdk.authority.Authority;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

public class JwtPrincipalExtractor {

    public AuthPrincipal getPrincipal(final Jwt jwt) {

        var authorities  = SecurityContextHolder
                .getContext()
                .getAuthentication().getAuthorities();

        for (GrantedAuthority authority : authorities) {
            if (Authority.ROLE_SERVICE.equals(authority)) {
                return new ServicePrincipal(jwt.getSubject(), jwt.getTokenValue());
            }
            if (Authority.ROLE_USER.equals(authority)) {
                return new UserPrincipal(jwt.getSubject(), jwt.getTokenValue());
            }
        }

        throw new AccessDeniedException("Unknown JWT Scope");
    }
}
