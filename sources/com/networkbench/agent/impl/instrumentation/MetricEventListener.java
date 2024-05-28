package com.networkbench.agent.impl.instrumentation;

import com.networkbench.agent.impl.p255g.p257b.C6410a;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public interface MetricEventListener {
    void addNetworkToSegment(C6410a c6410a);

    void asyncEnterMethod(NBSTraceUnit nBSTraceUnit);

    void enterMethod(NBSTraceUnit nBSTraceUnit);

    void exitMethod();

    void exitMethodCustom(String str);
}
