package com.mob.apc.p228a;

import com.mob.tools.MobLog;
import com.mob.tools.log.NLog;

/* renamed from: com.mob.apc.a.f */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5687f {

    /* renamed from: a */
    private static C5687f f14032a = new C5687f();

    private C5687f() {
    }

    /* renamed from: a */
    public static C5687f m12837a() {
        return f14032a;
    }

    /* renamed from: a */
    public void m12835a(Throwable th) {
        MobLog.getInstance().m11340d(th, "%s", "[MC][APC]");
    }

    /* renamed from: a */
    public void m12836a(String str, Object... objArr) {
        NLog mobLog = MobLog.getInstance();
        mobLog.m11342d("[MC][APC]" + String.format(str, objArr), new Object[0]);
    }

    /* renamed from: b */
    public void m12834b(String str, Object... objArr) {
        NLog mobLog = MobLog.getInstance();
        mobLog.m11333i("[MC][APC]" + String.format(str, objArr));
    }
}
