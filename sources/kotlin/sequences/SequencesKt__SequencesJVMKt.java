package kotlin.sequences;

import java.util.Enumeration;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.internal.InlineOnly;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* compiled from: SequencesJVM.kt */
@Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u001f\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u0087\b¨\u0006\u0004"}, m1890d2 = {"asSequence", "Lkotlin/sequences/Sequence;", "T", "Ljava/util/Enumeration;", "kotlin-stdlib"}, m1889k = 5, m1888mv = {1, 1, 16}, m1886xi = 1, m1885xs = "kotlin/sequences/SequencesKt")
/* loaded from: E:\11617560_dexfile_execute.dex */
public class SequencesKt__SequencesJVMKt extends SequencesKt__SequenceBuilderKt {
    @InlineOnly
    private static final <T> Sequence<T> asSequence(@NotNull Enumeration<T> enumeration) {
        return SequencesKt.asSequence(CollectionsKt.iterator(enumeration));
    }
}
