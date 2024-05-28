package com.bytedance.sdk.openadsdk.multipro;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import com.bytedance.sdk.openadsdk.ITTProvider;
import com.bytedance.sdk.openadsdk.TTAdSdk;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class TTMultiProvider extends ContentProvider {
    @Override // android.content.ContentProvider
    public boolean onCreate() {
        return true;
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        if (m16347mb() != null) {
            return m16347mb().query(uri, strArr, str, strArr2, str2);
        }
        return null;
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        return m16347mb() != null ? m16347mb().getType(uri) : "";
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues contentValues) {
        if (m16347mb() != null) {
            return m16347mb().insert(uri, contentValues);
        }
        return null;
    }

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String str, String[] strArr) {
        if (m16347mb() != null) {
            return m16347mb().delete(uri, str, strArr);
        }
        return 0;
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        if (m16347mb() != null) {
            return m16347mb().update(uri, contentValues, str, strArr);
        }
        return 0;
    }

    /* renamed from: mb */
    private ITTProvider m16347mb() {
        if (TTAdSdk.getAdManager() != null) {
            return (ITTProvider) TTAdSdk.getAdManager().getExtra(ITTProvider.class, null);
        }
        return null;
    }
}
