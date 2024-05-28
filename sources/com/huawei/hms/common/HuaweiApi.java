package com.huawei.hms.common;

import android.app.Activity;
import android.content.Context;
import android.os.Looper;
import android.text.TextUtils;
import com.huawei.hmf.tasks.Task;
import com.huawei.hmf.tasks.TaskCompletionSource;
import com.huawei.hms.adapter.BinderAdapter;
import com.huawei.hms.api.Api;
import com.huawei.hms.api.Api.ApiOptions;
import com.huawei.hms.api.ConnectionResult;
import com.huawei.hms.common.internal.AbstractClientBuilder;
import com.huawei.hms.common.internal.AnyClient;
import com.huawei.hms.common.internal.BaseHmsClient;
import com.huawei.hms.common.internal.BindResolveClients;
import com.huawei.hms.common.internal.ClientSettings;
import com.huawei.hms.common.internal.HmsClient;
import com.huawei.hms.common.internal.RequestHeader;
import com.huawei.hms.common.internal.RequestManager;
import com.huawei.hms.common.internal.ResolveClientBean;
import com.huawei.hms.common.internal.ResponseHeader;
import com.huawei.hms.common.internal.TaskApiCall;
import com.huawei.hms.common.internal.TaskApiCallWrapper;
import com.huawei.hms.common.internal.TransactionIdCreater;
import com.huawei.hms.core.aidl.IAIDLInvoke;
import com.huawei.hms.core.aidl.IMessageEntity;
import com.huawei.hms.support.api.client.Status;
import com.huawei.hms.support.api.client.SubAppInfo;
import com.huawei.hms.support.api.entity.auth.Scope;
import com.huawei.hms.support.hianalytics.HiAnalyticsInnerClient;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.utils.Checker;
import com.huawei.hms.utils.HMSBIInitializer;
import com.huawei.hms.utils.HMSPackageManager;
import com.huawei.hms.utils.Util;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class HuaweiApi<TOption extends Api.ApiOptions> {

    /* renamed from: a */
    private Context f11069a;

    /* renamed from: b */
    private TOption f11070b;

    /* renamed from: c */
    private Context f11071c;

    /* renamed from: d */
    private AbstractClientBuilder<?, TOption> f11072d;

    /* renamed from: e */
    private String f11073e;

    /* renamed from: f */
    private String f11074f;

    /* renamed from: g */
    private SubAppInfo f11075g;

    /* renamed from: h */
    private WeakReference<Activity> f11076h;

    /* renamed from: i */
    private int f11077i;

    /* renamed from: j */
    private int f11078j = 1;

    /* renamed from: k */
    private boolean f11079k = false;

    /* renamed from: l */
    private String f11080l;

    /* renamed from: m */
    private boolean f11081m;

    /* renamed from: n */
    private RequestManager f11082n;

    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static class RequestHandler<OptionsT extends Api.ApiOptions> implements BaseHmsClient.ConnectionCallbacks, BaseHmsClient.OnConnectionFailedListener {

        /* renamed from: b */
        private final AnyClient f11087b;

        /* renamed from: d */
        private final HuaweiApi<OptionsT> f11089d;

        /* renamed from: e */
        private ResolveClientBean f11090e;
        public final Queue<TaskApiCallbackWrapper> callbackWaitQueue = new LinkedList();

        /* renamed from: a */
        private final Queue<TaskApiCallbackWrapper> f11086a = new LinkedList();

        /* renamed from: c */
        private ConnectionResult f11088c = null;

        RequestHandler(HuaweiApi<OptionsT> huaweiApi) {
            this.f11089d = huaweiApi;
            this.f11087b = huaweiApi.getClient(RequestManager.getHandler().getLooper(), this);
        }

        public AnyClient getClient() {
            return this.f11087b;
        }

        @Override // com.huawei.hms.common.internal.BaseHmsClient.ConnectionCallbacks
        public void onConnected() {
            HMSLog.m14110i("HuaweiApi", "onConnected");
            BindResolveClients.getInstance().unRegister(this.f11090e);
            this.f11090e = null;
            RequestManager.getHandler().post(new Runnable() { // from class: com.huawei.hms.common.HuaweiApi.RequestHandler.4
                @Override // java.lang.Runnable
                public void run() {
                    RequestHandler.this.m15143b();
                }
            });
        }

        @Override // com.huawei.hms.common.internal.BaseHmsClient.OnConnectionFailedListener
        public void onConnectionFailed(final ConnectionResult connectionResult) {
            HMSLog.m14110i("HuaweiApi", "onConnectionFailed");
            BindResolveClients.getInstance().unRegister(this.f11090e);
            this.f11090e = null;
            RequestManager.getHandler().post(new Runnable() { // from class: com.huawei.hms.common.HuaweiApi.RequestHandler.3
                @Override // java.lang.Runnable
                public void run() {
                    RequestHandler.this.m15142b(connectionResult);
                }
            });
        }

        @Override // com.huawei.hms.common.internal.BaseHmsClient.ConnectionCallbacks
        public void onConnectionSuspended(int i) {
            HMSLog.m14110i("HuaweiApi", "onConnectionSuspended");
            BindResolveClients.getInstance().unRegister(this.f11090e);
            this.f11090e = null;
            RequestManager.getHandler().post(new Runnable() { // from class: com.huawei.hms.common.HuaweiApi.RequestHandler.5
                @Override // java.lang.Runnable
                public void run() {
                    RequestHandler.this.m15139c();
                }
            });
        }

        public void postMessage(final TaskApiCallbackWrapper taskApiCallbackWrapper) {
            RequestManager.addToConnectedReqMap(taskApiCallbackWrapper.m15135a().getTaskApiCall().getTransactionId(), this);
            this.f11086a.add(taskApiCallbackWrapper);
            String uri = taskApiCallbackWrapper.m15135a().getTaskApiCall().getUri();
            String packageName = (((HuaweiApi) this.f11089d).f11071c == null ? this.f11089d.getContext() : ((HuaweiApi) this.f11089d).f11071c).getPackageName();
            if (((HuaweiApi) this.f11089d).f11071c != null) {
                HuaweiApi<OptionsT> huaweiApi = this.f11089d;
                huaweiApi.m15154b(((HuaweiApi) huaweiApi).f11071c);
            }
            final RequestHeader requestHeader = new RequestHeader();
            requestHeader.setSrvName(uri.split("\\.")[0]);
            requestHeader.setApiName(uri);
            requestHeader.setAppID(this.f11089d.getAppID() + "|" + this.f11089d.getSubAppID());
            requestHeader.setPkgName(packageName);
            requestHeader.setSessionId(this.f11087b.getSessionId());
            TaskApiCall taskApiCall = taskApiCallbackWrapper.m15135a().getTaskApiCall();
            requestHeader.setTransactionId(m15144a(taskApiCall.getTransactionId(), uri));
            requestHeader.setParcelable(taskApiCall.getParcelable());
            requestHeader.setKitSdkVersion(this.f11089d.getKitSdkVersion());
            requestHeader.setApiLevel(Math.max(this.f11089d.getApiLevel(), taskApiCall.getApiLevel()));
            this.f11087b.post(requestHeader, taskApiCall.getRequestJson(), new AnyClient.CallBack() { // from class: com.huawei.hms.common.HuaweiApi.RequestHandler.2
                @Override // com.huawei.hms.common.internal.AnyClient.CallBack
                public void onCallback(IMessageEntity iMessageEntity, String str) {
                    AnyClient.CallBack m15134b = taskApiCallbackWrapper.m15134b();
                    if (m15134b != null) {
                        m15134b.onCallback(iMessageEntity, str);
                    }
                    RequestManager.removeReqByTransId(requestHeader.getTransactionId());
                    RequestManager.getHandler().post(new Runnable() { // from class: com.huawei.hms.common.HuaweiApi.RequestHandler.2.1
                        @Override // java.lang.Runnable
                        public void run() {
                            RequestHandler.this.f11086a.remove(taskApiCallbackWrapper);
                        }
                    });
                }
            });
        }

        /* renamed from: b */
        private TaskApiCallbackWrapper m15140b(final TaskApiCallWrapper taskApiCallWrapper) {
            return new TaskApiCallbackWrapper(taskApiCallWrapper, new AnyClient.CallBack() { // from class: com.huawei.hms.common.HuaweiApi.RequestHandler.1

                /* renamed from: a */
                private AtomicBoolean f11091a = new AtomicBoolean(true);

                @Override // com.huawei.hms.common.internal.AnyClient.CallBack
                public void onCallback(IMessageEntity iMessageEntity, String str) {
                    if (!(iMessageEntity instanceof ResponseHeader)) {
                        HMSLog.m14112e("HuaweiApi", "header is not instance of ResponseHeader");
                        return;
                    }
                    ResponseHeader responseHeader = (ResponseHeader) iMessageEntity;
                    if (responseHeader.getErrorCode() == 11) {
                        RequestHandler.this.m15151a();
                        HMSLog.m14110i("HuaweiApi", "unbind service");
                    }
                    if (!TextUtils.isEmpty(responseHeader.getResolution())) {
                        HMSLog.m14112e("HuaweiApi", "Response has resolution: " + responseHeader.getResolution());
                    }
                    if (this.f11091a.compareAndSet(true, false)) {
                        HiAnalyticsInnerClient.reportEntryExit(RequestHandler.this.f11089d.getContext(), responseHeader, String.valueOf(RequestHandler.this.f11089d.getKitSdkVersion()));
                    }
                    taskApiCallWrapper.getTaskApiCall().onResponse(RequestHandler.this.f11087b, responseHeader, str, taskApiCallWrapper.getTaskCompletionSource());
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: c */
        public void m15139c() {
            HMSLog.m14110i("HuaweiApi", "wait queue size = " + this.callbackWaitQueue.size());
            HMSLog.m14110i("HuaweiApi", "run queue size = " + this.f11086a.size());
            for (TaskApiCallbackWrapper taskApiCallbackWrapper : this.callbackWaitQueue) {
                m15146a(taskApiCallbackWrapper);
            }
            for (TaskApiCallbackWrapper taskApiCallbackWrapper2 : this.f11086a) {
                m15146a(taskApiCallbackWrapper2);
            }
            this.callbackWaitQueue.clear();
            this.f11086a.clear();
            this.f11088c = null;
            this.f11087b.disconnect();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: b */
        public void m15142b(ConnectionResult connectionResult) {
            this.f11088c = connectionResult;
            boolean z = true;
            for (TaskApiCallbackWrapper taskApiCallbackWrapper : this.callbackWaitQueue) {
                TaskApiCallWrapper m15135a = taskApiCallbackWrapper.m15135a();
                ResponseHeader responseHeader = new ResponseHeader(1, 907135003, "Connection Failed:" + m15149a(connectionResult) + "(" + connectionResult.getErrorCode() + ")");
                responseHeader.setTransactionId(m15135a.getTaskApiCall().getTransactionId());
                HiAnalyticsInnerClient.reportEntryExit(this.f11089d.getContext(), responseHeader, String.valueOf(this.f11089d.getKitSdkVersion()));
                if (this.f11088c.getResolution() != null && z) {
                    responseHeader.setParcelable(this.f11088c.getResolution());
                    z = false;
                    if (Util.isAvailableLibExist(this.f11089d.getContext()) && this.f11088c.getErrorCode() == 26) {
                        responseHeader.setResolution("hasContextResolution");
                    }
                }
                int errorCode = this.f11088c.getErrorCode();
                if (errorCode == 30 || errorCode == 31) {
                    responseHeader.setErrorCode(errorCode);
                }
                m15135a.getTaskApiCall().onResponse(this.f11087b, responseHeader, null, m15135a.getTaskCompletionSource());
            }
            this.callbackWaitQueue.clear();
            this.f11086a.clear();
            this.f11088c = null;
            this.f11087b.disconnect();
        }

        /* renamed from: a */
        void m15145a(TaskApiCallWrapper taskApiCallWrapper) {
            HMSLog.m14110i("HuaweiApi", "sendRequest");
            TaskApiCallbackWrapper m15140b = m15140b(taskApiCallWrapper);
            int hmsVersionCode = HMSPackageManager.getInstance(((HuaweiApi) this.f11089d).f11069a).getHmsVersionCode();
            if ((hmsVersionCode < 40000000 && hmsVersionCode > 0) && this.f11087b.isConnected() && !((HuaweiApi) this.f11089d).f11081m && ((BaseHmsClient) this.f11087b).getAdapter().getServiceAction().equals("com.huawei.hms.core.aidlservice")) {
                int requestHmsVersionCode = this.f11087b.getRequestHmsVersionCode();
                if (requestHmsVersionCode <= taskApiCallWrapper.getTaskApiCall().getMinApkVersion()) {
                    requestHmsVersionCode = taskApiCallWrapper.getTaskApiCall().getMinApkVersion();
                }
                if (requestHmsVersionCode > hmsVersionCode) {
                    this.f11087b.disconnect();
                }
            }
            if (this.f11087b.isConnected()) {
                HMSLog.m14110i("HuaweiApi", "isConnected:true.");
                BinderAdapter adapter = ((BaseHmsClient) this.f11087b).getAdapter();
                adapter.updateDelayTask();
                ((HmsClient) this.f11087b).setService(IAIDLInvoke.Stub.asInterface(adapter.getServiceBinder()));
                postMessage(m15140b);
                return;
            }
            HMSLog.m14110i("HuaweiApi", "isConnected:false.");
            this.callbackWaitQueue.add(m15140b);
            ConnectionResult connectionResult = this.f11088c;
            if (connectionResult != null && connectionResult.getErrorCode() != 0) {
                HMSLog.m14110i("HuaweiApi", "onConnectionFailed, ErrorCode:" + this.f11088c.getErrorCode());
                onConnectionFailed(this.f11088c);
                return;
            }
            RequestManager.addRequestToQueue(this);
            AnyClient anyClient = this.f11087b;
            if (anyClient instanceof BaseHmsClient) {
                ((BaseHmsClient) anyClient).setInternalRequest(this);
            }
            m15150a(taskApiCallWrapper.getTaskApiCall().getMinApkVersion(), m15140b);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: b */
        public void m15143b() {
            this.f11088c = null;
            this.f11086a.clear();
            for (TaskApiCallbackWrapper taskApiCallbackWrapper : this.callbackWaitQueue) {
                postMessage(taskApiCallbackWrapper);
            }
            this.callbackWaitQueue.clear();
        }

        /* renamed from: a */
        private String m15144a(String str, String str2) {
            return TextUtils.isEmpty(str) ? TransactionIdCreater.getId(this.f11089d.getAppID(), str2) : str;
        }

        /* renamed from: a */
        synchronized void m15150a(int i, TaskApiCallbackWrapper taskApiCallbackWrapper) {
            if (this.f11087b.isConnected()) {
                HMSLog.m14115d("HuaweiApi", "client is connected");
            } else if (this.f11087b.isConnecting()) {
                HMSLog.m14115d("HuaweiApi", "client is isConnecting");
            } else {
                if (this.f11089d.getActivity() != null) {
                    if (this.f11090e == null) {
                        this.f11090e = new ResolveClientBean(this.f11087b, i);
                    }
                    if (BindResolveClients.getInstance().isClientRegistered(this.f11090e)) {
                        HMSLog.m14110i("HuaweiApi", "mResolveClientBean has already register, return!");
                        return;
                    }
                    BindResolveClients.getInstance().register(this.f11090e);
                }
                this.f11087b.connect(i);
            }
        }

        /* renamed from: a */
        void m15151a() {
            this.f11087b.disconnect();
        }

        /* renamed from: a */
        private void m15146a(TaskApiCallbackWrapper taskApiCallbackWrapper) {
            TaskApiCallWrapper m15135a = taskApiCallbackWrapper.m15135a();
            ResponseHeader responseHeader = new ResponseHeader(1, 907135003, "Connection Suspended");
            responseHeader.setTransactionId(m15135a.getTaskApiCall().getTransactionId());
            m15135a.getTaskApiCall().onResponse(this.f11087b, responseHeader, null, m15135a.getTaskCompletionSource());
        }

        /* renamed from: a */
        private String m15149a(ConnectionResult connectionResult) {
            if (Util.isAvailableLibExist(this.f11089d.getContext())) {
                int errorCode = connectionResult.getErrorCode();
                if (errorCode != -1) {
                    if (errorCode != 3) {
                        if (errorCode != 8) {
                            if (errorCode != 10) {
                                if (errorCode != 13) {
                                    if (errorCode != 21) {
                                        switch (errorCode) {
                                            case 25:
                                                return "failed to get update result";
                                            case 26:
                                                return "update failed, because no activity incoming, can't pop update page";
                                            case 27:
                                                return "there is already an update popup at the front desk, but it hasn't been clicked or it is not effective for a while";
                                            default:
                                                return "unknown errorReason";
                                        }
                                    }
                                    return "device is too old to be support";
                                }
                                return "update cancelled";
                            }
                            return "application configuration error, please developer check configuration";
                        }
                        return "internal error";
                    }
                    return "HuaWei Mobile Service is disabled";
                }
                return "get update result, but has other error codes";
            }
            int errorCode2 = connectionResult.getErrorCode();
            return errorCode2 != -1 ? errorCode2 != 8 ? errorCode2 != 10 ? "unknown errorReason" : "application configuration error, please developer check configuration" : "internal error" : "get update result, but has other error codes";
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static class TaskApiCallbackWrapper {

        /* renamed from: a */
        private final TaskApiCallWrapper f11102a;

        /* renamed from: b */
        private final AnyClient.CallBack f11103b;

        TaskApiCallbackWrapper(TaskApiCallWrapper taskApiCallWrapper, AnyClient.CallBack callBack) {
            this.f11102a = taskApiCallWrapper;
            this.f11103b = callBack;
        }

        /* renamed from: a */
        TaskApiCallWrapper m15135a() {
            return this.f11102a;
        }

        /* renamed from: b */
        AnyClient.CallBack m15134b() {
            return this.f11103b;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.huawei.hms.common.HuaweiApi$a */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static class RunnableC4888a<OptionsT extends Api.ApiOptions> implements Runnable {

        /* renamed from: a */
        private final HuaweiApi<OptionsT> f11104a;

        /* renamed from: b */
        private final TaskApiCallWrapper f11105b;

        public RunnableC4888a(HuaweiApi<OptionsT> huaweiApi, TaskApiCallWrapper taskApiCallWrapper) {
            this.f11104a = huaweiApi;
            this.f11105b = taskApiCallWrapper;
        }

        /* JADX WARN: Removed duplicated region for block: B:22:0x0056 A[ADDED_TO_REGION] */
        /* JADX WARN: Removed duplicated region for block: B:29:0x0062  */
        /* JADX WARN: Removed duplicated region for block: B:30:0x0066  */
        /* renamed from: a */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private void m15133a(com.huawei.hms.common.HuaweiApi.RequestHandler r8, java.lang.Throwable r9) {
            /*
                r7 = this;
                r0 = 1
                r1 = 0
                com.huawei.hms.common.internal.AnyClient r8 = r8.getClient()     // Catch: java.lang.Throwable -> L34
                com.huawei.hms.common.internal.ResponseHeader r2 = new com.huawei.hms.common.internal.ResponseHeader     // Catch: java.lang.Throwable -> L31
                r3 = 907135001(0x3611c819, float:2.1723156E-6)
                java.lang.String r9 = r9.getMessage()     // Catch: java.lang.Throwable -> L31
                r2.<init>(r0, r3, r9)     // Catch: java.lang.Throwable -> L31
                org.json.JSONObject r9 = new org.json.JSONObject     // Catch: java.lang.Throwable -> L2d
                r9.<init>()     // Catch: java.lang.Throwable -> L2d
                java.lang.String r9 = r9.toString()     // Catch: java.lang.Throwable -> L2d
                com.huawei.hms.common.internal.TaskApiCallWrapper r3 = r7.f11105b     // Catch: java.lang.Throwable -> L2a
                com.huawei.hmf.tasks.TaskCompletionSource r3 = r3.getTaskCompletionSource()     // Catch: java.lang.Throwable -> L2a
                com.huawei.hms.common.internal.TaskApiCallWrapper r4 = r7.f11105b     // Catch: java.lang.Throwable -> L28
                com.huawei.hms.common.internal.TaskApiCall r1 = r4.getTaskApiCall()     // Catch: java.lang.Throwable -> L28
                goto L54
            L28:
                r4 = move-exception
                goto L3a
            L2a:
                r4 = move-exception
                r3 = r1
                goto L3a
            L2d:
                r4 = move-exception
                r9 = r1
                r3 = r9
                goto L3a
            L31:
                r9 = move-exception
                r4 = r9
                goto L37
            L34:
                r8 = move-exception
                r4 = r8
                r8 = r1
            L37:
                r9 = r1
                r2 = r9
                r3 = r2
            L3a:
                java.lang.StringBuilder r5 = new java.lang.StringBuilder
                r5.<init>()
                java.lang.String r6 = "<notifyCpException> "
                r5.append(r6)
                java.lang.String r4 = r4.getMessage()
                r5.append(r4)
                java.lang.String r4 = r5.toString()
                java.lang.String r5 = "HuaweiApi"
                com.huawei.hms.support.log.HMSLog.m14112e(r5, r4)
            L54:
                if (r8 == 0) goto L5f
                if (r2 == 0) goto L5f
                if (r9 == 0) goto L5f
                if (r3 == 0) goto L5f
                if (r1 == 0) goto L5f
                goto L60
            L5f:
                r0 = 0
            L60:
                if (r0 == 0) goto L66
                r1.onResponse(r8, r2, r9, r3)
                goto L6d
            L66:
                java.lang.String r8 = "HuaweiApi"
                java.lang.String r9 = "<notifyCpException> isNotify is false, Can not notify CP."
                com.huawei.hms.support.log.HMSLog.m14112e(r8, r9)
            L6d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.common.HuaweiApi.RunnableC4888a.m15133a(com.huawei.hms.common.HuaweiApi$RequestHandler, java.lang.Throwable):void");
        }

        @Override // java.lang.Runnable
        public void run() {
            RequestHandler requestHandler = new RequestHandler(this.f11104a);
            try {
                requestHandler.m15145a(this.f11105b);
            } catch (Throwable th) {
                m15133a(requestHandler, th);
            }
        }
    }

    public HuaweiApi(Activity activity, Api<TOption> api, TOption toption, AbstractClientBuilder abstractClientBuilder, int i) {
        Checker.checkNonNull(activity, "Null activity is not permitted.");
        this.f11076h = new WeakReference<>(activity);
        m15160a(activity, api, toption, abstractClientBuilder, i, null);
    }

    @Deprecated
    public Task<Boolean> disconnectService() {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        RequestManager.getInstance();
        RequestManager.getHandler().post(new Runnable() { // from class: com.huawei.hms.common.HuaweiApi.1
            @Override // java.lang.Runnable
            public void run() {
                HuaweiApi.this.m15157a(this, taskCompletionSource);
            }
        });
        return taskCompletionSource.getTask();
    }

    public <TResult, TClient extends AnyClient> Task<TResult> doWrite(TaskApiCall<TClient, TResult> taskApiCall) {
        this.f11079k = true;
        if (taskApiCall == null) {
            HMSLog.m14112e("HuaweiApi", "in doWrite:taskApiCall is null");
            TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
            taskCompletionSource.setException(new ApiException(Status.FAILURE));
            return taskCompletionSource.getTask();
        }
        HiAnalyticsInnerClient.reportEntryClient(this.f11069a, taskApiCall.getUri(), TextUtils.isEmpty(this.f11075g.getSubAppID()) ? this.f11074f : this.f11075g.getSubAppID(), taskApiCall.getTransactionId(), String.valueOf(getKitSdkVersion()));
        if (this.f11082n == null) {
            this.f11082n = RequestManager.getInstance();
        }
        return m15155a(taskApiCall);
    }

    public Activity getActivity() {
        WeakReference<Activity> weakReference = this.f11076h;
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    public int getApiLevel() {
        return this.f11078j;
    }

    public String getAppID() {
        return this.f11074f;
    }

    /* JADX WARN: Type inference failed for: r3v2, types: [com.huawei.hms.common.internal.AnyClient] */
    public AnyClient getClient(Looper looper, RequestHandler requestHandler) {
        return this.f11072d.buildClient(this.f11069a, getClientSetting(), requestHandler, requestHandler);
    }

    protected ClientSettings getClientSetting() {
        ClientSettings clientSettings = new ClientSettings(this.f11069a.getPackageName(), this.f11069a.getClass().getName(), getScopes(), this.f11073e, null, this.f11075g);
        if (!this.f11081m) {
            this.f11080l = HMSPackageManager.getInstance(this.f11069a).getHMSPackageNameForMultiService();
            HMSLog.m14110i("HuaweiApi", "No setInnerHms, hms pkg name is " + this.f11080l);
        }
        clientSettings.setInnerHmsPkg(this.f11080l);
        clientSettings.setUseInnerHms(this.f11081m);
        WeakReference<Activity> weakReference = this.f11076h;
        if (weakReference != null) {
            clientSettings.setCpActivity(weakReference.get());
        }
        return clientSettings;
    }

    public Context getContext() {
        return this.f11069a;
    }

    public int getKitSdkVersion() {
        return this.f11077i;
    }

    public TOption getOption() {
        return this.f11070b;
    }

    protected List<Scope> getScopes() {
        return Collections.emptyList();
    }

    public String getSubAppID() {
        return this.f11075g.getSubAppID();
    }

    public void setApiLevel(int i) {
        this.f11078j = i;
    }

    public void setHostContext(Context context) {
        this.f11071c = context;
    }

    public void setInnerHms() {
        this.f11080l = this.f11069a.getPackageName();
        this.f11081m = true;
        HMSLog.m14110i("HuaweiApi", "<setInnerHms> init inner hms pkg info:" + this.f11080l);
    }

    public void setKitSdkVersion(int i) {
        this.f11077i = i;
    }

    public void setSubAppId(String str) throws ApiException {
        if (!setSubAppInfo(new SubAppInfo(str))) {
            throw new ApiException(Status.FAILURE);
        }
    }

    @Deprecated
    public boolean setSubAppInfo(SubAppInfo subAppInfo) {
        HMSLog.m14110i("HuaweiApi", "Enter setSubAppInfo");
        SubAppInfo subAppInfo2 = this.f11075g;
        if (subAppInfo2 != null && !TextUtils.isEmpty(subAppInfo2.getSubAppID())) {
            HMSLog.m14112e("HuaweiApi", "subAppInfo is already set");
            return false;
        } else if (subAppInfo == null) {
            HMSLog.m14112e("HuaweiApi", "subAppInfo is null");
            return false;
        } else {
            String subAppID = subAppInfo.getSubAppID();
            if (TextUtils.isEmpty(subAppID)) {
                HMSLog.m14112e("HuaweiApi", "subAppId is empty");
                return false;
            } else if (subAppID.equals(this.f11073e)) {
                HMSLog.m14112e("HuaweiApi", "subAppId is host appid");
                return false;
            } else if (this.f11079k) {
                HMSLog.m14112e("HuaweiApi", "Client has sent request to Huawei Mobile Services, setting subAppId is not allowed");
                return false;
            } else {
                this.f11075g = new SubAppInfo(subAppInfo);
                return true;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public void m15154b(Context context) {
        String appId = Util.getAppId(context);
        this.f11073e = appId;
        this.f11074f = appId;
    }

    /* renamed from: a */
    private void m15160a(Context context, Api<TOption> api, TOption toption, AbstractClientBuilder abstractClientBuilder, int i, String str) {
        this.f11069a = context.getApplicationContext();
        this.f11070b = toption;
        this.f11072d = abstractClientBuilder;
        m15154b(context);
        this.f11075g = new SubAppInfo("");
        this.f11077i = i;
        if (!TextUtils.isEmpty(str)) {
            if (str.equals(this.f11073e)) {
                HMSLog.m14112e("HuaweiApi", "subAppId is host appid");
            } else {
                HMSLog.m14110i("HuaweiApi", "subAppId is " + str);
                this.f11075g = new SubAppInfo(str);
            }
        }
        m15161a(context);
    }

    /* renamed from: a */
    private void m15161a(Context context) {
        HMSBIInitializer.getInstance(context).initBI();
    }

    public HuaweiApi(Activity activity, Api<TOption> api, TOption toption, AbstractClientBuilder abstractClientBuilder, int i, String str) {
        Checker.checkNonNull(activity, "Null activity is not permitted.");
        this.f11076h = new WeakReference<>(activity);
        m15160a(activity, api, toption, abstractClientBuilder, i, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m15157a(HuaweiApi<?> huaweiApi, TaskCompletionSource<Boolean> taskCompletionSource) {
        HMSLog.m14110i("HuaweiApi", "innerDisconnect.");
        try {
            huaweiApi.getClient(RequestManager.getHandler().getLooper(), null).disconnect();
            taskCompletionSource.setResult(Boolean.TRUE);
        } catch (Exception e) {
            HMSLog.m14109w("HuaweiApi", "disconnect the binder failed for:" + e.getMessage());
        }
    }

    /* renamed from: a */
    private <TResult, TClient extends AnyClient> Task<TResult> m15155a(TaskApiCall<TClient, TResult> taskApiCall) {
        TaskCompletionSource taskCompletionSource;
        if (taskApiCall.getToken() == null) {
            taskCompletionSource = new TaskCompletionSource();
        } else {
            taskCompletionSource = new TaskCompletionSource(taskApiCall.getToken());
        }
        RequestManager.getHandler().post(new RunnableC4888a(this, new TaskApiCallWrapper(taskApiCall, taskCompletionSource)));
        return taskCompletionSource.getTask();
    }

    public HuaweiApi(Activity activity, Api<TOption> api, TOption toption, AbstractClientBuilder abstractClientBuilder) {
        Checker.checkNonNull(activity, "Null activity is not permitted.");
        this.f11076h = new WeakReference<>(activity);
        m15160a(activity, api, toption, abstractClientBuilder, 0, null);
    }

    public HuaweiApi(Context context, Api<TOption> api, TOption toption, AbstractClientBuilder abstractClientBuilder, int i) {
        Checker.checkNonNull(context, "Null context is not permitted.");
        m15160a(context, api, toption, abstractClientBuilder, i, null);
    }

    public HuaweiApi(Context context, Api<TOption> api, TOption toption, AbstractClientBuilder abstractClientBuilder, int i, String str) {
        Checker.checkNonNull(context, "Null context is not permitted.");
        m15160a(context, api, toption, abstractClientBuilder, i, str);
    }

    public HuaweiApi(Context context, Api<TOption> api, TOption toption, AbstractClientBuilder abstractClientBuilder) {
        Checker.checkNonNull(context, "Null context is not permitted.");
        m15160a(context, api, toption, abstractClientBuilder, 0, null);
    }
}
