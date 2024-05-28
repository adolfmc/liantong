package kotlin.collections;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.ArrayIteratorsKt;
import kotlin.jvm.internal.markers.KMarkers;
import org.jetbrains.annotations.NotNull;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* compiled from: Iterables.kt */
@Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u0000\u0011\n\u0000\n\u0002\u0010\u001c\n\u0000\n\u0002\u0010(\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u000f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003H\u0096\u0002¨\u0006\u0004¸\u0006\u0000"}, m1890d2 = {"kotlin/collections/CollectionsKt__IterablesKt$Iterable$1", "", "iterator", "", "kotlin-stdlib"}, m1889k = 1, m1888mv = {1, 1, 16})
/* loaded from: E:\11617560_dexfile_execute.dex */
public final class ArraysKt___ArraysKt$asIterable$$inlined$Iterable$4 implements Iterable<Integer>, KMarkers {
    final /* synthetic */ int[] $this_asIterable$inlined;

    public ArraysKt___ArraysKt$asIterable$$inlined$Iterable$4(int[] iArr) {
        this.$this_asIterable$inlined = iArr;
    }

    @Override // java.lang.Iterable
    @NotNull
    public Iterator<Integer> iterator() {
        return ArrayIteratorsKt.iterator(this.$this_asIterable$inlined);
    }
}
