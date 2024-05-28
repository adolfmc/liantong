package com.baidu.bdhttpdns;

import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.concurrent.RejectedExecutionException;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* renamed from: com.baidu.bdhttpdns.f */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class C2438f {

    /* renamed from: a */
    private static volatile C2438f f4327a;

    /* renamed from: c */
    private static C2441c f4328c;

    /* renamed from: b */
    private String f4329b = "180.76.76.200";

    /* renamed from: d */
    private boolean f4330d = true;

    /* renamed from: e */
    private long f4331e = 0;

    /* renamed from: h */
    private String f4334h = "";

    /* renamed from: i */
    private String f4335i = "";

    /* renamed from: g */
    private final Object f4333g = new Object();

    /* renamed from: f */
    private final HashSet<String> f4332f = new HashSet<>();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.bdhttpdns.f$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface InterfaceC2439a {
        /* renamed from: a */
        void mo20106a(int i, ArrayList<String> arrayList, long j, String str);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.bdhttpdns.f$b */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    class RunnableC2440b implements Runnable {

        /* renamed from: b */
        private String f4337b;

        /* renamed from: c */
        private InterfaceC2439a f4338c;

        public RunnableC2440b(String str, InterfaceC2439a interfaceC2439a) {
            this.f4337b = str;
            this.f4338c = interfaceC2439a;
        }

        @Override // java.lang.Runnable
        public void run() {
            String m20107c = C2438f.this.m20107c(this.f4337b);
            if (m20107c == null) {
                this.f4338c.mo20106a(-1, null, 0L, this.f4337b);
                C2444h.m20103a("Httpdns request failed, host(%s), get url error", this.f4337b);
                return;
            }
            C2438f.this.m20112a(m20107c, this.f4337b, this.f4338c);
            synchronized (C2438f.this.f4333g) {
                C2438f.this.f4332f.remove(this.f4337b);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.bdhttpdns.f$c */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public class C2441c implements HostnameVerifier {
        private C2441c() {
        }

        @Override // javax.net.ssl.HostnameVerifier
        public boolean verify(String str, SSLSession sSLSession) {
            return HttpsURLConnection.getDefaultHostnameVerifier().verify("httpdns.baidubce.com", sSLSession);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.bdhttpdns.f$d */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public class C2442d {

        /* renamed from: b */
        private String f4341b;

        /* renamed from: c */
        private final ArrayList<String> f4342c;

        /* renamed from: d */
        private final long f4343d;

        public C2442d(String str, ArrayList<String> arrayList, long j) {
            this.f4341b = str;
            this.f4342c = arrayList;
            this.f4343d = j;
        }

        /* renamed from: a */
        public ArrayList<String> m20105a() {
            return this.f4342c;
        }

        /* renamed from: b */
        public long m20104b() {
            return this.f4343d;
        }
    }

    private C2438f() {
        f4328c = new C2441c();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static C2438f m20121a() {
        if (f4327a == null) {
            synchronized (C2438f.class) {
                if (f4327a == null) {
                    f4327a = new C2438f();
                }
            }
        }
        return f4327a;
    }

    /* renamed from: a */
    private String m20117a(InputStream inputStream) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            StringBuilder sb = new StringBuilder();
            while (true) {
                try {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        bufferedReader.close();
                        return sb.toString();
                    }
                    sb.append(readLine);
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    private String m20115a(String str, long j) {
        return C2432c.m20141b(String.format("%s-%s-%d", str, this.f4335i, Long.valueOf(j)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x008d, code lost:
        if (r12 != null) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x008f, code lost:
        r12.disconnect();
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00a8, code lost:
        if (r12 == null) goto L26;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00ab, code lost:
        if (r0 == null) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x00b1, code lost:
        if (r0.isEmpty() == false) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x00b4, code lost:
        r12 = r0.get(0);
        r14.mo20106a(0, r12.m20105a(), r12.m20104b(), r13);
        com.baidu.bdhttpdns.C2444h.m20103a("Httpdns request successful, host(%s), ipList(%s), ttl(%d)", r13, r12.m20105a().toString(), java.lang.Long.valueOf(r12.m20104b()));
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00e8, code lost:
        r14.mo20106a(-1, null, 0, r13);
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00f1, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:?, code lost:
        return;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00f5  */
    /* JADX WARN: Type inference failed for: r12v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r12v2 */
    /* JADX WARN: Type inference failed for: r12v5, types: [java.net.HttpURLConnection] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void m20112a(java.lang.String r12, java.lang.String r13, com.baidu.bdhttpdns.C2438f.InterfaceC2439a r14) {
        /*
            Method dump skipped, instructions count: 249
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.bdhttpdns.C2438f.m20112a(java.lang.String, java.lang.String, com.baidu.bdhttpdns.f$a):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public String m20107c(String str) {
        String str2;
        Object[] objArr;
        long currentTimeMillis = (System.currentTimeMillis() / 1000) + 300 + this.f4331e;
        String m20115a = m20115a(str, currentTimeMillis);
        if (m20115a == null) {
            return null;
        }
        String format = String.format("%s/v3/resolve?account_id=%s&dn=%s&sign=%s&t=%d&sdk_ver=%s&os_type=%s", this.f4329b, this.f4334h, str, m20115a, Long.valueOf(currentTimeMillis), "1.1", "android");
        if (this.f4330d) {
            str2 = "https://%s";
            objArr = new Object[]{format};
        } else {
            str2 = "http://%s";
            objArr = new Object[]{format};
        }
        return String.format(str2, objArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m20116a(String str) {
        this.f4334h = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m20114a(String str, InterfaceC2439a interfaceC2439a) {
        if (str == null || str.isEmpty()) {
            return;
        }
        synchronized (this.f4333g) {
            if (this.f4332f.contains(str)) {
                C2444h.m20103a("Httpdns request failed, request for host(%s) is in processing", str);
                return;
            }
            this.f4332f.add(str);
            try {
                C2445i.m20101a().m20100b().execute(new RunnableC2440b(str, interfaceC2439a));
            } catch (RejectedExecutionException e) {
                e.printStackTrace();
                C2444h.m20103a("Httpdns request failed, host(%s), async tasks has exceed the maximum thread limit.", str);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m20111a(boolean z) {
        this.f4330d = z;
    }

    /* renamed from: a */
    boolean m20113a(String str, String str2) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            String optString = jSONObject.optString("msg");
            if (optString == null || optString.isEmpty()) {
                C2444h.m20103a("Httpdns request failed, host(%s), response lack of msg", str2);
                return false;
            } else if (!"SignatureExpired".equals(optString)) {
                if ("ok".equals(optString)) {
                    return true;
                }
                C2444h.m20103a("Httpdns request failed, host(%s), response msg(%s) is not ok", str2, optString);
                return false;
            } else {
                int optInt = jSONObject.optInt("timestamp");
                if (optInt == 0) {
                    C2444h.m20103a("Httpdns request failed, host(%s), response get invalid timestamp", str2);
                } else {
                    this.f4331e = optInt - (System.currentTimeMillis() / 1000);
                }
                return false;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            C2444h.m20103a("Httpdns request failed, host(%s), response parse json error", str2);
            return false;
        }
    }

    /* renamed from: b */
    ArrayList<C2442d> m20108b(String str, String str2) {
        String str3;
        Object[] objArr;
        ArrayList<C2442d> arrayList = new ArrayList<>();
        try {
            JSONObject optJSONObject = new JSONObject(str).optJSONObject("data");
            if (optJSONObject == null) {
                C2444h.m20103a("Httpdns request failed, host(%s), response has empty data", str2);
                return null;
            }
            Iterator<String> keys = optJSONObject.keys();
            int i = 0;
            while (keys.hasNext()) {
                String next = keys.next();
                JSONObject optJSONObject2 = optJSONObject.optJSONObject(next);
                JSONArray optJSONArray = optJSONObject2.optJSONArray("ip");
                if (optJSONArray == null || optJSONArray.length() == 0) {
                    str3 = "Httpdns request failed, host(%s), response has no ip field";
                    objArr = new Object[]{str2};
                } else {
                    ArrayList arrayList2 = new ArrayList();
                    for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                        String optString = optJSONArray.optString(i2);
                        if (optString == null || optString.isEmpty()) {
                            C2444h.m20103a("Httpdns request warning, host(%s), response get ip error", str2);
                        } else if (C2432c.m20142a(optString)) {
                            arrayList2.add(optString);
                        } else {
                            C2444h.m20103a("Httpdns request warning, host(%s), response get invalid ip(%s)", str2, optString);
                        }
                    }
                    if (arrayList2.isEmpty()) {
                        str3 = "Httpdns request failed, host(%s), response has no valid ip";
                        objArr = new Object[]{str2};
                    } else {
                        try {
                            long j = optJSONObject2.getLong("ttl");
                            if (j < 0) {
                                C2444h.m20103a("Httpdns request failed, host(%s), response has invalid ttl(%s)", str2, Long.valueOf(j));
                                return null;
                            }
                            arrayList.add(new C2442d(next, arrayList2, j));
                            i++;
                        } catch (JSONException e) {
                            e.printStackTrace();
                            C2444h.m20103a("Httpdns request failed, host(%s), response has no ttl", str2);
                            return null;
                        }
                    }
                }
                C2444h.m20103a(str3, objArr);
            }
            if (i == 0) {
                return null;
            }
            return arrayList;
        } catch (JSONException e2) {
            e2.printStackTrace();
            C2444h.m20103a("Httpdns request failed, host(%s), response parse data json error", str2);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: b */
    public void m20109b(String str) {
        this.f4335i = str;
    }
}
