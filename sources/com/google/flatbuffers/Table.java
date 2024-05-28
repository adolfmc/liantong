package com.google.flatbuffers;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;
import java.util.Arrays;
import java.util.Comparator;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class Table {

    /* renamed from: bb */
    protected ByteBuffer f10465bb;
    protected int bb_pos;
    private static final ThreadLocal<CharsetDecoder> UTF8_DECODER = new ThreadLocal<CharsetDecoder>() { // from class: com.google.flatbuffers.Table.1
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        public CharsetDecoder initialValue() {
            return Charset.forName("UTF-8").newDecoder();
        }
    };
    public static final ThreadLocal<Charset> UTF8_CHARSET = new ThreadLocal<Charset>() { // from class: com.google.flatbuffers.Table.2
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        public Charset initialValue() {
            return Charset.forName("UTF-8");
        }
    };
    private static final ThreadLocal<CharBuffer> CHAR_BUFFER = new ThreadLocal<>();

    protected int keysCompare(Integer num, Integer num2, ByteBuffer byteBuffer) {
        return 0;
    }

    public ByteBuffer getByteBuffer() {
        return this.f10465bb;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int __offset(int i) {
        int i2 = this.bb_pos;
        int i3 = i2 - this.f10465bb.getInt(i2);
        if (i < this.f10465bb.getShort(i3)) {
            return this.f10465bb.getShort(i3 + i);
        }
        return 0;
    }

    protected static int __offset(int i, int i2, ByteBuffer byteBuffer) {
        int capacity = byteBuffer.capacity() - i2;
        return byteBuffer.getShort((i + capacity) - byteBuffer.getInt(capacity)) + capacity;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int __indirect(int i) {
        return i + this.f10465bb.getInt(i);
    }

    protected static int __indirect(int i, ByteBuffer byteBuffer) {
        return i + byteBuffer.getInt(i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String __string(int i) {
        CharsetDecoder charsetDecoder = UTF8_DECODER.get();
        charsetDecoder.reset();
        int i2 = i + this.f10465bb.getInt(i);
        ByteBuffer order = this.f10465bb.duplicate().order(ByteOrder.LITTLE_ENDIAN);
        int i3 = order.getInt(i2);
        int i4 = i2 + 4;
        order.position(i4);
        order.limit(i4 + i3);
        int maxCharsPerByte = (int) (i3 * charsetDecoder.maxCharsPerByte());
        CharBuffer charBuffer = CHAR_BUFFER.get();
        if (charBuffer == null || charBuffer.capacity() < maxCharsPerByte) {
            charBuffer = CharBuffer.allocate(maxCharsPerByte);
            CHAR_BUFFER.set(charBuffer);
        }
        charBuffer.clear();
        try {
            CoderResult decode = charsetDecoder.decode(order, charBuffer, true);
            if (!decode.isUnderflow()) {
                decode.throwException();
            }
            return charBuffer.flip().toString();
        } catch (CharacterCodingException e) {
            throw new RuntimeException(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int __vector_len(int i) {
        int i2 = i + this.bb_pos;
        return this.f10465bb.getInt(i2 + this.f10465bb.getInt(i2));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int __vector(int i) {
        int i2 = i + this.bb_pos;
        return i2 + this.f10465bb.getInt(i2) + 4;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ByteBuffer __vector_as_bytebuffer(int i, int i2) {
        int __offset = __offset(i);
        if (__offset == 0) {
            return null;
        }
        ByteBuffer order = this.f10465bb.duplicate().order(ByteOrder.LITTLE_ENDIAN);
        int __vector = __vector(__offset);
        order.position(__vector);
        order.limit(__vector + (__vector_len(__offset) * i2));
        return order;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ByteBuffer __vector_in_bytebuffer(ByteBuffer byteBuffer, int i, int i2) {
        int __offset = __offset(i);
        if (__offset == 0) {
            return null;
        }
        int __vector = __vector(__offset);
        byteBuffer.rewind();
        byteBuffer.limit((__vector_len(__offset) * i2) + __vector);
        byteBuffer.position(__vector);
        return byteBuffer;
    }

    protected Table __union(Table table, int i) {
        int i2 = i + this.bb_pos;
        table.bb_pos = i2 + this.f10465bb.getInt(i2);
        table.f10465bb = this.f10465bb;
        return table;
    }

    protected static boolean __has_identifier(ByteBuffer byteBuffer, String str) {
        if (str.length() == 4) {
            for (int i = 0; i < 4; i++) {
                if (str.charAt(i) != ((char) byteBuffer.get(byteBuffer.position() + 4 + i))) {
                    return false;
                }
            }
            return true;
        }
        throw new AssertionError("FlatBuffers: file identifier must be length 4");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void sortTables(int[] iArr, final ByteBuffer byteBuffer) {
        Integer[] numArr = new Integer[iArr.length];
        for (int i = 0; i < iArr.length; i++) {
            numArr[i] = Integer.valueOf(iArr[i]);
        }
        Arrays.sort(numArr, new Comparator<Integer>() { // from class: com.google.flatbuffers.Table.3
            @Override // java.util.Comparator
            public int compare(Integer num, Integer num2) {
                return Table.this.keysCompare(num, num2, byteBuffer);
            }
        });
        for (int i2 = 0; i2 < iArr.length; i2++) {
            iArr[i2] = numArr[i2].intValue();
        }
    }

    protected static int compareStrings(int i, int i2, ByteBuffer byteBuffer) {
        int i3 = i + byteBuffer.getInt(i);
        int i4 = i2 + byteBuffer.getInt(i2);
        int i5 = byteBuffer.getInt(i3);
        int i6 = byteBuffer.getInt(i4);
        int i7 = i3 + 4;
        int i8 = i4 + 4;
        int min = Math.min(i5, i6);
        for (int i9 = 0; i9 < min; i9++) {
            int i10 = i9 + i7;
            int i11 = i9 + i8;
            if (byteBuffer.get(i10) != byteBuffer.get(i11)) {
                return byteBuffer.get(i10) - byteBuffer.get(i11);
            }
        }
        return i5 - i6;
    }

    protected static int compareStrings(int i, byte[] bArr, ByteBuffer byteBuffer) {
        int i2 = i + byteBuffer.getInt(i);
        int i3 = byteBuffer.getInt(i2);
        int length = bArr.length;
        int i4 = i2 + 4;
        int min = Math.min(i3, length);
        for (int i5 = 0; i5 < min; i5++) {
            int i6 = i5 + i4;
            if (byteBuffer.get(i6) != bArr[i5]) {
                return byteBuffer.get(i6) - bArr[i5];
            }
        }
        return i3 - length;
    }
}
