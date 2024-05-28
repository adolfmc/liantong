package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p049e.p050k;

/* renamed from: a.a.a.a.a.e.a.e.k.c */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0681c {

    /* renamed from: a */
    public byte[] f2038a;

    /* renamed from: b */
    public int f2039b;

    /* renamed from: c */
    public InterfaceC0687i f2040c;

    public C0681c(InterfaceC0687i interfaceC0687i, int i) {
        this.f2040c = interfaceC0687i;
        if (i % interfaceC0687i.mo22405a() == 0) {
            this.f2038a = new byte[i];
            this.f2039b = 0;
            return;
        }
        throw new IllegalArgumentException("buffer size not multiple of input block size");
    }

    /* renamed from: a */
    public int m22423a(byte b, byte[] bArr, int i) {
        byte[] bArr2 = this.f2038a;
        int i2 = this.f2039b;
        int i3 = i2 + 1;
        this.f2039b = i3;
        bArr2[i2] = b;
        if (i3 == bArr2.length) {
            int mo22404a = this.f2040c.mo22404a(bArr2, 0, bArr2.length, bArr, i);
            this.f2039b = 0;
            return mo22404a;
        }
        return 0;
    }

    /* renamed from: a */
    public int m22422a(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        if (i2 >= 0) {
            byte[] bArr3 = this.f2038a;
            int length = bArr3.length;
            int i4 = this.f2039b;
            int i5 = length - i4;
            int i6 = 0;
            if (i2 > i5) {
                System.arraycopy(bArr, i, bArr3, i4, i5);
                InterfaceC0687i interfaceC0687i = this.f2040c;
                byte[] bArr4 = this.f2038a;
                int mo22404a = interfaceC0687i.mo22404a(bArr4, 0, bArr4.length, bArr2, i3) + 0;
                this.f2039b = 0;
                int i7 = i2 - i5;
                int i8 = i + i5;
                int i9 = i3 + mo22404a;
                int length2 = i7 - (i7 % this.f2038a.length);
                i6 = mo22404a + this.f2040c.mo22404a(bArr, i8, length2, bArr2, i9);
                i2 = i7 - length2;
                i = i8 + length2;
            }
            if (i2 != 0) {
                System.arraycopy(bArr, i, this.f2038a, this.f2039b, i2);
                this.f2039b += i2;
            }
            return i6;
        }
        throw new IllegalArgumentException("Can't have a negative input length!");
    }
}
