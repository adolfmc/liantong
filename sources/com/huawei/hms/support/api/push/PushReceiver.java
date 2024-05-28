package com.huawei.hms.support.api.push;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.hms.aaid.constant.ErrorEnum;
import com.huawei.hms.push.AbstractC5047a;
import com.huawei.hms.push.AbstractC5048c;
import com.huawei.hms.push.C5051p;
import com.huawei.hms.push.ReceiverThreadPoolExecutor;
import com.huawei.hms.push.utils.JsonUtil;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.utils.ResourceLoaderUtil;
import java.util.concurrent.RejectedExecutionException;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public final class PushReceiver extends BroadcastReceiver {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.huawei.hms.support.api.push.PushReceiver$b */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static class RunnableC5070b implements Runnable {

        /* renamed from: a */
        private Context f11729a;

        /* renamed from: b */
        private Intent f11730b;

        @Override // java.lang.Runnable
        public void run() {
            Intent intent = new Intent("com.huawei.push.action.MESSAGING_EVENT");
            intent.setPackage(this.f11730b.getPackage());
            try {
                JSONObject m14141b = PushReceiver.m14141b(this.f11730b);
                String string = JsonUtil.getString(m14141b, "moduleName", "");
                int i = JsonUtil.getInt(m14141b, "msgType", 0);
                int i2 = JsonUtil.getInt(m14141b, "status", 0);
                if (ErrorEnum.SUCCESS.getInternalCode() != i2) {
                    i2 = ErrorEnum.ERROR_APP_SERVER_NOT_ONLINE.getInternalCode();
                }
                Bundle bundle = new Bundle();
                if ("Push".equals(string) && i == 1) {
                    bundle.putString("message_type", "delivery");
                    bundle.putString("message_id", JsonUtil.getString(m14141b, "msgId", ""));
                    bundle.putInt("error", i2);
                    bundle.putString("transaction_id", JsonUtil.getString(m14141b, "transactionId", ""));
                } else {
                    if (this.f11730b.getExtras() != null) {
                        bundle.putAll(this.f11730b.getExtras());
                    }
                    bundle.putString("message_type", "received_message");
                    bundle.putString("message_id", this.f11730b.getStringExtra("msgIdStr"));
                    bundle.putByteArray("message_body", this.f11730b.getByteArrayExtra("msg_data"));
                    bundle.putString("device_token", AbstractC5047a.m14300a(this.f11730b.getByteArrayExtra("device_token")));
                    bundle.putInt("inputType", 1);
                    bundle.putString("message_proxy_type", this.f11730b.getStringExtra("message_proxy_type"));
                }
                if (new C5051p().m14200a(this.f11729a, bundle, intent)) {
                    HMSLog.m14110i("PushReceiver", "receive " + this.f11730b.getAction() + " and start service success");
                    return;
                }
                HMSLog.m14112e("PushReceiver", "receive " + this.f11730b.getAction() + " and start service failed");
            } catch (RuntimeException unused) {
                HMSLog.m14112e("PushReceiver", "handle push message occur exception.");
            }
        }

        private RunnableC5070b(Context context, Intent intent) {
            this.f11729a = context;
            this.f11730b = intent;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.huawei.hms.support.api.push.PushReceiver$c */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static class RunnableC5071c implements Runnable {

        /* renamed from: a */
        private Context f11731a;

        /* renamed from: b */
        private Intent f11732b;

        @Override // java.lang.Runnable
        public void run() {
            try {
                byte[] byteArrayExtra = this.f11732b.getByteArrayExtra("device_token");
                if (byteArrayExtra != null && byteArrayExtra.length != 0) {
                    HMSLog.m14110i("PushReceiver", "receive a push token: " + this.f11731a.getPackageName());
                    Intent intent = new Intent("com.huawei.push.action.MESSAGING_EVENT");
                    intent.setPackage(this.f11732b.getPackage());
                    Bundle bundle = new Bundle();
                    bundle.putString("message_type", "new_token");
                    bundle.putString("device_token", AbstractC5047a.m14300a(byteArrayExtra));
                    bundle.putString("transaction_id", this.f11732b.getStringExtra("transaction_id"));
                    bundle.putString("subjectId", this.f11732b.getStringExtra("subjectId"));
                    bundle.putInt("error", this.f11732b.getIntExtra("error", ErrorEnum.SUCCESS.getInternalCode()));
                    bundle.putString("belongId", this.f11732b.getStringExtra("belongId"));
                    if (new C5051p().m14200a(this.f11731a, bundle, intent)) {
                        return;
                    }
                    HMSLog.m14112e("PushReceiver", "receive " + this.f11732b.getAction() + " and start service failed");
                    return;
                }
                HMSLog.m14110i("PushReceiver", "get a deviceToken, but it is null or empty");
            } catch (RejectedExecutionException unused) {
                HMSLog.m14112e("PushReceiver", "execute task error");
            } catch (Exception unused2) {
                HMSLog.m14112e("PushReceiver", "handle push token error");
            }
        }

        private RunnableC5071c(Context context, Intent intent) {
            this.f11731a = context;
            this.f11732b = intent;
        }
    }

    /* renamed from: b */
    private void m14142b(Context context, Intent intent) {
        try {
            if (intent.hasExtra("device_token")) {
                ReceiverThreadPoolExecutor.m14201a().execute(new RunnableC5071c(context, intent));
            } else {
                HMSLog.m14110i("PushReceiver", "This message dose not sent by hwpush.");
            }
        } catch (RuntimeException unused) {
            HMSLog.m14112e("PushReceiver", "handlePushMessageEvent execute task runtime exception.");
        } catch (Exception unused2) {
            HMSLog.m14112e("PushReceiver", "handlePushTokenEvent execute task error");
        }
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent == null || context == null) {
            return;
        }
        HMSLog.m14110i("PushReceiver", "push receive broadcast message, Intent:" + intent.getAction() + " pkgName:" + context.getPackageName());
        try {
            intent.getStringExtra("TestIntent");
            String action = intent.getAction();
            if (ResourceLoaderUtil.getmContext() == null) {
                ResourceLoaderUtil.setmContext(context.getApplicationContext());
            }
            if ("com.huawei.android.push.intent.REGISTRATION".equals(action)) {
                m14142b(context, intent);
            } else if ("com.huawei.android.push.intent.RECEIVE".equals(action)) {
                m14146a(context, intent);
            } else {
                HMSLog.m14110i("PushReceiver", "message can't be recognised.");
            }
        } catch (Exception unused) {
            HMSLog.m14112e("PushReceiver", "intent has some error");
        }
    }

    /* renamed from: a */
    private void m14146a(Context context, Intent intent) {
        try {
            if (intent.hasExtra("msg_data")) {
                ReceiverThreadPoolExecutor.m14201a().execute(new RunnableC5070b(context, intent));
            } else {
                HMSLog.m14110i("PushReceiver", "This push message dose not sent by hwpush.");
            }
        } catch (RuntimeException unused) {
            HMSLog.m14112e("PushReceiver", "handlePushMessageEvent execute task runtime exception.");
        } catch (Exception unused2) {
            HMSLog.m14112e("PushReceiver", "handlePushMessageEvent execute task error");
        }
    }

    /* renamed from: b */
    private static JSONObject m14140b(JSONObject jSONObject) {
        if (jSONObject != null) {
            return jSONObject.optJSONObject("psContent");
        }
        return null;
    }

    /* renamed from: a */
    private static JSONObject m14143a(byte[] bArr) {
        try {
            return new JSONObject(AbstractC5047a.m14300a(bArr));
        } catch (JSONException unused) {
            HMSLog.m14109w("PushReceiver", "JSONException:parse message body failed.");
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public static JSONObject m14141b(Intent intent) throws RuntimeException {
        JSONObject m14143a = m14143a(intent.getByteArrayExtra("msg_data"));
        JSONObject m14144a = m14144a(m14143a);
        String string = JsonUtil.getString(m14144a, "data", null);
        if (AbstractC5048c.m14294a(m14144a, m14140b(m14144a), string)) {
            return m14143a;
        }
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        try {
            return new JSONObject(string);
        } catch (JSONException unused) {
            return null;
        }
    }

    /* renamed from: a */
    private static JSONObject m14144a(JSONObject jSONObject) {
        if (jSONObject != null) {
            return jSONObject.optJSONObject("msgContent");
        }
        return null;
    }
}
