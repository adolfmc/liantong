package com.baidu.p120ar.capture;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.capture.AsyncWorker */
/* loaded from: E:\10201592_dexfile_execute.dex */
class AsyncWorker {
    private WorkerHandler mHandler;
    private String mTag;
    private HandlerThread mWorkerThread;

    public AsyncWorker(String str) {
        this.mTag = str;
    }

    public void start() {
        this.mWorkerThread = new HandlerThread(this.mTag);
        this.mWorkerThread.start();
        this.mHandler = new WorkerHandler(this.mWorkerThread.getLooper());
    }

    public void stop() {
        WorkerHandler workerHandler = this.mHandler;
        if (workerHandler != null) {
            workerHandler.release();
            this.mHandler = null;
        }
        HandlerThread handlerThread = this.mWorkerThread;
        if (handlerThread != null) {
            handlerThread.quit();
            this.mWorkerThread = null;
        }
    }

    public void execute(Runnable runnable) {
        WorkerHandler workerHandler = this.mHandler;
        if (workerHandler != null) {
            workerHandler.sendMessage(runnable);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.ar.capture.AsyncWorker$WorkerHandler */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    static final class WorkerHandler extends Handler {
        public WorkerHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            Runnable runnable = (Runnable) message.obj;
            if (runnable != null) {
                runnable.run();
            }
        }

        public void sendMessage(Runnable runnable) {
            Message obtain = Message.obtain();
            obtain.obj = runnable;
            sendMessage(obtain);
        }

        public void release() {
            Thread.currentThread().interrupt();
        }
    }
}
