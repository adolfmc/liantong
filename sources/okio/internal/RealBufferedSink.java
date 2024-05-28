package okio.internal;

import com.example.asus.detectionandalign.animation.C4280b;
import java.io.EOFException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okio.Buffer;
import okio.BufferedSink;
import okio.ByteString;
import okio.Source;
import okio.Timeout;
import org.jetbrains.annotations.NotNull;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
@Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u0000D\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0015\u001a\r\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0080\b\u001a\r\u0010\u0003\u001a\u00020\u0004*\u00020\u0002H\u0080\b\u001a\r\u0010\u0005\u001a\u00020\u0004*\u00020\u0002H\u0080\b\u001a\r\u0010\u0006\u001a\u00020\u0001*\u00020\u0002H\u0080\b\u001a\r\u0010\u0007\u001a\u00020\b*\u00020\u0002H\u0080\b\u001a\r\u0010\t\u001a\u00020\n*\u00020\u0002H\u0080\b\u001a\u0015\u0010\u000b\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\f\u001a\u00020\rH\u0080\b\u001a%\u0010\u000b\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000fH\u0080\b\u001a\u001d\u0010\u000b\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\f\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u0012H\u0080\b\u001a\u0015\u0010\u000b\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u0014H\u0080\b\u001a%\u0010\u000b\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000fH\u0080\b\u001a\u001d\u0010\u000b\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\f\u001a\u00020\u00152\u0006\u0010\u0010\u001a\u00020\u0012H\u0080\b\u001a\u0015\u0010\u0016\u001a\u00020\u0012*\u00020\u00022\u0006\u0010\f\u001a\u00020\u0015H\u0080\b\u001a\u0015\u0010\u0017\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u000fH\u0080\b\u001a\u0015\u0010\u0019\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u0012H\u0080\b\u001a\u0015\u0010\u001b\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u0012H\u0080\b\u001a\u0015\u0010\u001c\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\u001d\u001a\u00020\u000fH\u0080\b\u001a\u0015\u0010\u001e\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\u001d\u001a\u00020\u000fH\u0080\b\u001a\u0015\u0010\u001f\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u0012H\u0080\b\u001a\u0015\u0010 \u001a\u00020\u0004*\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u0012H\u0080\b\u001a\u0015\u0010!\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\"\u001a\u00020\u000fH\u0080\b\u001a\u0015\u0010#\u001a\u00020\u0004*\u00020\u00022\u0006\u0010\"\u001a\u00020\u000fH\u0080\b\u001a\u0015\u0010$\u001a\u00020\u0004*\u00020\u00022\u0006\u0010%\u001a\u00020\nH\u0080\b\u001a%\u0010$\u001a\u00020\u0004*\u00020\u00022\u0006\u0010%\u001a\u00020\n2\u0006\u0010&\u001a\u00020\u000f2\u0006\u0010'\u001a\u00020\u000fH\u0080\b\u001a\u0015\u0010(\u001a\u00020\u0004*\u00020\u00022\u0006\u0010)\u001a\u00020\u000fH\u0080\b¨\u0006*"}, m1890d2 = {"commonClose", "", "Lokio/RealBufferedSink;", "commonEmit", "Lokio/BufferedSink;", "commonEmitCompleteSegments", "commonFlush", "commonTimeout", "Lokio/Timeout;", "commonToString", "", "commonWrite", "source", "", "offset", "", "byteCount", "Lokio/Buffer;", "", "byteString", "Lokio/ByteString;", "Lokio/Source;", "commonWriteAll", "commonWriteByte", C4280b.f10047a, "commonWriteDecimalLong", "v", "commonWriteHexadecimalUnsignedLong", "commonWriteInt", "i", "commonWriteIntLe", "commonWriteLong", "commonWriteLongLe", "commonWriteShort", "s", "commonWriteShortLe", "commonWriteUtf8", "string", "beginIndex", "endIndex", "commonWriteUtf8CodePoint", "codePoint", "okio"}, m1889k = 2, m1888mv = {1, 1, 16})
/* renamed from: okio.internal.RealBufferedSinkKt */
/* loaded from: E:\11617560_dexfile_execute.dex */
public final class RealBufferedSink {
    public static final void commonWrite(@NotNull okio.RealBufferedSink commonWrite, @NotNull Buffer source, long j) {
        Intrinsics.checkParameterIsNotNull(commonWrite, "$this$commonWrite");
        Intrinsics.checkParameterIsNotNull(source, "source");
        if (!(!commonWrite.closed)) {
            throw new IllegalStateException("closed".toString());
        }
        commonWrite.bufferField.write(source, j);
        commonWrite.emitCompleteSegments();
    }

