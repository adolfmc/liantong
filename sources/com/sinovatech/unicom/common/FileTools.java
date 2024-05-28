package com.sinovatech.unicom.common;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.util.Base64;
import com.megvii.livenesslib.util.SDCardUtil;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class FileTools {
    public static boolean isExternalStorageWritable() {
        return SDCardUtil.isExternalStorageReadable();
    }

    public static String getLoginErrorLog() {
        return isExternalStorageWritable() ? SDCardUtil.getOwnFileUrl("UnicomLog") : "";
    }

    public static boolean fileIsExists(String str) {
        try {
            if (isExternalStorageWritable()) {
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

    public static void delteDirectory(File file) {
        if (file != null && file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles == null || listFiles.length == 0) {
                file.delete();
                return;
            }
            for (File file2 : listFiles) {
                deleteFile(file2);
            }
            file.delete();
        }
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

    public static String readInputStream(InputStream inputStream) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        if (inputStream != null) {
            while (true) {
                try {
                    int read = inputStream.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    byteArrayOutputStream.write(bArr, 0, read);
                } catch (Exception e) {
                    e.printStackTrace();
                    return "";
                }
            }
        }
        byteArrayOutputStream.close();
        inputStream.close();
        return new String(byteArrayOutputStream.toByteArray());
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

    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x003b, code lost:
        if (r8.startsWith("file://") == false) goto L22;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x004c, code lost:
        if (r7 != null) goto L8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0057, code lost:
        if (r7 == null) goto L6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0059, code lost:
        r7.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x005c, code lost:
        return null;
     */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0060  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String getDataColumn(android.content.Context r7, android.net.Uri r8, java.lang.String r9, java.lang.String[] r10) {
        /*
            java.lang.String r0 = "_data"
            java.lang.String[] r3 = new java.lang.String[]{r0}
            r0 = 0
            android.content.ContentResolver r1 = r7.getContentResolver()     // Catch: java.lang.Throwable -> L4f java.lang.Exception -> L52
            r6 = 0
            r2 = r8
            r4 = r9
            r5 = r10
            android.database.Cursor r7 = r1.query(r2, r3, r4, r5, r6)     // Catch: java.lang.Throwable -> L4f java.lang.Exception -> L52
            if (r7 == 0) goto L4c
            boolean r8 = r7.moveToFirst()     // Catch: java.lang.Exception -> L4a java.lang.Throwable -> L5d
            if (r8 == 0) goto L4c
            java.lang.String r8 = "_data"
            int r8 = r7.getColumnIndexOrThrow(r8)     // Catch: java.lang.Exception -> L4a java.lang.Throwable -> L5d
            java.lang.String r8 = r7.getString(r8)     // Catch: java.lang.Exception -> L4a java.lang.Throwable -> L5d
            java.lang.String r9 = "content://"
            boolean r9 = r8.startsWith(r9)     // Catch: java.lang.Exception -> L4a java.lang.Throwable -> L5d
            if (r9 != 0) goto L44
            java.lang.String r9 = "/"
            boolean r9 = r8.startsWith(r9)     // Catch: java.lang.Exception -> L4a java.lang.Throwable -> L5d
            if (r9 != 0) goto L3e
            java.lang.String r9 = "file://"
            boolean r9 = r8.startsWith(r9)     // Catch: java.lang.Exception -> L4a java.lang.Throwable -> L5d
            if (r9 != 0) goto L3e
            goto L44
        L3e:
            if (r7 == 0) goto L43
            r7.close()
        L43:
            return r8
        L44:
            if (r7 == 0) goto L49
            r7.close()
        L49:
            return r0
        L4a:
            r8 = move-exception
            goto L54
        L4c:
            if (r7 == 0) goto L5c
            goto L59
        L4f:
            r8 = move-exception
            r7 = r0
            goto L5e
        L52:
            r8 = move-exception
            r7 = r0
        L54:
            r8.printStackTrace()     // Catch: java.lang.Throwable -> L5d
            if (r7 == 0) goto L5c
        L59:
            r7.close()
        L5c:
            return r0
        L5d:
            r8 = move-exception
        L5e:
            if (r7 == 0) goto L63
            r7.close()
        L63:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.common.FileTools.getDataColumn(android.content.Context, android.net.Uri, java.lang.String, java.lang.String[]):java.lang.String");
    }
}
