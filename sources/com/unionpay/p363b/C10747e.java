package com.unionpay.p363b;

import android.util.Log;
import com.unionpay.UPSEInfoResp;
import com.unionpay.tsmservice.UPTsmAddon;
import com.unionpay.utils.C10923j;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.unionpay.b.e */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class C10747e implements UPTsmAddon.UPTsmConnectionListener {

    /* renamed from: a */
    final /* synthetic */ C10744b f20664a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C10747e(C10744b c10744b) {
        this.f20664a = c10744b;
    }

    @Override // com.unionpay.tsmservice.UPTsmAddon.UPTsmConnectionListener
    public final void onTsmConnected() {
        C10923j.m5828c("uppay", "TsmService connected.");
        this.f20664a.m5949b();
    }

    @Override // com.unionpay.tsmservice.UPTsmAddon.UPTsmConnectionListener
    public final void onTsmDisconnected() {
        String str;
        String str2;
        Log.e("uppay", "TsmService disconnected.");
        C10744b c10744b = this.f20664a;
        str = c10744b.f20654d;
        str2 = this.f20664a.f20655e;
        c10744b.m5950a(str, str2, UPSEInfoResp.ERROR_NONE, "Tsm service disconnect");
    }
}
