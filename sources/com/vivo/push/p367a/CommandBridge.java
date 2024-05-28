package com.vivo.push.p367a;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import com.vivo.push.BundleWapper;
import com.vivo.push.IPCManager;
import com.vivo.push.PushCommand;
import com.vivo.push.p372e.PushSecurityManager;
import com.vivo.push.util.AESParseManager;
import com.vivo.push.util.LogUtil;
import com.vivo.push.util.PushPackageUtils;
import com.vivo.push.util.RSAUtils;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.vivo.push.a.a */
/* loaded from: E:\11617560_dexfile_execute.dex */
public final class CommandBridge {
    /* renamed from: a */
    public static void m5815a(Context context, String str, PushCommand pushCommand) {
        boolean mo5323c = pushCommand.mo5323c();
        IPCManager m5658a = IPCManager.m5658a(context, mo5323c ? "com.vivo.vms.upstageservice" : "com.vivo.vms.aidlservice");
        boolean m5660a = m5658a.m5660a();
        if (TextUtils.isEmpty(pushCommand.m5330a())) {
            pushCommand.m5327a(context.getPackageName());
        }
        if (m5660a && !"com.vivo.pushservice".equals(context.getPackageName())) {
            BundleWapper bundleWapper = new BundleWapper(pushCommand.m5330a(), str, new Bundle());
            pushCommand.m5328a(bundleWapper);
            if (m5658a.m5657a(bundleWapper.m5726b())) {
                return;
            }
            LogUtil.m5346b("CommandBridge", "send command error by aidl");
            LogUtil.m5343c(context, "send command error by aidl");
        }
        Intent intent = new Intent("com.vivo.pushservice.action.METHOD");
        intent.setPackage(str);
        intent.setClassName(str, mo5323c ? "com.vivo.push.sdk.service.UpstageService" : "com.vivo.push.sdk.service.PushService");
        pushCommand.m5329a(intent);
        try {
            m5817a(context, intent);
        } catch (Exception e) {
            LogUtil.m5353a("CommandBridge", "CommandBridge startService exception: ", e);
        }
    }

    /* renamed from: a */
    public static void m5817a(Context context, Intent intent) throws Exception {
        if (context == null) {
            LogUtil.m5341d("CommandBridge", "enter startService context is null");
            throw new Exception("context is null");
        }
        try {
            context.startService(intent);
        } catch (Exception e) {
            LogUtil.m5353a("CommandBridge", "start service error", e);
            intent.setComponent(null);
            context.sendBroadcast(intent);
        }
    }

    /* renamed from: a */
    public static void m5816a(Context context, PushCommand pushCommand, String str) {
        try {
            boolean m5463d = PushPackageUtils.m5463d(context, str);
            String str2 = m5463d ? "com.vivo.pushservice.action.RECEIVE" : "com.vivo.pushclient.action.RECEIVE";
            if (TextUtils.isEmpty(str)) {
                LogUtil.m5343c(context, "消息接受者包名为空！");
                throw new Exception("消息接受者包名为空！");
            } else if (m5463d || m5814a(context, str2, str)) {
                if (TextUtils.isEmpty(pushCommand.m5330a())) {
                    pushCommand.m5327a(context.getPackageName());
                }
                Intent intent = new Intent();
                intent.setFlags(1048576);
                if (!TextUtils.isEmpty(str2)) {
                    intent.setAction(str2);
                }
                intent.setPackage(str);
                intent.setClassName(str, m5463d ? "com.vivo.push.sdk.service.CommandService" : "com.vivo.push.sdk.service.CommandClientService");
                intent.putExtra("security_avoid_pull", AESParseManager.m5476a(context).m5475a("com.vivo.pushservice"));
                pushCommand.m5325b(intent);
                intent.putExtra("command_type", "reflect_receiver");
                if (Build.VERSION.SDK_INT >= 18) {
                    intent.putExtra("security_avoid_pull_rsa", PushSecurityManager.m5714a().m5713a(context).mo5710a("com.vivo.pushservice"));
                    intent.putExtra("security_avoid_rsa_public_key", RSAUtils.m5458a(PushSecurityManager.m5714a().m5713a(context).mo5712a()));
                }
                m5817a(context, intent);
            }
        } catch (Exception e) {
            LogUtil.m5353a("CommandBridge", "CommandBridge sendCommandToClient exception", e);
        }
    }

    /* renamed from: a */
    private static boolean m5814a(Context context, String str, String str2) {
        Intent intent = new Intent(str);
        intent.setPackage(str2);
        try {
            List<ResolveInfo> queryBroadcastReceivers = context.getPackageManager().queryBroadcastReceivers(intent, 576);
            if (queryBroadcastReceivers != null && queryBroadcastReceivers.size() > 0) {
                return true;
            }
            LogUtil.m5346b("CommandBridge", "action check error：action>>" + str + ";pkgname>>" + str2);
            return false;
        } catch (Exception unused) {
            LogUtil.m5346b("CommandBridge", "queryBroadcastReceivers error");
            return false;
        }
    }
}
