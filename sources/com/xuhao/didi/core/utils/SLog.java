package com.xuhao.didi.core.utils;

import java.io.PrintStream;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class SLog {
    private static boolean isDebug;

    public static void setIsDebug(boolean z) {
        isDebug = z;
    }

    public static boolean isDebug() {
        return isDebug;
    }

    /* renamed from: e */
    public static void m2258e(String str) {
        if (isDebug) {
            PrintStream printStream = System.err;
            printStream.println("OkSocket, " + str);
        }
    }

    /* renamed from: i */
    public static void m2257i(String str) {
        if (isDebug) {
            PrintStream printStream = System.out;
            printStream.println("OkSocket, " + str);
        }
    }

    /* renamed from: w */
    public static void m2256w(String str) {
        m2257i(str);
    }
}
