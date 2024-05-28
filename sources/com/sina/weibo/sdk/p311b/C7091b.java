package com.sina.weibo.sdk.p311b;

import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import java.io.File;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.sina.weibo.sdk.b.b */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class C7091b {
    /* renamed from: e */
    public static long m8073e(String str) {
        if (!TextUtils.isEmpty(str) && new File(str).exists()) {
            MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
            mediaMetadataRetriever.setDataSource(str);
            return Long.parseLong(mediaMetadataRetriever.extractMetadata(9));
        }
        return 0L;
    }

    /* renamed from: b */
    public static String m8078b(File file) {
        try {
            String absolutePath = file.getAbsolutePath();
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(absolutePath, options);
            String str = options.outMimeType;
            if (!str.contains("jpg") && !str.contains("gif") && !str.contains("png")) {
                if (!str.contains("jpeg")) {
                    return null;
                }
            }
            return "image/*";
        } catch (Exception unused) {
            return "*/*";
        }
    }

    @TargetApi(19)
    /* renamed from: a */
    public static String m8082a(Context context, Uri uri) {
        Uri uri2 = null;
        if ((Build.VERSION.SDK_INT >= 19) && DocumentsContract.isDocumentUri(context, uri)) {
            if (m8079b(uri)) {
                String[] split = DocumentsContract.getDocumentId(uri).split(":");
                if ("primary".equalsIgnoreCase(split[0])) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }
            } else if (m8077c(uri)) {
                return m8081a(context, ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(DocumentsContract.getDocumentId(uri)).longValue()), null, null);
            } else {
                if (m8075d(uri)) {
                    String[] split2 = DocumentsContract.getDocumentId(uri).split(":");
                    String str = split2[0];
                    if ("image".equals(str)) {
                        uri2 = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                    } else if ("video".equals(str)) {
                        uri2 = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                    } else if ("audio".equals(str)) {
                        uri2 = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                    }
                    return m8081a(context, uri2, "_id=?", new String[]{split2[1]});
                }
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            if (m8074e(uri)) {
                return uri.getLastPathSegment();
            }
            return m8080b(context, uri);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }
        return null;
    }

    /* renamed from: b */
    private static boolean m8079b(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /* renamed from: c */
    private static boolean m8077c(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /* renamed from: d */
    private static boolean m8075d(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    /* renamed from: e */
    private static boolean m8074e(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x002b, code lost:
        if (r0 != null) goto L16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0035, code lost:
        if (r0 != null) goto L16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0037, code lost:
        r0.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x003e, code lost:
        return m8080b(r7, r8);
     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.lang.String m8081a(android.content.Context r7, android.net.Uri r8, java.lang.String r9, java.lang.String[] r10) {
        /*
            java.lang.String r0 = "_data"
            java.lang.String[] r3 = new java.lang.String[]{r0}
            r0 = 0
            android.content.ContentResolver r1 = r7.getContentResolver()     // Catch: java.lang.Throwable -> L2e java.lang.Exception -> L35
            r6 = 0
            r2 = r8
            r4 = r9
            r5 = r10
            android.database.Cursor r0 = r1.query(r2, r3, r4, r5, r6)     // Catch: java.lang.Throwable -> L2e java.lang.Exception -> L35
            if (r0 == 0) goto L2b
            boolean r9 = r0.moveToFirst()     // Catch: java.lang.Throwable -> L2e java.lang.Exception -> L35
            if (r9 == 0) goto L2b
            java.lang.String r9 = "_data"
            int r9 = r0.getColumnIndexOrThrow(r9)     // Catch: java.lang.Throwable -> L2e java.lang.Exception -> L35
            java.lang.String r7 = r0.getString(r9)     // Catch: java.lang.Throwable -> L2e java.lang.Exception -> L35
            if (r0 == 0) goto L2a
            r0.close()
        L2a:
            return r7
        L2b:
            if (r0 == 0) goto L3a
            goto L37
        L2e:
            r7 = move-exception
            if (r0 == 0) goto L34
            r0.close()
        L34:
            throw r7
        L35:
            if (r0 == 0) goto L3a
        L37:
            r0.close()
        L3a:
            java.lang.String r7 = m8080b(r7, r8)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sina.weibo.sdk.p311b.C7091b.m8081a(android.content.Context, android.net.Uri, java.lang.String, java.lang.String[]):java.lang.String");
    }

    /* renamed from: b */
    public static String m8080b(Context context, Uri uri) {
        String uri2 = uri.toString();
        return new File(context.getExternalFilesDir(null), uri2.substring(uri2.lastIndexOf("/"))).getAbsolutePath();
    }

    /* renamed from: c */
    public static boolean m8076c(File file) {
        if (file.getParent() != null) {
            file = new File(file.getParentFile().getCanonicalFile(), file.getName());
        }
        return !file.getCanonicalFile().equals(file.getAbsoluteFile());
    }
}
