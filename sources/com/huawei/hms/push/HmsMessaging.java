package com.huawei.hms.push;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.huawei.hmf.tasks.Task;
import com.huawei.hmf.tasks.TaskCompletionSource;
import com.huawei.hmf.tasks.Tasks;
import com.huawei.hms.aaid.constant.ErrorEnum;
import com.huawei.hms.aaid.encrypt.PushEncrypter;
import com.huawei.hms.aaid.init.AutoInitHelper;
import com.huawei.hms.aaid.plugin.ProxyCenter;
import com.huawei.hms.aaid.task.PushClientBuilder;
import com.huawei.hms.aaid.utils.BaseUtils;
import com.huawei.hms.aaid.utils.PushPreferences;
import com.huawei.hms.android.HwBuildEx;
import com.huawei.hms.api.Api;
import com.huawei.hms.common.ApiException;
import com.huawei.hms.common.HuaweiApi;
import com.huawei.hms.common.internal.AbstractClientBuilder;
import com.huawei.hms.common.internal.Preconditions;
import com.huawei.hms.push.task.BaseVoidTask;
import com.huawei.hms.push.task.IntentCallable;
import com.huawei.hms.push.task.SendUpStreamTask;
import com.huawei.hms.push.task.SubscribeTask;
import com.huawei.hms.push.utils.PushBiUtil;
import com.huawei.hms.support.api.entity.push.EnableNotifyReq;
import com.huawei.hms.support.api.entity.push.SubscribeReq;
import com.huawei.hms.support.api.entity.push.UpSendMsgReq;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.utils.JsonUtil;
import com.huawei.hms.utils.NetWorkUtil;
import java.util.regex.Pattern;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class HmsMessaging {
    public static final String DEFAULT_TOKEN_SCOPE = "HCM";

    /* renamed from: c */
    private static final Pattern f11574c = Pattern.compile("[\\u4e00-\\u9fa5\\w-_.~%]{1,900}");

    /* renamed from: a */
    private Context f11575a;

    /* renamed from: b */
    private HuaweiApi<Api.ApiOptions.NoOptions> f11576b;

    private HmsMessaging(Context context) {
        Preconditions.checkNotNull(context);
        this.f11575a = context;
        Api api = new Api("HuaweiPush.API");
        if (context instanceof Activity) {
            this.f11576b = new HuaweiApi<>((Activity) context, (Api<Api.ApiOptions>) api, (Api.ApiOptions) null, (AbstractClientBuilder) new PushClientBuilder());
        } else {
            this.f11576b = new HuaweiApi<>(context, api, (Api.ApiOptions) null, new PushClientBuilder());
        }
        this.f11576b.setKitSdkVersion(61100300);
    }

    /* renamed from: a */
    private Task<Void> m14315a(String str, String str2) {
        String reportEntry = PushBiUtil.reportEntry(this.f11575a, "push.subscribe");
        if (str != null && f11574c.matcher(str).matches()) {
            if (ProxyCenter.getProxy() != null) {
                HMSLog.m14110i("HmsMessaging", "use proxy subscribe.");
                return TextUtils.equals(str2, "Sub") ? ProxyCenter.getProxy().subscribe(this.f11575a, str, reportEntry) : ProxyCenter.getProxy().unsubscribe(this.f11575a, str, reportEntry);
            }
            try {
                ErrorEnum m14186a = TokenUtil.m14186a(this.f11575a);
                if (m14186a == ErrorEnum.SUCCESS) {
                    if (NetWorkUtil.getNetworkType(this.f11575a) != 0) {
                        SubscribeReq subscribeReq = new SubscribeReq(this.f11575a, str2, str);
                        subscribeReq.setToken(BaseUtils.getLocalToken(this.f11575a, null));
                        if (AbstractC5048c.m14293b()) {
                            return this.f11576b.doWrite(new BaseVoidTask("push.subscribe", JsonUtil.createJsonString(subscribeReq), reportEntry));
                        }
                        return this.f11576b.doWrite(new SubscribeTask("push.subscribe", JsonUtil.createJsonString(subscribeReq), reportEntry));
                    }
                    HMSLog.m14112e("HmsMessaging", "no network");
                    throw ErrorEnum.ERROR_NO_NETWORK.toApiException();
                }
                throw m14186a.toApiException();
            } catch (ApiException e) {
                TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
                taskCompletionSource.setException(e);
                PushBiUtil.reportExit(this.f11575a, "push.subscribe", reportEntry, e.getStatusCode());
                return taskCompletionSource.getTask();
            } catch (Exception unused) {
                TaskCompletionSource taskCompletionSource2 = new TaskCompletionSource();
                taskCompletionSource2.setException(ErrorEnum.ERROR_INTERNAL_ERROR.toApiException());
                PushBiUtil.reportExit(this.f11575a, "push.subscribe", reportEntry, ErrorEnum.ERROR_INTERNAL_ERROR);
                return taskCompletionSource2.getTask();
            }
        }
        PushBiUtil.reportExit(this.f11575a, "push.subscribe", reportEntry, ErrorEnum.ERROR_ARGUMENTS_INVALID);
        HMSLog.m14112e("HmsMessaging", "Invalid topic: topic should match the format:[\\u4e00-\\u9fa5\\w-_.~%]{1,900}");
        throw new IllegalArgumentException("Invalid topic: topic should match the format:[\\u4e00-\\u9fa5\\w-_.~%]{1,900}");
    }

    public static synchronized HmsMessaging getInstance(Context context) {
        HmsMessaging hmsMessaging;
        synchronized (HmsMessaging.class) {
            hmsMessaging = new HmsMessaging(context);
        }
        return hmsMessaging;
    }

    public boolean isAutoInitEnabled() {
        return AutoInitHelper.isAutoInitEnabled(this.f11575a);
    }

    public void send(RemoteMessage remoteMessage) {
        if (ProxyCenter.getProxy() == null) {
            HMSLog.m14110i("HmsMessaging", "send upstream message");
            m14317a(remoteMessage);
            return;
        }
        HMSLog.m14112e("HmsMessaging", "Operation(send) unsupported");
        throw new UnsupportedOperationException("Operation(send) unsupported");
    }

    public void setAutoInitEnabled(boolean z) {
        AutoInitHelper.setAutoInitEnabled(this.f11575a, z);
    }

    public Task<Void> subscribe(String str) {
        HMSLog.m14110i("HmsMessaging", "invoke subscribe");
        return m14315a(str, "Sub");
    }

    public Task<Void> turnOffPush() {
        if (ProxyCenter.getProxy() != null) {
            HMSLog.m14110i("HmsMessaging", "turn off for proxy");
            return ProxyCenter.getProxy().turnOff(this.f11575a, null);
        }
        HMSLog.m14110i("HmsMessaging", "invoke turnOffPush");
        return m14314a(false);
    }

    public Task<Void> turnOnPush() {
        if (ProxyCenter.getProxy() != null) {
            HMSLog.m14110i("HmsMessaging", "turn on for proxy");
            return ProxyCenter.getProxy().turnOn(this.f11575a, null);
        }
        HMSLog.m14110i("HmsMessaging", "invoke turnOnPush");
        return m14314a(true);
    }

    public Task<Void> unsubscribe(String str) {
        HMSLog.m14110i("HmsMessaging", "invoke unsubscribe");
        return m14315a(str, "UnSub");
    }

    /* renamed from: a */
    private void m14317a(RemoteMessage remoteMessage) {
        String reportEntry = PushBiUtil.reportEntry(this.f11575a, "push.sendMessage");
        ErrorEnum m14186a = TokenUtil.m14186a(this.f11575a);
        if (m14186a == ErrorEnum.SUCCESS) {
            if (!TextUtils.isEmpty(remoteMessage.getTo())) {
                if (!TextUtils.isEmpty(remoteMessage.getMessageId())) {
                    if (!TextUtils.isEmpty(remoteMessage.getData())) {
                        UpSendMsgReq upSendMsgReq = new UpSendMsgReq();
                        upSendMsgReq.setPackageName(this.f11575a.getPackageName());
                        upSendMsgReq.setMessageId(remoteMessage.getMessageId());
                        upSendMsgReq.setTo(remoteMessage.getTo());
                        upSendMsgReq.setData(remoteMessage.getData());
                        upSendMsgReq.setMessageType(remoteMessage.getMessageType());
                        upSendMsgReq.setTtl(remoteMessage.getTtl());
                        upSendMsgReq.setCollapseKey(remoteMessage.getCollapseKey());
                        upSendMsgReq.setSendMode(remoteMessage.getSendMode());
                        upSendMsgReq.setReceiptMode(remoteMessage.getReceiptMode());
                        if (AbstractC5048c.m14293b()) {
                            this.f11576b.doWrite(new BaseVoidTask("push.sendMessage", JsonUtil.createJsonString(upSendMsgReq), reportEntry));
                            return;
                        } else {
                            m14316a(upSendMsgReq, reportEntry);
                            return;
                        }
                    }
                    HMSLog.m14112e("HmsMessaging", "Mandatory parameter 'data' missing");
                    PushBiUtil.reportExit(this.f11575a, "push.sendMessage", reportEntry, ErrorEnum.ERROR_ARGUMENTS_INVALID);
                    throw new IllegalArgumentException("Mandatory parameter 'data' missing");
                }
                HMSLog.m14112e("HmsMessaging", "Mandatory parameter 'message_id' missing");
                PushBiUtil.reportExit(this.f11575a, "push.sendMessage", reportEntry, ErrorEnum.ERROR_ARGUMENTS_INVALID);
                throw new IllegalArgumentException("Mandatory parameter 'message_id' missing");
            }
            HMSLog.m14112e("HmsMessaging", "Mandatory parameter 'to' missing");
            PushBiUtil.reportExit(this.f11575a, "push.sendMessage", reportEntry, ErrorEnum.ERROR_ARGUMENTS_INVALID);
            throw new IllegalArgumentException("Mandatory parameter 'to' missing");
        }
        HMSLog.m14112e("HmsMessaging", "Message sent failed:" + m14186a.getExternalCode() + ':' + m14186a.getMessage());
        PushBiUtil.reportExit(this.f11575a, "push.sendMessage", reportEntry, m14186a);
        throw new UnsupportedOperationException(m14186a.getMessage());
    }

    /* renamed from: a */
    private Task<Void> m14314a(boolean z) {
        String reportEntry = PushBiUtil.reportEntry(this.f11575a, "push.setNotifyFlag");
        if (AbstractC5048c.m14289d(this.f11575a) && !AbstractC5048c.m14293b()) {
            if (HwBuildEx.VERSION.EMUI_SDK_INT < 12) {
                HMSLog.m14112e("HmsMessaging", "operation not available on Huawei device with EMUI lower than 5.1");
                TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
                taskCompletionSource.setException(ErrorEnum.ERROR_OPERATION_NOT_SUPPORTED.toApiException());
                PushBiUtil.reportExit(this.f11575a, "push.setNotifyFlag", reportEntry, ErrorEnum.ERROR_OPERATION_NOT_SUPPORTED);
                return taskCompletionSource.getTask();
            } else if (AbstractC5048c.m14292b(this.f11575a) < 90101310) {
                HMSLog.m14110i("HmsMessaging", "turn on/off with broadcast v1");
                Context context = this.f11575a;
                Intent putExtra = new Intent("com.huawei.intent.action.SELF_SHOW_FLAG").putExtra("enalbeFlag", PushEncrypter.encrypterOld(context, this.f11575a.getPackageName() + "#" + z));
                putExtra.setPackage("android");
                return Tasks.callInBackground(new IntentCallable(this.f11575a, putExtra, reportEntry));
            } else if (AbstractC5048c.m14292b(this.f11575a) < 110118300) {
                HMSLog.m14110i("HmsMessaging", "turn on/off with broadcast v2");
                new PushPreferences(this.f11575a, "push_notify_flag").saveBoolean("notify_msg_enable", !z);
                Uri parse = Uri.parse("content://" + this.f11575a.getPackageName() + ".huawei.push.provider/push_notify_flag.xml");
                Intent intent = new Intent("com.huawei.android.push.intent.SDK_COMMAND");
                intent.putExtra("type", "enalbeFlag");
                intent.putExtra("pkgName", this.f11575a.getPackageName());
                intent.putExtra("url", parse);
                intent.setPackage("android");
                return Tasks.callInBackground(new IntentCallable(this.f11575a, intent, reportEntry));
            } else {
                HMSLog.m14110i("HmsMessaging", "turn on/off with broadcast v3");
                if (TextUtils.isEmpty(BaseUtils.getLocalToken(this.f11575a, null))) {
                    TaskCompletionSource taskCompletionSource2 = new TaskCompletionSource();
                    taskCompletionSource2.setException(ErrorEnum.ERROR_NO_TOKEN.toApiException());
                    return taskCompletionSource2.getTask();
                }
                new PushPreferences(this.f11575a, "push_notify_flag").saveBoolean("notify_msg_enable", !z);
                Intent intent2 = new Intent("com.huawei.intent.action.SELF_SHOW_FLAG");
                intent2.putExtra("enalbeFlag", z);
                intent2.putExtra("device_token", BaseUtils.getLocalToken(this.f11575a, null));
                intent2.putExtra("pkgName", this.f11575a.getPackageName());
                intent2.putExtra("uid", this.f11575a.getApplicationInfo().uid);
                intent2.setPackage("android");
                return Tasks.callInBackground(new IntentCallable(this.f11575a, intent2, reportEntry));
            }
        }
        HMSLog.m14110i("HmsMessaging", "turn on/off with AIDL");
        EnableNotifyReq enableNotifyReq = new EnableNotifyReq();
        enableNotifyReq.setPackageName(this.f11575a.getPackageName());
        enableNotifyReq.setEnable(z);
        return this.f11576b.doWrite(new BaseVoidTask("push.setNotifyFlag", JsonUtil.createJsonString(enableNotifyReq), reportEntry));
    }

    /* renamed from: a */
    private void m14316a(UpSendMsgReq upSendMsgReq, String str) {
        upSendMsgReq.setToken(BaseUtils.getLocalToken(this.f11575a, null));
        try {
            this.f11576b.doWrite(new SendUpStreamTask("push.sendMessage", JsonUtil.createJsonString(upSendMsgReq), str, upSendMsgReq.getPackageName(), upSendMsgReq.getMessageId()));
        } catch (Exception e) {
            if (e.getCause() instanceof ApiException) {
                PushBiUtil.reportExit(this.f11575a, "push.sendMessage", str, ((ApiException) e.getCause()).getStatusCode());
            } else {
                PushBiUtil.reportExit(this.f11575a, "push.sendMessage", str, ErrorEnum.ERROR_INTERNAL_ERROR);
            }
        }
    }
}
