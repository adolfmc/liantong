package cn.sharesdk.framework.utils;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: cn.sharesdk.framework.utils.d */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class PendingIntentUtils {
    /* renamed from: a */
    public static PendingIntent m21694a(Context context, int i, Intent intent, int i2) {
        if (Build.VERSION.SDK_INT >= 23) {
            return PendingIntent.getBroadcast(context, i, intent, 67108864);
        }
        return PendingIntent.getBroadcast(context, i, intent, i2);
    }
}
