package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p038j0;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0515e;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0542i;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0641v;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p039k0.C0550a;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p040l0.InterfaceC0552a;

/* renamed from: a.a.a.a.a.e.a.c.j0.b */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0547b implements InterfaceC0641v {

    /* renamed from: a */
    public byte[] f1778a;

    /* renamed from: b */
    public byte[] f1779b;

    /* renamed from: c */
    public int f1780c;

    /* renamed from: d */
    public InterfaceC0515e f1781d;

    /* renamed from: e */
    public InterfaceC0552a f1782e;

    /* renamed from: f */
    public int f1783f;

    public C0547b(InterfaceC0515e interfaceC0515e) {
        this(interfaceC0515e, (interfaceC0515e.mo22861c() * 8) / 2, null);
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0641v
    /* renamed from: a */
    public String mo22656a() {
        return this.f1781d.mo22866a();
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0641v
    /* renamed from: b */
    public void mo22651b() {
        int i = 0;
        while (true) {
            byte[] bArr = this.f1779b;
            if (i < bArr.length) {
                bArr[i] = 0;
                i++;
            } else {
                this.f1780c = 0;
                this.f1781d.mo22863b();
                return;
            }
        }
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0641v
    /* renamed from: c */
    public int mo22650c() {
        return this.f1783f;
    }

    public C0547b(InterfaceC0515e interfaceC0515e, InterfaceC0552a interfaceC0552a) {
        this(interfaceC0515e, (interfaceC0515e.mo22861c() * 8) / 2, interfaceC0552a);
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0641v
    /* renamed from: a */
    public void mo22654a(InterfaceC0542i interfaceC0542i) {
        mo22651b();
        this.f1781d.mo22865a(true, interfaceC0542i);
    }

    public C0547b(InterfaceC0515e interfaceC0515e, int i) {
        this(interfaceC0515e, i, null);
    }

    public C0547b(InterfaceC0515e interfaceC0515e, int i, InterfaceC0552a interfaceC0552a) {
        if (i % 8 == 0) {
            this.f1781d = new C0550a(interfaceC0515e);
            this.f1782e = interfaceC0552a;
            this.f1783f = i / 8;
            this.f1778a = new byte[interfaceC0515e.mo22861c()];
            this.f1779b = new byte[interfaceC0515e.mo22861c()];
            this.f1780c = 0;
            return;
        }
        throw new IllegalArgumentException("MAC size must be multiple of 8");
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0641v
    /* renamed from: a */
    public void mo22655a(byte b) {
        int i = this.f1780c;
        byte[] bArr = this.f1779b;
        if (i == bArr.length) {
            this.f1781d.mo22864a(bArr, 0, this.f1778a, 0);
            this.f1780c = 0;
        }
        byte[] bArr2 = this.f1779b;
        int i2 = this.f1780c;
        this.f1780c = i2 + 1;
        bArr2[i2] = b;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0641v
    /* renamed from: a */
    public void mo22652a(byte[] bArr, int i, int i2) {
        if (i2 >= 0) {
            int mo22861c = this.f1781d.mo22861c();
            int i3 = this.f1780c;
            int i4 = mo22861c - i3;
            if (i2 > i4) {
                System.arraycopy(bArr, i, this.f1779b, i3, i4);
                this.f1781d.mo22864a(this.f1779b, 0, this.f1778a, 0);
                this.f1780c = 0;
                i2 -= i4;
                i += i4;
                while (i2 > mo22861c) {
                    this.f1781d.mo22864a(bArr, i, this.f1778a, 0);
                    i2 -= mo22861c;
                    i += mo22861c;
                }
            }
            System.arraycopy(bArr, i, this.f1779b, this.f1780c, i2);
            this.f1780c += i2;
            return;
        }
        throw new IllegalArgumentException("Can't have a negative input length!");
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0641v
    /* renamed from: a */
    public int mo22653a(byte[] bArr, int i) {
        int mo22861c = this.f1781d.mo22861c();
        if (this.f1782e == null) {
            while (true) {
                int i2 = this.f1780c;
                if (i2 >= mo22861c) {
                    break;
                }
                this.f1779b[i2] = 0;
                this.f1780c = i2 + 1;
            }
        } else {
            if (this.f1780c == mo22861c) {
                this.f1781d.mo22864a(this.f1779b, 0, this.f1778a, 0);
                this.f1780c = 0;
            }
            this.f1782e.mo22849a(this.f1779b, this.f1780c);
        }
        this.f1781d.mo22864a(this.f1779b, 0, this.f1778a, 0);
        System.arraycopy(this.f1778a, 0, bArr, i, this.f1783f);
        mo22651b();
        return this.f1783f;
    }
}
