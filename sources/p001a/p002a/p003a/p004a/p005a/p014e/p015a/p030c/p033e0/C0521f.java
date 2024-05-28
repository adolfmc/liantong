package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p033e0;

import java.math.BigInteger;
import java.security.SecureRandom;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0542i;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0.C0578k0;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0.C0596u;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p047d.p048a.AbstractC0655f;

/* renamed from: a.a.a.a.a.e.a.c.e0.f */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0521f implements InterfaceC0523h {

    /* renamed from: a */
    public C0596u f1700a;

    /* renamed from: b */
    public SecureRandom f1701b;

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p033e0.InterfaceC0523h
    /* renamed from: a */
    public void mo22921a(InterfaceC0542i interfaceC0542i) {
        if (interfaceC0542i instanceof C0578k0) {
            C0578k0 c0578k0 = (C0578k0) interfaceC0542i;
            if (c0578k0.m22801a() instanceof C0596u) {
                this.f1700a = (C0596u) c0578k0.m22801a();
                this.f1701b = c0578k0.m22800b();
                return;
            }
            throw new IllegalArgumentException("ECPublicKeyParameters are required for new randomness transform.");
        } else if (interfaceC0542i instanceof C0596u) {
            this.f1700a = (C0596u) interfaceC0542i;
            this.f1701b = new SecureRandom();
        } else {
            throw new IllegalArgumentException("ECPublicKeyParameters are required for new randomness transform.");
        }
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p033e0.InterfaceC0523h
    /* renamed from: a */
    public C0522g mo22922a(C0522g c0522g) {
        C0596u c0596u = this.f1700a;
        if (c0596u != null) {
            BigInteger m22920a = C0524i.m22920a(c0596u.m22765b().m22772d(), this.f1701b);
            AbstractC0655f m22581a = this.f1700a.m22765b().m22774b().m22581a(m22920a);
            return new C0522g(c0522g.m22924b().mo22569a(m22581a), this.f1700a.m22757c().m22581a(m22920a).mo22569a(c0522g.m22923c()));
        }
        throw new IllegalStateException("ECNewRandomnessTransform not initialised");
    }
}
