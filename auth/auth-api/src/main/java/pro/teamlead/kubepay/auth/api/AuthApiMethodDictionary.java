package pro.teamlead.kubepay.auth.api;

public final class AuthApiMethodDictionary {

    private AuthApiMethodDictionary() {
    }

    public static final String AUTH_SIGNUP = "/signup";

    public static final String AUTH_LOGIN = "/login";

    public static final String AUTH_LOGOUT = "/logout";

    public static final String SERVICE_TOKEN = "/service/{key}";
}
