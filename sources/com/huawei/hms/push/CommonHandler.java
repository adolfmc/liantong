package com.huawei.hms.push;

import android.os.Handler;
import android.os.Message;
import java.lang.ref.WeakReference;

/* renamed from: com.huawei.hms.push.e */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class CommonHandler extends Handler {

    /* renamed from: a */
    private WeakReference<InterfaceC5050a> f11624a;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* compiled from: CommonHandler.java */
    /* renamed from: com.huawei.hms.push.e$a */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface InterfaceC5050a {
        /* renamed from: a */
        void mo14273a(Message message);
    }

    public CommonHandler(InterfaceC5050a interfaceC5050a) {
        this.f11624a = new WeakReference<>(interfaceC5050a);
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        super.handleMessage(message);
        InterfaceC5050a interfaceC5050a = this.f11624a.get();
        if (interfaceC5050a != null) {
            interfaceC5050a.mo14273a(message);
        }
    }
}
