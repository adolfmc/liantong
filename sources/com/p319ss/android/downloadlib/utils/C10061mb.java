package com.p319ss.android.downloadlib.utils;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.text.TextUtils;
import com.p319ss.android.downloadlib.C9982hj;
import com.p319ss.android.downloadlib.addownload.C9940x;
import com.p319ss.android.socialbase.appdownloader.p340u.C10152hj;
import com.p319ss.android.socialbase.downloader.constants.DownloadConstants;
import com.p319ss.android.ttmd5.TTMd5;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.ss.android.downloadlib.utils.mb */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C10061mb {
    /* renamed from: mb */
    public static String m6987mb(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return TTMd5.ttmd5(new File(str));
    }

    /* renamed from: mb */
    public static int m6986mb(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return 5;
        }
        return TTMd5.checkMd5(str, new File(str2));
    }

    /* renamed from: ox */
    public static String m6983ox(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            ApplicationInfo applicationInfo = C9940x.getContext().getPackageManager().getApplicationInfo(str, 0);
            if (applicationInfo != null) {
                return applicationInfo.sourceDir;
            }
        } catch (Exception unused) {
        }
        return null;
    }

    /* renamed from: mb */
    public static void m6988mb() {
        if (C9940x.m7364lz().optInt("hook", 0) != 1) {
            return;
        }
        C9982hj.m7254mb().m7252mb(new Runnable() { // from class: com.ss.android.downloadlib.utils.mb.1
            @Override // java.lang.Runnable
            public void run() {
                C10152hj.m6572ko();
                C10061mb.m6989b();
            }
        }, 10000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public static void m6989b() {
        Field declaredField;
        Class<?> cls;
        try {
            if (Build.VERSION.SDK_INT < 26) {
                declaredField = Class.forName("android.app.ActivityManagerNative").getDeclaredField("gDefault");
            } else {
                declaredField = Class.forName("android.app.ActivityManager").getDeclaredField("IActivityManagerSingleton");
            }
            declaredField.setAccessible(true);
            Object obj = declaredField.get(null);
            Field declaredField2 = Class.forName("android.util.Singleton").getDeclaredField("mInstance");
            declaredField2.setAccessible(true);
            Object obj2 = declaredField2.get(obj);
            if (obj2 == null || (cls = Class.forName("android.app.IActivityManager")) == null) {
                return;
            }
            declaredField2.set(obj, Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{cls}, new C10063mb(obj2)));
        } catch (Throwable unused) {
        }
    }

    /* renamed from: mb */
    public static void m6985mb(Object[] objArr) {
        if (C9940x.m7364lz().optInt("hook", 0) == 1 && (objArr[1] instanceof String) && (objArr[2] instanceof Intent)) {
            Intent intent = (Intent) objArr[2];
            if ("android.intent.action.VIEW".equals(intent.getAction()) && DownloadConstants.MIME_APK.equals(intent.getType())) {
                if (C10152hj.m6580b()) {
                    String optString = C9940x.m7364lz().optString("hook_vivo_arg", "com.android.settings");
                    if ("null".equals(optString)) {
                        return;
                    }
                    objArr[1] = optString;
                } else if (C10152hj.m6577hj()) {
                    JSONObject m7364lz = C9940x.m7364lz();
                    String optString2 = m7364lz.optString("hook_kllk_arg1", "com." + DownloadConstants.LOWER_OPPO + ".market");
                    if (!"null".equals(optString2)) {
                        objArr[1] = optString2;
                    }
                    String optString3 = C9940x.m7364lz().optString("hook_kllk_arg2", "com.android.browser");
                    JSONObject m7364lz2 = C9940x.m7364lz();
                    String optString4 = m7364lz2.optString("hook_kllk_arg3", "m.store." + DownloadConstants.LOWER_OPPO + "mobile.com");
                    StringBuilder sb = new StringBuilder();
                    sb.append(DownloadConstants.LOWER_OPPO);
                    sb.append("_extra_pkg_name");
                    intent.putExtra(sb.toString(), optString3);
                    intent.putExtra("refererHost", optString4);
                    if (C9940x.m7364lz().optInt("hook_kllk_arg4", 0) == 1) {
                        Intent intent2 = new Intent();
                        intent2.putExtra(DownloadConstants.LOWER_OPPO + "_extra_pkg_name", optString3);
                        intent2.putExtra("refererHost", optString4);
                        intent.putExtra("android.intent.extra.INTENT", intent2);
                    }
                } else if (C10152hj.m6569mb()) {
                    String optString5 = C9940x.m7364lz().optString("hook_huawei_arg1", "com.huawei.appmarket");
                    if (!"null".equals(optString5)) {
                        objArr[1] = optString5;
                    }
                    intent.putExtra("caller_package", C9940x.m7364lz().optString("hook_huawei_arg2", "com.huawei.appmarket"));
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.ss.android.downloadlib.utils.mb$mb */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public static class C10063mb implements InvocationHandler {

        /* renamed from: mb */
        private Object f19398mb;

        private C10063mb(Object obj) {
            this.f19398mb = obj;
        }

        @Override // java.lang.reflect.InvocationHandler
        public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
            try {
                if ("startActivity".contains(method.getName())) {
                    C10061mb.m6985mb(objArr);
                }
            } catch (Throwable unused) {
            }
            return method.invoke(this.f19398mb, objArr);
        }
    }
}
