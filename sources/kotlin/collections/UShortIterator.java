package kotlin.collections;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.UShort;
import kotlin.Unsigned;
import kotlin.jvm.internal.markers.KMarkers;
import org.jetbrains.annotations.NotNull;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* compiled from: UIterators.kt */
@SinceKotlin(version = "1.3")
@Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010(\n\u0002\u0018\u0002\n\u0002\b\u0005\b'\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\f\u0010\u0004\u001a\u00020\u0002H\u0086\u0002ø\u0001\u0000J\u0010\u0010\u0005\u001a\u00020\u0002H&ø\u0001\u0000¢\u0006\u0002\u0010\u0006ø\u0001\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0007"}, m1890d2 = {"Lkotlin/collections/UShortIterator;", "", "Lkotlin/UShort;", "()V", "next", "nextUShort", "()S", "kotlin-stdlib"}, m1889k = 1, m1888mv = {1, 1, 16})
@Unsigned
/* loaded from: E:\11617560_dexfile_execute.dex */
public abstract class UShortIterator implements Iterator<UShort>, KMarkers {
    public abstract short nextUShort();

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Iterator
    @NotNull
    public final UShort next() {
        return UShort.m24734boximpl(nextUShort());
    }
}
