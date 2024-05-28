package com.sinovatech.unicom.separatemodule.push.vivo;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;
import com.sinovatech.unicom.common.DeviceHelper;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.push.PushManager;
import com.vivo.push.IPushActionListener;
import com.vivo.push.PushClient;
import com.vivo.push.PushConfig;
import com.vivo.push.listener.IPushQueryActionListener;
import com.vivo.push.util.VivoPushException;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class VivoPushManager {
    public static final String TAG = "VivoPushManager";
    private static VivoPushManager vivoPushManager;

    public static VivoPushManager getInstance() {
        if (vivoPushManager == null) {
            synchronized (VivoPushManager.class) {
                if (vivoPushManager == null) {
                    vivoPushManager = new VivoPushManager();
                }
            }
        }
        return vivoPushManager;
    }

    public void initSDK(Application application) {
        try {
            if (PushClient.getInstance(application).isSupport()) {
                PushClient.getInstance(application).initialize(new PushConfig.Builder().agreePrivacyStatement(true).build());
            } else {
                MsLogUtil.m7979d(TAG, "当前设备不支持推送");
            }
        } catch (VivoPushException e) {
            e.printStackTrace();
        }
    }

    public String getToken(Context context) {
        PushClient.getInstance(context).getRegId(new IPushQueryActionListener() { // from class: com.sinovatech.unicom.separatemodule.push.vivo.VivoPushManager.1
            @Override // com.vivo.push.listener.IPushRequestListener
            public void onSuccess(String str) {
                MsLogUtil.m7979d(VivoPushManager.TAG, "getRegId" + str);
                if (TextUtils.isEmpty(str)) {
                    return;
                }
                PushManager.getInstance().setPushToken(str);
            }

            @Override // com.vivo.push.listener.IPushRequestListener
            public void onFail(Integer num) {
                MsLogUtil.m7979d(VivoPushManager.TAG, "获取推送失败" + num);
            }
        });
        return "";
    }

    public void enablePush(boolean z, Context context) {
        if (DeviceHelper.isVivo()) {
            if (z) {
                PushClient.getInstance(context).turnOnPush(new IPushActionListener() { // from class: com.sinovatech.unicom.separatemodule.push.vivo.VivoPushManager.2
                    @Override // com.vivo.push.IPushActionListener
                    public void onStateChanged(int i) {
                        MsLogUtil.m7979d(VivoPushManager.TAG, i + "on");
                    }
                });
            } else {
                PushClient.getInstance(context).turnOffPush(new IPushActionListener() { // from class: com.sinovatech.unicom.separatemodule.push.vivo.VivoPushManager.3
                    @Override // com.vivo.push.IPushActionListener
                    public void onStateChanged(int i) {
                        MsLogUtil.m7979d(VivoPushManager.TAG, i + "off");
                    }
                });
            }
        }
    }
}
