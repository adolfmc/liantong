package com.vivo.push.p373f;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.text.TextUtils;
import android.util.Base64;
import com.vivo.push.PushClientManager;
import com.vivo.push.PushClientTask;
import com.vivo.push.PushCommand;
import com.vivo.push.model.NotifyArriveCallbackByUser;
import com.vivo.push.sdk.PushMessageCallback;
import com.vivo.push.util.LogUtil;
import com.vivo.push.util.RSAUtils;
import java.security.PublicKey;

/* renamed from: com.vivo.push.f.aa */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public abstract class OnReceiveTask extends PushClientTask {

    /* renamed from: b */
    protected PushMessageCallback f20972b;

    /* renamed from: c */
    private int f20973c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public OnReceiveTask(PushCommand pushCommand) {
        super(pushCommand);
        this.f20973c = 0;
    }

    /* renamed from: a */
    public final void m5700a(PushMessageCallback pushMessageCallback) {
        this.f20972b = pushMessageCallback;
    }

    /* renamed from: a */
    public final boolean m5699a(PublicKey publicKey, String str, String str2) {
        if (!PushClientManager.m5648a().m5618d()) {
            LogUtil.m5341d("OnVerifyCallBackCommand", "vertify is not support , vertify is ignore");
            return true;
        } else if (publicKey == null) {
            LogUtil.m5341d("OnVerifyCallBackCommand", "vertify key is null");
            return false;
        } else if (TextUtils.isEmpty(str)) {
            LogUtil.m5341d("OnVerifyCallBackCommand", "contentTag is null");
            return false;
        } else if (!TextUtils.isEmpty(str2)) {
            try {
                LogUtil.m5341d("OnVerifyCallBackCommand", str.hashCode() + " = " + str2);
                if (RSAUtils.m5457a(str.getBytes("UTF-8"), publicKey, Base64.decode(str2, 2))) {
                    LogUtil.m5341d("OnVerifyCallBackCommand", "vertify id is success");
                    return true;
                }
                LogUtil.m5341d("OnVerifyCallBackCommand", "vertify fail srcDigest is ".concat(String.valueOf(str)));
                LogUtil.m5343c(this.f21149a, "vertify fail srcDigest is ".concat(String.valueOf(str)));
                return false;
            } catch (Exception e) {
                e.printStackTrace();
                LogUtil.m5341d("OnVerifyCallBackCommand", "vertify exception");
                return false;
            }
        } else {
            LogUtil.m5341d("OnVerifyCallBackCommand", "vertify id is null");
            return false;
        }
    }

    /* renamed from: b */
    public final int m5698b() {
        if (Build.VERSION.SDK_INT >= 24) {
            NotificationManager notificationManager = (NotificationManager) this.f21149a.getSystemService("notification");
            if (notificationManager == null || notificationManager.areNotificationsEnabled()) {
                if (Build.VERSION.SDK_INT < 26 || notificationManager == null) {
                    return 0;
                }
                try {
                    NotificationChannel notificationChannel = notificationManager.getNotificationChannel("vivo_push_channel");
                    if (notificationChannel != null) {
                        return notificationChannel.getImportance() == 0 ? 2121 : 0;
                    }
                    return 0;
                } catch (Exception unused) {
                    LogUtil.m5346b("OnVerifyCallBackCommand", "判断通知通道出现系统错误");
                    return 0;
                }
            }
            return 2104;
        }
        return 0;
    }

    /* renamed from: a */
    public final int m5701a(NotifyArriveCallbackByUser notifyArriveCallbackByUser) {
        if (notifyArriveCallbackByUser == null) {
            LogUtil.m5346b("OnVerifyCallBackCommand", "pkg name : " + this.f21149a.getPackageName() + " 应用到达回调返回值为空，不做处理");
            LogUtil.m5348b(this.f21149a, "应用到达回调返回值异常，导致通知无法展示，如需打开请在onNotificationMessageArrived中返回正确的对象");
            return 2163;
        } else if (notifyArriveCallbackByUser.isIntercept()) {
            LogUtil.m5346b("OnVerifyCallBackCommand", "pkg name : " + this.f21149a.getPackageName() + " 应用主动拦截通知");
            LogUtil.m5348b(this.f21149a, "应用主动拦截通知，导致通知无法展示，如需打开请在onNotificationMessageArrived中返回false");
            return 2120;
        } else {
            return 0;
        }
    }

    /* renamed from: c */
    public final int m5697c() {
        return this.f20973c;
    }

    /* renamed from: a */
    public final void m5702a(int i) {
        this.f20973c = i;
    }
}
