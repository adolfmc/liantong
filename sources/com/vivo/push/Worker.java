package com.vivo.push;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.vivo.push.util.LogUtil;

/* renamed from: com.vivo.push.ab */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public abstract class Worker {

    /* renamed from: a */
    public Context f20863a;

    /* renamed from: b */
    protected Handler f20864b;

    /* renamed from: c */
    private final Object f20865c = new Object();

    /* renamed from: b */
    public abstract void mo5485b(Message message);

    public Worker() {
        HandlerThread handlerThread = new HandlerThread(getClass().getSimpleName(), 1);
        handlerThread.start();
        this.f20864b = new HandlerC10933a(handlerThread.getLooper());
    }

    /* renamed from: a */
    public final void m5813a(Context context) {
        this.f20863a = context;
    }

    /* renamed from: a */
    public final void m5812a(Message message) {
        synchronized (this.f20865c) {
            if (this.f20864b == null) {
                String simpleName = getClass().getSimpleName();
                LogUtil.m5340e(simpleName, ("Dead worker dropping a message: " + message.what) + " (Thread " + Thread.currentThread().getId() + ")");
            } else {
                this.f20864b.sendMessage(message);
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* compiled from: Worker.java */
    /* renamed from: com.vivo.push.ab$a */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    class HandlerC10933a extends Handler {
        public HandlerC10933a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            Worker.this.mo5485b(message);
        }
    }
}
