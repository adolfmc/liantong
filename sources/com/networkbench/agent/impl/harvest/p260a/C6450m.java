package com.networkbench.agent.impl.harvest.p260a;

import android.text.TextUtils;
import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.agent.impl.util.C6634d;
import com.networkbench.agent.impl.util.C6638h;
import com.networkbench.agent.impl.util.C6653u;
import java.util.HashMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.harvest.a.m */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6450m {

    /* renamed from: f */
    private static C6450m f16294f;

    /* renamed from: l */
    private C6634d f16305l;

    /* renamed from: e */
    private final InterfaceC6393e f16299e = C6394f.m10150a();

    /* renamed from: a */
    public String f16295a = "";

    /* renamed from: b */
    public String f16296b = "";

    /* renamed from: c */
    public boolean f16297c = true;

    /* renamed from: d */
    public String f16298d = "";

    /* renamed from: g */
    private String f16300g = "";

    /* renamed from: h */
    private boolean f16301h = false;

    /* renamed from: i */
    private Map<String, Object> f16302i = new HashMap();

    /* renamed from: k */
    private long f16304k = System.currentTimeMillis();

    /* renamed from: j */
    private long f16303j = System.nanoTime();

    /* renamed from: a */
    public static C6450m m9963a() {
        if (f16294f == null) {
            synchronized (C6450m.class) {
                if (f16294f == null) {
                    f16294f = new C6450m();
                }
            }
        }
        return f16294f;
    }

    /* renamed from: a */
    public void m9962a(String str) {
        try {
            this.f16303j = System.nanoTime();
            this.f16304k = C6653u.m8684q(str);
        } catch (Throwable th) {
            InterfaceC6393e interfaceC6393e = this.f16299e;
            interfaceC6393e.mo10115e("error get dc time stamp, dc value is:" + str + ", error:" + th.getMessage());
        }
    }

    /* renamed from: b */
    public long m9960b() {
        return this.f16304k + ((System.nanoTime() - this.f16303j) / 1000000);
    }

    /* renamed from: c */
    public C6634d m9958c() {
        try {
        } catch (Throwable th) {
            C6638h.f17124y.mo10121a("generate encryptContent error:", th);
        }
        if (TextUtils.isEmpty(this.f16300g)) {
            return null;
        }
        this.f16305l = new C6634d(this.f16300g);
        C6638h.m8963w().m9053a(this.f16305l);
        return this.f16305l;
    }

    private C6450m() {
    }

    /* renamed from: a */
    public void m9961a(Map<String, Object> map) {
        this.f16302i = map;
        this.f16296b = m9959b("ak");
        this.f16295a = m9959b("sk");
        this.f16298d = m9959b("so_host");
        this.f16297c = m9957c("so_disabled").doubleValue() != 0.0d;
        try {
            this.f16300g = m9959b("tySecretKey");
        } catch (Throwable unused) {
        }
        this.f16301h = m9957c("vd").doubleValue() == 1.0d;
        C6638h.m8963w().f17126B = this.f16301h;
        C6638h.m8963w().m9015d(this.f16300g);
        if (TextUtils.isEmpty(this.f16300g)) {
            return;
        }
        m9958c();
    }

    /* renamed from: b */
    private String m9959b(String str) {
        Object m9956d = m9956d(str);
        return m9956d != null ? m9956d.toString() : "";
    }

    /* renamed from: c */
    private Double m9957c(String str) {
        try {
            Object m9956d = m9956d(str);
            if (m9956d != null) {
                return (Double) m9956d;
            }
        } catch (Throwable th) {
            this.f16299e.mo10121a("parseKeyDouble error!", th);
        }
        return Double.valueOf(0.0d);
    }

    /* renamed from: d */
    private Object m9956d(String str) {
        if (this.f16302i.containsKey(str)) {
            return this.f16302i.get(str);
        }
        return null;
    }
}
