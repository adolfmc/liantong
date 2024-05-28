package com.unicom.pay;

import com.google.gson.Gson;
import com.networkbench.agent.impl.instrumentation.NBSGsonInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.unicom.pay.C10546a;
import com.unicom.pay.common.bean.WPResult;
import com.unicom.pay.common.callback.DataCallback;
import p470p0.C13648k;

@NBSInstrumented
/* renamed from: com.unicom.pay.b */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class RunnableC10578b implements Runnable {

    /* renamed from: a */
    public final /* synthetic */ C10546a.C10552b.C10553a f20126a;

    public RunnableC10578b(C10546a.C10552b.C10553a c10553a) {
        this.f20126a = c10553a;
    }

    @Override // java.lang.Runnable
    public final void run() {
        C10546a.C10552b.this.f20076c.setCode("0000");
        C10546a.C10552b c10552b = C10546a.C10552b.this;
        DataCallback dataCallback = c10552b.f20077d;
        WPResult wPResult = c10552b.f20076c;
        Gson gson = C13648k.f27492a;
        dataCallback.onResult(!(gson instanceof Gson) ? gson.toJson(wPResult) : NBSGsonInstrumentation.toJson(gson, wPResult));
    }
}
