package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PermissionInfo;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.text.TextUtils;
import android.util.Log;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.xiaomi.mipush.sdk.m */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C11105m {
    /* renamed from: a */
    public static void m5059a(final Context context) {
        new Thread(new Runnable() { // from class: com.xiaomi.mipush.sdk.m.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 4612);
                    C11105m.m5050c(context);
                    C11105m.m5048d(context, packageInfo);
                    C11105m.m5049c(context, packageInfo);
                } catch (Throwable th) {
                    Log.e("ManifestChecker", "", th);
                }
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public static void m5049c(Context context, PackageInfo packageInfo) {
        boolean z;
        String[] strArr;
        HashSet hashSet = new HashSet();
        String str = context.getPackageName() + ".permission.MIPUSH_RECEIVE";
        hashSet.addAll(Arrays.asList("android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE", str, "android.permission.ACCESS_WIFI_STATE", "android.permission.VIBRATE"));
        if (packageInfo.permissions != null) {
            for (PermissionInfo permissionInfo : packageInfo.permissions) {
                if (str.equals(permissionInfo.name)) {
                    z = true;
                    break;
                }
            }
        }
        z = false;
        if (!z) {
            throw new C11107a(String.format("<permission android:name=\"%1$s\" .../> is undefined in AndroidManifest.", str));
        }
        if (packageInfo.requestedPermissions != null) {
            for (String str2 : packageInfo.requestedPermissions) {
                if (!TextUtils.isEmpty(str2) && hashSet.contains(str2)) {
                    hashSet.remove(str2);
                    if (hashSet.isEmpty()) {
                        break;
                    }
                }
            }
        }
        if (!hashSet.isEmpty()) {
            throw new C11107a(String.format("<uses-permission android:name=\"%1$s\"/> is missing in AndroidManifest.", hashSet.iterator().next()));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: d */
    public static void m5048d(Context context, PackageInfo packageInfo) {
        ServiceInfo[] serviceInfoArr;
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        hashMap2.put(PushMessageHandler.class.getCanonicalName(), new C11108b(PushMessageHandler.class.getCanonicalName(), true, true, ""));
        hashMap2.put(MessageHandleService.class.getCanonicalName(), new C11108b(MessageHandleService.class.getCanonicalName(), true, false, ""));
        if (!MiPushClient.shouldUseMIUIPush(context) || m5055a(packageInfo, new String[]{"com.xiaomi.push.service.XMJobService", "com.xiaomi.push.service.XMPushService"})) {
            hashMap2.put("com.xiaomi.push.service.XMJobService", new C11108b("com.xiaomi.push.service.XMJobService", true, false, "android.permission.BIND_JOB_SERVICE"));
            hashMap2.put("com.xiaomi.push.service.XMPushService", new C11108b("com.xiaomi.push.service.XMPushService", true, false, ""));
        }
        if (MiPushClient.getOpenFCMPush(context)) {
            hashMap2.put("com.xiaomi.assemble.control.MiFireBaseInstanceIdService", new C11108b("com.xiaomi.assemble.control.MiFireBaseInstanceIdService", true, false, ""));
            hashMap2.put("com.xiaomi.assemble.control.MiFirebaseMessagingService", new C11108b("com.xiaomi.assemble.control.MiFirebaseMessagingService", true, false, ""));
        }
        if (MiPushClient.getOpenOPPOPush(context)) {
            hashMap2.put("com.xiaomi.assemble.control.COSPushMessageService", new C11108b("com.xiaomi.assemble.control.COSPushMessageService", true, true, "com.coloros.mcs.permission.SEND_MCS_MESSAGE"));
        }
        if (packageInfo.services != null) {
            for (ServiceInfo serviceInfo : packageInfo.services) {
                if (!TextUtils.isEmpty(serviceInfo.name) && hashMap2.containsKey(serviceInfo.name)) {
                    C11108b c11108b = (C11108b) hashMap2.remove(serviceInfo.name);
                    boolean z = c11108b.f21395a;
                    boolean z2 = c11108b.f21397b;
                    String str = c11108b.f21396b;
                    if (z != serviceInfo.enabled) {
                        throw new C11107a(String.format("<service android:name=\"%1$s\" .../> in AndroidManifest had the wrong enabled attribute, which should be android:enabled=%2$b.", serviceInfo.name, Boolean.valueOf(z)));
                    }
                    if (z2 != serviceInfo.exported) {
                        throw new C11107a(String.format("<service android:name=\"%1$s\" .../> in AndroidManifest had the wrong exported attribute, which should be android:exported=%2$b.", serviceInfo.name, Boolean.valueOf(z2)));
                    }
                    if (!TextUtils.isEmpty(str) && !TextUtils.equals(str, serviceInfo.permission)) {
                        throw new C11107a(String.format("<service android:name=\"%1$s\" .../> in AndroidManifest had the wrong permission attribute, which should be android:permission=\"%2$s\".", serviceInfo.name, str));
                    }
                    hashMap.put(serviceInfo.name, serviceInfo.processName);
                    if (hashMap2.isEmpty()) {
                        break;
                    }
                }
            }
        }
        if (!hashMap2.isEmpty()) {
            throw new C11107a(String.format("<service android:name=\"%1$s\" .../> is missing or disabled in AndroidManifest.", hashMap2.keySet().iterator().next()));
        }
        if (!TextUtils.equals((CharSequence) hashMap.get(PushMessageHandler.class.getCanonicalName()), (CharSequence) hashMap.get(MessageHandleService.class.getCanonicalName()))) {
            throw new C11107a(String.format("\"%1$s\" and \"%2$s\" must be running in the same process.", PushMessageHandler.class.getCanonicalName(), MessageHandleService.class.getCanonicalName()));
        }
        if (hashMap.containsKey("com.xiaomi.push.service.XMJobService") && hashMap.containsKey("com.xiaomi.push.service.XMPushService") && !TextUtils.equals((CharSequence) hashMap.get("com.xiaomi.push.service.XMJobService"), (CharSequence) hashMap.get("com.xiaomi.push.service.XMPushService"))) {
            throw new C11107a(String.format("\"%1$s\" and \"%2$s\" must be running in the same process.", "com.xiaomi.push.service.XMJobService", "com.xiaomi.push.service.XMPushService"));
        }
    }

    /* renamed from: a */
    private static boolean m5055a(PackageInfo packageInfo, String[] strArr) {
        for (ServiceInfo serviceInfo : packageInfo.services) {
            if (m5053a(strArr, serviceInfo.name)) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: a */
    private static boolean m5053a(String[] strArr, String str) {
        if (strArr == null || str == null) {
            return false;
        }
        for (String str2 : strArr) {
            if (TextUtils.equals(str2, str)) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: a */
    private static ActivityInfo m5054a(PackageManager packageManager, Intent intent, Class<?> cls) {
        for (ResolveInfo resolveInfo : packageManager.queryBroadcastReceivers(intent, 16384)) {
            ActivityInfo activityInfo = resolveInfo.activityInfo;
            if (activityInfo != null && cls.getCanonicalName().equals(activityInfo.name)) {
                return activityInfo;
            }
        }
        return null;
    }

    /* renamed from: a */
    private static void m5056a(ActivityInfo activityInfo, Boolean[] boolArr) {
        if (boolArr[0].booleanValue() != activityInfo.enabled) {
            throw new C11107a(String.format("<receiver android:name=\"%1$s\" .../> in AndroidManifest had the wrong enabled attribute, which should be android:enabled=%2$b.", activityInfo.name, boolArr[0]));
        }
        if (boolArr[1].booleanValue() != activityInfo.exported) {
            throw new C11107a(String.format("<receiver android:name=\"%1$s\" .../> in AndroidManifest had the wrong exported attribute, which should be android:exported=%2$b.", activityInfo.name, boolArr[1]));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00ac A[EDGE_INSN: B:43:0x00ac->B:30:0x00ac ?: BREAK  , SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0078 A[SYNTHETIC] */
    /* renamed from: c */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void m5050c(android.content.Context r8) {
        /*
            android.content.pm.PackageManager r0 = r8.getPackageManager()
            java.lang.String r1 = r8.getPackageName()
            android.content.Intent r2 = new android.content.Intent
            java.lang.String r3 = com.xiaomi.push.service.AbstractC11555an.f23591q
            r2.<init>(r3)
            r2.setPackage(r1)
            r3 = 1
            r4 = 0
            java.lang.String r5 = "com.xiaomi.push.service.receivers.PingReceiver"
            java.lang.Class r5 = com.xiaomi.push.C11479r.m2929a(r8, r5)     // Catch: java.lang.ClassNotFoundException -> L5f
            android.content.pm.ActivityInfo r2 = m5054a(r0, r2, r5)     // Catch: java.lang.ClassNotFoundException -> L5f
            boolean r5 = com.xiaomi.mipush.sdk.MiPushClient.shouldUseMIUIPush(r8)     // Catch: java.lang.ClassNotFoundException -> L5f
            r6 = 2
            if (r5 != 0) goto L4b
            if (r2 == 0) goto L39
            java.lang.Boolean[] r5 = new java.lang.Boolean[r6]     // Catch: java.lang.ClassNotFoundException -> L5f
            java.lang.Boolean r6 = java.lang.Boolean.valueOf(r3)     // Catch: java.lang.ClassNotFoundException -> L5f
            r5[r4] = r6     // Catch: java.lang.ClassNotFoundException -> L5f
            java.lang.Boolean r6 = java.lang.Boolean.valueOf(r4)     // Catch: java.lang.ClassNotFoundException -> L5f
            r5[r3] = r6     // Catch: java.lang.ClassNotFoundException -> L5f
            m5056a(r2, r5)     // Catch: java.lang.ClassNotFoundException -> L5f
            goto L63
        L39:
            com.xiaomi.mipush.sdk.m$a r2 = new com.xiaomi.mipush.sdk.m$a     // Catch: java.lang.ClassNotFoundException -> L5f
            java.lang.String r5 = "<receiver android:name=\"%1$s\" .../> is missing or disabled in AndroidManifest."
            java.lang.Object[] r6 = new java.lang.Object[r3]     // Catch: java.lang.ClassNotFoundException -> L5f
            java.lang.String r7 = "com.xiaomi.push.service.receivers.PingReceiver"
            r6[r4] = r7     // Catch: java.lang.ClassNotFoundException -> L5f
            java.lang.String r5 = java.lang.String.format(r5, r6)     // Catch: java.lang.ClassNotFoundException -> L5f
            r2.<init>(r5)     // Catch: java.lang.ClassNotFoundException -> L5f
            throw r2     // Catch: java.lang.ClassNotFoundException -> L5f
        L4b:
            if (r2 == 0) goto L63
            java.lang.Boolean[] r5 = new java.lang.Boolean[r6]     // Catch: java.lang.ClassNotFoundException -> L5f
            java.lang.Boolean r6 = java.lang.Boolean.valueOf(r3)     // Catch: java.lang.ClassNotFoundException -> L5f
            r5[r4] = r6     // Catch: java.lang.ClassNotFoundException -> L5f
            java.lang.Boolean r6 = java.lang.Boolean.valueOf(r4)     // Catch: java.lang.ClassNotFoundException -> L5f
            r5[r3] = r6     // Catch: java.lang.ClassNotFoundException -> L5f
            m5056a(r2, r5)     // Catch: java.lang.ClassNotFoundException -> L5f
            goto L63
        L5f:
            r2 = move-exception
            com.xiaomi.channel.commonutils.logger.AbstractC11049b.m5276a(r2)
        L63:
            android.content.Intent r2 = new android.content.Intent
            java.lang.String r5 = "com.xiaomi.mipush.RECEIVE_MESSAGE"
            r2.<init>(r5)
            r2.setPackage(r1)
            r1 = 16384(0x4000, float:2.2959E-41)
            java.util.List r0 = r0.queryBroadcastReceivers(r2, r1)
            java.util.Iterator r0 = r0.iterator()
            r1 = r4
        L78:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto Lac
            java.lang.Object r2 = r0.next()
            android.content.pm.ResolveInfo r2 = (android.content.pm.ResolveInfo) r2
            android.content.pm.ActivityInfo r2 = r2.activityInfo
            if (r2 == 0) goto La9
            java.lang.String r5 = r2.name     // Catch: java.lang.ClassNotFoundException -> La4
            boolean r5 = android.text.TextUtils.isEmpty(r5)     // Catch: java.lang.ClassNotFoundException -> La4
            if (r5 != 0) goto La9
            java.lang.Class<com.xiaomi.mipush.sdk.PushMessageReceiver> r5 = com.xiaomi.mipush.sdk.PushMessageReceiver.class
            java.lang.String r6 = r2.name     // Catch: java.lang.ClassNotFoundException -> La4
            java.lang.Class r6 = com.xiaomi.push.C11479r.m2929a(r8, r6)     // Catch: java.lang.ClassNotFoundException -> La4
            boolean r5 = r5.isAssignableFrom(r6)     // Catch: java.lang.ClassNotFoundException -> La4
            if (r5 == 0) goto La9
            boolean r1 = r2.enabled     // Catch: java.lang.ClassNotFoundException -> La4
            if (r1 == 0) goto La9
            r1 = r3
            goto Laa
        La4:
            r2 = move-exception
            com.xiaomi.channel.commonutils.logger.AbstractC11049b.m5276a(r2)
            goto L78
        La9:
            r1 = r4
        Laa:
            if (r1 == 0) goto L78
        Lac:
            if (r1 == 0) goto Ld0
            boolean r0 = com.xiaomi.mipush.sdk.MiPushClient.getOpenHmsPush(r8)
            if (r0 == 0) goto Lc2
            java.lang.String r0 = "com.huawei.android.push.intent.RECEIVE"
            java.lang.String r1 = "com.xiaomi.assemble.control.HmsPushReceiver"
            m5057a(r8, r0, r1)
            java.lang.String r0 = "com.huawei.intent.action.PUSH"
            java.lang.String r1 = "com.huawei.hms.support.api.push.PushEventReceiver"
            m5057a(r8, r0, r1)
        Lc2:
            boolean r0 = com.xiaomi.mipush.sdk.MiPushClient.getOpenVIVOPush(r8)
            if (r0 == 0) goto Lcf
            java.lang.String r0 = "com.vivo.pushclient.action.RECEIVE"
            java.lang.String r1 = "com.xiaomi.assemble.control.FTOSPushMessageReceiver"
            m5057a(r8, r0, r1)
        Lcf:
            return
        Ld0:
            com.xiaomi.mipush.sdk.m$a r8 = new com.xiaomi.mipush.sdk.m$a
            java.lang.String r0 = "Receiver: none of the subclasses of PushMessageReceiver is enabled or defined."
            r8.<init>(r0)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.mipush.sdk.C11105m.m5050c(android.content.Context):void");
    }

    /* renamed from: a */
    private static void m5057a(Context context, String str, String str2) {
        PackageManager packageManager = context.getPackageManager();
        String packageName = context.getPackageName();
        Intent intent = new Intent(str);
        intent.setPackage(packageName);
        boolean z = false;
        for (ResolveInfo resolveInfo : packageManager.queryBroadcastReceivers(intent, 16384)) {
            ActivityInfo activityInfo = resolveInfo.activityInfo;
            if (activityInfo == null || TextUtils.isEmpty(activityInfo.name) || !activityInfo.name.equals(str2)) {
                z = false;
                continue;
            } else {
                z = true;
                continue;
            }
            if (z) {
                break;
            }
        }
        if (!z) {
            throw new C11107a(String.format("<receiver android:name=\"%1$s\" .../> is missing or disabled in AndroidManifest.", str2));
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.xiaomi.mipush.sdk.m$b */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class C11108b {

        /* renamed from: a */
        public String f21394a;

        /* renamed from: a */
        public boolean f21395a;

        /* renamed from: b */
        public String f21396b;

        /* renamed from: b */
        public boolean f21397b;

        public C11108b(String str, boolean z, boolean z2, String str2) {
            this.f21394a = str;
            this.f21395a = z;
            this.f21397b = z2;
            this.f21396b = str2;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.xiaomi.mipush.sdk.m$a */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class C11107a extends RuntimeException {
        public C11107a(String str) {
            super(str);
        }
    }
}
