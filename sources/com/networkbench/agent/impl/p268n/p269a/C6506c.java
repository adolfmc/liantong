package com.networkbench.agent.impl.p268n.p269a;

import com.networkbench.agent.impl.harvest.type.HarvestableArray;
import com.networkbench.com.google.gson.JsonArray;
import com.networkbench.com.google.gson.JsonPrimitive;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.n.a.c */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6506c extends HarvestableArray {

    /* renamed from: a */
    protected int f16485a;

    /* renamed from: b */
    protected String f16486b;

    /* renamed from: c */
    protected String f16487c;

    /* renamed from: d */
    protected int f16488d;

    /* renamed from: e */
    protected int f16489e;

    /* renamed from: f */
    protected String f16490f;

    /* renamed from: g */
    protected String f16491g;

    /* renamed from: h */
    protected int f16492h;

    /* renamed from: i */
    protected int f16493i;

    @Override // com.networkbench.agent.impl.harvest.type.HarvestableArray, com.networkbench.agent.impl.harvest.type.BaseHarvestable, com.networkbench.agent.impl.harvest.type.Harvestable
    public JsonArray asJsonArray() {
        JsonArray jsonArray = new JsonArray();
        jsonArray.add(new JsonPrimitive((Number) Integer.valueOf(this.f16485a)));
        jsonArray.add(new JsonPrimitive(this.f16486b));
        jsonArray.add(new JsonPrimitive(this.f16487c));
        jsonArray.add(new JsonPrimitive((Number) Integer.valueOf(this.f16488d)));
        jsonArray.add(new JsonPrimitive((Number) Integer.valueOf(this.f16489e)));
        jsonArray.add(new JsonPrimitive(this.f16490f));
        jsonArray.add(new JsonPrimitive(this.f16491g));
        jsonArray.add(new JsonPrimitive((Number) Integer.valueOf(this.f16492h)));
        jsonArray.add(new JsonPrimitive((Number) Integer.valueOf(this.f16493i)));
        return jsonArray;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.networkbench.agent.impl.n.a.c$a */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static class C6507a {

        /* renamed from: b */
        private String f16495b = "";

        /* renamed from: c */
        private String f16496c = "";

        /* renamed from: d */
        private int f16497d = 0;

        /* renamed from: e */
        private int f16498e = 0;

        /* renamed from: f */
        private String f16499f = "";

        /* renamed from: g */
        private String f16500g = "";

        /* renamed from: h */
        private int f16501h = 0;

        /* renamed from: i */
        private int f16502i = 0;

        /* renamed from: a */
        private int f16494a = 0;

        /* renamed from: a */
        public C6507a m9694a(int i) {
            this.f16494a = i;
            return this;
        }

        /* renamed from: a */
        public C6507a m9692a(String str) {
            if (str == null) {
                str = "";
            }
            this.f16495b = str;
            return this;
        }

        /* renamed from: b */
        public C6507a m9689b(String str) {
            if (str == null) {
                str = "";
            }
            this.f16496c = str;
            return this;
        }

        /* renamed from: b */
        public C6507a m9691b(int i) {
            this.f16497d = i;
            return this;
        }

        /* renamed from: c */
        public C6507a m9688c(int i) {
            this.f16498e = i;
            return this;
        }

        /* renamed from: c */
        public C6507a m9686c(String str) {
            if (str == null) {
                str = "";
            }
            this.f16499f = str;
            return this;
        }

        /* renamed from: d */
        public C6507a m9683d(String str) {
            if (str == null) {
                str = "";
            }
            this.f16500g = str;
            return this;
        }

        /* renamed from: d */
        public C6507a m9685d(int i) {
            this.f16501h = i;
            return this;
        }

        /* renamed from: e */
        public C6507a m9682e(int i) {
            this.f16502i = i;
            return this;
        }

        /* renamed from: a */
        public C6506c m9695a() {
            return new C6506c(this);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public C6506c(C6507a c6507a) {
        this.f16486b = c6507a.f16495b;
        this.f16487c = c6507a.f16496c;
        this.f16488d = c6507a.f16497d;
        this.f16489e = c6507a.f16498e;
        this.f16490f = c6507a.f16499f;
        this.f16491g = c6507a.f16500g;
        this.f16492h = c6507a.f16501h;
        this.f16493i = c6507a.f16502i;
        this.f16485a = c6507a.f16494a;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("errorTypeName:" + this.f16486b + ", errorMessage:" + this.f16487c + ", lineOfError:" + this.f16488d + ", columnOfError:" + this.f16489e + ", filenameOfError:" + this.f16490f + ", stack:" + this.f16491g + ", jsErrorCount:" + this.f16492h + ", isFirstJsError:" + this.f16493i + ", offsetTimeStamp:" + this.f16485a);
        return sb.toString();
    }
}
