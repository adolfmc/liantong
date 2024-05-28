package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p049e.p050k;

import java.io.IOException;
import java.io.OutputStream;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p038j0.C0548c;

/* renamed from: a.a.a.a.a.e.a.e.k.g */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0685g implements InterfaceC0683e {

    /* renamed from: a */
    public final byte[] f2045a = {48, 49, 50, 51, 52, 53, C0548c.f1784h, 55, 56, 57, 97, 98, 99, 100, 101, 102};

    /* renamed from: b */
    public final byte[] f2046b = new byte[128];

    public C0685g() {
        m22410a();
    }

    /* renamed from: a */
    public static boolean m22409a(char c) {
        return c == '\n' || c == '\r' || c == '\t' || c == ' ';
    }

    /* renamed from: a */
    public void m22410a() {
        int i = 0;
        int i2 = 0;
        while (true) {
            byte[] bArr = this.f2046b;
            if (i2 >= bArr.length) {
                break;
            }
            bArr[i2] = -1;
            i2++;
        }
        while (true) {
            byte[] bArr2 = this.f2045a;
            if (i < bArr2.length) {
                this.f2046b[bArr2[i]] = (byte) i;
                i++;
            } else {
                byte[] bArr3 = this.f2046b;
                bArr3[65] = bArr3[97];
                bArr3[66] = bArr3[98];
                bArr3[67] = bArr3[99];
                bArr3[68] = bArr3[100];
                bArr3[69] = bArr3[101];
                bArr3[70] = bArr3[102];
                return;
            }
        }
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p049e.p050k.InterfaceC0683e
    /* renamed from: b */
    public int mo22406b(byte[] bArr, int i, int i2, OutputStream outputStream) {
        int i3 = i2 + i;
        while (i3 > i && m22409a((char) bArr[i3 - 1])) {
            i3--;
        }
        int i4 = 0;
        while (i < i3) {
            while (i < i3 && m22409a((char) bArr[i])) {
                i++;
            }
            int i5 = i + 1;
            byte b = this.f2046b[bArr[i]];
            while (i5 < i3 && m22409a((char) bArr[i5])) {
                i5++;
            }
            int i6 = i5 + 1;
            byte b2 = this.f2046b[bArr[i5]];
            if ((b | b2) >= 0) {
                outputStream.write((b << 4) | b2);
                i4++;
                i = i6;
            } else {
                throw new IOException("invalid characters encountered in Hex data");
            }
        }
        return i4;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p049e.p050k.InterfaceC0683e
    /* renamed from: a */
    public int mo22407a(byte[] bArr, int i, int i2, OutputStream outputStream) {
        for (int i3 = i; i3 < i + i2; i3++) {
            int i4 = bArr[i3] & 255;
            outputStream.write(this.f2045a[i4 >>> 4]);
            outputStream.write(this.f2045a[i4 & 15]);
        }
        return i2 * 2;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p049e.p050k.InterfaceC0683e
    /* renamed from: a */
    public int mo22408a(String str, OutputStream outputStream) {
        int length = str.length();
        while (length > 0 && m22409a(str.charAt(length - 1))) {
            length--;
        }
        int i = 0;
        int i2 = 0;
        while (i < length) {
            while (i < length && m22409a(str.charAt(i))) {
                i++;
            }
            int i3 = i + 1;
            byte b = this.f2046b[str.charAt(i)];
            while (i3 < length && m22409a(str.charAt(i3))) {
                i3++;
            }
            int i4 = i3 + 1;
            byte b2 = this.f2046b[str.charAt(i3)];
            if ((b | b2) >= 0) {
                outputStream.write((b << 4) | b2);
                i2++;
                i = i4;
            } else {
                throw new IOException("invalid characters encountered in Hex string");
            }
        }
        return i2;
    }
}
