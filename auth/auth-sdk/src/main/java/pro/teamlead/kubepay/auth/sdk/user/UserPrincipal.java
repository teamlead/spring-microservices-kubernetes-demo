package pro.teamlead.kubepay.auth.sdk.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserPrincipal implements AuthPrincipal {

    private String user;

    private String token;
}
