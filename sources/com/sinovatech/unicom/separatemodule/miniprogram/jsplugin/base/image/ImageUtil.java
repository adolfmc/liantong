package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.image;

import android.annotation.SuppressLint;
import android.content.ContentUris;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Base64;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class ImageUtil {
    private static final String TAG = "ImageUtil";

    public static final String getFileProviderName(Context context) {
        return context.getPackageName() + ".fileprovider";
    }

    public static String getFilePathWithUri(Context context, Uri uri) {
        if (uri != null) {
            File fileWithUri = getFileWithUri(context, uri);
            return fileWithUri == null ? "" : fileWithUri.getAbsolutePath();
        }
        return "";
    }

    public static File getFileWithUri(Context context, Uri uri) {
        String realPathFromUri = getRealPathFromUri(context, uri);
        if (TextUtils.isEmpty(realPathFromUri)) {
            return null;
        }
        return new File(realPathFromUri);
    }

    public static String getRealPathFromUri(Context context, Uri uri) {
        if (Build.VERSION.SDK_INT >= 19) {
            return getRealPathFromUriAboveApi19(context, uri);
        }
        return getRealPathFromUriBelowAPI19(context, uri);
    }

    private static String getRealPathFromUriBelowAPI19(Context context, Uri uri) {
        return getDataColumn(context, uri, null, null);
    }

    @SuppressLint({"NewApi"})
    private static String getRealPathFromUriAboveApi19(Context context, Uri uri) {
        String str = null;
        try {
            if (DocumentsContract.isDocumentUri(context, uri)) {
                String documentId = DocumentsContract.getDocumentId(uri);
                if (isMediaDocument(uri)) {
                    str = getDataColumn(context, MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "_id=?", new String[]{documentId.split(":")[1]});
                } else if (isDownloadsDocument(uri)) {
                    str = getDataColumn(context, ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(documentId).longValue()), null, null);
                }
            } else if ("content".equalsIgnoreCase(uri.getScheme())) {
                str = getDataColumn(context, uri, null, null);
            } else if ("file".equals(uri.getScheme())) {
                str = uri.getPath();
            }
        } catch (Exception e) {
            MsLogUtil.m7977e(TAG, e.getMessage());
        }
        return str;
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0058, code lost:
        if (r10 != null) goto L8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x005a, code lost:
        r10.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0066, code lost:
        if (r10 == null) goto L9;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r10v1, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r10v2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.lang.String getDataColumn(android.content.Context r8, android.net.Uri r9, java.lang.String r10, java.lang.String[] r11) {
        /*
            r0 = 0
            java.lang.String r1 = "_data"
            java.lang.String[] r1 = new java.lang.String[]{r1}     // Catch: java.lang.Exception -> L70
            android.content.ContentResolver r2 = r8.getContentResolver()     // Catch: java.lang.Throwable -> L5e java.lang.Exception -> L61
            r7 = 0
            r3 = r9
            r4 = r1
            r5 = r10
            r6 = r11
            android.database.Cursor r10 = r2.query(r3, r4, r5, r6, r7)     // Catch: java.lang.Throwable -> L5e java.lang.Exception -> L61
            if (r10 == 0) goto L58
            boolean r11 = r10.moveToFirst()     // Catch: java.lang.Exception -> L56 java.lang.Throwable -> L69
            if (r11 == 0) goto L58
            r11 = 0
            r11 = r1[r11]     // Catch: java.lang.Exception -> L56 java.lang.Throwable -> L69
            int r11 = r10.getColumnIndex(r11)     // Catch: java.lang.Exception -> L56 java.lang.Throwable -> L69
            if (r11 < 0) goto L2b
            java.lang.String r8 = r10.getString(r11)     // Catch: java.lang.Exception -> L56 java.lang.Throwable -> L69
            r0 = r8
            goto L58
        L2b:
            java.lang.String r11 = r9.getAuthority()     // Catch: java.lang.Exception -> L56 java.lang.Throwable -> L69
            java.lang.String r8 = getFileProviderName(r8)     // Catch: java.lang.Exception -> L56 java.lang.Throwable -> L69
            boolean r8 = android.text.TextUtils.equals(r11, r8)     // Catch: java.lang.Exception -> L56 java.lang.Throwable -> L69
            if (r8 == 0) goto L50
            java.io.File r8 = new java.io.File     // Catch: java.lang.Exception -> L56 java.lang.Throwable -> L69
            java.lang.String r9 = r9.getPath()     // Catch: java.lang.Exception -> L56 java.lang.Throwable -> L69
            java.lang.String r11 = "external_files/"
            java.lang.String r1 = ""
            java.lang.String r9 = r9.replace(r11, r1)     // Catch: java.lang.Exception -> L56 java.lang.Throwable -> L69
            r8.<init>(r9)     // Catch: java.lang.Exception -> L56 java.lang.Throwable -> L69
            java.lang.String r8 = r8.getAbsolutePath()     // Catch: java.lang.Exception -> L56 java.lang.Throwable -> L69
            r0 = r8
            goto L58
        L50:
            java.lang.String r8 = r9.getPath()     // Catch: java.lang.Exception -> L56 java.lang.Throwable -> L69
            r0 = r8
            goto L58
        L56:
            r8 = move-exception
            goto L63
        L58:
            if (r10 == 0) goto L7a
        L5a:
            r10.close()     // Catch: java.lang.Exception -> L70
            goto L7a
        L5e:
            r8 = move-exception
            r10 = r0
            goto L6a
        L61:
            r8 = move-exception
            r10 = r0
        L63:
            r8.printStackTrace()     // Catch: java.lang.Throwable -> L69
            if (r10 == 0) goto L7a
            goto L5a
        L69:
            r8 = move-exception
        L6a:
            if (r10 == 0) goto L6f
            r10.close()     // Catch: java.lang.Exception -> L70
        L6f:
            throw r8     // Catch: java.lang.Exception -> L70
        L70:
            r8 = move-exception
            java.lang.String r9 = com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.image.ImageUtil.TAG
            java.lang.String r8 = r8.getMessage()
            com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil.m7977e(r9, r8)
        L7a:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.image.ImageUtil.getDataColumn(android.content.Context, android.net.Uri, java.lang.String, java.lang.String[]):java.lang.String");
    }

    private static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    private static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    public static String bitmapToBase64(Bitmap bitmap) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            return Base64.encodeToString(byteArrayOutputStream.toByteArray(), 2);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String fileToBase64(File file) {
        FileInputStream fileInputStream;
        String str = null;
        try {
            try {
                try {
                    fileInputStream = new FileInputStream(file);
                } catch (FileNotFoundException e) {
                    e = e;
                    fileInputStream = null;
                } catch (IOException e2) {
                    e = e2;
                    fileInputStream = null;
                } catch (Throwable th) {
                    th = th;
                    fileInputStream = null;
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                    }
                    throw th;
                }
                try {
                    byte[] bArr = new byte[fileInputStream.available()];
                    str = Base64.encodeToString(bArr, 0, fileInputStream.read(bArr), 0);
                    fileInputStream.close();
                } catch (FileNotFoundException e4) {
                    e = e4;
                    e.printStackTrace();
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    return str;
                } catch (IOException e5) {
                    e = e5;
                    e.printStackTrace();
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    return str;
                }
            } catch (IOException e6) {
                e6.printStackTrace();
            }
            return str;
        } catch (Throwable th2) {
            th = th2;
        }
    }
}
