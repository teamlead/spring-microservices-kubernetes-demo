package pro.teamlead.kubepay.auth.sdk.controller;

import pro.teamlead.kubepay.auth.sdk.authority.PublicApi;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@RestController
//@PreAuthorize("hasAuthority('user')")
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@PublicApi
public @interface PublicApiController {
}
