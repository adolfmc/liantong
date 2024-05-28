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
public class RIPEMD160Digest extends GeneralDigest {
    private static final int DIGEST_LENGTH = 20;

    /* renamed from: H0 */
    private int f26473H0;

    /* renamed from: H1 */
    private int f26474H1;

    /* renamed from: H2 */
    private int f26475H2;

    /* renamed from: H3 */
    private int f26476H3;

    /* renamed from: H4 */
    private int f26477H4;

    /* renamed from: X */
    private int[] f26478X;
    private int xOff;

    public RIPEMD160Digest() {
        this(CryptoServicePurpose.ANY);
    }

    public RIPEMD160Digest(CryptoServicePurpose cryptoServicePurpose) {
        super(cryptoServicePurpose);
        this.f26478X = new int[16];
        CryptoServicesRegistrar.checkConstraints(cryptoServiceProperties());
        reset();
    }

    public RIPEMD160Digest(RIPEMD160Digest rIPEMD160Digest) {
        super(rIPEMD160Digest);
        this.f26478X = new int[16];
        CryptoServicesRegistrar.checkConstraints(cryptoServiceProperties());
        copyIn(rIPEMD160Digest);
    }

    /* renamed from: RL */
    private int m335RL(int i, int i2) {
        return (i >>> (32 - i2)) | (i << i2);
    }

    private void copyIn(RIPEMD160Digest rIPEMD160Digest) {
        super.copyIn((GeneralDigest) rIPEMD160Digest);
        this.f26473H0 = rIPEMD160Digest.f26473H0;
        this.f26474H1 = rIPEMD160Digest.f26474H1;
        this.f26475H2 = rIPEMD160Digest.f26475H2;
        this.f26476H3 = rIPEMD160Digest.f26476H3;
        this.f26477H4 = rIPEMD160Digest.f26477H4;
        int[] iArr = rIPEMD160Digest.f26478X;
        System.arraycopy(iArr, 0, this.f26478X, 0, iArr.length);
        this.xOff = rIPEMD160Digest.xOff;
    }

    /* renamed from: f1 */
    private int m334f1(int i, int i2, int i3) {
        return (i ^ i2) ^ i3;
    }

    /* renamed from: f2 */
    private int m333f2(int i, int i2, int i3) {
        return ((~i) & i3) | (i2 & i);
    }

    /* renamed from: f3 */
    private int m332f3(int i, int i2, int i3) {
        return (i | (~i2)) ^ i3;
    }

    /* renamed from: f4 */
    private int m331f4(int i, int i2, int i3) {
        return (i & i3) | (i2 & (~i3));
    }

    /* renamed from: f5 */
    private int m330f5(int i, int i2, int i3) {
        return i ^ (i2 | (~i3));
    }

    @Override // org.bouncycastle.util.Memoable
    public Memoable copy() {
        return new RIPEMD160Digest(this);
    }

    @Override // org.bouncycastle.crypto.digests.GeneralDigest
    protected CryptoServiceProperties cryptoServiceProperties() {
        return Utils.getDefaultProperties(this, 128, this.purpose);
    }

    @Override // org.bouncycastle.crypto.Digest
    public int doFinal(byte[] bArr, int i) {
        finish();
        Pack.intToLittleEndian(this.f26473H0, bArr, i);
        Pack.intToLittleEndian(this.f26474H1, bArr, i + 4);
        Pack.intToLittleEndian(this.f26475H2, bArr, i + 8);
        Pack.intToLittleEndian(this.f26476H3, bArr, i + 12);
        Pack.intToLittleEndian(this.f26477H4, bArr, i + 16);
        reset();
        return 20;
    }

    @Override // org.bouncycastle.crypto.Digest
    public String getAlgorithmName() {
        return "RIPEMD160";
    }

    @Override // org.bouncycastle.crypto.Digest
    public int getDigestSize() {
        return 20;
    }

