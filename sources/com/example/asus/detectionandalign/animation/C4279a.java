package com.example.asus.detectionandalign.animation;

import android.util.Log;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.example.asus.detectionandalign.animation.a */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C4279a {
    /* renamed from: a */
    public static float[] m16004a(boolean z, int i, int i2, int i3, int i4, int i5, int i6, int i7, float[] fArr) {
        int i8 = (i * i5) / i4;
        Log.e("AnimUtil", "相机分辨率：cameraWidth: " + i4 + " cameraHeight:  " + i5);
        Log.e("AnimUtil", "屏幕分辨率：displayWidth: " + i6 + " displayHeight:  " + i7);
        Log.e("AnimUtil", "原图尺寸dstWidth: " + i8 + " dstHeight:  " + i);
        Log.e("AnimUtil", "cutWidthSize: " + i3 + "  cutHeightSize:" + i2);
        int i9 = 0;
        if (z) {
            int i10 = (i4 * i) / i5;
            Log.e("AnimUtil", "第一次压缩尺寸dstWidth:  " + i + " dstHeight：" + i10);
            StringBuilder sb = new StringBuilder();
            sb.append("裁剪：centreSize: ");
            sb.append(i3);
            Log.e("AnimUtil", sb.toString());
            while (i9 < fArr.length) {
                float f = i8;
                float f2 = i;
                float f3 = (fArr[i9] + ((i - i3) / 2)) * (f / f2) * (i6 / f);
                int i11 = i9 + 1;
                fArr[i9] = f3;
                fArr[i11] = (fArr[i11] + ((i10 - i2) / 2)) * (f2 / i10) * (i7 / f2);
                i9 += 2;
            }
        } else if (z && i3 == i3) {
            int i12 = (i4 * i) / i5;
            Log.e("AnimUtil", "第一次压缩尺寸dstWidth:  " + i + " dstHeight：" + i12);
            StringBuilder sb2 = new StringBuilder();
            sb2.append("裁剪：centreSize: ");
            sb2.append(i3);
            Log.e("AnimUtil", sb2.toString());
            while (i9 < fArr.length) {
                float f4 = i8;
                float f5 = i;
                float f6 = (fArr[i9] + ((i - i3) / 2)) * (f4 / f5) * (i6 / f4);
                int i13 = i9 + 1;
                fArr[i9] = f6;
                fArr[i13] = (fArr[i13] + ((i12 - i3) / 2)) * (f5 / i12) * (i7 / f5);
                i9 += 2;
            }
        } else {
            int i14 = (i4 * i) / i5;
            Log.e("AnimUtil", "第一次压缩尺寸dstWidth:  " + i + " dstHeight：" + i14);
            while (i9 < fArr.length) {
                float f7 = i8;
                float f8 = i;
                float f9 = fArr[i9] * (f7 / f8) * (i6 / f7);
                int i15 = i9 + 1;
                fArr[i9] = f9;
                fArr[i15] = fArr[i15] * (f8 / i14) * (i7 / f8);
                i9 += 2;
            }
        }
        return fArr;
    }
}
