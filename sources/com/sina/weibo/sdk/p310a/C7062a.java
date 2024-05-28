package com.sina.weibo.sdk.p310a;

import com.sina.weibo.sdk.p310a.AbstractC7066c;
import java.util.Comparator;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.sina.weibo.sdk.a.a */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class C7062a {

    /* renamed from: E */
    private static final int f18264E;

    /* renamed from: F */
    private static final int f18265F;

    /* renamed from: G */
    private static final int f18266G;

    /* renamed from: I */
    private static final Comparator<Runnable> f18267I;

    /* renamed from: H */
    private ThreadPoolExecutor f18268H;

    static {
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        f18264E = availableProcessors;
        f18265F = availableProcessors + 1;
        f18266G = (f18264E * 2) + 1;
        f18267I = new Comparator<Runnable>() { // from class: com.sina.weibo.sdk.a.a.1
            @Override // java.util.Comparator
            public final /* bridge */ /* synthetic */ int compare(Runnable runnable, Runnable runnable2) {
                return 0;
            }
        };
    }

    public C7062a() {
        if (this.f18268H == null) {
            this.f18268H = new ThreadPoolExecutor(f18265F, f18266G, 1L, TimeUnit.SECONDS, new PriorityBlockingQueue(5, f18267I));
        }
    }

    /* renamed from: a */
    public final void m8103a(AbstractC7066c abstractC7066c) {
        ThreadPoolExecutor threadPoolExecutor = this.f18268H;
        if (abstractC7066c.f18271L != AbstractC7066c.EnumC7072b.f18283U) {
            switch (AbstractC7066c.C70704.f18280R[abstractC7066c.f18271L - 1]) {
                case 1:
                    throw new IllegalStateException("Cannot execute task: the task is already running.");
                case 2:
                    throw new IllegalStateException("Cannot execute task: the task has already been executed (a task can be executed only once)");
            }
        }
        abstractC7066c.f18271L = AbstractC7066c.EnumC7072b.f18284V;
        abstractC7066c.f18272M.f18288Y = abstractC7066c.f18275P;
        abstractC7066c.f18272M.priority = abstractC7066c.f18274O;
        threadPoolExecutor.execute(abstractC7066c.f18273N);
    }
}
