package com.xiaomi.push;

/* renamed from: com.xiaomi.push.a */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class C11129a {

    /* renamed from: a */
    public static final C11129a f21449a = new C11129a(new byte[0]);

    /* renamed from: a */
    private volatile int f21450a = 0;

    /* renamed from: a */
    private final byte[] f21451a;

    private C11129a(byte[] bArr) {
        this.f21451a = bArr;
    }

    /* renamed from: a */
    public int m4947a() {
        return this.f21451a.length;
    }

    /* renamed from: a */
    public static C11129a m4944a(byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, i, bArr2, 0, i2);
        return new C11129a(bArr2);
    }

    /* renamed from: a */
    public static C11129a m4945a(byte[] bArr) {
        return m4944a(bArr, 0, bArr.length);
    }

    /* renamed from: a */
    public byte[] m4946a() {
        byte[] bArr = this.f21451a;
        int length = bArr.length;
        byte[] bArr2 = new byte[length];
        System.arraycopy(bArr, 0, bArr2, 0, length);
        return bArr2;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof C11129a) {
            byte[] bArr = this.f21451a;
            int length = bArr.length;
            byte[] bArr2 = ((C11129a) obj).f21451a;
            if (length != bArr2.length) {
                return false;
            }
            for (int i = 0; i < length; i++) {
                if (bArr[i] != bArr2[i]) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = this.f21450a;
        if (i == 0) {
            byte[] bArr = this.f21451a;
            int length = bArr.length;
            for (byte b : bArr) {
                length = (length * 31) + b;
            }
            i = length == 0 ? 1 : length;
            this.f21450a = i;
        }
        return i;
    }
}
