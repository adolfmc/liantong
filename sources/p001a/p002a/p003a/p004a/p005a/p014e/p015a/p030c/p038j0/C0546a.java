package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p038j0;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0515e;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0542i;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0641v;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p039k0.C0550a;

/* renamed from: a.a.a.a.a.e.a.c.j0.a */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0546a implements InterfaceC0641v {

    /* renamed from: a */
    public byte[] f1773a;

    /* renamed from: b */
    public byte[] f1774b;

    /* renamed from: c */
    public int f1775c;

    /* renamed from: d */
    public InterfaceC0515e f1776d;

    /* renamed from: e */
    public int f1777e;

    public C0546a(InterfaceC0515e interfaceC0515e) {
        this(interfaceC0515e, (interfaceC0515e.mo22861c() * 8) / 2);
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0641v
    /* renamed from: a */
    public String mo22656a() {
        return this.f1776d.mo22866a();
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0641v
    /* renamed from: b */
    public void mo22651b() {
        int i = 0;
        while (true) {
            byte[] bArr = this.f1774b;
            if (i < bArr.length) {
                bArr[i] = 0;
                i++;
            } else {
                this.f1775c = 0;
                this.f1776d.mo22863b();
                return;
            }
        }
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0641v
    /* renamed from: c */
    public int mo22650c() {
        return this.f1777e;
    }

    public C0546a(InterfaceC0515e interfaceC0515e, int i) {
        if (i % 8 == 0) {
            this.f1776d = new C0550a(interfaceC0515e);
            this.f1777e = i / 8;
            this.f1773a = new byte[interfaceC0515e.mo22861c()];
            this.f1774b = new byte[interfaceC0515e.mo22861c()];
            this.f1775c = 0;
            return;
        }
        throw new IllegalArgumentException("MAC size must be multiple of 8");
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0641v
    /* renamed from: a */
    public void mo22654a(InterfaceC0542i interfaceC0542i) {
        mo22651b();
        this.f1776d.mo22865a(true, interfaceC0542i);
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0641v
    /* renamed from: a */
    public void mo22655a(byte b) {
        int i = this.f1775c;
        byte[] bArr = this.f1774b;
        if (i == bArr.length) {
            this.f1776d.mo22864a(bArr, 0, this.f1773a, 0);
            this.f1775c = 0;
        }
        byte[] bArr2 = this.f1774b;
        int i2 = this.f1775c;
        this.f1775c = i2 + 1;
        bArr2[i2] = b;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0641v
    /* renamed from: a */
    public void mo22652a(byte[] bArr, int i, int i2) {
        if (i2 >= 0) {
            int mo22861c = this.f1776d.mo22861c();
            int i3 = this.f1775c;
            int i4 = mo22861c - i3;
            if (i2 > i4) {
                System.arraycopy(bArr, i, this.f1774b, i3, i4);
                this.f1776d.mo22864a(this.f1774b, 0, this.f1773a, 0);
                this.f1775c = 0;
                i2 -= i4;
                i += i4;
                while (i2 > mo22861c) {
                    this.f1776d.mo22864a(bArr, i, this.f1773a, 0);
                    i2 -= mo22861c;
                    i += mo22861c;
                }
            }
            System.arraycopy(bArr, i, this.f1774b, this.f1775c, i2);
            this.f1775c += i2;
            return;
        }
        throw new IllegalArgumentException("Can't have a negative input length!");
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0641v
    /* renamed from: a */
    public int mo22653a(byte[] bArr, int i) {
        int mo22861c = this.f1776d.mo22861c();
        while (true) {
            int i2 = this.f1775c;
            if (i2 < mo22861c) {
                this.f1774b[i2] = 0;
                this.f1775c = i2 + 1;
            } else {
                this.f1776d.mo22864a(this.f1774b, 0, this.f1773a, 0);
                System.arraycopy(this.f1773a, 0, bArr, i, this.f1777e);
                mo22651b();
                return this.f1777e;
            }
        }
    }
}
