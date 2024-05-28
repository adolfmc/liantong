package kotlin.coroutines.experimental.intrinsics;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.coroutines.experimental.CoroutineContext;
import kotlin.coroutines.experimental.Coroutines;
import kotlin.coroutines.experimental.jvm.internal.CoroutineImpl;
import kotlin.coroutines.experimental.jvm.internal.CoroutineIntrinsics;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
@Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u00002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a:\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\"\u0004\b\u0000\u0010\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\t0\u00072\u0010\b\u0004\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\fH\u0082\b¢\u0006\u0002\b\r\u001aD\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\"\u0004\b\u0000\u0010\t*\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\t0\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u000f2\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\t0\u0007H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0010\u001a]\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\"\u0004\b\u0000\u0010\u0011\"\u0004\b\u0001\u0010\t*#\b\u0001\u0012\u0004\u0012\u0002H\u0011\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\t0\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0012¢\u0006\u0002\b\u00132\u0006\u0010\u0014\u001a\u0002H\u00112\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\t0\u0007H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0015\u001aA\u0010\u0016\u001a\u0004\u0018\u00010\u0001\"\u0004\b\u0000\u0010\t*\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\t0\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u000f2\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\t0\u0007H\u0087\bø\u0001\u0000¢\u0006\u0002\u0010\u0017\u001aZ\u0010\u0016\u001a\u0004\u0018\u00010\u0001\"\u0004\b\u0000\u0010\u0011\"\u0004\b\u0001\u0010\t*#\b\u0001\u0012\u0004\u0012\u0002H\u0011\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\t0\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0012¢\u0006\u0002\b\u00132\u0006\u0010\u0014\u001a\u0002H\u00112\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\t0\u0007H\u0087\bø\u0001\u0000¢\u0006\u0002\u0010\u0018\"\u001a\u0010\u0000\u001a\u00020\u00018FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0002\u0010\u0003\u001a\u0004\b\u0004\u0010\u0005\u0082\u0002\u0004\n\u0002\b\t¨\u0006\u0019"}, m1890d2 = {"COROUTINE_SUSPENDED", "", "COROUTINE_SUSPENDED$annotations", "()V", "getCOROUTINE_SUSPENDED", "()Ljava/lang/Object;", "buildContinuationByInvokeCall", "Lkotlin/coroutines/experimental/Continuation;", "", "T", "completion", "block", "Lkotlin/Function0;", "buildContinuationByInvokeCall$IntrinsicsKt__IntrinsicsJvmKt", "createCoroutineUnchecked", "Lkotlin/Function1;", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/experimental/Continuation;)Lkotlin/coroutines/experimental/Continuation;", "R", "Lkotlin/Function2;", "Lkotlin/ExtensionFunctionType;", "receiver", "(Lkotlin/jvm/functions/Function2;Ljava/lang/Object;Lkotlin/coroutines/experimental/Continuation;)Lkotlin/coroutines/experimental/Continuation;", "startCoroutineUninterceptedOrReturn", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/experimental/Continuation;)Ljava/lang/Object;", "(Lkotlin/jvm/functions/Function2;Ljava/lang/Object;Lkotlin/coroutines/experimental/Continuation;)Ljava/lang/Object;", "kotlin-stdlib-coroutines"}, m1889k = 5, m1888mv = {1, 1, 16}, m1886xi = 1, m1885xs = "kotlin/coroutines/experimental/intrinsics/IntrinsicsKt")
/* renamed from: kotlin.coroutines.experimental.intrinsics.IntrinsicsKt__IntrinsicsJvmKt */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class IntrinsicsJvm {
    @SinceKotlin(version = "1.1")
    public static /* synthetic */ void COROUTINE_SUSPENDED$annotations() {
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final <T> Object startCoroutineUninterceptedOrReturn(@NotNull Function1<? super Coroutines<? super T>, ? extends Object> function1, Coroutines<? super T> coroutines) {
        if (function1 != null) {
            return ((Function1) TypeIntrinsics.beforeCheckcastToFunctionOfArity(function1, 1)).invoke(coroutines);
        }
        throw new TypeCastException("null cannot be cast to non-null type (kotlin.coroutines.experimental.Continuation<T>) -> kotlin.Any?");
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final <R, T> Object startCoroutineUninterceptedOrReturn(@NotNull Function2<? super R, ? super Coroutines<? super T>, ? extends Object> function2, R r, Coroutines<? super T> coroutines) {
        if (function2 != null) {
            return ((Function2) TypeIntrinsics.beforeCheckcastToFunctionOfArity(function2, 2)).invoke(r, coroutines);
        }
        throw new TypeCastException("null cannot be cast to non-null type (R, kotlin.coroutines.experimental.Continuation<T>) -> kotlin.Any?");
    }

    @SinceKotlin(version = "1.1")
    @NotNull
    public static final <T> Coroutines<Unit> createCoroutineUnchecked(@NotNull final Function1<? super Coroutines<? super T>, ? extends Object> createCoroutineUnchecked, @NotNull final Coroutines<? super T> completion) {
        Intrinsics.checkParameterIsNotNull(createCoroutineUnchecked, "$this$createCoroutineUnchecked");
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        if (createCoroutineUnchecked instanceof CoroutineImpl) {
            Coroutines<Unit> create = ((CoroutineImpl) createCoroutineUnchecked).create(completion);
            if (create != null) {
                return ((CoroutineImpl) create).getFacade();
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.coroutines.experimental.jvm.internal.CoroutineImpl");
        }
        return CoroutineIntrinsics.interceptContinuationIfNeeded(completion.getContext(), new Coroutines<Unit>() { // from class: kotlin.coroutines.experimental.intrinsics.IntrinsicsKt__IntrinsicsJvmKt$createCoroutineUnchecked$$inlined$buildContinuationByInvokeCall$IntrinsicsKt__IntrinsicsJvmKt$1
            @Override // kotlin.coroutines.experimental.Coroutines
            @NotNull
            public CoroutineContext getContext() {
                return Coroutines.this.getContext();
            }

            @Override // kotlin.coroutines.experimental.Coroutines
            public void resume(@NotNull Unit value) {
                Intrinsics.checkParameterIsNotNull(value, "value");
                Coroutines coroutines = Coroutines.this;
                try {
                    Function1 function1 = createCoroutineUnchecked;
                    if (function1 == null) {
                        throw new TypeCastException("null cannot be cast to non-null type (kotlin.coroutines.experimental.Continuation<T>) -> kotlin.Any?");
                    }
                    Object invoke = ((Function1) TypeIntrinsics.beforeCheckcastToFunctionOfArity(function1, 1)).invoke(completion);
                    if (invoke != IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                        if (coroutines == null) {
                            throw new TypeCastException("null cannot be cast to non-null type kotlin.coroutines.experimental.Continuation<kotlin.Any?>");
                        }
                        coroutines.resume(invoke);
                    }
                } catch (Throwable th) {
                    coroutines.resumeWithException(th);
                }
            }

            @Override // kotlin.coroutines.experimental.Coroutines
            public void resumeWithException(@NotNull Throwable exception) {
                Intrinsics.checkParameterIsNotNull(exception, "exception");
                Coroutines.this.resumeWithException(exception);
            }
        });
    }

    @SinceKotlin(version = "1.1")
    @NotNull
    public static final <R, T> Coroutines<Unit> createCoroutineUnchecked(@NotNull final Function2<? super R, ? super Coroutines<? super T>, ? extends Object> createCoroutineUnchecked, final R r, @NotNull final Coroutines<? super T> completion) {
        Intrinsics.checkParameterIsNotNull(createCoroutineUnchecked, "$this$createCoroutineUnchecked");
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        if (createCoroutineUnchecked instanceof CoroutineImpl) {
            Coroutines<Unit> create = ((CoroutineImpl) createCoroutineUnchecked).create(r, completion);
            if (create != null) {
                return ((CoroutineImpl) create).getFacade();
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.coroutines.experimental.jvm.internal.CoroutineImpl");
        }
        return CoroutineIntrinsics.interceptContinuationIfNeeded(completion.getContext(), new Coroutines<Unit>() { // from class: kotlin.coroutines.experimental.intrinsics.IntrinsicsKt__IntrinsicsJvmKt$createCoroutineUnchecked$$inlined$buildContinuationByInvokeCall$IntrinsicsKt__IntrinsicsJvmKt$2
            @Override // kotlin.coroutines.experimental.Coroutines
            @NotNull
            public CoroutineContext getContext() {
                return Coroutines.this.getContext();
            }

            @Override // kotlin.coroutines.experimental.Coroutines
            public void resume(@NotNull Unit value) {
                Intrinsics.checkParameterIsNotNull(value, "value");
                Coroutines coroutines = Coroutines.this;
                try {
                    Function2 function2 = createCoroutineUnchecked;
                    if (function2 == null) {
                        throw new TypeCastException("null cannot be cast to non-null type (R, kotlin.coroutines.experimental.Continuation<T>) -> kotlin.Any?");
                    }
                    Object invoke = ((Function2) TypeIntrinsics.beforeCheckcastToFunctionOfArity(function2, 2)).invoke(r, completion);
                    if (invoke != IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                        if (coroutines == null) {
                            throw new TypeCastException("null cannot be cast to non-null type kotlin.coroutines.experimental.Continuation<kotlin.Any?>");
                        }
                        coroutines.resume(invoke);
                    }
                } catch (Throwable th) {
                    coroutines.resumeWithException(th);
                }
            }

            @Override // kotlin.coroutines.experimental.Coroutines
            public void resumeWithException(@NotNull Throwable exception) {
                Intrinsics.checkParameterIsNotNull(exception, "exception");
                Coroutines.this.resumeWithException(exception);
            }
        });
    }

    @NotNull
    public static final Object getCOROUTINE_SUSPENDED() {
        return kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED();
    }
}
