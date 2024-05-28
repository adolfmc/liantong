package com.sinovatech.unicom.separatemodule.push;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Process;
import android.text.TextUtils;
import com.heytap.msp.push.mode.DataMessage;
import com.huawei.hms.push.RemoteMessage;
import com.sinovatech.unicom.common.DeviceHelper;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.push.huawei.HmsManager;
import com.sinovatech.unicom.separatemodule.push.oppo.OppoPushManager;
import com.sinovatech.unicom.separatemodule.push.vivo.VivoPushManager;
import com.sinovatech.unicom.separatemodule.push.xiaomi.XiaoMiPushManager;
import com.vivo.push.model.UPSNotificationMessage;
import com.xiaomi.mipush.sdk.MiPushMessage;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class PushManager {
    private static PushManager pushManager;
    public final String TAG = getClass().getSimpleName();

    private PushManager() {
    }

    public static PushManager getInstance() {
        if (pushManager == null) {
            pushManager = new PushManager();
        }
        return pushManager;
    }

    public void init(Application application) {
        try {
            if (shouldInit(application) && DeviceHelper.isXIAOMI()) {
                XiaoMiPushManager.getInstance().initXiaoMiPush(application);
            } else if (DeviceHelper.isHuawei()) {
                MsLogUtil.m7979d(this.TAG, "---华为---");
            } else if (DeviceHelper.isVivo()) {
                VivoPushManager.getInstance().initSDK(application);
            } else if (DeviceHelper.isOppo()) {
                OppoPushManager.getInstance().initOppoSDk(application);
            }
        } catch (Exception e) {
            e.printStackTrace();
            String str = this.TAG;
            MsLogUtil.m7979d(str, "初始化推送SDK异常\n" + e.getMessage());
        }
    }

    public String getToken(Context context) {
        String string = App.getSharePreferenceUtil().getString("platformToken");
        try {
            if (TextUtils.isEmpty(string)) {
                if (DeviceHelper.isXIAOMI()) {
                    string = XiaoMiPushManager.getInstance().getToken(context);
                } else if (DeviceHelper.isHuawei()) {
                    string = HmsManager.getInstance().getHmsToken(context);
                } else if (DeviceHelper.isVivo()) {
                    string = VivoPushManager.getInstance().getToken(context);
                } else if (DeviceHelper.isOppo()) {
                    string = OppoPushManager.getInstance().getToken();
                } else {
                    MsLogUtil.m7979d(this.TAG, "获取token，无对应设备");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String str = this.TAG;
        MsLogUtil.m7979d(str, "推送token\n" + string);
        return string;
    }

    private boolean shouldInit(Application application) {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) application.getSystemService("activity")).getRunningAppProcesses();
        String str = application.getApplicationInfo().processName;
        int myPid = Process.myPid();
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            if (runningAppProcessInfo.pid == myPid && str.equals(runningAppProcessInfo.processName)) {
                return true;
            }
        }
        return false;
    }

    public void setPushToken(String str) {
        App.getSharePreferenceUtil().putString("platformToken", str);
    }

    public void pushSwitch(Context context, boolean z) {
        if (DeviceHelper.isHuawei()) {
            HmsManager.getInstance().enableReceiveNotifyMsg(z, context);
            String str = this.TAG;
            MsLogUtil.m7979d(str, "华为设备推送开关" + z);
        } else if (DeviceHelper.isXIAOMI()) {
            XiaoMiPushManager.getInstance().enablePush(z, context);
            String str2 = this.TAG;
            MsLogUtil.m7979d(str2, "小米设备推送开关" + z);
        } else if (DeviceHelper.isVivo()) {
            VivoPushManager.getInstance().enablePush(z, context);
        } else if (DeviceHelper.isOppo()) {
            OppoPushManager.getInstance().enablePush(z, context);
        } else {
            MsLogUtil.m7979d(this.TAG, "无对应设备");
        }
    }

    public void clickMessage(Object obj, Context context) {
        try {
            if (DeviceHelper.getNeedHuawei()) {
                RemoteMessage remoteMessage = (RemoteMessage) obj;
                MsLogUtil.m7979d(this.TAG, "onMessageReceived is called");
                if (remoteMessage == null) {
                    MsLogUtil.m7979d(this.TAG, "Received message entity is null!");
                    return;
                }
                String str = this.TAG;
                MsLogUtil.m7979d(str, "get Data: " + remoteMessage.getData() + "\n getFrom: " + remoteMessage.getFrom() + "\n getTo: " + remoteMessage.getTo() + "\n getMessageId: " + remoteMessage.getMessageId() + "\n getSentTime: " + remoteMessage.getSentTime() + "\n getDataMap: " + remoteMessage.getDataOfMap() + "\n getMessageType: " + remoteMessage.getMessageType() + "\n getTtl: " + remoteMessage.getTtl() + "\n getClickAction: " + remoteMessage.getNotification().getClickAction() + "\n getToken: " + remoteMessage.getToken());
                String clickAction = remoteMessage.getNotification().getClickAction();
                if (TextUtils.isEmpty(clickAction) || !clickAction.startsWith("{")) {
                    return;
                }
                context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(new JSONObject(clickAction).optString("intent"))));
            } else if (DeviceHelper.isXIAOMI()) {
                Map<String, String> extra = ((MiPushMessage) obj).getExtra();
                if (extra == null || extra.size() <= 0) {
                    return;
                }
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(extra.get("intent_uri")));
                intent.addFlags(67108864);
                context.startActivity(intent);
            } else if (DeviceHelper.isVivo()) {
                UPSNotificationMessage uPSNotificationMessage = (UPSNotificationMessage) obj;
            } else if (DeviceHelper.isOppo()) {
                ((DataMessage) obj).getDataExtra();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
