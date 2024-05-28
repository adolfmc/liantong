package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b;

import com.p201hb.omapi.union.sim.org.bouncycastle.asn1.ASN1ParsingException;
import java.io.IOException;

/* renamed from: a.a.a.a.a.e.a.b.s1 */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0266s1 implements InterfaceC0358v {

    /* renamed from: v3 */
    public C0371w f560v3;

    public C0266s1(C0371w c0371w) {
        this.f560v3 = c0371w;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0358v
    /* renamed from: a */
    public InterfaceC0136d mo23561a() {
        return this.f560v3.m23495a();
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0152g2
    /* renamed from: b */
    public AbstractC0258r mo23007b() {
        return new C0260r1(this.f560v3.m23491b(), false);
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
