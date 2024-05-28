package com.p226hw.videoprocessor.jssrc;

/*  JADX ERROR: NullPointerException in pass: ProcessKotlinInternals
    java.lang.NullPointerException
    	at jadx.core.dex.visitors.kotlin.ProcessKotlinInternals.processMth(ProcessKotlinInternals.java:92)
    	at jadx.core.dex.visitors.kotlin.ProcessKotlinInternals.visit(ProcessKotlinInternals.java:83)
    */
/* renamed from: com.hw.videoprocessor.jssrc.SplitRadixFft */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class SplitRadixFft {
    private static final int CDFT_RECURSIVE_N = 512;

    /*  JADX ERROR: JadxRuntimeException in pass: CheckCode
        jadx.core.utils.exceptions.JadxRuntimeException: Incorrect register number in instruction: 0x03fa: MOVE  (r0 I:??[long, double]) = (r1495 I:??[long, double]), expected to be less than 80
        	at jadx.core.dex.visitors.CheckCode.checkInstructions(CheckCode.java:75)
        	at jadx.core.dex.visitors.CheckCode.visit(CheckCode.java:33)
        */
    private final void cftb1st(int r76, double[] r77, double[] r78, int r79) {
        /*
            Method dump skipped, instructions count: 1044
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p226hw.videoprocessor.jssrc.SplitRadixFft.cftb1st(int, double[], double[], int):void");
    }

    public void cdft(int i, int i2, double[] dArr, int[] iArr, double[] dArr2) {
        int i3;
        int i4 = iArr[0];
        if (i > (i4 << 2)) {
            int i5 = i >> 2;
            makewt(i5, iArr, dArr2);
            i3 = i5;
        } else {
            i3 = i4;
        }
        if (i2 >= 0) {
            cftfsub(i, dArr, iArr, 2, i3, dArr2);
        } else {
            cftbsub(i, dArr, iArr, 2, i3, dArr2);
        }
    }

    public void rdft(int i, int i2, double[] dArr, int[] iArr, double[] dArr2) {
        int i3;
        int i4;
        int i5 = iArr[0];
        if (i > (i5 << 2)) {
            int i6 = i >> 2;
            makewt(i6, iArr, dArr2);
            i3 = i6;
        } else {
            i3 = i5;
        }
        int i7 = iArr[1];
        if (i > (i7 << 2)) {
            int i8 = i >> 2;
            makect(i8, iArr, dArr2, i3);
            i4 = i8;
        } else {
            i4 = i7;
        }
        if (i2 >= 0) {
            if (i > 4) {
                int i9 = i3;
                cftfsub(i, dArr, iArr, 2, i9, dArr2);
                rftfsub(i, dArr, i4, dArr2, i9);
            } else if (i == 4) {
                cftfsub(i, dArr, iArr, 2, i3, dArr2);
            }
            double d = dArr[0] - dArr[1];
            dArr[0] = dArr[0] + dArr[1];
            dArr[1] = d;
            return;
        }
        dArr[1] = (dArr[0] - dArr[1]) * 0.5d;
        dArr[0] = dArr[0] - dArr[1];
        if (i > 4) {
            int i10 = i3;
            rftbsub(i, dArr, i4, dArr2, i10);
            cftbsub(i, dArr, iArr, 2, i10, dArr2);
        } else if (i == 4) {
            cftbsub(i, dArr, iArr, 2, i3, dArr2);
        }
    }

    public void ddct(int i, int i2, double[] dArr, int[] iArr, double[] dArr2) {
        int i3;
        int i4;
        int i5;
        int i6 = iArr[0];
        if (i > (i6 << 2)) {
            int i7 = i >> 2;
            makewt(i7, iArr, dArr2);
            i3 = i7;
        } else {
            i3 = i6;
        }
        int i8 = iArr[1];
        if (i > i8) {
            makect(i, iArr, dArr2, i3);
            i4 = i;
        } else {
            i4 = i8;
        }
        if (i2 < 0) {
            double d = dArr[i - 1];
            for (int i9 = i - 2; i9 >= 2; i9 -= 2) {
                int i10 = i9 - 1;
                dArr[i9 + 1] = dArr[i9] - dArr[i10];
                dArr[i9] = dArr[i9] + dArr[i10];
            }
            dArr[1] = dArr[0] - d;
            dArr[0] = dArr[0] + d;
            if (i > 4) {
                int i11 = i3;
                rftbsub(i, dArr, i4, dArr2, i11);
                i5 = 2;
                cftbsub(i, dArr, iArr, 2, i11, dArr2);
            } else {
                i5 = 2;
                if (i == 4) {
                    cftbsub(i, dArr, iArr, 2, i3, dArr2);
                }
            }
        } else {
            i5 = 2;
        }
        dctsub(i, dArr, i4, dArr2, i3);
        if (i2 >= 0) {
            if (i > 4) {
                int i12 = i3;
                cftfsub(i, dArr, iArr, 2, i12, dArr2);
                rftfsub(i, dArr, i4, dArr2, i12);
            } else if (i == 4) {
                cftfsub(i, dArr, iArr, 2, i3, dArr2);
            }
            double d2 = dArr[0] - dArr[1];
            dArr[0] = dArr[0] + dArr[1];
            for (int i13 = i5; i13 < i; i13 += 2) {
                int i14 = i13 + 1;
                dArr[i13 - 1] = dArr[i13] - dArr[i14];
                dArr[i13] = dArr[i13] + dArr[i14];
            }
            dArr[i - 1] = d2;
        }
    }

    public void ddst(int i, int i2, double[] dArr, int[] iArr, double[] dArr2) {
        int i3;
        int i4;
        int i5;
        int i6 = iArr[0];
        if (i > (i6 << 2)) {
            int i7 = i >> 2;
            makewt(i7, iArr, dArr2);
            i3 = i7;
        } else {
            i3 = i6;
        }
        int i8 = iArr[1];
        if (i > i8) {
            makect(i, iArr, dArr2, i3);
            i4 = i;
        } else {
            i4 = i8;
        }
        if (i2 < 0) {
            double d = dArr[i - 1];
            for (int i9 = i - 2; i9 >= 2; i9 -= 2) {
                int i10 = i9 - 1;
                dArr[i9 + 1] = (-dArr[i9]) - dArr[i10];
                dArr[i9] = dArr[i9] - dArr[i10];
            }
            dArr[1] = dArr[0] + d;
            dArr[0] = dArr[0] - d;
            if (i > 4) {
                int i11 = i3;
                rftbsub(i, dArr, i4, dArr2, i11);
                i5 = 2;
                cftbsub(i, dArr, iArr, 2, i11, dArr2);
            } else {
                i5 = 2;
                if (i == 4) {
                    cftbsub(i, dArr, iArr, 2, i3, dArr2);
                }
            }
        } else {
            i5 = 2;
        }
        dstsub(i, dArr, i4, dArr2, i3);
        if (i2 >= 0) {
            if (i > 4) {
                int i12 = i3;
                cftfsub(i, dArr, iArr, 2, i12, dArr2);
                rftfsub(i, dArr, i4, dArr2, i12);
            } else if (i == 4) {
                cftfsub(i, dArr, iArr, 2, i3, dArr2);
            }
            double d2 = dArr[0] - dArr[1];
            dArr[0] = dArr[0] + dArr[1];
            for (int i13 = i5; i13 < i; i13 += 2) {
                int i14 = i13 + 1;
                dArr[i13 - 1] = (-dArr[i13]) - dArr[i14];
                dArr[i13] = dArr[i13] - dArr[i14];
            }
            dArr[i - 1] = -d2;
        }
    }

    public void dfct(int i, double[] dArr, double[] dArr2, int[] iArr, double[] dArr3) {
        int i2;
        int i3;
        int i4;
        int i5;
        int i6 = iArr[0];
        if (i > (i6 << 3)) {
            int i7 = i >> 3;
            makewt(i7, iArr, dArr3);
            i2 = i7;
        } else {
            i2 = i6;
        }
        int i8 = iArr[1];
        if (i > (i8 << 1)) {
            int i9 = i >> 1;
            makect(i9, iArr, dArr3, i2);
            i3 = i9;
        } else {
            i3 = i8;
        }
        int i10 = i >> 1;
        double d = dArr[i10];
        double d2 = dArr[0] + dArr[i];
        dArr[0] = dArr[0] - dArr[i];
        dArr2[0] = d2 - d;
        dArr2[i10] = d2 + d;
        if (i > 2) {
            int i11 = i10 >> 1;
            for (int i12 = 1; i12 < i11; i12++) {
                int i13 = i10 - i12;
                int i14 = i - i12;
                double d3 = dArr[i12] - dArr[i14];
                double d4 = dArr[i12] + dArr[i14];
                int i15 = i - i13;
                double d5 = dArr[i13] - dArr[i15];
                double d6 = dArr[i13] + dArr[i15];
                dArr[i12] = d3;
                dArr[i13] = d5;
                dArr2[i12] = d4 - d6;
                dArr2[i13] = d4 + d6;
            }
            int i16 = i - i11;
            dArr2[i11] = dArr[i11] + dArr[i16];
            dArr[i11] = dArr[i11] - dArr[i16];
            dctsub(i10, dArr, i3, dArr3, i2);
            if (i10 > 4) {
                i4 = 4;
                int i17 = i2;
                cftfsub(i10, dArr, iArr, 2, i17, dArr3);
                rftfsub(i10, dArr, i3, dArr3, i17);
            } else {
                i4 = 4;
                if (i10 == 4) {
                    cftfsub(i10, dArr, iArr, 2, i2, dArr3);
                }
            }
            dArr[i - 1] = dArr[0] - dArr[1];
            dArr[1] = dArr[0] + dArr[1];
            int i18 = 2;
            for (int i19 = i10 - 2; i19 >= 2; i19 -= 2) {
                int i20 = i19 * 2;
                int i21 = i19 + 1;
                dArr[i20 + 1] = dArr[i19] + dArr[i21];
                dArr[i20 - 1] = dArr[i19] - dArr[i21];
            }
            int i22 = i11;
            int i23 = 2;
            while (i22 >= i18) {
                dctsub(i22, dArr2, i3, dArr3, i2);
                if (i22 > i4) {
                    int i24 = i22;
                    int i25 = i2;
                    i5 = i18;
                    cftfsub(i24, dArr2, iArr, 2, i25, dArr3);
                    rftfsub(i24, dArr2, i3, dArr3, i25);
                } else {
                    i5 = i18;
                    if (i22 == i4) {
                        cftfsub(i22, dArr2, iArr, 2, i2, dArr3);
                    }
                }
                dArr[i - i23] = dArr2[0] - dArr2[1];
                dArr[i23] = dArr2[0] + dArr2[1];
                int i26 = 0;
                for (int i27 = i5; i27 < i22; i27 += 2) {
                    i26 += i23 << 2;
                    int i28 = i27 + 1;
                    dArr[i26 - i23] = dArr2[i27] - dArr2[i28];
                    dArr[i26 + i23] = dArr2[i27] + dArr2[i28];
                }
                i23 <<= 1;
                int i29 = i22 >> 1;
                for (int i30 = 0; i30 < i29; i30++) {
                    int i31 = i22 - i30;
                    int i32 = i22 + i31;
                    int i33 = i22 + i30;
                    dArr2[i30] = dArr2[i32] - dArr2[i33];
                    dArr2[i31] = dArr2[i32] + dArr2[i33];
                }
                dArr2[i29] = dArr2[i22 + i29];
                i22 = i29;
                i18 = i5;
            }
            int i34 = i18;
            dArr[i23] = dArr2[0];
            dArr[i] = dArr2[i34] - dArr2[1];
            dArr[0] = dArr2[i34] + dArr2[1];
            return;
        }
        dArr[1] = dArr[0];
        dArr[2] = dArr2[0];
        dArr[0] = dArr2[1];
    }

    public void dfst(int i, double[] dArr, double[] dArr2, int[] iArr, double[] dArr3) {
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7 = iArr[0];
        if (i > (i7 << 3)) {
            int i8 = i >> 3;
            makewt(i8, iArr, dArr3);
            i2 = i8;
        } else {
            i2 = i7;
        }
        int i9 = iArr[1];
        if (i > (i9 << 1)) {
            int i10 = i >> 1;
            makect(i10, iArr, dArr3, i2);
            i3 = i10;
        } else {
            i3 = i9;
        }
        if (i > 2) {
            int i11 = i >> 1;
            int i12 = i11 >> 1;
            for (int i13 = 1; i13 < i12; i13++) {
                int i14 = i11 - i13;
                int i15 = i - i13;
                double d = dArr[i13] + dArr[i15];
                double d2 = dArr[i13] - dArr[i15];
                int i16 = i - i14;
                double d3 = dArr[i14] + dArr[i16];
                double d4 = dArr[i14] - dArr[i16];
                dArr[i13] = d;
                dArr[i14] = d3;
                dArr2[i13] = d2 + d4;
                dArr2[i14] = d2 - d4;
            }
            int i17 = i - i12;
            dArr2[0] = dArr[i12] - dArr[i17];
            dArr[i12] = dArr[i12] + dArr[i17];
            dArr[0] = dArr[i11];
            dstsub(i11, dArr, i3, dArr3, i2);
            if (i11 > 4) {
                i4 = 4;
                int i18 = i2;
                i5 = i11;
                cftfsub(i11, dArr, iArr, 2, i18, dArr3);
                rftfsub(i5, dArr, i3, dArr3, i18);
            } else {
                i4 = 4;
                if (i11 == 4) {
                    i5 = i11;
                    cftfsub(i11, dArr, iArr, 2, i2, dArr3);
                } else {
                    i5 = i11;
                }
            }
            dArr[i - 1] = dArr[1] - dArr[0];
            dArr[1] = dArr[0] + dArr[1];
            int i19 = i5 - 2;
            for (int i20 = 2; i19 >= i20; i20 = 2) {
                int i21 = i19 * 2;
                int i22 = i19 + 1;
                dArr[i21 + 1] = dArr[i19] - dArr[i22];
                dArr[i21 - 1] = (-dArr[i19]) - dArr[i22];
                i19 -= 2;
            }
            int i23 = i12;
            int i24 = 2;
            int i25 = 2;
            while (i23 >= i24) {
                int i26 = i24;
                dstsub(i23, dArr2, i3, dArr3, i2);
                if (i23 > i4) {
                    int i27 = i2;
                    int i28 = i23;
                    cftfsub(i23, dArr2, iArr, 2, i27, dArr3);
                    rftfsub(i28, dArr2, i3, dArr3, i27);
                    i6 = i28;
                } else if (i23 == i4) {
                    i6 = i23;
                    cftfsub(i23, dArr2, iArr, 2, i2, dArr3);
                } else {
                    i6 = i23;
                }
                dArr[i - i25] = dArr2[1] - dArr2[0];
                dArr[i25] = dArr2[0] + dArr2[1];
                int i29 = 0;
                for (int i30 = i26; i30 < i6; i30 += 2) {
                    i29 += i25 << 2;
                    int i31 = i30 + 1;
                    dArr[i29 - i25] = (-dArr2[i30]) - dArr2[i31];
                    dArr[i29 + i25] = dArr2[i30] - dArr2[i31];
                }
                i25 <<= 1;
                i23 = i6 >> 1;
                for (int i32 = 1; i32 < i23; i32++) {
                    int i33 = i6 - i32;
                    int i34 = i6 + i33;
                    int i35 = i6 + i32;
                    dArr2[i32] = dArr2[i34] + dArr2[i35];
                    dArr2[i33] = dArr2[i34] - dArr2[i35];
                }
                dArr2[0] = dArr2[i6 + i23];
                i24 = i26;
                i4 = 4;
            }
            dArr[i25] = dArr2[0];
        }
        dArr[0] = 0.0d;
    }

    private void makewt(int i, int[] iArr, double[] dArr) {
        int i2 = 0;
        iArr[0] = i;
        iArr[1] = 1;
        if (i > 2) {
            int i3 = i >> 1;
            double d = i3;
            double d2 = 0.7853981633974483d / d;
            double cos = Math.cos(d * d2);
            dArr[0] = 1.0d;
            dArr[1] = cos;
            if (i3 >= 4) {
                dArr[2] = 0.5d / Math.cos(2.0d * d2);
                dArr[3] = 0.5d / Math.cos(6.0d * d2);
            }
            for (int i4 = 4; i4 < i3; i4 += 4) {
                double d3 = i4;
                double d4 = d2 * d3;
                dArr[i4] = Math.cos(d4);
                dArr[i4 + 1] = Math.sin(d4);
                double d5 = 3.0d * d2 * d3;
                dArr[i4 + 2] = Math.cos(d5);
                dArr[i4 + 3] = Math.sin(d5);
            }
            while (i3 > 2) {
                int i5 = i2 + i3;
                i3 >>= 1;
                dArr[i5] = 1.0d;
                dArr[i5 + 1] = cos;
                if (i3 >= 4) {
                    double d6 = dArr[i2 + 4];
                    double d7 = dArr[i2 + 6];
                    dArr[i5 + 2] = 0.5d / d6;
                    dArr[i5 + 3] = 0.5d / d7;
                }
                for (int i6 = 4; i6 < i3; i6 += 4) {
                    int i7 = (i6 * 2) + i2;
                    double d8 = dArr[i7];
                    double d9 = dArr[i7 + 1];
                    double d10 = dArr[i7 + 2];
                    double d11 = dArr[i7 + 3];
                    int i8 = i5 + i6;
                    dArr[i8] = d8;
                    dArr[i8 + 1] = d9;
                    dArr[i8 + 2] = d10;
                    dArr[i8 + 3] = d11;
                }
                i2 = i5;
            }
        }
    }

    private void makect(int i, int[] iArr, double[] dArr, int i2) {
        iArr[1] = i;
        if (i > 1) {
            int i3 = i >> 1;
            double d = i3;
            double d2 = 0.7853981633974483d / d;
            int i4 = i2 + 0;
            dArr[i4] = Math.cos(d * d2);
            dArr[i2 + i3] = dArr[i4] * 0.5d;
            for (int i5 = 1; i5 < i3; i5++) {
                double d3 = i5 * d2;
                dArr[i2 + i5] = Math.cos(d3) * 0.5d;
                dArr[(i2 + i) - i5] = Math.sin(d3) * 0.5d;
            }
        }
    }

    private void cftfsub(int i, double[] dArr, int[] iArr, int i2, int i3, double[] dArr2) {
        if (i > 32) {
            int i4 = i >> 2;
            cftf1st(i, dArr, dArr2, i3 - i4);
            if (i > 512) {
                cftrec1(i4, dArr, 0, i3, dArr2);
                cftrec2(i4, dArr, i4, i3, dArr2);
                cftrec1(i4, dArr, i4 * 2, i3, dArr2);
                cftrec1(i4, dArr, i4 * 3, i3, dArr2);
            } else if (i4 > 32) {
                cftexp1(i, dArr, 0, i3, dArr2);
            } else {
                cftfx41(i, dArr, 0, i3, dArr2);
            }
            bitrv2(i, iArr, i2, dArr);
        } else if (i > 8) {
            if (i == 32) {
                cftf161(dArr, 0, dArr2, i3 - 8);
                bitrv216(dArr);
                return;
            }
            cftf081(dArr, 0, dArr2, 0);
            bitrv208(dArr);
        } else if (i == 8) {
            cftf040(dArr);
        } else if (i == 4) {
            cftx020(dArr);
        }
    }

    private void cftbsub(int i, double[] dArr, int[] iArr, int i2, int i3, double[] dArr2) {
        if (i > 32) {
            int i4 = i >> 2;
            cftb1st(i, dArr, dArr2, i3 - i4);
            if (i > 512) {
                cftrec1(i4, dArr, 0, i3, dArr2);
                cftrec2(i4, dArr, i4, i3, dArr2);
                cftrec1(i4, dArr, i4 * 2, i3, dArr2);
                cftrec1(i4, dArr, i4 * 3, i3, dArr2);
            } else if (i4 > 32) {
                cftexp1(i, dArr, 0, i3, dArr2);
            } else {
                cftfx41(i, dArr, 0, i3, dArr2);
            }
            bitrv2conj(i, iArr, i2, dArr);
        } else if (i > 8) {
            if (i == 32) {
                cftf161(dArr, 0, dArr2, i3 - 8);
                bitrv216neg(dArr);
                return;
            }
            cftf081(dArr, 0, dArr2, 0);
            bitrv208neg(dArr);
        } else if (i == 8) {
            cftb040(dArr);
        } else if (i == 4) {
            cftx020(dArr);
        }
    }

    private final void bitrv2(int i, int[] iArr, int i2, double[] dArr) {
        int i3;
        iArr[i2 + 0] = 0;
        int i4 = i;
        int i5 = 1;
        while (true) {
            i3 = i5 << 3;
            if (i3 >= i4) {
                break;
            }
            i4 >>= 1;
            for (int i6 = 0; i6 < i5; i6++) {
                iArr[i2 + i5 + i6] = iArr[i2 + i6] + i4;
            }
            i5 <<= 1;
        }
        int i7 = i5 * 2;
        if (i3 != i4) {
            for (int i8 = 1; i8 < i5; i8++) {
                for (int i9 = 0; i9 < i8; i9++) {
                    int i10 = (i9 * 2) + iArr[i2 + i8];
                    int i11 = (i8 * 2) + iArr[i2 + i9];
                    double d = dArr[i10];
                    int i12 = i10 + 1;
                    double d2 = dArr[i12];
                    double d3 = dArr[i11];
                    int i13 = i11 + 1;
                    double d4 = dArr[i13];
                    dArr[i10] = d3;
                    dArr[i12] = d4;
                    dArr[i11] = d;
                    dArr[i13] = d2;
                    int i14 = i10 + i7;
                    int i15 = i11 + i7;
                    double d5 = dArr[i14];
                    int i16 = i14 + 1;
                    double d6 = dArr[i16];
                    double d7 = dArr[i15];
                    int i17 = i15 + 1;
                    double d8 = dArr[i17];
                    dArr[i14] = d7;
                    dArr[i16] = d8;
                    dArr[i15] = d5;
                    dArr[i17] = d6;
                }
            }
            return;
        }
        for (int i18 = 0; i18 < i5; i18++) {
            for (int i19 = 0; i19 < i18; i19++) {
                int i20 = (i19 * 2) + iArr[i2 + i18];
                int i21 = (i18 * 2) + iArr[i2 + i19];
                double d9 = dArr[i20];
                int i22 = i20 + 1;
                double d10 = dArr[i22];
                double d11 = dArr[i21];
                int i23 = i21 + 1;
                double d12 = dArr[i23];
                dArr[i20] = d11;
                dArr[i22] = d12;
                dArr[i21] = d9;
                dArr[i23] = d10;
                int i24 = i20 + i7;
                int i25 = i7 * 2;
                int i26 = i21 + i25;
                double d13 = dArr[i24];
                int i27 = i24 + 1;
                double d14 = dArr[i27];
                double d15 = dArr[i26];
                int i28 = i26 + 1;
                double d16 = dArr[i28];
                dArr[i24] = d15;
                dArr[i27] = d16;
                dArr[i26] = d13;
                dArr[i28] = d14;
                int i29 = i24 + i7;
                int i30 = i26 - i7;
                double d17 = dArr[i29];
                int i31 = i29 + 1;
                double d18 = dArr[i31];
                double d19 = dArr[i30];
                int i32 = i30 + 1;
                double d20 = dArr[i32];
                dArr[i29] = d19;
                dArr[i31] = d20;
                dArr[i30] = d17;
                dArr[i32] = d18;
                int i33 = i29 + i7;
                int i34 = i30 + i25;
                double d21 = dArr[i33];
                int i35 = i33 + 1;
                double d22 = dArr[i35];
                double d23 = dArr[i34];
                int i36 = i34 + 1;
                double d24 = dArr[i36];
                dArr[i33] = d23;
                dArr[i35] = d24;
                dArr[i34] = d21;
                dArr[i36] = d22;
            }
            int i37 = (i18 * 2) + i7 + iArr[i2 + i18];
            int i38 = i37 + i7;
            double d25 = dArr[i37];
            int i39 = i37 + 1;
            double d26 = dArr[i39];
            double d27 = dArr[i38];
            int i40 = i38 + 1;
            double d28 = dArr[i40];
            dArr[i37] = d27;
            dArr[i39] = d28;
            dArr[i38] = d25;
            dArr[i40] = d26;
        }
    }

    private final void bitrv2conj(int i, int[] iArr, int i2, double[] dArr) {
        int i3;
        int i4 = 0;
        iArr[i2 + 0] = 0;
        int i5 = i;
        int i6 = 1;
        while (true) {
            i3 = i6 << 3;
            if (i3 >= i5) {
                break;
            }
            i5 >>= 1;
            for (int i7 = 0; i7 < i6; i7++) {
                iArr[i2 + i6 + i7] = iArr[i2 + i7] + i5;
            }
            i6 <<= 1;
        }
        int i8 = i6 * 2;
        if (i3 != i5) {
            int i9 = i6;
            dArr[1] = -dArr[1];
            int i10 = i8 + 1;
            dArr[i10] = -dArr[i10];
            for (int i11 = 1; i11 < i9; i11++) {
                for (int i12 = 0; i12 < i11; i12++) {
                    int i13 = (i12 * 2) + iArr[i2 + i11];
                    int i14 = (i11 * 2) + iArr[i2 + i12];
                    double d = dArr[i13];
                    int i15 = i13 + 1;
                    int i16 = i14 + 1;
                    dArr[i13] = dArr[i14];
                    dArr[i15] = -dArr[i16];
                    dArr[i14] = d;
                    dArr[i16] = -dArr[i15];
                    int i17 = i13 + i8;
                    int i18 = i14 + i8;
                    double d2 = dArr[i17];
                    int i19 = i17 + 1;
                    int i20 = i18 + 1;
                    dArr[i17] = dArr[i18];
                    dArr[i19] = -dArr[i20];
                    dArr[i18] = d2;
                    dArr[i20] = -dArr[i19];
                }
                int i21 = (i11 * 2) + iArr[i2 + i11];
                int i22 = i21 + 1;
                dArr[i22] = -dArr[i22];
                int i23 = i21 + i8 + 1;
                dArr[i23] = -dArr[i23];
            }
            return;
        }
        int i24 = 0;
        while (i24 < i6) {
            int i25 = i4;
            while (i25 < i24) {
                int i26 = (i25 * 2) + iArr[i2 + i24];
                int i27 = (i24 * 2) + iArr[i2 + i25];
                double d3 = dArr[i26];
                int i28 = i26 + 1;
                int i29 = i27 + 1;
                int i30 = i6;
                dArr[i26] = dArr[i27];
                dArr[i28] = -dArr[i29];
                dArr[i27] = d3;
                dArr[i29] = -dArr[i28];
                int i31 = i26 + i8;
                int i32 = i8 * 2;
                int i33 = i27 + i32;
                double d4 = dArr[i31];
                int i34 = i31 + 1;
                int i35 = i33 + 1;
                dArr[i31] = dArr[i33];
                dArr[i34] = -dArr[i35];
                dArr[i33] = d4;
                dArr[i35] = -dArr[i34];
                int i36 = i31 + i8;
                int i37 = i33 - i8;
                double d5 = dArr[i36];
                int i38 = i36 + 1;
                int i39 = i37 + 1;
                dArr[i36] = dArr[i37];
                dArr[i38] = -dArr[i39];
                dArr[i37] = d5;
                dArr[i39] = -dArr[i38];
                int i40 = i36 + i8;
                int i41 = i37 + i32;
                double d6 = dArr[i40];
                int i42 = i40 + 1;
                int i43 = i41 + 1;
                dArr[i40] = dArr[i41];
                dArr[i42] = -dArr[i43];
                dArr[i41] = d6;
                dArr[i43] = -dArr[i42];
                i25++;
                i6 = i30;
            }
            int i44 = i6;
            int i45 = (i24 * 2) + iArr[i2 + i24];
            int i46 = i45 + 1;
            dArr[i46] = -dArr[i46];
            int i47 = i45 + i8;
            int i48 = i47 + i8;
            double d7 = dArr[i47];
            int i49 = i47 + 1;
            int i50 = i48 + 1;
            dArr[i47] = dArr[i48];
            dArr[i49] = -dArr[i50];
            dArr[i48] = d7;
            dArr[i50] = -dArr[i49];
            int i51 = i48 + i8 + 1;
            dArr[i51] = -dArr[i51];
            i24++;
            i6 = i44;
            i4 = 0;
        }
    }

    private void bitrv216(double[] dArr) {
        double d = dArr[2];
        double d2 = dArr[3];
        double d3 = dArr[4];
        double d4 = dArr[5];
        double d5 = dArr[6];
        double d6 = dArr[7];
        double d7 = dArr[8];
        double d8 = dArr[9];
        double d9 = dArr[10];
        double d10 = dArr[11];
        double d11 = dArr[14];
        double d12 = dArr[15];
        double d13 = dArr[16];
        double d14 = dArr[17];
        double d15 = dArr[20];
        double d16 = dArr[21];
        double d17 = dArr[22];
        double d18 = dArr[23];
        double d19 = dArr[24];
        double d20 = dArr[25];
        double d21 = dArr[26];
        double d22 = dArr[27];
        double d23 = dArr[28];
        double d24 = dArr[29];
        dArr[2] = d13;
        dArr[3] = d14;
        dArr[4] = d7;
        dArr[5] = d8;
        dArr[6] = d19;
        dArr[7] = d20;
        dArr[8] = d3;
        dArr[9] = d4;
        dArr[10] = d15;
        dArr[11] = d16;
        dArr[14] = d23;
        dArr[15] = d24;
        dArr[16] = d;
        dArr[17] = d2;
        dArr[20] = d9;
        dArr[21] = d10;
        dArr[22] = d21;
        dArr[23] = d22;
        dArr[24] = d5;
        dArr[25] = d6;
        dArr[26] = d17;
        dArr[27] = d18;
        dArr[28] = d11;
        dArr[29] = d12;
    }

    private void bitrv216neg(double[] dArr) {
        double d = dArr[2];
        double d2 = dArr[3];
        double d3 = dArr[4];
        double d4 = dArr[5];
        double d5 = dArr[6];
        double d6 = dArr[7];
        double d7 = dArr[8];
        double d8 = dArr[9];
        double d9 = dArr[10];
        double d10 = dArr[11];
        double d11 = dArr[12];
        double d12 = dArr[13];
        double d13 = dArr[14];
        double d14 = dArr[15];
        double d15 = dArr[16];
        double d16 = dArr[17];
        double d17 = dArr[18];
        double d18 = dArr[19];
        double d19 = dArr[20];
        double d20 = dArr[21];
        double d21 = dArr[22];
        double d22 = dArr[23];
        double d23 = dArr[24];
        double d24 = dArr[25];
        double d25 = dArr[26];
        double d26 = dArr[27];
        double d27 = dArr[28];
        double d28 = dArr[29];
        double d29 = dArr[30];
        double d30 = dArr[31];
        dArr[2] = d29;
        dArr[3] = d30;
        dArr[4] = d13;
        dArr[5] = d14;
        dArr[6] = d21;
        dArr[7] = d22;
        dArr[8] = d5;
        dArr[9] = d6;
        dArr[10] = d25;
        dArr[11] = d26;
        dArr[12] = d9;
        dArr[13] = d10;
        dArr[14] = d17;
        dArr[15] = d18;
        dArr[16] = d;
        dArr[17] = d2;
        dArr[18] = d27;
        dArr[19] = d28;
        dArr[20] = d11;
        dArr[21] = d12;
        dArr[22] = d19;
        dArr[23] = d20;
        dArr[24] = d3;
        dArr[25] = d4;
        dArr[26] = d23;
        dArr[27] = d24;
        dArr[28] = d7;
        dArr[29] = d8;
        dArr[30] = d15;
        dArr[31] = d16;
    }

    private void bitrv208(double[] dArr) {
        double d = dArr[2];
        double d2 = dArr[3];
        double d3 = dArr[6];
        double d4 = dArr[7];
        double d5 = dArr[8];
        double d6 = dArr[9];
        double d7 = dArr[12];
        double d8 = dArr[13];
        dArr[2] = d5;
        dArr[3] = d6;
        dArr[6] = d7;
        dArr[7] = d8;
        dArr[8] = d;
        dArr[9] = d2;
        dArr[12] = d3;
        dArr[13] = d4;
    }

    private void bitrv208neg(double[] dArr) {
        double d = dArr[2];
        double d2 = dArr[3];
        double d3 = dArr[4];
        double d4 = dArr[5];
        double d5 = dArr[6];
        double d6 = dArr[7];
        double d7 = dArr[8];
        double d8 = dArr[9];
        double d9 = dArr[10];
        double d10 = dArr[11];
        double d11 = dArr[12];
        double d12 = dArr[13];
        double d13 = dArr[14];
        double d14 = dArr[15];
        dArr[2] = d13;
        dArr[3] = d14;
        dArr[4] = d5;
        dArr[5] = d6;
        dArr[6] = d9;
        dArr[7] = d10;
        dArr[8] = d;
        dArr[9] = d2;
        dArr[10] = d11;
        dArr[11] = d12;
        dArr[12] = d3;
        dArr[13] = d4;
        dArr[14] = d7;
        dArr[15] = d8;
    }

    private void cftf1st(int i, double[] dArr, double[] dArr2, int i2) {
        int i3 = i >> 3;
        int i4 = i3 * 2;
        int i5 = i4 + i4;
        int i6 = i5 + i4;
        int i7 = 0;
        double d = dArr[0] + dArr[i5];
        int i8 = i5 + 1;
        double d2 = dArr[1] + dArr[i8];
        double d3 = dArr[0] - dArr[i5];
        double d4 = dArr[1] - dArr[i8];
        double d5 = dArr[i4] + dArr[i6];
        int i9 = i4 + 1;
        int i10 = i6 + 1;
        double d6 = dArr[i9] + dArr[i10];
        double d7 = dArr[i4] - dArr[i6];
        double d8 = dArr[i9] - dArr[i10];
        dArr[0] = d + d5;
        dArr[1] = d2 + d6;
        dArr[i4] = d - d5;
        dArr[i9] = d2 - d6;
        dArr[i5] = d3 - d8;
        dArr[i8] = d4 + d7;
        dArr[i6] = d3 + d8;
        dArr[i10] = d4 - d7;
        double d9 = dArr2[i2 + 1];
        double d10 = dArr2[i2 + 2];
        double d11 = dArr2[i2 + 3];
        double d12 = 0.0d;
        double d13 = 1.0d;
        int i11 = 2;
        double d14 = 0.0d;
        double d15 = 1.0d;
        while (true) {
            double d16 = d9;
            int i12 = i3 - 2;
            if (i11 < i12) {
                i7 += 4;
                int i13 = i2 + i7;
                double d17 = (d13 + dArr2[i13]) * d10;
                int i14 = i13 + 1;
                double d18 = (d12 + dArr2[i14]) * d10;
                int i15 = i13 + 2;
                double d19 = (d15 + dArr2[i15]) * d11;
                int i16 = i13 + 3;
                double d20 = (d14 - dArr2[i16]) * d11;
                double d21 = dArr2[i13];
                double d22 = dArr2[i14];
                double d23 = dArr2[i15];
                double d24 = d11;
                double d25 = -dArr2[i16];
                int i17 = i11 + i4;
                int i18 = i17 + i4;
                int i19 = i18 + i4;
                double d26 = dArr[i11] + dArr[i18];
                int i20 = i11 + 1;
                int i21 = i18 + 1;
                double d27 = dArr[i20] + dArr[i21];
                double d28 = dArr[i11] - dArr[i18];
                double d29 = dArr[i20] - dArr[i21];
                int i22 = i11 + 2;
                int i23 = i18 + 2;
                double d30 = dArr[i22] + dArr[i23];
                int i24 = i11 + 3;
                int i25 = i18 + 3;
                double d31 = dArr[i24] + dArr[i25];
                double d32 = dArr[i22] - dArr[i23];
                double d33 = dArr[i24] - dArr[i25];
                double d34 = dArr[i17] + dArr[i19];
                int i26 = i17 + 1;
                int i27 = i19 + 1;
                double d35 = dArr[i26] + dArr[i27];
                double d36 = dArr[i17] - dArr[i19];
                double d37 = dArr[i26] - dArr[i27];
                int i28 = i17 + 2;
                int i29 = i19 + 2;
                double d38 = dArr[i28] + dArr[i29];
                int i30 = i17 + 3;
                int i31 = i19 + 3;
                double d39 = dArr[i30] + dArr[i31];
                double d40 = dArr[i28] - dArr[i29];
                double d41 = dArr[i30] - dArr[i31];
                dArr[i11] = d26 + d34;
                dArr[i20] = d27 + d35;
                dArr[i22] = d30 + d38;
                dArr[i24] = d31 + d39;
                dArr[i17] = d26 - d34;
                dArr[i26] = d27 - d35;
                dArr[i28] = d30 - d38;
                dArr[i30] = d31 - d39;
                double d42 = d28 - d37;
                double d43 = d29 + d36;
                dArr[i18] = (d17 * d42) - (d18 * d43);
                dArr[i21] = (d43 * d17) + (d42 * d18);
                double d44 = d32 - d41;
                double d45 = d33 + d40;
                dArr[i23] = (d21 * d44) - (d22 * d45);
                dArr[i25] = (d45 * d21) + (d44 * d22);
                double d46 = d28 + d37;
                double d47 = d29 - d36;
                dArr[i19] = (d19 * d46) + (d20 * d47);
                dArr[i27] = (d47 * d19) - (d46 * d20);
                double d48 = d32 + d41;
                double d49 = d33 - d40;
                dArr[i29] = (d23 * d48) + (d25 * d49);
                dArr[i31] = (d49 * d23) - (d48 * d25);
                int i32 = i4 - i11;
                int i33 = i32 + i4;
                int i34 = i33 + i4;
                int i35 = i34 + i4;
                double d50 = dArr[i32] + dArr[i34];
                int i36 = i32 + 1;
                int i37 = i34 + 1;
                double d51 = dArr[i36] + dArr[i37];
                double d52 = dArr[i32] - dArr[i34];
                double d53 = dArr[i36] - dArr[i37];
                int i38 = i32 - 2;
                int i39 = i34 - 2;
                double d54 = dArr[i38] + dArr[i39];
                int i40 = i32 - 1;
                int i41 = i34 - 1;
                double d55 = dArr[i40] + dArr[i41];
                double d56 = dArr[i38] - dArr[i39];
                double d57 = dArr[i40] - dArr[i41];
                double d58 = dArr[i33] + dArr[i35];
                int i42 = i33 + 1;
                int i43 = i35 + 1;
                double d59 = dArr[i42] + dArr[i43];
                double d60 = dArr[i33] - dArr[i35];
                double d61 = dArr[i42] - dArr[i43];
                int i44 = i33 - 2;
                int i45 = i35 - 2;
                double d62 = dArr[i44] + dArr[i45];
                int i46 = i33 - 1;
                int i47 = i35 - 1;
                double d63 = dArr[i46] + dArr[i47];
                double d64 = dArr[i44] - dArr[i45];
                double d65 = dArr[i46] - dArr[i47];
                dArr[i32] = d50 + d58;
                dArr[i36] = d51 + d59;
                dArr[i38] = d54 + d62;
                dArr[i40] = d55 + d63;
                dArr[i33] = d50 - d58;
                dArr[i42] = d51 - d59;
                dArr[i44] = d54 - d62;
                dArr[i46] = d55 - d63;
                double d66 = d52 - d61;
                double d67 = d53 + d60;
                dArr[i34] = (d18 * d66) - (d17 * d67);
                dArr[i37] = (d18 * d67) + (d17 * d66);
                double d68 = d56 - d65;
                double d69 = d57 + d64;
                dArr[i39] = (d22 * d68) - (d21 * d69);
                dArr[i41] = (d69 * d22) + (d68 * d21);
                double d70 = d52 + d61;
                double d71 = d53 - d60;
                dArr[i35] = (d20 * d70) + (d19 * d71);
                dArr[i43] = (d20 * d71) - (d19 * d70);
                double d72 = d56 + d65;
                double d73 = d57 - d64;
                dArr[i45] = (d25 * d72) + (d23 * d73);
                dArr[i47] = (d73 * d25) - (d72 * d23);
                i11 += 4;
                d12 = d22;
                d14 = d25;
                d9 = d16;
                d13 = d21;
                d15 = d23;
                d11 = d24;
            } else {
                double d74 = d11;
                double d75 = (d13 + d16) * d10;
                double d76 = d10 * (d12 + d16);
                double d77 = d74 * (d15 - d16);
                double d78 = d74 * (d14 - d16);
                int i48 = i3 + i4;
                int i49 = i48 + i4;
                int i50 = i4 + i49;
                int i51 = i49 - 2;
                double d79 = dArr[i12] + dArr[i51];
                int i52 = i3 - 1;
                int i53 = i49 - 1;
                double d80 = dArr[i52] + dArr[i53];
                double d81 = dArr[i12] - dArr[i51];
                double d82 = dArr[i52] - dArr[i53];
                int i54 = i48 - 2;
                int i55 = i50 - 2;
                double d83 = dArr[i54] + dArr[i55];
                int i56 = i48 - 1;
                int i57 = i50 - 1;
                double d84 = dArr[i56] + dArr[i57];
                double d85 = dArr[i54] - dArr[i55];
                double d86 = dArr[i56] - dArr[i57];
                dArr[i12] = d79 + d83;
                dArr[i52] = d80 + d84;
                dArr[i54] = d79 - d83;
                dArr[i56] = d80 - d84;
                double d87 = d81 - d86;
                double d88 = d82 + d85;
                dArr[i51] = (d75 * d87) - (d76 * d88);
                dArr[i53] = (d88 * d75) + (d87 * d76);
                double d89 = d81 + d86;
                double d90 = d82 - d85;
                dArr[i55] = (d77 * d89) + (d78 * d90);
                dArr[i57] = (d90 * d77) - (d89 * d78);
                double d91 = dArr[i3] + dArr[i49];
                int i58 = i3 + 1;
                int i59 = i49 + 1;
                double d92 = dArr[i58] + dArr[i59];
                double d93 = dArr[i3] - dArr[i49];
                double d94 = dArr[i58] - dArr[i59];
                double d95 = dArr[i48] + dArr[i50];
                int i60 = i48 + 1;
                int i61 = i50 + 1;
                double d96 = dArr[i60] + dArr[i61];
                double d97 = dArr[i48] - dArr[i50];
                double d98 = dArr[i60] - dArr[i61];
                dArr[i3] = d91 + d95;
                dArr[i58] = d92 + d96;
                dArr[i48] = d91 - d95;
                dArr[i60] = d92 - d96;
                double d99 = d93 - d98;
                double d100 = d94 + d97;
                dArr[i49] = (d99 - d100) * d16;
                dArr[i59] = d16 * (d100 + d99);
                double d101 = d93 + d98;
                double d102 = d94 - d97;
                double d103 = -d16;
                dArr[i50] = (d101 + d102) * d103;
                dArr[i61] = d103 * (d102 - d101);
                int i62 = i3 + 2;
                int i63 = i49 + 2;
                double d104 = dArr[i62] + dArr[i63];
                int i64 = i3 + 3;
                int i65 = i49 + 3;
                double d105 = dArr[i64] + dArr[i65];
                double d106 = dArr[i62] - dArr[i63];
                double d107 = dArr[i64] - dArr[i65];
                int i66 = i48 + 2;
                int i67 = i50 + 2;
                double d108 = dArr[i66] + dArr[i67];
                int i68 = i48 + 3;
                int i69 = i50 + 3;
                double d109 = dArr[i68] + dArr[i69];
                double d110 = dArr[i66] - dArr[i67];
                double d111 = dArr[i68] - dArr[i69];
                dArr[i62] = d104 + d108;
                dArr[i64] = d105 + d109;
                dArr[i66] = d104 - d108;
                dArr[i68] = d105 - d109;
                double d112 = d106 - d111;
                double d113 = d107 + d110;
                dArr[i63] = (d76 * d112) - (d75 * d113);
                dArr[i65] = (d76 * d113) + (d75 * d112);
                double d114 = d106 + d111;
                double d115 = d107 - d110;
                dArr[i67] = (d78 * d114) + (d77 * d115);
                dArr[i69] = (d78 * d115) - (d77 * d114);
                return;
            }
        }
    }

    private void cftrec1(int i, double[] dArr, int i2, int i3, double[] dArr2) {
        int i4 = i >> 2;
        int i5 = i4 * 2;
        cftmdl1(i, dArr, i2, dArr2, i3 - i5);
        if (i > 512) {
            cftrec1(i4, dArr, i2, i3, dArr2);
            cftrec2(i4, dArr, i2 + i4, i3, dArr2);
            cftrec1(i4, dArr, i2 + i5, i3, dArr2);
            cftrec1(i4, dArr, i2 + (i4 * 3), i3, dArr2);
            return;
        }
        cftexp1(i, dArr, i2, i3, dArr2);
    }

    private void cftrec2(int i, double[] dArr, int i2, int i3, double[] dArr2) {
        int i4 = i >> 2;
        cftmdl2(i, dArr, i2, dArr2, i3 - i);
        if (i > 512) {
            cftrec1(i4, dArr, i2, i3, dArr2);
            cftrec2(i4, dArr, i2 + i4, i3, dArr2);
            cftrec1(i4, dArr, i2 + (i4 * 2), i3, dArr2);
            cftrec2(i4, dArr, i2 + (i4 * 3), i3, dArr2);
            return;
        }
        cftexp2(i, dArr, i2, i3, dArr2);
    }

    private void cftexp1(int i, double[] dArr, int i2, int i3, double[] dArr2) {
        int i4 = i >> 2;
        while (i4 > 128) {
            for (int i5 = i4; i5 < i; i5 <<= 2) {
                for (int i6 = i5 - i4; i6 < i; i6 += i5 * 4) {
                    int i7 = i3 - (i4 >> 1);
                    int i8 = i4;
                    cftmdl1(i8, dArr, i2 + i6, dArr2, i7);
                    cftmdl2(i8, dArr, i2 + i5 + i6, dArr2, i3 - i4);
                    cftmdl1(i8, dArr, i2 + (i5 * 2) + i6, dArr2, i7);
                }
            }
            cftmdl1(i4, dArr, (i2 + i) - i4, dArr2, i3 - (i4 >> 1));
            i4 >>= 2;
        }
        for (int i9 = i4; i9 < i; i9 <<= 2) {
            for (int i10 = i9 - i4; i10 < i; i10 += i9 * 4) {
                int i11 = i2 + i10;
                int i12 = i3 - (i4 >> 1);
                cftmdl1(i4, dArr, i11, dArr2, i12);
                cftfx41(i4, dArr, i11, i3, dArr2);
                int i13 = i2 + i9 + i10;
                cftmdl2(i4, dArr, i13, dArr2, i3 - i4);
                cftfx42(i4, dArr, i13, i3, dArr2);
                int i14 = i2 + (i9 * 2) + i10;
                cftmdl1(i4, dArr, i14, dArr2, i12);
                cftfx41(i4, dArr, i14, i3, dArr2);
            }
        }
        int i15 = (i2 + i) - i4;
        cftmdl1(i4, dArr, i15, dArr2, i3 - (i4 >> 1));
        cftfx41(i4, dArr, i15, i3, dArr2);
    }

    private void cftexp2(int i, double[] dArr, int i2, int i3, double[] dArr2) {
        int i4 = i >> 1;
        int i5 = i >> 2;
        while (i5 > 128) {
            for (int i6 = i5; i6 < i4; i6 <<= 2) {
                for (int i7 = i6 - i5; i7 < i4; i7 += i6 * 2) {
                    int i8 = i3 - (i5 >> 1);
                    int i9 = i5;
                    cftmdl1(i9, dArr, i2 + i7, dArr2, i8);
                    cftmdl1(i9, dArr, i2 + i4 + i7, dArr2, i8);
                }
                for (int i10 = (i6 * 2) - i5; i10 < i4; i10 += i6 * 4) {
                    int i11 = i3 - i5;
                    int i12 = i5;
                    cftmdl2(i12, dArr, i2 + i10, dArr2, i11);
                    cftmdl2(i12, dArr, i2 + i4 + i10, dArr2, i11);
                }
            }
            i5 >>= 2;
        }
        for (int i13 = i5; i13 < i4; i13 <<= 2) {
            for (int i14 = i13 - i5; i14 < i4; i14 += i13 * 2) {
                int i15 = i2 + i14;
                int i16 = i3 - (i5 >> 1);
                int i17 = i5;
                cftmdl1(i17, dArr, i15, dArr2, i16);
                int i18 = i5;
                cftfx41(i18, dArr, i15, i3, dArr2);
                int i19 = i2 + i4 + i14;
                cftmdl1(i17, dArr, i19, dArr2, i16);
                cftfx41(i18, dArr, i19, i3, dArr2);
            }
            for (int i20 = (i13 * 2) - i5; i20 < i4; i20 += i13 * 4) {
                int i21 = i2 + i20;
                int i22 = i3 - i5;
                int i23 = i5;
                cftmdl2(i23, dArr, i21, dArr2, i22);
                int i24 = i5;
                cftfx42(i24, dArr, i21, i3, dArr2);
                int i25 = i2 + i4 + i20;
                cftmdl2(i23, dArr, i25, dArr2, i22);
                cftfx42(i24, dArr, i25, i3, dArr2);
            }
        }
    }

    private final void cftmdl1(int i, double[] dArr, int i2, double[] dArr2, int i3) {
        int i4 = i >> 3;
        int i5 = i4 * 2;
        int i6 = i5 + i5;
        int i7 = i6 + i5;
        int i8 = i2 + 0;
        int i9 = i2 + i6;
        double d = dArr[i8] + dArr[i9];
        int i10 = i2 + 1;
        int i11 = i9 + 1;
        double d2 = dArr[i10] + dArr[i11];
        double d3 = dArr[i8] - dArr[i9];
        double d4 = dArr[i10] - dArr[i11];
        int i12 = i2 + i5;
        int i13 = i2 + i7;
        double d5 = dArr[i12] + dArr[i13];
        int i14 = i12 + 1;
        int i15 = i13 + 1;
        double d6 = dArr[i14] + dArr[i15];
        double d7 = dArr[i12] - dArr[i13];
        double d8 = dArr[i14] - dArr[i15];
        dArr[i8] = d + d5;
        dArr[i10] = d2 + d6;
        dArr[i12] = d - d5;
        dArr[i14] = d2 - d6;
        dArr[i9] = d3 - d8;
        dArr[i11] = d4 + d7;
        dArr[i13] = d3 + d8;
        dArr[i15] = d4 - d7;
        double d9 = dArr2[i3 + 1];
        int i16 = 0;
        for (int i17 = 2; i17 < i4; i17 += 2) {
            i16 += 4;
            int i18 = i3 + i16;
            double d10 = dArr2[i18];
            double d11 = dArr2[i18 + 1];
            double d12 = dArr2[i18 + 2];
            double d13 = -dArr2[i18 + 3];
            int i19 = i17 + i5;
            int i20 = i19 + i5;
            int i21 = i20 + i5;
            int i22 = i2 + i17;
            int i23 = i2 + i20;
            double d14 = dArr[i22] + dArr[i23];
            int i24 = i22 + 1;
            int i25 = i23 + 1;
            double d15 = dArr[i24] + dArr[i25];
            double d16 = dArr[i22] - dArr[i23];
            double d17 = dArr[i24] - dArr[i25];
            int i26 = i2 + i19;
            int i27 = i2 + i21;
            double d18 = dArr[i26] + dArr[i27];
            int i28 = i26 + 1;
            int i29 = i27 + 1;
            double d19 = dArr[i28] + dArr[i29];
            double d20 = dArr[i26] - dArr[i27];
            double d21 = dArr[i28] - dArr[i29];
            dArr[i22] = d14 + d18;
            dArr[i24] = d15 + d19;
            dArr[i26] = d14 - d18;
            dArr[i28] = d15 - d19;
            double d22 = d16 - d21;
            double d23 = d17 + d20;
            dArr[i23] = (d10 * d22) - (d11 * d23);
            dArr[i25] = (d23 * d10) + (d22 * d11);
            double d24 = d16 + d21;
            double d25 = d17 - d20;
            dArr[i27] = (d12 * d24) + (d13 * d25);
            dArr[i29] = (d25 * d12) - (d24 * d13);
            int i30 = i5 - i17;
            int i31 = i30 + i5;
            int i32 = i31 + i5;
            int i33 = i32 + i5;
            int i34 = i2 + i30;
            int i35 = i2 + i32;
            double d26 = dArr[i34] + dArr[i35];
            int i36 = i34 + 1;
            int i37 = i35 + 1;
            double d27 = dArr[i36] + dArr[i37];
            double d28 = dArr[i34] - dArr[i35];
            double d29 = dArr[i36] - dArr[i37];
            int i38 = i2 + i31;
            int i39 = i2 + i33;
            double d30 = dArr[i38] + dArr[i39];
            int i40 = i38 + 1;
            int i41 = i39 + 1;
            double d31 = dArr[i40] + dArr[i41];
            double d32 = dArr[i38] - dArr[i39];
            double d33 = dArr[i40] - dArr[i41];
            dArr[i34] = d26 + d30;
            dArr[i36] = d27 + d31;
            dArr[i38] = d26 - d30;
            dArr[i40] = d27 - d31;
            double d34 = d28 - d33;
            double d35 = d29 + d32;
            dArr[i35] = (d11 * d34) - (d10 * d35);
            dArr[i37] = (d11 * d35) + (d10 * d34);
            double d36 = d28 + d33;
            double d37 = d29 - d32;
            dArr[i39] = (d13 * d36) + (d12 * d37);
            dArr[i41] = (d13 * d37) - (d12 * d36);
        }
        int i42 = i4 + i5;
        int i43 = i42 + i5;
        int i44 = i5 + i43;
        int i45 = i2 + i4;
        int i46 = i2 + i43;
        double d38 = dArr[i45] + dArr[i46];
        int i47 = i45 + 1;
        int i48 = i46 + 1;
        double d39 = dArr[i47] + dArr[i48];
        double d40 = dArr[i45] - dArr[i46];
        double d41 = dArr[i47] - dArr[i48];
        int i49 = i2 + i42;
        int i50 = i2 + i44;
        double d42 = dArr[i49] + dArr[i50];
        int i51 = i49 + 1;
        int i52 = i50 + 1;
        double d43 = dArr[i51] + dArr[i52];
        double d44 = dArr[i49] - dArr[i50];
        double d45 = dArr[i51] - dArr[i52];
        dArr[i45] = d38 + d42;
        dArr[i47] = d39 + d43;
        dArr[i49] = d38 - d42;
        dArr[i51] = d39 - d43;
        double d46 = d40 - d45;
        double d47 = d41 + d44;
        dArr[i46] = (d46 - d47) * d9;
        dArr[i48] = (d47 + d46) * d9;
        double d48 = d40 + d45;
        double d49 = d41 - d44;
        double d50 = -d9;
        dArr[i50] = (d48 + d49) * d50;
        dArr[i52] = d50 * (d49 - d48);
    }

    private final void cftmdl2(int i, double[] dArr, int i2, double[] dArr2, int i3) {
        int i4 = i >> 3;
        int i5 = i4 * 2;
        double d = dArr2[i3 + 1];
        int i6 = i5 + i5;
        int i7 = i6 + i5;
        int i8 = i2 + 0;
        int i9 = i2 + i6;
        int i10 = i9 + 1;
        double d2 = dArr[i8] - dArr[i10];
        int i11 = i2 + 1;
        double d3 = dArr[i11] + dArr[i9];
        double d4 = dArr[i8] + dArr[i10];
        double d5 = dArr[i11] - dArr[i9];
        int i12 = i2 + i5;
        int i13 = i2 + i7;
        int i14 = i13 + 1;
        double d6 = dArr[i12] - dArr[i14];
        int i15 = i12 + 1;
        double d7 = dArr[i15] + dArr[i13];
        double d8 = dArr[i12] + dArr[i14];
        double d9 = dArr[i15] - dArr[i13];
        double d10 = (d6 - d7) * d;
        double d11 = (d7 + d6) * d;
        dArr[i8] = d2 + d10;
        dArr[i11] = d3 + d11;
        dArr[i12] = d2 - d10;
        dArr[i15] = d3 - d11;
        double d12 = (d8 - d9) * d;
        double d13 = d * (d9 + d8);
        dArr[i9] = d4 - d13;
        dArr[i10] = d5 + d12;
        dArr[i13] = d4 + d13;
        dArr[i14] = d5 - d12;
        int i16 = i5 * 2;
        int i17 = 0;
        int i18 = 2;
        while (i18 < i4) {
            int i19 = i17 + 4;
            int i20 = i3 + i19;
            double d14 = dArr2[i20];
            double d15 = dArr2[i20 + 1];
            double d16 = dArr2[i20 + 2];
            double d17 = -dArr2[i20 + 3];
            int i21 = i16 - 4;
            int i22 = i3 + i21;
            double d18 = dArr2[i22];
            double d19 = dArr2[i22 + 1];
            double d20 = dArr2[i22 + 2];
            double d21 = -dArr2[i22 + 3];
            int i23 = i18 + i5;
            int i24 = i23 + i5;
            int i25 = i24 + i5;
            int i26 = i2 + i18;
            int i27 = i2 + i24;
            int i28 = i27 + 1;
            double d22 = dArr[i26] - dArr[i28];
            int i29 = i26 + 1;
            double d23 = dArr[i29] + dArr[i27];
            double d24 = dArr[i26] + dArr[i28];
            double d25 = dArr[i29] - dArr[i27];
            int i30 = i2 + i23;
            int i31 = i2 + i25;
            int i32 = i31 + 1;
            double d26 = dArr[i30] - dArr[i32];
            int i33 = i30 + 1;
            double d27 = dArr[i33] + dArr[i31];
            double d28 = dArr[i30] + dArr[i32];
            double d29 = dArr[i33] - dArr[i31];
            double d30 = (d14 * d22) - (d15 * d23);
            double d31 = (d23 * d14) + (d22 * d15);
            double d32 = (d19 * d26) - (d18 * d27);
            double d33 = (d27 * d19) + (d26 * d18);
            dArr[i26] = d30 + d32;
            dArr[i29] = d31 + d33;
            dArr[i30] = d30 - d32;
            dArr[i33] = d31 - d33;
            double d34 = (d16 * d24) + (d17 * d25);
            double d35 = (d25 * d16) - (d24 * d17);
            double d36 = (d21 * d28) + (d20 * d29);
            double d37 = (d29 * d21) - (d28 * d20);
            dArr[i27] = d34 + d36;
            dArr[i28] = d35 + d37;
            dArr[i31] = d34 - d36;
            dArr[i32] = d35 - d37;
            int i34 = i5 - i18;
            int i35 = i34 + i5;
            int i36 = i35 + i5;
            int i37 = i36 + i5;
            int i38 = i2 + i34;
            int i39 = i2 + i36;
            int i40 = i39 + 1;
            double d38 = dArr[i38] - dArr[i40];
            int i41 = i38 + 1;
            double d39 = dArr[i41] + dArr[i39];
            double d40 = dArr[i38] + dArr[i40];
            double d41 = dArr[i41] - dArr[i39];
            int i42 = i2 + i35;
            int i43 = i2 + i37;
            int i44 = i43 + 1;
            double d42 = dArr[i42] - dArr[i44];
            int i45 = i42 + 1;
            double d43 = dArr[i45] + dArr[i43];
            double d44 = dArr[i42] + dArr[i44];
            double d45 = dArr[i45] - dArr[i43];
            double d46 = (d18 * d38) - (d19 * d39);
            double d47 = (d18 * d39) + (d19 * d38);
            double d48 = (d15 * d42) - (d14 * d43);
            double d49 = (d15 * d43) + (d14 * d42);
            dArr[i38] = d46 + d48;
            dArr[i41] = d47 + d49;
            dArr[i42] = d46 - d48;
            dArr[i45] = d47 - d49;
            double d50 = (d20 * d40) + (d21 * d41);
            double d51 = (d20 * d41) - (d21 * d40);
            double d52 = (d17 * d44) + (d16 * d45);
            double d53 = (d17 * d45) - (d16 * d44);
            dArr[i39] = d50 + d52;
            dArr[i40] = d51 + d53;
            dArr[i43] = d50 - d52;
            dArr[i44] = d51 - d53;
            i18 += 2;
            i17 = i19;
            i16 = i21;
        }
        int i46 = i3 + i5;
        double d54 = dArr2[i46];
        double d55 = dArr2[i46 + 1];
        int i47 = i4 + i5;
        int i48 = i47 + i5;
        int i49 = i5 + i48;
        int i50 = i2 + i4;
        int i51 = i2 + i48;
        int i52 = i51 + 1;
        double d56 = dArr[i50] - dArr[i52];
        int i53 = i50 + 1;
        double d57 = dArr[i53] + dArr[i51];
        double d58 = dArr[i50] + dArr[i52];
        double d59 = dArr[i53] - dArr[i51];
        int i54 = i2 + i47;
        int i55 = i2 + i49;
        int i56 = i55 + 1;
        double d60 = dArr[i54] - dArr[i56];
        int i57 = i54 + 1;
        double d61 = dArr[i57] + dArr[i55];
        double d62 = dArr[i54] + dArr[i56];
        double d63 = dArr[i57] - dArr[i55];
        double d64 = (d54 * d56) - (d55 * d57);
        double d65 = (d57 * d54) + (d56 * d55);
        double d66 = (d55 * d60) - (d54 * d61);
        double d67 = (d61 * d55) + (d60 * d54);
        dArr[i50] = d64 + d66;
        dArr[i53] = d65 + d67;
        dArr[i54] = d64 - d66;
        dArr[i57] = d65 - d67;
        double d68 = (d55 * d58) - (d54 * d59);
        double d69 = (d59 * d55) + (d58 * d54);
        double d70 = (d54 * d62) - (d55 * d63);
        double d71 = (d54 * d63) + (d55 * d62);
        dArr[i51] = d68 - d70;
        dArr[i52] = d69 - d71;
        dArr[i55] = d68 + d70;
        dArr[i56] = d69 + d71;
    }

    private void cftfx41(int i, double[] dArr, int i2, int i3, double[] dArr2) {
        if (i == 128) {
            int i4 = i3 - 8;
            cftf161(dArr, i2, dArr2, i4);
            cftf162(dArr, i2 + 32, dArr2, i3 - 32);
            cftf161(dArr, i2 + 64, dArr2, i4);
            cftf161(dArr, i2 + 96, dArr2, i4);
            return;
        }
        int i5 = i3 - 16;
        cftf081(dArr, i2, dArr2, i5);
        cftf082(dArr, i2 + 16, dArr2, i5);
        cftf081(dArr, i2 + 32, dArr2, i5);
        cftf081(dArr, i2 + 48, dArr2, i5);
    }

    private void cftfx42(int i, double[] dArr, int i2, int i3, double[] dArr2) {
        if (i == 128) {
            int i4 = i3 - 8;
            cftf161(dArr, i2, dArr2, i4);
            int i5 = i3 - 32;
            cftf162(dArr, i2 + 32, dArr2, i5);
            cftf161(dArr, i2 + 64, dArr2, i4);
            cftf162(dArr, i2 + 96, dArr2, i5);
            return;
        }
        int i6 = i3 - 16;
        cftf081(dArr, i2, dArr2, i6);
        cftf082(dArr, i2 + 16, dArr2, i6);
        cftf081(dArr, i2 + 32, dArr2, i6);
        cftf082(dArr, i2 + 48, dArr2, i6);
    }

    private void cftf161(double[] dArr, int i, double[] dArr2, int i2) {
        double d = dArr2[i2 + 1];
        int i3 = i2 + 2;
        double d2 = dArr2[i3] * d;
        double d3 = dArr2[i3] + d2;
        int i4 = i + 0;
        int i5 = i + 16;
        double d4 = dArr[i4] + dArr[i5];
        int i6 = i + 1;
        int i7 = i + 17;
        double d5 = dArr[i6] + dArr[i7];
        double d6 = dArr[i4] - dArr[i5];
        double d7 = dArr[i6] - dArr[i7];
        int i8 = i + 8;
        int i9 = i + 24;
        double d8 = dArr[i8] + dArr[i9];
        int i10 = i + 9;
        int i11 = i + 25;
        double d9 = dArr[i10] + dArr[i11];
        double d10 = dArr[i8] - dArr[i9];
        double d11 = dArr[i10] - dArr[i11];
        double d12 = d4 + d8;
        double d13 = d5 + d9;
        double d14 = d4 - d8;
        double d15 = d5 - d9;
        double d16 = d6 - d11;
        double d17 = d7 + d10;
        double d18 = d6 + d11;
        double d19 = d7 - d10;
        int i12 = i + 2;
        int i13 = i + 18;
        double d20 = dArr[i12] + dArr[i13];
        int i14 = i + 3;
        int i15 = i + 19;
        double d21 = dArr[i14] + dArr[i15];
        double d22 = dArr[i12] - dArr[i13];
        double d23 = dArr[i14] - dArr[i15];
        int i16 = i + 10;
        int i17 = i + 26;
        double d24 = dArr[i16] + dArr[i17];
        int i18 = i + 11;
        int i19 = i + 27;
        double d25 = dArr[i18] + dArr[i19];
        double d26 = dArr[i16] - dArr[i17];
        double d27 = dArr[i18] - dArr[i19];
        double d28 = d20 + d24;
        double d29 = d21 + d25;
        double d30 = d20 - d24;
        double d31 = d21 - d25;
        double d32 = d22 - d27;
        double d33 = d23 + d26;
        double d34 = (d3 * d32) - (d2 * d33);
        double d35 = (d33 * d3) + (d32 * d2);
        double d36 = d22 + d27;
        double d37 = d23 - d26;
        double d38 = (d2 * d36) - (d3 * d37);
        double d39 = (d37 * d2) + (d36 * d3);
        int i20 = i + 4;
        int i21 = i + 20;
        double d40 = dArr[i20] + dArr[i21];
        int i22 = i + 5;
        int i23 = i + 21;
        double d41 = dArr[i22] + dArr[i23];
        double d42 = dArr[i20] - dArr[i21];
        double d43 = dArr[i22] - dArr[i23];
        int i24 = i + 12;
        int i25 = i + 28;
        double d44 = dArr[i24] + dArr[i25];
        int i26 = i + 13;
        int i27 = i + 29;
        double d45 = dArr[i26] + dArr[i27];
        double d46 = dArr[i24] - dArr[i25];
        double d47 = dArr[i26] - dArr[i27];
        double d48 = d40 + d44;
        double d49 = d41 + d45;
        double d50 = d40 - d44;
        double d51 = d41 - d45;
        double d52 = d42 - d47;
        double d53 = d43 + d46;
        double d54 = (d52 - d53) * d;
        double d55 = (d53 + d52) * d;
        double d56 = d42 + d47;
        double d57 = d43 - d46;
        double d58 = (d56 + d57) * d;
        double d59 = (d57 - d56) * d;
        int i28 = i + 6;
        int i29 = i + 22;
        double d60 = dArr[i28] + dArr[i29];
        int i30 = i + 7;
        int i31 = i + 23;
        double d61 = dArr[i30] + dArr[i31];
        double d62 = dArr[i28] - dArr[i29];
        double d63 = dArr[i30] - dArr[i31];
        int i32 = i + 14;
        int i33 = i + 30;
        double d64 = dArr[i32] + dArr[i33];
        int i34 = i + 15;
        int i35 = i + 31;
        double d65 = dArr[i34] + dArr[i35];
        double d66 = dArr[i32] - dArr[i33];
        double d67 = dArr[i34] - dArr[i35];
        double d68 = d60 + d64;
        double d69 = d61 + d65;
        double d70 = d60 - d64;
        double d71 = d61 - d65;
        double d72 = d62 - d67;
        double d73 = d63 + d66;
        double d74 = (d2 * d72) - (d3 * d73);
        double d75 = (d73 * d2) + (d72 * d3);
        double d76 = d62 + d67;
        double d77 = d63 - d66;
        double d78 = (d3 * d76) - (d2 * d77);
        double d79 = (d3 * d77) + (d2 * d76);
        double d80 = d18 - d58;
        double d81 = d19 - d59;
        double d82 = d18 + d58;
        double d83 = d19 + d59;
        double d84 = d38 - d78;
        double d85 = d39 - d79;
        double d86 = d38 + d78;
        double d87 = d39 + d79;
        dArr[i9] = d80 + d84;
        dArr[i11] = d81 + d85;
        dArr[i17] = d80 - d84;
        dArr[i19] = d81 - d85;
        dArr[i25] = d82 - d87;
        dArr[i27] = d83 + d86;
        dArr[i33] = d82 + d87;
        dArr[i35] = d83 - d86;
        double d88 = d16 + d54;
        double d89 = d17 + d55;
        double d90 = d16 - d54;
        double d91 = d17 - d55;
        double d92 = d34 + d74;
        double d93 = d35 + d75;
        double d94 = d34 - d74;
        double d95 = d35 - d75;
        dArr[i5] = d88 + d92;
        dArr[i7] = d89 + d93;
        dArr[i13] = d88 - d92;
        dArr[i15] = d89 - d93;
        dArr[i21] = d90 - d95;
        dArr[i23] = d91 + d94;
        dArr[i29] = d90 + d95;
        dArr[i31] = d91 - d94;
        double d96 = d30 - d71;
        double d97 = d31 + d70;
        double d98 = (d96 - d97) * d;
        double d99 = (d97 + d96) * d;
        double d100 = d30 + d71;
        double d101 = d31 - d70;
        double d102 = (d100 - d101) * d;
        double d103 = d * (d101 + d100);
        double d104 = d14 - d51;
        double d105 = d15 + d50;
        double d106 = d14 + d51;
        double d107 = d15 - d50;
        dArr[i8] = d104 + d98;
        dArr[i10] = d105 + d99;
        dArr[i16] = d104 - d98;
        dArr[i18] = d105 - d99;
        dArr[i24] = d106 - d103;
        dArr[i26] = d107 + d102;
        dArr[i32] = d106 + d103;
        dArr[i34] = d107 - d102;
        double d108 = d12 + d48;
        double d109 = d13 + d49;
        double d110 = d12 - d48;
        double d111 = d13 - d49;
        double d112 = d28 + d68;
        double d113 = d29 + d69;
        double d114 = d28 - d68;
        double d115 = d29 - d69;
        dArr[i4] = d108 + d112;
        dArr[i6] = d109 + d113;
        dArr[i12] = d108 - d112;
        dArr[i14] = d109 - d113;
        dArr[i20] = d110 - d115;
        dArr[i22] = d111 + d114;
        dArr[i28] = d110 + d115;
        dArr[i30] = d111 - d114;
    }

    private void cftf162(double[] dArr, int i, double[] dArr2, int i2) {
        double d = dArr2[i2 + 1];
        double d2 = dArr2[i2 + 4];
        double d3 = dArr2[i2 + 5];
        double d4 = dArr2[i2 + 6];
        double d5 = dArr2[i2 + 7];
        double d6 = dArr2[i2 + 8];
        double d7 = dArr2[i2 + 9];
        int i3 = i + 0;
        int i4 = i + 17;
        double d8 = dArr[i3] - dArr[i4];
        int i5 = i + 1;
        int i6 = i + 16;
        double d9 = dArr[i5] + dArr[i6];
        int i7 = i + 8;
        int i8 = i + 25;
        double d10 = dArr[i7] - dArr[i8];
        int i9 = i + 9;
        int i10 = i + 24;
        double d11 = dArr[i9] + dArr[i10];
        double d12 = (d10 - d11) * d;
        double d13 = (d11 + d10) * d;
        double d14 = d8 + d12;
        double d15 = d9 + d13;
        double d16 = d8 - d12;
        double d17 = d9 - d13;
        double d18 = dArr[i3] + dArr[i4];
        double d19 = dArr[i5] - dArr[i6];
        double d20 = dArr[i7] + dArr[i8];
        double d21 = dArr[i9] - dArr[i10];
        double d22 = (d20 - d21) * d;
        double d23 = (d21 + d20) * d;
        double d24 = d18 - d23;
        double d25 = d19 + d22;
        double d26 = d18 + d23;
        double d27 = d19 - d22;
        int i11 = i + 2;
        int i12 = i + 19;
        double d28 = dArr[i11] - dArr[i12];
        int i13 = i + 3;
        int i14 = i + 18;
        double d29 = dArr[i13] + dArr[i14];
        double d30 = (d2 * d28) - (d3 * d29);
        double d31 = (d29 * d2) + (d28 * d3);
        int i15 = i + 10;
        int i16 = i + 27;
        double d32 = dArr[i15] - dArr[i16];
        int i17 = i + 11;
        int i18 = i + 26;
        double d33 = dArr[i17] + dArr[i18];
        double d34 = (d5 * d32) - (d4 * d33);
        double d35 = (d33 * d5) + (d32 * d4);
        double d36 = d30 + d34;
        double d37 = d31 + d35;
        double d38 = d30 - d34;
        double d39 = d31 - d35;
        double d40 = dArr[i11] + dArr[i12];
        double d41 = dArr[i13] - dArr[i14];
        double d42 = (d4 * d40) - (d5 * d41);
        double d43 = (d41 * d4) + (d40 * d5);
        double d44 = dArr[i15] + dArr[i16];
        double d45 = dArr[i17] - dArr[i18];
        double d46 = (d2 * d44) + (d3 * d45);
        double d47 = (d45 * d2) - (d44 * d3);
        double d48 = d42 - d46;
        double d49 = d43 - d47;
        double d50 = d42 + d46;
        double d51 = d43 + d47;
        int i19 = i + 4;
        int i20 = i + 21;
        double d52 = dArr[i19] - dArr[i20];
        int i21 = i + 5;
        int i22 = i + 20;
        double d53 = dArr[i21] + dArr[i22];
        double d54 = (d6 * d52) - (d7 * d53);
        double d55 = (d53 * d6) + (d52 * d7);
        int i23 = i + 12;
        int i24 = i + 29;
        double d56 = dArr[i23] - dArr[i24];
        int i25 = i + 13;
        int i26 = i + 28;
        double d57 = dArr[i25] + dArr[i26];
        double d58 = (d7 * d56) - (d6 * d57);
        double d59 = (d57 * d7) + (d56 * d6);
        double d60 = d54 + d58;
        double d61 = d55 + d59;
        double d62 = d54 - d58;
        double d63 = d55 - d59;
        double d64 = dArr[i19] + dArr[i20];
        double d65 = dArr[i21] - dArr[i22];
        double d66 = (d7 * d64) - (d6 * d65);
        double d67 = (d65 * d7) + (d64 * d6);
        double d68 = dArr[i23] + dArr[i24];
        double d69 = dArr[i25] - dArr[i26];
        double d70 = (d6 * d68) - (d7 * d69);
        double d71 = (d6 * d69) + (d7 * d68);
        double d72 = d66 - d70;
        double d73 = d67 - d71;
        double d74 = d66 + d70;
        double d75 = d67 + d71;
        int i27 = i + 6;
        int i28 = i + 23;
        double d76 = dArr[i27] - dArr[i28];
        int i29 = i + 7;
        int i30 = i + 22;
        double d77 = dArr[i29] + dArr[i30];
        double d78 = (d4 * d76) - (d5 * d77);
        double d79 = (d77 * d4) + (d76 * d5);
        int i31 = i + 14;
        int i32 = i + 31;
        double d80 = dArr[i31] - dArr[i32];
        int i33 = i + 15;
        int i34 = i + 30;
        double d81 = dArr[i33] + dArr[i34];
        double d82 = (d3 * d80) - (d2 * d81);
        double d83 = (d81 * d3) + (d80 * d2);
        double d84 = d78 + d82;
        double d85 = d79 + d83;
        double d86 = d78 - d82;
        double d87 = d79 - d83;
        double d88 = dArr[i27] + dArr[i28];
        double d89 = dArr[i29] - dArr[i30];
        double d90 = (d3 * d88) + (d2 * d89);
        double d91 = (d3 * d89) - (d2 * d88);
        double d92 = dArr[i31] + dArr[i32];
        double d93 = dArr[i33] - dArr[i34];
        double d94 = (d5 * d92) - (d4 * d93);
        double d95 = (d5 * d93) + (d4 * d92);
        double d96 = d90 + d94;
        double d97 = d91 + d95;
        double d98 = d90 - d94;
        double d99 = d91 - d95;
        double d100 = d14 + d60;
        double d101 = d15 + d61;
        double d102 = d36 + d84;
        double d103 = d37 + d85;
        dArr[i3] = d100 + d102;
        dArr[i5] = d101 + d103;
        dArr[i11] = d100 - d102;
        dArr[i13] = d101 - d103;
        double d104 = d14 - d60;
        double d105 = d15 - d61;
        double d106 = d36 - d84;
        double d107 = d37 - d85;
        dArr[i19] = d104 - d107;
        dArr[i21] = d105 + d106;
        dArr[i27] = d104 + d107;
        dArr[i29] = d105 - d106;
        double d108 = d16 - d63;
        double d109 = d17 + d62;
        double d110 = d38 - d87;
        double d111 = d39 + d86;
        double d112 = (d110 - d111) * d;
        double d113 = (d111 + d110) * d;
        dArr[i7] = d108 + d112;
        dArr[i9] = d109 + d113;
        dArr[i15] = d108 - d112;
        dArr[i17] = d109 - d113;
        double d114 = d16 + d63;
        double d115 = d17 - d62;
        double d116 = d38 + d87;
        double d117 = d39 - d86;
        double d118 = (d116 - d117) * d;
        double d119 = (d117 + d116) * d;
        dArr[i23] = d114 - d119;
        dArr[i25] = d115 + d118;
        dArr[i31] = d114 + d119;
        dArr[i33] = d115 - d118;
        double d120 = d24 + d72;
        double d121 = d25 + d73;
        double d122 = d48 - d96;
        double d123 = d49 - d97;
        dArr[i6] = d120 + d122;
        dArr[i4] = d121 + d123;
        dArr[i14] = d120 - d122;
        dArr[i12] = d121 - d123;
        double d124 = d24 - d72;
        double d125 = d25 - d73;
        double d126 = d48 + d96;
        double d127 = d49 + d97;
        dArr[i22] = d124 - d127;
        dArr[i20] = d125 + d126;
        dArr[i30] = d124 + d127;
        dArr[i28] = d125 - d126;
        double d128 = d26 - d75;
        double d129 = d27 + d74;
        double d130 = d50 + d99;
        double d131 = d51 - d98;
        double d132 = (d130 - d131) * d;
        double d133 = (d131 + d130) * d;
        dArr[i10] = d128 + d132;
        dArr[i8] = d129 + d133;
        dArr[i18] = d128 - d132;
        dArr[i16] = d129 - d133;
        double d134 = d26 + d75;
        double d135 = d27 - d74;
        double d136 = d50 - d99;
        double d137 = d51 + d98;
        double d138 = (d136 - d137) * d;
        double d139 = d * (d137 + d136);
        dArr[i26] = d134 - d139;
        dArr[i24] = d135 + d138;
        dArr[i34] = d134 + d139;
        dArr[i32] = d135 - d138;
    }

    private void cftf081(double[] dArr, int i, double[] dArr2, int i2) {
        double d = dArr2[i2 + 1];
        int i3 = i + 0;
        int i4 = i + 8;
        double d2 = dArr[i3] + dArr[i4];
        int i5 = i + 1;
        int i6 = i + 9;
        double d3 = dArr[i5] + dArr[i6];
        double d4 = dArr[i3] - dArr[i4];
        double d5 = dArr[i5] - dArr[i6];
        int i7 = i + 4;
        int i8 = i + 12;
        double d6 = dArr[i7] + dArr[i8];
        int i9 = i + 5;
        int i10 = i + 13;
        double d7 = dArr[i9] + dArr[i10];
        double d8 = dArr[i7] - dArr[i8];
        double d9 = dArr[i9] - dArr[i10];
        double d10 = d2 + d6;
        double d11 = d3 + d7;
        double d12 = d2 - d6;
        double d13 = d3 - d7;
        double d14 = d4 - d9;
        double d15 = d5 + d8;
        double d16 = d4 + d9;
        double d17 = d5 - d8;
        int i11 = i + 2;
        int i12 = i + 10;
        double d18 = dArr[i11] + dArr[i12];
        int i13 = i + 3;
        int i14 = i + 11;
        double d19 = dArr[i13] + dArr[i14];
        double d20 = dArr[i11] - dArr[i12];
        double d21 = dArr[i13] - dArr[i14];
        int i15 = i + 6;
        int i16 = i + 14;
        double d22 = dArr[i15] + dArr[i16];
        int i17 = i + 7;
        int i18 = i + 15;
        double d23 = dArr[i17] + dArr[i18];
        double d24 = dArr[i15] - dArr[i16];
        double d25 = dArr[i17] - dArr[i18];
        double d26 = d18 + d22;
        double d27 = d19 + d23;
        double d28 = d18 - d22;
        double d29 = d19 - d23;
        double d30 = d20 - d25;
        double d31 = d21 + d24;
        double d32 = d20 + d25;
        double d33 = d21 - d24;
        double d34 = (d30 - d31) * d;
        double d35 = (d30 + d31) * d;
        double d36 = (d32 - d33) * d;
        double d37 = d * (d32 + d33);
        dArr[i4] = d14 + d34;
        dArr[i6] = d15 + d35;
        dArr[i12] = d14 - d34;
        dArr[i14] = d15 - d35;
        dArr[i8] = d16 - d37;
        dArr[i10] = d17 + d36;
        dArr[i16] = d16 + d37;
        dArr[i18] = d17 - d36;
        dArr[i3] = d10 + d26;
        dArr[i5] = d11 + d27;
        dArr[i11] = d10 - d26;
        dArr[i13] = d11 - d27;
        dArr[i7] = d12 - d29;
        dArr[i9] = d13 + d28;
        dArr[i15] = d12 + d29;
        dArr[i17] = d13 - d28;
    }

    private void cftf082(double[] dArr, int i, double[] dArr2, int i2) {
        double d = dArr2[i2 + 1];
        double d2 = dArr2[i2 + 4];
        double d3 = dArr2[i2 + 5];
        int i3 = i + 0;
        int i4 = i + 9;
        double d4 = dArr[i3] - dArr[i4];
        int i5 = i + 1;
        int i6 = i + 8;
        double d5 = dArr[i5] + dArr[i6];
        double d6 = dArr[i3] + dArr[i4];
        double d7 = dArr[i5] - dArr[i6];
        int i7 = i + 4;
        int i8 = i + 13;
        double d8 = dArr[i7] - dArr[i8];
        int i9 = i + 5;
        int i10 = i + 12;
        double d9 = dArr[i9] + dArr[i10];
        double d10 = (d8 - d9) * d;
        double d11 = (d9 + d8) * d;
        double d12 = dArr[i7] + dArr[i8];
        double d13 = dArr[i9] - dArr[i10];
        double d14 = (d12 - d13) * d;
        double d15 = d * (d13 + d12);
        int i11 = i + 2;
        int i12 = i + 11;
        double d16 = dArr[i11] - dArr[i12];
        int i13 = i + 3;
        int i14 = i + 10;
        double d17 = dArr[i13] + dArr[i14];
        double d18 = (d2 * d16) - (d3 * d17);
        double d19 = (d17 * d2) + (d16 * d3);
        double d20 = dArr[i11] + dArr[i12];
        double d21 = dArr[i13] - dArr[i14];
        double d22 = (d3 * d20) - (d2 * d21);
        double d23 = (d21 * d3) + (d20 * d2);
        int i15 = i + 6;
        int i16 = i + 15;
        double d24 = dArr[i15] - dArr[i16];
        int i17 = i + 7;
        int i18 = i + 14;
        double d25 = dArr[i17] + dArr[i18];
        double d26 = (d3 * d24) - (d2 * d25);
        double d27 = (d25 * d3) + (d24 * d2);
        double d28 = dArr[i15] + dArr[i16];
        double d29 = dArr[i17] - dArr[i18];
        double d30 = (d2 * d28) - (d3 * d29);
        double d31 = (d2 * d29) + (d3 * d28);
        double d32 = d4 + d10;
        double d33 = d5 + d11;
        double d34 = d18 + d26;
        double d35 = d19 + d27;
        dArr[i3] = d32 + d34;
        dArr[i5] = d33 + d35;
        dArr[i11] = d32 - d34;
        dArr[i13] = d33 - d35;
        double d36 = d4 - d10;
        double d37 = d5 - d11;
        double d38 = d18 - d26;
        double d39 = d19 - d27;
        dArr[i7] = d36 - d39;
        dArr[i9] = d37 + d38;
        dArr[i15] = d36 + d39;
        dArr[i17] = d37 - d38;
        double d40 = d6 - d15;
        double d41 = d7 + d14;
        double d42 = d22 - d30;
        double d43 = d23 - d31;
        dArr[i6] = d40 + d42;
        dArr[i4] = d41 + d43;
        dArr[i14] = d40 - d42;
        dArr[i12] = d41 - d43;
        double d44 = d6 + d15;
        double d45 = d7 - d14;
        double d46 = d22 + d30;
        double d47 = d23 + d31;
        dArr[i10] = d44 - d47;
        dArr[i8] = d45 + d46;
        dArr[i18] = d44 + d47;
        dArr[i16] = d45 - d46;
    }

    private void cftf040(double[] dArr) {
        double d = dArr[0] + dArr[4];
        double d2 = dArr[1] + dArr[5];
        double d3 = dArr[0] - dArr[4];
        double d4 = dArr[1] - dArr[5];
        double d5 = dArr[2] + dArr[6];
        double d6 = dArr[3] + dArr[7];
        double d7 = dArr[2] - dArr[6];
        double d8 = dArr[3] - dArr[7];
        dArr[0] = d + d5;
        dArr[1] = d2 + d6;
        dArr[4] = d - d5;
        dArr[5] = d2 - d6;
        dArr[2] = d3 - d8;
        dArr[3] = d4 + d7;
        dArr[6] = d3 + d8;
        dArr[7] = d4 - d7;
    }

    private void cftb040(double[] dArr) {
        double d = dArr[0] + dArr[4];
        double d2 = dArr[1] + dArr[5];
        double d3 = dArr[0] - dArr[4];
        double d4 = dArr[1] - dArr[5];
        double d5 = dArr[2] + dArr[6];
        double d6 = dArr[3] + dArr[7];
        double d7 = dArr[2] - dArr[6];
        double d8 = dArr[3] - dArr[7];
        dArr[0] = d + d5;
        dArr[1] = d2 + d6;
        dArr[4] = d - d5;
        dArr[5] = d2 - d6;
        dArr[2] = d3 + d8;
        dArr[3] = d4 - d7;
        dArr[6] = d3 - d8;
        dArr[7] = d4 + d7;
    }

    private void cftx020(double[] dArr) {
        double d = dArr[1] - dArr[3];
        dArr[0] = dArr[0] + dArr[2];
        dArr[1] = dArr[1] + dArr[3];
        dArr[2] = dArr[0] - dArr[2];
        dArr[3] = d;
    }

    private void rftfsub(int i, double[] dArr, int i2, double[] dArr2, int i3) {
        int i4 = i >> 1;
        int i5 = (i2 * 2) / i4;
        int i6 = 0;
        for (int i7 = 2; i7 < i4; i7 += 2) {
            int i8 = i - i7;
            i6 += i5;
            double d = 0.5d - dArr2[(i3 + i2) - i6];
            double d2 = dArr2[i3 + i6];
            double d3 = dArr[i7] - dArr[i8];
            int i9 = i7 + 1;
            int i10 = i8 + 1;
            double d4 = dArr[i9] + dArr[i10];
            double d5 = (d * d3) - (d2 * d4);
            double d6 = (d * d4) + (d2 * d3);
            dArr[i7] = dArr[i7] - d5;
            dArr[i9] = dArr[i9] - d6;
            dArr[i8] = dArr[i8] + d5;
            dArr[i10] = dArr[i10] - d6;
        }
    }

    private void rftbsub(int i, double[] dArr, int i2, double[] dArr2, int i3) {
        int i4 = i >> 1;
        int i5 = (i2 * 2) / i4;
        int i6 = 0;
        for (int i7 = 2; i7 < i4; i7 += 2) {
            int i8 = i - i7;
            i6 += i5;
            double d = 0.5d - dArr2[(i3 + i2) - i6];
            double d2 = dArr2[i3 + i6];
            double d3 = dArr[i7] - dArr[i8];
            int i9 = i7 + 1;
            int i10 = i8 + 1;
            double d4 = dArr[i9] + dArr[i10];
            double d5 = (d * d3) + (d2 * d4);
            double d6 = (d * d4) - (d2 * d3);
            dArr[i7] = dArr[i7] - d5;
            dArr[i9] = dArr[i9] - d6;
            dArr[i8] = dArr[i8] + d5;
            dArr[i10] = dArr[i10] - d6;
        }
    }

    private void dctsub(int i, double[] dArr, int i2, double[] dArr2, int i3) {
        int i4 = i >> 1;
        int i5 = i2 / i;
        int i6 = 0;
        for (int i7 = 1; i7 < i4; i7++) {
            int i8 = i - i7;
            i6 += i5;
            int i9 = i3 + i6;
            int i10 = (i3 + i2) - i6;
            double d = dArr2[i9] - dArr2[i10];
            double d2 = dArr2[i9] + dArr2[i10];
            double d3 = (dArr[i7] * d2) - (dArr[i8] * d);
            dArr[i7] = (d * dArr[i7]) + (d2 * dArr[i8]);
            dArr[i8] = d3;
        }
        dArr[i4] = dArr[i4] * dArr2[i3 + 0];
    }

    private void dstsub(int i, double[] dArr, int i2, double[] dArr2, int i3) {
        int i4 = i >> 1;
        int i5 = i2 / i;
        int i6 = 0;
        for (int i7 = 1; i7 < i4; i7++) {
            int i8 = i - i7;
            i6 += i5;
            int i9 = i3 + i6;
            int i10 = (i3 + i2) - i6;
            double d = dArr2[i9] - dArr2[i10];
            double d2 = dArr2[i9] + dArr2[i10];
            double d3 = (dArr[i8] * d2) - (dArr[i7] * d);
            dArr[i8] = (d * dArr[i8]) + (d2 * dArr[i7]);
            dArr[i7] = d3;
        }
        dArr[i4] = dArr[i4] * dArr2[i3 + 0];
    }
}
