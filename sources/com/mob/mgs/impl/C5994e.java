package com.mob.mgs.impl;

import com.mob.tools.MobLog;
import com.mob.tools.log.NLog;

/* renamed from: com.mob.mgs.impl.e */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5994e {

    /* renamed from: a */
    private static C5994e f14762a = new C5994e();

    private C5994e() {
    }

    /* renamed from: a */
    public static C5994e m11860a() {
        return f14762a;
    }

    /* renamed from: a */
    public void m11859a(String str) {
        NLog mobLog = MobLog.getInstance();
        mobLog.m11342d("[MC][MGS]" + str, new Object[0]);
    }

    /* renamed from: b */
    public void m11856b(String str) {
        NLog mobLog = MobLog.getInstance();
        mobLog.m11342d("[MC][MGS]" + str, new Object[0]);
    }

    /* renamed from: a */
    public void m11857a(Throwable th) {
        MobLog.getInstance().m11340d(th, "[MC][MGS]", new Object[0]);
    }

    /* renamed from: a */
    public void m11858a(String str, Throwable th) {
        NLog mobLog = MobLog.getInstance();
        mobLog.m11340d(th, "[MC][MGS] " + str, new Object[0]);
    }

    /* renamed from: b */
    public void m11855b(Throwable th) {
        MobLog.getInstance().m11335e(th, "[MC][MGS]", new Object[0]);
    }
}
