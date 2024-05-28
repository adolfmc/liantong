package com.unionpay.p363b;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.unionpay.UPSEInfoResp;
import com.unionpay.utils.C10923j;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.unionpay.b.h */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class C10750h implements Handler.Callback {

    /* renamed from: a */
    final /* synthetic */ C10749g f20676a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C10750h(C10749g c10749g) {
        this.f20676a = c10749g;
    }

    @Override // android.os.Handler.Callback
    public final boolean handleMessage(Message message) {
        Handler handler;
        Handler handler2;
        int i = message.what;
        if (i == 1) {
            handler = this.f20676a.f20674i;
            handler.removeMessages(4);
            C10923j.m5828c("uppay", "msg error");
            C10749g.m5934a(this.f20676a, message.arg1, (String) message.obj);
            return false;
        } else if (i == 4) {
            C10923j.m5828c("uppay", "timeout");
            C10749g.m5934a(this.f20676a, message.arg1, UPSEInfoResp.ERROR_TIMEOUT);
            C10749g.m5928b(this.f20676a);
            return false;
        } else if (i != 4000) {
            return false;
        } else {
            handler2 = this.f20676a.f20674i;
            handler2.removeMessages(4);
            C10749g.m5933a(this.f20676a, (Bundle) message.obj);
            return false;
        }
    }
}
