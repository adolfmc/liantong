package com.bytedance.pangle.res.p181a;

import java.io.InputStream;

/* renamed from: com.bytedance.pangle.res.a.e */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public final class C3913e extends AbstractC3918j {

    /* renamed from: a */
    private long f9328a;

    public C3913e(InputStream inputStream) {
        super(inputStream);
    }

    @Override // com.bytedance.pangle.res.p181a.AbstractC3918j, java.io.FilterInputStream, java.io.InputStream
    public final synchronized long skip(long j) {
        long skip;
        skip = super.skip(j);
        this.f9328a += skip;
        return skip;
    }

    @Override // com.bytedance.pangle.res.p181a.AbstractC3918j
    /* renamed from: a */
    protected final synchronized void mo16690a(int i) {
        if (i != -1) {
            this.f9328a += i;
        }
    }

    /* renamed from: a */
    public final int m16701a() {
        long m16700b = m16700b();
        if (m16700b <= 2147483647L) {
            return (int) m16700b;
        }
        throw new ArithmeticException("The byte count " + m16700b + " is too large to be converted to an int");
    }

    /* renamed from: b */
    public final synchronized long m16700b() {
        return this.f9328a;
    }
}
