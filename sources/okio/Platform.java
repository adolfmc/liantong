package okio;

import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Functions;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import org.jetbrains.annotations.NotNull;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* compiled from: -Platform.kt */
@Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u00000\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a*\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0005H\u0080\b¢\u0006\u0002\u0010\u0006\u001a\f\u0010\u0007\u001a\u00020\b*\u00020\tH\u0000\u001a\f\u0010\n\u001a\u00020\t*\u00020\bH\u0000*\n\u0010\u000b\"\u00020\f2\u00020\f*\n\u0010\r\"\u00020\u000e2\u00020\u000e*\n\u0010\u000f\"\u00020\u00102\u00020\u0010¨\u0006\u0011"}, m1890d2 = {"synchronized", "R", "lock", "", "block", "Lkotlin/Function0;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "asUtf8ToByteArray", "", "", "toUtf8String", "ArrayIndexOutOfBoundsException", "Ljava/lang/ArrayIndexOutOfBoundsException;", "EOFException", "Ljava/io/EOFException;", "IOException", "Ljava/io/IOException;", "okio"}, m1889k = 2, m1888mv = {1, 1, 16})
@JvmName(name = "-Platform")
/* renamed from: okio.-Platform  reason: invalid class name */
/* loaded from: E:\11617560_dexfile_execute.dex */
public final class Platform {
    @NotNull
    public static final String toUtf8String(@NotNull byte[] toUtf8String) {
        Intrinsics.checkParameterIsNotNull(toUtf8String, "$this$toUtf8String");
        return new String(toUtf8String, Charsets.UTF_8);
    }

    @NotNull
    public static final byte[] asUtf8ToByteArray(@NotNull String asUtf8ToByteArray) {
        Intrinsics.checkParameterIsNotNull(asUtf8ToByteArray, "$this$asUtf8ToByteArray");
        byte[] bytes = asUtf8ToByteArray.getBytes(Charsets.UTF_8);
        Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
        return bytes;
    }

    /* renamed from: synchronized  reason: not valid java name */
    public static final <R> R m25627synchronized(@NotNull Object lock, @NotNull Functions<? extends R> block) {
        R invoke;
        Intrinsics.checkParameterIsNotNull(lock, "lock");
        Intrinsics.checkParameterIsNotNull(block, "block");
        synchronized (lock) {
            try {
                invoke = block.invoke();
                InlineMarker.finallyStart(1);
            } catch (Throwable th) {
                InlineMarker.finallyStart(1);
                InlineMarker.finallyEnd(1);
                throw th;
            }
        }
        InlineMarker.finallyEnd(1);
        return invoke;
    }
}
