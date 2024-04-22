package pro.teamlead.kubepay.auth.sdk.user;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@AuthenticationPrincipal(expression = "@JwtPrincipalExtractor.getPrincipal(#this)")
public @interface JwtPrincipal {
}
