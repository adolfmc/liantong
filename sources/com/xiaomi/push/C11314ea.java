package com.xiaomi.push;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.SystemClock;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import com.xiaomi.push.C11311dz;
import com.xiaomi.push.service.AbstractC11555an;
import com.xiaomi.push.service.C11603m;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.xiaomi.push.ea */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11314ea implements C11311dz.InterfaceC11312a {

    /* renamed from: a */
    protected Context f22042a;

    /* renamed from: a */
    private PendingIntent f22041a = null;

    /* renamed from: a */
    private volatile long f22040a = 0;

    public C11314ea(Context context) {
        this.f22042a = null;
        this.f22042a = context;
    }

    /* renamed from: a */
    public void m4053a(Intent intent, long j) {
        AlarmManager alarmManager = (AlarmManager) this.f22042a.getSystemService("alarm");
        if (Build.VERSION.SDK_INT >= 31) {
            this.f22041a = PendingIntent.getBroadcast(this.f22042a, 0, intent, 33554432);
        } else {
            this.f22041a = PendingIntent.getBroadcast(this.f22042a, 0, intent, 0);
        }
        if (Build.VERSION.SDK_INT >= 31 && !C11469j.m2972a(this.f22042a)) {
            alarmManager.set(2, j, this.f22041a);
        } else if (Build.VERSION.SDK_INT >= 23) {
            C11176aw.m4812a((Object) alarmManager, "setExactAndAllowWhileIdle", 2, Long.valueOf(j), this.f22041a);
        } else {
            m4054a(alarmManager, j, this.f22041a);
        }
        AbstractC11049b.m5270c("[Alarm] register timer " + j);
    }

    /* renamed from: a */
    private void m4054a(AlarmManager alarmManager, long j, PendingIntent pendingIntent) {
        try {
            AlarmManager.class.getMethod("setExact", Integer.TYPE, Long.TYPE, PendingIntent.class).invoke(alarmManager, 2, Long.valueOf(j), pendingIntent);
        } catch (Exception e) {
            AbstractC11049b.m5268d("[Alarm] invoke setExact method meet error. " + e);
        }
    }

    @Override // com.xiaomi.push.C11311dz.InterfaceC11312a
    /* renamed from: a */
    public void mo4049a(boolean z) {
        long m2515a = C11603m.m2511a(this.f22042a).m2515a();
        if (z || this.f22040a != 0) {
            if (z) {
                mo4052a();
            }
            long elapsedRealtime = SystemClock.elapsedRealtime();
            if (z || this.f22040a == 0) {
                this.f22040a = elapsedRealtime + (m2515a - (elapsedRealtime % m2515a));
            } else if (this.f22040a <= elapsedRealtime) {
                this.f22040a += m2515a;
                if (this.f22040a < elapsedRealtime) {
                    this.f22040a = elapsedRealtime + m2515a;
                }
            }
            Intent intent = new Intent(AbstractC11555an.f23591q);
            intent.setPackage(this.f22042a.getPackageName());
            m4053a(intent, this.f22040a);
        }
    }

    @Override // com.xiaomi.push.C11311dz.InterfaceC11312a
    /* renamed from: a */
    public void mo4052a() {
        if (this.f22041a != null) {
            try {
                ((AlarmManager) this.f22042a.getSystemService("alarm")).cancel(this.f22041a);
            } catch (Exception unused) {
            } catch (Throwable th) {
                this.f22041a = null;
                AbstractC11049b.m5270c("[Alarm] unregister timer");
                this.f22040a = 0L;
                throw th;
            }
            this.f22041a = null;
            AbstractC11049b.m5270c("[Alarm] unregister timer");
            this.f22040a = 0L;
        }
        this.f22040a = 0L;
    }

    @Override // com.xiaomi.push.C11311dz.InterfaceC11312a
    /* renamed from: a */
    public boolean mo4051a() {
        return this.f22040a != 0;
    }
}
