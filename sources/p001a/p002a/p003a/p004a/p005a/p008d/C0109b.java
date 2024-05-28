package p001a.p002a.p003a.p004a.p005a.p008d;

import java.math.BigInteger;
import p001a.p002a.p003a.p004a.p005a.p008d.p009f.C0114b;
import p001a.p002a.p003a.p004a.p005a.p008d.p012g.C0119b;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.C0502b;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0.C0595t;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0.C0596u;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p047d.p048a.AbstractC0651d;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p047d.p048a.AbstractC0655f;

/* renamed from: a.a.a.a.a.d.b */
/* loaded from: E:\567196_dexfile_execute.dex.fixout.dex */
public class C0109b {

    /* renamed from: a */
    public BigInteger f90a;

    /* renamed from: b */
    public AbstractC0655f f91b;

    /* renamed from: c */
    public byte[] f92c;

    /* renamed from: d */
    public byte[] f93d;

    /* renamed from: e */
    public int f94e;

    /* renamed from: a */
    public void m24318a(byte[] bArr) {
        C0114b m24262a = C0114b.m24262a();
        this.f94e = m24262a.f114i;
        C0502b mo22888a = m24262a.f116k.mo22888a();
        BigInteger m22758c = ((C0595t) mo22888a.m22974a()).m22758c();
        AbstractC0655f m22757c = ((C0596u) mo22888a.m22973b()).m22757c();
        this.f91b = m22757c;
        m24316a(bArr, m24321a(this.f94e, m22757c.m22578d().mo22584g()), m22758c);
    }

    /* renamed from: a */
    public void m24317a(byte[] bArr, String str, byte[] bArr2) {
        C0114b m24262a = C0114b.m24262a();
        this.f94e = m24262a.f114i;
        BigInteger bigInteger = new BigInteger(str, 16);
        AbstractC0655f m22621a = m24262a.f112g.m22621a(bArr2);
        this.f91b = m22621a;
        m24316a(bArr, m24321a(this.f94e, m22621a.m22578d().mo22584g()), bigInteger);
    }

    /* renamed from: a */
    public BigInteger m24321a(int i, BigInteger bigInteger) {
        return new BigInteger(m24322a(i, "0"), 2).add(bigInteger.and(new BigInteger(m24322a(i - 1, "1"), 2)));
    }

    /* renamed from: a */
    public String m24322a(int i, String str) {
        String str2 = "1";
        for (int i2 = 0; i2 < i; i2++) {
            str2 = str2 + str;
        }
        return str2;
    }

    /* renamed from: a */
    public void m24316a(byte[] bArr, BigInteger bigInteger, BigInteger bigInteger2) {
        this.f90a = bigInteger.multiply(bigInteger2).add(new BigInteger(C0112e.m24271g(bArr), 16)).mod(C0114b.m24262a().f109d);
    }

    /* renamed from: a */
    public String m24320a(AbstractC0655f abstractC0655f, AbstractC0655f abstractC0655f2, byte[] bArr, byte[] bArr2) {
        AbstractC0655f m24319a = m24319a(m24321a(this.f94e, abstractC0655f2.m22578d().mo22584g()), abstractC0655f, abstractC0655f2);
        this.f92c = C0112e.m24294a(m24319a.m22578d().mo22584g());
        this.f93d = C0112e.m24294a(m24319a.m22577e().mo22584g());
        byte[] bArr3 = new byte[32];
        m24315a(bArr3, bArr, bArr2);
        byte[] bArr4 = new byte[32];
        System.arraycopy(bArr3, 0, bArr4, 0, 32);
        return C0112e.m24279c(bArr4, true);
    }

    /* renamed from: a */
    public void m24315a(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        C0119b c0119b = new C0119b();
        byte[] bArr4 = this.f92c;
        c0119b.m24221a(bArr4, 0, bArr4.length);
        byte[] bArr5 = this.f93d;
        c0119b.m24221a(bArr5, 0, bArr5.length);
        c0119b.m24221a(bArr2, 0, bArr2.length);
        c0119b.m24221a(bArr3, 0, bArr3.length);
        byte b = (byte) 0;
        c0119b.m24224a(b);
        c0119b.m24224a(b);
        c0119b.m24224a(b);
        c0119b.m24224a((byte) 1);
        c0119b.m24222a(bArr, 0);
    }

    /* renamed from: a */
    public AbstractC0655f m24319a(BigInteger bigInteger, AbstractC0655f abstractC0655f, AbstractC0655f abstractC0655f2) {
        C0114b m24262a = C0114b.m24262a();
        BigInteger mo22584g = abstractC0655f.m22578d().mo22584g();
        BigInteger mo22584g2 = abstractC0655f.m22577e().mo22584g();
        AbstractC0651d.C0653b c0653b = new AbstractC0651d.C0653b(m24262a.f106a, mo22584g);
        AbstractC0651d.C0653b c0653b2 = new AbstractC0651d.C0653b(m24262a.f106a, mo22584g2);
        return new AbstractC0655f.C0657b(m24262a.f112g, c0653b, c0653b2).mo22569a(abstractC0655f2.m22581a(bigInteger)).m22581a(this.f90a);
    }
}
