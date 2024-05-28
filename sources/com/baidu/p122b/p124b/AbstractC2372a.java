package com.baidu.p122b.p124b;

import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;
import com.baidu.p122b.C2426h;
import com.baidu.p122b.p131e.C2419a;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import java.util.Comparator;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.b.b.a */
/* loaded from: E:\10201592_dexfile_execute.dex */
public abstract class AbstractC2372a {

    /* renamed from: c */
    public static Comparator<AbstractC2372a> f4127c = new C2378b();

    /* renamed from: a */
    protected C2373a f4128a;

    /* renamed from: b */
    protected C2419a.C2420a f4129b;

    /* renamed from: d */
    private final String f4130d;

    /* renamed from: e */
    private long f4131e;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.b.b.a$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class C2373a {

        /* renamed from: a */
        public Context f4132a;

        /* renamed from: b */
        public C2419a f4133b;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    @NBSInstrumented
    /* renamed from: com.baidu.b.b.a$b */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static abstract class AbstractC2374b {

        /* renamed from: a */
        private C2419a.C2420a f4134a;

        /* renamed from: b */
        private String f4135b;

        /* renamed from: c */
        private String f4136c;

        /* renamed from: d */
        private boolean f4137d = true;

        public AbstractC2374b(C2419a.C2420a c2420a, String str) {
            this.f4134a = c2420a;
            this.f4135b = str;
            this.f4136c = "target-pkg-" + Base64.encodeToString(str.getBytes(), 3);
        }

        /* renamed from: a */
        public abstract void mo20333a(JSONObject jSONObject);

        /* renamed from: a */
        public void m20350a(boolean z) {
            this.f4137d = z;
        }

        /* renamed from: a */
        public boolean m20351a() {
            String m20195a = this.f4134a.m20195a(this.f4136c, true);
            if (!TextUtils.isEmpty(m20195a)) {
                try {
                    mo20333a(new JSONObject(m20195a));
                    m20350a(false);
                    return true;
                } catch (Exception unused) {
                }
            }
            return false;
        }

        /* renamed from: b */
        public abstract void mo20331b(JSONObject jSONObject);

        /* renamed from: b */
        public boolean m20349b() {
            if (this.f4137d) {
                try {
                    JSONObject jSONObject = new JSONObject();
                    mo20331b(jSONObject);
                    this.f4134a.m20196a(this.f4136c, !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject), true);
                    m20350a(false);
                    return true;
                } catch (Exception unused) {
                }
            }
            return false;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.b.b.a$c */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class C2375c {
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.b.b.a$d */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class C2376d {

        /* renamed from: a */
        public boolean f4138a;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.b.b.a$e */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class C2377e {

        /* renamed from: a */
        public C2426h.C2427a f4139a;

        /* renamed from: b */
        public int f4140b;

        /* renamed from: c */
        public Exception f4141c;

        public C2377e(int i, C2426h.C2427a c2427a, Exception exc) {
            this.f4140b = i;
            this.f4139a = c2427a;
            this.f4141c = exc;
        }

        /* renamed from: a */
        public static C2377e m20347a(int i) {
            return new C2377e(i, null, null);
        }

        /* renamed from: a */
        public static C2377e m20346a(C2426h.C2427a c2427a) {
            return new C2377e(0, c2427a, null);
        }

        /* renamed from: b */
        public static C2377e m20345b() {
            return new C2377e(-1, null, null);
        }

        /* renamed from: a */
        public boolean m20348a() {
            return this.f4140b == 0;
        }
    }

    public AbstractC2372a(String str, long j) {
        this.f4130d = str;
        this.f4131e = j;
    }

    /* renamed from: a */
    public abstract C2377e mo20321a(String str, C2376d c2376d);

    /* renamed from: a */
    public String m20354a() {
        return this.f4130d;
    }

    /* renamed from: a */
    public final void m20353a(C2373a c2373a) {
        this.f4128a = c2373a;
        this.f4129b = c2373a.f4133b.m20201b().m20197a("cs");
    }

    /* renamed from: a */
    public abstract void mo20326a(C2375c c2375c);

    /* renamed from: b */
    public long m20352b() {
        return this.f4131e;
    }
}
