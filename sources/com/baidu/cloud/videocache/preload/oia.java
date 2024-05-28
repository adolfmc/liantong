package com.baidu.cloud.videocache.preload;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public abstract class oia {

    /* renamed from: a */
    private HandlerThread f4937a = null;

    /* renamed from: b */
    private Handler f4938b = null;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.cloud.videocache.preload.oia$oia  reason: collision with other inner class name */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    class HandlerC14291oia extends Handler {
        HandlerC14291oia(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            oia.this.mo19739b(message);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public Message m19744a(int i) {
        Handler handler = this.f4938b;
        if (handler != null) {
            return handler.obtainMessage(i);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public Message m19743a(int i, Object obj) {
        return m19742a(i, obj, -1, -1);
    }

    /* renamed from: a */
    protected Message m19742a(int i, Object obj, int i2, int i3) {
        Handler handler = this.f4938b;
        if (handler != null) {
            Message obtainMessage = handler.obtainMessage(i);
            obtainMessage.obj = obj;
            obtainMessage.arg1 = i2;
            obtainMessage.arg2 = i3;
            return obtainMessage;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public synchronized void m19745a() {
        if (this.f4937a == null) {
            this.f4937a = new HandlerThread("" + getClass().getSimpleName());
            this.f4937a.start();
            this.f4938b = new HandlerC14291oia(this.f4937a.getLooper());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public void m19741a(Message message) {
        Handler handler = this.f4938b;
        if (handler != null) {
            handler.sendMessage(message);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: b */
    public synchronized void m19740b() {
        if (this.f4937a != null) {
            this.f4937a.quit();
            this.f4937a = null;
        }
        if (this.f4938b != null) {
            this.f4938b.removeCallbacksAndMessages(null);
            this.f4938b = null;
        }
    }

    /* renamed from: b */
    protected abstract void mo19739b(Message message);
}
