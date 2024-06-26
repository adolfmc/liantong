package com.huawei.hms.push;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.hmf.tasks.Task;
import com.huawei.hmf.tasks.TaskCompletionSource;
import com.huawei.hms.aaid.constant.ErrorEnum;
import com.huawei.hms.aaid.task.PushClientBuilder;
import com.huawei.hms.aaid.utils.PushPreferences;
import com.huawei.hms.api.Api;
import com.huawei.hms.common.ApiException;
import com.huawei.hms.common.HuaweiApi;
import com.huawei.hms.common.internal.AbstractClientBuilder;
import com.huawei.hms.common.internal.Preconditions;
import com.huawei.hms.p224ui.SafeBundle;
import com.huawei.hms.push.task.AttributionReportTask;
import com.huawei.hms.push.utils.PushBiUtil;
import com.huawei.hms.support.api.entity.push.AttributionReportReq;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.utils.JsonUtil;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class AttributionReporter {
    public static final String APP_VERSION = "appVersion";
    public static final String SYSTEM_PERMISSION = "permission";

    /* renamed from: a */
    private HuaweiApi<Api.ApiOptions.NoOptions> f11563a;

    /* renamed from: b */
    private Context f11564b;

    private AttributionReporter(Context context) {
        Preconditions.checkNotNull(context);
        this.f11564b = context;
        Api api = new Api("HuaweiPush.API");
        if (context instanceof Activity) {
            this.f11563a = new HuaweiApi<>((Activity) context, (Api<Api.ApiOptions>) api, (Api.ApiOptions) null, (AbstractClientBuilder) new PushClientBuilder());
        } else {
            this.f11563a = new HuaweiApi<>(context, api, (Api.ApiOptions) null, new PushClientBuilder());
        }
        this.f11563a.setKitSdkVersion(61100300);
    }

    /* renamed from: a */
    private Task<Void> m14329a(AttributionEvent attributionEvent, Bundle bundle) {
        TaskCompletionSource taskCompletionSource;
        int externalCode;
        String reportEntry = PushBiUtil.reportEntry(this.f11564b, "push.analysisReport");
        if (bundle != null && attributionEvent != null) {
            try {
                if (AbstractC5048c.m14289d(this.f11564b)) {
                    long j = new PushPreferences(this.f11564b, "hwpush_local_config").getLong("analysis_last_failed_time");
                    if (j > 0 && System.currentTimeMillis() - j < 86400000) {
                        throw ErrorEnum.ERROR_NOT_IN_SERVICE.toApiException();
                    }
                    return this.f11563a.doWrite(new AttributionReportTask("push.analysisReport", JsonUtil.createJsonString(m14328a(attributionEvent, new SafeBundle(bundle))), reportEntry));
                }
                throw ErrorEnum.ERROR_OPERATION_NOT_SUPPORTED.toApiException();
            } catch (ApiException e) {
                TaskCompletionSource taskCompletionSource2 = new TaskCompletionSource();
                taskCompletionSource2.setException(e);
                externalCode = e.getStatusCode();
                taskCompletionSource = taskCompletionSource2;
                PushBiUtil.reportExit(this.f11564b, "push.analysisReport", reportEntry, externalCode);
                return taskCompletionSource.getTask();
            } catch (Exception unused) {
                taskCompletionSource = new TaskCompletionSource();
                taskCompletionSource.setException(ErrorEnum.ERROR_INTERNAL_ERROR.toApiException());
                externalCode = ErrorEnum.ERROR_INTERNAL_ERROR.getExternalCode();
                PushBiUtil.reportExit(this.f11564b, "push.analysisReport", reportEntry, externalCode);
                return taskCompletionSource.getTask();
            }
        }
        PushBiUtil.reportExit(this.f11564b, "push.analysisReport", reportEntry, ErrorEnum.ERROR_ARGUMENTS_INVALID);
        HMSLog.m14112e("AttributionReporter", "Invalid argument: argument should not be null");
        throw new IllegalArgumentException("Invalid argument: argument should not be null");
    }

    public static AttributionReporter getInstance(Context context) {
        return new AttributionReporter(context);
    }

    public Task<Void> report(AttributionEvent attributionEvent, Bundle bundle) {
        return m14329a(attributionEvent, bundle);
    }

    /* renamed from: a */
    private AttributionReportReq m14328a(AttributionEvent attributionEvent, SafeBundle safeBundle) throws ApiException {
        Bundle bundle = safeBundle.getBundle().getBundle("analysisExt");
        if (bundle != null && !bundle.isEmpty()) {
            String string = bundle.getString("msgId");
            if (!TextUtils.isEmpty(string)) {
                String string2 = bundle.getString("hsId");
                if (!TextUtils.isEmpty(string2)) {
                    AttributionReportReq attributionReportReq = new AttributionReportReq();
                    attributionReportReq.setCampaignId(bundle.getString("cid"));
                    attributionReportReq.setMsgId(string);
                    attributionReportReq.setHaStorageId(string2);
                    attributionReportReq.setEventId(attributionEvent.getEventId());
                    attributionReportReq.setPkgName(this.f11564b.getPackageName());
                    if (attributionEvent.equals(AttributionEvent.PERMISSION_GRANTED) || attributionEvent.equals(AttributionEvent.PERMISSION_DENIED)) {
                        String string3 = safeBundle.getString(SYSTEM_PERMISSION);
                        if (!TextUtils.isEmpty(string3) && string3.startsWith("android.permission")) {
                            attributionReportReq.setReqPermission(string3);
                        } else {
                            throw ErrorEnum.ERROR_ARGUMENTS_INVALID.toApiException();
                        }
                    }
                    attributionReportReq.setTimeStamp(System.currentTimeMillis());
                    attributionReportReq.setAppVersion(safeBundle.getString("appVersion"));
                    return attributionReportReq;
                }
                throw ErrorEnum.ERROR_ARGUMENTS_INVALID.toApiException();
            }
            throw ErrorEnum.ERROR_ARGUMENTS_INVALID.toApiException();
        }
        throw ErrorEnum.ERROR_ARGUMENTS_INVALID.toApiException();
    }
}
