package com.huawei.hms.opendevice;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hmf.tasks.TaskCompletionSource;
import com.huawei.hms.aaid.HmsInstanceId;
import com.huawei.hms.aaid.constant.ErrorEnum;
import com.huawei.hms.aaid.entity.TokenReq;
import com.huawei.hms.aaid.entity.TokenResp;
import com.huawei.hms.aaid.entity.TokenResult;
import com.huawei.hms.aaid.task.PushClient;
import com.huawei.hms.aaid.threads.AsyncExec;
import com.huawei.hms.common.ApiException;
import com.huawei.hms.common.internal.ResponseErrorCode;
import com.huawei.hms.common.internal.TaskApiCall;
import com.huawei.hms.support.api.client.Status;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.utils.JsonUtil;

/* renamed from: com.huawei.hms.opendevice.g */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class GetTokenTask extends TaskApiCall<PushClient, TokenResult> {

    /* renamed from: a */
    private Context f11541a;

    /* renamed from: b */
    private TokenReq f11542b;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: GetTokenTask.java */
    /* renamed from: com.huawei.hms.opendevice.g$a */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class RunnableC5041a implements Runnable {

        /* renamed from: a */
        final /* synthetic */ String f11543a;

        /* renamed from: b */
        final /* synthetic */ String f11544b;

        RunnableC5041a(String str, String str2) {
            this.f11543a = str;
            this.f11544b = str2;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (PushClientSp.m14368a(GetTokenTask.this.f11541a).m14365b(this.f11543a).equals(this.f11544b)) {
                return;
            }
            HMSLog.m14110i(HmsInstanceId.TAG, "receive a new token, refresh the local token");
            PushClientSp.m14368a(GetTokenTask.this.f11541a).m14364b(this.f11543a, this.f11544b);
        }
    }

    public GetTokenTask(String str, TokenReq tokenReq, Context context, String str2) {
        super(str, JsonUtil.createJsonString(tokenReq), str2);
        this.f11541a = context;
        this.f11542b = tokenReq;
    }

    @Override // com.huawei.hms.common.internal.TaskApiCall
    public int getMinApkVersion() {
        return this.f11542b.isMultiSender() ? 50004300 : 30000000;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.hms.common.internal.TaskApiCall
    /* renamed from: a */
    public void doExecute(PushClient pushClient, ResponseErrorCode responseErrorCode, String str, TaskCompletionSource<TokenResult> taskCompletionSource) {
        if (responseErrorCode.getErrorCode() != 0) {
            String str2 = HmsInstanceId.TAG;
            HMSLog.m14112e(str2, "TokenTask failed, ErrorCode:" + responseErrorCode.getErrorCode());
            m14379a(responseErrorCode, taskCompletionSource);
        } else {
            TokenResp tokenResp = (TokenResp) JsonUtil.jsonToEntity(str, new TokenResp());
            ErrorEnum fromCode = ErrorEnum.fromCode(tokenResp.getRetCode());
            if (fromCode != ErrorEnum.SUCCESS) {
                taskCompletionSource.setException(fromCode.toApiException());
                String str3 = HmsInstanceId.TAG;
                HMSLog.m14112e(str3, "TokenTask failed, StatusCode:" + fromCode.getExternalCode());
            } else {
                TokenResult tokenResult = new TokenResult();
                tokenResult.setToken(tokenResp.getToken());
                tokenResult.setBelongId(tokenResp.getBelongId());
                tokenResult.setRetCode(ErrorEnum.fromCode(tokenResp.getRetCode()).getExternalCode());
                taskCompletionSource.setResult(tokenResult);
                String token = tokenResp.getToken();
                if (TextUtils.isEmpty(token)) {
                    HMSLog.m14110i(HmsInstanceId.TAG, "GetTokenTask receive an empty token, please check onNewToken callback method.");
                    PushBiUtil.m14372a(pushClient.getContext(), getUri(), responseErrorCode);
                    return;
                }
                m14377a(token, this.f11542b.getSubjectId());
            }
        }
        PushBiUtil.m14372a(pushClient.getContext(), getUri(), responseErrorCode);
    }

    /* renamed from: a */
    private void m14379a(ResponseErrorCode responseErrorCode, TaskCompletionSource<TokenResult> taskCompletionSource) {
        ErrorEnum fromCode = ErrorEnum.fromCode(responseErrorCode.getErrorCode());
        if (fromCode != ErrorEnum.ERROR_UNKNOWN) {
            taskCompletionSource.setException(fromCode.toApiException());
        } else {
            taskCompletionSource.setException(new ApiException(new Status(responseErrorCode.getErrorCode(), responseErrorCode.getErrorReason())));
        }
    }

    /* renamed from: a */
    private void m14377a(String str, String str2) {
        AsyncExec.submitSeqIO(new RunnableC5041a(str2, str));
    }
}
