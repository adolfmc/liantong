package com.sina.weibo.sdk.p310a;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.sina.weibo.sdk.a.c */
/* loaded from: E:\11480076_dexfile_execute.dex */
public abstract class AbstractC7066c<Params, Progress, Result> {

    /* renamed from: P */
    Params[] f18275P;

    /* renamed from: L */
    volatile int f18271L = EnumC7072b.f18283U;

    /* renamed from: O */
    int f18274O = 5;

    /* renamed from: x */
    Handler f18276x = new Handler(Looper.getMainLooper()) { // from class: com.sina.weibo.sdk.a.c.1
        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            C7071a c7071a = (C7071a) message.obj;
            if (message.what != 1) {
                return;
            }
            AbstractC7066c.m8100a(c7071a.f18281S, c7071a.f18282T[0]);
            message.obj = null;
        }
    };

    /* renamed from: M */
    final AbstractCallableC7074d<Params, Result> f18272M = new AbstractCallableC7074d<Params, Result>() { // from class: com.sina.weibo.sdk.a.c.2
        @Override // java.util.concurrent.Callable
        public final Result call() {
            Process.setThreadPriority(AbstractC7066c.this.f18274O);
            return (Result) AbstractC7066c.this.mo8096l();
        }
    };

    /* renamed from: N */
    final FutureTask<Result> f18273N = new AbstractC7066c<Params, Progress, Result>.AbstractC7073c(this.f18272M) { // from class: com.sina.weibo.sdk.a.c.3
        @Override // java.lang.Comparable
        public final int compareTo(Object obj) {
            return 0;
        }

        @Override // java.util.concurrent.FutureTask
        protected final void done() {
            try {
                AbstractC7066c.this.f18276x.obtainMessage(1, new C7071a(AbstractC7066c.this, get())).sendToTarget();
            } catch (InterruptedException unused) {
                throw new RuntimeException("An error occur while execute doInBackground().");
            } catch (CancellationException unused2) {
                AbstractC7066c.this.f18276x.obtainMessage(3, new C7071a(AbstractC7066c.this, null)).sendToTarget();
            } catch (ExecutionException unused3) {
                throw new RuntimeException("An error occur while execute doInBackground().");
            }
        }
    };

    /* renamed from: l */
    protected abstract Result mo8096l();

    protected void onPostExecute(Result result) {
    }

    /* JADX WARN: $VALUES field not found */
    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* renamed from: com.sina.weibo.sdk.a.c$b */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static final class EnumC7072b {

        /* renamed from: U */
        public static final int f18283U = 1;

        /* renamed from: V */
        public static final int f18284V = 2;

        /* renamed from: W */
        public static final int f18285W = 3;

        /* renamed from: X */
        private static final /* synthetic */ int[] f18286X = {f18283U, f18284V, f18285W};

        /* renamed from: m */
        public static int[] m8099m() {
            return (int[]) f18286X.clone();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.sina.weibo.sdk.a.c$4 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    static /* synthetic */ class C70704 {

        /* renamed from: R */
        static final /* synthetic */ int[] f18280R = new int[EnumC7072b.m8099m().length];

        static {
            try {
                f18280R[EnumC7072b.f18284V - 1] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f18280R[EnumC7072b.f18285W - 1] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.sina.weibo.sdk.a.c$d */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    static abstract class AbstractCallableC7074d<Params, Result> implements Callable<Result> {

        /* renamed from: Y */
        Params[] f18288Y;
        int priority;

        private AbstractCallableC7074d() {
            this.priority = 10;
        }

        /* synthetic */ AbstractCallableC7074d(byte b) {
            this();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.sina.weibo.sdk.a.c$c */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    abstract class AbstractC7073c extends FutureTask<Result> implements Comparable<Object> {
        int priority;

        public AbstractC7073c(AbstractCallableC7074d abstractCallableC7074d) {
            super(abstractCallableC7074d);
            this.priority = abstractCallableC7074d.priority;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.sina.weibo.sdk.a.c$a */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    static class C7071a<Data> {

        /* renamed from: S */
        final AbstractC7066c f18281S;

        /* renamed from: T */
        final Data[] f18282T;

        C7071a(AbstractC7066c abstractC7066c, Data... dataArr) {
            this.f18281S = abstractC7066c;
            this.f18282T = dataArr;
        }
    }

    /* renamed from: a */
    static /* synthetic */ void m8100a(AbstractC7066c abstractC7066c, Object obj) {
        abstractC7066c.onPostExecute(obj);
        abstractC7066c.f18271L = EnumC7072b.f18285W;
    }
}