    @NotNull
    public static final BufferedSink commonWrite(@NotNull okio.RealBufferedSink commonWrite, @NotNull ByteString byteString) {
        Intrinsics.checkParameterIsNotNull(commonWrite, "$this$commonWrite");
        Intrinsics.checkParameterIsNotNull(byteString, "byteString");
        if (!(!commonWrite.closed)) {
            throw new IllegalStateException("closed".toString());
        }
        commonWrite.bufferField.write(byteString);
        return commonWrite.emitCompleteSegments();
    }

    @NotNull
    public static final BufferedSink commonWrite(@NotNull okio.RealBufferedSink commonWrite, @NotNull ByteString byteString, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(commonWrite, "$this$commonWrite");
        Intrinsics.checkParameterIsNotNull(byteString, "byteString");
        if (!(!commonWrite.closed)) {
            throw new IllegalStateException("closed".toString());
        }
        commonWrite.bufferField.write(byteString, i, i2);
        return commonWrite.emitCompleteSegments();
    }

    @NotNull
    public static final BufferedSink commonWriteUtf8(@NotNull okio.RealBufferedSink commonWriteUtf8, @NotNull String string) {
        Intrinsics.checkParameterIsNotNull(commonWriteUtf8, "$this$commonWriteUtf8");
        Intrinsics.checkParameterIsNotNull(string, "string");
        if (!(!commonWriteUtf8.closed)) {
            throw new IllegalStateException("closed".toString());
        }
        commonWriteUtf8.bufferField.writeUtf8(string);
        return commonWriteUtf8.emitCompleteSegments();
    }

    @NotNull
    public static final BufferedSink commonWriteUtf8(@NotNull okio.RealBufferedSink commonWriteUtf8, @NotNull String string, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(commonWriteUtf8, "$this$commonWriteUtf8");
        Intrinsics.checkParameterIsNotNull(string, "string");
        if (!(!commonWriteUtf8.closed)) {
            throw new IllegalStateException("closed".toString());
        }
        commonWriteUtf8.bufferField.writeUtf8(string, i, i2);
        return commonWriteUtf8.emitCompleteSegments();
    }

    @NotNull
    public static final BufferedSink commonWriteUtf8CodePoint(@NotNull okio.RealBufferedSink commonWriteUtf8CodePoint, int i) {
        Intrinsics.checkParameterIsNotNull(commonWriteUtf8CodePoint, "$this$commonWriteUtf8CodePoint");
        if (!(!commonWriteUtf8CodePoint.closed)) {
            throw new IllegalStateException("closed".toString());
        }
        commonWriteUtf8CodePoint.bufferField.writeUtf8CodePoint(i);
        return commonWriteUtf8CodePoint.emitCompleteSegments();
    }

    @NotNull
    public static final BufferedSink commonWrite(@NotNull okio.RealBufferedSink commonWrite, @NotNull byte[] source) {
        Intrinsics.checkParameterIsNotNull(commonWrite, "$this$commonWrite");
        Intrinsics.checkParameterIsNotNull(source, "source");
        if (!(!commonWrite.closed)) {
            throw new IllegalStateException("closed".toString());
        }
        commonWrite.bufferField.write(source);
        return commonWrite.emitCompleteSegments();
    }

    @NotNull
    public static final BufferedSink commonWrite(@NotNull okio.RealBufferedSink commonWrite, @NotNull byte[] source, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(commonWrite, "$this$commonWrite");
        Intrinsics.checkParameterIsNotNull(source, "source");
        if (!(!commonWrite.closed)) {
            throw new IllegalStateException("closed".toString());
        }
        commonWrite.bufferField.write(source, i, i2);
        return commonWrite.emitCompleteSegments();
    }

