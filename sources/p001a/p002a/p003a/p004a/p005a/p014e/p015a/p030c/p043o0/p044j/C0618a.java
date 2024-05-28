package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p043o0.p044j;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.InterfaceC0641v;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0.C0568f0;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p043o0.InterfaceC0610d;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p049e.C0669a;

/* renamed from: a.a.a.a.a.e.a.c.o0.j.a */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0618a implements InterfaceC0620c {

    /* renamed from: f */
    public static final long f1917f = 140737488355328L;

    /* renamed from: g */
    public static final int f1918g = 262144;

    /* renamed from: a */
    public byte[] f1919a;

    /* renamed from: b */
    public byte[] f1920b;

    /* renamed from: c */
    public long f1921c;

    /* renamed from: d */
    public InterfaceC0610d f1922d;

    /* renamed from: e */
    public InterfaceC0641v f1923e;

    public C0618a(InterfaceC0641v interfaceC0641v, int i, InterfaceC0610d interfaceC0610d, byte[] bArr, byte[] bArr2) {
        if (i <= C0621d.m22714a(interfaceC0641v)) {
            if (interfaceC0610d.mo22733b() >= i) {
                this.f1922d = interfaceC0610d;
                this.f1923e = interfaceC0641v;
                byte[] m22498a = C0669a.m22498a(interfaceC0610d.mo22732c(), bArr2, bArr);
                byte[] bArr3 = new byte[interfaceC0641v.mo22650c()];
                this.f1919a = bArr3;
                byte[] bArr4 = new byte[bArr3.length];
                this.f1920b = bArr4;
                C0669a.m22502a(bArr4, (byte) 1);
                m22722b(m22498a);
                this.f1921c = 1L;
                return;
            }
            throw new IllegalArgumentException("Not enough entropy for security strength required");
        }
        throw new IllegalArgumentException("Requested security strength is not supported by the derivation function");
    }

    /* renamed from: a */
    private void m22723a(byte[] bArr, byte b) {
        this.f1923e.mo22654a(new C0568f0(this.f1919a));
        InterfaceC0641v interfaceC0641v = this.f1923e;
        byte[] bArr2 = this.f1920b;
        interfaceC0641v.mo22652a(bArr2, 0, bArr2.length);
        this.f1923e.mo22655a(b);
        if (bArr != null) {
            this.f1923e.mo22652a(bArr, 0, bArr.length);
        }
        this.f1923e.mo22653a(this.f1919a, 0);
        this.f1923e.mo22654a(new C0568f0(this.f1919a));
        InterfaceC0641v interfaceC0641v2 = this.f1923e;
        byte[] bArr3 = this.f1920b;
        interfaceC0641v2.mo22652a(bArr3, 0, bArr3.length);
        this.f1923e.mo22653a(this.f1920b, 0);
    }

    /* renamed from: b */
    private void m22722b(byte[] bArr) {
        m22723a(bArr, (byte) 0);
        if (bArr != null) {
            m22723a(bArr, (byte) 1);
        }
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p043o0.p044j.InterfaceC0620c
    /* renamed from: a */
    public int mo22717a(byte[] bArr, byte[] bArr2, boolean z) {
        int length = bArr.length * 8;
        if (length <= 262144) {
            if (this.f1921c > 140737488355328L) {
                return -1;
            }
            if (z) {
                mo22718a(bArr2);
                bArr2 = null;
            }
            if (bArr2 != null) {
                m22722b(bArr2);
            }
            int length2 = bArr.length;
            byte[] bArr3 = new byte[length2];
            int length3 = bArr.length / this.f1920b.length;
            this.f1923e.mo22654a(new C0568f0(this.f1919a));
            for (int i = 0; i < length3; i++) {
                InterfaceC0641v interfaceC0641v = this.f1923e;
                byte[] bArr4 = this.f1920b;
                interfaceC0641v.mo22652a(bArr4, 0, bArr4.length);
                this.f1923e.mo22653a(this.f1920b, 0);
                byte[] bArr5 = this.f1920b;
                System.arraycopy(bArr5, 0, bArr3, bArr5.length * i, bArr5.length);
            }
            byte[] bArr6 = this.f1920b;
            if (bArr6.length * length3 < length2) {
                this.f1923e.mo22652a(bArr6, 0, bArr6.length);
                this.f1923e.mo22653a(this.f1920b, 0);
                byte[] bArr7 = this.f1920b;
                System.arraycopy(bArr7, 0, bArr3, bArr7.length * length3, length2 - (length3 * bArr7.length));
            }
            m22722b(bArr2);
            this.f1921c++;
            System.arraycopy(bArr3, 0, bArr, 0, bArr.length);
            return length;
        }
        throw new IllegalArgumentException("Number of bits per request limited to 262144");
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p043o0.p044j.InterfaceC0620c
    /* renamed from: a */
    public void mo22718a(byte[] bArr) {
        m22722b(C0669a.m22471b(this.f1922d.mo22732c(), bArr));
        this.f1921c = 1L;
    }
}
