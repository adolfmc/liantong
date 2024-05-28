package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b;

import com.p201hb.omapi.union.sim.org.bouncycastle.asn1.ASN1ParsingException;
import java.io.IOException;

/* renamed from: a.a.a.a.a.e.a.b.o0 */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0183o0 implements InterfaceC0358v {

    /* renamed from: v3 */
    public C0371w f234v3;

    public C0183o0(C0371w c0371w) {
        this.f234v3 = c0371w;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0358v
    /* renamed from: a */
    public InterfaceC0136d mo23561a() {
        return this.f234v3.m23495a();
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0152g2
    /* renamed from: b */
    public AbstractC0258r mo23007b() {
        return new C0179n0(this.f234v3.m23491b());
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        try {
            return mo23007b();
        } catch (IOException e) {
            throw new ASN1ParsingException(e.getMessage(), e);
        }
    }
}
