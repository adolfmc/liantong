package cn.sharesdk.system.text.login.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.telephony.SmsMessage;
import android.text.TextUtils;
import cn.sharesdk.framework.utils.SSDKLog;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class VerifyCodeReceiver extends BroadcastReceiver {

    /* renamed from: a */
    private static final String f3129a = new String(new char[]{30340, 39564, 35777, 30721, 65306});

    /* renamed from: b */
    private VerifyCodeReadListener f3130b;

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        Bundle extras;
        if (!intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED") || (extras = intent.getExtras()) == null) {
            return;
        }
        Object[] objArr = (Object[]) extras.get("pdus");
        SmsMessage[] smsMessageArr = new SmsMessage[objArr.length];
        for (int i = 0; i < objArr.length; i++) {
            smsMessageArr[i] = SmsMessage.createFromPdu((byte[]) objArr[i]);
        }
        for (SmsMessage smsMessage : smsMessageArr) {
            if (smsMessage != null) {
                try {
                    m21464a(smsMessage);
                } catch (Throwable th) {
                    SSDKLog.m21740b().m21737b(th);
                }
            }
        }
    }

    /* renamed from: a */
    private boolean m21464a(SmsMessage smsMessage) throws Throwable {
        String messageBody;
        int m21463a;
        if (smsMessage != null && (m21463a = m21463a((messageBody = smsMessage.getMessageBody()))) > -1) {
            String substring = messageBody.substring(m21463a, m21463a + 4);
            if (Looper.myLooper() != Looper.getMainLooper()) {
                throw new Throwable("operation not in UI Thread");
            }
            VerifyCodeReadListener verifyCodeReadListener = this.f3130b;
            if (verifyCodeReadListener == null) {
                throw new Throwable("listener can not be null");
            }
            verifyCodeReadListener.onReadVerifyCode(substring);
            return true;
        }
        return false;
    }

    /* renamed from: a */
    private int m21463a(String str) {
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        int indexOf = str.indexOf(f3129a);
        if (indexOf > -1) {
            return indexOf + f3129a.length();
        }
        int indexOf2 = str.indexOf("Your pin is ");
        return indexOf2 > -1 ? indexOf2 + f3129a.length() : indexOf2;
    }
}
