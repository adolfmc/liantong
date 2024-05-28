package com.xiaomi.push;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import com.xiaomi.push.C11134ae;
import com.xiaomi.push.service.C11537ah;

/* renamed from: com.xiaomi.push.dk */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11281dk {

    /* renamed from: a */
    private static volatile C11281dk f21866a;

    /* renamed from: a */
    private Context f21867a;

    /* renamed from: a */
    private InterfaceC11283a f21868a;

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.xiaomi.push.dk$a */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public interface InterfaceC11283a {
        /* renamed from: a */
        void m4348a();
    }

    /* renamed from: a */
    public static C11281dk m4352a(Context context) {
        if (f21866a == null) {
            synchronized (C11281dk.class) {
                if (f21866a == null) {
                    f21866a = new C11281dk(context);
                }
            }
        }
        return f21866a;
    }

    private C11281dk(Context context) {
        this.f21867a = context;
    }

    /* renamed from: a */
    public void m4355a() {
        C11134ae.m4937a(this.f21867a).m4928a(new Runnable() { // from class: com.xiaomi.push.dk.1
            @Override // java.lang.Runnable
            public void run() {
                C11281dk.this.m4349b();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public void m4349b() {
        InterfaceC11283a interfaceC11283a;
        C11134ae m4937a = C11134ae.m4937a(this.f21867a);
        C11537ah m2715a = C11537ah.m2715a(this.f21867a);
        SharedPreferences sharedPreferences = this.f21867a.getSharedPreferences("mipush_extra", 0);
        long currentTimeMillis = System.currentTimeMillis();
        long j = sharedPreferences.getLong("first_try_ts", currentTimeMillis);
        if (j == currentTimeMillis) {
            sharedPreferences.edit().putLong("first_try_ts", currentTimeMillis).commit();
        }
        if (Math.abs(currentTimeMillis - j) < 172800000) {
            return;
        }
        m4350a(m2715a, m4937a, false);
        if (m2715a.m2716a(EnumC11409gk.StorageCollectionSwitch.m3637a(), true)) {
            int m4353a = m4353a(m2715a.m2719a(EnumC11409gk.StorageCollectionFrequency.m3637a(), 86400));
            m4937a.m4933a(new C11285dm(this.f21867a, m4353a), m4353a, 0);
        }
        if (C11469j.m2972a(this.f21867a) && (interfaceC11283a = this.f21868a) != null) {
            interfaceC11283a.m4348a();
        }
        if (m2715a.m2716a(EnumC11409gk.ActivityTSSwitch.m3637a(), false)) {
            m4354a();
        }
        m4350a(m2715a, m4937a, true);
    }

    /* renamed from: a */
    private void m4350a(C11537ah c11537ah, C11134ae c11134ae, boolean z) {
        if (c11537ah.m2716a(EnumC11409gk.UploadSwitch.m3637a(), true)) {
            C11286dn c11286dn = new C11286dn(this.f21867a);
            if (z) {
                c11134ae.m4934a((C11134ae.AbstractRunnableC11137a) c11286dn, m4353a(c11537ah.m2719a(EnumC11409gk.UploadFrequency.m3637a(), 86400)));
            } else {
                c11134ae.m4935a((C11134ae.AbstractRunnableC11137a) c11286dn);
            }
        }
    }

    /* renamed from: a */
    public static int m4353a(int i) {
        return Math.max(60, i);
    }

    /* renamed from: a */
    private boolean m4354a() {
        Application application;
        try {
            if (this.f21867a instanceof Application) {
                application = (Application) this.f21867a;
            } else {
                application = (Application) this.f21867a.getApplicationContext();
            }
            application.registerActivityLifecycleCallbacks(new C11275de(this.f21867a, String.valueOf(System.currentTimeMillis() / 1000)));
            return true;
        } catch (Exception e) {
            AbstractC11049b.m5276a(e);
            return false;
        }
    }
}
