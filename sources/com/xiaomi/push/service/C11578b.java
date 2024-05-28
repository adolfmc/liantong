package com.xiaomi.push.service;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import com.xiaomi.push.C11647w;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;

/* renamed from: com.xiaomi.push.service.b */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11578b {

    /* renamed from: a */
    private static volatile C11578b f23648a;

    /* renamed from: a */
    private Context f23649a;

    /* renamed from: e */
    private volatile String f23656e;

    /* renamed from: f */
    private volatile String f23657f;

    /* renamed from: a */
    private final Object f23650a = new Object();

    /* renamed from: b */
    private final Object f23652b = new Object();

    /* renamed from: a */
    private final String f23651a = "mipush_region";

    /* renamed from: b */
    private final String f23653b = "mipush_country_code";

    /* renamed from: c */
    private final String f23654c = "mipush_region.lock";

    /* renamed from: d */
    private final String f23655d = "mipush_country_code.lock";

    /* renamed from: a */
    public static C11578b m2591a(Context context) {
        if (f23648a == null) {
            synchronized (C11578b.class) {
                if (f23648a == null) {
                    f23648a = new C11578b(context);
                }
            }
        }
        return f23648a;
    }

    public C11578b(Context context) {
        this.f23649a = context;
    }

    /* renamed from: a */
    public String m2592a() {
        if (TextUtils.isEmpty(this.f23656e)) {
            this.f23656e = m2590a(this.f23649a, "mipush_region", "mipush_region.lock", this.f23650a);
        }
        return this.f23656e;
    }

    /* renamed from: a */
    public void m2588a(String str, boolean z) {
        if (!TextUtils.equals(str, this.f23656e)) {
            this.f23656e = str;
        }
        if (z) {
            m2589a(this.f23649a, str, "mipush_region", "mipush_region.lock", this.f23650a);
        }
    }

    /* renamed from: b */
    public String m2587b() {
        if (TextUtils.isEmpty(this.f23657f)) {
            this.f23657f = m2590a(this.f23649a, "mipush_country_code", "mipush_country_code.lock", this.f23652b);
        }
        return this.f23657f;
    }

    /* renamed from: b */
    public void m2586b(String str, boolean z) {
        if (!TextUtils.equals(str, this.f23657f)) {
            this.f23657f = str;
        }
        if (z) {
            m2589a(this.f23649a, str, "mipush_country_code", "mipush_region.lock", this.f23650a);
        }
    }

    /* renamed from: a */
    private void m2589a(Context context, String str, String str2, String str3, Object obj) {
        RandomAccessFile randomAccessFile;
        synchronized (obj) {
            FileLock fileLock = null;
            try {
                File file = new File(context.getFilesDir(), str3);
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
                    C11647w.m2270a(new File(context.getFilesDir(), str2), str);
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

    /* renamed from: a */
    private String m2590a(Context context, String str, String str2, Object obj) {
        RandomAccessFile randomAccessFile;
        FileLock fileLock;
        File file = new File(context.getFilesDir(), str);
        FileLock fileLock2 = null;
        if (!file.exists()) {
            AbstractC11049b.m5282a("No ready file to get data from " + str);
            return null;
        }
        synchronized (obj) {
            try {
                File file2 = new File(context.getFilesDir(), str2);
                C11647w.m2272a(file2);
                randomAccessFile = new RandomAccessFile(file2, "rw");
                try {
                    fileLock = randomAccessFile.getChannel().lock();
                } catch (Exception e) {
                    e = e;
                    fileLock = null;
                } catch (Throwable th) {
                    th = th;
                    if (fileLock2 != null) {
                        try {
                            fileLock2.release();
                        } catch (IOException e2) {
                            AbstractC11049b.m5276a(e2);
                        }
                    }
                    C11647w.m2274a(randomAccessFile);
                    throw th;
                }
            } catch (Exception e3) {
                e = e3;
                randomAccessFile = null;
                fileLock = null;
            } catch (Throwable th2) {
                th = th2;
                randomAccessFile = null;
            }
            try {
                try {
                    String m2273a = C11647w.m2273a(file);
                    if (fileLock != null && fileLock.isValid()) {
                        try {
                            fileLock.release();
                        } catch (IOException e4) {
                            AbstractC11049b.m5276a(e4);
                        }
                    }
                    C11647w.m2274a(randomAccessFile);
                    return m2273a;
                } catch (Throwable th3) {
                    th = th3;
                    fileLock2 = fileLock;
                    if (fileLock2 != null && fileLock2.isValid()) {
                        fileLock2.release();
                    }
                    C11647w.m2274a(randomAccessFile);
                    throw th;
                }
            } catch (Exception e5) {
                e = e5;
                AbstractC11049b.m5276a(e);
                if (fileLock != null && fileLock.isValid()) {
                    try {
                        fileLock.release();
                    } catch (IOException e6) {
                        AbstractC11049b.m5276a(e6);
                    }
                }
                C11647w.m2274a(randomAccessFile);
                return null;
            }
        }
    }
}
