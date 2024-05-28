package com.sdk.base.framework.utils.log;

import java.util.HashMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class LiveDataBus3 {
    public static int key;
    private final Map<String, String> bus;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static class SingletonHolder {
        private static final LiveDataBus3 DEFAULT_BUS = new LiveDataBus3();

        private SingletonHolder() {
        }
    }

    private LiveDataBus3() {
        this.bus = new HashMap();
    }

    public static LiveDataBus3 get() {
        return SingletonHolder.DEFAULT_BUS;
    }

    public static boolean init() {
        SingletonHolder.DEFAULT_BUS.bus.clear();
        key = 0;
        return true;
    }

    public static String post(String str) {
        Map<String, String> map = SingletonHolder.DEFAULT_BUS.bus;
        int i = key;
        key = i + 1;
        return map.put(String.valueOf(i), str);
    }

    public static String toStr(int i) {
        Map<String, String> map = SingletonHolder.DEFAULT_BUS.bus;
        String str = "\n";
        int size = map.size();
        for (int i2 = 0; i2 < size; i2++) {
            String str2 = map.get(String.valueOf(i2));
            int indexOf = str2.indexOf("\n时间差=") + 5;
            int indexOf2 = str2.indexOf("ms\n");
            if (indexOf >= 0 && indexOf2 >= 0 && indexOf2 > indexOf) {
                String substring = str2.substring(indexOf, indexOf2);
                if (substring.length() < 5 && Integer.parseInt(substring) >= i) {
                    str = ((str + i2 + ": ") + str2) + "\n-----------------------\n";
                }
            }
        }
        return str;
    }
}
