package com.xiaomi.push;

import java.util.Iterator;
import java.util.LinkedList;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.xiaomi.push.cj */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
class C11240cj implements Comparable<C11240cj> {

    /* renamed from: a */
    protected int f21715a;

    /* renamed from: a */
    private long f21716a;

    /* renamed from: a */
    String f21717a;

    /* renamed from: a */
    private final LinkedList<C11227cb> f21718a;

    public C11240cj() {
        this(null, 0);
    }

    public C11240cj(String str) {
        this(str, 0);
    }

    public C11240cj(String str, int i) {
        this.f21718a = new LinkedList<>();
        this.f21716a = 0L;
        this.f21717a = str;
        this.f21715a = i;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public synchronized void m4529a(C11227cb c11227cb) {
        if (c11227cb != null) {
            this.f21718a.add(c11227cb);
            int m4608a = c11227cb.m4608a();
            if (m4608a > 0) {
                this.f21715a += c11227cb.m4608a();
            } else {
                int i = 0;
                for (int size = this.f21718a.size() - 1; size >= 0 && this.f21718a.get(size).m4608a() < 0; size--) {
                    i++;
                }
                this.f21715a += m4608a * i;
            }
            if (this.f21718a.size() > 30) {
                this.f21715a -= this.f21718a.remove().m4608a();
            }
        }
    }

    public String toString() {
        return this.f21717a + ":" + this.f21715a;
    }

    @Override // java.lang.Comparable
    /* renamed from: a */
    public int compareTo(C11240cj c11240cj) {
        if (c11240cj == null) {
            return 1;
        }
        return c11240cj.f21715a - this.f21715a;
    }

    /* renamed from: a */
    public synchronized JSONObject m4530a() {
        JSONObject jSONObject;
        jSONObject = new JSONObject();
        jSONObject.put("tt", this.f21716a);
        jSONObject.put("wt", this.f21715a);
        jSONObject.put("host", this.f21717a);
        JSONArray jSONArray = new JSONArray();
        Iterator<C11227cb> it = this.f21718a.iterator();
        while (it.hasNext()) {
            jSONArray.put(it.next().m4607a());
        }
        jSONObject.put("ah", jSONArray);
        return jSONObject;
    }

    /* renamed from: a */
    public synchronized C11240cj m4527a(JSONObject jSONObject) {
        this.f21716a = jSONObject.getLong("tt");
        this.f21715a = jSONObject.getInt("wt");
        this.f21717a = jSONObject.getString("host");
        JSONArray jSONArray = jSONObject.getJSONArray("ah");
        for (int i = 0; i < jSONArray.length(); i++) {
            this.f21718a.add(new C11227cb().m4606a(jSONArray.getJSONObject(i)));
        }
        return this;
    }
}
