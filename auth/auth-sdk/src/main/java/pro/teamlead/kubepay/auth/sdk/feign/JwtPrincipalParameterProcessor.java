package pro.teamlead.kubepay.auth.sdk.feign;

import pro.teamlead.kubepay.auth.sdk.user.JwtPrincipal;
import org.springframework.http.HttpHeaders;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Collection;

import feign.MethodMetadata;
import org.springframework.cloud.openfeign.AnnotatedParameterProcessor;

public class JwtPrincipalParameterProcessor implements AnnotatedParameterProcessor {

    private static final Class<JwtPrincipal> ANNOTATION = JwtPrincipal.class;

    public Class<? extends Annotation> getAnnotationType() {
        return ANNOTATION;
    }

    public boolean processArgument(AnnotatedParameterProcessor.AnnotatedParameterContext context,
                                   Annotation annotation, Method method) {
        int parameterIndex = context.getParameterIndex();
        MethodMetadata data = context.getMethodMetadata();
        String name = HttpHeaders.AUTHORIZATION;
        context.setParameterName(name);
        Collection<String> header = context.setTemplateParameter(name, (Collection) data.template().headers().get(name));
        data.template().header(name, header);
        data.indexToExpander().put(parameterIndex, new UserPrincipalTokenExpander());
        return true;
    }
}
