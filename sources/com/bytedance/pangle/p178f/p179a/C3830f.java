package com.bytedance.pangle.p178f.p179a;

/* renamed from: com.bytedance.pangle.f.a.f */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public final class C3830f {

    /* renamed from: a */
    int[] f9168a;

    /* renamed from: b */
    int[] f9169b;

    /* renamed from: a */
    public final String m16855a(int i) {
        int[] iArr;
        if (i < 0 || (iArr = this.f9168a) == null || i >= iArr.length) {
            return null;
        }
        int i2 = iArr[i];
        int m16854a = m16854a(this.f9169b, i2);
        StringBuilder sb = new StringBuilder(m16854a);
        while (m16854a != 0) {
            i2 += 2;
            sb.append((char) m16854a(this.f9169b, i2));
            m16854a--;
        }
        return sb.toString();
    }

    /* renamed from: a */
    private static final int m16854a(int[] iArr, int i) {
        int i2 = iArr[i / 4];
        return (i % 4) / 2 == 0 ? i2 & 65535 : i2 >>> 16;
    }
}
