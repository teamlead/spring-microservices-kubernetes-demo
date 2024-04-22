package pro.teamlead.kubepay.auth.api.domain.exception;

public class InvalidCredentialsException extends AuthApiException {

    public InvalidCredentialsException() {
        super("Invalid Credentials");
    }

    public static final String TYPE = "INVALID_CREDENTIALS";

    @Override
    public String getType() {
        return TYPE;
    }
}
