package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b;

import com.p201hb.omapi.union.sim.org.bouncycastle.asn1.ASN1ParsingException;
import java.io.IOException;

/* renamed from: a.a.a.a.a.e.a.b.q0 */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0254q0 implements InterfaceC0497z {

    /* renamed from: v3 */
    public boolean f468v3;

    /* renamed from: w3 */
    public int f469w3;

    /* renamed from: x3 */
    public C0371w f470x3;

    public C0254q0(boolean z, int i, C0371w c0371w) {
        this.f468v3 = z;
        this.f469w3 = i;
        this.f470x3 = c0371w;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0497z
    /* renamed from: a */
    public InterfaceC0136d mo22995a(int i, boolean z) {
        if (z) {
            if (this.f468v3) {
                return this.f470x3.m23495a();
            }
            throw new IOException("Explicit tags must be constructed (see X.690 8.14.2)");
        }
        return this.f470x3.m23492a(this.f468v3, i);
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0152g2
    /* renamed from: b */
    public AbstractC0258r mo23007b() {
        return this.f470x3.m23490b(this.f468v3, this.f469w3);
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        try {
            return mo23007b();
        } catch (IOException e) {
            throw new ASN1ParsingException(e.getMessage());
        }
    }

    /* renamed from: e */
    public boolean m23762e() {
        return this.f468v3;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0497z
    /* renamed from: f */
    public int mo22994f() {
        return this.f469w3;
    }
}
