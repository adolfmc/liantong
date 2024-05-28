package com.huawei.hms.common.internal;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import com.huawei.hms.adapter.AvailableAdapter;
import com.huawei.hms.adapter.BinderAdapter;
import com.huawei.hms.adapter.InnerBinderAdapter;
import com.huawei.hms.adapter.OuterBinderAdapter;
import com.huawei.hms.api.ConnectionResult;
import com.huawei.hms.api.FailedBinderCallBack;
import com.huawei.hms.api.HuaweiApiAvailability;
import com.huawei.hms.api.IPCTransport;
import com.huawei.hms.common.HuaweiApi;
import com.huawei.hms.core.aidl.IAIDLInvoke;
import com.huawei.hms.support.api.client.AidlApiClient;
import com.huawei.hms.support.api.client.SubAppInfo;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.utils.HMSPackageManager;
import com.huawei.hms.utils.Util;
import java.sql.Timestamp;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public abstract class BaseHmsClient implements AidlApiClient {
    protected static final int TIMEOUT_DISCONNECTED = 6;

    /* renamed from: i */
    private static final Object f11116i = new Object();

    /* renamed from: j */
    private static final AtomicInteger f11117j = new AtomicInteger(1);

    /* renamed from: k */
    private static final AtomicInteger f11118k = new AtomicInteger(1);

    /* renamed from: l */
    private static BinderAdapter f11119l;

    /* renamed from: m */
    private static BinderAdapter f11120m;

    /* renamed from: a */
    private final Context f11121a;

    /* renamed from: b */
    private String f11122b;

    /* renamed from: c */
    private final ClientSettings f11123c;

    /* renamed from: d */
    private volatile IAIDLInvoke f11124d;

    /* renamed from: e */
    private final ConnectionCallbacks f11125e;

    /* renamed from: f */
    private final OnConnectionFailedListener f11126f;

    /* renamed from: g */
    private Handler f11127g = null;

    /* renamed from: h */
    private HuaweiApi.RequestHandler f11128h;
    protected String sessionId;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface ConnectionCallbacks {
        public static final int CAUSE_API_CLIENT_EXPIRED = 3;
        public static final int CAUSE_NETWORK_LOST = 2;
        public static final int CAUSE_SERVICE_DISCONNECTED = 1;

        void onConnected();

        void onConnectionSuspended(int i);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static final class ConnectionResultWrapper {

        /* renamed from: a */
        private HuaweiApi.RequestHandler f11133a;

        /* renamed from: b */
        private ConnectionResult f11134b;

        public ConnectionResultWrapper(HuaweiApi.RequestHandler requestHandler, ConnectionResult connectionResult) {
            this.f11133a = requestHandler;
            this.f11134b = connectionResult;
        }

        public ConnectionResult getConnectionResult() {
            return this.f11134b;
        }

        public HuaweiApi.RequestHandler getRequest() {
            return this.f11133a;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface OnConnectionFailedListener {
        void onConnectionFailed(ConnectionResult connectionResult);
    }

    public BaseHmsClient(Context context, ClientSettings clientSettings, OnConnectionFailedListener onConnectionFailedListener, ConnectionCallbacks connectionCallbacks) {
        this.f11121a = context;
        this.f11123c = clientSettings;
        this.f11122b = clientSettings.getAppID();
        this.f11126f = onConnectionFailedListener;
        this.f11125e = connectionCallbacks;
    }

    /* renamed from: c */
    private BinderAdapter.BinderCallBack m15114c() {
        return new BinderAdapter.BinderCallBack() { // from class: com.huawei.hms.common.internal.BaseHmsClient.1
            @Override // com.huawei.hms.adapter.BinderAdapter.BinderCallBack
            public void onBinderFailed(int i) {
                onBinderFailed(i, null);
            }

            @Override // com.huawei.hms.adapter.BinderAdapter.BinderCallBack
            public void onNullBinding(ComponentName componentName) {
                BaseHmsClient.this.m15117b(1);
                BaseHmsClient.this.m15127a(10);
            }

            @Override // com.huawei.hms.adapter.BinderAdapter.BinderCallBack
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                HMSLog.m14110i("BaseHmsClient", "Enter onServiceConnected.");
                BaseHmsClient.this.connectedInternal(iBinder);
            }

            @Override // com.huawei.hms.adapter.BinderAdapter.BinderCallBack
            public void onServiceDisconnected(ComponentName componentName) {
                HMSLog.m14110i("BaseHmsClient", "Enter onServiceDisconnected.");
                BaseHmsClient.this.m15117b(1);
                RequestManager.getHandler().sendEmptyMessage(RequestManager.NOTIFY_CONNECT_SUSPENDED);
                if (BaseHmsClient.this.f11125e == null || (BaseHmsClient.this.f11125e instanceof HuaweiApi.RequestHandler)) {
                    return;
                }
                BaseHmsClient.this.f11125e.onConnectionSuspended(1);
            }

            @Override // com.huawei.hms.adapter.BinderAdapter.BinderCallBack
            public void onTimedDisconnected() {
                BaseHmsClient.this.m15117b(6);
                if (BaseHmsClient.this.f11125e == null || (BaseHmsClient.this.f11125e instanceof HuaweiApi.RequestHandler)) {
                    return;
                }
                BaseHmsClient.this.f11125e.onConnectionSuspended(1);
            }

            @Override // com.huawei.hms.adapter.BinderAdapter.BinderCallBack
            public void onBinderFailed(int i, Intent intent) {
                if (intent != null) {
                    Activity activeActivity = Util.getActiveActivity(BaseHmsClient.this.getClientSettings().getCpActivity(), BaseHmsClient.this.getContext());
                    if (activeActivity != null) {
                        HMSLog.m14110i("BaseHmsClient", "onBinderFailed: SDK try to resolve and reConnect!");
                        long time = new Timestamp(System.currentTimeMillis()).getTime();
                        FailedBinderCallBack.getInstance().setCallBack(Long.valueOf(time), new FailedBinderCallBack.BinderCallBack() { // from class: com.huawei.hms.common.internal.BaseHmsClient.1.1
                            @Override // com.huawei.hms.api.FailedBinderCallBack.BinderCallBack
                            public void binderCallBack(int i2) {
                                if (i2 != 0) {
                                    BaseHmsClient.this.m15124a(new ConnectionResult(10, (PendingIntent) null));
                                    BaseHmsClient.this.f11124d = null;
                                }
                            }
                        });
                        intent.putExtra(FailedBinderCallBack.CALLER_ID, time);
                        activeActivity.startActivity(intent);
                        return;
                    }
                    HMSLog.m14110i("BaseHmsClient", "onBinderFailed: return pendingIntent to kit and cp");
                    BaseHmsClient.this.m15124a(new ConnectionResult(10, PendingIntent.getActivity(BaseHmsClient.this.f11121a, 11, intent, 67108864)));
                    BaseHmsClient.this.f11124d = null;
                    return;
                }
                HMSLog.m14110i("BaseHmsClient", "onBinderFailed: intent is null!");
                BaseHmsClient.this.m15124a(new ConnectionResult(10, (PendingIntent) null));
                BaseHmsClient.this.f11124d = null;
            }
        };
    }

    /* renamed from: d */
    private void m15113d() {
        HMSLog.m14109w("BaseHmsClient", "Failed to get service as interface, trying to unbind.");
        if (this.f11123c.isUseInnerHms()) {
            BinderAdapter binderAdapter = f11120m;
            if (binderAdapter == null) {
                HMSLog.m14109w("BaseHmsClient", "mInnerBinderAdapter is null.");
                return;
            }
            binderAdapter.unBind();
        } else {
            BinderAdapter binderAdapter2 = f11119l;
            if (binderAdapter2 == null) {
                HMSLog.m14109w("BaseHmsClient", "mOuterBinderAdapter is null.");
                return;
            }
            binderAdapter2.unBind();
        }
        m15117b(1);
        m15127a(10);
    }

    /* renamed from: e */
    private void m15112e() {
        if (this.f11123c.isUseInnerHms()) {
            BinderAdapter binderAdapter = f11120m;
            if (binderAdapter != null) {
                binderAdapter.unBind();
                return;
            }
            return;
        }
        BinderAdapter binderAdapter2 = f11119l;
        if (binderAdapter2 != null) {
            binderAdapter2.unBind();
        }
    }

    protected final void checkConnected() {
        if (!isConnected()) {
            throw new IllegalStateException("Not connected. Call connect() and wait for onConnected() to be called.");
        }
    }

    public void connect(int i) {
        m15126a(i, false);
    }

    public void connectedInternal(IBinder iBinder) {
        this.f11124d = IAIDLInvoke.Stub.asInterface(iBinder);
        if (this.f11124d == null) {
            HMSLog.m14112e("BaseHmsClient", "mService is null, try to unBind.");
            m15113d();
            return;
        }
        onConnecting();
    }

    protected final void connectionConnected() {
        m15117b(3);
        RequestManager.getHandler().sendEmptyMessage(RequestManager.NOTIFY_CONNECT_SUCCESS);
        ConnectionCallbacks connectionCallbacks = this.f11125e;
        if (connectionCallbacks == null || (connectionCallbacks instanceof HuaweiApi.RequestHandler)) {
            return;
        }
        connectionCallbacks.onConnected();
    }

    public void disconnect() {
        int i = (this.f11123c.isUseInnerHms() ? f11118k : f11117j).get();
        HMSLog.m14110i("BaseHmsClient", "Enter disconnect, Connection Status: " + i);
        if (i == 3) {
            m15112e();
            m15117b(1);
        } else if (i != 5) {
        } else {
            m15118b();
            m15117b(1);
        }
    }

    public BinderAdapter getAdapter() {
        HMSLog.m14110i("BaseHmsClient", "getAdapter:isInner:" + this.f11123c.isUseInnerHms() + ", mInnerBinderAdapter:" + f11120m + ", mOuterBinderAdapter:" + f11119l);
        return this.f11123c.isUseInnerHms() ? f11120m : f11119l;
    }

    @Override // com.huawei.hms.support.api.client.AidlApiClient
    public List<String> getApiNameList() {
        return this.f11123c.getApiName();
    }

    @Override // com.huawei.hms.support.api.client.ApiClient
    public String getAppID() {
        return this.f11122b;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ClientSettings getClientSettings() {
        return this.f11123c;
    }

    public int getConnectionStatus() {
        return (this.f11123c.isUseInnerHms() ? f11118k : f11117j).get();
    }

    @Override // com.huawei.hms.support.api.client.ApiClient
    public Context getContext() {
        return this.f11121a;
    }

    @Override // com.huawei.hms.support.api.client.ApiClient
    public String getCpID() {
        return this.f11123c.getCpID();
    }

    @Deprecated
    public int getMinApkVersion() {
        return 30000000;
    }

    @Override // com.huawei.hms.support.api.client.ApiClient
    public String getPackageName() {
        return this.f11123c.getClientPackageName();
    }

    public int getRequestHmsVersionCode() {
        return getMinApkVersion();
    }

    @Override // com.huawei.hms.support.api.client.AidlApiClient
    public IAIDLInvoke getService() {
        return this.f11124d;
    }

    public String getServiceAction() {
        HMSPackageManager hMSPackageManager = HMSPackageManager.getInstance(this.f11121a);
        if (this.f11123c.isUseInnerHms()) {
            return hMSPackageManager.getInnerServiceAction();
        }
        return hMSPackageManager.getServiceAction();
    }

    @Override // com.huawei.hms.support.api.client.ApiClient
    public String getSessionId() {
        return this.sessionId;
    }

    @Override // com.huawei.hms.support.api.client.ApiClient
    public SubAppInfo getSubAppInfo() {
        return this.f11123c.getSubAppID();
    }

    @Override // com.huawei.hms.support.api.client.ApiClient
    public String getTransportName() {
        return IPCTransport.class.getName();
    }

    @Override // com.huawei.hms.support.api.client.ApiClient
    public boolean isConnected() {
        return !this.f11123c.isUseInnerHms() ? f11117j.get() != 3 : f11118k.get() != 3;
    }

    public boolean isConnecting() {
        return (this.f11123c.isUseInnerHms() ? f11118k : f11117j).get() == 5;
    }

    public void onConnecting() {
        connectionConnected();
    }

    public final void setInternalRequest(HuaweiApi.RequestHandler requestHandler) {
        this.f11128h = requestHandler;
    }

    public void setService(IAIDLInvoke iAIDLInvoke) {
        this.f11124d = iAIDLInvoke;
    }

    /* renamed from: b */
    void m15117b(int i) {
        if (this.f11123c.isUseInnerHms()) {
            f11118k.set(i);
        } else {
            f11117j.set(i);
        }
    }

    public void connect(int i, boolean z) {
        m15126a(i, z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m15128a() {
        String innerHmsPkg = this.f11123c.getInnerHmsPkg();
        String serviceAction = getServiceAction();
        HMSLog.m14110i("BaseHmsClient", "enter bindCoreService, packageName is " + innerHmsPkg + ", serviceAction is " + serviceAction);
        m15119a(innerHmsPkg, serviceAction);
    }

    /* renamed from: b */
    private void m15118b() {
        synchronized (f11116i) {
            Handler handler = this.f11127g;
            if (handler != null) {
                handler.removeMessages(2);
                this.f11127g = null;
            }
        }
    }

    /* renamed from: a */
    private void m15119a(String str, String str2) {
        if (this.f11123c.isUseInnerHms()) {
            f11120m = InnerBinderAdapter.getInstance(this.f11121a, str2, str);
            if (isConnected()) {
                HMSLog.m14110i("BaseHmsClient", "The binder is already connected.");
                getAdapter().updateDelayTask();
                connectedInternal(getAdapter().getServiceBinder());
                return;
            }
            m15117b(5);
            f11120m.binder(m15114c());
            return;
        }
        f11119l = OuterBinderAdapter.getInstance(this.f11121a, str2, str);
        if (isConnected()) {
            HMSLog.m14110i("BaseHmsClient", "The binder is already connected.");
            getAdapter().updateDelayTask();
            connectedInternal(getAdapter().getServiceBinder());
            return;
        }
        m15117b(5);
        f11119l.binder(m15114c());
    }

    /* renamed from: b */
    private void m15116b(AvailableAdapter availableAdapter, int i) {
        HMSLog.m14110i("BaseHmsClient", "enter HmsCore resolution");
        if (!getClientSettings().isHasActivity()) {
            m15124a(new ConnectionResult(26, HuaweiApiAvailability.getInstance().getErrPendingIntent(this.f11121a, i, 0)));
            return;
        }
        Activity activeActivity = Util.getActiveActivity(getClientSettings().getCpActivity(), getContext());
        if (activeActivity != null) {
            availableAdapter.startResolution(activeActivity, new AvailableAdapter.AvailableCallBack() { // from class: com.huawei.hms.common.internal.BaseHmsClient.3
                @Override // com.huawei.hms.adapter.AvailableAdapter.AvailableCallBack
                public void onComplete(int i2) {
                    if (i2 == 0) {
                        BaseHmsClient.this.m15128a();
                    } else {
                        BaseHmsClient.this.m15127a(i2);
                    }
                }
            });
        } else {
            m15127a(26);
        }
    }

    /* renamed from: a */
    private void m15126a(int i, boolean z) {
        HMSLog.m14110i("BaseHmsClient", "====== HMSSDK version: 60900300 ======");
        int i2 = (this.f11123c.isUseInnerHms() ? f11118k : f11117j).get();
        HMSLog.m14110i("BaseHmsClient", "Enter connect, Connection Status: " + i2);
        if (z || !(i2 == 3 || i2 == 5)) {
            if (getMinApkVersion() > i) {
                i = getMinApkVersion();
            }
            HMSLog.m14110i("BaseHmsClient", "connect minVersion:" + i + " packageName:" + this.f11123c.getInnerHmsPkg());
            if (this.f11121a.getPackageName().equals(this.f11123c.getInnerHmsPkg())) {
                HMSLog.m14110i("BaseHmsClient", "service packageName is same, bind core service return");
                m15128a();
            } else if (Util.isAvailableLibExist(this.f11121a)) {
                AvailableAdapter availableAdapter = new AvailableAdapter(i);
                int isHuaweiMobileServicesAvailable = availableAdapter.isHuaweiMobileServicesAvailable(this.f11121a);
                HMSLog.m14110i("BaseHmsClient", "check available result: " + isHuaweiMobileServicesAvailable);
                if (isHuaweiMobileServicesAvailable == 0) {
                    m15128a();
                } else if (availableAdapter.isUserResolvableError(isHuaweiMobileServicesAvailable)) {
                    HMSLog.m14110i("BaseHmsClient", "bindCoreService3.0 fail, start resolution now.");
                    m15116b(availableAdapter, isHuaweiMobileServicesAvailable);
                } else if (availableAdapter.isUserNoticeError(isHuaweiMobileServicesAvailable)) {
                    HMSLog.m14110i("BaseHmsClient", "bindCoreService3.0 fail, start notice now.");
                    m15125a(availableAdapter, isHuaweiMobileServicesAvailable);
                } else {
                    HMSLog.m14110i("BaseHmsClient", "bindCoreService3.0 fail: " + isHuaweiMobileServicesAvailable + " is not resolvable.");
                    m15127a(isHuaweiMobileServicesAvailable);
                }
            } else {
                int isHuaweiMobileServicesAvailable2 = HuaweiApiAvailability.getInstance().isHuaweiMobileServicesAvailable(this.f11121a, i);
                HMSLog.m14110i("BaseHmsClient", "HuaweiApiAvailability check available result: " + isHuaweiMobileServicesAvailable2);
                if (isHuaweiMobileServicesAvailable2 == 0) {
                    m15128a();
                } else {
                    m15127a(isHuaweiMobileServicesAvailable2);
                }
            }
        }
    }

    /* renamed from: a */
    private void m15125a(AvailableAdapter availableAdapter, int i) {
        HMSLog.m14110i("BaseHmsClient", "enter notice");
        if (!getClientSettings().isHasActivity()) {
            if (i == 29) {
                i = 9;
            }
            m15124a(new ConnectionResult(26, HuaweiApiAvailability.getInstance().getErrPendingIntent(this.f11121a, i, 0)));
            return;
        }
        Activity activeActivity = Util.getActiveActivity(getClientSettings().getCpActivity(), getContext());
        if (activeActivity != null) {
            availableAdapter.startNotice(activeActivity, new AvailableAdapter.AvailableCallBack() { // from class: com.huawei.hms.common.internal.BaseHmsClient.2
                @Override // com.huawei.hms.adapter.AvailableAdapter.AvailableCallBack
                public void onComplete(int i2) {
                    BaseHmsClient.this.m15127a(i2);
                }
            });
        } else {
            m15127a(26);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m15127a(int i) {
        HMSLog.m14110i("BaseHmsClient", "notifyFailed result: " + i);
        Message message = new Message();
        message.what = RequestManager.NOTIFY_CONNECT_FAILED;
        message.obj = new ConnectionResultWrapper(this.f11128h, new ConnectionResult(i));
        RequestManager.getHandler().sendMessage(message);
        OnConnectionFailedListener onConnectionFailedListener = this.f11126f;
        if (onConnectionFailedListener == null || (onConnectionFailedListener instanceof HuaweiApi.RequestHandler)) {
            return;
        }
        onConnectionFailedListener.onConnectionFailed(new ConnectionResult(i));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m15124a(ConnectionResult connectionResult) {
        HMSLog.m14110i("BaseHmsClient", "notifyFailed result: " + connectionResult.getErrorCode());
        Message message = new Message();
        message.what = RequestManager.NOTIFY_CONNECT_FAILED;
        HuaweiApi.RequestHandler requestHandler = this.f11128h;
        this.f11128h = null;
        message.obj = new ConnectionResultWrapper(requestHandler, connectionResult);
        RequestManager.getHandler().sendMessage(message);
        OnConnectionFailedListener onConnectionFailedListener = this.f11126f;
        if (onConnectionFailedListener == null || (onConnectionFailedListener instanceof HuaweiApi.RequestHandler)) {
            return;
        }
        onConnectionFailedListener.onConnectionFailed(connectionResult);
    }
}
