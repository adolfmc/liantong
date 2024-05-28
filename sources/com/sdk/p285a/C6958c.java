package com.sdk.p285a;

import android.net.Uri;
import android.os.SystemClock;
import android.util.Base64;
import android.util.Log;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.sdk.base.framework.bean.DataUtils;
import com.sdk.base.framework.utils.log.LogUtils;
import com.sdk.base.framework.utils.log.MobileLogManager;
import com.sdk.base.module.manager.SDKManager;
import com.sdk.p287c.C6974a;
import com.sdk.p287c.C6976c;
import com.sdk.p287c.InterfaceC6975b;
import com.sdk.p288d.AbstractC6979c;
import com.sdk.p289e.AbstractC6992b;
import com.sdk.p294j.C7008a;
import com.sdk.p296l.C7011a;
import com.sdk.p298n.C7014a;
import com.sdk.p302r.C7037a;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.ArrayList;
import java.util.TreeMap;
import org.json.JSONObject;

@NBSInstrumented
/* renamed from: com.sdk.a.c */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class C6958c<T> extends AbstractC6979c<Object, Object, Void> implements InterfaceC6975b {

    /* renamed from: B */
    public static final C6956b f17991B = new C6956b(102400, 60000);

    /* renamed from: C */
    public static TreeMap<String, Object> f17992C = new TreeMap<>();

    /* renamed from: A */
    public String f17993A;

    /* renamed from: j */
    public AbstractC6992b<T> f17995j;

    /* renamed from: k */
    public String f17996k;

    /* renamed from: l */
    public String f17997l;

    /* renamed from: n */
    public int f17999n;

    /* renamed from: p */
    public long f18001p;

    /* renamed from: s */
    public Boolean f18004s;

    /* renamed from: t */
    public Boolean f18005t;

    /* renamed from: u */
    public Boolean f18006u;

    /* renamed from: v */
    public C6962e<T> f18007v;

    /* renamed from: w */
    public long f18008w;

    /* renamed from: x */
    public TreeMap<String, Object> f18009x;

    /* renamed from: y */
    public long f18010y;

    /* renamed from: z */
    public int f18011z;

    /* renamed from: i */
    public long f17994i = C6956b.m8218a();

    /* renamed from: m */
    public EnumC6959a f17998m = EnumC6959a.WAITING;

    /* renamed from: o */
    public boolean f18000o = true;

    /* renamed from: q */
    public String f18002q = null;

    /* renamed from: r */
    public boolean f18003r = false;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.sdk.a.c$a */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public enum EnumC6959a {
        WAITING(0),
        STARTED(1),
        LOADING(2),
        FAILURE(3),
        CANCELLED(4),
        SUCCESS(5);

        EnumC6959a(int i) {
        }
    }

    public C6958c(C6960d<T> c6960d) {
        Boolean bool = Boolean.FALSE;
        this.f18004s = bool;
        this.f18005t = bool;
        this.f18006u = bool;
        this.f18011z = 1;
        C6962e<T> m8209a = c6960d.m8209a();
        this.f18007v = m8209a;
        if (m8209a != null) {
            this.f17996k = m8209a.m8200b();
            this.f17997l = this.f18007v.m8198d();
            this.f17999n = this.f18007v.m8199c();
            this.f17995j = this.f18007v.m8202a();
        }
    }

    /* renamed from: a */
    public void m8214a() {
        this.f17998m = EnumC6959a.CANCELLED;
        if (!this.f18101c.get()) {
            try {
                this.f18101c.set(true);
                this.f18100b.cancel(true);
            } catch (Throwable th) {
                LogUtils.m8186e("PriorityAsyncTask", th.getMessage(), this.f18104f);
            }
        }
        AbstractC6992b<T> abstractC6992b = this.f17995j;
        if (abstractC6992b != null) {
            abstractC6992b.getClass();
        }
    }

    /* renamed from: a */
    public boolean m8213a(long j, long j2, boolean z) {
        Object[] objArr;
        LogUtils.d_yl("PriorityAsyncTask", "HttpHandler updateProgress 开始", 0);
        if (this.f17995j != null && this.f17998m != EnumC6959a.CANCELLED) {
            if (z) {
                objArr = new Object[]{2, Long.valueOf(j), Long.valueOf(j2)};
            } else {
                long uptimeMillis = SystemClock.uptimeMillis();
                this.f17995j.getClass();
                if (uptimeMillis - this.f18001p >= 1000) {
                    this.f18001p = uptimeMillis;
                    objArr = new Object[]{2, Long.valueOf(j), Long.valueOf(j2)};
                }
            }
            m8178a(objArr);
        }
        return this.f17998m != EnumC6959a.CANCELLED;
    }

    /* renamed from: b */
    public final C6963f<T> m8210b(C6960d<T> c6960d, HttpURLConnection httpURLConnection) {
        C6963f<T> c6963f;
        C6956b c6956b;
        String m8217a;
        LogUtils.d_yl("PriorityAsyncTask", "private ResponseData<T> sendRequest()", 0);
        try {
            c6956b = f17991B;
        } catch (Throwable th) {
            MobileLogManager.status302002(th.toString());
            LogUtils.d_yl("PriorityAsyncTask", "访问异常HttpHandler：" + th.toString(), 0);
            int i = this.f17999n;
            if (i > 0) {
                this.f17999n = i - 1;
                c6963f = m8210b(c6960d, httpURLConnection);
            } else {
                c6963f = null;
            }
        }
        if (!c6956b.m8215b(this.f17996k) || (m8217a = c6956b.m8217a(this.f17997l)) == null) {
            if (this.f18004s.booleanValue() && this.f18003r) {
                File file = new File(this.f18002q);
                long length = (file.isFile() && file.exists()) ? file.length() : 0L;
                if (length > 0) {
                    httpURLConnection.setRequestProperty("RANGE", "bytes=" + length + "-");
                }
            }
            TreeMap<String, Object> treeMap = new TreeMap<>();
            this.f18009x = treeMap;
            treeMap.clear();
            long currentTimeMillis = System.currentTimeMillis();
            this.f18008w = currentTimeMillis;
            this.f18009x.put("startTime", Long.valueOf(currentTimeMillis));
            this.f18009x.put("url", httpURLConnection.getURL());
            LogUtils.d_yl("PriorityAsyncTask", "sendRequest() sendRequesturl" + httpURLConnection.getURL(), 3);
            HttpURLConnection m8205a = c6960d.m8205a(httpURLConnection);
            this.f18010y = System.currentTimeMillis();
            this.f18009x.put("forcedTime", Long.valueOf(c6960d.f18025c));
            this.f18009x.put("endTime", Long.valueOf(this.f18010y));
            TreeMap<String, Object> treeMap2 = f17992C;
            StringBuilder sb = new StringBuilder();
            sb.append("step");
            int i2 = this.f18011z;
            this.f18011z = i2 + 1;
            sb.append(i2);
            treeMap2.put(sb.toString(), this.f18009x);
            LogUtils.d_yl("PriorityAsyncTask", "HttpHandler sendRequest; accessTreeMap:" + f17992C.toString(), 3);
            c6963f = m8212a(c6960d, m8205a);
            if (c6963f == null) {
                C6963f<T> c6963f2 = new C6963f<>(1, "网络访问异常", false);
                LogUtils.d_yl("PriorityAsyncTask", "HttpHandler：responseInfo=null网络访问异常", 0);
                return c6963f2;
            }
            return c6963f;
        }
        return new C6963f<>(0, m8217a, true);
    }

    /* renamed from: b */
    public final String m8211b() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("code", 1);
            jSONObject.put("status", 102001);
            jSONObject.put("msg", "选择流量通道失败");
            jSONObject.put("seq", 102001);
            return !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject);
        } catch (Exception e) {
            Log.e("PriorityAsyncTask", "returnResult: ", e);
            return null;
        }
    }

    /* renamed from: a */
    public final C6963f<T> m8212a(C6960d<T> c6960d, HttpURLConnection httpURLConnection) {
        int i;
        URL url;
        String str;
        Uri parse;
        byte[] bArr;
        LogUtils.d_yl("PriorityAsyncTask", "HttpHandler handleResponse 开始", 0);
        if (this.f18101c.get()) {
            return new C6963f<>(1, "网络访问已取消", false);
        }
        LogUtils.d_yl("PriorityAsyncTask", "HttpHandler handleResponse 跳过 isCancelled", 0);
        try {
            try {
                long currentTimeMillis = System.currentTimeMillis();
                MobileLogManager.status101005(httpURLConnection.getURL().toString(), currentTimeMillis - this.f18008w);
                LogUtils.d_yl("PriorityAsyncTask", "HttpHandler handleResponse 跳过 MobileLogManager.status101005", 0);
                LogUtils.d_yl("PriorityAsyncTask", "handleResponse 正式发送数据 开始", 0);
                i = httpURLConnection.getResponseCode();
                try {
                    LogUtils.d_yl("PriorityAsyncTask", "handleResponse 正式发送数据 结束", 0);
                    LogUtils.d_yl("PriorityAsyncTask", "HttpHandler handleResponse 跳过 statusCode=" + i, 0);
                    LogUtils.d_yl("OAUTH_SDK", "HttpHandler handleResponse 打印\nnet请求host：" + url.getHost() + "\n net请求path：" + url.getPath() + "\n net请求Query：" + url.getQuery() + "\n  net请求码：" + i, 3);
                    if (i == 200 && httpURLConnection.toString().contains("gmbs")) {
                        this.f18009x.put("endTime", Long.valueOf(currentTimeMillis));
                    }
                    long currentTimeMillis2 = System.currentTimeMillis();
                    if (this.f18104f.booleanValue()) {
                        new TreeMap().put(httpURLConnection.getURL().toString(), Long.valueOf(currentTimeMillis2 - this.f18008w));
                        LogUtils.d_yl("PriorityAsyncTask", "响应返回：code=" + i + ";耗时=" + (System.currentTimeMillis() - this.f18008w), 1);
                    }
                    this.f18009x.put("endTime", Long.valueOf(currentTimeMillis2));
                    LogUtils.d_yl("PriorityAsyncTask", "step访问结束时间" + currentTimeMillis2, 0);
                    if (c6960d.f18024b.f18041b.contains("nisportal")) {
                        new DataUtils.Access_process().setIfProtal(1);
                        f17992C.put("ifProtal", 1);
                    }
                    if (httpURLConnection.getURL().getPath() == null) {
                        System.currentTimeMillis();
                        new ArrayList();
                        new ArrayList();
                        new ArrayList();
                        String str2 = "qcandroidabc000" + System.currentTimeMillis();
                    }
                } catch (SocketTimeoutException unused) {
                    Log.e("PriorityAsyncTask", "chaoshi ");
                    C7008a.m8154b(SDKManager.getContext(), "seq", "qcTimeout");
                    MobileLogManager.status302002("服务异常 ResponseCode = " + i);
                    LogUtils.m8186e("PriorityAsyncTask", "服务异常 ResponseCode = " + i, this.f18104f);
                    return new C6963f<>(0, "服务端数据格式出错", false);
                }
            } catch (Exception e) {
                MobileLogManager.status302002(e.toString());
                LogUtils.m8186e("PriorityAsyncTask", e.toString(), this.f18104f);
                Log.e("PriorityAsyncTask", "HttpHandler handleResponse");
                return new C6963f<>(1, "网络访问异常", false);
            }
        } catch (SocketTimeoutException unused2) {
            i = -1;
        }
        if (i < 300) {
            LogUtils.d_yl("PriorityAsyncTask", "(statusCode < 300)", 0);
            this.f18000o = false;
            if (this.f18003r) {
                this.f18004s = Boolean.valueOf(this.f18004s.booleanValue() && C7011a.m8148b(httpURLConnection));
                new C6974a().m8183a(httpURLConnection, this, this.f18002q, this.f18004s.booleanValue(), this.f18005t.booleanValue() ? C7011a.m8149a(httpURLConnection) : null);
            }
            if (this.f18006u.booleanValue()) {
                InputStream inputStream = httpURLConnection.getInputStream();
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byte[] bArr2 = new byte[4096];
                while (true) {
                    int read = inputStream.read(bArr2);
                    if (read == -1) {
                        break;
                    }
                    byteArrayOutputStream.write(bArr2, 0, read);
                }
                byteArrayOutputStream.flush();
                bArr = byteArrayOutputStream.toByteArray();
            } else {
                String m8182a = new C6976c().m8182a(httpURLConnection, this, "UTF-8");
                C6956b c6956b = f17991B;
                bArr = m8182a;
                if (c6956b.m8215b(this.f17996k)) {
                    c6956b.m8216a(this.f17997l, m8182a, this.f17994i);
                    bArr = m8182a;
                }
            }
            httpURLConnection.disconnect();
            return new C6963f<>(0, bArr, false);
        }
        if (i == 301 || i == 302) {
            LogUtils.d_yl("PriorityAsyncTask", "(statusCode == 301 || statusCode == 302)", 0);
            MobileLogManager.status101005(c6960d.f18024b.f18041b, System.currentTimeMillis() - this.f18008w);
            String str3 = c6960d.f18024b.f18041b;
            String m8156a = C7008a.m8156a(SDKManager.getContext(), "seq", (String) null);
            Log.d("PriorityAsyncTask", "handleResponse seq: " + m8156a);
            if (m8156a == null) {
                Uri parse2 = Uri.parse(str3);
                if (str3.contains("ret_url")) {
                    this.f17993A = Uri.parse(new String(Base64.decode(parse2.getQueryParameter("ret_url"), 0))).getQueryParameter("seq");
                    C7008a.m8154b(SDKManager.getContext(), "seq", this.f17993A);
                    Log.d("YYT", "格式匹配seq--->" + parse.getQueryParameter("seq"));
                }
            }
            String headerField = httpURLConnection.getHeaderField("Location");
            String headerField2 = httpURLConnection.getHeaderField("Set-Cookie");
            String path = httpURLConnection.getURL().getPath();
            if (headerField == null) {
                System.currentTimeMillis();
                new ArrayList();
                new ArrayList();
                new ArrayList();
                String str4 = "qcandroidabc000" + System.currentTimeMillis();
            }
            if (C7037a.m8129b(headerField).booleanValue()) {
                C6962e<T> c6962e = c6960d.f18024b;
                c6962e.f18041b = headerField;
                boolean z = C7014a.EnumC7021c.f18176a == c6962e.f18047h;
                C7011a.m8150a(headerField);
                HttpURLConnection m8206a = c6960d.m8206a(headerField, z);
                if (C7037a.m8129b(headerField2).booleanValue()) {
                    if ("/ctcnet/gctcmc.do".equals(path)) {
                        C7008a.m8154b(SDKManager.getContext(), "ctc", headerField2);
                        LogUtils.m8185i("PriorityAsyncTask", "mdb Cookie cache", this.f18104f);
                    }
                    str = "Cookie";
                } else {
                    str = "Cookie";
                    headerField2 = C7008a.m8155b(SDKManager.getContext(), "ctc");
                }
                m8206a.setRequestProperty(str, headerField2);
                return m8206a == null ? new C6963f<>(0, m8211b(), false) : m8210b(c6960d, m8206a);
            }
            httpURLConnection.disconnect();
        }
        MobileLogManager.status302002("服务异常 ResponseCode = " + i);
        LogUtils.m8186e("PriorityAsyncTask", "服务异常 ResponseCode = " + i, this.f18104f);
        return new C6963f<>(0, "服务端数据格式出错", false);
    }
}
