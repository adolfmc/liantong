package com.huawei.hms.push;

import android.content.Context;
import android.content.Intent;
import com.huawei.hms.push.utils.PluginUtil;
import com.huawei.hms.support.log.HMSLog;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.huawei.hms.push.r */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class SelfShow {
    /* renamed from: a */
    public static void m14194a(Context context, Intent intent) {
        try {
            if (context != null && intent != null) {
                String action = intent.getAction();
                if ("com.huawei.intent.action.PUSH".equals(action) || "com.huawei.push.msg.NOTIFY_MSG".equals(action) || "com.huawei.intent.action.PUSH_DELAY_NOTIFY".equals(action)) {
                    byte[] byteArrayExtra = intent.getByteArrayExtra("selfshow_info");
                    byte[] byteArrayExtra2 = intent.getByteArrayExtra("selfshow_token");
                    if (byteArrayExtra != null && byteArrayExtra2 != null && byteArrayExtra.length != 0 && byteArrayExtra2.length != 0) {
                        m14191a(context, intent, byteArrayExtra, byteArrayExtra2);
                        return;
                    }
                    HMSLog.m14110i("PushSelfShowLog", "self show info or token is null.");
                    return;
                }
                return;
            }
            HMSLog.m14115d("PushSelfShowLog", "enter SelfShowReceiver receiver, context or intent is null");
        } catch (RuntimeException e) {
            HMSLog.m14111e("PushSelfShowLog", "onReceive RuntimeException.", e);
        } catch (Exception unused) {
            HMSLog.m14115d("PushSelfShowLog", "onReceive Exception.");
        }
    }

    /* renamed from: a */
    private static void m14191a(Context context, Intent intent, byte[] bArr, byte[] bArr2) {
        String stringExtra = intent.getStringExtra("selfshow_event_id");
        int intExtra = intent.getIntExtra("selfshow_notify_id", 0);
        HMSLog.m14110i("PushSelfShowLog", "get notifyId:" + intExtra);
        PushSelfShowMessage pushSelfShowMessage = new PushSelfShowMessage(bArr, bArr2);
        if (!pushSelfShowMessage.m14206z()) {
            HMSLog.m14115d("PushSelfShowLog", "parseMessage failed");
            return;
        }
        HMSLog.m14110i("PushSelfShowLog", "onReceive the msg id = " + pushSelfShowMessage.m14216p() + ",and cmd is " + pushSelfShowMessage.m14226i() + ",and the eventId is " + stringExtra);
        if (stringExtra == null) {
            m14193a(context, intent, pushSelfShowMessage);
        } else {
            m14192a(context, intent, stringExtra, pushSelfShowMessage, intExtra);
        }
    }

    /* renamed from: a */
    private static void m14193a(Context context, Intent intent, PushSelfShowMessage pushSelfShowMessage) {
        HMSLog.m14110i("PushSelfShowLog", "receive a selfshow message, the cmd type is " + pushSelfShowMessage.m14226i());
        if (SelfShowType.m14189a(pushSelfShowMessage.m14226i())) {
            long m14281a = C5049d.m14281a(pushSelfShowMessage.m14238c());
            if (m14281a == 0) {
                new PushSelfShowThread(context, pushSelfShowMessage).start();
                return;
            }
            HMSLog.m14115d("PushSelfShowLog", "waiting...");
            intent.setPackage(context.getPackageName());
            C5049d.m14284a(context, intent, m14281a);
        }
    }

    /* renamed from: a */
    private static void m14192a(Context context, Intent intent, String str, PushSelfShowMessage pushSelfShowMessage, int i) {
        HMSLog.m14115d("PushSelfShowLog", "receive a selfshow user handle message eventId = " + str);
        if (!"-1".equals(str)) {
            C5049d.m14285a(context, intent);
        } else {
            C5049d.m14286a(context, i);
        }
        if ("1".equals(str)) {
            new SelfShowType(context, pushSelfShowMessage).m14187c();
            PluginUtil.onNotificationClicked(context, pushSelfShowMessage.m14216p(), pushSelfShowMessage.m14240b());
        } else if ("2".equals(str)) {
            PushAnalyticsUtils.m14262a(context, pushSelfShowMessage.m14216p(), pushSelfShowMessage.m14240b(), "2");
        } else {
            HMSLog.m14115d("PushSelfShowLog", "other event");
        }
    }
}
