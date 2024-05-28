package vi.com.gdi.bgl.android.java;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Build;
import android.text.Layout;
import android.text.TextPaint;
import android.util.SparseArray;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class EnvDrawText {
    private static final String DEVICE_VIVOX3L = "vivo X3L";
    private static final int FONT_STYLE_BOLD = 1;
    private static final int FONT_STYLE_ITALIC = 2;
    private static final int FONT_STYLE_NORMAL = 0;
    public static int[] buffer;
    private static Bitmap defaultFontBmp;
    public static SparseArray<C14248a> fontCache;
    private static Context mContext;

    /* JADX WARN: Removed duplicated region for block: B:118:0x0275 A[EDGE_INSN: B:118:0x0275->B:95:0x0275 ?: BREAK  , SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:66:0x01d1 A[Catch: all -> 0x02de, TryCatch #0 {, blocks: (B:4:0x0011, B:6:0x0020, B:10:0x002c, B:12:0x0049, B:13:0x005c, B:15:0x006b, B:17:0x008f, B:23:0x00ca, B:25:0x00ce, B:28:0x00d7, B:30:0x00df, B:33:0x00e4, B:35:0x00e9, B:37:0x00ed, B:40:0x00f9, B:41:0x0117, B:102:0x02c5, B:104:0x02ca, B:106:0x02d3, B:108:0x02d9, B:38:0x00f4, B:42:0x0129, B:43:0x0139, B:45:0x0141, B:48:0x0150, B:49:0x0159, B:51:0x015f, B:54:0x0172, B:56:0x018c, B:64:0x01cd, B:66:0x01d1, B:69:0x01da, B:71:0x01e2, B:74:0x01e7, B:76:0x01ef, B:78:0x01f3, B:80:0x01fd, B:88:0x0216, B:90:0x021e, B:92:0x0229, B:94:0x0256, B:95:0x0275, B:97:0x0283, B:99:0x0290, B:100:0x02b0, B:85:0x020d, B:86:0x0211, B:79:0x01fa), top: B:114:0x0011 }] */
    /* JADX WARN: Removed duplicated region for block: B:78:0x01f3 A[Catch: all -> 0x02de, TryCatch #0 {, blocks: (B:4:0x0011, B:6:0x0020, B:10:0x002c, B:12:0x0049, B:13:0x005c, B:15:0x006b, B:17:0x008f, B:23:0x00ca, B:25:0x00ce, B:28:0x00d7, B:30:0x00df, B:33:0x00e4, B:35:0x00e9, B:37:0x00ed, B:40:0x00f9, B:41:0x0117, B:102:0x02c5, B:104:0x02ca, B:106:0x02d3, B:108:0x02d9, B:38:0x00f4, B:42:0x0129, B:43:0x0139, B:45:0x0141, B:48:0x0150, B:49:0x0159, B:51:0x015f, B:54:0x0172, B:56:0x018c, B:64:0x01cd, B:66:0x01d1, B:69:0x01da, B:71:0x01e2, B:74:0x01e7, B:76:0x01ef, B:78:0x01f3, B:80:0x01fd, B:88:0x0216, B:90:0x021e, B:92:0x0229, B:94:0x0256, B:95:0x0275, B:97:0x0283, B:99:0x0290, B:100:0x02b0, B:85:0x020d, B:86:0x0211, B:79:0x01fa), top: B:114:0x0011 }] */
    /* JADX WARN: Removed duplicated region for block: B:79:0x01fa A[Catch: all -> 0x02de, TryCatch #0 {, blocks: (B:4:0x0011, B:6:0x0020, B:10:0x002c, B:12:0x0049, B:13:0x005c, B:15:0x006b, B:17:0x008f, B:23:0x00ca, B:25:0x00ce, B:28:0x00d7, B:30:0x00df, B:33:0x00e4, B:35:0x00e9, B:37:0x00ed, B:40:0x00f9, B:41:0x0117, B:102:0x02c5, B:104:0x02ca, B:106:0x02d3, B:108:0x02d9, B:38:0x00f4, B:42:0x0129, B:43:0x0139, B:45:0x0141, B:48:0x0150, B:49:0x0159, B:51:0x015f, B:54:0x0172, B:56:0x018c, B:64:0x01cd, B:66:0x01d1, B:69:0x01da, B:71:0x01e2, B:74:0x01e7, B:76:0x01ef, B:78:0x01f3, B:80:0x01fd, B:88:0x0216, B:90:0x021e, B:92:0x0229, B:94:0x0256, B:95:0x0275, B:97:0x0283, B:99:0x0290, B:100:0x02b0, B:85:0x020d, B:86:0x0211, B:79:0x01fa), top: B:114:0x0011 }] */
    /* JADX WARN: Removed duplicated region for block: B:82:0x0207  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x020a  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x021e A[Catch: all -> 0x02de, TryCatch #0 {, blocks: (B:4:0x0011, B:6:0x0020, B:10:0x002c, B:12:0x0049, B:13:0x005c, B:15:0x006b, B:17:0x008f, B:23:0x00ca, B:25:0x00ce, B:28:0x00d7, B:30:0x00df, B:33:0x00e4, B:35:0x00e9, B:37:0x00ed, B:40:0x00f9, B:41:0x0117, B:102:0x02c5, B:104:0x02ca, B:106:0x02d3, B:108:0x02d9, B:38:0x00f4, B:42:0x0129, B:43:0x0139, B:45:0x0141, B:48:0x0150, B:49:0x0159, B:51:0x015f, B:54:0x0172, B:56:0x018c, B:64:0x01cd, B:66:0x01d1, B:69:0x01da, B:71:0x01e2, B:74:0x01e7, B:76:0x01ef, B:78:0x01f3, B:80:0x01fd, B:88:0x0216, B:90:0x021e, B:92:0x0229, B:94:0x0256, B:95:0x0275, B:97:0x0283, B:99:0x0290, B:100:0x02b0, B:85:0x020d, B:86:0x0211, B:79:0x01fa), top: B:114:0x0011 }] */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0283 A[Catch: all -> 0x02de, TryCatch #0 {, blocks: (B:4:0x0011, B:6:0x0020, B:10:0x002c, B:12:0x0049, B:13:0x005c, B:15:0x006b, B:17:0x008f, B:23:0x00ca, B:25:0x00ce, B:28:0x00d7, B:30:0x00df, B:33:0x00e4, B:35:0x00e9, B:37:0x00ed, B:40:0x00f9, B:41:0x0117, B:102:0x02c5, B:104:0x02ca, B:106:0x02d3, B:108:0x02d9, B:38:0x00f4, B:42:0x0129, B:43:0x0139, B:45:0x0141, B:48:0x0150, B:49:0x0159, B:51:0x015f, B:54:0x0172, B:56:0x018c, B:64:0x01cd, B:66:0x01d1, B:69:0x01da, B:71:0x01e2, B:74:0x01e7, B:76:0x01ef, B:78:0x01f3, B:80:0x01fd, B:88:0x0216, B:90:0x021e, B:92:0x0229, B:94:0x0256, B:95:0x0275, B:97:0x0283, B:99:0x0290, B:100:0x02b0, B:85:0x020d, B:86:0x0211, B:79:0x01fa), top: B:114:0x0011 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static synchronized int[] drawText(java.lang.String r23, int r24, int r25, int[] r26, int r27, int r28, int r29, int r30, int r31) {
        /*
            Method dump skipped, instructions count: 737
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: vi.com.gdi.bgl.android.java.EnvDrawText.drawText(java.lang.String, int, int, int[], int, int, int, int, int):int[]");
    }

    private static Bitmap drawTextAlpha(String str, int i, int i2, int i3) {
        int ceil;
        int desiredWidth;
        Canvas canvas = new Canvas();
        TextPaint textPaint = new TextPaint();
        String str2 = Build.MODEL;
        int i4 = 0;
        int i5 = (str2 == null || !str2.equals("vivo X3L")) ? i2 : 0;
        textPaint.reset();
        textPaint.setSubpixelText(false);
        textPaint.setAntiAlias(false);
        textPaint.setTextSize(i);
        textPaint.setTypeface(getTypeface(i5));
        float f = (i3 * 1.3f) + 0.5f;
        int i6 = 92;
        int indexOf = str.indexOf(92, 0);
        Bitmap bitmap = null;
        if (indexOf == -1) {
            Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
            int desiredWidth2 = (int) (Layout.getDesiredWidth(str, 0, str.length(), textPaint) + f);
            int ceil2 = (int) Math.ceil(fontMetrics.descent - fontMetrics.ascent);
            if (desiredWidth2 > 0 && ceil2 > 0) {
                bitmap = Bitmap.createBitmap(desiredWidth2, ceil2, Bitmap.Config.ALPHA_8);
                if (bitmap == null) {
                    return bitmap;
                }
                bitmap.eraseColor(0);
                canvas.setBitmap(bitmap);
            }
            textPaint.setStyle(Paint.Style.FILL);
            canvas.drawText(str, f * 0.5f, 0.0f - fontMetrics.ascent, textPaint);
        } else {
            int i7 = indexOf + 1;
            int i8 = 2;
            int desiredWidth3 = (int) (Layout.getDesiredWidth(str.substring(0, indexOf), textPaint) + 0.5d);
            while (true) {
                int indexOf2 = str.indexOf(i6, i7);
                if (indexOf2 <= 0) {
                    break;
                }
                int desiredWidth4 = (int) (Layout.getDesiredWidth(str.substring(i7, indexOf2), textPaint) + 0.5d);
                if (desiredWidth4 > desiredWidth3) {
                    desiredWidth3 = desiredWidth4;
                }
                i7 = indexOf2 + 1;
                i8++;
                i6 = 92;
            }
            if (i7 != str.length() && (desiredWidth = (int) (Layout.getDesiredWidth(str.substring(i7, str.length()), textPaint) + 0.5d)) > desiredWidth3) {
                desiredWidth3 = desiredWidth;
            }
            Paint.FontMetrics fontMetrics2 = textPaint.getFontMetrics();
            int i9 = desiredWidth3 + i3;
            int ceil3 = i8 * ((int) Math.ceil(fontMetrics2.descent - fontMetrics2.ascent));
            if (i9 > 0 && ceil3 > 0) {
                bitmap = Bitmap.createBitmap(i9, ceil3, Bitmap.Config.ALPHA_8);
                if (bitmap == null) {
                    return bitmap;
                }
                bitmap.eraseColor(0);
                canvas.setBitmap(bitmap);
            }
            textPaint.setTextAlign(getTextAlignedType(3));
            float f2 = i9 - (f * 0.5f);
            int i10 = 0;
            while (true) {
                int indexOf3 = str.indexOf(92, i4);
                if (indexOf3 <= 0) {
                    break;
                }
                String substring = str.substring(i4, indexOf3);
                Layout.getDesiredWidth(substring, textPaint);
                textPaint.setStyle(Paint.Style.FILL);
                canvas.drawText(substring, f2, (i10 * ceil) - fontMetrics2.ascent, textPaint);
                i10++;
                i4 = indexOf3 + 1;
            }
            if (i4 != str.length()) {
                String substring2 = str.substring(i4, str.length());
                Layout.getDesiredWidth(substring2, textPaint);
                textPaint.setStyle(Paint.Style.FILL);
                canvas.drawText(substring2, f2, (i10 * ceil) - fontMetrics2.ascent, textPaint);
            }
        }
        return bitmap;
    }

    private static synchronized Bitmap drawTextExt(String str, int i, int i2, int[] iArr, int i3, int i4, int i5, int i6, int i7) {
        Paint.FontMetrics fontMetrics;
        int i8;
        int i9;
        Bitmap bitmap;
        int i10;
        int i11;
        Bitmap bitmap2;
        Paint.FontMetrics fontMetrics2;
        int desiredWidth;
        int i12;
        int i13;
        synchronized (EnvDrawText.class) {
            Canvas canvas = new Canvas();
            TextPaint textPaint = new TextPaint();
            String str2 = Build.MODEL;
            int i14 = (str2 == null || !str2.equals("vivo X3L")) ? i2 : 0;
            textPaint.reset();
            textPaint.setSubpixelText(true);
            textPaint.setAntiAlias(true);
            textPaint.setTextSize(i);
            textPaint.setShadowLayer(0.0f, 0.0f, 0.0f, 0);
            textPaint.setTypeface(getTypeface(i14));
            if (i6 != 0) {
                textPaint.setStrokeWidth(i6);
                textPaint.setStrokeCap(Paint.Cap.ROUND);
                textPaint.setStrokeJoin(Paint.Join.ROUND);
                textPaint.setStyle(Paint.Style.STROKE);
            }
            int indexOf = str.indexOf(92, 0);
            if (indexOf == -1) {
                Paint.FontMetrics fontMetrics3 = textPaint.getFontMetrics();
                int desiredWidth2 = (int) (Layout.getDesiredWidth(str, 0, str.length(), textPaint) + 0.5d);
                int ceil = (int) Math.ceil(fontMetrics3.descent - fontMetrics3.ascent);
                iArr[0] = desiredWidth2;
                iArr[1] = ceil;
                if (iArr.length == 4) {
                    i13 = (int) Math.pow(2.0d, (int) Math.ceil(Math.log(ceil) / Math.log(2.0d)));
                    i12 = (int) Math.pow(2.0d, (int) Math.ceil(Math.log(desiredWidth2) / Math.log(2.0d)));
                } else {
                    i12 = desiredWidth2;
                    i13 = ceil;
                }
                if (i12 == 0 && i13 == 0) {
                    i13 = 0;
                    i12 = 0;
                }
                if (iArr.length == 4) {
                    iArr[2] = i12;
                    iArr[3] = i13;
                }
                if (i12 <= 0 || i13 <= 0) {
                    bitmap2 = null;
                } else {
                    bitmap2 = Bitmap.createBitmap(i12, i13, Bitmap.Config.ARGB_8888);
                    if (bitmap2 == null) {
                        return bitmap2;
                    }
                    canvas.setBitmap(bitmap2);
                }
                if ((i5 & (-16777216)) == 0) {
                    canvas.drawColor(16777215);
                } else {
                    canvas.drawColor(i5);
                }
                if (i6 != 0) {
                    textPaint.setStrokeWidth(i6);
                    textPaint.setStrokeCap(Paint.Cap.ROUND);
                    textPaint.setStrokeJoin(Paint.Join.ROUND);
                    textPaint.setStyle(Paint.Style.STROKE);
                    textPaint.setColor(i4);
                    canvas.drawText(str, 0.0f, 0.0f - fontMetrics3.ascent, textPaint);
                }
                textPaint.setStyle(Paint.Style.FILL);
                textPaint.setColor(i3);
                canvas.drawText(str, 0.0f, 0.0f - fontMetrics3.ascent, textPaint);
            } else {
                int i15 = indexOf + 1;
                int desiredWidth3 = (int) (Layout.getDesiredWidth(str.substring(0, indexOf), textPaint) + 0.5d);
                int i16 = 2;
                while (true) {
                    int indexOf2 = str.indexOf(92, i15);
                    if (indexOf2 <= 0) {
                        break;
                    }
                    int desiredWidth4 = (int) (Layout.getDesiredWidth(str.substring(i15, indexOf2), textPaint) + 0.5d);
                    if (desiredWidth4 > desiredWidth3) {
                        desiredWidth3 = desiredWidth4;
                    }
                    i15 = indexOf2 + 1;
                    i16++;
                }
                if (i15 != str.length() && (desiredWidth = (int) (Layout.getDesiredWidth(str.substring(i15, str.length()), textPaint) + 0.5d)) > desiredWidth3) {
                    desiredWidth3 = desiredWidth;
                }
                Paint.FontMetrics fontMetrics4 = textPaint.getFontMetrics();
                int ceil2 = (int) Math.ceil(fontMetrics4.descent - fontMetrics4.ascent);
                int i17 = i16 * ceil2;
                iArr[0] = desiredWidth3;
                iArr[1] = i17;
                if (iArr.length == 4) {
                    fontMetrics = fontMetrics4;
                    i8 = ceil2;
                    desiredWidth3 = (int) Math.pow(2.0d, (int) Math.ceil(Math.log(desiredWidth3) / Math.log(2.0d)));
                    i9 = (int) Math.pow(2.0d, (int) Math.ceil(Math.log(i17) / Math.log(2.0d)));
                } else {
                    fontMetrics = fontMetrics4;
                    i8 = ceil2;
                    i9 = i17;
                }
                int i18 = desiredWidth3;
                if (i18 == 0 && i9 == 0) {
                    i9 = 0;
                    i18 = 0;
                }
                if (iArr.length == 4) {
                    iArr[2] = i18;
                    iArr[3] = i9;
                }
                if (i18 <= 0 || i9 <= 0) {
                    bitmap = null;
                } else {
                    Bitmap createBitmap = Bitmap.createBitmap(i18, i9, Bitmap.Config.ARGB_8888);
                    if (createBitmap == null) {
                        return createBitmap;
                    }
                    canvas.setBitmap(createBitmap);
                    bitmap = createBitmap;
                }
                if ((i5 & (-16777216)) == 0) {
                    canvas.drawColor(16777215);
                } else {
                    canvas.drawColor(i5);
                }
                textPaint.setTextAlign(getTextAlignedType(i7));
                if (i7 == 1) {
                    i10 = 0;
                    i11 = 0;
                } else if (i7 == 2) {
                    i10 = 0;
                    i11 = iArr[0];
                } else {
                    i10 = 0;
                    i11 = iArr[0] / 2;
                }
                int i19 = i10;
                while (true) {
                    int indexOf3 = str.indexOf(92, i10);
                    if (indexOf3 <= 0) {
                        break;
                    }
                    String substring = str.substring(i10, indexOf3);
                    Layout.getDesiredWidth(substring, textPaint);
                    i10 = indexOf3 + 1;
                    if (i6 != 0) {
                        textPaint.setStrokeWidth(i6);
                        textPaint.setStrokeCap(Paint.Cap.ROUND);
                        textPaint.setStrokeJoin(Paint.Join.ROUND);
                        textPaint.setStyle(Paint.Style.STROKE);
                        textPaint.setColor(i4);
                        fontMetrics2 = fontMetrics;
                        canvas.drawText(substring, i11, (i19 * i8) - fontMetrics2.ascent, textPaint);
                    } else {
                        fontMetrics2 = fontMetrics;
                    }
                    textPaint.setStyle(Paint.Style.FILL);
                    textPaint.setColor(i3);
                    canvas.drawText(substring, i11, (i19 * i8) - fontMetrics2.ascent, textPaint);
                    i19++;
                    fontMetrics = fontMetrics2;
                }
                Paint.FontMetrics fontMetrics5 = fontMetrics;
                if (i10 != str.length()) {
                    String substring2 = str.substring(i10, str.length());
                    Layout.getDesiredWidth(substring2, textPaint);
                    if (i6 != 0) {
                        textPaint.setStrokeWidth(i6);
                        textPaint.setStrokeCap(Paint.Cap.ROUND);
                        textPaint.setStrokeJoin(Paint.Join.ROUND);
                        textPaint.setStyle(Paint.Style.STROKE);
                        textPaint.setColor(i4);
                        canvas.drawText(substring2, i11, (i19 * i8) - fontMetrics5.ascent, textPaint);
                    }
                    textPaint.setStyle(Paint.Style.FILL);
                    textPaint.setColor(i3);
                    canvas.drawText(substring2, i11, (i19 * i8) - fontMetrics5.ascent, textPaint);
                }
                bitmap2 = bitmap;
            }
            return bitmap2;
        }
    }

    private static Paint.Align getTextAlignedType(int i) {
        return 1 == i ? Paint.Align.LEFT : 2 == i ? Paint.Align.RIGHT : Paint.Align.CENTER;
    }

    private static Bitmap getTextBitmap() {
        Paint paint = new Paint();
        paint.setSubpixelText(true);
        paint.setAntiAlias(false);
        paint.setTextSize(12.0f);
        paint.setTypeface(Typeface.DEFAULT);
        float measureText = paint.measureText("!");
        float descent = paint.descent() - paint.ascent();
        if (measureText <= 0.0f) {
            measureText = 3.0f;
        }
        if (descent <= 0.0f) {
            descent = 15.0f;
        }
        Bitmap createBitmap = Bitmap.createBitmap((int) Math.ceil(measureText), (int) Math.ceil(descent), Bitmap.Config.ALPHA_8);
        createBitmap.eraseColor(0);
        Canvas canvas = new Canvas();
        canvas.setBitmap(createBitmap);
        canvas.drawText("!", 0.0f, 0.0f - paint.ascent(), paint);
        return createBitmap;
    }

    private static short[] getTextSize(String str, int i, int i2) {
        int i3;
        int length = str.length();
        if (length == 0) {
            return null;
        }
        TextPaint textPaint = new TextPaint();
        textPaint.setSubpixelText(true);
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(i);
        textPaint.setTypeface(getTypeface(i2));
        short[] sArr = new short[length];
        for (int i4 = 0; i4 < length; i4++) {
            sArr[i4] = (short) (Layout.getDesiredWidth(str, 0, i3, textPaint) + 0.5d);
        }
        return sArr;
    }

    private static float[] getTextSizeExt(String str, int i, int i2) {
        if (str.length() == 0) {
            return null;
        }
        Paint paint = new Paint();
        paint.setSubpixelText(true);
        paint.setAntiAlias(true);
        paint.setTextSize(i);
        paint.setTypeface(getTypeface(i2));
        return new float[]{paint.measureText(str), paint.descent() - paint.ascent()};
    }

    private static Typeface getTypeface(int i) {
        Typeface typeface = Typeface.DEFAULT;
        switch (i) {
            case 1:
                return Typeface.DEFAULT_BOLD;
            case 2:
                return Typeface.create(Typeface.DEFAULT, 2);
            default:
                return Typeface.DEFAULT;
        }
    }

    private static synchronized boolean isSystemFontChanged() {
        synchronized (EnvDrawText.class) {
            if (defaultFontBmp == null) {
                defaultFontBmp = getTextBitmap();
                return false;
            }
            Bitmap textBitmap = getTextBitmap();
            if (!(!nativeIsBitmapSame(textBitmap, defaultFontBmp))) {
                textBitmap.recycle();
                return false;
            }
            defaultFontBmp.recycle();
            defaultFontBmp = Bitmap.createBitmap(textBitmap);
            textBitmap.recycle();
            return true;
        }
    }

    private static native boolean nativeIsBitmapSame(Bitmap bitmap, Bitmap bitmap2);

    public static synchronized void registFontCache(int i, Typeface typeface) {
        synchronized (EnvDrawText.class) {
            if (i == 0 || typeface == null) {
                return;
            }
            if (fontCache == null) {
                fontCache = new SparseArray<>();
            }
            C14248a c14248a = fontCache.get(i);
            if (c14248a == null) {
                C14248a c14248a2 = new C14248a();
                c14248a2.f27762a = typeface;
                c14248a2.f27763b++;
                fontCache.put(i, c14248a2);
            } else {
                c14248a.f27763b++;
            }
        }
    }

    public static synchronized void removeFontCache(int i) {
        synchronized (EnvDrawText.class) {
            C14248a c14248a = fontCache.get(i);
            if (c14248a == null) {
                return;
            }
            c14248a.f27763b--;
            if (c14248a.f27763b == 0) {
                fontCache.remove(i);
            }
        }
    }

    public static void setContext(Context context) {
        mContext = context;
    }
}
