package org.p415a.p436e.p443b;

import java.math.BigInteger;
import org.p415a.p448g.C12969c;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.e.b.e */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12885e implements InterfaceC12887g {

    /* renamed from: a */
    protected final InterfaceC12882b f26145a;

    /* renamed from: b */
    protected final InterfaceC12886f f26146b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C12885e(InterfaceC12882b interfaceC12882b, InterfaceC12886f interfaceC12886f) {
        this.f26145a = interfaceC12882b;
        this.f26146b = interfaceC12886f;
    }

    @Override // org.p415a.p436e.p443b.InterfaceC12882b
    /* renamed from: a */
    public BigInteger mo724a() {
        return this.f26145a.mo724a();
    }

    @Override // org.p415a.p436e.p443b.InterfaceC12882b
    /* renamed from: b */
    public int mo723b() {
        return this.f26145a.mo723b() * this.f26146b.mo727a();
    }

    @Override // org.p415a.p436e.p443b.InterfaceC12887g
    /* renamed from: c */
    public InterfaceC12886f mo725c() {
        return this.f26146b;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof C12885e) {
            C12885e c12885e = (C12885e) obj;
            return this.f26145a.equals(c12885e.f26145a) && this.f26146b.equals(c12885e.f26146b);
        }
        return false;
    }

    public int hashCode() {
        return this.f26145a.hashCode() ^ C12969c.m401a(this.f26146b.hashCode(), 16);
    }
}
