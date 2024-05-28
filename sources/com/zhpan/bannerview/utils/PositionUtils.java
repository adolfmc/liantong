package com.zhpan.bannerview.utils;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class PositionUtils {
    public static int getRealPosition(boolean z, int i, int i2) {
        if (z) {
            i--;
        }
        return (i + i2) % i2;
    }
}
