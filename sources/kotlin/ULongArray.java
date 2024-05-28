package kotlin;

import java.util.Arrays;
import java.util.Collection;
import java.util.NoSuchElementException;
import kotlin.collections.ArraysKt;
import kotlin.collections.ULongIterator;
import kotlin.jvm.internal.CollectionToArray;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMarkers;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* compiled from: ULongArray.kt */
@SinceKotlin(version = "1.3")
@Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0016\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\u0000\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0087@\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001-B\u0014\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004ø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0006B\u0014\b\u0001\u0012\u0006\u0010\u0007\u001a\u00020\bø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\tJ\u001b\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0002H\u0096\u0002ø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u0012J \u0010\u0013\u001a\u00020\u000f2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\u0016ø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0016J\u0013\u0010\u0017\u001a\u00020\u000f2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019HÖ\u0003J\u001b\u0010\u001a\u001a\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u0004H\u0086\u0002ø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001dJ\t\u0010\u001e\u001a\u00020\u0004HÖ\u0001J\u000f\u0010\u001f\u001a\u00020\u000fH\u0016¢\u0006\u0004\b \u0010!J\u0010\u0010\"\u001a\u00020#H\u0096\u0002¢\u0006\u0004\b$\u0010%J#\u0010&\u001a\u00020'2\u0006\u0010\u001b\u001a\u00020\u00042\u0006\u0010(\u001a\u00020\u0002H\u0086\u0002ø\u0001\u0000¢\u0006\u0004\b)\u0010*J\t\u0010+\u001a\u00020,HÖ\u0001R\u0014\u0010\u0003\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0016\u0010\u0007\u001a\u00020\b8\u0000X\u0081\u0004¢\u0006\b\n\u0000\u0012\u0004\b\f\u0010\rø\u0001\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006."}, m1890d2 = {"Lkotlin/ULongArray;", "", "Lkotlin/ULong;", "size", "", "constructor-impl", "(I)[J", "storage", "", "([J)[J", "getSize-impl", "([J)I", "storage$annotations", "()V", "contains", "", "element", "contains-VKZWuLQ", "([JJ)Z", "containsAll", "elements", "containsAll-impl", "([JLjava/util/Collection;)Z", "equals", "other", "", "get", "index", "get-impl", "([JI)J", "hashCode", "isEmpty", "isEmpty-impl", "([J)Z", "iterator", "Lkotlin/collections/ULongIterator;", "iterator-impl", "([J)Lkotlin/collections/ULongIterator;", "set", "", "value", "set-k8EXiF4", "([JIJ)V", "toString", "", "Iterator", "kotlin-stdlib"}, m1889k = 1, m1888mv = {1, 1, 16})
@Unsigned
/* loaded from: E:\11617560_dexfile_execute.dex */
public final class ULongArray implements Collection<ULong>, KMarkers {
    @NotNull
    private final long[] storage;

    @NotNull
    /* renamed from: box-impl  reason: not valid java name */
    public static final /* synthetic */ ULongArray m24687boximpl(@NotNull long[] v) {
        Intrinsics.checkParameterIsNotNull(v, "v");
        return new ULongArray(v);
    }

    @PublishedApi
    @NotNull
    /* renamed from: constructor-impl  reason: not valid java name */
    public static long[] m24689constructorimpl(@NotNull long[] storage) {
        Intrinsics.checkParameterIsNotNull(storage, "storage");
        return storage;
    }

    /* renamed from: equals-impl  reason: not valid java name */
    public static boolean m24692equalsimpl(long[] jArr, @Nullable Object obj) {
        return (obj instanceof ULongArray) && Intrinsics.areEqual(jArr, ((ULongArray) obj).m24703unboximpl());
    }

    /* renamed from: equals-impl0  reason: not valid java name */
    public static final boolean m24693equalsimpl0(@NotNull long[] jArr, @NotNull long[] jArr2) {
        return Intrinsics.areEqual(jArr, jArr2);
    }

    /* renamed from: hashCode-impl  reason: not valid java name */
    public static int m24696hashCodeimpl(long[] jArr) {
        if (jArr != null) {
            return Arrays.hashCode(jArr);
        }
        return 0;
    }

    @PublishedApi
    public static /* synthetic */ void storage$annotations() {
    }

    @NotNull
    /* renamed from: toString-impl  reason: not valid java name */
    public static String m24700toStringimpl(long[] jArr) {
        return "ULongArray(storage=" + Arrays.toString(jArr) + ")";
    }