    @NotNull
    public static final BufferedSink commonWriteByte(@NotNull okio.RealBufferedSink commonWriteByte, int i) {
        Intrinsics.checkParameterIsNotNull(commonWriteByte, "$this$commonWriteByte");
        if (!(!commonWriteByte.closed)) {
            throw new IllegalStateException("closed".toString());
        }
        commonWriteByte.bufferField.writeByte(i);
        return commonWriteByte.emitCompleteSegments();
    }

    @NotNull
    public static final BufferedSink commonWriteShort(@NotNull okio.RealBufferedSink commonWriteShort, int i) {
        Intrinsics.checkParameterIsNotNull(commonWriteShort, "$this$commonWriteShort");
        if (!(!commonWriteShort.closed)) {
            throw new IllegalStateException("closed".toString());
        }
        commonWriteShort.bufferField.writeShort(i);
        return commonWriteShort.emitCompleteSegments();
    }

    @NotNull
    public static final BufferedSink commonWriteShortLe(@NotNull okio.RealBufferedSink commonWriteShortLe, int i) {
        Intrinsics.checkParameterIsNotNull(commonWriteShortLe, "$this$commonWriteShortLe");
        if (!(!commonWriteShortLe.closed)) {
            throw new IllegalStateException("closed".toString());
        }
        commonWriteShortLe.bufferField.writeShortLe(i);
        return commonWriteShortLe.emitCompleteSegments();
    }

    @NotNull
    public static final BufferedSink commonWriteInt(@NotNull okio.RealBufferedSink commonWriteInt, int i) {
        Intrinsics.checkParameterIsNotNull(commonWriteInt, "$this$commonWriteInt");
        if (!(!commonWriteInt.closed)) {
            throw new IllegalStateException("closed".toString());
        }
        commonWriteInt.bufferField.writeInt(i);
        return commonWriteInt.emitCompleteSegments();
    }

    @NotNull
    public static final BufferedSink commonWriteIntLe(@NotNull okio.RealBufferedSink commonWriteIntLe, int i) {
        Intrinsics.checkParameterIsNotNull(commonWriteIntLe, "$this$commonWriteIntLe");
        if (!(!commonWriteIntLe.closed)) {
            throw new IllegalStateException("closed".toString());
        }
        commonWriteIntLe.bufferField.writeIntLe(i);
        return commonWriteIntLe.emitCompleteSegments();
    }

    @NotNull
    public static final BufferedSink commonWriteLong(@NotNull okio.RealBufferedSink commonWriteLong, long j) {
        Intrinsics.checkParameterIsNotNull(commonWriteLong, "$this$commonWriteLong");
        if (!(!commonWriteLong.closed)) {
            throw new IllegalStateException("closed".toString());
        }
        commonWriteLong.bufferField.writeLong(j);
        return commonWriteLong.emitCompleteSegments();
    }

    @NotNull
    public static final BufferedSink commonWriteLongLe(@NotNull okio.RealBufferedSink commonWriteLongLe, long j) {
        Intrinsics.checkParameterIsNotNull(commonWriteLongLe, "$this$commonWriteLongLe");
        if (!(!commonWriteLongLe.closed)) {
            throw new IllegalStateException("closed".toString());
        }
        commonWriteLongLe.bufferField.writeLongLe(j);
        return commonWriteLongLe.emitCompleteSegments();
    }

    @NotNull
    public static final BufferedSink commonWriteDecimalLong(@NotNull okio.RealBufferedSink commonWriteDecimalLong, long j) {
        Intrinsics.checkParameterIsNotNull(commonWriteDecimalLong, "$this$commonWriteDecimalLong");
        if (!(!commonWriteDecimalLong.closed)) {
            throw new IllegalStateException("closed".toString());
        }
        commonWriteDecimalLong.bufferField.writeDecimalLong(j);
        return commonWriteDecimalLong.emitCompleteSegments();
    }

    @NotNull
    public static final BufferedSink commonWriteHexadecimalUnsignedLong(@NotNull okio.RealBufferedSink commonWriteHexadecimalUnsignedLong, long j) {
        Intrinsics.checkParameterIsNotNull(commonWriteHexadecimalUnsignedLong, "$this$commonWriteHexadecimalUnsignedLong");
        if (!(!commonWriteHexadecimalUnsignedLong.closed)) {
            throw new IllegalStateException("closed".toString());
        }
        commonWriteHexadecimalUnsignedLong.bufferField.writeHexadecimalUnsignedLong(j);
        return commonWriteHexadecimalUnsignedLong.emitCompleteSegments();
    }

