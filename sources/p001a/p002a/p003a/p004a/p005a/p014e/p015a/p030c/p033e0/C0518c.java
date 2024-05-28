package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p033e0;

import java.math.BigInteger;
import java.security.SecureRandom;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0542i;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0.C0578k0;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0.C0596u;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p047d.p048a.AbstractC0655f;

/* renamed from: a.a.a.a.a.e.a.c.e0.c */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0518c implements InterfaceC0519d {

    /* renamed from: a */
    public C0596u f1696a;

    /* renamed from: b */
    public SecureRandom f1697b;

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p033e0.InterfaceC0519d
    /* renamed from: a */
    public void mo22927a(InterfaceC0542i interfaceC0542i) {
        if (interfaceC0542i instanceof C0578k0) {
            C0578k0 c0578k0 = (C0578k0) interfaceC0542i;
            if (c0578k0.m22801a() instanceof C0596u) {
                this.f1696a = (C0596u) c0578k0.m22801a();
                this.f1697b = c0578k0.m22800b();
                return;
            }
            throw new IllegalArgumentException("ECPublicKeyParameters are required for encryption.");
        } else if (interfaceC0542i instanceof C0596u) {
            this.f1696a = (C0596u) interfaceC0542i;
            this.f1697b = new SecureRandom();
        } else {
            throw new IllegalArgumentException("ECPublicKeyParameters are required for encryption.");
        }
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p033e0.InterfaceC0519d
    /* renamed from: a */
    public C0522g mo22926a(AbstractC0655f abstractC0655f) {
        C0596u c0596u = this.f1696a;
        if (c0596u != null) {
            BigInteger m22920a = C0524i.m22920a(c0596u.m22765b().m22772d(), this.f1697b);
            return new C0522g(this.f1696a.m22765b().m22774b().m22581a(m22920a), this.f1696a.m22757c().m22581a(m22920a).mo22569a(abstractC0655f));
        }
        throw new IllegalStateException("ECElGamalEncryptor not initialised");
    }
}
