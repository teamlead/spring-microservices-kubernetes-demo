package pro.teamlead.kubepay.common.testing.integration.configuration;

public final class Constants {

    private Constants() {

    }

    public static final String SPRING_TEST_PROFILE_NAME = "test";

    public static final String ENV_PROPERTY_MAPPING_ROOT = "com.dats.test";

    public static final String ENV_PROPERTY_MAPPING_WIREMOCK = "com.dats.test.wire-mock";

    public static final String ENV_PROPERTY_IS_WIREMOCK_ENABLED = "com.dats.test.wire-mock.enabled";

    public static final String ENV_PROPERTY_WIREMOCK_URL = "wire-mock.url";
}
