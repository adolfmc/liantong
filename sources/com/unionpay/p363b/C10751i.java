package com.unionpay.p363b;

import android.util.Log;
import com.unionpay.UPSEInfoResp;
import com.unionpay.tsmservice.p364mi.UPTsmAddon;
import com.unionpay.utils.C10923j;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.unionpay.b.i */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class C10751i implements UPTsmAddon.UPTsmConnectionListener {

    /* renamed from: a */
    final /* synthetic */ C10749g f20677a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C10751i(C10749g c10749g) {
        this.f20677a = c10749g;
    }

    @Override // com.unionpay.tsmservice.p364mi.UPTsmAddon.UPTsmConnectionListener
    public final void onTsmConnected() {
        C10923j.m5828c("uppay", "mi TsmService connected.");
        this.f20677a.m5929b();
    }

    @Override // com.unionpay.tsmservice.p364mi.UPTsmAddon.UPTsmConnectionListener
    public final void onTsmDisconnected() {
        String str;
        String str2;
        Log.e("uppay", "mi TsmService disconnected.");
        C10749g c10749g = this.f20677a;
        str = c10749g.f20669d;
        str2 = this.f20677a.f20670e;
        c10749g.m5930a(str, str2, UPSEInfoResp.ERROR_NONE, "Tsm service disconnect");
    }
}
