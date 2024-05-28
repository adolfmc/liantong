package com.huawei.hms.push.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.hms.aaid.constant.ErrorEnum;
import com.huawei.hms.aaid.plugin.ProxyCenter;
import com.huawei.hms.aaid.utils.PushPreferences;
import com.huawei.hms.push.C5051p;
import com.huawei.hms.push.NotificationUtil;
import com.huawei.hms.push.PushAnalyticsUtils;
import com.huawei.hms.push.PushConst;
import com.huawei.hms.push.SelfShow;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.utils.ResourceLoaderUtil;
import java.nio.charset.Charset;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class PluginUtil {
    private PluginUtil() {
    }

    /* renamed from: a */
    private static void m14181a(Context context, String str, String str2, String str3) {
        HMSLog.m14110i("PluginUtil", "onNotification");
        if (!NotificationUtil.m14268a(context)) {
            HMSLog.m14110i("PluginUtil", context.getPackageName() + " disable display notification.");
            PushAnalyticsUtils.m14262a(context, str, null, "103");
            return;
        }
        Intent intent = new Intent();
        intent.setAction("com.huawei.push.msg.NOTIFY_MSG");
        Charset charset = PushConst.f11631a;
        intent.putExtra("selfshow_info", str3.getBytes(charset));
        intent.putExtra("selfshow_token", str2.getBytes(charset));
        intent.setPackage(context.getPackageName());
        SelfShow.m14194a(context, intent);
        HMSLog.m14110i("PluginUtil", "invokeSelfShow done");
    }

    public static void onAppOpened(Context context, String str, String str2, Bundle bundle) {
        PushAnalyticsUtils.m14262a(context, str, str2, "appHasOpenedId");
        PushAnalyticsUtils.m14264a(context, bundle, "hmsStatistics");
    }

    public static boolean onDataMessage(Context context, String str, String str2, boolean z) {
        HMSLog.m14110i("PluginUtil", "onDataMessage");
        if (TextUtils.isEmpty(str2)) {
            HMSLog.m14110i("PluginUtil", "Empty message received");
            return true;
        }
        if (z) {
            m14182a(context, str);
        }
        Intent intent = new Intent("com.huawei.push.action.MESSAGING_EVENT");
        intent.setPackage(context.getPackageName());
        Bundle bundle = new Bundle();
        bundle.putString("message_id", str);
        bundle.putByteArray("message_body", str2.getBytes(PushConst.f11631a));
        bundle.putString("message_type", "received_message");
        return new C5051p().m14200a(context, bundle, intent);
    }

    public static boolean onDeletedMessages(Context context) {
        HMSLog.m14110i("PluginUtil", "onDeletedMessages");
        if (context == null) {
            return false;
        }
        Intent intent = new Intent("com.huawei.push.action.MESSAGING_EVENT");
        intent.setPackage(context.getPackageName());
        Bundle bundle = new Bundle();
        bundle.putString("message_proxy_type", ProxyCenter.getProxy().getProxyType());
        bundle.putString("message_type", "server_deleted_message");
        return new C5051p().m14200a(context, bundle, intent);
    }

    public static void onMessage(Context context, String[] strArr) {
        HMSLog.m14110i("PluginUtil", "onMessage");
        if (context == null || strArr == null || strArr.length < 5) {
            return;
        }
        if (ResourceLoaderUtil.getmContext() == null) {
            ResourceLoaderUtil.setmContext(context.getApplicationContext());
        }
        if (m14180a(context, strArr)) {
            m14181a(context, strArr[0], strArr[3], strArr[4]);
        } else {
            onDataMessage(context, strArr[0], strArr[4], true);
        }
    }

    public static boolean onNewToken(Context context, String str, String str2, ErrorEnum errorEnum) {
        HMSLog.m14110i("PluginUtil", "onNewToken called.");
        Intent intent = new Intent("com.huawei.push.action.MESSAGING_EVENT");
        intent.setPackage(context.getPackageName());
        Bundle bundle = new Bundle();
        bundle.putInt("error", errorEnum.getInternalCode());
        bundle.putString("message_type", "new_token");
        bundle.putString("device_token", str);
        if (TextUtils.equals(str2, context.getPackageName())) {
            bundle.putString("subjectId", null);
        } else {
            bundle.putString("subjectId", str2);
        }
        bundle.putString("message_proxy_type", ProxyCenter.getProxy().getProxyType());
        return new C5051p().m14200a(context, bundle, intent);
    }

    public static void onNotificationArrived(Context context, String str, String str2) {
        PushAnalyticsUtils.m14262a(context, str, str2, "100");
    }

    public static void onNotificationClicked(Context context, String str, String str2) {
        PushAnalyticsUtils.m14262a(context, str, str2, "1");
        onAppOpened(context, str, str2, null);
    }

    public static boolean onOldDataMessage(Context context, String str, String str2) {
        if (ResourceLoaderUtil.getmContext() == null) {
            ResourceLoaderUtil.setmContext(context.getApplicationContext());
        }
        return onDataMessage(context, str, m14179a(str2), true);
    }

    public static void saveNotifySwitch(Context context, boolean z) {
        new PushPreferences(context, "push_notify_flag").saveBoolean("notify_msg_enable", z);
    }

    /* renamed from: a */
    private static String m14179a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            jSONObject.put("data", str);
            jSONObject2.put("msgContent", jSONObject);
            return jSONObject2.toString();
        } catch (JSONException unused) {
            HMSLog.m14112e("PluginUtil", "rebuild message failed");
            return null;
        }
    }

    /* renamed from: a */
    private static boolean m14183a(Context context) {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
        if (runningAppProcesses == null) {
            HMSLog.m14109w("PluginUtil", "get running app processes null!");
            return false;
        }
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            if (runningAppProcessInfo.processName.equals(context.getPackageName())) {
                int i = runningAppProcessInfo.importance;
                return (i == 100 || i == 200) ? false : true;
            }
        }
        return false;
    }

    /* renamed from: a */
    private static boolean m14180a(Context context, String[] strArr) {
        if (TextUtils.equals(strArr[1], "0")) {
            return m14183a(context) || TextUtils.equals(strArr[2], "1");
        }
        return false;
    }

    /* renamed from: a */
    private static void m14182a(Context context, String str) {
        PushAnalyticsUtils.m14262a(context, str, null, "102");
    }
}
