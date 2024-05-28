package org.p415a.p427d.p432e;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.p415a.p427d.C12642b;
import org.p415a.p427d.C12729n;
import org.p415a.p427d.InterfaceC12676c;
import org.p415a.p427d.p435h.C12709e;
import org.p415a.p427d.p435h.C12710f;
import org.p415a.p427d.p435h.C12713i;
import org.p415a.p427d.p435h.C12714j;
import org.p415a.p436e.p437a.AbstractC12877t;
import org.p415a.p436e.p437a.C12866i;
import org.p415a.p436e.p437a.InterfaceC12849c;
import org.p415a.p436e.p437a.InterfaceC12859f;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.d.e.a */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12694a implements InterfaceC12676c, InterfaceC12849c {

    /* renamed from: a */
    C12709e f25865a;

    /* renamed from: b */
    SecureRandom f25866b;

    @Override // org.p415a.p427d.InterfaceC12676c
    /* renamed from: a */
    public C12642b mo1364a() {
        BigInteger m1339b = this.f25865a.m1339b();
        int bitLength = m1339b.bitLength();
        int i = bitLength >>> 2;
        while (true) {
            BigInteger bigInteger = new BigInteger(bitLength, this.f25866b);
            if (bigInteger.compareTo(f26071e) >= 0 && bigInteger.compareTo(m1339b) < 0 && AbstractC12877t.m735c(bigInteger) >= i) {
                return new C12642b(new C12714j(m1362b().mo869a(this.f25865a.m1340a(), bigInteger), this.f25865a), new C12713i(bigInteger, this.f25865a));
            }
        }
    }

    /* renamed from: a */
    public void m1363a(C12729n c12729n) {
        C12710f c12710f = (C12710f) c12729n;
        this.f25866b = c12710f.m1308a();
        this.f25865a = c12710f.m1338b();
        if (this.f25866b == null) {
            this.f25866b = new SecureRandom();
        }
    }

    /* renamed from: b */
    protected InterfaceC12859f m1362b() {
        return new C12866i();
    }
}
