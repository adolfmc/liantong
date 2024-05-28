package com.huawei.hms.common.internal;

import android.os.Parcelable;
import com.huawei.hmf.tasks.CancellationToken;
import com.huawei.hmf.tasks.TaskCompletionSource;
import com.huawei.hms.common.internal.AnyClient;
import com.huawei.hms.support.log.HMSLog;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public abstract class TaskApiCall<ClientT extends AnyClient, ResultT> {

    /* renamed from: a */
    private final String f11169a;

    /* renamed from: b */
    private final String f11170b;

    /* renamed from: c */
    private Parcelable f11171c;

    /* renamed from: d */
    private String f11172d;

    /* renamed from: e */
    private CancellationToken f11173e;

    /* renamed from: f */
    private int f11174f;

    @Deprecated
    public TaskApiCall(String str, String str2) {
        this.f11174f = 1;
        this.f11169a = str;
        this.f11170b = str2;
        this.f11171c = null;
        this.f11172d = null;
    }

    protected abstract void doExecute(ClientT clientt, ResponseErrorCode responseErrorCode, String str, TaskCompletionSource<ResultT> taskCompletionSource);

    public int getApiLevel() {
        return this.f11174f;
    }

    @Deprecated
    public int getMinApkVersion() {
        return 30000000;
    }

    public Parcelable getParcelable() {
        return this.f11171c;
    }

    public String getRequestJson() {
        return this.f11170b;
    }

    public CancellationToken getToken() {
        return this.f11173e;
    }

    public String getTransactionId() {
        return this.f11172d;
    }

    public String getUri() {
        return this.f11169a;
    }

    public final void onResponse(ClientT clientt, ResponseErrorCode responseErrorCode, String str, TaskCompletionSource<ResultT> taskCompletionSource) {
        CancellationToken cancellationToken = this.f11173e;
        if (cancellationToken != null && cancellationToken.isCancellationRequested()) {
            HMSLog.m14110i("TaskApiCall", "This Task has been canceled, uri:" + this.f11169a + ", transactionId:" + this.f11172d);
            return;
        }
        HMSLog.m14110i("TaskApiCall", "doExecute, uri:" + this.f11169a + ", errorCode:" + responseErrorCode.getErrorCode() + ", transactionId:" + this.f11172d);
        doExecute(clientt, responseErrorCode, str, taskCompletionSource);
    }

    public void setApiLevel(int i) {
        this.f11174f = i;
    }

    public void setParcelable(Parcelable parcelable) {
        this.f11171c = parcelable;
    }

    public void setToken(CancellationToken cancellationToken) {
        this.f11173e = cancellationToken;
    }

    public void setTransactionId(String str) {
        this.f11172d = str;
    }

    public TaskApiCall(String str, String str2, String str3) {
        this.f11174f = 1;
        this.f11169a = str;
        this.f11170b = str2;
        this.f11171c = null;
        this.f11172d = str3;
    }

    public TaskApiCall(String str, String str2, String str3, int i) {
        this.f11169a = str;
        this.f11170b = str2;
        this.f11171c = null;
        this.f11172d = str3;
        this.f11174f = i;
    }
}
