package com.huawei.hms.common.internal;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.huawei.hms.common.HuaweiApi;
import com.huawei.hms.common.internal.BaseHmsClient;
import com.huawei.hms.core.aidl.IAIDLInvoke;
import com.huawei.hms.support.log.HMSLog;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class RequestManager implements Handler.Callback {
    public static final int NOTIFY_CONNECT_FAILED = 10012;
    public static final int NOTIFY_CONNECT_SUCCESS = 10011;
    public static final int NOTIFY_CONNECT_SUSPENDED = 10013;

    /* renamed from: b */
    private static volatile RequestManager f11157b;

    /* renamed from: c */
    private static Handler f11158c;

    /* renamed from: a */
    private static final Object f11156a = new Object();

    /* renamed from: d */
    private static Queue<HuaweiApi.RequestHandler> f11159d = new ConcurrentLinkedQueue();

    /* renamed from: e */
    private static Map<String, HuaweiApi.RequestHandler> f11160e = new LinkedHashMap();

    private RequestManager(Looper looper) {
        f11158c = new Handler(looper, this);
    }

    public static void addRequestToQueue(HuaweiApi.RequestHandler requestHandler) {
        f11159d.add(requestHandler);
    }

    public static void addToConnectedReqMap(final String str, final HuaweiApi.RequestHandler requestHandler) {
        if (f11158c == null) {
            return;
        }
        HMSLog.m14110i("RequestManager", "addToConnectedReqMap");
        f11158c.post(new Runnable() { // from class: com.huawei.hms.common.internal.RequestManager.1
            @Override // java.lang.Runnable
            public void run() {
                RequestManager.f11160e.put(str, requestHandler);
            }
        });
    }

    /* renamed from: b */
    private void m15103b() {
        while (!f11159d.isEmpty()) {
            HuaweiApi.RequestHandler poll = f11159d.poll();
            if (poll != null) {
                AnyClient client = poll.getClient();
                if (client instanceof BaseHmsClient) {
                    BaseHmsClient baseHmsClient = (BaseHmsClient) client;
                    baseHmsClient.setService(IAIDLInvoke.Stub.asInterface(baseHmsClient.getAdapter().getServiceBinder()));
                    poll.onConnected();
                }
            }
        }
    }

    /* renamed from: c */
    private void m15102c() {
        HMSLog.m14110i("RequestManager", "NOTIFY_CONNECT_SUSPENDED.");
        while (!f11159d.isEmpty()) {
            f11159d.poll().onConnectionSuspended(1);
        }
        m15101d();
    }

    /* renamed from: d */
    private void m15101d() {
        HMSLog.m14110i("RequestManager", "notifyRunningRequestConnectSuspend, connectedReqMap.size(): " + f11160e.size());
        Iterator<Map.Entry<String, HuaweiApi.RequestHandler>> it = f11160e.entrySet().iterator();
        while (it.hasNext()) {
            try {
                it.next().getValue().onConnectionSuspended(1);
            } catch (RuntimeException e) {
                HMSLog.m14112e("RequestManager", "NOTIFY_CONNECT_SUSPENDED Exception: " + e.getMessage());
            }
            it.remove();
        }
    }

    public static Handler getHandler() {
        return f11158c;
    }

    public static RequestManager getInstance() {
        synchronized (f11156a) {
            if (f11157b == null) {
                HandlerThread handlerThread = new HandlerThread("RequestManager");
                handlerThread.start();
                f11157b = new RequestManager(handlerThread.getLooper());
            }
        }
        return f11157b;
    }

    public static void removeReqByTransId(final String str) {
        if (f11158c == null) {
            return;
        }
        HMSLog.m14110i("RequestManager", "removeReqByTransId");
        f11158c.post(new Runnable() { // from class: com.huawei.hms.common.internal.RequestManager.2
            @Override // java.lang.Runnable
            public void run() {
                RequestManager.f11160e.remove(str);
            }
        });
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        if (message == null) {
            return false;
        }
        HMSLog.m14110i("RequestManager", "RequestManager handleMessage.");
        switch (message.what) {
            case NOTIFY_CONNECT_SUCCESS /* 10011 */:
                m15103b();
                return true;
            case NOTIFY_CONNECT_FAILED /* 10012 */:
                m15104a(message);
                return true;
            case NOTIFY_CONNECT_SUSPENDED /* 10013 */:
                m15102c();
                return true;
            default:
                HMSLog.m14110i("RequestManager", "handleMessage unknown msg:" + message.what);
                return false;
        }
    }

    /* renamed from: a */
    private void m15104a(Message message) {
        HMSLog.m14110i("RequestManager", "NOTIFY_CONNECT_FAILED.");
        try {
            BaseHmsClient.ConnectionResultWrapper connectionResultWrapper = (BaseHmsClient.ConnectionResultWrapper) message.obj;
            HuaweiApi.RequestHandler request = connectionResultWrapper.getRequest();
            f11159d.remove(request);
            request.onConnectionFailed(connectionResultWrapper.getConnectionResult());
        } catch (RuntimeException e) {
            HMSLog.m14112e("RequestManager", "<handleConnectFailed> handle Failed" + e.getMessage());
        }
    }
}
