package com.xiaomi.push;

import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.xiaomi.push.ad */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C11133ad {
    /* renamed from: a */
    public static boolean m4939a(Context context) {
        try {
            return ((KeyguardManager) context.getSystemService("keyguard")).inKeyguardRestrictedInputMode();
        } catch (Exception e) {
            AbstractC11049b.m5276a(e);
            return false;
        }
    }

    /* renamed from: b */
    public static boolean m4938b(Context context) {
        Intent intent = null;
        try {
            intent = C11472m.m2947a(context, (BroadcastReceiver) null, new IntentFilter("android.intent.action.BATTERY_CHANGED"), (String) null, (Handler) null);
        } catch (Exception unused) {
        }
        if (intent == null) {
            return false;
        }
        int intExtra = intent.getIntExtra("status", -1);
        return intExtra == 2 || intExtra == 5;
    }
}
