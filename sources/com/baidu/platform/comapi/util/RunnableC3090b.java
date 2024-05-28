package com.baidu.platform.comapi.util;

import com.baidu.platform.comapi.util.C3087a;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.platform.comapi.util.b */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class RunnableC3090b implements Runnable {

    /* renamed from: a */
    final /* synthetic */ C3087a.C3088a f8062a;

    /* renamed from: b */
    final /* synthetic */ Object f8063b;

    /* renamed from: c */
    final /* synthetic */ C3087a f8064c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC3090b(C3087a c3087a, C3087a.C3088a c3088a, Object obj) {
        this.f8064c = c3087a;
        this.f8062a = c3088a;
        this.f8063b = obj;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f8064c.m17702a(this.f8062a, this.f8063b);
    }
}
