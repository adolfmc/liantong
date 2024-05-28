package com.xiaomi.push;

/* renamed from: com.xiaomi.push.ik */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public abstract class AbstractC11467ik {
    /* renamed from: a */
    public abstract int mo2982a(byte[] bArr, int i, int i2);

    /* renamed from: a */
    public void mo2983a(int i) {
    }

    /* renamed from: a */
    public abstract void mo2981a(byte[] bArr, int i, int i2);

    /* renamed from: a */
    public byte[] mo2984a() {
        return null;
    }

    /* renamed from: a_ */
    public int mo2980a_() {
        return 0;
    }

    /* renamed from: b */
    public int mo2979b() {
        return -1;
    }

    /* renamed from: b */
    public int m2978b(byte[] bArr, int i, int i2) {
        int i3 = 0;
        while (i3 < i2) {
            int mo2982a = mo2982a(bArr, i + i3, i2 - i3);
            if (mo2982a <= 0) {
                throw new C11468il("Cannot read. Remote side has closed. Tried to read " + i2 + " bytes, but only got " + i3 + " bytes.");
            }
            i3 += mo2982a;
        }
        return i3;
    }
}
