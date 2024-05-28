package com.networkbench.agent.impl.crash.p249a;

import com.networkbench.agent.impl.NBSAgent;
import com.networkbench.agent.impl.harvest.type.HarvestableObject;
import com.networkbench.agent.impl.p254f.C6396h;
import com.networkbench.com.google.gson.JsonArray;
import com.networkbench.com.google.gson.JsonObject;
import com.networkbench.com.google.gson.JsonPrimitive;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.crash.a.a */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6311a extends HarvestableObject {

    /* renamed from: b */
    static int f15843b = 100;

    /* renamed from: f */
    static C6311a f15844f = null;

    /* renamed from: g */
    private static final int f15845g = 60;

    /* renamed from: h */
    private static Object f15846h = new Object();

    /* renamed from: c */
    public boolean f15848c = false;

    /* renamed from: d */
    Queue<C6314d> f15849d = new ConcurrentLinkedQueue();

    /* renamed from: e */
    Queue<C6314d> f15850e = new ConcurrentLinkedQueue();

    /* renamed from: a */
    public int f15847a = 60;

    /* renamed from: a */
    public Queue<C6314d> m10488a() {
        return this.f15849d;
    }

    /* renamed from: b */
    public Queue<C6314d> m10484b() {
        return this.f15850e;
    }

    /* renamed from: c */
    public static C6311a m10483c() {
        if (f15844f == null) {
            synchronized (f15846h) {
                if (f15844f == null) {
                    f15844f = new C6311a();
                }
            }
        }
        return f15844f;
    }

    private C6311a() {
    }

    /* renamed from: a */
    public void m10487a(int i) {
        this.f15847a = i;
    }

    /* renamed from: a */
    public synchronized void m10486a(C6314d c6314d) {
        C6396h.m10128n("addErrorInfo : " + c6314d.toJsonString());
        if (c6314d == null) {
            return;
        }
        C6396h.m10128n("addErrorInfo   isSendState: " + this.f15848c);
        if (this.f15848c) {
            this.f15850e.add(c6314d);
        } else {
            this.f15849d.add(c6314d);
            C6396h.m10128n("CustomSaveProcess   putValueInSp: " + c6314d.m10462d());
            C6313c.m10477a(c6314d.m10462d(), c6314d.toJsonString());
        }
    }

    /* renamed from: d */
    public synchronized void m10482d() {
        this.f15849d.clear();
        C6313c.m10471f();
    }

    @Override // com.networkbench.agent.impl.harvest.type.HarvestableObject, com.networkbench.agent.impl.harvest.type.BaseHarvestable, com.networkbench.agent.impl.harvest.type.Harvestable
    public JsonObject asJsonObject() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("type", new JsonPrimitive("customError"));
        jsonObject.add("interval", new JsonPrimitive((Number) Integer.valueOf(this.f15847a)));
        jsonObject.add("timestamp", new JsonPrimitive((Number) Long.valueOf(TimeUnit.SECONDS.convert(System.currentTimeMillis(), TimeUnit.MILLISECONDS))));
        jsonObject.add("dev", NBSAgent.getDeviceData().asJson());
        jsonObject.add("errs", m10479g());
        return jsonObject;
    }

    /* renamed from: g */
    private synchronized JsonArray m10479g() {
        JsonArray jsonArray;
        jsonArray = new JsonArray();
        for (C6314d c6314d : this.f15849d) {
            jsonArray.add(c6314d.asJson());
        }
        return jsonArray;
    }

    /* renamed from: e */
    public int m10481e() {
        return this.f15849d.size();
    }

    /* renamed from: a */
    public void m10485a(Queue<C6314d> queue) {
        for (C6314d c6314d : queue) {
            m10486a(c6314d);
        }
    }

    /* renamed from: f */
    public synchronized void m10480f() {
        while (this.f15850e.size() > 0) {
            m10486a(this.f15850e.poll());
        }
    }
}
