package com.bytedance.pangle.res.p181a;

import java.io.DataInput;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.bytedance.pangle.res.a.f */
/* loaded from: E:\10762272_dexfile_execute.dex */
public abstract class AbstractC3914f implements DataInput {

    /* renamed from: a */
    public final C3917i f9329a;

    public AbstractC3914f(C3917i c3917i) {
        this.f9329a = c3917i;
    }

    @Override // java.io.DataInput
    public int skipBytes(int i) {
        return this.f9329a.skipBytes(i);
    }

    @Override // java.io.DataInput
    public int readUnsignedShort() {
        return this.f9329a.readUnsignedShort();
    }

    @Override // java.io.DataInput
    public int readUnsignedByte() {
        return this.f9329a.readUnsignedByte();
    }

    @Override // java.io.DataInput
    public String readUTF() {
        return this.f9329a.readUTF();
    }

    @Override // java.io.DataInput
    public short readShort() {
        return this.f9329a.readShort();
    }

    @Override // java.io.DataInput
    public long readLong() {
        return this.f9329a.readLong();
    }

    @Override // java.io.DataInput
    public String readLine() {
        return this.f9329a.readLine();
    }

    @Override // java.io.DataInput
    public int readInt() {
        return this.f9329a.readInt();
    }

    @Override // java.io.DataInput
    public void readFully(byte[] bArr, int i, int i2) {
        this.f9329a.readFully(bArr, i, i2);
    }

    @Override // java.io.DataInput
    public void readFully(byte[] bArr) {
        this.f9329a.readFully(bArr);
    }

    @Override // java.io.DataInput
    public float readFloat() {
        return this.f9329a.readFloat();
    }

    @Override // java.io.DataInput
    public double readDouble() {
        return this.f9329a.readDouble();
    }

    @Override // java.io.DataInput
    public char readChar() {
        return this.f9329a.readChar();
    }

    @Override // java.io.DataInput
    public byte readByte() {
        return this.f9329a.readByte();
    }

    @Override // java.io.DataInput
    public boolean readBoolean() {
        return this.f9329a.readBoolean();
    }
}
