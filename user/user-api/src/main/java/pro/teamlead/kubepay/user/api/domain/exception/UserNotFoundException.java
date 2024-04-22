package pro.teamlead.kubepay.user.api.domain.exception;

public class UserNotFoundException extends UserApiException {

    public UserNotFoundException() {
        super("User Not Found");
    }

    public static final String TYPE = "USER_NOT_FOUND";

    @Override
    public String getType() {
        return TYPE;
    }
}
