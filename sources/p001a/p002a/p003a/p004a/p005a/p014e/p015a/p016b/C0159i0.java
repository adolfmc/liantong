package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b;

import com.p201hb.omapi.union.sim.org.bouncycastle.asn1.ASN1ParsingException;
import java.io.IOException;
import java.io.InputStream;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p049e.p051l.C0690a;

/* renamed from: a.a.a.a.a.e.a.b.i0 */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0159i0 implements InterfaceC0234p {

    /* renamed from: v3 */
    public C0371w f196v3;

    public C0159i0(C0371w c0371w) {
        this.f196v3 = c0371w;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0152g2
    /* renamed from: b */
    public AbstractC0258r mo23007b() {
        return new C0149g0(C0690a.m22389b(mo23845c()));
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0234p
    /* renamed from: c */
    public InputStream mo23845c() {
        return new C0265s0(this.f196v3);
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
