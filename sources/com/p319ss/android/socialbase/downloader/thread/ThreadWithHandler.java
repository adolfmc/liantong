package com.p319ss.android.socialbase.downloader.thread;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/* renamed from: com.ss.android.socialbase.downloader.thread.ThreadWithHandler */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class ThreadWithHandler {
    private InnerThread handlerThread;
    private Object lock = new Object();
    private Queue<MessageEntity> mCacheQueue = new ConcurrentLinkedQueue();
    private Handler mHandler;

    public ThreadWithHandler(String str) {
        this.handlerThread = new InnerThread(str);
    }

    public void start() {
        this.handlerThread.start();
    }

    public void post(Runnable runnable) {
        postDelayed(runnable, 0L);
    }

    public void postDelayed(Runnable runnable, long j) {
        if (this.mHandler == null) {
            synchronized (this.lock) {
                if (this.mHandler == null) {
                    this.mCacheQueue.add(new MessageEntity(runnable, j));
                    return;
                }
            }
        }
        this.mHandler.postDelayed(runnable, j);
    }

    public void quit() {
        this.handlerThread.quit();
    }

    /* renamed from: com.ss.android.socialbase.downloader.thread.ThreadWithHandler$InnerThread */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    class InnerThread extends HandlerThread {
        InnerThread(String str) {
            super(str);
        }

        @Override // android.os.HandlerThread
        protected void onLooperPrepared() {
            super.onLooperPrepared();
            Looper looper = getLooper();
            synchronized (ThreadWithHandler.this.lock) {
                ThreadWithHandler.this.mHandler = new Handler(looper);
            }
            while (!ThreadWithHandler.this.mCacheQueue.isEmpty()) {
                MessageEntity messageEntity = (MessageEntity) ThreadWithHandler.this.mCacheQueue.poll();
                if (messageEntity != null) {
                    ThreadWithHandler.this.mHandler.postDelayed(messageEntity.target, messageEntity.delayMillis);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.ss.android.socialbase.downloader.thread.ThreadWithHandler$MessageEntity */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public class MessageEntity {
        public long delayMillis;
        public Runnable target;

        public MessageEntity(Runnable runnable, long j) {
            this.target = runnable;
            this.delayMillis = j;
        }
    }
}
