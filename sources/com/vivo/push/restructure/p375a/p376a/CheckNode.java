package com.vivo.push.restructure.p375a.p376a;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.text.TextUtils;
import android.util.Base64;
import com.vivo.push.p372e.PushSecurityManager;
import com.vivo.push.restructure.PushClientController;
import com.vivo.push.restructure.p375a.ReceivedMessage;
import com.vivo.push.sdk.CommandWorker;
import com.vivo.push.util.AESParseManager;
import com.vivo.push.util.LogUtil;
import com.vivo.push.util.PushPackageUtils;
import com.vivo.push.util.RSAUtils;
import com.vivo.push.util.Utility;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.vivo.push.restructure.a.a.c */
/* loaded from: E:\11617560_dexfile_execute.dex */
public final class CheckNode extends AbstractMessageNodeMonitor<ReceivedMessage> {

    /* renamed from: b */
    private static final List<Integer> f21091b = Arrays.asList(3);

    @Override // com.vivo.push.restructure.p375a.p376a.AbstractMessageNodeMonitor
    /* renamed from: a */
    protected final /* bridge */ /* synthetic */ int mo5571a(ReceivedMessage receivedMessage) {
        return m5577a(receivedMessage);
    }

    public CheckNode(ReceivedMessage receivedMessage, NodeListener nodeListener) {
        super("CheckNode", receivedMessage, nodeListener);
    }

    /* renamed from: a  reason: avoid collision after fix types in other method */
    private static int m5577a(ReceivedMessage receivedMessage) {
        try {
        } catch (Exception e) {
            LogUtil.m5352a("CheckNode", e);
        }
        if (!PushClientController.m5593a().m5588e().mo5524l().isAgreePrivacyStatement()) {
            LogUtil.m5341d("CheckNode", " checkNeedReportByPrivacyStatement is false  ");
            return 2809;
        }
        Intent mo5562b = receivedMessage.mo5562b();
        String m5486b = CommandWorker.m5490a().m5486b();
        if (!TextUtils.isEmpty(m5486b) && m5486b.contains("CommandService")) {
            if (!(mo5562b != null && m5578a(mo5562b) && m5576b(mo5562b))) {
                LogUtil.m5354a("CheckNode", " !checkIntentIsSecurity(intent)");
                return 2801;
            }
        }
        Context m5591b = PushClientController.m5593a().m5591b();
        String packageName = m5591b.getPackageName();
        String stringExtra = mo5562b.getStringExtra("command_type");
        if (!TextUtils.isEmpty(stringExtra) && stringExtra.equals("reflect_receiver")) {
            int intExtra = mo5562b.getIntExtra("command", -1);
            if (intExtra < 0) {
                intExtra = mo5562b.getIntExtra("method", -1);
            }
            if (f21091b.contains(Integer.valueOf(intExtra)) && PushPackageUtils.m5464c(m5591b, packageName) && !PushPackageUtils.m5467b(m5591b)) {
                LogUtil.m5354a("CheckNode", "METHOD_ON_MESSAGE is not support");
                return 2803;
            }
            String action = mo5562b.getAction();
            if (TextUtils.isEmpty(PushClientController.m5593a().m5588e().mo5542a(m5591b, action))) {
                LogUtil.m5341d("CheckNode", " reflectReceiver error: receiver for: " + action + " not found, package: " + packageName);
                mo5562b.setPackage(packageName);
                m5591b.sendBroadcast(mo5562b);
                return 2802;
            }
            return 0;
        }
        LogUtil.m5354a("CheckNode", "commandTypeStr is not satisfy == ".concat(String.valueOf(stringExtra)));
        return 2801;
    }

    /* renamed from: a */
    private static boolean m5578a(Intent intent) {
        try {
            String stringExtra = intent.getStringExtra("security_avoid_pull");
            if (!TextUtils.isEmpty(stringExtra)) {
                try {
                    String m5473b = AESParseManager.m5476a(PushClientController.m5593a().m5591b()).m5473b(stringExtra);
                    if ("com.vivo.pushservice".equals(m5473b)) {
                        return true;
                    }
                    LogUtil.m5354a("CheckNode", "!decrypt.equals, so decrypt == ".concat(String.valueOf(m5473b)));
                    return false;
                } catch (Exception e) {
                    LogUtil.m5354a("CheckNode", "checkIntentIsSecurity Exception: " + e.getMessage());
                    return false;
                }
            }
            LogUtil.m5354a("CheckNode", "checkIntentIsSecurityTextUtils.isEmpty");
            return true;
        } catch (Exception unused) {
            LogUtil.m5354a("CheckNode", "getStringExtra error");
            return true;
        }
    }

    /* renamed from: b */
    private static boolean m5576b(Intent intent) {
        if (Build.VERSION.SDK_INT < 18) {
            return true;
        }
        try {
            Context m5591b = PushClientController.m5593a().m5591b();
            String m5436b = Utility.m5436b(m5591b, "com.vivo.pushservice");
            LogUtil.m5341d("CheckNode", " 配置的验签参数 = ".concat(String.valueOf(m5436b)));
            if (TextUtils.equals(m5436b, "1")) {
                String stringExtra = intent.getStringExtra("security_avoid_pull_rsa");
                String stringExtra2 = intent.getStringExtra("security_avoid_rsa_public_key");
                if (!TextUtils.isEmpty(stringExtra) && !TextUtils.isEmpty(stringExtra2)) {
                    if (!TextUtils.equals(stringExtra, "com.vivo.pushservice") && !TextUtils.equals(stringExtra2, "com.vivo.pushservice")) {
                        if (PushSecurityManager.m5714a().m5713a(m5591b).mo5709a("com.vivo.pushservice".getBytes("UTF-8"), RSAUtils.m5459a(stringExtra2), Base64.decode(stringExtra, 2))) {
                            LogUtil.m5341d("CheckNode", " RSA验签通过  ");
                            return true;
                        }
                        LogUtil.m5341d("CheckNode", " RSA验签 不通过  ");
                        return false;
                    }
                    LogUtil.m5354a("CheckNode", " 验签参数传入错误 securityContent = " + stringExtra + " publickKey= " + stringExtra2);
                    return true;
                }
                LogUtil.m5354a("CheckNode", "!decrypt.equals, so securityContent == " + stringExtra + " or publickKey isempty ");
                return false;
            }
            return true;
        } catch (Exception e) {
            LogUtil.m5354a("CheckNode", "checkIntentIsSecurity Exception: " + e.getMessage());
            return true;
        }
    }
}
