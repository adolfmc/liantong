package com.networkbench.agent.impl.p241b;

import com.networkbench.agent.impl.harvest.ActionData;
import com.networkbench.agent.impl.harvest.type.HarvestableArray;
import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.agent.impl.util.C6638h;
import com.networkbench.com.google.gson.JsonArray;
import com.networkbench.com.google.gson.JsonPrimitive;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.b.h */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6238h extends HarvestableArray {

    /* renamed from: d */
    private static InterfaceC6393e f15434d = C6394f.m10150a();

    /* renamed from: a */
    private long f15435a;

    /* renamed from: b */
    private ActionData f15436b;

    /* renamed from: c */
    private long f15437c;

    public C6238h(ActionData actionData, long j, long j2) {
        this.f15436b = actionData;
        this.f15437c = j;
        this.f15435a = j2;
    }

    @Override // com.networkbench.agent.impl.harvest.type.HarvestableArray, com.networkbench.agent.impl.harvest.type.BaseHarvestable, com.networkbench.agent.impl.harvest.type.Harvestable
    public JsonArray asJsonArray() {
        JsonArray jsonArray = new JsonArray();
        try {
            long longValue = (this.f15436b.getTimestamp().longValue() - ((long) this.f15436b.getTotalTime())) - this.f15437c;
            if (longValue < 0) {
                return null;
            }
            jsonArray.add(new JsonPrimitive((Number) Long.valueOf(longValue)));
            jsonArray.add(new JsonPrimitive((Number) Long.valueOf(this.f15435a - this.f15437c)));
            jsonArray.add(new JsonPrimitive(this.f15436b.getUrl() == null ? "" : this.f15436b.getUrl()));
            jsonArray.add(new JsonPrimitive(this.f15436b.getUrlParams() == null ? "" : this.f15436b.getUrlParams()));
            jsonArray.add(new JsonPrimitive((Number) Integer.valueOf(this.f15436b.getRequestMethod().ordinal())));
            jsonArray.add(new JsonPrimitive(this.f15436b.getIP() == null ? "" : this.f15436b.getIP()));
            jsonArray.add(new JsonPrimitive((Number) Integer.valueOf(this.f15436b.getTime_to_dns())));
            jsonArray.add(new JsonPrimitive((Number) Integer.valueOf(this.f15436b.getTime_to_connect())));
            jsonArray.add(new JsonPrimitive((Number) Integer.valueOf(this.f15436b.getTime_first_package())));
            jsonArray.add(new JsonPrimitive((Number) Integer.valueOf(this.f15436b.getTime_ssl_handshake())));
            jsonArray.add(new JsonPrimitive((Number) Integer.valueOf(this.f15436b.getStatusCode())));
            jsonArray.add(new JsonPrimitive((Number) Integer.valueOf(this.f15436b.getErrorCode())));
            jsonArray.add(new JsonPrimitive((Number) Long.valueOf(this.f15436b.getBytesSent())));
            jsonArray.add(new JsonPrimitive((Number) Long.valueOf(this.f15436b.getBytesReceived())));
            jsonArray.add(new JsonPrimitive(this.f15436b.getAppData() == null ? "" : this.f15436b.getAppData()));
            if (C6638h.m8963w().m9065V()) {
                jsonArray.add(new JsonPrimitive(this.f15436b.getAppDataNew() == null ? "" : this.f15436b.getAppDataNew()));
            }
            return jsonArray;
        } catch (Exception e) {
            InterfaceC6393e interfaceC6393e = f15434d;
            interfaceC6393e.mo10116d("ANR NBSANRNetWorkTrace has an error " + e);
            return null;
        }
    }
}
