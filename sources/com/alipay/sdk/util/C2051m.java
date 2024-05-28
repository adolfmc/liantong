package com.alipay.sdk.util;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import com.alipay.sdk.app.EnvUtils;
import com.alipay.sdk.cons.C2003a;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.alipay.sdk.util.m */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2051m {

    /* renamed from: a */
    private static final String f3901a = "content://com.alipay.android.app.settings.data.ServerProvider/current_server";

    /* renamed from: a */
    public static String m20676a(Context context) {
        if (EnvUtils.isSandBox()) {
            return "https://mobilegw.alipaydev.com/mgw.htm";
        }
        if (context == null) {
            return C2003a.f3669a;
        }
        String str = C2003a.f3669a;
        return TextUtils.isEmpty(str) ? C2003a.f3669a : str;
    }

    /* renamed from: b */
    private static String m20675b(Context context) {
        Cursor query = context.getContentResolver().query(Uri.parse("content://com.alipay.android.app.settings.data.ServerProvider/current_server"), null, null, null, null);
        if (query != null && query.getCount() > 0) {
            r0 = query.moveToFirst() ? query.getString(query.getColumnIndex("url")) : null;
            query.close();
        }
        return r0;
    }
}
