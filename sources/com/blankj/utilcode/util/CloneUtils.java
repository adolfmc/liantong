package com.blankj.utilcode.util;

import java.lang.reflect.Type;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public final class CloneUtils {
    private CloneUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static <T> T deepClone(T t, Type type) {
        try {
            return (T) UtilsBridge.fromJson(UtilsBridge.toJson(t), type);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
