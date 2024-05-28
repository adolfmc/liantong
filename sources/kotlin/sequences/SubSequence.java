package kotlin.sequences;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* compiled from: Sequences.kt */
@Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010(\n\u0002\b\u0002\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B#\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0002\u0010\bJ\u0016\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\u00022\u0006\u0010\r\u001a\u00020\u0006H\u0016J\u000f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\u000fH\u0096\u0002J\u0016\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028\u00000\u00022\u0006\u0010\r\u001a\u00020\u0006H\u0016R\u0014\u0010\t\u001a\u00020\u00068BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, m1890d2 = {"Lkotlin/sequences/SubSequence;", "T", "Lkotlin/sequences/Sequence;", "Lkotlin/sequences/DropTakeSequence;", "sequence", "startIndex", "", "endIndex", "(Lkotlin/sequences/Sequence;II)V", "count", "getCount", "()I", "drop", "n", "iterator", "", "take", "kotlin-stdlib"}, m1889k = 1, m1888mv = {1, 1, 16})
/* loaded from: E:\11617560_dexfile_execute.dex */
public final class SubSequence<T> implements DropTakeSequence<T>, Sequence<T> {
    private final int endIndex;
    private final Sequence<T> sequence;
    private final int startIndex;

    /* JADX WARN: Multi-variable type inference failed */
    public SubSequence(@NotNull Sequence<? extends T> sequence, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(sequence, "sequence");
        this.sequence = sequence;
        this.startIndex = i;
        this.endIndex = i2;
        if (!(this.startIndex >= 0)) {
            throw new IllegalArgumentException(("startIndex should be non-negative, but is " + this.startIndex).toString());
        }
        if (!(this.endIndex >= 0)) {
            throw new IllegalArgumentException(("endIndex should be non-negative, but is " + this.endIndex).toString());
        }
        if (this.endIndex >= this.startIndex) {
            return;
        }
        throw new IllegalArgumentException(("endIndex should be not less than startIndex, but was " + this.endIndex + " < " + this.startIndex).toString());
    }

    private final int getCount() {
        return this.endIndex - this.startIndex;
    }

    @Override // kotlin.sequences.DropTakeSequence
    @NotNull
    public Sequence<T> drop(int i) {
        return i >= getCount() ? SequencesKt.emptySequence() : new SubSequence(this.sequence, this.startIndex + i, this.endIndex);
    }

    @Override // kotlin.sequences.DropTakeSequence
    @NotNull
    public Sequence<T> take(int i) {
        if (i >= getCount()) {
            return this;
        }
        Sequence<T> sequence = this.sequence;
        int i2 = this.startIndex;
        return new SubSequence(sequence, i2, i + i2);
    }

    @Override // kotlin.sequences.Sequence
    @NotNull
    public Iterator<T> iterator() {
        return new SubSequence$iterator$1(this);
    }
}
