package com.p319ss.android.downloadlib.utils;

import java.io.File;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.ss.android.downloadlib.utils.u */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C10068u {
    /* renamed from: mb */
    public static long m6980mb(File file) {
        if (file == null || !file.exists()) {
            return 0L;
        }
        return m6979mb(file, file.lastModified(), 0);
    }

    /* renamed from: mb */
    private static long m6979mb(File file, long j, int i) {
        File[] listFiles;
        if (file == null || !file.exists()) {
            return j;
        }
        long max = Math.max(j, file.lastModified());
        int i2 = i + 1;
        if (i2 >= 50) {
            return max;
        }
        if (file.isDirectory() && (listFiles = file.listFiles()) != null) {
            for (File file2 : listFiles) {
                max = Math.max(max, m6979mb(file2, max, i2));
            }
        }
        return max;
    }
}
