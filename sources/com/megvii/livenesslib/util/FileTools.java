package com.megvii.livenesslib.util;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class FileTools {
    public static boolean isExternalStorageWritable() {
        return "mounted".equals(Environment.getExternalStorageState());
    }

    public static boolean isExternalStorageReadable() {
        String externalStorageState = Environment.getExternalStorageState();
        return "mounted".equals(externalStorageState) || "mounted_ro".equals(externalStorageState);
    }

    public static boolean fileIsExists(String str) {
        try {
            if (isExternalStorageReadable()) {
                return new File(str).exists();
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    public static void createPath(String str) {
        File file = new File(str.substring(0, str.lastIndexOf("/")));
        if (file.exists() && file.isDirectory()) {
            return;
        }
        file.mkdirs();
    }

    public static void deleteFile(File file) {
        if (file != null && file.isFile()) {
            file.delete();
        }
    }

    public static long getFileSize(File file) throws Exception {
        if (file.exists()) {
            return new FileInputStream(file).available();
        }
        file.createNewFile();
        Log.d("face++", "获取文件大小不存在!");
        return 0L;
    }

    public static long getlist(File file) {
        File[] listFiles = file.listFiles();
        long length = listFiles.length;
        for (int i = 0; i < listFiles.length; i++) {
            if (listFiles[i].isDirectory()) {
                length = (length + getlist(listFiles[i])) - 1;
            }
        }
        return length;
    }

    public static String getDataColumn(Context context, Uri uri, String str, String[] strArr) {
        Cursor cursor;
        try {
            cursor = context.getContentResolver().query(uri, new String[]{"_data"}, str, strArr, null);
            if (cursor != null) {
                try {
                    if (cursor.moveToFirst()) {
                        String string = cursor.getString(cursor.getColumnIndexOrThrow("_data"));
                        if (cursor != null) {
                            cursor.close();
                        }
                        return string;
                    }
                } catch (Throwable th) {
                    th = th;
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th;
                }
            }
            if (cursor != null) {
                cursor.close();
            }
            return null;
        } catch (Throwable th2) {
            th = th2;
            cursor = null;
        }
    }

    public static String readFile(String str) {
        File file = new File(str);
        if (file.exists()) {
            try {
                FileInputStream fileInputStream = new FileInputStream(file);
                int available = fileInputStream.available();
                byte[] bArr = new byte[available];
                if (available > 0) {
                    fileInputStream.read(bArr, 0, available);
                    return new String(bArr);
                }
                return "";
            } catch (IOException e) {
                e.printStackTrace();
                return "";
            }
        }
        return "";
    }

    public static String readFile(InputStream inputStream) {
        String str = "";
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                str = str + readLine;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    public static void writeFile(String str, byte[] bArr, boolean z) {
        try {
            if (!fileIsExists(str)) {
                createPath(str);
            }
            FileOutputStream fileOutputStream = new FileOutputStream(str, z);
            fileOutputStream.write(bArr);
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void saveAppid(String str, File file) {
        try {
            if (!file.exists()) {
                new File(file.getParent()).mkdirs();
                file.createNewFile();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(str.getBytes());
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String readAppid(File file) {
        try {
            if (!file.exists()) {
                return null;
            }
            FileInputStream fileInputStream = new FileInputStream(file);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[1024];
            while (true) {
                int read = fileInputStream.read(bArr);
                if (read != -1) {
                    byteArrayOutputStream.write(bArr, 0, read);
                } else {
                    String byteArrayOutputStream2 = byteArrayOutputStream.toString();
                    byteArrayOutputStream.close();
                    fileInputStream.close();
                    return byteArrayOutputStream2;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }

    public static Bitmap stringToBitmap(String str) {
        try {
            byte[] decode = Base64.decode(("data:image/png;base64," + str).split(",")[1], 0);
            return BitmapFactory.decodeByteArray(decode, 0, decode.length);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String[] bitmapToBase64(Bitmap bitmap, int i) {
        try {
            new BitmapFactory.Options().inSampleSize = 1;
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, i, byteArrayOutputStream);
            int length = byteArrayOutputStream.toByteArray().length;
            return new String[]{Base64.encodeToString(byteArrayOutputStream.toByteArray(), 2), length + ""};
        } catch (Exception e) {
            e.printStackTrace();
            return new String[]{"", ""};
        }
    }

    public static Bitmap adjustPhotoRotation(Bitmap bitmap, int i) {
        float height;
        float width;
        Matrix matrix = new Matrix();
        matrix.setRotate(i, bitmap.getWidth() / 2.0f, bitmap.getHeight() / 2.0f);
        if (i == 90) {
            height = bitmap.getHeight();
            width = 0.0f;
        } else {
            height = bitmap.getHeight();
            width = bitmap.getWidth();
        }
        float[] fArr = new float[9];
        matrix.getValues(fArr);
        matrix.postTranslate(height - fArr[2], width - fArr[5]);
        Bitmap createBitmap = Bitmap.createBitmap(bitmap.getHeight(), bitmap.getWidth(), Bitmap.Config.ARGB_8888);
        new Canvas(createBitmap).drawBitmap(bitmap, matrix, new Paint());
        return createBitmap;
    }

    public static String bitmapToBase642(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i = 100;
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        while (byteArrayOutputStream.toByteArray().length / 1024 > 200 && i > 10) {
            byteArrayOutputStream.reset();
            bitmap.compress(Bitmap.CompressFormat.JPEG, i, byteArrayOutputStream);
            if (i > 10) {
                i -= 10;
            }
        }
        String encodeToString = Base64.encodeToString(byteArrayOutputStream.toByteArray(), 2);
        System.gc();
        return encodeToString;
    }

    public static Bitmap base64ToBitmap(String str) {
        byte[] decode = Base64.decode(str, 0);
        return BitmapFactory.decodeByteArray(decode, 0, decode.length);
    }

    public static Bitmap rotaingImageView(Bitmap bitmap) {
        int i = bitmap.getHeight() > bitmap.getWidth() ? -90 : 0;
        Matrix matrix = new Matrix();
        matrix.postRotate(i);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    public static ByteArrayOutputStream qualityCompress(Bitmap bitmap, File file, int i) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, i, byteArrayOutputStream);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(byteArrayOutputStream.toByteArray());
            fileOutputStream.flush();
            fileOutputStream.close();
            return byteArrayOutputStream;
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("face", "压缩图片方法异常了~");
            return null;
        }
    }

    public static byte[] saveImage(String str, Bitmap bitmap) {
        try {
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(str));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bufferedOutputStream);
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
            ByteBuffer allocate = ByteBuffer.allocate(bitmap.getByteCount());
            bitmap.copyPixelsToBuffer(allocate);
            return allocate.array();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }
}
