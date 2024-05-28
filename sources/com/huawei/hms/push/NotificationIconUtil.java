package com.huawei.hms.push;

import android.annotation.TargetApi;
import android.app.Notification;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.text.TextUtils;
import com.huawei.hms.android.HwBuildEx;
import com.huawei.hms.support.log.HMSLog;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.huawei.hms.push.f */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class NotificationIconUtil {
    /* renamed from: a */
    private static int m14272a(Context context) {
        int i = context.getApplicationInfo().icon;
        if (i == 0) {
            int identifier = context.getResources().getIdentifier("btn_star_big_on", "drawable", "android");
            HMSLog.m14115d("PushSelfShowLog", "icon is btn_star_big_on ");
            if (identifier == 0) {
                HMSLog.m14115d("PushSelfShowLog", "icon is sym_def_app_icon ");
                return 17301651;
            }
            return identifier;
        }
        return i;
    }

    /* renamed from: b */
    private static int m14269b(Context context, PushSelfShowMessage pushSelfShowMessage) {
        int i = 0;
        if (context != null && pushSelfShowMessage != null) {
            if (!TextUtils.isEmpty(pushSelfShowMessage.m14219m())) {
                String[] split = pushSelfShowMessage.m14219m().split("/");
                if (split.length == 3) {
                    i = ResourceLoader.m14195a(context, split[1], split[2]);
                }
            }
            if (i == 0) {
                i = ResourceLoader.m14196a(context, "com.huawei.messaging.default_notification_icon");
            }
            return i != 0 ? i : m14272a(context);
        }
        HMSLog.m14110i("PushSelfShowLog", "enter getSmallIconId, context or msg is null");
        return 0;
    }

    /* renamed from: a */
    public static Bitmap m14270a(Context context, PushSelfShowMessage pushSelfShowMessage) {
        if (context == null || pushSelfShowMessage == null) {
            return null;
        }
        try {
            if (HwBuildEx.VERSION.EMUI_SDK_INT >= 11) {
                HMSLog.m14110i("PushSelfShowLog", "huawei phone, and emui5.0, need not show large icon.");
                return null;
            } else if ("com.huawei.android.pushagent".equals(pushSelfShowMessage.m14222k())) {
                return null;
            } else {
                HMSLog.m14110i("PushSelfShowLog", "get left bitmap from " + pushSelfShowMessage.m14222k());
                return ((BitmapDrawable) context.getPackageManager().getApplicationIcon(pushSelfShowMessage.m14222k())).getBitmap();
            }
        } catch (PackageManager.NameNotFoundException unused) {
            HMSLog.m14112e("PushSelfShowLog", "build left icon occur NameNotFoundException.");
            return null;
        } catch (Exception unused2) {
            HMSLog.m14112e("PushSelfShowLog", "build left icon occur Exception.");
            return null;
        }
    }

    @TargetApi(23)
    /* renamed from: a */
    public static void m14271a(Context context, Notification.Builder builder, PushSelfShowMessage pushSelfShowMessage) {
        if (context != null && builder != null && pushSelfShowMessage != null) {
            builder.setSmallIcon(m14269b(context, pushSelfShowMessage));
        } else {
            HMSLog.m14112e("PushSelfShowLog", "msg is null");
        }
    }
}
