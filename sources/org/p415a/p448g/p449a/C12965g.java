package org.p415a.p448g.p449a;

import java.io.IOException;
import java.io.OutputStream;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p038j0.C0548c;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.g.a.g */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12965g implements InterfaceC12962d {

    /* renamed from: a */
    protected final byte[] f26245a = {48, 49, 50, 51, 52, 53, C0548c.f1784h, 55, 56, 57, 97, 98, 99, 100, 101, 102};

    /* renamed from: b */
    protected final byte[] f26246b = new byte[128];

    public C12965g() {
        m414a();
    }

    /* renamed from: a */
    private static boolean m413a(char c) {
        return c == '\n' || c == '\r' || c == '\t' || c == ' ';
    }

    @Override // org.p415a.p448g.p449a.InterfaceC12962d
    /* renamed from: a */
    public int mo412a(String str, OutputStream outputStream) {
        int length = str.length();
        while (length > 0 && m413a(str.charAt(length - 1))) {
            length--;
        }
        int i = 0;
        int i2 = 0;
        while (i < length) {
            while (i < length && m413a(str.charAt(i))) {
                i++;
            }
            int i3 = i + 1;
            byte b = this.f26246b[str.charAt(i)];
            while (i3 < length && m413a(str.charAt(i3))) {
                i3++;
            }
            int i4 = i3 + 1;
            byte b2 = this.f26246b[str.charAt(i3)];
            if ((b | b2) < 0) {
                throw new IOException("invalid characters encountered in Hex string");
            }
            outputStream.write((b << 4) | b2);
            i2++;
            i = i4;
        }
        return i2;
    }

    @Override // org.p415a.p448g.p449a.InterfaceC12962d
    /* renamed from: a */
    public int mo411a(byte[] bArr, int i, int i2, OutputStream outputStream) {
        for (int i3 = i; i3 < i + i2; i3++) {
            int i4 = bArr[i3] & 255;
            outputStream.write(this.f26245a[i4 >>> 4]);
            outputStream.write(this.f26245a[i4 & 15]);
        }
        return i2 * 2;
    }

    /* renamed from: a */
    protected void m414a() {
        int i = 0;
        int i2 = 0;
        while (true) {
            byte[] bArr = this.f26246b;
            if (i2 >= bArr.length) {
                break;
            }
            bArr[i2] = -1;
            i2++;
        }
        while (true) {
            byte[] bArr2 = this.f26245a;
            if (i >= bArr2.length) {
                byte[] bArr3 = this.f26246b;
                bArr3[65] = bArr3[97];
                bArr3[66] = bArr3[98];
                bArr3[67] = bArr3[99];
                bArr3[68] = bArr3[100];
                bArr3[69] = bArr3[101];
                bArr3[70] = bArr3[102];
                return;
            }
            this.f26246b[bArr2[i]] = (byte) i;
            i++;
        }
    }

    @Override // org.p415a.p448g.p449a.InterfaceC12962d
    /* renamed from: b */
    public int mo410b(byte[] bArr, int i, int i2, OutputStream outputStream) {
        int i3 = i2 + i;
        while (i3 > i && m413a((char) bArr[i3 - 1])) {
            i3--;
        }
        int i4 = 0;
        while (i < i3) {
            while (i < i3 && m413a((char) bArr[i])) {
                i++;
            }
            int i5 = i + 1;
            byte b = this.f26246b[bArr[i]];
            while (i5 < i3 && m413a((char) bArr[i5])) {
                i5++;
            }
            int i6 = i5 + 1;
            byte b2 = this.f26246b[bArr[i5]];
            if ((b | b2) < 0) {
                throw new IOException("invalid characters encountered in Hex data");
            }
            outputStream.write((b << 4) | b2);
            i4++;
            i = i6;
        }
        return i4;
    }
}
