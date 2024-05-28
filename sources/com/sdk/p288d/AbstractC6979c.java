package com.sdk.p288d;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.os.SystemClock;
import android.util.Log;
import com.sdk.base.framework.utils.log.LogUtils;
import com.sdk.base.framework.utils.log.MobileLogManager;
import com.sdk.base.module.manager.SDKManager;
import com.sdk.p285a.C6958c;
import com.sdk.p285a.C6960d;
import com.sdk.p285a.C6963f;
import com.sdk.p289e.InterfaceC6991a;
import com.sdk.p290f.C6998d;
import com.sdk.p291g.C7004a;
import com.sdk.p291g.C7005b;
import com.sdk.p294j.C7008a;
import com.sdk.p298n.C7014a;
import com.sdk.p302r.C7037a;
import com.sdk.p308x.C7048a;
import java.net.HttpURLConnection;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONObject;

/* renamed from: com.sdk.d.c */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public abstract class AbstractC6979c<Params, Progress, Result> {

    /* renamed from: g */
    public static final HandlerC6983d f18097g = new HandlerC6983d();

    /* renamed from: h */
    public static final Executor f18098h = new ExecutorC6985d(5);

    /* renamed from: a */
    public final AbstractCallableC6984e<Params, Result> f18099a;

    /* renamed from: b */
    public final FutureTask<Result> f18100b;

    /* renamed from: c */
    public final AtomicBoolean f18101c = new AtomicBoolean();

    /* renamed from: d */
    public final AtomicBoolean f18102d = new AtomicBoolean();

    /* renamed from: e */
    public volatile boolean f18103e = false;

    /* renamed from: f */
    public Boolean f18104f = Boolean.valueOf(C6998d.f18136b);

    /* renamed from: com.sdk.d.c$a */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class C6980a extends AbstractCallableC6984e<Params, Result> {
        public C6980a() {
            super(null);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.concurrent.Callable
        public Result call() {
            AbstractC6979c.this.f18102d.set(true);
            Process.setThreadPriority(10);
            AbstractC6979c abstractC6979c = AbstractC6979c.this;
            Params[] paramsArr = this.f18109a;
            C6958c c6958c = (C6958c) abstractC6979c;
            c6958c.getClass();
            LogUtils.d_yl("PriorityAsyncTask", "HttpHandler doInBackground", 0);
            if (c6958c.f17998m != C6958c.EnumC6959a.CANCELLED && paramsArr != null && paramsArr.length != 0) {
                if (paramsArr.length == 4) {
                    c6958c.f18002q = String.valueOf(paramsArr[1]);
                    c6958c.f18003r = true;
                    c6958c.f18004s = (Boolean) paramsArr[2];
                    c6958c.f18005t = (Boolean) paramsArr[3];
                }
                if (paramsArr.length == 2) {
                    c6958c.f18006u = (Boolean) paramsArr[1];
                }
                try {
                    c6958c.f18001p = SystemClock.uptimeMillis();
                    c6958c.m8178a(1);
                    C6960d c6960d = (C6960d) paramsArr[0];
                    c6958c.f17997l = c6960d.m8204b();
                    long currentTimeMillis = System.currentTimeMillis();
                    HttpURLConnection m8206a = c6960d.m8206a(c6958c.f17997l, C7014a.EnumC7021c.f18176a == c6960d.f18024b.f18047h);
                    long currentTimeMillis2 = System.currentTimeMillis();
                    StringBuilder sb = new StringBuilder();
                    sb.append("request.client强开耗时时间: ");
                    long j = currentTimeMillis2 - currentTimeMillis;
                    sb.append(j);
                    sb.append("\ndoInBackground forcedTime: ");
                    sb.append(C7048a.f18240k + j);
                    LogUtils.d_yl("PriorityAsyncTask", sb.toString(), 1);
                    C6958c.f17992C.put("forcedTime", Long.valueOf(j + C7048a.f18240k));
                    if (m8206a == null) {
                        c6958c.m8178a(4, new C6963f(0, c6958c.m8211b(), false));
                        LogUtils.d_yl("PriorityAsyncTask", "client = null", 0);
                    } else {
                        C6963f m8210b = c6958c.m8210b(c6960d, m8206a);
                        if (m8210b.f18049b.equals("网络访问已取消")) {
                            c6958c.f18009x.put("endTime", Long.valueOf(System.currentTimeMillis()));
                        }
                        if (m8210b.f18048a == 0) {
                            c6958c.m8178a(4, m8210b);
                        } else {
                            c6958c.m8178a(3, Integer.valueOf(m8210b.f18048a), m8210b.f18049b);
                            m8210b.f18049b.equals("网络访问异常");
                            m8210b.f18049b.equals("网络访问已取消");
                        }
                    }
                } catch (Exception e) {
                    MobileLogManager.status302002(e.toString());
                    LogUtils.d_yl("PriorityAsyncTask", "HttpHandler：doInBackground", 0);
                    c6958c.m8178a(3, 302002, "网络访问异常");
                }
            }
            return (Result) abstractC6979c.m8179a((AbstractC6979c) null);
        }
    }

    /* renamed from: com.sdk.d.c$b */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class C6981b extends FutureTask<Result> {
        public C6981b(Callable callable) {
            super(callable);
        }

        @Override // java.util.concurrent.FutureTask
        public void done() {
            try {
                AbstractC6979c abstractC6979c = AbstractC6979c.this;
                Result result = get();
                if (abstractC6979c.f18102d.get()) {
                    return;
                }
                abstractC6979c.m8179a((AbstractC6979c) result);
            } catch (Exception e) {
                AbstractC6979c abstractC6979c2 = AbstractC6979c.this;
                if (!abstractC6979c2.f18102d.get()) {
                    abstractC6979c2.m8179a((AbstractC6979c) null);
                }
                LogUtils.m8186e("PriorityAsyncTask", e.getMessage(), AbstractC6979c.this.f18104f);
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.sdk.d.c$c */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static class C6982c<Data> {

        /* renamed from: a */
        public final AbstractC6979c f18107a;

        /* renamed from: b */
        public final Data[] f18108b;

        public C6982c(AbstractC6979c abstractC6979c, Data... dataArr) {
            this.f18107a = abstractC6979c;
            this.f18108b = dataArr;
        }
    }

    /* renamed from: com.sdk.d.c$d */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public static class HandlerC6983d extends Handler {
        public HandlerC6983d() {
            super(Looper.getMainLooper());
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            C6982c c6982c = (C6982c) message.obj;
            int i = message.what;
            int i2 = 1;
            if (i == 1) {
                AbstractC6979c abstractC6979c = c6982c.f18107a;
                Object obj = c6982c.f18108b[0];
                abstractC6979c.f18101c.get();
            } else if (i != 2) {
            } else {
                AbstractC6979c abstractC6979c2 = c6982c.f18107a;
                Object[] objArr = c6982c.f18108b;
                C6958c c6958c = (C6958c) abstractC6979c2;
                c6958c.getClass();
                LogUtils.d_yl("PriorityAsyncTask", "HttpHandler onProgressUpdate 开始", 0);
                if (c6958c.f17998m == C6958c.EnumC6959a.CANCELLED || objArr == null || objArr.length == 0 || c6958c.f17995j == null) {
                    return;
                }
                switch (((Integer) objArr[0]).intValue()) {
                    case 1:
                        c6958c.f17998m = C6958c.EnumC6959a.STARTED;
                        c6958c.f17995j.getClass();
                        return;
                    case 2:
                        if (objArr.length != 3) {
                            return;
                        }
                        c6958c.f17998m = C6958c.EnumC6959a.LOADING;
                        Object obj2 = c6958c.f17995j;
                        Long.parseLong(String.valueOf(objArr[1]));
                        Long.parseLong(String.valueOf(objArr[2]));
                        obj2.getClass();
                        return;
                    case 3:
                        if (objArr.length != 3) {
                            return;
                        }
                        c6958c.f17998m = C6958c.EnumC6959a.FAILURE;
                        Object obj3 = c6958c.f17995j;
                        int intValue = ((Integer) objArr[1]).intValue();
                        C7005b c7005b = ((C7004a) obj3).f18142a;
                        String str = ((Object) ((String) objArr[2])) + "";
                        InterfaceC6991a<T> interfaceC6991a = c7005b.f18149e;
                        if (interfaceC6991a != 0) {
                            interfaceC6991a.mo8108a(intValue, 302002, str);
                            c7005b.f18149e = null;
                        }
                        String.valueOf(intValue);
                        Log.e(C7005b.f18143j, "BaseProtocol onFailure: ");
                        return;
                    case 4:
                        if (objArr.length != 2) {
                            return;
                        }
                        c6958c.f17998m = C6958c.EnumC6959a.SUCCESS;
                        Object obj4 = c6958c.f17995j;
                        C6963f c6963f = (C6963f) objArr[1];
                        String str2 = c6958c.f18007v.f18041b;
                        C7004a c7004a = (C7004a) obj4;
                        c7004a.getClass();
                        try {
                            JSONObject jSONObject = new JSONObject(c6963f == null ? "" : (String) c6963f.f18049b);
                            i2 = jSONObject.optInt("code");
                            String optString = jSONObject.optString("msg");
                            int optInt = jSONObject.optInt("status");
                            String optString2 = jSONObject.optString("obj");
                            String optString3 = jSONObject.optString("seq");
                            if (C7037a.m8130a(optString).booleanValue() && C7037a.m8130a(optString3).booleanValue() && C7037a.m8130a(optString2).booleanValue()) {
                                c7004a.f18142a.m8166a(1, "服务端数据格式出错", 302003, null, MobileLogManager.getMobileLog().f18130c);
                                LogUtils.m8186e(C7005b.f18143j, "返回数据为空", Boolean.valueOf(C7005b.f18144k));
                                return;
                            }
                            c7004a.f18142a.m8166a(i2, optString, optInt, optString2, optString3);
                            String.valueOf(i2);
                            String str3 = optInt + "";
                            if (optInt == 100) {
                                return;
                            }
                            str3.length();
                            return;
                        } catch (Throwable th) {
                            c7004a.f18142a.f18153i = C7008a.m8156a(SDKManager.getContext(), "seq", "");
                            MobileLogManager.status302003(th.toString());
                            c7004a.f18142a.m8166a(i2, "服务端数据格式出错", 302003, null, MobileLogManager.getMobileLog().f18130c);
                            LogUtils.m8186e(C7005b.f18143j, "返回数据解析异常：" + th.toString(), Boolean.valueOf(C7005b.f18144k));
                            if (C7037a.m8130a(c7004a.f18142a.f18153i).booleanValue()) {
                                c7004a.f18142a.f18153i = C7008a.m8156a(SDKManager.getContext(), "seq", "");
                                if (C7037a.m8130a(c7004a.f18142a.f18153i).booleanValue()) {
                                    c7004a.f18142a.f18153i = "seqAndroidEmpty";
                                    return;
                                }
                                return;
                            }
                            return;
                        }
                    default:
                        return;
                }
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.sdk.d.c$e */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static abstract class AbstractCallableC6984e<Params, Result> implements Callable<Result> {

        /* renamed from: a */
        public Params[] f18109a;

        public AbstractCallableC6984e() {
        }

        public /* synthetic */ AbstractCallableC6984e(C6980a c6980a) {
            this();
        }
    }

    public AbstractC6979c() {
        C6980a c6980a = new C6980a();
        this.f18099a = c6980a;
        this.f18100b = new C6981b(c6980a);
    }

    /* renamed from: a */
    public final Result m8179a(Result result) {
        f18097g.obtainMessage(1, new C6982c(this, result)).sendToTarget();
        return result;
    }

    /* renamed from: a */
    public final void m8178a(Progress... progressArr) {
        if (this.f18101c.get()) {
            return;
        }
        f18097g.obtainMessage(2, new C6982c(this, progressArr)).sendToTarget();
    }
}
