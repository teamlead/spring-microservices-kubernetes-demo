package pro.teamlead.kubepay.auth.sdk.error;

import com.fasterxml.jackson.databind.ObjectMapper;
import pro.teamlead.kubepay.common.json.ObjectMapperBuilder;
import feign.FeignException;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;

import java.util.HashSet;
import java.util.Set;


@Slf4j
public class ApiClientErrorDecoder<T extends Exception> implements ErrorDecoder {

    private static final Set<Integer> APPLICATION_ERROR_STATUSES = new HashSet<>();

    private static final ObjectMapper OBJECT_MAPPER = ObjectMapperBuilder.build();

    static {
        APPLICATION_ERROR_STATUSES.add(HttpStatus.UNPROCESSABLE_ENTITY.value());
        APPLICATION_ERROR_STATUSES.add(HttpStatus.BAD_REQUEST.value());
        APPLICATION_ERROR_STATUSES.add(HttpStatus.FORBIDDEN.value());
    }

    private final Class<T> type;

    public ApiClientErrorDecoder(Class<T> type) {
        this.type = type;
    }

    public Exception decode(final @NotNull String methodKey, final @NotNull Response response) {

        if (!APPLICATION_ERROR_STATUSES.contains(response.status())) {
            return FeignException.errorStatus(methodKey, response);
        }

        if (HttpStatus.FORBIDDEN.value() == response.status()) {
            return new AccessDeniedException(HttpStatus.FORBIDDEN.getReasonPhrase());
        }

        try {
            if (response.body() != null) {
                return OBJECT_MAPPER.readValue(response.body().asInputStream(), type);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return FeignException.errorStatus(methodKey, response);
    }
}
