package com.alipay.sdk.data;

import android.content.Context;
import java.util.HashMap;
import java.util.concurrent.Callable;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.alipay.sdk.data.d */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class CallableC2010d implements Callable<String> {

    /* renamed from: a */
    final /* synthetic */ Context f3751a;

    /* renamed from: b */
    final /* synthetic */ HashMap f3752b;

    /* renamed from: c */
    final /* synthetic */ C2009c f3753c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public CallableC2010d(C2009c c2009c, Context context, HashMap hashMap) {
        this.f3753c = c2009c;
        this.f3751a = context;
        this.f3752b = hashMap;
    }

    @Override // java.util.concurrent.Callable
    /* renamed from: a */
    public String call() throws Exception {
        String m20859a;
        m20859a = this.f3753c.m20859a(this.f3751a, this.f3752b);
        return m20859a;
    }
}
