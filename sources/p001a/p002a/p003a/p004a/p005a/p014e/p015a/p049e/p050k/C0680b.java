package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p049e.p050k;

import java.io.IOException;
import java.io.OutputStream;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p038j0.C0548c;

/* renamed from: a.a.a.a.a.e.a.e.k.b */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0680b implements InterfaceC0683e {

    /* renamed from: a */
    public final byte[] f2035a = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, C0548c.f1784h, 55, 56, 57, 43, 47};

    /* renamed from: b */
    public byte f2036b = 61;

    /* renamed from: c */
    public final byte[] f2037c = new byte[128];

    public C0680b() {
        m22428a();
    }

    /* renamed from: a */
    private boolean m22427a(char c) {
        return c == '\n' || c == '\r' || c == '\t' || c == ' ';
    }

    /* renamed from: a */
    public void m22428a() {
        int i = 0;
        int i2 = 0;
        while (true) {
            byte[] bArr = this.f2037c;
            if (i2 >= bArr.length) {
                break;
            }
            bArr[i2] = -1;
            i2++;
        }
        while (true) {
            byte[] bArr2 = this.f2035a;
            if (i >= bArr2.length) {
                return;
            }
            this.f2037c[bArr2[i]] = (byte) i;
            i++;
        }
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p049e.p050k.InterfaceC0683e
    /* renamed from: b */
    public int mo22406b(byte[] bArr, int i, int i2, OutputStream outputStream) {
        int i3 = i2 + i;
        while (i3 > i && m22427a((char) bArr[i3 - 1])) {
            i3--;
        }
        int i4 = i3 - 4;
        int m22424a = m22424a(bArr, i, i4);
        int i5 = 0;
        while (m22424a < i4) {
            int i6 = m22424a + 1;
            byte b = this.f2037c[bArr[m22424a]];
            int m22424a2 = m22424a(bArr, i6, i4);
            int i7 = m22424a2 + 1;
            byte b2 = this.f2037c[bArr[m22424a2]];
            int m22424a3 = m22424a(bArr, i7, i4);
            int i8 = m22424a3 + 1;
            byte b3 = this.f2037c[bArr[m22424a3]];
            int m22424a4 = m22424a(bArr, i8, i4);
            int i9 = m22424a4 + 1;
            byte b4 = this.f2037c[bArr[m22424a4]];
            if ((b | b2 | b3 | b4) >= 0) {
                outputStream.write((b << 2) | (b2 >> 4));
                outputStream.write((b2 << 4) | (b3 >> 2));
                outputStream.write((b3 << 6) | b4);
                i5 += 3;
                m22424a = m22424a(bArr, i9, i4);
            } else {
                throw new IOException("invalid characters encountered in base64 data");
            }
        }
        return i5 + m22426a(outputStream, (char) bArr[i4], (char) bArr[i3 - 3], (char) bArr[i3 - 2], (char) bArr[i3 - 1]);
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p049e.p050k.InterfaceC0683e
    /* renamed from: a */
    public int mo22407a(byte[] bArr, int i, int i2, OutputStream outputStream) {
        int i3;
        int i4 = i2 % 3;
        int i5 = i2 - i4;
        int i6 = i;
        while (true) {
            i3 = i + i5;
            if (i6 >= i3) {
                break;
            }
            int i7 = bArr[i6] & 255;
            int i8 = bArr[i6 + 1] & 255;
            int i9 = bArr[i6 + 2] & 255;
            outputStream.write(this.f2035a[(i7 >>> 2) & 63]);
            outputStream.write(this.f2035a[((i7 << 4) | (i8 >>> 4)) & 63]);
            outputStream.write(this.f2035a[((i8 << 2) | (i9 >>> 6)) & 63]);
            outputStream.write(this.f2035a[i9 & 63]);
            i6 += 3;
        }
        if (i4 == 1) {
            int i10 = bArr[i3] & 255;
            outputStream.write(this.f2035a[(i10 >>> 2) & 63]);
            outputStream.write(this.f2035a[(i10 << 4) & 63]);
            outputStream.write(this.f2036b);
            outputStream.write(this.f2036b);
        } else if (i4 == 2) {
            int i11 = bArr[i3] & 255;
            int i12 = bArr[i3 + 1] & 255;
            outputStream.write(this.f2035a[(i11 >>> 2) & 63]);
            outputStream.write(this.f2035a[((i11 << 4) | (i12 >>> 4)) & 63]);
            outputStream.write(this.f2035a[(i12 << 2) & 63]);
            outputStream.write(this.f2036b);
        }
        return ((i5 / 3) * 4) + (i4 == 0 ? 0 : 4);
    }

    /* renamed from: a */
    private int m22424a(byte[] bArr, int i, int i2) {
        while (i < i2 && m22427a((char) bArr[i])) {
            i++;
        }
        return i;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p049e.p050k.InterfaceC0683e
    /* renamed from: a */
    public int mo22408a(String str, OutputStream outputStream) {
        int length = str.length();
        while (length > 0 && m22427a(str.charAt(length - 1))) {
            length--;
        }
        int i = length - 4;
        int i2 = 0;
        int m22425a = m22425a(str, 0, i);
        while (m22425a < i) {
            int i3 = m22425a + 1;
            byte b = this.f2037c[str.charAt(m22425a)];
            int m22425a2 = m22425a(str, i3, i);
            int i4 = m22425a2 + 1;
            byte b2 = this.f2037c[str.charAt(m22425a2)];
            int m22425a3 = m22425a(str, i4, i);
            int i5 = m22425a3 + 1;
            byte b3 = this.f2037c[str.charAt(m22425a3)];
            int m22425a4 = m22425a(str, i5, i);
            int i6 = m22425a4 + 1;
            byte b4 = this.f2037c[str.charAt(m22425a4)];
            if ((b | b2 | b3 | b4) >= 0) {
                outputStream.write((b << 2) | (b2 >> 4));
                outputStream.write((b2 << 4) | (b3 >> 2));
                outputStream.write((b3 << 6) | b4);
                i2 += 3;
                m22425a = m22425a(str, i6, i);
            } else {
                throw new IOException("invalid characters encountered in base64 data");
            }
        }
        return i2 + m22426a(outputStream, str.charAt(i), str.charAt(length - 3), str.charAt(length - 2), str.charAt(length - 1));
    }

    /* renamed from: a */
    private int m22426a(OutputStream outputStream, char c, char c2, char c3, char c4) {
        byte b = this.f2036b;
        if (c3 == b) {
            byte[] bArr = this.f2037c;
            byte b2 = bArr[c];
            byte b3 = bArr[c2];
            if ((b2 | b3) >= 0) {
                outputStream.write((b2 << 2) | (b3 >> 4));
                return 1;
            }
            throw new IOException("invalid characters encountered at end of base64 data");
        } else if (c4 == b) {
            byte[] bArr2 = this.f2037c;
            byte b4 = bArr2[c];
            byte b5 = bArr2[c2];
            byte b6 = bArr2[c3];
            if ((b4 | b5 | b6) >= 0) {
                outputStream.write((b4 << 2) | (b5 >> 4));
                outputStream.write((b5 << 4) | (b6 >> 2));
                return 2;
            }
            throw new IOException("invalid characters encountered at end of base64 data");
        } else {
            byte[] bArr3 = this.f2037c;
            byte b7 = bArr3[c];
            byte b8 = bArr3[c2];
            byte b9 = bArr3[c3];
            byte b10 = bArr3[c4];
            if ((b7 | b8 | b9 | b10) >= 0) {
                outputStream.write((b7 << 2) | (b8 >> 4));
                outputStream.write((b8 << 4) | (b9 >> 2));
                outputStream.write((b9 << 6) | b10);
                return 3;
            }
            throw new IOException("invalid characters encountered at end of base64 data");
        }
    }

    /* renamed from: a */
    private int m22425a(String str, int i, int i2) {
        while (i < i2 && m22427a(str.charAt(i))) {
            i++;
        }
        return i;
    }
}
