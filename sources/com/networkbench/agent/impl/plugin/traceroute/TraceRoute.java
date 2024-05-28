package com.networkbench.agent.impl.plugin.traceroute;

import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import java.util.ArrayList;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class TraceRoute {

    /* renamed from: a */
    protected static InterfaceC6393e f16910a = C6394f.m10150a();

    /* renamed from: b */
    private StringBuilder f16911b;

    /* renamed from: c */
    private InterfaceC6597a f16912c;

    public native int execute(Object[] objArr);

    static {
        try {
            System.loadLibrary("nbstraceroute");
        } catch (Exception e) {
            f16910a.mo10121a("load traceroute so Exception", e);
        } catch (UnsatisfiedLinkError e2) {
            f16910a.mo10121a("load traceroute so UnsatisfiedLinkError", e2);
        }
    }

    /* renamed from: a */
    public void m9298a(InterfaceC6597a interfaceC6597a) {
        this.f16912c = interfaceC6597a;
    }

    public void clearResult() {
        this.f16911b = null;
    }

    public void appendResult(String str) {
        if (this.f16911b == null) {
            this.f16911b = new StringBuilder();
        }
        this.f16911b.append(str);
        InterfaceC6597a interfaceC6597a = this.f16912c;
        if (interfaceC6597a != null) {
            interfaceC6597a.m9293a(str);
        }
    }

    /* renamed from: a */
    public C6598b m9297a(String str, Boolean bool) {
        final ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("traceroute");
        arrayList.add(str);
        if (bool.booleanValue()) {
            C6599c.m9288a().m9287a(new Runnable() { // from class: com.networkbench.agent.impl.plugin.traceroute.TraceRoute.1
                @Override // java.lang.Runnable
                public void run() {
                    TraceRoute.this.m9296a(arrayList);
                }
            });
            return null;
        }
        return m9296a(arrayList);
    }

    /* renamed from: a */
    public C6598b m9296a(ArrayList<String> arrayList) {
        StringBuilder sb;
        C6598b c6598b = new C6598b();
        f16910a.mo10122a("begin execute by so file");
        try {
            c6598b.f16917b = execute(arrayList.toArray());
        } catch (Throwable th) {
            f16910a.mo10121a("error invoke execute", th);
        }
        if (c6598b.f16917b == 0 && (sb = this.f16911b) != null) {
            c6598b.f16918c = sb.toString();
            this.f16912c.m9294a(c6598b);
        } else {
            c6598b.f16918c = "execute traceroute failed.";
            this.f16912c.m9295a(c6598b.f16917b, c6598b.f16918c);
        }
        return c6598b;
    }
}
