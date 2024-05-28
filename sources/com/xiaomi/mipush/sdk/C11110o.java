package com.xiaomi.mipush.sdk;

import android.content.Context;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import com.xiaomi.push.C11134ae;
import com.xiaomi.push.C11417gs;
import com.xiaomi.push.C11422gx;
import com.xiaomi.push.C11430he;
import com.xiaomi.push.C11441hp;
import com.xiaomi.push.EnumC11404gf;
import com.xiaomi.push.EnumC11410gl;
import com.xiaomi.push.EnumC11414gp;
import com.xiaomi.push.service.C11537ah;
import com.xiaomi.push.service.C11539ai;

/* renamed from: com.xiaomi.mipush.sdk.o */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11110o extends C11134ae.AbstractRunnableC11137a {

    /* renamed from: a */
    private Context f21400a;

    /* renamed from: a */
    private boolean f21401a = false;

    @Override // com.xiaomi.push.C11134ae.AbstractRunnableC11137a
    /* renamed from: a */
    public String mo2289a() {
        return "2";
    }

    public C11110o(Context context) {
        this.f21400a = context;
    }

    @Override // java.lang.Runnable
    public void run() {
        C11537ah m2715a = C11537ah.m2715a(this.f21400a);
        C11422gx c11422gx = new C11422gx();
        if (this.f21401a) {
            c11422gx.m3446a(0);
            c11422gx.m3441b(0);
        } else {
            c11422gx.m3446a(C11539ai.m2707a(m2715a, EnumC11410gl.MISC_CONFIG));
            c11422gx.m3441b(C11539ai.m2707a(m2715a, EnumC11410gl.PLUGIN_CONFIG));
        }
        C11430he c11430he = new C11430he("-1", false);
        c11430he.m3331c(EnumC11414gp.DailyCheckClientConfig.f22745a);
        c11430he.m3338a(C11441hp.m3085a(c11422gx));
        AbstractC11049b.m5271b("OcVersionCheckJob", "-->check version: checkMessage=", c11422gx);
        C11118u.m5003a(this.f21400a).m4988a((C11118u) c11430he, EnumC11404gf.Notification, (C11417gs) null);
    }
}
