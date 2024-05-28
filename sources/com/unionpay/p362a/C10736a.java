package com.unionpay.p362a;

import java.io.IOException;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

/* renamed from: com.unionpay.a.a */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C10736a {

    /* renamed from: a */
    private SSLContext f20635a = null;

    /* renamed from: b */
    private String f20636b;

    public C10736a(String str) {
        this.f20636b = str;
    }

    /* renamed from: a */
    private static SSLContext m5965a(String str) {
        try {
            SSLContext sSLContext = SSLContext.getInstance("TLS");
            sSLContext.init(null, new TrustManager[]{new C10737b(str)}, null);
            return sSLContext;
        } catch (Exception e) {
            throw new IOException(e.getMessage());
        }
    }

    /* renamed from: a */
    public final SSLContext m5966a() {
        if (this.f20635a == null) {
            this.f20635a = m5965a(this.f20636b);
        }
        return this.f20635a;
    }

    public boolean equals(Object obj) {
        return obj != null && obj.getClass().equals(C10736a.class);
    }

    public int hashCode() {
        return C10736a.class.hashCode();
    }
}
