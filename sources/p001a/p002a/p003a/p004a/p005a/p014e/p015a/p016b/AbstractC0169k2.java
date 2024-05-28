package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b;

import java.io.InputStream;

/* renamed from: a.a.a.a.a.e.a.b.k2 */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public abstract class AbstractC0169k2 extends InputStream {

    /* renamed from: C */
    public final InputStream f208C;

    /* renamed from: D */
    public int f209D;

    public AbstractC0169k2(InputStream inputStream, int i) {
        this.f208C = inputStream;
        this.f209D = i;
    }

    /* renamed from: a */
    public int mo24110a() {
        return this.f209D;
    }

    /* renamed from: a */
    public void m24109a(boolean z) {
        InputStream inputStream = this.f208C;
        if (inputStream instanceof C0157h2) {
            ((C0157h2) inputStream).m24141b(z);
        }
    }
}
