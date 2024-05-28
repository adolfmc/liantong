package com.networkbench.agent.impl.plugin;

import android.content.Context;
import com.networkbench.agent.impl.harvest.type.BaseHarvestable;
import com.networkbench.agent.impl.harvest.type.HarvestableType;
import com.networkbench.agent.impl.p243c.p246c.C6267d;
import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.agent.impl.plugin.p274e.AbstractC6566h;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.plugin.h */
/* loaded from: E:\11480076_dexfile_execute.dex */
public abstract class AbstractC6590h extends BaseHarvestable {

    /* renamed from: f */
    public static InterfaceC6393e f16887f = C6394f.m10150a();

    /* renamed from: g */
    public String f16888g;

    /* renamed from: h */
    public C6267d f16889h;

    /* renamed from: i */
    protected InterfaceC6530a f16890i;

    /* renamed from: j */
    public boolean f16891j;

    /* renamed from: l */
    public boolean f16892l;

    /* renamed from: m */
    protected String f16893m;

    /* renamed from: n */
    public AbstractC6566h f16894n;

    /* renamed from: a */
    protected abstract void mo9300a(Map<String, Object> map);

    public AbstractC6590h(HarvestableType harvestableType, AbstractC6566h abstractC6566h, C6267d c6267d) {
        super(harvestableType);
        this.f16888g = "";
        this.f16894n = abstractC6566h;
        this.f16889h = c6267d;
        this.f16891j = false;
        this.f16892l = false;
        this.f16890i = new C6553d();
        this.f16893m = m9309e();
    }

    /* renamed from: b */
    public String m9311b() {
        return this.f16893m;
    }

    /* renamed from: c */
    public void m9310c() {
        mo9301a();
    }

    /* renamed from: a */
    protected void mo9301a() {
        this.f16891j = true;
        this.f16894n.m9370f();
    }

    /* renamed from: d */
    public boolean mo9299d() {
        return this.f16890i.mo9368a();
    }

    /* renamed from: e */
    public String m9309e() {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            if (this.f16889h.f15616d != null) {
                messageDigest.update(this.f16889h.f15616d.toString().getBytes());
            }
            if (this.f16889h.f15615c != null) {
                messageDigest.update(this.f16889h.f15615c.getBytes());
            }
            return new String(messageDigest.digest());
        } catch (NoSuchAlgorithmException unused) {
            f16887f.mo10116d("Unable to initialize SHA-1 hash algorithm");
            return null;
        }
    }

    public String toString() {
        return "PluginObserver{action=" + this.f16889h.toString() + "} ";
    }

    /* renamed from: a */
    public String m9312a(Context context) {
        try {
            String[] m9473a = new C6542c(context).m9473a();
            return (m9473a == null || m9473a.length <= 0) ? "" : m9473a[0];
        } catch (Exception e) {
            InterfaceC6393e interfaceC6393e = f16887f;
            interfaceC6393e.mo10122a("error getDnsServer e:" + e.getMessage());
            return "";
        }
    }
}
