package com.networkbench.agent.impl.p241b;

import android.os.Looper;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import com.huawei.hms.push.AttributionReporter;
import com.networkbench.agent.impl.crash.C6317b;
import com.networkbench.agent.impl.crash.C6325e;
import com.networkbench.agent.impl.crash.C6328g;
import com.networkbench.agent.impl.crash.NBSErrorEventType;
import com.networkbench.agent.impl.harvest.ActionData;
import com.networkbench.agent.impl.harvest.ActionDatas;
import com.networkbench.agent.impl.harvest.Harvest;
import com.networkbench.agent.impl.harvest.HarvestConfiguration;
import com.networkbench.agent.impl.harvest.type.HarvestableArray;
import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.agent.impl.util.C6632b;
import com.networkbench.agent.impl.util.C6638h;
import com.networkbench.agent.impl.util.C6642k;
import com.networkbench.agent.impl.util.C6653u;
import com.networkbench.com.google.gson.JsonArray;
import com.networkbench.com.google.gson.JsonObject;
import com.networkbench.com.google.gson.JsonPrimitive;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.b.i */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6239i extends HarvestableArray {

    /* renamed from: f */
    private static final int f15438f = 30;

    /* renamed from: i */
    private static InterfaceC6393e f15439i = C6394f.m10150a();

    /* renamed from: o */
    private static String f15440o = "android.os.MessageQueue.nativePollOnce";

    /* renamed from: p */
    private static final String[] f15441p = {"getLastKnownLocation", "requestLocationUpdates", "Permission", AttributionReporter.SYSTEM_PERMISSION, "postFrameCallback", "NBSAndroidAgentImpl.addLocationListener", "uncaughtException"};

    /* renamed from: a */
    public String f15442a;

    /* renamed from: b */
    public C6240j<String> f15443b;

    /* renamed from: e */
    private long f15446e;

    /* renamed from: j */
    private String f15449j;

    /* renamed from: m */
    private HashMap<String, JsonObject> f15451m;

    /* renamed from: r */
    private Map f15454r;

    /* renamed from: g */
    private Random f15447g = C6653u.m8757a();

    /* renamed from: h */
    private String f15448h = null;

    /* renamed from: n */
    private String f15452n = "";

    /* renamed from: c */
    public String f15444c = "ANR";

    /* renamed from: d */
    public String f15445d = "Dear developer,a slowly operation is blocking the main thread.";

    /* renamed from: q */
    private String f15453q = "";

    /* renamed from: l */
    private Queue<StackTraceElement[]> f15450l = new LinkedList();

    /* renamed from: d */
    public boolean m10910d() {
        return true;
    }

    /* renamed from: a */
    public void m10918a() {
        this.f15448h = m10904j();
    }

    /* renamed from: b */
    public HashMap<String, JsonObject> m10914b() {
        return this.f15451m;
    }

    public C6239i(long j) throws C6632b {
        this.f15446e = j;
        this.f15450l.addAll(RunnableC6242l.m10892a());
        this.f15443b = new C6240j<>();
        this.f15449j = Harvest.currentActivityName;
        this.f15451m = new HashMap<>();
    }

    /* renamed from: c */
    public String m10912c() {
        return this.f15446e + "";
    }

    /* renamed from: a */
    public void m10916a(String str) {
        this.f15452n = str;
    }

    @Override // com.networkbench.agent.impl.harvest.type.HarvestableArray, com.networkbench.agent.impl.harvest.type.BaseHarvestable, com.networkbench.agent.impl.harvest.type.Harvestable
    @RequiresApi(api = 3)
    public JsonArray asJsonArray() {
        JsonArray jsonArray = new JsonArray();
        jsonArray.add(new JsonPrimitive((Number) Long.valueOf(TimeUnit.SECONDS.convert(this.f15446e, TimeUnit.MILLISECONDS))));
        jsonArray.add(new JsonPrimitive((Number) Long.valueOf(m10905i())));
        jsonArray.add(new JsonPrimitive((Number) Integer.valueOf(C6653u.m8703f())));
        jsonArray.add(new JsonPrimitive(this.f15444c));
        String str = this.f15449j;
        if (str == null) {
            str = "";
        }
        jsonArray.add(new JsonPrimitive(str));
        jsonArray.add(new JsonPrimitive(this.f15445d));
        jsonArray.add(m10906h());
        String str2 = this.f15449j;
        if (str2 == null) {
            str2 = "";
        }
        jsonArray.add(new JsonPrimitive(str2));
        jsonArray.add(new JsonPrimitive(this.f15452n));
        jsonArray.add(new JsonPrimitive(this.f15442a));
        jsonArray.add(C6642k.m8902f(C6638h.m8963w().m9076K()));
        jsonArray.add(C6642k.m8923a());
        String str3 = this.f15448h;
        if (str3 == null) {
            str3 = "";
        }
        jsonArray.add(new JsonPrimitive(str3));
        jsonArray.add(new JsonPrimitive(""));
        jsonArray.add(null);
        HashMap<String, JsonObject> hashMap = this.f15451m;
        if (hashMap != null) {
            jsonArray.add(C6653u.m8716c((Map<String, JsonObject>) hashMap));
        } else {
            jsonArray.add(new JsonObject());
        }
        if (C6638h.m8963w().m9065V()) {
            jsonArray.add(new JsonPrimitive(this.f15453q));
        }
        jsonArray.add(new JsonPrimitive(C6653u.m8701f(this.f15454r)));
        return jsonArray;
    }

    @RequiresApi(api = 3)
    /* renamed from: h */
    private JsonArray m10906h() {
        JsonArray jsonArray = new JsonArray();
        Iterator<String> it = this.f15443b.iterator();
        while (it.hasNext()) {
            JsonArray jsonArray2 = new JsonArray();
            jsonArray2.add(new JsonPrimitive((Number) Long.valueOf(Looper.getMainLooper().getThread().getId())));
            jsonArray2.add(new JsonPrimitive(Looper.getMainLooper().getThread().getName()));
            jsonArray2.add(new JsonPrimitive(it.next()));
            jsonArray.add(jsonArray2);
        }
        return jsonArray;
    }

    /* renamed from: b */
    private boolean m10913b(String str) {
        return (str != null && str.startsWith(f15440o)) || str.startsWith("sun.misc.Unsafe.park");
    }

    /* renamed from: e */
    public void m10909e() {
        for (StackTraceElement[] stackTraceElementArr : this.f15450l) {
            if (stackTraceElementArr.length <= 0 || !m10913b(stackTraceElementArr[0].toString())) {
                String sb = m10917a(30, stackTraceElementArr, 0).toString();
                if (!TextUtils.isEmpty(sb)) {
                    this.f15443b.m10903a(sb);
                }
            }
        }
    }

    /* renamed from: i */
    private long m10905i() {
        long m9068S = C6638h.m8963w().m9068S();
        return m9068S <= 0 ? this.f15446e : m9068S;
    }

    /* renamed from: j */
    private String m10904j() {
        try {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("pu", (Number) 0);
            jsonObject.addProperty("ppu", (Number) 0);
            jsonObject.addProperty("mem", Long.valueOf(C6317b.m10443f(C6638h.m8963w().m9076K())));
            jsonObject.addProperty("sp", Long.valueOf(C6317b.m10450b()));
            long m10448c = C6317b.m10448c();
            if (m10448c != -1) {
                jsonObject.addProperty("sd", Long.valueOf(m10448c));
            }
            jsonObject.addProperty("jb", Integer.valueOf(C6317b.m10441h(C6638h.m8963w().m9076K())));
            JsonObject m10398a = C6325e.m10398a();
            InterfaceC6393e interfaceC6393e = f15439i;
            interfaceC6393e.mo10122a("cust is" + m10398a.toString());
            if (m10398a != null) {
                jsonObject.add("cust", m10398a);
            }
            jsonObject.add("tr", C6653u.m8722c());
            jsonObject.add("req", m10908f());
            return jsonObject.toString();
        } catch (Throwable unused) {
            return "";
        }
    }

    /* renamed from: a */
    private StringBuilder m10917a(int i, StackTraceElement[] stackTraceElementArr, int i2) {
        StringBuilder sb = new StringBuilder();
        if (i == 0 || stackTraceElementArr == null || stackTraceElementArr.length == 0 || i2 >= stackTraceElementArr.length) {
            f15439i.mo10122a("anr getShortStackTrace failed");
            return sb;
        }
        int i3 = 0;
        while (true) {
            if (i2 >= stackTraceElementArr.length) {
                break;
            } else if (i3 >= i) {
                if (!TextUtils.isEmpty(sb)) {
                    sb.append("\t...");
                    sb.append(stackTraceElementArr.length - i2);
                    sb.append(" more");
                }
            } else {
                i3++;
                if (!stackTraceElementArr[i2].toString().startsWith("java.")) {
                    sb.append("\tat " + stackTraceElementArr[i2] + "\n");
                }
                i2++;
            }
        }
        return sb;
    }

    /* renamed from: c */
    private boolean m10911c(String str) {
        if (str != null) {
            for (String str2 : f15441p) {
                if (str.contains(str2)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    /* renamed from: f */
    public JsonArray m10908f() {
        JsonArray jsonArray = new JsonArray();
        ActionDatas actionDatas = Harvest.getInstance().getHarvestData().getActionDatas();
        int anrAction = HarvestConfiguration.getDefaultHarvestConfiguration().getAnrAction();
        if (anrAction == -1) {
            return jsonArray;
        }
        for (ActionData actionData : actionDatas.getActionDatas()) {
            if (actionData.getTimestamp().longValue() > this.f15446e && (anrAction == 0 || anrAction > jsonArray.size())) {
                JsonArray asJsonArray = new C6238h(actionData, this.f15446e, actionData.getTimestamp().longValue()).asJsonArray();
                if (asJsonArray != null) {
                    jsonArray.add(asJsonArray);
                }
            }
        }
        return jsonArray;
    }

    /* renamed from: g */
    public void m10907g() {
        this.f15453q = new UUID(this.f15447g.nextLong(), this.f15447g.nextLong()).toString();
        C6328g.m10394a().m10391a(NBSErrorEventType.anr, this.f15453q);
    }

    /* renamed from: a */
    public void m10915a(Map map) {
        this.f15454r = map;
    }
}
