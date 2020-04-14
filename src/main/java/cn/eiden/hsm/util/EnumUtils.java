package cn.eiden.hsm.util;

/**
 * @author Eiden J.P Zhou
 * @date 2020/4/14 9:19
 */
public class EnumUtils {
    /**
     * A common method for all enums since they can't have another base class
     * @param <T> Enum type
     * @param c enum type. All enums must be all caps.
     * @param string case insensitive
     * @return corresponding enum, or null
     */
    public static <T extends Enum<T>> T getEnumFromString(Class<T> c, String string) {
        if( c != null && string != null ) {
            try {
                return Enum.valueOf(c, string.trim().toUpperCase());
            } catch(IllegalArgumentException ex) {
                return null;
            }
        }
        return null;
    }
}
