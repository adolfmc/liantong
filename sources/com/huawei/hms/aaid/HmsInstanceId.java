package com.huawei.hms.aaid;

import android.app.Activity;
import android.content.Context;
import android.os.Looper;
import android.text.TextUtils;
import com.huawei.hmf.tasks.Task;
import com.huawei.hmf.tasks.TaskCompletionSource;
import com.huawei.hmf.tasks.Tasks;
import com.huawei.hms.aaid.constant.ErrorEnum;
import com.huawei.hms.aaid.entity.AAIDResult;
import com.huawei.hms.aaid.entity.DeleteTokenReq;
import com.huawei.hms.aaid.entity.TokenReq;
import com.huawei.hms.aaid.entity.TokenResult;
import com.huawei.hms.aaid.plugin.ProxyCenter;
import com.huawei.hms.aaid.task.PushClientBuilder;
import com.huawei.hms.aaid.utils.BaseUtils;
import com.huawei.hms.aaid.utils.PushPreferences;
import com.huawei.hms.api.Api;
import com.huawei.hms.common.ApiException;
import com.huawei.hms.common.HuaweiApi;
import com.huawei.hms.common.internal.AbstractClientBuilder;
import com.huawei.hms.common.internal.Preconditions;
import com.huawei.hms.opendevice.AAIDCallable;
import com.huawei.hms.opendevice.AaidUtils;
import com.huawei.hms.opendevice.CommFun;
import com.huawei.hms.opendevice.DeleteTokenTask;
import com.huawei.hms.opendevice.GetTokenTask;
import com.huawei.hms.opendevice.PushBiUtil;
import com.huawei.hms.opendevice.PushClientSp;
import com.huawei.hms.opendevice.SecretUtil;
import com.huawei.hms.support.log.HMSLog;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class HmsInstanceId {
    public static final String TAG = "HmsInstanceId";

    /* renamed from: a */
    private Context f10879a;

    /* renamed from: b */
    private PushPreferences f10880b;

    /* renamed from: c */
    private HuaweiApi<Api.ApiOptions.NoOptions> f10881c;

    private HmsInstanceId(Context context) {
        this.f10879a = context.getApplicationContext();
        this.f10880b = new PushPreferences(context, "aaid");
        Api api = new Api("HuaweiPush.API");
        if (context instanceof Activity) {
            this.f10881c = new HuaweiApi<>((Activity) context, (Api<Api.ApiOptions>) api, (Api.ApiOptions) null, (AbstractClientBuilder) new PushClientBuilder());
        } else {
            this.f10881c = new HuaweiApi<>(context, api, (Api.ApiOptions) null, new PushClientBuilder());
        }
        this.f10881c.setKitSdkVersion(61100100);
    }

    /* renamed from: a */
    private void m15345a(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (CommFun.m14382e(this.f10879a)) {
            String string = PushClientSp.m14368a(this.f10879a).getString("subjectId");
            if (TextUtils.isEmpty(string)) {
                PushClientSp.m14368a(this.f10879a).saveString("subjectId", str);
                return;
            } else if (string.contains(str)) {
                return;
            } else {
                PushClientSp m14368a = PushClientSp.m14368a(this.f10879a);
                m14368a.saveString("subjectId", string + "," + str);
                return;
            }
        }
        PushClientSp.m14368a(this.f10879a).removeKey("subjectId");
    }

    /* renamed from: b */
    private void m15344b() throws ApiException {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            throw ErrorEnum.ERROR_MAIN_THREAD.toApiException();
        }
    }

    public static HmsInstanceId getInstance(Context context) {
        Preconditions.checkNotNull(context);
        SecretUtil.m14344c(context);
        return new HmsInstanceId(context);
    }

    public void deleteAAID() throws ApiException {
        m15344b();
        try {
            if (this.f10880b.containsKey("aaid")) {
                this.f10880b.removeKey("aaid");
                this.f10880b.removeKey("creationTime");
                if (AaidUtils.m14391d(this.f10879a)) {
                    if (ProxyCenter.getProxy() != null) {
                        HMSLog.m14110i(TAG, "use proxy delete all token after delete AaId.");
                        ProxyCenter.getProxy().deleteAllToken(this.f10879a);
                        return;
                    }
                    DeleteTokenReq m14400a = AaidUtils.m14400a(this.f10879a);
                    m14400a.setDeleteType(1);
                    m14400a.setMultiSender(false);
                    m15347a(m14400a, 1);
                    BaseUtils.deleteAllTokenCache(this.f10879a);
                }
            }
        } catch (ApiException e) {
            throw e;
        } catch (Exception unused) {
            throw ErrorEnum.ERROR_INTERNAL_ERROR.toApiException();
        }
    }

    public void deleteToken(String str, String str2) throws ApiException {
        m15344b();
        m15348a();
        DeleteTokenReq m14398a = AaidUtils.m14398a(this.f10879a, str, str2);
        m14398a.setMultiSender(false);
        m15347a(m14398a, 1);
    }

    public Task<AAIDResult> getAAID() {
        try {
            return Tasks.callInBackground(new AAIDCallable(this.f10879a.getApplicationContext()));
        } catch (Exception unused) {
            TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
            taskCompletionSource.setException(ErrorEnum.ERROR_INTERNAL_ERROR.toApiException());
            return taskCompletionSource.getTask();
        }
    }

    public long getCreationTime() {
        try {
            if (!this.f10880b.containsKey("creationTime")) {
                getAAID();
            }
            return this.f10880b.getLong("creationTime");
        } catch (Exception unused) {
            return 0L;
        }
    }

    public String getId() {
        return AaidUtils.m14396b(this.f10879a);
    }

    @Deprecated
    public String getToken() {
        try {
            return getToken(null, null);
        } catch (Exception unused) {
            return null;
        }
    }

    public String getToken(String str, String str2) throws ApiException {
        m15344b();
        m15348a();
        TokenReq m14394b = AaidUtils.m14394b(this.f10879a, null, str2);
        m14394b.setAaid(getId());
        m14394b.setMultiSender(false);
        PushClientSp.m14368a(this.f10879a).saveString(this.f10879a.getPackageName(), "1");
        return m15346a(m14394b, 1);
    }

    public void deleteToken(String str) throws ApiException {
        m15344b();
        m15348a();
        if (!TextUtils.isEmpty(str)) {
            String m14392c = AaidUtils.m14392c(this.f10879a);
            if (!TextUtils.isEmpty(m14392c)) {
                if (str.equals(m14392c)) {
                    deleteToken(null, null);
                    return;
                }
                DeleteTokenReq m14399a = AaidUtils.m14399a(this.f10879a, str);
                m14399a.setMultiSender(true);
                m15347a(m14399a, 2);
                return;
            }
            throw ErrorEnum.ERROR_MISSING_PROJECT_ID.toApiException();
        }
        throw ErrorEnum.ERROR_ARGUMENTS_INVALID.toApiException();
    }

    public String getToken(String str) throws ApiException {
        m15344b();
        m15348a();
        if (!TextUtils.isEmpty(str)) {
            String m14392c = AaidUtils.m14392c(this.f10879a);
            if (!TextUtils.isEmpty(m14392c)) {
                if (str.equals(m14392c)) {
                    return getToken(null, null);
                }
                TokenReq m14395b = AaidUtils.m14395b(this.f10879a, str);
                m14395b.setAaid(getId());
                m14395b.setMultiSender(true);
                return m15346a(m14395b, 2);
            }
            throw ErrorEnum.ERROR_MISSING_PROJECT_ID.toApiException();
        }
        throw ErrorEnum.ERROR_ARGUMENTS_INVALID.toApiException();
    }

    /* renamed from: a */
    private String m15346a(TokenReq tokenReq, int i) throws ApiException {
        if (ProxyCenter.getProxy() != null) {
            HMSLog.m14110i(TAG, "use proxy get token, please check HmsMessageService.onNewToken receive result.");
            ProxyCenter.getProxy().getToken(this.f10879a, tokenReq.getSubjectId(), null);
            return null;
        }
        m15345a(tokenReq.getSubjectId());
        String m14373a = PushBiUtil.m14373a(this.f10879a, "push.gettoken");
        try {
            String str = TAG;
            HMSLog.m14115d(str, "getToken req :" + tokenReq.toString());
            GetTokenTask getTokenTask = new GetTokenTask("push.gettoken", tokenReq, this.f10879a, m14373a);
            getTokenTask.setApiLevel(i);
            return ((TokenResult) Tasks.await(this.f10881c.doWrite(getTokenTask))).getToken();
        } catch (Exception e) {
            if (e.getCause() instanceof ApiException) {
                ApiException apiException = (ApiException) e.getCause();
                PushBiUtil.m14371a(this.f10879a, "push.gettoken", m14373a, apiException.getStatusCode());
                throw apiException;
            }
            Context context = this.f10879a;
            ErrorEnum errorEnum = ErrorEnum.ERROR_INTERNAL_ERROR;
            PushBiUtil.m14370a(context, "push.gettoken", m14373a, errorEnum);
            throw errorEnum.toApiException();
        }
    }

    /* renamed from: a */
    private void m15347a(DeleteTokenReq deleteTokenReq, int i) throws ApiException {
        String subjectId = deleteTokenReq.getSubjectId();
        if (ProxyCenter.getProxy() != null) {
            HMSLog.m14110i(TAG, "use proxy delete token");
            ProxyCenter.getProxy().deleteToken(this.f10879a, subjectId, null);
            return;
        }
        String m14373a = PushBiUtil.m14373a(this.f10879a, "push.deletetoken");
        try {
            String m14365b = PushClientSp.m14368a(this.f10879a).m14365b(subjectId);
            if (deleteTokenReq.isMultiSender() && (TextUtils.isEmpty(m14365b) || m14365b.equals(PushClientSp.m14368a(this.f10879a).m14365b(null)))) {
                PushClientSp.m14368a(this.f10879a).removeKey(subjectId);
                HMSLog.m14110i(TAG, "The local subject token is null");
                return;
            }
            deleteTokenReq.setToken(m14365b);
            DeleteTokenTask deleteTokenTask = new DeleteTokenTask("push.deletetoken", deleteTokenReq, m14373a);
            deleteTokenTask.setApiLevel(i);
            Tasks.await(this.f10881c.doWrite(deleteTokenTask));
            PushClientSp.m14368a(this.f10879a).m14363c(subjectId);
        } catch (Exception e) {
            if (e.getCause() instanceof ApiException) {
                ApiException apiException = (ApiException) e.getCause();
                PushBiUtil.m14371a(this.f10879a, "push.deletetoken", m14373a, apiException.getStatusCode());
                throw apiException;
            }
            Context context = this.f10879a;
            ErrorEnum errorEnum = ErrorEnum.ERROR_INTERNAL_ERROR;
            PushBiUtil.m14370a(context, "push.deletetoken", m14373a, errorEnum);
            throw errorEnum.toApiException();
        }
    }

    /* renamed from: a */
    private void m15348a() throws ApiException {
        if (BaseUtils.getProxyInit(this.f10879a) && ProxyCenter.getProxy() == null && !BaseUtils.isMainProc(this.f10879a)) {
            HMSLog.m14112e(TAG, "Operations in child processes are not supported.");
            throw ErrorEnum.ERROR_OPER_IN_CHILD_PROCESS.toApiException();
        }
    }
}
