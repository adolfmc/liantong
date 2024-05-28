package com.networkbench.agent.impl.crash.p249a;

import com.networkbench.agent.impl.NBSAgent;
import com.networkbench.agent.impl.crash.C6328g;
import com.networkbench.agent.impl.crash.NBSErrorEventType;
import com.networkbench.agent.impl.harvest.type.HarvestableObject;
import com.networkbench.agent.impl.util.C6638h;
import com.networkbench.agent.impl.util.C6653u;
import com.networkbench.com.google.gson.Gson;
import com.networkbench.com.google.gson.JsonArray;
import com.networkbench.com.google.gson.JsonElement;
import com.networkbench.com.google.gson.JsonObject;
import com.networkbench.com.google.gson.JsonPrimitive;
import java.io.PrintStream;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.crash.a.d */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6314d extends HarvestableObject {

    /* renamed from: a */
    static final int f15857a = 2;

    /* renamed from: b */
    static int f15858b = 1024;

    /* renamed from: c */
    static int f15859c = 131072;

    /* renamed from: d */
    static final String f15860d = "metaData的大小大于最大限制128K";

    /* renamed from: e */
    static int f15861e = 100;

    /* renamed from: f */
    public int f15862f;

    /* renamed from: g */
    private Map f15863g;

    /* renamed from: h */
    private String f15864h;

    /* renamed from: i */
    private JsonArray f15865i;

    /* renamed from: j */
    private String f15866j;

    /* renamed from: l */
    private JsonArray f15867l;

    /* renamed from: m */
    private int f15868m;

    /* renamed from: n */
    private long f15869n;

    /* renamed from: o */
    private String f15870o;

    /* renamed from: p */
    private String f15871p;

    /* renamed from: q */
    private String f15872q;

    /* renamed from: r */
    private long f15873r;

    /* renamed from: s */
    private int f15874s;

    /* renamed from: a */
    public JsonArray m10466a() {
        return this.f15865i;
    }

    /* renamed from: b */
    public long m10464b() {
        return this.f15873r;
    }

    private C6314d(String str, JsonArray jsonArray, String str2, int i, int i2, int i3, String str3, Map map) {
        this.f15867l = new JsonArray();
        this.f15874s = 2;
        this.f15864h = str;
        this.f15865i = jsonArray;
        this.f15866j = str2;
        this.f15873r = System.currentTimeMillis();
        this.f15868m = i;
        this.f15874s = i2;
        this.f15870o = NBSAgent.getBuildId();
        this.f15871p = C6653u.m8751a(C6638h.m8963w().m9076K(), false);
        m10463c();
        this.f15862f = i3;
        this.f15872q = str3;
        this.f15863g = map;
    }

    /* renamed from: c */
    public void m10463c() {
        this.f15867l = C6653u.m8722c();
    }

    /* renamed from: a */
    static JsonElement m10465a(Map<String, Object> map) {
        return new Gson().toJsonTree(map);
    }

    @Override // com.networkbench.agent.impl.harvest.type.HarvestableObject, com.networkbench.agent.impl.harvest.type.BaseHarvestable, com.networkbench.agent.impl.harvest.type.Harvestable
    public JsonObject asJsonObject() {
        JsonObject jsonObject = new JsonObject();
        long j = this.f15869n;
        if (j <= 0) {
            jsonObject.add("timestamp", new JsonPrimitive((Number) Long.valueOf(TimeUnit.SECONDS.convert(this.f15873r, TimeUnit.MILLISECONDS))));
        } else {
            jsonObject.add("timestamp", new JsonPrimitive((Number) Long.valueOf(j)));
        }
        jsonObject.add("type", new JsonPrimitive((Number) Integer.valueOf(this.f15868m)));
        jsonObject.add("platform", new JsonPrimitive((Number) Integer.valueOf(this.f15874s)));
        jsonObject.add("msg", new JsonPrimitive(this.f15864h));
        jsonObject.add("stack", this.f15865i);
        jsonObject.add("image", new JsonArray());
        jsonObject.add("bid", new JsonPrimitive(this.f15870o));
        if (C6638h.m8963w().m9065V()) {
            jsonObject.add("obv", new JsonPrimitive(""));
        }
        jsonObject.add("meta", new JsonPrimitive(this.f15866j));
        if (C6638h.m8963w().m9065V()) {
            JsonArray jsonArray = this.f15867l;
            if (jsonArray == null) {
                jsonArray = new JsonArray();
            }
            jsonObject.add("trail", jsonArray);
            jsonObject.add("addit", new JsonPrimitive(this.f15871p));
            jsonObject.add("sruuid", new JsonPrimitive(this.f15872q));
        }
        jsonObject.add("custom", new JsonPrimitive(C6653u.m8701f(this.f15863g)));
        return jsonObject;
    }

    /* renamed from: d */
    public String m10462d() {
        return String.valueOf(this.f15862f) + String.valueOf(this.f15873r);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.networkbench.agent.impl.crash.a.d$a */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static class C6316a {

        /* renamed from: h */
        private int f15882h;

        /* renamed from: i */
        private String f15883i;

        /* renamed from: b */
        private String f15876b = "";

        /* renamed from: c */
        private JsonArray f15877c = new JsonArray();

        /* renamed from: d */
        private String f15878d = "";

        /* renamed from: e */
        private int f15879e = 0;

        /* renamed from: f */
        private long f15880f = Thread.currentThread().getId();

        /* renamed from: g */
        private String f15881g = Thread.currentThread().getName();

        /* renamed from: a */
        int f15875a = C6653u.m8757a().nextInt(10000);

        /* renamed from: a */
        public C6316a m10457a(Throwable th) {
            if (th == null) {
                return this;
            }
            try {
                JsonArray jsonArray = new JsonArray();
                jsonArray.add(new JsonPrimitive((Number) Long.valueOf(this.f15880f)));
                jsonArray.add(new JsonPrimitive(this.f15881g == null ? "" : this.f15881g));
                jsonArray.add(new JsonPrimitive(C6653u.m8754a(C6314d.f15861e, th).toString()));
                this.f15877c.add(jsonArray);
            } catch (Throwable th2) {
                PrintStream printStream = System.out;
                printStream.println("error in setStack" + th2.getMessage());
            }
            return this;
        }

        /* renamed from: a */
        public C6316a m10460a(int i) {
            this.f15882h = i;
            return this;
        }

        /* renamed from: a */
        public C6316a m10458a(String str, String str2) {
            if (str == null) {
                return this;
            }
            try {
                JsonArray jsonArray = new JsonArray();
                jsonArray.add(new JsonPrimitive((Number) 0));
                if (str2 == null) {
                    str2 = "";
                }
                jsonArray.add(new JsonPrimitive(str2));
                jsonArray.add(new JsonPrimitive(str));
                this.f15877c.add(jsonArray);
            } catch (Throwable th) {
                PrintStream printStream = System.out;
                printStream.println("error in setStack" + th.getMessage());
            }
            return this;
        }

        /* renamed from: a */
        public C6316a m10459a(String str) {
            if (str == null) {
                return this;
            }
            if (str.length() > C6314d.f15858b) {
                this.f15876b = str.substring(0, C6314d.f15858b);
            } else {
                this.f15876b = str;
            }
            return this;
        }

        /* renamed from: a */
        public C6316a m10456a(Map<String, Object> map) {
            if (map == null) {
                return this;
            }
            this.f15878d = C6314d.m10465a(map).toString();
            if (this.f15878d.length() > C6314d.f15859c) {
                this.f15878d = m10453c().toString();
            }
            return this;
        }

        /* renamed from: b */
        public C6316a m10454b(int i) {
            this.f15879e = i;
            return this;
        }

        /* renamed from: c */
        private JsonObject m10453c() {
            JsonObject jsonObject = new JsonObject();
            jsonObject.add("error", new JsonPrimitive("metaData的大小大于最大限制128K"));
            return jsonObject;
        }

        /* renamed from: a */
        public C6314d m10461a() {
            return new C6314d(this.f15876b, this.f15877c, this.f15878d, this.f15879e, this.f15882h, this.f15875a, this.f15883i, C6638h.m8963w().m9001h());
        }

        /* renamed from: b */
        public C6316a m10455b() {
            this.f15883i = new UUID(C6653u.m8757a().nextLong(), C6653u.m8757a().nextLong()).toString();
            C6328g.m10394a().m10391a(NBSErrorEventType.customError, this.f15883i);
            return this;
        }
    }
}
