package com.xiaomi.push.service;

import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import com.xiaomi.push.C11180ay;

/* renamed from: com.xiaomi.push.service.ar */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11561ar {

    /* renamed from: a */
    private static int f23608a = 8;

    /* renamed from: d */
    private int f23612d = -666;

    /* renamed from: a */
    private byte[] f23609a = new byte[256];

    /* renamed from: c */
    private int f23611c = 0;

    /* renamed from: b */
    private int f23610b = 0;

    /* renamed from: a */
    public static int m2650a(byte b) {
        return b >= 0 ? b : b + 256;
    }

    /* renamed from: a */
    private void m2649a(int i, byte[] bArr, boolean z) {
        int length = bArr.length;
        for (int i2 = 0; i2 < 256; i2++) {
            this.f23609a[i2] = (byte) i2;
        }
        this.f23611c = 0;
        this.f23610b = 0;
        while (true) {
            int i3 = this.f23610b;
            if (i3 >= i) {
                break;
            }
            this.f23611c = ((this.f23611c + m2650a(this.f23609a[i3])) + m2650a(bArr[this.f23610b % length])) % 256;
            m2646a(this.f23609a, this.f23610b, this.f23611c);
            this.f23610b++;
        }
        if (i != 256) {
            this.f23612d = ((this.f23611c + m2650a(this.f23609a[i])) + m2650a(bArr[i % length])) % 256;
        }
        if (z) {
            StringBuilder sb = new StringBuilder();
            sb.append("S_");
            int i4 = i - 1;
            sb.append(i4);
            sb.append(":");
            for (int i5 = 0; i5 <= i; i5++) {
                sb.append(" ");
                sb.append(m2650a(this.f23609a[i5]));
            }
            sb.append("   j_");
            sb.append(i4);
            sb.append("=");
            sb.append(this.f23611c);
            sb.append("   j_");
            sb.append(i);
            sb.append("=");
            sb.append(this.f23612d);
            sb.append("   S_");
            sb.append(i4);
            sb.append("[j_");
            sb.append(i4);
            sb.append("]=");
            sb.append(m2650a(this.f23609a[this.f23611c]));
            sb.append("   S_");
            sb.append(i4);
            sb.append("[j_");
            sb.append(i);
            sb.append("]=");
            sb.append(m2650a(this.f23609a[this.f23612d]));
            if (this.f23609a[1] != 0) {
                sb.append("   S[1]!=0");
            }
            AbstractC11049b.m5282a(sb.toString());
        }
    }

    /* renamed from: a */
    private void m2647a(byte[] bArr) {
        m2649a(256, bArr, false);
    }

    /* renamed from: a */
    private void m2651a() {
        this.f23611c = 0;
        this.f23610b = 0;
    }

    /* renamed from: a */
    byte m2652a() {
        this.f23610b = (this.f23610b + 1) % 256;
        this.f23611c = (this.f23611c + m2650a(this.f23609a[this.f23610b])) % 256;
        m2646a(this.f23609a, this.f23610b, this.f23611c);
        byte[] bArr = this.f23609a;
        return bArr[(m2650a(bArr[this.f23610b]) + m2650a(this.f23609a[this.f23611c])) % 256];
    }

    /* renamed from: a */
    private static void m2646a(byte[] bArr, int i, int i2) {
        byte b = bArr[i];
        bArr[i] = bArr[i2];
        bArr[i2] = b;
    }

    /* renamed from: a */
    public static byte[] m2644a(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[bArr2.length];
        C11561ar c11561ar = new C11561ar();
        c11561ar.m2647a(bArr);
        c11561ar.m2651a();
        for (int i = 0; i < bArr2.length; i++) {
            bArr3[i] = (byte) (bArr2[i] ^ c11561ar.m2652a());
        }
        return bArr3;
    }

    /* renamed from: a */
    public static byte[] m2643a(byte[] bArr, byte[] bArr2, boolean z, int i, int i2) {
        byte[] bArr3;
        int i3;
        if (i < 0 || i > bArr2.length || i + i2 > bArr2.length) {
            throw new IllegalArgumentException("start = " + i + " len = " + i2);
        }
        if (z) {
            bArr3 = bArr2;
            i3 = i;
        } else {
            bArr3 = new byte[i2];
            i3 = 0;
        }
        C11561ar c11561ar = new C11561ar();
        c11561ar.m2647a(bArr);
        c11561ar.m2651a();
        for (int i4 = 0; i4 < i2; i4++) {
            bArr3[i3 + i4] = (byte) (bArr2[i + i4] ^ c11561ar.m2652a());
        }
        return bArr3;
    }

    /* renamed from: a */
    public static byte[] m2645a(byte[] bArr, String str) {
        return m2644a(bArr, C11180ay.m4796a(str));
    }

    /* renamed from: a */
    public static byte[] m2648a(String str, String str2) {
        byte[] m4796a = C11180ay.m4796a(str);
        byte[] bytes = str2.getBytes();
        byte[] bArr = new byte[m4796a.length + 1 + bytes.length];
        for (int i = 0; i < m4796a.length; i++) {
            bArr[i] = m4796a[i];
        }
        bArr[m4796a.length] = 95;
        for (int i2 = 0; i2 < bytes.length; i2++) {
            bArr[m4796a.length + 1 + i2] = bytes[i2];
        }
        return bArr;
    }
}
