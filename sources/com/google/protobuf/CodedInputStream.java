package com.google.protobuf;

import com.google.protobuf.MessageLite;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public abstract class CodedInputStream {
    private static final int DEFAULT_BUFFER_SIZE = 4096;
    private static final int DEFAULT_RECURSION_LIMIT = 100;
    private static final int DEFAULT_SIZE_LIMIT = Integer.MAX_VALUE;
    int recursionDepth;
    int recursionLimit;
    int sizeLimit;

    public static int decodeZigZag32(int i) {
        return (-(i & 1)) ^ (i >>> 1);
    }

    public static long decodeZigZag64(long j) {
        return (-(j & 1)) ^ (j >>> 1);
    }

    public abstract void checkLastTagWas(int i) throws InvalidProtocolBufferException;

    public abstract void enableAliasing(boolean z);

    public abstract int getBytesUntilLimit();

    public abstract int getLastTag();

    public abstract int getTotalBytesRead();

    public abstract boolean isAtEnd() throws IOException;

    public abstract void popLimit(int i);

    public abstract int pushLimit(int i) throws InvalidProtocolBufferException;

    public abstract boolean readBool() throws IOException;

    public abstract byte[] readByteArray() throws IOException;

    public abstract ByteBuffer readByteBuffer() throws IOException;

    public abstract ByteString readBytes() throws IOException;

    public abstract double readDouble() throws IOException;

    public abstract int readEnum() throws IOException;

    public abstract int readFixed32() throws IOException;

    public abstract long readFixed64() throws IOException;

    public abstract float readFloat() throws IOException;

    public abstract <T extends MessageLite> T readGroup(int i, Parser<T> parser, ExtensionRegistryLite extensionRegistryLite) throws IOException;

    public abstract void readGroup(int i, MessageLite.Builder builder, ExtensionRegistryLite extensionRegistryLite) throws IOException;

    public abstract int readInt32() throws IOException;

    public abstract long readInt64() throws IOException;

    public abstract <T extends MessageLite> T readMessage(Parser<T> parser, ExtensionRegistryLite extensionRegistryLite) throws IOException;

    public abstract void readMessage(MessageLite.Builder builder, ExtensionRegistryLite extensionRegistryLite) throws IOException;

    public abstract byte readRawByte() throws IOException;

    public abstract byte[] readRawBytes(int i) throws IOException;

    public abstract int readRawLittleEndian32() throws IOException;

    public abstract long readRawLittleEndian64() throws IOException;

    public abstract int readRawVarint32() throws IOException;

    public abstract long readRawVarint64() throws IOException;

    abstract long readRawVarint64SlowPath() throws IOException;

    public abstract int readSFixed32() throws IOException;

    public abstract long readSFixed64() throws IOException;

    public abstract int readSInt32() throws IOException;

    public abstract long readSInt64() throws IOException;

    public abstract String readString() throws IOException;

    public abstract String readStringRequireUtf8() throws IOException;

    public abstract int readTag() throws IOException;

    public abstract int readUInt32() throws IOException;

    public abstract long readUInt64() throws IOException;

    @Deprecated
    public abstract void readUnknownGroup(int i, MessageLite.Builder builder) throws IOException;

    public abstract void resetSizeCounter();

    public abstract boolean skipField(int i) throws IOException;

    @Deprecated
    public abstract boolean skipField(int i, CodedOutputStream codedOutputStream) throws IOException;

    public abstract void skipMessage() throws IOException;

    public abstract void skipMessage(CodedOutputStream codedOutputStream) throws IOException;

    public abstract void skipRawBytes(int i) throws IOException;

    public static CodedInputStream newInstance(InputStream inputStream) {
        return newInstance(inputStream, 4096);
    }

    static CodedInputStream newInstance(InputStream inputStream, int i) {
        if (inputStream == null) {
            return newInstance(Internal.EMPTY_BYTE_ARRAY);
        }
        return new StreamDecoder(inputStream, i);
    }

    public static CodedInputStream newInstance(byte[] bArr) {
        return newInstance(bArr, 0, bArr.length);
    }

    public static CodedInputStream newInstance(byte[] bArr, int i, int i2) {
        return newInstance(bArr, i, i2, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static CodedInputStream newInstance(byte[] bArr, int i, int i2, boolean z) {
        ArrayDecoder arrayDecoder = new ArrayDecoder(bArr, i, i2, z);
        try {
            arrayDecoder.pushLimit(i2);
            return arrayDecoder;
        } catch (InvalidProtocolBufferException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static CodedInputStream newInstance(ByteBuffer byteBuffer) {
        return newInstance(byteBuffer, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static CodedInputStream newInstance(ByteBuffer byteBuffer, boolean z) {
        if (byteBuffer.hasArray()) {
            return newInstance(byteBuffer.array(), byteBuffer.arrayOffset() + byteBuffer.position(), byteBuffer.remaining(), z);
        }
        if (byteBuffer.isDirect() && UnsafeDirectNioDecoder.isSupported()) {
            return new UnsafeDirectNioDecoder(byteBuffer, z);
        }
        byte[] bArr = new byte[byteBuffer.remaining()];
        byteBuffer.duplicate().get(bArr);
        return newInstance(bArr, 0, bArr.length, true);
    }

    private CodedInputStream() {
        this.recursionLimit = 100;
        this.sizeLimit = Integer.MAX_VALUE;
    }

    public final int setRecursionLimit(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("Recursion limit cannot be negative: " + i);
        }
        int i2 = this.recursionLimit;
        this.recursionLimit = i;
        return i2;
    }

    public final int setSizeLimit(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("Size limit cannot be negative: " + i);
        }
        int i2 = this.sizeLimit;
        this.sizeLimit = i;
        return i2;
    }

    public static int readRawVarint32(int i, InputStream inputStream) throws IOException {
        if ((i & 128) == 0) {
            return i;
        }
        int i2 = i & 127;
        int i3 = 7;
        while (i3 < 32) {
            int read = inputStream.read();
            if (read == -1) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            i2 |= (read & 127) << i3;
            if ((read & 128) == 0) {
                return i2;
            }
            i3 += 7;
        }
        while (i3 < 64) {
            int read2 = inputStream.read();
            if (read2 == -1) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            if ((read2 & 128) == 0) {
                return i2;
            }
            i3 += 7;
        }
        throw InvalidProtocolBufferException.malformedVarint();
    }

    static int readRawVarint32(InputStream inputStream) throws IOException {
        int read = inputStream.read();
        if (read == -1) {
            throw InvalidProtocolBufferException.truncatedMessage();
        }
        return readRawVarint32(read, inputStream);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static final class ArrayDecoder extends CodedInputStream {
        private final byte[] buffer;
        private int bufferSizeAfterLimit;
        private int currentLimit;
        private boolean enableAliasing;
        private final boolean immutable;
        private int lastTag;
        private int limit;
        private int pos;
        private int startPos;

        private ArrayDecoder(byte[] bArr, int i, int i2, boolean z) {
            super();
            this.currentLimit = Integer.MAX_VALUE;
            this.buffer = bArr;
            this.limit = i2 + i;
            this.pos = i;
            this.startPos = this.pos;
            this.immutable = z;
        }

        @Override // com.google.protobuf.CodedInputStream
        public int readTag() throws IOException {
            if (isAtEnd()) {
                this.lastTag = 0;
                return 0;
            }
            this.lastTag = readRawVarint32();
            if (WireFormat.getTagFieldNumber(this.lastTag) == 0) {
                throw InvalidProtocolBufferException.invalidTag();
            }
            return this.lastTag;
        }

        @Override // com.google.protobuf.CodedInputStream
        public void checkLastTagWas(int i) throws InvalidProtocolBufferException {
            if (this.lastTag != i) {
                throw InvalidProtocolBufferException.invalidEndTag();
            }
        }

        @Override // com.google.protobuf.CodedInputStream
        public int getLastTag() {
            return this.lastTag;
        }

        @Override // com.google.protobuf.CodedInputStream
        public boolean skipField(int i) throws IOException {
            switch (WireFormat.getTagWireType(i)) {
                case 0:
                    skipRawVarint();
                    return true;
                case 1:
                    skipRawBytes(8);
                    return true;
                case 2:
                    skipRawBytes(readRawVarint32());
                    return true;
                case 3:
                    skipMessage();
                    checkLastTagWas(WireFormat.makeTag(WireFormat.getTagFieldNumber(i), 4));
                    return true;
                case 4:
                    return false;
                case 5:
                    skipRawBytes(4);
                    return true;
                default:
                    throw InvalidProtocolBufferException.invalidWireType();
            }
        }

        @Override // com.google.protobuf.CodedInputStream
        public boolean skipField(int i, CodedOutputStream codedOutputStream) throws IOException {
            switch (WireFormat.getTagWireType(i)) {
                case 0:
                    long readInt64 = readInt64();
                    codedOutputStream.writeRawVarint32(i);
                    codedOutputStream.writeUInt64NoTag(readInt64);
                    return true;
                case 1:
                    long readRawLittleEndian64 = readRawLittleEndian64();
                    codedOutputStream.writeRawVarint32(i);
                    codedOutputStream.writeFixed64NoTag(readRawLittleEndian64);
                    return true;
                case 2:
                    ByteString readBytes = readBytes();
                    codedOutputStream.writeRawVarint32(i);
                    codedOutputStream.writeBytesNoTag(readBytes);
                    return true;
                case 3:
                    codedOutputStream.writeRawVarint32(i);
                    skipMessage(codedOutputStream);
                    int makeTag = WireFormat.makeTag(WireFormat.getTagFieldNumber(i), 4);
                    checkLastTagWas(makeTag);
                    codedOutputStream.writeRawVarint32(makeTag);
                    return true;
                case 4:
                    return false;
                case 5:
                    int readRawLittleEndian32 = readRawLittleEndian32();
                    codedOutputStream.writeRawVarint32(i);
                    codedOutputStream.writeFixed32NoTag(readRawLittleEndian32);
                    return true;
                default:
                    throw InvalidProtocolBufferException.invalidWireType();
            }
        }

        @Override // com.google.protobuf.CodedInputStream
        public void skipMessage() throws IOException {
            int readTag;
            do {
                readTag = readTag();
                if (readTag == 0) {
                    return;
                }
            } while (skipField(readTag));
        }

        @Override // com.google.protobuf.CodedInputStream
        public void skipMessage(CodedOutputStream codedOutputStream) throws IOException {
            int readTag;
            do {
                readTag = readTag();
                if (readTag == 0) {
                    return;
                }
            } while (skipField(readTag, codedOutputStream));
        }

        @Override // com.google.protobuf.CodedInputStream
        public double readDouble() throws IOException {
            return Double.longBitsToDouble(readRawLittleEndian64());
        }

        @Override // com.google.protobuf.CodedInputStream
        public float readFloat() throws IOException {
            return Float.intBitsToFloat(readRawLittleEndian32());
        }

        @Override // com.google.protobuf.CodedInputStream
        public long readUInt64() throws IOException {
            return readRawVarint64();
        }

        @Override // com.google.protobuf.CodedInputStream
        public long readInt64() throws IOException {
            return readRawVarint64();
        }

        @Override // com.google.protobuf.CodedInputStream
        public int readInt32() throws IOException {
            return readRawVarint32();
        }

        @Override // com.google.protobuf.CodedInputStream
        public long readFixed64() throws IOException {
            return readRawLittleEndian64();
        }

        @Override // com.google.protobuf.CodedInputStream
        public int readFixed32() throws IOException {
            return readRawLittleEndian32();
        }

        @Override // com.google.protobuf.CodedInputStream
        public boolean readBool() throws IOException {
            return readRawVarint64() != 0;
        }

        @Override // com.google.protobuf.CodedInputStream
        public String readString() throws IOException {
            int readRawVarint32 = readRawVarint32();
            if (readRawVarint32 > 0) {
                int i = this.limit;
                int i2 = this.pos;
                if (readRawVarint32 <= i - i2) {
                    String str = new String(this.buffer, i2, readRawVarint32, Internal.UTF_8);
                    this.pos += readRawVarint32;
                    return str;
                }
            }
            if (readRawVarint32 == 0) {
                return "";
            }
            if (readRawVarint32 < 0) {
                throw InvalidProtocolBufferException.negativeSize();
            }
            throw InvalidProtocolBufferException.truncatedMessage();
        }

        @Override // com.google.protobuf.CodedInputStream
        public String readStringRequireUtf8() throws IOException {
            int readRawVarint32 = readRawVarint32();
            if (readRawVarint32 > 0) {
                int i = this.limit;
                int i2 = this.pos;
                if (readRawVarint32 <= i - i2) {
                    if (!Utf8.isValidUtf8(this.buffer, i2, i2 + readRawVarint32)) {
                        throw InvalidProtocolBufferException.invalidUtf8();
                    }
                    int i3 = this.pos;
                    this.pos = i3 + readRawVarint32;
                    return new String(this.buffer, i3, readRawVarint32, Internal.UTF_8);
                }
            }
            if (readRawVarint32 == 0) {
                return "";
            }
            if (readRawVarint32 <= 0) {
                throw InvalidProtocolBufferException.negativeSize();
            }
            throw InvalidProtocolBufferException.truncatedMessage();
        }

        @Override // com.google.protobuf.CodedInputStream
        public void readGroup(int i, MessageLite.Builder builder, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            if (this.recursionDepth >= this.recursionLimit) {
                throw InvalidProtocolBufferException.recursionLimitExceeded();
            }
            this.recursionDepth++;
            builder.mergeFrom(this, extensionRegistryLite);
            checkLastTagWas(WireFormat.makeTag(i, 4));
            this.recursionDepth--;
        }

        @Override // com.google.protobuf.CodedInputStream
        public <T extends MessageLite> T readGroup(int i, Parser<T> parser, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            if (this.recursionDepth >= this.recursionLimit) {
                throw InvalidProtocolBufferException.recursionLimitExceeded();
            }
            this.recursionDepth++;
            T parsePartialFrom = parser.parsePartialFrom(this, extensionRegistryLite);
            checkLastTagWas(WireFormat.makeTag(i, 4));
            this.recursionDepth--;
            return parsePartialFrom;
        }

        @Override // com.google.protobuf.CodedInputStream
        @Deprecated
        public void readUnknownGroup(int i, MessageLite.Builder builder) throws IOException {
            readGroup(i, builder, ExtensionRegistryLite.getEmptyRegistry());
        }

        @Override // com.google.protobuf.CodedInputStream
        public void readMessage(MessageLite.Builder builder, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            int readRawVarint32 = readRawVarint32();
            if (this.recursionDepth >= this.recursionLimit) {
                throw InvalidProtocolBufferException.recursionLimitExceeded();
            }
            int pushLimit = pushLimit(readRawVarint32);
            this.recursionDepth++;
            builder.mergeFrom(this, extensionRegistryLite);
            checkLastTagWas(0);
            this.recursionDepth--;
            popLimit(pushLimit);
        }

        @Override // com.google.protobuf.CodedInputStream
        public <T extends MessageLite> T readMessage(Parser<T> parser, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            int readRawVarint32 = readRawVarint32();
            if (this.recursionDepth >= this.recursionLimit) {
                throw InvalidProtocolBufferException.recursionLimitExceeded();
            }
            int pushLimit = pushLimit(readRawVarint32);
            this.recursionDepth++;
            T parsePartialFrom = parser.parsePartialFrom(this, extensionRegistryLite);
            checkLastTagWas(0);
            this.recursionDepth--;
            popLimit(pushLimit);
            return parsePartialFrom;
        }

        @Override // com.google.protobuf.CodedInputStream
        public ByteString readBytes() throws IOException {
            ByteString copyFrom;
            int readRawVarint32 = readRawVarint32();
            if (readRawVarint32 > 0) {
                int i = this.limit;
                int i2 = this.pos;
                if (readRawVarint32 <= i - i2) {
                    if (this.immutable && this.enableAliasing) {
                        copyFrom = ByteString.wrap(this.buffer, i2, readRawVarint32);
                    } else {
                        copyFrom = ByteString.copyFrom(this.buffer, this.pos, readRawVarint32);
                    }
                    this.pos += readRawVarint32;
                    return copyFrom;
                }
            }
            if (readRawVarint32 == 0) {
                return ByteString.EMPTY;
            }
            return ByteString.wrap(readRawBytes(readRawVarint32));
        }

        @Override // com.google.protobuf.CodedInputStream
        public byte[] readByteArray() throws IOException {
            return readRawBytes(readRawVarint32());
        }

        @Override // com.google.protobuf.CodedInputStream
        public ByteBuffer readByteBuffer() throws IOException {
            ByteBuffer wrap;
            int readRawVarint32 = readRawVarint32();
            if (readRawVarint32 > 0) {
                int i = this.limit;
                int i2 = this.pos;
                if (readRawVarint32 <= i - i2) {
                    if (!this.immutable && this.enableAliasing) {
                        wrap = ByteBuffer.wrap(this.buffer, i2, readRawVarint32).slice();
                    } else {
                        byte[] bArr = this.buffer;
                        int i3 = this.pos;
                        wrap = ByteBuffer.wrap(Arrays.copyOfRange(bArr, i3, i3 + readRawVarint32));
                    }
                    this.pos += readRawVarint32;
                    return wrap;
                }
            }
            if (readRawVarint32 == 0) {
                return Internal.EMPTY_BYTE_BUFFER;
            }
            if (readRawVarint32 < 0) {
                throw InvalidProtocolBufferException.negativeSize();
            }
            throw InvalidProtocolBufferException.truncatedMessage();
        }

        @Override // com.google.protobuf.CodedInputStream
        public int readUInt32() throws IOException {
            return readRawVarint32();
        }

        @Override // com.google.protobuf.CodedInputStream
        public int readEnum() throws IOException {
            return readRawVarint32();
        }

        @Override // com.google.protobuf.CodedInputStream
        public int readSFixed32() throws IOException {
            return readRawLittleEndian32();
        }

        @Override // com.google.protobuf.CodedInputStream
        public long readSFixed64() throws IOException {
            return readRawLittleEndian64();
        }

        @Override // com.google.protobuf.CodedInputStream
        public int readSInt32() throws IOException {
            return decodeZigZag32(readRawVarint32());
        }

        @Override // com.google.protobuf.CodedInputStream
        public long readSInt64() throws IOException {
            return decodeZigZag64(readRawVarint64());
        }

        /* JADX WARN: Code restructure failed: missing block: B:32:0x0068, code lost:
            if (r2[r3] < 0) goto L34;
         */
        @Override // com.google.protobuf.CodedInputStream
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public int readRawVarint32() throws java.io.IOException {
            /*
                r5 = this;
                int r0 = r5.pos
                int r1 = r5.limit
                if (r1 != r0) goto L7
                goto L6a
            L7:
                byte[] r2 = r5.buffer
                int r3 = r0 + 1
                r0 = r2[r0]
                if (r0 < 0) goto L12
                r5.pos = r3
                return r0
            L12:
                int r1 = r1 - r3
                r4 = 9
                if (r1 >= r4) goto L18
                goto L6a
            L18:
                int r1 = r3 + 1
                r3 = r2[r3]
                int r3 = r3 << 7
                r0 = r0 ^ r3
                if (r0 >= 0) goto L24
                r0 = r0 ^ (-128(0xffffffffffffff80, float:NaN))
                goto L71
            L24:
                int r3 = r1 + 1
                r1 = r2[r1]
                int r1 = r1 << 14
                r0 = r0 ^ r1
                if (r0 < 0) goto L31
                r0 = r0 ^ 16256(0x3f80, float:2.278E-41)
                r1 = r3
                goto L71
            L31:
                int r1 = r3 + 1
                r3 = r2[r3]
                int r3 = r3 << 21
                r0 = r0 ^ r3
                if (r0 >= 0) goto L3f
                r2 = -2080896(0xffffffffffe03f80, float:NaN)
                r0 = r0 ^ r2
                goto L71
            L3f:
                int r3 = r1 + 1
                r1 = r2[r1]
                int r4 = r1 << 28
                r0 = r0 ^ r4
                r4 = 266354560(0xfe03f80, float:2.2112565E-29)
                r0 = r0 ^ r4
                if (r1 >= 0) goto L70
                int r1 = r3 + 1
                r3 = r2[r3]
                if (r3 >= 0) goto L71
                int r3 = r1 + 1
                r1 = r2[r1]
                if (r1 >= 0) goto L70
                int r1 = r3 + 1
                r3 = r2[r3]
                if (r3 >= 0) goto L71
                int r3 = r1 + 1
                r1 = r2[r1]
                if (r1 >= 0) goto L70
                int r1 = r3 + 1
                r2 = r2[r3]
                if (r2 >= 0) goto L71
            L6a:
                long r0 = r5.readRawVarint64SlowPath()
                int r0 = (int) r0
                return r0
            L70:
                r1 = r3
            L71:
                r5.pos = r1
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.CodedInputStream.ArrayDecoder.readRawVarint32():int");
        }

        private void skipRawVarint() throws IOException {
            if (this.limit - this.pos >= 10) {
                skipRawVarintFastPath();
            } else {
                skipRawVarintSlowPath();
            }
        }

        private void skipRawVarintFastPath() throws IOException {
            for (int i = 0; i < 10; i++) {
                byte[] bArr = this.buffer;
                int i2 = this.pos;
                this.pos = i2 + 1;
                if (bArr[i2] >= 0) {
                    return;
                }
            }
            throw InvalidProtocolBufferException.malformedVarint();
        }

        private void skipRawVarintSlowPath() throws IOException {
            for (int i = 0; i < 10; i++) {
                if (readRawByte() >= 0) {
                    return;
                }
            }
            throw InvalidProtocolBufferException.malformedVarint();
        }

        @Override // com.google.protobuf.CodedInputStream
        public long readRawVarint64() throws IOException {
            long j;
            int i = this.pos;
            int i2 = this.limit;
            if (i2 != i) {
                byte[] bArr = this.buffer;
                int i3 = i + 1;
                byte b = bArr[i];
                if (b >= 0) {
                    this.pos = i3;
                    return b;
                } else if (i2 - i3 >= 9) {
                    int i4 = i3 + 1;
                    int i5 = b ^ (bArr[i3] << 7);
                    if (i5 < 0) {
                        j = i5 ^ (-128);
                    } else {
                        int i6 = i4 + 1;
                        int i7 = i5 ^ (bArr[i4] << 14);
                        if (i7 >= 0) {
                            i4 = i6;
                            j = i7 ^ 16256;
                        } else {
                            i4 = i6 + 1;
                            int i8 = i7 ^ (bArr[i6] << 21);
                            if (i8 < 0) {
                                j = i8 ^ (-2080896);
                            } else {
                                long j2 = i8;
                                int i9 = i4 + 1;
                                long j3 = j2 ^ (bArr[i4] << 28);
                                if (j3 >= 0) {
                                    j = j3 ^ 266354560;
                                    i4 = i9;
                                } else {
                                    i4 = i9 + 1;
                                    long j4 = j3 ^ (bArr[i9] << 35);
                                    if (j4 < 0) {
                                        j = j4 ^ (-34093383808L);
                                    } else {
                                        int i10 = i4 + 1;
                                        long j5 = j4 ^ (bArr[i4] << 42);
                                        if (j5 >= 0) {
                                            j = j5 ^ 4363953127296L;
                                            i4 = i10;
                                        } else {
                                            i4 = i10 + 1;
                                            long j6 = j5 ^ (bArr[i10] << 49);
                                            if (j6 < 0) {
                                                j = j6 ^ (-558586000294016L);
                                            } else {
                                                int i11 = i4 + 1;
                                                long j7 = (j6 ^ (bArr[i4] << 56)) ^ 71499008037633920L;
                                                if (j7 < 0) {
                                                    i4 = i11 + 1;
                                                    if (bArr[i11] >= 0) {
                                                        j = j7;
                                                    }
                                                } else {
                                                    i4 = i11;
                                                    j = j7;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    this.pos = i4;
                    return j;
                }
            }
            return readRawVarint64SlowPath();
        }

        @Override // com.google.protobuf.CodedInputStream
        long readRawVarint64SlowPath() throws IOException {
            long j = 0;
            for (int i = 0; i < 64; i += 7) {
                byte readRawByte = readRawByte();
                j |= (readRawByte & Byte.MAX_VALUE) << i;
                if ((readRawByte & 128) == 0) {
                    return j;
                }
            }
            throw InvalidProtocolBufferException.malformedVarint();
        }

        @Override // com.google.protobuf.CodedInputStream
        public int readRawLittleEndian32() throws IOException {
            int i = this.pos;
            if (this.limit - i < 4) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            byte[] bArr = this.buffer;
            this.pos = i + 4;
            return ((bArr[i + 3] & 255) << 24) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16);
        }

        @Override // com.google.protobuf.CodedInputStream
        public long readRawLittleEndian64() throws IOException {
            int i = this.pos;
            if (this.limit - i < 8) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            byte[] bArr = this.buffer;
            this.pos = i + 8;
            return ((bArr[i + 7] & 255) << 56) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16) | ((bArr[i + 3] & 255) << 24) | ((bArr[i + 4] & 255) << 32) | ((bArr[i + 5] & 255) << 40) | ((bArr[i + 6] & 255) << 48);
        }

        @Override // com.google.protobuf.CodedInputStream
        public void enableAliasing(boolean z) {
            this.enableAliasing = z;
        }

        @Override // com.google.protobuf.CodedInputStream
        public void resetSizeCounter() {
            this.startPos = this.pos;
        }

        @Override // com.google.protobuf.CodedInputStream
        public int pushLimit(int i) throws InvalidProtocolBufferException {
            if (i < 0) {
                throw InvalidProtocolBufferException.negativeSize();
            }
            int totalBytesRead = i + getTotalBytesRead();
            int i2 = this.currentLimit;
            if (totalBytesRead > i2) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            this.currentLimit = totalBytesRead;
            recomputeBufferSizeAfterLimit();
            return i2;
        }

        private void recomputeBufferSizeAfterLimit() {
            this.limit += this.bufferSizeAfterLimit;
            int i = this.limit;
            int i2 = i - this.startPos;
            int i3 = this.currentLimit;
            if (i2 > i3) {
                this.bufferSizeAfterLimit = i2 - i3;
                this.limit = i - this.bufferSizeAfterLimit;
                return;
            }
            this.bufferSizeAfterLimit = 0;
        }

        @Override // com.google.protobuf.CodedInputStream
        public void popLimit(int i) {
            this.currentLimit = i;
            recomputeBufferSizeAfterLimit();
        }

        @Override // com.google.protobuf.CodedInputStream
        public int getBytesUntilLimit() {
            int i = this.currentLimit;
            if (i == Integer.MAX_VALUE) {
                return -1;
            }
            return i - getTotalBytesRead();
        }

        @Override // com.google.protobuf.CodedInputStream
        public boolean isAtEnd() throws IOException {
            return this.pos == this.limit;
        }

        @Override // com.google.protobuf.CodedInputStream
        public int getTotalBytesRead() {
            return this.pos - this.startPos;
        }

        @Override // com.google.protobuf.CodedInputStream
        public byte readRawByte() throws IOException {
            int i = this.pos;
            if (i == this.limit) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            byte[] bArr = this.buffer;
            this.pos = i + 1;
            return bArr[i];
        }

        @Override // com.google.protobuf.CodedInputStream
        public byte[] readRawBytes(int i) throws IOException {
            if (i > 0) {
                int i2 = this.limit;
                int i3 = this.pos;
                if (i <= i2 - i3) {
                    this.pos = i + i3;
                    return Arrays.copyOfRange(this.buffer, i3, this.pos);
                }
            }
            if (i <= 0) {
                if (i == 0) {
                    return Internal.EMPTY_BYTE_ARRAY;
                }
                throw InvalidProtocolBufferException.negativeSize();
            }
            throw InvalidProtocolBufferException.truncatedMessage();
        }

        @Override // com.google.protobuf.CodedInputStream
        public void skipRawBytes(int i) throws IOException {
            if (i >= 0) {
                int i2 = this.limit;
                int i3 = this.pos;
                if (i <= i2 - i3) {
                    this.pos = i3 + i;
                    return;
                }
            }
            if (i < 0) {
                throw InvalidProtocolBufferException.negativeSize();
            }
            throw InvalidProtocolBufferException.truncatedMessage();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static final class UnsafeDirectNioDecoder extends CodedInputStream {
        private final long address;
        private final ByteBuffer buffer;
        private int bufferSizeAfterLimit;
        private int currentLimit;
        private boolean enableAliasing;
        private final boolean immutable;
        private int lastTag;
        private long limit;
        private long pos;
        private long startPos;

        static boolean isSupported() {
            return UnsafeUtil.hasUnsafeByteBufferOperations();
        }

        private UnsafeDirectNioDecoder(ByteBuffer byteBuffer, boolean z) {
            super();
            this.currentLimit = Integer.MAX_VALUE;
            this.buffer = byteBuffer;
            this.address = UnsafeUtil.addressOffset(byteBuffer);
            this.limit = this.address + byteBuffer.limit();
            this.pos = this.address + byteBuffer.position();
            this.startPos = this.pos;
            this.immutable = z;
        }

        @Override // com.google.protobuf.CodedInputStream
        public int readTag() throws IOException {
            if (isAtEnd()) {
                this.lastTag = 0;
                return 0;
            }
            this.lastTag = readRawVarint32();
            if (WireFormat.getTagFieldNumber(this.lastTag) == 0) {
                throw InvalidProtocolBufferException.invalidTag();
            }
            return this.lastTag;
        }

        @Override // com.google.protobuf.CodedInputStream
        public void checkLastTagWas(int i) throws InvalidProtocolBufferException {
            if (this.lastTag != i) {
                throw InvalidProtocolBufferException.invalidEndTag();
            }
        }

        @Override // com.google.protobuf.CodedInputStream
        public int getLastTag() {
            return this.lastTag;
        }

        @Override // com.google.protobuf.CodedInputStream
        public boolean skipField(int i) throws IOException {
            switch (WireFormat.getTagWireType(i)) {
                case 0:
                    skipRawVarint();
                    return true;
                case 1:
                    skipRawBytes(8);
                    return true;
                case 2:
                    skipRawBytes(readRawVarint32());
                    return true;
                case 3:
                    skipMessage();
                    checkLastTagWas(WireFormat.makeTag(WireFormat.getTagFieldNumber(i), 4));
                    return true;
                case 4:
                    return false;
                case 5:
                    skipRawBytes(4);
                    return true;
                default:
                    throw InvalidProtocolBufferException.invalidWireType();
            }
        }

        @Override // com.google.protobuf.CodedInputStream
        public boolean skipField(int i, CodedOutputStream codedOutputStream) throws IOException {
            switch (WireFormat.getTagWireType(i)) {
                case 0:
                    long readInt64 = readInt64();
                    codedOutputStream.writeRawVarint32(i);
                    codedOutputStream.writeUInt64NoTag(readInt64);
                    return true;
                case 1:
                    long readRawLittleEndian64 = readRawLittleEndian64();
                    codedOutputStream.writeRawVarint32(i);
                    codedOutputStream.writeFixed64NoTag(readRawLittleEndian64);
                    return true;
                case 2:
                    ByteString readBytes = readBytes();
                    codedOutputStream.writeRawVarint32(i);
                    codedOutputStream.writeBytesNoTag(readBytes);
                    return true;
                case 3:
                    codedOutputStream.writeRawVarint32(i);
                    skipMessage(codedOutputStream);
                    int makeTag = WireFormat.makeTag(WireFormat.getTagFieldNumber(i), 4);
                    checkLastTagWas(makeTag);
                    codedOutputStream.writeRawVarint32(makeTag);
                    return true;
                case 4:
                    return false;
                case 5:
                    int readRawLittleEndian32 = readRawLittleEndian32();
                    codedOutputStream.writeRawVarint32(i);
                    codedOutputStream.writeFixed32NoTag(readRawLittleEndian32);
                    return true;
                default:
                    throw InvalidProtocolBufferException.invalidWireType();
            }
        }

        @Override // com.google.protobuf.CodedInputStream
        public void skipMessage() throws IOException {
            int readTag;
            do {
                readTag = readTag();
                if (readTag == 0) {
                    return;
                }
            } while (skipField(readTag));
        }

        @Override // com.google.protobuf.CodedInputStream
        public void skipMessage(CodedOutputStream codedOutputStream) throws IOException {
            int readTag;
            do {
                readTag = readTag();
                if (readTag == 0) {
                    return;
                }
            } while (skipField(readTag, codedOutputStream));
        }

        @Override // com.google.protobuf.CodedInputStream
        public double readDouble() throws IOException {
            return Double.longBitsToDouble(readRawLittleEndian64());
        }

        @Override // com.google.protobuf.CodedInputStream
        public float readFloat() throws IOException {
            return Float.intBitsToFloat(readRawLittleEndian32());
        }

        @Override // com.google.protobuf.CodedInputStream
        public long readUInt64() throws IOException {
            return readRawVarint64();
        }

        @Override // com.google.protobuf.CodedInputStream
        public long readInt64() throws IOException {
            return readRawVarint64();
        }

        @Override // com.google.protobuf.CodedInputStream
        public int readInt32() throws IOException {
            return readRawVarint32();
        }

        @Override // com.google.protobuf.CodedInputStream
        public long readFixed64() throws IOException {
            return readRawLittleEndian64();
        }

        @Override // com.google.protobuf.CodedInputStream
        public int readFixed32() throws IOException {
            return readRawLittleEndian32();
        }

        @Override // com.google.protobuf.CodedInputStream
        public boolean readBool() throws IOException {
            return readRawVarint64() != 0;
        }

        @Override // com.google.protobuf.CodedInputStream
        public String readString() throws IOException {
            int readRawVarint32 = readRawVarint32();
            if (readRawVarint32 <= 0 || readRawVarint32 > remaining()) {
                if (readRawVarint32 == 0) {
                    return "";
                }
                if (readRawVarint32 < 0) {
                    throw InvalidProtocolBufferException.negativeSize();
                }
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            long j = this.pos;
            long j2 = readRawVarint32;
            String str = new String(copyToArray(j, j + j2), Internal.UTF_8);
            this.pos += j2;
            return str;
        }

        @Override // com.google.protobuf.CodedInputStream
        public String readStringRequireUtf8() throws IOException {
            int readRawVarint32 = readRawVarint32();
            if (readRawVarint32 < 0 || readRawVarint32 > remaining()) {
                if (readRawVarint32 == 0) {
                    return "";
                }
                if (readRawVarint32 <= 0) {
                    throw InvalidProtocolBufferException.negativeSize();
                }
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            long j = this.pos;
            long j2 = readRawVarint32;
            byte[] copyToArray = copyToArray(j, j + j2);
            if (!Utf8.isValidUtf8(copyToArray)) {
                throw InvalidProtocolBufferException.invalidUtf8();
            }
            String str = new String(copyToArray, Internal.UTF_8);
            this.pos += j2;
            return str;
        }

        @Override // com.google.protobuf.CodedInputStream
        public void readGroup(int i, MessageLite.Builder builder, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            if (this.recursionDepth >= this.recursionLimit) {
                throw InvalidProtocolBufferException.recursionLimitExceeded();
            }
            this.recursionDepth++;
            builder.mergeFrom(this, extensionRegistryLite);
            checkLastTagWas(WireFormat.makeTag(i, 4));
            this.recursionDepth--;
        }

        @Override // com.google.protobuf.CodedInputStream
        public <T extends MessageLite> T readGroup(int i, Parser<T> parser, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            if (this.recursionDepth >= this.recursionLimit) {
                throw InvalidProtocolBufferException.recursionLimitExceeded();
            }
            this.recursionDepth++;
            T parsePartialFrom = parser.parsePartialFrom(this, extensionRegistryLite);
            checkLastTagWas(WireFormat.makeTag(i, 4));
            this.recursionDepth--;
            return parsePartialFrom;
        }

        @Override // com.google.protobuf.CodedInputStream
        @Deprecated
        public void readUnknownGroup(int i, MessageLite.Builder builder) throws IOException {
            readGroup(i, builder, ExtensionRegistryLite.getEmptyRegistry());
        }

        @Override // com.google.protobuf.CodedInputStream
        public void readMessage(MessageLite.Builder builder, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            int readRawVarint32 = readRawVarint32();
            if (this.recursionDepth >= this.recursionLimit) {
                throw InvalidProtocolBufferException.recursionLimitExceeded();
            }
            int pushLimit = pushLimit(readRawVarint32);
            this.recursionDepth++;
            builder.mergeFrom(this, extensionRegistryLite);
            checkLastTagWas(0);
            this.recursionDepth--;
            popLimit(pushLimit);
        }

        @Override // com.google.protobuf.CodedInputStream
        public <T extends MessageLite> T readMessage(Parser<T> parser, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            int readRawVarint32 = readRawVarint32();
            if (this.recursionDepth >= this.recursionLimit) {
                throw InvalidProtocolBufferException.recursionLimitExceeded();
            }
            int pushLimit = pushLimit(readRawVarint32);
            this.recursionDepth++;
            T parsePartialFrom = parser.parsePartialFrom(this, extensionRegistryLite);
            checkLastTagWas(0);
            this.recursionDepth--;
            popLimit(pushLimit);
            return parsePartialFrom;
        }

        @Override // com.google.protobuf.CodedInputStream
        public ByteString readBytes() throws IOException {
            ByteBuffer copy;
            int readRawVarint32 = readRawVarint32();
            if (readRawVarint32 <= 0 || readRawVarint32 > remaining()) {
                if (readRawVarint32 == 0) {
                    return ByteString.EMPTY;
                }
                if (readRawVarint32 < 0) {
                    throw InvalidProtocolBufferException.negativeSize();
                }
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            if (this.immutable && this.enableAliasing) {
                long j = this.pos;
                copy = slice(j, readRawVarint32 + j);
            } else {
                long j2 = this.pos;
                copy = copy(j2, readRawVarint32 + j2);
            }
            this.pos += readRawVarint32;
            return ByteString.wrap(copy);
        }

        @Override // com.google.protobuf.CodedInputStream
        public byte[] readByteArray() throws IOException {
            return readRawBytes(readRawVarint32());
        }

        @Override // com.google.protobuf.CodedInputStream
        public ByteBuffer readByteBuffer() throws IOException {
            ByteBuffer copy;
            int readRawVarint32 = readRawVarint32();
            if (readRawVarint32 <= 0 || readRawVarint32 > remaining()) {
                if (readRawVarint32 == 0) {
                    return Internal.EMPTY_BYTE_BUFFER;
                }
                if (readRawVarint32 < 0) {
                    throw InvalidProtocolBufferException.negativeSize();
                }
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            if (!this.immutable && this.enableAliasing) {
                long j = this.pos;
                copy = slice(j, readRawVarint32 + j);
            } else {
                long j2 = this.pos;
                copy = copy(j2, readRawVarint32 + j2);
            }
            this.pos += readRawVarint32;
            return copy;
        }

        @Override // com.google.protobuf.CodedInputStream
        public int readUInt32() throws IOException {
            return readRawVarint32();
        }

        @Override // com.google.protobuf.CodedInputStream
        public int readEnum() throws IOException {
            return readRawVarint32();
        }

        @Override // com.google.protobuf.CodedInputStream
        public int readSFixed32() throws IOException {
            return readRawLittleEndian32();
        }

        @Override // com.google.protobuf.CodedInputStream
        public long readSFixed64() throws IOException {
            return readRawLittleEndian64();
        }

        @Override // com.google.protobuf.CodedInputStream
        public int readSInt32() throws IOException {
            return decodeZigZag32(readRawVarint32());
        }

        @Override // com.google.protobuf.CodedInputStream
        public long readSInt64() throws IOException {
            return decodeZigZag64(readRawVarint64());
        }

        /* JADX WARN: Code restructure failed: missing block: B:32:0x0083, code lost:
            if (com.google.protobuf.UnsafeUtil.getByte(r4) < 0) goto L36;
         */
        @Override // com.google.protobuf.CodedInputStream
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public int readRawVarint32() throws java.io.IOException {
            /*
                r10 = this;
                long r0 = r10.pos
                long r2 = r10.limit
                int r2 = (r2 > r0 ? 1 : (r2 == r0 ? 0 : -1))
                if (r2 != 0) goto La
                goto L85
            La:
                r2 = 1
                long r4 = r0 + r2
                byte r0 = com.google.protobuf.UnsafeUtil.getByte(r0)
                if (r0 < 0) goto L17
                r10.pos = r4
                return r0
            L17:
                long r6 = r10.limit
                long r6 = r6 - r4
                r8 = 9
                int r1 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
                if (r1 >= 0) goto L21
                goto L85
            L21:
                long r6 = r4 + r2
                byte r1 = com.google.protobuf.UnsafeUtil.getByte(r4)
                int r1 = r1 << 7
                r0 = r0 ^ r1
                if (r0 >= 0) goto L2f
                r0 = r0 ^ (-128(0xffffffffffffff80, float:NaN))
                goto L90
            L2f:
                long r4 = r6 + r2
                byte r1 = com.google.protobuf.UnsafeUtil.getByte(r6)
                int r1 = r1 << 14
                r0 = r0 ^ r1
                if (r0 < 0) goto L3e
                r0 = r0 ^ 16256(0x3f80, float:2.278E-41)
                r6 = r4
                goto L90
            L3e:
                long r6 = r4 + r2
                byte r1 = com.google.protobuf.UnsafeUtil.getByte(r4)
                int r1 = r1 << 21
                r0 = r0 ^ r1
                if (r0 >= 0) goto L4e
                r1 = -2080896(0xffffffffffe03f80, float:NaN)
                r0 = r0 ^ r1
                goto L90
            L4e:
                long r4 = r6 + r2
                byte r1 = com.google.protobuf.UnsafeUtil.getByte(r6)
                int r6 = r1 << 28
                r0 = r0 ^ r6
                r6 = 266354560(0xfe03f80, float:2.2112565E-29)
                r0 = r0 ^ r6
                if (r1 >= 0) goto L8f
                long r6 = r4 + r2
                byte r1 = com.google.protobuf.UnsafeUtil.getByte(r4)
                if (r1 >= 0) goto L90
                long r4 = r6 + r2
                byte r1 = com.google.protobuf.UnsafeUtil.getByte(r6)
                if (r1 >= 0) goto L8d
                long r6 = r4 + r2
                byte r1 = com.google.protobuf.UnsafeUtil.getByte(r4)
                if (r1 >= 0) goto L90
                long r4 = r6 + r2
                byte r1 = com.google.protobuf.UnsafeUtil.getByte(r6)
                if (r1 >= 0) goto L8b
                long r6 = r4 + r2
                byte r1 = com.google.protobuf.UnsafeUtil.getByte(r4)
                if (r1 >= 0) goto L90
            L85:
                long r0 = r10.readRawVarint64SlowPath()
                int r0 = (int) r0
                return r0
            L8b:
                r6 = r4
                goto L90
            L8d:
                r6 = r4
                goto L90
            L8f:
                r6 = r4
            L90:
                r10.pos = r6
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.CodedInputStream.UnsafeDirectNioDecoder.readRawVarint32():int");
        }

        private void skipRawVarint() throws IOException {
            if (remaining() >= 10) {
                skipRawVarintFastPath();
            } else {
                skipRawVarintSlowPath();
            }
        }

        private void skipRawVarintFastPath() throws IOException {
            for (int i = 0; i < 10; i++) {
                long j = this.pos;
                this.pos = 1 + j;
                if (UnsafeUtil.getByte(j) >= 0) {
                    return;
                }
            }
            throw InvalidProtocolBufferException.malformedVarint();
        }

        private void skipRawVarintSlowPath() throws IOException {
            for (int i = 0; i < 10; i++) {
                if (readRawByte() >= 0) {
                    return;
                }
            }
            throw InvalidProtocolBufferException.malformedVarint();
        }

        @Override // com.google.protobuf.CodedInputStream
        public long readRawVarint64() throws IOException {
            long j;
            long j2;
            long j3 = this.pos;
            if (this.limit != j3) {
                long j4 = j3 + 1;
                byte b = UnsafeUtil.getByte(j3);
                if (b >= 0) {
                    this.pos = j4;
                    return b;
                } else if (this.limit - j4 >= 9) {
                    long j5 = j4 + 1;
                    int i = b ^ (UnsafeUtil.getByte(j4) << 7);
                    if (i < 0) {
                        j2 = i ^ (-128);
                        j = j5;
                    } else {
                        j = j5 + 1;
                        int i2 = i ^ (UnsafeUtil.getByte(j5) << 14);
                        if (i2 >= 0) {
                            j2 = i2 ^ 16256;
                        } else {
                            long j6 = j + 1;
                            int i3 = i2 ^ (UnsafeUtil.getByte(j) << 21);
                            if (i3 < 0) {
                                j2 = i3 ^ (-2080896);
                                j = j6;
                            } else {
                                j = j6 + 1;
                                long j7 = i3 ^ (UnsafeUtil.getByte(j6) << 28);
                                if (j7 >= 0) {
                                    j2 = j7 ^ 266354560;
                                } else {
                                    long j8 = j + 1;
                                    long j9 = j7 ^ (UnsafeUtil.getByte(j) << 35);
                                    if (j9 < 0) {
                                        j2 = j9 ^ (-34093383808L);
                                        j = j8;
                                    } else {
                                        j = j8 + 1;
                                        long j10 = j9 ^ (UnsafeUtil.getByte(j8) << 42);
                                        if (j10 >= 0) {
                                            j2 = j10 ^ 4363953127296L;
                                        } else {
                                            long j11 = j + 1;
                                            long j12 = j10 ^ (UnsafeUtil.getByte(j) << 49);
                                            if (j12 < 0) {
                                                j2 = j12 ^ (-558586000294016L);
                                                j = j11;
                                            } else {
                                                j = j11 + 1;
                                                j2 = (j12 ^ (UnsafeUtil.getByte(j11) << 56)) ^ 71499008037633920L;
                                                if (j2 < 0) {
                                                    long j13 = 1 + j;
                                                    if (UnsafeUtil.getByte(j) >= 0) {
                                                        j = j13;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    this.pos = j;
                    return j2;
                }
            }
            return readRawVarint64SlowPath();
        }

        @Override // com.google.protobuf.CodedInputStream
        long readRawVarint64SlowPath() throws IOException {
            long j = 0;
            for (int i = 0; i < 64; i += 7) {
                byte readRawByte = readRawByte();
                j |= (readRawByte & Byte.MAX_VALUE) << i;
                if ((readRawByte & 128) == 0) {
                    return j;
                }
            }
            throw InvalidProtocolBufferException.malformedVarint();
        }

        @Override // com.google.protobuf.CodedInputStream
        public int readRawLittleEndian32() throws IOException {
            long j = this.pos;
            if (this.limit - j < 4) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            this.pos = 4 + j;
            return ((UnsafeUtil.getByte(j + 3) & 255) << 24) | (UnsafeUtil.getByte(j) & 255) | ((UnsafeUtil.getByte(1 + j) & 255) << 8) | ((UnsafeUtil.getByte(2 + j) & 255) << 16);
        }

        @Override // com.google.protobuf.CodedInputStream
        public long readRawLittleEndian64() throws IOException {
            long j = this.pos;
            if (this.limit - j < 8) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            this.pos = 8 + j;
            return ((UnsafeUtil.getByte(j + 7) & 255) << 56) | (UnsafeUtil.getByte(j) & 255) | ((UnsafeUtil.getByte(1 + j) & 255) << 8) | ((UnsafeUtil.getByte(2 + j) & 255) << 16) | ((UnsafeUtil.getByte(3 + j) & 255) << 24) | ((UnsafeUtil.getByte(4 + j) & 255) << 32) | ((UnsafeUtil.getByte(5 + j) & 255) << 40) | ((UnsafeUtil.getByte(6 + j) & 255) << 48);
        }

        @Override // com.google.protobuf.CodedInputStream
        public void enableAliasing(boolean z) {
            this.enableAliasing = z;
        }

        @Override // com.google.protobuf.CodedInputStream
        public void resetSizeCounter() {
            this.startPos = this.pos;
        }

        @Override // com.google.protobuf.CodedInputStream
        public int pushLimit(int i) throws InvalidProtocolBufferException {
            if (i < 0) {
                throw InvalidProtocolBufferException.negativeSize();
            }
            int totalBytesRead = i + getTotalBytesRead();
            int i2 = this.currentLimit;
            if (totalBytesRead > i2) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            this.currentLimit = totalBytesRead;
            recomputeBufferSizeAfterLimit();
            return i2;
        }

        @Override // com.google.protobuf.CodedInputStream
        public void popLimit(int i) {
            this.currentLimit = i;
            recomputeBufferSizeAfterLimit();
        }

        @Override // com.google.protobuf.CodedInputStream
        public int getBytesUntilLimit() {
            int i = this.currentLimit;
            if (i == Integer.MAX_VALUE) {
                return -1;
            }
            return i - getTotalBytesRead();
        }

        @Override // com.google.protobuf.CodedInputStream
        public boolean isAtEnd() throws IOException {
            return this.pos == this.limit;
        }

        @Override // com.google.protobuf.CodedInputStream
        public int getTotalBytesRead() {
            return (int) (this.pos - this.startPos);
        }

        @Override // com.google.protobuf.CodedInputStream
        public byte readRawByte() throws IOException {
            long j = this.pos;
            if (j == this.limit) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            this.pos = 1 + j;
            return UnsafeUtil.getByte(j);
        }

        @Override // com.google.protobuf.CodedInputStream
        public byte[] readRawBytes(int i) throws IOException {
            if (i < 0 || i > remaining()) {
                if (i <= 0) {
                    if (i == 0) {
                        return Internal.EMPTY_BYTE_ARRAY;
                    }
                    throw InvalidProtocolBufferException.negativeSize();
                }
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            byte[] bArr = new byte[i];
            long j = this.pos;
            long j2 = i;
            slice(j, j + j2).get(bArr);
            this.pos += j2;
            return bArr;
        }

        @Override // com.google.protobuf.CodedInputStream
        public void skipRawBytes(int i) throws IOException {
            if (i >= 0 && i <= remaining()) {
                this.pos += i;
            } else if (i < 0) {
                throw InvalidProtocolBufferException.negativeSize();
            } else {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
        }

        private void recomputeBufferSizeAfterLimit() {
            this.limit += this.bufferSizeAfterLimit;
            long j = this.limit;
            int i = (int) (j - this.startPos);
            int i2 = this.currentLimit;
            if (i > i2) {
                this.bufferSizeAfterLimit = i - i2;
                this.limit = j - this.bufferSizeAfterLimit;
                return;
            }
            this.bufferSizeAfterLimit = 0;
        }

        private int remaining() {
            return (int) (this.limit - this.pos);
        }

        private int bufferPos(long j) {
            return (int) (j - this.address);
        }

        private ByteBuffer slice(long j, long j2) throws IOException {
            int position = this.buffer.position();
            int limit = this.buffer.limit();
            try {
                try {
                    this.buffer.position(bufferPos(j));
                    this.buffer.limit(bufferPos(j2));
                    return this.buffer.slice();
                } catch (IllegalArgumentException unused) {
                    throw InvalidProtocolBufferException.truncatedMessage();
                }
            } finally {
                this.buffer.position(position);
                this.buffer.limit(limit);
            }
        }

        private ByteBuffer copy(long j, long j2) throws IOException {
            return ByteBuffer.wrap(copyToArray(j, j2));
        }

        private byte[] copyToArray(long j, long j2) throws IOException {
            int position = this.buffer.position();
            int limit = this.buffer.limit();
            try {
                try {
                    this.buffer.position(bufferPos(j));
                    this.buffer.limit(bufferPos(j2));
                    byte[] bArr = new byte[(int) (j2 - j)];
                    this.buffer.get(bArr);
                    return bArr;
                } catch (IllegalArgumentException unused) {
                    throw InvalidProtocolBufferException.truncatedMessage();
                }
            } finally {
                this.buffer.position(position);
                this.buffer.limit(limit);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static final class StreamDecoder extends CodedInputStream {
        private final byte[] buffer;
        private int bufferSize;
        private int bufferSizeAfterLimit;
        private int currentLimit;
        private final InputStream input;
        private int lastTag;
        private int pos;
        private RefillCallback refillCallback;
        private int totalBytesRetired;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
        /* loaded from: E:\10762272_dexfile_execute.dex */
        public interface RefillCallback {
            void onRefill();
        }

        @Override // com.google.protobuf.CodedInputStream
        public void enableAliasing(boolean z) {
        }

        private StreamDecoder(InputStream inputStream, int i) {
            super();
            this.currentLimit = Integer.MAX_VALUE;
            this.refillCallback = null;
            Internal.checkNotNull(inputStream, "input");
            this.input = inputStream;
            this.buffer = new byte[i];
            this.bufferSize = 0;
            this.pos = 0;
            this.totalBytesRetired = 0;
        }

        @Override // com.google.protobuf.CodedInputStream
        public int readTag() throws IOException {
            if (isAtEnd()) {
                this.lastTag = 0;
                return 0;
            }
            this.lastTag = readRawVarint32();
            if (WireFormat.getTagFieldNumber(this.lastTag) == 0) {
                throw InvalidProtocolBufferException.invalidTag();
            }
            return this.lastTag;
        }

        @Override // com.google.protobuf.CodedInputStream
        public void checkLastTagWas(int i) throws InvalidProtocolBufferException {
            if (this.lastTag != i) {
                throw InvalidProtocolBufferException.invalidEndTag();
            }
        }

        @Override // com.google.protobuf.CodedInputStream
        public int getLastTag() {
            return this.lastTag;
        }

        @Override // com.google.protobuf.CodedInputStream
        public boolean skipField(int i) throws IOException {
            switch (WireFormat.getTagWireType(i)) {
                case 0:
                    skipRawVarint();
                    return true;
                case 1:
                    skipRawBytes(8);
                    return true;
                case 2:
                    skipRawBytes(readRawVarint32());
                    return true;
                case 3:
                    skipMessage();
                    checkLastTagWas(WireFormat.makeTag(WireFormat.getTagFieldNumber(i), 4));
                    return true;
                case 4:
                    return false;
                case 5:
                    skipRawBytes(4);
                    return true;
                default:
                    throw InvalidProtocolBufferException.invalidWireType();
            }
        }

        @Override // com.google.protobuf.CodedInputStream
        public boolean skipField(int i, CodedOutputStream codedOutputStream) throws IOException {
            switch (WireFormat.getTagWireType(i)) {
                case 0:
                    long readInt64 = readInt64();
                    codedOutputStream.writeRawVarint32(i);
                    codedOutputStream.writeUInt64NoTag(readInt64);
                    return true;
                case 1:
                    long readRawLittleEndian64 = readRawLittleEndian64();
                    codedOutputStream.writeRawVarint32(i);
                    codedOutputStream.writeFixed64NoTag(readRawLittleEndian64);
                    return true;
                case 2:
                    ByteString readBytes = readBytes();
                    codedOutputStream.writeRawVarint32(i);
                    codedOutputStream.writeBytesNoTag(readBytes);
                    return true;
                case 3:
                    codedOutputStream.writeRawVarint32(i);
                    skipMessage(codedOutputStream);
                    int makeTag = WireFormat.makeTag(WireFormat.getTagFieldNumber(i), 4);
                    checkLastTagWas(makeTag);
                    codedOutputStream.writeRawVarint32(makeTag);
                    return true;
                case 4:
                    return false;
                case 5:
                    int readRawLittleEndian32 = readRawLittleEndian32();
                    codedOutputStream.writeRawVarint32(i);
                    codedOutputStream.writeFixed32NoTag(readRawLittleEndian32);
                    return true;
                default:
                    throw InvalidProtocolBufferException.invalidWireType();
            }
        }

        @Override // com.google.protobuf.CodedInputStream
        public void skipMessage() throws IOException {
            int readTag;
            do {
                readTag = readTag();
                if (readTag == 0) {
                    return;
                }
            } while (skipField(readTag));
        }

        @Override // com.google.protobuf.CodedInputStream
        public void skipMessage(CodedOutputStream codedOutputStream) throws IOException {
            int readTag;
            do {
                readTag = readTag();
                if (readTag == 0) {
                    return;
                }
            } while (skipField(readTag, codedOutputStream));
        }

        /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
        /* loaded from: E:\10762272_dexfile_execute.dex */
        class SkippedDataSink implements RefillCallback {
            private ByteArrayOutputStream byteArrayStream;
            private int lastPos;

            private SkippedDataSink() {
                this.lastPos = StreamDecoder.this.pos;
            }

            @Override // com.google.protobuf.CodedInputStream.StreamDecoder.RefillCallback
            public void onRefill() {
                if (this.byteArrayStream == null) {
                    this.byteArrayStream = new ByteArrayOutputStream();
                }
                this.byteArrayStream.write(StreamDecoder.this.buffer, this.lastPos, StreamDecoder.this.pos - this.lastPos);
                this.lastPos = 0;
            }

            ByteBuffer getSkippedData() {
                ByteArrayOutputStream byteArrayOutputStream = this.byteArrayStream;
                if (byteArrayOutputStream == null) {
                    return ByteBuffer.wrap(StreamDecoder.this.buffer, this.lastPos, StreamDecoder.this.pos - this.lastPos);
                }
                byteArrayOutputStream.write(StreamDecoder.this.buffer, this.lastPos, StreamDecoder.this.pos);
                return ByteBuffer.wrap(this.byteArrayStream.toByteArray());
            }
        }

        @Override // com.google.protobuf.CodedInputStream
        public double readDouble() throws IOException {
            return Double.longBitsToDouble(readRawLittleEndian64());
        }

        @Override // com.google.protobuf.CodedInputStream
        public float readFloat() throws IOException {
            return Float.intBitsToFloat(readRawLittleEndian32());
        }

        @Override // com.google.protobuf.CodedInputStream
        public long readUInt64() throws IOException {
            return readRawVarint64();
        }

        @Override // com.google.protobuf.CodedInputStream
        public long readInt64() throws IOException {
            return readRawVarint64();
        }

        @Override // com.google.protobuf.CodedInputStream
        public int readInt32() throws IOException {
            return readRawVarint32();
        }

        @Override // com.google.protobuf.CodedInputStream
        public long readFixed64() throws IOException {
            return readRawLittleEndian64();
        }

        @Override // com.google.protobuf.CodedInputStream
        public int readFixed32() throws IOException {
            return readRawLittleEndian32();
        }

        @Override // com.google.protobuf.CodedInputStream
        public boolean readBool() throws IOException {
            return readRawVarint64() != 0;
        }

        @Override // com.google.protobuf.CodedInputStream
        public String readString() throws IOException {
            int readRawVarint32 = readRawVarint32();
            if (readRawVarint32 > 0) {
                int i = this.bufferSize;
                int i2 = this.pos;
                if (readRawVarint32 <= i - i2) {
                    String str = new String(this.buffer, i2, readRawVarint32, Internal.UTF_8);
                    this.pos += readRawVarint32;
                    return str;
                }
            }
            if (readRawVarint32 == 0) {
                return "";
            }
            if (readRawVarint32 <= this.bufferSize) {
                refillBuffer(readRawVarint32);
                String str2 = new String(this.buffer, this.pos, readRawVarint32, Internal.UTF_8);
                this.pos += readRawVarint32;
                return str2;
            }
            return new String(readRawBytesSlowPath(readRawVarint32), Internal.UTF_8);
        }

        @Override // com.google.protobuf.CodedInputStream
        public String readStringRequireUtf8() throws IOException {
            byte[] readRawBytesSlowPath;
            int readRawVarint32 = readRawVarint32();
            int i = this.pos;
            int i2 = 0;
            if (readRawVarint32 <= this.bufferSize - i && readRawVarint32 > 0) {
                readRawBytesSlowPath = this.buffer;
                this.pos = i + readRawVarint32;
                i2 = i;
            } else if (readRawVarint32 == 0) {
                return "";
            } else {
                if (readRawVarint32 <= this.bufferSize) {
                    refillBuffer(readRawVarint32);
                    readRawBytesSlowPath = this.buffer;
                    this.pos = readRawVarint32 + 0;
                } else {
                    readRawBytesSlowPath = readRawBytesSlowPath(readRawVarint32);
                }
            }
            if (!Utf8.isValidUtf8(readRawBytesSlowPath, i2, i2 + readRawVarint32)) {
                throw InvalidProtocolBufferException.invalidUtf8();
            }
            return new String(readRawBytesSlowPath, i2, readRawVarint32, Internal.UTF_8);
        }

        @Override // com.google.protobuf.CodedInputStream
        public void readGroup(int i, MessageLite.Builder builder, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            if (this.recursionDepth >= this.recursionLimit) {
                throw InvalidProtocolBufferException.recursionLimitExceeded();
            }
            this.recursionDepth++;
            builder.mergeFrom(this, extensionRegistryLite);
            checkLastTagWas(WireFormat.makeTag(i, 4));
            this.recursionDepth--;
        }

        @Override // com.google.protobuf.CodedInputStream
        public <T extends MessageLite> T readGroup(int i, Parser<T> parser, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            if (this.recursionDepth >= this.recursionLimit) {
                throw InvalidProtocolBufferException.recursionLimitExceeded();
            }
            this.recursionDepth++;
            T parsePartialFrom = parser.parsePartialFrom(this, extensionRegistryLite);
            checkLastTagWas(WireFormat.makeTag(i, 4));
            this.recursionDepth--;
            return parsePartialFrom;
        }

        @Override // com.google.protobuf.CodedInputStream
        @Deprecated
        public void readUnknownGroup(int i, MessageLite.Builder builder) throws IOException {
            readGroup(i, builder, ExtensionRegistryLite.getEmptyRegistry());
        }

        @Override // com.google.protobuf.CodedInputStream
        public void readMessage(MessageLite.Builder builder, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            int readRawVarint32 = readRawVarint32();
            if (this.recursionDepth >= this.recursionLimit) {
                throw InvalidProtocolBufferException.recursionLimitExceeded();
            }
            int pushLimit = pushLimit(readRawVarint32);
            this.recursionDepth++;
            builder.mergeFrom(this, extensionRegistryLite);
            checkLastTagWas(0);
            this.recursionDepth--;
            popLimit(pushLimit);
        }

        @Override // com.google.protobuf.CodedInputStream
        public <T extends MessageLite> T readMessage(Parser<T> parser, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            int readRawVarint32 = readRawVarint32();
            if (this.recursionDepth >= this.recursionLimit) {
                throw InvalidProtocolBufferException.recursionLimitExceeded();
            }
            int pushLimit = pushLimit(readRawVarint32);
            this.recursionDepth++;
            T parsePartialFrom = parser.parsePartialFrom(this, extensionRegistryLite);
            checkLastTagWas(0);
            this.recursionDepth--;
            popLimit(pushLimit);
            return parsePartialFrom;
        }

        @Override // com.google.protobuf.CodedInputStream
        public ByteString readBytes() throws IOException {
            int readRawVarint32 = readRawVarint32();
            int i = this.bufferSize;
            int i2 = this.pos;
            if (readRawVarint32 > i - i2 || readRawVarint32 <= 0) {
                if (readRawVarint32 == 0) {
                    return ByteString.EMPTY;
                }
                return ByteString.wrap(readRawBytesSlowPath(readRawVarint32));
            }
            ByteString copyFrom = ByteString.copyFrom(this.buffer, i2, readRawVarint32);
            this.pos += readRawVarint32;
            return copyFrom;
        }

        @Override // com.google.protobuf.CodedInputStream
        public byte[] readByteArray() throws IOException {
            int readRawVarint32 = readRawVarint32();
            int i = this.bufferSize;
            int i2 = this.pos;
            if (readRawVarint32 <= i - i2 && readRawVarint32 > 0) {
                byte[] copyOfRange = Arrays.copyOfRange(this.buffer, i2, i2 + readRawVarint32);
                this.pos += readRawVarint32;
                return copyOfRange;
            }
            return readRawBytesSlowPath(readRawVarint32);
        }

        @Override // com.google.protobuf.CodedInputStream
        public ByteBuffer readByteBuffer() throws IOException {
            int readRawVarint32 = readRawVarint32();
            int i = this.bufferSize;
            int i2 = this.pos;
            if (readRawVarint32 > i - i2 || readRawVarint32 <= 0) {
                if (readRawVarint32 == 0) {
                    return Internal.EMPTY_BYTE_BUFFER;
                }
                return ByteBuffer.wrap(readRawBytesSlowPath(readRawVarint32));
            }
            ByteBuffer wrap = ByteBuffer.wrap(Arrays.copyOfRange(this.buffer, i2, i2 + readRawVarint32));
            this.pos += readRawVarint32;
            return wrap;
        }

        @Override // com.google.protobuf.CodedInputStream
        public int readUInt32() throws IOException {
            return readRawVarint32();
        }

        @Override // com.google.protobuf.CodedInputStream
        public int readEnum() throws IOException {
            return readRawVarint32();
        }

        @Override // com.google.protobuf.CodedInputStream
        public int readSFixed32() throws IOException {
            return readRawLittleEndian32();
        }

        @Override // com.google.protobuf.CodedInputStream
        public long readSFixed64() throws IOException {
            return readRawLittleEndian64();
        }

        @Override // com.google.protobuf.CodedInputStream
        public int readSInt32() throws IOException {
            return decodeZigZag32(readRawVarint32());
        }

        @Override // com.google.protobuf.CodedInputStream
        public long readSInt64() throws IOException {
            return decodeZigZag64(readRawVarint64());
        }

        /* JADX WARN: Code restructure failed: missing block: B:32:0x0068, code lost:
            if (r2[r3] < 0) goto L34;
         */
        @Override // com.google.protobuf.CodedInputStream
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public int readRawVarint32() throws java.io.IOException {
            /*
                r5 = this;
                int r0 = r5.pos
                int r1 = r5.bufferSize
                if (r1 != r0) goto L7
                goto L6a
            L7:
                byte[] r2 = r5.buffer
                int r3 = r0 + 1
                r0 = r2[r0]
                if (r0 < 0) goto L12
                r5.pos = r3
                return r0
            L12:
                int r1 = r1 - r3
                r4 = 9
                if (r1 >= r4) goto L18
                goto L6a
            L18:
                int r1 = r3 + 1
                r3 = r2[r3]
                int r3 = r3 << 7
                r0 = r0 ^ r3
                if (r0 >= 0) goto L24
                r0 = r0 ^ (-128(0xffffffffffffff80, float:NaN))
                goto L71
            L24:
                int r3 = r1 + 1
                r1 = r2[r1]
                int r1 = r1 << 14
                r0 = r0 ^ r1
                if (r0 < 0) goto L31
                r0 = r0 ^ 16256(0x3f80, float:2.278E-41)
                r1 = r3
                goto L71
            L31:
                int r1 = r3 + 1
                r3 = r2[r3]
                int r3 = r3 << 21
                r0 = r0 ^ r3
                if (r0 >= 0) goto L3f
                r2 = -2080896(0xffffffffffe03f80, float:NaN)
                r0 = r0 ^ r2
                goto L71
            L3f:
                int r3 = r1 + 1
                r1 = r2[r1]
                int r4 = r1 << 28
                r0 = r0 ^ r4
                r4 = 266354560(0xfe03f80, float:2.2112565E-29)
                r0 = r0 ^ r4
                if (r1 >= 0) goto L70
                int r1 = r3 + 1
                r3 = r2[r3]
                if (r3 >= 0) goto L71
                int r3 = r1 + 1
                r1 = r2[r1]
                if (r1 >= 0) goto L70
                int r1 = r3 + 1
                r3 = r2[r3]
                if (r3 >= 0) goto L71
                int r3 = r1 + 1
                r1 = r2[r1]
                if (r1 >= 0) goto L70
                int r1 = r3 + 1
                r2 = r2[r3]
                if (r2 >= 0) goto L71
            L6a:
                long r0 = r5.readRawVarint64SlowPath()
                int r0 = (int) r0
                return r0
            L70:
                r1 = r3
            L71:
                r5.pos = r1
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.CodedInputStream.StreamDecoder.readRawVarint32():int");
        }

        private void skipRawVarint() throws IOException {
            if (this.bufferSize - this.pos >= 10) {
                skipRawVarintFastPath();
            } else {
                skipRawVarintSlowPath();
            }
        }

        private void skipRawVarintFastPath() throws IOException {
            for (int i = 0; i < 10; i++) {
                byte[] bArr = this.buffer;
                int i2 = this.pos;
                this.pos = i2 + 1;
                if (bArr[i2] >= 0) {
                    return;
                }
            }
            throw InvalidProtocolBufferException.malformedVarint();
        }

        private void skipRawVarintSlowPath() throws IOException {
            for (int i = 0; i < 10; i++) {
                if (readRawByte() >= 0) {
                    return;
                }
            }
            throw InvalidProtocolBufferException.malformedVarint();
        }

        @Override // com.google.protobuf.CodedInputStream
        public long readRawVarint64() throws IOException {
            long j;
            int i = this.pos;
            int i2 = this.bufferSize;
            if (i2 != i) {
                byte[] bArr = this.buffer;
                int i3 = i + 1;
                byte b = bArr[i];
                if (b >= 0) {
                    this.pos = i3;
                    return b;
                } else if (i2 - i3 >= 9) {
                    int i4 = i3 + 1;
                    int i5 = b ^ (bArr[i3] << 7);
                    if (i5 < 0) {
                        j = i5 ^ (-128);
                    } else {
                        int i6 = i4 + 1;
                        int i7 = i5 ^ (bArr[i4] << 14);
                        if (i7 >= 0) {
                            i4 = i6;
                            j = i7 ^ 16256;
                        } else {
                            i4 = i6 + 1;
                            int i8 = i7 ^ (bArr[i6] << 21);
                            if (i8 < 0) {
                                j = i8 ^ (-2080896);
                            } else {
                                long j2 = i8;
                                int i9 = i4 + 1;
                                long j3 = j2 ^ (bArr[i4] << 28);
                                if (j3 >= 0) {
                                    j = j3 ^ 266354560;
                                    i4 = i9;
                                } else {
                                    i4 = i9 + 1;
                                    long j4 = j3 ^ (bArr[i9] << 35);
                                    if (j4 < 0) {
                                        j = j4 ^ (-34093383808L);
                                    } else {
                                        int i10 = i4 + 1;
                                        long j5 = j4 ^ (bArr[i4] << 42);
                                        if (j5 >= 0) {
                                            j = j5 ^ 4363953127296L;
                                            i4 = i10;
                                        } else {
                                            i4 = i10 + 1;
                                            long j6 = j5 ^ (bArr[i10] << 49);
                                            if (j6 < 0) {
                                                j = j6 ^ (-558586000294016L);
                                            } else {
                                                int i11 = i4 + 1;
                                                long j7 = (j6 ^ (bArr[i4] << 56)) ^ 71499008037633920L;
                                                if (j7 < 0) {
                                                    i4 = i11 + 1;
                                                    if (bArr[i11] >= 0) {
                                                        j = j7;
                                                    }
                                                } else {
                                                    i4 = i11;
                                                    j = j7;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    this.pos = i4;
                    return j;
                }
            }
            return readRawVarint64SlowPath();
        }

        @Override // com.google.protobuf.CodedInputStream
        long readRawVarint64SlowPath() throws IOException {
            long j = 0;
            for (int i = 0; i < 64; i += 7) {
                byte readRawByte = readRawByte();
                j |= (readRawByte & Byte.MAX_VALUE) << i;
                if ((readRawByte & 128) == 0) {
                    return j;
                }
            }
            throw InvalidProtocolBufferException.malformedVarint();
        }

        @Override // com.google.protobuf.CodedInputStream
        public int readRawLittleEndian32() throws IOException {
            int i = this.pos;
            if (this.bufferSize - i < 4) {
                refillBuffer(4);
                i = this.pos;
            }
            byte[] bArr = this.buffer;
            this.pos = i + 4;
            return ((bArr[i + 3] & 255) << 24) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16);
        }

        @Override // com.google.protobuf.CodedInputStream
        public long readRawLittleEndian64() throws IOException {
            int i = this.pos;
            if (this.bufferSize - i < 8) {
                refillBuffer(8);
                i = this.pos;
            }
            byte[] bArr = this.buffer;
            this.pos = i + 8;
            return ((bArr[i + 7] & 255) << 56) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16) | ((bArr[i + 3] & 255) << 24) | ((bArr[i + 4] & 255) << 32) | ((bArr[i + 5] & 255) << 40) | ((bArr[i + 6] & 255) << 48);
        }

        @Override // com.google.protobuf.CodedInputStream
        public void resetSizeCounter() {
            this.totalBytesRetired = -this.pos;
        }

        @Override // com.google.protobuf.CodedInputStream
        public int pushLimit(int i) throws InvalidProtocolBufferException {
            if (i < 0) {
                throw InvalidProtocolBufferException.negativeSize();
            }
            int i2 = i + this.totalBytesRetired + this.pos;
            int i3 = this.currentLimit;
            if (i2 > i3) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            this.currentLimit = i2;
            recomputeBufferSizeAfterLimit();
            return i3;
        }

        private void recomputeBufferSizeAfterLimit() {
            this.bufferSize += this.bufferSizeAfterLimit;
            int i = this.totalBytesRetired;
            int i2 = this.bufferSize;
            int i3 = i + i2;
            int i4 = this.currentLimit;
            if (i3 > i4) {
                this.bufferSizeAfterLimit = i3 - i4;
                this.bufferSize = i2 - this.bufferSizeAfterLimit;
                return;
            }
            this.bufferSizeAfterLimit = 0;
        }

        @Override // com.google.protobuf.CodedInputStream
        public void popLimit(int i) {
            this.currentLimit = i;
            recomputeBufferSizeAfterLimit();
        }

        @Override // com.google.protobuf.CodedInputStream
        public int getBytesUntilLimit() {
            int i = this.currentLimit;
            if (i == Integer.MAX_VALUE) {
                return -1;
            }
            return i - (this.totalBytesRetired + this.pos);
        }

        @Override // com.google.protobuf.CodedInputStream
        public boolean isAtEnd() throws IOException {
            return this.pos == this.bufferSize && !tryRefillBuffer(1);
        }

        @Override // com.google.protobuf.CodedInputStream
        public int getTotalBytesRead() {
            return this.totalBytesRetired + this.pos;
        }

        private void refillBuffer(int i) throws IOException {
            if (!tryRefillBuffer(i)) {
                throw InvalidProtocolBufferException.truncatedMessage();
            }
        }

        private boolean tryRefillBuffer(int i) throws IOException {
            int i2 = this.pos;
            if (i2 + i <= this.bufferSize) {
                throw new IllegalStateException("refillBuffer() called when " + i + " bytes were already available in buffer");
            } else if (this.totalBytesRetired + i2 + i > this.currentLimit) {
                return false;
            } else {
                RefillCallback refillCallback = this.refillCallback;
                if (refillCallback != null) {
                    refillCallback.onRefill();
                }
                int i3 = this.pos;
                if (i3 > 0) {
                    int i4 = this.bufferSize;
                    if (i4 > i3) {
                        byte[] bArr = this.buffer;
                        System.arraycopy(bArr, i3, bArr, 0, i4 - i3);
                    }
                    this.totalBytesRetired += i3;
                    this.bufferSize -= i3;
                    this.pos = 0;
                }
                InputStream inputStream = this.input;
                byte[] bArr2 = this.buffer;
                int i5 = this.bufferSize;
                int read = inputStream.read(bArr2, i5, bArr2.length - i5);
                if (read == 0 || read < -1 || read > this.buffer.length) {
                    throw new IllegalStateException("InputStream#read(byte[]) returned invalid result: " + read + "\nThe InputStream implementation is buggy.");
                } else if (read > 0) {
                    this.bufferSize += read;
                    if ((this.totalBytesRetired + i) - this.sizeLimit > 0) {
                        throw InvalidProtocolBufferException.sizeLimitExceeded();
                    }
                    recomputeBufferSizeAfterLimit();
                    if (this.bufferSize >= i) {
                        return true;
                    }
                    return tryRefillBuffer(i);
                } else {
                    return false;
                }
            }
        }

        @Override // com.google.protobuf.CodedInputStream
        public byte readRawByte() throws IOException {
            if (this.pos == this.bufferSize) {
                refillBuffer(1);
            }
            byte[] bArr = this.buffer;
            int i = this.pos;
            this.pos = i + 1;
            return bArr[i];
        }

        @Override // com.google.protobuf.CodedInputStream
        public byte[] readRawBytes(int i) throws IOException {
            int i2 = this.pos;
            if (i <= this.bufferSize - i2 && i > 0) {
                int i3 = i + i2;
                this.pos = i3;
                return Arrays.copyOfRange(this.buffer, i2, i3);
            }
            return readRawBytesSlowPath(i);
        }

        private byte[] readRawBytesSlowPath(int i) throws IOException {
            if (i == 0) {
                return Internal.EMPTY_BYTE_ARRAY;
            }
            if (i < 0) {
                throw InvalidProtocolBufferException.negativeSize();
            }
            int i2 = this.totalBytesRetired + this.pos + i;
            if (i2 - this.sizeLimit > 0) {
                throw InvalidProtocolBufferException.sizeLimitExceeded();
            }
            int i3 = this.currentLimit;
            if (i2 > i3) {
                skipRawBytes((i3 - this.totalBytesRetired) - this.pos);
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            int i4 = this.pos;
            int i5 = this.bufferSize;
            int i6 = i5 - i4;
            this.totalBytesRetired += i5;
            this.pos = 0;
            this.bufferSize = 0;
            int i7 = i - i6;
            if (i7 < 4096 || i7 <= this.input.available()) {
                byte[] bArr = new byte[i];
                System.arraycopy(this.buffer, i4, bArr, 0, i6);
                while (i6 < bArr.length) {
                    int read = this.input.read(bArr, i6, i - i6);
                    if (read == -1) {
                        throw InvalidProtocolBufferException.truncatedMessage();
                    }
                    this.totalBytesRetired += read;
                    i6 += read;
                }
                return bArr;
            }
            ArrayList<byte[]> arrayList = new ArrayList();
            while (i7 > 0) {
                byte[] bArr2 = new byte[Math.min(i7, 4096)];
                int i8 = 0;
                while (i8 < bArr2.length) {
                    int read2 = this.input.read(bArr2, i8, bArr2.length - i8);
                    if (read2 == -1) {
                        throw InvalidProtocolBufferException.truncatedMessage();
                    }
                    this.totalBytesRetired += read2;
                    i8 += read2;
                }
                i7 -= bArr2.length;
                arrayList.add(bArr2);
            }
            byte[] bArr3 = new byte[i];
            System.arraycopy(this.buffer, i4, bArr3, 0, i6);
            for (byte[] bArr4 : arrayList) {
                System.arraycopy(bArr4, 0, bArr3, i6, bArr4.length);
                i6 += bArr4.length;
            }
            return bArr3;
        }

        @Override // com.google.protobuf.CodedInputStream
        public void skipRawBytes(int i) throws IOException {
            int i2 = this.bufferSize;
            int i3 = this.pos;
            if (i <= i2 - i3 && i >= 0) {
                this.pos = i3 + i;
            } else {
                skipRawBytesSlowPath(i);
            }
        }

        private void skipRawBytesSlowPath(int i) throws IOException {
            if (i < 0) {
                throw InvalidProtocolBufferException.negativeSize();
            }
            int i2 = this.totalBytesRetired;
            int i3 = this.pos;
            int i4 = i2 + i3 + i;
            int i5 = this.currentLimit;
            if (i4 > i5) {
                skipRawBytes((i5 - i2) - i3);
                throw InvalidProtocolBufferException.truncatedMessage();
            }
            int i6 = this.bufferSize;
            int i7 = i6 - i3;
            this.pos = i6;
            refillBuffer(1);
            while (true) {
                int i8 = i - i7;
                int i9 = this.bufferSize;
                if (i8 > i9) {
                    i7 += i9;
                    this.pos = i9;
                    refillBuffer(1);
                } else {
                    this.pos = i8;
                    return;
                }
            }
        }
    }
}
