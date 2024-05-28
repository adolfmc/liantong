package com.networkbench.agent.impl.p259h;

import com.networkbench.agent.impl.instrumentation.NBSTransactionState;
import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.BufferedSource;
import okio.Okio;
import okio.Source;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.h.e */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6428e extends ResponseBody {

    /* renamed from: e */
    private static final InterfaceC6393e f16261e = C6394f.m10150a();

    /* renamed from: a */
    private boolean f16262a;

    /* renamed from: b */
    private final ResponseBody f16263b;

    /* renamed from: c */
    private BufferedSource f16264c;

    /* renamed from: d */
    private NBSTransactionState f16265d;

    public C6428e(ResponseBody responseBody, NBSTransactionState nBSTransactionState, boolean z) {
        this.f16263b = responseBody;
        this.f16265d = nBSTransactionState;
        this.f16262a = z;
    }

    @Override // okhttp3.ResponseBody
    public MediaType contentType() {
        return this.f16263b.contentType();
    }

    @Override // okhttp3.ResponseBody
    public long contentLength() {
        ResponseBody responseBody = this.f16263b;
        if (responseBody != null) {
            return responseBody.contentLength();
        }
        return 0L;
    }

    @Override // okhttp3.ResponseBody
    public BufferedSource source() {
        if (this.f16264c == null) {
            this.f16264c = Okio.buffer(m9991a(this.f16263b.source()));
        }
        return this.f16264c;
    }

    /* renamed from: a */
    private Source m9991a(BufferedSource bufferedSource) {
        return new C6423a(this.f16265d, bufferedSource, this.f16262a, this.f16263b.contentLength());
    }

    @Override // okhttp3.ResponseBody, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.f16263b.close();
    }
}
