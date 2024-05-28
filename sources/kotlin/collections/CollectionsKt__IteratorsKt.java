package kotlin.collections;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* compiled from: Iterators.kt */
@Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010(\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a-\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00010\u0005H\u0086\b\u001a\u001f\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0003\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u0087\n\u001a\"\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\b0\u0003\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003¨\u0006\t"}, m1890d2 = {"forEach", "", "T", "", "operation", "Lkotlin/Function1;", "iterator", "withIndex", "Lkotlin/collections/IndexedValue;", "kotlin-stdlib"}, m1889k = 5, m1888mv = {1, 1, 16}, m1886xi = 1, m1885xs = "kotlin/collections/CollectionsKt")
/* loaded from: E:\11617560_dexfile_execute.dex */
class CollectionsKt__IteratorsKt extends IteratorsJVM {
    /* JADX WARN: Multi-variable type inference failed */
    @InlineOnly
    private static final <T> Iterator<T> iterator(@NotNull Iterator<? extends T> iterator) {
        Intrinsics.checkParameterIsNotNull(iterator, "$this$iterator");
        return iterator;
    }

    @NotNull
    public static final <T> Iterator<IndexedValue<T>> withIndex(@NotNull Iterator<? extends T> withIndex) {
        Intrinsics.checkParameterIsNotNull(withIndex, "$this$withIndex");
        return new IndexingIterator(withIndex);
    }

    public static final <T> void forEach(@NotNull Iterator<? extends T> forEach, @NotNull Function1<? super T, Unit> operation) {
        Intrinsics.checkParameterIsNotNull(forEach, "$this$forEach");
        Intrinsics.checkParameterIsNotNull(operation, "operation");
        while (forEach.hasNext()) {
            operation.invoke((T) forEach.next());
        }
    }
}
