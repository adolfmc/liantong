package kotlin.text;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* compiled from: MatchResult.kt */
@SinceKotlin(version = "1.1")
@Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\bg\u0018\u00002\u00020\u0001J\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u0005H¦\u0002¨\u0006\u0006"}, m1890d2 = {"Lkotlin/text/MatchNamedGroupCollection;", "Lkotlin/text/MatchGroupCollection;", "get", "Lkotlin/text/MatchGroup;", "name", "", "kotlin-stdlib"}, m1889k = 1, m1888mv = {1, 1, 16})
/* loaded from: E:\11617560_dexfile_execute.dex */
public interface MatchNamedGroupCollection extends MatchGroupCollection {
    @Nullable
    MatchGroup get(@NotNull String str);
}
