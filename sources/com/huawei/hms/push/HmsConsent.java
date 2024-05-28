package com.huawei.hms.push;

import android.app.Activity;
import android.content.Context;
import com.huawei.hmf.tasks.Task;
import com.huawei.hmf.tasks.TaskCompletionSource;
import com.huawei.hms.aaid.constant.ErrorEnum;
import com.huawei.hms.aaid.task.PushClientBuilder;
import com.huawei.hms.api.Api;
import com.huawei.hms.common.ApiException;
import com.huawei.hms.common.HuaweiApi;
import com.huawei.hms.common.internal.AbstractClientBuilder;
import com.huawei.hms.common.internal.Preconditions;
import com.huawei.hms.push.task.ConsentTask;
import com.huawei.hms.push.utils.PushBiUtil;
import com.huawei.hms.support.api.entity.push.EnableConsentReq;
import com.huawei.hms.utils.JsonUtil;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class HmsConsent {

    /* renamed from: a */
    private HuaweiApi<Api.ApiOptions.NoOptions> f11567a;

    /* renamed from: b */
    private Context f11568b;

    private HmsConsent(Context context) {
        Preconditions.checkNotNull(context);
        this.f11568b = context;
        Api api = new Api("HuaweiPush.API");
        if (context instanceof Activity) {
            this.f11567a = new HuaweiApi<>((Activity) context, (Api<Api.ApiOptions>) api, (Api.ApiOptions) null, (AbstractClientBuilder) new PushClientBuilder());
        } else {
            this.f11567a = new HuaweiApi<>(context, api, (Api.ApiOptions) null, new PushClientBuilder());
        }
        this.f11567a.setKitSdkVersion(61100300);
    }

    /* renamed from: a */
    private Task<Void> m14327a(boolean z) {
        TaskCompletionSource taskCompletionSource;
        int externalCode;
        String reportEntry = PushBiUtil.reportEntry(this.f11568b, "push.consent");
        try {
            if (AbstractC5048c.m14289d(this.f11568b)) {
                EnableConsentReq enableConsentReq = new EnableConsentReq();
                enableConsentReq.setPackageName(this.f11568b.getPackageName());
                enableConsentReq.setEnable(z);
                return this.f11567a.doWrite(new ConsentTask("push.consent", JsonUtil.createJsonString(enableConsentReq), reportEntry));
            }
            throw ErrorEnum.ERROR_OPERATION_NOT_SUPPORTED.toApiException();
        } catch (ApiException e) {
            TaskCompletionSource taskCompletionSource2 = new TaskCompletionSource();
            taskCompletionSource2.setException(e);
            externalCode = e.getStatusCode();
            taskCompletionSource = taskCompletionSource2;
            PushBiUtil.reportExit(this.f11568b, "push.consent", reportEntry, externalCode);
            return taskCompletionSource.getTask();
        } catch (Exception unused) {
            taskCompletionSource = new TaskCompletionSource();
            taskCompletionSource.setException(ErrorEnum.ERROR_INTERNAL_ERROR.toApiException());
            externalCode = ErrorEnum.ERROR_INTERNAL_ERROR.getExternalCode();
            PushBiUtil.reportExit(this.f11568b, "push.consent", reportEntry, externalCode);
            return taskCompletionSource.getTask();
        }
    }

    public static HmsConsent getInstance(Context context) {
        return new HmsConsent(context);
    }

    public Task<Void> consentOff() {
        return m14327a(false);
    }

    public Task<Void> consentOn() {
        return m14327a(true);
    }
}
