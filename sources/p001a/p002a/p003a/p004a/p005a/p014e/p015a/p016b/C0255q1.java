package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b;

import java.io.IOException;

/* renamed from: a.a.a.a.a.e.a.b.q1 */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0255q1 implements InterfaceC0298t {

    /* renamed from: v3 */
    public C0371w f471v3;

    public C0255q1(C0371w c0371w) {
        this.f471v3 = c0371w;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0298t
    /* renamed from: a */
    public InterfaceC0136d mo23612a() {
        return this.f471v3.m23495a();
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0152g2
    /* renamed from: b */
    public AbstractC0258r mo23007b() {
        return new C0184o1(this.f471v3.m23491b());
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.InterfaceC0136d
    /* renamed from: d */
    public AbstractC0258r mo23015d() {
        try {
            return mo23007b();
        } catch (IOException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }
}
