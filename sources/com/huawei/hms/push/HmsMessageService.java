package com.huawei.hms.push;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.text.TextUtils;
import com.huawei.hms.aaid.constant.ErrorEnum;
import com.huawei.hms.aaid.threads.AsyncExec;
import com.huawei.hms.aaid.utils.BaseUtils;
import com.huawei.hms.push.CommonHandler;
import com.huawei.hms.push.utils.PushBiUtil;
import com.huawei.hms.support.log.HMSLog;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class HmsMessageService extends Service {
    public static final String PROXY_TYPE = "proxy_type";
    public static final String SUBJECT_ID = "subject_id";

    /* renamed from: a */
    private final Messenger f11569a = new Messenger(new CommonHandler(new C5045b(this, null)));

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.huawei.hms.push.HmsMessageService$a */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class RunnableC5044a implements Runnable {

        /* renamed from: a */
        final /* synthetic */ String f11570a;

        /* renamed from: b */
        final /* synthetic */ String f11571b;

        RunnableC5044a(String str, String str2) {
            this.f11570a = str;
            this.f11571b = str2;
        }

        @Override // java.lang.Runnable
        public void run() {
            Context applicationContext = HmsMessageService.this.getApplicationContext();
            if (this.f11571b.equals(BaseUtils.getLocalToken(applicationContext, this.f11570a))) {
                return;
            }
            HMSLog.m14110i("HmsMessageService", "receive a new token, refresh the local token");
            BaseUtils.saveToken(applicationContext, this.f11570a, this.f11571b);
        }
    }

    /* renamed from: com.huawei.hms.push.HmsMessageService$b */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    class C5045b implements CommonHandler.InterfaceC5050a {
        private C5045b() {
        }

        @Override // com.huawei.hms.push.CommonHandler.InterfaceC5050a
        /* renamed from: a */
        public void mo14273a(Message message) {
            if (message == null) {
                HMSLog.m14112e("HmsMessageService", "receive message is null");
                return;
            }
            HMSLog.m14110i("HmsMessageService", "handle message start...");
            Bundle data = Message.obtain(message).getData();
            if (data != null) {
                Intent intent = new Intent();
                intent.putExtras(data);
                intent.putExtra("inputType", data.getInt("inputType", -1));
                HmsMessageService.this.handleIntentMessage(intent);
            }
        }

        /* synthetic */ C5045b(HmsMessageService hmsMessageService, RunnableC5044a runnableC5044a) {
            this();
        }
    }

    /* renamed from: a */
    private void m14322a(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        AsyncExec.submitSeqIO(new RunnableC5044a(str2, str));
    }

    /* renamed from: b */
    private void m14320b(Intent intent) {
        HMSLog.m14110i("HmsMessageService", "parse batch response.");
        String stringExtra = intent.getStringExtra("batchMsgbody");
        if (TextUtils.isEmpty(stringExtra)) {
            return;
        }
        try {
            JSONArray jSONArray = new JSONArray(stringExtra);
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                String optString = jSONObject.optString("transactionId");
                String optString2 = jSONObject.optString("msgId");
                int optInt = jSONObject.optInt("ret", ErrorEnum.ERROR_UNKNOWN.getInternalCode());
                if (ErrorEnum.SUCCESS.getInternalCode() == optInt) {
                    m14319b(optString, optString2);
                } else {
                    m14318b(optString, optString2, optInt);
                }
            }
        } catch (JSONException unused) {
            HMSLog.m14109w("HmsMessageService", "parse batch response failed.");
        }
    }

    protected void doMsgReceived(Intent intent) {
        onMessageReceived(new RemoteMessage(m14326a(intent)));
    }

    public void handleIntentMessage(Intent intent) {
        if (intent == null) {
            HMSLog.m14112e("HmsMessageService", "receive message is null");
            return;
        }
        try {
            String stringExtra = intent.getStringExtra("message_id");
            String stringExtra2 = intent.getStringExtra("message_type");
            String stringExtra3 = intent.getStringExtra("transaction_id");
            if ("new_token".equals(stringExtra2)) {
                HMSLog.m14110i("HmsMessageService", "onNewToken");
                m14323a(intent, stringExtra3);
            } else if ("received_message".equals(stringExtra2)) {
                HMSLog.m14110i("HmsMessageService", "onMessageReceived, message id:" + stringExtra);
                m14321a("push.receiveMessage", stringExtra, ErrorEnum.SUCCESS.getInternalCode());
                doMsgReceived(intent);
            } else if ("sent_message".equals(stringExtra2)) {
                m14319b(stringExtra3, stringExtra);
            } else if ("send_error".equals(stringExtra2)) {
                m14318b(stringExtra3, stringExtra, intent.getIntExtra("error", ErrorEnum.ERROR_UNKNOWN.getInternalCode()));
            } else if ("delivery".equals(stringExtra2)) {
                int intExtra = intent.getIntExtra("error", ErrorEnum.ERROR_APP_SERVER_NOT_ONLINE.getInternalCode());
                HMSLog.m14110i("HmsMessageService", "onMessageDelivery, message id:" + stringExtra + ", status:" + intExtra + ", transactionId: " + stringExtra3);
                m14321a("push.deliveryMessage", stringExtra3, intExtra);
                onMessageDelivered(stringExtra, new SendException(intExtra));
            } else if ("server_deleted_message".equals(stringExtra2)) {
                HMSLog.m14110i("HmsMessageService", "delete message, message id:" + stringExtra);
                onDeletedMessages();
            } else if ("batchSent".equals(stringExtra2)) {
                m14320b(intent);
            } else {
                HMSLog.m14112e("HmsMessageService", "Receive unknown message: " + stringExtra2);
            }
        } catch (RuntimeException e) {
            HMSLog.m14112e("HmsMessageService", "handle intent RuntimeException: " + e.getMessage());
        } catch (Exception e2) {
            HMSLog.m14112e("HmsMessageService", "handle intent exception: " + e2.getMessage());
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        HMSLog.m14110i("HmsMessageService", "start to bind");
        return this.f11569a.getBinder();
    }

    public void onDeletedMessages() {
    }

    @Override // android.app.Service
    public void onDestroy() {
        HMSLog.m14110i("HmsMessageService", "start to destroy");
        super.onDestroy();
    }

    public void onMessageDelivered(String str, Exception exc) {
    }

    public void onMessageReceived(RemoteMessage remoteMessage) {
    }

    public void onMessageSent(String str) {
    }

    public void onNewToken(String str) {
    }

    public void onNewToken(String str, Bundle bundle) {
    }

    public void onSendError(String str, Exception exc) {
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        HMSLog.m14110i("HmsMessageService", "start to command , startId = " + i2);
        handleIntentMessage(intent);
        return 2;
    }

    public void onTokenError(Exception exc) {
    }

    public void onTokenError(Exception exc, Bundle bundle) {
    }

    /* renamed from: a */
    private Bundle m14326a(Intent intent) {
        Bundle bundle = new Bundle();
        bundle.putString("message_id", intent.getStringExtra("message_id"));
        bundle.putByteArray("message_body", intent.getByteArrayExtra("message_body"));
        bundle.putString("device_token", intent.getStringExtra("device_token"));
        if (intent.getIntExtra("inputType", -1) == 1) {
            bundle.putInt("inputType", 1);
        }
        return bundle;
    }

    /* renamed from: a */
    private void m14323a(Intent intent, String str) {
        int intExtra = intent.getIntExtra("error", ErrorEnum.SUCCESS.getInternalCode());
        m14321a("push.onNewToken", str, intExtra);
        String stringExtra = intent.getStringExtra("subjectId");
        String stringExtra2 = intent.getStringExtra("message_proxy_type");
        HMSLog.m14110i("HmsMessageService", "doOnNewToken:transactionId = " + str + " , internalCode = " + intExtra + ",subjectId:" + stringExtra + ",proxyType:" + stringExtra2);
        Bundle bundle = new Bundle();
        if (!TextUtils.isEmpty(stringExtra)) {
            bundle.putString(SUBJECT_ID, stringExtra);
        }
        if (!TextUtils.isEmpty(stringExtra2)) {
            bundle.putString(PROXY_TYPE, stringExtra2);
        }
        if (intExtra == ErrorEnum.SUCCESS.getInternalCode()) {
            HMSLog.m14110i("HmsMessageService", "Apply token OnNewToken, subId: " + stringExtra);
            m14325a(intent, bundle, stringExtra);
            return;
        }
        HMSLog.m14110i("HmsMessageService", "Apply token failed, subId: " + stringExtra);
        m14324a(intent, bundle, stringExtra, intExtra);
    }

    /* renamed from: b */
    private void m14319b(String str, String str2) {
        HMSLog.m14110i("HmsMessageService", "onMessageSent, message id:" + str2 + ", transactionId: " + str);
        m14321a("push.sendMessageRet", str, ErrorEnum.SUCCESS.getInternalCode());
        onMessageSent(str2);
    }

    /* renamed from: b */
    private void m14318b(String str, String str2, int i) {
        HMSLog.m14110i("HmsMessageService", "onSendError, message id:" + str2 + " error:" + i + ", transactionId: " + str);
        m14321a("push.sendMessageRet", str, i);
        onSendError(str2, new SendException(i));
    }

    /* renamed from: a */
    private synchronized void m14324a(Intent intent, Bundle bundle, String str, int i) {
        Context applicationContext = getApplicationContext();
        boolean z = !TextUtils.isEmpty(BaseUtils.getCacheData(applicationContext, applicationContext.getPackageName(), false));
        if (bundle.isEmpty() && z) {
            HMSLog.m14110i("HmsMessageService", "onTokenError to host app.");
            onTokenError(new BaseException(i));
            BaseUtils.deleteCacheData(applicationContext, applicationContext.getPackageName());
        }
        if (TextUtils.isEmpty(str)) {
            String[] subjectIds = BaseUtils.getSubjectIds(applicationContext);
            if (subjectIds != null && subjectIds.length != 0) {
                for (int i2 = 0; i2 < subjectIds.length; i2++) {
                    Bundle bundle2 = new Bundle();
                    bundle2.putString(SUBJECT_ID, subjectIds[i2]);
                    HMSLog.m14110i("HmsMessageService", "onTokenError to sub app, subjectId:" + subjectIds[i2]);
                    onTokenError(new BaseException(i), bundle2);
                }
                BaseUtils.clearSubjectIds(applicationContext);
            }
            HMSLog.m14110i("HmsMessageService", "onTokenError to host app with bundle.");
            onTokenError(new BaseException(i), bundle);
            return;
        }
        HMSLog.m14110i("HmsMessageService", "onTokenError to sub app, subjectId:" + str);
        onTokenError(new BaseException(i), bundle);
    }

    /* renamed from: a */
    private synchronized void m14325a(Intent intent, Bundle bundle, String str) {
        String stringExtra = intent.getStringExtra("device_token");
        m14322a(stringExtra, str);
        Context applicationContext = getApplicationContext();
        boolean z = !TextUtils.isEmpty(BaseUtils.getCacheData(applicationContext, applicationContext.getPackageName(), false));
        if (bundle.isEmpty() && z) {
            HMSLog.m14110i("HmsMessageService", "onNewToken to host app.");
            onNewToken(stringExtra);
            BaseUtils.deleteCacheData(applicationContext, applicationContext.getPackageName());
        }
        if (TextUtils.isEmpty(str)) {
            String[] subjectIds = BaseUtils.getSubjectIds(applicationContext);
            if (subjectIds != null && subjectIds.length != 0) {
                for (int i = 0; i < subjectIds.length; i++) {
                    Bundle bundle2 = new Bundle();
                    bundle2.putString(SUBJECT_ID, subjectIds[i]);
                    HMSLog.m14110i("HmsMessageService", "onNewToken to sub app, subjectId:" + subjectIds[i]);
                    onNewToken(stringExtra, bundle2);
                    m14322a(stringExtra, subjectIds[i]);
                }
                BaseUtils.clearSubjectIds(applicationContext);
            }
            HMSLog.m14110i("HmsMessageService", "onNewToken to host app with bundle.");
            bundle.putString("belongId", intent.getStringExtra("belongId"));
            onNewToken(stringExtra, bundle);
            return;
        }
        HMSLog.m14110i("HmsMessageService", "onNewToken to sub app, subjectId:" + str);
        onNewToken(stringExtra, bundle);
    }

    /* renamed from: a */
    private void m14321a(String str, String str2, int i) {
        if (TextUtils.isEmpty(str2)) {
            str2 = "null";
        }
        PushBiUtil.reportExit(getApplicationContext(), str, str2, i);
    }
}
