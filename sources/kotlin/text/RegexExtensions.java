package kotlin.text;

import java.util.Set;
import kotlin.Metadata;
import kotlin.internal.InlineOnly;
import org.jetbrains.annotations.NotNull;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
@Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\r\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0087\b\u001a\u001b\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0087\b\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0005H\u0087\b¨\u0006\u0007"}, m1890d2 = {"toRegex", "Lkotlin/text/Regex;", "", "options", "", "Lkotlin/text/RegexOption;", "option", "kotlin-stdlib"}, m1889k = 5, m1888mv = {1, 1, 16}, m1886xi = 1, m1885xs = "kotlin/text/StringsKt")
/* renamed from: kotlin.text.StringsKt__RegexExtensionsKt */
/* loaded from: E:\11617560_dexfile_execute.dex */
class RegexExtensions extends RegexExtensionsJVM {
    @InlineOnly
    private static final Regex toRegex(@NotNull String str) {
        return new Regex(str);
    }

    @InlineOnly
    private static final Regex toRegex(@NotNull String str, RegexOption regexOption) {
        return new Regex(str, regexOption);
    }

    @InlineOnly
    private static final Regex toRegex(@NotNull String str, Set<? extends RegexOption> set) {
        return new Regex(str, set);
    }
}
