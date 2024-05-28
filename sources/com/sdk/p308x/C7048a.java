package com.sdk.p308x;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.sdk.base.api.CallBack;
import com.sdk.base.api.CallBackTimeOut;
import com.sdk.base.framework.utils.log.LogUtils;
import com.sdk.base.framework.utils.log.MobileLogManager;
import com.sdk.base.module.manager.SDKManager;
import com.sdk.p285a.C6954a;
import com.sdk.p285a.C6958c;
import com.sdk.p286b.C6964a;
import com.sdk.p289e.InterfaceC6991a;
import com.sdk.p290f.C6998d;
import com.sdk.p294j.C7008a;
import com.sdk.p300p.C7028b;
import com.sdk.p302r.C7037a;
import com.sdk.p303s.C7038a;
import com.sdk.p304t.C7039a;
import com.sdk.p307w.C7047a;
import java.net.HttpURLConnection;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import org.json.JSONObject;

/* renamed from: com.sdk.x.a */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class C7048a<T> {

    /* renamed from: h */
    public static final String f18237h = "com.sdk.x.a";

    /* renamed from: j */
    public static ConnectivityManager.NetworkCallback f18239j;

    /* renamed from: k */
    public static long f18240k;

    /* renamed from: a */
    public CallBack<T> f18242a;

    /* renamed from: b */
    public Context f18243b;

    /* renamed from: c */
    public C7048a<T>.RunnableC7053d f18244c;

    /* renamed from: d */
    public C7048a<T>.RunnableC7053d f18245d;

    /* renamed from: e */
    public C6958c f18246e;

    /* renamed from: f */
    public ConnectivityManager f18247f;

    /* renamed from: g */
    public Handler f18248g;

    /* renamed from: i */
    public static Boolean f18238i = Boolean.valueOf(C6998d.f18135a);

    /* renamed from: l */
    public static String f18241l = "\n";

    /* renamed from: com.sdk.x.a$a */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class HandlerC7049a extends Handler {
        public HandlerC7049a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            C7048a.this.getClass();
            if (100 == message.what) {
                C7048a c7048a = C7048a.this;
                if (c7048a.f18246e != null) {
                    String str = C7048a.f18237h;
                    Log.d(str, "finish 超时，已取消请求结束时间: " + System.currentTimeMillis());
                    LogUtils.m8184w(str, "超时，已取消请求", C7048a.f18238i);
                    c7048a.f18246e.m8214a();
                    c7048a.m8112a(1, 101005, "超时");
                }
            }
        }
    }

    /* renamed from: com.sdk.x.a$b */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class C7050b implements CallBackTimeOut<T> {
        public C7050b() {
        }

        @Override // com.sdk.base.api.CallBackTimeOut
        public void timeout(int i, int i2, String str) {
            if (C7048a.this.f18246e != null) {
                LogUtils.m8184w(C7048a.f18237h, "超时，已取消请求", C7048a.f18238i);
                C7048a.this.f18246e.m8214a();
                C7048a.this.m8112a(1, 101005, "超时");
            }
            C7048a<T>.RunnableC7053d runnableC7053d = C7048a.this.f18244c;
            runnableC7053d.f18254a.removeCallbacks(runnableC7053d);
        }
    }

    /* renamed from: com.sdk.x.a$c */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class C7051c extends ConnectivityManager.NetworkCallback {

        /* renamed from: a */
        public final /* synthetic */ int f18251a;

        @NBSInstrumented
        /* renamed from: com.sdk.x.a$c$a */
        /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
        public class C7052a implements InterfaceC6991a<T> {
            public C7052a() {
            }

            @Override // com.sdk.p289e.InterfaceC6991a
            /* renamed from: a */
            public void mo8108a(int i, int i2, String str) {
                String str2 = C7048a.f18237h;
                LogUtils.d_yl(str2, "public void getAuthoriseCode onFailure", 0);
                C7048a.this.m8112a(i, i2, str);
                Log.d(str2, "onFailure: " + i);
            }

            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Type inference failed for: r14v5, types: [java.lang.String] */
            /* JADX WARN: Type inference failed for: r14v9 */
            @Override // com.sdk.p289e.InterfaceC6991a
            public void onSuccess(int i, String str, int i2, T t, String str2) {
                T t2;
                String str3 = C7048a.f18237h;
                LogUtils.d_yl(str3, "public void getAuthoriseCode onSuccess", 0);
                C7028b.m8140b(C7048a.this.f18243b);
                T t3 = t;
                if (i == 0) {
                    try {
                        String obj = t.toString();
                        Context context = C7048a.this.f18243b;
                        ?? r14 = (T) C7038a.m8128a(String.valueOf(t));
                        LogUtils.d_yl(str3, "public void getAuthoriseCode ToolsUtils.decryptResponse 后", 0);
                        if (r14 == 0) {
                            C7048a.this.m8112a(1, 302001, "SDK解密异常");
                            return;
                        }
                        if (SDKManager.useCache()) {
                            C7051c c7051c = C7051c.this;
                            Context context2 = C7048a.this.f18243b;
                            int i3 = c7051c.f18251a;
                            String m8194a = C6964a.m8194a(str, obj, str2, C7039a.f18205g);
                            if (C7037a.m8129b(m8194a).booleanValue()) {
                                String m8197a = C6964a.m8197a(i3, "CUCC");
                                if (C7037a.m8129b(m8197a).booleanValue()) {
                                    C7008a.m8154b(context2, m8197a, m8194a);
                                }
                            }
                        }
                        JSONObject jSONObject = new JSONObject((String) r14);
                        if (C7037a.m8130a(jSONObject.get("accessCode").toString()).booleanValue()) {
                            C7048a.this.m8112a(1, 302001, "SDK解密异常");
                        }
                        if (C7051c.this.f18251a == 1) {
                            jSONObject.remove("fakeMobile");
                            t2 = !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject);
                            LogUtils.d_yl(str3, "public void getAuthoriseCode toSucceed 前", 0);
                            C7048a.this.m8111a(i, str, i2, t2, str2);
                        }
                        t3 = r14;
                        if (C7037a.m8130a(jSONObject.get("fakeMobile").toString()).booleanValue()) {
                            C7048a.this.m8112a(1, 302001, "SDK解密异常");
                            t3 = r14;
                        }
                    } catch (Exception unused) {
                        C7048a.this.m8112a(1, 302001, "SDK解密异常");
                        return;
                    }
                }
                t2 = t3;
                LogUtils.d_yl(str3, "public void getAuthoriseCode toSucceed 前", 0);
                C7048a.this.m8111a(i, str, i2, t2, str2);
            }
        }

        public C7051c(int i) {
            this.f18251a = i;
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onAvailable(Network network) {
            ConnectivityManager.NetworkCallback networkCallback;
            String str = C7048a.f18237h;
            LogUtils.d_yl(str, "NetworkCallback onAvailable ", 0);
            super.onAvailable(network);
            if (network != null) {
                try {
                    LogUtils.d_yl(str, "获取网络 onAvailable getUrl", 0);
                    URL m8109a = C7048a.m8109a(C7048a.this);
                    C7048a c7048a = C7048a.this;
                    HttpURLConnection httpURLConnection = (HttpURLConnection) network.openConnection(m8109a);
                    c7048a.getClass();
                    C7048a<T>.RunnableC7053d runnableC7053d = C7048a.this.f18245d;
                    runnableC7053d.f18254a.removeCallbacks(runnableC7053d);
                    C7048a.f18240k = System.currentTimeMillis() - C7048a.f18240k;
                    LogUtils.d_yl(str, "获取网络 onAvailable time= " + C7048a.f18240k, 0);
                } catch (Exception e) {
                    String str2 = C7048a.f18237h;
                    Log.d(str2, "onAvailable: " + e);
                }
            }
            List<String> m8114a = C7048a.this.m8114a();
            C7047a c7047a = new C7047a();
            C7048a c7048a2 = C7048a.this;
            c7048a2.f18246e = c7047a.m8115a(c7048a2.f18243b, this.f18251a, m8114a, new C7052a());
            ConnectivityManager connectivityManager = C7048a.this.f18247f;
            if (connectivityManager == null || (networkCallback = C7048a.f18239j) == null) {
                return;
            }
            connectivityManager.unregisterNetworkCallback(networkCallback);
        }

        @Override // android.net.ConnectivityManager.NetworkCallback
        public void onLost(Network network) {
            ConnectivityManager.NetworkCallback networkCallback;
            super.onLost(network);
            ConnectivityManager connectivityManager = C7048a.this.f18247f;
            if (connectivityManager != null && (networkCallback = C7048a.f18239j) != null) {
                connectivityManager.unregisterNetworkCallback(networkCallback);
            }
            LogUtils.d_yl(C7048a.f18237h, "NetworkCallback onLost", 0);
        }
    }

    /* renamed from: com.sdk.x.a$d */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class RunnableC7053d implements Runnable {

        /* renamed from: a */
        public Handler f18254a = new Handler(Looper.getMainLooper());

        /* renamed from: b */
        public long f18255b;

        /* renamed from: c */
        public int f18256c;

        /* renamed from: d */
        public CallBackTimeOut<T> f18257d;

        public RunnableC7053d(C7048a c7048a, long j, int i, CallBackTimeOut<T> callBackTimeOut) {
            this.f18255b = j;
            this.f18256c = i;
            this.f18257d = callBackTimeOut;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f18257d.timeout(1, this.f18256c, "");
        }
    }

    public C7048a(Context context, int i, CallBack<T> callBack) {
        m8110a(context, i, callBack);
    }

    /* renamed from: a */
    public static URL m8109a(C7048a c7048a) {
        StringBuilder sb;
        String str;
        c7048a.getClass();
        String mo8168a = C6998d.EnumC7000b.f18139b.mo8168a();
        if (C6998d.f18137c) {
            mo8168a = C6998d.EnumC7000b.f18140c.mo8168a();
        }
        if (C7039a.f18204f) {
            sb = new StringBuilder();
            sb.append(mo8168a);
            str = "/dro/netm/v2.0/qc";
        } else {
            sb = new StringBuilder();
            sb.append(mo8168a);
            str = "/dro/netm/v1.0/qc";
        }
        sb.append(str);
        try {
            return new URL(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public List<String> m8114a() {
        Enumeration<InetAddress> inetAddresses;
        ArrayList arrayList = new ArrayList();
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            if (networkInterfaces != null) {
                while (networkInterfaces.hasMoreElements()) {
                    NetworkInterface nextElement = networkInterfaces.nextElement();
                    if (!nextElement.isVirtual() && nextElement.isUp() && (inetAddresses = nextElement.getInetAddresses()) != null) {
                        while (inetAddresses.hasMoreElements()) {
                            InetAddress nextElement2 = inetAddresses.nextElement();
                            if (!nextElement2.isLoopbackAddress() && !nextElement2.isLinkLocalAddress() && !nextElement2.isMulticastAddress() && !nextElement2.isAnyLocalAddress() && ((nextElement2 instanceof Inet4Address) || (nextElement2 instanceof Inet6Address))) {
                                arrayList.add(nextElement2.getHostAddress());
                            }
                        }
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    @SuppressLint({"NewApi", "WrongConstant"})
    /* renamed from: a */
    public void m8113a(int i) {
        ConnectivityManager.NetworkCallback networkCallback;
        String str = f18237h;
        LogUtils.d_yl(str, "public void getAuthoriseCode", 0);
        try {
            if (SDKManager.useCache()) {
                String m8196a = C6964a.m8196a(this.f18243b, i, "CUCC");
                if (C7037a.m8129b(m8196a).booleanValue()) {
                    m8111a(0, "成功", 100, C6964a.m8195a(m8196a), C6964a.m8193b(m8196a));
                    return;
                }
            }
            if (!C7028b.m8141a(this.f18243b)) {
                m8112a(1, 201001, "操作频繁,请稍后再试");
            } else if (Build.VERSION.SDK_INT >= 21) {
                f18239j = new C7051c(i);
                f18240k = System.currentTimeMillis();
                LogUtils.d_yl(str, "public void getAuthoriseCode 强开前", 0);
                ConnectivityManager connectivityManager = (ConnectivityManager) this.f18243b.getApplicationContext().getSystemService("connectivity");
                this.f18247f = connectivityManager;
                if (connectivityManager != null && (networkCallback = f18239j) != null) {
                    C6954a.m8219a(connectivityManager, networkCallback);
                }
                C7048a<T>.RunnableC7053d runnableC7053d = new RunnableC7053d(this, 2000L, 1, new C7054b(this));
                this.f18245d = runnableC7053d;
                runnableC7053d.f18254a.postDelayed(runnableC7053d, runnableC7053d.f18255b);
            }
        } catch (Exception unused) {
            if (SDKManager.useCache()) {
                String m8196a2 = C6964a.m8196a(this.f18243b, 0, "CUCC");
                if (C7037a.m8129b(m8196a2).booleanValue()) {
                    m8111a(0, "成功", 100, C6964a.m8195a(m8196a2), C6964a.m8193b(m8196a2));
                    return;
                }
            }
            if (C7028b.m8141a(this.f18243b)) {
                this.f18246e = new C7047a().m8115a(this.f18243b, 0, m8114a(), new C7055c(this, 0));
            } else {
                m8112a(1, 201001, "操作频繁,请稍后再试");
            }
        }
    }

    @SuppressLint({"NewApi", "WrongConstant"})
    /* renamed from: a */
    public final void m8110a(Context context, int i, CallBack<T> callBack) {
        this.f18242a = callBack;
        this.f18243b = context;
        if (i <= 0) {
            i = 30;
        }
        Handler handler = this.f18248g;
        if (handler == null) {
            this.f18248g = new HandlerC7049a(Looper.getMainLooper());
        } else {
            handler.removeMessages(100);
        }
        this.f18248g.sendEmptyMessageDelayed(100, i * 1000);
        C7048a<T>.RunnableC7053d runnableC7053d = new RunnableC7053d(this, i * 1000, 2, new C7050b());
        this.f18244c = runnableC7053d;
        runnableC7053d.f18254a.postDelayed(runnableC7053d, runnableC7053d.f18255b);
        MobileLogManager.init();
        System.currentTimeMillis();
    }

    /* renamed from: a */
    public final void m8112a(int i, int i2, String str) {
        String m8156a;
        try {
            if (C7037a.m8130a(MobileLogManager.getMobileLog().f18130c).booleanValue()) {
                m8156a = "seqAndroidEmpty";
            } else {
                m8156a = C7008a.m8156a(SDKManager.getContext(), "seq", "");
                String str2 = f18237h;
                Log.d(str2, "toFailed seq: " + m8156a);
            }
            String str3 = f18237h;
            LogUtils.d_yl(str3, "toFailed 函数 去掉超时回调 ", 0);
            C7048a<T>.RunnableC7053d runnableC7053d = this.f18244c;
            if (runnableC7053d != null) {
                runnableC7053d.f18254a.removeCallbacks(runnableC7053d);
            }
            C6958c c6958c = this.f18246e;
            if (c6958c != null) {
                c6958c.m8214a();
                this.f18246e = null;
            }
            CallBack<T> callBack = this.f18242a;
            if (callBack != null) {
                callBack.onFailed(i, i2, str, m8156a);
                this.f18242a = null;
            }
            SDKManager.releaseConnect(this.f18243b);
            LogUtils.d_yl(str3, "toFailed 函数 releaseConnect 完成", 0);
        } catch (Exception unused) {
            String str4 = MobileLogManager.getMobileLog().f18130c;
            if (C7037a.m8130a(str4).booleanValue()) {
                str4 = "seqAndroidEmpty";
            }
            C7048a<T>.RunnableC7053d runnableC7053d2 = this.f18244c;
            if (runnableC7053d2 != null) {
                runnableC7053d2.f18254a.removeCallbacks(runnableC7053d2);
            }
            CallBack<T> callBack2 = this.f18242a;
            if (callBack2 != null) {
                callBack2.onFailed(i, i2, str, str4);
                this.f18242a = null;
            }
            SDKManager.releaseConnect(this.f18243b);
        }
    }

    /* renamed from: a */
    public final void m8111a(int i, String str, int i2, T t, String str2) {
        try {
            if (C7037a.m8130a(str2).booleanValue()) {
                str2 = C7008a.m8156a(SDKManager.getContext(), "seq", "");
                if (C7037a.m8130a(str2).booleanValue()) {
                    str2 = "seqAndroidEmpty";
                }
            }
            String str3 = f18237h;
            LogUtils.d_yl(str3, "去掉超时回调", 0);
            C7048a<T>.RunnableC7053d runnableC7053d = this.f18244c;
            if (runnableC7053d != null) {
                runnableC7053d.f18254a.removeCallbacks(runnableC7053d);
            }
            C6958c c6958c = this.f18246e;
            if (c6958c != null) {
                c6958c.m8214a();
                this.f18246e = null;
            }
            CallBack<T> callBack = this.f18242a;
            if (callBack != null) {
                callBack.onSuccess(i, str, i2, t, str2);
                this.f18242a = null;
            }
            SDKManager.releaseConnect(this.f18243b);
            LogUtils.d_yl(str3, "toSucceed 函数 releaseConnect 完成", 0);
        } catch (Exception unused) {
            if (C7037a.m8130a(str2).booleanValue()) {
                str2 = "seqAndroidEmpty";
            }
            String str4 = str2;
            C7048a<T>.RunnableC7053d runnableC7053d2 = this.f18244c;
            if (runnableC7053d2 != null) {
                runnableC7053d2.f18254a.removeCallbacks(runnableC7053d2);
            }
            CallBack<T> callBack2 = this.f18242a;
            if (callBack2 != null) {
                callBack2.onSuccess(i, str, i2, t, str4);
                this.f18242a = null;
            }
            SDKManager.releaseConnect(this.f18243b);
            LogUtils.d_yl(f18237h, "toSucceed 异常函数 releaseConnect 完成", 0);
        }
    }
}
