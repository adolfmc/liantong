package com.huawei.hms.support.api.push.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import com.huawei.hms.push.BundleUtil;
import com.huawei.hms.push.NotificationUtil;
import com.huawei.hms.push.SelfShow;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.utils.HMSPackageManager;
import com.huawei.hms.utils.PackageManagerHelper;
import com.huawei.hms.utils.ResourceLoaderUtil;
import java.util.Objects;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class HmsMsgService extends Service {

    /* renamed from: com.huawei.hms.support.api.push.service.HmsMsgService$a */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    static class HandlerC5072a extends Handler {

        /* renamed from: a */
        private Context f11733a;

        HandlerC5072a(Context context) {
            this.f11733a = context;
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (Build.VERSION.SDK_INT < 21) {
                return;
            }
            Bundle data = message.getData();
            if (Objects.equals(this.f11733a.getApplicationContext().getPackageManager().getNameForUid(message.sendingUid), HMSPackageManager.getInstance(this.f11733a).getHMSPackageName()) && data != null) {
                if (HMSPackageManager.getInstance(this.f11733a).getHMSPackageStates() != PackageManagerHelper.PackageStates.ENABLED) {
                    HMSLog.m14110i("HmsMsgService", "service not start by hms");
                } else {
                    HMSLog.m14110i("HmsMsgService", "chose push type");
                    if (Objects.equals(BundleUtil.m14298b(data, "push_action"), "com.huawei.push.msg.NOTIFY_MSG")) {
                        if (ResourceLoaderUtil.getmContext() == null) {
                            ResourceLoaderUtil.setmContext(this.f11733a.getApplicationContext());
                        }
                        HMSLog.m14110i("HmsMsgService", "invokeSelfShow");
                        HmsMsgService.m14137c(this.f11733a, data);
                    } else if (Objects.equals(BundleUtil.m14298b(data, "push_action"), "com.huawei.push.msg.PASSBY_MSG")) {
                        HMSLog.m14110i("HmsMsgService", "sendBroadcastToHms");
                        HmsMsgService.m14136d(this.f11733a, data);
                    }
                }
            }
            super.handleMessage(message);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public static void m14137c(Context context, Bundle bundle) {
        if (!NotificationUtil.m14268a(context)) {
            HMSLog.m14110i("HmsMsgService", context.getPackageName() + " disable display notification.");
            return;
        }
        Intent intent = new Intent();
        intent.setAction("com.huawei.push.msg.NOTIFY_MSG");
        intent.putExtra("selfshow_info", BundleUtil.m14299a(bundle, "selfshow_info"));
        intent.putExtra("selfshow_token", BundleUtil.m14299a(bundle, "selfshow_token"));
        intent.setPackage(BundleUtil.m14297c(bundle, "push_package"));
        SelfShow.m14194a(context, intent);
        HMSLog.m14110i("HmsMsgService", "invokeSelfShow done");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: d */
    public static void m14136d(Context context, Bundle bundle) {
        try {
            Intent intent = new Intent();
            intent.setAction("com.huawei.android.push.intent.RECEIVE");
            intent.putExtra("msg_data", BundleUtil.m14299a(bundle, "msg_data"));
            intent.putExtra("device_token", BundleUtil.m14299a(bundle, "device_token"));
            intent.putExtra("msgIdStr", BundleUtil.m14297c(bundle, "msgIdStr"));
            intent.setFlags(32);
            intent.setPackage(BundleUtil.m14297c(bundle, "push_package"));
            context.sendBroadcast(intent, context.getPackageName() + ".permission.PROCESS_PUSH_MSG");
            HMSLog.m14110i("HmsMsgService", "send broadcast passby done");
        } catch (SecurityException unused) {
            HMSLog.m14110i("HmsMsgService", "send broadcast SecurityException");
        } catch (Exception unused2) {
            HMSLog.m14110i("HmsMsgService", "send broadcast Exception");
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        HMSLog.m14110i("HmsMsgService", "onBind");
        return new Messenger(new HandlerC5072a(this)).getBinder();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        HMSLog.m14110i("HmsMsgService", "Enter onStartCommand.");
        return 2;
    }
}
