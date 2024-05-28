package com.bytedance.pangle.res.p181a;

import java.io.IOException;

/* renamed from: com.bytedance.pangle.res.a.g */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public final class C3915g extends AbstractC3914f {
    public C3915g(C3917i c3917i) {
        super(c3917i);
    }

    /* renamed from: a */
    public final int[] m16698a(int i) {
        int[] iArr = new int[i];
        for (int i2 = 0; i2 < i; i2++) {
            iArr[i2] = readInt();
        }
        return iArr;
    }

    /* renamed from: a */
    public final void m16699a() {
        short readShort = readShort();
        if (readShort != 8) {
            throw new IOException(String.format("Expected: 0x%08x, got: 0x%08x", (short) 8, Short.valueOf(readShort)));
        }
    }

    /* renamed from: b */
    public final void m16697b() {
        byte readByte = readByte();
        if (readByte != 0) {
            throw new IOException(String.format("Expected: 0x%08x, got: 0x%08x", (byte) 0, Byte.valueOf(readByte)));
        }
    }

    /* renamed from: b */
    public final void m16696b(int i) {
        int readInt;
        while (true) {
            readInt = readInt();
            if (readInt != i && readInt >= 1835009) {
                break;
            }
            i = -1;
        }
        if (readInt != 1835009) {
            throw new IOException(String.format("Expected: 0x%08x, got: 0x%08x", 1835009, Integer.valueOf(readInt)));
        }
    }

    @Override // com.bytedance.pangle.res.p181a.AbstractC3914f, java.io.DataInput
    public final int skipBytes(int i) {
        int i2 = 0;
        while (i2 < i) {
            int skipBytes = super.skipBytes(i - i2);
            if (skipBytes <= 0) {
                break;
            }
            i2 += skipBytes;
        }
        return i2;
    }
}
