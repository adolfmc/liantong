package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b;

import com.p201hb.omapi.union.sim.org.bouncycastle.asn1.ASN1Exception;
import com.p201hb.omapi.union.sim.org.bouncycastle.asn1.ASN1ParsingException;
import java.io.IOException;

/* renamed from: a.a.a.a.a.e.a.b.a1 */
/* loaded from: E:\567196_dexfile_execute.dex.fixout.dex */
public class C0126a1 implements InterfaceC0136d, InterfaceC0152g2 {

    /* renamed from: v3 */
    public C0371w f151v3;

    public C0126a1(C0371w c0371w) {
        this.f151v3 = c0371w;
    }

    /* renamed from: a */
    public InterfaceC0136d m24196a() {
        return this.f151v3.m23495a();
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0152g2
    /* renamed from: b */
    public AbstractC0258r mo23007b() {
        try {
            return new C0498z0(this.f151v3.m23491b());
        } catch (IllegalArgumentException e) {
            throw new ASN1Exception(e.getMessage(), e);
        }
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        try {
            return mo23007b();
        } catch (IOException e) {
            throw new ASN1ParsingException("unable to get DER object", e);
        } catch (IllegalArgumentException e2) {
            throw new ASN1ParsingException("unable to get DER object", e2);
        }
    }
}
