package com.mob.mcl.p236d;

import com.mob.tools.MobLog;
import com.mob.tools.log.NLog;

/* renamed from: com.mob.mcl.d.b */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5957b {

    /* renamed from: a */
    private static C5957b f14670a = new C5957b();

    private C5957b() {
    }

    /* renamed from: a */
    public static C5957b m11958a() {
        return f14670a;
    }

    /* renamed from: a */
    public void m11957a(String str) {
        NLog mobLog = MobLog.getInstance();
        mobLog.m11342d("[MC][MCL]" + str, new Object[0]);
    }

    /* renamed from: b */
    public void m11954b(String str) {
        NLog mobLog = MobLog.getInstance();
        mobLog.m11342d("[MC][MCL]" + str, new Object[0]);
    }

    /* renamed from: a */
    public void m11955a(Throwable th) {
        MobLog.getInstance().m11340d(th, "%s", "[MC][MCL]");
    }

    /* renamed from: a */
    public void m11956a(String str, Throwable th) {
        NLog mobLog = MobLog.getInstance();
        mobLog.m11340d(th, "%s", "[MC][MCL] " + str);
    }
}
