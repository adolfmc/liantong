package kotlin.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.Tuples;
import kotlin.TuplesKt;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Functions;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* compiled from: Iterables.kt */
@Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u0000:\n\u0000\n\u0002\u0010\u001c\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010(\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u001e\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a+\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0014\b\u0004\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00050\u0004H\u0087\b\u001a \u0010\u0006\u001a\u00020\u0007\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\b\u001a\u00020\u0007H\u0001\u001a\u001f\u0010\t\u001a\u0004\u0018\u00010\u0007\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0001H\u0001¢\u0006\u0002\u0010\n\u001a\u001e\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\u00020\f\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0001H\u0000\u001a,\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\u00020\f\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001H\u0000\u001a\"\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0010\"\u0004\b\u0000\u0010\u0002*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00010\u0001\u001a\u001d\u0010\u0011\u001a\u00020\u0012\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\fH\u0002¢\u0006\u0002\b\u0013\u001a@\u0010\u0014\u001a\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00160\u00100\u0015\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0016*\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00160\u00150\u0001¨\u0006\u0017"}, m1890d2 = {"Iterable", "", "T", "iterator", "Lkotlin/Function0;", "", "collectionSizeOrDefault", "", "default", "collectionSizeOrNull", "(Ljava/lang/Iterable;)Ljava/lang/Integer;", "convertToSetForSetOperation", "", "convertToSetForSetOperationWith", "source", "flatten", "", "safeToConvertToSet", "", "safeToConvertToSet$CollectionsKt__IterablesKt", "unzip", "Lkotlin/Pair;", "R", "kotlin-stdlib"}, m1889k = 5, m1888mv = {1, 1, 16}, m1886xi = 1, m1885xs = "kotlin/collections/CollectionsKt")
/* loaded from: E:\11617560_dexfile_execute.dex */
public class CollectionsKt__IterablesKt extends CollectionsKt__CollectionsKt {
    @InlineOnly
    private static final <T> Iterable<T> Iterable(Functions<? extends Iterator<? extends T>> functions) {
        return new CollectionsKt__IterablesKt$Iterable$1(functions);
    }

    @PublishedApi
    @Nullable
    public static final <T> Integer collectionSizeOrNull(@NotNull Iterable<? extends T> collectionSizeOrNull) {
        Intrinsics.checkParameterIsNotNull(collectionSizeOrNull, "$this$collectionSizeOrNull");
        if (collectionSizeOrNull instanceof Collection) {
            return Integer.valueOf(((Collection) collectionSizeOrNull).size());
        }
        return null;
    }

    @PublishedApi
    public static final <T> int collectionSizeOrDefault(@NotNull Iterable<? extends T> collectionSizeOrDefault, int i) {
        Intrinsics.checkParameterIsNotNull(collectionSizeOrDefault, "$this$collectionSizeOrDefault");
        return collectionSizeOrDefault instanceof Collection ? ((Collection) collectionSizeOrDefault).size() : i;
    }

    private static final <T> boolean safeToConvertToSet$CollectionsKt__IterablesKt(@NotNull Collection<? extends T> collection) {
        return collection.size() > 2 && (collection instanceof ArrayList);
    }

    @NotNull
    public static final <T> Collection<T> convertToSetForSetOperationWith(@NotNull Iterable<? extends T> convertToSetForSetOperationWith, @NotNull Iterable<? extends T> source) {
        Intrinsics.checkParameterIsNotNull(convertToSetForSetOperationWith, "$this$convertToSetForSetOperationWith");
        Intrinsics.checkParameterIsNotNull(source, "source");
        if (convertToSetForSetOperationWith instanceof Set) {
            return (Collection) convertToSetForSetOperationWith;
        }
        if (convertToSetForSetOperationWith instanceof Collection) {
            if (!(source instanceof Collection) || ((Collection) source).size() >= 2) {
                Collection<T> collection = (Collection) convertToSetForSetOperationWith;
                return safeToConvertToSet$CollectionsKt__IterablesKt(collection) ? CollectionsKt.toHashSet(convertToSetForSetOperationWith) : collection;
            }
            return (Collection) convertToSetForSetOperationWith;
        }
        return CollectionsKt.toHashSet(convertToSetForSetOperationWith);
    }

    @NotNull
    public static final <T> Collection<T> convertToSetForSetOperation(@NotNull Iterable<? extends T> convertToSetForSetOperation) {
        Intrinsics.checkParameterIsNotNull(convertToSetForSetOperation, "$this$convertToSetForSetOperation");
        if (convertToSetForSetOperation instanceof Set) {
            return (Collection) convertToSetForSetOperation;
        }
        if (convertToSetForSetOperation instanceof Collection) {
            Collection<T> collection = (Collection) convertToSetForSetOperation;
            return safeToConvertToSet$CollectionsKt__IterablesKt(collection) ? CollectionsKt.toHashSet(convertToSetForSetOperation) : collection;
        }
        return CollectionsKt.toHashSet(convertToSetForSetOperation);
    }

    @NotNull
    public static final <T> List<T> flatten(@NotNull Iterable<? extends Iterable<? extends T>> flatten) {
        Intrinsics.checkParameterIsNotNull(flatten, "$this$flatten");
        ArrayList arrayList = new ArrayList();
        for (Iterable<? extends T> iterable : flatten) {
            CollectionsKt.addAll(arrayList, iterable);
        }
        return arrayList;
    }

    @NotNull
    public static final <T, R> Tuples<List<T>, List<R>> unzip(@NotNull Iterable<? extends Tuples<? extends T, ? extends R>> unzip) {
        Intrinsics.checkParameterIsNotNull(unzip, "$this$unzip");
        int collectionSizeOrDefault = CollectionsKt.collectionSizeOrDefault(unzip, 10);
        ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
        ArrayList arrayList2 = new ArrayList(collectionSizeOrDefault);
        for (Tuples<? extends T, ? extends R> tuples : unzip) {
            arrayList.add(tuples.getFirst());
            arrayList2.add(tuples.getSecond());
        }
        return TuplesKt.m1883to(arrayList, arrayList2);
    }
}
