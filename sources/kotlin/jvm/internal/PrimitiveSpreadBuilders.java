package kotlin.jvm.internal;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
@Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0018\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u0006\u0010\u000b\u001a\u00020\u0002J\f\u0010\f\u001a\u00020\u0004*\u00020\u0002H\u0014R\u000e\u0010\u0006\u001a\u00020\u0002X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, m1890d2 = {"Lkotlin/jvm/internal/BooleanSpreadBuilder;", "Lkotlin/jvm/internal/PrimitiveSpreadBuilder;", "", "size", "", "(I)V", "values", "add", "", "value", "", "toArray", "getSize", "kotlin-stdlib"}, m1889k = 1, m1888mv = {1, 1, 16})
/* renamed from: kotlin.jvm.internal.BooleanSpreadBuilder */
/* loaded from: E:\11617560_dexfile_execute.dex */
public final class PrimitiveSpreadBuilders extends PrimitiveSpreadBuilder<boolean[]> {
    private final boolean[] values;

    public PrimitiveSpreadBuilders(int i) {
        super(i);
        this.values = new boolean[i];
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlin.jvm.internal.PrimitiveSpreadBuilder
    public int getSize(@NotNull boolean[] getSize) {
        Intrinsics.checkParameterIsNotNull(getSize, "$this$getSize");
        return getSize.length;
    }

    public final void add(boolean z) {
        boolean[] zArr = this.values;
        int position = getPosition();
        setPosition(position + 1);
        zArr[position] = z;
    }

    @NotNull
    public final boolean[] toArray() {
        return toArray(this.values, new boolean[size()]);
    }
}
