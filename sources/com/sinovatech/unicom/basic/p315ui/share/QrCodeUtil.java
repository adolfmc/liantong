package com.sinovatech.unicom.basic.p315ui.share;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.util.Hashtable;

/* renamed from: com.sinovatech.unicom.basic.ui.share.QrCodeUtil */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class QrCodeUtil {
    private static final int BLACK = -16777216;
    private static final int CODE_WIDTH = 1280;
    private static final int LOGO_WIDTH_MAX = 640;
    private static final int LOGO_WIDTH_MIN = 640;
    private static final int WHITE = -1;
    private Uri imageFileUri;

    public Bitmap createCode(String str, String str2, String str3, String str4, Bitmap bitmap) throws WriterException {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.setScale(640 / width, 640 / height);
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, false);
        int width2 = createBitmap.getWidth();
        int height2 = createBitmap.getHeight();
        Hashtable hashtable = new Hashtable();
        hashtable.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        hashtable.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hashtable.put(EncodeHintType.MAX_SIZE, 640);
        hashtable.put(EncodeHintType.MIN_SIZE, 640);
        hashtable.put(EncodeHintType.MARGIN, 2);
        BitMatrix encode = new MultiFormatWriter().encode(str4, BarcodeFormat.QR_CODE, 1280, 1280, hashtable);
        int width3 = encode.getWidth();
        int height3 = encode.getHeight();
        int i = width3 / 2;
        int i2 = height3 / 2;
        int[] iArr = new int[width3 * height3];
        for (int i3 = 0; i3 < height3; i3++) {
            for (int i4 = 0; i4 < width3; i4++) {
                int i5 = width2 / 2;
                if (i4 > i - i5 && i4 < i + i5) {
                    int i6 = height2 / 2;
                    if (i3 > i2 - i6 && i3 < i2 + i6) {
                        iArr[(i3 * width3) + i4] = createBitmap.getPixel((i4 - i) + i5, (i3 - i2) + i6);
                    }
                }
                iArr[(i3 * width3) + i4] = encode.get(i4, i3) ? -16777216 : -1;
            }
        }
        Bitmap createBitmap2 = Bitmap.createBitmap(width3, height3, Bitmap.Config.ARGB_8888);
        createBitmap2.setPixels(iArr, 0, width3, 0, 0, width3, height3);
        return createBitmap2;
    }

    public static Bitmap createCode(Context context, String str) throws WriterException {
        try {
            Hashtable hashtable = new Hashtable();
            hashtable.put(EncodeHintType.MARGIN, 0);
            hashtable.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            hashtable.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
            BitMatrix encode = new MultiFormatWriter().encode(str, BarcodeFormat.QR_CODE, dp2px(context, 250.0f), dp2px(context, 250.0f), hashtable);
            int width = encode.getWidth();
            int height = encode.getHeight();
            int[] iArr = new int[width * height];
            for (int i = 0; i < height; i++) {
                for (int i2 = 0; i2 < width; i2++) {
                    if (encode.get(i2, i)) {
                        iArr[(i * width) + i2] = -16777216;
                    } else {
                        iArr[(i * width) + i2] = -1;
                    }
                }
            }
            Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            createBitmap.setPixels(iArr, 0, width, 0, 0, width, height);
            return createBitmap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Bitmap createCode(Context context, String str, int i, int i2) throws WriterException {
        try {
            Hashtable hashtable = new Hashtable();
            hashtable.put(EncodeHintType.MARGIN, 0);
            hashtable.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            hashtable.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
            BitMatrix encode = new MultiFormatWriter().encode(str, BarcodeFormat.QR_CODE, dp2px(context, 250.0f), dp2px(context, 250.0f), hashtable);
            int width = encode.getWidth();
            int height = encode.getHeight();
            int[] iArr = new int[width * height];
            for (int i3 = 0; i3 < height; i3++) {
                for (int i4 = 0; i4 < width; i4++) {
                    if (encode.get(i4, i3)) {
                        iArr[(i3 * width) + i4] = -16777216;
                    } else {
                        iArr[(i3 * width) + i4] = -1;
                    }
                }
            }
            Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            createBitmap.setPixels(iArr, 0, width, 0, 0, width, height);
            return createBitmap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int dp2px(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static float px2dp(Context context, int i) {
        return i / context.getResources().getDisplayMetrics().density;
    }
}
