package org.p415a.p448g.p449a;

import java.io.IOException;
import java.io.OutputStream;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p038j0.C0548c;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.g.a.b */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12960b implements InterfaceC12962d {

    /* renamed from: a */
    protected final byte[] f26239a = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, C0548c.f1784h, 55, 56, 57, 43, 47};

    /* renamed from: b */
    protected byte f26240b = 61;

    /* renamed from: c */
    protected final byte[] f26241c = new byte[128];

    public C12960b() {
        m424a();
    }

    /* renamed from: a */
    private int m422a(OutputStream outputStream, char c, char c2, char c3, char c4) {
        byte b = this.f26240b;
        if (c3 == b) {
            if (c4 == b) {
                byte[] bArr = this.f26241c;
                byte b2 = bArr[c];
                byte b3 = bArr[c2];
                if ((b2 | b3) >= 0) {
                    outputStream.write((b2 << 2) | (b3 >> 4));
                    return 1;
                }
                throw new IOException("invalid characters encountered at end of base64 data");
            }
            throw new IOException("invalid characters encountered at end of base64 data");
        } else if (c4 == b) {
            byte[] bArr2 = this.f26241c;
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
            byte[] bArr3 = this.f26241c;
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
    private int m421a(String str, int i, int i2) {
        while (i < i2 && m423a(str.charAt(i))) {
            i++;
        }
        return i;
    }

    /* renamed from: a */
    private int m420a(byte[] bArr, int i, int i2) {
        while (i < i2 && m423a((char) bArr[i])) {
            i++;
        }
        return i;
    }

    /* renamed from: a */
    private boolean m423a(char c) {
        return c == '\n' || c == '\r' || c == '\t' || c == ' ';
    }

    @Override // org.p415a.p448g.p449a.InterfaceC12962d
    /* renamed from: a */
    public int mo412a(String str, OutputStream outputStream) {
        int length = str.length();
        while (length > 0 && m423a(str.charAt(length - 1))) {
            length--;
        }
        int i = length - 4;
        int i2 = 0;
        int m421a = m421a(str, 0, i);
        while (m421a < i) {
            int i3 = m421a + 1;
            byte b = this.f26241c[str.charAt(m421a)];
            int m421a2 = m421a(str, i3, i);
            int i4 = m421a2 + 1;
            byte b2 = this.f26241c[str.charAt(m421a2)];
            int m421a3 = m421a(str, i4, i);
            int i5 = m421a3 + 1;
            byte b3 = this.f26241c[str.charAt(m421a3)];
            int m421a4 = m421a(str, i5, i);
            int i6 = m421a4 + 1;
            byte b4 = this.f26241c[str.charAt(m421a4)];
            if ((b | b2 | b3 | b4) < 0) {
                throw new IOException("invalid characters encountered in base64 data");
            }
            outputStream.write((b << 2) | (b2 >> 4));
            outputStream.write((b2 << 4) | (b3 >> 2));
            outputStream.write((b3 << 6) | b4);
            i2 += 3;
            m421a = m421a(str, i6, i);
        }
        return i2 + m422a(outputStream, str.charAt(i), str.charAt(length - 3), str.charAt(length - 2), str.charAt(length - 1));
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x00a2  */
    @Override // org.p415a.p448g.p449a.InterfaceC12962d
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int mo411a(byte[] r9, int r10, int r11, java.io.OutputStream r12) {
        /*
            r8 = this;
            int r0 = r11 % 3
            int r11 = r11 - r0
            r1 = r10
        L4:
            int r2 = r10 + r11
            r3 = 4
            if (r1 >= r2) goto L4b
            r2 = r9[r1]
            r2 = r2 & 255(0xff, float:3.57E-43)
            int r4 = r1 + 1
            r4 = r9[r4]
            r4 = r4 & 255(0xff, float:3.57E-43)
            int r5 = r1 + 2
            r5 = r9[r5]
            r5 = r5 & 255(0xff, float:3.57E-43)
            byte[] r6 = r8.f26239a
            int r7 = r2 >>> 2
            r7 = r7 & 63
            r6 = r6[r7]
            r12.write(r6)
            byte[] r6 = r8.f26239a
            int r2 = r2 << r3
            int r3 = r4 >>> 4
            r2 = r2 | r3
            r2 = r2 & 63
            r2 = r6[r2]
            r12.write(r2)
            byte[] r2 = r8.f26239a
            int r3 = r4 << 2
            int r4 = r5 >>> 6
            r3 = r3 | r4
            r3 = r3 & 63
            r2 = r2[r3]
            r12.write(r2)
            byte[] r2 = r8.f26239a
            r3 = r5 & 63
            r2 = r2[r3]
            r12.write(r2)
            int r1 = r1 + 3
            goto L4
        L4b:
            switch(r0) {
                case 0: goto L9d;
                case 1: goto L7a;
                case 2: goto L4f;
                default: goto L4e;
            }
        L4e:
            goto L9d
        L4f:
            r10 = r9[r2]
            r10 = r10 & 255(0xff, float:3.57E-43)
            int r2 = r2 + 1
            r9 = r9[r2]
            r9 = r9 & 255(0xff, float:3.57E-43)
            int r1 = r10 >>> 2
            r1 = r1 & 63
            int r10 = r10 << r3
            int r2 = r9 >>> 4
            r10 = r10 | r2
            r10 = r10 & 63
            int r9 = r9 << 2
            r9 = r9 & 63
            byte[] r2 = r8.f26239a
            r1 = r2[r1]
            r12.write(r1)
            byte[] r1 = r8.f26239a
            r10 = r1[r10]
            r12.write(r10)
            byte[] r10 = r8.f26239a
            r9 = r10[r9]
            goto L95
        L7a:
            r9 = r9[r2]
            r9 = r9 & 255(0xff, float:3.57E-43)
            int r10 = r9 >>> 2
            r10 = r10 & 63
            int r9 = r9 << r3
            r9 = r9 & 63
            byte[] r1 = r8.f26239a
            r10 = r1[r10]
            r12.write(r10)
            byte[] r10 = r8.f26239a
            r9 = r10[r9]
            r12.write(r9)
            byte r9 = r8.f26240b
        L95:
            r12.write(r9)
            byte r9 = r8.f26240b
            r12.write(r9)
        L9d:
            int r11 = r11 / 3
            int r11 = r11 * r3
            if (r0 != 0) goto La3
            r3 = 0
        La3:
            int r11 = r11 + r3
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: org.p415a.p448g.p449a.C12960b.mo411a(byte[], int, int, java.io.OutputStream):int");
    }

    /* renamed from: a */
    protected void m424a() {
        int i = 0;
        int i2 = 0;
        while (true) {
            byte[] bArr = this.f26241c;
            if (i2 >= bArr.length) {
                break;
            }
            bArr[i2] = -1;
            i2++;
        }
        while (true) {
            byte[] bArr2 = this.f26239a;
            if (i >= bArr2.length) {
                return;
            }
            this.f26241c[bArr2[i]] = (byte) i;
            i++;
        }
    }

    @Override // org.p415a.p448g.p449a.InterfaceC12962d
    /* renamed from: b */
    public int mo410b(byte[] bArr, int i, int i2, OutputStream outputStream) {
        int i3 = i2 + i;
        while (i3 > i && m423a((char) bArr[i3 - 1])) {
            i3--;
        }
        int i4 = i3 - 4;
        int m420a = m420a(bArr, i, i4);
        int i5 = 0;
        while (m420a < i4) {
            int i6 = m420a + 1;
            byte b = this.f26241c[bArr[m420a]];
            int m420a2 = m420a(bArr, i6, i4);
            int i7 = m420a2 + 1;
            byte b2 = this.f26241c[bArr[m420a2]];
            int m420a3 = m420a(bArr, i7, i4);
            int i8 = m420a3 + 1;
            byte b3 = this.f26241c[bArr[m420a3]];
            int m420a4 = m420a(bArr, i8, i4);
            int i9 = m420a4 + 1;
            byte b4 = this.f26241c[bArr[m420a4]];
            if ((b | b2 | b3 | b4) < 0) {
                throw new IOException("invalid characters encountered in base64 data");
            }
            outputStream.write((b << 2) | (b2 >> 4));
            outputStream.write((b2 << 4) | (b3 >> 2));
            outputStream.write((b3 << 6) | b4);
            i5 += 3;
            m420a = m420a(bArr, i9, i4);
        }
        return i5 + m422a(outputStream, (char) bArr[i4], (char) bArr[i3 - 3], (char) bArr[i3 - 2], (char) bArr[i3 - 1]);
    }
}
