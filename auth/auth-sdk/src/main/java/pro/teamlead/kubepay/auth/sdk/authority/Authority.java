package pro.teamlead.kubepay.auth.sdk.authority;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Authority implements GrantedAuthority {

    public static final Authority ROLE_SERVICE = new Authority("ROLE_SERVICE");
    public static final Authority ROLE_USER = new Authority("ROLE_USER");
    public static final Authority AUTHORITY_INTROSPECT_USER = new Authority("user:info");
    public static final Authority AUTHORITY_USER_CREATE = new Authority("user:create");
    public static final Authority AUTHORITY_VIEW_USER_PASSWORD = new Authority("user:password");
    public static final Authority AUTHORITY_WALLET_CREATE = new Authority("wallet:create");

    @SuppressWarnings("squid:S1700")
    private String authority;

    @SuppressWarnings("squid:S1201")
    public boolean equals(GrantedAuthority o) {
        if (o == null) return false;
        if (this == o) return true;
        return Objects.equals(authority, o.getAuthority());
    }

}
