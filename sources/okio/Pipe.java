package okio;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import kotlin.Annotations;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* compiled from: Pipe.kt */
@Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u0011\u001a\u00020\nJ\r\u0010\u0011\u001a\u00020\nH\u0007¢\u0006\u0002\b J\r\u0010\u0018\u001a\u00020\u0019H\u0007¢\u0006\u0002\b!J&\u0010\"\u001a\u00020\u001f*\u00020\n2\u0017\u0010#\u001a\u0013\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u001f0$¢\u0006\u0002\b%H\u0082\bR\u0014\u0010\u0005\u001a\u00020\u0006X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\u0011\u001a\u00020\n8G¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\fR\u001a\u0010\u0012\u001a\u00020\u0013X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u0013\u0010\u0018\u001a\u00020\u00198G¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u001aR\u001a\u0010\u001b\u001a\u00020\u0013X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0015\"\u0004\b\u001d\u0010\u0017¨\u0006&"}, m1890d2 = {"Lokio/Pipe;", "", "maxBufferSize", "", "(J)V", "buffer", "Lokio/Buffer;", "getBuffer$okio", "()Lokio/Buffer;", "foldedSink", "Lokio/Sink;", "getFoldedSink$okio", "()Lokio/Sink;", "setFoldedSink$okio", "(Lokio/Sink;)V", "getMaxBufferSize$okio", "()J", "sink", "sinkClosed", "", "getSinkClosed$okio", "()Z", "setSinkClosed$okio", "(Z)V", "source", "Lokio/Source;", "()Lokio/Source;", "sourceClosed", "getSourceClosed$okio", "setSourceClosed$okio", "fold", "", "-deprecated_sink", "-deprecated_source", "forward", "block", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "okio"}, m1889k = 1, m1888mv = {1, 1, 16})
/* loaded from: E:\11617560_dexfile_execute.dex */
public final class Pipe {
    @NotNull
    private final Buffer buffer = new Buffer();
    @Nullable
    private Sink foldedSink;
    private final long maxBufferSize;
    @NotNull
    private final Sink sink;
    private boolean sinkClosed;
    @NotNull
    private final Source source;
    private boolean sourceClosed;

