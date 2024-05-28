package com.baidu.location.p138c;

import android.location.OnNmeaMessageListener;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.location.c.g */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2706g implements OnNmeaMessageListener {

    /* renamed from: a */
    final /* synthetic */ C2697f f5605a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2706g(C2697f c2697f) {
        this.f5605a = c2697f;
    }

    @Override // android.location.OnNmeaMessageListener
    public void onNmeaMessage(String str, long j) {
        if (this.f5605a.f5552M != null) {
            this.f5605a.f5552M.sendMessage(this.f5605a.f5552M.obtainMessage(5, str));
        }
    }
}
