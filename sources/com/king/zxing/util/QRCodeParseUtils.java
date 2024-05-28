package com.king.zxing.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.common.GlobalHistogramBinarizer;
import com.google.zxing.common.HybridBinarizer;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class QRCodeParseUtils {
    public static final Map<DecodeHintType, Object> HINTS = new EnumMap(DecodeHintType.class);

    static {
        ArrayList arrayList = new ArrayList();
        arrayList.add(BarcodeFormat.AZTEC);
        arrayList.add(BarcodeFormat.CODABAR);
        arrayList.add(BarcodeFormat.CODE_39);
        arrayList.add(BarcodeFormat.CODE_93);
        arrayList.add(BarcodeFormat.CODE_128);
        arrayList.add(BarcodeFormat.DATA_MATRIX);
        arrayList.add(BarcodeFormat.EAN_8);
        arrayList.add(BarcodeFormat.EAN_13);
        arrayList.add(BarcodeFormat.ITF);
        arrayList.add(BarcodeFormat.MAXICODE);
        arrayList.add(BarcodeFormat.PDF_417);
        arrayList.add(BarcodeFormat.QR_CODE);
        arrayList.add(BarcodeFormat.RSS_14);
        arrayList.add(BarcodeFormat.RSS_EXPANDED);
        arrayList.add(BarcodeFormat.UPC_A);
        arrayList.add(BarcodeFormat.UPC_E);
        arrayList.add(BarcodeFormat.UPC_EAN_EXTENSION);
        HINTS.put(DecodeHintType.TRY_HARDER, BarcodeFormat.QR_CODE);
        HINTS.put(DecodeHintType.POSSIBLE_FORMATS, arrayList);
        HINTS.put(DecodeHintType.CHARACTER_SET, "utf-8");
    }

    public static String syncDecodeQRCode(String str) {
        return syncDecodeQRCode(getDecodeAbleBitmap(str));
    }

    public static String syncDecodeQRCode(Bitmap bitmap) {
        RGBLuminanceSource rGBLuminanceSource;
        try {
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            int[] iArr = new int[width * height];
            bitmap.getPixels(iArr, 0, width, 0, 0, width, height);
            rGBLuminanceSource = new RGBLuminanceSource(width, height, iArr);
            try {
                return new MultiFormatReader().decode(new BinaryBitmap(new HybridBinarizer(rGBLuminanceSource)), HINTS).getText();
            } catch (Exception e) {
                e = e;
                e.printStackTrace();
                if (rGBLuminanceSource != null) {
                    try {
                        return new MultiFormatReader().decode(new BinaryBitmap(new GlobalHistogramBinarizer(rGBLuminanceSource)), HINTS).getText();
                    } catch (Throwable th) {
                        th.printStackTrace();
                        return null;
                    }
                }
                return null;
            }
        } catch (Exception e2) {
            e = e2;
            rGBLuminanceSource = null;
        }
    }

    private static Bitmap getDecodeAbleBitmap(String str) {
        try {
            if (needCompress(str, 3)) {
                BitmapFactory.Options options = new BitmapFactory.Options();
                int i = 1;
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(str, options);
                int i2 = options.outHeight / 400;
                if (i2 > 0) {
                    i = i2;
                }
                options.inSampleSize = i;
                options.inJustDecodeBounds = false;
                return BitmapFactory.decodeFile(str, options);
            }
            return BitmapFactory.decodeFile(str);
        } catch (Exception unused) {
            return null;
        }
    }

    private static boolean needCompress(String str, int i) {
        if (i == 0) {
            i = 1;
        }
        try {
            return new FileInputStream(str).available() > i * 1048576;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
