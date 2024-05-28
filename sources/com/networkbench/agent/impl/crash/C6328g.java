package com.networkbench.agent.impl.crash;

import android.content.Context;
import android.text.TextUtils;
import com.networkbench.agent.impl.NBSAgent;
import com.networkbench.agent.impl.harvest.Harvest;
import com.networkbench.agent.impl.harvest.HarvestConnection;
import com.networkbench.agent.impl.harvest.p261b.C6458a;
import com.networkbench.agent.impl.instrumentation.TingyunErrorEventFeedBack;
import com.networkbench.agent.impl.p241b.RunnableC6227a;
import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.agent.impl.p262i.EnumC6467d;
import com.networkbench.agent.impl.plugin.p274e.C6560b;
import com.networkbench.agent.impl.plugin.p274e.C6565g;
import com.networkbench.agent.impl.util.C6636f;
import com.networkbench.agent.impl.util.C6638h;
import com.networkbench.agent.impl.util.C6640i;
import com.networkbench.agent.impl.util.C6642k;
import com.networkbench.agent.impl.util.C6653u;
import com.networkbench.com.google.gson.JsonArray;
import com.networkbench.com.google.gson.JsonElement;
import com.networkbench.com.google.gson.JsonObject;
import com.networkbench.com.google.gson.JsonParseException;
import com.networkbench.com.google.gson.JsonParser;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.crash.g */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6328g implements InterfaceC6327f {

    /* renamed from: b */
    public static Context f15928b;

    /* renamed from: g */
    private static C6328g f15932g;

    /* renamed from: d */
    TingyunErrorEventFeedBack f15934d;

    /* renamed from: e */
    private static final InterfaceC6393e f15930e = C6394f.m10150a();

    /* renamed from: f */
    private static final AtomicBoolean f15931f = new AtomicBoolean(false);

    /* renamed from: c */
    public static String f15929c = "NBSCrashStore";

    /* renamed from: a */
    ExecutorService f15933a = Executors.newSingleThreadExecutor();

    /* renamed from: h */
    private InterfaceC6329h f15935h = new C6332j(f15928b, f15929c);

    /* renamed from: a */
    public static C6328g m10394a() {
        if (f15932g == null) {
            f15932g = new C6328g();
        }
        return f15932g;
    }

    private C6328g() {
    }

    /* renamed from: a */
    public static void m10392a(Context context) {
        f15928b = context;
    }

    /* renamed from: b */
    public void m10381b() {
        Map<String, ?> mo10374b;
        try {
            if (f15928b == null) {
                f15930e.mo10122a("user close crash report ");
                return;
            }
            if (C6638h.m8963w().m9042ad() && Harvest.isCrash_enabled()) {
                if (C6638h.m8963w().m8976o()) {
                    if (C6638h.m8963w().m8989k()) {
                        f15930e.mo10116d("版本升级,清除掉log.....");
                        this.f15935h.mo10369d();
                    }
                    int i = 0;
                    if (f15931f.compareAndSet(false, true) && (mo10374b = this.f15935h.mo10374b()) != null) {
                        InterfaceC6393e interfaceC6393e = f15930e;
                        interfaceC6393e.mo10122a("report all stored crash ,crashStore size is " + mo10374b.size());
                        Iterator<Map.Entry<String, ?>> it = mo10374b.entrySet().iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                break;
                            }
                            Map.Entry<String, ?> next = it.next();
                            if (C6325e.m10397a(next.getKey())) {
                                InterfaceC6393e interfaceC6393e2 = f15930e;
                                interfaceC6393e2.mo10122a("crash has reported, timestamp is " + C6636f.m9092c(next.getKey()));
                                break;
                            }
                            String m9092c = C6636f.m9092c((String) next.getValue());
                            if (m9092c != null) {
                                try {
                                    JsonArray m10383a = m10383a(m9092c, C6638h.m8963w().m9075L());
                                    JsonObject m10386a = m10386a(m10383a, C6638h.m8963w().m9075L());
                                    if (HarvestConnection.isSoDisable()) {
                                        this.f15933a.execute(new RunnableC6227a(m10386a.toString(), this.f15935h, C6636f.m9092c(next.getKey()), C6640i.f17186a, EnumC6467d.CRASH_DATA, m10388a(m10383a)));
                                    } else {
                                        InterfaceC6329h interfaceC6329h = this.f15935h;
                                        this.f15933a.execute(new RunnableC6310a(m10386a, interfaceC6329h, C6636f.m9092c(next.getKey() + ""), EnumC6467d.CRASH_DATA.m9915a(), m10388a(m10383a)));
                                    }
                                    C6325e.m10395b(next.getKey());
                                    InterfaceC6393e interfaceC6393e3 = f15930e;
                                    StringBuilder sb = new StringBuilder();
                                    sb.append("ThreadPool submit store crash report Runnable ,crash num is ");
                                    i++;
                                    sb.append(i);
                                    interfaceC6393e3.mo10122a(sb.toString());
                                } catch (JsonParseException unused) {
                                    f15930e.mo10115e("invaild json str for crash");
                                    this.f15935h.mo10376a(C6636f.m9092c(next.getKey()));
                                }
                            }
                        }
                        this.f15933a.shutdown();
                        return;
                    }
                    return;
                }
                return;
            }
            InterfaceC6393e interfaceC6393e4 = f15930e;
            interfaceC6393e4.mo10115e("Crash_enabled() is " + Harvest.isCrash_enabled() + ",stop report crash");
        } catch (Throwable th) {
            f15930e.mo10121a("Exception occur while send stored crash", th);
        }
    }

    /* renamed from: b */
    private void m10380b(C6321c c6321c, boolean z) {
        this.f15935h.mo10375a(c6321c.m10415c(), c6321c.asJsonArray().toString());
        if (C6638h.m8963w().m8976o()) {
            f15930e.mo10122a("report crash start");
            C6325e.m10395b(C6636f.m9093b(c6321c.m10415c()));
            if (C6565g.f16795g) {
                C6560b c6560b = new C6560b(c6321c, f15928b);
                C6565g.m9380a(c6560b);
                c6560b.m9372d();
                m10393a(1000L);
                c6560b.m9386b();
                if (c6321c.m10417b().size() > 0) {
                    String jsonArray = c6321c.asJsonArray().toString();
                    InterfaceC6393e interfaceC6393e = f15930e;
                    interfaceC6393e.mo10122a("scene store value:" + jsonArray);
                    this.f15935h.mo10375a(c6321c.m10415c(), jsonArray);
                }
                m10390a(c6321c, z);
                return;
            }
            m10390a(c6321c, z);
        }
    }

    /* renamed from: a */
    public void m10390a(C6321c c6321c, boolean z) {
        JsonObject m10386a = m10386a(c6321c.asJsonArray(), C6638h.m8963w().m9075L());
        if (HarvestConnection.isSoDisable()) {
            try {
                m10384a(new RunnableC6227a(m10386a.toString(), this.f15935h, c6321c.m10415c(), C6640i.f17186a, EnumC6467d.CRASH_DATA, c6321c.m10423a()), z);
                return;
            } catch (Exception e) {
                f15930e.mo10121a("reportCrash error", e);
                return;
            }
        }
        m10384a(new RunnableC6310a(m10386a, this.f15935h, c6321c.m10415c(), EnumC6467d.CRASH_DATA.m9915a(), c6321c.m10423a()), z);
    }

    /* renamed from: a */
    private void m10384a(Runnable runnable, boolean z) {
        Thread thread = new Thread(runnable);
        thread.start();
        if (z) {
            try {
                thread.join(3000L);
            } catch (InterruptedException e) {
                f15930e.mo10121a("Exception occur while waiting to send crash", e);
            }
        }
    }

    /* renamed from: a */
    private void m10393a(long j) {
        try {
            Thread.sleep(j);
        } catch (Throwable th) {
            f15930e.mo10121a("Exception occur in blockCrashHandlerForUpload while waiting to send crash", th);
        }
    }

    /* renamed from: a */
    public JsonArray m10383a(String str, String str2) throws JsonParseException {
        if (str == null) {
            throw new IllegalArgumentException("crash message error");
        }
        return new JsonParser().parse(str).getAsJsonArray();
    }

    /* renamed from: a */
    public JsonObject m10386a(JsonArray jsonArray, String str) throws JsonParseException {
        if (jsonArray == null) {
            throw new IllegalArgumentException("crash message error");
        }
        JsonObject jsonObject = new JsonObject();
        JsonArray jsonArray2 = new JsonArray();
        jsonArray2.add(jsonArray);
        jsonObject.add("data", jsonArray2);
        if (TextUtils.isEmpty(str)) {
            if (HarvestConnection.isSoDisable()) {
                jsonObject.addProperty("did", NBSAgent.getImpl().m9148o());
                jsonObject.add("dev", NBSAgent.getDeviceInformation().asJsonArray());
                jsonObject.add("app", NBSAgent.getApplicationInformation().asJsonArray());
            } else {
                jsonObject.addProperty("did", NBSAgent.getImpl().m9148o());
                jsonObject.add("dev", NBSAgent.getDeviceInformation().asSocketJsonArray());
                jsonObject.add("app", NBSAgent.getApplicationInformation().asSocketJsonArray());
            }
        }
        return jsonObject;
    }

    /* renamed from: a */
    private String m10388a(JsonArray jsonArray) {
        InterfaceC6393e interfaceC6393e = f15930e;
        interfaceC6393e.mo10122a("getUserActionId  jsonArray :" + jsonArray.toString());
        return jsonArray != null ? m10387a(jsonArray, 14).getAsString() : "";
    }

    /* renamed from: a */
    private JsonElement m10385a(JsonObject jsonObject, String str) {
        if (jsonObject == null || str == null) {
            throw new IllegalArgumentException("error getElementFromJO");
        }
        return jsonObject.get(str);
    }

    /* renamed from: a */
    private JsonElement m10387a(JsonArray jsonArray, int i) throws IndexOutOfBoundsException {
        if (jsonArray == null || i < 0) {
            throw new IllegalArgumentException("error");
        }
        return jsonArray.get(i);
    }

    @Override // com.networkbench.agent.impl.crash.InterfaceC6327f
    /* renamed from: a */
    public void mo10382a(Thread thread, Throwable th, long j) {
        try {
            UUID uuid = new UUID(C6653u.m8757a().nextLong(), C6653u.m8757a().nextLong());
            m10391a(NBSErrorEventType.crash, uuid.toString());
            NBSAgent.getImpl().m9147p().m8847b(uuid.toString());
            f15932g.m10380b(new C6321c(th, j, C6642k.m8902f(f15928b), C6642k.m8907c("1"), Thread.getAllStackTraces(), uuid), true);
        } catch (Exception e) {
            C6458a.m9944a(C6458a.f16323b, 0);
            f15930e.mo10121a("catch an Exception during reporting an user crash ", e);
        }
    }

    /* renamed from: a */
    public void m10389a(TingyunErrorEventFeedBack tingyunErrorEventFeedBack) {
        this.f15934d = tingyunErrorEventFeedBack;
    }

    /* renamed from: a */
    public void m10391a(NBSErrorEventType nBSErrorEventType, String str) {
        TingyunErrorEventFeedBack tingyunErrorEventFeedBack = this.f15934d;
        if (tingyunErrorEventFeedBack != null) {
            tingyunErrorEventFeedBack.errorEventFeedBack(nBSErrorEventType, str);
        }
    }
}
