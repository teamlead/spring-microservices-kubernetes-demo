package pro.teamlead.kubepay.auth.sdk.controller;

import pro.teamlead.kubepay.auth.sdk.authority.ServiceApi;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@RestController
//@PreAuthorize("hasAuthority('service')")
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@ServiceApi
public @interface ServiceApiController {
}
