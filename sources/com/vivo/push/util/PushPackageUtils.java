package com.vivo.push.util;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.text.TextUtils;
import com.vivo.push.cache.ConfigManagerFactory;
import com.vivo.push.cache.IConfigManager;
import com.vivo.push.model.PushPackageInfo;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.vivo.push.util.aa */
/* loaded from: E:\11617560_dexfile_execute.dex */
public final class PushPackageUtils {

    /* renamed from: a */
    private static Boolean f21177a;

    /* renamed from: b */
    private static String f21178b;

    /* renamed from: a */
    public static PushPackageInfo m5470a(Context context, ISystemRely iSystemRely) {
        PushPackageInfo m5461f;
        Context applicationContext = ContextDelegate.getContext(context).getApplicationContext();
        PushPackageInfo m5465c = m5465c(applicationContext);
        if (m5465c != null) {
            LogUtil.m5341d("PushPackageUtils", "get system push info :".concat(String.valueOf(m5465c)));
            return m5465c;
        }
        List<String> mo5381a = iSystemRely.mo5381a(applicationContext);
        PushPackageInfo m5461f2 = m5461f(applicationContext, applicationContext.getPackageName());
        if (mo5381a == null || mo5381a.size() <= 0) {
            if (m5461f2 != null && m5461f2.m5594d()) {
                m5465c = m5461f2;
            }
            LogUtil.m5354a("PushPackageUtils", "findAllPushPackages error: find no package!");
        } else {
            PushPackageInfo pushPackageInfo = null;
            String mo5408a = SystemCache.m5449b(applicationContext).mo5408a("com.vivo.push.cur_pkg", null);
            m5465c = (TextUtils.isEmpty(mo5408a) || !m5468a(applicationContext, mo5408a, "com.vivo.pushservice.action.METHOD") || (m5465c = m5461f(applicationContext, mo5408a)) == null || !m5465c.m5594d()) ? null : null;
            m5461f2 = (m5461f2 == null || !m5461f2.m5594d()) ? null : null;
            if (m5465c == null) {
                m5465c = null;
            }
            if (m5461f2 != null && (m5465c == null || (!m5461f2.m5595c() ? m5465c.m5595c() || m5461f2.m5597b() > m5465c.m5597b() : m5465c.m5595c() && m5461f2.m5597b() > m5465c.m5597b()))) {
                m5465c = m5461f2;
            }
            HashMap hashMap = new HashMap();
            if (m5465c == null) {
                m5465c = null;
            } else if (m5465c.m5595c()) {
                pushPackageInfo = m5465c;
                m5465c = null;
            }
            int size = mo5381a.size();
            for (int i = 0; i < size; i++) {
                String str = mo5381a.get(i);
                if (!TextUtils.isEmpty(str) && (m5461f = m5461f(applicationContext, str)) != null) {
                    hashMap.put(str, m5461f);
                    if (m5461f.m5594d()) {
                        if (m5461f.m5595c()) {
                            if (pushPackageInfo == null || m5461f.m5597b() > pushPackageInfo.m5597b()) {
                                pushPackageInfo = m5461f;
                            }
                        } else if (m5465c == null || m5461f.m5597b() > m5465c.m5597b()) {
                            m5465c = m5461f;
                        }
                    }
                }
            }
            if (m5465c == null) {
                LogUtil.m5341d("PushPackageUtils", "findSuitablePushPackage, all push app in balck list.");
                m5465c = pushPackageInfo;
            }
        }
        if (m5465c != null) {
            if (m5465c.m5595c()) {
                LogUtil.m5356a(applicationContext, "查找最优包为:" + m5465c.m5602a() + "(" + m5465c.m5597b() + ", Black)");
                LogUtil.m5341d("PushPackageUtils", "finSuitablePushPackage" + m5465c.m5602a() + "(" + m5465c.m5597b() + ", Black)");
            } else {
                LogUtil.m5356a(applicationContext, "查找最优包为:" + m5465c.m5602a() + "(" + m5465c.m5597b() + ")");
                LogUtil.m5341d("PushPackageUtils", "finSuitablePushPackage" + m5465c.m5602a() + "(" + m5465c.m5597b() + ")");
            }
        } else {
            LogUtil.m5348b(applicationContext, "查找最优包为空!");
            LogUtil.m5341d("PushPackageUtils", "finSuitablePushPackage is null");
        }
        return m5465c;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:113:0x014c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:95:0x013c A[Catch: Exception -> 0x0113, TRY_ENTER, TryCatch #7 {Exception -> 0x0113, blocks: (B:77:0x010f, B:81:0x0117, B:83:0x011b, B:95:0x013c, B:97:0x0141, B:99:0x0145), top: B:118:0x000e }] */
    /* JADX WARN: Type inference failed for: r11v0, types: [android.content.Context] */
    /* JADX WARN: Type inference failed for: r11v10, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r11v13 */
    /* JADX WARN: Type inference failed for: r11v27 */
    /* JADX WARN: Type inference failed for: r11v3 */
    /* JADX WARN: Type inference failed for: r11v6 */
    /* JADX WARN: Type inference failed for: r11v7, types: [android.database.Cursor] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String m5472a(android.content.Context r11) {
        /*
            Method dump skipped, instructions count: 356
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vivo.push.util.PushPackageUtils.m5472a(android.content.Context):java.lang.String");
    }

    /* renamed from: c */
    private static PushPackageInfo m5465c(Context context) {
        String m5472a = m5472a(context);
        ApplicationInfo applicationInfo = null;
        if (TextUtils.isEmpty(m5472a)) {
            return null;
        }
        PushPackageInfo pushPackageInfo = new PushPackageInfo(m5472a);
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(m5472a, 128);
            if (packageInfo != null) {
                pushPackageInfo.m5601a(packageInfo.versionCode);
                pushPackageInfo.m5599a(packageInfo.versionName);
                applicationInfo = packageInfo.applicationInfo;
            }
            if (applicationInfo != null) {
                pushPackageInfo.m5600a(Utility.m5446a(context, m5472a));
            }
            pushPackageInfo.m5598a(m5471a(context, pushPackageInfo.m5597b()));
            pushPackageInfo.m5596b(m5469a(context, m5472a));
            return pushPackageInfo;
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.m5345b("PushPackageUtils", "PackageManager NameNotFoundException is null", e);
            return null;
        }
    }

    /* renamed from: f */
    private static PushPackageInfo m5461f(Context context, String str) {
        ApplicationInfo applicationInfo;
        if (!TextUtils.isEmpty(str)) {
            if (m5468a(context, str, "com.vivo.pushservice.action.METHOD") || m5468a(context, str, "com.vivo.pushservice.action.RECEIVE")) {
                PushPackageInfo pushPackageInfo = new PushPackageInfo(str);
                try {
                    PackageInfo packageInfo = context.getPackageManager().getPackageInfo(str, 128);
                    if (packageInfo != null) {
                        pushPackageInfo.m5601a(packageInfo.versionCode);
                        pushPackageInfo.m5599a(packageInfo.versionName);
                        applicationInfo = packageInfo.applicationInfo;
                    } else {
                        applicationInfo = null;
                    }
                    if (applicationInfo != null) {
                        pushPackageInfo.m5600a(Utility.m5446a(context, str));
                    }
                    pushPackageInfo.m5596b(m5469a(context, str));
                    pushPackageInfo.m5598a(m5471a(context, pushPackageInfo.m5597b()));
                    return pushPackageInfo;
                } catch (Exception e) {
                    LogUtil.m5353a("PushPackageUtils", "getPushPackageInfo exception: ", e);
                    return null;
                }
            }
        }
        return null;
    }

    /* renamed from: a */
    public static boolean m5469a(Context context, String str) {
        if (TextUtils.isEmpty(str) || context == null) {
            return false;
        }
        Intent intent = new Intent("com.vivo.pushservice.action.PUSH_SERVICE");
        intent.setPackage(str);
        PackageManager packageManager = context.getPackageManager();
        List<ResolveInfo> queryIntentServices = packageManager.queryIntentServices(intent, 576);
        if (queryIntentServices == null || queryIntentServices.size() <= 0) {
            LogUtil.m5354a("PushPackageUtils", "isEnablePush error: can not find push service.");
            return false;
        }
        int size = queryIntentServices.size();
        boolean z = false;
        for (int i = 0; i < size; i++) {
            ResolveInfo resolveInfo = queryIntentServices.get(i);
            if (resolveInfo != null && resolveInfo.serviceInfo != null) {
                String str2 = resolveInfo.serviceInfo.name;
                boolean z2 = resolveInfo.serviceInfo.exported;
                if ("com.vivo.push.sdk.service.PushService".equals(str2) && z2) {
                    boolean z3 = resolveInfo.serviceInfo.enabled;
                    int componentEnabledSetting = packageManager.getComponentEnabledSetting(new ComponentName(str, "com.vivo.push.sdk.service.PushService"));
                    boolean z4 = true;
                    if (componentEnabledSetting != 1 && (componentEnabledSetting != 0 || !z3)) {
                        z4 = false;
                    }
                    z = z4;
                }
            }
        }
        return z;
    }

    /* renamed from: a */
    private static boolean m5471a(Context context, long j) {
        IConfigManager m5748a = ConfigManagerFactory.m5749a().m5748a(context);
        if (m5748a != null) {
            return m5748a.isInBlackList(j);
        }
        return false;
    }

    /* renamed from: a */
    private static boolean m5468a(Context context, String str, String str2) {
        List<ResolveInfo> list;
        Intent intent = new Intent(str2);
        intent.setPackage(str);
        try {
            list = context.getPackageManager().queryBroadcastReceivers(intent, 576);
        } catch (Exception unused) {
            list = null;
        }
        return list != null && list.size() > 0;
    }

    /* renamed from: c */
    public static boolean m5464c(Context context, String str) {
        return m5468a(context, str, "com.vivo.pushclient.action.RECEIVE");
    }

    /* renamed from: d */
    public static boolean m5463d(Context context, String str) {
        return m5468a(context, str, "com.vivo.pushservice.action.RECEIVE");
    }

    /* renamed from: e */
    public static boolean m5462e(Context context, String str) {
        return m5468a(context, str, "com.vivo.pushservice.action.METHOD");
    }

    /* renamed from: g */
    private static String m5460g(Context context, String str) {
        if (TextUtils.isEmpty(str) || context == null) {
            return null;
        }
        try {
            byte[] digest = MessageDigest.getInstance("SHA256").digest(context.getPackageManager().getPackageInfo(str, 64).signatures[0].toByteArray());
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b : digest) {
                String upperCase = Integer.toHexString(b & 255).toUpperCase(Locale.US);
                if (upperCase.length() == 1) {
                    stringBuffer.append("0");
                }
                stringBuffer.append(upperCase);
            }
            return stringBuffer.toString();
        } catch (Exception e) {
            LogUtil.m5354a("PushPackageUtils", " getSignatureSHA exception ".concat(String.valueOf(e)));
            return null;
        }
    }

    /* renamed from: b */
    public static boolean m5467b(Context context) {
        ProviderInfo resolveContentProvider;
        Boolean bool = f21177a;
        if (bool != null) {
            return bool.booleanValue();
        }
        String str = null;
        if (context != null && !TextUtils.isEmpty("com.vivo.push.sdk.service.SystemPushConfig") && (resolveContentProvider = context.getPackageManager().resolveContentProvider("com.vivo.push.sdk.service.SystemPushConfig", 128)) != null) {
            str = resolveContentProvider.packageName;
        }
        Boolean valueOf = Boolean.valueOf("BCC35D4D3606F154F0402AB7634E8490C0B244C2675C3C6238986987024F0C02".equals(m5460g(context, str)));
        f21177a = valueOf;
        return valueOf.booleanValue();
    }

    /* renamed from: b */
    public static int m5466b(Context context, String str) {
        int i = m5468a(context, str, "com.vivo.pushservice.action.RECEIVE") ? 0 : -1;
        if (m5468a(context, str, "com.vivo.pushclient.action.RECEIVE")) {
            return 1;
        }
        return i;
    }
}
