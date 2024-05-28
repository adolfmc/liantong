package com.huawei.hms.api;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.text.TextUtils;
import com.huawei.hms.api.Api;
import com.huawei.hms.api.HuaweiApiClient;
import com.huawei.hms.common.api.ConnectionPostProcessor;
import com.huawei.hms.common.internal.AutoLifecycleFragment;
import com.huawei.hms.core.aidl.CodecLookup;
import com.huawei.hms.core.aidl.DataBuffer;
import com.huawei.hms.core.aidl.IAIDLCallback;
import com.huawei.hms.core.aidl.IAIDLInvoke;
import com.huawei.hms.core.aidl.IMessageEntity;
import com.huawei.hms.core.aidl.MessageCodec;
import com.huawei.hms.core.aidl.RequestHeader;
import com.huawei.hms.core.aidl.ResponseHeader;
import com.huawei.hms.support.api.PendingResultImpl;
import com.huawei.hms.support.api.ResolveResult;
import com.huawei.hms.support.api.client.ApiClient;
import com.huawei.hms.support.api.client.BundleResult;
import com.huawei.hms.support.api.client.InnerApiClient;
import com.huawei.hms.support.api.client.PendingResult;
import com.huawei.hms.support.api.client.ResultCallback;
import com.huawei.hms.support.api.client.Status;
import com.huawei.hms.support.api.client.SubAppInfo;
import com.huawei.hms.support.api.core.ConnectService;
import com.huawei.hms.support.api.entity.auth.PermissionInfo;
import com.huawei.hms.support.api.entity.auth.Scope;
import com.huawei.hms.support.api.entity.core.CheckConnectInfo;
import com.huawei.hms.support.api.entity.core.ConnectInfo;
import com.huawei.hms.support.api.entity.core.ConnectResp;
import com.huawei.hms.support.api.entity.core.DisconnectInfo;
import com.huawei.hms.support.api.entity.core.DisconnectResp;
import com.huawei.hms.support.api.entity.core.JosGetNoticeResp;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.utils.Checker;
import com.huawei.hms.utils.HMSPackageManager;
import com.huawei.hms.utils.PackageManagerHelper;
import com.huawei.hms.utils.UIUtil;
import com.huawei.hms.utils.Util;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class HuaweiApiClientImpl extends HuaweiApiClient implements ServiceConnection, InnerApiClient {

    /* renamed from: A */
    private static final Object f11000A = new Object();

    /* renamed from: B */
    private static final Object f11001B = new Object();
    public static final String DEFAULT_ACCOUNT = "<<default account>>";
    public static final int SIGN_IN_MODE_OPTIONAL = 2;
    public static final int SIGN_IN_MODE_REQUIRED = 1;

    /* renamed from: b */
    private final Context f11003b;

    /* renamed from: c */
    private final String f11004c;

    /* renamed from: d */
    private String f11005d;

    /* renamed from: e */
    private String f11006e;

    /* renamed from: f */
    private volatile IAIDLInvoke f11007f;

    /* renamed from: g */
    private String f11008g;

    /* renamed from: h */
    private WeakReference<Activity> f11009h;

    /* renamed from: i */
    private WeakReference<Activity> f11010i;

    /* renamed from: l */
    private List<Scope> f11013l;

    /* renamed from: m */
    private List<PermissionInfo> f11014m;

    /* renamed from: n */
    private Map<Api<?>, Api.ApiOptions> f11015n;

    /* renamed from: o */
    private SubAppInfo f11016o;

    /* renamed from: s */
    private final ReentrantLock f11020s;

    /* renamed from: t */
    private final Condition f11021t;

    /* renamed from: u */
    private ConnectionResult f11022u;

    /* renamed from: v */
    private HuaweiApiClient.ConnectionCallbacks f11023v;

    /* renamed from: w */
    private HuaweiApiClient.OnConnectionFailedListener f11024w;

    /* renamed from: x */
    private Handler f11025x;

    /* renamed from: y */
    private Handler f11026y;

    /* renamed from: z */
    private CheckUpdatelistener f11027z;

    /* renamed from: a */
    private int f11002a = -1;

    /* renamed from: j */
    private boolean f11011j = false;

    /* renamed from: k */
    private AtomicInteger f11012k = new AtomicInteger(1);

    /* renamed from: p */
    private long f11017p = 0;

    /* renamed from: q */
    private int f11018q = 0;

    /* renamed from: r */
    private final Object f11019r = new Object();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.huawei.hms.api.HuaweiApiClientImpl$a */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class C4856a implements Handler.Callback {
        C4856a() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            if (message == null || message.what != 2) {
                return false;
            }
            HMSLog.m14112e("HuaweiApiClientImpl", "In connect, bind core service time out");
            if (HuaweiApiClientImpl.this.f11012k.get() == 5) {
                HuaweiApiClientImpl.this.m15212c(1);
                HuaweiApiClientImpl.this.m15218b();
            }
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.huawei.hms.api.HuaweiApiClientImpl$b */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class C4857b implements Handler.Callback {
        C4857b() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            if (message == null || message.what != 3) {
                return false;
            }
            HMSLog.m14112e("HuaweiApiClientImpl", "In connect, process time out");
            if (HuaweiApiClientImpl.this.f11012k.get() == 2) {
                HuaweiApiClientImpl.this.m15212c(1);
                HuaweiApiClientImpl.this.m15218b();
            }
            return true;
        }
    }

    /* renamed from: com.huawei.hms.api.HuaweiApiClientImpl$c */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    class BinderC4858c extends IAIDLCallback.Stub {

        /* renamed from: a */
        final /* synthetic */ ResultCallback f11030a;

        BinderC4858c(ResultCallback resultCallback) {
            this.f11030a = resultCallback;
        }

        @Override // com.huawei.hms.core.aidl.IAIDLCallback
        public void call(DataBuffer dataBuffer) {
            if (dataBuffer != null) {
                MessageCodec find = CodecLookup.find(dataBuffer.getProtocol());
                ResponseHeader responseHeader = new ResponseHeader();
                find.decode(dataBuffer.header, responseHeader);
                BundleResult bundleResult = new BundleResult(responseHeader.getStatusCode(), dataBuffer.getBody());
                HMSLog.m14110i("HuaweiApiClientImpl", "Exit asyncRequest onResult");
                this.f11030a.onResult(bundleResult);
                return;
            }
            HMSLog.m14110i("HuaweiApiClientImpl", "Exit asyncRequest onResult -1");
            this.f11030a.onResult(new BundleResult(-1, null));
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.huawei.hms.api.HuaweiApiClientImpl$d */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    static class C4859d extends PendingResultImpl<Status, IMessageEntity> {
        public C4859d(ApiClient apiClient, String str, IMessageEntity iMessageEntity) {
            super(apiClient, str, iMessageEntity);
        }

        @Override // com.huawei.hms.support.api.PendingResultImpl
        /* renamed from: a */
        public Status onComplete(IMessageEntity iMessageEntity) {
            return new Status(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.huawei.hms.api.HuaweiApiClientImpl$e */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class C4860e implements ResultCallback<ResolveResult<ConnectResp>> {

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: com.huawei.hms.api.HuaweiApiClientImpl$e$a */
        /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
        public class RunnableC4861a implements Runnable {

            /* renamed from: a */
            final /* synthetic */ ResolveResult f11033a;

            RunnableC4861a(ResolveResult resolveResult) {
                this.f11033a = resolveResult;
            }

            @Override // java.lang.Runnable
            public void run() {
                HuaweiApiClientImpl.this.m15219a(this.f11033a);
            }
        }

        private C4860e() {
        }

        @Override // com.huawei.hms.support.api.client.ResultCallback
        /* renamed from: a */
        public void onResult(ResolveResult<ConnectResp> resolveResult) {
            new Handler(Looper.getMainLooper()).post(new RunnableC4861a(resolveResult));
        }

        /* synthetic */ C4860e(HuaweiApiClientImpl huaweiApiClientImpl, C4856a c4856a) {
            this();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.huawei.hms.api.HuaweiApiClientImpl$f */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class C4862f implements ResultCallback<ResolveResult<DisconnectResp>> {

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: com.huawei.hms.api.HuaweiApiClientImpl$f$a */
        /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
        public class RunnableC4863a implements Runnable {

            /* renamed from: a */
            final /* synthetic */ ResolveResult f11036a;

            RunnableC4863a(ResolveResult resolveResult) {
                this.f11036a = resolveResult;
            }

            @Override // java.lang.Runnable
            public void run() {
                HuaweiApiClientImpl.this.m15214b(this.f11036a);
            }
        }

        private C4862f() {
        }

        @Override // com.huawei.hms.support.api.client.ResultCallback
        /* renamed from: a */
        public void onResult(ResolveResult<DisconnectResp> resolveResult) {
            new Handler(Looper.getMainLooper()).post(new RunnableC4863a(resolveResult));
        }

        /* synthetic */ C4862f(HuaweiApiClientImpl huaweiApiClientImpl, C4856a c4856a) {
            this();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.huawei.hms.api.HuaweiApiClientImpl$g */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class C4864g implements ResultCallback<ResolveResult<JosGetNoticeResp>> {
        private C4864g() {
        }

        @Override // com.huawei.hms.support.api.client.ResultCallback
        /* renamed from: a */
        public void onResult(ResolveResult<JosGetNoticeResp> resolveResult) {
            JosGetNoticeResp value;
            Intent noticeIntent;
            if (resolveResult == null || !resolveResult.getStatus().isSuccess() || (noticeIntent = (value = resolveResult.getValue()).getNoticeIntent()) == null || value.getStatusCode() != 0) {
                return;
            }
            HMSLog.m14110i("HuaweiApiClientImpl", "get notice has intent.");
            Activity validActivity = Util.getValidActivity((Activity) HuaweiApiClientImpl.this.f11009h.get(), HuaweiApiClientImpl.this.getTopActivity());
            if (validActivity != null) {
                HuaweiApiClientImpl.this.f11011j = true;
                validActivity.startActivity(noticeIntent);
                return;
            }
            HMSLog.m14112e("HuaweiApiClientImpl", "showNotice no valid activity!");
        }

        /* synthetic */ C4864g(HuaweiApiClientImpl huaweiApiClientImpl, C4856a c4856a) {
            this();
        }
    }

    public HuaweiApiClientImpl(Context context) {
        ReentrantLock reentrantLock = new ReentrantLock();
        this.f11020s = reentrantLock;
        this.f11021t = reentrantLock.newCondition();
        this.f11025x = null;
        this.f11026y = null;
        this.f11027z = null;
        this.f11003b = context;
        String appId = Util.getAppId(context);
        this.f11004c = appId;
        this.f11005d = appId;
        this.f11006e = Util.getCpId(context);
    }

    /* renamed from: d */
    private DisconnectInfo m15209d() {
        ArrayList arrayList = new ArrayList();
        Map<Api<?>, Api.ApiOptions> map = this.f11015n;
        if (map != null) {
            for (Api<?> api : map.keySet()) {
                arrayList.add(api.getApiName());
            }
        }
        return new DisconnectInfo(this.f11013l, arrayList);
    }

    /* renamed from: e */
    private int m15208e() {
        int hmsVersion = Util.getHmsVersion(this.f11003b);
        if (hmsVersion == 0 || hmsVersion < 20503000) {
            int m15207f = m15207f();
            if (m15206g()) {
                if (m15207f < 20503000) {
                    return 20503000;
                }
                return m15207f;
            } else if (m15207f < 20600000) {
                return 20600000;
            } else {
                return m15207f;
            }
        }
        return hmsVersion;
    }

    /* renamed from: f */
    private int m15207f() {
        Integer num;
        int intValue;
        Map<Api<?>, Api.ApiOptions> apiMap = getApiMap();
        int i = 0;
        if (apiMap == null) {
            return 0;
        }
        for (Api<?> api : apiMap.keySet()) {
            String apiName = api.getApiName();
            if (!TextUtils.isEmpty(apiName) && (num = HuaweiApiAvailability.getApiMap().get(apiName)) != null && (intValue = num.intValue()) > i) {
                i = intValue;
            }
        }
        return i;
    }

    /* renamed from: g */
    private boolean m15206g() {
        Map<Api<?>, Api.ApiOptions> map = this.f11015n;
        if (map != null) {
            for (Api<?> api : map.keySet()) {
                if ("HuaweiGame.API".equals(api.getApiName())) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    /* renamed from: h */
    private void m15205h() {
        Handler handler = this.f11025x;
        if (handler != null) {
            handler.removeMessages(2);
        } else {
            this.f11025x = new Handler(Looper.getMainLooper(), new C4856a());
        }
        this.f11025x.sendEmptyMessageDelayed(2, 5000L);
    }

    /* renamed from: i */
    private void m15204i() {
        synchronized (f11001B) {
            Handler handler = this.f11026y;
            if (handler != null) {
                handler.removeMessages(3);
            } else {
                this.f11026y = new Handler(Looper.getMainLooper(), new C4857b());
            }
            boolean sendEmptyMessageDelayed = this.f11026y.sendEmptyMessageDelayed(3, 3000L);
            HMSLog.m14115d("HuaweiApiClientImpl", "sendEmptyMessageDelayed for onConnectionResult 3 seconds. the result is : " + sendEmptyMessageDelayed);
        }
    }

    /* renamed from: j */
    private void m15203j() {
        HMSLog.m14110i("HuaweiApiClientImpl", "Enter sendConnectApiServceRequest.");
        ConnectService.connect(this, m15213c()).setResultCallback(new C4860e(this, null));
    }

    /* renamed from: k */
    private void m15202k() {
        ConnectService.disconnect(this, m15209d()).setResultCallback(new C4862f(this, null));
    }

    /* renamed from: l */
    private void m15201l() {
        HMSLog.m14110i("HuaweiApiClientImpl", "Enter sendForceConnectApiServceRequest.");
        ConnectService.forceConnect(this, m15213c()).setResultCallback(new C4860e(this, null));
    }

    /* renamed from: m */
    private void m15200m() {
        if (this.f11011j) {
            HMSLog.m14110i("HuaweiApiClientImpl", "Connect notice has been shown.");
        } else if (HuaweiApiAvailability.getInstance().isHuaweiMobileNoticeAvailable(this.f11003b) == 0) {
            ConnectService.getNotice(this, 0, "6.9.0.300").setResultCallback(new C4864g(this, null));
        }
    }

    /* renamed from: n */
    private void m15199n() {
        Util.unBindServiceCatchException(this.f11003b, this);
        this.f11007f = null;
    }

    public int asyncRequest(Bundle bundle, String str, int i, ResultCallback<BundleResult> resultCallback) {
        HMSLog.m14110i("HuaweiApiClientImpl", "Enter asyncRequest.");
        if (resultCallback != null && str != null && bundle != null) {
            if (!innerIsConnected()) {
                HMSLog.m14112e("HuaweiApiClientImpl", "client is unConnect.");
                return 907135003;
            }
            DataBuffer dataBuffer = new DataBuffer(str, i);
            MessageCodec find = CodecLookup.find(dataBuffer.getProtocol());
            dataBuffer.addBody(bundle);
            RequestHeader requestHeader = new RequestHeader(getAppID(), getPackageName(), 60900300, getSessionId());
            requestHeader.setApiNameList(getApiNameList());
            dataBuffer.header = find.encode(requestHeader, new Bundle());
            try {
                getService().asyncCall(dataBuffer, new BinderC4858c(resultCallback));
                return 0;
            } catch (RemoteException e) {
                HMSLog.m14112e("HuaweiApiClientImpl", "remote exception:" + e.getMessage());
                return 907135001;
            }
        }
        HMSLog.m14112e("HuaweiApiClientImpl", "arguments is invalid.");
        return 907135000;
    }

    @Override // com.huawei.hms.api.HuaweiApiClient
    public void checkUpdate(Activity activity, CheckUpdatelistener checkUpdatelistener) {
        if (checkUpdatelistener == null) {
            HMSLog.m14112e("HuaweiApiClientImpl", "listener is null!");
        } else if (activity != null && !activity.isFinishing()) {
            this.f11027z = checkUpdatelistener;
            try {
                Class<?> cls = Class.forName("com.huawei.hms.update.manager.CheckUpdateLegacy");
                cls.getMethod("initCheckUpdateCallBack", Object.class, Activity.class).invoke(cls.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]), this, activity);
            } catch (ClassCastException | ClassNotFoundException | IllegalAccessException | IllegalArgumentException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
                HMSLog.m14112e("HuaweiApiClientImpl", "invoke CheckUpdateLegacy.initCheckUpdateCallBack fail. " + e.getMessage());
                checkUpdatelistener.onResult(-1);
            }
        } else {
            HMSLog.m14112e("HuaweiApiClientImpl", "checkUpdate, activity is illegal: " + activity);
            checkUpdatelistener.onResult(-1);
        }
    }

    @Override // com.huawei.hms.api.HuaweiApiClient
    public void connect(Activity activity) {
        HMSLog.m14110i("HuaweiApiClientImpl", "====== HMSSDK version: 60900300 ======");
        int i = this.f11012k.get();
        HMSLog.m14110i("HuaweiApiClientImpl", "Enter connect, Connection Status: " + i);
        if (i == 3 || i == 5 || i == 2 || i == 4) {
            return;
        }
        if (activity != null) {
            this.f11009h = new WeakReference<>(activity);
            this.f11010i = new WeakReference<>(activity);
        }
        this.f11005d = TextUtils.isEmpty(this.f11004c) ? Util.getAppId(this.f11003b) : this.f11004c;
        int m15208e = m15208e();
        HMSLog.m14110i("HuaweiApiClientImpl", "connect minVersion:" + m15208e);
        HuaweiApiAvailability.setServicesVersionCode(m15208e);
        int isHuaweiMobileServicesAvailable = HuaweiMobileServicesUtil.isHuaweiMobileServicesAvailable(this.f11003b, m15208e);
        HMSLog.m14110i("HuaweiApiClientImpl", "In connect, isHuaweiMobileServicesAvailable result: " + isHuaweiMobileServicesAvailable);
        this.f11018q = HMSPackageManager.getInstance(this.f11003b).getHmsMultiServiceVersion();
        if (isHuaweiMobileServicesAvailable == 0) {
            m15212c(5);
            if (this.f11007f == null) {
                m15225a();
                return;
            }
            m15212c(2);
            m15203j();
            m15204i();
        } else if (this.f11024w != null) {
            m15217b(isHuaweiMobileServicesAvailable);
        }
    }

    @Override // com.huawei.hms.api.HuaweiApiClient
    public void connectForeground() {
        HMSLog.m14110i("HuaweiApiClientImpl", "====== HMSSDK version: 60900300 ======");
        int i = this.f11012k.get();
        HMSLog.m14110i("HuaweiApiClientImpl", "Enter forceConnect, Connection Status: " + i);
        if (i == 3 || i == 5 || i == 2 || i == 4) {
            return;
        }
        this.f11005d = TextUtils.isEmpty(this.f11004c) ? Util.getAppId(this.f11003b) : this.f11004c;
        m15201l();
    }

    @Override // com.huawei.hms.api.HuaweiApiClient
    public void disableLifeCycleManagement(Activity activity) {
        if (this.f11002a >= 0) {
            AutoLifecycleFragment.getInstance(activity).stopAutoManage(this.f11002a);
            return;
        }
        throw new IllegalStateException("disableLifeCycleManagement failed");
    }

    @Override // com.huawei.hms.api.HuaweiApiClient
    public PendingResult<Status> discardAndReconnect() {
        return new C4859d(this, null, null);
    }

    @Override // com.huawei.hms.api.HuaweiApiClient
    public void disconnect() {
        int i = this.f11012k.get();
        HMSLog.m14110i("HuaweiApiClientImpl", "Enter disconnect, Connection Status: " + i);
        if (i == 2) {
            m15212c(4);
        } else if (i == 3) {
            m15212c(4);
            m15202k();
        } else if (i != 5) {
        } else {
            m15224a(2);
            m15212c(4);
        }
    }

    @Override // com.huawei.hms.api.HuaweiApiClient
    public Map<Api<?>, Api.ApiOptions> getApiMap() {
        return this.f11015n;
    }

    @Override // com.huawei.hms.support.api.client.AidlApiClient
    public List<String> getApiNameList() {
        ArrayList arrayList = new ArrayList();
        Map<Api<?>, Api.ApiOptions> map = this.f11015n;
        if (map != null) {
            for (Api<?> api : map.keySet()) {
                arrayList.add(api.getApiName());
            }
        }
        return arrayList;
    }

    @Override // com.huawei.hms.support.api.client.ApiClient
    public String getAppID() {
        return this.f11005d;
    }

    @Override // com.huawei.hms.api.HuaweiApiClient
    public ConnectionResult getConnectionResult(Api<?> api) {
        if (isConnected()) {
            this.f11022u = null;
            return new ConnectionResult(0, (PendingIntent) null);
        }
        ConnectionResult connectionResult = this.f11022u;
        return connectionResult != null ? connectionResult : new ConnectionResult(13, (PendingIntent) null);
    }

    @Override // com.huawei.hms.support.api.client.ApiClient
    public Context getContext() {
        return this.f11003b;
    }

    @Override // com.huawei.hms.support.api.client.ApiClient
    public String getCpID() {
        return this.f11006e;
    }

    @Override // com.huawei.hms.support.api.client.ApiClient
    public String getPackageName() {
        return this.f11003b.getPackageName();
    }

    @Override // com.huawei.hms.api.HuaweiApiClient
    public List<PermissionInfo> getPermissionInfos() {
        return this.f11014m;
    }

    @Override // com.huawei.hms.api.HuaweiApiClient
    public List<Scope> getScopes() {
        return this.f11013l;
    }

    @Override // com.huawei.hms.support.api.client.AidlApiClient
    public IAIDLInvoke getService() {
        return this.f11007f;
    }

    @Override // com.huawei.hms.support.api.client.ApiClient
    public String getSessionId() {
        return this.f11008g;
    }

    @Override // com.huawei.hms.support.api.client.ApiClient
    public final SubAppInfo getSubAppInfo() {
        return this.f11016o;
    }

    @Override // com.huawei.hms.api.HuaweiApiClient
    public Activity getTopActivity() {
        WeakReference<Activity> weakReference = this.f11010i;
        if (weakReference == null) {
            return null;
        }
        return weakReference.get();
    }

    @Override // com.huawei.hms.support.api.client.ApiClient
    public String getTransportName() {
        return IPCTransport.class.getName();
    }

    @Override // com.huawei.hms.api.HuaweiApiClient
    public boolean hasConnectedApi(Api<?> api) {
        return isConnected();
    }

    @Override // com.huawei.hms.api.HuaweiApiClient
    public boolean hasConnectionFailureListener(HuaweiApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        Checker.checkNonNull(onConnectionFailedListener, "onConnectionFailedListener should not be null");
        synchronized (this.f11019r) {
            return this.f11024w == onConnectionFailedListener;
        }
    }

    @Override // com.huawei.hms.api.HuaweiApiClient
    public boolean hasConnectionSuccessListener(HuaweiApiClient.ConnectionCallbacks connectionCallbacks) {
        Checker.checkNonNull(connectionCallbacks, "connectionCallbacksListener should not be null");
        synchronized (this.f11019r) {
            return this.f11023v == connectionCallbacks;
        }
    }

    @Override // com.huawei.hms.api.HuaweiApiClient
    public ConnectionResult holdUpConnect() {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            this.f11020s.lock();
            try {
                connect((Activity) null);
                while (isConnecting()) {
                    this.f11021t.await();
                }
                if (isConnected()) {
                    this.f11022u = null;
                    return new ConnectionResult(0, (PendingIntent) null);
                }
                ConnectionResult connectionResult = this.f11022u;
                return connectionResult != null ? connectionResult : new ConnectionResult(13, (PendingIntent) null);
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
                return new ConnectionResult(15, (PendingIntent) null);
            } finally {
                this.f11020s.unlock();
            }
        }
        throw new IllegalStateException("blockingConnect must not be called on the UI thread");
    }

    @Override // com.huawei.hms.support.api.client.InnerApiClient
    public boolean innerIsConnected() {
        return this.f11012k.get() == 3 || this.f11012k.get() == 4;
    }

    @Override // com.huawei.hms.api.HuaweiApiClient, com.huawei.hms.support.api.client.ApiClient
    public boolean isConnected() {
        if (this.f11018q == 0) {
            this.f11018q = HMSPackageManager.getInstance(this.f11003b).getHmsMultiServiceVersion();
        }
        if (this.f11018q < 20504000) {
            long currentTimeMillis = System.currentTimeMillis() - this.f11017p;
            if (currentTimeMillis > 0 && currentTimeMillis < 300000) {
                return innerIsConnected();
            }
            if (innerIsConnected()) {
                Status status = ConnectService.checkconnect(this, new CheckConnectInfo()).awaitOnAnyThread(2000L, TimeUnit.MILLISECONDS).getStatus();
                if (status.isSuccess()) {
                    this.f11017p = System.currentTimeMillis();
                    return true;
                }
                int statusCode = status.getStatusCode();
                HMSLog.m14110i("HuaweiApiClientImpl", "isConnected is false, statuscode:" + statusCode);
                if (statusCode != 907135004) {
                    m15199n();
                    m15212c(1);
                    this.f11017p = System.currentTimeMillis();
                    return false;
                }
                return false;
            }
            return false;
        }
        return innerIsConnected();
    }

    @Override // com.huawei.hms.api.HuaweiApiClient
    public boolean isConnecting() {
        int i = this.f11012k.get();
        return i == 2 || i == 5;
    }

    @Override // com.huawei.hms.api.HuaweiApiClient
    public void onPause(Activity activity) {
        HMSLog.m14110i("HuaweiApiClientImpl", "onPause");
    }

    public void onResult(int i) {
        this.f11027z.onResult(i);
    }

    @Override // com.huawei.hms.api.HuaweiApiClient
    public void onResume(Activity activity) {
        if (activity != null) {
            HMSLog.m14110i("HuaweiApiClientImpl", "onResume");
            this.f11010i = new WeakReference<>(activity);
        }
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        HMSLog.m14110i("HuaweiApiClientImpl", "HuaweiApiClientImpl Enter onServiceConnected.");
        m15224a(2);
        this.f11007f = IAIDLInvoke.Stub.asInterface(iBinder);
        if (this.f11007f == null) {
            HMSLog.m14112e("HuaweiApiClientImpl", "In onServiceConnected, mCoreService must not be null.");
            m15199n();
            m15212c(1);
            if (this.f11024w != null) {
                PendingIntent pendingIntent = null;
                WeakReference<Activity> weakReference = this.f11009h;
                if (weakReference != null && weakReference.get() != null) {
                    pendingIntent = HuaweiApiAvailability.getInstance().getResolveErrorPendingIntent(this.f11009h.get(), 10);
                }
                ConnectionResult connectionResult = new ConnectionResult(10, pendingIntent);
                this.f11024w.onConnectionFailed(connectionResult);
                this.f11022u = connectionResult;
            }
        } else if (this.f11012k.get() == 5) {
            m15212c(2);
            m15203j();
            m15204i();
        } else if (this.f11012k.get() != 3) {
            m15199n();
        }
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName componentName) {
        HMSLog.m14110i("HuaweiApiClientImpl", "Enter onServiceDisconnected.");
        this.f11007f = null;
        m15212c(1);
        HuaweiApiClient.ConnectionCallbacks connectionCallbacks = this.f11023v;
        if (connectionCallbacks != null) {
            connectionCallbacks.onConnectionSuspended(1);
        }
    }

    @Override // com.huawei.hms.api.HuaweiApiClient
    public void print(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
    }

    @Override // com.huawei.hms.api.HuaweiApiClient
    public void reconnect() {
        disconnect();
        connect((Activity) null);
    }

    @Override // com.huawei.hms.api.HuaweiApiClient
    public void removeConnectionFailureListener(HuaweiApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        Checker.checkNonNull(onConnectionFailedListener, "onConnectionFailedListener should not be null");
        synchronized (this.f11019r) {
            if (this.f11024w != onConnectionFailedListener) {
                HMSLog.m14109w("HuaweiApiClientImpl", "unregisterConnectionFailedListener: this onConnectionFailedListener has not been registered");
            } else {
                this.f11024w = null;
            }
        }
    }

    @Override // com.huawei.hms.api.HuaweiApiClient
    public void removeConnectionSuccessListener(HuaweiApiClient.ConnectionCallbacks connectionCallbacks) {
        Checker.checkNonNull(connectionCallbacks, "connectionCallbacksListener should not be null");
        synchronized (this.f11019r) {
            if (this.f11023v != connectionCallbacks) {
                HMSLog.m14109w("HuaweiApiClientImpl", "unregisterConnectionCallback: this connectionCallbacksListener has not been registered");
            } else {
                this.f11023v = null;
            }
        }
    }

    public void resetListener() {
        this.f11027z = null;
    }

    public void setApiMap(Map<Api<?>, Api.ApiOptions> map) {
        this.f11015n = map;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setAutoLifecycleClientId(int i) {
        this.f11002a = i;
    }

    @Override // com.huawei.hms.api.HuaweiApiClient
    public void setConnectionCallbacks(HuaweiApiClient.ConnectionCallbacks connectionCallbacks) {
        this.f11023v = connectionCallbacks;
    }

    @Override // com.huawei.hms.api.HuaweiApiClient
    public void setConnectionFailedListener(HuaweiApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        this.f11024w = onConnectionFailedListener;
    }

    public void setHasShowNotice(boolean z) {
        this.f11011j = z;
    }

    public void setPermissionInfos(List<PermissionInfo> list) {
        this.f11014m = list;
    }

    public void setScopes(List<Scope> list) {
        this.f11013l = list;
    }

    @Override // com.huawei.hms.api.HuaweiApiClient
    public boolean setSubAppInfo(SubAppInfo subAppInfo) {
        HMSLog.m14110i("HuaweiApiClientImpl", "Enter setSubAppInfo");
        if (subAppInfo == null) {
            HMSLog.m14112e("HuaweiApiClientImpl", "subAppInfo is null");
            return false;
        }
        String subAppID = subAppInfo.getSubAppID();
        if (TextUtils.isEmpty(subAppID)) {
            HMSLog.m14112e("HuaweiApiClientImpl", "subAppId is empty");
            return false;
        }
        if (subAppID.equals(TextUtils.isEmpty(this.f11004c) ? Util.getAppId(this.f11003b) : this.f11004c)) {
            HMSLog.m14112e("HuaweiApiClientImpl", "subAppId is host appid");
            return false;
        }
        this.f11016o = new SubAppInfo(subAppInfo);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public void m15212c(int i) {
        this.f11012k.set(i);
        if (i == 1 || i == 3 || i == 2) {
            this.f11020s.lock();
            try {
                this.f11021t.signalAll();
            } finally {
                this.f11020s.unlock();
            }
        }
    }

    /* renamed from: b */
    private void m15217b(int i) {
        PendingIntent pendingIntent;
        WeakReference<Activity> weakReference = this.f11009h;
        if (weakReference == null || weakReference.get() == null) {
            pendingIntent = null;
        } else {
            pendingIntent = HuaweiApiAvailability.getInstance().getResolveErrorPendingIntent(this.f11009h.get(), i);
            HMSLog.m14110i("HuaweiApiClientImpl", "connect 2.0 fail: " + i);
        }
        ConnectionResult connectionResult = new ConnectionResult(i, pendingIntent);
        this.f11024w.onConnectionFailed(connectionResult);
        this.f11022u = connectionResult;
    }

    /* renamed from: a */
    private void m15225a() {
        Intent intent = new Intent(HMSPackageManager.getInstance(this.f11003b).getServiceAction());
        HMSPackageManager.getInstance(this.f11003b).refreshForMultiService();
        try {
            intent.setPackage(HMSPackageManager.getInstance(this.f11003b).getHMSPackageNameForMultiService());
            synchronized (f11000A) {
                if (this.f11003b.bindService(intent, this, 1)) {
                    m15205h();
                    return;
                }
                m15212c(1);
                HMSLog.m14112e("HuaweiApiClientImpl", "In connect, bind core service fail");
                m15218b();
            }
        } catch (IllegalArgumentException unused) {
            HMSLog.m14112e("HuaweiApiClientImpl", "IllegalArgumentException when bindCoreService intent.setPackage");
            m15212c(1);
            HMSLog.m14112e("HuaweiApiClientImpl", "In connect, bind core service fail");
            m15218b();
        }
    }

    /* renamed from: c */
    private ConnectInfo m15213c() {
        String packageSignature = new PackageManagerHelper(this.f11003b).getPackageSignature(this.f11003b.getPackageName());
        if (packageSignature == null) {
            packageSignature = "";
        }
        SubAppInfo subAppInfo = this.f11016o;
        return new ConnectInfo(getApiNameList(), this.f11013l, packageSignature, subAppInfo == null ? null : subAppInfo.getSubAppID());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public void m15218b() {
        m15199n();
        if (this.f11024w != null) {
            int i = UIUtil.isBackground(this.f11003b) ? 7 : 6;
            PendingIntent pendingIntent = null;
            WeakReference<Activity> weakReference = this.f11009h;
            if (weakReference != null && weakReference.get() != null) {
                pendingIntent = HuaweiApiAvailability.getInstance().getResolveErrorPendingIntent(this.f11009h.get(), i);
            }
            ConnectionResult connectionResult = new ConnectionResult(i, pendingIntent);
            this.f11024w.onConnectionFailed(connectionResult);
            this.f11022u = connectionResult;
        }
    }

    /* renamed from: c */
    private void m15210c(ResolveResult<ConnectResp> resolveResult) {
        if (resolveResult.getValue() != null) {
            ProtocolNegotiate.getInstance().negotiate(resolveResult.getValue().protocolVersion);
        }
        m15212c(3);
        this.f11022u = null;
        HuaweiApiClient.ConnectionCallbacks connectionCallbacks = this.f11023v;
        if (connectionCallbacks != null) {
            connectionCallbacks.onConnected();
        }
        if (this.f11009h != null) {
            m15200m();
        }
        for (Map.Entry<Api<?>, Api.ApiOptions> entry : getApiMap().entrySet()) {
            if (entry.getKey().getmConnetctPostList() != null && !entry.getKey().getmConnetctPostList().isEmpty()) {
                HMSLog.m14110i("HuaweiApiClientImpl", "Enter onConnectionResult, get the ConnetctPostList ");
                for (ConnectionPostProcessor connectionPostProcessor : entry.getKey().getmConnetctPostList()) {
                    HMSLog.m14110i("HuaweiApiClientImpl", "Enter onConnectionResult, processor.run");
                    connectionPostProcessor.run(this, this.f11009h);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public void m15214b(ResolveResult<DisconnectResp> resolveResult) {
        HMSLog.m14110i("HuaweiApiClientImpl", "Enter onDisconnectionResult, disconnect from server result: " + resolveResult.getStatus().getStatusCode());
        m15199n();
        m15212c(1);
    }

    /* renamed from: a */
    private void m15224a(int i) {
        if (i == 2) {
            synchronized (f11000A) {
                Handler handler = this.f11025x;
                if (handler != null) {
                    handler.removeMessages(i);
                    this.f11025x = null;
                }
            }
        }
        if (i == 3) {
            synchronized (f11001B) {
                Handler handler2 = this.f11026y;
                if (handler2 != null) {
                    handler2.removeMessages(i);
                    this.f11026y = null;
                }
            }
        }
        synchronized (f11000A) {
            Handler handler3 = this.f11025x;
            if (handler3 != null) {
                handler3.removeMessages(2);
                this.f11025x = null;
            }
        }
    }

    @Override // com.huawei.hms.api.HuaweiApiClient
    public void connect(int i) {
        connect((Activity) null);
    }

    @Override // com.huawei.hms.api.HuaweiApiClient
    public ConnectionResult holdUpConnect(long j, TimeUnit timeUnit) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            this.f11020s.lock();
            try {
                connect((Activity) null);
                long nanos = timeUnit.toNanos(j);
                while (isConnecting()) {
                    if (nanos <= 0) {
                        disconnect();
                        return new ConnectionResult(14, (PendingIntent) null);
                    }
                    nanos = this.f11021t.awaitNanos(nanos);
                }
                if (isConnected()) {
                    this.f11022u = null;
                    return new ConnectionResult(0, (PendingIntent) null);
                }
                ConnectionResult connectionResult = this.f11022u;
                return connectionResult != null ? connectionResult : new ConnectionResult(13, (PendingIntent) null);
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
                return new ConnectionResult(15, (PendingIntent) null);
            } finally {
                this.f11020s.unlock();
            }
        }
        throw new IllegalStateException("blockingConnect must not be called on the UI thread");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m15219a(ResolveResult<ConnectResp> resolveResult) {
        HMSLog.m14110i("HuaweiApiClientImpl", "Enter onConnectionResult");
        if (this.f11007f != null && this.f11012k.get() == 2) {
            m15224a(3);
            ConnectResp value = resolveResult.getValue();
            if (value != null) {
                this.f11008g = value.sessionId;
            }
            SubAppInfo subAppInfo = this.f11016o;
            PendingIntent pendingIntent = null;
            String subAppID = subAppInfo == null ? null : subAppInfo.getSubAppID();
            if (!TextUtils.isEmpty(subAppID)) {
                this.f11005d = subAppID;
            }
            int statusCode = resolveResult.getStatus().getStatusCode();
            HMSLog.m14110i("HuaweiApiClientImpl", "Enter onConnectionResult, connect to server result: " + statusCode);
            if (Status.SUCCESS.equals(resolveResult.getStatus())) {
                m15210c(resolveResult);
                return;
            } else if (resolveResult.getStatus() != null && resolveResult.getStatus().getStatusCode() == 1001) {
                m15199n();
                m15212c(1);
                HuaweiApiClient.ConnectionCallbacks connectionCallbacks = this.f11023v;
                if (connectionCallbacks != null) {
                    connectionCallbacks.onConnectionSuspended(3);
                    return;
                }
                return;
            } else {
                m15199n();
                m15212c(1);
                if (this.f11024w != null) {
                    WeakReference<Activity> weakReference = this.f11009h;
                    if (weakReference != null && weakReference.get() != null) {
                        pendingIntent = HuaweiApiAvailability.getInstance().getResolveErrorPendingIntent(this.f11009h.get(), statusCode);
                    }
                    ConnectionResult connectionResult = new ConnectionResult(statusCode, pendingIntent);
                    this.f11024w.onConnectionFailed(connectionResult);
                    this.f11022u = connectionResult;
                    return;
                }
                return;
            }
        }
        HMSLog.m14112e("HuaweiApiClientImpl", "Invalid onConnectionResult");
    }
}
