package p001a.p058b.p062b.p063a.p064a.p081k;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: a.b.b.a.a.k.d */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class ThreadFactoryC0772d implements ThreadFactory {

    /* renamed from: a */
    public final ThreadGroup f2387a;

    /* renamed from: b */
    public final String f2388b;

    /* renamed from: c */
    public final AtomicInteger f2389c = new AtomicInteger(1);

    public ThreadFactoryC0772d(String str) {
        SecurityManager securityManager = System.getSecurityManager();
        this.f2387a = securityManager == null ? Thread.currentThread().getThreadGroup() : securityManager.getThreadGroup();
        this.f2388b = "APM_" + str + "-";
    }

    @Override // java.util.concurrent.ThreadFactory
    public Thread newThread(Runnable runnable) {
        ThreadGroup threadGroup = this.f2387a;
        Thread thread = new Thread(threadGroup, runnable, this.f2388b + this.f2389c.getAndIncrement(), 0L);
        if (thread.isDaemon()) {
            thread.setDaemon(false);
        }
        if (thread.getPriority() != 5) {
            thread.setPriority(5);
        }
        return thread;
    }
}
