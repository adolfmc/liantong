package com.xiaomi.push;

import android.content.Context;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.xiaomi.push.ac */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C11132ac {
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00dc A[Catch: all -> 0x00f0, IOException -> 0x00f2, LOOP:0: B:41:0x00d6->B:43:0x00dc, LOOP_END, TRY_LEAVE, TryCatch #8 {IOException -> 0x00f2, all -> 0x00f0, blocks: (B:40:0x00d2, B:41:0x00d6, B:43:0x00dc), top: B:65:0x00d2 }] */
    /* JADX WARN: Type inference failed for: r7v3, types: [boolean] */
    /* JADX WARN: Type inference failed for: r7v4, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r7v5 */
    /* renamed from: b */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static boolean m4940b(android.content.Context r15, java.lang.String r16, long r17) {
        /*
            Method dump skipped, instructions count: 264
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.C11132ac.m4940b(android.content.Context, java.lang.String, long):boolean");
    }

    /* renamed from: a */
    public static boolean m4941a(Context context, String str, long j) {
        RandomAccessFile randomAccessFile;
        FileLock fileLock = null;
        try {
            File file = new File(context.getFilesDir(), "/.vdevdir/");
            if (!C11646v.m2275a(file)) {
                C11647w.m2274a((Closeable) null);
                return true;
            }
            File file2 = new File(file, "lcfp.lock");
            C11647w.m2272a(file2);
            randomAccessFile = new RandomAccessFile(file2, "rw");
            try {
                try {
                    fileLock = randomAccessFile.getChannel().lock();
                    boolean m4940b = m4940b(context, str, j);
                    if (fileLock != null && fileLock.isValid()) {
                        try {
                            fileLock.release();
                        } catch (IOException unused) {
                        }
                    }
                    C11647w.m2274a(randomAccessFile);
                    return m4940b;
                } catch (Throwable th) {
                    th = th;
                    if (fileLock != null && fileLock.isValid()) {
                        try {
                            fileLock.release();
                        } catch (IOException unused2) {
                        }
                    }
                    C11647w.m2274a(randomAccessFile);
                    throw th;
                }
            } catch (IOException e) {
                e = e;
                e.printStackTrace();
                if (fileLock != null && fileLock.isValid()) {
                    try {
                        fileLock.release();
                    } catch (IOException unused3) {
                    }
                }
                C11647w.m2274a(randomAccessFile);
                return true;
            }
        } catch (IOException e2) {
            e = e2;
            randomAccessFile = null;
        } catch (Throwable th2) {
            th = th2;
            randomAccessFile = null;
            if (fileLock != null) {
                fileLock.release();
            }
            C11647w.m2274a(randomAccessFile);
            throw th;
        }
    }
}
