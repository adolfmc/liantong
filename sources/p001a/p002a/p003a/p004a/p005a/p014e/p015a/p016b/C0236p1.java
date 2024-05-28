package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

/* renamed from: a.a.a.a.a.e.a.b.p1 */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0236p1 extends AbstractC0142e1 {

    /* renamed from: e */
    public final ByteArrayOutputStream f405e;

    public C0236p1(OutputStream outputStream) {
        super(outputStream);
        this.f405e = new ByteArrayOutputStream();
    }

    /* renamed from: a */
    public void m23843a(InterfaceC0136d interfaceC0136d) {
        interfaceC0136d.mo23015d().mo22982a(new C0176m1(this.f405e));
    }

    /* renamed from: b */
    public void m23842b() {
        m24166a(48, this.f405e.toByteArray());
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0158i
    /* renamed from: a */
    public OutputStream mo23844a() {
        return this.f405e;
    }

    public C0236p1(OutputStream outputStream, int i, boolean z) {
        super(outputStream, i, z);
        this.f405e = new ByteArrayOutputStream();
    }
}
