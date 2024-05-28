package com.huawei.hms.support.api.push;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.huawei.hms.android.HwBuildEx;
import com.huawei.hms.push.NotificationUtil;
import com.huawei.hms.push.SelfShow;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.utils.ResourceLoaderUtil;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class PushMsgReceiver extends BroadcastReceiver {
    /* renamed from: a */
    private static void m14147a(Context context, Intent intent) {
        if (intent.hasExtra("selfshow_info")) {
            if (!NotificationUtil.m14268a(context)) {
                HMSLog.m14110i("PushMsgReceiver", context.getPackageName() + " disable display notification.");
                if (!intent.hasExtra("selfshow_event_id")) {
                    return;
                }
            }
            SelfShow.m14194a(context, intent);
        }
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent == null || context == null) {
            return;
        }
        HMSLog.m14110i("PushMsgReceiver", "push receive broadcast message, Intent:" + intent.getAction() + " pkgName:" + context.getPackageName());
        try {
            intent.getStringExtra("TestIntent");
            String action = intent.getAction();
            if (ResourceLoaderUtil.getmContext() == null) {
                ResourceLoaderUtil.setmContext(context.getApplicationContext());
            }
            if (!"com.huawei.intent.action.PUSH_DELAY_NOTIFY".equals(action) && (!"com.huawei.intent.action.PUSH".equals(action) || HwBuildEx.VERSION.EMUI_SDK_INT >= 10)) {
                HMSLog.m14110i("PushMsgReceiver", "message can't be recognised.");
            } else {
                m14147a(context, intent);
            }
        } catch (Exception unused) {
            HMSLog.m14112e("PushMsgReceiver", "intent has some error");
        }
    }
}
