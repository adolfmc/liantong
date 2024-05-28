package com.huawei.hms.push;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import com.huawei.hms.support.log.HMSLog;

/* renamed from: com.huawei.hms.push.s */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class SelfShowType {

    /* renamed from: c */
    private static final String[] f11674c = {"url", "app", "cosa", "rp"};

    /* renamed from: a */
    private Context f11675a;

    /* renamed from: b */
    private PushSelfShowMessage f11676b;

    public SelfShowType(Context context, PushSelfShowMessage pushSelfShowMessage) {
        this.f11675a = context;
        this.f11676b = pushSelfShowMessage;
    }

    /* renamed from: a */
    public static boolean m14189a(String str) {
        for (String str2 : f11674c) {
            if (str2.equals(str)) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: b */
    private void m14188b() {
        HMSLog.m14110i("PushSelfShowLog", "run into launchCosaApp");
        try {
            HMSLog.m14110i("PushSelfShowLog", "enter launchExistApp cosa, appPackageName =" + this.f11676b.m14236d() + ",and msg.intentUri is " + this.f11676b.m14218n());
            Intent m14279b = C5049d.m14279b(this.f11675a, this.f11676b.m14236d());
            boolean z = false;
            if (this.f11676b.m14218n() != null) {
                try {
                    Intent parseUri = Intent.parseUri(this.f11676b.m14218n(), 0);
                    parseUri.setSelector(null);
                    if (parseUri.getClipData() == null) {
                        parseUri.setClipData(ClipData.newPlainText("avoid intent add read permission flags", "avoid"));
                    }
                    HMSLog.m14110i("PushSelfShowLog", "Intent.parseUri(msg.intentUri, 0), action:" + parseUri.getAction());
                    z = C5049d.m14282a(this.f11675a, this.f11676b.m14236d(), parseUri).booleanValue();
                    if (z) {
                        m14279b = parseUri;
                    }
                } catch (Exception e) {
                    HMSLog.m14109w("PushSelfShowLog", "intentUri error." + e.toString());
                }
            } else if (this.f11676b.m14244a() != null) {
                Intent intent = new Intent(this.f11676b.m14244a());
                if (C5049d.m14282a(this.f11675a, this.f11676b.m14236d(), intent).booleanValue()) {
                    m14279b = intent;
                }
            }
            if (m14279b == null) {
                HMSLog.m14110i("PushSelfShowLog", "launchCosaApp,intent == null");
                return;
            }
            m14279b.setPackage(this.f11676b.m14236d());
            if (z) {
                m14279b.addFlags(268435456);
            } else {
                m14279b.setFlags(805437440);
            }
            this.f11675a.startActivity(m14279b);
        } catch (Exception e2) {
            HMSLog.m14112e("PushSelfShowLog", "launch Cosa App exception." + e2.toString());
        }
    }

    /* renamed from: c */
    public void m14187c() {
        PushSelfShowMessage pushSelfShowMessage;
        HMSLog.m14115d("PushSelfShowLog", "enter launchNotify()");
        if (this.f11675a != null && (pushSelfShowMessage = this.f11676b) != null) {
            if ("app".equals(pushSelfShowMessage.m14226i())) {
                m14190a();
                return;
            } else if ("cosa".equals(this.f11676b.m14226i())) {
                m14188b();
                return;
            } else if ("rp".equals(this.f11676b.m14226i())) {
                HMSLog.m14109w("PushSelfShowLog", this.f11676b.m14226i() + " not support rich message.");
                return;
            } else if ("url".equals(this.f11676b.m14226i())) {
                HMSLog.m14109w("PushSelfShowLog", this.f11676b.m14226i() + " not support URL.");
                return;
            } else {
                HMSLog.m14115d("PushSelfShowLog", this.f11676b.m14226i() + " is not exist in hShowType");
                return;
            }
        }
        HMSLog.m14115d("PushSelfShowLog", "launchNotify  context or msg is null");
    }

    /* renamed from: a */
    private void m14190a() {
        try {
            HMSLog.m14110i("PushSelfShowLog", "enter launchApp, appPackageName =" + this.f11676b.m14236d());
            if (C5049d.m14277c(this.f11675a, this.f11676b.m14236d())) {
                m14188b();
            }
        } catch (Exception e) {
            HMSLog.m14112e("PushSelfShowLog", "launchApp error:" + e.toString());
        }
    }
}
