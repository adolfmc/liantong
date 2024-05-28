package com.bytedance.sdk.openadsdk;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface ITTProvider {
    int delete(Uri uri, String str, String[] strArr);

    String getTableName();

    String getType(Uri uri);

    void init();

    void injectContext(Context context);

    Uri insert(Uri uri, ContentValues contentValues);

    Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2);

    int update(Uri uri, ContentValues contentValues, String str, String[] strArr);
}
