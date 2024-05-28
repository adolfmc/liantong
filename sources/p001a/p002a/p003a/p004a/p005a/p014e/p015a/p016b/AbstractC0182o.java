package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p049e.C0669a;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p049e.p050k.C0684f;

/* renamed from: a.a.a.a.a.e.a.b.o */
/* loaded from: E:\567196_dexfile_execute.dex.fixout.dex */
public abstract class AbstractC0182o extends AbstractC0258r implements InterfaceC0234p {

    /* renamed from: v3 */
    public byte[] f233v3;

    public AbstractC0182o(byte[] bArr) {
        if (bArr != null) {
            this.f233v3 = bArr;
            return;
        }
        throw new NullPointerException("string cannot be null");
    }

    /* renamed from: a */
    public static AbstractC0182o m24090a(AbstractC0494y abstractC0494y, boolean z) {
        AbstractC0258r m23004m = abstractC0494y.m23004m();
        if (!z && !(m23004m instanceof AbstractC0182o)) {
            return C0149g0.m24152a(AbstractC0263s.m23749a((Object) m23004m));
        }
        return m24089a((Object) m23004m);
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r
    /* renamed from: a */
    public abstract void mo22982a(C0252q c0252q);

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0152g2
    /* renamed from: b */
    public AbstractC0258r mo23007b() {
        return mo23015d();
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0234p
    /* renamed from: c */
    public InputStream mo23845c() {
        return new ByteArrayInputStream(this.f233v3);
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m
    public int hashCode() {
        return C0669a.m22472b(mo24088m());
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r
    /* renamed from: k */
    public AbstractC0258r mo23006k() {
        return new C0168k1(this.f233v3);
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r
    /* renamed from: l */
    public AbstractC0258r mo23005l() {
        return new C0168k1(this.f233v3);
    }

    /* renamed from: m */
    public byte[] mo24088m() {
        return this.f233v3;
    }

    /* renamed from: n */
    public InterfaceC0234p m24087n() {
        return this;
    }

    public String toString() {
        return "#" + new String(C0684f.m22413b(this.f233v3));
    }

    /* renamed from: a */
    public static AbstractC0182o m24089a(Object obj) {
        if (obj != null && !(obj instanceof AbstractC0182o)) {
            if (obj instanceof byte[]) {
                try {
                    return m24089a((Object) AbstractC0258r.m23755a((byte[]) obj));
                } catch (IOException e) {
                    throw new IllegalArgumentException("failed to construct OCTET STRING from byte[]: " + e.getMessage());
                }
            }
            if (obj instanceof InterfaceC0136d) {
                AbstractC0258r mo23015d = ((InterfaceC0136d) obj).mo23015d();
                if (mo23015d instanceof AbstractC0182o) {
                    return (AbstractC0182o) mo23015d;
                }
            }
            throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
        }
        return (AbstractC0182o) obj;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r
    /* renamed from: a */
    public boolean mo22981a(AbstractC0258r abstractC0258r) {
        if (abstractC0258r instanceof AbstractC0182o) {
            return C0669a.m22499a(this.f233v3, ((AbstractC0182o) abstractC0258r).f233v3);
        }
        return false;
    }
}
