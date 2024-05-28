package kotlin.collections;

import java.util.Enumeration;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
@Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010(\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u001f\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u0086\u0002¨\u0006\u0004"}, m1890d2 = {"iterator", "", "T", "Ljava/util/Enumeration;", "kotlin-stdlib"}, m1889k = 5, m1888mv = {1, 1, 16}, m1886xi = 1, m1885xs = "kotlin/collections/CollectionsKt")
/* renamed from: kotlin.collections.CollectionsKt__IteratorsJVMKt */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class IteratorsJVM extends CollectionsKt__IterablesKt {
    @NotNull
    public static final <T> Iterator<T> iterator(@NotNull Enumeration<T> iterator) {
        Intrinsics.checkParameterIsNotNull(iterator, "$this$iterator");
        return new CollectionsKt__IteratorsJVMKt$iterator$1(iterator);
    }
}
