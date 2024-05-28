package com.vivo.push.p373f;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.text.TextUtils;
import com.vivo.push.PushClientManager;
import com.vivo.push.PushClientThread;
import com.vivo.push.PushCommand;
import com.vivo.push.model.InsideNotificationItem;
import com.vivo.push.model.UPSNotificationMessage;
import com.vivo.push.p368b.OnNotificationClickReceiveCommand;
import com.vivo.push.p368b.ReporterCommand;
import com.vivo.push.restructure.PushClientController;
import com.vivo.push.util.LogUtil;
import com.vivo.push.util.MessageConvertUtil;
import com.vivo.push.util.NotifyAdapterUtil;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.vivo.push.f.e */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class NotifyOpenClientClickTask extends OnReceiveTask {
    /* JADX INFO: Access modifiers changed from: package-private */
    public NotifyOpenClientClickTask(PushCommand pushCommand) {
        super(pushCommand);
    }

    @Override // com.vivo.push.PushClientTask
    /* renamed from: a */
    public final void mo5492a(PushCommand pushCommand) {
        Intent parseUri;
        String str;
        OnNotificationClickReceiveCommand onNotificationClickReceiveCommand = (OnNotificationClickReceiveCommand) pushCommand;
        InsideNotificationItem m5778f = onNotificationClickReceiveCommand.m5778f();
        if (m5778f == null) {
            LogUtil.m5341d("NotifyOpenClientTask", "current notification item is null");
            return;
        }
        UPSNotificationMessage m5339a = MessageConvertUtil.m5339a(m5778f);
        boolean equals = this.f21149a.getPackageName().equals(onNotificationClickReceiveCommand.m5782d());
        if (equals) {
            NotifyAdapterUtil.cancelNotify(this.f21149a);
        }
        if (equals) {
            ReporterCommand reporterCommand = new ReporterCommand(1030L);
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("type", "2");
            hashMap.put("messageID", String.valueOf(onNotificationClickReceiveCommand.m5780e()));
            hashMap.put("platform", this.f21149a.getPackageName());
            String mo5543a = PushClientController.m5593a().m5588e().mo5543a();
            if (!TextUtils.isEmpty(mo5543a)) {
                hashMap.put("remoteAppId", mo5543a);
            }
            reporterCommand.m5761a(hashMap);
            PushClientManager.m5648a().m5638a(reporterCommand);
            LogUtil.m5341d("NotifyOpenClientTask", "notification is clicked by skip type[" + m5339a.getSkipType() + "]");
            boolean z = true;
            switch (m5339a.getSkipType()) {
                case 1:
                    new Thread(new RunnableC10949f(this, this.f21149a, m5339a.getParams())).start();
                    m5689a(m5339a);
                    return;
                case 2:
                    String skipContent = m5339a.getSkipContent();
                    if (!skipContent.startsWith("http://") && !skipContent.startsWith("https://")) {
                        z = false;
                    }
                    if (z) {
                        Uri parse = Uri.parse(skipContent);
                        Intent intent = new Intent("android.intent.action.VIEW", parse);
                        intent.setFlags(268435456);
                        m5688b(intent, m5339a.getParams());
                        try {
                            this.f21149a.startActivity(intent);
                        } catch (Exception unused) {
                            LogUtil.m5354a("NotifyOpenClientTask", "startActivity error : ".concat(String.valueOf(parse)));
                        }
                    } else {
                        LogUtil.m5354a("NotifyOpenClientTask", "url not legal");
                    }
                    m5689a(m5339a);
                    return;
                case 3:
                    m5689a(m5339a);
                    return;
                case 4:
                    String skipContent2 = m5339a.getSkipContent();
                    try {
                        parseUri = Intent.parseUri(skipContent2, 1);
                        str = parseUri.getPackage();
                    } catch (Exception e) {
                        LogUtil.m5353a("NotifyOpenClientTask", "open activity error : ".concat(String.valueOf(skipContent2)), e);
                    }
                    if (!TextUtils.isEmpty(str) && !this.f21149a.getPackageName().equals(str)) {
                        LogUtil.m5354a("NotifyOpenClientTask", "open activity error : local pkgName is " + this.f21149a.getPackageName() + "; but remote pkgName is " + parseUri.getPackage());
                        return;
                    }
                    String packageName = parseUri.getComponent() == null ? null : parseUri.getComponent().getPackageName();
                    if (!TextUtils.isEmpty(packageName) && !this.f21149a.getPackageName().equals(packageName)) {
                        LogUtil.m5354a("NotifyOpenClientTask", "open activity component error : local pkgName is " + this.f21149a.getPackageName() + "; but remote pkgName is " + parseUri.getPackage());
                        return;
                    }
                    parseUri.setSelector(null);
                    parseUri.setPackage(this.f21149a.getPackageName());
                    parseUri.addFlags(335544320);
                    m5688b(parseUri, m5339a.getParams());
                    ActivityInfo resolveActivityInfo = parseUri.resolveActivityInfo(this.f21149a.getPackageManager(), 65536);
                    if (resolveActivityInfo != null && !resolveActivityInfo.exported) {
                        LogUtil.m5354a("NotifyOpenClientTask", "activity is not exported : " + resolveActivityInfo.toString());
                        return;
                    }
                    this.f21149a.startActivity(parseUri);
                    m5689a(m5339a);
                    return;
                default:
                    LogUtil.m5354a("NotifyOpenClientTask", "illegitmacy skip type error : " + m5339a.getSkipType());
                    return;
            }
        }
        LogUtil.m5354a("NotifyOpenClientTask", "notify is " + m5339a + " ; isMatch is " + equals);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public static Intent m5688b(Intent intent, Map<String, String> map) {
        if (map == null || map.entrySet() == null) {
            return intent;
        }
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (entry != null && entry.getKey() != null) {
                intent.putExtra(entry.getKey(), entry.getValue());
            }
        }
        return intent;
    }

    /* renamed from: a */
    private void m5689a(UPSNotificationMessage uPSNotificationMessage) {
        PushClientThread.m5480c(new RunnableC10950g(this, uPSNotificationMessage));
    }
}
