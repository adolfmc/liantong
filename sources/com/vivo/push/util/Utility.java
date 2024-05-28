package com.vivo.push.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.ComponentInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.vivo.push.PushConstants;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.vivo.push.util.ag */
/* loaded from: E:\11617560_dexfile_execute.dex */
public final class Utility {

    /* renamed from: a */
    private static String[] f21192a = {"com.vivo.push.sdk.RegistrationReceiver", "com.vivo.push.sdk.service.PushService", "com.vivo.push.sdk.service.CommonJobService"};

    /* renamed from: b */
    private static String[] f21193b = {"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE", "android.permission.WRITE_SETTINGS", "android.permission.VIBRATE", "android.permission.ACCESS_WIFI_STATE", "android.permission.WAKE_LOCK", "com.bbk.account.permission.READ_ACCOUNTINFO", "android.permission.AUTHENTICATE_ACCOUNTS", "android.permission.MOUNT_UNMOUNT_FILESYSTEMS", "android.permission.GET_TASKS"};

    /* renamed from: c */
    private static String[] f21194c = {"com.vivo.push.sdk.service.CommandService", "com.vivo.push.sdk.service.CommonJobService"};

    /* renamed from: d */
    private static String[] f21195d = {"com.vivo.push.sdk.RegistrationReceiver"};

    /* renamed from: e */
    private static String[] f21196e = new String[0];

    /* renamed from: f */
    private static Map<String, Bundle> f21197f = new ConcurrentHashMap();

    /* renamed from: a */
    public static long m5448a(Context context) {
        String m5472a = PushPackageUtils.m5472a(context);
        if (TextUtils.isEmpty(m5472a)) {
            LogUtil.m5354a("Utility", "systemPushPkgName is null");
            return -1L;
        }
        return m5446a(context, m5472a);
    }

    /* renamed from: a */
    public static long m5446a(Context context, String str) {
        Object m5445a = m5445a(context, str, "com.vivo.push.sdk_version");
        if (m5445a == null) {
            m5445a = m5445a(context, str, "sdk_version");
        }
        if (m5445a != null) {
            try {
                return Long.parseLong(m5445a.toString());
            } catch (Exception e) {
                e.printStackTrace();
                LogUtil.m5353a("Utility", "getSdkVersionCode error ", e);
                return -1L;
            }
        }
        LogUtil.m5354a("Utility", "getSdkVersionCode sdk version is null");
        return -1L;
    }

    /* renamed from: b */
    public static String m5436b(Context context, String str) {
        Object m5445a = m5445a(context, str, "verification_status");
        return m5445a != null ? m5445a.toString() : "";
    }

    /* renamed from: a */
    public static Object m5445a(Context context, String str, String str2) {
        Bundle bundle;
        if (context == null || str2 == null || TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            Object obj = (f21197f == null || f21197f.size() <= 0 || (bundle = f21197f.get(str)) == null) ? null : bundle.get(str2);
            if (obj != null) {
                return obj;
            }
            try {
                ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(str, 128);
                r0 = applicationInfo != null ? applicationInfo.metaData : null;
                Object obj2 = r0 != null ? r0.get(str2) : obj;
                try {
                    if (f21197f.size() <= 300) {
                        f21197f.put(str, r0);
                        return obj2;
                    }
                    return obj2;
                } catch (Exception e) {
                    r0 = obj2;
                    e = e;
                    LogUtil.m5354a("Utility", "getMetaValue::".concat(String.valueOf(e)));
                    return r0;
                }
            } catch (Exception e2) {
                e = e2;
                r0 = obj;
            }
        } catch (Exception e3) {
            e = e3;
        }
    }

    /* renamed from: a */
    public static Object m5439a(String str, String str2) throws Exception {
        Class<?> cls = Class.forName(str);
        return cls.getField(str2).get(cls);
    }

