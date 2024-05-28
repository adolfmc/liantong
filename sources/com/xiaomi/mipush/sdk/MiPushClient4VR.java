package com.xiaomi.mipush.sdk;

import android.content.Context;
import com.xiaomi.push.C11417gs;
import com.xiaomi.push.C11430he;
import com.xiaomi.push.EnumC11404gf;
import com.xiaomi.push.EnumC11414gp;
import com.xiaomi.push.service.C11541aj;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class MiPushClient4VR {
    public static void uploadData(Context context, String str) {
        C11430he c11430he = new C11430he();
        c11430he.m3331c(EnumC11414gp.VRUpload.f22745a);
        c11430he.m3335b(C11087b.m5151a(context).m5156a());
        c11430he.m3327d(context.getPackageName());
        c11430he.m3343a("data", str);
        c11430he.m3344a(C11541aj.m2703a());
        C11118u.m5003a(context).m4988a((C11118u) c11430he, EnumC11404gf.Notification, (C11417gs) null);
    }
}
