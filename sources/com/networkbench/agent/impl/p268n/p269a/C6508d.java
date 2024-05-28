package com.networkbench.agent.impl.p268n.p269a;

import com.networkbench.agent.impl.harvest.type.HarvestableArray;
import com.networkbench.com.google.gson.JsonArray;
import com.networkbench.com.google.gson.JsonPrimitive;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.n.a.d */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6508d extends HarvestableArray {

    /* renamed from: A */
    private int f16503A;

    /* renamed from: B */
    private int f16504B;

    /* renamed from: C */
    private String f16505C;

    /* renamed from: D */
    private String f16506D;

    /* renamed from: E */
    private int f16507E;

    /* renamed from: F */
    private C6505b f16508F;

    /* renamed from: G */
    private C6505b f16509G;

    /* renamed from: a */
    private int f16510a;

    /* renamed from: b */
    private String f16511b;

    /* renamed from: c */
    private String f16512c;

    /* renamed from: d */
    private int f16513d;

    /* renamed from: e */
    private int f16514e;

    /* renamed from: f */
    private int f16515f;

    /* renamed from: g */
    private int f16516g;

    /* renamed from: h */
    private int f16517h;

    /* renamed from: i */
    private int f16518i;

    /* renamed from: j */
    private int f16519j;

    /* renamed from: l */
    private int f16520l;

    /* renamed from: m */
    private int f16521m;

    /* renamed from: n */
    private int f16522n;

    /* renamed from: o */
    private int f16523o;

    /* renamed from: p */
    private int f16524p;

    /* renamed from: q */
    private int f16525q;

    /* renamed from: r */
    private int f16526r;

    /* renamed from: s */
    private int f16527s;

    /* renamed from: t */
    private int f16528t;

    /* renamed from: u */
    private int f16529u;

    /* renamed from: v */
    private int f16530v;

    /* renamed from: w */
    private int f16531w;

    /* renamed from: x */
    private int f16532x;

    /* renamed from: y */
    private int f16533y;

    /* renamed from: z */
    private int f16534z;

    private C6508d(C6510a c6510a) {
        this.f16508F = new C6505b();
        this.f16509G = new C6505b();
        this.f16511b = c6510a.f16540b;
        this.f16512c = c6510a.f16541c;
        this.f16505C = c6510a.f16536B;
        this.f16506D = c6510a.f16537C;
        this.f16507E = c6510a.f16538D;
        this.f16513d = c6510a.f16542d;
        this.f16514e = c6510a.f16543e;
        this.f16515f = c6510a.f16544f;
        this.f16516g = c6510a.f16545g;
        this.f16517h = c6510a.f16546h;
        this.f16518i = c6510a.f16547i;
        this.f16519j = c6510a.f16548j;
        this.f16520l = c6510a.f16549k;
        this.f16521m = c6510a.f16550l;
        this.f16522n = c6510a.f16551m;
        this.f16523o = c6510a.f16552n;
        this.f16524p = c6510a.f16553o;
        this.f16525q = c6510a.f16554p;
        this.f16526r = c6510a.f16555q;
        this.f16527s = c6510a.f16556r;
        this.f16528t = c6510a.f16557s;
        this.f16529u = c6510a.f16558t;
        this.f16530v = c6510a.f16559u;
        this.f16531w = c6510a.f16560v;
        this.f16532x = c6510a.f16561w;
        this.f16533y = c6510a.f16562x;
        this.f16534z = c6510a.f16563y;
        this.f16503A = c6510a.f16564z;
        this.f16504B = c6510a.f16535A;
        this.f16510a = c6510a.f16539a;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.networkbench.agent.impl.n.a.d$a */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static class C6510a {

        /* renamed from: A */
        private int f16535A;

        /* renamed from: D */
        private int f16538D;

        /* renamed from: a */
        private int f16539a;

        /* renamed from: d */
        private int f16542d;

        /* renamed from: e */
        private int f16543e;

        /* renamed from: f */
        private int f16544f;

        /* renamed from: g */
        private int f16545g;

        /* renamed from: h */
        private int f16546h;

        /* renamed from: i */
        private int f16547i;

        /* renamed from: j */
        private int f16548j;

        /* renamed from: k */
        private int f16549k;

        /* renamed from: l */
        private int f16550l;

        /* renamed from: m */
        private int f16551m;

        /* renamed from: n */
        private int f16552n;

        /* renamed from: o */
        private int f16553o;

        /* renamed from: p */
        private int f16554p;

        /* renamed from: q */
        private int f16555q;

        /* renamed from: r */
        private int f16556r;

        /* renamed from: s */
        private int f16557s;

        /* renamed from: t */
        private int f16558t;

        /* renamed from: u */
        private int f16559u;

        /* renamed from: v */
        private int f16560v;

        /* renamed from: w */
        private int f16561w;

        /* renamed from: x */
        private int f16562x;

        /* renamed from: y */
        private int f16563y;

        /* renamed from: z */
        private int f16564z;

        /* renamed from: b */
        private String f16540b = "";

        /* renamed from: c */
        private String f16541c = "";

        /* renamed from: B */
        private String f16536B = "";

        /* renamed from: C */
        private String f16537C = "";

        /* renamed from: a */
        public C6510a m9669a(int i) {
            this.f16539a = i;
            return this;
        }

        /* renamed from: a */
        public C6510a m9667a(String str) {
            if (str == null) {
                str = "";
            }
            this.f16540b = str;
            return this;
        }

        /* renamed from: b */
        public C6510a m9664b(String str) {
            if (str == null) {
                str = "";
            }
            this.f16541c = str;
            return this;
        }

        /* renamed from: b */
        public C6510a m9666b(int i) {
            this.f16542d = i;
            return this;
        }

        /* renamed from: c */
        public C6510a m9663c(int i) {
            this.f16543e = i;
            return this;
        }

        /* renamed from: d */
        public C6510a m9660d(int i) {
            this.f16544f = i;
            return this;
        }

        /* renamed from: e */
        public C6510a m9657e(int i) {
            this.f16545g = i;
            return this;
        }

        /* renamed from: f */
        public C6510a m9655f(int i) {
            this.f16546h = i;
            return this;
        }

        /* renamed from: g */
        public C6510a m9653g(int i) {
            this.f16547i = i;
            return this;
        }

        /* renamed from: h */
        public C6510a m9651h(int i) {
            this.f16548j = i;
            return this;
        }

        /* renamed from: i */
        public C6510a m9649i(int i) {
            this.f16549k = i;
            return this;
        }

        /* renamed from: j */
        public C6510a m9647j(int i) {
            this.f16550l = i;
            return this;
        }

        /* renamed from: k */
        public C6510a m9645k(int i) {
            this.f16551m = i;
            return this;
        }

        /* renamed from: l */
        public C6510a m9643l(int i) {
            this.f16552n = i;
            return this;
        }

        /* renamed from: m */
        public C6510a m9641m(int i) {
            this.f16553o = i;
            return this;
        }

        /* renamed from: n */
        public C6510a m9639n(int i) {
            this.f16554p = i;
            return this;
        }

        /* renamed from: o */
        public C6510a m9637o(int i) {
            this.f16555q = i;
            return this;
        }

        /* renamed from: p */
        public C6510a m9635p(int i) {
            this.f16556r = i;
            return this;
        }

        /* renamed from: q */
        public C6510a m9633q(int i) {
            this.f16557s = i;
            return this;
        }

        /* renamed from: r */
        public C6510a m9631r(int i) {
            this.f16558t = i;
            return this;
        }

        /* renamed from: s */
        public C6510a m9629s(int i) {
            this.f16559u = i;
            return this;
        }

        /* renamed from: t */
        public C6510a m9627t(int i) {
            if (i == 0) {
                i = -1;
            }
            this.f16560v = i;
            return this;
        }

        /* renamed from: u */
        public C6510a m9625u(int i) {
            if (i == 0) {
                i = -1;
            }
            this.f16561w = i;
            return this;
        }

        /* renamed from: v */
        public C6510a m9623v(int i) {
            this.f16562x = i;
            return this;
        }

        /* renamed from: w */
        public C6510a m9621w(int i) {
            this.f16563y = i;
            return this;
        }

        /* renamed from: x */
        public C6510a m9619x(int i) {
            this.f16564z = i;
            return this;
        }

        /* renamed from: y */
        public C6510a m9617y(int i) {
            this.f16535A = i;
            return this;
        }

        /* renamed from: c */
        public C6510a m9661c(String str) {
            if (str == null) {
                str = "";
            }
            this.f16536B = str;
            return this;
        }

        /* renamed from: d */
        public C6510a m9658d(String str) {
            if (str == null) {
                str = "";
            }
            this.f16537C = str;
            return this;
        }

        /* renamed from: z */
        public C6510a m9615z(int i) {
            this.f16538D = i;
            return this;
        }

        /* renamed from: a */
        public C6508d m9670a() {
            return new C6508d(this);
        }
    }

    /* renamed from: a */
    public C6505b m9676a() {
        return this.f16508F;
    }

    /* renamed from: b */
    public C6505b m9675b() {
        return this.f16509G;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("startTimeInSec:" + this.f16510a + "url:" + this.f16511b + ", pvId:" + this.f16512c + ", redirectStart:" + this.f16513d + ", redirectEnd:" + this.f16514e + ", fetchStart:" + this.f16515f + ", domainLookupStart:" + this.f16516g + ", domainLookupEnd:" + this.f16517h + ", connectStart:" + this.f16518i + ", connectEnd:" + this.f16519j + ", secureConnectStart:" + this.f16520l + ", requestStart:" + this.f16521m + ", responseStart:" + this.f16522n + ", responseEnd:" + this.f16523o + ", domLoading:" + this.f16524p + ", domInteractive:" + this.f16525q + ", domContentLoadedEventStart:" + this.f16526r + ", domContentLoadedEventEnd:" + this.f16527s + ", domComplete:" + this.f16528t + ", loadEventStart:" + this.f16529u + ", loadEventEnd:" + this.f16530v + ", firstPaintTime:" + this.f16531w + ", firstScreenTime:" + this.f16532x + ", jsErrorCount:" + this.f16533y + ", httpStatusCode:" + this.f16534z + ", network_error_code:" + this.f16503A + ", byteRecv:" + this.f16504B + ", appData:" + this.f16505C + ", slowIndicator:" + this.f16507E + ", cdnVendorName:" + this.f16506D + ", pageResourceDataArray:" + this.f16508F.toString() + ", pageJsErrorsDataArray:" + this.f16509G.toString());
        return sb.toString();
    }

    @Override // com.networkbench.agent.impl.harvest.type.HarvestableArray, com.networkbench.agent.impl.harvest.type.BaseHarvestable, com.networkbench.agent.impl.harvest.type.Harvestable
    public JsonArray asJsonArray() {
        JsonArray jsonArray = new JsonArray();
        jsonArray.add(new JsonPrimitive((Number) Integer.valueOf(this.f16510a)));
        jsonArray.add(new JsonPrimitive(this.f16511b));
        jsonArray.add(new JsonPrimitive(this.f16512c));
        jsonArray.add(new JsonPrimitive((Number) Integer.valueOf(this.f16513d)));
        jsonArray.add(new JsonPrimitive((Number) Integer.valueOf(this.f16514e)));
        jsonArray.add(new JsonPrimitive((Number) Integer.valueOf(this.f16515f)));
        jsonArray.add(new JsonPrimitive((Number) Integer.valueOf(this.f16516g)));
        jsonArray.add(new JsonPrimitive((Number) Integer.valueOf(this.f16517h)));
        jsonArray.add(new JsonPrimitive((Number) Integer.valueOf(this.f16518i)));
        jsonArray.add(new JsonPrimitive((Number) Integer.valueOf(this.f16519j)));
        jsonArray.add(new JsonPrimitive((Number) Integer.valueOf(this.f16520l)));
        jsonArray.add(new JsonPrimitive((Number) Integer.valueOf(this.f16521m)));
        jsonArray.add(new JsonPrimitive((Number) Integer.valueOf(this.f16522n)));
        jsonArray.add(new JsonPrimitive((Number) Integer.valueOf(this.f16523o)));
        jsonArray.add(new JsonPrimitive((Number) Integer.valueOf(this.f16524p)));
        jsonArray.add(new JsonPrimitive((Number) Integer.valueOf(this.f16525q)));
        jsonArray.add(new JsonPrimitive((Number) Integer.valueOf(this.f16526r)));
        jsonArray.add(new JsonPrimitive((Number) Integer.valueOf(this.f16527s)));
        jsonArray.add(new JsonPrimitive((Number) Integer.valueOf(this.f16528t)));
        jsonArray.add(new JsonPrimitive((Number) Integer.valueOf(this.f16529u)));
        jsonArray.add(new JsonPrimitive((Number) Integer.valueOf(this.f16530v)));
        jsonArray.add(new JsonPrimitive((Number) Integer.valueOf(this.f16531w)));
        jsonArray.add(new JsonPrimitive((Number) Integer.valueOf(this.f16532x)));
        jsonArray.add(new JsonPrimitive((Number) Integer.valueOf(this.f16533y)));
        jsonArray.add(new JsonPrimitive((Number) Integer.valueOf(this.f16534z)));
        jsonArray.add(new JsonPrimitive((Number) Integer.valueOf(this.f16503A)));
        jsonArray.add(new JsonPrimitive((Number) Integer.valueOf(this.f16504B)));
        jsonArray.add(new JsonPrimitive(this.f16505C));
        jsonArray.add(new JsonPrimitive(this.f16506D));
        jsonArray.add(new JsonPrimitive((Number) Integer.valueOf(this.f16507E)));
        jsonArray.add(this.f16508F.asJsonArray());
        jsonArray.add(this.f16509G.asJsonArray());
        return jsonArray;
    }
}
