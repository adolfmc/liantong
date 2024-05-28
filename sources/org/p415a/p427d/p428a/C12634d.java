package org.p415a.p427d.p428a;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.d.a.d */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12634d extends AbstractC12631a {

    /* renamed from: a */
    private int f25660a;

    /* renamed from: b */
    private int f25661b;

    /* renamed from: c */
    private int f25662c;

    /* renamed from: d */
    private int f25663d;

    /* renamed from: e */
    private int[] f25664e = new int[16];

    /* renamed from: f */
    private int f25665f;

    public C12634d() {
        mo1484c();
    }

    /* renamed from: a */
    private int m1512a(int i, int i2) {
        return (i >>> (32 - i2)) | (i << i2);
    }

    /* renamed from: a */
    private int m1511a(int i, int i2, int i3) {
        return ((~i) & i3) | (i2 & i);
    }

    /* renamed from: a */
    private void m1510a(int i, byte[] bArr, int i2) {
        bArr[i2] = (byte) i;
        bArr[i2 + 1] = (byte) (i >>> 8);
        bArr[i2 + 2] = (byte) (i >>> 16);
        bArr[i2 + 3] = (byte) (i >>> 24);
    }

    /* renamed from: b */
    private int m1509b(int i, int i2, int i3) {
        return (i & i3) | (i2 & (~i3));
    }

    /* renamed from: c */
    private int m1508c(int i, int i2, int i3) {
        return (i ^ i2) ^ i3;
    }

    /* renamed from: d */
    private int m1507d(int i, int i2, int i3) {
        return (i | (~i3)) ^ i2;
    }

    @Override // org.p415a.p427d.InterfaceC12724i
    /* renamed from: a */
    public int mo1314a() {
        return 16;
    }

    @Override // org.p415a.p427d.InterfaceC12724i
    /* renamed from: a */
    public int mo1312a(byte[] bArr, int i) {
        m1527b();
        m1510a(this.f25660a, bArr, i);
        m1510a(this.f25661b, bArr, i + 4);
        m1510a(this.f25662c, bArr, i + 8);
        m1510a(this.f25663d, bArr, i + 12);
        mo1484c();
        return 16;
    }

    @Override // org.p415a.p427d.p428a.AbstractC12631a
    /* renamed from: a */
    protected void mo1488a(long j) {
        if (this.f25665f > 14) {
            mo1482d();
        }
        int[] iArr = this.f25664e;
        iArr[14] = (int) ((-1) & j);
        iArr[15] = (int) (j >>> 32);
    }

    @Override // org.p415a.p427d.p428a.AbstractC12631a
    /* renamed from: b */
    protected void mo1485b(byte[] bArr, int i) {
        int[] iArr = this.f25664e;
        int i2 = this.f25665f;
        this.f25665f = i2 + 1;
        iArr[i2] = ((bArr[i + 3] & 255) << 24) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16);
        if (this.f25665f == 16) {
            mo1482d();
        }
    }

    @Override // org.p415a.p427d.p428a.AbstractC12631a
    /* renamed from: c */
    public void mo1484c() {
        super.mo1484c();
        this.f25660a = 1732584193;
        this.f25661b = -271733879;
        this.f25662c = -1732584194;
        this.f25663d = 271733878;
        this.f25665f = 0;
        int i = 0;
        while (true) {
            int[] iArr = this.f25664e;
            if (i == iArr.length) {
                return;
            }
            iArr[i] = 0;
            i++;
        }
    }

    @Override // org.p415a.p427d.p428a.AbstractC12631a
    /* renamed from: d */
    protected void mo1482d() {
        int i = this.f25660a;
        int i2 = this.f25661b;
        int i3 = this.f25662c;
        int i4 = this.f25663d;
        int m1512a = m1512a(((i + m1511a(i2, i3, i4)) + this.f25664e[0]) - 680876936, 7) + i2;
        int m1512a2 = m1512a(((i4 + m1511a(m1512a, i2, i3)) + this.f25664e[1]) - 389564586, 12) + m1512a;
        int m1512a3 = m1512a(i3 + m1511a(m1512a2, m1512a, i2) + this.f25664e[2] + 606105819, 17) + m1512a2;
        int m1512a4 = m1512a(((i2 + m1511a(m1512a3, m1512a2, m1512a)) + this.f25664e[3]) - 1044525330, 22) + m1512a3;
        int m1512a5 = m1512a(((m1512a + m1511a(m1512a4, m1512a3, m1512a2)) + this.f25664e[4]) - 176418897, 7) + m1512a4;
        int m1512a6 = m1512a(m1512a2 + m1511a(m1512a5, m1512a4, m1512a3) + this.f25664e[5] + 1200080426, 12) + m1512a5;
        int m1512a7 = m1512a(((m1512a3 + m1511a(m1512a6, m1512a5, m1512a4)) + this.f25664e[6]) - 1473231341, 17) + m1512a6;
        int m1512a8 = m1512a(((m1512a4 + m1511a(m1512a7, m1512a6, m1512a5)) + this.f25664e[7]) - 45705983, 22) + m1512a7;
        int m1512a9 = m1512a(m1512a5 + m1511a(m1512a8, m1512a7, m1512a6) + this.f25664e[8] + 1770035416, 7) + m1512a8;
        int m1512a10 = m1512a(((m1512a6 + m1511a(m1512a9, m1512a8, m1512a7)) + this.f25664e[9]) - 1958414417, 12) + m1512a9;
        int m1512a11 = m1512a(((m1512a7 + m1511a(m1512a10, m1512a9, m1512a8)) + this.f25664e[10]) - 42063, 17) + m1512a10;
        int m1512a12 = m1512a(((m1512a8 + m1511a(m1512a11, m1512a10, m1512a9)) + this.f25664e[11]) - 1990404162, 22) + m1512a11;
        int m1512a13 = m1512a(m1512a9 + m1511a(m1512a12, m1512a11, m1512a10) + this.f25664e[12] + 1804603682, 7) + m1512a12;
        int m1512a14 = m1512a(((m1512a10 + m1511a(m1512a13, m1512a12, m1512a11)) + this.f25664e[13]) - 40341101, 12) + m1512a13;
        int m1512a15 = m1512a(((m1512a11 + m1511a(m1512a14, m1512a13, m1512a12)) + this.f25664e[14]) - 1502002290, 17) + m1512a14;
        int m1512a16 = m1512a(m1512a12 + m1511a(m1512a15, m1512a14, m1512a13) + this.f25664e[15] + 1236535329, 22) + m1512a15;
        int m1512a17 = m1512a(((m1512a13 + m1509b(m1512a16, m1512a15, m1512a14)) + this.f25664e[1]) - 165796510, 5) + m1512a16;
        int m1512a18 = m1512a(((m1512a14 + m1509b(m1512a17, m1512a16, m1512a15)) + this.f25664e[6]) - 1069501632, 9) + m1512a17;
        int m1512a19 = m1512a(m1512a15 + m1509b(m1512a18, m1512a17, m1512a16) + this.f25664e[11] + 643717713, 14) + m1512a18;
        int m1512a20 = m1512a(((m1512a16 + m1509b(m1512a19, m1512a18, m1512a17)) + this.f25664e[0]) - 373897302, 20) + m1512a19;
        int m1512a21 = m1512a(((m1512a17 + m1509b(m1512a20, m1512a19, m1512a18)) + this.f25664e[5]) - 701558691, 5) + m1512a20;
        int m1512a22 = m1512a(m1512a18 + m1509b(m1512a21, m1512a20, m1512a19) + this.f25664e[10] + 38016083, 9) + m1512a21;
        int m1512a23 = m1512a(((m1512a19 + m1509b(m1512a22, m1512a21, m1512a20)) + this.f25664e[15]) - 660478335, 14) + m1512a22;
        int m1512a24 = m1512a(((m1512a20 + m1509b(m1512a23, m1512a22, m1512a21)) + this.f25664e[4]) - 405537848, 20) + m1512a23;
        int m1512a25 = m1512a(m1512a21 + m1509b(m1512a24, m1512a23, m1512a22) + this.f25664e[9] + 568446438, 5) + m1512a24;
        int m1512a26 = m1512a(((m1512a22 + m1509b(m1512a25, m1512a24, m1512a23)) + this.f25664e[14]) - 1019803690, 9) + m1512a25;
        int m1512a27 = m1512a(((m1512a23 + m1509b(m1512a26, m1512a25, m1512a24)) + this.f25664e[3]) - 187363961, 14) + m1512a26;
        int m1512a28 = m1512a(m1512a24 + m1509b(m1512a27, m1512a26, m1512a25) + this.f25664e[8] + 1163531501, 20) + m1512a27;
        int m1512a29 = m1512a(((m1512a25 + m1509b(m1512a28, m1512a27, m1512a26)) + this.f25664e[13]) - 1444681467, 5) + m1512a28;
        int m1512a30 = m1512a(((m1512a26 + m1509b(m1512a29, m1512a28, m1512a27)) + this.f25664e[2]) - 51403784, 9) + m1512a29;
        int m1512a31 = m1512a(m1512a27 + m1509b(m1512a30, m1512a29, m1512a28) + this.f25664e[7] + 1735328473, 14) + m1512a30;
        int m1512a32 = m1512a(((m1512a28 + m1509b(m1512a31, m1512a30, m1512a29)) + this.f25664e[12]) - 1926607734, 20) + m1512a31;
        int m1512a33 = m1512a(((m1512a29 + m1508c(m1512a32, m1512a31, m1512a30)) + this.f25664e[5]) - 378558, 4) + m1512a32;
        int m1512a34 = m1512a(((m1512a30 + m1508c(m1512a33, m1512a32, m1512a31)) + this.f25664e[8]) - 2022574463, 11) + m1512a33;
        int m1512a35 = m1512a(m1512a31 + m1508c(m1512a34, m1512a33, m1512a32) + this.f25664e[11] + 1839030562, 16) + m1512a34;
        int m1512a36 = m1512a(((m1512a32 + m1508c(m1512a35, m1512a34, m1512a33)) + this.f25664e[14]) - 35309556, 23) + m1512a35;
        int m1512a37 = m1512a(((m1512a33 + m1508c(m1512a36, m1512a35, m1512a34)) + this.f25664e[1]) - 1530992060, 4) + m1512a36;
        int m1512a38 = m1512a(m1512a34 + m1508c(m1512a37, m1512a36, m1512a35) + this.f25664e[4] + 1272893353, 11) + m1512a37;
        int m1512a39 = m1512a(((m1512a35 + m1508c(m1512a38, m1512a37, m1512a36)) + this.f25664e[7]) - 155497632, 16) + m1512a38;
        int m1512a40 = m1512a(((m1512a36 + m1508c(m1512a39, m1512a38, m1512a37)) + this.f25664e[10]) - 1094730640, 23) + m1512a39;
        int m1512a41 = m1512a(m1512a37 + m1508c(m1512a40, m1512a39, m1512a38) + this.f25664e[13] + 681279174, 4) + m1512a40;
        int m1512a42 = m1512a(((m1512a38 + m1508c(m1512a41, m1512a40, m1512a39)) + this.f25664e[0]) - 358537222, 11) + m1512a41;
        int m1512a43 = m1512a(((m1512a39 + m1508c(m1512a42, m1512a41, m1512a40)) + this.f25664e[3]) - 722521979, 16) + m1512a42;
        int m1512a44 = m1512a(m1512a40 + m1508c(m1512a43, m1512a42, m1512a41) + this.f25664e[6] + 76029189, 23) + m1512a43;
        int m1512a45 = m1512a(((m1512a41 + m1508c(m1512a44, m1512a43, m1512a42)) + this.f25664e[9]) - 640364487, 4) + m1512a44;
        int m1512a46 = m1512a(((m1512a42 + m1508c(m1512a45, m1512a44, m1512a43)) + this.f25664e[12]) - 421815835, 11) + m1512a45;
        int m1512a47 = m1512a(m1512a43 + m1508c(m1512a46, m1512a45, m1512a44) + this.f25664e[15] + 530742520, 16) + m1512a46;
        int m1512a48 = m1512a(((m1512a44 + m1508c(m1512a47, m1512a46, m1512a45)) + this.f25664e[2]) - 995338651, 23) + m1512a47;
        int m1512a49 = m1512a(((m1512a45 + m1507d(m1512a48, m1512a47, m1512a46)) + this.f25664e[0]) - 198630844, 6) + m1512a48;
        int m1512a50 = m1512a(m1512a46 + m1507d(m1512a49, m1512a48, m1512a47) + this.f25664e[7] + 1126891415, 10) + m1512a49;
        int m1512a51 = m1512a(((m1512a47 + m1507d(m1512a50, m1512a49, m1512a48)) + this.f25664e[14]) - 1416354905, 15) + m1512a50;
        int m1512a52 = m1512a(((m1512a48 + m1507d(m1512a51, m1512a50, m1512a49)) + this.f25664e[5]) - 57434055, 21) + m1512a51;
        int m1512a53 = m1512a(m1512a49 + m1507d(m1512a52, m1512a51, m1512a50) + this.f25664e[12] + 1700485571, 6) + m1512a52;
        int m1512a54 = m1512a(((m1512a50 + m1507d(m1512a53, m1512a52, m1512a51)) + this.f25664e[3]) - 1894986606, 10) + m1512a53;
        int m1512a55 = m1512a(((m1512a51 + m1507d(m1512a54, m1512a53, m1512a52)) + this.f25664e[10]) - 1051523, 15) + m1512a54;
        int m1512a56 = m1512a(((m1512a52 + m1507d(m1512a55, m1512a54, m1512a53)) + this.f25664e[1]) - 2054922799, 21) + m1512a55;
        int m1512a57 = m1512a(m1512a53 + m1507d(m1512a56, m1512a55, m1512a54) + this.f25664e[8] + 1873313359, 6) + m1512a56;
        int m1512a58 = m1512a(((m1512a54 + m1507d(m1512a57, m1512a56, m1512a55)) + this.f25664e[15]) - 30611744, 10) + m1512a57;
        int m1512a59 = m1512a(((m1512a55 + m1507d(m1512a58, m1512a57, m1512a56)) + this.f25664e[6]) - 1560198380, 15) + m1512a58;
        int m1512a60 = m1512a(m1512a56 + m1507d(m1512a59, m1512a58, m1512a57) + this.f25664e[13] + 1309151649, 21) + m1512a59;
        int m1512a61 = m1512a(((m1512a57 + m1507d(m1512a60, m1512a59, m1512a58)) + this.f25664e[4]) - 145523070, 6) + m1512a60;
        int m1512a62 = m1512a(((m1512a58 + m1507d(m1512a61, m1512a60, m1512a59)) + this.f25664e[11]) - 1120210379, 10) + m1512a61;
        int m1512a63 = m1512a(m1512a59 + m1507d(m1512a62, m1512a61, m1512a60) + this.f25664e[2] + 718787259, 15) + m1512a62;
        this.f25660a += m1512a61;
        this.f25661b += m1512a(((m1512a60 + m1507d(m1512a63, m1512a62, m1512a61)) + this.f25664e[9]) - 343485551, 21) + m1512a63;
        this.f25662c += m1512a63;
        this.f25663d += m1512a62;
        this.f25665f = 0;
        int i5 = 0;
        while (true) {
            int[] iArr = this.f25664e;
            if (i5 == iArr.length) {
                return;
            }
            iArr[i5] = 0;
            i5++;
        }
    }
}