    public Pipe(long j) {
        this.maxBufferSize = j;
        if (!(this.maxBufferSize >= 1)) {
            throw new IllegalArgumentException(("maxBufferSize < 1: " + this.maxBufferSize).toString());
        }
        this.sink = new Sink() { // from class: okio.Pipe$sink$1
            private final Timeout timeout = new Timeout();

            @Override // okio.Sink
            public void write(@NotNull Buffer source, long j2) {
                boolean hasDeadline;
                Intrinsics.checkParameterIsNotNull(source, "source");
                Sink sink = null;
                synchronized (Pipe.this.getBuffer$okio()) {
                    if (!(!Pipe.this.getSinkClosed$okio())) {
                        throw new IllegalStateException("closed".toString());
                    }
                    while (true) {
                        if (j2 <= 0) {
                            break;
                        }
                        Sink foldedSink$okio = Pipe.this.getFoldedSink$okio();
                        if (foldedSink$okio != null) {
                            sink = foldedSink$okio;
                            break;
                        } else if (Pipe.this.getSourceClosed$okio()) {
                            throw new IOException("source is closed");
                        } else {
                            long maxBufferSize$okio = Pipe.this.getMaxBufferSize$okio() - Pipe.this.getBuffer$okio().size();
                            if (maxBufferSize$okio == 0) {
                                this.timeout.waitUntilNotified(Pipe.this.getBuffer$okio());
                            } else {
                                long min = Math.min(maxBufferSize$okio, j2);
                                Pipe.this.getBuffer$okio().write(source, min);
                                j2 -= min;
                                Buffer buffer$okio = Pipe.this.getBuffer$okio();
                                if (buffer$okio == null) {
                                    throw new TypeCastException("null cannot be cast to non-null type java.lang.Object");
                                }
                                buffer$okio.notifyAll();
                            }
                        }
                    }
                    Unit unit = Unit.INSTANCE;
                }
                if (sink != null) {
                    Pipe pipe = Pipe.this;
                    Timeout timeout = sink.timeout();
                    Timeout timeout2 = pipe.sink().timeout();
                    long timeoutNanos = timeout.timeoutNanos();
                    timeout.timeout(Timeout.Companion.minTimeout(timeout2.timeoutNanos(), timeout.timeoutNanos()), TimeUnit.NANOSECONDS);
                    if (timeout.hasDeadline()) {
                        long deadlineNanoTime = timeout.deadlineNanoTime();
                        if (timeout2.hasDeadline()) {
                            timeout.deadlineNanoTime(Math.min(timeout.deadlineNanoTime(), timeout2.deadlineNanoTime()));
                        }
                        try {
                            sink.write(source, j2);
                            if (hasDeadline) {
                                return;
                            }
                            return;
                        } finally {
                            timeout.timeout(timeoutNanos, TimeUnit.NANOSECONDS);
                            if (timeout2.hasDeadline()) {
                                timeout.deadlineNanoTime(deadlineNanoTime);
                            }
                        }
                    }
                    if (timeout2.hasDeadline()) {
                        timeout.deadlineNanoTime(timeout2.deadlineNanoTime());
                    }
                    try {
                        sink.write(source, j2);
                    } finally {
                        timeout.timeout(timeoutNanos, TimeUnit.NANOSECONDS);
                        if (timeout2.hasDeadline()) {
                            timeout.clearDeadline();
                        }
                    }
                }
            }

            @Override // okio.Sink, java.io.Flushable
            public void flush() {
                boolean hasDeadline;
                Sink sink = null;
                synchronized (Pipe.this.getBuffer$okio()) {
                    if (!(!Pipe.this.getSinkClosed$okio())) {
                        throw new IllegalStateException("closed".toString());
                    }
                    Sink foldedSink$okio = Pipe.this.getFoldedSink$okio();
                    if (foldedSink$okio != null) {
                        sink = foldedSink$okio;
                    } else if (Pipe.this.getSourceClosed$okio() && Pipe.this.getBuffer$okio().size() > 0) {
                        throw new IOException("source is closed");
                    }
                    Unit unit = Unit.INSTANCE;
                }
                if (sink != null) {
                    Pipe pipe = Pipe.this;
                    Timeout timeout = sink.timeout();
                    Timeout timeout2 = pipe.sink().timeout();
                    long timeoutNanos = timeout.timeoutNanos();
                    timeout.timeout(Timeout.Companion.minTimeout(timeout2.timeoutNanos(), timeout.timeoutNanos()), TimeUnit.NANOSECONDS);
                    if (timeout.hasDeadline()) {
                        long deadlineNanoTime = timeout.deadlineNanoTime();
                        if (timeout2.hasDeadline()) {
                            timeout.deadlineNanoTime(Math.min(timeout.deadlineNanoTime(), timeout2.deadlineNanoTime()));
                        }
                        try {
                            sink.flush();
                            if (hasDeadline) {
                                return;
                            }
                            return;
                        } finally {
                            timeout.timeout(timeoutNanos, TimeUnit.NANOSECONDS);
                            if (timeout2.hasDeadline()) {
                                timeout.deadlineNanoTime(deadlineNanoTime);
                            }
                        }
                    }
                    if (timeout2.hasDeadline()) {
                        timeout.deadlineNanoTime(timeout2.deadlineNanoTime());
                    }
                    try {
                        sink.flush();
                    } finally {
                        timeout.timeout(timeoutNanos, TimeUnit.NANOSECONDS);
                        if (timeout2.hasDeadline()) {
                            timeout.clearDeadline();
                        }
                    }
                }
            }

            @Override // okio.Sink, java.io.Closeable, java.lang.AutoCloseable
            public void close() {
                boolean hasDeadline;
                Sink sink = null;
                synchronized (Pipe.this.getBuffer$okio()) {
                    if (Pipe.this.getSinkClosed$okio()) {
                        return;
                    }
                    Sink foldedSink$okio = Pipe.this.getFoldedSink$okio();
                    if (foldedSink$okio != null) {
                        sink = foldedSink$okio;
                    } else {
                        if (Pipe.this.getSourceClosed$okio() && Pipe.this.getBuffer$okio().size() > 0) {
                            throw new IOException("source is closed");
                        }
                        Pipe.this.setSinkClosed$okio(true);
                        Buffer buffer$okio = Pipe.this.getBuffer$okio();
                        if (buffer$okio == null) {
                            throw new TypeCastException("null cannot be cast to non-null type java.lang.Object");
                        }
                        buffer$okio.notifyAll();
                    }
                    Unit unit = Unit.INSTANCE;
                    if (sink != null) {
                        Pipe pipe = Pipe.this;
                        Timeout timeout = sink.timeout();
                        Timeout timeout2 = pipe.sink().timeout();
                        long timeoutNanos = timeout.timeoutNanos();
                        timeout.timeout(Timeout.Companion.minTimeout(timeout2.timeoutNanos(), timeout.timeoutNanos()), TimeUnit.NANOSECONDS);
                        if (timeout.hasDeadline()) {
                            long deadlineNanoTime = timeout.deadlineNanoTime();
                            if (timeout2.hasDeadline()) {
                                timeout.deadlineNanoTime(Math.min(timeout.deadlineNanoTime(), timeout2.deadlineNanoTime()));
                            }
                            try {
                                sink.close();
                                if (hasDeadline) {
                                    return;
                                }
                                return;
                            } finally {
                                timeout.timeout(timeoutNanos, TimeUnit.NANOSECONDS);
                                if (timeout2.hasDeadline()) {
                                    timeout.deadlineNanoTime(deadlineNanoTime);
                                }
                            }
                        }
                        if (timeout2.hasDeadline()) {
                            timeout.deadlineNanoTime(timeout2.deadlineNanoTime());
                        }
                        try {
                            sink.close();
                        } finally {
                            timeout.timeout(timeoutNanos, TimeUnit.NANOSECONDS);
                            if (timeout2.hasDeadline()) {
                                timeout.clearDeadline();
                            }
                        }
                    }
                }
            }

            @Override // okio.Sink
            @NotNull
            public Timeout timeout() {
                return this.timeout;
            }
        };
        this.source = new Source() { // from class: okio.Pipe$source$1
            private final Timeout timeout = new Timeout();

            @Override // okio.Source
            public long read(@NotNull Buffer sink, long j2) {
                Intrinsics.checkParameterIsNotNull(sink, "sink");
                synchronized (Pipe.this.getBuffer$okio()) {
                    if (!(!Pipe.this.getSourceClosed$okio())) {
                        throw new IllegalStateException("closed".toString());
                    }
                    while (Pipe.this.getBuffer$okio().size() == 0) {
                        if (Pipe.this.getSinkClosed$okio()) {
                            return -1L;
                        }
                        this.timeout.waitUntilNotified(Pipe.this.getBuffer$okio());
                    }
                    long read = Pipe.this.getBuffer$okio().read(sink, j2);
                    Buffer buffer$okio = Pipe.this.getBuffer$okio();
                    if (buffer$okio != null) {
                        buffer$okio.notifyAll();
                        return read;
                    }
                    throw new TypeCastException("null cannot be cast to non-null type java.lang.Object");
                }
            }

            @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
            public void close() {
                synchronized (Pipe.this.getBuffer$okio()) {
                    Pipe.this.setSourceClosed$okio(true);
                    Buffer buffer$okio = Pipe.this.getBuffer$okio();
                    if (buffer$okio == null) {
                        throw new TypeCastException("null cannot be cast to non-null type java.lang.Object");
                    }
                    buffer$okio.notifyAll();
                    Unit unit = Unit.INSTANCE;
                }
            }

            @Override // okio.Source
            @NotNull
            public Timeout timeout() {
                return this.timeout;
            }
        };
    }

