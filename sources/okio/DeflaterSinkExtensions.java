package okio;

import java.util.zip.Deflater;
import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* compiled from: DeflaterSink.kt */
@Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0017\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u0004H\u0086\b¨\u0006\u0005"}, m1890d2 = {"deflate", "Lokio/DeflaterSink;", "Lokio/Sink;", "deflater", "Ljava/util/zip/Deflater;", "okio"}, m1889k = 2, m1888mv = {1, 1, 16})
@JvmName(name = "-DeflaterSinkExtensions")
/* renamed from: okio.-DeflaterSinkExtensions  reason: invalid class name */
/* loaded from: E:\11617560_dexfile_execute.dex */
public final class DeflaterSinkExtensions {
    public static /* synthetic */ DeflaterSink deflate$default(Sink deflate, Deflater deflater, int i, Object obj) {
        if ((i & 1) != 0) {
            deflater = new Deflater();
        }
        Intrinsics.checkParameterIsNotNull(deflate, "$this$deflate");
        Intrinsics.checkParameterIsNotNull(deflater, "deflater");
        return new DeflaterSink(deflate, deflater);
    }

    @NotNull
    public static final DeflaterSink deflate(@NotNull Sink deflate, @NotNull Deflater deflater) {
        Intrinsics.checkParameterIsNotNull(deflate, "$this$deflate");
        Intrinsics.checkParameterIsNotNull(deflater, "deflater");
        return new DeflaterSink(deflate, deflater);
    }
}
