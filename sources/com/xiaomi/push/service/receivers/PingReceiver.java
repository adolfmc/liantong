package com.xiaomi.push.service.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import com.xiaomi.push.C11311dz;
import com.xiaomi.push.service.AbstractC11555an;
import com.xiaomi.push.service.ServiceClient;
import com.xiaomi.push.service.XMPushService;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class PingReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        AbstractC11049b.m5270c(intent.getPackage() + " is the package name");
        if (XMPushService.m2842e()) {
            return;
        }
        if (AbstractC11555an.f23591q.equals(intent.getAction())) {
            if (TextUtils.equals(context.getPackageName(), intent.getPackage())) {
                AbstractC11049b.m5270c("Ping XMChannelService on timer");
                try {
                    Intent intent2 = new Intent(context, XMPushService.class);
                    intent2.putExtra("time_stamp", System.currentTimeMillis());
                    intent2.setAction("com.xiaomi.push.timer");
                    ServiceClient.getInstance(context).startServiceSafely(intent2);
                    return;
                } catch (Exception e) {
                    AbstractC11049b.m5276a(e);
                    return;
                }
            }
            return;
        }
        AbstractC11049b.m5282a("cancel the old ping timer");
        C11311dz.m4068a();
    }
}
