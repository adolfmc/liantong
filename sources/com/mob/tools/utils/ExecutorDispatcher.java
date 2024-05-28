package com.mob.tools.utils;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.mob.tools.MobLog;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public final class ExecutorDispatcher implements InterfaceC6199a {

    /* renamed from: b */
    private static volatile ExecutorDispatcher f15177b;

    /* renamed from: a */
    private final InterfaceC6199a f15178a = new C6159a();

    public static InterfaceC6199a getInstance() {
        if (f15177b == null) {
            synchronized (ExecutorDispatcher.class) {
                if (f15177b == null) {
                    f15177b = new ExecutorDispatcher();
                }
            }
        }
        return f15177b;
    }

    @Override // com.mob.tools.utils.InterfaceC6199a
    public <T extends SafeRunnable> void executeImmediately(T t) {
        if (t == null) {
            return;
        }
        try {
            this.f15178a.executeImmediately(t);
        } catch (Throwable th) {
            MobLog.getInstance().m11341d(th);
        }
    }

    @Override // com.mob.tools.utils.InterfaceC6199a
    public <T extends SafeRunnable> void executeSerial(T t) {
        if (t == null) {
            return;
        }
        try {
            this.f15178a.executeSerial(t);
        } catch (Throwable th) {
            MobLog.getInstance().m11341d(th);
        }
    }

    @Override // com.mob.tools.utils.InterfaceC6199a
    public <T extends SafeRunnable> void executeDuctile(T t) {
        if (t == null) {
            return;
        }
        try {
            this.f15178a.executeDuctile(t);
        } catch (Throwable th) {
            MobLog.getInstance().m11341d(th);
        }
    }

    @Override // com.mob.tools.utils.InterfaceC6199a
    public <T extends SafeRunnable> void executeDelayed(T t, long j) {
        if (t == null) {
            return;
        }
        try {
            if (j <= 0) {
                this.f15178a.executeDuctile(t);
            } else {
                this.f15178a.executeDelayed(t, j);
            }
        } catch (Throwable th) {
            MobLog.getInstance().m11341d(th);
        }
    }

    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public static abstract class SafeRunnable implements Runnable {
        public void afterRun() {
        }

        public void beforeRun() {
        }

        public void handleException(Throwable th) {
        }

        public String name() {
            return "";
        }

        public abstract void safeRun();

        @Override // java.lang.Runnable
        public void run() {
            try {
                String name = name();
                if (!TextUtils.isEmpty(name)) {
                    Thread.currentThread().setName(name);
                }
                beforeRun();
                safeRun();
                afterRun();
            } catch (Throwable th) {
                try {
                    handleException(th);
                } catch (Throwable unused) {
                }
            }
        }
    }

    /* renamed from: com.mob.tools.utils.ExecutorDispatcher$a */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    static final class C6159a implements InterfaceC6199a {

        /* renamed from: a */
        private final ExecutorService f15179a;

        /* renamed from: b */
        private final ExecutorService f15180b;

        /* renamed from: c */
        private final ExecutorService f15181c;

        /* renamed from: d */
        private final Handler f15182d;

        private C6159a() {
            this.f15182d = new Handler(Looper.getMainLooper());
            ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, Integer.MAX_VALUE, 10L, TimeUnit.SECONDS, new SynchronousQueue());
            threadPoolExecutor.allowCoreThreadTimeOut(true);
            this.f15179a = threadPoolExecutor;
            this.f15180b = new ThreadPoolExecutor(2, 2, 10L, TimeUnit.SECONDS, new LinkedBlockingQueue());
            this.f15181c = Executors.newSingleThreadExecutor();
        }

        @Override // com.mob.tools.utils.InterfaceC6199a
        public <T extends SafeRunnable> void executeImmediately(T t) {
            try {
                this.f15179a.execute(t);
            } catch (Throwable th) {
                MobLog.getInstance().m11341d(th);
            }
        }

        @Override // com.mob.tools.utils.InterfaceC6199a
        public <T extends SafeRunnable> void executeSerial(T t) {
            try {
                this.f15181c.execute(t);
            } catch (Throwable th) {
                MobLog.getInstance().m11341d(th);
            }
        }

        @Override // com.mob.tools.utils.InterfaceC6199a
        public <T extends SafeRunnable> void executeDuctile(T t) {
            try {
                this.f15180b.execute(t);
            } catch (Throwable th) {
                MobLog.getInstance().m11341d(th);
            }
        }

        @Override // com.mob.tools.utils.InterfaceC6199a
        public <T extends SafeRunnable> void executeDelayed(final T t, long j) {
            if (t == null) {
                return;
            }
            try {
                this.f15182d.postDelayed(new SafeRunnable() { // from class: com.mob.tools.utils.ExecutorDispatcher.a.1
                    @Override // com.mob.tools.utils.ExecutorDispatcher.SafeRunnable
                    public void safeRun() {
                        C6159a.this.executeImmediately(t);
                    }
                }, j);
            } catch (Throwable th) {
                MobLog.getInstance().m11341d(th);
            }
        }
    }
}
