package io.objectbox.android;

import android.os.Handler;
import android.os.Looper;
import io.objectbox.reactive.RunWithParam;
import io.objectbox.reactive.Scheduler;
import java.util.ArrayDeque;
import java.util.Deque;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class AndroidScheduler extends Handler implements Scheduler {
    private static AndroidScheduler MAIN_THREAD;
    private final Deque<Runner> freeRunners;

    public static synchronized Scheduler mainThread() {
        AndroidScheduler androidScheduler;
        synchronized (AndroidScheduler.class) {
            if (MAIN_THREAD == null) {
                MAIN_THREAD = new AndroidScheduler(Looper.getMainLooper());
            }
            androidScheduler = MAIN_THREAD;
        }
        return androidScheduler;
    }

    public AndroidScheduler(Looper looper) {
        super(looper);
        this.freeRunners = new ArrayDeque();
    }

    @Override // io.objectbox.reactive.Scheduler
    public <T> void run(RunWithParam runWithParam, T t) {
        Runner poll;
        synchronized (this.freeRunners) {
            poll = this.freeRunners.poll();
        }
        if (poll == null) {
            poll = new Runner();
        }
        poll.runWithParam = runWithParam;
        poll.param = t;
        post(poll);
    }

    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    class Runner implements Runnable {
        Object param;
        RunWithParam runWithParam;

        Runner() {
        }

        @Override // java.lang.Runnable
        public void run() {
            this.runWithParam.run(this.param);
            this.runWithParam = null;
            this.param = null;
            synchronized (AndroidScheduler.this.freeRunners) {
                if (AndroidScheduler.this.freeRunners.size() < 20) {
                    AndroidScheduler.this.freeRunners.add(this);
                }
            }
        }
    }
}
