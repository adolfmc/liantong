package kotlin;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
@Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a!\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u000b\b\u0000\u0010\u0002\u0018\u0001¢\u0006\u0002\b\u0003H\u0086\b¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, m1890d2 = {"emptyArray", "", "T", "Lkotlin/internal/PureReifiable;", "()[Ljava/lang/Object;", "kotlin-stdlib"}, m1889k = 2, m1888mv = {1, 1, 16})
/* renamed from: kotlin.ArrayIntrinsicsKt */
/* loaded from: E:\11617560_dexfile_execute.dex */
public final class ArrayIntrinsics {
    @NotNull
    public static final /* synthetic */ <T> T[] emptyArray() {
        Intrinsics.reifiedOperationMarker(0, "T?");
        return (T[]) new Object[0];
    }
}
