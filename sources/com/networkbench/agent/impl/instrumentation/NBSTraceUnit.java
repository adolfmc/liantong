package com.networkbench.agent.impl.instrumentation;

import android.os.Looper;
import com.networkbench.agent.impl.p243c.p248e.C6293k;
import com.networkbench.agent.impl.p243c.p248e.C6295m;
import com.networkbench.agent.impl.p255g.p257b.C6410a;
import com.networkbench.agent.impl.tracing.TracingInactiveException;
import com.networkbench.agent.impl.util.C6638h;
import com.networkbench.agent.impl.util.C6642k;
import com.networkbench.com.google.gson.JsonObject;
import com.networkbench.com.google.gson.JsonPrimitive;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class NBSTraceUnit extends NBSUnit {
    public int callType;
    private long invokeTimeFromAppStart;
    public boolean isPageLoadEnd;
    public int nodeType;
    public C6410a segmentParams;
    public int segmentType;

    private String checkValue(String str) {
        return str == null ? "" : str;
    }

    public NBSTraceUnit() {
        this.isPageLoadEnd = false;
        this.segmentParams = null;
        this.nodeType = 0;
        this.callType = 1;
        this.segmentType = 0;
    }

    public NBSTraceUnit(String str, int i) {
        this.isPageLoadEnd = false;
        this.callType = (Looper.myLooper() == Looper.getMainLooper() ? C6295m.EnumC6296a.SYNC : C6295m.EnumC6296a.ASYNC).m10532a();
        this.metricName = str;
        this.segmentType = i;
        this.entryTimestamp = System.currentTimeMillis();
    }

    public void setSegmentParams(C6410a c6410a) {
        this.segmentParams = c6410a;
    }

    @Override // com.networkbench.agent.impl.instrumentation.NBSUnit
    public void complete() throws TracingInactiveException {
        super.complete();
        this.exitTimestamp = System.currentTimeMillis();
        this.isComplete = true;
    }

    public JsonObject completeSegmentParams(C6293k c6293k) {
        JsonObject jsonObject = new JsonObject();
        JsonObject jsonObject2 = new JsonObject();
        C6410a c6410a = this.segmentParams;
        if (c6410a == null) {
            return jsonObject;
        }
        if (C6642k.m8921a(c6410a.m10061c().m10952s(), this.segmentParams.m10060d())) {
            this.segmentParams.m10061c().m10968f(200);
            this.segmentParams.m10064a(0);
        }
        if (this.segmentParams.m10061c().m10952s() > 600 || this.segmentParams.m10061c().m10952s() == -1 || this.segmentParams.m10061c().m10951t() == -1) {
            c6293k.m10554e();
        } else if (this.segmentParams.m10061c().m10952s() != 200) {
            c6293k.m10556d();
        }
        c6293k.m10553f();
        jsonObject.add("host", new JsonPrimitive(this.segmentParams.m10061c().m10959l()));
        jsonObject.add("url", new JsonPrimitive(this.segmentParams.m10060d()));
        jsonObject.add("httpStatus", new JsonPrimitive((Number) Integer.valueOf(this.segmentParams.m10061c().m10952s())));
        jsonObject.add("errorCode", new JsonPrimitive((Number) Integer.valueOf(this.segmentParams.m10061c().m10951t())));
        jsonObject.add("bytesSent", new JsonPrimitive((Number) Long.valueOf(this.segmentParams.m10061c().m10949v())));
        jsonObject.add("bytesReceived", new JsonPrimitive((Number) Long.valueOf(this.segmentParams.m10061c().m10948w())));
        jsonObject.add("dns", new JsonPrimitive((Number) Integer.valueOf(this.segmentParams.m10061c().m10960k())));
        jsonObject.add("conn", new JsonPrimitive((Number) Integer.valueOf(this.segmentParams.m10061c().m10958m())));
        jsonObject.add("fp", new JsonPrimitive((Number) Integer.valueOf(this.segmentParams.m10061c().m10956o())));
        jsonObject.add("ssl", new JsonPrimitive((Number) Integer.valueOf(this.segmentParams.m10061c().m10957n())));
        JsonPrimitive jsonPrimitive = null;
        jsonObject.add("txData", this.segmentParams.m10061c().m10947x() == null ? null : new JsonPrimitive(this.segmentParams.m10061c().m10947x()));
        try {
            jsonObject2.add("offset", new JsonPrimitive((Number) Long.valueOf(this.segmentParams.m10061c().m10989A() - c6293k.m10563b())));
            jsonObject2.add("url", new JsonPrimitive(checkValue(this.segmentParams.m10060d())));
            jsonObject2.add("param", this.segmentParams.m10061c().m10950u() == null ? new JsonPrimitive("") : new JsonPrimitive(this.segmentParams.m10061c().m10950u()));
            jsonObject2.add("method", new JsonPrimitive((Number) Integer.valueOf(this.segmentParams.m10061c().m10954q().ordinal())));
            jsonObject2.add("hc", new JsonPrimitive((Number) Integer.valueOf(this.segmentParams.m10061c().m10952s())));
            jsonObject2.add("ec", new JsonPrimitive((Number) Integer.valueOf(this.segmentParams.m10061c().m10951t())));
            jsonObject2.add("du", new JsonPrimitive((Number) Integer.valueOf(this.segmentParams.m10061c().m10988B())));
            jsonObject2.add("dns", new JsonPrimitive((Number) Integer.valueOf(this.segmentParams.m10061c().m10960k())));
            jsonObject2.add("tcp", new JsonPrimitive((Number) Integer.valueOf(this.segmentParams.m10061c().m10958m())));
            jsonObject2.add("ssl", new JsonPrimitive((Number) Integer.valueOf(this.segmentParams.m10061c().m10957n())));
            jsonObject2.add("fp", new JsonPrimitive((Number) Integer.valueOf(this.segmentParams.m10061c().m10956o())));
            jsonObject2.add("rp", new JsonPrimitive((Number) Integer.valueOf(this.segmentParams.m10061c().m10980b())));
            jsonObject2.add("lq", new JsonPrimitive((Number) Integer.valueOf(this.segmentParams.m10061c().m10977c())));
            jsonObject2.add("bs", new JsonPrimitive((Number) Long.valueOf(this.segmentParams.m10061c().m10949v())));
            jsonObject2.add("br", new JsonPrimitive((Number) Long.valueOf(this.segmentParams.m10061c().m10948w())));
            jsonObject2.add("tx", this.segmentParams.m10061c().m10947x() == null ? null : new JsonPrimitive(this.segmentParams.m10061c().m10947x()));
            if (C6638h.m8963w().m9065V()) {
                jsonObject.add("txDataNew", this.segmentParams.m10061c().m10946y() == null ? null : new JsonPrimitive(this.segmentParams.m10061c().m10946y()));
                if (this.segmentParams.m10061c().m10946y() != null) {
                    jsonPrimitive = new JsonPrimitive(this.segmentParams.m10061c().m10946y());
                }
                jsonObject2.add("txn", jsonPrimitive);
            }
            c6293k.f15747g.add(jsonObject2);
        } catch (Throwable th) {
            f16379b.mo10121a("completeSegmentParams has an error ", th);
        }
        return jsonObject;
    }

    @Override // com.networkbench.agent.impl.instrumentation.NBSUnit
    public String toString() {
        return "NBSTraceUnit{invokeTimeFromAppStart=" + this.invokeTimeFromAppStart + "entryTimestamp " + this.entryTimestamp + "exitTimestamp " + this.exitTimestamp + ", segmentType=" + this.segmentType + ", callType=" + this.callType + ", nodeType=" + this.nodeType + ", segmentParams=" + this.segmentParams + "} " + super.toString();
    }
}
