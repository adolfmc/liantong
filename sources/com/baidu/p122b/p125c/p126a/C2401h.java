package com.baidu.p122b.p125c.p126a;

import javax.crypto.ShortBufferException;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.b.c.a.h */
/* loaded from: E:\10201592_dexfile_execute.dex */
final class C2401h implements InterfaceC2402i {

    /* renamed from: a */
    private int f4227a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2401h(int i) {
        this.f4227a = i;
    }

    @Override // com.baidu.p122b.p125c.p126a.InterfaceC2402i
    /* renamed from: a */
    public int mo20264a(int i) {
        int i2 = this.f4227a;
        return i2 - (i % i2);
    }

    @Override // com.baidu.p122b.p125c.p126a.InterfaceC2402i
    /* renamed from: a */
    public void mo20263a(byte[] bArr, int i, int i2) {
        if (bArr == null) {
            return;
        }
        if (i + i2 > bArr.length) {
            throw new ShortBufferException("Buffer too small to hold padding");
        }
        byte b = (byte) (i2 & 255);
        for (int i3 = 0; i3 < i2; i3++) {
            bArr[i3 + i] = b;
        }
    }

    @Override // com.baidu.p122b.p125c.p126a.InterfaceC2402i
    /* renamed from: b */
    public int mo20262b(byte[] bArr, int i, int i2) {
        int i3;
        if (bArr == null || i2 == 0) {
            return 0;
        }
        int i4 = i2 + i;
        int i5 = bArr[i4 - 1];
        int i6 = i5 & 255;
        if (i6 < 1 || i6 > this.f4227a || (i3 = i4 - i6) < i) {
            return -1;
        }
        for (int i7 = 0; i7 < i6; i7++) {
            if (bArr[i3 + i7] != i5) {
                return -1;
            }
        }
        return i3;
    }
}
