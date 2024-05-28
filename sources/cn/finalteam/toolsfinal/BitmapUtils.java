package cn.finalteam.toolsfinal;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.util.Base64;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class BitmapUtils {
    public static byte[] bitmapToByte(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public static Bitmap byteToBitmap(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        return BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
    }

    public static String bitmapToString(Bitmap bitmap) {
        return Base64.encodeToString(bitmapToByte(bitmap), 0);
    }

    public static Bitmap drawableToBitmap(Drawable drawable) {
        if (drawable == null) {
            return null;
        }
        return ((BitmapDrawable) drawable).getBitmap();
    }

    public static Drawable bitmapToDrawable(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        return new BitmapDrawable(bitmap);
    }

    public static Bitmap scaleImageTo(Bitmap bitmap, int i, int i2) {
        return scaleImage(bitmap, i / bitmap.getWidth(), i2 / bitmap.getHeight());
    }

    public static Bitmap scaleImage(Bitmap bitmap, float f, float f2) {
        if (bitmap == null) {
            return null;
        }
        Matrix matrix = new Matrix();
        matrix.postScale(f, f2);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    public static Bitmap toRoundCorner(Bitmap bitmap) {
        int height = bitmap.getHeight();
        int height2 = bitmap.getHeight();
        Bitmap createBitmap = Bitmap.createBitmap(height2, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        Rect rect = new Rect(0, 0, height2, height);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(0);
        float f = height2 / 2;
        canvas.drawCircle(f, height / 2, f, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return createBitmap;
    }

    public static Bitmap createBitmapThumbnail(Bitmap bitmap, boolean z, int i, int i2) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.postScale(i2 / width, i / height);
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
        if (z) {
            bitmap.recycle();
        }
        return createBitmap;
    }

    public static void saveBitmap(Bitmap bitmap, File file) {
        if (file.exists()) {
            file.delete();
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void saveBitmap(Bitmap bitmap, int i, File file) {
        if (file.exists()) {
            file.delete();
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, i, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Bitmap compressBitmap(Bitmap bitmap, boolean z, long j) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i = 100;
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        while (byteArrayOutputStream.toByteArray().length > j) {
            byteArrayOutputStream.reset();
            bitmap.compress(Bitmap.CompressFormat.JPEG, i, byteArrayOutputStream);
            i -= 10;
        }
        Bitmap decodeStream = BitmapFactory.decodeStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()), null, null);
        if (z) {
            bitmap.recycle();
        }
        return decodeStream;
    }

    public static Bitmap compressBitmap(Bitmap bitmap, boolean z, int i, int i2) {
        Matrix matrix = new Matrix();
        matrix.postScale(i / bitmap.getWidth(), i2 / bitmap.getHeight());
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        if (z) {
            bitmap.recycle();
        }
        return createBitmap;
    }

    public static Bitmap compressBitmap(String str, boolean z, long j, int i, int i2) {
        return compress(str, null, false, z, j, i, i2);
    }

    private static Bitmap compress(String str, String str2, boolean z, boolean z2, long j, int i, int i2) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        int i3 = options.outWidth;
        int i4 = options.outHeight;
        options.inJustDecodeBounds = false;
        int i5 = 1;
        while (i3 / i5 > i) {
            i5++;
        }
        while (i4 / i5 > i2) {
            i5++;
        }
        if (i5 <= 0) {
            i5 = 1;
        }
        options.inSampleSize = i5;
        Bitmap compressBitmap = compressBitmap(BitmapFactory.decodeFile(str, options), false, i, i2);
        if (z2) {
            compressBitmap = compressBitmap(compressBitmap, true, j);
        }
        if (z) {
            if (!StringUtils.isEmpty(str2)) {
                str = str2;
            }
            saveBitmap(compressBitmap, new File(str));
        }
        return compressBitmap;
    }

    public static void compressImage(String str, String str2, boolean z, long j, int i, int i2) {
        compress(str, str2, true, z, j, i, i2).recycle();
    }

    public static void compressImage(String str, boolean z, long j, int i, int i2) {
        compressImage(str, null, z, j, i, i2);
    }

    public static void compressImage(String str, int i, int i2) {
        compressImage(str, null, false, 0L, i, i2);
    }

    public static Bitmap compressBitmap(String str, int i, int i2) {
        return compressBitmap(str, false, 0L, i, i2);
    }

    public static void compressImageSmall(String str, int i) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        compressImage(str, options.outWidth / i, options.outHeight / i);
    }

    public static Bitmap compressBitmapSmall(String str, int i) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        return compressBitmap(str, options.outWidth / i, options.outHeight / i);
    }

    public static void compressImageBig(String str, int i) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        compressImage(str, options.outWidth * i, options.outHeight * i);
    }

    public static Bitmap compressBitmapBig(String str, int i) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        return compressBitmap(str, options.outWidth * i, options.outHeight * i);
    }

    public static void compressImage(String str, String str2, boolean z, long j) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        compressImage(str, str2, z, j, options.outWidth / 2, options.outHeight / 2);
    }

    public static void compressImage(String str, boolean z, long j) {
        compressImage(str, null, z, j);
    }

    public static Bitmap compressBitmap(String str, boolean z, long j) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(str, options);
        return compressBitmap(str, z, j, options.outWidth / 2, options.outHeight / 2);
    }

    public static void compressImage(String str, long j) {
        compressImage(str, true, j);
    }

    public static Bitmap compressBimap(String str, long j) {
        return compressBitmap(str, true, j);
    }

    public static void compressImage(String str) {
        compressImage(str, true, 1048576L);
    }

    public static Bitmap compressBitmap(String str) {
        return compressBitmap(str, true, 1048576L);
    }

    public static Bitmap rotateBitmap(Bitmap bitmap, int i, boolean z) {
        Matrix matrix = new Matrix();
        matrix.postRotate(i);
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        if (z) {
            bitmap.recycle();
        }
        return createBitmap;
    }

    public static final int getDegress(String str) {
        try {
            int attributeInt = new ExifInterface(str).getAttributeInt("Orientation", 1);
            if (attributeInt != 3) {
                if (attributeInt != 6) {
                    if (attributeInt != 8) {
                        return 0;
                    }
                    return SubsamplingScaleImageView.ORIENTATION_270;
                }
                return 90;
            }
            return 180;
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
