package com.sina.weibo.sdk;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.openapi.SdkListener;
import com.sina.weibo.sdk.p311b.C7089a;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.sina.weibo.sdk.a */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class C7061a {

    /* renamed from: a */
    private static boolean f18262a;

    /* renamed from: b */
    private static AuthInfo f18263b;

    /* renamed from: a */
    public static void m8105a(AuthInfo authInfo, SdkListener sdkListener) {
        if (f18262a) {
            return;
        }
        if (authInfo == null) {
            throw new RuntimeException("authInfo must not be null.");
        }
        f18263b = authInfo;
        f18262a = true;
        if (sdkListener != null) {
            sdkListener.onInitSuccess();
        }
    }

    /* renamed from: a */
    public static boolean m8106a(Context context) {
        List<ResolveInfo> queryIntentServices;
        String[] strArr = {"com.sina.weibo", "com.sina.weibog3"};
        for (int i = 0; i < 2; i++) {
            String str = strArr[i];
            Intent intent = new Intent("com.sina.weibo.action.sdkidentity");
            intent.setPackage(str);
            intent.addCategory("android.intent.category.DEFAULT");
            if (context != null && (queryIntentServices = context.getPackageManager().queryIntentServices(intent, 0)) != null && !queryIntentServices.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: b */
    public static boolean m8104b(Context context) {
        C7089a.C7090a m8083e;
        return m8106a(context) && (m8083e = C7089a.m8083e(context)) != null && m8083e.f18304ah >= 10772;
    }

    /* renamed from: a */
    public static AuthInfo m8107a() {
        if (f18262a) {
            return f18263b;
        }
        throw new RuntimeException("please init sdk before use it. Wb.install()");
    }
}
