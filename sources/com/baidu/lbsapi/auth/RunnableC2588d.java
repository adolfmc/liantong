package com.baidu.lbsapi.auth;

import android.content.Context;
import java.util.HashMap;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.lbsapi.auth.d */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class RunnableC2588d implements Runnable {

    /* renamed from: a */
    final /* synthetic */ C2586c f4972a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC2588d(C2586c c2586c) {
        this.f4972a = c2586c;
    }

    @Override // java.lang.Runnable
    public void run() {
        Context context;
        HashMap<String, String> hashMap;
        C2583a.m19676a("postWithHttps start Thread id = " + String.valueOf(Thread.currentThread().getId()));
        context = this.f4972a.f4969a;
        C2592g c2592g = new C2592g(context);
        hashMap = this.f4972a.f4970b;
        this.f4972a.m19663a(c2592g.m19651a(hashMap));
    }
}
