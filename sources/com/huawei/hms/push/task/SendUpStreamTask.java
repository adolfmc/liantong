package com.huawei.hms.push.task;

import android.content.Intent;
import android.os.Bundle;
import com.huawei.hmf.tasks.TaskCompletionSource;
import com.huawei.hms.aaid.constant.ErrorEnum;
import com.huawei.hms.aaid.task.PushClient;
import com.huawei.hms.common.ApiException;
import com.huawei.hms.common.internal.ResponseErrorCode;
import com.huawei.hms.common.internal.TaskApiCall;
import com.huawei.hms.push.C5051p;
import com.huawei.hms.push.utils.PushBiUtil;
import com.huawei.hms.support.api.client.Status;
import com.huawei.hms.support.log.HMSLog;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class SendUpStreamTask extends TaskApiCall<PushClient, BaseVoidTask> {

    /* renamed from: a */
    private String f11680a;

    /* renamed from: b */
    private String f11681b;

    public SendUpStreamTask(String str, String str2, String str3, String str4, String str5) {
        super(str, str2, str3);
        this.f11680a = str4;
        this.f11681b = str5;
    }

    /* renamed from: a */
    private void m14185a(PushClient pushClient, ResponseErrorCode responseErrorCode) {
        HMSLog.m14110i("SendUpStreamTask", "receive upstream, msgId :" + this.f11681b + " , packageName = " + this.f11680a + " , errorCode = " + responseErrorCode.getErrorCode());
        Intent intent = new Intent("com.huawei.push.action.MESSAGING_EVENT");
        intent.setPackage(this.f11680a);
        Bundle bundle = new Bundle();
        bundle.putString("message_id", this.f11681b);
        bundle.putInt("error", responseErrorCode.getErrorCode());
        if (ErrorEnum.SUCCESS.getInternalCode() == responseErrorCode.getErrorCode()) {
            bundle.putString("message_type", "sent_message");
        } else {
            bundle.putString("message_type", "send_error");
        }
        if (new C5051p().m14200a(pushClient.getContext(), bundle, intent)) {
            HMSLog.m14110i("SendUpStreamTask", "receive upstream, start service success");
            PushBiUtil.reportExit(pushClient.getContext(), getUri(), responseErrorCode);
            return;
        }
        HMSLog.m14109w("SendUpStreamTask", "receive upstream, start service failed");
        PushBiUtil.reportExit(pushClient.getContext(), getUri(), responseErrorCode.getTransactionId(), ErrorEnum.ERROR_BIND_SERVICE_SELF_MAPPING);
    }

    @Override // com.huawei.hms.common.internal.TaskApiCall
    public int getMinApkVersion() {
        return 40003000;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.hms.common.internal.TaskApiCall
    public void doExecute(PushClient pushClient, ResponseErrorCode responseErrorCode, String str, TaskCompletionSource<BaseVoidTask> taskCompletionSource) {
        if (responseErrorCode.getErrorCode() == 0) {
            HMSLog.m14110i("SendUpStreamTask", "send up stream task,Operate succeed");
            taskCompletionSource.setResult(null);
        } else {
            HMSLog.m14112e("SendUpStreamTask", "send up stream task,Operate failed with ret=" + responseErrorCode.getErrorCode());
            ErrorEnum fromCode = ErrorEnum.fromCode(responseErrorCode.getErrorCode());
            if (fromCode != ErrorEnum.ERROR_UNKNOWN) {
                taskCompletionSource.setException(fromCode.toApiException());
            } else {
                taskCompletionSource.setException(new ApiException(new Status(responseErrorCode.getErrorCode(), responseErrorCode.getErrorReason())));
            }
        }
        m14185a(pushClient, responseErrorCode);
    }
}
