package org.p415a.p445f.p446a.p447a;

import java.io.IOException;
import java.io.OutputStream;
import org.p415a.p436e.p437a.AbstractC12860g;
import org.p415a.p445f.C12934f;
import org.p415a.p445f.p446a.InterfaceC12921e;
import org.p415a.p448g.p449a.C12964f;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.f.a.a.h */
/* loaded from: E:\11617560_dexfile_execute.dex */
class C12915h {

    /* renamed from: a */
    private static final byte[] f26163a = C12964f.m419a("416E6F6E796D6F75732053656E64657220202020");

    /* renamed from: b */
    private final InterfaceC12921e f26164b;

    /* renamed from: c */
    private final int f26165c;

    public C12915h(InterfaceC12921e interfaceC12921e, int i) {
        this.f26164b = interfaceC12921e;
        this.f26165c = i;
    }

    /* renamed from: a */
    private static int m492a(int i) {
        switch (i) {
            case 7:
                return 16;
            case 8:
                return 24;
            case 9:
                return 32;
            default:
                throw new C12934f("unknown symmetric algorithm ID: " + i);
        }
    }

    /* renamed from: a */
    private static byte[] m490a(InterfaceC12921e interfaceC12921e, AbstractC12860g abstractC12860g, int i, byte[] bArr) {
        byte[] m900l = abstractC12860g.m858f().m900l();
        OutputStream mo480b = interfaceC12921e.mo480b();
        mo480b.write(0);
        mo480b.write(0);
        mo480b.write(0);
        mo480b.write(1);
        mo480b.write(m900l);
        mo480b.write(bArr);
        byte[] bArr2 = new byte[i];
        System.arraycopy(interfaceC12921e.mo479c(), 0, bArr2, 0, bArr2.length);
        return bArr2;
    }

    /* renamed from: a */
    public byte[] m491a(AbstractC12860g abstractC12860g, byte[] bArr) {
        try {
            return m490a(this.f26164b, abstractC12860g, m492a(this.f26165c), bArr);
        } catch (IOException e) {
            throw new C12934f("Exception performing KDF: " + e.getMessage(), e);
        }
    }
}
