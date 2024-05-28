package com.baidu.mapsdkplatform.comapi.commonutils;

import com.baidu.mapsdkplatform.comapi.commonutils.C2898b;
import com.baidu.platform.comjni.engine.NAEngine;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.mapsdkplatform.comapi.commonutils.c */
/* loaded from: E:\10201592_dexfile_execute.dex */
class RunnableC2903c implements Runnable {

    /* renamed from: a */
    final /* synthetic */ C2898b.EnumC2900b f7174a;

    /* renamed from: b */
    final /* synthetic */ String f7175b;

    /* renamed from: c */
    final /* synthetic */ String f7176c;

    /* renamed from: d */
    final /* synthetic */ C2898b f7177d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC2903c(C2898b c2898b, C2898b.EnumC2900b enumC2900b, String str, String str2) {
        this.f7177d = c2898b;
        this.f7174a = enumC2900b;
        this.f7175b = str;
        this.f7176c = str2;
    }

    @Override // java.lang.Runnable
    public void run() {
        NAEngine.m17674a(this.f7174a.ordinal(), this.f7175b, this.f7176c);
    }
}
