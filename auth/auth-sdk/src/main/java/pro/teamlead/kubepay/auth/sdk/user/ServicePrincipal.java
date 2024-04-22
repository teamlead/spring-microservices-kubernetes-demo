package pro.teamlead.kubepay.auth.sdk.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ServicePrincipal implements AuthPrincipal {

    private String service;

    private String token;
}
