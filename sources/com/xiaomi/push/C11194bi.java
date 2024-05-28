package com.xiaomi.push;

import android.content.Context;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import com.xiaomi.clientreport.manager.C11053a;
import com.xiaomi.push.C11134ae;

/* renamed from: com.xiaomi.push.bi */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11194bi extends C11134ae.AbstractRunnableC11137a {

    /* renamed from: a */
    private Context f21607a;

    @Override // com.xiaomi.push.C11134ae.AbstractRunnableC11137a
    /* renamed from: a */
    public String mo2289a() {
        return "100886";
    }

    public C11194bi(Context context) {
        this.f21607a = context;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            if (m4723a()) {
                AbstractC11049b.m5270c(this.f21607a.getPackageName() + " begin upload event");
                C11053a.m5261a(this.f21607a).m5248b();
            }
        } catch (Exception e) {
            AbstractC11049b.m5276a(e);
        }
    }

    /* renamed from: a */
    private boolean m4723a() {
        return C11053a.m5261a(this.f21607a).m5264a().isEventUploadSwitchOpen();
    }
}
