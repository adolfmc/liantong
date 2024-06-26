package kotlin.coroutines.experimental.jvm.internal;

import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.coroutines.experimental.CoroutineContext;
import kotlin.coroutines.experimental.Coroutines;
import kotlin.coroutines.experimental.intrinsics.IntrinsicsKt;
import kotlin.jvm.JvmPlatformAnnotations;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* compiled from: CoroutineImpl.kt */
@Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u0003\n\u0002\b\u0003\b&\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0003B\u001f\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0010\u0010\u0006\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0018\u00010\u0003¢\u0006\u0002\u0010\u0007J$\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\u00032\b\u0010\u0014\u001a\u0004\u0018\u00010\u00022\n\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u0003H\u0016J\u001a\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\u00032\n\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u0003H\u0016J\u001e\u0010\u0015\u001a\u0004\u0018\u00010\u00022\b\u0010\u0016\u001a\u0004\u0018\u00010\u00022\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H$J\u0012\u0010\u0019\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0002H\u0016J\u0010\u0010\u001a\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\u0018H\u0016R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\n\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0006\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0018\u00010\u00038\u0004@\u0004X\u0085\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\u00020\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0019\u0010\u000e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0012\u0010\u0011\u001a\u00020\u00058\u0004@\u0004X\u0085\u000e¢\u0006\u0002\n\u0000¨\u0006\u001b"}, m1890d2 = {"Lkotlin/coroutines/experimental/jvm/internal/CoroutineImpl;", "Lkotlin/jvm/internal/Lambda;", "", "Lkotlin/coroutines/experimental/Continuation;", "arity", "", "completion", "(ILkotlin/coroutines/experimental/Continuation;)V", "_context", "Lkotlin/coroutines/experimental/CoroutineContext;", "_facade", "context", "getContext", "()Lkotlin/coroutines/experimental/CoroutineContext;", "facade", "getFacade", "()Lkotlin/coroutines/experimental/Continuation;", "label", "create", "", "value", "doResume", "data", "exception", "", "resume", "resumeWithException", "kotlin-stdlib-coroutines"}, m1889k = 1, m1888mv = {1, 1, 16})
/* loaded from: E:\11617560_dexfile_execute.dex */
public abstract class CoroutineImpl extends Lambda<Object> implements Coroutines<Object> {
    private final CoroutineContext _context;
    private Coroutines<Object> _facade;
    @JvmPlatformAnnotations
    @Nullable
    protected Coroutines<Object> completion;
    @JvmPlatformAnnotations
    protected int label;

    @Nullable
    protected abstract Object doResume(@Nullable Object obj, @Nullable Throwable th);

    public CoroutineImpl(int i, @Nullable Coroutines<Object> coroutines) {
        super(i);
        this.completion = coroutines;
        this.label = this.completion != null ? 0 : -1;
        Coroutines<Object> coroutines2 = this.completion;
        this._context = coroutines2 != null ? coroutines2.getContext() : null;
    }

    @Override // kotlin.coroutines.experimental.Coroutines
    @NotNull
    public CoroutineContext getContext() {
        CoroutineContext coroutineContext = this._context;
        if (coroutineContext == null) {
            Intrinsics.throwNpe();
        }
        return coroutineContext;
    }

    @NotNull
    public final Coroutines<Object> getFacade() {
        if (this._facade == null) {
            CoroutineContext coroutineContext = this._context;
            if (coroutineContext == null) {
                Intrinsics.throwNpe();
            }
            this._facade = CoroutineIntrinsics.interceptContinuationIfNeeded(coroutineContext, this);
        }
        Coroutines<Object> coroutines = this._facade;
        if (coroutines == null) {
            Intrinsics.throwNpe();
        }
        return coroutines;
    }

    @Override // kotlin.coroutines.experimental.Coroutines
    public void resume(@Nullable Object obj) {
        Coroutines<Object> coroutines = this.completion;
        if (coroutines == null) {
            Intrinsics.throwNpe();
        }
        try {
            Object doResume = doResume(obj, null);
            if (doResume != IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                if (coroutines == null) {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.coroutines.experimental.Continuation<kotlin.Any?>");
                }
                coroutines.resume(doResume);
            }
        } catch (Throwable th) {
            coroutines.resumeWithException(th);
        }
    }

    @Override // kotlin.coroutines.experimental.Coroutines
    public void resumeWithException(@NotNull Throwable exception) {
        Intrinsics.checkParameterIsNotNull(exception, "exception");
        Coroutines<Object> coroutines = this.completion;
        if (coroutines == null) {
            Intrinsics.throwNpe();
        }
        try {
            Object doResume = doResume(null, exception);
            if (doResume != IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                if (coroutines == null) {
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.coroutines.experimental.Continuation<kotlin.Any?>");
                }
                coroutines.resume(doResume);
            }
        } catch (Throwable th) {
            coroutines.resumeWithException(th);
        }
    }

    @NotNull
    public Coroutines<Unit> create(@NotNull Coroutines<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        throw new IllegalStateException("create(Continuation) has not been overridden");
    }

    @NotNull
    public Coroutines<Unit> create(@Nullable Object obj, @NotNull Coroutines<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        throw new IllegalStateException("create(Any?;Continuation) has not been overridden");
    }
}
