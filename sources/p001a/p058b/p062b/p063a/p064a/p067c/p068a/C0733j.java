package p001a.p058b.p062b.p063a.p064a.p067c.p068a;

import com.baidu.uaq.agent.android.UAQ;
import com.baidu.uaq.agent.android.transmission.InterfaceC3323a;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.JSONArray;
import p001a.p058b.p062b.p063a.p064a.p067c.p071d.AbstractC0746c;
import p001a.p058b.p062b.p063a.p064a.p072d.C0749a;
import p001a.p058b.p062b.p063a.p064a.p072d.C0751c;

/* renamed from: a.b.b.a.a.c.a.j */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0733j extends AbstractC0746c {

    /* renamed from: b */
    public final List<C0732i> f2239b = new ArrayList();

    /* renamed from: c */
    public InterfaceC3323a f2240c;

    static {
        C0751c c0751c = C0749a.f2299a;
        UAQ.getInstance();
    }

    public C0733j(InterfaceC3323a interfaceC3323a) {
        this.f2240c = interfaceC3323a;
    }

    @Override // p001a.p058b.p062b.p063a.p064a.p067c.p071d.C0745b, com.baidu.uaq.agent.android.harvest.type.InterfaceC3319b
    /* renamed from: U */
    public JSONArray mo17433U() {
        List<C0732i> m22304a = m22304a();
        return m22304a.size() != 0 ? this.f2240c.mo17424c(m22304a) : new JSONArray();
    }

    /* renamed from: a */
    public final List<C0732i> m22304a() {
        synchronized (this) {
            if (this.f2239b.size() == 0) {
                return Collections.emptyList();
            }
            ArrayList arrayList = new ArrayList(this.f2239b);
            this.f2239b.clear();
            return arrayList;
        }
    }
}