    @NotNull
    public static final BufferedSink commonEmitCompleteSegments(@NotNull okio.RealBufferedSink commonEmitCompleteSegments) {
        Intrinsics.checkParameterIsNotNull(commonEmitCompleteSegments, "$this$commonEmitCompleteSegments");
        if (!(!commonEmitCompleteSegments.closed)) {
            throw new IllegalStateException("closed".toString());
        }
        long completeSegmentByteCount = commonEmitCompleteSegments.bufferField.completeSegmentByteCount();
        if (completeSegmentByteCount > 0) {
            commonEmitCompleteSegments.sink.write(commonEmitCompleteSegments.bufferField, completeSegmentByteCount);
        }
        return commonEmitCompleteSegments;
    }

    @NotNull
    public static final BufferedSink commonEmit(@NotNull okio.RealBufferedSink commonEmit) {
        Intrinsics.checkParameterIsNotNull(commonEmit, "$this$commonEmit");
        if (!(!commonEmit.closed)) {
            throw new IllegalStateException("closed".toString());
        }
        long size = commonEmit.bufferField.size();
        if (size > 0) {
            commonEmit.sink.write(commonEmit.bufferField, size);
        }
        return commonEmit;
    }

    public static final void commonFlush(@NotNull okio.RealBufferedSink commonFlush) {
        Intrinsics.checkParameterIsNotNull(commonFlush, "$this$commonFlush");
        if (!(!commonFlush.closed)) {
            throw new IllegalStateException("closed".toString());
        }
        if (commonFlush.bufferField.size() > 0) {
            commonFlush.sink.write(commonFlush.bufferField, commonFlush.bufferField.size());
        }
        commonFlush.sink.flush();
    }

    public static final void commonClose(@NotNull okio.RealBufferedSink commonClose) {
        Intrinsics.checkParameterIsNotNull(commonClose, "$this$commonClose");
        if (commonClose.closed) {
            return;
        }
        Throwable th = null;
        try {
            if (commonClose.bufferField.size() > 0) {
                commonClose.sink.write(commonClose.bufferField, commonClose.bufferField.size());
            }
        } catch (Throwable th2) {
            th = th2;
        }
        try {
            commonClose.sink.close();
        } catch (Throwable th3) {
            if (th == null) {
                th = th3;
            }
        }
        commonClose.closed = true;
        if (th != null) {
            throw th;
        }
    }

    @NotNull
    public static final Timeout commonTimeout(@NotNull okio.RealBufferedSink commonTimeout) {
        Intrinsics.checkParameterIsNotNull(commonTimeout, "$this$commonTimeout");
        return commonTimeout.sink.timeout();
    }

    @NotNull
    public static final String commonToString(@NotNull okio.RealBufferedSink commonToString) {
        Intrinsics.checkParameterIsNotNull(commonToString, "$this$commonToString");
        return "buffer(" + commonToString.sink + ')';
    }

    public static final long commonWriteAll(@NotNull okio.RealBufferedSink commonWriteAll, @NotNull Source source) {
        Intrinsics.checkParameterIsNotNull(commonWriteAll, "$this$commonWriteAll");
        Intrinsics.checkParameterIsNotNull(source, "source");
        long j = 0;
        while (true) {
            long read = source.read(commonWriteAll.bufferField, 8192);
            if (read == -1) {
                return j;
            }
            j += read;
            commonWriteAll.emitCompleteSegments();
        }
    }

    @NotNull
    public static final BufferedSink commonWrite(@NotNull okio.RealBufferedSink commonWrite, @NotNull Source source, long j) {
        Intrinsics.checkParameterIsNotNull(commonWrite, "$this$commonWrite");
        Intrinsics.checkParameterIsNotNull(source, "source");
        while (j > 0) {
            long read = source.read(commonWrite.bufferField, j);
            if (read == -1) {
                throw new EOFException();
            }
            j -= read;
            commonWrite.emitCompleteSegments();
        }
        return commonWrite;
    }
}
