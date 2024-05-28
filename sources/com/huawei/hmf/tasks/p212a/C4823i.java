package com.huawei.hmf.tasks.p212a;

import android.app.Activity;
import com.huawei.hmf.tasks.Continuation;
import com.huawei.hmf.tasks.ExecuteResult;
import com.huawei.hmf.tasks.OnCanceledListener;
import com.huawei.hmf.tasks.OnCompleteListener;
import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hmf.tasks.SuccessContinuation;
import com.huawei.hmf.tasks.Task;
import com.huawei.hmf.tasks.TaskExecutors;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

/* renamed from: com.huawei.hmf.tasks.a.i */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public final class C4823i<TResult> extends Task<TResult> {

    /* renamed from: b */
    private boolean f10853b;

    /* renamed from: c */
    private volatile boolean f10854c;

    /* renamed from: d */
    private TResult f10855d;

    /* renamed from: e */
    private Exception f10856e;

    /* renamed from: a */
    private final Object f10852a = new Object();

    /* renamed from: f */
    private List<ExecuteResult<TResult>> f10857f = new ArrayList();

    /* renamed from: a */
    private Task<TResult> m15359a(ExecuteResult<TResult> executeResult) {
        boolean isComplete;
        synchronized (this.f10852a) {
            isComplete = isComplete();
            if (!isComplete) {
                this.f10857f.add(executeResult);
            }
        }
        if (isComplete) {
            executeResult.onComplete(this);
        }
        return this;
    }

    /* renamed from: b */
    private void m15356b() {
        synchronized (this.f10852a) {
            for (ExecuteResult<TResult> executeResult : this.f10857f) {
                try {
                    executeResult.onComplete(this);
                } catch (RuntimeException e) {
                    throw e;
                } catch (Exception e2) {
                    throw new RuntimeException(e2);
                }
            }
            this.f10857f = null;
        }
    }

    /* renamed from: a */
    public final void m15358a(Exception exc) {
        synchronized (this.f10852a) {
            if (this.f10853b) {
                return;
            }
            this.f10853b = true;
            this.f10856e = exc;
            this.f10852a.notifyAll();
            m15356b();
        }
    }

    /* renamed from: a */
    public final void m15357a(TResult tresult) {
        synchronized (this.f10852a) {
            if (this.f10853b) {
                return;
            }
            this.f10853b = true;
            this.f10855d = tresult;
            this.f10852a.notifyAll();
            m15356b();
        }
    }

    /* renamed from: a */
    public final boolean m15360a() {
        synchronized (this.f10852a) {
            if (this.f10853b) {
                return false;
            }
            this.f10853b = true;
            this.f10854c = true;
            this.f10852a.notifyAll();
            m15356b();
            return true;
        }
    }

    @Override // com.huawei.hmf.tasks.Task
    public final Task<TResult> addOnCanceledListener(Activity activity, OnCanceledListener onCanceledListener) {
        C4812b c4812b = new C4812b(TaskExecutors.uiThread(), onCanceledListener);
        FragmentC4820g.m15364a(activity, c4812b);
        return m15359a((ExecuteResult) c4812b);
    }

    @Override // com.huawei.hmf.tasks.Task
    public final Task<TResult> addOnCanceledListener(OnCanceledListener onCanceledListener) {
        return addOnCanceledListener(TaskExecutors.uiThread(), onCanceledListener);
    }

    @Override // com.huawei.hmf.tasks.Task
    public final Task<TResult> addOnCanceledListener(Executor executor, OnCanceledListener onCanceledListener) {
        return m15359a((ExecuteResult) new C4812b(executor, onCanceledListener));
    }

    @Override // com.huawei.hmf.tasks.Task
    public final Task<TResult> addOnCompleteListener(Activity activity, OnCompleteListener<TResult> onCompleteListener) {
        C4815d c4815d = new C4815d(TaskExecutors.uiThread(), onCompleteListener);
        FragmentC4820g.m15364a(activity, c4815d);
        return m15359a((ExecuteResult) c4815d);
    }

    @Override // com.huawei.hmf.tasks.Task
    public final Task<TResult> addOnCompleteListener(OnCompleteListener<TResult> onCompleteListener) {
        return addOnCompleteListener(TaskExecutors.uiThread(), onCompleteListener);
    }

    @Override // com.huawei.hmf.tasks.Task
    public final Task<TResult> addOnCompleteListener(Executor executor, OnCompleteListener<TResult> onCompleteListener) {
        return m15359a((ExecuteResult) new C4815d(executor, onCompleteListener));
    }

    @Override // com.huawei.hmf.tasks.Task
    public final Task<TResult> addOnFailureListener(Activity activity, OnFailureListener onFailureListener) {
        C4818f c4818f = new C4818f(TaskExecutors.uiThread(), onFailureListener);
        FragmentC4820g.m15364a(activity, c4818f);
        return m15359a((ExecuteResult) c4818f);
    }

    @Override // com.huawei.hmf.tasks.Task
    public final Task<TResult> addOnFailureListener(OnFailureListener onFailureListener) {
        return addOnFailureListener(TaskExecutors.uiThread(), onFailureListener);
    }

    @Override // com.huawei.hmf.tasks.Task
    public final Task<TResult> addOnFailureListener(Executor executor, OnFailureListener onFailureListener) {
        return m15359a((ExecuteResult) new C4818f(executor, onFailureListener));
    }

    @Override // com.huawei.hmf.tasks.Task
    public final Task<TResult> addOnSuccessListener(Activity activity, OnSuccessListener<TResult> onSuccessListener) {
        C4821h c4821h = new C4821h(TaskExecutors.uiThread(), onSuccessListener);
        FragmentC4820g.m15364a(activity, c4821h);
        return m15359a((ExecuteResult) c4821h);
    }

    @Override // com.huawei.hmf.tasks.Task
    public final Task<TResult> addOnSuccessListener(OnSuccessListener<TResult> onSuccessListener) {
        return addOnSuccessListener(TaskExecutors.uiThread(), onSuccessListener);
    }

    @Override // com.huawei.hmf.tasks.Task
    public final Task<TResult> addOnSuccessListener(Executor executor, OnSuccessListener<TResult> onSuccessListener) {
        return m15359a((ExecuteResult) new C4821h(executor, onSuccessListener));
    }

    @Override // com.huawei.hmf.tasks.Task
    public final <TContinuationResult> Task<TContinuationResult> continueWith(Continuation<TResult, TContinuationResult> continuation) {
        return continueWith(TaskExecutors.uiThread(), continuation);
    }

    @Override // com.huawei.hmf.tasks.Task
    public final <TContinuationResult> Task<TContinuationResult> continueWith(Executor executor, final Continuation<TResult, TContinuationResult> continuation) {
        final C4823i c4823i = new C4823i();
        addOnCompleteListener(executor, new OnCompleteListener<TResult>() { // from class: com.huawei.hmf.tasks.a.i.5
            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.huawei.hmf.tasks.OnCompleteListener
            public final void onComplete(Task<TResult> task) {
                if (task.isCanceled()) {
                    c4823i.m15360a();
                    return;
                }
                try {
                    c4823i.m15357a((C4823i) continuation.then(task));
                } catch (Exception e) {
                    c4823i.m15358a(e);
                }
            }
        });
        return c4823i;
    }

    @Override // com.huawei.hmf.tasks.Task
    public final <TContinuationResult> Task<TContinuationResult> continueWithTask(Continuation<TResult, Task<TContinuationResult>> continuation) {
        return continueWithTask(TaskExecutors.uiThread(), continuation);
    }

    @Override // com.huawei.hmf.tasks.Task
    public final <TContinuationResult> Task<TContinuationResult> continueWithTask(Executor executor, final Continuation<TResult, Task<TContinuationResult>> continuation) {
        final C4823i c4823i = new C4823i();
        addOnCompleteListener(executor, new OnCompleteListener<TResult>() { // from class: com.huawei.hmf.tasks.a.i.4
            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.huawei.hmf.tasks.OnCompleteListener
            public final void onComplete(Task<TResult> task) {
                try {
                    Task task2 = (Task) continuation.then(task);
                    if (task2 == 0) {
                        c4823i.m15358a((Exception) new NullPointerException("Continuation returned null"));
                    } else {
                        task2.addOnCompleteListener(new OnCompleteListener<TContinuationResult>() { // from class: com.huawei.hmf.tasks.a.i.4.1
                            @Override // com.huawei.hmf.tasks.OnCompleteListener
                            public final void onComplete(Task<TContinuationResult> task3) {
                                if (task3.isSuccessful()) {
                                    c4823i.m15357a((C4823i) task3.getResult());
                                } else if (task3.isCanceled()) {
                                    c4823i.m15360a();
                                } else {
                                    c4823i.m15358a(task3.getException());
                                }
                            }
                        });
                    }
                } catch (Exception e) {
                    c4823i.m15358a(e);
                }
            }
        });
        return c4823i;
    }

    @Override // com.huawei.hmf.tasks.Task
    public final Exception getException() {
        Exception exc;
        synchronized (this.f10852a) {
            exc = this.f10856e;
        }
        return exc;
    }

    @Override // com.huawei.hmf.tasks.Task
    public final TResult getResult() {
        TResult tresult;
        synchronized (this.f10852a) {
            if (this.f10856e != null) {
                throw new RuntimeException(this.f10856e);
            }
            tresult = this.f10855d;
        }
        return tresult;
    }

    @Override // com.huawei.hmf.tasks.Task
    public final <E extends Throwable> TResult getResultThrowException(Class<E> cls) throws Throwable {
        TResult tresult;
        synchronized (this.f10852a) {
            if (cls != null) {
                if (cls.isInstance(this.f10856e)) {
                    throw cls.cast(this.f10856e);
                }
            }
            if (this.f10856e != null) {
                throw new RuntimeException(this.f10856e);
            }
            tresult = this.f10855d;
        }
        return tresult;
    }

    @Override // com.huawei.hmf.tasks.Task
    public final boolean isCanceled() {
        return this.f10854c;
    }

    @Override // com.huawei.hmf.tasks.Task
    public final boolean isComplete() {
        boolean z;
        synchronized (this.f10852a) {
            z = this.f10853b;
        }
        return z;
    }

    @Override // com.huawei.hmf.tasks.Task
    public final boolean isSuccessful() {
        boolean z;
        synchronized (this.f10852a) {
            z = this.f10853b && !isCanceled() && this.f10856e == null;
        }
        return z;
    }

    @Override // com.huawei.hmf.tasks.Task
    public final <TContinuationResult> Task<TContinuationResult> onSuccessTask(SuccessContinuation<TResult, TContinuationResult> successContinuation) {
        return onSuccessTask(TaskExecutors.uiThread(), successContinuation);
    }

    @Override // com.huawei.hmf.tasks.Task
    public final <TContinuationResult> Task<TContinuationResult> onSuccessTask(Executor executor, final SuccessContinuation<TResult, TContinuationResult> successContinuation) {
        final C4823i c4823i = new C4823i();
        addOnSuccessListener(executor, new OnSuccessListener<TResult>() { // from class: com.huawei.hmf.tasks.a.i.1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.huawei.hmf.tasks.OnSuccessListener
            public final void onSuccess(TResult tresult) {
                try {
                    Task then = successContinuation.then(tresult);
                    if (then == 0) {
                        c4823i.m15358a((Exception) new NullPointerException("SuccessContinuation returned null"));
                    } else {
                        then.addOnCompleteListener(new OnCompleteListener<TContinuationResult>() { // from class: com.huawei.hmf.tasks.a.i.1.1
                            @Override // com.huawei.hmf.tasks.OnCompleteListener
                            public final void onComplete(Task<TContinuationResult> task) {
                                if (task.isSuccessful()) {
                                    c4823i.m15357a((C4823i) task.getResult());
                                } else if (task.isCanceled()) {
                                    c4823i.m15360a();
                                } else {
                                    c4823i.m15358a(task.getException());
                                }
                            }
                        });
                    }
                } catch (Exception e) {
                    c4823i.m15358a(e);
                }
            }
        });
        addOnFailureListener(new OnFailureListener() { // from class: com.huawei.hmf.tasks.a.i.2
            @Override // com.huawei.hmf.tasks.OnFailureListener
            public final void onFailure(Exception exc) {
                c4823i.m15358a(exc);
            }
        });
        addOnCanceledListener(new OnCanceledListener() { // from class: com.huawei.hmf.tasks.a.i.3
            @Override // com.huawei.hmf.tasks.OnCanceledListener
            public final void onCanceled() {
                c4823i.m15360a();
            }
        });
        return c4823i;
    }
}
