package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p035g0;

import java.math.BigInteger;
import java.security.SecureRandom;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.C0502b;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.C0639t;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0504c;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0.C0557a;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0.C0589q;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0.C0591r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0.C0595t;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0.C0596u;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p047d.p048a.InterfaceC0647b;

/* renamed from: a.a.a.a.a.e.a.c.g0.a */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0531a implements InterfaceC0504c, InterfaceC0647b {

    /* renamed from: f */
    public C0589q f1735f;

    /* renamed from: g */
    public SecureRandom f1736g;

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0504c
    /* renamed from: a */
    public void mo22887a(C0639t c0639t) {
        C0591r c0591r = (C0591r) c0639t;
        this.f1736g = c0591r.m22659a();
        this.f1735f = c0591r.m22768c();
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0504c
    /* renamed from: a */
    public C0502b mo22888a() {
        BigInteger m22772d = this.f1735f.m22772d();
        int bitLength = m22772d.bitLength();
        while (true) {
            BigInteger bigInteger = new BigInteger(bitLength, this.f1736g);
            if (!bigInteger.equals(InterfaceC0647b.f1976a) && bigInteger.compareTo(m22772d) < 0) {
                return new C0502b((C0557a) new C0596u(this.f1735f.m22774b().m22581a(bigInteger), this.f1735f), (C0557a) new C0595t(bigInteger, this.f1735f));
            }
        }
    }
}
