package jp.wasabeef.glide.transformations.internal;

import android.graphics.Bitmap;
import java.lang.reflect.Array;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class FastBlur {
    public static Bitmap blur(Bitmap bitmap, int i, boolean z) {
        int i2;
        int[] iArr;
        int i3 = i;
        Bitmap copy = z ? bitmap : bitmap.copy(bitmap.getConfig(), true);
        if (i3 < 1) {
            return null;
        }
        int width = copy.getWidth();
        int height = copy.getHeight();
        int i4 = width * height;
        int[] iArr2 = new int[i4];
        copy.getPixels(iArr2, 0, width, 0, 0, width, height);
        int i5 = width - 1;
        int i6 = height - 1;
        int i7 = i3 + i3 + 1;
        int[] iArr3 = new int[i4];
        int[] iArr4 = new int[i4];
        int[] iArr5 = new int[i4];
        int[] iArr6 = new int[Math.max(width, height)];
        int i8 = (i7 + 1) >> 1;
        int i9 = i8 * i8;
        int i10 = i9 * 256;
        int[] iArr7 = new int[i10];
        for (int i11 = 0; i11 < i10; i11++) {
            iArr7[i11] = i11 / i9;
        }
        int[][] iArr8 = (int[][]) Array.newInstance(int.class, i7, 3);
        int i12 = i3 + 1;
        int i13 = 0;
        int i14 = 0;
        int i15 = 0;
        while (i13 < height) {
            Bitmap bitmap2 = copy;
            int i16 = -i3;
            int i17 = 0;
            int i18 = 0;
            int i19 = 0;
            int i20 = 0;
            int i21 = 0;
            int i22 = 0;
            int i23 = 0;
            int i24 = 0;
            int i25 = 0;
            while (i16 <= i3) {
                int i26 = i6;
                int i27 = height;
                int i28 = iArr2[i14 + Math.min(i5, Math.max(i16, 0))];
                int[] iArr9 = iArr8[i16 + i3];
                iArr9[0] = (i28 & 16711680) >> 16;
                iArr9[1] = (i28 & 65280) >> 8;
                iArr9[2] = i28 & 255;
                int abs = i12 - Math.abs(i16);
                i17 += iArr9[0] * abs;
                i18 += iArr9[1] * abs;
                i19 += iArr9[2] * abs;
                if (i16 > 0) {
                    i23 += iArr9[0];
                    i24 += iArr9[1];
                    i25 += iArr9[2];
                } else {
                    i20 += iArr9[0];
                    i21 += iArr9[1];
                    i22 += iArr9[2];
                }
                i16++;
                height = i27;
                i6 = i26;
            }
            int i29 = i6;
            int i30 = height;
            int i31 = i3;
            int i32 = 0;
            while (i32 < width) {
                iArr3[i14] = iArr7[i17];
                iArr4[i14] = iArr7[i18];
                iArr5[i14] = iArr7[i19];
                int i33 = i17 - i20;
                int i34 = i18 - i21;
                int i35 = i19 - i22;
                int[] iArr10 = iArr8[((i31 - i3) + i7) % i7];
                int i36 = i20 - iArr10[0];
                int i37 = i21 - iArr10[1];
                int i38 = i22 - iArr10[2];
                if (i13 == 0) {
                    iArr = iArr7;
                    iArr6[i32] = Math.min(i32 + i3 + 1, i5);
                } else {
                    iArr = iArr7;
                }
                int i39 = iArr2[i15 + iArr6[i32]];
                iArr10[0] = (i39 & 16711680) >> 16;
                iArr10[1] = (i39 & 65280) >> 8;
                iArr10[2] = i39 & 255;
                int i40 = i23 + iArr10[0];
                int i41 = i24 + iArr10[1];
                int i42 = i25 + iArr10[2];
                i17 = i33 + i40;
                i18 = i34 + i41;
                i19 = i35 + i42;
                i31 = (i31 + 1) % i7;
                int[] iArr11 = iArr8[i31 % i7];
                i20 = i36 + iArr11[0];
                i21 = i37 + iArr11[1];
                i22 = i38 + iArr11[2];
                i23 = i40 - iArr11[0];
                i24 = i41 - iArr11[1];
                i25 = i42 - iArr11[2];
                i14++;
                i32++;
                iArr7 = iArr;
            }
            i15 += width;
            i13++;
            copy = bitmap2;
            height = i30;
            i6 = i29;
        }
        Bitmap bitmap3 = copy;
        int i43 = i6;
        int i44 = height;
        int[] iArr12 = iArr7;
        int i45 = 0;
        while (i45 < width) {
            int i46 = -i3;
            int i47 = i46 * width;
            int i48 = 0;
            int i49 = 0;
            int i50 = 0;
            int i51 = 0;
            int i52 = 0;
            int i53 = 0;
            int i54 = 0;
            int i55 = 0;
            int i56 = 0;
            while (i46 <= i3) {
                int[] iArr13 = iArr6;
                int max = Math.max(0, i47) + i45;
                int[] iArr14 = iArr8[i46 + i3];
                iArr14[0] = iArr3[max];
                iArr14[1] = iArr4[max];
                iArr14[2] = iArr5[max];
                int abs2 = i12 - Math.abs(i46);
                i48 += iArr3[max] * abs2;
                i49 += iArr4[max] * abs2;
                i50 += iArr5[max] * abs2;
                if (i46 > 0) {
                    i54 += iArr14[0];
                    i55 += iArr14[1];
                    i56 += iArr14[2];
                    i2 = i43;
                } else {
                    i51 += iArr14[0];
                    i52 += iArr14[1];
                    i53 += iArr14[2];
                    i2 = i43;
                }
                if (i46 < i2) {
                    i47 += width;
                }
                i46++;
                i43 = i2;
                iArr6 = iArr13;
            }
            int[] iArr15 = iArr6;
            int i57 = i43;
            int i58 = i45;
            int i59 = i55;
            int i60 = i56;
            int i61 = 0;
            int i62 = i3;
            int i63 = i54;
            int i64 = i53;
            int i65 = i52;
            int i66 = i51;
            int i67 = i50;
            int i68 = i49;
            int i69 = i48;
            int i70 = i44;
            while (i61 < i70) {
                iArr2[i58] = (iArr2[i58] & (-16777216)) | (iArr12[i69] << 16) | (iArr12[i68] << 8) | iArr12[i67];
                int i71 = i69 - i66;
                int i72 = i68 - i65;
                int i73 = i67 - i64;
                int[] iArr16 = iArr8[((i62 - i3) + i7) % i7];
                int i74 = i66 - iArr16[0];
                int i75 = i65 - iArr16[1];
                int i76 = i64 - iArr16[2];
                if (i45 == 0) {
                    iArr15[i61] = Math.min(i61 + i12, i57) * width;
                }
                int i77 = iArr15[i61] + i45;
                iArr16[0] = iArr3[i77];
                iArr16[1] = iArr4[i77];
                iArr16[2] = iArr5[i77];
                int i78 = i63 + iArr16[0];
                int i79 = i59 + iArr16[1];
                int i80 = i60 + iArr16[2];
                i69 = i71 + i78;
                i68 = i72 + i79;
                i67 = i73 + i80;
                i62 = (i62 + 1) % i7;
                int[] iArr17 = iArr8[i62];
                i66 = i74 + iArr17[0];
                i65 = i75 + iArr17[1];
                i64 = i76 + iArr17[2];
                i63 = i78 - iArr17[0];
                i59 = i79 - iArr17[1];
                i60 = i80 - iArr17[2];
                i58 += width;
                i61++;
                i3 = i;
            }
            i45++;
            i43 = i57;
            i44 = i70;
            iArr6 = iArr15;
            i3 = i;
        }
        bitmap3.setPixels(iArr2, 0, width, 0, 0, width, i44);
        return bitmap3;
    }
}
