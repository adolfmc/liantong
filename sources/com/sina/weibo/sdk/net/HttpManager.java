package com.sina.weibo.sdk.net;

import android.content.Context;
import android.text.TextUtils;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class HttpManager {
    private static native String calcOauthSignNative(Context context, String str, String str2);

    static {
        System.loadLibrary("weibosdkcore");
    }

    /* renamed from: a */
    public static String m8059a(Context context, String str, String str2, String str3, String str4) {
        StringBuilder sb = new StringBuilder();
        if (!TextUtils.isEmpty(str)) {
            sb.append(str);
        }
        if (!TextUtils.isEmpty(str2)) {
            sb.append(str2);
        }
        if (!TextUtils.isEmpty(str3)) {
            sb.append(str3);
        }
        return calcOauthSignNative(context, sb.toString(), str4);
    }
}
