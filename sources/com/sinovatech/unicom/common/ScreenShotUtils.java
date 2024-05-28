package com.sinovatech.unicom.common;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Picture;
import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.megvii.livenesslib.util.SDCardUtil;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class ScreenShotUtils {
    public static final String LongWebviewCaptureFileName = ".jpg";
    public static String LongWebviewCapturePicture = "";

    public static String screenshot(Activity activity) {
        View decorView = activity.getWindow().getDecorView();
        decorView.setDrawingCacheEnabled(true);
        decorView.buildDrawingCache();
        Bitmap drawingCache = decorView.getDrawingCache();
        String str = "";
        if (drawingCache != null) {
            try {
                str = SDCardUtil.getMediaFileUrl("screenshot") + "screenshot.png";
                FileTools.createPath(str);
                FileOutputStream fileOutputStream = new FileOutputStream(new File(str));
                drawingCache.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
                fileOutputStream.flush();
                fileOutputStream.close();
                return str;
            } catch (Exception e) {
                e.printStackTrace();
                return str;
            }
        }
        return "";
    }

    public static Bitmap captureWebView(WebView webView) {
        Bitmap createBitmap;
        if (webView == null) {
            return null;
        }
        webView.setDrawingCacheEnabled(true);
        webView.buildDrawingCache();
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        webView.measure(makeMeasureSpec, makeMeasureSpec);
        if (webView.getMeasuredWidth() <= 0 || webView.getMeasuredHeight() <= 0) {
            return null;
        }
        try {
            createBitmap = Bitmap.createBitmap(webView.getMeasuredWidth(), webView.getMeasuredHeight(), Bitmap.Config.RGB_565);
        } catch (OutOfMemoryError unused) {
            System.gc();
            try {
                createBitmap = Bitmap.createBitmap(webView.getMeasuredWidth(), webView.getMeasuredHeight(), Bitmap.Config.RGB_565);
            } catch (OutOfMemoryError unused2) {
                return null;
            }
        }
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawBitmap(createBitmap, 0.0f, createBitmap.getHeight(), new Paint());
        webView.draw(canvas);
        return createBitmap;
    }

    public static Bitmap createSnapshotWithBottomLogo(Context context, WebView webView, Bitmap bitmap, boolean z) {
        Bitmap createBitmap;
        Bitmap bitmap2;
        webView.setDrawingCacheEnabled(true);
        webView.buildDrawingCache();
        Bitmap decodeResource = BitmapFactory.decodeResource(context.getResources(), 2131232331);
        if (Build.VERSION.SDK_INT < 19) {
            Picture capturePicture = webView.capturePicture();
            int width = capturePicture.getWidth();
            int height = capturePicture.getHeight();
            Bitmap fitBitmap = ImageCompressUtils.fitBitmap(decodeResource, width);
            int height2 = fitBitmap.getHeight();
            if (width <= 0 || height <= 0) {
                bitmap2 = null;
            } else {
                bitmap2 = Bitmap.createBitmap(width, height + height2, Bitmap.Config.RGB_565);
                Canvas canvas = new Canvas(bitmap2);
                capturePicture.draw(canvas);
                drawLogo(context, height2, width, height, canvas, fitBitmap, bitmap);
            }
            createBitmap = bitmap2;
        } else {
            int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
            webView.measure(makeMeasureSpec, makeMeasureSpec);
            int measuredWidth = webView.getMeasuredWidth();
            int measuredHeight = webView.getMeasuredHeight();
            Bitmap fitBitmap2 = ImageCompressUtils.fitBitmap(decodeResource, measuredWidth);
            int height3 = z ? fitBitmap2.getHeight() : 0;
            createBitmap = Bitmap.createBitmap(measuredWidth, measuredHeight + height3, Bitmap.Config.RGB_565);
            Canvas canvas2 = new Canvas(createBitmap);
            webView.draw(canvas2);
            drawLogo(context, height3, measuredWidth, measuredHeight, canvas2, fitBitmap2, bitmap);
        }
        decodeResource.recycle();
        return createBitmap;
    }

    private static void drawLogo(Context context, float f, int i, float f2, Canvas canvas, Bitmap bitmap, Bitmap bitmap2) {
        float height = bitmap2.getHeight();
        float f3 = (f <= 0.1f || height <= 0.1f) ? 1.0f : (f / height) * 0.7f * (context.getResources().getDisplayMetrics().density / CustomDensityHandler.sNonCompatDensity);
        Matrix matrix = new Matrix();
        matrix.postScale(f3, f3);
        createBi(f, i, f2, canvas, bitmap, Bitmap.createBitmap(bitmap2, 0, 0, bitmap2.getWidth(), bitmap2.getHeight(), matrix, true));
    }

    private static void createBi(float f, int i, float f2, Canvas canvas, Bitmap bitmap, Bitmap bitmap2) {
        if (f > 0.0f) {
            Paint paint = new Paint();
            canvas.drawBitmap(bitmap, 0.0f, f2, paint);
            int height = (bitmap.getHeight() - bitmap2.getHeight()) / 2;
            if (bitmap2 != null) {
                canvas.drawBitmap(bitmap2, i * 0.75f, f2 + height, paint);
                bitmap2.recycle();
            }
        }
        if (bitmap != null) {
            bitmap.recycle();
        }
    }

    public static Bitmap encodeAsBitmap(String str, int i) {
        Bitmap bitmap = null;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            BitMatrix encode = new MultiFormatWriter().encode(str, BarcodeFormat.QR_CODE, i, i);
            int width = encode.getWidth();
            int height = encode.getHeight();
            int[] iArr = new int[width * height];
            for (int i2 = 0; i2 < height; i2++) {
                for (int i3 = 0; i3 < width; i3++) {
                    if (encode.get(i3, i2)) {
                        iArr[(i2 * width) + i3] = -16777216;
                    }
                }
            }
            Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            try {
                createBitmap.setPixels(iArr, 0, width, 0, 0, width, height);
                return createBitmap;
            } catch (WriterException e) {
                bitmap = createBitmap;
                e = e;
                e.printStackTrace();
                return bitmap;
            }
        } catch (WriterException e2) {
            e = e2;
        }
    }

    public static void SaveBitmapToFile(Context context, Bitmap bitmap) {
        FileOutputStream fileOutputStream = null;
        try {
            try {
                try {
                    String GetLongWebviewCaptureFilepath = GetLongWebviewCaptureFilepath();
                    if (!FileTools.fileIsExists(GetLongWebviewCaptureFilepath)) {
                        FileTools.createPath(GetLongWebviewCaptureFilepath);
                    }
                    FileOutputStream fileOutputStream2 = new FileOutputStream(new File(GetLongWebviewCaptureFilepath));
                    try {
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream2);
                        fileOutputStream2.flush();
                        fileOutputStream2.close();
                    } catch (Exception e) {
                        e = e;
                        fileOutputStream = fileOutputStream2;
                        e.printStackTrace();
                        if (fileOutputStream != null) {
                            fileOutputStream.flush();
                            fileOutputStream.close();
                        }
                    } catch (Throwable th) {
                        th = th;
                        fileOutputStream = fileOutputStream2;
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.flush();
                                fileOutputStream.close();
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Exception e3) {
                e = e3;
            }
        } catch (IOException e4) {
            e4.printStackTrace();
        }
    }

    public static String GetLongWebviewCaptureFilepath() {
        LongWebviewCapturePicture = SDCardUtil.getExternalDir(Environment.DIRECTORY_DCIM, "") + (System.currentTimeMillis() / 1000) + ".jpg";
        return LongWebviewCapturePicture;
    }

    public static boolean saveImageToGallery(Context context, Bitmap bitmap) {
        String GetLongWebviewCaptureFilepath = GetLongWebviewCaptureFilepath();
        if (!FileTools.fileIsExists(GetLongWebviewCaptureFilepath)) {
            FileTools.createPath(GetLongWebviewCaptureFilepath);
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(GetLongWebviewCaptureFilepath));
            boolean compress = bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            return compress;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
