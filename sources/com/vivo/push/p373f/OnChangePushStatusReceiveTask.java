package com.vivo.push.p373f;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.text.TextUtils;
import com.vivo.push.PushClientTask;
import com.vivo.push.PushCommand;
import com.vivo.push.p368b.OnChangePushStatusReceiveCommand;
import com.vivo.push.util.LogUtil;
import com.vivo.push.util.PushPackageUtils;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.vivo.push.f.j */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class OnChangePushStatusReceiveTask extends PushClientTask {
    /* JADX INFO: Access modifiers changed from: package-private */
    public OnChangePushStatusReceiveTask(PushCommand pushCommand) {
        super(pushCommand);
    }

    /* renamed from: a */
    public static boolean m5685a(Context context) {
        Intent intent = new Intent("com.vivo.pushservice.action.PUSH_SERVICE");
        intent.setPackage(context.getPackageName());
        List<ResolveInfo> queryIntentServices = context.getPackageManager().queryIntentServices(intent, 576);
        if (queryIntentServices == null || queryIntentServices.size() <= 0) {
            LogUtil.m5354a("OnChangePushStatusTask", "enableService error: can not find push service.");
            return false;
        }
        PackageManager packageManager = context.getPackageManager();
        ComponentName componentName = new ComponentName(context, queryIntentServices.get(0).serviceInfo.name);
        if (packageManager.getComponentEnabledSetting(componentName) != 1) {
            packageManager.setComponentEnabledSetting(componentName, 1, 1);
            LogUtil.m5341d("OnChangePushStatusTask", "enableService push service.");
            return true;
        }
        LogUtil.m5341d("OnChangePushStatusTask", "push service has enabled");
        return false;
    }

    /* renamed from: b */
    public static boolean m5684b(Context context) {
        Intent intent = new Intent("com.vivo.pushservice.action.PUSH_SERVICE");
        intent.setPackage(context.getPackageName());
        List<ResolveInfo> queryIntentServices = context.getPackageManager().queryIntentServices(intent, 576);
        if (queryIntentServices == null || queryIntentServices.size() <= 0) {
            LogUtil.m5354a("OnChangePushStatusTask", "disableService error: can not find push service.");
            return false;
        }
        PackageManager packageManager = context.getPackageManager();
        ComponentName componentName = new ComponentName(context, queryIntentServices.get(0).serviceInfo.name);
        if (packageManager.getComponentEnabledSetting(componentName) != 2) {
            packageManager.setComponentEnabledSetting(componentName, 2, 1);
            LogUtil.m5341d("OnChangePushStatusTask", "disableService push service.");
            return true;
        }
        LogUtil.m5341d("OnChangePushStatusTask", "push service has disabled");
        return false;
    }

    /* renamed from: c */
    private static List<ResolveInfo> m5683c(Context context) {
        List<ResolveInfo> list;
        Intent intent = new Intent("com.vivo.pushservice.action.RECEIVE");
        intent.setPackage(context.getPackageName());
        try {
            list = context.getPackageManager().queryBroadcastReceivers(intent, 576);
        } catch (Exception unused) {
            list = null;
        }
        if (list == null || list.size() <= 0) {
            Intent intent2 = new Intent("com.vivo.pushclient.action.RECEIVE");
            intent2.setPackage(context.getPackageName());
            try {
                return context.getPackageManager().queryBroadcastReceivers(intent2, 576);
            } catch (Exception unused2) {
                return list;
            }
        }
        return list;
    }

    @Override // com.vivo.push.PushClientTask
    /* renamed from: a */
    public final void mo5492a(PushCommand pushCommand) {
        if (this.f21149a.getPackageName().equals(PushPackageUtils.m5472a(this.f21149a))) {
            return;
        }
        OnChangePushStatusReceiveCommand onChangePushStatusReceiveCommand = (OnChangePushStatusReceiveCommand) pushCommand;
        int m5798d = onChangePushStatusReceiveCommand.m5798d();
        int m5797e = onChangePushStatusReceiveCommand.m5797e();
        LogUtil.m5341d("OnChangePushStatusTask", "OnChangePushStatusTask serviceStatus is " + m5798d + " ; receiverStatus is " + m5797e);
        if (m5798d == 2) {
            m5684b(this.f21149a);
        } else if (m5798d == 1) {
            m5685a(this.f21149a);
        } else if (m5798d == 0) {
            Context context = this.f21149a;
            Intent intent = new Intent("com.vivo.pushservice.action.PUSH_SERVICE");
            intent.setPackage(context.getPackageName());
            List<ResolveInfo> queryIntentServices = context.getPackageManager().queryIntentServices(intent, 576);
            if (queryIntentServices == null || queryIntentServices.size() <= 0) {
                LogUtil.m5354a("OnChangePushStatusTask", "defaultService error: can not find push service.");
            } else {
                PackageManager packageManager = context.getPackageManager();
                ComponentName componentName = new ComponentName(context, queryIntentServices.get(0).serviceInfo.name);
                if (packageManager.getComponentEnabledSetting(componentName) != 0) {
                    packageManager.setComponentEnabledSetting(componentName, 0, 1);
                    LogUtil.m5341d("OnChangePushStatusTask", "defaultService push service.");
                } else {
                    LogUtil.m5341d("OnChangePushStatusTask", "push service has defaulted");
                }
            }
        }
        if (m5797e == 2) {
            Context context2 = this.f21149a;
            List<ResolveInfo> m5683c = m5683c(context2);
            if (m5683c == null || m5683c.size() <= 0) {
                LogUtil.m5354a("OnChangePushStatusTask", "disableReceiver error: can not find push service.");
                return;
            }
            String str = m5683c.get(0).activityInfo.name;
            if (TextUtils.isEmpty(str)) {
                LogUtil.m5341d("OnChangePushStatusTask", "disableReceiver error: className is null. ");
                return;
            }
            PackageManager packageManager2 = context2.getPackageManager();
            ComponentName componentName2 = new ComponentName(context2, str);
            if (packageManager2.getComponentEnabledSetting(componentName2) != 2) {
                packageManager2.setComponentEnabledSetting(componentName2, 2, 1);
                LogUtil.m5341d("OnChangePushStatusTask", "push service disableReceiver ");
                return;
            }
            LogUtil.m5341d("OnChangePushStatusTask", "push service has disableReceiver ");
        } else if (m5797e == 1) {
            Context context3 = this.f21149a;
            List<ResolveInfo> m5683c2 = m5683c(context3);
            if (m5683c2 == null || m5683c2.size() <= 0) {
                LogUtil.m5354a("OnChangePushStatusTask", "enableReceiver error: can not find push service.");
                return;
            }
            String str2 = m5683c2.get(0).activityInfo.name;
            if (TextUtils.isEmpty(str2)) {
                LogUtil.m5341d("OnChangePushStatusTask", "enableReceiver error: className is null. ");
                return;
            }
            PackageManager packageManager3 = context3.getPackageManager();
            ComponentName componentName3 = new ComponentName(context3, str2);
            if (packageManager3.getComponentEnabledSetting(componentName3) != 1) {
                packageManager3.setComponentEnabledSetting(componentName3, 1, 1);
                LogUtil.m5341d("OnChangePushStatusTask", "push service enableReceiver ");
                return;
            }
            LogUtil.m5341d("OnChangePushStatusTask", "push service has enableReceiver ");
        } else if (m5797e == 0) {
            Context context4 = this.f21149a;
            List<ResolveInfo> m5683c3 = m5683c(context4);
            if (m5683c3 == null || m5683c3.size() <= 0) {
                LogUtil.m5354a("OnChangePushStatusTask", "defaultReceiver error: can not find push service.");
                return;
            }
            String str3 = m5683c3.get(0).activityInfo.name;
            if (TextUtils.isEmpty(str3)) {
                LogUtil.m5341d("OnChangePushStatusTask", "defaultReceiver error: className is null. ");
                return;
            }
            PackageManager packageManager4 = context4.getPackageManager();
            ComponentName componentName4 = new ComponentName(context4, str3);
            if (packageManager4.getComponentEnabledSetting(componentName4) != 0) {
                packageManager4.setComponentEnabledSetting(componentName4, 0, 1);
                LogUtil.m5341d("OnChangePushStatusTask", "push service defaultReceiver ");
                return;
            }
            LogUtil.m5341d("OnChangePushStatusTask", "push service has defaulted");
        }
    }
}
