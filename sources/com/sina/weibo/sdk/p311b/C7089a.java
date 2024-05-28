package com.sina.weibo.sdk.p311b;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.text.TextUtils;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.sina.weibo.sdk.b.a */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class C7089a {

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.sina.weibo.sdk.b.a$a */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static class C7090a {

        /* renamed from: ah */
        public int f18304ah;
        public String packageName = "com.sina.weibo";

        /* renamed from: ag */
        public String f18303ag = "com.sina.weibo.SSOActivity";
    }

    /* renamed from: a */
    public static boolean m8087a(Context context, Intent intent) {
        PackageManager packageManager;
        ResolveInfo resolveActivity;
        if (context == null || (packageManager = context.getPackageManager()) == null || (resolveActivity = packageManager.resolveActivity(intent, 0)) == null) {
            return false;
        }
        try {
            Signature[] signatureArr = packageManager.getPackageInfo(resolveActivity.activityInfo.packageName, 64).signatures;
            if (signatureArr == null) {
                return false;
            }
            for (Signature signature : signatureArr) {
                if ("18da2bf10352443a00a5e046d9fca6bd".equals(C7093d.m8070a(signature.toByteArray()))) {
                    return true;
                }
            }
            return false;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    /* renamed from: c */
    public static C7090a m8085c(Context context) {
        return m8083e(context);
    }

    /* renamed from: d */
    public static boolean m8084d(Context context) {
        C7090a m8083e = m8083e(context);
        return m8083e != null && m8083e.f18304ah >= 10791;
    }

    /* renamed from: e */
    public static C7090a m8083e(Context context) {
        List<ResolveInfo> queryIntentServices;
        C7090a m8086a;
        String[] strArr = {"com.sina.weibo", "com.sina.weibog3"};
        for (int i = 0; i < 2; i++) {
            String str = strArr[i];
            Intent intent = new Intent("com.sina.weibo.action.sdkidentity");
            intent.setPackage(str);
            intent.addCategory("android.intent.category.DEFAULT");
            if (context != null && (queryIntentServices = context.getPackageManager().queryIntentServices(intent, 0)) != null && !queryIntentServices.isEmpty()) {
                for (ResolveInfo resolveInfo : queryIntentServices) {
                    if (resolveInfo.serviceInfo != null && resolveInfo.serviceInfo.applicationInfo != null && !TextUtils.isEmpty(resolveInfo.serviceInfo.packageName)) {
                        String str2 = resolveInfo.serviceInfo.packageName;
                        if (TextUtils.equals(str, str2) && (m8086a = m8086a(context, str2)) != null) {
                            return m8086a;
                        }
                    }
                }
                continue;
            }
        }
        return null;
    }

    /* renamed from: a */
    private static C7090a m8086a(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            InputStream open = context.createPackageContext(str, 2).getAssets().open("weibo_for_sdk.json");
            StringBuilder sb = new StringBuilder();
            byte[] bArr = new byte[4096];
            while (true) {
                int read = open.read(bArr, 0, 4096);
                if (read != -1) {
                    sb.append(new String(bArr, 0, read));
                } else {
                    JSONObject jSONObject = new JSONObject(sb.toString());
                    C7090a c7090a = new C7090a();
                    c7090a.f18304ah = jSONObject.optInt("support_api", -1);
                    c7090a.f18303ag = jSONObject.optString("authActivityName", null);
                    c7090a.packageName = str;
                    return c7090a;
                }
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        } catch (JSONException e3) {
            e3.printStackTrace();
            return null;
        }
    }
}
