package com.baidu.platform.comapi.util;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.platform.comapi.util.h */
/* loaded from: E:\10201592_dexfile_execute.dex */
public abstract class AbstractHandlerC3096h extends Handler {
    public AbstractHandlerC3096h(Looper looper) {
        super(looper);
    }

    /* renamed from: a */
    public abstract void mo17681a(Message message);

    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        if (message == null) {
            return;
        }
        Message obtain = Message.obtain();
        obtain.copyFrom(message);
        mo17681a(obtain);
        obtain.recycle();
    }
}
