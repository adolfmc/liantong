package com.baidu.cloud.util;

import java.lang.reflect.Array;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ArrayUtils {
    public static Object insertElement(Class cls, Object[] objArr, Object obj) {
        Object newInstance = Array.newInstance(cls, objArr.length + 1);
        Array.set(newInstance, 0, obj);
        for (int i = 1; i < objArr.length + 1; i++) {
            Array.set(newInstance, i, objArr[i - 1]);
        }
        return newInstance;
    }

    public static <T> boolean contains(T[] tArr, T t) {
        return indexOf(tArr, t) != -1;
    }

    public static int indexOf(int[] iArr, int i) {
        if (iArr == null) {
            return -1;
        }
        for (int i2 = 0; i2 < iArr.length; i2++) {
            if (iArr[i2] == i) {
                return i2;
            }
        }
        return -1;
    }

    public static long indexOf(long[] jArr, long j) {
        if (jArr == null) {
            return -1L;
        }
        for (int i = 0; i < jArr.length; i++) {
            if (jArr[i] == j) {
                return i;
            }
        }
        return -1L;
    }

    public static <T> int indexOf(T[] tArr, T t) {
        if (tArr == null) {
            return -1;
        }
        for (int i = 0; i < tArr.length; i++) {
            if (equals(tArr[i], t)) {
                return i;
            }
        }
        return -1;
    }

    private static boolean equals(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    public static <T> String join(List<T> list, String str) {
        if (str == null) {
            str = "";
        }
        if (list == null || list.size() == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (T t : list) {
            sb.append(t);
            sb.append(str);
        }
        if (str.length() > 0 && sb.length() > str.length()) {
            sb.delete(sb.length() - str.length(), sb.length());
        }
        return sb.toString();
    }

    public static <T> String join(T[] tArr, String str) {
        if (str == null) {
            str = "";
        }
        if (tArr == null || tArr.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (T t : tArr) {
            sb.append(t);
            sb.append(str);
        }
        if (str.length() > 0 && sb.length() > str.length()) {
            sb.delete(sb.length() - str.length(), sb.length());
        }
        return sb.toString();
    }
}
