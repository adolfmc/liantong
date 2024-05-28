package com.xiaomi.push.service.receivers;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import com.xiaomi.mipush.sdk.C11087b;
import com.xiaomi.mipush.sdk.C11111p;
import com.xiaomi.mipush.sdk.C11118u;
import com.xiaomi.mipush.sdk.COSPushHelper;
import com.xiaomi.mipush.sdk.EnumC11090d;
import com.xiaomi.mipush.sdk.EnumC11125v;
import com.xiaomi.mipush.sdk.FTOSPushHelper;
import com.xiaomi.mipush.sdk.HWPushHelper;
import com.xiaomi.mipush.sdk.MiPushClient;
import com.xiaomi.push.C11169au;
import com.xiaomi.push.C11392fz;
import com.xiaomi.push.C11472m;
import com.xiaomi.push.service.ServiceClient;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class NetworkStatusReceiver extends BroadcastReceiver {

    /* renamed from: a */
    private static boolean f23743a;

    /* renamed from: b */
    private boolean f23744b;

    public NetworkStatusReceiver() {
        this.f23744b = false;
        this.f23744b = true;
    }

    public NetworkStatusReceiver(Object obj) {
        this.f23744b = false;
        f23743a = true;
    }

    /* renamed from: a */
    public static boolean m2410a() {
        return f23743a;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(final Context context, Intent intent) {
        if (this.f23744b) {
            return;
        }
        C11169au.m4854a();
        C11472m.m2950a().post(new Runnable() { // from class: com.xiaomi.push.service.receivers.NetworkStatusReceiver.1
            @Override // java.lang.Runnable
            public void run() {
                NetworkStatusReceiver.this.m2409a(context);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m2409a(Context context) {
        if (!C11118u.m5003a(context).m5008a() && C11087b.m5151a(context).m5136c() && !C11087b.m5151a(context).m5130f()) {
            try {
                Intent intent = new Intent();
                intent.setComponent(new ComponentName(context, "com.xiaomi.push.service.XMPushService"));
                intent.setAction("com.xiaomi.push.network_status_changed");
                ServiceClient.getInstance(context).startServiceSafely(intent);
            } catch (Exception e) {
                AbstractC11049b.m5276a(e);
            }
        }
        C11392fz.m3735a(context);
        if (C11169au.m4849a(context) && C11118u.m5003a(context).m4973b()) {
            C11118u.m5003a(context).m4969c();
        }
        if (C11169au.m4849a(context)) {
            if ("syncing".equals(C11111p.m5047a(context).m5046a(EnumC11125v.DISABLE_PUSH))) {
                MiPushClient.disablePush(context);
            }
            if ("syncing".equals(C11111p.m5047a(context).m5046a(EnumC11125v.ENABLE_PUSH))) {
                MiPushClient.enablePush(context);
            }
            if ("syncing".equals(C11111p.m5047a(context).m5046a(EnumC11125v.UPLOAD_HUAWEI_TOKEN))) {
                C11118u.m5003a(context).m4980a((String) null, EnumC11125v.UPLOAD_HUAWEI_TOKEN, EnumC11090d.ASSEMBLE_PUSH_HUAWEI, "net");
            }
            if ("syncing".equals(C11111p.m5047a(context).m5046a(EnumC11125v.UPLOAD_FCM_TOKEN))) {
                C11118u.m5003a(context).m4980a((String) null, EnumC11125v.UPLOAD_HUAWEI_TOKEN, EnumC11090d.ASSEMBLE_PUSH_HUAWEI, "net");
            }
            if ("syncing".equals(C11111p.m5047a(context).m5046a(EnumC11125v.UPLOAD_COS_TOKEN))) {
                C11118u.m5003a(context).m4980a((String) null, EnumC11125v.UPLOAD_COS_TOKEN, EnumC11090d.ASSEMBLE_PUSH_COS, "net");
            }
            if ("syncing".equals(C11111p.m5047a(context).m5046a(EnumC11125v.UPLOAD_FTOS_TOKEN))) {
                C11118u.m5003a(context).m4980a((String) null, EnumC11125v.UPLOAD_FTOS_TOKEN, EnumC11090d.ASSEMBLE_PUSH_FTOS, "net");
            }
            if (HWPushHelper.needConnect() && HWPushHelper.shouldTryConnect(context)) {
                HWPushHelper.setConnectTime(context);
                HWPushHelper.registerHuaWeiAssemblePush(context);
            }
            COSPushHelper.doInNetworkChange(context);
            FTOSPushHelper.doInNetworkChange(context);
        }
    }
}
