package com.networkbench.agent.impl.p255g;

import com.networkbench.agent.impl.harvest.Harvest;
import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.agent.impl.p255g.p256a.C6404a;
import com.networkbench.agent.impl.p255g.p256a.C6405b;
import com.networkbench.agent.impl.p255g.p256a.C6408e;
import com.networkbench.agent.impl.p255g.p257b.C6410a;
import com.networkbench.agent.impl.p255g.p257b.C6412c;
import com.networkbench.agent.impl.p255g.p258c.C6414a;
import com.networkbench.agent.impl.p255g.p258c.C6417d;
import com.networkbench.agent.impl.p255g.p258c.C6418e;
import com.networkbench.agent.impl.util.C6642k;
import com.networkbench.agent.impl.util.C6648q;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.g.g */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6422g {

    /* renamed from: b */
    private static final InterfaceC6393e f16238b = C6394f.m10150a();

    /* renamed from: a */
    static final C6413c f16237a = new C6413c();

    /* renamed from: c */
    private static final C6414a f16239c = new C6414a();

    /* renamed from: d */
    private static final C6417d f16240d = new C6417d();

    /* renamed from: e */
    private static final C6405b f16241e = new C6405b();

    /* renamed from: f */
    private static final C6404a f16242f = new C6404a();

    /* renamed from: g */
    private static final C6418e f16243g = new C6418e();

    /* renamed from: h */
    private static final C6408e f16244h = new C6408e(f16242f, f16241e);

    /* renamed from: i */
    private static boolean f16245i = true;

    /* renamed from: a */
    public static void m10014a() {
        f16238b.mo10122a("Measurement Engine initialized.");
        C6648q.m8777c();
        f16237a.m10029a(f16239c);
        f16237a.m10029a(f16240d);
        f16237a.m10030a(f16241e);
        f16237a.m10030a(f16242f);
        f16237a.m10029a(f16243g);
        f16237a.m10030a(f16244h);
        Harvest.addHarvestListener(f16242f);
        Harvest.addHarvestListener(f16241e);
        Harvest.addHarvestListener(f16244h);
    }

    /* renamed from: b */
    public static void m10010b() {
        C6648q.m8775d();
        f16238b.mo10122a("Measurement Engine shutting down.");
        f16237a.m10027b(f16239c);
        f16237a.m10027b(f16240d);
        f16237a.m10028b(f16241e);
        f16237a.m10028b(f16242f);
        f16237a.m10027b(f16243g);
        f16237a.m10028b(f16244h);
    }

    /* renamed from: a */
    public static void m10013a(C6410a c6410a) {
        if (c6410a == null || C6642k.m8905d(c6410a.m10060d())) {
            f16238b.mo10116d("TransactionMeasurement is null or hosname isNewlensHostName ");
            return;
        }
        f16240d.m10023a(c6410a);
        m10008d();
    }

    /* renamed from: a */
    public static void m10012a(C6412c c6412c) {
        if (c6412c == null) {
            f16238b.mo10116d("TransactionData is null. HttpError measurement not created.");
        } else if (c6412c.m10035c()) {
        } else {
            f16243g.mo10022a(c6412c);
        }
    }

    /* renamed from: a */
    public static void m10011a(boolean z) {
        f16245i = z;
    }

    /* renamed from: d */
    private static void m10008d() {
        if (f16245i) {
            m10009c();
        }
    }

    /* renamed from: c */
    public static void m10009c() {
        f16237a.m10031a();
    }
}
