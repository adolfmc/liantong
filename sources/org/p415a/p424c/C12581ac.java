package org.p415a.p424c;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Vector;
import org.p415a.p424c.p426b.C12595b;
import org.p415a.p424c.p426b.C12602i;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.c.ac */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12581ac extends AbstractC12611h {

    /* renamed from: a */
    private int f25551a;

    /* renamed from: b */
    private int f25552b;

    /* renamed from: c */
    private long f25553c;

    /* renamed from: d */
    private long f25554d;

    /* renamed from: e */
    private int f25555e;

    /* renamed from: f */
    private int f25556f;

    /* renamed from: g */
    private C12620q[] f25557g;

    /* renamed from: h */
    private byte[] f25558h;

    /* renamed from: i */
    private C12582ad[] f25559i;

    /* renamed from: j */
    private C12582ad[] f25560j;

    /* renamed from: k */
    private byte[] f25561k;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C12581ac(C12592b c12592b) {
        this.f25551a = c12592b.read();
        int i = this.f25551a;
        if (i == 3 || i == 2) {
            c12592b.read();
            this.f25552b = c12592b.read();
            this.f25553c = ((c12592b.read() << 24) | (c12592b.read() << 16) | (c12592b.read() << 8) | c12592b.read()) * 1000;
            this.f25554d |= c12592b.read() << 56;
            this.f25554d |= c12592b.read() << 48;
            this.f25554d |= c12592b.read() << 40;
            this.f25554d |= c12592b.read() << 32;
            this.f25554d |= c12592b.read() << 24;
            this.f25554d |= c12592b.read() << 16;
            this.f25554d |= c12592b.read() << 8;
            this.f25554d |= c12592b.read();
            this.f25555e = c12592b.read();
            this.f25556f = c12592b.read();
        } else if (i != 4) {
            throw new RuntimeException("unsupported version: " + this.f25551a);
        } else {
            this.f25552b = c12592b.read();
            this.f25555e = c12592b.read();
            this.f25556f = c12592b.read();
            byte[] bArr = new byte[(c12592b.read() << 8) | c12592b.read()];
            c12592b.m1576a(bArr);
            C12583ae c12583ae = new C12583ae(new ByteArrayInputStream(bArr));
            Vector vector = new Vector();
            while (true) {
                C12582ad m1583a = c12583ae.m1583a();
                if (m1583a == null) {
                    break;
                }
                vector.addElement(m1583a);
            }
            this.f25559i = new C12582ad[vector.size()];
            for (int i2 = 0; i2 != this.f25559i.length; i2++) {
                C12582ad c12582ad = (C12582ad) vector.elementAt(i2);
                if (c12582ad instanceof C12595b) {
                    this.f25554d = ((C12595b) c12582ad).m1571a();
                } else if (c12582ad instanceof C12602i) {
                    this.f25553c = ((C12602i) c12582ad).m1570a().getTime();
                }
                this.f25559i[i2] = c12582ad;
            }
            byte[] bArr2 = new byte[(c12592b.read() << 8) | c12592b.read()];
            c12592b.m1576a(bArr2);
            C12583ae c12583ae2 = new C12583ae(new ByteArrayInputStream(bArr2));
            vector.removeAllElements();
            while (true) {
                C12582ad m1583a2 = c12583ae2.m1583a();
                if (m1583a2 == null) {
                    break;
                }
                vector.addElement(m1583a2);
            }
            this.f25560j = new C12582ad[vector.size()];
            for (int i3 = 0; i3 != this.f25560j.length; i3++) {
                C12582ad c12582ad2 = (C12582ad) vector.elementAt(i3);
                if (c12582ad2 instanceof C12595b) {
                    this.f25554d = ((C12595b) c12582ad2).m1571a();
                }
                this.f25560j[i3] = c12582ad2;
            }
        }
        this.f25558h = new byte[2];
        c12592b.m1576a(this.f25558h);
        int i4 = this.f25555e;
        if (i4 == 1 || i4 == 3) {
            C12620q c12620q = new C12620q(c12592b);
            this.f25557g = new C12620q[1];
            this.f25557g[0] = c12620q;
            return;
        }
        switch (i4) {
            case 16:
                break;
            case 17:
                C12620q c12620q2 = new C12620q(c12592b);
                C12620q c12620q3 = new C12620q(c12592b);
                this.f25557g = new C12620q[2];
                C12620q[] c12620qArr = this.f25557g;
                c12620qArr[0] = c12620q2;
                c12620qArr[1] = c12620q3;
                return;
            default:
                switch (i4) {
                    case 19:
                        C12620q c12620q4 = new C12620q(c12592b);
                        C12620q c12620q5 = new C12620q(c12592b);
                        this.f25557g = new C12620q[2];
                        C12620q[] c12620qArr2 = this.f25557g;
                        c12620qArr2[0] = c12620q4;
                        c12620qArr2[1] = c12620q5;
                        return;
                    case 20:
                        break;
                    default:
                        if (i4 < 100 || i4 > 110) {
                            throw new IOException("unknown signature key algorithm: " + this.f25555e);
                        }
                        this.f25557g = null;
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        while (true) {
                            int read = c12592b.read();
                            if (read < 0) {
                                this.f25561k = byteArrayOutputStream.toByteArray();
                                return;
                            }
                            byteArrayOutputStream.write(read);
                        }
                        break;
                }
        }
        C12620q c12620q6 = new C12620q(c12592b);
        C12620q c12620q7 = new C12620q(c12592b);
        C12620q c12620q8 = new C12620q(c12592b);
        this.f25557g = new C12620q[3];
        C12620q[] c12620qArr3 = this.f25557g;
        c12620qArr3[0] = c12620q6;
        c12620qArr3[1] = c12620q7;
        c12620qArr3[2] = c12620q8;
    }

    /* renamed from: a */
    public int m1585a() {
        return this.f25552b;
    }

    @Override // org.p415a.p424c.AbstractC12611h
    /* renamed from: a */
    public void mo1537a(C12608e c12608e) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        C12608e c12608e2 = new C12608e(byteArrayOutputStream);
        c12608e2.write(this.f25551a);
        int i = this.f25551a;
        int i2 = 0;
        if (i == 3 || i == 2) {
            c12608e2.write(5);
            long j = this.f25553c / 1000;
            c12608e2.write(this.f25552b);
            c12608e2.write((byte) (j >> 24));
            c12608e2.write((byte) (j >> 16));
            c12608e2.write((byte) (j >> 8));
            c12608e2.write((byte) j);
            c12608e2.write((byte) (this.f25554d >> 56));
            c12608e2.write((byte) (this.f25554d >> 48));
            c12608e2.write((byte) (this.f25554d >> 40));
            c12608e2.write((byte) (this.f25554d >> 32));
            c12608e2.write((byte) (this.f25554d >> 24));
            c12608e2.write((byte) (this.f25554d >> 16));
            c12608e2.write((byte) (this.f25554d >> 8));
            c12608e2.write((byte) this.f25554d);
            c12608e2.write(this.f25555e);
            c12608e2.write(this.f25556f);
        } else if (i != 4) {
            throw new IOException("unknown version: " + this.f25551a);
        } else {
            c12608e2.write(this.f25552b);
            c12608e2.write(this.f25555e);
            c12608e2.write(this.f25556f);
            ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
            int i3 = 0;
            while (true) {
                C12582ad[] c12582adArr = this.f25559i;
                if (i3 == c12582adArr.length) {
                    break;
                }
                c12582adArr[i3].m1584a(byteArrayOutputStream2);
                i3++;
            }
            byte[] byteArray = byteArrayOutputStream2.toByteArray();
            c12608e2.write(byteArray.length >> 8);
            c12608e2.write(byteArray.length);
            c12608e2.write(byteArray);
            byteArrayOutputStream2.reset();
            int i4 = 0;
            while (true) {
                C12582ad[] c12582adArr2 = this.f25560j;
                if (i4 == c12582adArr2.length) {
                    break;
                }
                c12582adArr2[i4].m1584a(byteArrayOutputStream2);
                i4++;
            }
            byte[] byteArray2 = byteArrayOutputStream2.toByteArray();
            c12608e2.write(byteArray2.length >> 8);
            c12608e2.write(byteArray2.length);
            c12608e2.write(byteArray2);
        }
        c12608e2.write(this.f25558h);
        if (this.f25557g != null) {
            while (true) {
                C12620q[] c12620qArr = this.f25557g;
                if (i2 == c12620qArr.length) {
                    break;
                }
                c12608e2.m1564a(c12620qArr[i2]);
                i2++;
            }
        } else {
            c12608e2.write(this.f25561k);
        }
        c12608e2.close();
        c12608e.m1566a(2, byteArrayOutputStream.toByteArray(), true);
    }
}
