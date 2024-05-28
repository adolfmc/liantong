package p001a.p002a.p003a.p004a.p005a.p008d.p009f;

import java.math.BigInteger;
import p001a.p002a.p003a.p004a.p005a.p008d.C0112e;
import p001a.p002a.p003a.p004a.p005a.p008d.p012g.C0119b;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.C0502b;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0.C0595t;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0.C0596u;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p047d.p048a.AbstractC0655f;

/* renamed from: a.a.a.a.a.d.f.a */
/* loaded from: E:\567196_dexfile_execute.dex.fixout.dex */
public class C0113a {

    /* renamed from: b */
    public AbstractC0655f f100b;

    /* renamed from: c */
    public C0119b f101c;

    /* renamed from: d */
    public C0119b f102d;

    /* renamed from: a */
    public int f99a = 1;

    /* renamed from: e */
    public byte[] f103e = new byte[32];

    /* renamed from: f */
    public byte f104f = 0;

    /* renamed from: a */
    private void m24269a() {
        C0119b c0119b = new C0119b(this.f101c);
        c0119b.m24224a((byte) ((this.f99a >> 24) & 255));
        c0119b.m24224a((byte) ((this.f99a >> 16) & 255));
        c0119b.m24224a((byte) ((this.f99a >> 8) & 255));
        c0119b.m24224a((byte) (this.f99a & 255));
        c0119b.m24222a(this.f103e, 0);
        this.f104f = (byte) 0;
        this.f99a++;
    }

    /* renamed from: b */
    private void m24265b() {
        this.f101c = new C0119b();
        this.f102d = new C0119b();
        byte[] m24294a = C0112e.m24294a(this.f100b.m22578d().mo22584g());
        this.f101c.m24221a(m24294a, 0, m24294a.length);
        this.f102d.m24221a(m24294a, 0, m24294a.length);
        byte[] m24294a2 = C0112e.m24294a(this.f100b.m22577e().mo22584g());
        this.f101c.m24221a(m24294a2, 0, m24294a2.length);
        this.f99a = 1;
        m24269a();
    }

    /* renamed from: c */
    public void m24263c(byte[] bArr) {
        this.f102d.m24221a(bArr, 0, bArr.length);
        for (int i = 0; i < bArr.length; i++) {
            if (this.f104f == this.f103e.length) {
                m24269a();
            }
            byte b = bArr[i];
            byte[] bArr2 = this.f103e;
            byte b2 = this.f104f;
            this.f104f = (byte) (b2 + 1);
            bArr[i] = (byte) (b ^ bArr2[b2]);
        }
    }

    /* renamed from: a */
    public AbstractC0655f m24268a(C0114b c0114b, AbstractC0655f abstractC0655f) {
        C0502b mo22888a = c0114b.f116k.mo22888a();
        BigInteger m22758c = ((C0595t) mo22888a.m22974a()).m22758c();
        AbstractC0655f m22757c = ((C0596u) mo22888a.m22973b()).m22757c();
        this.f100b = abstractC0655f.m22581a(m22758c);
        m24265b();
        return m22757c;
    }

    /* renamed from: b */
    public void m24264b(byte[] bArr) {
        byte[] m24294a = C0112e.m24294a(this.f100b.m22577e().mo22584g());
        this.f102d.m24221a(m24294a, 0, m24294a.length);
        this.f102d.m24222a(bArr, 0);
        m24265b();
    }

    /* renamed from: a */
    public void m24267a(BigInteger bigInteger, AbstractC0655f abstractC0655f) {
        this.f100b = abstractC0655f.m22581a(bigInteger);
        m24265b();
    }

    /* renamed from: a */
    public void m24266a(byte[] bArr) {
        for (int i = 0; i < bArr.length; i++) {
            if (this.f104f == this.f103e.length) {
                m24269a();
            }
            byte b = bArr[i];
            byte[] bArr2 = this.f103e;
            byte b2 = this.f104f;
            this.f104f = (byte) (b2 + 1);
            bArr[i] = (byte) (b ^ bArr2[b2]);
        }
        this.f102d.m24221a(bArr, 0, bArr.length);
    }
}
