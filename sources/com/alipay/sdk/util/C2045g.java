package com.alipay.sdk.util;

import com.alipay.sdk.app.AlipayResultActivity;
import com.alipay.sdk.app.C1998j;
import java.util.concurrent.CountDownLatch;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.alipay.sdk.util.g */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2045g implements AlipayResultActivity.InterfaceC1985a {

    /* renamed from: a */
    final /* synthetic */ CountDownLatch f3884a;

    /* renamed from: b */
    final /* synthetic */ C2042e f3885b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2045g(C2042e c2042e, CountDownLatch countDownLatch) {
        this.f3885b = c2042e;
        this.f3884a = countDownLatch;
    }

    @Override // com.alipay.sdk.app.AlipayResultActivity.InterfaceC1985a
    /* renamed from: a */
    public void mo20692a(int i, String str, String str2) {
        this.f3885b.f3881i = C1998j.m20914a(i, str, str2);
        this.f3884a.countDown();
    }
}
