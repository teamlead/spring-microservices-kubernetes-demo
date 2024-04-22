package pro.teamlead.kubepay.auth.sdk.feign;

import pro.teamlead.kubepay.auth.sdk.user.AuthPrincipal;
import feign.Param;

public class UserPrincipalTokenExpander implements Param.Expander {

    @Override
    public String expand(Object value) {
        if (value instanceof AuthPrincipal principal) {
            return "Bearer " + principal.getToken();
        }
        return null;
    }
}
