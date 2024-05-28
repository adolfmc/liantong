package com.networkbench.agent.impl.p255g.p257b;

import android.text.TextUtils;
import com.networkbench.agent.impl.p239a.p240a.C6226b;
import com.networkbench.agent.impl.p255g.C6409b;
import com.networkbench.agent.impl.p255g.EnumC6421f;
import com.networkbench.agent.impl.util.C6638h;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.g.b.a */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6410a extends C6409b {

    /* renamed from: b */
    public long f16195b;

    /* renamed from: c */
    public String f16196c;

    /* renamed from: d */
    private C6226b f16197d;

    /* renamed from: c */
    public C6226b m10061c() {
        return this.f16197d;
    }

    /* renamed from: a */
    public void m10063a(C6226b c6226b) {
        this.f16197d = c6226b;
    }

    public C6410a(C6226b c6226b) {
        super(EnumC6421f.Network);
        this.f16197d = c6226b;
        m10068a(c6226b.m10989A());
        m10062a(c6226b.m10987a());
    }

    /* renamed from: d */
    public String m10060d() {
        String m10953r = this.f16197d.m10953r();
        if (TextUtils.isEmpty(this.f16197d.m10974d())) {
            return m10953r;
        }
        return m10953r + "?" + this.f16197d.m10974d();
    }

    /* renamed from: a */
    public void m10062a(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            if (!TextUtils.isEmpty(this.f16197d.f15374a)) {
                jSONObject = new JSONObject(this.f16197d.f15374a);
            }
            jSONObject.put(C6638h.m8963w().f17178d, str);
            this.f16197d.f15374a = jSONObject.toString();
        } catch (Throwable unused) {
        }
    }

    /* renamed from: a */
    public void m10064a(int i) {
        this.f16197d.m10966g(i);
    }

    @Override // com.networkbench.agent.impl.p255g.C6409b
    public String toString() {
        return "HttpActionMeasurement{" + this.f16197d.toString() + '}';
    }
}
