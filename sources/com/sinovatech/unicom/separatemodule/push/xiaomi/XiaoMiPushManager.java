package com.sinovatech.unicom.separatemodule.push.xiaomi;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;
import com.sinovatech.unicom.common.DeviceHelper;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.push.PushManager;
import com.xiaomi.channel.commonutils.logger.LoggerInterface;
import com.xiaomi.mipush.sdk.Logger;
import com.xiaomi.mipush.sdk.MiPushClient;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class XiaoMiPushManager {
    private static XiaoMiPushManager xiaoMiPushManager;
    public final String TAG = getClass().getSimpleName();

    private XiaoMiPushManager() {
    }

    public static XiaoMiPushManager getInstance() {
        if (xiaoMiPushManager == null) {
            synchronized (XiaoMiPushManager.class) {
                if (xiaoMiPushManager == null) {
                    xiaoMiPushManager = new XiaoMiPushManager();
                }
            }
        }
        return xiaoMiPushManager;
    }

    public void initXiaoMiPush(Application application) {
        if (DeviceHelper.isXIAOMI()) {
            MiPushClient.registerPush(application, "2882303761517191369", "5261719120369");
            MsLogUtil.m7979d(this.TAG, "小米手机，初始化小米推送SDK");
            Logger.setLogger(application, new LoggerInterface() { // from class: com.sinovatech.unicom.separatemodule.push.xiaomi.XiaoMiPushManager.1
                @Override // com.xiaomi.channel.commonutils.logger.LoggerInterface
                public void setTag(String str) {
                }

                @Override // com.xiaomi.channel.commonutils.logger.LoggerInterface
                public void log(String str, Throwable th) {
                    String str2 = XiaoMiPushManager.this.TAG;
                    MsLogUtil.m7979d(str2, str + "\n" + th.getMessage());
                }

                @Override // com.xiaomi.channel.commonutils.logger.LoggerInterface
                public void log(String str) {
                    MsLogUtil.m7979d(XiaoMiPushManager.this.TAG, str);
                }
            });
            return;
        }
        MsLogUtil.m7979d(this.TAG, "不是小米手机，不初始化小米推送SDK");
    }

    public String getToken(Context context) {
        String str = "";
        try {
            str = MiPushClient.getRegId(context);
            if (!TextUtils.isEmpty(str)) {
                PushManager.getInstance().setPushToken(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    public void enablePush(boolean z, Context context) {
        if (DeviceHelper.isXIAOMI()) {
            if (z) {
                MiPushClient.enablePush(context);
            } else {
                MiPushClient.disablePush(context);
            }
        }
    }
}
