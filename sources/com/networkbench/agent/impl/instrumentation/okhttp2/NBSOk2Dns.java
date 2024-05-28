package com.networkbench.agent.impl.instrumentation.okhttp2;

import com.networkbench.agent.impl.instrumentation.NBSTransactionState;
import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.agent.impl.util.C6638h;
import com.networkbench.agent.impl.util.C6642k;
import com.squareup.okhttp.Dns;
import com.squareup.okhttp.OkHttpClient;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class NBSOk2Dns implements Dns {
    private static final InterfaceC6393e log = C6394f.m10150a();
    private Dns impl;
    private NBSTransactionState transactionState;

    private NBSOk2Dns(Dns dns, NBSTransactionState nBSTransactionState) {
        this.impl = dns;
        this.transactionState = nBSTransactionState;
    }

    public void setTransactionState(NBSTransactionState nBSTransactionState) {
        this.transactionState = nBSTransactionState;
    }

    public List<InetAddress> lookup(String str) throws UnknownHostException {
        NBSTransactionState nBSTransactionState = this.transactionState;
        long currentTimeMillis = System.currentTimeMillis();
        List<InetAddress> lookup = this.impl.lookup(str);
        try {
            int currentTimeMillis2 = (int) (System.currentTimeMillis() - currentTimeMillis);
            InterfaceC6393e interfaceC6393e = log;
            interfaceC6393e.mo10122a("okhttp2 dns time:" + currentTimeMillis2 + ", hostname:" + str + ", pre dns:" + nBSTransactionState.getDnsElapse());
            if (nBSTransactionState.getUrl().contains(str) && nBSTransactionState.getDnsElapse() <= 0) {
                if (!C6642k.m8922a(currentTimeMillis2)) {
                    currentTimeMillis2 = -1;
                }
                nBSTransactionState.setDnsElapse(currentTimeMillis2);
            }
        } catch (Throwable unused) {
        }
        return lookup;
    }

    public static void replaceDefaultDns(OkHttpClient okHttpClient, NBSTransactionState nBSTransactionState) {
        if (okHttpClient == null) {
            return;
        }
        try {
            if (C6638h.m8963w().m9063X()) {
                if (okHttpClient.getDns() == null) {
                    okHttpClient.setDns(Dns.SYSTEM);
                }
                if (okHttpClient.getDns() != null) {
                    if (!(okHttpClient.getDns() instanceof NBSOk2Dns)) {
                        okHttpClient.setDns(new NBSOk2Dns(okHttpClient.getDns(), nBSTransactionState));
                    } else {
                        ((NBSOk2Dns) okHttpClient.getDns()).setTransactionState(nBSTransactionState);
                    }
                }
            }
        } catch (Throwable th) {
            log.mo10121a("replaceDefaultDns failed:", th);
        }
    }
}
