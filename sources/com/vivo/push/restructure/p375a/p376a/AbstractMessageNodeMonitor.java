package com.vivo.push.restructure.p375a.p376a;

import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.vivo.push.util.ConcurrentUtils;
import com.vivo.push.util.LogUtil;
import org.json.JSONArray;
import org.json.JSONObject;

@NBSInstrumented
/* renamed from: com.vivo.push.restructure.a.a.a */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
abstract class AbstractMessageNodeMonitor<T> {

    /* renamed from: a */
    protected T f21083a;

    /* renamed from: b */
    private String f21084b;

    /* renamed from: e */
    private NodeListener f21087e;

    /* renamed from: g */
    private AbstractMessageNodeMonitor f21089g;

    /* renamed from: c */
    private long f21085c = -1;

    /* renamed from: d */
    private int f21086d = -1;

    /* renamed from: f */
    private boolean f21088f = false;

    /* renamed from: a */
    protected abstract int mo5571a(T t);

    public AbstractMessageNodeMonitor(String str, T t, NodeListener nodeListener) {
        this.f21084b = str;
        this.f21083a = t;
        this.f21087e = nodeListener;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: d */
    public void m5579d() {
        long currentTimeMillis = System.currentTimeMillis();
        this.f21086d = mo5571a((AbstractMessageNodeMonitor<T>) this.f21083a);
        this.f21085c = System.currentTimeMillis() - currentTimeMillis;
        int i = this.f21086d;
        if (i != 0) {
            NodeListener nodeListener = this.f21087e;
            if (nodeListener != null) {
                nodeListener.mo5569a(this, this.f21083a, i);
                return;
            }
            return;
        }
        AbstractMessageNodeMonitor abstractMessageNodeMonitor = this.f21089g;
        if (abstractMessageNodeMonitor != null) {
            abstractMessageNodeMonitor.m5584a();
            return;
        }
        NodeListener nodeListener2 = this.f21087e;
        if (nodeListener2 != null) {
            nodeListener2.mo5567a((NodeListener) this.f21083a);
        }
    }

    /* renamed from: b */
    public synchronized String mo5572b() {
        JSONObject jSONObject;
        jSONObject = new JSONObject();
        try {
            jSONObject.put("name", this.f21084b);
            jSONObject.put("code", this.f21086d);
            jSONObject.put("cost", this.f21085c);
        } catch (Exception e) {
            LogUtil.m5352a("AbstractMessageNodeMoni", e);
        }
        return !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject);
    }

    /* renamed from: a */
    public final void m5582a(AbstractMessageNodeMonitor abstractMessageNodeMonitor) {
        if (this != abstractMessageNodeMonitor) {
            this.f21089g = abstractMessageNodeMonitor;
        }
    }

    /* renamed from: a */
    public final void m5583a(long j) {
        this.f21085c = j;
    }

    /* renamed from: c */
    public final JSONArray m5580c() {
        JSONArray jSONArray = new JSONArray();
        for (AbstractMessageNodeMonitor<T> abstractMessageNodeMonitor = this; abstractMessageNodeMonitor != null; abstractMessageNodeMonitor = abstractMessageNodeMonitor.f21089g) {
            try {
                jSONArray.put(abstractMessageNodeMonitor.mo5572b());
            } catch (Exception e) {
                LogUtil.m5352a("AbstractMessageNodeMoni", e);
            }
        }
        return jSONArray;
    }

    /* renamed from: a */
    public final void m5584a() {
        if (this.f21088f) {
            ConcurrentUtils.m5404a().execute(new RunnableC10970b(this));
        } else {
            m5579d();
        }
    }
}
