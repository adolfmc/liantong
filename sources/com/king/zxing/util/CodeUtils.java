package com.king.zxing.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.ColorInt;
import android.support.annotation.FloatRange;
import android.support.annotation.NonNull;
import android.text.TextPaint;
import android.text.TextUtils;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.GlobalHistogramBinarizer;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.king.zxing.DecodeFormatManager;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public final class CodeUtils {
    private CodeUtils() {
        throw new AssertionError();
    }

    public static Bitmap createQRCode(String str, int i) {
        return createQRCode(str, i, (Bitmap) null);
    }

    public static Bitmap createQRCode(String str, int i, int i2) {
        return createQRCode(str, i, (Bitmap) null, i2);
    }

    public static Bitmap createQRCode(String str, int i, Bitmap bitmap) {
        return createQRCode(str, i, bitmap, -16777216);
    }

    public static Bitmap createQRCode(String str, int i, Bitmap bitmap, int i2) {
        return createQRCode(str, i, bitmap, 0.2f, i2);
    }

    public static Bitmap createQRCode(String str, int i, Bitmap bitmap, @FloatRange(from = 0.0d, m22210to = 1.0d) float f) {
        HashMap hashMap = new HashMap();
        hashMap.put(EncodeHintType.CHARACTER_SET, "utf-8");
        hashMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hashMap.put(EncodeHintType.MARGIN, 1);
        return createQRCode(str, i, bitmap, f, hashMap);
    }

    public static Bitmap createQRCode(String str, int i, Bitmap bitmap, @FloatRange(from = 0.0d, m22210to = 1.0d) float f, int i2) {
        HashMap hashMap = new HashMap();
        hashMap.put(EncodeHintType.CHARACTER_SET, "utf-8");
        hashMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hashMap.put(EncodeHintType.MARGIN, 1);
        return createQRCode(str, i, bitmap, f, hashMap, i2);
    }

    public static Bitmap createQRCode(String str, int i, Bitmap bitmap, @FloatRange(from = 0.0d, m22210to = 1.0d) float f, Map<EncodeHintType, ?> map) {
        return createQRCode(str, i, bitmap, f, map, -16777216);
    }

    public static Bitmap createQRCode(String str, int i, Bitmap bitmap, @FloatRange(from = 0.0d, m22210to = 1.0d) float f, Map<EncodeHintType, ?> map, int i2) {
        try {
            BitMatrix encode = new QRCodeWriter().encode(str, BarcodeFormat.QR_CODE, i, i, map);
            int[] iArr = new int[i * i];
            for (int i3 = 0; i3 < i; i3++) {
                for (int i4 = 0; i4 < i; i4++) {
                    if (encode.get(i4, i3)) {
                        iArr[(i3 * i) + i4] = i2;
                    } else {
                        iArr[(i3 * i) + i4] = -1;
                    }
                }
            }
            Bitmap createBitmap = Bitmap.createBitmap(i, i, Bitmap.Config.ARGB_8888);
            createBitmap.setPixels(iArr, 0, i, 0, 0, i, i);
            return bitmap != null ? addLogo(createBitmap, bitmap, f) : createBitmap;
        } catch (WriterException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Bitmap addLogo(Bitmap bitmap, Bitmap bitmap2, @FloatRange(from = 0.0d, m22210to = 1.0d) float f) {
        if (bitmap == null) {
            return null;
        }
        if (bitmap2 == null) {
            return bitmap;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int width2 = bitmap2.getWidth();
        int height2 = bitmap2.getHeight();
        if (width == 0 || height == 0) {
            return null;
        }
        if (width2 == 0 || height2 == 0) {
            return bitmap;
        }
        float f2 = (width * f) / width2;
        Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        try {
            Canvas canvas = new Canvas(createBitmap);
            canvas.drawBitmap(bitmap, 0.0f, 0.0f, (Paint) null);
            canvas.scale(f2, f2, width / 2, height / 2);
            canvas.drawBitmap(bitmap2, (width - width2) / 2, (height - height2) / 2, (Paint) null);
            canvas.save();
            canvas.restore();
            return createBitmap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String parseQRCode(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put(DecodeHintType.CHARACTER_SET, "utf-8");
        return parseQRCode(str, hashMap);
    }

    public static String parseQRCode(String str, Map<DecodeHintType, ?> map) {
        Result parseQRCodeResult = parseQRCodeResult(str, map);
        if (parseQRCodeResult != null) {
            return parseQRCodeResult.getText();
        }
        return null;
    }

    public static Result parseQRCodeResult(String str, Map<DecodeHintType, ?> map) {
        Result result;
        boolean z;
        Result result2 = null;
        try {
            QRCodeReader qRCodeReader = new QRCodeReader();
            RGBLuminanceSource rGBLuminanceSource = getRGBLuminanceSource(compressBitmap(str));
            if (rGBLuminanceSource != null) {
                try {
                    result = qRCodeReader.decode(new BinaryBitmap(new HybridBinarizer(rGBLuminanceSource)), map);
                    z = false;
                } catch (Exception unused) {
                    result = null;
                    z = true;
                }
                if (z) {
                    try {
                        result = qRCodeReader.decode(new BinaryBitmap(new HybridBinarizer(rGBLuminanceSource.invert())), map);
                        z = false;
                    } catch (Exception unused2) {
                        z = true;
                    }
                }
                if (z) {
                    try {
                        result = qRCodeReader.decode(new BinaryBitmap(new GlobalHistogramBinarizer(rGBLuminanceSource)), map);
                        z = false;
                    } catch (Exception unused3) {
                        z = true;
                    }
                }
                if (z) {
                    try {
                        if (rGBLuminanceSource.isRotateSupported()) {
                            try {
                                result2 = qRCodeReader.decode(new BinaryBitmap(new HybridBinarizer(rGBLuminanceSource.rotateCounterClockwise())), map);
                            } catch (Exception unused4) {
                            }
                            qRCodeReader.reset();
                        }
                    } catch (Exception e) {
                        e = e;
                        result2 = result;
                        e.printStackTrace();
                        return result2;
                    }
                }
                result2 = result;
                qRCodeReader.reset();
            }
        } catch (Exception e2) {
            e = e2;
        }
        return result2;
    }

    public static String parseCode(String str) {
        HashMap hashMap = new HashMap();
        Vector vector = new Vector();
        vector.addAll(DecodeFormatManager.ONE_D_FORMATS);
        vector.addAll(DecodeFormatManager.QR_CODE_FORMATS);
        vector.addAll(DecodeFormatManager.DATA_MATRIX_FORMATS);
        vector.addAll(DecodeFormatManager.AZTEC_FORMATS);
        vector.addAll(DecodeFormatManager.PDF417_FORMATS);
        hashMap.put(DecodeHintType.TRY_HARDER, Boolean.TRUE);
        hashMap.put(DecodeHintType.POSSIBLE_FORMATS, vector);
        return parseCode(str, hashMap);
    }

    public static String parseCode(String str, Map<DecodeHintType, Object> map) {
        Result parseCodeResult = parseCodeResult(str, map);
        if (parseCodeResult != null) {
            return parseCodeResult.getText();
        }
        return null;
    }

    public static Result parseCodeResult(String str, Map<DecodeHintType, Object> map) {
        Result result;
        boolean z;
        Result result2 = null;
        try {
            MultiFormatReader multiFormatReader = new MultiFormatReader();
            multiFormatReader.setHints(map);
            RGBLuminanceSource rGBLuminanceSource = getRGBLuminanceSource(compressBitmap(str));
            if (rGBLuminanceSource != null) {
                boolean z2 = false;
                try {
                    result = multiFormatReader.decodeWithState(new BinaryBitmap(new HybridBinarizer(rGBLuminanceSource)));
                    z = false;
                } catch (Exception unused) {
                    result = null;
                    z = true;
                }
                if (z) {
                    try {
                        result = multiFormatReader.decodeWithState(new BinaryBitmap(new HybridBinarizer(rGBLuminanceSource.invert())));
                        z = false;
                    } catch (Exception unused2) {
                        z = true;
                    }
                }
                if (z) {
                    try {
                        result2 = multiFormatReader.decodeWithState(new BinaryBitmap(new GlobalHistogramBinarizer(rGBLuminanceSource)));
                    } catch (Exception unused3) {
                        z2 = true;
                        result2 = result;
                    }
                } else {
                    z2 = z;
                    result2 = result;
                }
                if (z2 && rGBLuminanceSource.isRotateSupported()) {
                    try {
                        result2 = multiFormatReader.decodeWithState(new BinaryBitmap(new HybridBinarizer(rGBLuminanceSource.rotateCounterClockwise())));
                    } catch (Exception unused4) {
                    }
                }
                multiFormatReader.reset();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result2;
    }

    private static Bitmap compressBitmap(String str) {
        int i;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        int i2 = options.outWidth;
        int i3 = options.outHeight;
        if (i2 > i3 && i2 > 800.0f) {
            i = (int) (options.outWidth / 800.0f);
        } else {
            i = (i2 >= i3 || ((float) i3) <= 480.0f) ? 1 : (int) (options.outHeight / 480.0f);
        }
        options.inSampleSize = i > 0 ? i : 1;
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(str, options);
    }

    private static RGBLuminanceSource getRGBLuminanceSource(@NonNull Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int[] iArr = new int[width * height];
        bitmap.getPixels(iArr, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());
        return new RGBLuminanceSource(width, height, iArr);
    }

    public static Bitmap createBarCode(String str, int i, int i2) {
        return createBarCode(str, BarcodeFormat.CODE_128, i, i2, (Map<EncodeHintType, ?>) null);
    }

    public static Bitmap createBarCode(String str, BarcodeFormat barcodeFormat, int i, int i2) {
        return createBarCode(str, barcodeFormat, i, i2, (Map<EncodeHintType, ?>) null);
    }

    public static Bitmap createBarCode(String str, int i, int i2, boolean z) {
        return createBarCode(str, BarcodeFormat.CODE_128, i, i2, null, z, 40, -16777216);
    }

    public static Bitmap createBarCode(String str, int i, int i2, boolean z, @ColorInt int i3) {
        return createBarCode(str, BarcodeFormat.CODE_128, i, i2, null, z, 40, i3);
    }

    public static Bitmap createBarCode(String str, BarcodeFormat barcodeFormat, int i, int i2, Map<EncodeHintType, ?> map) {
        return createBarCode(str, barcodeFormat, i, i2, map, false, 40, -16777216);
    }

    public static Bitmap createBarCode(String str, BarcodeFormat barcodeFormat, int i, int i2, Map<EncodeHintType, ?> map, boolean z) {
        return createBarCode(str, barcodeFormat, i, i2, map, z, 40, -16777216);
    }

    public static Bitmap createBarCode(String str, BarcodeFormat barcodeFormat, int i, int i2, boolean z, @ColorInt int i3) {
        return createBarCode(str, barcodeFormat, i, i2, null, z, 40, i3);
    }

    public static Bitmap createBarCode(String str, BarcodeFormat barcodeFormat, int i, int i2, Map<EncodeHintType, ?> map, boolean z, @ColorInt int i3) {
        return createBarCode(str, barcodeFormat, i, i2, map, z, 40, i3);
    }

    public static Bitmap createBarCode(String str, BarcodeFormat barcodeFormat, int i, int i2, Map<EncodeHintType, ?> map, boolean z, int i3, @ColorInt int i4) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            BitMatrix encode = new MultiFormatWriter().encode(str, barcodeFormat, i, i2, map);
            int width = encode.getWidth();
            int height = encode.getHeight();
            int[] iArr = new int[width * height];
            for (int i5 = 0; i5 < height; i5++) {
                int i6 = i5 * width;
                for (int i7 = 0; i7 < width; i7++) {
                    iArr[i6 + i7] = encode.get(i7, i5) ? i4 : -1;
                }
            }
            Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            createBitmap.setPixels(iArr, 0, width, 0, 0, width, height);
            return z ? addCode(createBitmap, str, i3, i4, i3 / 2) : createBitmap;
        } catch (WriterException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Bitmap addCode(Bitmap bitmap, String str, int i, @ColorInt int i2, int i3) {
        if (bitmap == null) {
            return null;
        }
        if (TextUtils.isEmpty(str)) {
            return bitmap;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (width <= 0 || height <= 0) {
            return null;
        }
        Bitmap createBitmap = Bitmap.createBitmap(width, height + i + (i3 * 2), Bitmap.Config.ARGB_8888);
        try {
            Canvas canvas = new Canvas(createBitmap);
            canvas.drawBitmap(bitmap, 0.0f, 0.0f, (Paint) null);
            TextPaint textPaint = new TextPaint();
            textPaint.setTextSize(i);
            textPaint.setColor(i2);
            textPaint.setTextAlign(Paint.Align.CENTER);
            canvas.drawText(str, width / 2, height + (i / 2) + i3, textPaint);
            canvas.save();
            canvas.restore();
            return createBitmap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
