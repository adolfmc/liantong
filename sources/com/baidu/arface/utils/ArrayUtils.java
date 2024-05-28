package com.baidu.arface.utils;

import java.lang.reflect.Array;
import java.security.InvalidParameterException;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ArrayUtils {
    public static String arrayToString(Object obj) {
        if (obj == null) {
            return null;
        }
        if (!obj.getClass().isArray()) {
            throw new InvalidParameterException("Not a primitive array: " + obj.getClass());
        }
        int length = Array.getLength(obj);
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        int i = 0;
        while (i < length) {
            sb.append(Array.get(obj, i));
            sb.append(',');
            sb.append(' ');
            i++;
        }
        if (i > 0) {
            sb.delete(sb.length() - 2, sb.length());
        }
        sb.append(']');
        return sb.toString();
    }
}
