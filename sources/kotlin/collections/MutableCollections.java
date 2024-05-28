package kotlin.collections;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.RandomAccess;
import kotlin.Annotations;
import kotlin.DeprecationLevel;
import kotlin.ExperimentalStdlibApi;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.SinceKotlin;
import kotlin.TypeCastException;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.random.Random;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
@Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u0000^\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u001f\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001d\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\u001a-\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0000\u0012\u0002H\u00020\u00032\u000e\u0010\u0004\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0005¢\u0006\u0002\u0010\u0006\u001a&\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0000\u0012\u0002H\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0007\u001a&\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0000\u0012\u0002H\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\b\u001a9\u0010\t\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\n2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00010\f2\u0006\u0010\r\u001a\u00020\u0001H\u0002¢\u0006\u0002\b\u000e\u001a9\u0010\t\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u000f2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00010\f2\u0006\u0010\r\u001a\u00020\u0001H\u0002¢\u0006\u0002\b\u000e\u001a(\u0010\u0010\u001a\u00020\u0011\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0000\u0012\u0002H\u00020\u00032\u0006\u0010\u0012\u001a\u0002H\u0002H\u0087\n¢\u0006\u0002\u0010\u0013\u001a.\u0010\u0010\u001a\u00020\u0011\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0000\u0012\u0002H\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0005H\u0087\n¢\u0006\u0002\u0010\u0014\u001a)\u0010\u0010\u001a\u00020\u0011\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0000\u0012\u0002H\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0007H\u0087\n\u001a)\u0010\u0010\u001a\u00020\u0011\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0000\u0012\u0002H\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\bH\u0087\n\u001a(\u0010\u0015\u001a\u00020\u0011\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0000\u0012\u0002H\u00020\u00032\u0006\u0010\u0012\u001a\u0002H\u0002H\u0087\n¢\u0006\u0002\u0010\u0013\u001a.\u0010\u0015\u001a\u00020\u0011\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0000\u0012\u0002H\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0005H\u0087\n¢\u0006\u0002\u0010\u0014\u001a)\u0010\u0015\u001a\u00020\u0011\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0000\u0012\u0002H\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0007H\u0087\n\u001a)\u0010\u0015\u001a\u00020\u0011\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0000\u0012\u0002H\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\bH\u0087\n\u001a-\u0010\u0016\u001a\u00020\u0001\"\t\b\u0000\u0010\u0002¢\u0006\u0002\b\u0017*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u00032\u0006\u0010\u0012\u001a\u0002H\u0002H\u0087\b¢\u0006\u0002\u0010\u0018\u001a&\u0010\u0016\u001a\u0002H\u0002\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u000f2\u0006\u0010\u0019\u001a\u00020\u001aH\u0087\b¢\u0006\u0002\u0010\u001b\u001a-\u0010\u001c\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0000\u0012\u0002H\u00020\u00032\u000e\u0010\u0004\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0005¢\u0006\u0002\u0010\u0006\u001a&\u0010\u001c\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0000\u0012\u0002H\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0007\u001a&\u0010\u001c\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0000\u0012\u0002H\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\b\u001a.\u0010\u001c\u001a\u00020\u0001\"\t\b\u0000\u0010\u0002¢\u0006\u0002\b\u0017*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u001dH\u0087\b\u001a*\u0010\u001c\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\n2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00010\f\u001a*\u0010\u001c\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u000f2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00010\f\u001a\u001d\u0010\u001e\u001a\u0002H\u0002\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u000fH\u0007¢\u0006\u0002\u0010\u001f\u001a\u001f\u0010 \u001a\u0004\u0018\u0001H\u0002\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u000fH\u0007¢\u0006\u0002\u0010\u001f\u001a\u001d\u0010!\u001a\u0002H\u0002\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u000fH\u0007¢\u0006\u0002\u0010\u001f\u001a\u001f\u0010\"\u001a\u0004\u0018\u0001H\u0002\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u000fH\u0007¢\u0006\u0002\u0010\u001f\u001a-\u0010#\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0000\u0012\u0002H\u00020\u00032\u000e\u0010\u0004\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0005¢\u0006\u0002\u0010\u0006\u001a&\u0010#\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0000\u0012\u0002H\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0007\u001a&\u0010#\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\n\u0012\u0006\b\u0000\u0012\u0002H\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\b\u001a.\u0010#\u001a\u00020\u0001\"\t\b\u0000\u0010\u0002¢\u0006\u0002\b\u0017*\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u001dH\u0087\b\u001a*\u0010#\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\n2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00010\f\u001a*\u0010#\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u000f2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00010\f\u001a\u0015\u0010$\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u0003H\u0002¢\u0006\u0002\b%\u001a \u0010&\u001a\u00020\u0011\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u000f2\u0006\u0010'\u001a\u00020(H\u0007\u001a&\u0010)\u001a\b\u0012\u0004\u0012\u0002H\u00020*\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00072\u0006\u0010'\u001a\u00020(H\u0007¨\u0006+"}, m1890d2 = {"addAll", "", "T", "", "elements", "", "(Ljava/util/Collection;[Ljava/lang/Object;)Z", "", "Lkotlin/sequences/Sequence;", "filterInPlace", "", "predicate", "Lkotlin/Function1;", "predicateResultToRemove", "filterInPlace$CollectionsKt__MutableCollectionsKt", "", "minusAssign", "", "element", "(Ljava/util/Collection;Ljava/lang/Object;)V", "(Ljava/util/Collection;[Ljava/lang/Object;)V", "plusAssign", "remove", "Lkotlin/internal/OnlyInputTypes;", "(Ljava/util/Collection;Ljava/lang/Object;)Z", "index", "", "(Ljava/util/List;I)Ljava/lang/Object;", "removeAll", "", "removeFirst", "(Ljava/util/List;)Ljava/lang/Object;", "removeFirstOrNull", "removeLast", "removeLastOrNull", "retainAll", "retainNothing", "retainNothing$CollectionsKt__MutableCollectionsKt", "shuffle", "random", "Lkotlin/random/Random;", "shuffled", "", "kotlin-stdlib"}, m1889k = 5, m1888mv = {1, 1, 16}, m1886xi = 1, m1885xs = "kotlin/collections/CollectionsKt")
/* renamed from: kotlin.collections.CollectionsKt__MutableCollectionsKt */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class MutableCollections extends MutableCollectionsJVM {
    @InlineOnly
    private static final <T> boolean remove(@NotNull Collection<? extends T> collection, T t) {
        if (collection != null) {
            return TypeIntrinsics.asMutableCollection(collection).remove(t);
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.MutableCollection<T>");
    }

    @InlineOnly
    private static final <T> boolean removeAll(@NotNull Collection<? extends T> collection, Collection<? extends T> collection2) {
        if (collection != null) {
            return TypeIntrinsics.asMutableCollection(collection).removeAll(collection2);
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.MutableCollection<T>");
    }

    @InlineOnly
    private static final <T> boolean retainAll(@NotNull Collection<? extends T> collection, Collection<? extends T> collection2) {
        if (collection != null) {
            return TypeIntrinsics.asMutableCollection(collection).retainAll(collection2);
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.MutableCollection<T>");
    }

    /* JADX WARN: Multi-variable type inference failed */
    @InlineOnly
    private static final <T> void plusAssign(@NotNull Collection<? super T> plusAssign, T t) {
        Intrinsics.checkParameterIsNotNull(plusAssign, "$this$plusAssign");
        plusAssign.add(t);
    }

    @InlineOnly
    private static final <T> void plusAssign(@NotNull Collection<? super T> plusAssign, Iterable<? extends T> iterable) {
        Intrinsics.checkParameterIsNotNull(plusAssign, "$this$plusAssign");
        CollectionsKt.addAll(plusAssign, iterable);
    }

    @InlineOnly
    private static final <T> void plusAssign(@NotNull Collection<? super T> plusAssign, T[] tArr) {
        Intrinsics.checkParameterIsNotNull(plusAssign, "$this$plusAssign");
        CollectionsKt.addAll(plusAssign, tArr);
    }

    @InlineOnly
    private static final <T> void plusAssign(@NotNull Collection<? super T> plusAssign, Sequence<? extends T> sequence) {
        Intrinsics.checkParameterIsNotNull(plusAssign, "$this$plusAssign");
        CollectionsKt.addAll(plusAssign, sequence);
    }

    @InlineOnly
    private static final <T> void minusAssign(@NotNull Collection<? super T> minusAssign, T t) {
        Intrinsics.checkParameterIsNotNull(minusAssign, "$this$minusAssign");
        minusAssign.remove(t);
    }

    @InlineOnly
    private static final <T> void minusAssign(@NotNull Collection<? super T> minusAssign, Iterable<? extends T> iterable) {
        Intrinsics.checkParameterIsNotNull(minusAssign, "$this$minusAssign");
        CollectionsKt.removeAll(minusAssign, iterable);
    }

    @InlineOnly
    private static final <T> void minusAssign(@NotNull Collection<? super T> minusAssign, T[] tArr) {
        Intrinsics.checkParameterIsNotNull(minusAssign, "$this$minusAssign");
        CollectionsKt.removeAll(minusAssign, tArr);
    }

    @InlineOnly
    private static final <T> void minusAssign(@NotNull Collection<? super T> minusAssign, Sequence<? extends T> sequence) {
        Intrinsics.checkParameterIsNotNull(minusAssign, "$this$minusAssign");
        CollectionsKt.removeAll(minusAssign, sequence);
    }

    public static final <T> boolean addAll(@NotNull Collection<? super T> addAll, @NotNull Iterable<? extends T> elements) {
        Intrinsics.checkParameterIsNotNull(addAll, "$this$addAll");
        Intrinsics.checkParameterIsNotNull(elements, "elements");
        if (elements instanceof Collection) {
            return addAll.addAll((Collection) elements);
        }
        boolean z = false;
        Iterator<? extends T> it = elements.iterator();
        while (it.hasNext()) {
            if (addAll.add((T) it.next())) {
                z = true;
            }
        }
        return z;
    }

    public static final <T> boolean addAll(@NotNull Collection<? super T> addAll, @NotNull Sequence<? extends T> elements) {
        Intrinsics.checkParameterIsNotNull(addAll, "$this$addAll");
        Intrinsics.checkParameterIsNotNull(elements, "elements");
        Iterator<? extends T> it = elements.iterator();
        boolean z = false;
        while (it.hasNext()) {
            if (addAll.add((T) it.next())) {
                z = true;
            }
        }
        return z;
    }

    public static final <T> boolean addAll(@NotNull Collection<? super T> addAll, @NotNull T[] elements) {
        Intrinsics.checkParameterIsNotNull(addAll, "$this$addAll");
        Intrinsics.checkParameterIsNotNull(elements, "elements");
        return addAll.addAll(ArraysKt.asList(elements));
    }

    public static final <T> boolean removeAll(@NotNull Collection<? super T> removeAll, @NotNull Iterable<? extends T> elements) {
        Intrinsics.checkParameterIsNotNull(removeAll, "$this$removeAll");
        Intrinsics.checkParameterIsNotNull(elements, "elements");
        return TypeIntrinsics.asMutableCollection(removeAll).removeAll(CollectionsKt.convertToSetForSetOperationWith(elements, removeAll));
    }

    public static final <T> boolean removeAll(@NotNull Collection<? super T> removeAll, @NotNull Sequence<? extends T> elements) {
        Intrinsics.checkParameterIsNotNull(removeAll, "$this$removeAll");
        Intrinsics.checkParameterIsNotNull(elements, "elements");
        HashSet hashSet = SequencesKt.toHashSet(elements);
        return (hashSet.isEmpty() ^ true) && removeAll.removeAll(hashSet);
    }

    public static final <T> boolean removeAll(@NotNull Collection<? super T> removeAll, @NotNull T[] elements) {
        Intrinsics.checkParameterIsNotNull(removeAll, "$this$removeAll");
        Intrinsics.checkParameterIsNotNull(elements, "elements");
        return ((elements.length == 0) ^ true) && removeAll.removeAll(ArraysKt.toHashSet(elements));
    }

    public static final <T> boolean retainAll(@NotNull Collection<? super T> retainAll, @NotNull Iterable<? extends T> elements) {
        Intrinsics.checkParameterIsNotNull(retainAll, "$this$retainAll");
        Intrinsics.checkParameterIsNotNull(elements, "elements");
        return TypeIntrinsics.asMutableCollection(retainAll).retainAll(CollectionsKt.convertToSetForSetOperationWith(elements, retainAll));
    }

    public static final <T> boolean retainAll(@NotNull Collection<? super T> retainAll, @NotNull T[] elements) {
        Intrinsics.checkParameterIsNotNull(retainAll, "$this$retainAll");
        Intrinsics.checkParameterIsNotNull(elements, "elements");
        if (!(elements.length == 0)) {
            return retainAll.retainAll(ArraysKt.toHashSet(elements));
        }
        return retainNothing$CollectionsKt__MutableCollectionsKt(retainAll);
    }

    public static final <T> boolean retainAll(@NotNull Collection<? super T> retainAll, @NotNull Sequence<? extends T> elements) {
        Intrinsics.checkParameterIsNotNull(retainAll, "$this$retainAll");
        Intrinsics.checkParameterIsNotNull(elements, "elements");
        HashSet hashSet = SequencesKt.toHashSet(elements);
        if (!hashSet.isEmpty()) {
            return retainAll.retainAll(hashSet);
        }
        return retainNothing$CollectionsKt__MutableCollectionsKt(retainAll);
    }

    private static final boolean retainNothing$CollectionsKt__MutableCollectionsKt(@NotNull Collection<?> collection) {
        boolean z = !collection.isEmpty();
        collection.clear();
        return z;
    }

    public static final <T> boolean removeAll(@NotNull Iterable<? extends T> removeAll, @NotNull Function1<? super T, Boolean> predicate) {
        Intrinsics.checkParameterIsNotNull(removeAll, "$this$removeAll");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        return filterInPlace$CollectionsKt__MutableCollectionsKt((Iterable) removeAll, (Function1) predicate, true);
    }

    public static final <T> boolean retainAll(@NotNull Iterable<? extends T> retainAll, @NotNull Function1<? super T, Boolean> predicate) {
        Intrinsics.checkParameterIsNotNull(retainAll, "$this$retainAll");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        return filterInPlace$CollectionsKt__MutableCollectionsKt((Iterable) retainAll, (Function1) predicate, false);
    }

    private static final <T> boolean filterInPlace$CollectionsKt__MutableCollectionsKt(@NotNull Iterable<? extends T> iterable, Function1<? super T, Boolean> function1, boolean z) {
        Iterator<? extends T> it = iterable.iterator();
        boolean z2 = false;
        while (it.hasNext()) {
            if (function1.invoke((T) it.next()).booleanValue() == z) {
                it.remove();
                z2 = true;
            }
        }
        return z2;
    }

    @SinceKotlin(version = "1.3")
    @NotNull
    public static final <T> List<T> shuffled(@NotNull Iterable<? extends T> shuffled, @NotNull Random random) {
        Intrinsics.checkParameterIsNotNull(shuffled, "$this$shuffled");
        Intrinsics.checkParameterIsNotNull(random, "random");
        List<T> mutableList = CollectionsKt.toMutableList(shuffled);
        CollectionsKt.shuffle(mutableList, random);
        return mutableList;
    }

    @Annotations(level = DeprecationLevel.ERROR, message = "Use removeAt(index) instead.", replaceWith = @ReplaceWith(expression = "removeAt(index)", imports = {}))
    @InlineOnly
    private static final <T> T remove(@NotNull List<T> list, int i) {
        return list.remove(i);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    public static final <T> T removeFirst(@NotNull List<T> removeFirst) {
        Intrinsics.checkParameterIsNotNull(removeFirst, "$this$removeFirst");
        if (removeFirst.isEmpty()) {
            throw new NoSuchElementException("List is empty.");
        }
        return removeFirst.remove(0);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @Nullable
    public static final <T> T removeFirstOrNull(@NotNull List<T> removeFirstOrNull) {
        Intrinsics.checkParameterIsNotNull(removeFirstOrNull, "$this$removeFirstOrNull");
        if (removeFirstOrNull.isEmpty()) {
            return null;
        }
        return removeFirstOrNull.remove(0);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    public static final <T> T removeLast(@NotNull List<T> removeLast) {
        Intrinsics.checkParameterIsNotNull(removeLast, "$this$removeLast");
        if (removeLast.isEmpty()) {
            throw new NoSuchElementException("List is empty.");
        }
        return removeLast.remove(CollectionsKt.getLastIndex(removeLast));
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalStdlibApi
    @Nullable
    public static final <T> T removeLastOrNull(@NotNull List<T> removeLastOrNull) {
        Intrinsics.checkParameterIsNotNull(removeLastOrNull, "$this$removeLastOrNull");
        if (removeLastOrNull.isEmpty()) {
            return null;
        }
        return removeLastOrNull.remove(CollectionsKt.getLastIndex(removeLastOrNull));
    }

    public static final <T> boolean removeAll(@NotNull List<T> removeAll, @NotNull Function1<? super T, Boolean> predicate) {
        Intrinsics.checkParameterIsNotNull(removeAll, "$this$removeAll");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        return filterInPlace$CollectionsKt__MutableCollectionsKt((List) removeAll, (Function1) predicate, true);
    }

    public static final <T> boolean retainAll(@NotNull List<T> retainAll, @NotNull Function1<? super T, Boolean> predicate) {
        Intrinsics.checkParameterIsNotNull(retainAll, "$this$retainAll");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        return filterInPlace$CollectionsKt__MutableCollectionsKt((List) retainAll, (Function1) predicate, false);
    }

    private static final <T> boolean filterInPlace$CollectionsKt__MutableCollectionsKt(@NotNull List<T> list, Function1<? super T, Boolean> function1, boolean z) {
        int i;
        if (!(list instanceof RandomAccess)) {
            if (list != null) {
                return filterInPlace$CollectionsKt__MutableCollectionsKt(TypeIntrinsics.asMutableIterable(list), function1, z);
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.MutableIterable<T>");
        }
        int lastIndex = CollectionsKt.getLastIndex(list);
        if (lastIndex >= 0) {
            int i2 = 0;
            i = 0;
            while (true) {
                T t = list.get(i2);
                if (function1.invoke(t).booleanValue() != z) {
                    if (i != i2) {
                        list.set(i, t);
                    }
                    i++;
                }
                if (i2 == lastIndex) {
                    break;
                }
                i2++;
            }
        } else {
            i = 0;
        }
        if (i >= list.size()) {
            return false;
        }
        int lastIndex2 = CollectionsKt.getLastIndex(list);
        if (lastIndex2 < i) {
            return true;
        }
        while (true) {
            list.remove(lastIndex2);
            if (lastIndex2 == i) {
                return true;
            }
            lastIndex2--;
        }
    }

    @SinceKotlin(version = "1.3")
    public static final <T> void shuffle(@NotNull List<T> shuffle, @NotNull Random random) {
        Intrinsics.checkParameterIsNotNull(shuffle, "$this$shuffle");
        Intrinsics.checkParameterIsNotNull(random, "random");
        for (int lastIndex = CollectionsKt.getLastIndex(shuffle); lastIndex >= 1; lastIndex--) {
            int nextInt = random.nextInt(lastIndex + 1);
            T t = shuffle.get(lastIndex);
            shuffle.set(lastIndex, shuffle.get(nextInt));
            shuffle.set(nextInt, t);
        }
    }
}
