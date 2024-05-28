package kotlin.text;

import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* compiled from: Charsets.kt */
@Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a\u0011\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0087\b¨\u0006\u0004"}, m1890d2 = {"charset", "Ljava/nio/charset/Charset;", "charsetName", "", "kotlin-stdlib"}, m1889k = 2, m1888mv = {1, 1, 16})
@JvmName(name = "CharsetsKt")
/* loaded from: E:\11617560_dexfile_execute.dex */
public final class CharsetsKt {
    @InlineOnly
    private static final Charset charset(String str) {
        Charset forName = Charset.forName(str);
        Intrinsics.checkExpressionValueIsNotNull(forName, "Charset.forName(charsetName)");
        return forName;
    }
}
