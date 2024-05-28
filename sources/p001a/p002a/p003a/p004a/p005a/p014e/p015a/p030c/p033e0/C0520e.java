package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p033e0;

import java.math.BigInteger;
import java.security.SecureRandom;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0542i;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0.C0578k0;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0.C0596u;

/* renamed from: a.a.a.a.a.e.a.c.e0.e */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0520e implements InterfaceC0523h {

    /* renamed from: a */
    public C0596u f1698a;

    /* renamed from: b */
    public SecureRandom f1699b;

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p033e0.InterfaceC0523h
    /* renamed from: a */
    public void mo22921a(InterfaceC0542i interfaceC0542i) {
        if (interfaceC0542i instanceof C0578k0) {
            C0578k0 c0578k0 = (C0578k0) interfaceC0542i;
            if (c0578k0.m22801a() instanceof C0596u) {
                this.f1698a = (C0596u) c0578k0.m22801a();
                this.f1699b = c0578k0.m22800b();
                return;
            }
            throw new IllegalArgumentException("ECPublicKeyParameters are required for new public key transform.");
        } else if (interfaceC0542i instanceof C0596u) {
            this.f1698a = (C0596u) interfaceC0542i;
            this.f1699b = new SecureRandom();
        } else {
            throw new IllegalArgumentException("ECPublicKeyParameters are required for new public key transform.");
        }
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p033e0.InterfaceC0523h
    /* renamed from: a */
    public C0522g mo22922a(C0522g c0522g) {
        C0596u c0596u = this.f1698a;
        if (c0596u != null) {
            BigInteger m22920a = C0524i.m22920a(c0596u.m22765b().m22772d(), this.f1699b);
            return new C0522g(this.f1698a.m22765b().m22774b().m22581a(m22920a), this.f1698a.m22757c().m22581a(m22920a).mo22569a(c0522g.m22923c()));
        }
        throw new IllegalStateException("ECNewPublicKeyTransform not initialised");
    }
}
