package com.xiaomi.push;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import com.xiaomi.push.C11134ae;
import com.xiaomi.push.service.C11537ah;

/* renamed from: com.xiaomi.push.dl */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public abstract class AbstractC11284dl extends C11134ae.AbstractRunnableC11137a {

    /* renamed from: a */
    protected int f21870a;

    /* renamed from: a */
    protected Context f21871a;

    /* renamed from: a */
    public abstract EnumC11406gh mo4340a();

    /* renamed from: b */
    public abstract String mo4339b();

    /* renamed from: b */
    protected boolean m4344b() {
        return true;
    }

    /* renamed from: c */
    protected boolean m4342c() {
        return false;
    }

    public AbstractC11284dl(Context context, int i) {
        this.f21870a = i;
        this.f21871a = context;
    }

    @Override // java.lang.Runnable
    public void run() {
        String mo4339b = mo4339b();
        if (TextUtils.isEmpty(mo4339b)) {
            return;
        }
        if (m4347a()) {
            AbstractC11049b.m5282a("DC run job mutual: " + mo4340a());
            return;
        }
        InterfaceC11276df m4363a = C11277dg.m4362a().m4363a();
        String mo4365a = m4363a == null ? "" : m4363a.mo4365a();
        if (!TextUtils.isEmpty(mo4365a) && m4344b()) {
            if (m4342c()) {
                SharedPreferences sharedPreferences = this.f21871a.getSharedPreferences("mipush_extra", 0);
                if (C11184bb.m4757a(mo4339b).equals(sharedPreferences.getString(m4341d(), null))) {
                    long j = sharedPreferences.getLong(m4343c(), 0L);
                    int m2719a = C11537ah.m2715a(this.f21871a).m2719a(EnumC11409gk.DCJobUploadRepeatedInterval.m3637a(), 604800);
                    if ((System.currentTimeMillis() - j) / 1000 < this.f21870a) {
                        return;
                    }
                    if ((System.currentTimeMillis() - j) / 1000 < m2719a) {
                        mo4339b = "same_" + j;
                    }
                }
            }
            C11412gn c11412gn = new C11412gn();
            c11412gn.m3626a(mo4339b);
            c11412gn.m3630a(System.currentTimeMillis());
            c11412gn.m3629a(mo4340a());
            m4345a(this.f21871a, c11412gn, mo4365a);
        }
    }

    /* renamed from: a */
    protected boolean m4347a() {
        return C11279di.m4359a(this.f21871a, String.valueOf(mo4340a()), this.f21870a);
    }

    /* renamed from: a */
    public static void m4346a(Context context, C11412gn c11412gn) {
        InterfaceC11276df m4363a = C11277dg.m4362a().m4363a();
        String mo4365a = m4363a == null ? "" : m4363a.mo4365a();
        if (TextUtils.isEmpty(mo4365a) || TextUtils.isEmpty(c11412gn.m3633a())) {
            return;
        }
        m4345a(context, c11412gn, mo4365a);
    }

    /* JADX WARN: Removed duplicated region for block: B:54:0x00a3 A[Catch: all -> 0x00b3, TRY_LEAVE, TryCatch #7 {, blocks: (B:23:0x0067, B:25:0x006d, B:26:0x0070, B:27:0x0073, B:48:0x009c, B:54:0x00a3, B:56:0x00a9, B:57:0x00ac, B:58:0x00b2, B:44:0x008f, B:46:0x0095, B:47:0x0098), top: B:72:0x0013 }] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void m4345a(android.content.Context r6, com.xiaomi.push.C11412gn r7, java.lang.String r8) {
        /*
            byte[] r7 = com.xiaomi.push.C11441hp.m3085a(r7)
            byte[] r7 = com.xiaomi.push.C11279di.m4356b(r8, r7)
            if (r7 == 0) goto Lb6
            int r8 = r7.length
            if (r8 != 0) goto Lf
            goto Lb6
        Lf:
            java.lang.Object r8 = com.xiaomi.push.C11280dj.f21863a
            monitor-enter(r8)
            r0 = 0
            java.io.File r1 = new java.io.File     // Catch: java.lang.Throwable -> L83 java.io.IOException -> L87
            java.io.File r2 = r6.getFilesDir()     // Catch: java.lang.Throwable -> L83 java.io.IOException -> L87
            java.lang.String r3 = "push_cdata.lock"
            r1.<init>(r2, r3)     // Catch: java.lang.Throwable -> L83 java.io.IOException -> L87
            com.xiaomi.push.C11647w.m2272a(r1)     // Catch: java.lang.Throwable -> L83 java.io.IOException -> L87
            java.io.RandomAccessFile r2 = new java.io.RandomAccessFile     // Catch: java.lang.Throwable -> L83 java.io.IOException -> L87
            java.lang.String r3 = "rw"
            r2.<init>(r1, r3)     // Catch: java.lang.Throwable -> L83 java.io.IOException -> L87
            java.nio.channels.FileChannel r1 = r2.getChannel()     // Catch: java.lang.Throwable -> L7d java.io.IOException -> L80
            java.nio.channels.FileLock r1 = r1.lock()     // Catch: java.lang.Throwable -> L7d java.io.IOException -> L80
            java.io.File r3 = new java.io.File     // Catch: java.lang.Throwable -> L77 java.io.IOException -> L79
            java.io.File r6 = r6.getFilesDir()     // Catch: java.lang.Throwable -> L77 java.io.IOException -> L79
            java.lang.String r4 = "push_cdata.data"
            r3.<init>(r6, r4)     // Catch: java.lang.Throwable -> L77 java.io.IOException -> L79
            boolean r6 = com.xiaomi.push.C11646v.m2275a(r3)     // Catch: java.lang.Throwable -> L77 java.io.IOException -> L79
            if (r6 == 0) goto L64
            java.io.BufferedOutputStream r6 = new java.io.BufferedOutputStream     // Catch: java.lang.Throwable -> L77 java.io.IOException -> L79
            java.io.FileOutputStream r4 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L77 java.io.IOException -> L79
            r5 = 1
            r4.<init>(r3, r5)     // Catch: java.lang.Throwable -> L77 java.io.IOException -> L79
            r6.<init>(r4)     // Catch: java.lang.Throwable -> L77 java.io.IOException -> L79
            int r0 = r7.length     // Catch: java.lang.Throwable -> L60 java.io.IOException -> L62
            byte[] r0 = com.xiaomi.push.C11650y.m2261a(r0)     // Catch: java.lang.Throwable -> L60 java.io.IOException -> L62
            r6.write(r0)     // Catch: java.lang.Throwable -> L60 java.io.IOException -> L62
            r6.write(r7)     // Catch: java.lang.Throwable -> L60 java.io.IOException -> L62
            r6.flush()     // Catch: java.lang.Throwable -> L60 java.io.IOException -> L62
            r4 = 0
            r3.setLastModified(r4)     // Catch: java.lang.Throwable -> L60 java.io.IOException -> L62
            goto L65
        L60:
            r7 = move-exception
            goto La0
        L62:
            r7 = move-exception
            goto L7b
        L64:
            r6 = r0
        L65:
            if (r1 == 0) goto L70
            boolean r7 = r1.isValid()     // Catch: java.lang.Throwable -> Lb3
            if (r7 == 0) goto L70
            r1.release()     // Catch: java.io.IOException -> L70 java.lang.Throwable -> Lb3
        L70:
            com.xiaomi.push.C11647w.m2274a(r6)     // Catch: java.lang.Throwable -> Lb3
        L73:
            com.xiaomi.push.C11647w.m2274a(r2)     // Catch: java.lang.Throwable -> Lb3
            goto L9c
        L77:
            r7 = move-exception
            goto La1
        L79:
            r7 = move-exception
            r6 = r0
        L7b:
            r0 = r1
            goto L8a
        L7d:
            r7 = move-exception
            r1 = r0
            goto La1
        L80:
            r7 = move-exception
            r6 = r0
            goto L8a
        L83:
            r7 = move-exception
            r1 = r0
            r2 = r1
            goto La1
        L87:
            r7 = move-exception
            r6 = r0
            r2 = r6
        L8a:
            r7.printStackTrace()     // Catch: java.lang.Throwable -> L9e
            if (r0 == 0) goto L98
            boolean r7 = r0.isValid()     // Catch: java.lang.Throwable -> Lb3
            if (r7 == 0) goto L98
            r0.release()     // Catch: java.io.IOException -> L98 java.lang.Throwable -> Lb3
        L98:
            com.xiaomi.push.C11647w.m2274a(r6)     // Catch: java.lang.Throwable -> Lb3
            goto L73
        L9c:
            monitor-exit(r8)     // Catch: java.lang.Throwable -> Lb3
            return
        L9e:
            r7 = move-exception
            r1 = r0
        La0:
            r0 = r6
        La1:
            if (r1 == 0) goto Lac
            boolean r6 = r1.isValid()     // Catch: java.lang.Throwable -> Lb3
            if (r6 == 0) goto Lac
            r1.release()     // Catch: java.io.IOException -> Lac java.lang.Throwable -> Lb3
        Lac:
            com.xiaomi.push.C11647w.m2274a(r0)     // Catch: java.lang.Throwable -> Lb3
            com.xiaomi.push.C11647w.m2274a(r2)     // Catch: java.lang.Throwable -> Lb3
            throw r7     // Catch: java.lang.Throwable -> Lb3
        Lb3:
            r6 = move-exception
            monitor-exit(r8)     // Catch: java.lang.Throwable -> Lb3
            throw r6
        Lb6:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.AbstractC11284dl.m4345a(android.content.Context, com.xiaomi.push.gn, java.lang.String):void");
    }

    /* renamed from: c */
    private String m4343c() {
        return "dc_job_result_time_" + mo4340a();
    }

    /* renamed from: d */
    private String m4341d() {
        return "dc_job_result_" + mo4340a();
    }
}