    @Override // java.util.Collection
    public /* synthetic */ boolean add(ULong uLong) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    /* renamed from: add-VKZWuLQ  reason: not valid java name */
    public boolean m24701addVKZWuLQ(long j) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public boolean addAll(Collection<? extends ULong> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public void clear() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    /* renamed from: contains-VKZWuLQ  reason: not valid java name */
    public boolean m24702containsVKZWuLQ(long j) {
        return m24690containsVKZWuLQ(this.storage, j);
    }

    @Override // java.util.Collection
    public boolean containsAll(@NotNull Collection<? extends Object> collection) {
        return m24691containsAllimpl(this.storage, collection);
    }

    @Override // java.util.Collection
    public boolean equals(Object obj) {
        return m24692equalsimpl(this.storage, obj);
    }

    public int getSize() {
        return m24695getSizeimpl(this.storage);
    }

    @Override // java.util.Collection
    public int hashCode() {
        return m24696hashCodeimpl(this.storage);
    }

    @Override // java.util.Collection
    public boolean isEmpty() {
        return m24697isEmptyimpl(this.storage);
    }

    @Override // java.util.Collection, java.lang.Iterable
    @NotNull
    public ULongIterator iterator() {
        return m24698iteratorimpl(this.storage);
    }

    @Override // java.util.Collection
    public boolean remove(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public boolean removeAll(Collection<? extends Object> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public boolean retainAll(Collection<? extends Object> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection
    public Object[] toArray() {
        return CollectionToArray.toArray(this);
    }

    @Override // java.util.Collection
    public <T> T[] toArray(T[] tArr) {
        return (T[]) CollectionToArray.toArray(this, tArr);
    }

    public String toString() {
        return m24700toStringimpl(this.storage);
    }

    @NotNull
    /* renamed from: unbox-impl  reason: not valid java name */
    public final /* synthetic */ long[] m24703unboximpl() {
        return this.storage;
    }

    @Override // java.util.Collection
    public final boolean contains(Object obj) {
        if (obj instanceof ULong) {
            return m24702containsVKZWuLQ(((ULong) obj).m24686unboximpl());
        }
        return false;
    }

    @Override // java.util.Collection
    public final int size() {
        return getSize();
    }

    @PublishedApi
    private /* synthetic */ ULongArray(@NotNull long[] storage) {
        Intrinsics.checkParameterIsNotNull(storage, "storage");
        this.storage = storage;
    }

    @NotNull
    /* renamed from: constructor-impl  reason: not valid java name */
    public static long[] m24688constructorimpl(int i) {
        return m24689constructorimpl(new long[i]);
    }

    /* renamed from: get-impl  reason: not valid java name */
    public static final long m24694getimpl(long[] jArr, int i) {
        return ULong.m24643constructorimpl(jArr[i]);
    }

    /* renamed from: set-k8EXiF4  reason: not valid java name */
    public static final void m24699setk8EXiF4(long[] jArr, int i, long j) {
        jArr[i] = j;
    }

    /* renamed from: getSize-impl  reason: not valid java name */
    public static int m24695getSizeimpl(long[] jArr) {
        return jArr.length;
    }

    @NotNull
    /* renamed from: iterator-impl  reason: not valid java name */
    public static ULongIterator m24698iteratorimpl(long[] jArr) {
        return new Iterator(jArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* compiled from: ULongArray.kt */
    @Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0016\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\bH\u0096\u0002J\u0010\u0010\t\u001a\u00020\nH\u0016ø\u0001\u0000¢\u0006\u0002\u0010\u000bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\f"}, m1890d2 = {"Lkotlin/ULongArray$Iterator;", "Lkotlin/collections/ULongIterator;", "array", "", "([J)V", "index", "", "hasNext", "", "nextULong", "Lkotlin/ULong;", "()J", "kotlin-stdlib"}, m1889k = 1, m1888mv = {1, 1, 16})
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static final class Iterator extends ULongIterator {
        private final long[] array;
        private int index;

        public Iterator(@NotNull long[] array) {
            Intrinsics.checkParameterIsNotNull(array, "array");
            this.array = array;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.index < this.array.length;
        }

        @Override // kotlin.collections.ULongIterator
        public long nextULong() {
            int i = this.index;
            long[] jArr = this.array;
            if (i < jArr.length) {
                this.index = i + 1;
                return ULong.m24643constructorimpl(jArr[i]);
            }
            throw new NoSuchElementException(String.valueOf(i));
        }
    }

    /* renamed from: contains-VKZWuLQ  reason: not valid java name */
    public static boolean m24690containsVKZWuLQ(long[] jArr, long j) {
        return ArraysKt.contains(jArr, j);
    }

    /* renamed from: containsAll-impl  reason: not valid java name */
    public static boolean m24691containsAllimpl(long[] jArr, @NotNull Collection<ULong> elements) {
        boolean z;
        Intrinsics.checkParameterIsNotNull(elements, "elements");
        Collection<ULong> collection = elements;
        if (collection.isEmpty()) {
            return true;
        }
        for (Object obj : collection) {
            if ((obj instanceof ULong) && ArraysKt.contains(jArr, ((ULong) obj).m24686unboximpl())) {
                z = true;
                continue;
            } else {
                z = false;
                continue;
            }
            if (!z) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: isEmpty-impl  reason: not valid java name */
    public static boolean m24697isEmptyimpl(long[] jArr) {
        return jArr.length == 0;
    }
}
