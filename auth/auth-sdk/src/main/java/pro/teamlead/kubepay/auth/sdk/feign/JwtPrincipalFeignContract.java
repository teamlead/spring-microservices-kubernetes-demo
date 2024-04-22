package pro.teamlead.kubepay.auth.sdk.feign;

import pro.teamlead.kubepay.auth.sdk.user.JwtPrincipal;
import feign.MethodMetadata;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.http.HttpHeaders;

import java.lang.annotation.Annotation;
import java.util.List;

public class JwtPrincipalFeignContract extends SpringMvcContract {

    @Override
    protected boolean processAnnotationsOnParameter(MethodMetadata data,
                                                    Annotation[] annotations,
                                                    int paramIndex) {

        for (Annotation annotation: annotations) {

            if (annotation instanceof JwtPrincipal) {
                String name = HttpHeaders.AUTHORIZATION;
                nameParam(data, name, paramIndex);
                data.template().header(name, List.of(String.format("{%s}", name)));
                data.indexToExpander().put(paramIndex, new UserPrincipalTokenExpander());
                return true;
            }
        }

        return super.processAnnotationsOnParameter(data, annotations, paramIndex);
    }

}
