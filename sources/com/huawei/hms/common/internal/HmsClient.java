package com.huawei.hms.common.internal;

import android.app.Activity;
import android.content.Context;
import android.os.Parcelable;
import android.text.TextUtils;
import com.huawei.hms.adapter.BaseAdapter;
import com.huawei.hms.common.internal.AnyClient;
import com.huawei.hms.common.internal.BaseHmsClient;
import com.huawei.hms.core.aidl.IMessageEntity;
import com.huawei.hms.support.log.HMSLog;
import java.lang.ref.WeakReference;
import org.json.JSONObject;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public abstract class HmsClient extends BaseHmsClient implements AnyClient {
    public HmsClient(Context context, ClientSettings clientSettings, BaseHmsClient.OnConnectionFailedListener onConnectionFailedListener, BaseHmsClient.ConnectionCallbacks connectionCallbacks) {
        super(context, clientSettings, onConnectionFailedListener, connectionCallbacks);
    }

    @Override // com.huawei.hms.common.internal.AnyClient
    public void post(IMessageEntity iMessageEntity, String str, AnyClient.CallBack callBack) {
        if (callBack == null) {
            HMSLog.m14112e("HmsClient", "callback is invalid, discard.");
            return;
        }
        if ((iMessageEntity instanceof RequestHeader) && str != null) {
            if (!isConnected()) {
                HMSLog.m14110i("HmsClient", "No connection now, the connection status:" + getConnectionStatus());
                if (getConnectionStatus() != 6) {
                    HMSLog.m14112e("HmsClient", "post failed for not connected.");
                    callBack.onCallback(new ResponseHeader(1, 907135001, "Not Connected"), new JSONObject().toString());
                    return;
                }
                HMSLog.m14110i("HmsClient", "in timeout-disconnect status, need to bind again.");
                m15128a();
            }
            RequestHeader requestHeader = (RequestHeader) iMessageEntity;
            HMSLog.m14110i("HmsClient", "post msg " + requestHeader);
            Activity cpActivity = getClientSettings().getCpActivity();
            boolean z = cpActivity == null;
            if (z) {
                HMSLog.m14110i("HmsClient", "Activity is null for " + getClientSettings().getAppID());
            }
            (z ? new BaseAdapter(this) : new BaseAdapter(this, cpActivity)).baseRequest(requestHeader.toJson(), str, requestHeader.getParcelable(), new C4897a(this, callBack));
            return;
        }
        HMSLog.m14112e("HmsClient", "arguments is invalid.");
        callBack.onCallback(new ResponseHeader(1, 907135000, "Args is invalid"), new JSONObject().toString());
    }

    public void updateSessionId(String str) {
        if (TextUtils.isEmpty(this.sessionId)) {
            this.sessionId = str;
        }
    }

    /* renamed from: com.huawei.hms.common.internal.HmsClient$a */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    static class C4897a implements BaseAdapter.BaseCallBack {

        /* renamed from: a */
        private final AnyClient.CallBack f11152a;

        /* renamed from: b */
        private final WeakReference<HmsClient> f11153b;

        C4897a(HmsClient hmsClient, AnyClient.CallBack callBack) {
            this.f11152a = callBack;
            this.f11153b = new WeakReference<>(hmsClient);
        }

        /* renamed from: a */
        private void m15109a(String str) {
            HmsClient hmsClient = this.f11153b.get();
            if (hmsClient != null) {
                hmsClient.updateSessionId(str);
            }
        }

        @Override // com.huawei.hms.adapter.BaseAdapter.BaseCallBack
        public void onComplete(String str, String str2, Parcelable parcelable) {
            if (parcelable == null) {
                m15108a(str, str2);
            } else {
                m15107a(str, str2, parcelable);
            }
        }

        @Override // com.huawei.hms.adapter.BaseAdapter.BaseCallBack
        public void onError(String str) {
            ResponseWrap responseWrap = new ResponseWrap(new ResponseHeader());
            if (responseWrap.fromJson(str)) {
                HMSLog.m14110i("HmsClient", "receive msg " + responseWrap);
                ResponseHeader responseHeader = responseWrap.getResponseHeader();
                m15109a(responseHeader.getSessionId());
                this.f11152a.onCallback(responseHeader, responseWrap.getBody());
                return;
            }
            this.f11152a.onCallback(new ResponseHeader(1, 907135000, "response header json error"), new JSONObject().toString());
        }

        /* renamed from: a */
        private void m15108a(String str, String str2) {
            ResponseHeader responseHeader = new ResponseHeader();
            if (responseHeader.fromJson(str)) {
                HMSLog.m14110i("HmsClient", "receive msg " + responseHeader);
                m15109a(responseHeader.getSessionId());
                this.f11152a.onCallback(responseHeader, str2);
                return;
            }
            this.f11152a.onCallback(new ResponseHeader(1, 907135000, "response header json error"), new JSONObject().toString());
        }

        /* renamed from: a */
        private void m15107a(String str, String str2, Parcelable parcelable) {
            ResponseHeader responseHeader = new ResponseHeader();
            if (responseHeader.fromJson(str)) {
                responseHeader.setParcelable(parcelable);
                HMSLog.m14110i("HmsClient", "receive msg " + responseHeader);
                m15109a(responseHeader.getSessionId());
                this.f11152a.onCallback(responseHeader, str2);
                return;
            }
            this.f11152a.onCallback(new ResponseHeader(1, 907135000, "response header json error"), new JSONObject().toString());
        }
    }
}
