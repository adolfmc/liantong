package com.sinovatech.unicom.separatemodule.capture;

import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class UriUtils {
    private UriUtils() {
        throw new AssertionError();
    }

    public static String getImagePath(Context context, Intent intent) {
        String str = null;
        try {
            Uri data = intent.getData();
            if (Build.VERSION.SDK_INT > 19) {
                Log.d("uri=intent.getData :", "" + data);
                if (DocumentsContract.isDocumentUri(context, data)) {
                    String documentId = DocumentsContract.getDocumentId(data);
                    Log.d("getDocumentId(uri) :", "" + documentId);
                    Log.d("uri.getAuthority() :", "" + data.getAuthority());
                    if ("com.android.providers.media.documents".equals(data.getAuthority())) {
                        String str2 = documentId.split(":")[1];
                        str = getImagePath(context, MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "_id=" + str2);
                    } else if ("com.android.providers.downloads.documents".equals(data.getAuthority())) {
                        str = getImagePath(context, ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(documentId).longValue()), null);
                    }
                } else if ("content".equalsIgnoreCase(data.getScheme())) {
                    str = getImagePath(context, data, null);
                }
            } else {
                str = getImagePath(context, data, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    private static String getImagePath(Context context, Uri uri, String str) {
        Cursor query = context.getContentResolver().query(uri, null, str, null, null);
        if (query != null) {
            r7 = query.moveToFirst() ? query.getString(query.getColumnIndex("_data")) : null;
            query.close();
        }
        return r7;
    }
}
