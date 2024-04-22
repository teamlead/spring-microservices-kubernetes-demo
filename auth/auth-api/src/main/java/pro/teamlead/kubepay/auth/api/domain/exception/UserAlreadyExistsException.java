package pro.teamlead.kubepay.auth.api.domain.exception;

public class UserAlreadyExistsException extends AuthApiException {

    public UserAlreadyExistsException() {
        super("User already exists");
    }

    public static final String TYPE = "USER_ALREADY_EXISTS";

    @Override
    public String getType() {
        return TYPE;
    }
}
