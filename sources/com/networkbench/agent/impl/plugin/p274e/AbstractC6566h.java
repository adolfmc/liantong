package com.networkbench.agent.impl.plugin.p274e;

import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.C6396h;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.agent.impl.plugin.AbstractC6590h;
import com.networkbench.agent.impl.plugin.EnumC6558e;
import com.networkbench.agent.impl.util.C6648q;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.plugin.e.h */
/* loaded from: E:\11480076_dexfile_execute.dex */
public abstract class AbstractC6566h {

    /* renamed from: d */
    protected static InterfaceC6393e f16798d = C6394f.m10150a();

    /* renamed from: e */
    EnumC6558e f16801e;

    /* renamed from: b */
    List<AbstractC6590h> f16799b = new CopyOnWriteArrayList();

    /* renamed from: c */
    protected Map<String, Object> f16800c = new HashMap();

    /* renamed from: f */
    protected AtomicInteger f16802f = new AtomicInteger(0);

    /* renamed from: a */
    protected abstract void mo9378a();

    public AbstractC6566h(EnumC6558e enumC6558e) {
        this.f16801e = enumC6558e;
    }

    /* renamed from: c */
    public List<AbstractC6590h> m9373c() {
        return this.f16799b;
    }

    /* renamed from: a */
    public void m9376a(AbstractC6590h abstractC6590h, Boolean bool) {
        if (abstractC6590h != null) {
            synchronized (this.f16799b) {
                if (bool.booleanValue() && m9374b(abstractC6590h)) {
                    return;
                }
                this.f16799b.add(abstractC6590h);
            }
        }
    }

    /* renamed from: b */
    private boolean m9374b(AbstractC6590h abstractC6590h) {
        for (AbstractC6590h abstractC6590h2 : this.f16799b) {
            if (abstractC6590h2.m9311b().equals(abstractC6590h.m9311b())) {
                C6396h.m10137e("这个pluginObserver已经注册过类似的了.." + abstractC6590h.toString());
                return true;
            }
        }
        return false;
    }

    /* renamed from: a */
    public void m9377a(AbstractC6590h abstractC6590h) {
        if (abstractC6590h != null) {
            this.f16799b.remove(abstractC6590h);
        }
    }

    /* renamed from: d */
    public void m9372d() {
        C6396h.m10137e("PluginObserver  notifyObservers  list.size():" + this.f16799b.size());
        if (this.f16799b.size() <= 0) {
            mo9378a();
            return;
        }
        for (AbstractC6590h abstractC6590h : this.f16799b) {
            C6396h.m10137e("PluginObserver  notifyObservers    pluginObserver.getClass().getSimpleName()" + abstractC6590h.getClass().getSimpleName());
            abstractC6590h.m9310c();
        }
    }

    /* renamed from: e */
    public Map<String, Object> m9371e() {
        return this.f16800c;
    }

    /* renamed from: a */
    public void m9375a(String str, Object obj) {
        this.f16800c.put(str, obj);
    }

    /* renamed from: f */
    public void m9370f() {
        if (this.f16802f.incrementAndGet() == this.f16799b.size()) {
            mo9378a();
        }
        m9369g();
    }

    /* renamed from: g */
    protected void m9369g() {
        for (AbstractC6590h abstractC6590h : this.f16799b) {
            if (abstractC6590h.f16891j && !abstractC6590h.f16892l && !abstractC6590h.f16889h.m10736a()) {
                C6648q.m8781a(abstractC6590h);
                abstractC6590h.f16892l = true;
            }
        }
    }
}
