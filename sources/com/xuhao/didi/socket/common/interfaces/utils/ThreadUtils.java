package com.xuhao.didi.socket.common.interfaces.utils;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class ThreadUtils {
    public static void sleep(long j) {
        long j2 = 0;
        long j3 = j;
        long j4 = 0;
        while (true) {
            long j5 = j2 - j4;
            if (j5 >= j3) {
                return;
            }
            j3 -= j5;
            try {
                j4 = System.currentTimeMillis();
                Thread.sleep(j3);
                j2 = System.currentTimeMillis();
            } catch (InterruptedException unused) {
                j2 = System.currentTimeMillis();
            }
        }
    }
}
