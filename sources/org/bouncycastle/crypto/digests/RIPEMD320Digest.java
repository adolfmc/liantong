package org.bouncycastle.crypto.digests;

import org.bouncycastle.crypto.CryptoServiceProperties;
import org.bouncycastle.crypto.CryptoServicePurpose;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.util.Memoable;
import org.bouncycastle.util.Pack;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p032d0.C0512f;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class RIPEMD320Digest extends GeneralDigest {
    private static final int DIGEST_LENGTH = 40;

    /* renamed from: H0 */
    private int f26488H0;

    /* renamed from: H1 */
    private int f26489H1;

    /* renamed from: H2 */
    private int f26490H2;

    /* renamed from: H3 */
    private int f26491H3;

    /* renamed from: H4 */
    private int f26492H4;

    /* renamed from: H5 */
    private int f26493H5;

    /* renamed from: H6 */
    private int f26494H6;

    /* renamed from: H7 */
    private int f26495H7;

    /* renamed from: H8 */
    private int f26496H8;

    /* renamed from: H9 */
    private int f26497H9;

    /* renamed from: X */
    private int[] f26498X;
    private int xOff;

    public RIPEMD320Digest() {
        this(CryptoServicePurpose.ANY);
    }

    public RIPEMD320Digest(CryptoServicePurpose cryptoServicePurpose) {
        super(cryptoServicePurpose);
        this.f26498X = new int[16];
        CryptoServicesRegistrar.checkConstraints(Utils.getDefaultProperties(this, 128, cryptoServicePurpose));
        reset();
    }

    public RIPEMD320Digest(RIPEMD320Digest rIPEMD320Digest) {
        super(rIPEMD320Digest.purpose);
        this.f26498X = new int[16];
        CryptoServicesRegistrar.checkConstraints(Utils.getDefaultProperties(this, 128, this.purpose));
        doCopy(rIPEMD320Digest);
    }

    /* renamed from: RL */
    private int m320RL(int i, int i2) {
        return (i >>> (32 - i2)) | (i << i2);
    }

    private void doCopy(RIPEMD320Digest rIPEMD320Digest) {
        super.copyIn(rIPEMD320Digest);
        this.f26488H0 = rIPEMD320Digest.f26488H0;
        this.f26489H1 = rIPEMD320Digest.f26489H1;
        this.f26490H2 = rIPEMD320Digest.f26490H2;
        this.f26491H3 = rIPEMD320Digest.f26491H3;
        this.f26492H4 = rIPEMD320Digest.f26492H4;
        this.f26493H5 = rIPEMD320Digest.f26493H5;
        this.f26494H6 = rIPEMD320Digest.f26494H6;
        this.f26495H7 = rIPEMD320Digest.f26495H7;
        this.f26496H8 = rIPEMD320Digest.f26496H8;
        this.f26497H9 = rIPEMD320Digest.f26497H9;
        int[] iArr = rIPEMD320Digest.f26498X;
        System.arraycopy(iArr, 0, this.f26498X, 0, iArr.length);
        this.xOff = rIPEMD320Digest.xOff;
    }

    /* renamed from: f1 */
    private int m319f1(int i, int i2, int i3) {
        return (i ^ i2) ^ i3;
    }

    /* renamed from: f2 */
    private int m318f2(int i, int i2, int i3) {
        return ((~i) & i3) | (i2 & i);
    }

    /* renamed from: f3 */
    private int m317f3(int i, int i2, int i3) {
        return (i | (~i2)) ^ i3;
    }

    /* renamed from: f4 */
    private int m316f4(int i, int i2, int i3) {
        return (i & i3) | (i2 & (~i3));
    }

    /* renamed from: f5 */
    private int m315f5(int i, int i2, int i3) {
        return i ^ (i2 | (~i3));
    }

    @Override // org.bouncycastle.util.Memoable
    public Memoable copy() {
        return new RIPEMD320Digest(this);
    }

    @Override // org.bouncycastle.crypto.digests.GeneralDigest
    protected CryptoServiceProperties cryptoServiceProperties() {
        return Utils.getDefaultProperties(this, this.purpose);
    }

    @Override // org.bouncycastle.crypto.Digest
    public int doFinal(byte[] bArr, int i) {
        finish();
        Pack.intToLittleEndian(this.f26488H0, bArr, i);
        Pack.intToLittleEndian(this.f26489H1, bArr, i + 4);
        Pack.intToLittleEndian(this.f26490H2, bArr, i + 8);
        Pack.intToLittleEndian(this.f26491H3, bArr, i + 12);
        Pack.intToLittleEndian(this.f26492H4, bArr, i + 16);
        Pack.intToLittleEndian(this.f26493H5, bArr, i + 20);
        Pack.intToLittleEndian(this.f26494H6, bArr, i + 24);
        Pack.intToLittleEndian(this.f26495H7, bArr, i + 28);
        Pack.intToLittleEndian(this.f26496H8, bArr, i + 32);
        Pack.intToLittleEndian(this.f26497H9, bArr, i + 36);
        reset();
        return 40;
    }

    @Override // org.bouncycastle.crypto.Digest
    public String getAlgorithmName() {
        return "RIPEMD320";
    }

    @Override // org.bouncycastle.crypto.Digest
    public int getDigestSize() {
        return 40;
    }

    @Override // org.bouncycastle.crypto.digests.GeneralDigest
    protected void processBlock() {
        int i = this.f26488H0;
        int i2 = this.f26489H1;
        int i3 = this.f26490H2;
        int i4 = this.f26491H3;
        int i5 = this.f26492H4;
        int i6 = this.f26493H5;
        int i7 = this.f26494H6;
        int i8 = this.f26495H7;
        int i9 = this.f26496H8;
        int i10 = this.f26497H9;
        int m320RL = m320RL(i + m319f1(i2, i3, i4) + this.f26498X[0], 11) + i5;
        int m320RL2 = m320RL(i3, 10);
        int m320RL3 = m320RL(i5 + m319f1(m320RL, i2, m320RL2) + this.f26498X[1], 14) + i4;
        int m320RL4 = m320RL(i2, 10);
        int m320RL5 = m320RL(i4 + m319f1(m320RL3, m320RL, m320RL4) + this.f26498X[2], 15) + m320RL2;
        int m320RL6 = m320RL(m320RL, 10);
        int m320RL7 = m320RL(m320RL2 + m319f1(m320RL5, m320RL3, m320RL6) + this.f26498X[3], 12) + m320RL4;
        int m320RL8 = m320RL(m320RL3, 10);
        int m320RL9 = m320RL(m320RL4 + m319f1(m320RL7, m320RL5, m320RL8) + this.f26498X[4], 5) + m320RL6;
        int m320RL10 = m320RL(m320RL5, 10);
        int m320RL11 = m320RL(m320RL6 + m319f1(m320RL9, m320RL7, m320RL10) + this.f26498X[5], 8) + m320RL8;
        int m320RL12 = m320RL(m320RL7, 10);
        int m320RL13 = m320RL(m320RL8 + m319f1(m320RL11, m320RL9, m320RL12) + this.f26498X[6], 7) + m320RL10;
        int m320RL14 = m320RL(m320RL9, 10);
        int m320RL15 = m320RL(m320RL10 + m319f1(m320RL13, m320RL11, m320RL14) + this.f26498X[7], 9) + m320RL12;
        int m320RL16 = m320RL(m320RL11, 10);
        int m320RL17 = m320RL(m320RL12 + m319f1(m320RL15, m320RL13, m320RL16) + this.f26498X[8], 11) + m320RL14;
        int m320RL18 = m320RL(m320RL13, 10);
        int m320RL19 = m320RL(m320RL14 + m319f1(m320RL17, m320RL15, m320RL18) + this.f26498X[9], 13) + m320RL16;
        int m320RL20 = m320RL(m320RL15, 10);
        int m320RL21 = m320RL(m320RL16 + m319f1(m320RL19, m320RL17, m320RL20) + this.f26498X[10], 14) + m320RL18;
        int m320RL22 = m320RL(m320RL17, 10);
        int m320RL23 = m320RL(m320RL18 + m319f1(m320RL21, m320RL19, m320RL22) + this.f26498X[11], 15) + m320RL20;
        int m320RL24 = m320RL(m320RL19, 10);
        int m320RL25 = m320RL(m320RL20 + m319f1(m320RL23, m320RL21, m320RL24) + this.f26498X[12], 6) + m320RL22;
        int m320RL26 = m320RL(m320RL21, 10);
        int m320RL27 = m320RL(m320RL22 + m319f1(m320RL25, m320RL23, m320RL26) + this.f26498X[13], 7) + m320RL24;
        int m320RL28 = m320RL(m320RL23, 10);
        int m320RL29 = m320RL(m320RL24 + m319f1(m320RL27, m320RL25, m320RL28) + this.f26498X[14], 9) + m320RL26;
        int m320RL30 = m320RL(m320RL25, 10);
        int m320RL31 = m320RL(m320RL26 + m319f1(m320RL29, m320RL27, m320RL30) + this.f26498X[15], 8) + m320RL28;
        int m320RL32 = m320RL(m320RL27, 10);
        int m320RL33 = m320RL(i6 + m315f5(i7, i8, i9) + this.f26498X[5] + 1352829926, 8) + i10;
        int m320RL34 = m320RL(i8, 10);
        int m320RL35 = m320RL(i10 + m315f5(m320RL33, i7, m320RL34) + this.f26498X[14] + 1352829926, 9) + i9;
        int m320RL36 = m320RL(i7, 10);
        int m320RL37 = m320RL(i9 + m315f5(m320RL35, m320RL33, m320RL36) + this.f26498X[7] + 1352829926, 9) + m320RL34;
        int m320RL38 = m320RL(m320RL33, 10);
        int m320RL39 = m320RL(m320RL34 + m315f5(m320RL37, m320RL35, m320RL38) + this.f26498X[0] + 1352829926, 11) + m320RL36;
        int m320RL40 = m320RL(m320RL35, 10);
        int m320RL41 = m320RL(m320RL36 + m315f5(m320RL39, m320RL37, m320RL40) + this.f26498X[9] + 1352829926, 13) + m320RL38;
        int m320RL42 = m320RL(m320RL37, 10);
        int m320RL43 = m320RL(m320RL38 + m315f5(m320RL41, m320RL39, m320RL42) + this.f26498X[2] + 1352829926, 15) + m320RL40;
        int m320RL44 = m320RL(m320RL39, 10);
        int m320RL45 = m320RL(m320RL40 + m315f5(m320RL43, m320RL41, m320RL44) + this.f26498X[11] + 1352829926, 15) + m320RL42;
        int m320RL46 = m320RL(m320RL41, 10);
        int m320RL47 = m320RL(m320RL42 + m315f5(m320RL45, m320RL43, m320RL46) + this.f26498X[4] + 1352829926, 5) + m320RL44;
        int m320RL48 = m320RL(m320RL43, 10);
        int m320RL49 = m320RL(m320RL44 + m315f5(m320RL47, m320RL45, m320RL48) + this.f26498X[13] + 1352829926, 7) + m320RL46;
        int m320RL50 = m320RL(m320RL45, 10);
        int m320RL51 = m320RL(m320RL46 + m315f5(m320RL49, m320RL47, m320RL50) + this.f26498X[6] + 1352829926, 7) + m320RL48;
        int m320RL52 = m320RL(m320RL47, 10);
        int m320RL53 = m320RL(m320RL48 + m315f5(m320RL51, m320RL49, m320RL52) + this.f26498X[15] + 1352829926, 8) + m320RL50;
        int m320RL54 = m320RL(m320RL49, 10);
        int m320RL55 = m320RL(m320RL50 + m315f5(m320RL53, m320RL51, m320RL54) + this.f26498X[8] + 1352829926, 11) + m320RL52;
        int m320RL56 = m320RL(m320RL51, 10);
        int m320RL57 = m320RL(m320RL52 + m315f5(m320RL55, m320RL53, m320RL56) + this.f26498X[1] + 1352829926, 14) + m320RL54;
        int m320RL58 = m320RL(m320RL53, 10);
        int m320RL59 = m320RL(m320RL54 + m315f5(m320RL57, m320RL55, m320RL58) + this.f26498X[10] + 1352829926, 14) + m320RL56;
        int m320RL60 = m320RL(m320RL55, 10);
        int m320RL61 = m320RL(m320RL56 + m315f5(m320RL59, m320RL57, m320RL60) + this.f26498X[3] + 1352829926, 12) + m320RL58;
        int m320RL62 = m320RL(m320RL57, 10);
        int m320RL63 = m320RL(m320RL58 + m315f5(m320RL61, m320RL59, m320RL62) + this.f26498X[12] + 1352829926, 6) + m320RL60;
        int m320RL64 = m320RL(m320RL59, 10);
        int m320RL65 = m320RL(m320RL28 + m318f2(m320RL63, m320RL29, m320RL32) + this.f26498X[7] + C0512f.f1670m, 7) + m320RL30;
        int m320RL66 = m320RL(m320RL29, 10);
        int m320RL67 = m320RL(m320RL30 + m318f2(m320RL65, m320RL63, m320RL66) + this.f26498X[4] + C0512f.f1670m, 6) + m320RL32;
        int m320RL68 = m320RL(m320RL63, 10);
        int m320RL69 = m320RL(m320RL32 + m318f2(m320RL67, m320RL65, m320RL68) + this.f26498X[13] + C0512f.f1670m, 8) + m320RL66;
        int m320RL70 = m320RL(m320RL65, 10);
        int m320RL71 = m320RL(m320RL66 + m318f2(m320RL69, m320RL67, m320RL70) + this.f26498X[1] + C0512f.f1670m, 13) + m320RL68;
        int m320RL72 = m320RL(m320RL67, 10);
        int m320RL73 = m320RL(m320RL68 + m318f2(m320RL71, m320RL69, m320RL72) + this.f26498X[10] + C0512f.f1670m, 11) + m320RL70;
        int m320RL74 = m320RL(m320RL69, 10);
        int m320RL75 = m320RL(m320RL70 + m318f2(m320RL73, m320RL71, m320RL74) + this.f26498X[6] + C0512f.f1670m, 9) + m320RL72;
        int m320RL76 = m320RL(m320RL71, 10);
        int m320RL77 = m320RL(m320RL72 + m318f2(m320RL75, m320RL73, m320RL76) + this.f26498X[15] + C0512f.f1670m, 7) + m320RL74;
        int m320RL78 = m320RL(m320RL73, 10);
        int m320RL79 = m320RL(m320RL74 + m318f2(m320RL77, m320RL75, m320RL78) + this.f26498X[3] + C0512f.f1670m, 15) + m320RL76;
        int m320RL80 = m320RL(m320RL75, 10);
        int m320RL81 = m320RL(m320RL76 + m318f2(m320RL79, m320RL77, m320RL80) + this.f26498X[12] + C0512f.f1670m, 7) + m320RL78;
        int m320RL82 = m320RL(m320RL77, 10);
        int m320RL83 = m320RL(m320RL78 + m318f2(m320RL81, m320RL79, m320RL82) + this.f26498X[0] + C0512f.f1670m, 12) + m320RL80;
        int m320RL84 = m320RL(m320RL79, 10);
        int m320RL85 = m320RL(m320RL80 + m318f2(m320RL83, m320RL81, m320RL84) + this.f26498X[9] + C0512f.f1670m, 15) + m320RL82;
        int m320RL86 = m320RL(m320RL81, 10);
        int m320RL87 = m320RL(m320RL82 + m318f2(m320RL85, m320RL83, m320RL86) + this.f26498X[5] + C0512f.f1670m, 9) + m320RL84;
        int m320RL88 = m320RL(m320RL83, 10);
        int m320RL89 = m320RL(m320RL84 + m318f2(m320RL87, m320RL85, m320RL88) + this.f26498X[2] + C0512f.f1670m, 11) + m320RL86;
        int m320RL90 = m320RL(m320RL85, 10);
        int m320RL91 = m320RL(m320RL86 + m318f2(m320RL89, m320RL87, m320RL90) + this.f26498X[14] + C0512f.f1670m, 7) + m320RL88;
        int m320RL92 = m320RL(m320RL87, 10);
        int m320RL93 = m320RL(m320RL88 + m318f2(m320RL91, m320RL89, m320RL92) + this.f26498X[11] + C0512f.f1670m, 13) + m320RL90;
        int m320RL94 = m320RL(m320RL89, 10);
        int m320RL95 = m320RL(m320RL90 + m318f2(m320RL93, m320RL91, m320RL94) + this.f26498X[8] + C0512f.f1670m, 12) + m320RL92;
        int m320RL96 = m320RL(m320RL91, 10);
        int m320RL97 = m320RL(m320RL60 + m316f4(m320RL31, m320RL61, m320RL64) + this.f26498X[6] + 1548603684, 9) + m320RL62;
        int m320RL98 = m320RL(m320RL61, 10);
        int m320RL99 = m320RL(m320RL62 + m316f4(m320RL97, m320RL31, m320RL98) + this.f26498X[11] + 1548603684, 13) + m320RL64;
        int m320RL100 = m320RL(m320RL31, 10);
        int m320RL101 = m320RL(m320RL64 + m316f4(m320RL99, m320RL97, m320RL100) + this.f26498X[3] + 1548603684, 15) + m320RL98;
        int m320RL102 = m320RL(m320RL97, 10);
        int m320RL103 = m320RL(m320RL98 + m316f4(m320RL101, m320RL99, m320RL102) + this.f26498X[7] + 1548603684, 7) + m320RL100;
        int m320RL104 = m320RL(m320RL99, 10);
        int m320RL105 = m320RL(m320RL100 + m316f4(m320RL103, m320RL101, m320RL104) + this.f26498X[0] + 1548603684, 12) + m320RL102;
        int m320RL106 = m320RL(m320RL101, 10);
        int m320RL107 = m320RL(m320RL102 + m316f4(m320RL105, m320RL103, m320RL106) + this.f26498X[13] + 1548603684, 8) + m320RL104;
        int m320RL108 = m320RL(m320RL103, 10);
        int m320RL109 = m320RL(m320RL104 + m316f4(m320RL107, m320RL105, m320RL108) + this.f26498X[5] + 1548603684, 9) + m320RL106;
        int m320RL110 = m320RL(m320RL105, 10);
        int m320RL111 = m320RL(m320RL106 + m316f4(m320RL109, m320RL107, m320RL110) + this.f26498X[10] + 1548603684, 11) + m320RL108;
        int m320RL112 = m320RL(m320RL107, 10);
        int m320RL113 = m320RL(m320RL108 + m316f4(m320RL111, m320RL109, m320RL112) + this.f26498X[14] + 1548603684, 7) + m320RL110;
        int m320RL114 = m320RL(m320RL109, 10);
        int m320RL115 = m320RL(m320RL110 + m316f4(m320RL113, m320RL111, m320RL114) + this.f26498X[15] + 1548603684, 7) + m320RL112;
        int m320RL116 = m320RL(m320RL111, 10);
        int m320RL117 = m320RL(m320RL112 + m316f4(m320RL115, m320RL113, m320RL116) + this.f26498X[8] + 1548603684, 12) + m320RL114;
        int m320RL118 = m320RL(m320RL113, 10);
        int m320RL119 = m320RL(m320RL114 + m316f4(m320RL117, m320RL115, m320RL118) + this.f26498X[12] + 1548603684, 7) + m320RL116;
        int m320RL120 = m320RL(m320RL115, 10);
        int m320RL121 = m320RL(m320RL116 + m316f4(m320RL119, m320RL117, m320RL120) + this.f26498X[4] + 1548603684, 6) + m320RL118;
        int m320RL122 = m320RL(m320RL117, 10);
        int m320RL123 = m320RL(m320RL118 + m316f4(m320RL121, m320RL119, m320RL122) + this.f26498X[9] + 1548603684, 15) + m320RL120;
        int m320RL124 = m320RL(m320RL119, 10);
        int m320RL125 = m320RL(m320RL120 + m316f4(m320RL123, m320RL121, m320RL124) + this.f26498X[1] + 1548603684, 13) + m320RL122;
        int m320RL126 = m320RL(m320RL121, 10);
        int m320RL127 = m320RL(m320RL122 + m316f4(m320RL125, m320RL123, m320RL126) + this.f26498X[2] + 1548603684, 11) + m320RL124;
        int m320RL128 = m320RL(m320RL123, 10);
        int m320RL129 = m320RL(m320RL92 + m317f3(m320RL95, m320RL93, m320RL128) + this.f26498X[3] + C0512f.f1671n, 11) + m320RL94;
        int m320RL130 = m320RL(m320RL93, 10);
        int m320RL131 = m320RL(m320RL94 + m317f3(m320RL129, m320RL95, m320RL130) + this.f26498X[10] + C0512f.f1671n, 13) + m320RL128;
        int m320RL132 = m320RL(m320RL95, 10);
        int m320RL133 = m320RL(m320RL128 + m317f3(m320RL131, m320RL129, m320RL132) + this.f26498X[14] + C0512f.f1671n, 6) + m320RL130;
        int m320RL134 = m320RL(m320RL129, 10);
        int m320RL135 = m320RL(m320RL130 + m317f3(m320RL133, m320RL131, m320RL134) + this.f26498X[4] + C0512f.f1671n, 7) + m320RL132;
        int m320RL136 = m320RL(m320RL131, 10);
        int m320RL137 = m320RL(m320RL132 + m317f3(m320RL135, m320RL133, m320RL136) + this.f26498X[9] + C0512f.f1671n, 14) + m320RL134;
        int m320RL138 = m320RL(m320RL133, 10);
        int m320RL139 = m320RL(m320RL134 + m317f3(m320RL137, m320RL135, m320RL138) + this.f26498X[15] + C0512f.f1671n, 9) + m320RL136;
        int m320RL140 = m320RL(m320RL135, 10);
        int m320RL141 = m320RL(m320RL136 + m317f3(m320RL139, m320RL137, m320RL140) + this.f26498X[8] + C0512f.f1671n, 13) + m320RL138;
        int m320RL142 = m320RL(m320RL137, 10);
        int m320RL143 = m320RL(m320RL138 + m317f3(m320RL141, m320RL139, m320RL142) + this.f26498X[1] + C0512f.f1671n, 15) + m320RL140;
        int m320RL144 = m320RL(m320RL139, 10);
        int m320RL145 = m320RL(m320RL140 + m317f3(m320RL143, m320RL141, m320RL144) + this.f26498X[2] + C0512f.f1671n, 14) + m320RL142;
        int m320RL146 = m320RL(m320RL141, 10);
        int m320RL147 = m320RL(m320RL142 + m317f3(m320RL145, m320RL143, m320RL146) + this.f26498X[7] + C0512f.f1671n, 8) + m320RL144;
        int m320RL148 = m320RL(m320RL143, 10);
        int m320RL149 = m320RL(m320RL144 + m317f3(m320RL147, m320RL145, m320RL148) + this.f26498X[0] + C0512f.f1671n, 13) + m320RL146;
        int m320RL150 = m320RL(m320RL145, 10);
        int m320RL151 = m320RL(m320RL146 + m317f3(m320RL149, m320RL147, m320RL150) + this.f26498X[6] + C0512f.f1671n, 6) + m320RL148;
        int m320RL152 = m320RL(m320RL147, 10);
        int m320RL153 = m320RL(m320RL148 + m317f3(m320RL151, m320RL149, m320RL152) + this.f26498X[13] + C0512f.f1671n, 5) + m320RL150;
        int m320RL154 = m320RL(m320RL149, 10);
        int m320RL155 = m320RL(m320RL150 + m317f3(m320RL153, m320RL151, m320RL154) + this.f26498X[11] + C0512f.f1671n, 12) + m320RL152;
        int m320RL156 = m320RL(m320RL151, 10);
        int m320RL157 = m320RL(m320RL152 + m317f3(m320RL155, m320RL153, m320RL156) + this.f26498X[5] + C0512f.f1671n, 7) + m320RL154;
        int m320RL158 = m320RL(m320RL153, 10);
        int m320RL159 = m320RL(m320RL154 + m317f3(m320RL157, m320RL155, m320RL158) + this.f26498X[12] + C0512f.f1671n, 5) + m320RL156;
        int m320RL160 = m320RL(m320RL155, 10);
        int m320RL161 = m320RL(m320RL124 + m317f3(m320RL127, m320RL125, m320RL96) + this.f26498X[15] + 1836072691, 9) + m320RL126;
        int m320RL162 = m320RL(m320RL125, 10);
        int m320RL163 = m320RL(m320RL126 + m317f3(m320RL161, m320RL127, m320RL162) + this.f26498X[5] + 1836072691, 7) + m320RL96;
        int m320RL164 = m320RL(m320RL127, 10);
        int m320RL165 = m320RL(m320RL96 + m317f3(m320RL163, m320RL161, m320RL164) + this.f26498X[1] + 1836072691, 15) + m320RL162;
        int m320RL166 = m320RL(m320RL161, 10);
        int m320RL167 = m320RL(m320RL162 + m317f3(m320RL165, m320RL163, m320RL166) + this.f26498X[3] + 1836072691, 11) + m320RL164;
        int m320RL168 = m320RL(m320RL163, 10);
        int m320RL169 = m320RL(m320RL164 + m317f3(m320RL167, m320RL165, m320RL168) + this.f26498X[7] + 1836072691, 8) + m320RL166;
        int m320RL170 = m320RL(m320RL165, 10);
        int m320RL171 = m320RL(m320RL166 + m317f3(m320RL169, m320RL167, m320RL170) + this.f26498X[14] + 1836072691, 6) + m320RL168;
        int m320RL172 = m320RL(m320RL167, 10);
        int m320RL173 = m320RL(m320RL168 + m317f3(m320RL171, m320RL169, m320RL172) + this.f26498X[6] + 1836072691, 6) + m320RL170;
        int m320RL174 = m320RL(m320RL169, 10);
        int m320RL175 = m320RL(m320RL170 + m317f3(m320RL173, m320RL171, m320RL174) + this.f26498X[9] + 1836072691, 14) + m320RL172;
        int m320RL176 = m320RL(m320RL171, 10);
        int m320RL177 = m320RL(m320RL172 + m317f3(m320RL175, m320RL173, m320RL176) + this.f26498X[11] + 1836072691, 12) + m320RL174;
        int m320RL178 = m320RL(m320RL173, 10);
        int m320RL179 = m320RL(m320RL174 + m317f3(m320RL177, m320RL175, m320RL178) + this.f26498X[8] + 1836072691, 13) + m320RL176;
        int m320RL180 = m320RL(m320RL175, 10);
        int m320RL181 = m320RL(m320RL176 + m317f3(m320RL179, m320RL177, m320RL180) + this.f26498X[12] + 1836072691, 5) + m320RL178;
        int m320RL182 = m320RL(m320RL177, 10);
        int m320RL183 = m320RL(m320RL178 + m317f3(m320RL181, m320RL179, m320RL182) + this.f26498X[2] + 1836072691, 14) + m320RL180;
        int m320RL184 = m320RL(m320RL179, 10);
        int m320RL185 = m320RL(m320RL180 + m317f3(m320RL183, m320RL181, m320RL184) + this.f26498X[10] + 1836072691, 13) + m320RL182;
        int m320RL186 = m320RL(m320RL181, 10);
        int m320RL187 = m320RL(m320RL182 + m317f3(m320RL185, m320RL183, m320RL186) + this.f26498X[0] + 1836072691, 13) + m320RL184;
        int m320RL188 = m320RL(m320RL183, 10);
        int m320RL189 = m320RL(m320RL184 + m317f3(m320RL187, m320RL185, m320RL188) + this.f26498X[4] + 1836072691, 7) + m320RL186;
        int m320RL190 = m320RL(m320RL185, 10);
        int m320RL191 = m320RL(m320RL186 + m317f3(m320RL189, m320RL187, m320RL190) + this.f26498X[13] + 1836072691, 5) + m320RL188;
        int m320RL192 = m320RL(m320RL187, 10);
        int m320RL193 = m320RL(m320RL188 + m316f4(m320RL159, m320RL157, m320RL160) + this.f26498X[1] + C0512f.f1672o, 11) + m320RL158;
        int m320RL194 = m320RL(m320RL157, 10);
        int m320RL195 = m320RL(m320RL158 + m316f4(m320RL193, m320RL159, m320RL194) + this.f26498X[9] + C0512f.f1672o, 12) + m320RL160;
        int m320RL196 = m320RL(m320RL159, 10);
        int m320RL197 = m320RL(m320RL160 + m316f4(m320RL195, m320RL193, m320RL196) + this.f26498X[11] + C0512f.f1672o, 14) + m320RL194;
        int m320RL198 = m320RL(m320RL193, 10);
        int m320RL199 = m320RL(m320RL194 + m316f4(m320RL197, m320RL195, m320RL198) + this.f26498X[10] + C0512f.f1672o, 15) + m320RL196;
        int m320RL200 = m320RL(m320RL195, 10);
        int m320RL201 = m320RL(m320RL196 + m316f4(m320RL199, m320RL197, m320RL200) + this.f26498X[0] + C0512f.f1672o, 14) + m320RL198;
        int m320RL202 = m320RL(m320RL197, 10);
        int m320RL203 = m320RL(m320RL198 + m316f4(m320RL201, m320RL199, m320RL202) + this.f26498X[8] + C0512f.f1672o, 15) + m320RL200;
        int m320RL204 = m320RL(m320RL199, 10);
        int m320RL205 = m320RL(m320RL200 + m316f4(m320RL203, m320RL201, m320RL204) + this.f26498X[12] + C0512f.f1672o, 9) + m320RL202;
        int m320RL206 = m320RL(m320RL201, 10);
        int m320RL207 = m320RL(m320RL202 + m316f4(m320RL205, m320RL203, m320RL206) + this.f26498X[4] + C0512f.f1672o, 8) + m320RL204;
        int m320RL208 = m320RL(m320RL203, 10);
        int m320RL209 = m320RL(m320RL204 + m316f4(m320RL207, m320RL205, m320RL208) + this.f26498X[13] + C0512f.f1672o, 9) + m320RL206;
        int m320RL210 = m320RL(m320RL205, 10);
        int m320RL211 = m320RL(m320RL206 + m316f4(m320RL209, m320RL207, m320RL210) + this.f26498X[3] + C0512f.f1672o, 14) + m320RL208;
        int m320RL212 = m320RL(m320RL207, 10);
        int m320RL213 = m320RL(m320RL208 + m316f4(m320RL211, m320RL209, m320RL212) + this.f26498X[7] + C0512f.f1672o, 5) + m320RL210;
        int m320RL214 = m320RL(m320RL209, 10);
        int m320RL215 = m320RL(m320RL210 + m316f4(m320RL213, m320RL211, m320RL214) + this.f26498X[15] + C0512f.f1672o, 6) + m320RL212;
        int m320RL216 = m320RL(m320RL211, 10);
        int m320RL217 = m320RL(m320RL212 + m316f4(m320RL215, m320RL213, m320RL216) + this.f26498X[14] + C0512f.f1672o, 8) + m320RL214;
        int m320RL218 = m320RL(m320RL213, 10);
        int m320RL219 = m320RL(m320RL214 + m316f4(m320RL217, m320RL215, m320RL218) + this.f26498X[5] + C0512f.f1672o, 6) + m320RL216;
        int m320RL220 = m320RL(m320RL215, 10);
        int m320RL221 = m320RL(m320RL216 + m316f4(m320RL219, m320RL217, m320RL220) + this.f26498X[6] + C0512f.f1672o, 5) + m320RL218;
        int m320RL222 = m320RL(m320RL217, 10);
        int m320RL223 = m320RL(m320RL218 + m316f4(m320RL221, m320RL219, m320RL222) + this.f26498X[2] + C0512f.f1672o, 12) + m320RL220;
        int m320RL224 = m320RL(m320RL219, 10);
        int m320RL225 = m320RL(m320RL156 + m318f2(m320RL191, m320RL189, m320RL192) + this.f26498X[8] + 2053994217, 15) + m320RL190;
        int m320RL226 = m320RL(m320RL189, 10);
        int m320RL227 = m320RL(m320RL190 + m318f2(m320RL225, m320RL191, m320RL226) + this.f26498X[6] + 2053994217, 5) + m320RL192;
        int m320RL228 = m320RL(m320RL191, 10);
        int m320RL229 = m320RL(m320RL192 + m318f2(m320RL227, m320RL225, m320RL228) + this.f26498X[4] + 2053994217, 8) + m320RL226;
        int m320RL230 = m320RL(m320RL225, 10);
        int m320RL231 = m320RL(m320RL226 + m318f2(m320RL229, m320RL227, m320RL230) + this.f26498X[1] + 2053994217, 11) + m320RL228;
        int m320RL232 = m320RL(m320RL227, 10);
        int m320RL233 = m320RL(m320RL228 + m318f2(m320RL231, m320RL229, m320RL232) + this.f26498X[3] + 2053994217, 14) + m320RL230;
        int m320RL234 = m320RL(m320RL229, 10);
        int m320RL235 = m320RL(m320RL230 + m318f2(m320RL233, m320RL231, m320RL234) + this.f26498X[11] + 2053994217, 14) + m320RL232;
        int m320RL236 = m320RL(m320RL231, 10);
        int m320RL237 = m320RL(m320RL232 + m318f2(m320RL235, m320RL233, m320RL236) + this.f26498X[15] + 2053994217, 6) + m320RL234;
        int m320RL238 = m320RL(m320RL233, 10);
        int m320RL239 = m320RL(m320RL234 + m318f2(m320RL237, m320RL235, m320RL238) + this.f26498X[0] + 2053994217, 14) + m320RL236;
        int m320RL240 = m320RL(m320RL235, 10);
        int m320RL241 = m320RL(m320RL236 + m318f2(m320RL239, m320RL237, m320RL240) + this.f26498X[5] + 2053994217, 6) + m320RL238;
        int m320RL242 = m320RL(m320RL237, 10);
        int m320RL243 = m320RL(m320RL238 + m318f2(m320RL241, m320RL239, m320RL242) + this.f26498X[12] + 2053994217, 9) + m320RL240;
        int m320RL244 = m320RL(m320RL239, 10);
        int m320RL245 = m320RL(m320RL240 + m318f2(m320RL243, m320RL241, m320RL244) + this.f26498X[2] + 2053994217, 12) + m320RL242;
        int m320RL246 = m320RL(m320RL241, 10);
        int m320RL247 = m320RL(m320RL242 + m318f2(m320RL245, m320RL243, m320RL246) + this.f26498X[13] + 2053994217, 9) + m320RL244;
        int m320RL248 = m320RL(m320RL243, 10);
        int m320RL249 = m320RL(m320RL244 + m318f2(m320RL247, m320RL245, m320RL248) + this.f26498X[9] + 2053994217, 12) + m320RL246;
        int m320RL250 = m320RL(m320RL245, 10);
        int m320RL251 = m320RL(m320RL246 + m318f2(m320RL249, m320RL247, m320RL250) + this.f26498X[7] + 2053994217, 5) + m320RL248;
        int m320RL252 = m320RL(m320RL247, 10);
        int m320RL253 = m320RL(m320RL248 + m318f2(m320RL251, m320RL249, m320RL252) + this.f26498X[10] + 2053994217, 15) + m320RL250;
        int m320RL254 = m320RL(m320RL249, 10);
        int m320RL255 = m320RL(m320RL250 + m318f2(m320RL253, m320RL251, m320RL254) + this.f26498X[14] + 2053994217, 8) + m320RL252;
        int m320RL256 = m320RL(m320RL251, 10);
        int m320RL257 = m320RL(((m320RL220 + m315f5(m320RL223, m320RL253, m320RL224)) + this.f26498X[4]) - 1454113458, 9) + m320RL222;
        int m320RL258 = m320RL(m320RL253, 10);
        int m320RL259 = m320RL(((m320RL222 + m315f5(m320RL257, m320RL223, m320RL258)) + this.f26498X[0]) - 1454113458, 15) + m320RL224;
        int m320RL260 = m320RL(m320RL223, 10);
        int m320RL261 = m320RL(((m320RL224 + m315f5(m320RL259, m320RL257, m320RL260)) + this.f26498X[5]) - 1454113458, 5) + m320RL258;
        int m320RL262 = m320RL(m320RL257, 10);
        int m320RL263 = m320RL(((m320RL258 + m315f5(m320RL261, m320RL259, m320RL262)) + this.f26498X[9]) - 1454113458, 11) + m320RL260;
        int m320RL264 = m320RL(m320RL259, 10);
        int m320RL265 = m320RL(((m320RL260 + m315f5(m320RL263, m320RL261, m320RL264)) + this.f26498X[7]) - 1454113458, 6) + m320RL262;
        int m320RL266 = m320RL(m320RL261, 10);
        int m320RL267 = m320RL(((m320RL262 + m315f5(m320RL265, m320RL263, m320RL266)) + this.f26498X[12]) - 1454113458, 8) + m320RL264;
        int m320RL268 = m320RL(m320RL263, 10);
        int m320RL269 = m320RL(((m320RL264 + m315f5(m320RL267, m320RL265, m320RL268)) + this.f26498X[2]) - 1454113458, 13) + m320RL266;
        int m320RL270 = m320RL(m320RL265, 10);
        int m320RL271 = m320RL(((m320RL266 + m315f5(m320RL269, m320RL267, m320RL270)) + this.f26498X[10]) - 1454113458, 12) + m320RL268;
        int m320RL272 = m320RL(m320RL267, 10);
        int m320RL273 = m320RL(((m320RL268 + m315f5(m320RL271, m320RL269, m320RL272)) + this.f26498X[14]) - 1454113458, 5) + m320RL270;
        int m320RL274 = m320RL(m320RL269, 10);
        int m320RL275 = m320RL(((m320RL270 + m315f5(m320RL273, m320RL271, m320RL274)) + this.f26498X[1]) - 1454113458, 12) + m320RL272;
        int m320RL276 = m320RL(m320RL271, 10);
        int m320RL277 = m320RL(((m320RL272 + m315f5(m320RL275, m320RL273, m320RL276)) + this.f26498X[3]) - 1454113458, 13) + m320RL274;
        int m320RL278 = m320RL(m320RL273, 10);
        int m320RL279 = m320RL(((m320RL274 + m315f5(m320RL277, m320RL275, m320RL278)) + this.f26498X[8]) - 1454113458, 14) + m320RL276;
        int m320RL280 = m320RL(m320RL275, 10);
        int m320RL281 = m320RL(((m320RL276 + m315f5(m320RL279, m320RL277, m320RL280)) + this.f26498X[11]) - 1454113458, 11) + m320RL278;
        int m320RL282 = m320RL(m320RL277, 10);
        int m320RL283 = m320RL(((m320RL278 + m315f5(m320RL281, m320RL279, m320RL282)) + this.f26498X[6]) - 1454113458, 8) + m320RL280;
        int m320RL284 = m320RL(m320RL279, 10);
        int m320RL285 = m320RL(((m320RL280 + m315f5(m320RL283, m320RL281, m320RL284)) + this.f26498X[15]) - 1454113458, 5) + m320RL282;
        int m320RL286 = m320RL(m320RL281, 10);
        int m320RL287 = m320RL(((m320RL282 + m315f5(m320RL285, m320RL283, m320RL286)) + this.f26498X[13]) - 1454113458, 6) + m320RL284;
        int m320RL288 = m320RL(m320RL283, 10);
        int m320RL289 = m320RL(m320RL252 + m319f1(m320RL255, m320RL221, m320RL256) + this.f26498X[12], 8) + m320RL254;
        int m320RL290 = m320RL(m320RL221, 10);
        int m320RL291 = m320RL(m320RL254 + m319f1(m320RL289, m320RL255, m320RL290) + this.f26498X[15], 5) + m320RL256;
        int m320RL292 = m320RL(m320RL255, 10);
        int m320RL293 = m320RL(m320RL256 + m319f1(m320RL291, m320RL289, m320RL292) + this.f26498X[10], 12) + m320RL290;
        int m320RL294 = m320RL(m320RL289, 10);
        int m320RL295 = m320RL(m320RL290 + m319f1(m320RL293, m320RL291, m320RL294) + this.f26498X[4], 9) + m320RL292;
        int m320RL296 = m320RL(m320RL291, 10);
        int m320RL297 = m320RL(m320RL292 + m319f1(m320RL295, m320RL293, m320RL296) + this.f26498X[1], 12) + m320RL294;
        int m320RL298 = m320RL(m320RL293, 10);
        int m320RL299 = m320RL(m320RL294 + m319f1(m320RL297, m320RL295, m320RL298) + this.f26498X[5], 5) + m320RL296;
        int m320RL300 = m320RL(m320RL295, 10);
        int m320RL301 = m320RL(m320RL296 + m319f1(m320RL299, m320RL297, m320RL300) + this.f26498X[8], 14) + m320RL298;
        int m320RL302 = m320RL(m320RL297, 10);
        int m320RL303 = m320RL(m320RL298 + m319f1(m320RL301, m320RL299, m320RL302) + this.f26498X[7], 6) + m320RL300;
        int m320RL304 = m320RL(m320RL299, 10);
        int m320RL305 = m320RL(m320RL300 + m319f1(m320RL303, m320RL301, m320RL304) + this.f26498X[6], 8) + m320RL302;
        int m320RL306 = m320RL(m320RL301, 10);
        int m320RL307 = m320RL(m320RL302 + m319f1(m320RL305, m320RL303, m320RL306) + this.f26498X[2], 13) + m320RL304;
        int m320RL308 = m320RL(m320RL303, 10);
        int m320RL309 = m320RL(m320RL304 + m319f1(m320RL307, m320RL305, m320RL308) + this.f26498X[13], 6) + m320RL306;
        int m320RL310 = m320RL(m320RL305, 10);
        int m320RL311 = m320RL(m320RL306 + m319f1(m320RL309, m320RL307, m320RL310) + this.f26498X[14], 5) + m320RL308;
        int m320RL312 = m320RL(m320RL307, 10);
        int m320RL313 = m320RL(m320RL308 + m319f1(m320RL311, m320RL309, m320RL312) + this.f26498X[0], 15) + m320RL310;
        int m320RL314 = m320RL(m320RL309, 10);
        int m320RL315 = m320RL(m320RL310 + m319f1(m320RL313, m320RL311, m320RL314) + this.f26498X[3], 13) + m320RL312;
        int m320RL316 = m320RL(m320RL311, 10);
        int m320RL317 = m320RL(m320RL312 + m319f1(m320RL315, m320RL313, m320RL316) + this.f26498X[9], 11) + m320RL314;
        int m320RL318 = m320RL(m320RL313, 10);
        int m320RL319 = m320RL(m320RL314 + m319f1(m320RL317, m320RL315, m320RL318) + this.f26498X[11], 11) + m320RL316;
        int m320RL320 = m320RL(m320RL315, 10);
        this.f26488H0 += m320RL284;
        this.f26489H1 += m320RL287;
        this.f26490H2 += m320RL285;
        this.f26491H3 += m320RL288;
        this.f26492H4 += m320RL318;
        this.f26493H5 += m320RL316;
        this.f26494H6 += m320RL319;
        this.f26495H7 += m320RL317;
        this.f26496H8 += m320RL320;
        this.f26497H9 += m320RL286;
        int i11 = 0;
        this.xOff = 0;
        while (true) {
            int[] iArr = this.f26498X;
            if (i11 == iArr.length) {
                return;
            }
            iArr[i11] = 0;
            i11++;
        }
    }

    @Override // org.bouncycastle.crypto.digests.GeneralDigest
    protected void processLength(long j) {
        if (this.xOff > 14) {
            processBlock();
        }
        int[] iArr = this.f26498X;
        iArr[14] = (int) ((-1) & j);
        iArr[15] = (int) (j >>> 32);
    }

    @Override // org.bouncycastle.crypto.digests.GeneralDigest
    protected void processWord(byte[] bArr, int i) {
        int[] iArr = this.f26498X;
        int i2 = this.xOff;
        this.xOff = i2 + 1;
        iArr[i2] = Pack.littleEndianToInt(bArr, i);
        if (this.xOff == 16) {
            processBlock();
        }
    }

    @Override // org.bouncycastle.crypto.digests.GeneralDigest, org.bouncycastle.crypto.Digest
    public void reset() {
        super.reset();
        this.f26488H0 = 1732584193;
        this.f26489H1 = -271733879;
        this.f26490H2 = -1732584194;
        this.f26491H3 = 271733878;
        this.f26492H4 = -1009589776;
        this.f26493H5 = 1985229328;
        this.f26494H6 = -19088744;
        this.f26495H7 = -1985229329;
        this.f26496H8 = 19088743;
        this.f26497H9 = 1009589775;
        this.xOff = 0;
        int i = 0;
        while (true) {
            int[] iArr = this.f26498X;
            if (i == iArr.length) {
                return;
            }
            iArr[i] = 0;
            i++;
        }
    }

    @Override // org.bouncycastle.util.Memoable
    public void reset(Memoable memoable) {
        doCopy((RIPEMD320Digest) memoable);
    }
}
