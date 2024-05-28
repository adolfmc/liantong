package kotlin.coroutines.experimental.intrinsics;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.Standard;
import kotlin.coroutines.experimental.Coroutines;
import kotlin.coroutines.experimental.jvm.internal.CoroutineIntrinsics;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;
import org.jetbrains.annotations.NotNull;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
@Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u0000\u0014\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u001a5\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u00012\u001c\b\u0004\u0010\u0002\u001a\u0016\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0003H\u0087Hø\u0001\u0000¢\u0006\u0002\u0010\u0006\u001a5\u0010\u0007\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u00012\u001c\b\u0004\u0010\u0002\u001a\u0016\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0003H\u0087Hø\u0001\u0000¢\u0006\u0002\u0010\u0006\u001a\u001f\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0004\"\u0004\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u0004H\u0087\b\u0082\u0002\u0004\n\u0002\b\t¨\u0006\t"}, m1890d2 = {"suspendCoroutineOrReturn", "T", "block", "Lkotlin/Function1;", "Lkotlin/coroutines/experimental/Continuation;", "", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/experimental/Continuation;)Ljava/lang/Object;", "suspendCoroutineUninterceptedOrReturn", "intercepted", "kotlin-stdlib-coroutines"}, m1889k = 5, m1888mv = {1, 1, 16}, m1886xi = 1, m1885xs = "kotlin/coroutines/experimental/intrinsics/IntrinsicsKt")
/* renamed from: kotlin.coroutines.experimental.intrinsics.IntrinsicsKt__IntrinsicsKt */
/* loaded from: E:\11617560_dexfile_execute.dex */
class Intrinsics extends IntrinsicsJvm {
    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final <T> Object suspendCoroutineOrReturn(Function1<? super Coroutines<? super T>, ? extends Object> function1, Coroutines<? super T> coroutines) {
        InlineMarker.mark(0);
        Object invoke = function1.invoke(CoroutineIntrinsics.normalizeContinuation(coroutines));
        InlineMarker.mark(1);
        return invoke;
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final <T> Object suspendCoroutineUninterceptedOrReturn(Function1<? super Coroutines<? super T>, ? extends Object> function1, Coroutines<? super T> coroutines) {
        throw new Standard("Implementation of suspendCoroutineUninterceptedOrReturn is intrinsic");
    }

    @SinceKotlin(version = "1.2")
    @InlineOnly
    private static final <T> Coroutines<T> intercepted(@NotNull Coroutines<? super T> coroutines) {
        throw new Standard("Implementation of intercepted is intrinsic");
    }
}
