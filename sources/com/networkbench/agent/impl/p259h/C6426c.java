package com.networkbench.agent.impl.p259h;

import com.networkbench.agent.impl.instrumentation.NBSTransactionState;
import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.agent.impl.socket.C6621r;
import com.networkbench.agent.impl.util.C6638h;
import com.networkbench.agent.impl.util.C6642k;
import com.networkbench.agent.impl.util.C6645n;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import okhttp3.Dns;
import okhttp3.OkHttpClient;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.h.c */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6426c implements Dns {

    /* renamed from: c */
    private static final InterfaceC6393e f16255c = C6394f.m10150a();

    /* renamed from: a */
    private Dns f16256a;

    /* renamed from: b */
    private NBSTransactionState f16257b;

    public C6426c(Dns dns, NBSTransactionState nBSTransactionState) {
        this.f16256a = dns;
        this.f16257b = nBSTransactionState;
    }

    /* renamed from: a */
    public void m9999a(NBSTransactionState nBSTransactionState) {
        this.f16257b = nBSTransactionState;
    }

    @Override // okhttp3.Dns
    public List<InetAddress> lookup(String str) throws UnknownHostException {
        NBSTransactionState nBSTransactionState = this.f16257b;
        long currentTimeMillis = System.currentTimeMillis();
        List<InetAddress> lookup = this.f16256a.lookup(str);
        try {
            int currentTimeMillis2 = (int) (System.currentTimeMillis() - currentTimeMillis);
            InterfaceC6393e interfaceC6393e = f16255c;
            interfaceC6393e.mo10122a("okhttp3 dns time:" + currentTimeMillis2 + ", hostname:" + str + ", pre dns:" + nBSTransactionState.getDnsElapse());
            if (nBSTransactionState.getUrl().contains(str) && nBSTransactionState.getDnsElapse() <= 0) {
                if (!C6642k.m8922a(currentTimeMillis2)) {
                    currentTimeMillis2 = -1;
                }
                nBSTransactionState.setDnsElapse(currentTimeMillis2);
            }
            if (lookup.size() > 0) {
                C6621r.f17047b.put(str, lookup.get(0).getHostAddress());
            }
        } catch (Throwable unused) {
            f16255c.mo10115e("error happened in set dns time in ok3");
        }
        return lookup;
    }

    /* renamed from: a */
    public static void m9998a(OkHttpClient okHttpClient, NBSTransactionState nBSTransactionState) {
        if (okHttpClient == null) {
            return;
        }
        try {
            if (C6638h.m8963w().m9063X() && okHttpClient.dns() != null) {
                if (!(okHttpClient.dns() instanceof C6426c)) {
                    C6645n.m8876a(OkHttpClient.class.getDeclaredField("dns"), okHttpClient, new C6426c(okHttpClient.dns(), nBSTransactionState));
                } else {
                    ((C6426c) okHttpClient.dns()).m9999a(nBSTransactionState);
                }
            }
        } catch (Throwable th) {
            f16255c.mo10121a("replaceDefaultDns failed:", th);
        }
    }
}
