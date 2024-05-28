package com.xiaomi.push.service;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import com.xiaomi.push.C11134ae;
import com.xiaomi.push.C11180ay;
import com.xiaomi.push.C11184bb;
import com.xiaomi.push.C11408gj;
import com.xiaomi.push.C11425h;
import com.xiaomi.push.C11441hp;
import com.xiaomi.push.C11474o;
import com.xiaomi.push.C11647w;
import com.xiaomi.push.C11650y;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;
import java.util.Arrays;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.xiaomi.push.service.ba */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C11579ba {

    /* renamed from: a */
    public static final Object f23658a = new Object();

    /* renamed from: a */
    public static void m2584a(final Context context, final C11408gj c11408gj) {
        if (C11577az.m2595a(c11408gj.m3648e())) {
            C11134ae.m4937a(context).m4928a(new Runnable() { // from class: com.xiaomi.push.service.ba.1
                @Override // java.lang.Runnable
                public void run() {
                    RandomAccessFile randomAccessFile;
                    synchronized (C11579ba.f23658a) {
                        FileLock fileLock = null;
                        try {
                            File file = new File(context.getFilesDir(), "tiny_data.lock");
                            C11647w.m2272a(file);
                            randomAccessFile = new RandomAccessFile(file, "rw");
                        } catch (Exception e) {
                            e = e;
                            randomAccessFile = null;
                        } catch (Throwable th) {
                            th = th;
                            randomAccessFile = null;
                            if (fileLock != null) {
                                try {
                                    fileLock.release();
                                } catch (IOException e2) {
                                    AbstractC11049b.m5276a(e2);
                                }
                            }
                            C11647w.m2274a(randomAccessFile);
                            throw th;
                        }
                        try {
                            try {
                                fileLock = randomAccessFile.getChannel().lock();
                                C11579ba.m2581c(context, c11408gj);
                                if (fileLock != null && fileLock.isValid()) {
                                    try {
                                        fileLock.release();
                                    } catch (IOException e3) {
                                        AbstractC11049b.m5276a(e3);
                                    }
                                }
                            } catch (Throwable th2) {
                                th = th2;
                                if (fileLock != null && fileLock.isValid()) {
                                    fileLock.release();
                                }
                                C11647w.m2274a(randomAccessFile);
                                throw th;
                            }
                        } catch (Exception e4) {
                            e = e4;
                            AbstractC11049b.m5276a(e);
                            if (fileLock != null && fileLock.isValid()) {
                                try {
                                    fileLock.release();
                                } catch (IOException e5) {
                                    AbstractC11049b.m5276a(e5);
                                }
                            }
                            C11647w.m2274a(randomAccessFile);
                        }
                        C11647w.m2274a(randomAccessFile);
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [byte[]] */
    /* JADX WARN: Type inference failed for: r0v20 */
    /* JADX WARN: Type inference failed for: r0v21 */
    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARN: Type inference failed for: r0v7, types: [java.io.Closeable] */
    /* renamed from: c */
    public static void m2581c(Context context, C11408gj c11408gj) {
        BufferedOutputStream bufferedOutputStream;
        BufferedOutputStream bufferedOutputStream2;
        BufferedOutputStream m2585a = m2585a(context);
        try {
            try {
                byte[] m3409b = C11425h.m3409b(m2585a, C11441hp.m3085a(c11408gj));
                if (m3409b != null && m3409b.length >= 1) {
                    if (m3409b.length > 30720) {
                        AbstractC11049b.m5282a("TinyData write to cache file failed case too much data content item:" + c11408gj.m3651d() + "  ts:" + System.currentTimeMillis());
                        C11647w.m2274a((Closeable) null);
                        C11647w.m2274a((Closeable) null);
                        return;
                    }
                    BufferedOutputStream bufferedOutputStream3 = new BufferedOutputStream(new FileOutputStream(new File(context.getFilesDir(), "tiny_data.data"), true));
                    try {
                        bufferedOutputStream3.write(C11650y.m2261a(m3409b.length));
                        bufferedOutputStream3.write(m3409b);
                        bufferedOutputStream3.flush();
                        C11647w.m2274a((Closeable) null);
                        C11647w.m2274a(bufferedOutputStream3);
                        return;
                    } catch (IOException e) {
                        bufferedOutputStream2 = bufferedOutputStream3;
                        e = e;
                        AbstractC11049b.m5279a("TinyData write to cache file failed cause io exception item:" + c11408gj.m3651d(), e);
                        m2585a = bufferedOutputStream2;
                        C11647w.m2274a((Closeable) null);
                        C11647w.m2274a((Closeable) m2585a);
                        return;
                    } catch (Exception e2) {
                        bufferedOutputStream = bufferedOutputStream3;
                        e = e2;
                        AbstractC11049b.m5279a("TinyData write to cache file  failed item:" + c11408gj.m3651d(), e);
                        m2585a = bufferedOutputStream;
                        C11647w.m2274a((Closeable) null);
                        C11647w.m2274a((Closeable) m2585a);
                        return;
                    } catch (Throwable th) {
                        m2585a = bufferedOutputStream3;
                        th = th;
                        C11647w.m2274a((Closeable) null);
                        C11647w.m2274a(m2585a);
                        throw th;
                    }
                }
                AbstractC11049b.m5282a("TinyData write to cache file failed case encryption fail item:" + c11408gj.m3651d() + "  ts:" + System.currentTimeMillis());
                C11647w.m2274a((Closeable) null);
                C11647w.m2274a((Closeable) null);
            } catch (IOException e3) {
                e = e3;
                bufferedOutputStream2 = null;
            } catch (Exception e4) {
                e = e4;
                bufferedOutputStream = null;
            } catch (Throwable th2) {
                th = th2;
                m2585a = null;
            }
        } catch (Throwable th3) {
            th = th3;
        }
    }

    /* renamed from: a */
    public static byte[] m2585a(Context context) {
        String m2941a = C11474o.m2944a(context).m2941a("mipush", "td_key", "");
        if (TextUtils.isEmpty(m2941a)) {
            m2941a = C11184bb.m4758a(20);
            C11474o.m2944a(context).m2940a("mipush", "td_key", m2941a);
        }
        return m2583a(m2941a);
    }

    /* renamed from: a */
    private static byte[] m2583a(String str) {
        byte[] copyOf = Arrays.copyOf(C11180ay.m4796a(str), 16);
        copyOf[0] = 68;
        copyOf[15] = 84;
        return copyOf;
    }
}
