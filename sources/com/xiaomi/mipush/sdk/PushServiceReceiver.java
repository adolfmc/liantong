package com.xiaomi.mipush.sdk;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class PushServiceReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        Intent intent2 = new Intent(context, PushMessageHandler.class);
        intent2.putExtras(intent);
        intent2.setAction(intent.getAction());
        PushMessageHandler.m5175a(context, intent2);
    }
}
