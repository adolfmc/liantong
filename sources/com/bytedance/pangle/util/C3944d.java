package com.bytedance.pangle.util;

import android.support.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.bytedance.pangle.util.d */
/* loaded from: E:\10762272_dexfile_execute.dex */
public final class C3944d {
    /* renamed from: a */
    public static <T> boolean m16643a(@Nullable T[] tArr) {
        return tArr == null || tArr.length == 0;
    }

    /* renamed from: a */
    public static <T> boolean m16641a(@Nullable T[] tArr, T[] tArr2) {
        if (tArr2 == null) {
            return true;
        }
        for (T t : tArr2) {
            if (!m16642a(tArr, t)) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: a */
    private static <T> boolean m16642a(@Nullable T[] tArr, T t) {
        int i;
        if (tArr != null) {
            i = 0;
            while (i < tArr.length) {
                if (tArr[i] == t || (tArr[i] != null && tArr[i].equals(t))) {
                    break;
                }
                i++;
            }
        }
        i = -1;
        return i != -1;
    }
}
