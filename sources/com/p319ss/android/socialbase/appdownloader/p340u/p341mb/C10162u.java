package com.p319ss.android.socialbase.appdownloader.p340u.p341mb;

import java.io.IOException;

/* renamed from: com.ss.android.socialbase.appdownloader.u.mb.u */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C10162u {

    /* renamed from: mb */
    private int[] f19652mb;

    /* renamed from: ox */
    private int[] f19653ox;

    /* renamed from: mb */
    public static C10162u m6511mb(C10156hj c10156hj) throws IOException {
        C10161ox.m6513mb(c10156hj, 1835009);
        int m6537ox = c10156hj.m6537ox();
        int m6537ox2 = c10156hj.m6537ox();
        int m6537ox3 = c10156hj.m6537ox();
        c10156hj.m6537ox();
        int m6537ox4 = c10156hj.m6537ox();
        int m6537ox5 = c10156hj.m6537ox();
        C10162u c10162u = new C10162u();
        c10162u.f19652mb = c10156hj.m6536ox(m6537ox2);
        if (m6537ox3 != 0) {
            c10156hj.m6536ox(m6537ox3);
        }
        int i = (m6537ox5 == 0 ? m6537ox : m6537ox5) - m6537ox4;
        if (i % 4 != 0) {
            throw new IOException("String data size is not multiple of 4 (" + i + ").");
        }
        c10162u.f19653ox = c10156hj.m6536ox(i / 4);
        if (m6537ox5 != 0) {
            int i2 = m6537ox - m6537ox5;
            if (i2 % 4 != 0) {
                throw new IOException("Style data size is not multiple of 4 (" + i2 + ").");
            }
            c10156hj.m6536ox(i2 / 4);
        }
        return c10162u;
    }

    /* renamed from: mb */
    public String m6512mb(int i) {
        int[] iArr;
        if (i < 0 || (iArr = this.f19652mb) == null || i >= iArr.length) {
            return null;
        }
        int i2 = iArr[i];
        int m6510mb = m6510mb(this.f19653ox, i2);
        StringBuilder sb = new StringBuilder(m6510mb);
        while (m6510mb != 0) {
            i2 += 2;
            sb.append((char) m6510mb(this.f19653ox, i2));
            m6510mb--;
        }
        return sb.toString();
    }

    private C10162u() {
    }

    /* renamed from: mb */
    private static final int m6510mb(int[] iArr, int i) {
        int i2 = iArr[i / 4];
        return (i % 4) / 2 == 0 ? i2 & 65535 : i2 >>> 16;
    }
}
