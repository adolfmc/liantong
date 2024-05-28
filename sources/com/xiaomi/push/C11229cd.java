package com.xiaomi.push;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.xiaomi.push.cd */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
class C11229cd {

    /* renamed from: a */
    private String f21691a;

    /* renamed from: a */
    private final ArrayList<C11228cc> f21692a = new ArrayList<>();

    public C11229cd(String str) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("the host is empty");
        }
        this.f21691a = str;
    }

    public C11229cd() {
    }

    /* renamed from: a */
    public synchronized void m4581a(C11228cc c11228cc) {
        int i = 0;
        while (true) {
            if (i >= this.f21692a.size()) {
                break;
            } else if (this.f21692a.get(i).m4599a(c11228cc)) {
                this.f21692a.set(i, c11228cc);
                break;
            } else {
                i++;
            }
        }
        if (i >= this.f21692a.size()) {
            this.f21692a.add(c11228cc);
        }
    }

    /* renamed from: a */
    public synchronized C11228cc m4585a() {
        for (int size = this.f21692a.size() - 1; size >= 0; size--) {
            C11228cc c11228cc = this.f21692a.get(size);
            if (c11228cc.m4602a()) {
                C11232cg.m4574a().m4564a(c11228cc.m4605a());
                return c11228cc;
            }
        }
        return null;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.f21691a);
        sb.append("\n");
        Iterator<C11228cc> it = this.f21692a.iterator();
        while (it.hasNext()) {
            sb.append(it.next());
        }
        return sb.toString();
    }

    /* renamed from: a */
    public ArrayList<C11228cc> m4583a() {
        return this.f21692a;
    }

    /* renamed from: a */
    public synchronized void m4579a(boolean z) {
        for (int size = this.f21692a.size() - 1; size >= 0; size--) {
            C11228cc c11228cc = this.f21692a.get(size);
            if (z) {
                if (c11228cc.m4587c()) {
                    this.f21692a.remove(size);
                }
            } else if (!c11228cc.mo4541b()) {
                this.f21692a.remove(size);
            }
        }
    }

    /* renamed from: a */
    public String m4584a() {
        return this.f21691a;
    }

    /* renamed from: a */
    public synchronized JSONObject m4582a() {
        JSONObject jSONObject;
        jSONObject = new JSONObject();
        jSONObject.put("host", this.f21691a);
        JSONArray jSONArray = new JSONArray();
        Iterator<C11228cc> it = this.f21692a.iterator();
        while (it.hasNext()) {
            jSONArray.put(it.next().m4603a());
        }
        jSONObject.put("fbs", jSONArray);
        return jSONObject;
    }

    /* renamed from: a */
    public synchronized C11229cd m4580a(JSONObject jSONObject) {
        this.f21691a = jSONObject.getString("host");
        JSONArray jSONArray = jSONObject.getJSONArray("fbs");
        for (int i = 0; i < jSONArray.length(); i++) {
            this.f21692a.add(new C11228cc(this.f21691a).m4592a(jSONArray.getJSONObject(i)));
        }
        return this;
    }
}
