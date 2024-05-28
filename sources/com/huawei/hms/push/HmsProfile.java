package com.huawei.hms.push;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import com.huawei.agconnect.config.AGConnectServicesConfig;
import com.huawei.hmf.tasks.Task;
import com.huawei.hmf.tasks.TaskCompletionSource;
import com.huawei.hms.aaid.constant.ErrorEnum;
import com.huawei.hms.aaid.task.PushClientBuilder;
import com.huawei.hms.api.Api;
import com.huawei.hms.common.ApiException;
import com.huawei.hms.common.HuaweiApi;
import com.huawei.hms.common.internal.AbstractClientBuilder;
import com.huawei.hms.common.internal.Preconditions;
import com.huawei.hms.push.task.ProfileTask;
import com.huawei.hms.push.utils.PushBiUtil;
import com.huawei.hms.support.api.entity.push.ProfileReq;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.utils.JsonUtil;
import com.huawei.secure.android.common.encrypt.hash.SHA;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class HmsProfile {
    public static final int CUSTOM_PROFILE = 2;
    public static final int HUAWEI_PROFILE = 1;

    /* renamed from: c */
    private static final String f11577c = "HmsProfile";

    /* renamed from: a */
    private Context f11578a;

    /* renamed from: b */
    private HuaweiApi<Api.ApiOptions.NoOptions> f11579b;

    private HmsProfile(Context context) {
        this.f11578a = null;
        Preconditions.checkNotNull(context);
        this.f11578a = context;
        Api api = new Api("HuaweiPush.API");
        if (context instanceof Activity) {
            this.f11579b = new HuaweiApi<>((Activity) context, (Api<Api.ApiOptions>) api, (Api.ApiOptions) null, (AbstractClientBuilder) new PushClientBuilder());
        } else {
            this.f11579b = new HuaweiApi<>(context, api, (Api.ApiOptions) null, new PushClientBuilder());
        }
        this.f11579b.setKitSdkVersion(61100300);
    }

    /* renamed from: a */
    private Task<Void> m14313a(int i, String str, int i2, String str2) {
        if (!isSupportProfile()) {
            TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
            taskCompletionSource.setException(ErrorEnum.ERROR_OPERATION_NOT_SUPPORTED.toApiException());
            return taskCompletionSource.getTask();
        }
        if (!TextUtils.isEmpty(str)) {
            String m14312a = m14312a(this.f11578a);
            if (TextUtils.isEmpty(m14312a)) {
                HMSLog.m14110i(f11577c, "agc connect services config missing project id.");
                TaskCompletionSource taskCompletionSource2 = new TaskCompletionSource();
                taskCompletionSource2.setException(ErrorEnum.ERROR_MISSING_PROJECT_ID.toApiException());
                return taskCompletionSource2.getTask();
            } else if (str.equals(m14312a)) {
                str = "";
            }
        }
        ProfileReq profileReq = new ProfileReq();
        if (i == 0) {
            profileReq.setOperation(0);
            profileReq.setType(i2);
        } else {
            profileReq.setOperation(1);
        }
        String reportEntry = PushBiUtil.reportEntry(this.f11578a, "push.profile");
        try {
            profileReq.setSubjectId(str);
            profileReq.setProfileId(SHA.sha256Encrypt(str2));
            profileReq.setPkgName(this.f11578a.getPackageName());
            return this.f11579b.doWrite(new ProfileTask("push.profile", JsonUtil.createJsonString(profileReq), reportEntry));
        } catch (Exception e) {
            if (e.getCause() instanceof ApiException) {
                TaskCompletionSource taskCompletionSource3 = new TaskCompletionSource();
                ApiException apiException = (ApiException) e.getCause();
                taskCompletionSource3.setException(apiException);
                PushBiUtil.reportExit(this.f11578a, "push.profile", reportEntry, apiException.getStatusCode());
                return taskCompletionSource3.getTask();
            }
            TaskCompletionSource taskCompletionSource4 = new TaskCompletionSource();
            PushBiUtil.reportExit(this.f11578a, "push.profile", reportEntry, ErrorEnum.ERROR_INTERNAL_ERROR);
            taskCompletionSource4.setException(ErrorEnum.ERROR_INTERNAL_ERROR.toApiException());
            return taskCompletionSource4.getTask();
        }
    }

    /* renamed from: b */
    private boolean m14311b(Context context) {
        return AbstractC5048c.m14292b(context) >= 110001400;
    }

    public static HmsProfile getInstance(Context context) {
        return new HmsProfile(context);
    }

    public Task<Void> addProfile(int i, String str) {
        return addProfile("", i, str);
    }

    public Task<Void> deleteProfile(String str) {
        return deleteProfile("", str);
    }

    public boolean isSupportProfile() {
        if (AbstractC5048c.m14289d(this.f11578a)) {
            if (AbstractC5048c.m14291c()) {
                HMSLog.m14110i(f11577c, "current EMUI version below 9.1, not support profile operation.");
                return false;
            } else if (m14311b(this.f11578a)) {
                return true;
            } else {
                HMSLog.m14110i(f11577c, "current HwPushService.apk version below 11.0.1.400,please upgrade your HwPushService.apk version.");
                return false;
            }
        }
        return true;
    }

    public Task<Void> addProfile(String str, int i, String str2) {
        if (i != 1 && i != 2) {
            HMSLog.m14110i(f11577c, "add profile type undefined.");
            TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
            taskCompletionSource.setException(ErrorEnum.ERROR_PUSH_ARGUMENTS_INVALID.toApiException());
            return taskCompletionSource.getTask();
        } else if (TextUtils.isEmpty(str2)) {
            HMSLog.m14110i(f11577c, "add profile params is empty.");
            TaskCompletionSource taskCompletionSource2 = new TaskCompletionSource();
            taskCompletionSource2.setException(ErrorEnum.ERROR_PUSH_ARGUMENTS_INVALID.toApiException());
            return taskCompletionSource2.getTask();
        } else {
            return m14313a(0, str, i, str2);
        }
    }

    public Task<Void> deleteProfile(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            HMSLog.m14112e(f11577c, "del profile params is empty.");
            TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
            taskCompletionSource.setException(ErrorEnum.ERROR_PUSH_ARGUMENTS_INVALID.toApiException());
            return taskCompletionSource.getTask();
        }
        return m14313a(1, str, -1, str2);
    }

    /* renamed from: a */
    private static String m14312a(Context context) {
        return AGConnectServicesConfig.fromContext(context).getString("client/project_id");
    }
}
