package com.xiaomi.push;

import android.text.TextUtils;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.xiaomi.push.cc */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11228cc {

    /* renamed from: a */
    private long f21678a;

    /* renamed from: a */
    public String f21679a;

    /* renamed from: b */
    public String f21682b;

    /* renamed from: c */
    public String f21683c;

    /* renamed from: d */
    public String f21684d;

    /* renamed from: e */
    public String f21685e;

    /* renamed from: f */
    public String f21686f;

    /* renamed from: g */
    public String f21687g;

    /* renamed from: h */
    protected String f21688h;

    /* renamed from: i */
    private String f21689i;

    /* renamed from: a */
    private ArrayList<C11240cj> f21680a = new ArrayList<>();

    /* renamed from: a */
    private double f21677a = 0.1d;

    /* renamed from: j */
    private String f21690j = "s.mi1.cc";

    /* renamed from: b */
    private long f21681b = 86400000;

    public C11228cc(String str) {
        this.f21679a = "";
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("the host is empty");
        }
        this.f21678a = System.currentTimeMillis();
        this.f21680a.add(new C11240cj(str, -1));
        this.f21679a = C11232cg.m4573a();
        this.f21682b = str;
    }

    /* renamed from: a */
    public boolean m4602a() {
        return TextUtils.equals(this.f21679a, C11232cg.m4573a());
    }

    /* renamed from: a */
    public boolean m4599a(C11228cc c11228cc) {
        return TextUtils.equals(this.f21679a, c11228cc.f21679a);
    }

    /* renamed from: b */
    public boolean mo4541b() {
        return System.currentTimeMillis() - this.f21678a < this.f21681b;
    }

    /* renamed from: a */
    public void m4600a(long j) {
        if (j <= 0) {
            throw new IllegalArgumentException("the duration is invalid " + j);
        }
        this.f21681b = j;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c */
    public boolean m4587c() {
        long j = this.f21681b;
        if (864000000 >= j) {
            j = 864000000;
        }
        long currentTimeMillis = System.currentTimeMillis();
        long j2 = this.f21678a;
        return currentTimeMillis - j2 > j || (currentTimeMillis - j2 > this.f21681b && this.f21679a.startsWith("WIFI-"));
    }

    /* renamed from: a */
    public ArrayList<String> m4597a(String str) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("the url is empty.");
        }
        URL url = new URL(str);
        if (TextUtils.equals(url.getHost(), this.f21682b)) {
            ArrayList<String> arrayList = new ArrayList<>();
            Iterator<String> it = mo4542a(true).iterator();
            while (it.hasNext()) {
                C11230ce m4576a = C11230ce.m4576a(it.next(), url.getPort());
                arrayList.add(new URL(url.getProtocol(), m4576a.m4577a(), m4576a.m4578a(), url.getFile()).toString());
            }
            return arrayList;
        }
        throw new IllegalArgumentException("the url is not supported by the fallback");
    }

    /* renamed from: a */
    public void m4594a(String str, long j, long j2) {
        try {
            m4589b(new URL(str).getHost(), j, j2);
        } catch (MalformedURLException unused) {
        }
    }

    /* renamed from: b */
    public void m4589b(String str, long j, long j2) {
        m4595a(str, 0, j, j2, null);
    }

    /* renamed from: a */
    public void m4593a(String str, long j, long j2, Exception exc) {
        try {
            m4588b(new URL(str).getHost(), j, j2, exc);
        } catch (MalformedURLException unused) {
        }
    }

    /* renamed from: b */
    public void m4588b(String str, long j, long j2, Exception exc) {
        m4595a(str, -1, j, j2, exc);
    }

    /* renamed from: a */
    public void m4595a(String str, int i, long j, long j2, Exception exc) {
        mo4543a(str, new C11227cb(i, j, j2, exc));
    }

    /* JADX WARN: Code restructure failed: missing block: B:8:0x001b, code lost:
        r1.m4529a(r5);
     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized void mo4543a(java.lang.String r4, com.xiaomi.push.C11227cb r5) {
        /*
            r3 = this;
            monitor-enter(r3)
            java.util.ArrayList<com.xiaomi.push.cj> r0 = r3.f21680a     // Catch: java.lang.Throwable -> L20
            java.util.Iterator r0 = r0.iterator()     // Catch: java.lang.Throwable -> L20
        L7:
            boolean r1 = r0.hasNext()     // Catch: java.lang.Throwable -> L20
            if (r1 == 0) goto L1e
            java.lang.Object r1 = r0.next()     // Catch: java.lang.Throwable -> L20
            com.xiaomi.push.cj r1 = (com.xiaomi.push.C11240cj) r1     // Catch: java.lang.Throwable -> L20
            java.lang.String r2 = r1.f21717a     // Catch: java.lang.Throwable -> L20
            boolean r2 = android.text.TextUtils.equals(r4, r2)     // Catch: java.lang.Throwable -> L20
            if (r2 == 0) goto L7
            r1.m4529a(r5)     // Catch: java.lang.Throwable -> L20
        L1e:
            monitor-exit(r3)
            return
        L20:
            r4 = move-exception
            monitor-exit(r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.C11228cc.mo4543a(java.lang.String, com.xiaomi.push.cb):void");
    }

    /* renamed from: a */
    public synchronized ArrayList<String> m4604a() {
        return mo4542a(false);
    }

    /* renamed from: a */
    public synchronized ArrayList<String> mo4542a(boolean z) {
        ArrayList<String> arrayList;
        C11240cj[] c11240cjArr = new C11240cj[this.f21680a.size()];
        this.f21680a.toArray(c11240cjArr);
        Arrays.sort(c11240cjArr);
        arrayList = new ArrayList<>();
        for (C11240cj c11240cj : c11240cjArr) {
            if (z) {
                arrayList.add(c11240cj.f21717a);
            } else {
                int indexOf = c11240cj.f21717a.indexOf(":");
                if (indexOf != -1) {
                    arrayList.add(c11240cj.f21717a.substring(0, indexOf));
                } else {
                    arrayList.add(c11240cj.f21717a);
                }
            }
        }
        return arrayList;
    }

    /* renamed from: a */
    public synchronized void m4596a(String str) {
        m4598a(new C11240cj(str));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public synchronized void m4598a(C11240cj c11240cj) {
        m4586c(c11240cj.f21717a);
        this.f21680a.add(c11240cj);
    }

    /* renamed from: a */
    public synchronized void m4591a(String[] strArr) {
        int i;
        int size = this.f21680a.size() - 1;
        while (true) {
            i = 0;
            if (size < 0) {
                break;
            }
            int length = strArr.length;
            while (true) {
                if (i < length) {
                    if (TextUtils.equals(this.f21680a.get(size).f21717a, strArr[i])) {
                        this.f21680a.remove(size);
                        break;
                    }
                    i++;
                }
            }
            size--;
        }
        Iterator<C11240cj> it = this.f21680a.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            C11240cj next = it.next();
            if (next.f21715a > i2) {
                i2 = next.f21715a;
            }
        }
        while (i < strArr.length) {
            m4598a(new C11240cj(strArr[i], (strArr.length + i2) - i));
            i++;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.f21679a);
        sb.append("\n");
        sb.append(m4605a());
        Iterator<C11240cj> it = this.f21680a.iterator();
        while (it.hasNext()) {
            sb.append("\n");
            sb.append(it.next().toString());
        }
        sb.append("\n");
        return sb.toString();
    }

    /* renamed from: a */
    public synchronized String m4605a() {
        if (!TextUtils.isEmpty(this.f21689i)) {
            return this.f21689i;
        } else if (TextUtils.isEmpty(this.f21685e)) {
            return "hardcode_isp";
        } else {
            this.f21689i = C11184bb.m4750a(new String[]{this.f21685e, this.f21683c, this.f21684d, this.f21687g, this.f21686f}, "_");
            return this.f21689i;
        }
    }

    /* renamed from: b */
    public void m4590b(String str) {
        this.f21690j = str;
    }

    /* renamed from: a */
    public void m4601a(double d) {
        this.f21677a = d;
    }

    /* renamed from: a */
    public synchronized JSONObject m4603a() {
        JSONObject jSONObject;
        jSONObject = new JSONObject();
        jSONObject.put("net", this.f21679a);
        jSONObject.put("ttl", this.f21681b);
        jSONObject.put("pct", this.f21677a);
        jSONObject.put("ts", this.f21678a);
        jSONObject.put("city", this.f21684d);
        jSONObject.put("prv", this.f21683c);
        jSONObject.put("cty", this.f21687g);
        jSONObject.put("isp", this.f21685e);
        jSONObject.put("ip", this.f21686f);
        jSONObject.put("host", this.f21682b);
        jSONObject.put("xf", this.f21688h);
        JSONArray jSONArray = new JSONArray();
        Iterator<C11240cj> it = this.f21680a.iterator();
        while (it.hasNext()) {
            jSONArray.put(it.next().m4530a());
        }
        jSONObject.put("fbs", jSONArray);
        return jSONObject;
    }

    /* renamed from: a */
    public synchronized C11228cc m4592a(JSONObject jSONObject) {
        this.f21679a = jSONObject.optString("net");
        this.f21681b = jSONObject.getLong("ttl");
        this.f21677a = jSONObject.getDouble("pct");
        this.f21678a = jSONObject.getLong("ts");
        this.f21684d = jSONObject.optString("city");
        this.f21683c = jSONObject.optString("prv");
        this.f21687g = jSONObject.optString("cty");
        this.f21685e = jSONObject.optString("isp");
        this.f21686f = jSONObject.optString("ip");
        this.f21682b = jSONObject.optString("host");
        this.f21688h = jSONObject.optString("xf");
        JSONArray jSONArray = jSONObject.getJSONArray("fbs");
        for (int i = 0; i < jSONArray.length(); i++) {
            m4598a(new C11240cj().m4527a(jSONArray.getJSONObject(i)));
        }
        return this;
    }

    /* renamed from: c */
    private synchronized void m4586c(String str) {
        Iterator<C11240cj> it = this.f21680a.iterator();
        while (it.hasNext()) {
            if (TextUtils.equals(it.next().f21717a, str)) {
                it.remove();
            }
        }
    }
}
