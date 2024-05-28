package kotlin.p401io;

import java.io.InputStream;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Functions;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
@Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u0000n\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0002\u0010\u0005\n\u0002\u0010\f\n\u0002\u0010\u0019\n\u0002\u0010\u0006\n\u0002\u0010\u0007\n\u0002\u0010\t\n\u0002\u0010\n\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0087\b\u001a\u0011\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\rH\u0087\b\u001a\u0011\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u000eH\u0087\b\u001a\u0011\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u000fH\u0087\b\u001a\u0011\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0010H\u0087\b\u001a\u0011\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0011H\u0087\b\u001a\u0011\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0012H\u0087\b\u001a\u0011\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0001H\u0087\b\u001a\u0011\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0013H\u0087\b\u001a\u0011\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0014H\u0087\b\u001a\t\u0010\u0015\u001a\u00020\nH\u0087\b\u001a\u0013\u0010\u0015\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0087\b\u001a\u0011\u0010\u0015\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\rH\u0087\b\u001a\u0011\u0010\u0015\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u000eH\u0087\b\u001a\u0011\u0010\u0015\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u000fH\u0087\b\u001a\u0011\u0010\u0015\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0010H\u0087\b\u001a\u0011\u0010\u0015\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0011H\u0087\b\u001a\u0011\u0010\u0015\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0012H\u0087\b\u001a\u0011\u0010\u0015\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0001H\u0087\b\u001a\u0011\u0010\u0015\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0013H\u0087\b\u001a\u0011\u0010\u0015\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0014H\u0087\b\u001a\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017\u001a\u001a\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0003\u001a\u00020\u0004H\u0000\u001a\f\u0010\u001a\u001a\u00020\r*\u00020\u001bH\u0002\u001a\f\u0010\u001c\u001a\u00020\n*\u00020\u001dH\u0002\u001a\u0018\u0010\u001e\u001a\u00020\n*\u00020\u001b2\n\u0010\u001f\u001a\u00060 j\u0002`!H\u0002\u001a$\u0010\"\u001a\u00020\r*\u00020\u00042\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\u001b2\u0006\u0010&\u001a\u00020\rH\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006¨\u0006'"}, m1890d2 = {"BUFFER_SIZE", "", "LINE_SEPARATOR_MAX_LENGTH", "decoder", "Ljava/nio/charset/CharsetDecoder;", "getDecoder", "()Ljava/nio/charset/CharsetDecoder;", "decoder$delegate", "Lkotlin/Lazy;", "print", "", "message", "", "", "", "", "", "", "", "", "", "println", "readLine", "", "inputStream", "Ljava/io/InputStream;", "endsWithLineSeparator", "Ljava/nio/CharBuffer;", "flipBack", "Ljava/nio/Buffer;", "offloadPrefixTo", "builder", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "tryDecode", "byteBuffer", "Ljava/nio/ByteBuffer;", "charBuffer", "isEndOfStream", "kotlin-stdlib"}, m1889k = 2, m1888mv = {1, 1, 16})
@JvmName(name = "ConsoleKt")
/* renamed from: kotlin.io.ConsoleKt */
/* loaded from: E:\11617560_dexfile_execute.dex */
public final class Console {
    private static final int BUFFER_SIZE = 32;
    private static final int LINE_SEPARATOR_MAX_LENGTH = 2;
    private static final Lazy decoder$delegate = LazyKt.lazy(new Functions<CharsetDecoder>() { // from class: kotlin.io.ConsoleKt$decoder$2
        @Override // kotlin.jvm.functions.Functions
        public final CharsetDecoder invoke() {
            return Charset.defaultCharset().newDecoder();
        }
    });

    private static final CharsetDecoder getDecoder() {
        return (CharsetDecoder) decoder$delegate.getValue();
    }

    @InlineOnly
    private static final void print(Object obj) {
        System.out.print(obj);
    }

    @InlineOnly
    private static final void print(int i) {
        System.out.print(i);
    }

    @InlineOnly
    private static final void print(long j) {
        System.out.print(j);
    }

    @InlineOnly
    private static final void print(byte b) {
        System.out.print(Byte.valueOf(b));
    }

    @InlineOnly
    private static final void print(short s) {
        System.out.print(Short.valueOf(s));
    }

    @InlineOnly
    private static final void print(char c) {
        System.out.print(c);
    }

    @InlineOnly
    private static final void print(boolean z) {
        System.out.print(z);
    }

    @InlineOnly
    private static final void print(float f) {
        System.out.print(f);
    }

    @InlineOnly
    private static final void print(double d) {
        System.out.print(d);
    }

    @InlineOnly
    private static final void print(char[] cArr) {
        System.out.print(cArr);
    }

    @InlineOnly
    private static final void println(Object obj) {
        System.out.println(obj);
    }

    @InlineOnly
    private static final void println(int i) {
        System.out.println(i);
    }

    @InlineOnly
    private static final void println(long j) {
        System.out.println(j);
    }

    @InlineOnly
    private static final void println(byte b) {
        System.out.println(Byte.valueOf(b));
    }

    @InlineOnly
    private static final void println(short s) {
        System.out.println(Short.valueOf(s));
    }

    @InlineOnly
    private static final void println(char c) {
        System.out.println(c);
    }

    @InlineOnly
    private static final void println(boolean z) {
        System.out.println(z);
    }

    @InlineOnly
    private static final void println(float f) {
        System.out.println(f);
    }

    @InlineOnly
    private static final void println(double d) {
        System.out.println(d);
    }

    @InlineOnly
    private static final void println(char[] cArr) {
        System.out.println(cArr);
    }

    @InlineOnly
    private static final void println() {
        System.out.println();
    }

    @Nullable
    public static final String readLine() {
        InputStream inputStream = System.in;
        Intrinsics.checkExpressionValueIsNotNull(inputStream, "System.`in`");
        return readLine(inputStream, getDecoder());
    }

    @Nullable
    public static final String readLine(@NotNull InputStream inputStream, @NotNull CharsetDecoder decoder) {
        Intrinsics.checkParameterIsNotNull(inputStream, "inputStream");
        Intrinsics.checkParameterIsNotNull(decoder, "decoder");
        if (!(decoder.maxCharsPerByte() <= ((float) 1))) {
            throw new IllegalArgumentException("Encodings with multiple chars per byte are not supported".toString());
        }
        ByteBuffer byteBuffer = ByteBuffer.allocate(32);
        CharBuffer charBuffer = CharBuffer.allocate(4);
        StringBuilder sb = new StringBuilder();
        int read = inputStream.read();
        if (read == -1) {
            return null;
        }
        do {
            byteBuffer.put((byte) read);
            Intrinsics.checkExpressionValueIsNotNull(byteBuffer, "byteBuffer");
            Intrinsics.checkExpressionValueIsNotNull(charBuffer, "charBuffer");
            if (tryDecode(decoder, byteBuffer, charBuffer, false)) {
                if (endsWithLineSeparator(charBuffer)) {
                    break;
                } else if (charBuffer.remaining() < 2) {
                    offloadPrefixTo(charBuffer, sb);
                }
            }
            read = inputStream.read();
        } while (read != -1);
        tryDecode(decoder, byteBuffer, charBuffer, true);
        decoder.reset();
        int position = charBuffer.position();
        if (position > 0 && charBuffer.get(position - 1) == '\n' && position - 1 > 0 && charBuffer.get(position - 1) == '\r') {
            position--;
        }
        charBuffer.flip();
        for (int i = 0; i < position; i++) {
            sb.append(charBuffer.get());
        }
        return sb.toString();
    }

    private static final boolean tryDecode(@NotNull CharsetDecoder charsetDecoder, ByteBuffer byteBuffer, CharBuffer charBuffer, boolean z) {
        int position = charBuffer.position();
        byteBuffer.flip();
        CoderResult decode = charsetDecoder.decode(byteBuffer, charBuffer, z);
        if (decode.isError()) {
            decode.throwException();
        }
        boolean z2 = charBuffer.position() > position;
        if (z2) {
            byteBuffer.clear();
        } else {
            flipBack(byteBuffer);
        }
        return z2;
    }

    private static final boolean endsWithLineSeparator(@NotNull CharBuffer charBuffer) {
        int position = charBuffer.position();
        return position > 0 && charBuffer.get(position - 1) == '\n';
    }

    private static final void flipBack(@NotNull Buffer buffer) {
        buffer.position(buffer.limit());
        buffer.limit(buffer.capacity());
    }

    private static final void offloadPrefixTo(@NotNull CharBuffer charBuffer, StringBuilder sb) {
        charBuffer.flip();
        int limit = charBuffer.limit() - 1;
        for (int i = 0; i < limit; i++) {
            sb.append(charBuffer.get());
        }
        charBuffer.compact();
    }
}
