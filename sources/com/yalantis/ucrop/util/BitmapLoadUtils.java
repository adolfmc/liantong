package com.yalantis.ucrop.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Point;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.WindowManager;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.yalantis.ucrop.callback.BitmapLoadCallback;
import com.yalantis.ucrop.task.BitmapLoadTask;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class BitmapLoadUtils {
    private static final String TAG = "BitmapLoadUtils";

    public static int exifToDegrees(int i) {
        switch (i) {
            case 3:
            case 4:
                return 180;
            case 5:
            case 6:
                return 90;
            case 7:
            case 8:
                return SubsamplingScaleImageView.ORIENTATION_270;
            default:
                return 0;
        }
    }

    public static int exifToTranslation(int i) {
        if (i != 2 && i != 7) {
            switch (i) {
                case 4:
                case 5:
                    break;
                default:
                    return 1;
            }
        }
        return -1;
    }

    public static void decodeBitmapInBackground(@NonNull Context context, @NonNull Uri uri, @Nullable Uri uri2, int i, int i2, BitmapLoadCallback bitmapLoadCallback) {
        new BitmapLoadTask(context, uri, uri2, i, i2, bitmapLoadCallback).execute(new Void[0]);
    }

    public static Bitmap transformBitmap(@NonNull Bitmap bitmap, @NonNull Matrix matrix) {
        try {
            Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            return !bitmap.sameAs(createBitmap) ? createBitmap : bitmap;
        } catch (OutOfMemoryError e) {
            Log.e("BitmapLoadUtils", "transformBitmap: ", e);
            return bitmap;
        }
    }

    public static int calculateInSampleSize(@NonNull BitmapFactory.Options options, int i, int i2) {
        int i3 = options.outHeight;
        int i4 = options.outWidth;
        int i5 = 1;
        if (i3 > i2 || i4 > i) {
            while (true) {
                if (i3 / i5 <= i2 && i4 / i5 <= i) {
                    break;
                }
                i5 *= 2;
            }
        }
        return i5;
    }

    public static int getExifOrientation(@NonNull Context context, @NonNull Uri uri) {
        int i = 0;
        try {
            InputStream openInputStream = context.getContentResolver().openInputStream(uri);
            if (openInputStream == null) {
                return 0;
            }
            i = new ImageHeaderParser(openInputStream).getOrientation();
            close(openInputStream);
            return i;
        } catch (IOException e) {
            Log.e("BitmapLoadUtils", "getExifOrientation: " + uri.toString(), e);
            return i;
        }
    }

    public static int calculateMaxBitmapSize(@NonNull Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        Point point = new Point();
        if (windowManager != null) {
            windowManager.getDefaultDisplay().getSize(point);
        }
        int sqrt = (int) Math.sqrt(Math.pow(point.x, 2.0d) + Math.pow(point.y, 2.0d));
        Canvas canvas = new Canvas();
        int min = Math.min(canvas.getMaximumBitmapWidth(), canvas.getMaximumBitmapHeight());
        if (min > 0) {
            sqrt = Math.min(sqrt, min);
        }
        int maxTextureSize = EglUtils.getMaxTextureSize();
        if (maxTextureSize > 0) {
            sqrt = Math.min(sqrt, maxTextureSize);
        }
        Log.d("BitmapLoadUtils", "maxBitmapSize: " + sqrt);
        return sqrt;
    }

    public static void close(@Nullable Closeable closeable) {
        if (closeable == null || !(closeable instanceof Closeable)) {
            return;
        }
        try {
            closeable.close();
        } catch (IOException unused) {
        }
    }
}