    public final long getMaxBufferSize$okio() {
        return this.maxBufferSize;
    }

    @NotNull
    public final Buffer getBuffer$okio() {
        return this.buffer;
    }

    public final boolean getSinkClosed$okio() {
        return this.sinkClosed;
    }

    public final void setSinkClosed$okio(boolean z) {
        this.sinkClosed = z;
    }

    public final boolean getSourceClosed$okio() {
        return this.sourceClosed;
    }

    public final void setSourceClosed$okio(boolean z) {
        this.sourceClosed = z;
    }

    @Nullable
    public final Sink getFoldedSink$okio() {
        return this.foldedSink;
    }

    public final void setFoldedSink$okio(@Nullable Sink sink) {
        this.foldedSink = sink;
    }

    @JvmName(name = "sink")
    @NotNull
    public final Sink sink() {
        return this.sink;
    }

    @JvmName(name = "source")
    @NotNull
    public final Source source() {
        return this.source;
    }

    public final void fold(@NotNull Sink sink) throws IOException {
        boolean z;
        Buffer buffer;
        Intrinsics.checkParameterIsNotNull(sink, "sink");
        while (true) {
            synchronized (this.buffer) {
                if (!(this.foldedSink == null)) {
                    throw new IllegalStateException("sink already folded".toString());
                }
                if (this.buffer.exhausted()) {
                    this.sourceClosed = true;
                    this.foldedSink = sink;
                    return;
                }
                z = this.sinkClosed;
                buffer = new Buffer();
                buffer.write(this.buffer, this.buffer.size());
                Buffer buffer2 = this.buffer;
                if (buffer2 == null) {
                    throw new TypeCastException("null cannot be cast to non-null type java.lang.Object");
                }
                buffer2.notifyAll();
                Unit unit = Unit.INSTANCE;
            }
            try {
                sink.write(buffer, buffer.size());
                if (z) {
                    sink.close();
                } else {
                    sink.flush();
                }
            } catch (Throwable th) {
                synchronized (this.buffer) {
                    this.sourceClosed = true;
                    Buffer buffer3 = this.buffer;
                    if (buffer3 == null) {
                        throw new TypeCastException("null cannot be cast to non-null type java.lang.Object");
                    }
                    buffer3.notifyAll();
                    Unit unit2 = Unit.INSTANCE;
                    throw th;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void forward(@NotNull Sink sink, Function1<? super Sink, Unit> function1) {
        Timeout timeout = sink.timeout();
        Timeout timeout2 = sink().timeout();
        long timeoutNanos = timeout.timeoutNanos();
        timeout.timeout(Timeout.Companion.minTimeout(timeout2.timeoutNanos(), timeout.timeoutNanos()), TimeUnit.NANOSECONDS);
        if (timeout.hasDeadline()) {
            long deadlineNanoTime = timeout.deadlineNanoTime();
            if (timeout2.hasDeadline()) {
                timeout.deadlineNanoTime(Math.min(timeout.deadlineNanoTime(), timeout2.deadlineNanoTime()));
            }
            try {
                function1.invoke(sink);
                return;
            } finally {
                InlineMarker.finallyStart(1);
                timeout.timeout(timeoutNanos, TimeUnit.NANOSECONDS);
                if (timeout2.hasDeadline()) {
                    timeout.deadlineNanoTime(deadlineNanoTime);
                }
                InlineMarker.finallyEnd(1);
            }
        }
        if (timeout2.hasDeadline()) {
            timeout.deadlineNanoTime(timeout2.deadlineNanoTime());
        }
        try {
            function1.invoke(sink);
        } finally {
            InlineMarker.finallyStart(1);
            timeout.timeout(timeoutNanos, TimeUnit.NANOSECONDS);
            if (timeout2.hasDeadline()) {
                timeout.clearDeadline();
            }
            InlineMarker.finallyEnd(1);
        }
    }

    @Annotations(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "sink", imports = {}))
    @JvmName(name = "-deprecated_sink")
    @NotNull
    /* renamed from: -deprecated_sink  reason: not valid java name */
    public final Sink m25646deprecated_sink() {
        return this.sink;
    }

    @Annotations(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "source", imports = {}))
    @JvmName(name = "-deprecated_source")
    @NotNull
    /* renamed from: -deprecated_source  reason: not valid java name */
    public final Source m25647deprecated_source() {
        return this.source;
    }
}
