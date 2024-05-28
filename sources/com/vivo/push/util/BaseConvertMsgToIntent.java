package com.vivo.push.util;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.text.TextUtils;
import com.vivo.push.model.InsideNotificationItem;
import com.vivo.push.model.NotifyArriveCallbackByUser;
import com.vivo.push.p368b.OnNotificationClickReceiveCommand;
import com.vivo.push.p372e.PushSecurityManager;
import com.vivo.push.restructure.PushClientController;
import java.security.PublicKey;
import java.util.HashMap;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.vivo.push.util.b */
/* loaded from: E:\11617560_dexfile_execute.dex */
public abstract class BaseConvertMsgToIntent {

    /* renamed from: a */
    protected String f21198a;

    /* renamed from: b */
    protected long f21199b;

    /* renamed from: c */
    protected Context f21200c;

    /* renamed from: d */
    protected NotifyArriveCallbackByUser f21201d;

    /* renamed from: a */
    protected abstract int mo5403a();

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public abstract PendingIntent mo5402a(Context context, Intent intent);

    /* renamed from: a */
    protected abstract Intent mo5401a(Context context, InsideNotificationItem insideNotificationItem, NotifyArriveCallbackByUser notifyArriveCallbackByUser);

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public static void m5425a(Intent intent, Context context) {
        try {
            intent.putExtra("security_avoid_pull", AESParseManager.m5476a(context).m5475a("com.vivo.pushservice"));
            if (Build.VERSION.SDK_INT >= 18) {
                String mo5710a = PushSecurityManager.m5714a().m5713a(context).mo5710a("com.vivo.pushservice");
                PublicKey mo5712a = PushSecurityManager.m5714a().m5713a(context).mo5712a();
                if (TextUtils.isEmpty(mo5710a)) {
                    mo5710a = "com.vivo.pushservice";
                }
                intent.putExtra("security_avoid_pull_rsa", mo5710a);
                intent.putExtra("security_avoid_rsa_public_key", mo5712a == null ? "com.vivo.pushservice" : RSAUtils.m5458a(mo5712a));
            }
        } catch (Exception e) {
            LogUtil.m5354a("BaseNotifyClickIntentParam", "pushNotificationBySystem encrypt ：" + e.getMessage());
            intent.putExtra("security_avoid_pull_rsa", "com.vivo.pushservice");
            intent.putExtra("security_avoid_rsa_public_key", "com.vivo.pushservice");
        }
    }

    /* renamed from: b */
    public final long m5424b() {
        return this.f21199b;
    }

    /* renamed from: a */
    public final Intent m5426a(Context context, String str, long j, InsideNotificationItem insideNotificationItem, NotifyArriveCallbackByUser notifyArriveCallbackByUser) {
        this.f21199b = j;
        this.f21198a = str;
        this.f21200c = context;
        this.f21201d = notifyArriveCallbackByUser;
        Intent mo5401a = mo5401a(context, insideNotificationItem, notifyArriveCallbackByUser);
        int mo5403a = mo5403a();
        if (mo5403a > 0) {
            HashMap hashMap = new HashMap();
            hashMap.put("messageID", String.valueOf(this.f21199b));
            String mo5543a = PushClientController.m5593a().m5588e().mo5543a();
            if (!TextUtils.isEmpty(mo5543a)) {
                hashMap.put("remoteAppId", mo5543a);
            }
            hashMap.put("ap", this.f21198a);
            hashMap.put("clientsdkver", String.valueOf(Utility.m5433c(this.f21200c, this.f21198a)));
            ClientReportUtil.m5405a(mo5403a, hashMap);
            return null;
        }
        return mo5401a;
    }

    /* renamed from: a */
    public static Intent m5427a(Context context, String str, long j, Intent intent, InsideNotificationItem insideNotificationItem) {
        Intent intent2 = new Intent("com.vivo.pushservice.action.RECEIVE");
        intent2.setPackage(context.getPackageName());
        intent2.setClassName(context.getPackageName(), "com.vivo.push.sdk.service.CommandService");
        intent2.putExtra("command_type", "reflect_receiver");
        intent2.putExtras(intent.getExtras());
        m5425a(intent2, context);
        OnNotificationClickReceiveCommand onNotificationClickReceiveCommand = new OnNotificationClickReceiveCommand(str, j, insideNotificationItem);
        onNotificationClickReceiveCommand.m5784b(intent.getAction());
        if (intent.getComponent() != null) {
            onNotificationClickReceiveCommand.m5783c(intent.getComponent().getPackageName());
            onNotificationClickReceiveCommand.m5781d(intent.getComponent().getClassName());
        }
        if (intent.getData() != null) {
            onNotificationClickReceiveCommand.m5785a(intent.getData());
        }
        onNotificationClickReceiveCommand.m5325b(intent2);
        return intent2;
    }
}
