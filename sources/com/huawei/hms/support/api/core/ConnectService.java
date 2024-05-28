package com.huawei.hms.support.api.core;

import android.text.TextUtils;
import com.huawei.hms.core.aidl.IMessageEntity;
import com.huawei.hms.support.api.PendingResultImpl;
import com.huawei.hms.support.api.ResolvePendingResult;
import com.huawei.hms.support.api.ResolveResult;
import com.huawei.hms.support.api.client.ApiClient;
import com.huawei.hms.support.api.client.InnerPendingResult;
import com.huawei.hms.support.api.client.PendingResult;
import com.huawei.hms.support.api.client.Status;
import com.huawei.hms.support.api.entity.core.CheckConnectInfo;
import com.huawei.hms.support.api.entity.core.CheckConnectResp;
import com.huawei.hms.support.api.entity.core.ConnectInfo;
import com.huawei.hms.support.api.entity.core.ConnectResp;
import com.huawei.hms.support.api.entity.core.DisconnectInfo;
import com.huawei.hms.support.api.entity.core.DisconnectResp;
import com.huawei.hms.support.api.entity.core.JosGetNoticeReq;
import com.huawei.hms.support.api.entity.core.JosGetNoticeResp;
import com.huawei.hms.support.log.HMSLog;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public final class ConnectService {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.huawei.hms.support.api.core.ConnectService$a */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class C5064a extends PendingResultImpl<ResolveResult<ConnectResp>, ConnectResp> {
        C5064a(ApiClient apiClient, String str, IMessageEntity iMessageEntity) {
            super(apiClient, str, iMessageEntity);
        }

        @Override // com.huawei.hms.support.api.PendingResultImpl
        /* renamed from: a */
        public ResolveResult<ConnectResp> onComplete(ConnectResp connectResp) {
            ResolveResult<ConnectResp> resolveResult = new ResolveResult<>(connectResp);
            resolveResult.setStatus(Status.SUCCESS);
            HMSLog.m14115d("connectservice", "connect - onComplete: success");
            return resolveResult;
        }

        @Override // com.huawei.hms.support.api.PendingResultImpl
        public boolean checkApiClient(ApiClient apiClient) {
            return apiClient != null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.huawei.hms.support.api.core.ConnectService$b */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class C5065b extends PendingResultImpl<ResolveResult<ConnectResp>, ConnectResp> {
        C5065b(ApiClient apiClient, String str, IMessageEntity iMessageEntity) {
            super(apiClient, str, iMessageEntity);
        }

        @Override // com.huawei.hms.support.api.PendingResultImpl
        /* renamed from: a */
        public ResolveResult<ConnectResp> onComplete(ConnectResp connectResp) {
            ResolveResult<ConnectResp> resolveResult = new ResolveResult<>(connectResp);
            resolveResult.setStatus(Status.SUCCESS);
            HMSLog.m14115d("connectservice", "forceConnect - onComplete: success");
            return resolveResult;
        }

        @Override // com.huawei.hms.support.api.PendingResultImpl
        public boolean checkApiClient(ApiClient apiClient) {
            return apiClient != null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.huawei.hms.support.api.core.ConnectService$c */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class C5066c extends PendingResultImpl<ResolveResult<JosGetNoticeResp>, JosGetNoticeResp> {
        C5066c(ApiClient apiClient, String str, IMessageEntity iMessageEntity) {
            super(apiClient, str, iMessageEntity);
        }

        @Override // com.huawei.hms.support.api.PendingResultImpl
        /* renamed from: a */
        public ResolveResult<JosGetNoticeResp> onComplete(JosGetNoticeResp josGetNoticeResp) {
            if (josGetNoticeResp == null) {
                HMSLog.m14112e("connectservice", "JosNoticeResp is null");
                return null;
            }
            HMSLog.m14110i("connectservice", "josNoticeResp status code :" + josGetNoticeResp.getStatusCode());
            ResolveResult<JosGetNoticeResp> resolveResult = new ResolveResult<>(josGetNoticeResp);
            resolveResult.setStatus(Status.SUCCESS);
            return resolveResult;
        }
    }

    private ConnectService() {
    }

    public static InnerPendingResult<ResolveResult<CheckConnectResp>> checkconnect(ApiClient apiClient, CheckConnectInfo checkConnectInfo) {
        return ResolvePendingResult.build(apiClient, "core.checkconnect", checkConnectInfo, CheckConnectResp.class);
    }

    public static PendingResult<ResolveResult<ConnectResp>> connect(ApiClient apiClient, ConnectInfo connectInfo) {
        return new C5064a(apiClient, "core.connect", connectInfo);
    }

    public static ResolvePendingResult<DisconnectResp> disconnect(ApiClient apiClient, DisconnectInfo disconnectInfo) {
        return ResolvePendingResult.build(apiClient, "core.disconnect", disconnectInfo, DisconnectResp.class);
    }

    public static PendingResult<ResolveResult<ConnectResp>> forceConnect(ApiClient apiClient, ConnectInfo connectInfo) {
        return new C5065b(apiClient, "core.foreconnect", connectInfo);
    }

    public static PendingResult<ResolveResult<JosGetNoticeResp>> getNotice(ApiClient apiClient, int i, String str) {
        JosGetNoticeReq josGetNoticeReq = new JosGetNoticeReq();
        josGetNoticeReq.setNoticeType(i);
        josGetNoticeReq.setHmsSdkVersionName(str);
        if (!TextUtils.isEmpty(apiClient.getCpID())) {
            josGetNoticeReq.setCpID(apiClient.getCpID());
        }
        return new C5066c(apiClient, "core.getNoticeIntent", josGetNoticeReq);
    }
}
