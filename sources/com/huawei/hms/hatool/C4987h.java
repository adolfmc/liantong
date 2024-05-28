package com.huawei.hms.hatool;

/* renamed from: com.huawei.hms.hatool.h */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C4987h {

    /* renamed from: a */
    private byte[] f11407a;

    /* renamed from: b */
    private int f11408b = 0;

    public C4987h(int i) {
        this.f11407a = null;
        this.f11407a = new byte[i];
    }

    /* renamed from: a */
    public void m14691a(byte[] bArr, int i) {
        if (i <= 0) {
            return;
        }
        byte[] bArr2 = this.f11407a;
        int length = bArr2.length;
        int i2 = this.f11408b;
        if (length - i2 >= i) {
            System.arraycopy(bArr, 0, bArr2, i2, i);
        } else {
            byte[] bArr3 = new byte[(bArr2.length + i) << 1];
            System.arraycopy(bArr2, 0, bArr3, 0, i2);
            System.arraycopy(bArr, 0, bArr3, this.f11408b, i);
            this.f11407a = bArr3;
        }
        this.f11408b += i;
    }

    /* renamed from: a */
    public byte[] m14692a() {
        int i = this.f11408b;
        if (i <= 0) {
            return new byte[0];
        }
        byte[] bArr = new byte[i];
        System.arraycopy(this.f11407a, 0, bArr, 0, i);
        return bArr;
    }

    /* renamed from: b */
    public int m14690b() {
        return this.f11408b;
    }
}
