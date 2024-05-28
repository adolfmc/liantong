package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b;

import com.p201hb.omapi.union.sim.org.bouncycastle.asn1.ASN1ParsingException;
import java.io.IOException;
import java.io.InputStream;

/* renamed from: a.a.a.a.a.e.a.b.l1 */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0172l1 implements InterfaceC0234p {

    /* renamed from: v3 */
    public C0147f2 f210v3;

    public C0172l1(C0147f2 c0147f2) {
        this.f210v3 = c0147f2;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0152g2
    /* renamed from: b */
    public AbstractC0258r mo23007b() {
        return new C0168k1(this.f210v3.m24154b());
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0234p
    /* renamed from: c */
    public InputStream mo23845c() {
        return this.f210v3;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        try {
            return mo23007b();
        } catch (IOException e) {
            throw new ASN1ParsingException("IOException converting stream to byte array: " + e.getMessage(), e);
        }
    }
}
