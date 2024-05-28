package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b;

import java.io.InputStream;
import java.io.OutputStream;

/* renamed from: a.a.a.a.a.e.a.b.f0 */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0145f0 extends AbstractC0158i {

    /* renamed from: b */
    public boolean f173b;

    /* renamed from: c */
    public boolean f174c;

    /* renamed from: d */
    public int f175d;

    public C0145f0(OutputStream outputStream) {
        super(outputStream);
        this.f173b = false;
    }

    /* renamed from: b */
    private void m24159b(int i) {
        this.f195a.write(i);
        this.f195a.write(128);
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0158i
    /* renamed from: a */
    public OutputStream mo23844a() {
        return this.f195a;
    }

    /* renamed from: a */
    public void m24162a(int i) {
        if (this.f173b) {
            int i2 = this.f175d | 128;
            if (this.f174c) {
                m24159b(i2 | 32);
                m24159b(i);
                return;
            } else if ((i & 32) != 0) {
                m24159b(i2 | 32);
                return;
            } else {
                m24159b(i2);
                return;
            }
        }
        m24159b(i);
    }

    public C0145f0(OutputStream outputStream, int i, boolean z) {
        super(outputStream);
        this.f173b = false;
        this.f173b = true;
        this.f174c = z;
        this.f175d = i;
    }

    /* renamed from: b */
    public void m24160b() {
        this.f195a.write(0);
        this.f195a.write(0);
        if (this.f173b && this.f174c) {
            this.f195a.write(0);
            this.f195a.write(0);
        }
    }

    /* renamed from: a */
    public void m24161a(InputStream inputStream) {
        while (true) {
            int read = inputStream.read();
            if (read < 0) {
                return;
            }
            this.f195a.write(read);
        }
    }
}
