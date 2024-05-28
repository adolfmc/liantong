package kotlin.coroutines.jvm.internal;

import com.example.asus.detectionandalign.DetectionAuthentic;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
@Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\u001a\"\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001H\u0001\u001a\u0014\u0010\u0004\u001a\u00020\u00052\n\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u0001H\u0001\u001a\u0014\u0010\u0007\u001a\u00020\u00052\n\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u0001H\u0001¨\u0006\b"}, m1890d2 = {"probeCoroutineCreated", "Lkotlin/coroutines/Continuation;", "T", "completion", "probeCoroutineResumed", "", DetectionAuthentic.FRAME, "probeCoroutineSuspended", "kotlin-stdlib"}, m1889k = 2, m1888mv = {1, 1, 16})
/* renamed from: kotlin.coroutines.jvm.internal.DebugProbesKt */
/* loaded from: E:\11617560_dexfile_execute.dex */
public final class DebugProbes {
    /* JADX WARN: Multi-variable type inference failed */
    @SinceKotlin(version = "1.3")
    @NotNull
    public static final <T> Continuation<T> probeCoroutineCreated(@NotNull Continuation<? super T> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        return completion;
    }

    @SinceKotlin(version = "1.3")
    public static final void probeCoroutineResumed(@NotNull Continuation<?> frame) {
        Intrinsics.checkParameterIsNotNull(frame, "frame");
    }

    @SinceKotlin(version = "1.3")
    public static final void probeCoroutineSuspended(@NotNull Continuation<?> frame) {
        Intrinsics.checkParameterIsNotNull(frame, "frame");
    }
}
