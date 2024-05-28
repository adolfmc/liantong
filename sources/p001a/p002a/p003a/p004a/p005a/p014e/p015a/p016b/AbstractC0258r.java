package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b;

import java.io.IOException;

/* renamed from: a.a.a.a.a.e.a.b.r */
/* loaded from: E:\567196_dexfile_execute.dex.fixout.dex */
public abstract class AbstractC0258r extends AbstractC0174m {
    /* renamed from: a */
    public static AbstractC0258r m23755a(byte[] bArr) {
        try {
            return new C0162j(bArr).m24124d();
        } catch (ClassCastException unused) {
            throw new IOException("cannot recognise object in stream");
        }
    }

    /* renamed from: a */
    public abstract void mo22982a(C0252q c0252q);

    /* renamed from: a */
    public abstract boolean mo22981a(AbstractC0258r abstractC0258r);

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        return this;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof InterfaceC0136d) && mo22981a(((InterfaceC0136d) obj).mo23015d());
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m
    public abstract int hashCode();

    /* renamed from: i */
    public abstract int mo22977i();

    /* renamed from: j */
    public abstract boolean mo22976j();

    /* renamed from: k */
    public AbstractC0258r mo23006k() {
        return this;
    }

    /* renamed from: l */
    public AbstractC0258r mo23005l() {
        return this;
    }
}
