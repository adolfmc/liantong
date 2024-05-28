package com.xuhao.didi.socket.common.interfaces.basic;

import com.xuhao.didi.core.utils.SLog;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public abstract class AbsLoopThread implements Runnable {
    private volatile boolean isStop;
    protected volatile String threadName;
    public volatile Thread thread = null;
    private volatile boolean isShutdown = true;
    private volatile Exception ioException = null;
    private volatile long loopTimes = 0;

    /* JADX INFO: Access modifiers changed from: protected */
    public void beforeLoop() throws Exception {
    }

    protected abstract void loopFinish(Exception exc);

    protected abstract void runInLoopThread() throws Exception;

    public AbsLoopThread() {
        this.threadName = "";
        this.isStop = false;
        this.isStop = true;
        this.threadName = getClass().getSimpleName();
    }

    public AbsLoopThread(String str) {
        this.threadName = "";
        this.isStop = false;
        this.isStop = true;
        this.threadName = str;
    }

    public synchronized void start() {
        if (this.isStop) {
            this.thread = new Thread(this, this.threadName);
            this.isStop = false;
            this.loopTimes = 0L;
            this.thread.start();
            SLog.m2256w(this.threadName + " is starting");
        }
    }

    @Override // java.lang.Runnable
    public final void run() {
        StringBuilder sb;
        try {
            try {
                this.isShutdown = false;
                beforeLoop();
                while (!this.isStop) {
                    runInLoopThread();
                    this.loopTimes++;
                }
                this.isShutdown = true;
                loopFinish(this.ioException);
                this.ioException = null;
                sb = new StringBuilder();
            } catch (Exception e) {
                if (this.ioException == null) {
                    this.ioException = e;
                }
                this.isShutdown = true;
                loopFinish(this.ioException);
                this.ioException = null;
                sb = new StringBuilder();
            }
            sb.append(this.threadName);
            sb.append(" is shutting down");
            SLog.m2256w(sb.toString());
        } catch (Throwable th) {
            this.isShutdown = true;
            loopFinish(this.ioException);
            this.ioException = null;
            SLog.m2256w(this.threadName + " is shutting down");
            throw th;
        }
    }

    public long getLoopTimes() {
        return this.loopTimes;
    }

    public String getThreadName() {
        return this.threadName;
    }

    public synchronized void shutdown() {
        if (this.thread != null && !this.isStop) {
            this.isStop = true;
            this.thread.interrupt();
            this.thread = null;
        }
    }

    public synchronized void shutdown(Exception exc) {
        this.ioException = exc;
        shutdown();
    }

    public boolean isShutdown() {
        return this.isShutdown;
    }
}
