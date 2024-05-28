package org.p415a.p418b.p420b;

import java.math.BigInteger;
import java.util.Hashtable;
import org.p415a.p418b.C12563o;
import org.p415a.p418b.p423e.AbstractC12548e;
import org.p415a.p418b.p423e.C12547d;
import org.p415a.p418b.p423e.C12549f;
import org.p415a.p436e.p437a.AbstractC12850d;
import org.p415a.p448g.C12975h;
import org.p415a.p448g.p449a.C12964f;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.b.b.a */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12437a {

    /* renamed from: a */
    static AbstractC12548e f25144a = new AbstractC12548e() { // from class: org.a.b.b.a.1
        @Override // org.p415a.p418b.p423e.AbstractC12548e
        /* renamed from: a */
        public C12547d mo1459a() {
            BigInteger m1715b = C12437a.m1715b("FFFFFFFEFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF00000000FFFFFFFFFFFFFFFF");
            BigInteger m1715b2 = C12437a.m1715b("FFFFFFFEFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF00000000FFFFFFFFFFFFFFFC");
            BigInteger m1715b3 = C12437a.m1715b("28E9FA9E9D9F5E344D5A9E4BCF6509A7F39789F515AB8F92DDBCBD414D940E93");
            BigInteger m1715b4 = C12437a.m1715b("FFFFFFFEFFFFFFFFFFFFFFFFFFFFFFFF7203DF6B21C6052B53BBF40939D54123");
            BigInteger valueOf = BigInteger.valueOf(1L);
            AbstractC12850d m1714b = C12437a.m1714b(new AbstractC12850d.C12855e(m1715b, m1715b2, m1715b3, m1715b4, valueOf));
            return new C12547d(m1714b, new C12549f(m1714b, C12964f.m419a("0432C4AE2C1F1981195F9904466A39C9948FE30BBFF2660BE1715A4589334C74C7BC3736A2F4F6779C59BDCEE36B692153D0A9877CC62A474002DF32E52139F0A0")), m1715b4, valueOf, null);
        }
    };

    /* renamed from: b */
    static AbstractC12548e f25145b = new AbstractC12548e() { // from class: org.a.b.b.a.2
        @Override // org.p415a.p418b.p423e.AbstractC12548e
        /* renamed from: a */
        public C12547d mo1459a() {
            BigInteger m1715b = C12437a.m1715b("BDB6F4FE3E8B1D9E0DA8C0D46F4C318CEFE4AFE3B6B8551F");
            BigInteger m1715b2 = C12437a.m1715b("BB8E5E8FBC115E139FE6A814FE48AAA6F0ADA1AA5DF91985");
            BigInteger m1715b3 = C12437a.m1715b("1854BEBDC31B21B7AEFC80AB0ECD10D5B1B3308E6DBF11C1");
            BigInteger m1715b4 = C12437a.m1715b("BDB6F4FE3E8B1D9E0DA8C0D40FC962195DFAE76F56564677");
            BigInteger valueOf = BigInteger.valueOf(1L);
            AbstractC12850d m1714b = C12437a.m1714b(new AbstractC12850d.C12855e(m1715b, m1715b2, m1715b3, m1715b4, valueOf));
            return new C12547d(m1714b, new C12549f(m1714b, C12964f.m419a("044AD5F7048DE709AD51236DE65E4D4B482C836DC6E410664002BB3A02D4AAADACAE24817A4CA3A1B014B5270432DB27D2")), m1715b4, valueOf, null);
        }
    };

    /* renamed from: c */
    static final Hashtable f25146c = new Hashtable();

    /* renamed from: d */
    static final Hashtable f25147d = new Hashtable();

    /* renamed from: e */
    static final Hashtable f25148e = new Hashtable();

    static {
        m1718a("wapip192v1", InterfaceC12440b.f25158J, f25145b);
        m1718a("sm2p256v1", InterfaceC12440b.f25154F, f25144a);
    }

    /* renamed from: a */
    public static C12547d m1717a(C12563o c12563o) {
        AbstractC12548e abstractC12548e = (AbstractC12548e) f25147d.get(c12563o);
        if (abstractC12548e == null) {
            return null;
        }
        return abstractC12548e.m1665b();
    }

    /* renamed from: a */
    static void m1718a(String str, C12563o c12563o, AbstractC12548e abstractC12548e) {
        f25146c.put(C12975h.m387b(str), c12563o);
        f25148e.put(c12563o, str);
        f25147d.put(c12563o, abstractC12548e);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public static BigInteger m1715b(String str) {
        return new BigInteger(1, C12964f.m419a(str));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public static AbstractC12850d m1714b(AbstractC12850d abstractC12850d) {
        return abstractC12850d;
    }
}
