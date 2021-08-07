package org.atbyuan.note.aspect;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @author atbyuan
 * @since 2021年8月6日 22点56分
 **/
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DsSelector {

    private static final ThreadLocal<String> DS = new ThreadLocal<>();

    public static String get() {
        return DS.get();
    }

    public static void set(String ds) {
        DS.set(ds);
    }

    public static void remove() {
        DS.remove();
    }

}
