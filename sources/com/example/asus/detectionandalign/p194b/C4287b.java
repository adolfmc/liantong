package com.example.asus.detectionandalign.p194b;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.example.asus.detectionandalign.b.b */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C4287b {
    /* renamed from: a */
    private static float m15976a(float f) {
        return Math.abs(f);
    }

    /* renamed from: a */
    private static int m15975a(float[] fArr, int i, int i2, float[] fArr2, float f, float[] fArr3, float[] fArr4, int i3) {
        if (m15973a(fArr, i, i2, fArr3, fArr4, f, i3) < 0) {
            return -1;
        }
        int i4 = (i < i2 ? i : i2) - 1;
        int i5 = 0;
        while (i5 <= i4 && fArr[(i5 * i2) + i5] != 0.0d) {
            i5++;
        }
        int i6 = i5 - 1;
        for (int i7 = 0; i7 <= i2 - 1; i7++) {
            for (int i8 = 0; i8 <= i - 1; i8++) {
                int i9 = (i7 * i) + i8;
                fArr2[i9] = 0.0f;
                for (int i10 = 0; i10 <= i6; i10++) {
                    int i11 = i10 * i2;
                    fArr2[i9] = fArr2[i9] + ((fArr4[i11 + i7] * fArr3[(i8 * i) + i10]) / fArr[i11 + i10]);
                }
            }
        }
        return 1;
    }

    /* renamed from: a */
    static int m15974a(float[] fArr, int i, int i2, float[] fArr2, float[] fArr3, float f) {
        return m15972a(fArr, i, i2, fArr2, fArr3, new float[i2 * i], f, new float[i * i], new float[i2 * i2], i > i2 ? i + 1 : i2 + 1);
    }

    /* JADX WARN: Code restructure failed: missing block: B:292:0x06e4, code lost:
        if (r10[r6] == 0.0d) goto L269;
     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static int m15973a(float[] r29, int r30, int r31, float[] r32, float[] r33, float r34, int r35) {
        /*
            Method dump skipped, instructions count: 1855
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.asus.detectionandalign.p194b.C4287b.m15973a(float[], int, int, float[], float[], float, int):int");
    }

    /* renamed from: a */
    private static int m15972a(float[] fArr, int i, int i2, float[] fArr2, float[] fArr3, float[] fArr4, float f, float[] fArr5, float[] fArr6, int i3) {
        if (m15975a(fArr, i, i2, fArr4, f, fArr5, fArr6, i3) < 0) {
            return -1;
        }
        for (int i4 = 0; i4 <= i2 - 1; i4++) {
            fArr3[i4] = 0.0f;
            for (int i5 = 0; i5 <= i - 1; i5++) {
                fArr3[i4] = fArr3[i4] + (fArr4[(i4 * i) + i5] * fArr2[i5]);
            }
        }
        return 1;
    }

    /* renamed from: a */
    private static void m15971a(float[] fArr, float[] fArr2) {
        float f = 0.0f;
        float f2 = 1.0f;
        if (m15976a(fArr[0]) + m15976a(fArr[1]) == 0.0d) {
            fArr2[0] = 1.0f;
            fArr2[1] = 0.0f;
        } else {
            float sqrt = (float) Math.sqrt((fArr[0] * fArr[0]) + (fArr[1] * fArr[1]));
            if (m15976a(fArr[0]) > m15976a(fArr[1])) {
                sqrt = m15976a(sqrt);
                if (fArr[0] < 0.0d) {
                    sqrt = -sqrt;
                }
            }
            if (m15976a(fArr[1]) >= m15976a(fArr[0])) {
                sqrt = m15976a(sqrt);
                if (fArr[1] < 0.0d) {
                    sqrt = -sqrt;
                }
            }
            f = sqrt;
            fArr2[0] = fArr[0] / f;
            fArr2[1] = fArr[1] / f;
        }
        if (m15976a(fArr[0]) > m15976a(fArr[1])) {
            f2 = fArr2[1];
        } else if (fArr2[0] != 0.0d) {
            f2 = 1.0f / fArr2[0];
        }
        fArr[0] = f;
        fArr[1] = f2;
    }

    /* renamed from: a */
    public static void m15970a(float[] fArr, float[] fArr2, int i, float[] fArr3) {
        float[] fArr4 = new float[5];
        float[] fArr5 = new float[i * 5];
        float[] fArr6 = new float[4];
        float[] fArr7 = new float[i * 3];
        float[] fArr8 = new float[i];
        float[] fArr9 = new float[2];
        float[] fArr10 = new float[i];
        float f = 0.0f;
        float f2 = 0.0f;
        for (int i2 = 0; i2 < i; i2++) {
            f += fArr[i2];
            f2 += fArr2[i2];
        }
        float f3 = i;
        float f4 = f / f3;
        float f5 = f2 / f3;
        for (int i3 = 0; i3 < i; i3++) {
            int i4 = i3 * 5;
            float f6 = fArr[i3] - f4;
            float f7 = fArr2[i3] - f5;
            fArr8[i3] = 10000.0f;
            float f8 = -f6;
            fArr5[i4] = f8 * f6;
            fArr5[i4 + 1] = (-f7) * f7;
            fArr5[i4 + 2] = f8 * f7;
            fArr5[i4 + 3] = f6;
            fArr5[i4 + 4] = f7;
        }
        float[] fArr11 = new float[5];
        m15974a(fArr5, i, 5, fArr8, fArr11, 1.0E-6f);
        fArr6[0] = fArr11[0] * 2.0f;
        float f9 = fArr11[2];
        fArr6[2] = f9;
        fArr6[1] = f9;
        fArr6[3] = fArr11[1] * 2.0f;
        fArr9[0] = fArr11[3];
        fArr9[1] = fArr11[4];
        float[] fArr12 = new float[2];
        m15974a(fArr6, 2, 2, fArr9, fArr12, 1.0E-6f);
        fArr4[0] = fArr12[0];
        fArr4[1] = fArr12[1];
        for (int i5 = 0; i5 < i; i5++) {
            float f10 = fArr[i5] - f4;
            float f11 = fArr2[i5] - f5;
            fArr10[i5] = 1.0f;
            int i6 = i5 * 3;
            fArr7[i6] = (f10 - fArr4[0]) * (f10 - fArr4[0]);
            fArr7[i6 + 1] = (f11 - fArr4[1]) * (f11 - fArr4[1]);
            fArr7[i6 + 2] = (f10 - fArr4[0]) * (f11 - fArr4[1]);
        }
        m15974a(fArr7, i, 3, fArr10, fArr11, 1.0E-6f);
        fArr4[4] = (float) (Math.atan2(fArr11[2], fArr11[1] - fArr11[0]) * (-0.5d));
        float sin = (float) Math.sin(fArr4[4] * (-2.0d));
        float f12 = Math.abs(sin) > m15976a(fArr11[2]) * 1.0E-6f ? fArr11[2] / sin : fArr11[1] - fArr11[0];
        fArr4[2] = m15976a((fArr11[0] + fArr11[1]) - f12);
        if (fArr4[2] > 1.0E-6f) {
            fArr4[2] = (float) Math.sqrt(2.0d / fArr4[2]);
        }
        fArr4[3] = m15976a(fArr11[0] + fArr11[1] + f12);
        if (fArr4[3] > 1.0E-6f) {
            fArr4[3] = (float) Math.sqrt(2.0d / fArr4[3]);
        }
        fArr3[0] = fArr4[0] + f4;
        fArr3[1] = fArr4[1] + f5;
        fArr3[2] = fArr4[2] * 2.0f;
        fArr3[3] = fArr4[3] * 2.0f;
        if (fArr3[2] > fArr3[3]) {
            float f13 = fArr3[2];
            fArr3[2] = fArr3[3];
            fArr3[3] = f13;
        }
        fArr3[4] = (float) (((fArr4[4] * 180.0f) / 3.141592653589793d) + 90.0d);
        if (fArr3[4] < -180.0f) {
            fArr3[4] = fArr3[4] + 360.0f;
        }
        if (fArr3[4] > 360.0f) {
            fArr3[4] = fArr3[4] - 360.0f;
        }
    }

    /* renamed from: a */
    private static void m15969a(float[] fArr, float[] fArr2, float[] fArr3, float[] fArr4, int i, int i2) {
        int i3;
        int i4 = i >= i2 ? i2 : i;
        int i5 = 1;
        while (true) {
            i3 = i4 - 1;
            if (i5 > i3) {
                break;
            }
            int i6 = i5 - 1;
            int i7 = (i6 * i2) + i5;
            fArr[i7 - 1] = fArr3[i6];
            fArr[i7] = fArr2[i6];
            i5++;
        }
        int i8 = (i3 * i2) + i4;
        fArr[i8 - 1] = fArr3[i3];
        if (i < i2) {
            fArr[i8] = fArr2[i3];
        }
        int i9 = 1;
        while (i9 <= i2 - 1) {
            int i10 = i9 + 1;
            for (int i11 = i10; i11 <= i2; i11++) {
                int i12 = (((i9 - 1) * i2) + i11) - 1;
                int i13 = (((i11 - 1) * i2) + i9) - 1;
                float f = fArr4[i12];
                fArr4[i12] = fArr4[i13];
                fArr4[i13] = f;
            }
            i9 = i10;
        }
    }
}
