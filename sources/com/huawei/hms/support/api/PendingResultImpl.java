package com.huawei.hms.support.api;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Pair;
import com.huawei.hms.adapter.BaseAdapter;
import com.huawei.hms.common.internal.TransactionIdCreater;
import com.huawei.hms.core.aidl.AbstractMessageEntity;
import com.huawei.hms.core.aidl.IMessageEntity;
import com.huawei.hms.support.api.client.ApiClient;
import com.huawei.hms.support.api.client.InnerPendingResult;
import com.huawei.hms.support.api.client.Result;
import com.huawei.hms.support.api.client.ResultCallback;
import com.huawei.hms.support.api.client.Status;
import com.huawei.hms.support.api.client.SubAppInfo;
import com.huawei.hms.support.api.transport.DatagramTransport;
import com.huawei.hms.support.gentyref.GenericTypeReflector;
import com.huawei.hms.support.hianalytics.HiAnalyticsUtil;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.utils.Util;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public abstract class PendingResultImpl<R extends Result, T extends IMessageEntity> extends InnerPendingResult<R> {

    /* renamed from: a */
    private CountDownLatch f11705a;

    /* renamed from: c */
    private WeakReference<ApiClient> f11707c;
    protected DatagramTransport transport = null;

    /* renamed from: b */
    private R f11706b = null;

    /* renamed from: d */
    private String f11708d = null;

    /* renamed from: e */
    private String f11709e = null;

    /* renamed from: f */
    private boolean f11710f = true;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.huawei.hms.support.api.PendingResultImpl$a */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class C5059a implements DatagramTransport.InterfaceC5073a {
        C5059a() {
        }

        @Override // com.huawei.hms.support.api.transport.DatagramTransport.InterfaceC5073a
        /* renamed from: a */
        public void mo14135a(int i, IMessageEntity iMessageEntity) {
            PendingResultImpl.this.m14163a(i, iMessageEntity);
            PendingResultImpl.this.f11705a.countDown();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.huawei.hms.support.api.PendingResultImpl$b */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class C5060b implements DatagramTransport.InterfaceC5073a {

        /* renamed from: a */
        final /* synthetic */ AtomicBoolean f11712a;

        C5060b(AtomicBoolean atomicBoolean) {
            this.f11712a = atomicBoolean;
        }

        @Override // com.huawei.hms.support.api.transport.DatagramTransport.InterfaceC5073a
        /* renamed from: a */
        public void mo14135a(int i, IMessageEntity iMessageEntity) {
            if (!this.f11712a.get()) {
                PendingResultImpl.this.m14163a(i, iMessageEntity);
            }
            PendingResultImpl.this.f11705a.countDown();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.huawei.hms.support.api.PendingResultImpl$c */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class C5061c implements DatagramTransport.InterfaceC5073a {

        /* renamed from: a */
        final /* synthetic */ HandlerC5062d f11714a;

        /* renamed from: b */
        final /* synthetic */ ResultCallback f11715b;

        C5061c(HandlerC5062d handlerC5062d, ResultCallback resultCallback) {
            this.f11714a = handlerC5062d;
            this.f11715b = resultCallback;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.huawei.hms.support.api.transport.DatagramTransport.InterfaceC5073a
        /* renamed from: a */
        public void mo14135a(int i, IMessageEntity iMessageEntity) {
            PendingResultImpl.this.m14163a(i, iMessageEntity);
            this.f11714a.m14158a(this.f11715b, PendingResultImpl.this.f11706b);
        }
    }

    /* renamed from: com.huawei.hms.support.api.PendingResultImpl$d */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static class HandlerC5062d<R extends Result> extends Handler {
        public HandlerC5062d(Looper looper) {
            super(looper);
        }

        /* renamed from: a */
        public void m14158a(ResultCallback<? super R> resultCallback, R r) {
            sendMessage(obtainMessage(1, new Pair(resultCallback, r)));
        }

        /* renamed from: b */
        protected void m14157b(ResultCallback<? super R> resultCallback, R r) {
            resultCallback.onResult(r);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what != 1) {
                return;
            }
            Pair pair = (Pair) message.obj;
            m14157b((ResultCallback) pair.first, (Result) pair.second);
        }
    }

    public PendingResultImpl(ApiClient apiClient, String str, IMessageEntity iMessageEntity) {
        m14160a(apiClient, str, iMessageEntity, getResponseType(), 0);
    }

    @Override // com.huawei.hms.support.api.client.PendingResult
    public final R await() {
        HMSLog.m14110i("PendingResultImpl", "await");
        if (Looper.myLooper() != Looper.getMainLooper()) {
            return awaitOnAnyThread();
        }
        HMSLog.m14112e("PendingResultImpl", "await in main thread");
        throw new IllegalStateException("await must not be called on the UI thread");
    }

    @Override // com.huawei.hms.support.api.client.InnerPendingResult
    public final R awaitOnAnyThread() {
        HMSLog.m14110i("PendingResultImpl", "awaitOnAnyThread");
        WeakReference<ApiClient> weakReference = this.f11707c;
        if (weakReference == null) {
            HMSLog.m14112e("PendingResultImpl", "api is null");
            m14163a(907135003, (IMessageEntity) null);
            return this.f11706b;
        }
        ApiClient apiClient = weakReference.get();
        if (!checkApiClient(apiClient)) {
            HMSLog.m14112e("PendingResultImpl", "client invalid");
            m14163a(907135003, (IMessageEntity) null);
            return this.f11706b;
        }
        if (this.f11710f) {
            m14164a(0, 1);
        }
        this.transport.send(apiClient, new C5059a());
        try {
            this.f11705a.await();
        } catch (InterruptedException unused) {
            HMSLog.m14112e("PendingResultImpl", "await in anythread InterruptedException");
            m14163a(907135001, (IMessageEntity) null);
        }
        return this.f11706b;
    }

    @Override // com.huawei.hms.support.api.client.PendingResult
    @Deprecated
    public void cancel() {
    }

    protected boolean checkApiClient(ApiClient apiClient) {
        return true;
    }

    protected Class<T> getResponseType() {
        Type type;
        Type genericSuperclass = getClass().getGenericSuperclass();
        if (genericSuperclass == null || (type = ((ParameterizedType) genericSuperclass).getActualTypeArguments()[1]) == null) {
            return null;
        }
        return (Class) type;
    }

    @Override // com.huawei.hms.support.api.client.PendingResult
    @Deprecated
    public boolean isCanceled() {
        return false;
    }

    public abstract R onComplete(T t);

    protected R onError(int i) {
        Type genericSuperclass = getClass().getGenericSuperclass();
        Type type = genericSuperclass != null ? ((ParameterizedType) genericSuperclass).getActualTypeArguments()[0] : null;
        Class<?> type2 = type != null ? GenericTypeReflector.getType(type) : null;
        if (type2 != null) {
            try {
                R r = (R) type2.newInstance();
                this.f11706b = r;
                r.setStatus(new Status(i));
            } catch (Exception e) {
                HMSLog.m14112e("PendingResultImpl", "on Error:" + e.getMessage());
                return null;
            }
        }
        return this.f11706b;
    }

    @Override // com.huawei.hms.support.api.client.PendingResult
    public void setResultCallback(ResultCallback<R> resultCallback) {
        this.f11710f = !(resultCallback instanceof BaseAdapter.BaseRequestResultCallback);
        setResultCallback(Looper.getMainLooper(), resultCallback);
    }

    /* renamed from: a */
    private void m14160a(ApiClient apiClient, String str, IMessageEntity iMessageEntity, Class<T> cls, int i) {
        HMSLog.m14110i("PendingResultImpl", "init uri:" + str);
        this.f11708d = str;
        if (apiClient == null) {
            HMSLog.m14112e("PendingResultImpl", "client is null");
            return;
        }
        this.f11707c = new WeakReference<>(apiClient);
        this.f11705a = new CountDownLatch(1);
        try {
            this.transport = (DatagramTransport) Class.forName(apiClient.getTransportName()).getConstructor(String.class, IMessageEntity.class, Class.class, Integer.TYPE).newInstance(str, iMessageEntity, cls, Integer.valueOf(i));
        } catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            HMSLog.m14112e("PendingResultImpl", "gen transport error:" + e.getMessage());
            throw new IllegalStateException("Instancing transport exception, " + e.getMessage(), e);
        }
    }

    @Override // com.huawei.hms.support.api.client.PendingResult
    public final void setResultCallback(Looper looper, ResultCallback<R> resultCallback) {
        HMSLog.m14110i("PendingResultImpl", "setResultCallback");
        if (looper == null) {
            looper = Looper.myLooper();
        }
        HandlerC5062d handlerC5062d = new HandlerC5062d(looper);
        WeakReference<ApiClient> weakReference = this.f11707c;
        if (weakReference == null) {
            HMSLog.m14112e("PendingResultImpl", "api is null");
            m14163a(907135003, (IMessageEntity) null);
            return;
        }
        ApiClient apiClient = weakReference.get();
        if (!checkApiClient(apiClient)) {
            HMSLog.m14112e("PendingResultImpl", "client is invalid");
            m14163a(907135003, (IMessageEntity) null);
            handlerC5062d.m14158a(resultCallback, this.f11706b);
            return;
        }
        if (this.f11710f) {
            m14164a(0, 1);
        }
        this.transport.post(apiClient, new C5061c(handlerC5062d, resultCallback));
    }

    @Override // com.huawei.hms.support.api.client.PendingResult
    public R await(long j, TimeUnit timeUnit) {
        HMSLog.m14110i("PendingResultImpl", "await timeout:" + j + " unit:" + timeUnit.toString());
        if (Looper.myLooper() != Looper.getMainLooper()) {
            return awaitOnAnyThread(j, timeUnit);
        }
        HMSLog.m14110i("PendingResultImpl", "await in main thread");
        throw new IllegalStateException("await must not be called on the UI thread");
    }

    @Override // com.huawei.hms.support.api.client.PendingResult
    @Deprecated
    public void setResultCallback(ResultCallback<R> resultCallback, long j, TimeUnit timeUnit) {
        setResultCallback(resultCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: a */
    public void m14163a(int i, IMessageEntity iMessageEntity) {
        Status status;
        HMSLog.m14110i("PendingResultImpl", "setResult:" + i);
        Status commonStatus = iMessageEntity instanceof AbstractMessageEntity ? ((AbstractMessageEntity) iMessageEntity).getCommonStatus() : null;
        if (i == 0) {
            this.f11706b = onComplete(iMessageEntity);
        } else {
            this.f11706b = onError(i);
        }
        if (this.f11710f) {
            m14164a(i, 2);
        }
        R r = this.f11706b;
        if (r == null || (status = r.getStatus()) == null || commonStatus == null) {
            return;
        }
        int statusCode = status.getStatusCode();
        String statusMessage = status.getStatusMessage();
        int statusCode2 = commonStatus.getStatusCode();
        String statusMessage2 = commonStatus.getStatusMessage();
        if (statusCode != statusCode2) {
            HMSLog.m14112e("PendingResultImpl", "rstStatus code (" + statusCode + ") is not equal commonStatus code (" + statusCode2 + ")");
            HMSLog.m14112e("PendingResultImpl", "rstStatus msg (" + statusMessage + ") is not equal commonStatus msg (" + statusMessage2 + ")");
        } else if (!TextUtils.isEmpty(statusMessage) || TextUtils.isEmpty(statusMessage2)) {
        } else {
            HMSLog.m14110i("PendingResultImpl", "rstStatus msg (" + statusMessage + ") is not equal commonStatus msg (" + statusMessage2 + ")");
            this.f11706b.setStatus(new Status(statusCode, statusMessage2, status.getResolution()));
        }
    }

    public PendingResultImpl(ApiClient apiClient, String str, IMessageEntity iMessageEntity, Class<T> cls) {
        m14160a(apiClient, str, iMessageEntity, cls, 0);
    }

    @Override // com.huawei.hms.support.api.client.InnerPendingResult
    public final R awaitOnAnyThread(long j, TimeUnit timeUnit) {
        HMSLog.m14110i("PendingResultImpl", "awaitOnAnyThread timeout:" + j + " unit:" + timeUnit.toString());
        WeakReference<ApiClient> weakReference = this.f11707c;
        if (weakReference == null) {
            HMSLog.m14112e("PendingResultImpl", "api is null");
            m14163a(907135003, (IMessageEntity) null);
            return this.f11706b;
        }
        ApiClient apiClient = weakReference.get();
        if (!checkApiClient(apiClient)) {
            HMSLog.m14112e("PendingResultImpl", "client invalid");
            m14163a(907135003, (IMessageEntity) null);
            return this.f11706b;
        }
        AtomicBoolean atomicBoolean = new AtomicBoolean();
        if (this.f11710f) {
            m14164a(0, 1);
        }
        this.transport.post(apiClient, new C5060b(atomicBoolean));
        try {
            if (!this.f11705a.await(j, timeUnit)) {
                atomicBoolean.set(true);
                m14163a(907135004, (IMessageEntity) null);
            }
        } catch (InterruptedException unused) {
            HMSLog.m14112e("PendingResultImpl", "awaitOnAnyThread InterruptedException");
            m14163a(907135001, (IMessageEntity) null);
        }
        return this.f11706b;
    }

    /* renamed from: a */
    private void m14164a(int i, int i2) {
        SubAppInfo subAppInfo;
        HMSLog.m14110i("PendingResultImpl", "biReportEvent ====== ");
        ApiClient apiClient = this.f11707c.get();
        if (apiClient != null && this.f11708d != null && !HiAnalyticsUtil.getInstance().hasError(apiClient.getContext())) {
            HashMap hashMap = new HashMap();
            hashMap.put("package", apiClient.getPackageName());
            hashMap.put("baseVersion", "6.9.0.300");
            if (i2 == 1) {
                hashMap.put("direction", "req");
            } else {
                hashMap.put("direction", "rsp");
                hashMap.put("result", String.valueOf(i));
                R r = this.f11706b;
                if (r != null && r.getStatus() != null) {
                    hashMap.put("statusCode", String.valueOf(this.f11706b.getStatus().getStatusCode()));
                }
            }
            hashMap.put("version", "0");
            String appId = Util.getAppId(apiClient.getContext());
            if (TextUtils.isEmpty(appId) && (subAppInfo = apiClient.getSubAppInfo()) != null) {
                appId = subAppInfo.getSubAppID();
            }
            hashMap.put("appid", appId);
            if (TextUtils.isEmpty(this.f11709e)) {
                String id = TransactionIdCreater.getId(appId, this.f11708d);
                this.f11709e = id;
                hashMap.put("transId", id);
            } else {
                hashMap.put("transId", this.f11709e);
                this.f11709e = null;
            }
            String[] split = this.f11708d.split("\\.");
            if (split.length >= 2) {
                hashMap.put("service", split[0]);
                hashMap.put("apiName", split[1]);
            }
            hashMap.put("callTime", String.valueOf(System.currentTimeMillis()));
            hashMap.put("phoneType", Util.getSystemProperties("ro.logsystem.usertype", ""));
            HiAnalyticsUtil.getInstance().onEvent(apiClient.getContext(), "HMS_SDK_BASE_CALL_AIDL", hashMap);
            return;
        }
        HMSLog.m14112e("PendingResultImpl", "<biReportEvent> has some error.");
    }

    public PendingResultImpl(ApiClient apiClient, String str, IMessageEntity iMessageEntity, int i) {
        m14160a(apiClient, str, iMessageEntity, getResponseType(), i);
    }
}
