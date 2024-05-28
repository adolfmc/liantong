package com.vivo.push.util;

import java.util.concurrent.ThreadFactory;

/* compiled from: ConcurrentUtils.java */
/* renamed from: com.vivo.push.util.h */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
final class ThreadFactoryC10989h implements ThreadFactory {

    /* renamed from: a */
    final /* synthetic */ String f21213a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ThreadFactoryC10989h(String str) {
        this.f21213a = str;
    }

    @Override // java.util.concurrent.ThreadFactory
    public final Thread newThread(Runnable runnable) {
        Thread thread = new Thread(runnable);
        thread.setName(this.f21213a);
        thread.setDaemon(true);
        return thread;
    }
}
