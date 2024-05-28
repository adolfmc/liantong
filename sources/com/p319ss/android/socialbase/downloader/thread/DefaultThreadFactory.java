package com.p319ss.android.socialbase.downloader.thread;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: com.ss.android.socialbase.downloader.thread.DefaultThreadFactory */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class DefaultThreadFactory implements ThreadFactory {
    private final boolean ignoreStatusCheck;
    private final String threadName;
    private final AtomicInteger threadSeq;

    public DefaultThreadFactory(String str) {
        this(str, false);
    }

    public DefaultThreadFactory(String str, boolean z) {
        this.threadSeq = new AtomicInteger();
        this.threadName = str;
        this.ignoreStatusCheck = z;
    }

    @Override // java.util.concurrent.ThreadFactory
    public Thread newThread(Runnable runnable) {
        int incrementAndGet = this.threadSeq.incrementAndGet();
        Thread thread = new Thread(runnable, this.threadName + "-" + incrementAndGet);
        if (!this.ignoreStatusCheck) {
            if (thread.isDaemon()) {
                thread.setDaemon(false);
            }
            if (thread.getPriority() != 5) {
                thread.setPriority(5);
            }
        }
        return thread;
    }
}
