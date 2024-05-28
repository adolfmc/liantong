package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p049e.p050k;

/* renamed from: a.a.a.a.a.e.a.e.k.d */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0682d {

    /* renamed from: a */
    public byte[] f2041a;

    /* renamed from: b */
    public int f2042b;

    /* renamed from: c */
    public InterfaceC0687i f2043c;

    public C0682d(InterfaceC0687i interfaceC0687i, int i) {
        this.f2043c = interfaceC0687i;
        if (i % interfaceC0687i.mo22405a() == 0) {
            this.f2041a = new byte[i];
            this.f2042b = 0;
            return;
        }
        throw new IllegalArgumentException("buffer size not multiple of input block size");
    }

    /* renamed from: a */
    public int m22421a(byte b, byte[] bArr, int i) {
        byte[] bArr2 = this.f2041a;
        int i2 = this.f2042b;
        int i3 = i2 + 1;
        this.f2042b = i3;
        bArr2[i2] = b;
        if (i3 == bArr2.length) {
            int mo22402b = this.f2043c.mo22402b(bArr2, 0, bArr2.length, bArr, i);
            this.f2042b = 0;
            return mo22402b;
        }
        return 0;
    }

    /* renamed from: a */
    public int m22420a(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        if (i2 >= 0) {
            byte[] bArr3 = this.f2041a;
            int length = bArr3.length;
            int i4 = this.f2042b;
            int i5 = length - i4;
            int i6 = 0;
            if (i2 > i5) {
                System.arraycopy(bArr, i, bArr3, i4, i5);
                InterfaceC0687i interfaceC0687i = this.f2043c;
                byte[] bArr4 = this.f2041a;
                int mo22402b = interfaceC0687i.mo22402b(bArr4, 0, bArr4.length, bArr2, i3) + 0;
                this.f2042b = 0;
                int i7 = i2 - i5;
                int i8 = i + i5;
                int i9 = i3 + mo22402b;
                int length2 = i7 - (i7 % this.f2041a.length);
                i6 = mo22402b + this.f2043c.mo22402b(bArr, i8, length2, bArr2, i9);
                i2 = i7 - length2;
                i = i8 + length2;
            }
            if (i2 != 0) {
                System.arraycopy(bArr, i, this.f2041a, this.f2042b, i2);
                this.f2042b += i2;
            }
            return i6;
        }
        throw new IllegalArgumentException("Can't have a negative input length!");
    }
}
