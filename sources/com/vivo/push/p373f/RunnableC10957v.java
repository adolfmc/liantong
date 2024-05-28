package com.vivo.push.p373f;

import android.content.Context;
import android.net.NetworkInfo;
import android.text.TextUtils;
import com.vivo.push.model.InsideNotificationItem;
import com.vivo.push.model.NotifyArriveCallbackByUser;
import com.vivo.push.p368b.OnNotifyArrivedReceiveCommand;
import com.vivo.push.restructure.PushClientController;
import com.vivo.push.sdk.PushMessageCallback;
import com.vivo.push.util.ClientReportUtil;
import com.vivo.push.util.ImageDownTask;
import com.vivo.push.util.LogUtil;
import com.vivo.push.util.MessageConvertUtil;
import com.vivo.push.util.NetUtils;
import com.vivo.push.util.Utility;
import java.util.HashMap;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: OnNotificationArrivedReceiveTask.java */
/* renamed from: com.vivo.push.f.v */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class RunnableC10957v implements Runnable {

    /* renamed from: a */
    final /* synthetic */ InsideNotificationItem f21008a;

    /* renamed from: b */
    final /* synthetic */ OnNotifyArrivedReceiveCommand f21009b;

    /* renamed from: c */
    final /* synthetic */ OnNotificationArrivedReceiveTask f21010c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC10957v(OnNotificationArrivedReceiveTask onNotificationArrivedReceiveTask, InsideNotificationItem insideNotificationItem, OnNotifyArrivedReceiveCommand onNotifyArrivedReceiveCommand) {
        this.f21010c = onNotificationArrivedReceiveTask;
        this.f21008a = insideNotificationItem;
        this.f21009b = onNotifyArrivedReceiveCommand;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Context context;
        Context context2;
        Context context3;
        Context context4;
        Context context5;
        Context context6;
        char c;
        Context context7;
        Context context8;
        Context context9;
        Context context10;
        PushMessageCallback pushMessageCallback = this.f21010c.f20972b;
        context = this.f21010c.f21149a;
        NotifyArriveCallbackByUser onNotificationMessageArrived = pushMessageCallback.onNotificationMessageArrived(context, MessageConvertUtil.m5339a(this.f21008a));
        int a = this.f21010c.m5701a(onNotificationMessageArrived);
        if (a > 0) {
            HashMap hashMap = new HashMap();
            hashMap.put("messageID", String.valueOf(this.f21009b.m5764f()));
            String mo5543a = PushClientController.m5593a().m5588e().mo5543a();
            if (!TextUtils.isEmpty(mo5543a)) {
                hashMap.put("remoteAppId", mo5543a);
            }
            context9 = this.f21010c.f21149a;
            context10 = this.f21010c.f21149a;
            hashMap.put("clientsdkver", String.valueOf(Utility.m5433c(context9, context10.getPackageName())));
            ClientReportUtil.m5405a(a, hashMap);
            return;
        }
        int b = this.f21010c.m5698b();
        if (b <= 0) {
            context2 = this.f21010c.f21149a;
            InsideNotificationItem insideNotificationItem = this.f21008a;
            long f = this.f21009b.m5764f();
            PushMessageCallback pushMessageCallback2 = this.f21010c.f20972b;
            context3 = this.f21010c.f21149a;
            ImageDownTask imageDownTask = new ImageDownTask(context2, insideNotificationItem, f, pushMessageCallback2.isAllowNet(context3), new C10958w(this), onNotificationMessageArrived);
            boolean isShowBigPicOnMobileNet = this.f21008a.isShowBigPicOnMobileNet();
            String purePicUrl = this.f21008a.getPurePicUrl();
            if (TextUtils.isEmpty(purePicUrl)) {
                purePicUrl = this.f21008a.getCoverUrl();
            }
            if (!TextUtils.isEmpty(purePicUrl)) {
                LogUtil.m5342c("OnNotificationArrivedTask", "showCode=".concat(String.valueOf(isShowBigPicOnMobileNet)));
                if (!isShowBigPicOnMobileNet) {
                    context5 = this.f21010c.f21149a;
                    LogUtil.m5356a(context5, "mobile net unshow");
                    context6 = this.f21010c.f21149a;
                    NetworkInfo m5335a = NetUtils.m5335a(context6);
                    if (m5335a == null) {
                        c = 0;
                    } else if (m5335a.getState() != NetworkInfo.State.CONNECTED) {
                        c = 0;
                    } else {
                        int type = m5335a.getType();
                        c = type == 1 ? (char) 2 : type == 0 ? (char) 1 : (char) 3;
                    }
                    if (c == 1) {
                        purePicUrl = null;
                        this.f21008a.clearCoverUrl();
                        this.f21008a.clearPurePicUrl();
                    }
                } else {
                    context4 = this.f21010c.f21149a;
                    LogUtil.m5356a(context4, "mobile net show");
                }
            }
            imageDownTask.execute(this.f21008a.getIconUrl(), purePicUrl);
            return;
        }
        StringBuilder sb = new StringBuilder("pkg name : ");
        context7 = this.f21010c.f21149a;
        sb.append(context7.getPackageName());
        sb.append(" notify channel switch is ");
        sb.append(b);
        LogUtil.m5346b("OnNotificationArrivedTask", sb.toString());
        context8 = this.f21010c.f21149a;
        LogUtil.m5348b(context8, "允许通知开关或者推送通知渠道开关关闭，导致通知无法展示，请到设置页打开应用通知开关 ".concat(String.valueOf(b)));
        HashMap hashMap2 = new HashMap();
        hashMap2.put("messageID", String.valueOf(this.f21009b.m5764f()));
        String mo5543a2 = PushClientController.m5593a().m5588e().mo5543a();
        if (!TextUtils.isEmpty(mo5543a2)) {
            hashMap2.put("remoteAppId", mo5543a2);
        }
        ClientReportUtil.m5405a(b, hashMap2);
    }
}
