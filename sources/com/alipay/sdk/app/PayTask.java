package com.alipay.sdk.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import com.alipay.sdk.app.PayResultActivity;
import com.alipay.sdk.app.statistic.C2000a;
import com.alipay.sdk.cons.C2003a;
import com.alipay.sdk.data.C2006a;
import com.alipay.sdk.data.C2009c;
import com.alipay.sdk.packet.impl.C2029e;
import com.alipay.sdk.protocol.C2031b;
import com.alipay.sdk.protocol.EnumC2030a;
import com.alipay.sdk.sys.C2032a;
import com.alipay.sdk.sys.C2033b;
import com.alipay.sdk.tid.C2035b;
import com.alipay.sdk.util.C2040c;
import com.alipay.sdk.util.C2042e;
import com.alipay.sdk.util.C2047i;
import com.alipay.sdk.util.C2050l;
import com.alipay.sdk.util.C2052n;
import com.alipay.sdk.util.H5PayResultModel;
import com.alipay.sdk.widget.C2058a;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* loaded from: E:\10201592_dexfile_execute.dex */
public class PayTask {

    /* renamed from: a */
    static final Object f3550a = C2042e.class;

    /* renamed from: h */
    private static long f3551h = 0;

    /* renamed from: i */
    private static final long f3552i = 3000;

    /* renamed from: j */
    private static long f3553j = -1;

    /* renamed from: b */
    private Activity f3554b;

    /* renamed from: c */
    private C2058a f3555c;

    /* renamed from: d */
    private String f3556d = "wappaygw.alipay.com/service/rest.htm";

    /* renamed from: e */
    private String f3557e = "mclient.alipay.com/service/rest.htm";

    /* renamed from: f */
    private String f3558f = "mclient.alipay.com/home/exterfaceAssign.htm";

    /* renamed from: g */
    private Map<String, C1987a> f3559g = new HashMap();

    public String getVersion() {
        return "15.6.8";
    }

    public PayTask(Activity activity) {
        this.f3554b = activity;
        C2033b.m20772a().m20771a(this.f3554b, C2009c.m20855b());
        C2000a.m20902a(activity);
        this.f3555c = new C2058a(activity, "去支付宝付款");
    }

    public synchronized String pay(String str, boolean z) {
        if (m20933b()) {
            return C1998j.m20909d();
        }
        if (z) {
            showLoading();
        }
        if (str.contains("payment_inst=")) {
            String substring = str.substring(str.indexOf("payment_inst=") + 13);
            int indexOf = substring.indexOf(38);
            if (indexOf > 0) {
                substring = substring.substring(0, indexOf);
            }
            C1997i.m20917a(substring.replaceAll("\"", "").toLowerCase(Locale.getDefault()).replaceAll("alipay", ""));
        } else {
            C1997i.m20917a("");
        }
        if (str.contains("service=alipay.acquire.mr.ord.createandpay")) {
            C2003a.f3687s = true;
        }
        if (C2003a.f3687s) {
            if (str.startsWith("https://wappaygw.alipay.com/home/exterfaceAssign.htm?")) {
                str = str.substring(str.indexOf("https://wappaygw.alipay.com/home/exterfaceAssign.htm?") + 53);
            } else if (str.startsWith("https://mclient.alipay.com/home/exterfaceAssign.htm?")) {
                str = str.substring(str.indexOf("https://mclient.alipay.com/home/exterfaceAssign.htm?") + 52);
            }
        }
        String m20940a = m20940a(str);
        C2047i.m20690a(this.f3554b.getApplicationContext(), m20940a);
        C2006a.m20868g().m20879a(this.f3554b.getApplicationContext());
        dismissLoading();
        C2000a.m20895b(this.f3554b.getApplicationContext(), str);
        return m20940a;
    }

    public synchronized Map<String, String> payV2(String str, boolean z) {
        return C2050l.m20679a(pay(str, z));
    }

    public synchronized String fetchTradeToken() {
        return C2047i.m20691a(this.f3554b.getApplicationContext());
    }