    /* JADX WARN: Code restructure failed: missing block: B:42:0x0102, code lost:
        r9 = r9 + 1;
     */
    /* renamed from: b */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void m5437b(android.content.Context r15) throws com.vivo.push.util.VivoPushException {
        /*
            Method dump skipped, instructions count: 516
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vivo.push.util.Utility.m5437b(android.content.Context):void");
    }

    /* renamed from: d */
    private static void m5431d(Context context, String str) throws VivoPushException {
        try {
            if (context.getPackageManager() == null) {
                throw new VivoPushException("localPackageManager is null");
            }
            ServiceInfo[] serviceInfoArr = context.getPackageManager().getPackageInfo(context.getPackageName(), 4).services;
            if (serviceInfoArr == null) {
                throw new VivoPushException("serviceInfos is null");
            }
            for (String str2 : f21194c) {
                m5438a(str2, serviceInfoArr, str);
            }
        } catch (Exception e) {
            throw new VivoPushException("error " + e.getMessage());
        }
    }

    /* renamed from: e */
    private static void m5430e(Context context, String str) throws VivoPushException {
        if (f21196e.length <= 0) {
            return;
        }
        try {
            if (context.getPackageManager() == null) {
                throw new VivoPushException("localPackageManager is null");
            }
            ActivityInfo[] activityInfoArr = context.getPackageManager().getPackageInfo(context.getPackageName(), 1).activities;
            if (activityInfoArr == null) {
                throw new VivoPushException("activityInfos is null");
            }
            for (String str2 : f21196e) {
                m5438a(str2, activityInfoArr, str);
            }
        } catch (Exception e) {
            throw new VivoPushException("error " + e.getMessage());
        }
    }

    /* renamed from: a */
    private static void m5438a(String str, ComponentInfo[] componentInfoArr, String str2) throws VivoPushException {
        for (ComponentInfo componentInfo : componentInfoArr) {
            if (str.equals(componentInfo.name)) {
                if (!componentInfo.enabled) {
                    throw new VivoPushException(componentInfo.name + " module Push-SDK need is illegitmacy !");
                }
                m5441a(componentInfo, str2);
                return;
            }
        }
        throw new VivoPushException(str + " module Push-SDK need is not exist");
    }

    /* renamed from: a */
    private static void m5441a(ComponentInfo componentInfo, String str) throws VivoPushException {
        if (componentInfo.applicationInfo.packageName.equals(str)) {
            return;
        }
        for (String str2 : f21192a) {
            if (str2.equals(componentInfo.name) && !componentInfo.processName.contains(":pushservice")) {
                throw new VivoPushException("module : " + componentInfo.name + " process :" + componentInfo.processName + "  check process fail");
            }
        }
    }

    /* renamed from: f */
    private static void m5429f(Context context, String str) throws VivoPushException {
        try {
            if (context.getPackageManager() == null) {
                throw new VivoPushException("localPackageManager is null");
            }
            ActivityInfo[] activityInfoArr = context.getPackageManager().getPackageInfo(context.getPackageName(), 2).receivers;
            if (activityInfoArr == null) {
                throw new VivoPushException("receivers is null");
            }
            for (String str2 : f21195d) {
                m5438a(str2, activityInfoArr, str);
            }
        } catch (Exception e) {
            throw new VivoPushException(e.getMessage());
        }
    }

    /* renamed from: a */
    private static void m5443a(Context context, String str, String str2, boolean z) throws VivoPushException {
        Intent intent = new Intent(str);
        intent.setPackage(context.getPackageName());
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager == null) {
                throw new VivoPushException("localPackageManager is null");
            }
            if (z) {
                List<ResolveInfo> queryBroadcastReceivers = packageManager.queryBroadcastReceivers(intent, 576);
                if (queryBroadcastReceivers == null || queryBroadcastReceivers.size() <= 0) {
                    throw new VivoPushException("checkModule " + intent + " has no receivers");
                }
                for (ResolveInfo resolveInfo : queryBroadcastReceivers) {
                    if (str2.equals(resolveInfo.activityInfo.name)) {
                        return;
                    }
                }
                throw new VivoPushException(str2 + " is missing");
            }
            List<ResolveInfo> queryIntentServices = packageManager.queryIntentServices(intent, 576);
            if (queryIntentServices == null || queryIntentServices.size() <= 0) {
                throw new VivoPushException("checkModule " + intent + " has no services");
            }
            for (ResolveInfo resolveInfo2 : queryIntentServices) {
                if (str2.equals(resolveInfo2.serviceInfo.name)) {
                    if (resolveInfo2.serviceInfo.exported) {
                        return;
                    }
                    throw new VivoPushException(resolveInfo2.serviceInfo.name + " exported is false");
                }
            }
            throw new VivoPushException(str2 + " is missing");
        } catch (Exception e) {
            LogUtil.m5354a("Utility", "error  " + e.getMessage());
            throw new VivoPushException("checkModule error" + e.getMessage());
        }
    }

    /* renamed from: b */
    public static String m5435b(String str, String str2) {
        String str3;
        try {
            str3 = (String) Class.forName("android.os.SystemProperties").getMethod("get", String.class).invoke(null, str);
        } catch (Exception e) {
            e.printStackTrace();
            str3 = str2;
        }
        return (str3 == null || str3.length() == 0) ? str2 : str3;
    }

    /* JADX WARN: Code restructure failed: missing block: B:47:0x00b1, code lost:
        if (android.os.Build.VERSION.SDK_INT >= 24) goto L47;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x00b3, code lost:
        r2.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x00ce, code lost:
        if (android.os.Build.VERSION.SDK_INT >= 24) goto L47;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00c7 A[Catch: Exception -> 0x00d1, TRY_ENTER, TryCatch #11 {Exception -> 0x00d1, blocks: (B:44:0x00aa, B:46:0x00af, B:48:0x00b3, B:58:0x00c7, B:60:0x00cc), top: B:85:0x0003 }] */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00cc A[Catch: Exception -> 0x00d1, TRY_LEAVE, TryCatch #11 {Exception -> 0x00d1, blocks: (B:44:0x00aa, B:46:0x00af, B:48:0x00b3, B:58:0x00c7, B:60:0x00cc), top: B:85:0x0003 }] */
    /* JADX WARN: Removed duplicated region for block: B:83:0x00d5 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r11v0, types: [android.content.Context] */
    /* JADX WARN: Type inference failed for: r11v10 */
    /* JADX WARN: Type inference failed for: r11v2 */
    /* JADX WARN: Type inference failed for: r11v20 */
    /* JADX WARN: Type inference failed for: r11v5, types: [android.database.Cursor] */
    /* renamed from: c */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.security.PublicKey m5434c(android.content.Context r11) {
        /*
            Method dump skipped, instructions count: 226
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vivo.push.util.Utility.m5434c(android.content.Context):java.security.PublicKey");
    }

    /* renamed from: a */
    public static void m5447a(Context context, Intent intent) {
        String m5472a = PushPackageUtils.m5472a(context);
        String stringExtra = intent.getStringExtra("client_pkgname");
        if (TextUtils.isEmpty(m5472a)) {
            LogUtil.m5354a("Utility", "illegality abe adapter : push pkg is null");
        } else if (TextUtils.isEmpty(stringExtra)) {
            LogUtil.m5354a("Utility", "illegality abe adapter : src pkg is null");
        } else if (m5472a.equals(context.getPackageName())) {
            LogUtil.m5354a("Utility", "illegality abe adapter : abe is not pushservice");
        } else if (!m5472a.equals(stringExtra)) {
            LogUtil.m5341d("Utility", "proxy to core : intent pkg : " + intent.getPackage() + " ; src pkg : " + stringExtra + " ; push pkg : " + m5472a);
            intent.setPackage(m5472a);
            intent.setClassName(m5472a, "com.vivo.push.sdk.service.PushService");
            context.startService(intent);
        } else {
            LogUtil.m5354a("Utility", "illegality abe adapter : pushPkg = " + m5472a + " ; srcPkg = " + stringExtra);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0103 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r1v0, types: [android.content.ContentProviderClient, android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r1v11 */
    /* JADX WARN: Type inference failed for: r1v12 */
    /* JADX WARN: Type inference failed for: r1v5 */
    /* JADX WARN: Type inference failed for: r1v6, types: [android.database.Cursor] */
    /* renamed from: d */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean m5432d(android.content.Context r17) {
        /*
            Method dump skipped, instructions count: 283
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vivo.push.util.Utility.m5432d(android.content.Context):boolean");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:83:0x00f1 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r1v0, types: [android.content.ContentProviderClient, android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r1v10 */
    /* JADX WARN: Type inference failed for: r1v11 */
    /* JADX WARN: Type inference failed for: r1v5 */
    /* JADX WARN: Type inference failed for: r1v6, types: [android.database.Cursor] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean m5444a(android.content.Context r15, java.lang.String r16, java.lang.String r17, long r18) {
        /*
            Method dump skipped, instructions count: 265
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vivo.push.util.Utility.m5444a(android.content.Context, java.lang.String, java.lang.String, long):boolean");
    }

    /* renamed from: a */
    public static boolean m5442a(Context context, String str, boolean z) {
        Cursor m5440a;
        Cursor cursor = null;
        try {
            try {
                try {
                    Uri uri = PushConstants.f21252e;
                    String[] strArr = new String[2];
                    strArr[0] = str;
                    strArr[1] = z ? "1" : "0";
                    m5440a = m5440a(uri, "appPkgName = ? and agreePrivacyStatement = ? ", strArr, context);
                } catch (Throwable th) {
                    if (0 != 0) {
                        try {
                            cursor.close();
                        } catch (Exception e) {
                            LogUtil.m5353a("Utility", "close", e);
                        }
                    }
                    throw th;
                }
            } catch (Exception e2) {
                LogUtil.m5353a("Utility", "syncAgreePrivacyStatement", e2);
                if (0 != 0) {
                    cursor.close();
                }
            }
        } catch (Exception e3) {
            LogUtil.m5353a("Utility", "close", e3);
        }
        if (m5440a == null) {
            LogUtil.m5354a("Utility", "cursor is null");
            if (m5440a != null) {
                try {
                    m5440a.close();
                } catch (Exception e4) {
                    LogUtil.m5353a("Utility", "close", e4);
                }
            }
            return false;
        } else if (!m5440a.moveToFirst()) {
            if (m5440a != null) {
                m5440a.close();
            }
            return false;
        } else {
            boolean parseBoolean = Boolean.parseBoolean(m5440a.getString(m5440a.getColumnIndex("agreePrivacyStatement")));
            if (m5440a != null) {
                try {
                    m5440a.close();
                } catch (Exception e5) {
                    LogUtil.m5353a("Utility", "close", e5);
                }
            }
            return parseBoolean;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:58:0x0094 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static android.database.Cursor m5440a(android.net.Uri r10, java.lang.String r11, java.lang.String[] r12, android.content.Context r13) {
        /*
            r0 = 24
            r1 = 0
            if (r13 != 0) goto Ld
            java.lang.String r10 = "Utility"
            java.lang.String r11 = "context is null"
            com.vivo.push.util.LogUtil.m5354a(r10, r11)     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L75
            return r1
        Ld:
            int r2 = android.os.Build.VERSION.SDK_INT     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L75
            if (r2 < r0) goto L2f
            android.content.ContentResolver r2 = r13.getContentResolver()     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L75
            android.content.ContentProviderClient r2 = r2.acquireUnstableContentProviderClient(r10)     // Catch: java.lang.Throwable -> L72 java.lang.Exception -> L75
            if (r2 == 0) goto L2d
            java.lang.String r3 = "Utility"
            java.lang.String r4 = "client is null"
            com.vivo.push.util.LogUtil.m5354a(r3, r4)     // Catch: java.lang.Exception -> L41 java.lang.Throwable -> L91
            r5 = 0
            r8 = 0
            r3 = r2
            r4 = r10
            r6 = r11
            r7 = r12
            android.database.Cursor r3 = r3.query(r4, r5, r6, r7, r8)     // Catch: java.lang.Exception -> L41 java.lang.Throwable -> L91
            goto L31
        L2d:
            r3 = r1
            goto L31
        L2f:
            r2 = r1
            r3 = r2
        L31:
            if (r3 != 0) goto L43
            android.content.ContentResolver r4 = r13.getContentResolver()     // Catch: java.lang.Exception -> L41 java.lang.Throwable -> L91
            r6 = 0
            r9 = 0
            r5 = r10
            r7 = r11
            r8 = r12
            android.database.Cursor r3 = r4.query(r5, r6, r7, r8, r9)     // Catch: java.lang.Exception -> L41 java.lang.Throwable -> L91
            goto L43
        L41:
            r10 = move-exception
            goto L77
        L43:
            if (r3 != 0) goto L5f
            java.lang.String r10 = "Utility"
            java.lang.String r11 = "cursor is null"
            com.vivo.push.util.LogUtil.m5354a(r10, r11)     // Catch: java.lang.Exception -> L41 java.lang.Throwable -> L91
            if (r2 == 0) goto L5e
            int r10 = android.os.Build.VERSION.SDK_INT     // Catch: java.lang.Exception -> L56
            if (r10 < r0) goto L5e
            r2.close()     // Catch: java.lang.Exception -> L56
            goto L5e
        L56:
            r10 = move-exception
            java.lang.String r11 = "Utility"
            java.lang.String r12 = "close"
            com.vivo.push.util.LogUtil.m5353a(r11, r12, r10)
        L5e:
            return r1
        L5f:
            if (r2 == 0) goto L71
            int r10 = android.os.Build.VERSION.SDK_INT     // Catch: java.lang.Exception -> L69
            if (r10 < r0) goto L71
            r2.close()     // Catch: java.lang.Exception -> L69
            goto L71
        L69:
            r10 = move-exception
            java.lang.String r11 = "Utility"
            java.lang.String r12 = "close"
            com.vivo.push.util.LogUtil.m5353a(r11, r12, r10)
        L71:
            return r3
        L72:
            r10 = move-exception
            r2 = r1
            goto L92
        L75:
            r10 = move-exception
            r2 = r1
        L77:
            java.lang.String r11 = "Utility"
            java.lang.String r12 = "queryContentResolver"
            com.vivo.push.util.LogUtil.m5353a(r11, r12, r10)     // Catch: java.lang.Throwable -> L91
            if (r2 == 0) goto L90
            int r10 = android.os.Build.VERSION.SDK_INT     // Catch: java.lang.Exception -> L88
            if (r10 < r0) goto L90
            r2.close()     // Catch: java.lang.Exception -> L88
            goto L90
        L88:
            r10 = move-exception
            java.lang.String r11 = "Utility"
            java.lang.String r12 = "close"
            com.vivo.push.util.LogUtil.m5353a(r11, r12, r10)
        L90:
            return r1
        L91:
            r10 = move-exception
        L92:
            if (r2 == 0) goto La4
            int r11 = android.os.Build.VERSION.SDK_INT     // Catch: java.lang.Exception -> L9c
            if (r11 < r0) goto La4
            r2.close()     // Catch: java.lang.Exception -> L9c
            goto La4
        L9c:
            r11 = move-exception
            java.lang.String r12 = "Utility"
            java.lang.String r13 = "close"
            com.vivo.push.util.LogUtil.m5353a(r12, r13, r11)
        La4:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vivo.push.util.Utility.m5440a(android.net.Uri, java.lang.String, java.lang.String[], android.content.Context):android.database.Cursor");
    }

    /* renamed from: g */
    private static int m5428g(Context context, String str) {
        int i;
        if (context == null || TextUtils.isEmpty(str)) {
            LogUtil.m5354a("Utility", "getClientSdkVersion() error, context is null or pkgName is empty");
            return 0;
        }
        String str2 = "";
        Object m5445a = m5445a(context, str, "sdk_version_vivo");
        if (m5445a instanceof String) {
            str2 = (String) m5445a;
            i = 0;
        } else if (m5445a instanceof Integer) {
            i = ((Integer) m5445a).intValue();
        } else if (m5445a == null) {
            return 0;
        } else {
            str2 = m5445a.toString();
            i = 0;
        }
        if (i > 0) {
            return i;
        }
        try {
            return Integer.parseInt(str2);
        } catch (Exception e) {
            LogUtil.m5354a("Utility", "getClientSdkVersion: ".concat(String.valueOf(e)));
            return 0;
        }
    }

    /* renamed from: c */
    public static int m5433c(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            LogUtil.m5354a("Utility", "getClientSdkVersionCode() error, context is null or pkgName is empty");
            return 0;
        }
        int m5446a = (int) m5446a(context, str);
        return m5446a <= 0 ? m5428g(context, str) : m5446a;
    }
}
