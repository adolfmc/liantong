package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p033e0;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0542i;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0.C0595t;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p047d.p048a.AbstractC0655f;

/* renamed from: a.a.a.a.a.e.a.c.e0.b */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0517b implements InterfaceC0516a {

    /* renamed from: a */
    public C0595t f1695a;

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p033e0.InterfaceC0516a
    /* renamed from: a */
    public void mo22928a(InterfaceC0542i interfaceC0542i) {
        if (interfaceC0542i instanceof C0595t) {
            this.f1695a = (C0595t) interfaceC0542i;
            return;
        }
        throw new IllegalArgumentException("ECPrivateKeyParameters are required for decryption.");
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p033e0.InterfaceC0516a
    /* renamed from: a */
    public AbstractC0655f mo22929a(C0522g c0522g) {
        if (this.f1695a != null) {
            return c0522g.m22923c().mo22569a(c0522g.m22924b().m22581a(this.f1695a.m22758c()).mo22566h());
        }
        throw new IllegalStateException("ECElGamalDecryptor not initialised");
    }
}
