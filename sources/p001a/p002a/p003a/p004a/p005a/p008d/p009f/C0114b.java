package p001a.p002a.p003a.p004a.p005a.p008d.p009f;

import java.math.BigInteger;
import java.security.SecureRandom;
import p001a.p002a.p003a.p004a.p005a.p008d.C0112e;
import p001a.p002a.p003a.p004a.p005a.p008d.p009f.p011e.C0117a;
import p001a.p002a.p003a.p004a.p005a.p008d.p012g.C0119b;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.C0502b;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p035g0.C0531a;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0.C0589q;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0.C0591r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0.C0595t;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0.C0596u;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p047d.p048a.AbstractC0648c;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p047d.p048a.AbstractC0651d;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p047d.p048a.AbstractC0655f;

/* renamed from: a.a.a.a.a.d.f.b */
/* loaded from: E:\567196_dexfile_execute.dex.fixout.dex */
public class C0114b {

    /* renamed from: n */
    public static String[] f105n = {"FFFFFFFEFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF00000000FFFFFFFFFFFFFFFF", "FFFFFFFEFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF00000000FFFFFFFFFFFFFFFC", "28E9FA9E9D9F5E344D5A9E4BCF6509A7F39789F515AB8F92DDBCBD414D940E93", "FFFFFFFEFFFFFFFFFFFFFFFFFFFFFFFF7203DF6B21C6052B53BBF40939D54123", "32C4AE2C1F1981195F9904466A39C9948FE30BBFF2660BE1715A4589334C74C7", "BC3736A2F4F6779C59BDCEE36B692153D0A9877CC62A474002DF32E52139F0A0"};

    /* renamed from: i */
    public final int f114i;

    /* renamed from: j */
    public final C0589q f115j;

    /* renamed from: k */
    public final C0531a f116k;

    /* renamed from: a */
    public final BigInteger f106a = new BigInteger(f105n[0], 16);

    /* renamed from: b */
    public final BigInteger f107b = new BigInteger(f105n[1], 16);

    /* renamed from: c */
    public final BigInteger f108c = new BigInteger(f105n[2], 16);

    /* renamed from: d */
    public final BigInteger f109d = new BigInteger(f105n[3], 16);

    /* renamed from: e */
    public final BigInteger f110e = new BigInteger(f105n[4], 16);

    /* renamed from: f */
    public final BigInteger f111f = new BigInteger(f105n[5], 16);

    /* renamed from: l */
    public final AbstractC0651d f117l = new AbstractC0651d.C0653b(this.f106a, this.f110e);

    /* renamed from: m */
    public final AbstractC0651d f118m = new AbstractC0651d.C0653b(this.f106a, this.f111f);

    /* renamed from: g */
    public final AbstractC0648c f112g = new AbstractC0648c.C0650b(this.f106a, this.f107b, this.f108c);

    /* renamed from: h */
    public final AbstractC0655f f113h = new AbstractC0655f.C0657b(this.f112g, this.f117l, this.f118m);

    public C0114b() {
        C0589q c0589q = new C0589q(this.f112g, this.f113h, this.f109d);
        this.f115j = c0589q;
        C0591r c0591r = new C0591r(c0589q, new SecureRandom());
        C0531a c0531a = new C0531a();
        this.f116k = c0531a;
        c0531a.mo22887a(c0591r);
        this.f114i = 127;
    }

    /* renamed from: a */
    public static C0114b m24262a() {
        return new C0114b();
    }

    /* renamed from: a */
    public byte[] m24261a(byte[] bArr, AbstractC0655f abstractC0655f) {
        C0119b c0119b = new C0119b();
        int length = bArr.length * 8;
        c0119b.m24224a((byte) ((length >> 8) & 65280));
        c0119b.m24224a((byte) (length & 255));
        c0119b.m24221a(bArr, 0, bArr.length);
        byte[] m24294a = C0112e.m24294a(this.f107b);
        c0119b.m24221a(m24294a, 0, m24294a.length);
        byte[] m24294a2 = C0112e.m24294a(this.f108c);
        c0119b.m24221a(m24294a2, 0, m24294a2.length);
        byte[] m24294a3 = C0112e.m24294a(this.f110e);
        c0119b.m24221a(m24294a3, 0, m24294a3.length);
        byte[] m24294a4 = C0112e.m24294a(this.f111f);
        c0119b.m24221a(m24294a4, 0, m24294a4.length);
        byte[] m24294a5 = C0112e.m24294a(abstractC0655f.m22578d().mo22584g());
        c0119b.m24221a(m24294a5, 0, m24294a5.length);
        byte[] m24294a6 = C0112e.m24294a(abstractC0655f.m22577e().mo22584g());
        c0119b.m24221a(m24294a6, 0, m24294a6.length);
        byte[] bArr2 = new byte[c0119b.m24225a()];
        c0119b.m24222a(bArr2, 0);
        return bArr2;
    }

    /* renamed from: a */
    public void m24259a(byte[] bArr, BigInteger bigInteger, AbstractC0655f abstractC0655f, C0117a c0117a) {
        BigInteger bigInteger2 = new BigInteger(C0112e.m24271g(bArr), 16);
        while (true) {
            C0502b mo22888a = this.f116k.mo22888a();
            BigInteger m22758c = ((C0595t) mo22888a.m22974a()).m22758c();
            BigInteger mod = bigInteger2.add(((C0596u) mo22888a.m22973b()).m22757c().m22578d().mo22584g()).mod(this.f109d);
            if (!mod.equals(BigInteger.ZERO) && !mod.add(m22758c).equals(this.f109d)) {
                BigInteger mod2 = bigInteger.add(BigInteger.ONE).modInverse(this.f109d).multiply(m22758c.subtract(mod.multiply(bigInteger)).mod(this.f109d)).mod(this.f109d);
                if (!mod2.equals(BigInteger.ZERO)) {
                    c0117a.f121a = mod;
                    c0117a.f122b = mod2;
                    return;
                }
            }
        }
    }

    /* renamed from: a */
    public void m24260a(byte[] bArr, AbstractC0655f abstractC0655f, BigInteger bigInteger, BigInteger bigInteger2, C0117a c0117a) {
        c0117a.f123c = null;
        BigInteger bigInteger3 = new BigInteger(C0112e.m24271g(bArr), 16);
        BigInteger mod = bigInteger.add(bigInteger2).mod(this.f109d);
        if (mod.equals(BigInteger.ZERO)) {
            return;
        }
        c0117a.f123c = bigInteger3.add(this.f113h.m22581a(c0117a.f122b).mo22569a(abstractC0655f.m22581a(mod)).m22578d().mo22584g()).mod(this.f109d);
    }
}
