package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b;

import com.p201hb.omapi.union.sim.org.bouncycastle.asn1.ASN1ParsingException;
import java.io.IOException;

/* renamed from: a.a.a.a.a.e.a.b.c0 */
/* loaded from: E:\567196_dexfile_execute.dex.fixout.dex */
public class C0133c0 implements InterfaceC0124a {

    /* renamed from: v3 */
    public final int f155v3;

    /* renamed from: w3 */
    public final C0371w f156w3;

    public C0133c0(int i, C0371w c0371w) {
        this.f155v3 = i;
        this.f156w3 = c0371w;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0124a
    /* renamed from: a */
    public InterfaceC0136d mo24190a() {
        return this.f156w3.m23495a();
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0152g2
    /* renamed from: b */
    public AbstractC0258r mo23007b() {
        return new C0129b0(this.f155v3, this.f156w3.m23491b());
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
