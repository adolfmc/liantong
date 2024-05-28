package com.bytedance.pangle.res.p181a;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FilterInputStream;

/* renamed from: com.bytedance.pangle.res.a.i */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public final class C3917i extends FilterInputStream implements DataInput {
    /* renamed from: a */
    private static int m16692a(byte b, byte b2, byte b3, byte b4) {
        return (b << 24) | ((b2 & 255) << 16) | ((b3 & 255) << 8) | (b4 & 255);
    }

    public C3917i(C3913e c3913e) {
        super(c3913e);
    }

    @Override // java.io.DataInput
    public final String readLine() {
        throw new UnsupportedOperationException("readLine is not supported");
    }

    @Override // java.io.DataInput
    public final void readFully(byte[] bArr, int i, int i2) {
        C3912d.m16703a(this, bArr, i, i2);
    }

    @Override // java.io.DataInput
    public final int skipBytes(int i) {
        return (int) this.in.skip(i);
    }

    @Override // java.io.DataInput
    public final int readUnsignedByte() {
        int read = this.in.read();
        if (read >= 0) {
            return read;
        }
        throw new EOFException();
    }

    @Override // java.io.DataInput
    public final int readUnsignedShort() {
        return m16692a((byte) 0, (byte) 0, m16691b(), m16691b());
    }

    @Override // java.io.DataInput
    public final int readInt() {
        byte m16691b = m16691b();
        byte m16691b2 = m16691b();
        return m16692a(m16691b(), m16691b(), m16691b2, m16691b);
    }

    @Override // java.io.DataInput
    public final long readLong() {
        byte m16691b = m16691b();
        byte m16691b2 = m16691b();
        byte m16691b3 = m16691b();
        byte m16691b4 = m16691b();
        byte m16691b5 = m16691b();
        byte m16691b6 = m16691b();
        return ((m16691b() & 255) << 56) | ((m16691b() & 255) << 48) | ((m16691b6 & 255) << 40) | ((m16691b5 & 255) << 32) | ((m16691b4 & 255) << 24) | ((m16691b3 & 255) << 16) | ((m16691b2 & 255) << 8) | (m16691b & 255);
    }

    @Override // java.io.DataInput
    public final float readFloat() {
        return Float.intBitsToFloat(readInt());
    }

    @Override // java.io.DataInput
    public final double readDouble() {
        return Double.longBitsToDouble(readLong());
    }

    @Override // java.io.DataInput
    public final String readUTF() {
        return new DataInputStream(this.in).readUTF();
    }

    @Override // java.io.DataInput
    public final short readShort() {
        return (short) readUnsignedShort();
    }

    @Override // java.io.DataInput
    public final char readChar() {
        return (char) readUnsignedShort();
    }

    @Override // java.io.DataInput
    public final byte readByte() {
        return (byte) readUnsignedByte();
    }

    @Override // java.io.DataInput
    public final boolean readBoolean() {
        return readUnsignedByte() != 0;
    }

    /* renamed from: b */
    private byte m16691b() {
        int read = this.in.read();
        if (-1 != read) {
            return (byte) read;
        }
        throw new EOFException();
    }

    /* renamed from: a */
    public final C3913e m16693a() {
        return (C3913e) this.in;
    }

    @Override // java.io.DataInput
    public final void readFully(byte[] bArr) {
        C3912d.m16703a(this, bArr, 0, bArr.length);
    }
}
