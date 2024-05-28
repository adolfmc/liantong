package com.xiaomi.mipush.sdk;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import com.xiaomi.mipush.sdk.MessageHandleService;
import com.xiaomi.push.C11305dt;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public abstract class PushMessageReceiver extends BroadcastReceiver {
    public void onCommandResult(Context context, MiPushCommandMessage miPushCommandMessage) {
    }

    public void onNotificationMessageArrived(Context context, MiPushMessage miPushMessage) {
    }

    public void onNotificationMessageClicked(Context context, MiPushMessage miPushMessage) {
    }

    @Deprecated
    public void onReceiveMessage(Context context, MiPushMessage miPushMessage) {
    }

    public void onReceivePassThroughMessage(Context context, MiPushMessage miPushMessage) {
    }

    public void onReceiveRegisterResult(Context context, MiPushCommandMessage miPushCommandMessage) {
    }

    public void onRequirePermissions(Context context, String[] strArr) {
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        if (intent != null) {
            AbstractC11049b.m5266e("[CRcv] receive a msg broadcast: " + intent.getAction());
        }
        MessageHandleService.addJob(context.getApplicationContext(), new MessageHandleService.C11070a(intent, this));
        try {
            int intExtra = intent.getIntExtra("eventMessageType", -1);
            if (intExtra == 2000) {
                C11305dt.m4117a(context.getApplicationContext()).m4114a(context.getPackageName(), intent, 2003, (String) null);
            } else if (intExtra == 6000) {
                C11305dt.m4117a(context.getApplicationContext()).m4114a(context.getPackageName(), intent, 6005, (String) null);
            }
        } catch (Exception e) {
            AbstractC11049b.m5268d("meet error in PushMessageReceiver. " + e);
        }
    }
}
