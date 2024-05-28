package com.xiaomi.push.service;

import android.content.SharedPreferences;
import android.util.Base64;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import com.xiaomi.push.AbstractC11238ci;
import com.xiaomi.push.C11139af;
import com.xiaomi.push.C11224c;
import com.xiaomi.push.C11287do;
import com.xiaomi.push.C11289dp;
import com.xiaomi.push.C11390fy;
import com.xiaomi.push.C11455i;
import com.xiaomi.push.C11479r;
import com.xiaomi.push.InterfaceC11168at;
import java.io.BufferedOutputStream;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.xiaomi.push.service.ax */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11571ax {

    /* renamed from: a */
    private static C11571ax f23629a = new C11571ax();

    /* renamed from: a */
    private static String f23630a;

    /* renamed from: a */
    private C11139af.AbstractC11143b f23631a;

    /* renamed from: a */
    private C11287do.C11288a f23632a;

    /* renamed from: a */
    private List<AbstractC11573a> f23633a = new ArrayList();

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.xiaomi.push.service.ax$a */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static abstract class AbstractC11573a {
        /* renamed from: a */
        public void mo2609a(C11287do.C11288a c11288a) {
        }

        /* renamed from: a */
        public void mo2608a(C11289dp.C11291b c11291b) {
        }
    }

    /* renamed from: a */
    public static C11571ax m2625a() {
        return f23629a;
    }

    private C11571ax() {
    }

    /* renamed from: a */
    public synchronized void m2621a(AbstractC11573a abstractC11573a) {
        this.f23633a.add(abstractC11573a);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public synchronized void m2623a() {
        this.f23633a.clear();
    }

    /* renamed from: b */
    private void m2615b() {
        if (this.f23632a == null) {
            m2613d();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public int m2627a() {
        m2615b();
        C11287do.C11288a c11288a = this.f23632a;
        if (c11288a != null) {
            return c11288a.m4322c();
        }
        return 0;
    }

    /* renamed from: a */
    public C11287do.C11288a m2626a() {
        m2615b();
        return this.f23632a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m2622a(C11289dp.C11291b c11291b) {
        AbstractC11573a[] abstractC11573aArr;
        if (c11291b.m4265d() && c11291b.m4266d() > m2627a()) {
            m2614c();
        }
        synchronized (this) {
            abstractC11573aArr = (AbstractC11573a[]) this.f23633a.toArray(new AbstractC11573a[this.f23633a.size()]);
        }
        for (AbstractC11573a abstractC11573a : abstractC11573aArr) {
            abstractC11573a.mo2608a(c11291b);
        }
    }

    /* renamed from: a */
    public static synchronized String m2624a() {
        String str;
        synchronized (C11571ax.class) {
            if (f23630a == null) {
                SharedPreferences sharedPreferences = C11479r.m2934a().getSharedPreferences("XMPushServiceConfig", 0);
                f23630a = sharedPreferences.getString("DeviceUUID", null);
                if (f23630a == null) {
                    f23630a = C11455i.m3046a(C11479r.m2934a(), false);
                    if (f23630a != null) {
                        sharedPreferences.edit().putString("DeviceUUID", f23630a).commit();
                    }
                }
            }
            str = f23630a;
        }
        return str;
    }

    /* renamed from: c */
    private void m2614c() {
        if (this.f23631a != null) {
            return;
        }
        this.f23631a = new C11139af.AbstractC11143b() { // from class: com.xiaomi.push.service.ax.1

            /* renamed from: a */
            boolean f23635a = false;

            @Override // com.xiaomi.push.C11139af.AbstractC11143b
            /* renamed from: b */
            public void mo2611b() {
                try {
                    C11287do.C11288a m4327a = C11287do.C11288a.m4327a(Base64.decode(AbstractC11238ci.m4536a(C11479r.m2934a(), "https://resolver.msg.xiaomi.net/psc/?t=a", (List<InterfaceC11168at>) null), 10));
                    if (m4327a != null) {
                        C11571ax.this.f23632a = m4327a;
                        this.f23635a = true;
                        C11571ax.this.m2612e();
                    }
                } catch (Exception e) {
                    AbstractC11049b.m5282a("fetch config failure: " + e.getMessage());
                }
            }

            @Override // com.xiaomi.push.C11139af.AbstractC11143b
            /* renamed from: c */
            public void mo2610c() {
                AbstractC11573a[] abstractC11573aArr;
                C11571ax.this.f23631a = null;
                if (this.f23635a) {
                    synchronized (C11571ax.this) {
                        abstractC11573aArr = (AbstractC11573a[]) C11571ax.this.f23633a.toArray(new AbstractC11573a[C11571ax.this.f23633a.size()]);
                    }
                    for (AbstractC11573a abstractC11573a : abstractC11573aArr) {
                        abstractC11573a.mo2609a(C11571ax.this.f23632a);
                    }
                }
            }
        };
        C11390fy.m3744a(this.f23631a);
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x004b  */
    /* JADX WARN: Removed duplicated region for block: B:26:? A[RETURN, SYNTHETIC] */
    /* renamed from: d */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void m2613d() {
        /*
            r4 = this;
            r0 = 0
            android.content.Context r1 = com.xiaomi.push.C11479r.m2934a()     // Catch: java.lang.Throwable -> L29 java.lang.Exception -> L2b
            java.lang.String r2 = "XMCloudCfg"
            java.io.FileInputStream r1 = r1.openFileInput(r2)     // Catch: java.lang.Throwable -> L29 java.lang.Exception -> L2b
            java.io.BufferedInputStream r2 = new java.io.BufferedInputStream     // Catch: java.lang.Throwable -> L29 java.lang.Exception -> L2b
            r2.<init>(r1)     // Catch: java.lang.Throwable -> L29 java.lang.Exception -> L2b
            com.xiaomi.push.b r0 = com.xiaomi.push.C11182b.m4776a(r2)     // Catch: java.lang.Throwable -> L21 java.lang.Exception -> L25
            com.xiaomi.push.do$a r0 = com.xiaomi.push.C11287do.C11288a.m4324b(r0)     // Catch: java.lang.Throwable -> L21 java.lang.Exception -> L25
            r4.f23632a = r0     // Catch: java.lang.Throwable -> L21 java.lang.Exception -> L25
            r2.close()     // Catch: java.lang.Throwable -> L21 java.lang.Exception -> L25
            com.xiaomi.push.C11647w.m2274a(r2)
            goto L47
        L21:
            r0 = move-exception
            r1 = r0
            r0 = r2
            goto L53
        L25:
            r0 = move-exception
            r1 = r0
            r0 = r2
            goto L2c
        L29:
            r1 = move-exception
            goto L53
        L2b:
            r1 = move-exception
        L2c:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L29
            r2.<init>()     // Catch: java.lang.Throwable -> L29
            java.lang.String r3 = "load config failure: "
            r2.append(r3)     // Catch: java.lang.Throwable -> L29
            java.lang.String r1 = r1.getMessage()     // Catch: java.lang.Throwable -> L29
            r2.append(r1)     // Catch: java.lang.Throwable -> L29
            java.lang.String r1 = r2.toString()     // Catch: java.lang.Throwable -> L29
            com.xiaomi.channel.commonutils.logger.AbstractC11049b.m5282a(r1)     // Catch: java.lang.Throwable -> L29
            com.xiaomi.push.C11647w.m2274a(r0)
        L47:
            com.xiaomi.push.do$a r0 = r4.f23632a
            if (r0 != 0) goto L52
            com.xiaomi.push.do$a r0 = new com.xiaomi.push.do$a
            r0.<init>()
            r4.f23632a = r0
        L52:
            return
        L53:
            com.xiaomi.push.C11647w.m2274a(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.service.C11571ax.m2613d():void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: e */
    public void m2612e() {
        try {
            if (this.f23632a != null) {
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(C11479r.m2934a().openFileOutput("XMCloudCfg", 0));
                C11224c m4635a = C11224c.m4635a(bufferedOutputStream);
                this.f23632a.mo4059a(m4635a);
                m4635a.m4657a();
                bufferedOutputStream.close();
            }
        } catch (Exception e) {
            AbstractC11049b.m5282a("save config failure: " + e.getMessage());
        }
    }
}