    public synchronized boolean payInterceptorWithUrl(String str, boolean z, H5PayCallback h5PayCallback) {
        String fetchOrderInfoFromH5PayUrl;
        fetchOrderInfoFromH5PayUrl = fetchOrderInfoFromH5PayUrl(str);
        if (!TextUtils.isEmpty(fetchOrderInfoFromH5PayUrl)) {
            new Thread(new RunnableC1995g(this, fetchOrderInfoFromH5PayUrl, z, h5PayCallback)).start();
        }
        return !TextUtils.isEmpty(fetchOrderInfoFromH5PayUrl);
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x00dc, code lost:
        if (r9.startsWith("http://" + r16.f3557e) != false) goto L98;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0179, code lost:
        if (r9.startsWith("http://" + r16.f3558f) != false) goto L85;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x003f, code lost:
        if (r9.startsWith("http://" + r16.f3556d) != false) goto L103;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized java.lang.String fetchOrderInfoFromH5PayUrl(java.lang.String r17) {
        /*
            Method dump skipped, instructions count: 1249
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.app.PayTask.fetchOrderInfoFromH5PayUrl(java.lang.String):java.lang.String");
    }

    /* renamed from: a */
    private static final String m20934a(String... strArr) {
        if (strArr == null) {
            return "";
        }
        for (String str : strArr) {
            if (!TextUtils.isEmpty(str)) {
                return str;
            }
        }
        return "";
    }

    public static synchronized boolean fetchSdkConfig(Context context) {
        synchronized (PayTask.class) {
            try {
                C2033b.m20772a().m20771a(context, C2009c.m20855b());
                long elapsedRealtime = SystemClock.elapsedRealtime() / 1000;
                if (elapsedRealtime - f3551h < C2006a.m20868g().m20870e()) {
                    return false;
                }
                f3551h = elapsedRealtime;
                C2006a.m20868g().m20879a(context.getApplicationContext());
                return true;
            } catch (Exception e) {
                C2040c.m20715a(e);
                return false;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.alipay.sdk.app.PayTask$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public class C1987a {

        /* renamed from: b */
        private String f3561b;

        /* renamed from: c */
        private String f3562c;

        /* renamed from: d */
        private String f3563d;

        /* renamed from: e */
        private String f3564e;

        private C1987a() {
            this.f3561b = "";
            this.f3562c = "";
            this.f3563d = "";
            this.f3564e = "";
        }

        /* synthetic */ C1987a(PayTask payTask, RunnableC1995g runnableC1995g) {
            this();
        }

        /* renamed from: a */
        public String m20931a() {
            return this.f3561b;
        }

        /* renamed from: a */
        public void m20930a(String str) {
            this.f3561b = str;
        }

        /* renamed from: b */
        public String m20929b() {
            return this.f3563d;
        }

        /* renamed from: b */
        public void m20928b(String str) {
            this.f3563d = str;
        }

        /* renamed from: c */
        public String m20927c() {
            return this.f3562c;
        }

        /* renamed from: c */
        public void m20926c(String str) {
            this.f3562c = str;
        }

        /* renamed from: d */
        public String m20925d() {
            return this.f3564e;
        }

        /* renamed from: d */
        public void m20924d(String str) {
            this.f3564e = str;
        }
    }

    /* renamed from: a */
    private boolean m20935a(boolean z, boolean z2, String str, StringBuilder sb, Map<String, String> map, String... strArr) {
        String str2 = "";
        int length = strArr.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            String str3 = strArr[i];
            if (!TextUtils.isEmpty(map.get(str3))) {
                str2 = map.get(str3);
                break;
            }
            i++;
        }
        if (TextUtils.isEmpty(str2)) {
            return !z2;
        } else if (z) {
            sb.append("&");
            sb.append(str);
            sb.append("=\"");
            sb.append(str2);
            sb.append("\"");
            return true;
        } else {
            sb.append(str);
            sb.append("=\"");
            sb.append(str2);
            sb.append("\"");
            return true;
        }
    }

    public synchronized H5PayResultModel h5Pay(String str, boolean z) {
        H5PayResultModel h5PayResultModel;
        h5PayResultModel = new H5PayResultModel();
        String[] split = pay(str, z).split(";");
        HashMap hashMap = new HashMap();
        for (String str2 : split) {
            int indexOf = str2.indexOf("={");
            if (indexOf >= 0) {
                String substring = str2.substring(0, indexOf);
                hashMap.put(substring, m20939a(str2, substring));
            }
        }
        if (hashMap.containsKey("resultStatus")) {
            h5PayResultModel.setResultCode(hashMap.get("resultStatus"));
        }
        h5PayResultModel.setReturnUrl(m20937a(str, hashMap));
        if (TextUtils.isEmpty(h5PayResultModel.getReturnUrl())) {
            C2000a.m20899a("biz", "H5CbUrlEmpty", "");
        }
        return h5PayResultModel;
    }

    /* renamed from: a */
    private String m20937a(String str, Map<String, String> map) throws UnsupportedEncodingException {
        boolean equals = "9000".equals(map.get("resultStatus"));
        String str2 = map.get("result");
        C1987a remove = this.f3559g.remove(str);
        String[] strArr = new String[2];
        strArr[0] = remove != null ? remove.m20929b() : "";
        strArr[1] = remove != null ? remove.m20925d() : "";
        m20934a(strArr);
        if (map.containsKey("callBackUrl")) {
            return map.get("callBackUrl");
        }
        if (str2.length() > 15) {
            String m20934a = m20934a(C2052n.m20664a("&callBackUrl=\"", "\"", str2), C2052n.m20664a("&call_back_url=\"", "\"", str2), C2052n.m20664a("&return_url=\"", "\"", str2), URLDecoder.decode(C2052n.m20664a("&return_url=", "&", str2), "utf-8"), URLDecoder.decode(C2052n.m20664a("&callBackUrl=", "&", str2), "utf-8"), C2052n.m20664a("call_back_url=\"", "\"", str2));
            if (!TextUtils.isEmpty(m20934a)) {
                return m20934a;
            }
        }
        if (remove != null) {
            String m20931a = equals ? remove.m20931a() : remove.m20927c();
            if (!TextUtils.isEmpty(m20931a)) {
                return m20931a;
            }
        }
        return remove != null ? C2006a.m20868g().m20871d() : "";
    }

    /* renamed from: a */
    private String m20939a(String str, String str2) {
        String str3 = str2 + "={";
        return str.substring(str.indexOf(str3) + str3.length(), str.lastIndexOf("}"));
    }

    /* renamed from: a */
    private C2042e.InterfaceC2043a m20943a() {
        return new C1996h(this);
    }

    public void showLoading() {
        C2058a c2058a = this.f3555c;
        if (c2058a != null) {
            c2058a.m20619b();
        }
    }

    public void dismissLoading() {
        C2058a c2058a = this.f3555c;
        if (c2058a != null) {
            c2058a.m20617c();
            this.f3555c = null;
        }
    }

    /* renamed from: a */
    private String m20940a(String str) {
        String m20780a = new C2032a(this.f3554b).m20780a(str);
        if (m20780a.contains("paymethod=\"expressGateway\"")) {
            return m20932b(m20780a);
        }
        List<C2006a.C2007a> m20869f = C2006a.m20868g().m20869f();
        if (!C2006a.m20868g().f3732q || m20869f == null) {
            m20869f = C1997i.f3583a;
        }
        if (C2052n.m20659b(this.f3554b, m20869f)) {
            C2042e c2042e = new C2042e(this.f3554b, m20943a());
            String m20700a = c2042e.m20700a(m20780a);
            c2042e.m20705a();
            if (TextUtils.equals(m20700a, "failed") || TextUtils.equals(m20700a, "scheme_failed")) {
                C2000a.m20899a("biz", "LogBindCalledH5", "");
                return m20932b(m20780a);
            } else if (TextUtils.isEmpty(m20700a)) {
                return C1998j.m20910c();
            } else {
                if (m20700a.contains("{\"isLogin\":\"false\"}")) {
                    C2000a.m20899a("biz", "LogHkLoginByIntent", "");
                    return m20938a(m20780a, m20869f, m20700a, this.f3554b);
                }
                return m20700a;
            }
        }
        C2000a.m20899a("biz", "LogCalledH5", "");
        return m20932b(m20780a);
    }

    /* renamed from: a */
    private static String m20938a(String str, List<C2006a.C2007a> list, String str2, Activity activity) {
        C2052n.C2053a m20669a = C2052n.m20669a(activity, list);
        if (m20669a == null || m20669a.m20641a() || m20669a.m20640b() || !TextUtils.equals(m20669a.f3908a.packageName, "hk.alipay.wallet")) {
            return str2;
        }
        C2040c.m20714b("msp", "PayTask:payResult: NOT_LOGIN");
        String valueOf = String.valueOf(str.hashCode());
        PayResultActivity.f3540b.put(valueOf, new Object());
        Intent intent = new Intent(activity, PayResultActivity.class);
        intent.putExtra("orderSuffix", str);
        intent.putExtra("externalPkgName", activity.getPackageName());
        intent.putExtra("phonecashier.pay.hash", valueOf);
        activity.startActivity(intent);
        synchronized (PayResultActivity.f3540b.get(valueOf)) {
            try {
                C2040c.m20714b("msp", "PayTask:payResult: wait");
                PayResultActivity.f3540b.get(valueOf).wait();
            } catch (InterruptedException e) {
                C2040c.m20714b("msp", "PayTask:payResult: InterruptedException:" + e);
                return C1998j.m20910c();
            }
        }
        String str3 = PayResultActivity.C1986a.f3549b;
        C2040c.m20714b("msp", "PayTask:payResult: result:" + str3);
        return str3;
    }

    /* renamed from: b */
    private String m20932b(String str) {
        showLoading();
        EnumC1999k enumC1999k = null;
        try {
            try {
                JSONObject m20813c = new C2029e().mo20793a(this.f3554b.getApplicationContext(), str).m20813c();
                String optString = m20813c.optString("end_code", null);
                List<C2031b> m20784a = C2031b.m20784a(m20813c.optJSONObject("form").optJSONObject("onload"));
                for (int i = 0; i < m20784a.size(); i++) {
                    if (m20784a.get(i).m20783b() == EnumC2030a.Update) {
                        C2031b.m20786a(m20784a.get(i));
                    }
                }
                m20936a(m20813c);
                dismissLoading();
                for (int i2 = 0; i2 < m20784a.size(); i2++) {
                    C2031b c2031b = m20784a.get(i2);
                    if (c2031b.m20783b() == EnumC2030a.WapPay) {
                        return m20942a(c2031b);
                    }
                    if (c2031b.m20783b() == EnumC2030a.OpenWeb) {
                        return m20941a(c2031b, optString);
                    }
                }
            } catch (IOException e) {
                enumC1999k = EnumC1999k.m20903b(EnumC1999k.NETWORK_ERROR.m20907a());
                C2000a.m20896a("net", e);
            }
            if (enumC1999k == null) {
                enumC1999k = EnumC1999k.m20903b(EnumC1999k.FAILED.m20907a());
            }
            return C1998j.m20914a(enumC1999k.m20907a(), enumC1999k.m20904b(), "");
        } finally {
            dismissLoading();
        }
    }

    /* renamed from: a */
    private void m20936a(JSONObject jSONObject) {
        try {
            String optString = jSONObject.optString("tid");
            String optString2 = jSONObject.optString("client_key");
            if (TextUtils.isEmpty(optString) || TextUtils.isEmpty(optString2)) {
                return;
            }
            C2035b.m20758a(C2033b.m20772a().m20770b()).m20757a(optString, optString2);
        } catch (Throwable th) {
            C2000a.m20898a("biz", "ParserTidClientKeyEx", th);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0092, code lost:
        r0 = r6.m20781c();
        r10 = com.alipay.sdk.app.C1998j.m20914a(java.lang.Integer.valueOf(r0[1]).intValue(), r0[0], com.alipay.sdk.util.C2052n.m20647e(r0[2]));
     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.String m20941a(com.alipay.sdk.protocol.C2031b r10, java.lang.String r11) {
        /*
            Method dump skipped, instructions count: 280
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.app.PayTask.m20941a(com.alipay.sdk.protocol.b, java.lang.String):java.lang.String");
    }

    /* renamed from: a */
    private String m20942a(C2031b c2031b) {
        String[] m20781c = c2031b.m20781c();
        Intent intent = new Intent(this.f3554b, H5PayActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("url", m20781c[0]);
        if (m20781c.length == 2) {
            bundle.putString("cookie", m20781c[1]);
        }
        intent.putExtras(bundle);
        this.f3554b.startActivity(intent);
        synchronized (f3550a) {
            try {
                f3550a.wait();
            } catch (InterruptedException e) {
                C2040c.m20715a(e);
                return C1998j.m20910c();
            }
        }
        String m20915a = C1998j.m20915a();
        return TextUtils.isEmpty(m20915a) ? C1998j.m20910c() : m20915a;
    }

    /* renamed from: b */
    private static boolean m20933b() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (elapsedRealtime - f3553j >= 3000) {
            f3553j = elapsedRealtime;
            return false;
        }
        return true;
    }
}
