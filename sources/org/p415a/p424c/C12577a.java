package org.p415a.p424c;

import java.io.EOFException;
import java.io.InputStream;
import org.p415a.p448g.C12975h;
import org.p415a.p448g.InterfaceC12974g;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.c.a */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12577a extends InputStream {

    /* renamed from: o */
    private static final byte[] f25525o = new byte[128];

    /* renamed from: a */
    InputStream f25526a;

    /* renamed from: b */
    boolean f25527b;

    /* renamed from: c */
    int[] f25528c;

    /* renamed from: d */
    int f25529d;

    /* renamed from: e */
    C12609f f25530e;

    /* renamed from: f */
    boolean f25531f;

    /* renamed from: g */
    boolean f25532g;

    /* renamed from: h */
    String f25533h;

    /* renamed from: i */
    boolean f25534i;

    /* renamed from: j */
    boolean f25535j;

    /* renamed from: k */
    boolean f25536k;

    /* renamed from: l */
    InterfaceC12974g f25537l;

    /* renamed from: m */
    int f25538m;

    /* renamed from: n */
    boolean f25539n;

    static {
        for (int i = 65; i <= 90; i++) {
            f25525o[i] = (byte) (i - 65);
        }
        for (int i2 = 97; i2 <= 122; i2++) {
            f25525o[i2] = (byte) ((i2 - 97) + 26);
        }
        for (int i3 = 48; i3 <= 57; i3++) {
            f25525o[i3] = (byte) ((i3 - 48) + 52);
        }
        byte[] bArr = f25525o;
        bArr[43] = 62;
        bArr[47] = 63;
    }

    public C12577a(InputStream inputStream) {
        this(inputStream, true);
    }

    public C12577a(InputStream inputStream, boolean z) {
        this.f25527b = true;
        this.f25528c = new int[3];
        this.f25529d = 3;
        this.f25530e = new C12609f();
        this.f25531f = false;
        this.f25532g = true;
        this.f25533h = null;
        this.f25534i = false;
        this.f25535j = false;
        this.f25536k = false;
        this.f25537l = C12975h.m392a();
        this.f25538m = 0;
        this.f25526a = inputStream;
        this.f25532g = z;
        if (z) {
            m1590a();
        }
        this.f25527b = false;
    }

    /* renamed from: a */
    private int m1589a(int i, int i2, int i3, int i4, int[] iArr) {
        if (i4 >= 0) {
            if (i3 == 61) {
                byte[] bArr = f25525o;
                iArr[2] = (((bArr[i] & 255) << 2) | ((bArr[i2] & 255) >> 4)) & 255;
                return 2;
            } else if (i4 == 61) {
                byte[] bArr2 = f25525o;
                byte b = bArr2[i];
                byte b2 = bArr2[i2];
                byte b3 = bArr2[i3];
                iArr[1] = ((b << 2) | (b2 >> 4)) & 255;
                iArr[2] = ((b2 << 4) | (b3 >> 2)) & 255;
                return 1;
            } else {
                byte[] bArr3 = f25525o;
                byte b4 = bArr3[i];
                byte b5 = bArr3[i2];
                byte b6 = bArr3[i3];
                byte b7 = bArr3[i4];
                iArr[0] = ((b4 << 2) | (b5 >> 4)) & 255;
                iArr[1] = ((b5 << 4) | (b6 >> 2)) & 255;
                iArr[2] = ((b6 << 6) | b7) & 255;
                return 0;
            }
        }
        throw new EOFException("unexpected end of file in armored stream.");
    }

    /* renamed from: a */
    private boolean m1590a() {
        int i;
        boolean z;
        this.f25533h = null;
        this.f25537l = C12975h.m392a();
        if (!this.f25536k) {
            int i2 = 0;
            while (true) {
                int read = this.f25526a.read();
                if (read < 0) {
                    i = i2;
                    z = false;
                    break;
                } else if (read != 45 || (i2 != 0 && i2 != 10 && i2 != 13)) {
                    i2 = read;
                }
            }
        } else {
            z = true;
            i = 0;
        }
        if (z) {
            StringBuffer stringBuffer = new StringBuffer("-");
            if (this.f25536k) {
                stringBuffer.append('-');
            }
            boolean z2 = false;
            boolean z3 = false;
            while (true) {
                int read2 = this.f25526a.read();
                if (read2 >= 0) {
                    if (i == 13 && read2 == 10) {
                        z3 = true;
                    }
                    if ((z2 && i != 13 && read2 == 10) || (z2 && read2 == 13)) {
                        break;
                    }
                    if (read2 == 13 || (i != 13 && read2 == 10)) {
                        String stringBuffer2 = stringBuffer.toString();
                        if (stringBuffer2.trim().length() == 0) {
                            break;
                        }
                        this.f25537l.mo381a(stringBuffer2);
                        stringBuffer.setLength(0);
                    }
                    if (read2 != 10 && read2 != 13) {
                        stringBuffer.append((char) read2);
                        z2 = false;
                    } else if (read2 == 13 || (i != 13 && read2 == 10)) {
                        z2 = true;
                    }
                    i = read2;
                } else {
                    break;
                }
            }
            if (z3) {
                this.f25526a.read();
            }
        }
        if (this.f25537l.size() > 0) {
            this.f25533h = this.f25537l.mo383a(0);
        }
        this.f25535j = "-----BEGIN PGP SIGNED MESSAGE-----".equals(this.f25533h);
        this.f25534i = true;
        return z;
    }

    /* renamed from: b */
    private int m1588b() {
        while (true) {
            int read = this.f25526a.read();
            if (read != 32 && read != 9) {
                return read;
            }
        }
    }

    @Override // java.io.InputStream
    public int available() {
        return this.f25526a.available();
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.f25526a.close();
    }

    /* JADX WARN: Code restructure failed: missing block: B:26:0x004f, code lost:
        if (r9.f25538m != 13) goto L20;
     */
    @Override // java.io.InputStream
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int read() {
        /*
            Method dump skipped, instructions count: 293
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.p415a.p424c.C12577a.read():int");
    }
}
