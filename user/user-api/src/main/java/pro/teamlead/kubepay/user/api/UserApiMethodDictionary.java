package pro.teamlead.kubepay.user.api;

public final class UserApiMethodDictionary {

    private UserApiMethodDictionary() {

    }

    public static final String CREATE_USER = "/create";

    public static final String GET_PASSWORD_HASH = "/getPasswordHash/{user}";

    public static final String GET_MY_INFO = "/me";

    public static final String GET_USER_INFO = "/info/{user}";
}
