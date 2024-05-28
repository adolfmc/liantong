package com.huawei.hms.aaid;

import android.app.Activity;
import android.content.Context;
import com.huawei.hmf.tasks.Task;
import com.huawei.hmf.tasks.TaskCompletionSource;
import com.huawei.hms.aaid.constant.ErrorEnum;
import com.huawei.hms.aaid.entity.TokenReq;
import com.huawei.hms.aaid.entity.TokenResult;
import com.huawei.hms.aaid.plugin.ProxyCenter;
import com.huawei.hms.aaid.task.PushClientBuilder;
import com.huawei.hms.aaid.utils.PushPreferences;
import com.huawei.hms.api.Api;
import com.huawei.hms.common.ApiException;
import com.huawei.hms.common.HuaweiApi;
import com.huawei.hms.common.internal.AbstractClientBuilder;
import com.huawei.hms.common.internal.Preconditions;
import com.huawei.hms.opendevice.AaidUtils;
import com.huawei.hms.opendevice.GetTokenTask;
import com.huawei.hms.opendevice.PushBiUtil;
import com.huawei.hms.support.log.HMSLog;
import java.util.UUID;

@Deprecated
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class HmsInstanceIdEx {
    public static final String TAG = "HmsInstanceIdEx";

    /* renamed from: a */
    private Context f10882a;

    /* renamed from: b */
    private PushPreferences f10883b;

    /* renamed from: c */
    private HuaweiApi<Api.ApiOptions.NoOptions> f10884c;

    private HmsInstanceIdEx(Context context) {
        this.f10883b = null;
        this.f10882a = context;
        this.f10883b = new PushPreferences(context, "aaid");
        Api api = new Api("HuaweiPush.API");
        if (context instanceof Activity) {
            this.f10884c = new HuaweiApi<>((Activity) context, (Api<Api.ApiOptions>) api, (Api.ApiOptions) null, (AbstractClientBuilder) new PushClientBuilder());
        } else {
            this.f10884c = new HuaweiApi<>(context, api, (Api.ApiOptions) null, new PushClientBuilder());
        }
        this.f10884c.setKitSdkVersion(61100100);
    }

    /* renamed from: a */
    private String m15342a(String str) {
        return "creationTime" + str;
    }

    public static HmsInstanceIdEx getInstance(Context context) {
        Preconditions.checkNotNull(context);
        return new HmsInstanceIdEx(context);
    }

    public void deleteAAID(String str) throws ApiException {
        if (str != null) {
            try {
                if (this.f10883b.containsKey(str)) {
                    this.f10883b.removeKey(str);
                    this.f10883b.removeKey(m15342a(str));
                    return;
                }
                return;
            } catch (RuntimeException unused) {
                throw ErrorEnum.ERROR_INTERNAL_ERROR.toApiException();
            } catch (Exception unused2) {
                throw ErrorEnum.ERROR_INTERNAL_ERROR.toApiException();
            }
        }
        throw ErrorEnum.ERROR_ARGUMENTS_INVALID.toApiException();
    }

    public String getAAId(String str) throws ApiException {
        if (str != null) {
            try {
                if (this.f10883b.containsKey(str)) {
                    return this.f10883b.getString(str);
                }
                String uuid = UUID.randomUUID().toString();
                this.f10883b.saveString(str, uuid);
                this.f10883b.saveLong(m15342a(str), Long.valueOf(System.currentTimeMillis()));
                return uuid;
            } catch (RuntimeException unused) {
                throw ErrorEnum.ERROR_INTERNAL_ERROR.toApiException();
            } catch (Exception unused2) {
                throw ErrorEnum.ERROR_INTERNAL_ERROR.toApiException();
            }
        }
        throw ErrorEnum.ERROR_ARGUMENTS_INVALID.toApiException();
    }

    public long getCreationTime(String str) throws ApiException {
        if (str != null) {
            try {
                if (!this.f10883b.containsKey(m15342a(str))) {
                    getAAId(str);
                }
                return this.f10883b.getLong(m15342a(str));
            } catch (RuntimeException unused) {
                throw ErrorEnum.ERROR_INTERNAL_ERROR.toApiException();
            } catch (Exception unused2) {
                throw ErrorEnum.ERROR_INTERNAL_ERROR.toApiException();
            }
        }
        throw ErrorEnum.ERROR_ARGUMENTS_INVALID.toApiException();
    }

    public Task<TokenResult> getToken() {
        if (ProxyCenter.getProxy() != null) {
            try {
                HMSLog.m14110i(TAG, "use proxy get token, please check HmsMessageService.onNewToken receive result.");
                ProxyCenter.getProxy().getToken(this.f10882a, null, null);
                TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
                taskCompletionSource.setResult(new TokenResult());
                return taskCompletionSource.getTask();
            } catch (ApiException e) {
                return m15343a(e);
            } catch (Exception unused) {
                return m15343a(ErrorEnum.ERROR_INTERNAL_ERROR.toApiException());
            }
        }
        String m14373a = PushBiUtil.m14373a(this.f10882a, "push.gettoken");
        try {
            TokenReq m14394b = AaidUtils.m14394b(this.f10882a, null, null);
            m14394b.setAaid(HmsInstanceId.getInstance(this.f10882a).getId());
            return this.f10884c.doWrite(new GetTokenTask("push.gettoken", m14394b, this.f10882a, m14373a));
        } catch (RuntimeException unused2) {
            Context context = this.f10882a;
            ErrorEnum errorEnum = ErrorEnum.ERROR_INTERNAL_ERROR;
            PushBiUtil.m14370a(context, "push.gettoken", m14373a, errorEnum);
            return m15343a(errorEnum.toApiException());
        } catch (Exception unused3) {
            Context context2 = this.f10882a;
            ErrorEnum errorEnum2 = ErrorEnum.ERROR_INTERNAL_ERROR;
            PushBiUtil.m14370a(context2, "push.gettoken", m14373a, errorEnum2);
            return m15343a(errorEnum2.toApiException());
        }
    }

    /* renamed from: a */
    private Task<TokenResult> m15343a(Exception exc) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        taskCompletionSource.setException(exc);
        return taskCompletionSource.getTask();
    }
}
