package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class HWPushHelper {

    /* renamed from: a */
    private static boolean f21319a;

    public static synchronized void setConnectTime(Context context) {
        synchronized (HWPushHelper.class) {
            context.getSharedPreferences("mipush_extra", 0).edit().putLong("last_connect_time", System.currentTimeMillis()).commit();
        }
    }

    public static synchronized boolean shouldTryConnect(Context context) {
        boolean z;
        synchronized (HWPushHelper.class) {
            z = Math.abs(System.currentTimeMillis() - context.getSharedPreferences("mipush_extra", 0).getLong("last_connect_time", -1L)) > 5000;
        }
        return z;
    }

    public static synchronized void setGetTokenTime(Context context) {
        synchronized (HWPushHelper.class) {
            context.getSharedPreferences("mipush_extra", 0).edit().putLong("last_get_token_time", System.currentTimeMillis()).commit();
        }
    }

    public static synchronized boolean shouldGetToken(Context context) {
        boolean z;
        synchronized (HWPushHelper.class) {
            z = Math.abs(System.currentTimeMillis() - context.getSharedPreferences("mipush_extra", 0).getLong("last_get_token_time", -1L)) > 172800000;
        }
        return z;
    }

    public static boolean isHmsTokenSynced(Context context) {
        String m5094a = C11094f.m5094a(context, EnumC11090d.ASSEMBLE_PUSH_HUAWEI, false);
        String m5046a = C11111p.m5047a(context).m5046a(EnumC11125v.UPLOAD_HUAWEI_TOKEN);
        return (TextUtils.isEmpty(m5094a) || TextUtils.isEmpty(m5046a) || !"synced".equals(m5046a)) ? false : true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x002c, code lost:
        r0 = r2.getString("pushMsg");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void notifyHmsNotificationMessageClicked(android.content.Context r4, java.lang.String r5) {
        /*
            java.lang.String r0 = ""
            boolean r1 = android.text.TextUtils.isEmpty(r5)
            if (r1 != 0) goto L39
            org.json.JSONArray r1 = new org.json.JSONArray     // Catch: java.lang.Exception -> L31
            r1.<init>(r5)     // Catch: java.lang.Exception -> L31
            int r5 = r1.length()     // Catch: java.lang.Exception -> L31
            if (r5 <= 0) goto L39
            r5 = 0
        L14:
            int r2 = r1.length()     // Catch: java.lang.Exception -> L31
            if (r5 >= r2) goto L39
            org.json.JSONObject r2 = r1.getJSONObject(r5)     // Catch: java.lang.Exception -> L31
            java.lang.String r3 = "pushMsg"
            boolean r3 = r2.has(r3)     // Catch: java.lang.Exception -> L31
            if (r3 == 0) goto L2e
            java.lang.String r5 = "pushMsg"
            java.lang.String r5 = r2.getString(r5)     // Catch: java.lang.Exception -> L31
            r0 = r5
            goto L39
        L2e:
            int r5 = r5 + 1
            goto L14
        L31:
            r5 = move-exception
            java.lang.String r5 = r5.toString()
            com.xiaomi.channel.commonutils.logger.AbstractC11049b.m5268d(r5)
        L39:
            com.xiaomi.mipush.sdk.PushMessageReceiver r5 = com.xiaomi.mipush.sdk.C11094f.m5103a(r4)
            if (r5 == 0) goto L53
            com.xiaomi.mipush.sdk.MiPushMessage r0 = com.xiaomi.mipush.sdk.C11094f.m5087a(r0)
            java.util.Map r1 = r0.getExtra()
            java.lang.String r2 = "notify_effect"
            boolean r1 = r1.containsKey(r2)
            if (r1 == 0) goto L50
            return
        L50:
            r5.onNotificationMessageClicked(r4, r0)
        L53:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.mipush.sdk.HWPushHelper.notifyHmsNotificationMessageClicked(android.content.Context, java.lang.String):void");
    }

    public static void notifyHmsPassThoughMessageArrived(Context context, String str) {
        String str2 = "";
        try {
            if (!TextUtils.isEmpty(str)) {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.has("content")) {
                    str2 = jSONObject.getString("content");
                }
            }
        } catch (Exception e) {
            AbstractC11049b.m5268d(e.toString());
        }
        PushMessageReceiver m5103a = C11094f.m5103a(context);
        if (m5103a != null) {
            m5103a.onReceivePassThroughMessage(context, C11094f.m5087a(str2));
        }
    }

    public static boolean isUserOpenHmsPush(Context context) {
        return MiPushClient.getOpenHmsPush(context);
    }

    public static void registerHuaWeiAssemblePush(Context context) {
        AbstractPushManager m5112a = C11091e.m5114a(context).m5112a(EnumC11090d.ASSEMBLE_PUSH_HUAWEI);
        if (m5112a != null) {
            m5112a.register();
        }
    }

    public static void uploadToken(Context context, String str) {
        C11094f.m5095a(context, EnumC11090d.ASSEMBLE_PUSH_HUAWEI, str);
    }

    public static void reportError(String str, int i) {
        C11094f.m5086a(str, i);
    }

    public static boolean hasNetwork(Context context) {
        return C11094f.m5101a(context);
    }

    public static void setNeedConnect(boolean z) {
        f21319a = z;
    }

    public static boolean needConnect() {
        return f21319a;
    }

    public static void convertMessage(Intent intent) {
        C11094f.m5091a(intent);
    }
}
