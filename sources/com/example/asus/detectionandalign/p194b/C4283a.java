package com.example.asus.detectionandalign.p194b;

import com.example.asus.detectionandalign.p194b.p195a.C4286b;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.example.asus.detectionandalign.b.a */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C4283a {
    /* renamed from: a */
    private static double m15992a(C4286b c4286b, C4286b c4286b2) {
        return c4286b.m15981a(c4286b2);
    }

    /* renamed from: a */
    public static float m15991a(C4286b[] c4286bArr) {
        int i;
        C4286b[] c4286bArr2 = new C4286b[6];
        C4286b[] c4286bArr3 = new C4286b[6];
        int i2 = 36;
        while (true) {
            if (i2 >= 42) {
                break;
            }
            c4286bArr2[i2 - 36] = c4286bArr[i2];
            i2++;
        }
        for (i = 42; i < 48; i++) {
            c4286bArr3[i - 42] = c4286bArr[i];
        }
        return m15990a(c4286bArr2, c4286bArr3);
    }

    /* renamed from: a */
    public static float m15990a(C4286b[] c4286bArr, C4286b[] c4286bArr2) {
        return (m15985f(c4286bArr).f10065a + m15985f(c4286bArr2).f10065a) / 2.0f;
    }

    /* renamed from: b */
    public static float m15989b(C4286b[] c4286bArr) {
        return (float) (((float) (((c4286bArr[61].m15981a(c4286bArr[67]) + c4286bArr[62].m15981a(c4286bArr[66])) + c4286bArr[63].m15981a(c4286bArr[65])) / 3.0d)) / c4286bArr[60].m15981a(c4286bArr[64]));
    }

    /* renamed from: c */
    public static float m15988c(C4286b[] c4286bArr) {
        return (float) (m15992a(c4286bArr[27], c4286bArr[30]) / m15992a(c4286bArr[0], c4286bArr[16]));
    }

    /* renamed from: d */
    public static float m15987d(C4286b[] c4286bArr) {
        double m15992a = m15992a(c4286bArr[0], c4286bArr[27]);
        double m15992a2 = m15992a(c4286bArr[1], c4286bArr[28]);
        double m15992a3 = m15992a + m15992a2 + m15992a(c4286bArr[2], c4286bArr[29]);
        double m15992a4 = m15992a(c4286bArr[16], c4286bArr[27]);
        double m15992a5 = m15992a(c4286bArr[15], c4286bArr[28]);
        return (float) (((m15992a3 + m15992a(c4286bArr[3], c4286bArr[29])) / 4.0d) / ((((m15992a4 + m15992a5) + m15992a(c4286bArr[14], c4286bArr[29])) + m15992a(c4286bArr[13], c4286bArr[29])) / 4.0d));
    }

    /* renamed from: e */
    public static float m15986e(C4286b[] c4286bArr) {
        double m15992a = m15992a(c4286bArr[0], c4286bArr[27]);
        double m15992a2 = m15992a(c4286bArr[1], c4286bArr[28]);
        double m15992a3 = m15992a + m15992a2 + m15992a(c4286bArr[2], c4286bArr[29]);
        double m15992a4 = m15992a(c4286bArr[16], c4286bArr[27]);
        double m15992a5 = m15992a(c4286bArr[15], c4286bArr[28]);
        return (float) (((((m15992a4 + m15992a5) + m15992a(c4286bArr[14], c4286bArr[29])) + m15992a(c4286bArr[13], c4286bArr[29])) / 4.0d) / ((m15992a3 + m15992a(c4286bArr[3], c4286bArr[29])) / 4.0d));
    }

    /* renamed from: f */
    private static C4288c m15985f(C4286b[] c4286bArr) {
        float[] fArr = new float[5];
        float[] fArr2 = new float[c4286bArr.length];
        float[] fArr3 = new float[c4286bArr.length];
        for (C4286b c4286b : c4286bArr) {
        }
        for (int i = 0; i < c4286bArr.length; i++) {
            fArr2[i] = (float) c4286bArr[i].m15983a();
            fArr3[i] = (float) c4286bArr[i].m15980b();
        }
        C4287b.m15970a(fArr2, fArr3, c4286bArr.length, fArr);
        return new C4288c(fArr);
    }
}
