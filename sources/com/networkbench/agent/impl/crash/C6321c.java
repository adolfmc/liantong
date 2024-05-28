package com.networkbench.agent.impl.crash;

import android.content.Context;
import android.os.Looper;
import com.networkbench.agent.impl.NBSAgent;
import com.networkbench.agent.impl.harvest.Harvest;
import com.networkbench.agent.impl.harvest.type.HarvestableArray;
import com.networkbench.agent.impl.p243c.p244a.C6255f;
import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.agent.impl.util.C6638h;
import com.networkbench.agent.impl.util.C6653u;
import com.networkbench.com.google.gson.JsonArray;
import com.networkbench.com.google.gson.JsonObject;
import com.networkbench.com.google.gson.JsonPrimitive;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.crash.c */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6321c extends HarvestableArray {

    /* renamed from: c */
    private int f15904c;

    /* renamed from: d */
    private Throwable f15905d;

    /* renamed from: e */
    private UUID f15906e;

    /* renamed from: f */
    private long f15907f;

    /* renamed from: h */
    private String f15909h;

    /* renamed from: n */
    private HashMap<String, JsonObject> f15914n;

    /* renamed from: o */
    private JsonArray f15915o;

    /* renamed from: p */
    private JsonArray f15916p;

    /* renamed from: q */
    private Map f15917q;

    /* renamed from: b */
    private static InterfaceC6393e f15903b = C6394f.m10150a();

    /* renamed from: a */
    public static String f15902a = "";

    /* renamed from: g */
    private Random f15908g = C6653u.m8757a();

    /* renamed from: i */
    private final Context f15910i = C6638h.m8963w().m9076K();

    /* renamed from: l */
    private String f15912l = null;

    /* renamed from: m */
    private JsonArray f15913m = null;

    /* renamed from: j */
    private String f15911j = C6255f.m10820a();

    /* renamed from: a */
    public String m10423a() {
        return this.f15911j;
    }

    /* renamed from: b */
    public HashMap<String, JsonObject> m10417b() {
        return this.f15914n;
    }

    public C6321c(Throwable th, long j, JsonArray jsonArray, JsonArray jsonArray2, Map<Thread, StackTraceElement[]> map, UUID uuid) {
        this.f15909h = m10420a(th);
        this.f15906e = uuid;
        this.f15907f = j;
        this.f15905d = th;
        this.f15904c = NBSAgent.getStackTraceLimit();
        if (this.f15904c == 0) {
            this.f15904c = 100;
        }
        this.f15914n = new HashMap<>();
        InterfaceC6393e interfaceC6393e = f15903b;
        interfaceC6393e.mo10117c("stackDepth is " + this.f15904c);
        this.f15915o = jsonArray;
        this.f15916p = jsonArray2;
        m10416b(map);
        this.f15917q = C6638h.m8963w().m9001h();
    }

    /* renamed from: a */
    private String m10420a(Throwable th) {
        if (th == null) {
            return "";
        }
        String m8717c = C6653u.m8717c(th);
        InterfaceC6393e interfaceC6393e = f15903b;
        interfaceC6393e.mo10122a("className:" + th.getClass().getName() + ", throwable message is " + m8717c);
        return m8717c;
    }

    /* renamed from: a */
    public JsonArray m10419a(Map<Thread, StackTraceElement[]> map) {
        JsonArray jsonArray = new JsonArray();
        if (C6653u.m8725b(this.f15905d)) {
            jsonArray.add(m10421a(Thread.currentThread().getId(), "com.facebook.react.JavaScript", C6653u.m8738a(this.f15905d).toString()));
        }
        if (Looper.myLooper() == Looper.getMainLooper()) {
            f15903b.mo10117c("user crash thread is UIThread");
            jsonArray.add(m10421a(Thread.currentThread().getId(), Thread.currentThread().getName(), m10422a(this.f15904c).toString()));
        } else {
            f15903b.mo10117c("user crash thread is not UIThread");
            Thread currentThread = Thread.currentThread();
            jsonArray.add(m10421a(currentThread.getId(), currentThread.getName(), m10422a(this.f15904c).toString()));
        }
        m10418a(map, jsonArray);
        InterfaceC6393e interfaceC6393e = f15903b;
        interfaceC6393e.mo10117c("crash crashStacktraces is" + jsonArray.toString());
        return jsonArray;
    }

    /* renamed from: a */
    private JsonArray m10421a(long j, String str, String str2) {
        JsonArray jsonArray = new JsonArray();
        jsonArray.add(new JsonPrimitive((Number) Long.valueOf(j)));
        jsonArray.add(new JsonPrimitive(str));
        if (str2 == null) {
            str2 = "";
        }
        jsonArray.add(new JsonPrimitive(str2));
        return jsonArray;
    }

    /* renamed from: a */
    public void m10418a(Map<Thread, StackTraceElement[]> map, JsonArray jsonArray) {
        if (map == null) {
            return;
        }
        ArrayList<Map.Entry> arrayList = new ArrayList(map.entrySet());
        Collections.sort(arrayList, new Comparator<Map.Entry<Thread, StackTraceElement[]>>() { // from class: com.networkbench.agent.impl.crash.c.1
            @Override // java.util.Comparator
            /* renamed from: a */
            public int compare(Map.Entry<Thread, StackTraceElement[]> entry, Map.Entry<Thread, StackTraceElement[]> entry2) {
                return (int) (entry.getKey().getId() - entry2.getKey().getId());
            }
        });
        for (Map.Entry entry : arrayList) {
            if (!((Thread) entry.getKey()).getName().equals(Thread.currentThread().getName())) {
                StackTraceElement[] stackTraceElementArr = (StackTraceElement[]) entry.getValue();
                if (stackTraceElementArr.length > 0) {
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < stackTraceElementArr.length; i++) {
                        if (i <= this.f15904c) {
                            sb.append("\tat " + stackTraceElementArr[i] + "\n");
                        }
                    }
                    jsonArray.add(m10421a(((Thread) entry.getKey()).getId(), ((Thread) entry.getKey()).getName(), sb.toString()));
                }
            }
        }
    }

    /* renamed from: e */
    private String m10413e() {
        Map<Thread, StackTraceElement[]> allStackTraces = Thread.getAllStackTraces();
        for (Thread thread : allStackTraces.keySet()) {
            if (thread.getName() == Looper.getMainLooper().getThread().getName()) {
                StackTraceElement[] stackTraceElementArr = allStackTraces.get(thread);
                StringBuilder sb = new StringBuilder();
                int length = stackTraceElementArr.length;
                int i = this.f15904c;
                if (i < length) {
                    length = i;
                }
                for (int i2 = 0; i2 < length; i2++) {
                    sb.append("\tat " + stackTraceElementArr[i2] + "\n");
                }
                return sb.toString();
            }
        }
        return null;
    }

    /* renamed from: c */
    public String m10415c() {
        return this.f15907f + "";
    }

    /* renamed from: d */
    public String m10414d() {
        return this.f15906e.toString();
    }

    /* renamed from: b */
    private void m10416b(Map<Thread, StackTraceElement[]> map) {
        if (this.f15912l == null) {
            this.f15912l = C6653u.m8751a(this.f15910i, true);
        }
        this.f15913m = m10419a(map);
    }

    @Override // com.networkbench.agent.impl.harvest.type.HarvestableArray, com.networkbench.agent.impl.harvest.type.BaseHarvestable, com.networkbench.agent.impl.harvest.type.Harvestable
    public JsonArray asJsonArray() {
        JsonArray jsonArray = new JsonArray();
        jsonArray.add(new JsonPrimitive((Number) Long.valueOf(this.f15907f)));
        jsonArray.add(new JsonPrimitive((Number) Long.valueOf(m10412f())));
        jsonArray.add(new JsonPrimitive((Number) Integer.valueOf(C6653u.m8703f())));
        jsonArray.add(new JsonPrimitive(this.f15906e.toString()));
        jsonArray.add(new JsonPrimitive(this.f15909h));
        JsonArray jsonArray2 = this.f15913m;
        if (jsonArray2 == null) {
            jsonArray2 = new JsonArray();
        }
        jsonArray.add(jsonArray2);
        jsonArray.add(new JsonPrimitive(Harvest.currentActivityName == null ? "" : Harvest.currentActivityName));
        jsonArray.add(this.f15915o);
        jsonArray.add(this.f15916p);
        if (this.f15912l == null) {
            this.f15912l = C6653u.m8751a(this.f15910i, true);
        }
        jsonArray.add(new JsonPrimitive(this.f15912l));
        jsonArray.add(new JsonPrimitive(""));
        jsonArray.add(null);
        if (C6638h.f17115o) {
            jsonArray.add(new JsonPrimitive("logcats :" + f15902a));
        } else {
            jsonArray.add(new JsonPrimitive(""));
            f15903b.mo10122a("logcats collect  is  not turned on !");
        }
        if (C6653u.m8729b(this.f15910i)) {
            if (Harvest.isUI_enabled()) {
                jsonArray.add(new JsonPrimitive((Number) C6638h.f17113m));
            } else {
                jsonArray.add(new JsonPrimitive((Number) 0));
            }
        } else {
            jsonArray.add(new JsonPrimitive((Number) 0));
        }
        jsonArray.add(new JsonPrimitive(this.f15911j));
        HashMap<String, JsonObject> hashMap = this.f15914n;
        if (hashMap != null) {
            jsonArray.add(C6653u.m8716c((Map<String, JsonObject>) hashMap));
        } else {
            jsonArray.add(new JsonObject());
        }
        if (C6638h.m8963w().m9065V()) {
            jsonArray.add(new JsonPrimitive(this.f15906e.toString()));
        }
        jsonArray.add(new JsonPrimitive(C6653u.m8701f(this.f15917q)));
        return jsonArray;
    }

    /* renamed from: f */
    private long m10412f() {
        long m9068S = C6638h.m8963w().m9068S();
        return m9068S <= 0 ? this.f15907f : m9068S;
    }

    /* renamed from: a */
    public StringBuilder m10422a(int i) {
        return C6653u.m8754a(i, this.f15905d);
    }

    /* renamed from: g */
    private String m10411g() {
        return (NBSAgent.getImpl() == null || NBSAgent.getImpl().m9148o() == null) ? "" : NBSAgent.getImpl().m9148o();
    }
}
