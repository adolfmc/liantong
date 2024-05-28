package kotlin.coroutines.experimental;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.Standard;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.coroutines.experimental.intrinsics.IntrinsicsKt;
import kotlin.coroutines.experimental.jvm.internal.CoroutineIntrinsics;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Functions;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
@Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a%\u0010\u0006\u001a\u00020\u00072\n\u0010\b\u001a\u0006\u0012\u0002\b\u00030\t2\u000e\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000bH\u0081\b\u001a3\u0010\r\u001a\u0002H\u000e\"\u0004\b\u0000\u0010\u000e2\u001a\b\u0004\u0010\n\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000e0\t\u0012\u0004\u0012\u00020\u00070\u000fH\u0087Hø\u0001\u0000¢\u0006\u0002\u0010\u0010\u001aD\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00070\t\"\u0004\b\u0000\u0010\u000e*\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000e0\t\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000f2\f\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u000e0\tH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0012\u001a]\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00070\t\"\u0004\b\u0000\u0010\u0013\"\u0004\b\u0001\u0010\u000e*#\b\u0001\u0012\u0004\u0012\u0002H\u0013\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000e0\t\u0012\u0006\u0012\u0004\u0018\u00010\f0\u0014¢\u0006\u0002\b\u00152\u0006\u0010\u0016\u001a\u0002H\u00132\f\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u000e0\tH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0017\u001a>\u0010\u0018\u001a\u00020\u0007\"\u0004\b\u0000\u0010\u000e*\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000e0\t\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000f2\f\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u000e0\tH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0019\u001aW\u0010\u0018\u001a\u00020\u0007\"\u0004\b\u0000\u0010\u0013\"\u0004\b\u0001\u0010\u000e*#\b\u0001\u0012\u0004\u0012\u0002H\u0013\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000e0\t\u0012\u0006\u0012\u0004\u0018\u00010\f0\u0014¢\u0006\u0002\b\u00152\u0006\u0010\u0016\u001a\u0002H\u00132\f\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u000e0\tH\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u001a\"\u001b\u0010\u0000\u001a\u00020\u00018Æ\u0002X\u0087\u0004¢\u0006\f\u0012\u0004\b\u0002\u0010\u0003\u001a\u0004\b\u0004\u0010\u0005\u0082\u0002\u0004\n\u0002\b\t¨\u0006\u001b"}, m1890d2 = {"coroutineContext", "Lkotlin/coroutines/experimental/CoroutineContext;", "coroutineContext$annotations", "()V", "getCoroutineContext", "()Lkotlin/coroutines/experimental/CoroutineContext;", "processBareContinuationResume", "", "completion", "Lkotlin/coroutines/experimental/Continuation;", "block", "Lkotlin/Function0;", "", "suspendCoroutine", "T", "Lkotlin/Function1;", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/experimental/Continuation;)Ljava/lang/Object;", "createCoroutine", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/experimental/Continuation;)Lkotlin/coroutines/experimental/Continuation;", "R", "Lkotlin/Function2;", "Lkotlin/ExtensionFunctionType;", "receiver", "(Lkotlin/jvm/functions/Function2;Ljava/lang/Object;Lkotlin/coroutines/experimental/Continuation;)Lkotlin/coroutines/experimental/Continuation;", "startCoroutine", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/experimental/Continuation;)V", "(Lkotlin/jvm/functions/Function2;Ljava/lang/Object;Lkotlin/coroutines/experimental/Continuation;)V", "kotlin-stdlib-coroutines"}, m1889k = 2, m1888mv = {1, 1, 16})
@JvmName(name = "CoroutinesKt")
/* renamed from: kotlin.coroutines.experimental.CoroutinesKt */
/* loaded from: E:\11617560_dexfile_execute.dex */
public final class CoroutinesLibrary {
    @SinceKotlin(version = "1.2")
    @InlineOnly
    public static /* synthetic */ void coroutineContext$annotations() {
    }

    @SinceKotlin(version = "1.1")
    public static final <R, T> void startCoroutine(@NotNull Function2<? super R, ? super Coroutines<? super T>, ? extends Object> startCoroutine, R r, @NotNull Coroutines<? super T> completion) {
        Intrinsics.checkParameterIsNotNull(startCoroutine, "$this$startCoroutine");
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        IntrinsicsKt.createCoroutineUnchecked(startCoroutine, r, completion).resume(Unit.INSTANCE);
    }

    @SinceKotlin(version = "1.1")
    public static final <T> void startCoroutine(@NotNull Function1<? super Coroutines<? super T>, ? extends Object> startCoroutine, @NotNull Coroutines<? super T> completion) {
        Intrinsics.checkParameterIsNotNull(startCoroutine, "$this$startCoroutine");
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        IntrinsicsKt.createCoroutineUnchecked(startCoroutine, completion).resume(Unit.INSTANCE);
    }

    @SinceKotlin(version = "1.1")
    @NotNull
    public static final <R, T> Coroutines<Unit> createCoroutine(@NotNull Function2<? super R, ? super Coroutines<? super T>, ? extends Object> createCoroutine, R r, @NotNull Coroutines<? super T> completion) {
        Intrinsics.checkParameterIsNotNull(createCoroutine, "$this$createCoroutine");
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        return new SafeContinuation(IntrinsicsKt.createCoroutineUnchecked(createCoroutine, r, completion), IntrinsicsKt.getCOROUTINE_SUSPENDED());
    }

    @SinceKotlin(version = "1.1")
    @NotNull
    public static final <T> Coroutines<Unit> createCoroutine(@NotNull Function1<? super Coroutines<? super T>, ? extends Object> createCoroutine, @NotNull Coroutines<? super T> completion) {
        Intrinsics.checkParameterIsNotNull(createCoroutine, "$this$createCoroutine");
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        return new SafeContinuation(IntrinsicsKt.createCoroutineUnchecked(createCoroutine, completion), IntrinsicsKt.getCOROUTINE_SUSPENDED());
    }

    @SinceKotlin(version = "1.1")
    @Nullable
    public static final <T> Object suspendCoroutine(@NotNull Function1<? super Coroutines<? super T>, Unit> function1, @NotNull Coroutines<? super T> coroutines) {
        SafeContinuation safeContinuation = new SafeContinuation(CoroutineIntrinsics.normalizeContinuation(coroutines));
        function1.invoke(safeContinuation);
        return safeContinuation.getResult();
    }

    @SinceKotlin(version = "1.1")
    @Nullable
    private static final Object suspendCoroutine$$forInline(@NotNull Function1 function1, @NotNull Coroutines coroutines) {
        InlineMarker.mark(0);
        SafeContinuation safeContinuation = new SafeContinuation(CoroutineIntrinsics.normalizeContinuation(coroutines));
        function1.invoke(safeContinuation);
        Object result = safeContinuation.getResult();
        InlineMarker.mark(1);
        return result;
    }

    private static final CoroutineContext getCoroutineContext() {
        throw new Standard("Implemented as intrinsic");
    }

    @InlineOnly
    private static final void processBareContinuationResume(Coroutines<?> coroutines, Functions<? extends Object> functions) {
        try {
            Object invoke = functions.invoke();
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
}
