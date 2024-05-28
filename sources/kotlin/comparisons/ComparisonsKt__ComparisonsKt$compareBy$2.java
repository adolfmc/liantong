package kotlin.comparisons;

import com.example.asus.detectionandalign.animation.C4280b;
import java.util.Comparator;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* compiled from: Comparisons.kt */
@Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u0000\n\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u000e\u0010\u0003\u001a\n \u0004*\u0004\u0018\u0001H\u0002H\u00022\u000e\u0010\u0005\u001a\n \u0004*\u0004\u0018\u0001H\u0002H\u0002H\n¢\u0006\u0004\b\u0006\u0010\u0007"}, m1890d2 = {"<anonymous>", "", "T", "a", "kotlin.jvm.PlatformType", C4280b.f10047a, "compare", "(Ljava/lang/Object;Ljava/lang/Object;)I"}, m1889k = 3, m1888mv = {1, 1, 16})
/* loaded from: E:\11617560_dexfile_execute.dex */
public final class ComparisonsKt__ComparisonsKt$compareBy$2<T> implements Comparator<T> {
    final /* synthetic */ Function1 $selector;

    public ComparisonsKt__ComparisonsKt$compareBy$2(Function1 function1) {
        this.$selector = function1;
    }

    @Override // java.util.Comparator
    public final int compare(T t, T t2) {
        return ComparisonsKt.compareValues((Comparable) this.$selector.invoke(t), (Comparable) this.$selector.invoke(t2));
    }
}
