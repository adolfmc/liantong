package com.sinovatech.unicom.separatemodule.dianziqianming.utils;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import com.sinovatech.unicom.separatemodule.dianziqianming.entity.SignatureEntity;
import java.util.ArrayList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class SignatureUtils {
    public static boolean isYaSuo;
    public static int oldSignaturePadsHeight;
    public static int oldSignaturePadsWidth;
    public static int qHeight;
    public static int qWidth;
    public static List<SignatureEntity> signatureEntities = new ArrayList();
    public static int signaturePadsHeight;
    public static int signaturePadsWidth;
    public static int yaSuoXY;

    public static Bitmap imageScale(Bitmap bitmap, int i, int i2) {
        try {
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            Matrix matrix = new Matrix();
            matrix.postScale(i / width, i2 / height);
            return Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
        } catch (Exception unused) {
            return bitmap;
        }
    }

    public static double zHDouble(String str) {
        try {
            return Double.parseDouble(str);
        } catch (Exception unused) {
            return 0.0d;
        }
    }
}