    @Override // org.bouncycastle.crypto.digests.GeneralDigest
    protected void processBlock() {
        int i = this.f26473H0;
        int i2 = this.f26474H1;
        int i3 = this.f26475H2;
        int i4 = this.f26476H3;
        int i5 = this.f26477H4;
        int m335RL = m335RL(m334f1(i2, i3, i4) + i + this.f26478X[0], 11) + i5;
        int m335RL2 = m335RL(i3, 10);
        int m335RL3 = m335RL(m334f1(m335RL, i2, m335RL2) + i5 + this.f26478X[1], 14) + i4;
        int m335RL4 = m335RL(i2, 10);
        int m335RL5 = m335RL(m334f1(m335RL3, m335RL, m335RL4) + i4 + this.f26478X[2], 15) + m335RL2;
        int m335RL6 = m335RL(m335RL, 10);
        int m335RL7 = m335RL(m335RL2 + m334f1(m335RL5, m335RL3, m335RL6) + this.f26478X[3], 12) + m335RL4;
        int m335RL8 = m335RL(m335RL3, 10);
        int m335RL9 = m335RL(m335RL4 + m334f1(m335RL7, m335RL5, m335RL8) + this.f26478X[4], 5) + m335RL6;
        int m335RL10 = m335RL(m335RL5, 10);
        int m335RL11 = m335RL(m335RL6 + m334f1(m335RL9, m335RL7, m335RL10) + this.f26478X[5], 8) + m335RL8;
        int m335RL12 = m335RL(m335RL7, 10);
        int m335RL13 = m335RL(m335RL8 + m334f1(m335RL11, m335RL9, m335RL12) + this.f26478X[6], 7) + m335RL10;
        int m335RL14 = m335RL(m335RL9, 10);
        int m335RL15 = m335RL(m335RL10 + m334f1(m335RL13, m335RL11, m335RL14) + this.f26478X[7], 9) + m335RL12;
        int m335RL16 = m335RL(m335RL11, 10);
        int m335RL17 = m335RL(m335RL12 + m334f1(m335RL15, m335RL13, m335RL16) + this.f26478X[8], 11) + m335RL14;
        int m335RL18 = m335RL(m335RL13, 10);
        int m335RL19 = m335RL(m335RL14 + m334f1(m335RL17, m335RL15, m335RL18) + this.f26478X[9], 13) + m335RL16;
        int m335RL20 = m335RL(m335RL15, 10);
        int m335RL21 = m335RL(m335RL16 + m334f1(m335RL19, m335RL17, m335RL20) + this.f26478X[10], 14) + m335RL18;
        int m335RL22 = m335RL(m335RL17, 10);
        int m335RL23 = m335RL(m335RL18 + m334f1(m335RL21, m335RL19, m335RL22) + this.f26478X[11], 15) + m335RL20;
        int m335RL24 = m335RL(m335RL19, 10);
        int m335RL25 = m335RL(m335RL20 + m334f1(m335RL23, m335RL21, m335RL24) + this.f26478X[12], 6) + m335RL22;
        int m335RL26 = m335RL(m335RL21, 10);
        int m335RL27 = m335RL(m335RL22 + m334f1(m335RL25, m335RL23, m335RL26) + this.f26478X[13], 7) + m335RL24;
        int m335RL28 = m335RL(m335RL23, 10);
        int m335RL29 = m335RL(m335RL24 + m334f1(m335RL27, m335RL25, m335RL28) + this.f26478X[14], 9) + m335RL26;
        int m335RL30 = m335RL(m335RL25, 10);
        int m335RL31 = m335RL(m335RL26 + m334f1(m335RL29, m335RL27, m335RL30) + this.f26478X[15], 8) + m335RL28;
        int m335RL32 = m335RL(m335RL27, 10);
        int m335RL33 = m335RL(i + m330f5(i2, i3, i4) + this.f26478X[5] + 1352829926, 8) + i5;
        int m335RL34 = m335RL(i3, 10);
        int m335RL35 = m335RL(i5 + m330f5(m335RL33, i2, m335RL34) + this.f26478X[14] + 1352829926, 9) + i4;
        int m335RL36 = m335RL(i2, 10);
        int m335RL37 = m335RL(i4 + m330f5(m335RL35, m335RL33, m335RL36) + this.f26478X[7] + 1352829926, 9) + m335RL34;
        int m335RL38 = m335RL(m335RL33, 10);
        int m335RL39 = m335RL(m335RL34 + m330f5(m335RL37, m335RL35, m335RL38) + this.f26478X[0] + 1352829926, 11) + m335RL36;
        int m335RL40 = m335RL(m335RL35, 10);
        int m335RL41 = m335RL(m335RL36 + m330f5(m335RL39, m335RL37, m335RL40) + this.f26478X[9] + 1352829926, 13) + m335RL38;
        int m335RL42 = m335RL(m335RL37, 10);
        int m335RL43 = m335RL(m335RL38 + m330f5(m335RL41, m335RL39, m335RL42) + this.f26478X[2] + 1352829926, 15) + m335RL40;
        int m335RL44 = m335RL(m335RL39, 10);
        int m335RL45 = m335RL(m335RL40 + m330f5(m335RL43, m335RL41, m335RL44) + this.f26478X[11] + 1352829926, 15) + m335RL42;
        int m335RL46 = m335RL(m335RL41, 10);
        int m335RL47 = m335RL(m335RL42 + m330f5(m335RL45, m335RL43, m335RL46) + this.f26478X[4] + 1352829926, 5) + m335RL44;
        int m335RL48 = m335RL(m335RL43, 10);
        int m335RL49 = m335RL(m335RL44 + m330f5(m335RL47, m335RL45, m335RL48) + this.f26478X[13] + 1352829926, 7) + m335RL46;
        int m335RL50 = m335RL(m335RL45, 10);
        int m335RL51 = m335RL(m335RL46 + m330f5(m335RL49, m335RL47, m335RL50) + this.f26478X[6] + 1352829926, 7) + m335RL48;
        int m335RL52 = m335RL(m335RL47, 10);
        int m335RL53 = m335RL(m335RL48 + m330f5(m335RL51, m335RL49, m335RL52) + this.f26478X[15] + 1352829926, 8) + m335RL50;
        int m335RL54 = m335RL(m335RL49, 10);
        int m335RL55 = m335RL(m335RL50 + m330f5(m335RL53, m335RL51, m335RL54) + this.f26478X[8] + 1352829926, 11) + m335RL52;
        int m335RL56 = m335RL(m335RL51, 10);
        int m335RL57 = m335RL(m335RL52 + m330f5(m335RL55, m335RL53, m335RL56) + this.f26478X[1] + 1352829926, 14) + m335RL54;
        int m335RL58 = m335RL(m335RL53, 10);
        int m335RL59 = m335RL(m335RL54 + m330f5(m335RL57, m335RL55, m335RL58) + this.f26478X[10] + 1352829926, 14) + m335RL56;
        int m335RL60 = m335RL(m335RL55, 10);
        int m335RL61 = m335RL(m335RL56 + m330f5(m335RL59, m335RL57, m335RL60) + this.f26478X[3] + 1352829926, 12) + m335RL58;
        int m335RL62 = m335RL(m335RL57, 10);
        int m335RL63 = m335RL(m335RL58 + m330f5(m335RL61, m335RL59, m335RL62) + this.f26478X[12] + 1352829926, 6) + m335RL60;
        int m335RL64 = m335RL(m335RL59, 10);
        int m335RL65 = m335RL(m335RL28 + m333f2(m335RL31, m335RL29, m335RL32) + this.f26478X[7] + C0512f.f1670m, 7) + m335RL30;
        int m335RL66 = m335RL(m335RL29, 10);
        int m335RL67 = m335RL(m335RL30 + m333f2(m335RL65, m335RL31, m335RL66) + this.f26478X[4] + C0512f.f1670m, 6) + m335RL32;
        int m335RL68 = m335RL(m335RL31, 10);
        int m335RL69 = m335RL(m335RL32 + m333f2(m335RL67, m335RL65, m335RL68) + this.f26478X[13] + C0512f.f1670m, 8) + m335RL66;
        int m335RL70 = m335RL(m335RL65, 10);
        int m335RL71 = m335RL(m335RL66 + m333f2(m335RL69, m335RL67, m335RL70) + this.f26478X[1] + C0512f.f1670m, 13) + m335RL68;
        int m335RL72 = m335RL(m335RL67, 10);
        int m335RL73 = m335RL(m335RL68 + m333f2(m335RL71, m335RL69, m335RL72) + this.f26478X[10] + C0512f.f1670m, 11) + m335RL70;
        int m335RL74 = m335RL(m335RL69, 10);
        int m335RL75 = m335RL(m335RL70 + m333f2(m335RL73, m335RL71, m335RL74) + this.f26478X[6] + C0512f.f1670m, 9) + m335RL72;
        int m335RL76 = m335RL(m335RL71, 10);
        int m335RL77 = m335RL(m335RL72 + m333f2(m335RL75, m335RL73, m335RL76) + this.f26478X[15] + C0512f.f1670m, 7) + m335RL74;
        int m335RL78 = m335RL(m335RL73, 10);
        int m335RL79 = m335RL(m335RL74 + m333f2(m335RL77, m335RL75, m335RL78) + this.f26478X[3] + C0512f.f1670m, 15) + m335RL76;
        int m335RL80 = m335RL(m335RL75, 10);
        int m335RL81 = m335RL(m335RL76 + m333f2(m335RL79, m335RL77, m335RL80) + this.f26478X[12] + C0512f.f1670m, 7) + m335RL78;
        int m335RL82 = m335RL(m335RL77, 10);
        int m335RL83 = m335RL(m335RL78 + m333f2(m335RL81, m335RL79, m335RL82) + this.f26478X[0] + C0512f.f1670m, 12) + m335RL80;
        int m335RL84 = m335RL(m335RL79, 10);
        int m335RL85 = m335RL(m335RL80 + m333f2(m335RL83, m335RL81, m335RL84) + this.f26478X[9] + C0512f.f1670m, 15) + m335RL82;
        int m335RL86 = m335RL(m335RL81, 10);
        int m335RL87 = m335RL(m335RL82 + m333f2(m335RL85, m335RL83, m335RL86) + this.f26478X[5] + C0512f.f1670m, 9) + m335RL84;
        int m335RL88 = m335RL(m335RL83, 10);
        int m335RL89 = m335RL(m335RL84 + m333f2(m335RL87, m335RL85, m335RL88) + this.f26478X[2] + C0512f.f1670m, 11) + m335RL86;
        int m335RL90 = m335RL(m335RL85, 10);
        int m335RL91 = m335RL(m335RL86 + m333f2(m335RL89, m335RL87, m335RL90) + this.f26478X[14] + C0512f.f1670m, 7) + m335RL88;
        int m335RL92 = m335RL(m335RL87, 10);
        int m335RL93 = m335RL(m335RL88 + m333f2(m335RL91, m335RL89, m335RL92) + this.f26478X[11] + C0512f.f1670m, 13) + m335RL90;
        int m335RL94 = m335RL(m335RL89, 10);
        int m335RL95 = m335RL(m335RL90 + m333f2(m335RL93, m335RL91, m335RL94) + this.f26478X[8] + C0512f.f1670m, 12) + m335RL92;
        int m335RL96 = m335RL(m335RL91, 10);
        int m335RL97 = m335RL(m335RL60 + m331f4(m335RL63, m335RL61, m335RL64) + this.f26478X[6] + 1548603684, 9) + m335RL62;
        int m335RL98 = m335RL(m335RL61, 10);
        int m335RL99 = m335RL(m335RL62 + m331f4(m335RL97, m335RL63, m335RL98) + this.f26478X[11] + 1548603684, 13) + m335RL64;
        int m335RL100 = m335RL(m335RL63, 10);
        int m335RL101 = m335RL(m335RL64 + m331f4(m335RL99, m335RL97, m335RL100) + this.f26478X[3] + 1548603684, 15) + m335RL98;
        int m335RL102 = m335RL(m335RL97, 10);
        int m335RL103 = m335RL(m335RL98 + m331f4(m335RL101, m335RL99, m335RL102) + this.f26478X[7] + 1548603684, 7) + m335RL100;
        int m335RL104 = m335RL(m335RL99, 10);
        int m335RL105 = m335RL(m335RL100 + m331f4(m335RL103, m335RL101, m335RL104) + this.f26478X[0] + 1548603684, 12) + m335RL102;
        int m335RL106 = m335RL(m335RL101, 10);
        int m335RL107 = m335RL(m335RL102 + m331f4(m335RL105, m335RL103, m335RL106) + this.f26478X[13] + 1548603684, 8) + m335RL104;
        int m335RL108 = m335RL(m335RL103, 10);
        int m335RL109 = m335RL(m335RL104 + m331f4(m335RL107, m335RL105, m335RL108) + this.f26478X[5] + 1548603684, 9) + m335RL106;
        int m335RL110 = m335RL(m335RL105, 10);
        int m335RL111 = m335RL(m335RL106 + m331f4(m335RL109, m335RL107, m335RL110) + this.f26478X[10] + 1548603684, 11) + m335RL108;
        int m335RL112 = m335RL(m335RL107, 10);
        int m335RL113 = m335RL(m335RL108 + m331f4(m335RL111, m335RL109, m335RL112) + this.f26478X[14] + 1548603684, 7) + m335RL110;
        int m335RL114 = m335RL(m335RL109, 10);
        int m335RL115 = m335RL(m335RL110 + m331f4(m335RL113, m335RL111, m335RL114) + this.f26478X[15] + 1548603684, 7) + m335RL112;
        int m335RL116 = m335RL(m335RL111, 10);
        int m335RL117 = m335RL(m335RL112 + m331f4(m335RL115, m335RL113, m335RL116) + this.f26478X[8] + 1548603684, 12) + m335RL114;
        int m335RL118 = m335RL(m335RL113, 10);
        int m335RL119 = m335RL(m335RL114 + m331f4(m335RL117, m335RL115, m335RL118) + this.f26478X[12] + 1548603684, 7) + m335RL116;
        int m335RL120 = m335RL(m335RL115, 10);
        int m335RL121 = m335RL(m335RL116 + m331f4(m335RL119, m335RL117, m335RL120) + this.f26478X[4] + 1548603684, 6) + m335RL118;
        int m335RL122 = m335RL(m335RL117, 10);
        int m335RL123 = m335RL(m335RL118 + m331f4(m335RL121, m335RL119, m335RL122) + this.f26478X[9] + 1548603684, 15) + m335RL120;
        int m335RL124 = m335RL(m335RL119, 10);
        int m335RL125 = m335RL(m335RL120 + m331f4(m335RL123, m335RL121, m335RL124) + this.f26478X[1] + 1548603684, 13) + m335RL122;
        int m335RL126 = m335RL(m335RL121, 10);
        int m335RL127 = m335RL(m335RL122 + m331f4(m335RL125, m335RL123, m335RL126) + this.f26478X[2] + 1548603684, 11) + m335RL124;
        int m335RL128 = m335RL(m335RL123, 10);
        int m335RL129 = m335RL(m335RL92 + m332f3(m335RL95, m335RL93, m335RL96) + this.f26478X[3] + C0512f.f1671n, 11) + m335RL94;
        int m335RL130 = m335RL(m335RL93, 10);
        int m335RL131 = m335RL(m335RL94 + m332f3(m335RL129, m335RL95, m335RL130) + this.f26478X[10] + C0512f.f1671n, 13) + m335RL96;
        int m335RL132 = m335RL(m335RL95, 10);
        int m335RL133 = m335RL(m335RL96 + m332f3(m335RL131, m335RL129, m335RL132) + this.f26478X[14] + C0512f.f1671n, 6) + m335RL130;
        int m335RL134 = m335RL(m335RL129, 10);
        int m335RL135 = m335RL(m335RL130 + m332f3(m335RL133, m335RL131, m335RL134) + this.f26478X[4] + C0512f.f1671n, 7) + m335RL132;
        int m335RL136 = m335RL(m335RL131, 10);
        int m335RL137 = m335RL(m335RL132 + m332f3(m335RL135, m335RL133, m335RL136) + this.f26478X[9] + C0512f.f1671n, 14) + m335RL134;
        int m335RL138 = m335RL(m335RL133, 10);
        int m335RL139 = m335RL(m335RL134 + m332f3(m335RL137, m335RL135, m335RL138) + this.f26478X[15] + C0512f.f1671n, 9) + m335RL136;
        int m335RL140 = m335RL(m335RL135, 10);
        int m335RL141 = m335RL(m335RL136 + m332f3(m335RL139, m335RL137, m335RL140) + this.f26478X[8] + C0512f.f1671n, 13) + m335RL138;
        int m335RL142 = m335RL(m335RL137, 10);
        int m335RL143 = m335RL(m335RL138 + m332f3(m335RL141, m335RL139, m335RL142) + this.f26478X[1] + C0512f.f1671n, 15) + m335RL140;
        int m335RL144 = m335RL(m335RL139, 10);
        int m335RL145 = m335RL(m335RL140 + m332f3(m335RL143, m335RL141, m335RL144) + this.f26478X[2] + C0512f.f1671n, 14) + m335RL142;
        int m335RL146 = m335RL(m335RL141, 10);
        int m335RL147 = m335RL(m335RL142 + m332f3(m335RL145, m335RL143, m335RL146) + this.f26478X[7] + C0512f.f1671n, 8) + m335RL144;
        int m335RL148 = m335RL(m335RL143, 10);
        int m335RL149 = m335RL(m335RL144 + m332f3(m335RL147, m335RL145, m335RL148) + this.f26478X[0] + C0512f.f1671n, 13) + m335RL146;
        int m335RL150 = m335RL(m335RL145, 10);
        int m335RL151 = m335RL(m335RL146 + m332f3(m335RL149, m335RL147, m335RL150) + this.f26478X[6] + C0512f.f1671n, 6) + m335RL148;
        int m335RL152 = m335RL(m335RL147, 10);
        int m335RL153 = m335RL(m335RL148 + m332f3(m335RL151, m335RL149, m335RL152) + this.f26478X[13] + C0512f.f1671n, 5) + m335RL150;
        int m335RL154 = m335RL(m335RL149, 10);
        int m335RL155 = m335RL(m335RL150 + m332f3(m335RL153, m335RL151, m335RL154) + this.f26478X[11] + C0512f.f1671n, 12) + m335RL152;
        int m335RL156 = m335RL(m335RL151, 10);
        int m335RL157 = m335RL(m335RL152 + m332f3(m335RL155, m335RL153, m335RL156) + this.f26478X[5] + C0512f.f1671n, 7) + m335RL154;
        int m335RL158 = m335RL(m335RL153, 10);
        int m335RL159 = m335RL(m335RL154 + m332f3(m335RL157, m335RL155, m335RL158) + this.f26478X[12] + C0512f.f1671n, 5) + m335RL156;
        int m335RL160 = m335RL(m335RL155, 10);
        int m335RL161 = m335RL(m335RL124 + m332f3(m335RL127, m335RL125, m335RL128) + this.f26478X[15] + 1836072691, 9) + m335RL126;
        int m335RL162 = m335RL(m335RL125, 10);
        int m335RL163 = m335RL(m335RL126 + m332f3(m335RL161, m335RL127, m335RL162) + this.f26478X[5] + 1836072691, 7) + m335RL128;
        int m335RL164 = m335RL(m335RL127, 10);
        int m335RL165 = m335RL(m335RL128 + m332f3(m335RL163, m335RL161, m335RL164) + this.f26478X[1] + 1836072691, 15) + m335RL162;
        int m335RL166 = m335RL(m335RL161, 10);
        int m335RL167 = m335RL(m335RL162 + m332f3(m335RL165, m335RL163, m335RL166) + this.f26478X[3] + 1836072691, 11) + m335RL164;
        int m335RL168 = m335RL(m335RL163, 10);
        int m335RL169 = m335RL(m335RL164 + m332f3(m335RL167, m335RL165, m335RL168) + this.f26478X[7] + 1836072691, 8) + m335RL166;
        int m335RL170 = m335RL(m335RL165, 10);
        int m335RL171 = m335RL(m335RL166 + m332f3(m335RL169, m335RL167, m335RL170) + this.f26478X[14] + 1836072691, 6) + m335RL168;
        int m335RL172 = m335RL(m335RL167, 10);
        int m335RL173 = m335RL(m335RL168 + m332f3(m335RL171, m335RL169, m335RL172) + this.f26478X[6] + 1836072691, 6) + m335RL170;
        int m335RL174 = m335RL(m335RL169, 10);
        int m335RL175 = m335RL(m335RL170 + m332f3(m335RL173, m335RL171, m335RL174) + this.f26478X[9] + 1836072691, 14) + m335RL172;
        int m335RL176 = m335RL(m335RL171, 10);
        int m335RL177 = m335RL(m335RL172 + m332f3(m335RL175, m335RL173, m335RL176) + this.f26478X[11] + 1836072691, 12) + m335RL174;
        int m335RL178 = m335RL(m335RL173, 10);
        int m335RL179 = m335RL(m335RL174 + m332f3(m335RL177, m335RL175, m335RL178) + this.f26478X[8] + 1836072691, 13) + m335RL176;
        int m335RL180 = m335RL(m335RL175, 10);
        int m335RL181 = m335RL(m335RL176 + m332f3(m335RL179, m335RL177, m335RL180) + this.f26478X[12] + 1836072691, 5) + m335RL178;
        int m335RL182 = m335RL(m335RL177, 10);
        int m335RL183 = m335RL(m335RL178 + m332f3(m335RL181, m335RL179, m335RL182) + this.f26478X[2] + 1836072691, 14) + m335RL180;
        int m335RL184 = m335RL(m335RL179, 10);
        int m335RL185 = m335RL(m335RL180 + m332f3(m335RL183, m335RL181, m335RL184) + this.f26478X[10] + 1836072691, 13) + m335RL182;
        int m335RL186 = m335RL(m335RL181, 10);
        int m335RL187 = m335RL(m335RL182 + m332f3(m335RL185, m335RL183, m335RL186) + this.f26478X[0] + 1836072691, 13) + m335RL184;
        int m335RL188 = m335RL(m335RL183, 10);
        int m335RL189 = m335RL(m335RL184 + m332f3(m335RL187, m335RL185, m335RL188) + this.f26478X[4] + 1836072691, 7) + m335RL186;
        int m335RL190 = m335RL(m335RL185, 10);
        int m335RL191 = m335RL(m335RL186 + m332f3(m335RL189, m335RL187, m335RL190) + this.f26478X[13] + 1836072691, 5) + m335RL188;
        int m335RL192 = m335RL(m335RL187, 10);
        int m335RL193 = m335RL(m335RL156 + m331f4(m335RL159, m335RL157, m335RL160) + this.f26478X[1] + C0512f.f1672o, 11) + m335RL158;
        int m335RL194 = m335RL(m335RL157, 10);
        int m335RL195 = m335RL(m335RL158 + m331f4(m335RL193, m335RL159, m335RL194) + this.f26478X[9] + C0512f.f1672o, 12) + m335RL160;
        int m335RL196 = m335RL(m335RL159, 10);
        int m335RL197 = m335RL(m335RL160 + m331f4(m335RL195, m335RL193, m335RL196) + this.f26478X[11] + C0512f.f1672o, 14) + m335RL194;
        int m335RL198 = m335RL(m335RL193, 10);
        int m335RL199 = m335RL(m335RL194 + m331f4(m335RL197, m335RL195, m335RL198) + this.f26478X[10] + C0512f.f1672o, 15) + m335RL196;
        int m335RL200 = m335RL(m335RL195, 10);
        int m335RL201 = m335RL(m335RL196 + m331f4(m335RL199, m335RL197, m335RL200) + this.f26478X[0] + C0512f.f1672o, 14) + m335RL198;
        int m335RL202 = m335RL(m335RL197, 10);
        int m335RL203 = m335RL(m335RL198 + m331f4(m335RL201, m335RL199, m335RL202) + this.f26478X[8] + C0512f.f1672o, 15) + m335RL200;
        int m335RL204 = m335RL(m335RL199, 10);
        int m335RL205 = m335RL(m335RL200 + m331f4(m335RL203, m335RL201, m335RL204) + this.f26478X[12] + C0512f.f1672o, 9) + m335RL202;
        int m335RL206 = m335RL(m335RL201, 10);
        int m335RL207 = m335RL(m335RL202 + m331f4(m335RL205, m335RL203, m335RL206) + this.f26478X[4] + C0512f.f1672o, 8) + m335RL204;
        int m335RL208 = m335RL(m335RL203, 10);
        int m335RL209 = m335RL(m335RL204 + m331f4(m335RL207, m335RL205, m335RL208) + this.f26478X[13] + C0512f.f1672o, 9) + m335RL206;
        int m335RL210 = m335RL(m335RL205, 10);
        int m335RL211 = m335RL(m335RL206 + m331f4(m335RL209, m335RL207, m335RL210) + this.f26478X[3] + C0512f.f1672o, 14) + m335RL208;
        int m335RL212 = m335RL(m335RL207, 10);
        int m335RL213 = m335RL(m335RL208 + m331f4(m335RL211, m335RL209, m335RL212) + this.f26478X[7] + C0512f.f1672o, 5) + m335RL210;
        int m335RL214 = m335RL(m335RL209, 10);
        int m335RL215 = m335RL(m335RL210 + m331f4(m335RL213, m335RL211, m335RL214) + this.f26478X[15] + C0512f.f1672o, 6) + m335RL212;
        int m335RL216 = m335RL(m335RL211, 10);
        int m335RL217 = m335RL(m335RL212 + m331f4(m335RL215, m335RL213, m335RL216) + this.f26478X[14] + C0512f.f1672o, 8) + m335RL214;
        int m335RL218 = m335RL(m335RL213, 10);
        int m335RL219 = m335RL(m335RL214 + m331f4(m335RL217, m335RL215, m335RL218) + this.f26478X[5] + C0512f.f1672o, 6) + m335RL216;
        int m335RL220 = m335RL(m335RL215, 10);
        int m335RL221 = m335RL(m335RL216 + m331f4(m335RL219, m335RL217, m335RL220) + this.f26478X[6] + C0512f.f1672o, 5) + m335RL218;
        int m335RL222 = m335RL(m335RL217, 10);
        int m335RL223 = m335RL(m335RL218 + m331f4(m335RL221, m335RL219, m335RL222) + this.f26478X[2] + C0512f.f1672o, 12) + m335RL220;
        int m335RL224 = m335RL(m335RL219, 10);
        int m335RL225 = m335RL(m335RL188 + m333f2(m335RL191, m335RL189, m335RL192) + this.f26478X[8] + 2053994217, 15) + m335RL190;
        int m335RL226 = m335RL(m335RL189, 10);
        int m335RL227 = m335RL(m335RL190 + m333f2(m335RL225, m335RL191, m335RL226) + this.f26478X[6] + 2053994217, 5) + m335RL192;
        int m335RL228 = m335RL(m335RL191, 10);
        int m335RL229 = m335RL(m335RL192 + m333f2(m335RL227, m335RL225, m335RL228) + this.f26478X[4] + 2053994217, 8) + m335RL226;
        int m335RL230 = m335RL(m335RL225, 10);
        int m335RL231 = m335RL(m335RL226 + m333f2(m335RL229, m335RL227, m335RL230) + this.f26478X[1] + 2053994217, 11) + m335RL228;
        int m335RL232 = m335RL(m335RL227, 10);
        int m335RL233 = m335RL(m335RL228 + m333f2(m335RL231, m335RL229, m335RL232) + this.f26478X[3] + 2053994217, 14) + m335RL230;
        int m335RL234 = m335RL(m335RL229, 10);
        int m335RL235 = m335RL(m335RL230 + m333f2(m335RL233, m335RL231, m335RL234) + this.f26478X[11] + 2053994217, 14) + m335RL232;
        int m335RL236 = m335RL(m335RL231, 10);
        int m335RL237 = m335RL(m335RL232 + m333f2(m335RL235, m335RL233, m335RL236) + this.f26478X[15] + 2053994217, 6) + m335RL234;
        int m335RL238 = m335RL(m335RL233, 10);
        int m335RL239 = m335RL(m335RL234 + m333f2(m335RL237, m335RL235, m335RL238) + this.f26478X[0] + 2053994217, 14) + m335RL236;
        int m335RL240 = m335RL(m335RL235, 10);
        int m335RL241 = m335RL(m335RL236 + m333f2(m335RL239, m335RL237, m335RL240) + this.f26478X[5] + 2053994217, 6) + m335RL238;
        int m335RL242 = m335RL(m335RL237, 10);
        int m335RL243 = m335RL(m335RL238 + m333f2(m335RL241, m335RL239, m335RL242) + this.f26478X[12] + 2053994217, 9) + m335RL240;
        int m335RL244 = m335RL(m335RL239, 10);
        int m335RL245 = m335RL(m335RL240 + m333f2(m335RL243, m335RL241, m335RL244) + this.f26478X[2] + 2053994217, 12) + m335RL242;
        int m335RL246 = m335RL(m335RL241, 10);
        int m335RL247 = m335RL(m335RL242 + m333f2(m335RL245, m335RL243, m335RL246) + this.f26478X[13] + 2053994217, 9) + m335RL244;
        int m335RL248 = m335RL(m335RL243, 10);
        int m335RL249 = m335RL(m335RL244 + m333f2(m335RL247, m335RL245, m335RL248) + this.f26478X[9] + 2053994217, 12) + m335RL246;
        int m335RL250 = m335RL(m335RL245, 10);
        int m335RL251 = m335RL(m335RL246 + m333f2(m335RL249, m335RL247, m335RL250) + this.f26478X[7] + 2053994217, 5) + m335RL248;
        int m335RL252 = m335RL(m335RL247, 10);
        int m335RL253 = m335RL(m335RL248 + m333f2(m335RL251, m335RL249, m335RL252) + this.f26478X[10] + 2053994217, 15) + m335RL250;
        int m335RL254 = m335RL(m335RL249, 10);
        int m335RL255 = m335RL(m335RL250 + m333f2(m335RL253, m335RL251, m335RL254) + this.f26478X[14] + 2053994217, 8) + m335RL252;
        int m335RL256 = m335RL(m335RL251, 10);
        int m335RL257 = m335RL(((m335RL220 + m330f5(m335RL223, m335RL221, m335RL224)) + this.f26478X[4]) - 1454113458, 9) + m335RL222;
        int m335RL258 = m335RL(m335RL221, 10);
        int m335RL259 = m335RL(((m335RL222 + m330f5(m335RL257, m335RL223, m335RL258)) + this.f26478X[0]) - 1454113458, 15) + m335RL224;
        int m335RL260 = m335RL(m335RL223, 10);
        int m335RL261 = m335RL(((m335RL224 + m330f5(m335RL259, m335RL257, m335RL260)) + this.f26478X[5]) - 1454113458, 5) + m335RL258;
        int m335RL262 = m335RL(m335RL257, 10);
        int m335RL263 = m335RL(((m335RL258 + m330f5(m335RL261, m335RL259, m335RL262)) + this.f26478X[9]) - 1454113458, 11) + m335RL260;
        int m335RL264 = m335RL(m335RL259, 10);
        int m335RL265 = m335RL(((m335RL260 + m330f5(m335RL263, m335RL261, m335RL264)) + this.f26478X[7]) - 1454113458, 6) + m335RL262;
        int m335RL266 = m335RL(m335RL261, 10);
        int m335RL267 = m335RL(((m335RL262 + m330f5(m335RL265, m335RL263, m335RL266)) + this.f26478X[12]) - 1454113458, 8) + m335RL264;
        int m335RL268 = m335RL(m335RL263, 10);
        int m335RL269 = m335RL(((m335RL264 + m330f5(m335RL267, m335RL265, m335RL268)) + this.f26478X[2]) - 1454113458, 13) + m335RL266;
        int m335RL270 = m335RL(m335RL265, 10);
        int m335RL271 = m335RL(((m335RL266 + m330f5(m335RL269, m335RL267, m335RL270)) + this.f26478X[10]) - 1454113458, 12) + m335RL268;
        int m335RL272 = m335RL(m335RL267, 10);
        int m335RL273 = m335RL(((m335RL268 + m330f5(m335RL271, m335RL269, m335RL272)) + this.f26478X[14]) - 1454113458, 5) + m335RL270;
        int m335RL274 = m335RL(m335RL269, 10);
        int m335RL275 = m335RL(((m335RL270 + m330f5(m335RL273, m335RL271, m335RL274)) + this.f26478X[1]) - 1454113458, 12) + m335RL272;
        int m335RL276 = m335RL(m335RL271, 10);
        int m335RL277 = m335RL(((m335RL272 + m330f5(m335RL275, m335RL273, m335RL276)) + this.f26478X[3]) - 1454113458, 13) + m335RL274;
        int m335RL278 = m335RL(m335RL273, 10);
        int m335RL279 = m335RL(((m335RL274 + m330f5(m335RL277, m335RL275, m335RL278)) + this.f26478X[8]) - 1454113458, 14) + m335RL276;
        int m335RL280 = m335RL(m335RL275, 10);
        int m335RL281 = m335RL(((m335RL276 + m330f5(m335RL279, m335RL277, m335RL280)) + this.f26478X[11]) - 1454113458, 11) + m335RL278;
        int m335RL282 = m335RL(m335RL277, 10);
        int m335RL283 = m335RL(((m335RL278 + m330f5(m335RL281, m335RL279, m335RL282)) + this.f26478X[6]) - 1454113458, 8) + m335RL280;
        int m335RL284 = m335RL(m335RL279, 10);
        int m335RL285 = m335RL(((m335RL280 + m330f5(m335RL283, m335RL281, m335RL284)) + this.f26478X[15]) - 1454113458, 5) + m335RL282;
        int m335RL286 = m335RL(m335RL281, 10);
        int m335RL287 = m335RL(m335RL283, 10);
        int m335RL288 = m335RL(m335RL252 + m334f1(m335RL255, m335RL253, m335RL256) + this.f26478X[12], 8) + m335RL254;
        int m335RL289 = m335RL(m335RL253, 10);
        int m335RL290 = m335RL(m335RL254 + m334f1(m335RL288, m335RL255, m335RL289) + this.f26478X[15], 5) + m335RL256;
        int m335RL291 = m335RL(m335RL255, 10);
        int m335RL292 = m335RL(m335RL256 + m334f1(m335RL290, m335RL288, m335RL291) + this.f26478X[10], 12) + m335RL289;
        int m335RL293 = m335RL(m335RL288, 10);
        int m335RL294 = m335RL(m335RL289 + m334f1(m335RL292, m335RL290, m335RL293) + this.f26478X[4], 9) + m335RL291;
        int m335RL295 = m335RL(m335RL290, 10);
        int m335RL296 = m335RL(m335RL291 + m334f1(m335RL294, m335RL292, m335RL295) + this.f26478X[1], 12) + m335RL293;
        int m335RL297 = m335RL(m335RL292, 10);
        int m335RL298 = m335RL(m335RL293 + m334f1(m335RL296, m335RL294, m335RL297) + this.f26478X[5], 5) + m335RL295;
        int m335RL299 = m335RL(m335RL294, 10);
        int m335RL300 = m335RL(m335RL295 + m334f1(m335RL298, m335RL296, m335RL299) + this.f26478X[8], 14) + m335RL297;
        int m335RL301 = m335RL(m335RL296, 10);
        int m335RL302 = m335RL(m335RL297 + m334f1(m335RL300, m335RL298, m335RL301) + this.f26478X[7], 6) + m335RL299;
        int m335RL303 = m335RL(m335RL298, 10);
        int m335RL304 = m335RL(m335RL299 + m334f1(m335RL302, m335RL300, m335RL303) + this.f26478X[6], 8) + m335RL301;
        int m335RL305 = m335RL(m335RL300, 10);
        int m335RL306 = m335RL(m335RL301 + m334f1(m335RL304, m335RL302, m335RL305) + this.f26478X[2], 13) + m335RL303;
        int m335RL307 = m335RL(m335RL302, 10);
        int m335RL308 = m335RL(m335RL303 + m334f1(m335RL306, m335RL304, m335RL307) + this.f26478X[13], 6) + m335RL305;
        int m335RL309 = m335RL(m335RL304, 10);
        int m335RL310 = m335RL(m335RL305 + m334f1(m335RL308, m335RL306, m335RL309) + this.f26478X[14], 5) + m335RL307;
        int m335RL311 = m335RL(m335RL306, 10);
        int m335RL312 = m335RL(m335RL307 + m334f1(m335RL310, m335RL308, m335RL311) + this.f26478X[0], 15) + m335RL309;
        int m335RL313 = m335RL(m335RL308, 10);
        int m335RL314 = m335RL(m335RL309 + m334f1(m335RL312, m335RL310, m335RL313) + this.f26478X[3], 13) + m335RL311;
        int m335RL315 = m335RL(m335RL310, 10);
        int m335RL316 = m335RL(m335RL311 + m334f1(m335RL314, m335RL312, m335RL315) + this.f26478X[9], 11) + m335RL313;
        int m335RL317 = m335RL(m335RL312, 10);
        int m335RL318 = m335RL(m335RL313 + m334f1(m335RL316, m335RL314, m335RL317) + this.f26478X[11], 11) + m335RL315;
        this.f26474H1 = this.f26475H2 + m335RL287 + m335RL317;
        this.f26475H2 = this.f26476H3 + m335RL286 + m335RL315;
        this.f26476H3 = this.f26477H4 + m335RL284 + m335RL318;
        this.f26477H4 = this.f26473H0 + m335RL(((m335RL282 + m330f5(m335RL285, m335RL283, m335RL286)) + this.f26478X[13]) - 1454113458, 6) + m335RL284 + m335RL316;
        this.f26473H0 = m335RL(m335RL314, 10) + m335RL285 + this.f26474H1;
        int i6 = 0;
        this.xOff = 0;
        while (true) {
            int[] iArr = this.f26478X;
            if (i6 == iArr.length) {
                return;
            }
            iArr[i6] = 0;
            i6++;
        }
    }

    @Override // org.bouncycastle.crypto.digests.GeneralDigest
    protected void processLength(long j) {
        if (this.xOff > 14) {
            processBlock();
        }
        int[] iArr = this.f26478X;
        iArr[14] = (int) ((-1) & j);
        iArr[15] = (int) (j >>> 32);
    }

    @Override // org.bouncycastle.crypto.digests.GeneralDigest
    protected void processWord(byte[] bArr, int i) {
        int[] iArr = this.f26478X;
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
        this.f26473H0 = 1732584193;
        this.f26474H1 = -271733879;
        this.f26475H2 = -1732584194;
        this.f26476H3 = 271733878;
        this.f26477H4 = -1009589776;
        this.xOff = 0;
        int i = 0;
        while (true) {
            int[] iArr = this.f26478X;
            if (i == iArr.length) {
                return;
            }
            iArr[i] = 0;
            i++;
        }
    }

    @Override // org.bouncycastle.util.Memoable
    public void reset(Memoable memoable) {
        copyIn((RIPEMD160Digest) memoable);
    }
}
