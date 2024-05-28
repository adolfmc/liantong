package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p049e.p050k;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p038j0.C0548c;

/* renamed from: a.a.a.a.a.e.a.e.k.h */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0686h implements InterfaceC0687i {

    /* renamed from: a */
    public static final byte[] f2047a = {48, 49, 50, 51, 52, 53, C0548c.f1784h, 55, 56, 57, 97, 98, 99, 100, 101, 102};

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p049e.p050k.InterfaceC0687i
    /* renamed from: a */
    public int mo22405a() {
        return 2;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p049e.p050k.InterfaceC0687i
    /* renamed from: a */
    public int mo22404a(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        int i4 = i2 / 2;
        for (int i5 = 0; i5 < i4; i5++) {
            int i6 = (i5 * 2) + i;
            byte b = bArr[i6];
            byte b2 = bArr[i6 + 1];
            if (b < 97) {
                bArr2[i3] = (byte) ((b - 48) << 4);
            } else {
                bArr2[i3] = (byte) (((b - 97) + 10) << 4);
            }
            if (b2 < 97) {
                bArr2[i3] = (byte) (bArr2[i3] + ((byte) (b2 - 48)));
            } else {
                bArr2[i3] = (byte) (bArr2[i3] + ((byte) ((b2 - 97) + 10)));
            }
            i3++;
        }
        return i4;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p049e.p050k.InterfaceC0687i
    /* renamed from: b */
    public int mo22403b() {
        return 1;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p049e.p050k.InterfaceC0687i
    /* renamed from: b */
    public int mo22402b(byte[] bArr, int i, int i2, byte[] bArr2, int i3) {
        int i4 = 0;
        int i5 = i;
        int i6 = 0;
        while (i4 < i2) {
            int i7 = i3 + i6;
            byte[] bArr3 = f2047a;
            bArr2[i7] = bArr3[(bArr[i5] >> 4) & 15];
            bArr2[i7 + 1] = bArr3[bArr[i5] & 15];
            i5++;
            i4++;
            i6 += 2;
        }
        return i2 * 2;
    }
}
