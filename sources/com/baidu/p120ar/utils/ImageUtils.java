package com.baidu.p120ar.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.os.Environment;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.utils.ImageUtils */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ImageUtils {
    private static final String TAG = "ImageUtils";

    public static Bitmap rgba2Bitmap(int i, int i2, byte[] bArr) {
        if (bArr.length <= 0) {
            return null;
        }
        int[] iArr = new int[bArr.length / 4];
        for (int i3 = 0; i3 < iArr.length; i3++) {
            int i4 = i3 * 4;
            iArr[i3] = ((bArr[i4 + 3] & 255) << 24) | ((bArr[i4 + 0] & 255) << 16) | ((bArr[i4 + 1] & 255) << 8) | (bArr[i4 + 2] & 255);
        }
        return Bitmap.createBitmap(iArr, 0, i, i, i2, Bitmap.Config.ARGB_8888);
    }

    public static byte[] bitmap2Bytes(Bitmap bitmap, int i) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, i, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public static byte[] getRgbaFromBitmap(Bitmap bitmap) {
        ByteBuffer allocate = ByteBuffer.allocate(bitmap.getByteCount());
        bitmap.copyPixelsToBuffer(allocate);
        return allocate.array();
    }

    public static Bitmap saveYUV2Bitmap(byte[] bArr, int i, int i2) {
        YuvImage yuvImage = new YuvImage(bArr, 17, i, i2, null);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        yuvImage.compressToJpeg(new Rect(0, 0, i, i2), 100, byteArrayOutputStream);
        Bitmap decodeByteArray = BitmapFactory.decodeByteArray(byteArrayOutputStream.toByteArray(), 0, byteArrayOutputStream.size());
        try {
            byteArrayOutputStream.flush();
            byteArrayOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return decodeByteArray;
    }

    public static void saveBitmap(Bitmap bitmap, String str) {
        if (!Environment.getExternalStorageState().equals("mounted")) {
            ARLog.m20419e("ImageUtils", "saveBitmap failure : sdcard not mounted");
            return;
        }
        try {
            File file = new File(str);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
