package com.alipay.android.phone.mrpc.core;

import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.alipay.android.phone.mrpc.core.m */
/* loaded from: E:\10201592_dexfile_execute.dex */
final class C1945m extends FutureTask<C1953u> {

    /* renamed from: a */
    final /* synthetic */ CallableC1949q f3428a;

    /* renamed from: b */
    final /* synthetic */ C1944l f3429b;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C1945m(C1944l c1944l, Callable callable, CallableC1949q callableC1949q) {
        super(callable);
        this.f3429b = c1944l;
        this.f3428a = callableC1949q;
    }

    @Override // java.util.concurrent.FutureTask
    protected final void done() {
        C1947o m21086a = this.f3428a.m21086a();
        if (m21086a.m21069f() == null) {
            super.done();
            return;
        }
        try {
            get();
            if (isCancelled() || m21086a.m21067h()) {
                m21086a.m21068g();
                if (isCancelled() && isDone()) {
                    return;
                }
                cancel(false);
            }
        } catch (InterruptedException e) {
            new StringBuilder().append(e);
        } catch (CancellationException unused) {
            m21086a.m21068g();
        } catch (ExecutionException e2) {
            if (e2.getCause() == null || !(e2.getCause() instanceof HttpException)) {
                new StringBuilder().append(e2);
                return;
            }
            HttpException httpException = (HttpException) e2.getCause();
            httpException.getCode();
            httpException.getMsg();
        } catch (Throwable th) {
            throw new RuntimeException("An error occured while executing http request", th);
        }
    }
}
