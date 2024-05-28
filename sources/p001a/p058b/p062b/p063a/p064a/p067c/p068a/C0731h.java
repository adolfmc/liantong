package p001a.p058b.p062b.p063a.p064a.p067c.p068a;

import com.baidu.uaq.agent.android.UAQ;
import com.baidu.uaq.agent.android.trace.InterfaceC3322a;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.JSONObject;
import p001a.p058b.p062b.p063a.p064a.p067c.p071d.AbstractC0747d;
import p001a.p058b.p062b.p063a.p064a.p072d.C0749a;
import p001a.p058b.p062b.p063a.p064a.p072d.C0751c;

/* renamed from: a.b.b.a.a.c.a.h */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0731h extends AbstractC0747d {

    /* renamed from: b */
    public static final UAQ f2234b;

    /* renamed from: c */
    public final List<C0730g> f2235c = new ArrayList();

    /* renamed from: d */
    public InterfaceC3322a f2236d;

    static {
        C0751c c0751c = C0749a.f2299a;
        f2234b = UAQ.getInstance();
    }

    public C0731h(InterfaceC3322a interfaceC3322a) {
        this.f2236d = interfaceC3322a;
    }

    /* renamed from: a */
    public final List<C0730g> m22307a() {
        synchronized (this) {
            if (this.f2235c.size() == 0) {
                return Collections.emptyList();
            }
            ArrayList arrayList = new ArrayList(this.f2235c);
            this.f2235c.clear();
            return arrayList;
        }
    }

    /* renamed from: a */
    public synchronized void m22306a(C0730g c0730g) {
        this.f2235c.add(c0730g);
    }

    @Override // p001a.p058b.p062b.p063a.p064a.p067c.p071d.C0745b, com.baidu.uaq.agent.android.harvest.type.InterfaceC3319b
    /* renamed from: z */
    public JSONObject mo17429z() {
        List<C0730g> m22307a = m22307a();
        return m22307a.size() != 0 ? this.f2236d.mo17425b(m22307a) : new JSONObject();
    }
}
