package com.networkbench.agent.impl.tracing;

import com.networkbench.agent.impl.harvest.type.MetricCategory;
import com.networkbench.agent.impl.instrumentation.NBSTraceEngine;
import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.C6396h;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.agent.impl.util.C6638h;
import com.networkbench.agent.impl.util.C6653u;
import java.util.ArrayList;
import java.util.Arrays;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class CustomTracer {
    private static final ArrayList<String> categoryParams = new ArrayList<>(Arrays.asList("category", MetricCategory.class.getName(), "CUSTOMEVENT"));
    private static InterfaceC6393e log = C6394f.m10150a();

    public static void beginTracer(String str, String str2) {
        if (!C6653u.m8689l(str2)) {
            InterfaceC6393e interfaceC6393e = log;
            interfaceC6393e.mo10116d("beginTracer param is invalid!" + str2);
            if (C6638h.m8963w().m8979n()) {
                C6396h.m10140b("beginTracer param is invalid!" + str2, new Object[0]);
                return;
            }
            return;
        }
        NBSTraceEngine.enterMethod("<_TY_C_API>" + str + str2, categoryParams);
    }

    public static void endTracer(String str, String str2) {
        if (!C6653u.m8689l(str2)) {
            InterfaceC6393e interfaceC6393e = log;
            interfaceC6393e.mo10116d("endTracer param is invalid!" + str2);
            if (C6638h.m8963w().m8979n()) {
                C6396h.m10140b("endTracer param is invalid!" + str2, new Object[0]);
                return;
            }
            return;
        }
        NBSTraceEngine.exitCustomApiMethod("<_TY_C_API>" + str + str2);
    }
}
