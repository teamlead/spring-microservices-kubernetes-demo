package pro.teamlead.kubepay.common.util;

import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.NoSuchElementException;

@UtilityClass
public class MapUtil {

    public static <K, V> K getKeyByValue(@NotNull Map<K, V> map, V value) {

        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (value.equals(entry.getValue())) {
                return entry.getKey();
            }
        }

        throw new NoSuchElementException();
    }
}
