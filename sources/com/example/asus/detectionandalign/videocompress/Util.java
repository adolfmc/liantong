package com.example.asus.detectionandalign.videocompress;

import android.net.Uri;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class Util {
    /* JADX WARN: Removed duplicated region for block: B:28:0x00a9  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00ce  */
    @android.annotation.SuppressLint({"NewApi"})
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String getFilePath(android.content.Context r12, android.net.Uri r13) {
        /*
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 0
            r2 = 19
            if (r0 < r2) goto L9a
            android.content.Context r0 = r12.getApplicationContext()
            boolean r0 = android.provider.DocumentsContract.isDocumentUri(r0, r13)
            if (r0 == 0) goto L9a
            boolean r0 = isExternalStorageDocument(r13)
            r2 = 1
            if (r0 == 0) goto L3d
            java.lang.String r12 = android.provider.DocumentsContract.getDocumentId(r13)
            java.lang.String r13 = ":"
            java.lang.String[] r12 = r12.split(r13)
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r13.<init>()
            java.io.File r0 = android.os.Environment.getExternalStorageDirectory()
            r13.append(r0)
            java.lang.String r0 = "/"
            r13.append(r0)
            r12 = r12[r2]
            r13.append(r12)
            java.lang.String r12 = r13.toString()
            return r12
        L3d:
            boolean r0 = isDownloadsDocument(r13)
            if (r0 == 0) goto L5a
            java.lang.String r13 = android.provider.DocumentsContract.getDocumentId(r13)
            java.lang.String r0 = "content://downloads/public_downloads"
            android.net.Uri r0 = android.net.Uri.parse(r0)
            java.lang.Long r13 = java.lang.Long.valueOf(r13)
            long r2 = r13.longValue()
            android.net.Uri r13 = android.content.ContentUris.withAppendedId(r0, r2)
            goto L9a
        L5a:
            boolean r0 = isMediaDocument(r13)
            if (r0 == 0) goto L9a
            java.lang.String r0 = android.provider.DocumentsContract.getDocumentId(r13)
            java.lang.String r3 = ":"
            java.lang.String[] r0 = r0.split(r3)
            r3 = 0
            r4 = r0[r3]
            java.lang.String r5 = "image"
            boolean r5 = r5.equals(r4)
            if (r5 == 0) goto L78
            android.net.Uri r13 = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            goto L8e
        L78:
            java.lang.String r5 = "video"
            boolean r5 = r5.equals(r4)
            if (r5 == 0) goto L84
            android.net.Uri r13 = android.provider.MediaStore.Video.Media.EXTERNAL_CONTENT_URI
            goto L8e
        L84:
            java.lang.String r5 = "audio"
            boolean r4 = r5.equals(r4)
            if (r4 == 0) goto L8e
            android.net.Uri r13 = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        L8e:
            java.lang.String r4 = "_id=?"
            java.lang.String[] r5 = new java.lang.String[r2]
            r0 = r0[r2]
            r5[r3] = r0
            r7 = r13
            r9 = r4
            r10 = r5
            goto L9d
        L9a:
            r7 = r13
            r9 = r1
            r10 = r9
        L9d:
            java.lang.String r13 = "content"
            java.lang.String r0 = r7.getScheme()
            boolean r13 = r13.equalsIgnoreCase(r0)
            if (r13 == 0) goto Lce
            java.lang.String r13 = "_data"
            java.lang.String[] r8 = new java.lang.String[]{r13}
            android.content.ContentResolver r6 = r12.getContentResolver()     // Catch: java.lang.Exception -> Lc9
            r11 = 0
            android.database.Cursor r12 = r6.query(r7, r8, r9, r10, r11)     // Catch: java.lang.Exception -> Lc9
            java.lang.String r13 = "_data"
            int r13 = r12.getColumnIndexOrThrow(r13)     // Catch: java.lang.Exception -> Lc9
            boolean r0 = r12.moveToFirst()     // Catch: java.lang.Exception -> Lc9
            if (r0 == 0) goto Ldf
            java.lang.String r12 = r12.getString(r13)     // Catch: java.lang.Exception -> Lc9
            return r12
        Lc9:
            r12 = move-exception
            r12.printStackTrace()
            goto Ldf
        Lce:
            java.lang.String r12 = "file"
            java.lang.String r13 = r7.getScheme()
            boolean r12 = r12.equalsIgnoreCase(r13)
            if (r12 == 0) goto Ldf
            java.lang.String r12 = r7.getPath()
            return r12
        Ldf:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.asus.detectionandalign.videocompress.Util.getFilePath(android.content.Context, android.net.Uri):java.lang.String");
    }

    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }
}
