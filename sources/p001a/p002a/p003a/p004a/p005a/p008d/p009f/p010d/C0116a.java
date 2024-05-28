package p001a.p002a.p003a.p004a.p005a.p008d.p009f.p010d;

import java.math.BigInteger;
import p001a.p002a.p003a.p004a.p005a.p008d.C0112e;
import p001a.p002a.p003a.p004a.p005a.p008d.p009f.C0114b;
import p001a.p002a.p003a.p004a.p005a.p008d.p012g.C0119b;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.C0502b;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0.C0595t;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0.C0596u;

/* renamed from: a.a.a.a.a.d.f.d.a */
/* loaded from: E:\567196_dexfile_execute.dex.fixout.dex */
public class C0116a {

    /* renamed from: a */
    public byte[] f119a;

    /* renamed from: b */
    public byte[] f120b;

    /* renamed from: a */
    public void m24252a() {
        C0502b mo22888a = C0114b.m24262a().f116k.mo22888a();
        BigInteger m22758c = ((C0595t) mo22888a.m22974a()).m22758c();
        this.f119a = ((C0596u) mo22888a.m22973b()).m22757c().m22579c();
        this.f120b = m22758c.toByteArray();
    }

    /* renamed from: b */
    public void m24248b(byte[] bArr) {
        this.f119a = bArr;
    }

    /* renamed from: c */
    public byte[] m24247c() {
        return this.f119a;
    }

    /* renamed from: b */
    public byte[] m24249b() {
        return this.f120b;
    }

    /* renamed from: a */
    public void m24250a(byte[] bArr) {
        this.f120b = bArr;
    }

    /* renamed from: a */
    public void m24251a(String str, byte[] bArr) {
        C0119b c0119b = new C0119b();
        byte[] m24272g = C0112e.m24272g(str);
        int length = m24272g.length * 8;
        c0119b.m24221a(new byte[]{(byte) (65280 & length), (byte) (length & 255)}, 0, 2);
        c0119b.m24221a(m24272g, 0, m24272g.length);
        C0114b m24262a = C0114b.m24262a();
        byte[] m24272g2 = C0112e.m24272g(m24262a.f107b.toString(16));
        byte[] m24272g3 = C0112e.m24272g(m24262a.f108c.toString(16));
        c0119b.m24221a(m24272g2, 0, m24272g2.length);
        c0119b.m24221a(m24272g3, 0, m24272g3.length);
        byte[] m24272g4 = C0112e.m24272g(m24262a.f110e.toString(16));
        byte[] m24272g5 = C0112e.m24272g(m24262a.f111f.toString(16));
        c0119b.m24221a(m24272g4, 0, m24272g4.length);
        c0119b.m24221a(m24272g5, 0, m24272g5.length);
        byte[] bArr2 = this.f119a;
        c0119b.m24221a(bArr2, 1, bArr2.length - 1);
        c0119b.m24222a(bArr, 0);
    }
}
