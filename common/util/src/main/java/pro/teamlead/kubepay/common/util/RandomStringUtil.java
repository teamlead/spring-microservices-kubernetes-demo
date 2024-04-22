package pro.teamlead.kubepay.common.util;

import lombok.experimental.UtilityClass;

import java.util.UUID;

@UtilityClass
public class RandomStringUtil {

    private volatile String mock = null;

    public static String generate() {
        return mock != null ? mock : UUID.randomUUID().toString();
    }

    public static void setMock(String mock) {
        RandomStringUtil.mock = mock;
    }

    public static void removeMock() {
        RandomStringUtil.mock = null;
    }
}
