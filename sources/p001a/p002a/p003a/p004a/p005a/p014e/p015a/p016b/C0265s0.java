package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b;

import java.io.InputStream;

/* renamed from: a.a.a.a.a.e.a.b.s0 */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0265s0 extends InputStream {

    /* renamed from: C */
    public final C0371w f557C;

    /* renamed from: D */
    public boolean f558D = true;

    /* renamed from: E */
    public InputStream f559E;

    public C0265s0(C0371w c0371w) {
        this.f557C = c0371w;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) {
        InterfaceC0234p interfaceC0234p;
        int i3 = 0;
        if (this.f559E == null) {
            if (!this.f558D || (interfaceC0234p = (InterfaceC0234p) this.f557C.m23495a()) == null) {
                return -1;
            }
            this.f558D = false;
            this.f559E = interfaceC0234p.mo23845c();
        }
        while (true) {
            int read = this.f559E.read(bArr, i + i3, i2 - i3);
            if (read >= 0) {
                i3 += read;
                if (i3 == i2) {
                    return i3;
                }
            } else {
                InterfaceC0234p interfaceC0234p2 = (InterfaceC0234p) this.f557C.m23495a();
                if (interfaceC0234p2 == null) {
                    this.f559E = null;
                    if (i3 < 1) {
                        return -1;
                    }
                    return i3;
                }
                this.f559E = interfaceC0234p2.mo23845c();
            }
        }
    }

    @Override // java.io.InputStream
    public int read() {
        InterfaceC0234p interfaceC0234p;
        if (this.f559E == null) {
            if (!this.f558D || (interfaceC0234p = (InterfaceC0234p) this.f557C.m23495a()) == null) {
                return -1;
            }
            this.f558D = false;
            this.f559E = interfaceC0234p.mo23845c();
        }
        while (true) {
            int read = this.f559E.read();
            if (read >= 0) {
                return read;
            }
            InterfaceC0234p interfaceC0234p2 = (InterfaceC0234p) this.f557C.m23495a();
            if (interfaceC0234p2 == null) {
                this.f559E = null;
                return -1;
            }
            this.f559E = interfaceC0234p2.mo23845c();
        }
    }
}
