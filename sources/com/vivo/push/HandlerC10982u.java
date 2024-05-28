package com.vivo.push;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.vivo.push.util.LogUtil;

/* compiled from: PushClientThread.java */
/* renamed from: com.vivo.push.u */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
final class HandlerC10982u extends Handler {
    /* JADX INFO: Access modifiers changed from: package-private */
    public HandlerC10982u(Looper looper) {
        super(looper);
    }

    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        Object obj = message.obj;
        if (obj instanceof PushClientTask) {
            PushClientTask pushClientTask = (PushClientTask) obj;
            LogUtil.m5342c("PushClientThread", "PushClientThread-handleMessage, task = ".concat(String.valueOf(pushClientTask)));
            pushClientTask.run();
        }
    }
}
