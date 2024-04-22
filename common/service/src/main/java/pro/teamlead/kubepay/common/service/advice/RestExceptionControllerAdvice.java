package pro.teamlead.kubepay.common.service.advice;

import pro.teamlead.kubepay.common.api.domain.exception.ApiException;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestExceptionControllerAdvice extends ResponseEntityExceptionHandler {

    public static final String DEFAULT_ERROR_TYPE = "ERROR";

    @ExceptionHandler(ApiException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handle(final @NotNull ApiException e) {
        return new ApiError(e);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {

        FieldError fieldError = ex.getBindingResult().getFieldError();
        String fieldName = fieldError != null ? fieldError.getField() : "unknown";
        String errorMessage = fieldError != null ? fieldError.getDefaultMessage() : "unknown error";

        ApiError responseEntity = new ApiError("Field '" + fieldName + "' " + errorMessage);
        return new ResponseEntity<>(responseEntity, headers, status);
    }

    @Getter
    public static class ApiError {

        private final String message;
        private final String type;

        public ApiError(ApiException e) {
            this.message = e.getMessage();
            this.type = e.getType();
        }

        public ApiError(String message) {
            this.message = message;
            this.type = DEFAULT_ERROR_TYPE;
        }
    }
}
