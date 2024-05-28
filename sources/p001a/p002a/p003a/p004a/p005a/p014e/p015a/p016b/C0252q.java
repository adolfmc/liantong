package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b;

import java.io.IOException;
import java.io.OutputStream;

/* renamed from: a.a.a.a.a.e.a.b.q */
/* loaded from: E:\567196_dexfile_execute.dex.fixout.dex */
public class C0252q {

    /* renamed from: a */
    public OutputStream f465a;

    /* renamed from: a.a.a.a.a.e.a.b.q$a */
    /* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
    public class C0253a extends C0252q {

        /* renamed from: b */
        public boolean f466b;

        public C0253a(OutputStream outputStream) {
            super(outputStream);
            this.f466b = true;
        }

        @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0252q
        /* renamed from: a */
        public void mo23763a(int i) {
            if (this.f466b) {
                this.f466b = false;
            } else {
                super.mo23763a(i);
            }
        }
    }

    public C0252q(OutputStream outputStream) {
        this.f465a = outputStream;
    }

    /* renamed from: a */
    public void mo23763a(int i) {
        this.f465a.write(i);
    }

    /* renamed from: b */
    public void m23767b(int i) {
        if (i > 127) {
            int i2 = i;
            int i3 = 1;
            while (true) {
                i2 >>>= 8;
                if (i2 == 0) {
                    break;
                }
                i3++;
            }
            mo23763a((byte) (i3 | 128));
            for (int i4 = (i3 - 1) * 8; i4 >= 0; i4 -= 8) {
                mo23763a((byte) (i >> i4));
            }
            return;
        }
        mo23763a((byte) i);
    }

    /* renamed from: c */
    public C0252q mo23766c() {
        return new C0176m1(this.f465a);
    }

    /* renamed from: d */
    public C0252q mo23765d() {
        return new C0131b2(this.f465a);
    }

    /* renamed from: e */
    public void m23764e() {
        this.f465a.write(5);
        this.f465a.write(0);
    }

    /* renamed from: a */
    public void m23770a(byte[] bArr) {
        this.f465a.write(bArr);
    }

    /* renamed from: a */
    public void m23769a(byte[] bArr, int i, int i2) {
        this.f465a.write(bArr, i, i2);
    }

    /* renamed from: a */
    public void m23773a(int i, byte[] bArr) {
        mo23763a(i);
        m23767b(bArr.length);
        m23770a(bArr);
    }

    /* renamed from: a */
    public void m23775a(int i, int i2) {
        if (i2 < 31) {
            mo23763a(i | i2);
            return;
        }
        mo23763a(i | 31);
        if (i2 < 128) {
            mo23763a(i2);
            return;
        }
        byte[] bArr = new byte[5];
        int i3 = 4;
        bArr[4] = (byte) (i2 & 127);
        do {
            i2 >>= 7;
            i3--;
            bArr[i3] = (byte) ((i2 & 127) | 128);
        } while (i2 > 127);
        m23769a(bArr, i3, 5 - i3);
    }

    /* renamed from: b */
    public void m23768b() {
        this.f465a.flush();
    }

    /* renamed from: a */
    public void m23774a(int i, int i2, byte[] bArr) {
        m23775a(i, i2);
        m23767b(bArr.length);
        m23770a(bArr);
    }

    /* renamed from: a */
    public void mo23772a(InterfaceC0136d interfaceC0136d) {
        if (interfaceC0136d != null) {
            interfaceC0136d.mo23015d().mo22982a(this);
            return;
        }
        throw new IOException("null object detected");
    }

    /* renamed from: a */
    public void m23771a(AbstractC0258r abstractC0258r) {
        if (abstractC0258r != null) {
            abstractC0258r.mo22982a(new C0253a(this.f465a));
            return;
        }
        throw new IOException("null object detected");
    }

    /* renamed from: a */
    public void m23776a() {
        this.f465a.close();
    }
}
