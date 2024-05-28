package com.baidu.p120ar.bus;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import com.baidu.p120ar.utils.ARLog;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.bus.HandlerPoster */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class HandlerPoster extends Handler {
    private final ARBus arBus;
    private boolean handlerActive;
    private boolean isRelease;
    private final int maxMillisInsideHandleMessage;
    private final PendingPostQueue queue;

    /* JADX INFO: Access modifiers changed from: protected */
    public HandlerPoster(ARBus aRBus, Looper looper, int i) {
        super(looper);
        this.isRelease = false;
        this.arBus = aRBus;
        this.maxMillisInsideHandleMessage = i;
        this.queue = new PendingPostQueue();
    }

    public void enqueue(Subscription subscription, Object obj) {
        PendingPost obtainPendingPost = PendingPost.obtainPendingPost(subscription, obj);
        synchronized (this) {
            this.queue.enqueue(obtainPendingPost);
            if (!this.handlerActive) {
                this.handlerActive = true;
                if (!sendMessage(obtainMessage())) {
                    ARLog.m20420e("Could not send handler message");
                }
            }
        }
    }

    public void release() {
        this.isRelease = true;
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        try {
            long uptimeMillis = SystemClock.uptimeMillis();
            while (!this.isRelease) {
                PendingPost poll = this.queue.poll();
                if (poll == null) {
                    synchronized (this) {
                        poll = this.queue.poll();
                        if (poll == null) {
                            this.handlerActive = false;
                            return;
                        }
                    }
                }
                this.arBus.invokeSubscriber(poll);
                if (SystemClock.uptimeMillis() - uptimeMillis >= this.maxMillisInsideHandleMessage) {
                    if (!sendMessage(obtainMessage())) {
                        ARLog.m20420e("Could not send handler message");
                    }
                    this.handlerActive = true;
                    return;
                }
            }
        } finally {
            this.handlerActive = false;
        }
    }
}
