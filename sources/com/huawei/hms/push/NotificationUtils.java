package com.huawei.hms.push;

import android.app.Notification;
import android.text.TextUtils;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.huawei.hms.push.h */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class NotificationUtils {
    /* renamed from: a */
    public static NotifyStyle m14265a(PushSelfShowMessage pushSelfShowMessage) {
        NotifyStyle notifyStyle = NotifyStyle.STYLE_DEFAULT;
        return (pushSelfShowMessage.m14209w() < 0 || pushSelfShowMessage.m14209w() >= NotifyStyle.values().length) ? notifyStyle : NotifyStyle.values()[pushSelfShowMessage.m14209w()];
    }

    /* renamed from: a */
    public static void m14266a(Notification.Builder builder, String str, PushSelfShowMessage pushSelfShowMessage) {
        Notification.BigTextStyle bigTextStyle = new Notification.BigTextStyle();
        if (!TextUtils.isEmpty(pushSelfShowMessage.m14228h())) {
            bigTextStyle.setBigContentTitle(pushSelfShowMessage.m14228h());
        }
        if (TextUtils.isEmpty(str)) {
            str = null;
        }
        if (!TextUtils.isEmpty(str)) {
            bigTextStyle.bigText(str);
        }
        builder.setStyle(bigTextStyle);
    }
}
