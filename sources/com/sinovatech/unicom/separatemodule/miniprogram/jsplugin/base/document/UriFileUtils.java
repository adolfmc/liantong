package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.document;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Method;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class UriFileUtils {
    private static int sBufferSize = 524288;

    public static File uri2File(Context context, Uri uri) {
        if (uri == null) {
            return null;
        }
        try {
            File uri2FileReal = uri2FileReal(context, uri);
            return uri2FileReal != null ? uri2FileReal : copyUri2Cache(context, uri);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static File uri2FileNoCacheCopy(Context context, Uri uri) {
        if (uri == null) {
            return null;
        }
        try {
            return uri2FileReal(context, uri);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static File uri2FileReal(Context context, Uri uri) {
        Uri uri2;
        File fileFromUri;
        String[] split;
        boolean z;
        String str;
        String[] strArr;
        File file;
        MsLogUtil.m7979d("UriUtils", uri.toString());
        String authority = uri.getAuthority();
        String scheme = uri.getScheme();
        String path = uri.getPath();
        if (Build.VERSION.SDK_INT >= 24 && path != null) {
            for (String str2 : new String[]{"/external/", "/external_path/"}) {
                if (path.startsWith(str2)) {
                    File file2 = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + path.replace(str2, "/"));
                    if (file2.exists()) {
                        MsLogUtil.m7979d("UriUtils", uri.toString() + " -> " + str2);
                        return file2;
                    }
                }
            }
            if (path.startsWith("/files_path/")) {
                file = new File(context.getApplicationContext().getFilesDir().getAbsolutePath() + path.replace("/files_path/", "/"));
            } else if (path.startsWith("/cache_path/")) {
                file = new File(context.getApplicationContext().getCacheDir().getAbsolutePath() + path.replace("/cache_path/", "/"));
            } else if (path.startsWith("/external_files_path/")) {
                file = new File(context.getApplicationContext().getExternalFilesDir(null).getAbsolutePath() + path.replace("/external_files_path/", "/"));
            } else if (path.startsWith("/external_cache_path/")) {
                file = new File(context.getApplicationContext().getExternalCacheDir().getAbsolutePath() + path.replace("/external_cache_path/", "/"));
            } else {
                file = null;
            }
            if (file != null && file.exists()) {
                MsLogUtil.m7979d("UriUtils", uri.toString() + " -> " + path);
                return file;
            }
        }
        if ("file".equals(scheme)) {
            if (path != null) {
                return new File(path);
            }
            MsLogUtil.m7979d("UriUtils", uri.toString() + " parse failed. -> 0");
            return null;
        } else if (Build.VERSION.SDK_INT >= 19 && DocumentsContract.isDocumentUri(context.getApplicationContext(), uri)) {
            if ("com.android.externalstorage.documents".equals(authority)) {
                String str3 = DocumentsContract.getDocumentId(uri).split(":")[0];
                if ("primary".equalsIgnoreCase(str3)) {
                    return new File(Environment.getExternalStorageDirectory() + "/" + split[1]);
                }
                StorageManager storageManager = (StorageManager) context.getApplicationContext().getSystemService("storage");
                try {
                    Class<?> cls = Class.forName("android.os.storage.StorageVolume");
                    Method method = storageManager.getClass().getMethod("getVolumeList", new Class[0]);
                    Method method2 = cls.getMethod("getUuid", new Class[0]);
                    Method method3 = cls.getMethod("getState", new Class[0]);
                    Method method4 = cls.getMethod("getPath", new Class[0]);
                    Method method5 = cls.getMethod("isPrimary", new Class[0]);
                    Method method6 = cls.getMethod("isEmulated", new Class[0]);
                    Object invoke = method.invoke(storageManager, new Object[0]);
                    int length = Array.getLength(invoke);
                    for (int i = 0; i < length; i++) {
                        Object obj = Array.get(invoke, i);
                        if (!"mounted".equals(method3.invoke(obj, new Object[0])) && !"mounted_ro".equals(method3.invoke(obj, new Object[0]))) {
                            z = false;
                            if (z && ((!((Boolean) method5.invoke(obj, new Object[0])).booleanValue() || !((Boolean) method6.invoke(obj, new Object[0])).booleanValue()) && (str = (String) method2.invoke(obj, new Object[0])) != null && str.equals(str3))) {
                                return new File(method4.invoke(obj, new Object[0]) + "/" + split[1]);
                            }
                        }
                        z = true;
                        if (z) {
                            return new File(method4.invoke(obj, new Object[0]) + "/" + split[1]);
                        }
                    }
                } catch (Exception e) {
                    MsLogUtil.m7979d("UriUtils", uri.toString() + " parse failed. " + e.toString() + " -> 1_0");
                }
                MsLogUtil.m7979d("UriUtils", uri.toString() + " parse failed. -> 1_0");
                return null;
            } else if ("com.android.providers.downloads.documents".equals(authority)) {
                String documentId = DocumentsContract.getDocumentId(uri);
                if (TextUtils.isEmpty(documentId)) {
                    MsLogUtil.m7979d("UriUtils", uri.toString() + " parse failed(id is null). -> 1_1");
                    return null;
                } else if (documentId.startsWith("raw:")) {
                    return new File(documentId.substring(4));
                } else {
                    if (documentId.startsWith("msf:")) {
                        documentId = documentId.split(":")[1];
                    }
                    try {
                        long parseLong = Long.parseLong(documentId);
                        for (String str4 : new String[]{"content://downloads/public_downloads", "content://downloads/all_downloads", "content://downloads/my_downloads"}) {
                            try {
                                fileFromUri = getFileFromUri(context, ContentUris.withAppendedId(Uri.parse(str4), parseLong), "1_1");
                            } catch (Exception unused) {
                            }
                            if (fileFromUri != null) {
                                return fileFromUri;
                            }
                        }
                        MsLogUtil.m7979d("UriUtils", uri.toString() + " parse failed. -> 1_1");
                        return null;
                    } catch (Exception unused2) {
                        return null;
                    }
                }
            } else if ("com.android.providers.media.documents".equals(authority)) {
                String[] split2 = DocumentsContract.getDocumentId(uri).split(":");
                String str5 = split2[0];
                if ("image".equals(str5)) {
                    uri2 = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(str5)) {
                    uri2 = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if (!"audio".equals(str5)) {
                    MsLogUtil.m7979d("UriUtils", uri.toString() + " parse failed. -> 1_2");
                    return null;
                } else {
                    uri2 = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }
                return getFileFromUri(context, uri2, "_id=?", new String[]{split2[1]}, "1_2");
            } else if ("content".equals(scheme)) {
                return getFileFromUri(context, uri, "1_3");
            } else {
                MsLogUtil.m7979d("UriUtils", uri.toString() + " parse failed. -> 1_4");
                return null;
            }
        } else if ("content".equals(scheme)) {
            return getFileFromUri(context, uri, "2");
        } else {
            MsLogUtil.m7979d("UriUtils", uri.toString() + " parse failed. -> 3");
            return null;
        }
    }

    private static File getFileFromUri(Context context, Uri uri, String str) {
        return getFileFromUri(context, uri, null, null, str);
    }

    private static File getFileFromUri(Context context, Uri uri, String str, String[] strArr, String str2) {
        if ("com.google.android.apps.photos.content".equals(uri.getAuthority())) {
            if (!TextUtils.isEmpty(uri.getLastPathSegment())) {
                return new File(uri.getLastPathSegment());
            }
        } else if ("com.tencent.mtt.fileprovider".equals(uri.getAuthority())) {
            String path = uri.getPath();
            if (!TextUtils.isEmpty(path)) {
                return new File(Environment.getExternalStorageDirectory(), path.substring(10, path.length()));
            }
        } else if ("com.huawei.hidisk.fileprovider".equals(uri.getAuthority())) {
            String path2 = uri.getPath();
            if (!TextUtils.isEmpty(path2)) {
                return new File(path2.replace("/root", ""));
            }
        }
        Cursor query = context.getApplicationContext().getContentResolver().query(uri, new String[]{"_data"}, str, strArr, null);
        try {
            if (query == null) {
                MsLogUtil.m7979d("UriUtils", uri.toString() + " parse failed(cursor is null). -> " + str2);
                return null;
            } else if (!query.moveToFirst()) {
                MsLogUtil.m7979d("UriUtils", uri.toString() + " parse failed(moveToFirst return false). -> " + str2);
                return null;
            } else {
                int columnIndex = query.getColumnIndex("_data");
                if (columnIndex > -1) {
                    return new File(query.getString(columnIndex));
                }
                MsLogUtil.m7979d("UriUtils", uri.toString() + " parse failed(columnIndex: " + columnIndex + " is wrong). -> " + str2);
                return null;
            }
        } catch (Exception unused) {
            MsLogUtil.m7979d("UriUtils", uri.toString() + " parse failed. -> " + str2);
            return null;
        } finally {
            query.close();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x006f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.io.File copyUri2Cache(android.content.Context r5, android.net.Uri r6) {
        /*
            java.lang.String r0 = "UriUtils"
            java.lang.String r1 = "copyUri2Cache() called"
            com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil.m7979d(r0, r1)
            r0 = 0
            android.content.ContentResolver r1 = r5.getContentResolver()     // Catch: java.lang.Throwable -> L59 java.io.FileNotFoundException -> L5c
            java.io.InputStream r1 = r1.openInputStream(r6)     // Catch: java.lang.Throwable -> L59 java.io.FileNotFoundException -> L5c
            java.lang.String r5 = getRealFileName(r5, r6)     // Catch: java.io.FileNotFoundException -> L57 java.lang.Throwable -> L6c
            java.io.File r6 = new java.io.File     // Catch: java.io.FileNotFoundException -> L57 java.lang.Throwable -> L6c
            java.lang.String r2 = "uritofilecache"
            java.lang.String r2 = com.megvii.livenesslib.util.SDCardUtil.getOwnFileUrl(r2)     // Catch: java.io.FileNotFoundException -> L57 java.lang.Throwable -> L6c
            r6.<init>(r2)     // Catch: java.io.FileNotFoundException -> L57 java.lang.Throwable -> L6c
            boolean r2 = r6.exists()     // Catch: java.io.FileNotFoundException -> L57 java.lang.Throwable -> L6c
            if (r2 != 0) goto L29
            r6.mkdirs()     // Catch: java.io.FileNotFoundException -> L57 java.lang.Throwable -> L6c
        L29:
            java.io.File r2 = new java.io.File     // Catch: java.io.FileNotFoundException -> L57 java.lang.Throwable -> L6c
            boolean r3 = android.text.TextUtils.isEmpty(r5)     // Catch: java.io.FileNotFoundException -> L57 java.lang.Throwable -> L6c
            if (r3 == 0) goto L46
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.io.FileNotFoundException -> L57 java.lang.Throwable -> L6c
            r5.<init>()     // Catch: java.io.FileNotFoundException -> L57 java.lang.Throwable -> L6c
            java.lang.String r3 = ""
            r5.append(r3)     // Catch: java.io.FileNotFoundException -> L57 java.lang.Throwable -> L6c
            long r3 = java.lang.System.currentTimeMillis()     // Catch: java.io.FileNotFoundException -> L57 java.lang.Throwable -> L6c
            r5.append(r3)     // Catch: java.io.FileNotFoundException -> L57 java.lang.Throwable -> L6c
            java.lang.String r5 = r5.toString()     // Catch: java.io.FileNotFoundException -> L57 java.lang.Throwable -> L6c
        L46:
            r2.<init>(r6, r5)     // Catch: java.io.FileNotFoundException -> L57 java.lang.Throwable -> L6c
            writeFileFromIS(r2, r1)     // Catch: java.io.FileNotFoundException -> L57 java.lang.Throwable -> L6c
            if (r1 == 0) goto L56
            r1.close()     // Catch: java.io.IOException -> L52
            goto L56
        L52:
            r5 = move-exception
            r5.printStackTrace()
        L56:
            return r2
        L57:
            r5 = move-exception
            goto L5e
        L59:
            r5 = move-exception
            r1 = r0
            goto L6d
        L5c:
            r5 = move-exception
            r1 = r0
        L5e:
            r5.printStackTrace()     // Catch: java.lang.Throwable -> L6c
            if (r1 == 0) goto L6b
            r1.close()     // Catch: java.io.IOException -> L67
            goto L6b
        L67:
            r5 = move-exception
            r5.printStackTrace()
        L6b:
            return r0
        L6c:
            r5 = move-exception
        L6d:
            if (r1 == 0) goto L77
            r1.close()     // Catch: java.io.IOException -> L73
            goto L77
        L73:
            r6 = move-exception
            r6.printStackTrace()
        L77:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.document.UriFileUtils.copyUri2Cache(android.content.Context, android.net.Uri):java.io.File");
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0024, code lost:
        if (r7 != null) goto L6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0026, code lost:
        r7.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0033, code lost:
        if (r7 == null) goto L7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0036, code lost:
        return r0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.lang.String getRealFileName(android.content.Context r7, android.net.Uri r8) {
        /*
            java.lang.String r0 = "_display_name"
            java.lang.String[] r3 = new java.lang.String[]{r0}
            r0 = 0
            android.content.ContentResolver r1 = r7.getContentResolver()     // Catch: java.lang.Throwable -> L2a java.lang.Exception -> L32
            r4 = 0
            r5 = 0
            r6 = 0
            r2 = r8
            android.database.Cursor r7 = r1.query(r2, r3, r4, r5, r6)     // Catch: java.lang.Throwable -> L2a java.lang.Exception -> L32
            if (r7 == 0) goto L24
            boolean r8 = r7.moveToNext()     // Catch: java.lang.Throwable -> L22 java.lang.Exception -> L33
            if (r8 == 0) goto L24
            r8 = 0
            java.lang.String r8 = r7.getString(r8)     // Catch: java.lang.Throwable -> L22 java.lang.Exception -> L33
            r0 = r8
            goto L24
        L22:
            r8 = move-exception
            goto L2c
        L24:
            if (r7 == 0) goto L36
        L26:
            r7.close()
            goto L36
        L2a:
            r8 = move-exception
            r7 = r0
        L2c:
            if (r7 == 0) goto L31
            r7.close()
        L31:
            throw r8
        L32:
            r7 = r0
        L33:
            if (r7 == 0) goto L36
            goto L26
        L36:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.document.UriFileUtils.getRealFileName(android.content.Context, android.net.Uri):java.lang.String");
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:34:0x0050 -> B:52:0x0053). Please submit an issue!!! */
    private static void writeFileFromIS(File file, InputStream inputStream) {
        BufferedOutputStream bufferedOutputStream = null;
        try {
            try {
                try {
                    if (file.exists()) {
                        file.delete();
                    }
                    file.createNewFile();
                    BufferedOutputStream bufferedOutputStream2 = new BufferedOutputStream(new FileOutputStream(file), sBufferSize);
                    try {
                        byte[] bArr = new byte[sBufferSize];
                        while (true) {
                            int read = inputStream.read(bArr);
                            if (read != -1) {
                                bufferedOutputStream2.write(bArr, 0, read);
                            } else {
                                try {
                                    break;
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        inputStream.close();
                        bufferedOutputStream2.close();
                    } catch (IOException e2) {
                        e = e2;
                        bufferedOutputStream = bufferedOutputStream2;
                        e.printStackTrace();
                        try {
                            inputStream.close();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                        if (bufferedOutputStream != null) {
                            bufferedOutputStream.close();
                        }
                    } catch (Throwable th) {
                        th = th;
                        bufferedOutputStream = bufferedOutputStream2;
                        try {
                            inputStream.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                        if (bufferedOutputStream != null) {
                            try {
                                bufferedOutputStream.close();
                            } catch (IOException e5) {
                                e5.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (IOException e6) {
                    e = e6;
                }
            } catch (IOException e7) {
                e7.printStackTrace();
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }
}
