package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b;

import java.io.IOException;
import java.io.OutputStream;

/* renamed from: a.a.a.a.a.e.a.b.j0 */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0163j0 extends C0176m1 {
    public C0163j0(OutputStream outputStream) {
        super(outputStream);
    }

    /* renamed from: a */
    public void m24123a(Object obj) {
        if (obj == null) {
            m23764e();
        } else if (obj instanceof AbstractC0258r) {
            ((AbstractC0258r) obj).mo22982a(this);
        } else if (obj instanceof InterfaceC0136d) {
            ((InterfaceC0136d) obj).mo23015d().mo22982a(this);
        } else {
            throw new IOException("object not BEREncodable");
        }
    }
}
