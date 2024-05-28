package com.squareup.wire;

import java.io.EOFException;
import java.io.IOException;
import java.net.ProtocolException;
import okio.BufferedSource;
import okio.ByteString;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class ProtoReader {
    private static final int FIELD_ENCODING_MASK = 7;
    private static final int RECURSION_LIMIT = 65;
    private static final int STATE_END_GROUP = 4;
    private static final int STATE_FIXED32 = 5;
    private static final int STATE_FIXED64 = 1;
    private static final int STATE_LENGTH_DELIMITED = 2;
    private static final int STATE_PACKED_TAG = 7;
    private static final int STATE_START_GROUP = 3;
    private static final int STATE_TAG = 6;
    private static final int STATE_VARINT = 0;
    static final int TAG_FIELD_ENCODING_BITS = 3;
    private FieldEncoding nextFieldEncoding;
    private int recursionDepth;
    private final BufferedSource source;
    private long pos = 0;
    private long limit = Long.MAX_VALUE;
    private int state = 2;
    private int tag = -1;
    private long pushedLimit = -1;

    public ProtoReader(BufferedSource bufferedSource) {
        this.source = bufferedSource;
    }

    public long beginMessage() throws IOException {
        if (this.state != 2) {
            throw new IllegalStateException("Unexpected call to beginMessage()");
        }
        int i = this.recursionDepth + 1;
        this.recursionDepth = i;
        if (i > 65) {
            throw new IOException("Wire recursion limit exceeded");
        }
        long j = this.pushedLimit;
        this.pushedLimit = -1L;
        this.state = 6;
        return j;
    }

    public void endMessage(long j) throws IOException {
        if (this.state != 6) {
            throw new IllegalStateException("Unexpected call to endMessage()");
        }
        int i = this.recursionDepth - 1;
        this.recursionDepth = i;
        if (i < 0 || this.pushedLimit != -1) {
            throw new IllegalStateException("No corresponding call to beginMessage()");
        }
        if (this.pos != this.limit && this.recursionDepth != 0) {
            throw new IOException("Expected to end at " + this.limit + " but was " + this.pos);
        }
        this.limit = j;
    }

    public int nextTag() throws IOException {
        int i = this.state;
        if (i == 7) {
            this.state = 2;
            return this.tag;
        } else if (i != 6) {
            throw new IllegalStateException("Unexpected call to nextTag()");
        } else {
            while (this.pos < this.limit && !this.source.exhausted()) {
                int internalReadVarint32 = internalReadVarint32();
                if (internalReadVarint32 == 0) {
                    throw new ProtocolException("Unexpected tag 0");
                }
                this.tag = internalReadVarint32 >> 3;
                int i2 = internalReadVarint32 & 7;
                switch (i2) {
                    case 0:
                        this.nextFieldEncoding = FieldEncoding.VARINT;
                        this.state = 0;
                        return this.tag;
                    case 1:
                        this.nextFieldEncoding = FieldEncoding.FIXED64;
                        this.state = 1;
                        return this.tag;
                    case 2:
                        this.nextFieldEncoding = FieldEncoding.LENGTH_DELIMITED;
                        this.state = 2;
                        int internalReadVarint322 = internalReadVarint32();
                        if (internalReadVarint322 < 0) {
                            throw new ProtocolException("Negative length: " + internalReadVarint322);
                        } else if (this.pushedLimit != -1) {
                            throw new IllegalStateException();
                        } else {
                            this.pushedLimit = this.limit;
                            this.limit = this.pos + internalReadVarint322;
                            if (this.limit > this.pushedLimit) {
                                throw new EOFException();
                            }
                            return this.tag;
                        }
                    case 3:
                        skipGroup(this.tag);
                    case 4:
                        throw new ProtocolException("Unexpected end group");
                    case 5:
                        this.nextFieldEncoding = FieldEncoding.FIXED32;
                        this.state = 5;
                        return this.tag;
                    default:
                        throw new ProtocolException("Unexpected field encoding: " + i2);
                }
            }
            return -1;
        }
    }

    public FieldEncoding peekFieldEncoding() {
        return this.nextFieldEncoding;
    }

    public void skip() throws IOException {
        int i = this.state;
        if (i != 5) {
            switch (i) {
                case 0:
                    readVarint64();
                    return;
                case 1:
                    readFixed64();
                    return;
                case 2:
                    this.source.skip(beforeLengthDelimitedScalar());
                    return;
                default:
                    throw new IllegalStateException("Unexpected call to skip()");
            }
        }
        readFixed32();
    }

    private void skipGroup(int i) throws IOException {
        while (this.pos < this.limit && !this.source.exhausted()) {
            int internalReadVarint32 = internalReadVarint32();
            if (internalReadVarint32 == 0) {
                throw new ProtocolException("Unexpected tag 0");
            }
            int i2 = internalReadVarint32 >> 3;
            int i3 = internalReadVarint32 & 7;
            switch (i3) {
                case 0:
                    this.state = 0;
                    readVarint64();
                    break;
                case 1:
                    this.state = 1;
                    readFixed64();
                    break;
                case 2:
                    long internalReadVarint322 = internalReadVarint32();
                    this.pos += internalReadVarint322;
                    this.source.skip(internalReadVarint322);
                    break;
                case 3:
                    skipGroup(i2);
                    break;
                case 4:
                    if (i2 != i) {
                        throw new ProtocolException("Unexpected end group");
                    }
                    return;
                case 5:
                    this.state = 5;
                    readFixed32();
                    break;
                default:
                    throw new ProtocolException("Unexpected field encoding: " + i3);
            }
        }
        throw new EOFException();
    }

    public ByteString readBytes() throws IOException {
        long beforeLengthDelimitedScalar = beforeLengthDelimitedScalar();
        this.source.require(beforeLengthDelimitedScalar);
        return this.source.readByteString(beforeLengthDelimitedScalar);
    }

    public String readString() throws IOException {
        long beforeLengthDelimitedScalar = beforeLengthDelimitedScalar();
        this.source.require(beforeLengthDelimitedScalar);
        return this.source.readUtf8(beforeLengthDelimitedScalar);
    }

    public int readVarint32() throws IOException {
        int i = this.state;
        if (i != 0 && i != 2) {
            throw new ProtocolException("Expected VARINT or LENGTH_DELIMITED but was " + this.state);
        }
        int internalReadVarint32 = internalReadVarint32();
        afterPackableScalar(0);
        return internalReadVarint32;
    }

    private int internalReadVarint32() throws IOException {
        this.source.require(1L);
        this.pos++;
        byte readByte = this.source.readByte();
        if (readByte >= 0) {
            return readByte;
        }
        int i = readByte & Byte.MAX_VALUE;
        this.source.require(1L);
        this.pos++;
        byte readByte2 = this.source.readByte();
        if (readByte2 >= 0) {
            return i | (readByte2 << 7);
        }
        int i2 = i | ((readByte2 & Byte.MAX_VALUE) << 7);
        this.source.require(1L);
        this.pos++;
        byte readByte3 = this.source.readByte();
        if (readByte3 >= 0) {
            return i2 | (readByte3 << 14);
        }
        int i3 = i2 | ((readByte3 & Byte.MAX_VALUE) << 14);
        this.source.require(1L);
        this.pos++;
        byte readByte4 = this.source.readByte();
        if (readByte4 >= 0) {
            return i3 | (readByte4 << 21);
        }
        int i4 = i3 | ((readByte4 & Byte.MAX_VALUE) << 21);
        this.source.require(1L);
        this.pos++;
        byte readByte5 = this.source.readByte();
        int i5 = i4 | (readByte5 << 28);
        if (readByte5 < 0) {
            for (int i6 = 0; i6 < 5; i6++) {
                this.source.require(1L);
                this.pos++;
                if (this.source.readByte() >= 0) {
                    return i5;
                }
            }
            throw new ProtocolException("Malformed VARINT");
        }
        return i5;
    }

    public long readVarint64() throws IOException {
        byte readByte;
        int i = this.state;
        if (i != 0 && i != 2) {
            throw new ProtocolException("Expected VARINT or LENGTH_DELIMITED but was " + this.state);
        }
        long j = 0;
        for (int i2 = 0; i2 < 64; i2 += 7) {
            this.source.require(1L);
            this.pos++;
            j |= (readByte & Byte.MAX_VALUE) << i2;
            if ((this.source.readByte() & 128) == 0) {
                afterPackableScalar(0);
                return j;
            }
        }
        throw new ProtocolException("WireInput encountered a malformed varint");
    }

    public int readFixed32() throws IOException {
        int i = this.state;
        if (i != 5 && i != 2) {
            throw new ProtocolException("Expected FIXED32 or LENGTH_DELIMITED but was " + this.state);
        }
        this.source.require(4L);
        this.pos += 4;
        int readIntLe = this.source.readIntLe();
        afterPackableScalar(5);
        return readIntLe;
    }

    public long readFixed64() throws IOException {
        int i = this.state;
        if (i != 1 && i != 2) {
            throw new ProtocolException("Expected FIXED64 or LENGTH_DELIMITED but was " + this.state);
        }
        this.source.require(8L);
        this.pos += 8;
        long readLongLe = this.source.readLongLe();
        afterPackableScalar(1);
        return readLongLe;
    }

    private void afterPackableScalar(int i) throws IOException {
        if (this.state == i) {
            this.state = 6;
            return;
        }
        long j = this.pos;
        long j2 = this.limit;
        if (j > j2) {
            throw new IOException("Expected to end at " + this.limit + " but was " + this.pos);
        } else if (j == j2) {
            this.limit = this.pushedLimit;
            this.pushedLimit = -1L;
            this.state = 6;
        } else {
            this.state = 7;
        }
    }

    private long beforeLengthDelimitedScalar() throws IOException {
        if (this.state != 2) {
            throw new ProtocolException("Expected LENGTH_DELIMITED but was " + this.state);
        }
        long j = this.limit - this.pos;
        this.source.require(j);
        this.state = 6;
        this.pos = this.limit;
        this.limit = this.pushedLimit;
        this.pushedLimit = -1L;
        return j;
    }
}
