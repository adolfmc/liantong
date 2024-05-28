package kotlin.collections;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.UInt;
import kotlin.Unsigned;
import kotlin.jvm.internal.markers.KMarkers;
import org.jetbrains.annotations.NotNull;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* compiled from: UIterators.kt */
@SinceKotlin(version = "1.3")
@Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010(\n\u0002\u0018\u0002\n\u0002\b\u0005\b'\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\f\u0010\u0004\u001a\u00020\u0002H\u0086\u0002ø\u0001\u0000J\u0010\u0010\u0005\u001a\u00020\u0002H&ø\u0001\u0000¢\u0006\u0002\u0010\u0006ø\u0001\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0007"}, m1890d2 = {"Lkotlin/collections/UIntIterator;", "", "Lkotlin/UInt;", "()V", "next", "nextUInt", "()I", "kotlin-stdlib"}, m1889k = 1, m1888mv = {1, 1, 16})
@Unsigned
/* loaded from: E:\11617560_dexfile_execute.dex */
public abstract class UIntIterator implements Iterator<UInt>, KMarkers {
    public abstract int nextUInt();

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Iterator
    @NotNull
    public final UInt next() {
        return UInt.m24568boximpl(nextUInt());
    }
}
