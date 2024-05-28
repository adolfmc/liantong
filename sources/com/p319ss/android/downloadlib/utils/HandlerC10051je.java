package com.p319ss.android.downloadlib.utils;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.lang.ref.WeakReference;

/* renamed from: com.ss.android.downloadlib.utils.je */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class HandlerC10051je extends Handler {

    /* renamed from: mb */
    WeakReference<InterfaceC10052mb> f19381mb;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.ss.android.downloadlib.utils.je$mb */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface InterfaceC10052mb {
        /* renamed from: mb */
        void mo7026mb(Message message);
    }

    public HandlerC10051je(Looper looper, InterfaceC10052mb interfaceC10052mb) {
        super(looper);
        this.f19381mb = new WeakReference<>(interfaceC10052mb);
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        InterfaceC10052mb interfaceC10052mb = this.f19381mb.get();
        if (interfaceC10052mb == null || message == null) {
            return;
        }
        interfaceC10052mb.mo7026mb(message);
    }
}
