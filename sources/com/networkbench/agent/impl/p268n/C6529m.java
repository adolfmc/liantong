package com.networkbench.agent.impl.p268n;

import android.text.TextUtils;
import com.networkbench.agent.impl.NBSAgent;
import com.networkbench.agent.impl.harvest.ActionData;
import com.networkbench.agent.impl.harvest.Harvest;
import com.networkbench.agent.impl.harvest.HarvestConfiguration;
import com.networkbench.agent.impl.harvest.HttpLibType;
import com.networkbench.agent.impl.harvest.RequestMethodType;
import com.networkbench.agent.impl.harvest.type.HarvestableArray;
import com.networkbench.agent.impl.p243c.C6281e;
import com.networkbench.agent.impl.p243c.p244a.C6255f;
import com.networkbench.agent.impl.p252e.C6369q;
import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.C6396h;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.agent.impl.p268n.p269a.C6504a;
import com.networkbench.agent.impl.p268n.p269a.C6506c;
import com.networkbench.agent.impl.p268n.p269a.C6508d;
import com.networkbench.agent.impl.p268n.p269a.C6511e;
import com.networkbench.agent.impl.util.C6638h;
import com.networkbench.agent.impl.util.C6652t;
import com.networkbench.agent.impl.util.C6653u;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.n.m */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6529m {

    /* renamed from: b */
    private static final long f16666b = 120000;

    /* renamed from: c */
    private static final int f16667c = 10;

    /* renamed from: a */
    private static InterfaceC6393e f16665a = C6394f.m10150a();

    /* renamed from: d */
    private static ConcurrentLinkedQueue<C6511e> f16668d = new ConcurrentLinkedQueue<>();

    /* renamed from: a */
    private static long m9519a(long j) {
        if (j >= -1) {
            return j;
        }
        return 0L;
    }

    /* renamed from: a */
    public static void m9517a(String str) {
        if (str == null || str.length() <= 0) {
            return;
        }
        try {
            JSONArray jSONArray = new JSONArray(str);
            int length = jSONArray.length();
            if (length > 0) {
                for (int i = 0; i < length; i++) {
                    JSONObject jSONObject = (JSONObject) jSONArray.get(i);
                    String string = jSONObject.getString("md");
                    String string2 = jSONObject.getString("hf");
                    String string3 = jSONObject.getString("ul");
                    int i2 = jSONObject.getInt("dr");
                    jSONObject.getInt("rt");
                    int i3 = jSONObject.getInt("sc");
                    int i4 = jSONObject.getInt("ec");
                    int i5 = jSONObject.getInt("rsz");
                    int i6 = jSONObject.getInt("rqz");
                    jSONObject.getLong("st");
                    jSONObject.getLong("ed");
                    String string4 = jSONObject.getString("xData");
                    ActionData actionData = new ActionData();
                    actionData.setUrl(C6653u.m8726b(string2 + string3));
                    actionData.setStatusCode(i3);
                    actionData.setErrorCode(i4);
                    actionData.setTotalTime(i2);
                    actionData.setCarrier(NBSAgent.getActiveNetworkCarrier());
                    actionData.setBytesReceived(i5);
                    actionData.setBytesSent(i6);
                    actionData.setAppData(string4);
                    actionData.setTimestamp(Long.valueOf(System.currentTimeMillis()));
                    actionData.setUrlParams(null);
                    actionData.setRequestMethod(C6524j.m9530f(string));
                    actionData.setHttpLibType(HttpLibType.WebviewAJAX);
                    actionData.setTime_to_connect(0);
                    actionData.setTime_first_package(0);
                    actionData.setTime_ssl_handshake(0);
                    actionData.setTime_to_dns(0);
                    actionData.setIP("");
                    if (C6652t.m8761a(actionData.getUrl())) {
                        return;
                    }
                    actionData.correctDataInfo();
                    if (i2 >= 300000) {
                        return;
                    }
                    Harvest.addHttpTransaction(actionData);
                }
            }
        } catch (Exception e) {
            f16665a.mo10116d("parseAjax error " + e);
        }
    }

    /* renamed from: a */
    public static void m9514a(String str, String str2, String str3, String str4, int i, int i2, String str5, String str6, int i3, int i4, String str7, long j, int i5) {
        if (i <= 0 || i2 <= 0) {
            return;
        }
        try {
            C6506c.C6507a c6507a = new C6506c.C6507a();
            c6507a.m9692a(str3).m9689b(str4).m9691b(i).m9688c(i2).m9686c(str5).m9683d(m9507e(str6)).m9685d(i3).m9682e(i4).m9694a(i5);
            C6504a c6504a = new C6504a(c6507a);
            c6504a.m9701c(str7);
            c6504a.m9702b(C6653u.m8726b(str2));
            c6504a.m9703a(str);
            c6504a.m9704a((int) TimeUnit.SECONDS.convert(j, TimeUnit.MILLISECONDS));
            Harvest.addJsError(c6504a);
        } catch (Exception unused) {
        }
    }

    /* renamed from: b */
    public static ConcurrentLinkedQueue<C6506c> m9510b(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        ConcurrentLinkedQueue<C6506c> concurrentLinkedQueue = new ConcurrentLinkedQueue<>();
        try {
            JSONArray jSONArray = new JSONArray(str);
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONArray jSONArray2 = jSONArray.getJSONArray(i);
                if (jSONArray2 != null) {
                    String m8726b = C6653u.m8726b(jSONArray2.getString(8));
                    String string = jSONArray2.getString(0);
                    int i2 = jSONArray2.getInt(1);
                    int i3 = jSONArray2.getInt(2);
                    String string2 = jSONArray2.getString(3);
                    String m9507e = m9507e(jSONArray2.getString(5));
                    int i4 = jSONArray2.getInt(4);
                    int i5 = jSONArray2.getInt(7);
                    int i6 = jSONArray2.getInt(9);
                    if (i2 > 0 && i3 > 0) {
                        concurrentLinkedQueue.add(new C6506c.C6507a().m9692a(m8726b).m9689b(string).m9691b(i2).m9688c(i3).m9686c(string2).m9683d(m9507e).m9685d(i4).m9682e(i5).m9694a(i6).m9695a());
                    }
                }
            }
        } catch (Exception e) {
            f16665a.mo10121a("parseJsError failed:", e);
        }
        return concurrentLinkedQueue;
    }

    /* renamed from: a */
    public static void m9516a(String str, int i) {
        JSONArray jSONArray;
        long j;
        long j2;
        long j3;
        long j4;
        long j5;
        long j6;
        long j7;
        long j8;
        long j9;
        if (str == null || str.length() <= 0) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                if (next.equals("res") && (jSONArray = (JSONArray) jSONObject.get(next)) != null && jSONArray.length() > 0) {
                    int length = jSONArray.length();
                    int i2 = 0;
                    while (i2 < length) {
                        JSONObject jSONObject2 = (JSONObject) jSONArray.get(i2);
                        long optLong = jSONObject2.optLong("o");
                        String optString = jSONObject2.optString("rt");
                        if (optString.equalsIgnoreCase("IFRAME")) {
                            optString = "html";
                        }
                        String optString2 = jSONObject2.optString("n");
                        long optLong2 = jSONObject2.optLong("dr");
                        long optLong3 = jSONObject2.optLong("f");
                        long optLong4 = jSONObject2.optLong("ds");
                        Iterator<String> it = keys;
                        JSONObject jSONObject3 = jSONObject;
                        long optLong5 = jSONObject2.optLong("de");
                        JSONArray jSONArray2 = jSONArray;
                        int i3 = length;
                        long optLong6 = jSONObject2.optLong("cs");
                        long optLong7 = jSONObject2.optLong("ce");
                        long optLong8 = jSONObject2.optLong("sl");
                        long optLong9 = jSONObject2.optLong("qs");
                        long optLong10 = jSONObject2.optLong("rs");
                        long optLong11 = jSONObject2.optLong("re");
                        long optLong12 = jSONObject2.optLong("ts");
                        long optLong13 = jSONObject2.optLong("es");
                        long optLong14 = jSONObject2.optLong("des");
                        if (optLong2 <= 0) {
                            optLong2 = optLong11;
                        }
                        String m8726b = C6653u.m8726b(optString2);
                        if (!C6652t.m8761a(m8726b)) {
                            int m8706e = C6653u.m8706e(m8726b);
                            if (optLong2 >= 120000) {
                                return;
                            }
                            if (m8706e == 200) {
                                j = optLong4;
                                j2 = optLong7;
                                j3 = optLong8;
                                j4 = optLong9;
                                j5 = optLong10;
                                j6 = optLong11;
                                j7 = optLong12;
                                j8 = optLong13;
                                j9 = optLong14;
                                m9515a(m8726b, m8706e, optLong2, optLong6, j2, j5, j4, j3, j, optLong5, HttpLibType.WebViewResource, j8);
                            } else {
                                j = optLong4;
                                j2 = optLong7;
                                j3 = optLong8;
                                j4 = optLong9;
                                j5 = optLong10;
                                j6 = optLong11;
                                j7 = optLong12;
                                j8 = optLong13;
                                j9 = optLong14;
                            }
                            if ((!C6638h.m8963w().m9065V() && i > 0) || C6516c.f16607a) {
                                f16668d.add(new C6511e.C6513a().m9612a((int) optLong).m9610a(optString).m9607b(m8726b).m9609b((int) optLong3).m9606c((int) j).m9603d((int) optLong5).m9600e((int) optLong6).m9598f((int) j2).m9596g((int) j3).m9594h((int) j4).m9592i((int) j5).m9590j((int) j6).m9588k((int) j7).m9586l((int) j8).m9584m((int) j9).m9604c("").m9601d("").m9613a());
                            }
                        }
                        i2++;
                        keys = it;
                        jSONObject = jSONObject3;
                        jSONArray = jSONArray2;
                        length = i3;
                    }
                    continue;
                }
                keys = keys;
                jSONObject = jSONObject;
            }
        } catch (Exception e) {
            InterfaceC6393e interfaceC6393e = f16665a;
            interfaceC6393e.mo10116d("parse resource error " + e);
        }
    }

    /* renamed from: a */
    private static void m9515a(String str, int i, long j, long j2, long j3, long j4, long j5, long j6, long j7, long j8, HttpLibType httpLibType, long j9) {
        int i2 = i;
        ActionData actionData = new ActionData();
        actionData.setDataTag(C6638h.m8963w().m9001h());
        actionData.setUrl(str);
        actionData.setStatusCode(i);
        if (i2 == 200) {
            i2 = 0;
        }
        actionData.setErrorCode(i2);
        int i3 = (int) j;
        actionData.setTotalTime(i3);
        actionData.setCarrier(NBSAgent.getActiveNetworkCarrier());
        actionData.setBytesReceived(j9);
        actionData.setBytesSent(0L);
        actionData.setAppData(null);
        actionData.setTimestamp(Long.valueOf(System.currentTimeMillis()));
        actionData.setUrlParams(null);
        actionData.setRequestMethod(RequestMethodType.GET);
        actionData.setHttpLibType(httpLibType);
        long j10 = j3 - j2;
        if (j10 == 0) {
            j10 = -1;
        }
        actionData.setTime_to_connect((int) j10);
        actionData.setTime_first_package((int) (j4 - j5));
        actionData.setTime_ssl_handshake(C6653u.m8742a(str, j3, j6));
        long j11 = j8 - j7;
        if (j11 == 0) {
            j11 = -1;
        }
        actionData.setTime_to_dns((int) j11);
        actionData.setIP("");
        actionData.setAppPhase(C6638h.f17113m.intValue());
        actionData.correctDataInfo();
        if (i3 >= C6638h.f17105b) {
            return;
        }
        if (actionData.getHttpLibType() == HttpLibType.Webview && (((actionData.getStatusCode() >= 400 && actionData.getStatusCode() < 900) || actionData.getStatusCode() == -1) && (actionData.getStatusCode() >= 400 || actionData.getStatusCode() == -1))) {
            C6281e c6281e = new C6281e(C6653u.m8744a(actionData.getUrl()), "", C6653u.m8685p(actionData.getUrl()), "", actionData.getStatusCode(), "", "", null, "", actionData.getRequestMethod(), actionData.getCdnVendorName(), actionData.getHttpLibType(), actionData.getAppPhase(), C6255f.m10820a(), actionData.requestHeaderParam, actionData.responseHeaderParam, C6638h.m8963w().m9001h());
            c6281e.m10658a(C6638h.m8963w().m9001h());
            Harvest.addHttpError(actionData, c6281e);
            return;
        }
        Harvest.addHttpTransaction(actionData);
    }

    /* renamed from: c */
    public static void m9509c(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            String string = jSONObject.getString("cpm");
            if (string == null || f16668d == null) {
                return;
            }
            f16668d.clear();
            String string2 = jSONObject.getString("rm");
            if (string2 != null) {
                C6396h.m10138d("resource_metric :" + string2);
                m9516a(string2, m9508d(string));
            }
            String string3 = jSONObject.getString("em");
            C6396h.m10138d("errors_metrics:" + string3);
            ConcurrentLinkedQueue<C6506c> m9510b = m9510b(string3);
            C6396h.m10138d("current_pg_metric :" + string);
            m9513a(string, m9510b);
        } catch (Throwable th) {
            C6396h.m10138d("parsePageInfo error: " + th.getMessage());
        }
    }

    /* renamed from: d */
    public static int m9508d(String str) {
        try {
            return new JSONObject(str).optInt("sli");
        } catch (Throwable th) {
            InterfaceC6393e interfaceC6393e = f16665a;
            interfaceC6393e.mo10116d("getSlowIndicator error " + th);
            return 0;
        }
    }

    /* renamed from: a */
    public static void m9513a(String str, ConcurrentLinkedQueue<C6506c> concurrentLinkedQueue) {
        String str2;
        String str3;
        String str4;
        String str5;
        try {
            JSONObject jSONObject = new JSONObject(str);
            long optLong = jSONObject.optLong("ns");
            String m8726b = C6653u.m8726b(jSONObject.optString("ul"));
            String optString = jSONObject.optString("pvid");
            int m9519a = (int) m9519a(jSONObject.optLong("es"));
            long m9519a2 = m9519a(jSONObject.optLong("ee"));
            long m9519a3 = m9519a(jSONObject.optLong("f"));
            long m9519a4 = m9519a(jSONObject.optLong("ds"));
            long m9519a5 = m9519a(jSONObject.optLong("de"));
            long m9519a6 = m9519a(jSONObject.optLong("cs"));
            long m9519a7 = m9519a(jSONObject.optLong("ce"));
            long m9519a8 = m9519a(jSONObject.optLong("sl"));
            long m9519a9 = m9519a(jSONObject.optLong("qs"));
            long m9519a10 = m9519a(jSONObject.optLong("rs"));
            long m9519a11 = m9519a(jSONObject.optLong("re"));
            long m9519a12 = m9519a(jSONObject.optLong("doml"));
            long m9519a13 = m9519a(jSONObject.optLong("oi"));
            long m9519a14 = m9519a(jSONObject.optLong("os"));
            long m9519a15 = m9519a(jSONObject.optLong("oe"));
            long m9519a16 = m9519a(jSONObject.optLong("oc"));
            long m9519a17 = m9519a(jSONObject.optLong("ls"));
            long m9519a18 = m9519a(jSONObject.optLong("le"));
            long m9519a19 = m9519a(jSONObject.optLong("fp"));
            long m9519a20 = m9519a(jSONObject.optLong("fs"));
            int optInt = jSONObject.optInt("je");
            int optInt2 = jSONObject.optInt("sli");
            m9519a(jSONObject.optLong("dr"));
            long j = m9519a19 > m9519a16 ? m9519a16 : m9519a19;
            str2 = "";
            if (C6652t.m8761a(m8726b)) {
                return;
            }
            try {
                Map<String, String> m8712d = C6653u.m8712d(jSONObject.optString("header"));
                if (m8712d != null) {
                    str2 = C6638h.m8963w().m9036aj() ? m8712d.get("X-Tingyun-Tx-Data") : "";
                    str5 = m8712d.get(HarvestConfiguration.getDefaultHarvestConfiguration().getCdnHeaderName());
                } else {
                    str5 = "";
                }
                str4 = str5;
                str3 = str2;
            } catch (Exception e) {
                InterfaceC6393e interfaceC6393e = f16665a;
                interfaceC6393e.mo10115e("parsePageData header warning " + e);
                str3 = str2;
                str4 = "";
            }
            int m8706e = C6653u.m8706e(m8726b);
            if (m8706e <= 900 && m8706e != -1) {
                int m8760a = C6652t.m8760a(m8726b, m8706e);
                long j2 = j;
                m9515a(m8726b, m8760a, m9519a11, m9519a6, m9519a7, m9519a10, m9519a9, m9519a8, m9519a4, m9519a5, HttpLibType.Webview, 0L);
                if (!C6638h.m8963w().m9065V() || C6516c.f16607a) {
                    C6508d m9518a = m9518a(optLong, m8726b, optString, m9519a, (int) m9519a2, (int) m9519a3, (int) m9519a4, (int) m9519a5, (int) m9519a6, (int) m9519a7, (int) m9519a8, (int) m9519a9, (int) m9519a10, (int) m9519a11, (int) m9519a12, (int) m9519a13, (int) m9519a14, (int) m9519a15, (int) m9519a16, (int) m9519a17, (int) m9519a18, (int) j2, (int) m9519a20, optInt, optInt2, str3, str4, m8760a);
                    Iterator<C6511e> it = f16668d.iterator();
                    while (it.hasNext()) {
                        C6511e next = it.next();
                        if (next != null) {
                            m9518a.m9676a().m9699a((HarvestableArray) next);
                        }
                    }
                    if (concurrentLinkedQueue != null) {
                        Iterator<C6506c> it2 = concurrentLinkedQueue.iterator();
                        while (it2.hasNext()) {
                            C6506c next2 = it2.next();
                            if (next2 != null) {
                                m9518a.m9675b().m9699a((HarvestableArray) next2);
                            }
                        }
                    }
                    Harvest.addPagePerfData(m9518a);
                }
            }
        } catch (Exception e2) {
            InterfaceC6393e interfaceC6393e2 = f16665a;
            interfaceC6393e2.mo10116d("parsePageData error " + e2);
        }
    }

    /* renamed from: a */
    public static C6508d m9518a(long j, String str, String str2, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12, int i13, int i14, int i15, int i16, int i17, int i18, int i19, int i20, int i21, int i22, String str3, String str4, int i23) {
        int i24 = i23;
        C6508d.C6510a m9621w = new C6508d.C6510a().m9669a((int) TimeUnit.SECONDS.convert(j, TimeUnit.MILLISECONDS)).m9667a(str).m9664b(str2).m9666b(i).m9663c(i2).m9660d(i3).m9657e(i4).m9655f(i5).m9653g(i6).m9651h(i7).m9649i(i8).m9647j(i9).m9645k(i10).m9643l(i11).m9641m(i12).m9639n(i13).m9637o(i14).m9635p(i15).m9633q(i16).m9631r(i17).m9629s(i18).m9627t(i19).m9625u(i20).m9623v(i21).m9621w(i24);
        if (i24 == 200) {
            i24 = 0;
        }
        return m9621w.m9619x(i24).m9617y(0).m9661c(str3).m9658d(str4).m9615z(i22).m9670a();
    }

    /* renamed from: e */
    public static String m9507e(String str) {
        return C6653u.m8683r(str) ? "" : m9512a(str.split("\n")) ? m9511a(str.split("\n"), 10) : str;
    }

    /* renamed from: a */
    private static boolean m9512a(String[] strArr) {
        return strArr != null && strArr.length > 10;
    }

    /* renamed from: a */
    private static String m9511a(String[] strArr, int i) {
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < i; i2++) {
            sb.append(strArr[i2]);
            sb.append('\n');
        }
        return sb.toString();
    }

    /* renamed from: f */
    public static void m9506f(String str) {
        if (str != null) {
            try {
                if (new JSONObject(str).getInt("type") == 0) {
                    C6369q.m10273a().m10272a(new RunnableC6519e(str));
                }
            } catch (Throwable unused) {
            }
        }
    }
}
