package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import com.xiaomi.push.C11427hb;
import com.xiaomi.push.C11430he;
import com.xiaomi.push.C11469j;
import com.xiaomi.push.EnumC11404gf;
import com.xiaomi.push.EnumC11414gp;
import java.util.HashMap;

/* renamed from: com.xiaomi.mipush.sdk.j */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11102j {

    /* renamed from: a */
    private static volatile C11102j f21391a;

    /* renamed from: a */
    private final Context f21392a;

    /* renamed from: a */
    private static C11102j m5068a(Context context) {
        if (f21391a == null) {
            synchronized (C11102j.class) {
                if (f21391a == null) {
                    f21391a = new C11102j(context);
                }
            }
        }
        return f21391a;
    }

    private C11102j(Context context) {
        this.f21392a = context.getApplicationContext();
    }

    /* renamed from: a */
    public static void m5066a(Context context, C11427hb c11427hb, boolean z) {
        m5068a(context).m5065a(c11427hb, 1, z);
    }

    /* renamed from: b */
    public static void m5064b(Context context, C11427hb c11427hb, boolean z) {
        m5068a(context).m5065a(c11427hb, 2, z);
    }

    /* renamed from: c */
    public static void m5063c(Context context, C11427hb c11427hb, boolean z) {
        m5068a(context).m5065a(c11427hb, 3, z);
    }

    /* renamed from: d */
    public static void m5062d(Context context, C11427hb c11427hb, boolean z) {
        m5068a(context).m5065a(c11427hb, 4, z);
    }

    /* renamed from: e */
    public static void m5061e(Context context, C11427hb c11427hb, boolean z) {
        C11087b m5151a = C11087b.m5151a(context);
        if (TextUtils.isEmpty(m5151a.m5138c()) || TextUtils.isEmpty(m5151a.m5135d())) {
            m5068a(context).m5065a(c11427hb, 6, z);
        } else if (m5151a.m5130f()) {
            m5068a(context).m5065a(c11427hb, 7, z);
        } else {
            m5068a(context).m5065a(c11427hb, 5, z);
        }
    }

    /* renamed from: a */
    public static void m5067a(Context context, C11427hb c11427hb) {
        m5068a(context).m5065a(c11427hb, 0, true);
    }

    /* renamed from: a */
    private void m5065a(C11427hb c11427hb, int i, boolean z) {
        if (C11469j.m2972a(this.f21392a) || !C11469j.m2974a() || c11427hb == null || c11427hb.f22974a != EnumC11404gf.SendMessage || c11427hb.m3388a() == null || !z) {
            return;
        }
        AbstractC11049b.m5282a("click to start activity result:" + String.valueOf(i));
        C11430he c11430he = new C11430he(c11427hb.m3388a().m3559a(), false);
        c11430he.m3331c(EnumC11414gp.SDK_START_ACTIVITY.f22745a);
        c11430he.m3335b(c11427hb.m3387a());
        c11430he.m3327d(c11427hb.f22981b);
        c11430he.f23010a = new HashMap();
        c11430he.f23010a.put("result", String.valueOf(i));
        C11118u.m5003a(this.f21392a).m4981a(c11430he, EnumC11404gf.Notification, false, false, null, true, c11427hb.f22981b, c11427hb.f22977a, true, false);
    }
}
