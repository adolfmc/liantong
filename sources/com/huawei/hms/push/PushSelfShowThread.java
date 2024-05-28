package com.huawei.hms.push;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import com.huawei.hms.support.log.HMSLog;

/* renamed from: com.huawei.hms.push.n */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class PushSelfShowThread extends Thread {

    /* renamed from: a */
    private Context f11665a;

    /* renamed from: b */
    private PushSelfShowMessage f11666b;

    public PushSelfShowThread(Context context, PushSelfShowMessage pushSelfShowMessage) {
        this.f11665a = context;
        this.f11666b = pushSelfShowMessage;
    }

    /* renamed from: a */
    private static Intent m14204a(Context context, PushSelfShowMessage pushSelfShowMessage) {
        if (pushSelfShowMessage == null) {
            return null;
        }
        Intent m14279b = C5049d.m14279b(context, pushSelfShowMessage.m14236d());
        if (pushSelfShowMessage.m14218n() != null) {
            try {
                Intent parseUri = Intent.parseUri(pushSelfShowMessage.m14218n(), 0);
                parseUri.setSelector(null);
                if (parseUri.getClipData() == null) {
                    parseUri.setClipData(ClipData.newPlainText("avoid intent add read permission flags", "avoid"));
                }
                HMSLog.m14115d("PushSelfShowLog", "Intent.parseUri(msg.intentUri, 0), action:" + parseUri.getAction());
                return C5049d.m14282a(context, pushSelfShowMessage.m14236d(), parseUri).booleanValue() ? parseUri : m14279b;
            } catch (Exception e) {
                HMSLog.m14109w("PushSelfShowLog", "intentUri error," + e.toString());
                return m14279b;
            }
        }
        if (pushSelfShowMessage.m14244a() != null) {
            Intent intent = new Intent(pushSelfShowMessage.m14244a());
            if (C5049d.m14282a(context, pushSelfShowMessage.m14236d(), intent).booleanValue()) {
                m14279b = intent;
            }
        }
        m14279b.setPackage(pushSelfShowMessage.m14236d());
        return m14279b;
    }

    /* renamed from: b */
    private boolean m14203b(Context context) {
        if ("cosa".equals(this.f11666b.m14226i())) {
            return m14205a(context);
        }
        return true;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        HMSLog.m14110i("PushSelfShowLog", "enter run()");
        try {
            if (!m14203b(this.f11665a) || m14202b(this.f11665a, this.f11666b)) {
                return;
            }
            PushNotification.m14257a(this.f11665a, this.f11666b);
        } catch (Exception e) {
            HMSLog.m14112e("PushSelfShowLog", e.toString());
        }
    }

    /* renamed from: b */
    private boolean m14202b(Context context, PushSelfShowMessage pushSelfShowMessage) {
        if ("cosa".equals(pushSelfShowMessage.m14226i()) && m14204a(context, pushSelfShowMessage) == null) {
            HMSLog.m14115d("PushSelfShowLog", "launchCosaApp,intent == null");
            return true;
        }
        return false;
    }

    /* renamed from: a */
    private boolean m14205a(Context context) {
        return C5049d.m14277c(context, this.f11666b.m14236d());
    }
}
