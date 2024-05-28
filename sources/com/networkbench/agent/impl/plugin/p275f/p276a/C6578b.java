package com.networkbench.agent.impl.plugin.p275f.p276a;

import com.networkbench.agent.impl.plugin.p275f.p276a.p277a.C6574b;
import com.networkbench.agent.impl.plugin.p275f.p276a.p277a.C6576c;
import com.networkbench.agent.impl.plugin.p275f.p276a.p277a.C6577d;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.plugin.f.a.b */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class C6578b {

    /* renamed from: a */
    private final String f16856a;

    /* renamed from: b */
    private final String f16857b;

    /* renamed from: c */
    private final InterfaceC6582c f16858c;

    /* renamed from: d */
    private final InterfaceC6580a f16859d;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.networkbench.agent.impl.plugin.f.a.b$a */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface InterfaceC6580a {
        /* renamed from: a */
        void mo9325a(C6581b c6581b);
    }

    private C6578b(String str, String str2, InterfaceC6582c interfaceC6582c, InterfaceC6580a interfaceC6580a) {
        this.f16856a = str;
        this.f16857b = str2;
        this.f16858c = interfaceC6582c;
        this.f16859d = interfaceC6580a;
    }

    /* renamed from: a */
    public static void m9327a(String str, InterfaceC6582c interfaceC6582c, InterfaceC6580a interfaceC6580a) {
        m9326a(str, null, interfaceC6582c, interfaceC6580a);
    }

    /* renamed from: a */
    public static void m9326a(String str, String str2, InterfaceC6582c interfaceC6582c, InterfaceC6580a interfaceC6580a) {
        String[] m9348b;
        if (str2 == null && (m9348b = C6572a.m9348b()) != null) {
            str2 = m9348b[0];
        }
        new C6578b(str, str2, interfaceC6582c, interfaceC6580a).m9328a();
    }

    /* renamed from: a */
    private void m9328a() {
        if (this.f16857b == null) {
            this.f16859d.mo9325a(new C6581b(-1, 0, null, "dns has an error ."));
            return;
        }
        this.f16858c.mo9317a("nslookup " + this.f16856a + " @" + this.f16857b);
        try {
            C6577d c6577d = new C6577d(InetAddress.getByName(this.f16857b));
            try {
                long currentTimeMillis = System.currentTimeMillis();
                C6576c[] m9330a = c6577d.m9330a(this.f16856a);
                long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
                for (C6576c c6576c : m9330a) {
                    this.f16858c.mo9317a(c6576c.toString());
                }
                this.f16859d.mo9325a(new C6581b(0, (int) currentTimeMillis2, m9330a, C6574b.m9343a(C6574b.f16824a.ordinal())));
            } catch (IOException e) {
                String message = e.getMessage();
                e.printStackTrace();
                this.f16859d.mo9325a(new C6581b(-3, 0, null, message == null ? e.toString() : message));
            }
        } catch (UnknownHostException e2) {
            C6581b c6581b = new C6581b(-1, 0, null, "nslookup server invalid : " + e2.getMessage());
            this.f16858c.mo9317a("nslookup server invalid");
            this.f16859d.mo9325a(c6581b);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.networkbench.agent.impl.plugin.f.a.b$b */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static class C6581b {

        /* renamed from: a */
        public final int f16860a;

        /* renamed from: b */
        public final int f16861b;

        /* renamed from: c */
        public final C6576c[] f16862c;

        /* renamed from: d */
        public final String f16863d;

        private C6581b(int i, int i2, C6576c[] c6576cArr, String str) {
            this.f16860a = i;
            this.f16861b = i2;
            this.f16862c = c6576cArr;
            this.f16863d = str;
        }
    }
}
