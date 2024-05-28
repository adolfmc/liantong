package com.networkbench.agent.impl.plugin.p272c;

import com.networkbench.com.google.gson.JsonArray;
import com.networkbench.com.google.gson.JsonPrimitive;
import java.util.ArrayList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.plugin.c.c */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6547c {

    /* renamed from: a */
    public List<C6549b> f16727a;

    public C6547c() {
        this.f16727a = new ArrayList();
        this.f16727a = new ArrayList();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.networkbench.agent.impl.plugin.c.c$b */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static class C6549b {

        /* renamed from: a */
        public String f16737a;

        /* renamed from: b */
        public String f16738b;

        /* renamed from: d */
        boolean f16740d;

        /* renamed from: e */
        public String f16741e = "";

        /* renamed from: c */
        public List<C6548a> f16739c = new ArrayList();

        public String toString() {
            return "TraceResult{ip='" + this.f16737a + "', hostName='" + this.f16738b + "', routers=" + this.f16739c + ", result=" + this.f16740d + '}';
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.networkbench.agent.impl.plugin.c.c$a */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static class C6548a {

        /* renamed from: a */
        String f16728a;

        /* renamed from: b */
        public String f16729b;

        /* renamed from: c */
        String f16730c;

        /* renamed from: d */
        String f16731d;

        /* renamed from: e */
        String f16732e;

        /* renamed from: f */
        String f16733f;

        /* renamed from: g */
        String f16734g;

        /* renamed from: h */
        String f16735h;

        /* renamed from: i */
        String f16736i;

        /* renamed from: a */
        public JsonArray m9452a() {
            JsonArray jsonArray = new JsonArray();
            jsonArray.add(new JsonPrimitive(m9451a(this.f16729b)));
            jsonArray.add(new JsonPrimitive((Number) Double.valueOf(m9450b())));
            jsonArray.add(new JsonPrimitive((Number) Integer.valueOf(m9448c(this.f16731d))));
            jsonArray.add(new JsonPrimitive((Number) Double.valueOf(m9449b(this.f16732e))));
            jsonArray.add(new JsonPrimitive((Number) Double.valueOf(m9449b(this.f16733f))));
            jsonArray.add(new JsonPrimitive((Number) Double.valueOf(m9449b(this.f16734g))));
            jsonArray.add(new JsonPrimitive((Number) Double.valueOf(m9449b(this.f16735h))));
            jsonArray.add(new JsonPrimitive((Number) Double.valueOf(m9449b(this.f16736i))));
            return jsonArray;
        }

        /* renamed from: a */
        private String m9451a(String str) {
            return (str == null || str.equals("")) ? "*" : str;
        }

        /* renamed from: b */
        private double m9450b() {
            String str = this.f16730c;
            if (str == null || str.equals("")) {
                this.f16730c = "0";
            }
            return Math.round(Double.valueOf(Double.valueOf(this.f16730c).doubleValue() / 100.0d).doubleValue() * 1000.0d) / 1000.0d;
        }

        /* renamed from: b */
        private double m9449b(String str) {
            return Math.round(Double.valueOf((str == null || str.equals("")) ? "0" : "0").doubleValue() * 10.0d) / 10.0d;
        }

        /* renamed from: c */
        private int m9448c(String str) {
            return Integer.valueOf((str == null || str.equals("")) ? "0" : "0").intValue();
        }

        public String toString() {
            return "Router{host='" + this.f16728a + "', ip='" + this.f16729b + "', loss='" + this.f16730c + "', snt='" + this.f16731d + "', last='" + this.f16732e + "', avg='" + this.f16733f + "', best='" + this.f16734g + "', worst='" + this.f16735h + "', stDev='" + this.f16736i + "'}";
        }
    }
}
