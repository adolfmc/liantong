package com.megvii.livenesslib.util;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.net.Uri;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class PhotoUtils {
    private static final String TAG = "face++ PhotoUtils";
    private static float scaleHeight = 1.0f;
    private static float scaleWidth = 1.0f;

    public static byte[] panDuanBtm(Context context, byte[] bArr, String str, boolean z) {
        Bitmap imageScale;
        try {
            Bitmap Bytes2Bimap = Bytes2Bimap(bArr);
            if (z) {
                imageScale = imageScale(Bytes2Bimap, 700, 700);
            } else {
                imageScale = imageScale(Bytes2Bimap, 600, 800);
            }
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            imageScale.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            return compressImage(BitmapFactory.decodeStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()), null, new BitmapFactory.Options()));
        } catch (Exception unused) {
            return bArr;
        }
    }

    public static byte[] compressImage(Bitmap bitmap) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            int i = 90;
            while (byteArrayOutputStream.toByteArray().length / 1024 > 150 && i >= 10) {
                Log.d(TAG, "图片大于300kb,正在缩小。。。。" + (byteArrayOutputStream.toByteArray().length / 1024));
                byteArrayOutputStream.reset();
                bitmap.compress(Bitmap.CompressFormat.JPEG, i, byteArrayOutputStream);
                i += -10;
                Log.d(TAG, "图片大于300kb,缩小结果。。。。" + (byteArrayOutputStream.toByteArray().length / 1024));
            }
            double d = 1.1d;
            while (byteArrayOutputStream.toByteArray().length / 1024 < 50) {
                Log.d(TAG, "图片小于50kb,正在放大。。。。" + (byteArrayOutputStream.toByteArray().length / 1024));
                byteArrayOutputStream.reset();
                int width = bitmap.getWidth();
                int height = bitmap.getHeight();
                Log.d(TAG, "bmpWidth = " + width + ", bmpHeight = " + height);
                scaleWidth = (float) (((double) scaleWidth) * d);
                scaleHeight = (float) (((double) scaleHeight) * d);
                Matrix matrix = new Matrix();
                matrix.postScale(scaleWidth, scaleHeight);
                bitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
                scaleWidth = 1.0f;
                scaleHeight = 1.0f;
                d += 0.1d;
            }
            return byteArrayOutputStream.toByteArray();
        } catch (Exception unused) {
            return BitmapToBytes(bitmap);
        }
    }

    public static boolean saveImageToGallery(Context context, byte[] bArr, String str) {
        Bitmap Bytes2Bimap = Bytes2Bimap(bArr);
        if (!FileTools.fileIsExists(str)) {
            FileTools.createPath(str);
        }
        File file = new File(str);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            boolean compress = Bytes2Bimap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            context.sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", Uri.fromFile(file)));
            return compress;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Bitmap Bytes2Bimap(byte[] bArr) {
        if (bArr.length != 0) {
            return BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
        }
        return null;
    }

    private static byte[] BitmapToBytes(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    private static Bitmap BytesToBitmap(byte[] bArr) {
        return BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
    }

    public static void deleteDir(String str) {
        deleteDirWihtFile(new File(str));
    }

    public static void deleteDirWihtFile(File file) {
        File[] listFiles;
        if (file != null && file.exists() && file.isDirectory()) {
            for (File file2 : file.listFiles()) {
                if (file2.isFile()) {
                    file2.delete();
                } else if (file2.isDirectory()) {
                    deleteDirWihtFile(file2);
                }
            }
            file.delete();
        }
    }

    public static String getPath(String str) {
        return SDCardUtil.getOwnFileUrl("AVMImage") + str + ".jpg";
    }

    public static Bitmap imageScale(Bitmap bitmap, int i, int i2) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float f = i / width;
        float f2 = i2 / height;
        Log.d(TAG, "src_w:" + width + ",src_h:" + height + ",new_w:" + i + ",new_h:" + i2 + ",scale_w:" + f + ",scale_h:" + f2);
        Matrix matrix = new Matrix();
        matrix.postScale(f, f2);
        return Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
    }

    public static Bitmap compoundBitmap(Bitmap bitmap, Bitmap bitmap2) {
        Bitmap copy = bitmap.copy(Bitmap.Config.ARGB_8888, true);
        int width = copy.getWidth();
        int height = copy.getHeight();
        if (width == bitmap2.getWidth() && height == bitmap2.getHeight()) {
            for (int i = 0; i < height; i++) {
                for (int i2 = 0; i2 < width; i2++) {
                    if (bitmap2.getPixel(i2, i) != -16777216) {
                        copy.setPixel(i2, i, bitmap2.getPixel(i2, i));
                    }
                }
            }
        }
        return copy;
    }

    private Bitmap loadBitmapFromView(View view) {
        int width = view.getWidth();
        int height = view.getHeight();
        Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawColor(-1);
        view.layout(0, 0, width, height);
        view.draw(canvas);
        return createBitmap;
    }

    public static Bitmap mergeBitmap(Bitmap bitmap, Bitmap bitmap2) {
        if (bitmap == null || bitmap.isRecycled() || bitmap2 == null || bitmap2.isRecycled()) {
            Log.d(TAG, "backBitmap=" + bitmap + ";frontBitmap=" + bitmap2);
            return null;
        }
        Bitmap copy = bitmap.copy(Bitmap.Config.ARGB_8888, true);
        new Canvas(copy).drawBitmap(bitmap2, new Rect(0, 0, bitmap2.getWidth(), bitmap2.getHeight()), new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight()), (Paint) null);
        return copy;
    }

    public static Bitmap mergeBitmap_LR(Bitmap bitmap, Bitmap bitmap2, boolean z) {
        int height;
        if (bitmap == null || bitmap.isRecycled() || bitmap2 == null || bitmap2.isRecycled()) {
            Log.d(TAG, "leftBitmap=" + bitmap + ";rightBitmap=" + bitmap2);
            return null;
        }
        if (z) {
            height = bitmap.getHeight() > bitmap2.getHeight() ? bitmap.getHeight() : bitmap2.getHeight();
        } else {
            height = bitmap.getHeight() < bitmap2.getHeight() ? bitmap.getHeight() : bitmap2.getHeight();
        }
        if (bitmap.getHeight() != height) {
            bitmap = Bitmap.createScaledBitmap(bitmap, (int) (((bitmap.getWidth() * 1.0f) / bitmap.getHeight()) * height), height, false);
        } else if (bitmap2.getHeight() != height) {
            bitmap2 = Bitmap.createScaledBitmap(bitmap2, (int) (((bitmap2.getWidth() * 1.0f) / bitmap2.getHeight()) * height), height, false);
        }
        int width = bitmap.getWidth() + bitmap2.getWidth();
        Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        Rect rect2 = new Rect(0, 0, bitmap2.getWidth(), bitmap2.getHeight());
        Rect rect3 = new Rect(bitmap.getWidth(), 0, width, height);
        canvas.drawBitmap(bitmap, rect, rect, (Paint) null);
        canvas.drawBitmap(bitmap2, rect2, rect3, (Paint) null);
        return createBitmap;
    }

    public static Bitmap mergeBitmap_TB(Bitmap bitmap, Bitmap bitmap2, boolean z) {
        int width;
        if (bitmap == null || bitmap.isRecycled() || bitmap2 == null || bitmap2.isRecycled()) {
            Log.d(TAG, "topBitmap=" + bitmap + ";bottomBitmap=" + bitmap2);
            return null;
        }
        if (z) {
            width = bitmap.getWidth() > bitmap2.getWidth() ? bitmap.getWidth() : bitmap2.getWidth();
        } else {
            width = bitmap.getWidth() < bitmap2.getWidth() ? bitmap.getWidth() : bitmap2.getWidth();
        }
        if (bitmap.getWidth() != width) {
            bitmap = Bitmap.createScaledBitmap(bitmap, width, (int) (((bitmap.getHeight() * 1.0f) / bitmap.getWidth()) * width), false);
        } else if (bitmap2.getWidth() != width) {
            bitmap2 = Bitmap.createScaledBitmap(bitmap2, width, (int) (((bitmap2.getHeight() * 1.0f) / bitmap2.getWidth()) * width), false);
        }
        int height = bitmap.getHeight() + bitmap2.getHeight();
        Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        Rect rect2 = new Rect(0, 0, bitmap2.getWidth(), bitmap2.getHeight());
        Rect rect3 = new Rect(0, bitmap.getHeight(), width, height);
        canvas.drawBitmap(bitmap, rect, rect, (Paint) null);
        canvas.drawBitmap(bitmap2, rect2, rect3, (Paint) null);
        return createBitmap;
    }

    public static Bitmap mergeBitmaps(Bitmap bitmap, Bitmap bitmap2) {
        if (bitmap != null) {
            try {
                if (!bitmap.isRecycled() && bitmap2 != null && !bitmap2.isRecycled()) {
                    int width = bitmap2.getWidth();
                    int height = bitmap2.getHeight();
                    Bitmap imageScale = imageScale(bitmap, 500, 500);
                    int width2 = imageScale.getWidth();
                    imageScale.getHeight();
                    Bitmap createBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
                    Canvas canvas = new Canvas(createBitmap);
                    canvas.drawBitmap(bitmap2, 0.0f, 0.0f, (Paint) null);
                    canvas.drawBitmap(imageScale, (width - width2) / 2, ((height / 4) * 3) - 500, (Paint) null);
                    return createBitmap;
                }
            } catch (Exception unused) {
                return imageScale(bitmap, 600, 800);
            }
        }
        Log.d(TAG, "topBitmap=" + bitmap + ";bottomBitmap=" + bitmap2);
        return null;
    }

    public static void getSize(byte[] bArr) {
        Bitmap Bytes2Bimap = Bytes2Bimap(bArr);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Bytes2Bimap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        Log.d(TAG, "image_env-----后得到的数据---长度为--" + (byteArrayOutputStream.toByteArray().length / 1024));
    }

    public static String zhuanHuanString(byte[] bArr) {
        return (bArr == null || bArr.length <= 0) ? "" : Base64.encodeToString(bArr, 0).replaceAll("[\\s*\t\n\r]", "");
    }
}
