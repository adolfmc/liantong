package com.networkbench.agent.impl.p243c.p248e;

import com.networkbench.agent.impl.harvest.type.HarvestableObject;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.networkbench.agent.impl.instrumentation.NBSUnit;
import com.networkbench.agent.impl.p243c.p248e.C6295m;
import com.networkbench.agent.impl.p254f.C6396h;
import com.networkbench.agent.impl.util.C6642k;
import com.networkbench.com.google.gson.JsonArray;
import com.networkbench.com.google.gson.JsonObject;
import com.networkbench.com.google.gson.JsonPrimitive;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.c.e.k */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6293k extends HarvestableObject {

    /* renamed from: c */
    private long f15743c;

    /* renamed from: j */
    public NBSTraceUnit f15750j;

    /* renamed from: l */
    private C6295m.EnumC6301f f15751l;

    /* renamed from: e */
    public String f15745e = null;

    /* renamed from: a */
    private int f15741a = 0;

    /* renamed from: b */
    private int f15742b = 0;

    /* renamed from: f */
    protected final ConcurrentHashMap<UUID, NBSUnit> f15746f = new ConcurrentHashMap<>();

    /* renamed from: g */
    public JsonArray f15747g = new JsonArray();

    /* renamed from: h */
    public final Set<UUID> f15748h = Collections.synchronizedSet(new HashSet());

    /* renamed from: d */
    private boolean f15744d = false;

    /* renamed from: m */
    private int f15752m = 0;

    /* renamed from: i */
    protected long f15749i = System.currentTimeMillis();

    /* renamed from: a */
    public long m10569a() {
        return this.f15743c;
    }

    /* renamed from: a */
    public void m10568a(long j) {
        this.f15743c = j;
    }

    /* renamed from: b */
    public long m10563b() {
        return this.f15749i;
    }

    /* renamed from: b */
    public void m10562b(long j) {
        this.f15749i = j;
    }

    /* renamed from: c */
    public long m10559c() {
        return this.f15750j.exitTimestamp;
    }

    /* renamed from: d */
    public void m10556d() {
        this.f15741a++;
    }

    /* renamed from: e */
    public void m10554e() {
        this.f15742b++;
    }

    /* renamed from: f */
    public void m10553f() {
        this.f15752m++;
    }

    /* renamed from: g */
    public int m10552g() {
        return this.f15741a;
    }

    /* renamed from: h */
    public int m10551h() {
        return this.f15742b;
    }

    /* renamed from: i */
    public int m10550i() {
        return this.f15752m;
    }

    public C6293k(NBSTraceUnit nBSTraceUnit, C6295m.EnumC6301f enumC6301f) {
        this.f15750j = nBSTraceUnit;
        this.f15750j.entryTimestamp = System.currentTimeMillis();
        this.f15750j.nodeType = 0;
        this.f15751l = enumC6301f;
    }

    /* renamed from: j */
    public NBSTraceUnit m10549j() {
        return this.f15750j;
    }

    /* renamed from: k */
    public ConcurrentHashMap<UUID, NBSUnit> m10548k() {
        return this.f15746f;
    }

    @Override // com.networkbench.agent.impl.harvest.type.HarvestableObject, com.networkbench.agent.impl.harvest.type.BaseHarvestable, com.networkbench.agent.impl.harvest.type.Harvestable
    public JsonObject asJsonObject() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("cpu", new JsonArray());
        jsonObject.add("mem", new JsonArray());
        jsonObject.add("stacks", mo10565a((NBSUnit) this.f15750j));
        return jsonObject;
    }

    /* renamed from: l */
    public JsonObject m10547l() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.add("cpu", new JsonArray());
        jsonObject.add("mem", new JsonArray());
        jsonObject.add("stacks", m10555d(this.f15750j));
        return jsonObject;
    }

    /* renamed from: b */
    public void m10560b(NBSUnit nBSUnit) {
        this.f15748h.add(nBSUnit.myUUID);
    }

    /* renamed from: c */
    public void m10557c(NBSUnit nBSUnit) {
        this.f15748h.remove(nBSUnit.myUUID);
        this.f15746f.put(nBSUnit.myUUID, nBSUnit);
    }

    /* renamed from: m */
    public boolean m10546m() {
        return !this.f15748h.isEmpty();
    }

    /* renamed from: n */
    public boolean m10545n() {
        return this.f15744d;
    }

    /* renamed from: o */
    public C6293k m10544o() {
        if (this.f15744d) {
            return null;
        }
        C6396h.m10133i("Completing slowStartTrace trace size of " + this.f15746f.size());
        this.f15744d = true;
        this.f15750j.exitTimestamp = System.currentTimeMillis();
        this.f15743c = this.f15750j.exitTimestamp;
        return this;
    }

    /* renamed from: c */
    private long m10558c(long j) {
        return j == -1 ? j : j - this.f15749i;
    }

    /* renamed from: a */
    private long m10566a(NBSTraceUnit nBSTraceUnit) {
        if (this.f15751l == C6295m.EnumC6301f.pageLoading && nBSTraceUnit.isPageLoadEnd) {
            return -1L;
        }
        if (nBSTraceUnit.exitTimestamp == -1) {
            return nBSTraceUnit.exitTimestamp;
        }
        return nBSTraceUnit.exitTimestamp - this.f15749i;
    }

    /* renamed from: d */
    public JsonArray m10555d(NBSUnit nBSUnit) {
        if (nBSUnit instanceof NBSTraceUnit) {
            NBSTraceUnit nBSTraceUnit = (NBSTraceUnit) nBSUnit;
            if ((nBSTraceUnit.segmentType == C6295m.EnumC6300e.NETWORK.m10531a() && (nBSTraceUnit.segmentParams == null || C6642k.m8903e(nBSTraceUnit.segmentParams.m10060d()))) || m10561b(nBSTraceUnit)) {
                return null;
            }
            JsonArray jsonArray = new JsonArray();
            jsonArray.add(new JsonPrimitive((Number) Long.valueOf(nBSTraceUnit.callType == C6295m.EnumC6296a.SYNC.m10532a() ? 0L : m10558c(nBSTraceUnit.entryTimestamp))));
            jsonArray.add(new JsonPrimitive((Number) Long.valueOf(m10558c(nBSTraceUnit.entryTimestamp))));
            jsonArray.add(new JsonPrimitive((Number) Long.valueOf(m10566a(nBSTraceUnit))));
            jsonArray.add(new JsonPrimitive(nBSTraceUnit.metricName));
            jsonArray.add(new JsonPrimitive((Number) Integer.valueOf(nBSTraceUnit.segmentType)));
            jsonArray.add(new JsonPrimitive((Number) Integer.valueOf(nBSTraceUnit.callType)));
            jsonArray.add(m10567a(nBSTraceUnit.threadId, nBSTraceUnit.threadName));
            jsonArray.add(nBSTraceUnit.completeSegmentParams(this));
            ArrayList<NBSUnit> arrayList = new ArrayList();
            JsonArray jsonArray2 = new JsonArray();
            if (nBSTraceUnit.myUUID != null) {
                for (Map.Entry<UUID, NBSUnit> entry : this.f15746f.entrySet()) {
                    if (nBSTraceUnit.myUUID == entry.getValue().parentUUID) {
                        arrayList.add(entry.getValue());
                    }
                }
            }
            m10564a((List<NBSUnit>) arrayList);
            for (NBSUnit nBSUnit2 : arrayList) {
                JsonArray m10555d = m10555d(nBSUnit2);
                if (m10555d != null) {
                    jsonArray2.add(m10555d);
                }
            }
            jsonArray.add(jsonArray2);
            jsonArray.add(new JsonPrimitive((Number) Integer.valueOf(nBSTraceUnit.nodeType)));
            return jsonArray;
        }
        return null;
    }

    /* renamed from: a */
    public void m10564a(List<NBSUnit> list) {
        int i = 0;
        while (i < list.size()) {
            int i2 = i + 1;
            for (int i3 = i2; i3 < list.size(); i3++) {
                if (list.get(i).entryTimestamp > list.get(i3).entryTimestamp) {
                    list.set(i, list.get(i3));
                    list.set(i3, list.get(i));
                }
            }
            i = i2;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public JsonArray mo10565a(NBSUnit nBSUnit) {
        JsonArray mo10565a;
        if (nBSUnit instanceof NBSTraceUnit) {
            NBSTraceUnit nBSTraceUnit = (NBSTraceUnit) nBSUnit;
            JsonArray jsonArray = new JsonArray();
            if ((nBSTraceUnit.segmentType == C6295m.EnumC6300e.NETWORK.m10531a() && (nBSTraceUnit.segmentParams == null || C6642k.m8903e(nBSTraceUnit.segmentParams.m10060d()))) || m10561b(nBSTraceUnit)) {
                return null;
            }
            jsonArray.add(new JsonPrimitive((Number) Long.valueOf(nBSTraceUnit.callType == C6295m.EnumC6296a.SYNC.m10532a() ? 0L : m10558c(nBSTraceUnit.entryTimestamp))));
            jsonArray.add(new JsonPrimitive((Number) Long.valueOf(m10558c(nBSTraceUnit.entryTimestamp))));
            jsonArray.add(new JsonPrimitive((Number) Long.valueOf(m10566a(nBSTraceUnit))));
            jsonArray.add(new JsonPrimitive(nBSTraceUnit.metricName));
            jsonArray.add(new JsonPrimitive((Number) Integer.valueOf(nBSTraceUnit.segmentType)));
            jsonArray.add(new JsonPrimitive((Number) Integer.valueOf(nBSTraceUnit.callType)));
            jsonArray.add(m10567a(nBSTraceUnit.threadId, nBSTraceUnit.threadName));
            jsonArray.add(nBSTraceUnit.completeSegmentParams(this));
            JsonArray jsonArray2 = new JsonArray();
            ArrayList arrayList = new ArrayList();
            if (!nBSTraceUnit.getChildren().isEmpty()) {
                for (UUID uuid : nBSUnit.getChildren()) {
                    NBSUnit nBSUnit2 = this.f15746f.get(uuid);
                    if (nBSUnit2 != null) {
                        arrayList.add(nBSUnit2);
                    }
                }
            }
            m10564a((List<NBSUnit>) arrayList);
            for (NBSUnit nBSUnit3 : arrayList) {
                if (nBSUnit3 != null && (mo10565a = mo10565a(nBSUnit3)) != null) {
                    jsonArray2.add(mo10565a);
                }
            }
            jsonArray.add(jsonArray2);
            jsonArray.add(new JsonPrimitive((Number) Integer.valueOf(nBSTraceUnit.nodeType)));
            return jsonArray;
        }
        return null;
    }

    /* renamed from: b */
    private boolean m10561b(NBSTraceUnit nBSTraceUnit) {
        if (nBSTraceUnit.entryTimestamp > this.f15743c) {
            return true;
        }
        if (nBSTraceUnit.callType == C6295m.EnumC6296a.ASYNC.m10532a()) {
            if (nBSTraceUnit.entryTimestamp > nBSTraceUnit.exitTimestamp) {
                nBSTraceUnit.exitTimestamp = nBSTraceUnit.entryTimestamp;
            }
            if (nBSTraceUnit.exitTimestamp > m10569a()) {
                nBSTraceUnit.isPageLoadEnd = true;
                return false;
            }
            return false;
        }
        return false;
    }

    /* renamed from: a */
    public JsonArray m10567a(long j, String str) {
        JsonArray jsonArray = new JsonArray();
        jsonArray.add(new JsonPrimitive((Number) Long.valueOf(j)));
        if (str == null) {
            str = "";
        }
        jsonArray.add(new JsonPrimitive(str));
        return jsonArray;
    }

    /* renamed from: p */
    public long m10543p() {
        return this.f15750j.exitTimestamp - this.f15750j.entryTimestamp;
    }

    /* renamed from: q */
    public long m10542q() {
        return this.f15743c - this.f15749i;
    }
}
