package com.unionpay.p363b;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.unionpay.UPSEInfoResp;
import com.unionpay.utils.C10923j;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.unionpay.b.c */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class C10745c implements Handler.Callback {

    /* renamed from: a */
    final /* synthetic */ C10744b f20662a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C10745c(C10744b c10744b) {
        this.f20662a = c10744b;
    }

    @Override // android.os.Handler.Callback
    public final boolean handleMessage(Message message) {
        Handler handler;
        String str;
        String str2;
        Handler handler2;
        Handler handler3;
        Handler handler4;
        Handler handler5;
        int i;
        boolean z;
        int i2 = message.what;
        if (i2 != 1) {
            if (i2 != 4) {
                switch (i2) {
                    case 4000:
                        handler2 = this.f20662a.f20660j;
                        handler2.removeMessages(4);
                        C10744b.m5954a(this.f20662a, (Bundle) message.obj);
                        break;
                    case 4001:
                        handler3 = this.f20662a.f20660j;
                        handler3.removeMessages(4003);
                        if (message.obj instanceof Bundle) {
                            C10744b.m5947b(this.f20662a, (Bundle) message.obj);
                            break;
                        }
                        break;
                    case 4002:
                        handler4 = this.f20662a.f20660j;
                        handler4.removeMessages(4003);
                        if (message.obj instanceof Bundle) {
                            C10744b.m5944c(this.f20662a, (Bundle) message.obj);
                            break;
                        }
                        break;
                    case 4003:
                        str = "uppay";
                        str2 = "queryHwPayStatus timeout";
                        break;
                    case 4004:
                        handler5 = this.f20662a.f20660j;
                        handler5.removeMessages(4005);
                        try {
                            i = ((Integer) message.obj).intValue();
                        } catch (Exception unused) {
                            i = 0;
                        }
                        z = this.f20662a.f20657g;
                        if (!z) {
                            if (i == 1) {
                                C10744b.m5941e(this.f20662a);
                                break;
                            }
                            this.f20662a.m5946c();
                            break;
                        }
                        break;
                    case 4005:
                        C10923j.m5828c("uppay", "QUERY_VENDOR_CAPACITY_TIMEOUT");
                        C10744b.m5939g(this.f20662a);
                        this.f20662a.m5946c();
                        break;
                }
            } else {
                str = "uppay";
                str2 = "timeout";
            }
            C10923j.m5828c(str, str2);
            r6.m5950a(r6.f20654d, this.f20662a.f20655e, UPSEInfoResp.ERROR_TIMEOUT, "timeout");
            C10744b.m5945c(this.f20662a);
        } else {
            handler = this.f20662a.f20660j;
            handler.removeMessages(4);
            C10923j.m5828c("uppay", "msg error");
            C10744b.m5955a(this.f20662a, message.arg1, (String) message.obj);
        }
        return false;
    }
}
