package com.vivo.push;

import android.content.Context;
import android.text.TextUtils;
import com.vivo.push.restructure.PushClientController;
import com.vivo.push.util.Utility;

/* renamed from: com.vivo.push.aa */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
final class SubscribeImpl implements Runnable {

    /* renamed from: a */
    final /* synthetic */ String f20861a;

    /* renamed from: b */
    final /* synthetic */ C10992z f20862b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SubscribeImpl(C10992z c10992z, String str) {
        this.f20862b = c10992z;
        this.f20861a = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Context m5591b = PushClientController.m5593a().m5591b();
        if (m5591b == null) {
            return;
        }
        long j = PushClientManager.m5648a().m5616f() ? 488L : 341L;
        if (TextUtils.isEmpty(this.f20861a) || !Utility.m5444a(m5591b, m5591b.getPackageName(), this.f20861a, j)) {
            return;
        }
        PushClientController.m5593a().m5588e().mo5532e();
        this.f20862b.f21258d = "";
    }
}
