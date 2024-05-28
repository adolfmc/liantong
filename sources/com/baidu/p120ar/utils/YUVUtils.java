package com.baidu.p120ar.utils;

import android.graphics.Bitmap;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.utils.YUVUtils */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class YUVUtils {
    public static byte[] cropYuv(byte[] bArr, int i, int i2, byte[] bArr2, int i3, int i4) {
        if (bArr == null || bArr2.length < i * i2) {
            bArr = new byte[i * i2];
        }
        int i5 = 0;
        for (int i6 = 0; i6 < i4; i6++) {
            if (i6 % 2 == 1) {
                int i7 = i5;
                for (int i8 = 0; i8 < i3; i8++) {
                    if (i8 % 2 == 1) {
                        bArr[i7] = bArr2[(i6 * i3) + i8];
                        i7++;
                    }
                }
                i5 = i7;
            }
        }
        return bArr;
    }

    public static Bitmap rawByte2RGBABitmap(int i, int i2, byte[] bArr) throws NullPointerException, IllegalArgumentException {
        int i3 = i * i2;
        int[] iArr = new int[i3];
        for (int i4 = 0; i4 < i2; i4++) {
            for (int i5 = 0; i5 < i; i5++) {
                int i6 = (i4 * i) + i5;
                int i7 = bArr[i6] & 255;
                int i8 = ((i4 >> 1) * i) + i3 + (i5 & (-2));
                int i9 = bArr[i8 + 0] & 255;
                int i10 = bArr[i8 + 1] & 255;
                if (i7 < 16) {
                    i7 = 16;
                }
                float f = (i7 - 16) * 1.164f;
                float f2 = i10 - 128;
                int round = Math.round((1.596f * f2) + f);
                float f3 = i9 - 128;
                int round2 = Math.round((f - (f2 * 0.813f)) - (0.391f * f3));
                int round3 = Math.round(f + (f3 * 2.018f));
                if (round < 0) {
                    round = 0;
                } else if (round > 255) {
                    round = 255;
                }
                if (round2 < 0) {
                    round2 = 0;
                } else if (round2 > 255) {
                    round2 = 255;
                }
                if (round3 < 0) {
                    round3 = 0;
                } else if (round3 > 255) {
                    round3 = 255;
                }
                iArr[i6] = ((round3 << 16) - 16777216) + (round2 << 8) + round;
            }
        }
        Bitmap createBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
        createBitmap.setPixels(iArr, 0, i, 0, 0, i, i2);
        return createBitmap;
    }

    public static void decodeYUV420SP(int[] iArr, byte[] bArr, int i, int i2) {
        int i3 = i * i2;
        int i4 = 0;
        int i5 = 0;
        while (i4 < i2) {
            int i6 = 0;
            int i7 = 0;
            int i8 = ((i4 >> 1) * i) + i3;
            int i9 = i5;
            int i10 = 0;
            while (i10 < i) {
                int i11 = (bArr[i9] & 255) - 16;
                if (i11 < 0) {
                    i11 = 0;
                }
                if ((i10 & 1) == 0) {
                    int i12 = i8 + 1;
                    if (i8 < bArr.length) {
                        i7 = (bArr[i8] & 255) - 128;
                        i6 = (bArr[i8] & 255) - 128;
                        i8 = i12;
                    } else {
                        i8 = i12;
                    }
                }
                int i13 = i11 * 1192;
                int i14 = (i7 * 1634) + i13;
                int i15 = (i13 - (i7 * 833)) - (i6 * 400);
                int i16 = i13 + (i6 * 2066);
                if (i14 < 0) {
                    i14 = 0;
                } else if (i14 > 262143) {
                    i14 = 262143;
                }
                if (i15 < 0) {
                    i15 = 0;
                } else if (i15 > 262143) {
                    i15 = 262143;
                }
                if (i16 < 0) {
                    i16 = 0;
                } else if (i16 > 262143) {
                    i16 = 262143;
                }
                if (i9 < iArr.length) {
                    iArr[i9] = ((i16 << 6) & 16711680) | (-16777216) | ((i15 >> 2) & 65280) | ((i14 >> 10) & 255);
                }
                i10++;
                i9++;
            }
            i4++;
            i5 = i9;
        }
    }

    public static void decodeYUV420SPrgb565(int[] iArr, byte[] bArr, int i, int i2) {
        int i3 = i * i2;
        int i4 = 0;
        int i5 = 0;
        while (i4 < i2) {
            int i6 = 0;
            int i7 = 0;
            int i8 = ((i4 >> 1) * i) + i3;
            int i9 = i5;
            int i10 = 0;
            while (i10 < i) {
                int i11 = (bArr[i9] & 255) - 16;
                if (i11 < 0) {
                    i11 = 0;
                }
                if ((i10 & 1) == 0) {
                    int i12 = i8 + 1;
                    int i13 = i12 + 1;
                    i6 = (bArr[i12] & 255) - 128;
                    i7 = (bArr[i8] & 255) - 128;
                    i8 = i13;
                }
                int i14 = i11 * 1192;
                int i15 = (i7 * 1634) + i14;
                int i16 = (i14 - (i7 * 833)) - (i6 * 400);
                int i17 = i14 + (i6 * 2066);
                int i18 = 262143;
                if (i15 < 0) {
                    i15 = 0;
                } else if (i15 > 262143) {
                    i15 = 262143;
                }
                if (i16 < 0) {
                    i16 = 0;
                } else if (i16 > 262143) {
                    i16 = 262143;
                }
                if (i17 < 0) {
                    i18 = 0;
                } else if (i17 <= 262143) {
                    i18 = i17;
                }
                iArr[i9] = (-16777216) | ((i15 << 6) & 16711680) | ((i16 >> 2) & 65280) | ((i18 >> 10) & 255);
                i10++;
                i9++;
            }
            i4++;
            i5 = i9;
        }
    }
}
