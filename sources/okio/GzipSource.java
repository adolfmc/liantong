package okio;

import java.io.EOFException;
import java.io.IOException;
import java.util.Arrays;
import java.util.zip.CRC32;
import java.util.zip.Inflater;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* compiled from: GzipSource.kt */
@Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0002\u0010\u0003J \u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0012H\u0002J\b\u0010\u0014\u001a\u00020\u000eH\u0016J\b\u0010\u0015\u001a\u00020\u000eH\u0002J\b\u0010\u0016\u001a\u00020\u000eH\u0002J\u0018\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u0018H\u0016J\b\u0010\u001c\u001a\u00020\u001dH\u0016J \u0010\u001e\u001a\u00020\u000e2\u0006\u0010\u001f\u001a\u00020\u001a2\u0006\u0010 \u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u0018H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006!"}, m1890d2 = {"Lokio/GzipSource;", "Lokio/Source;", "source", "(Lokio/Source;)V", "crc", "Ljava/util/zip/CRC32;", "inflater", "Ljava/util/zip/Inflater;", "inflaterSource", "Lokio/InflaterSource;", "section", "", "Lokio/RealBufferedSource;", "checkEqual", "", "name", "", "expected", "", "actual", "close", "consumeHeader", "consumeTrailer", "read", "", "sink", "Lokio/Buffer;", "byteCount", "timeout", "Lokio/Timeout;", "updateCrc", "buffer", "offset", "okio"}, m1889k = 1, m1888mv = {1, 1, 16})
/* loaded from: E:\11617560_dexfile_execute.dex */
public final class GzipSource implements Source {
    private final CRC32 crc;
    private final Inflater inflater;
    private final InflaterSource inflaterSource;
    private byte section;
    private final RealBufferedSource source;

    public GzipSource(@NotNull Source source) {
        Intrinsics.checkParameterIsNotNull(source, "source");
        this.source = new RealBufferedSource(source);
        this.inflater = new Inflater(true);
        this.inflaterSource = new InflaterSource((BufferedSource) this.source, this.inflater);
        this.crc = new CRC32();
    }

    @Override // okio.Source
    public long read(@NotNull Buffer sink, long j) throws IOException {
        Intrinsics.checkParameterIsNotNull(sink, "sink");
        int i = (j > 0L ? 1 : (j == 0L ? 0 : -1));
        if (!(i >= 0)) {
            throw new IllegalArgumentException(("byteCount < 0: " + j).toString());
        } else if (i == 0) {
            return 0L;
        } else {
            if (this.section == 0) {
                consumeHeader();
                this.section = (byte) 1;
            }
            if (this.section == 1) {
                long size = sink.size();
                long read = this.inflaterSource.read(sink, j);
                if (read != -1) {
                    updateCrc(sink, size, read);
                    return read;
                }
                this.section = (byte) 2;
            }
            if (this.section == 2) {
                consumeTrailer();
                this.section = (byte) 3;
                if (!this.source.exhausted()) {
                    throw new IOException("gzip finished without exhausting source");
                }
            }
            return -1L;
        }
    }

    private final void consumeHeader() throws IOException {
        this.source.require(10L);
        byte b = this.source.bufferField.getByte(3L);
        boolean z = ((b >> 1) & 1) == 1;
        if (z) {
            updateCrc(this.source.bufferField, 0L, 10L);
        }
        checkEqual("ID1ID2", 8075, this.source.readShort());
        this.source.skip(8L);
        if (((b >> 2) & 1) == 1) {
            this.source.require(2L);
            if (z) {
                updateCrc(this.source.bufferField, 0L, 2L);
            }
            long readShortLe = this.source.bufferField.readShortLe();
            this.source.require(readShortLe);
            if (z) {
                updateCrc(this.source.bufferField, 0L, readShortLe);
            }
            this.source.skip(readShortLe);
        }
        if (((b >> 3) & 1) == 1) {
            long indexOf = this.source.indexOf((byte) 0);
            if (indexOf == -1) {
                throw new EOFException();
            }
            if (z) {
                updateCrc(this.source.bufferField, 0L, indexOf + 1);
            }
            this.source.skip(indexOf + 1);
        }
        if (((b >> 4) & 1) == 1) {
            long indexOf2 = this.source.indexOf((byte) 0);
            if (indexOf2 == -1) {
                throw new EOFException();
            }
            if (z) {
                updateCrc(this.source.bufferField, 0L, indexOf2 + 1);
            }
            this.source.skip(indexOf2 + 1);
        }
        if (z) {
            checkEqual("FHCRC", this.source.readShortLe(), (short) this.crc.getValue());
            this.crc.reset();
        }
    }

    private final void consumeTrailer() throws IOException {
        checkEqual("CRC", this.source.readIntLe(), (int) this.crc.getValue());
        checkEqual("ISIZE", this.source.readIntLe(), (int) this.inflater.getBytesWritten());
    }

    @Override // okio.Source
    @NotNull
    public Timeout timeout() {
        return this.source.timeout();
    }

    @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.inflaterSource.close();
    }

    private final void updateCrc(Buffer buffer, long j, long j2) {
        int i;
        Segment segment = buffer.head;
        if (segment == null) {
            Intrinsics.throwNpe();
        }
        while (j >= segment.limit - segment.pos) {
            j -= segment.limit - segment.pos;
            segment = segment.next;
            if (segment == null) {
                Intrinsics.throwNpe();
            }
        }
        while (j2 > 0) {
            int min = (int) Math.min(segment.limit - i, j2);
            this.crc.update(segment.data, (int) (segment.pos + j), min);
            j2 -= min;
            segment = segment.next;
            if (segment == null) {
                Intrinsics.throwNpe();
            }
            j = 0;
        }
    }

    private final void checkEqual(String str, int i, int i2) {
        if (i2 == i) {
            return;
        }
        Object[] objArr = {str, Integer.valueOf(i2), Integer.valueOf(i)};
        String format = String.format("%s: actual 0x%08x != expected 0x%08x", Arrays.copyOf(objArr, objArr.length));
        Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(this, *args)");
        throw new IOException(format);
    }
}
