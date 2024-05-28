package kotlin.jvm.internal;

import java.util.Iterator;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* compiled from: ArrayIterator.kt */
@Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010(\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\u001a%\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0004¢\u0006\u0002\u0010\u0005¨\u0006\u0006"}, m1890d2 = {"iterator", "", "T", "array", "", "([Ljava/lang/Object;)Ljava/util/Iterator;", "kotlin-stdlib"}, m1889k = 2, m1888mv = {1, 1, 16})
/* loaded from: E:\11617560_dexfile_execute.dex */
public final class ArrayIteratorKt {
    @NotNull
    public static final <T> Iterator<T> iterator(@NotNull T[] array) {
        Intrinsics.checkParameterIsNotNull(array, "array");
        return new ArrayIterator(array);
    }
}
