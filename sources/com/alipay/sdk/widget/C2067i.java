package com.alipay.sdk.widget;

import android.content.Intent;
import android.net.Uri;
import android.webkit.DownloadListener;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.alipay.sdk.widget.i */
/* loaded from: E:\10201592_dexfile_execute.dex */
class C2067i implements DownloadListener {

    /* renamed from: a */
    final /* synthetic */ C2066h f3943a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2067i(C2066h c2066h) {
        this.f3943a = c2066h;
    }

    @Override // android.webkit.DownloadListener
    public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
            intent.setFlags(268435456);
            this.f3943a.f3940a.startActivity(intent);
        } catch (Throwable unused) {
        }
    }
}
