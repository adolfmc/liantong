package com.example.asus.detectionandalign.utils;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.util.Base64;
import android.util.Log;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.example.asus.detectionandalign.utils.c */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C4291c {
    /* renamed from: a */
    public static void m15954a(Bitmap bitmap) {
        if (bitmap == null || bitmap.isRecycled()) {
            return;
        }
        bitmap.recycle();
        System.gc();
    }

    @SuppressLint({"NewApi"})
    /* renamed from: b */
    public static String m15953b(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream;
        String str;
        ByteArrayOutputStream byteArrayOutputStream2 = null;
        String str2 = null;
        byteArrayOutputStream2 = null;
        if (bitmap != null) {
            try {
                try {
                    byteArrayOutputStream = new ByteArrayOutputStream();
                    try {
                        ByteArrayOutputStream byteArrayOutputStream3 = new ByteArrayOutputStream(bitmap.getByteCount());
                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream3);
                        if (byteArrayOutputStream3.toByteArray().length / 1024 > 100) {
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 80, byteArrayOutputStream);
                        } else {
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
                        }
                        Log.e("bitmapToBase64", "80% quality,picture length: " + String.valueOf(byteArrayOutputStream.toByteArray().length / 1024) + "KB");
                        byteArrayOutputStream.flush();
                        byteArrayOutputStream.close();
                        str2 = Base64.encodeToString(byteArrayOutputStream.toByteArray(), 2);
                        Log.e("bitmapToBase64", "80% quality,Base64 length: " + (str2.getBytes().length / 1024) + "KB");
                        try {
                            byteArrayOutputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } catch (IOException e2) {
                        e = e2;
                        str = str2;
                        byteArrayOutputStream2 = byteArrayOutputStream;
                        e.printStackTrace();
                        if (byteArrayOutputStream2 != null) {
                            try {
                                byteArrayOutputStream2.close();
                            } catch (IOException e3) {
                                e3.printStackTrace();
                            }
                        }
                        str2 = str;
                        System.gc();
                        return str2;
                    } catch (Throwable th) {
                        th = th;
                        if (byteArrayOutputStream != null) {
                            try {
                                byteArrayOutputStream.close();
                            } catch (IOException e4) {
                                e4.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    byteArrayOutputStream = byteArrayOutputStream2;
                }
            } catch (IOException e5) {
                e = e5;
                str = null;
            }
            System.gc();
            return str2;
        }
        return null;
    }
}
