package com.networkbench.agent.impl.p259h;

import com.networkbench.agent.impl.instrumentation.NBSTransactionState;
import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.agent.impl.p255g.p257b.C6412c;
import com.networkbench.agent.impl.util.C6648q;
import java.io.IOException;
import okio.BufferedSource;
import okio.ForwardingSource;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.h.a */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6423a extends ForwardingSource {

    /* renamed from: c */
    private static final InterfaceC6393e f16246c = C6394f.m10150a();

    /* renamed from: a */
    long f16247a;

    /* renamed from: b */
    boolean f16248b;

    /* renamed from: d */
    private NBSTransactionState f16249d;

    /* renamed from: e */
    private boolean f16250e;

    /* renamed from: f */
    private long f16251f;

    public C6423a(NBSTransactionState nBSTransactionState, BufferedSource bufferedSource, boolean z, long j) {
        super(bufferedSource);
        this.f16247a = 0L;
        this.f16248b = false;
        this.f16249d = nBSTransactionState;
        this.f16250e = z;
        this.f16251f = j;
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0033, code lost:
        if (m10005c() == false) goto L20;
     */
    @Override // okio.ForwardingSource, okio.Source
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public long read(okio.Buffer r5, long r6) throws java.io.IOException {
        /*
            r4 = this;
            long r5 = super.read(r5, r6)
            long r0 = r4.f16247a
            r2 = -1
            int r7 = (r5 > r2 ? 1 : (r5 == r2 ? 0 : -1))
            if (r7 == 0) goto Le
            r2 = r5
            goto L10
        Le:
            r2 = 0
        L10:
            long r0 = r0 + r2
            r4.f16247a = r0
            boolean r0 = r4.f16248b
            if (r0 != 0) goto L25
            com.networkbench.agent.impl.instrumentation.NBSTransactionState r0 = r4.f16249d
            if (r0 == 0) goto L25
            java.util.concurrent.ConcurrentLinkedQueue<java.lang.Object> r0 = com.networkbench.agent.impl.util.C6648q.f17235e
            com.networkbench.agent.impl.instrumentation.NBSTransactionState r1 = r4.f16249d
            r0.remove(r1)
            r0 = 1
            r4.f16248b = r0
        L25:
            if (r7 == 0) goto L35
            long r0 = r4.f16247a     // Catch: java.io.IOException -> L5d
            long r2 = r4.f16251f     // Catch: java.io.IOException -> L5d
            int r7 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r7 == 0) goto L35
            boolean r7 = r4.m10005c()     // Catch: java.io.IOException -> L5d
            if (r7 == 0) goto L5c
        L35:
            com.networkbench.agent.impl.instrumentation.NBSTransactionState r7 = r4.f16249d     // Catch: java.io.IOException -> L5d
            if (r7 == 0) goto L5c
            com.networkbench.agent.impl.f.e r7 = com.networkbench.agent.impl.p259h.C6423a.f16246c     // Catch: java.io.IOException -> L5d
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: java.io.IOException -> L5d
            r0.<init>()     // Catch: java.io.IOException -> L5d
            java.lang.String r1 = "complete totalBytesRead: "
            r0.append(r1)     // Catch: java.io.IOException -> L5d
            long r1 = r4.f16247a     // Catch: java.io.IOException -> L5d
            r0.append(r1)     // Catch: java.io.IOException -> L5d
            java.lang.String r1 = ", bytesRead:"
            r0.append(r1)     // Catch: java.io.IOException -> L5d
            r0.append(r5)     // Catch: java.io.IOException -> L5d
            java.lang.String r0 = r0.toString()     // Catch: java.io.IOException -> L5d
            r7.mo10122a(r0)     // Catch: java.io.IOException -> L5d
            r4.m10007a()     // Catch: java.io.IOException -> L5d
        L5c:
            return r5
        L5d:
            r5 = move-exception
            com.networkbench.agent.impl.instrumentation.NBSTransactionState r6 = r4.f16249d
            if (r6 == 0) goto L75
            r7 = 200(0xc8, float:2.8E-43)
            r6.setStatusCode(r7)
            com.networkbench.agent.impl.instrumentation.NBSTransactionState r6 = r4.f16249d
            r7 = 905(0x389, float:1.268E-42)
            java.lang.String r0 = r5.getMessage()
            r6.setErrorCode(r7, r0)
            r4.m10007a()
        L75:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.networkbench.agent.impl.p259h.C6423a.read(okio.Buffer, long):long");
    }

    /* renamed from: a */
    private void m10007a() {
        try {
            this.f16249d.setBytesReceived(this.f16247a);
            this.f16249d.setEndTime(System.currentTimeMillis());
            this.f16249d.end();
            if (this.f16249d == null) {
                return;
            }
            C6648q.m8781a(new C6412c(this.f16249d));
            this.f16249d = null;
        } catch (Throwable unused) {
        }
    }

    @Override // okio.ForwardingSource, okio.Source, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        m10006b();
        super.close();
    }

    /* renamed from: b */
    private void m10006b() {
        try {
            if (this.f16249d != null) {
                if (this.f16250e) {
                    this.f16249d.setStatusCode(200);
                    this.f16249d.setErrorCode(905, "ClientAbortException Content-Range");
                }
                m10007a();
            }
        } catch (Throwable th) {
            f16246c.mo10121a("addDataIfEndSuddenly", th);
        }
    }

    /* renamed from: c */
    private boolean m10005c() throws IOException {
        return ((BufferedSource) delegate()).exhausted();
    }
}
