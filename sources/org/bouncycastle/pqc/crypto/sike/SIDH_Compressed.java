package org.bouncycastle.pqc.crypto.sike;

import java.lang.reflect.Array;
import java.security.SecureRandom;
import org.bouncycastle.util.Arrays;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class SIDH_Compressed {
    private static final int t_points = 2;
    private SIKEEngine engine;

    public SIDH_Compressed(SIKEEngine sIKEEngine) {
        this.engine = sIKEEngine;
    }

    private int EphemeralKeyGeneration_A(byte[] bArr, byte[] bArr2) {
        int[] iArr = new int[3];
        int[] iArr2 = new int[this.engine.params.DLEN_3];
        long[] jArr = new long[this.engine.params.NWORDS_ORDER];
        long[] jArr2 = new long[this.engine.params.NWORDS_ORDER];
        long[] jArr3 = new long[this.engine.params.NWORDS_ORDER];
        long[] jArr4 = new long[this.engine.params.NWORDS_ORDER];
        long[][] jArr5 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][][] jArr6 = (long[][][]) Array.newInstance(long.class, 4, 2, this.engine.params.NWORDS_FIELD);
        long[][][][] jArr7 = (long[][][][]) Array.newInstance(long.class, this.engine.params.MAX_Alice + 1, 5, 2, this.engine.params.NWORDS_FIELD);
        PointProjFull[] pointProjFullArr = new PointProjFull[2];
        FullIsogeny_A_dual(bArr, jArr7, jArr5, 0);
        BuildOrdinary3nBasis_dual(jArr5, jArr7, pointProjFullArr, iArr, iArr, 2);
        Tate3_pairings(pointProjFullArr, jArr6);
        Dlogs3_dual(jArr6, iArr2, jArr2, jArr, jArr4, jArr3);
        Compress_PKA_dual(jArr2, jArr, jArr4, jArr3, jArr5, iArr, bArr2);
        return 0;
    }

    private boolean FirstPoint_dual(PointProj pointProj, PointProjFull pointProjFull, byte[] bArr) {
        PointProjFull pointProjFull2 = new PointProjFull(this.engine.params.NWORDS_FIELD);
        PointProjFull pointProjFull3 = new PointProjFull(this.engine.params.NWORDS_FIELD);
        long[][][] jArr = (long[][][]) Array.newInstance(long.class, 2, 2, this.engine.params.NWORDS_FIELD);
        long[][][] jArr2 = (long[][][]) Array.newInstance(long.class, 2, 2, this.engine.params.NWORDS_FIELD);
        long[] jArr3 = new long[this.engine.params.NWORDS_FIELD];
        int i = this.engine.params.NWORDS_FIELD;
        this.engine.fpx.fpcopy(this.engine.params.B_gen_3_tors, this.engine.params.NWORDS_FIELD * 0, pointProjFull2.f27240X[0]);
        this.engine.fpx.fpcopy(this.engine.params.B_gen_3_tors, this.engine.params.NWORDS_FIELD * 1, pointProjFull2.f27240X[1]);
        this.engine.fpx.fpcopy(this.engine.params.B_gen_3_tors, this.engine.params.NWORDS_FIELD * 2, pointProjFull2.f27241Y[0]);
        this.engine.fpx.fpcopy(this.engine.params.B_gen_3_tors, this.engine.params.NWORDS_FIELD * 3, pointProjFull2.f27241Y[1]);
        this.engine.fpx.fpcopy(this.engine.params.B_gen_3_tors, this.engine.params.NWORDS_FIELD * 4, pointProjFull3.f27240X[0]);
        this.engine.fpx.fpcopy(this.engine.params.B_gen_3_tors, this.engine.params.NWORDS_FIELD * 5, pointProjFull3.f27240X[1]);
        this.engine.fpx.fpcopy(this.engine.params.B_gen_3_tors, this.engine.params.NWORDS_FIELD * 6, pointProjFull3.f27241Y[0]);
        this.engine.fpx.fpcopy(this.engine.params.B_gen_3_tors, this.engine.params.NWORDS_FIELD * 7, pointProjFull3.f27241Y[1]);
        this.engine.isogeny.CompletePoint(pointProj, pointProjFull);
        Tate3_proj(pointProjFull2, pointProjFull, jArr[0], jArr2[0]);
        Tate3_proj(pointProjFull3, pointProjFull, jArr[1], jArr2[1]);
        FinalExpo3_2way(jArr, jArr2);
        this.engine.fpx.fp2correction(jArr[0]);
        this.engine.fpx.fp2correction(jArr[1]);
        int i2 = Fpx.subarrayEquals(jArr[0][1], jArr3, i) ? 0 : Fpx.subarrayEquals(jArr[0][1], this.engine.params.g_R_S_im, i) ? 1 : 2;
        int i3 = Fpx.subarrayEquals(jArr[1][1], jArr3, i) ? 0 : Fpx.subarrayEquals(jArr[1][1], this.engine.params.g_R_S_im, i) ? 1 : 2;
        if (i2 == 0 && i3 == 0) {
            return false;
        }
        if (i2 == 0) {
            bArr[0] = 0;
        } else if (i3 == 0) {
            bArr[0] = 1;
        } else if (i2 + i3 == 3) {
            bArr[0] = 3;
        } else {
            bArr[0] = 2;
        }
        return true;
    }

    private boolean SecondPoint_dual(PointProj pointProj, PointProjFull pointProjFull, byte[] bArr) {
        PointProjFull pointProjFull2 = new PointProjFull(this.engine.params.NWORDS_FIELD);
        long[][] jArr = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr2 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        int i = this.engine.params.NWORDS_FIELD;
        this.engine.fpx.fpcopy(this.engine.params.B_gen_3_tors, ((bArr[0] * 4) + 0) * this.engine.params.NWORDS_FIELD, pointProjFull2.f27240X[0]);
        this.engine.fpx.fpcopy(this.engine.params.B_gen_3_tors, ((bArr[0] * 4) + 1) * this.engine.params.NWORDS_FIELD, pointProjFull2.f27240X[1]);
        this.engine.fpx.fpcopy(this.engine.params.B_gen_3_tors, ((bArr[0] * 4) + 2) * this.engine.params.NWORDS_FIELD, pointProjFull2.f27241Y[0]);
        this.engine.fpx.fpcopy(this.engine.params.B_gen_3_tors, ((bArr[0] * 4) + 3) * this.engine.params.NWORDS_FIELD, pointProjFull2.f27241Y[1]);
        this.engine.isogeny.CompletePoint(pointProj, pointProjFull);
        Tate3_proj(pointProjFull2, pointProjFull, jArr, jArr2);
        FinalExpo3(jArr, jArr2);
        this.engine.fpx.fp2correction(jArr);
        return !Fpx.subarrayEquals(jArr[1], new long[this.engine.params.NWORDS_FIELD], i);
    }

    private void Tate2_pairings(PointProj pointProj, PointProj pointProj2, PointProjFull[] pointProjFullArr, long[][][] jArr) {
        long[][][] jArr2 = jArr;
        long[][][] jArr3 = (long[][][]) Array.newInstance(long.class, 4, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr4 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr5 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr6 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr7 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr8 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr9 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        this.engine.fpx.fpcopy(this.engine.params.Montgomery_one, 0, jArr4[0]);
        for (int i = 0; i < 2; i++) {
            this.engine.fpx.fp2copy(jArr4, jArr2[i]);
            this.engine.fpx.fp2copy(jArr4, jArr2[i + 2]);
        }
        long[][] jArr10 = pointProj.f27238X;
        long[][] jArr11 = pointProj.f27239Z;
        long[] jArr12 = this.engine.params.T_tate2_firststep_P;
        long[] jArr13 = this.engine.params.T_tate2_firststep_P;
        this.engine.fpx.fpcopy(this.engine.params.T_tate2_firststep_P, this.engine.params.NWORDS_FIELD * 2, jArr5[0]);
        this.engine.fpx.fpcopy(this.engine.params.T_tate2_firststep_P, this.engine.params.NWORDS_FIELD * 3, jArr5[1]);
        int i2 = 0;
        for (int i3 = 2; i2 < i3; i3 = 2) {
            this.engine.fpx.fp2sub(pointProjFullArr[i2].f27240X, jArr10, jArr6);
            this.engine.fpx.fp2sub(pointProjFullArr[i2].f27241Y, jArr11, jArr7);
            this.engine.fpx.fp2mul_mont(jArr5, jArr6, jArr6);
            this.engine.fpx.fp2sub(jArr6, jArr7, jArr8);
            this.engine.fpx.fpsubPRIME(pointProjFullArr[i2].f27240X[0], this.engine.params.T_tate2_firststep_P, 0, jArr9[0]);
            this.engine.fpx.fpcopy(pointProjFullArr[i2].f27240X[1], 0, jArr9[1]);
            this.engine.fpx.fpnegPRIME(jArr9[1]);
            this.engine.fpx.fp2mul_mont(jArr8, jArr9, jArr8);
            this.engine.fpx.fp2sqr_mont(jArr2[i2], jArr2[i2]);
            this.engine.fpx.fp2mul_mont(jArr2[i2], jArr8, jArr2[i2]);
            i2++;
            jArr11 = jArr11;
        }
        long[] jArr14 = jArr12;
        int i4 = this.engine.params.NWORDS_FIELD * 1;
        long[] jArr15 = jArr13;
        int i5 = 0;
        int i6 = 0;
        while (i5 < this.engine.params.OALICE_BITS - 2) {
            long[] jArr16 = this.engine.params.T_tate2_P;
            long[] jArr17 = this.engine.params.T_tate2_P;
            long[] jArr18 = this.engine.params.T_tate2_P;
            long[][][] jArr19 = jArr3;
            int i7 = i5 * 3;
            int i8 = this.engine.params.NWORDS_FIELD * (i7 + 0);
            long[][] jArr20 = jArr5;
            int i9 = this.engine.params.NWORDS_FIELD * (i7 + 1);
            int i10 = this.engine.params.NWORDS_FIELD * (i7 + 2);
            int i11 = i5;
            int i12 = 0;
            for (int i13 = 2; i12 < i13; i13 = 2) {
                int i14 = i8;
                this.engine.fpx.fpsubPRIME(jArr14, i6, pointProjFullArr[i12].f27240X[0], jArr6[1]);
                this.engine.fpx.fpmul_mont(jArr18, i10, jArr6[1], jArr6[1]);
                this.engine.fpx.fpmul_mont(jArr18, i10, pointProjFullArr[i12].f27240X[1], jArr6[0]);
                this.engine.fpx.fpsubPRIME(pointProjFullArr[i12].f27241Y[1], jArr15, i4, jArr7[1]);
                this.engine.fpx.fpsubPRIME(jArr6[1], jArr7[1], jArr8[1]);
                this.engine.fpx.fpsubPRIME(jArr6[0], pointProjFullArr[i12].f27241Y[0], jArr8[0]);
                this.engine.fpx.fpsubPRIME(pointProjFullArr[i12].f27240X[0], jArr16, i14, jArr9[0]);
                this.engine.fpx.fpcopy(pointProjFullArr[i12].f27240X[1], 0, jArr9[1]);
                this.engine.fpx.fpnegPRIME(jArr9[1]);
                this.engine.fpx.fp2mul_mont(jArr8, jArr9, jArr8);
                jArr2 = jArr;
                this.engine.fpx.fp2sqr_mont(jArr2[i12], jArr2[i12]);
                this.engine.fpx.fp2mul_mont(jArr2[i12], jArr8, jArr2[i12]);
                i12++;
                i8 = i14;
                i6 = i6;
                jArr15 = jArr15;
            }
            int i15 = i8;
            i5 = i11 + 1;
            i4 = i9;
            jArr14 = jArr16;
            jArr15 = jArr17;
            jArr3 = jArr19;
            jArr5 = jArr20;
            i6 = i15;
        }
        long[][][] jArr21 = jArr3;
        int i16 = i6;
        long[][] jArr22 = jArr5;
        for (int i17 = 0; i17 < 2; i17++) {
            this.engine.fpx.fpsubPRIME(pointProjFullArr[i17].f27240X[0], jArr14, i16, jArr8[0]);
            this.engine.fpx.fpcopy(pointProjFullArr[i17].f27240X[1], 0, jArr8[1]);
            this.engine.fpx.fp2sqr_mont(jArr2[i17], jArr2[i17]);
            this.engine.fpx.fp2mul_mont(jArr2[i17], jArr8, jArr2[i17]);
        }
        long[][] jArr23 = pointProj2.f27238X;
        long[][] jArr24 = pointProj2.f27239Z;
        long[] jArr25 = this.engine.params.T_tate2_firststep_Q;
        long[] jArr26 = this.engine.params.T_tate2_firststep_Q;
        int i18 = this.engine.params.NWORDS_FIELD * 1;
        this.engine.fpx.fpcopy(this.engine.params.T_tate2_firststep_Q, this.engine.params.NWORDS_FIELD * 2, jArr22[0]);
        this.engine.fpx.fpcopy(this.engine.params.T_tate2_firststep_Q, this.engine.params.NWORDS_FIELD * 3, jArr22[1]);
        int i19 = 0;
        while (i19 < 2) {
            this.engine.fpx.fp2sub(pointProjFullArr[i19].f27240X, jArr23, jArr6);
            this.engine.fpx.fp2sub(pointProjFullArr[i19].f27241Y, jArr24, jArr7);
            this.engine.fpx.fp2mul_mont(jArr22, jArr6, jArr6);
            this.engine.fpx.fp2sub(jArr6, jArr7, jArr8);
            this.engine.fpx.fpsubPRIME(pointProjFullArr[i19].f27240X[0], jArr25, 0, jArr9[0]);
            long[][] jArr27 = jArr23;
            this.engine.fpx.fpcopy(pointProjFullArr[i19].f27240X[1], 0, jArr9[1]);
            this.engine.fpx.fpnegPRIME(jArr9[1]);
            this.engine.fpx.fp2mul_mont(jArr8, jArr9, jArr8);
            int i20 = i19 + 2;
            this.engine.fpx.fp2sqr_mont(jArr2[i20], jArr2[i20]);
            this.engine.fpx.fp2mul_mont(jArr2[i20], jArr8, jArr2[i20]);
            i19++;
            jArr24 = jArr24;
            jArr23 = jArr27;
        }
        int i21 = 0;
        int i22 = 0;
        while (i21 < this.engine.params.OALICE_BITS - 2) {
            long[] jArr28 = this.engine.params.T_tate2_Q;
            long[] jArr29 = this.engine.params.T_tate2_Q;
            long[] jArr30 = this.engine.params.T_tate2_Q;
            int i23 = i21 * 3;
            int i24 = this.engine.params.NWORDS_FIELD * (i23 + 0);
            int i25 = this.engine.params.NWORDS_FIELD * (i23 + 1);
            int i26 = i21;
            int i27 = this.engine.params.NWORDS_FIELD * (i23 + 2);
            int i28 = 0;
            for (int i29 = 2; i28 < i29; i29 = 2) {
                this.engine.fpx.fpsubPRIME(pointProjFullArr[i28].f27240X[0], jArr25, i22, jArr6[0]);
                this.engine.fpx.fpmul_mont(jArr30, i27, jArr6[0], jArr6[0]);
                int i30 = i22;
                this.engine.fpx.fpmul_mont(jArr30, i27, pointProjFullArr[i28].f27240X[1], jArr6[1]);
                this.engine.fpx.fpsubPRIME(pointProjFullArr[i28].f27241Y[0], jArr26, i18, jArr7[0]);
                this.engine.fpx.fpsubPRIME(jArr6[0], jArr7[0], jArr8[0]);
                this.engine.fpx.fpsubPRIME(jArr6[1], pointProjFullArr[i28].f27241Y[1], jArr8[1]);
                jArr28 = jArr28;
                this.engine.fpx.fpsubPRIME(pointProjFullArr[i28].f27240X[0], jArr28, i24, jArr9[0]);
                this.engine.fpx.fpcopy(pointProjFullArr[i28].f27240X[1], 0, jArr9[1]);
                this.engine.fpx.fpnegPRIME(jArr9[1]);
                this.engine.fpx.fp2mul_mont(jArr8, jArr9, jArr8);
                int i31 = i28 + 2;
                jArr2 = jArr;
                this.engine.fpx.fp2sqr_mont(jArr2[i31], jArr2[i31]);
                this.engine.fpx.fp2mul_mont(jArr2[i31], jArr8, jArr2[i31]);
                i28++;
                i18 = i18;
                i22 = i30;
                jArr26 = jArr26;
            }
            i21 = i26 + 1;
            i18 = i25;
            jArr25 = jArr28;
            i22 = i24;
            jArr26 = jArr29;
        }
        int i32 = i22;
        for (int i33 = 0; i33 < 2; i33++) {
            this.engine.fpx.fpsubPRIME(pointProjFullArr[i33].f27240X[0], jArr25, i32, jArr8[0]);
            this.engine.fpx.fpcopy(pointProjFullArr[i33].f27240X[1], 0, jArr8[1]);
            int i34 = i33 + 2;
            this.engine.fpx.fp2sqr_mont(jArr2[i34], jArr2[i34]);
            this.engine.fpx.fp2mul_mont(jArr2[i34], jArr8, jArr2[i34]);
        }
        this.engine.fpx.mont_n_way_inv(jArr2, 4, jArr21);
        for (int i35 = 0; i35 < 4; i35++) {
            final_exponentiation_2_torsion(jArr2[i35], jArr21[i35], jArr2[i35]);
        }
    }

    private void Tate3_pairings(PointProjFull[] pointProjFullArr, long[][][] jArr) {
        long[] jArr2 = new long[this.engine.params.NWORDS_FIELD];
        long[] jArr3 = new long[this.engine.params.NWORDS_FIELD];
        long[] jArr4 = new long[this.engine.params.NWORDS_FIELD];
        long[] jArr5 = new long[this.engine.params.NWORDS_FIELD];
        long[] jArr6 = new long[this.engine.params.NWORDS_FIELD];
        long[] jArr7 = new long[this.engine.params.NWORDS_FIELD];
        long[] jArr8 = new long[this.engine.params.NWORDS_FIELD];
        long[] jArr9 = new long[this.engine.params.NWORDS_FIELD];
        long[] jArr10 = new long[this.engine.params.NWORDS_FIELD];
        long[][][] jArr11 = (long[][][]) Array.newInstance(long.class, 2, 2, this.engine.params.NWORDS_FIELD);
        long[][][] jArr12 = (long[][][]) Array.newInstance(long.class, 4, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr13 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr14 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr15 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr16 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr17 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr18 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr19 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr20 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr21 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr22 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        this.engine.fpx.fpcopy(this.engine.params.Montgomery_one, 0, jArr13[0]);
        for (int i = 0; i < 2; i++) {
            this.engine.fpx.fp2copy(jArr13, jArr[i]);
            this.engine.fpx.fp2copy(jArr13, jArr[i + 2]);
            this.engine.fpx.fp2sqr_mont(pointProjFullArr[i].f27240X, jArr11[i]);
        }
        int i2 = 0;
        while (i2 < this.engine.params.OBOB_EXPON - 1) {
            int i3 = i2 * 6;
            int i4 = i2;
            System.arraycopy(this.engine.params.T_tate3, this.engine.params.NWORDS_FIELD * (i3 + 0), jArr4, 0, this.engine.params.NWORDS_FIELD);
            System.arraycopy(this.engine.params.T_tate3, this.engine.params.NWORDS_FIELD * (i3 + 1), jArr5, 0, this.engine.params.NWORDS_FIELD);
            System.arraycopy(this.engine.params.T_tate3, this.engine.params.NWORDS_FIELD * (i3 + 2), jArr6, 0, this.engine.params.NWORDS_FIELD);
            System.arraycopy(this.engine.params.T_tate3, this.engine.params.NWORDS_FIELD * (i3 + 3), jArr7, 0, this.engine.params.NWORDS_FIELD);
            System.arraycopy(this.engine.params.T_tate3, this.engine.params.NWORDS_FIELD * (i3 + 4), jArr9, 0, this.engine.params.NWORDS_FIELD);
            char c = 0;
            System.arraycopy(this.engine.params.T_tate3, this.engine.params.NWORDS_FIELD * (i3 + 5), jArr10, 0, this.engine.params.NWORDS_FIELD);
            int i5 = 0;
            int i6 = 2;
            while (i5 < i6) {
                this.engine.fpx.fpmul_mont(pointProjFullArr[i5].f27240X[c], jArr4, jArr14[c]);
                this.engine.fpx.fpmul_mont(pointProjFullArr[i5].f27240X[1], jArr4, jArr14[1]);
                this.engine.fpx.fpmul_mont(pointProjFullArr[i5].f27240X[0], jArr5, jArr15[0]);
                this.engine.fpx.fpmul_mont(pointProjFullArr[i5].f27240X[1], jArr5, jArr15[1]);
                this.engine.fpx.fpaddPRIME(jArr11[i5][0], jArr9, jArr16[0]);
                long[] jArr23 = jArr5;
                this.engine.fpx.fpcopy(jArr11[i5][1], 0, jArr16[1]);
                this.engine.fpx.fpmul_mont(pointProjFullArr[i5].f27240X[0], jArr10, jArr22[0]);
                this.engine.fpx.fpmul_mont(pointProjFullArr[i5].f27240X[1], jArr10, jArr22[1]);
                long[][] jArr24 = jArr18;
                this.engine.fpx.fp2sub(jArr14, pointProjFullArr[i5].f27241Y, jArr24);
                this.engine.fpx.fpaddPRIME(jArr24[0], jArr6, jArr24[0]);
                long[][] jArr25 = jArr17;
                this.engine.fpx.fp2sub(jArr15, pointProjFullArr[i5].f27241Y, jArr25);
                long[] jArr26 = jArr9;
                this.engine.fpx.fpaddPRIME(jArr25[0], jArr7, jArr25[0]);
                long[][] jArr27 = jArr19;
                this.engine.fpx.fp2mul_mont(jArr24, jArr25, jArr27);
                long[] jArr28 = jArr10;
                long[][] jArr29 = jArr20;
                long[][] jArr30 = jArr22;
                this.engine.fpx.fp2sub(jArr16, jArr30, jArr29);
                this.engine.fpx.fp2_conj(jArr29, jArr29);
                this.engine.fpx.fp2mul_mont(jArr27, jArr29, jArr27);
                long[] jArr31 = jArr4;
                long[][] jArr32 = jArr21;
                this.engine.fpx.fp2sqr_mont(jArr[i5], jArr32);
                this.engine.fpx.fp2mul_mont(jArr[i5], jArr32, jArr[i5]);
                this.engine.fpx.fp2mul_mont(jArr[i5], jArr27, jArr[i5]);
                this.engine.fpx.fpsubPRIME(jArr14[1], pointProjFullArr[i5].f27241Y[0], jArr24[0]);
                this.engine.fpx.fpaddPRIME(jArr14[0], pointProjFullArr[i5].f27241Y[1], jArr24[1]);
                this.engine.fpx.fpnegPRIME(jArr24[1]);
                this.engine.fpx.fpaddPRIME(jArr24[1], jArr6, jArr24[1]);
                this.engine.fpx.fpsubPRIME(jArr15[1], pointProjFullArr[i5].f27241Y[0], jArr25[0]);
                this.engine.fpx.fpaddPRIME(jArr15[0], pointProjFullArr[i5].f27241Y[1], jArr25[1]);
                this.engine.fpx.fpnegPRIME(jArr25[1]);
                this.engine.fpx.fpaddPRIME(jArr25[1], jArr7, jArr25[1]);
                this.engine.fpx.fp2mul_mont(jArr24, jArr25, jArr27);
                this.engine.fpx.fp2add(jArr16, jArr30, jArr29);
                this.engine.fpx.fp2_conj(jArr29, jArr29);
                this.engine.fpx.fp2mul_mont(jArr27, jArr29, jArr27);
                int i7 = i5 + 2;
                this.engine.fpx.fp2sqr_mont(jArr[i7], jArr32);
                this.engine.fpx.fp2mul_mont(jArr[i7], jArr32, jArr[i7]);
                this.engine.fpx.fp2mul_mont(jArr[i7], jArr27, jArr[i7]);
                i5++;
                jArr22 = jArr30;
                jArr9 = jArr26;
                jArr11 = jArr11;
                jArr4 = jArr31;
                i6 = 2;
                c = 0;
                jArr19 = jArr27;
                jArr20 = jArr29;
                jArr17 = jArr25;
                jArr10 = jArr28;
                jArr5 = jArr23;
                jArr18 = jArr24;
                jArr21 = jArr32;
                jArr14 = jArr14;
            }
            i2 = i4 + 1;
            jArr19 = jArr19;
            jArr20 = jArr20;
            jArr17 = jArr17;
            jArr10 = jArr10;
            jArr5 = jArr5;
            jArr18 = jArr18;
            jArr21 = jArr21;
            jArr14 = jArr14;
        }
        long[][] jArr33 = jArr18;
        long[][] jArr34 = jArr19;
        long[][] jArr35 = jArr20;
        long[] jArr36 = jArr4;
        long[][] jArr37 = jArr21;
        long[][] jArr38 = jArr14;
        for (int i8 = 0; i8 < 2; i8++) {
            System.arraycopy(this.engine.params.T_tate3, this.engine.params.NWORDS_FIELD * (((this.engine.params.OBOB_EXPON - 1) * 6) + 0), jArr2, 0, this.engine.params.NWORDS_FIELD);
            System.arraycopy(this.engine.params.T_tate3, this.engine.params.NWORDS_FIELD * (((this.engine.params.OBOB_EXPON - 1) * 6) + 1), jArr3, 0, this.engine.params.NWORDS_FIELD);
            System.arraycopy(this.engine.params.T_tate3, this.engine.params.NWORDS_FIELD * (((this.engine.params.OBOB_EXPON - 1) * 6) + 2), jArr36, 0, this.engine.params.NWORDS_FIELD);
            System.arraycopy(this.engine.params.T_tate3, this.engine.params.NWORDS_FIELD * (((this.engine.params.OBOB_EXPON - 1) * 6) + 3), jArr8, 0, this.engine.params.NWORDS_FIELD);
            this.engine.fpx.fpsubPRIME(pointProjFullArr[i8].f27240X[0], jArr2, jArr38[0]);
            this.engine.fpx.fpcopy(pointProjFullArr[i8].f27240X[1], 0, jArr38[1]);
            this.engine.fpx.fpmul_mont(jArr36, jArr38[0], jArr33[0]);
            this.engine.fpx.fpmul_mont(jArr36, jArr38[1], jArr33[1]);
            this.engine.fpx.fp2sub(jArr33, pointProjFullArr[i8].f27241Y, jArr15);
            this.engine.fpx.fpaddPRIME(jArr15[0], jArr3, jArr15[0]);
            this.engine.fpx.fp2mul_mont(jArr38, jArr15, jArr34);
            this.engine.fpx.fpsubPRIME(pointProjFullArr[i8].f27240X[0], jArr8, jArr35[0]);
            this.engine.fpx.fpcopy(pointProjFullArr[i8].f27240X[1], 0, jArr35[1]);
            this.engine.fpx.fpnegPRIME(jArr35[1]);
            this.engine.fpx.fp2mul_mont(jArr34, jArr35, jArr34);
            this.engine.fpx.fp2sqr_mont(jArr[i8], jArr37);
            this.engine.fpx.fp2mul_mont(jArr[i8], jArr37, jArr[i8]);
            this.engine.fpx.fp2mul_mont(jArr[i8], jArr34, jArr[i8]);
            this.engine.fpx.fpaddPRIME(pointProjFullArr[i8].f27240X[0], jArr2, jArr38[0]);
            this.engine.fpx.fpmul_mont(jArr36, jArr38[0], jArr33[0]);
            this.engine.fpx.fpsubPRIME(pointProjFullArr[i8].f27241Y[0], jArr33[1], jArr15[0]);
            this.engine.fpx.fpaddPRIME(pointProjFullArr[i8].f27241Y[1], jArr33[0], jArr15[1]);
            this.engine.fpx.fpsubPRIME(jArr15[1], jArr3, jArr15[1]);
            this.engine.fpx.fp2mul_mont(jArr38, jArr15, jArr34);
            this.engine.fpx.fpaddPRIME(pointProjFullArr[i8].f27240X[0], jArr8, jArr35[0]);
            this.engine.fpx.fp2mul_mont(jArr34, jArr35, jArr34);
            int i9 = i8 + 2;
            this.engine.fpx.fp2sqr_mont(jArr[i9], jArr37);
            this.engine.fpx.fp2mul_mont(jArr[i9], jArr37, jArr[i9]);
            this.engine.fpx.fp2mul_mont(jArr[i9], jArr34, jArr[i9]);
        }
        this.engine.fpx.mont_n_way_inv(jArr, 4, jArr12);
        for (int i10 = 0; i10 < 4; i10++) {
            final_exponentiation_3_torsion(jArr[i10], jArr12[i10], jArr[i10]);
        }
    }

    private void final_exponentiation_2_torsion(long[][] jArr, long[][] jArr2, long[][] jArr3) {
        long[] jArr4 = new long[this.engine.params.NWORDS_FIELD];
        long[][] jArr5 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        this.engine.fpx.fpcopy(this.engine.params.Montgomery_one, 0, jArr4);
        this.engine.fpx.fp2_conj(jArr, jArr5);
        this.engine.fpx.fp2mul_mont(jArr5, jArr2, jArr5);
        for (int i = 0; i < this.engine.params.OBOB_EXPON; i++) {
            this.engine.fpx.cube_Fp2_cycl(jArr5, jArr4);
        }
        this.engine.fpx.fp2copy(jArr5, jArr3);
    }

    private void final_exponentiation_3_torsion(long[][] jArr, long[][] jArr2, long[][] jArr3) {
        long[] jArr4 = new long[this.engine.params.NWORDS_FIELD];
        long[][] jArr5 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        this.engine.fpx.fpcopy(this.engine.params.Montgomery_one, 0, jArr4);
        this.engine.fpx.fp2_conj(jArr, jArr5);
        this.engine.fpx.fp2mul_mont(jArr5, jArr2, jArr5);
        for (int i = 0; i < this.engine.params.OALICE_BITS; i++) {
            this.engine.fpx.sqr_Fp2_cycl(jArr5, jArr4);
        }
        this.engine.fpx.fp2copy(jArr5, jArr3);
    }

    private void from_base(int[] iArr, long[] jArr, int i, int i2) {
        int i3;
        long[] jArr2 = new long[this.engine.params.NWORDS_ORDER];
        long[] jArr3 = new long[this.engine.params.NWORDS_ORDER];
        long[] jArr4 = new long[this.engine.params.NWORDS_ORDER];
        jArr2[0] = i2;
        if (iArr[i - 1] < 0) {
            jArr3[0] = (-iArr[i3]) * jArr2[0];
            if ((i2 & 1) == 0) {
                this.engine.fpx.Montgomery_neg(jArr3, this.engine.params.Alice_order);
                this.engine.fpx.copy_words(jArr3, jArr, this.engine.params.NWORDS_ORDER);
            } else {
                this.engine.fpx.mp_sub(this.engine.params.Bob_order, jArr3, jArr, this.engine.params.NWORDS_ORDER);
            }
        } else {
            jArr[0] = iArr[i3] * jArr2[0];
        }
        for (int i4 = i - 2; i4 >= 1; i4--) {
            Arrays.fill(jArr3, 0L);
            if (iArr[i4] < 0) {
                jArr3[0] = -iArr[i4];
                if ((i2 & 1) == 0) {
                    this.engine.fpx.Montgomery_neg(jArr3, this.engine.params.Alice_order);
                } else {
                    this.engine.fpx.mp_sub(this.engine.params.Bob_order, jArr3, jArr3, this.engine.params.NWORDS_ORDER);
                }
            } else {
                jArr3[0] = iArr[i4];
            }
            this.engine.fpx.mp_add(jArr, jArr3, jArr, this.engine.params.NWORDS_ORDER);
            int i5 = i2 & 1;
            if (i5 != 0 && !this.engine.fpx.is_orderelm_lt(jArr, this.engine.params.Bob_order)) {
                this.engine.fpx.mp_sub(jArr, this.engine.params.Bob_order, jArr, this.engine.params.NWORDS_ORDER);
            }
            if (i5 == 0) {
                for (int i6 = i2; i6 > 1; i6 /= 2) {
                    this.engine.fpx.mp_add(jArr, jArr, jArr, this.engine.params.NWORDS_ORDER);
                }
            } else {
                for (int i7 = i2; i7 > 1; i7 /= 3) {
                    Arrays.fill(jArr4, 0L);
                    this.engine.fpx.mp_add(jArr, jArr, jArr4, this.engine.params.NWORDS_ORDER);
                    if (!this.engine.fpx.is_orderelm_lt(jArr4, this.engine.params.Bob_order)) {
                        this.engine.fpx.mp_sub(jArr4, this.engine.params.Bob_order, jArr4, this.engine.params.NWORDS_ORDER);
                    }
                    this.engine.fpx.mp_add(jArr, jArr4, jArr, this.engine.params.NWORDS_ORDER);
                    if (!this.engine.fpx.is_orderelm_lt(jArr, this.engine.params.Bob_order)) {
                        this.engine.fpx.mp_sub(jArr, this.engine.params.Bob_order, jArr, this.engine.params.NWORDS_ORDER);
                    }
                }
            }
        }
        Arrays.fill(jArr3, 0L);
        if (iArr[0] < 0) {
            jArr3[0] = -iArr[0];
            if ((i2 & 1) == 0) {
                this.engine.fpx.Montgomery_neg(jArr3, this.engine.params.Alice_order);
            } else {
                this.engine.fpx.mp_sub(this.engine.params.Bob_order, jArr3, jArr3, this.engine.params.NWORDS_ORDER);
            }
        } else {
            jArr3[0] = iArr[0];
        }
        this.engine.fpx.mp_add(jArr, jArr3, jArr, this.engine.params.NWORDS_ORDER);
        if ((i2 & 1) == 0 || this.engine.fpx.is_orderelm_lt(jArr, this.engine.params.Bob_order)) {
            return;
        }
        this.engine.fpx.mp_sub(jArr, this.engine.params.Bob_order, jArr, this.engine.params.NWORDS_ORDER);
    }

    protected void BiQuad_affine(long[][] jArr, long[][] jArr2, long[][] jArr3, PointProj pointProj) {
        long[][] jArr4 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr5 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr6 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr7 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr8 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr9 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        this.engine.fpx.fp2add(jArr, jArr, jArr4);
        this.engine.fpx.fp2add(jArr4, jArr4, jArr4);
        this.engine.fpx.fp2sub(jArr2, jArr3, jArr5);
        this.engine.fpx.fp2sqr_mont(jArr5, jArr5);
        this.engine.fpx.fp2mul_mont(jArr2, jArr3, jArr7);
        this.engine.fpx.fpsubPRIME(jArr7[0], this.engine.params.Montgomery_one, jArr7[0]);
        this.engine.fpx.fp2sqr_mont(jArr7, jArr7);
        this.engine.fpx.fpsubPRIME(jArr2[0], this.engine.params.Montgomery_one, jArr6[0]);
        this.engine.fpx.fpcopy(jArr2[1], 0, jArr6[1]);
        this.engine.fpx.fp2sqr_mont(jArr6, jArr6);
        this.engine.fpx.fp2mul_mont(jArr4, jArr2, jArr8);
        this.engine.fpx.fp2add(jArr6, jArr8, jArr6);
        this.engine.fpx.fp2mul_mont(jArr3, jArr6, jArr6);
        this.engine.fpx.fpsubPRIME(jArr3[0], this.engine.params.Montgomery_one, jArr8[0]);
        this.engine.fpx.fpcopy(jArr3[1], 0, jArr8[1]);
        this.engine.fpx.fp2sqr_mont(jArr8, jArr8);
        this.engine.fpx.fp2mul_mont(jArr4, jArr3, jArr9);
        this.engine.fpx.fp2add(jArr8, jArr9, jArr8);
        this.engine.fpx.fp2mul_mont(jArr2, jArr8, jArr8);
        this.engine.fpx.fp2add(jArr6, jArr8, jArr6);
        this.engine.fpx.fp2add(jArr6, jArr6, jArr6);
        this.engine.fpx.fp2sqr_mont(jArr6, jArr8);
        this.engine.fpx.fp2mul_mont(jArr5, jArr7, jArr9);
        this.engine.fpx.fp2add(jArr9, jArr9, jArr9);
        this.engine.fpx.fp2add(jArr9, jArr9, jArr9);
        this.engine.fpx.fp2sub(jArr8, jArr9, jArr8);
        this.engine.fpx.sqrt_Fp2(jArr8, jArr8);
        make_positive(jArr8);
        this.engine.fpx.fp2add(jArr6, jArr8, pointProj.f27238X);
        this.engine.fpx.fp2add(jArr5, jArr5, pointProj.f27239Z);
    }

    protected void BuildEntangledXonly(long[][] jArr, PointProj[] pointProjArr, byte[] bArr, byte[] bArr2) {
        long[][] jArr2;
        Fpx fpx;
        long[] jArr3;
        long[] jArr4;
        long[] jArr5 = new long[this.engine.params.NWORDS_FIELD];
        long[][] jArr6 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr7 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        if (this.engine.fpx.is_sqr_fp2(jArr, jArr5)) {
            jArr2 = this.engine.params.table_v_qnr;
            bArr[0] = 1;
        } else {
            jArr2 = this.engine.params.table_v_qr;
            bArr[0] = 0;
        }
        bArr2[0] = 0;
        int i = 0;
        do {
            this.engine.fpx.fp2mul_mont(jArr, jArr2, i, pointProjArr[0].f27238X);
            i += 2;
            this.engine.fpx.fp2neg(pointProjArr[0].f27238X);
            this.engine.fpx.fp2add(pointProjArr[0].f27238X, jArr, jArr7);
            this.engine.fpx.fp2mul_mont(pointProjArr[0].f27238X, jArr7, jArr7);
            this.engine.fpx.fpaddPRIME(jArr7[0], this.engine.params.Montgomery_one, jArr7[0]);
            this.engine.fpx.fp2mul_mont(pointProjArr[0].f27238X, jArr7, jArr7);
            bArr2[0] = (byte) (bArr2[0] + 1);
        } while (!this.engine.fpx.is_sqr_fp2(jArr7, jArr5));
        bArr2[0] = (byte) (bArr2[0] - 1);
        if (bArr[0] == 1) {
            fpx = this.engine.fpx;
            jArr3 = this.engine.params.table_r_qnr[bArr2[0]];
            jArr4 = jArr6[0];
        } else {
            fpx = this.engine.fpx;
            jArr3 = this.engine.params.table_r_qr[bArr2[0]];
            jArr4 = jArr6[0];
        }
        fpx.fpcopy(jArr3, 0, jArr4);
        this.engine.fpx.fp2add(pointProjArr[0].f27238X, jArr, pointProjArr[1].f27238X);
        this.engine.fpx.fp2neg(pointProjArr[1].f27238X);
        this.engine.fpx.fp2sub(pointProjArr[0].f27238X, pointProjArr[1].f27238X, pointProjArr[2].f27239Z);
        this.engine.fpx.fp2sqr_mont(pointProjArr[2].f27239Z, pointProjArr[2].f27239Z);
        this.engine.fpx.fpcopy(jArr6[0], 0, jArr6[1]);
        this.engine.fpx.fpaddPRIME(this.engine.params.Montgomery_one, jArr6[0], jArr6[0]);
        this.engine.fpx.fp2sqr_mont(jArr6, jArr6);
        this.engine.fpx.fp2mul_mont(jArr7, jArr6, pointProjArr[2].f27238X);
    }

    protected void BuildEntangledXonly_Decomp(long[][] jArr, PointProj[] pointProjArr, int i, int i2) {
        Fpx fpx;
        long[] jArr2;
        long[] jArr3;
        long[][] jArr4 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr5 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr6 = i == 1 ? this.engine.params.table_v_qnr : this.engine.params.table_v_qr;
        if (i2 >= this.engine.params.TABLE_V_LEN / 2) {
            i2 = 0;
        }
        this.engine.fpx.fp2mul_mont(jArr, jArr6, i2 * 2, pointProjArr[0].f27238X);
        this.engine.fpx.fp2neg(pointProjArr[0].f27238X);
        this.engine.fpx.fp2add(pointProjArr[0].f27238X, jArr, jArr5);
        this.engine.fpx.fp2mul_mont(pointProjArr[0].f27238X, jArr5, jArr5);
        this.engine.fpx.fpaddPRIME(jArr5[0], this.engine.params.Montgomery_one, jArr5[0]);
        this.engine.fpx.fp2mul_mont(pointProjArr[0].f27238X, jArr5, jArr5);
        if (i == 1) {
            fpx = this.engine.fpx;
            jArr2 = this.engine.params.table_r_qnr[i2];
            jArr3 = jArr4[0];
        } else {
            fpx = this.engine.fpx;
            jArr2 = this.engine.params.table_r_qr[i2];
            jArr3 = jArr4[0];
        }
        fpx.fpcopy(jArr2, 0, jArr3);
        this.engine.fpx.fp2add(pointProjArr[0].f27238X, jArr, pointProjArr[1].f27238X);
        this.engine.fpx.fp2neg(pointProjArr[1].f27238X);
        this.engine.fpx.fp2sub(pointProjArr[0].f27238X, pointProjArr[1].f27238X, pointProjArr[2].f27239Z);
        this.engine.fpx.fp2sqr_mont(pointProjArr[2].f27239Z, pointProjArr[2].f27239Z);
        this.engine.fpx.fpcopy(jArr4[0], 0, jArr4[1]);
        this.engine.fpx.fpaddPRIME(this.engine.params.Montgomery_one, jArr4[0], jArr4[0]);
        this.engine.fpx.fp2sqr_mont(jArr4, jArr4);
        this.engine.fpx.fp2mul_mont(jArr5, jArr4, pointProjArr[2].f27238X);
    }

    protected void BuildOrdinary2nBasis_dual(long[][] jArr, long[][][][] jArr2, PointProjFull[] pointProjFullArr, byte[] bArr, byte[] bArr2) {
        long[] jArr3 = new long[this.engine.params.NWORDS_FIELD];
        long[][] jArr4 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        PointProj[] pointProjArr = {new PointProj(this.engine.params.NWORDS_FIELD), new PointProj(this.engine.params.NWORDS_FIELD), new PointProj(this.engine.params.NWORDS_FIELD)};
        BuildEntangledXonly(jArr, pointProjArr, bArr, bArr2);
        this.engine.fpx.fpcopy(this.engine.params.Montgomery_one, 0, pointProjArr[0].f27239Z[0]);
        this.engine.fpx.fpcopy(this.engine.params.Montgomery_one, 0, pointProjArr[1].f27239Z[0]);
        for (int i = 0; i < this.engine.params.MAX_Bob; i++) {
            this.engine.isogeny.eval_3_isog(pointProjArr[0], jArr2[(this.engine.params.MAX_Bob - 1) - i]);
            this.engine.isogeny.eval_3_isog(pointProjArr[1], jArr2[(this.engine.params.MAX_Bob - 1) - i]);
            this.engine.isogeny.eval_3_isog(pointProjArr[2], jArr2[(this.engine.params.MAX_Bob - 1) - i]);
        }
        this.engine.fpx.fpcopy(this.engine.params.Montgomery_one, 0, jArr4[0]);
        this.engine.fpx.fpaddPRIME(jArr4[0], jArr4[0], jArr3);
        this.engine.fpx.fpaddPRIME(jArr3, jArr3, jArr4[0]);
        this.engine.fpx.fpaddPRIME(jArr4[0], jArr3, jArr4[0]);
        this.engine.isogeny.CompleteMPoint(jArr4, pointProjArr[0], pointProjFullArr[0]);
        RecoverY(jArr4, pointProjArr, pointProjFullArr);
    }

    protected void BuildOrdinary3nBasis_Decomp_dual(long[][] jArr, PointProj[] pointProjArr, int[] iArr, int[] iArr2, int i) {
        byte[] bArr = {(byte) (iArr2[i] & 1), (byte) ((iArr2[i] >>> 1) & 1)};
        iArr[0] = iArr[0] - 1;
        Elligator2(jArr, iArr, 0, pointProjArr[0].f27238X, bArr, 0, 1);
        iArr[1] = iArr[1] - 1;
        Elligator2(jArr, iArr, 1, pointProjArr[1].f27238X, bArr, 1, 1);
        BiQuad_affine(jArr, pointProjArr[0].f27238X, pointProjArr[1].f27238X, pointProjArr[2]);
    }

    protected void BuildOrdinary3nBasis_dual(long[][] jArr, long[][][][] jArr2, PointProjFull[] pointProjFullArr, int[] iArr, int[] iArr2, int i) {
        PointProj pointProj = new PointProj(this.engine.params.NWORDS_FIELD);
        long[][][] jArr3 = (long[][][]) Array.newInstance(long.class, 2, 2, this.engine.params.NWORDS_FIELD);
        byte[] bArr = new byte[1];
        byte[] bArr2 = new byte[1];
        FirstPoint3n(jArr, jArr2, jArr3[0], pointProjFullArr[0], iArr, bArr, bArr2);
        iArr2[i] = bArr2[0];
        iArr[1] = iArr[0];
        SecondPoint3n(jArr, jArr2, jArr3[1], pointProjFullArr[1], iArr, bArr, bArr2);
        iArr2[i] = iArr2[i] | (bArr2[0] << 1);
        BiQuad_affine(jArr, jArr3[0], jArr3[1], pointProj);
        eval_full_dual_4_isog(jArr2, pointProj);
        makeDiff(pointProjFullArr[0], pointProjFullArr[1], pointProj);
    }

    protected void Compress_PKA_dual(long[] jArr, long[] jArr2, long[] jArr3, long[] jArr4, long[][] jArr5, int[] iArr, byte[] bArr) {
        char c;
        long[][] jArr6;
        char c2;
        long[] jArr7 = new long[this.engine.params.NWORDS_ORDER];
        long[] jArr8 = new long[this.engine.params.NWORDS_ORDER];
        long[][] jArr9 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        this.engine.fpx.fp2add(jArr5, jArr5, jArr9);
        this.engine.fpx.fp2add(jArr9, jArr9, jArr9);
        this.engine.fpx.fpsubPRIME(jArr9[0], this.engine.params.Montgomery_one, jArr9[0]);
        this.engine.fpx.fpsubPRIME(jArr9[0], this.engine.params.Montgomery_one, jArr9[0]);
        int mod3 = this.engine.fpx.mod3(jArr3);
        this.engine.fpx.to_Montgomery_mod_order(jArr2, jArr2, this.engine.params.Bob_order, this.engine.params.Montgomery_RB2, this.engine.params.Montgomery_RB1);
        this.engine.fpx.to_Montgomery_mod_order(jArr4, jArr4, this.engine.params.Bob_order, this.engine.params.Montgomery_RB2, this.engine.params.Montgomery_RB1);
        this.engine.fpx.to_Montgomery_mod_order(jArr, jArr, this.engine.params.Bob_order, this.engine.params.Montgomery_RB2, this.engine.params.Montgomery_RB1);
        this.engine.fpx.to_Montgomery_mod_order(jArr3, jArr3, this.engine.params.Bob_order, this.engine.params.Montgomery_RB2, this.engine.params.Montgomery_RB1);
        if (mod3 != 0) {
            this.engine.fpx.Montgomery_inversion_mod_order_bingcd(jArr3, jArr8, this.engine.params.Bob_order, this.engine.params.Montgomery_RB2, this.engine.params.Montgomery_RB1);
            this.engine.fpx.Montgomery_neg(jArr, this.engine.params.Bob_order);
            this.engine.fpx.Montgomery_multiply_mod_order(jArr, jArr8, jArr7, this.engine.params.Bob_order, this.engine.params.Montgomery_RB2);
            this.engine.fpx.from_Montgomery_mod_order(jArr7, jArr7, this.engine.params.Bob_order, this.engine.params.Montgomery_RB2);
            this.engine.fpx.encode_to_bytes(jArr7, bArr, 0, this.engine.params.ORDER_B_ENCODED_BYTES);
            this.engine.fpx.Montgomery_neg(jArr4, this.engine.params.Bob_order);
            this.engine.fpx.Montgomery_multiply_mod_order(jArr4, jArr8, jArr7, this.engine.params.Bob_order, this.engine.params.Montgomery_RB2);
            this.engine.fpx.from_Montgomery_mod_order(jArr7, jArr7, this.engine.params.Bob_order, this.engine.params.Montgomery_RB2);
            this.engine.fpx.encode_to_bytes(jArr7, bArr, this.engine.params.ORDER_B_ENCODED_BYTES, this.engine.params.ORDER_B_ENCODED_BYTES);
            this.engine.fpx.Montgomery_multiply_mod_order(jArr2, jArr8, jArr7, this.engine.params.Bob_order, this.engine.params.Montgomery_RB2);
            this.engine.fpx.from_Montgomery_mod_order(jArr7, jArr7, this.engine.params.Bob_order, this.engine.params.Montgomery_RB2);
            this.engine.fpx.encode_to_bytes(jArr7, bArr, this.engine.params.ORDER_B_ENCODED_BYTES * 2, this.engine.params.ORDER_B_ENCODED_BYTES);
            bArr[(this.engine.params.ORDER_B_ENCODED_BYTES * 3) + this.engine.params.FP2_ENCODED_BYTES] = 0;
            c = 2;
            c2 = 0;
            jArr6 = jArr9;
        } else {
            c = 2;
            this.engine.fpx.Montgomery_inversion_mod_order_bingcd(jArr, jArr8, this.engine.params.Bob_order, this.engine.params.Montgomery_RB2, this.engine.params.Montgomery_RB1);
            this.engine.fpx.Montgomery_neg(jArr3, this.engine.params.Bob_order);
            jArr6 = jArr9;
            c2 = 0;
            this.engine.fpx.Montgomery_multiply_mod_order(jArr3, jArr8, jArr7, this.engine.params.Bob_order, this.engine.params.Montgomery_RB2);
            this.engine.fpx.from_Montgomery_mod_order(jArr7, jArr7, this.engine.params.Bob_order, this.engine.params.Montgomery_RB2);
            this.engine.fpx.encode_to_bytes(jArr7, bArr, 0, this.engine.params.ORDER_B_ENCODED_BYTES);
            this.engine.fpx.Montgomery_multiply_mod_order(jArr4, jArr8, jArr7, this.engine.params.Bob_order, this.engine.params.Montgomery_RB2);
            this.engine.fpx.from_Montgomery_mod_order(jArr7, jArr7, this.engine.params.Bob_order, this.engine.params.Montgomery_RB2);
            this.engine.fpx.encode_to_bytes(jArr7, bArr, this.engine.params.ORDER_B_ENCODED_BYTES, this.engine.params.ORDER_B_ENCODED_BYTES);
            this.engine.fpx.Montgomery_neg(jArr2, this.engine.params.Bob_order);
            this.engine.fpx.Montgomery_multiply_mod_order(jArr2, jArr8, jArr7, this.engine.params.Bob_order, this.engine.params.Montgomery_RB2);
            this.engine.fpx.from_Montgomery_mod_order(jArr7, jArr7, this.engine.params.Bob_order, this.engine.params.Montgomery_RB2);
            this.engine.fpx.encode_to_bytes(jArr7, bArr, this.engine.params.ORDER_B_ENCODED_BYTES * 2, this.engine.params.ORDER_B_ENCODED_BYTES);
            bArr[(this.engine.params.ORDER_B_ENCODED_BYTES * 3) + this.engine.params.FP2_ENCODED_BYTES] = Byte.MIN_VALUE;
        }
        this.engine.fpx.fp2_encode(jArr6, bArr, this.engine.params.ORDER_B_ENCODED_BYTES * 3);
        int i = (this.engine.params.ORDER_B_ENCODED_BYTES * 3) + this.engine.params.FP2_ENCODED_BYTES;
        bArr[i] = (byte) (bArr[i] | ((byte) iArr[c2]));
        bArr[(this.engine.params.ORDER_B_ENCODED_BYTES * 3) + this.engine.params.FP2_ENCODED_BYTES + 1] = (byte) iArr[1];
        bArr[(this.engine.params.ORDER_B_ENCODED_BYTES * 3) + this.engine.params.FP2_ENCODED_BYTES + 2] = (byte) iArr[c];
    }

    protected void Compress_PKB_dual(long[] jArr, long[] jArr2, long[] jArr3, long[] jArr4, long[][] jArr5, byte[] bArr, byte[] bArr2, byte[] bArr3) {
        long[] jArr6 = new long[this.engine.params.NWORDS_ORDER * 2];
        long[] jArr7 = new long[this.engine.params.NWORDS_ORDER];
        if ((jArr3[0] & 1) == 1) {
            this.engine.fpx.inv_mod_orderA(jArr3, jArr7);
            this.engine.fpx.Montgomery_neg(jArr, this.engine.params.Alice_order);
            this.engine.fpx.multiply(jArr, jArr7, jArr6, this.engine.params.NWORDS_ORDER);
            this.engine.fpx.encode_to_bytes(jArr6, bArr3, 0, this.engine.params.ORDER_A_ENCODED_BYTES);
            int i = this.engine.params.ORDER_A_ENCODED_BYTES - 1;
            bArr3[i] = (byte) (bArr3[i] & this.engine.params.MASK_ALICE);
            this.engine.fpx.Montgomery_neg(jArr4, this.engine.params.Alice_order);
            this.engine.fpx.multiply(jArr4, jArr7, jArr6, this.engine.params.NWORDS_ORDER);
            this.engine.fpx.encode_to_bytes(jArr6, bArr3, this.engine.params.ORDER_A_ENCODED_BYTES, this.engine.params.ORDER_A_ENCODED_BYTES);
            int i2 = (this.engine.params.ORDER_A_ENCODED_BYTES * 2) - 1;
            bArr3[i2] = (byte) (bArr3[i2] & this.engine.params.MASK_ALICE);
            this.engine.fpx.multiply(jArr2, jArr7, jArr6, this.engine.params.NWORDS_ORDER);
            this.engine.fpx.encode_to_bytes(jArr6, bArr3, this.engine.params.ORDER_A_ENCODED_BYTES * 2, this.engine.params.ORDER_A_ENCODED_BYTES);
            int i3 = (this.engine.params.ORDER_A_ENCODED_BYTES * 3) - 1;
            bArr3[i3] = (byte) (bArr3[i3] & this.engine.params.MASK_ALICE);
            bArr3[(this.engine.params.ORDER_A_ENCODED_BYTES * 3) + this.engine.params.FP2_ENCODED_BYTES] = 0;
        } else {
            this.engine.fpx.inv_mod_orderA(jArr, jArr7);
            this.engine.fpx.Montgomery_neg(jArr3, this.engine.params.Alice_order);
            this.engine.fpx.multiply(jArr3, jArr7, jArr6, this.engine.params.NWORDS_ORDER);
            this.engine.fpx.encode_to_bytes(jArr6, bArr3, 0, this.engine.params.ORDER_A_ENCODED_BYTES);
            int i4 = this.engine.params.ORDER_A_ENCODED_BYTES - 1;
            bArr3[i4] = (byte) (bArr3[i4] & this.engine.params.MASK_ALICE);
            this.engine.fpx.multiply(jArr4, jArr7, jArr6, this.engine.params.NWORDS_ORDER);
            this.engine.fpx.encode_to_bytes(jArr6, bArr3, this.engine.params.ORDER_A_ENCODED_BYTES, this.engine.params.ORDER_A_ENCODED_BYTES);
            int i5 = (this.engine.params.ORDER_A_ENCODED_BYTES * 2) - 1;
            bArr3[i5] = (byte) (bArr3[i5] & this.engine.params.MASK_ALICE);
            this.engine.fpx.Montgomery_neg(jArr2, this.engine.params.Alice_order);
            this.engine.fpx.multiply(jArr2, jArr7, jArr6, this.engine.params.NWORDS_ORDER);
            this.engine.fpx.encode_to_bytes(jArr6, bArr3, this.engine.params.ORDER_A_ENCODED_BYTES * 2, this.engine.params.ORDER_A_ENCODED_BYTES);
            int i6 = (this.engine.params.ORDER_A_ENCODED_BYTES * 3) - 1;
            bArr3[i6] = (byte) (bArr3[i6] & this.engine.params.MASK_ALICE);
            bArr3[(this.engine.params.ORDER_A_ENCODED_BYTES * 3) + this.engine.params.FP2_ENCODED_BYTES] = Byte.MIN_VALUE;
        }
        this.engine.fpx.fp2_encode(jArr5, bArr3, this.engine.params.ORDER_A_ENCODED_BYTES * 3);
        int i7 = (this.engine.params.ORDER_A_ENCODED_BYTES * 3) + this.engine.params.FP2_ENCODED_BYTES;
        bArr3[i7] = (byte) (bArr3[i7] | bArr[0]);
        bArr3[(this.engine.params.ORDER_A_ENCODED_BYTES * 3) + this.engine.params.FP2_ENCODED_BYTES + 1] = bArr2[0];
        bArr3[(this.engine.params.ORDER_A_ENCODED_BYTES * 3) + this.engine.params.FP2_ENCODED_BYTES + 2] = 0;
    }

    protected void Compress_PKB_dual_extended(long[] jArr, long[] jArr2, long[] jArr3, long[] jArr4, long[][] jArr5, byte[] bArr, byte[] bArr2, byte[] bArr3) {
        long[] jArr6 = new long[this.engine.params.NWORDS_ORDER * 2];
        long[] jArr7 = new long[this.engine.params.NWORDS_ORDER * 2];
        long[] jArr8 = new long[this.engine.params.NWORDS_ORDER * 2];
        long j = (-1) >>> (this.engine.params.MAXBITS_ORDER - this.engine.params.OALICE_BITS);
        this.engine.fpx.multiply(jArr2, jArr3, jArr6, this.engine.params.NWORDS_ORDER);
        this.engine.fpx.multiply(jArr4, jArr, jArr7, this.engine.params.NWORDS_ORDER);
        this.engine.fpx.Montgomery_neg(jArr7, this.engine.params.Alice_order);
        this.engine.fpx.mp_add(jArr6, jArr7, jArr7, this.engine.params.NWORDS_ORDER);
        int i = this.engine.params.NWORDS_ORDER - 1;
        jArr7[i] = jArr7[i] & j;
        this.engine.fpx.inv_mod_orderA(jArr7, jArr8);
        this.engine.fpx.multiply(jArr3, jArr8, jArr6, this.engine.params.NWORDS_ORDER);
        int i2 = this.engine.params.NWORDS_ORDER - 1;
        jArr6[i2] = jArr6[i2] & j;
        this.engine.fpx.encode_to_bytes(jArr6, bArr3, 0, this.engine.params.ORDER_A_ENCODED_BYTES);
        this.engine.fpx.Montgomery_neg(jArr, this.engine.params.Alice_order);
        this.engine.fpx.multiply(jArr, jArr8, jArr6, this.engine.params.NWORDS_ORDER);
        int i3 = this.engine.params.NWORDS_ORDER - 1;
        jArr6[i3] = jArr6[i3] & j;
        this.engine.fpx.encode_to_bytes(jArr6, bArr3, this.engine.params.ORDER_A_ENCODED_BYTES, this.engine.params.ORDER_A_ENCODED_BYTES);
        this.engine.fpx.Montgomery_neg(jArr4, this.engine.params.Alice_order);
        this.engine.fpx.multiply(jArr4, jArr8, jArr6, this.engine.params.NWORDS_ORDER);
        int i4 = this.engine.params.NWORDS_ORDER - 1;
        jArr6[i4] = jArr6[i4] & j;
        this.engine.fpx.encode_to_bytes(jArr6, bArr3, this.engine.params.ORDER_A_ENCODED_BYTES * 2, this.engine.params.ORDER_A_ENCODED_BYTES);
        this.engine.fpx.multiply(jArr2, jArr8, jArr6, this.engine.params.NWORDS_ORDER);
        int i5 = this.engine.params.NWORDS_ORDER - 1;
        jArr6[i5] = jArr6[i5] & j;
        this.engine.fpx.encode_to_bytes(jArr6, bArr3, this.engine.params.ORDER_A_ENCODED_BYTES * 3, this.engine.params.ORDER_A_ENCODED_BYTES);
        this.engine.fpx.fp2_encode(jArr5, bArr3, this.engine.params.ORDER_A_ENCODED_BYTES * 4);
        bArr3[(this.engine.params.ORDER_A_ENCODED_BYTES * 4) + this.engine.params.FP2_ENCODED_BYTES] = bArr[0];
        bArr3[(this.engine.params.ORDER_A_ENCODED_BYTES * 4) + this.engine.params.FP2_ENCODED_BYTES + 1] = bArr2[0];
    }

    protected void Dlogs2_dual(long[][][] jArr, int[] iArr, long[] jArr2, long[] jArr3, long[] jArr4, long[] jArr5) {
        solve_dlog(jArr[0], iArr, jArr2, 2);
        solve_dlog(jArr[2], iArr, jArr3, 2);
        solve_dlog(jArr[1], iArr, jArr4, 2);
        solve_dlog(jArr[3], iArr, jArr5, 2);
        this.engine.fpx.mp_sub(this.engine.params.Alice_order, jArr3, jArr3, this.engine.params.NWORDS_ORDER);
        this.engine.fpx.mp_sub(this.engine.params.Alice_order, jArr5, jArr5, this.engine.params.NWORDS_ORDER);
    }

    protected void Dlogs3_dual(long[][][] jArr, int[] iArr, long[] jArr2, long[] jArr3, long[] jArr4, long[] jArr5) {
        solve_dlog(jArr[0], iArr, jArr2, 3);
        solve_dlog(jArr[2], iArr, jArr3, 3);
        solve_dlog(jArr[1], iArr, jArr4, 3);
        solve_dlog(jArr[3], iArr, jArr5, 3);
        this.engine.fpx.mp_sub(this.engine.params.Bob_order, jArr3, jArr3, this.engine.params.NWORDS_ORDER);
        this.engine.fpx.mp_sub(this.engine.params.Bob_order, jArr5, jArr5, this.engine.params.NWORDS_ORDER);
    }

    protected void Elligator2(long[][] jArr, int[] iArr, int i, long[][] jArr2, byte[] bArr, int i2, int i3) {
        long[] jArr3 = new long[this.engine.params.NWORDS_FIELD];
        long[] jArr4 = new long[this.engine.params.NWORDS_FIELD];
        long[] jArr5 = new long[this.engine.params.NWORDS_FIELD];
        long[] jArr6 = new long[this.engine.params.NWORDS_FIELD];
        long[] jArr7 = new long[this.engine.params.NWORDS_FIELD];
        long[] jArr8 = new long[this.engine.params.NWORDS_FIELD];
        long[][] jArr9 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr10 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        this.engine.fpx.fpcopy(this.engine.params.Montgomery_one, 0, jArr3);
        this.engine.fpx.fp2add(jArr, jArr, jArr9);
        this.engine.fpx.fpsubPRIME(jArr9[0], jArr3, jArr9[0]);
        this.engine.fpx.fp2add(jArr9, jArr9, jArr9);
        this.engine.fpx.fp2mul_mont(jArr9, this.engine.params.v_3_torsion[iArr[i]], jArr2);
        this.engine.fpx.fp2neg(jArr2);
        if (i3 != 0) {
            if (bArr[i2] == 1) {
                this.engine.fpx.fp2neg(jArr2);
                this.engine.fpx.fp2sub(jArr2, jArr9, jArr2);
                return;
            }
            return;
        }
        this.engine.fpx.fp2add(jArr9, jArr2, jArr10);
        this.engine.fpx.fp2mul_mont(jArr10, jArr2, jArr10);
        this.engine.fpx.fpaddPRIME(jArr10[0], jArr3, jArr10[0]);
        this.engine.fpx.fp2mul_mont(jArr2, jArr10, jArr10);
        this.engine.fpx.fpsqr_mont(jArr10[0], jArr4);
        this.engine.fpx.fpsqr_mont(jArr10[1], jArr5);
        this.engine.fpx.fpaddPRIME(jArr4, jArr5, jArr6);
        this.engine.fpx.fpcopy(jArr6, 0, jArr7);
        for (int i4 = 0; i4 < this.engine.params.OALICE_BITS - 2; i4++) {
            this.engine.fpx.fpsqr_mont(jArr7, jArr7);
        }
        for (int i5 = 0; i5 < this.engine.params.OBOB_EXPON; i5++) {
            this.engine.fpx.fpsqr_mont(jArr7, jArr8);
            this.engine.fpx.fpmul_mont(jArr7, jArr8, jArr7);
        }
        this.engine.fpx.fpsqr_mont(jArr7, jArr8);
        this.engine.fpx.fpcorrectionPRIME(jArr8);
        this.engine.fpx.fpcorrectionPRIME(jArr6);
        if (Fpx.subarrayEquals(jArr8, jArr6, this.engine.params.NWORDS_FIELD)) {
            return;
        }
        this.engine.fpx.fp2neg(jArr2);
        this.engine.fpx.fp2sub(jArr2, jArr9, jArr2);
        if (i3 == 0) {
            bArr[i2] = 1;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int EphemeralKeyGeneration_A_extended(byte[] bArr, byte[] bArr2) {
        int[] iArr = new int[3];
        int[] iArr2 = new int[this.engine.params.DLEN_3];
        long[][] jArr = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][][][] jArr2 = (long[][][][]) Array.newInstance(long.class, this.engine.params.MAX_Alice + 1, 5, 2, this.engine.params.NWORDS_FIELD);
        long[][][] jArr3 = (long[][][]) Array.newInstance(long.class, 4, 2, this.engine.params.NWORDS_FIELD);
        long[] jArr4 = new long[this.engine.params.NWORDS_ORDER];
        long[] jArr5 = new long[this.engine.params.NWORDS_ORDER];
        long[] jArr6 = new long[this.engine.params.NWORDS_ORDER];
        long[] jArr7 = new long[this.engine.params.NWORDS_ORDER];
        PointProjFull[] pointProjFullArr = {new PointProjFull(this.engine.params.NWORDS_FIELD), new PointProjFull(this.engine.params.NWORDS_FIELD)};
        FullIsogeny_A_dual(bArr, jArr2, jArr, 1);
        BuildOrdinary3nBasis_dual(jArr, jArr2, pointProjFullArr, iArr, iArr, 2);
        Tate3_pairings(pointProjFullArr, jArr3);
        Dlogs3_dual(jArr3, iArr2, jArr5, jArr4, jArr7, jArr6);
        Compress_PKA_dual(jArr5, jArr4, jArr7, jArr6, jArr, iArr, bArr2);
        return 0;
    }

    protected int EphemeralKeyGeneration_B(byte[] bArr, byte[] bArr2) {
        return EphemeralKeyGeneration_B_extended(bArr, bArr2, 0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int EphemeralKeyGeneration_B_extended(byte[] bArr, byte[] bArr2, int i) {
        byte[] bArr3 = new byte[1];
        byte[] bArr4 = new byte[1];
        int[] iArr = new int[this.engine.params.DLEN_2];
        long[] jArr = new long[this.engine.params.NWORDS_ORDER];
        long[] jArr2 = new long[this.engine.params.NWORDS_ORDER];
        long[] jArr3 = new long[this.engine.params.NWORDS_ORDER];
        long[] jArr4 = new long[this.engine.params.NWORDS_ORDER];
        long[][][][] jArr5 = (long[][][][]) Array.newInstance(long.class, this.engine.params.MAX_Bob, 2, 2, this.engine.params.NWORDS_FIELD);
        long[][][] jArr6 = (long[][][]) Array.newInstance(long.class, 4, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr7 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        PointProjFull[] pointProjFullArr = {new PointProjFull(this.engine.params.NWORDS_FIELD), new PointProjFull(this.engine.params.NWORDS_FIELD)};
        PointProj pointProj = new PointProj(this.engine.params.NWORDS_FIELD);
        PointProj pointProj2 = new PointProj(this.engine.params.NWORDS_FIELD);
        FullIsogeny_B_dual(bArr, jArr5, jArr7);
        BuildOrdinary2nBasis_dual(jArr7, jArr5, pointProjFullArr, bArr3, bArr4);
        this.engine.fpx.fpaddPRIME(this.engine.params.Montgomery_one, pointProjFullArr[0].f27240X[0], pointProjFullArr[0].f27240X[0]);
        this.engine.fpx.fpaddPRIME(this.engine.params.Montgomery_one, pointProjFullArr[0].f27240X[0], pointProjFullArr[0].f27240X[0]);
        this.engine.fpx.fpaddPRIME(this.engine.params.Montgomery_one, pointProjFullArr[1].f27240X[0], pointProjFullArr[1].f27240X[0]);
        this.engine.fpx.fpaddPRIME(this.engine.params.Montgomery_one, pointProjFullArr[1].f27240X[0], pointProjFullArr[1].f27240X[0]);
        this.engine.fpx.fpcopy(this.engine.params.A_basis_zero, this.engine.params.NWORDS_FIELD * 0, pointProj.f27238X[0]);
        this.engine.fpx.fpcopy(this.engine.params.A_basis_zero, this.engine.params.NWORDS_FIELD * 1, pointProj.f27238X[1]);
        this.engine.fpx.fpcopy(this.engine.params.A_basis_zero, this.engine.params.NWORDS_FIELD * 2, pointProj.f27239Z[0]);
        this.engine.fpx.fpcopy(this.engine.params.A_basis_zero, this.engine.params.NWORDS_FIELD * 3, pointProj.f27239Z[1]);
        this.engine.fpx.fpcopy(this.engine.params.A_basis_zero, this.engine.params.NWORDS_FIELD * 4, pointProj2.f27238X[0]);
        this.engine.fpx.fpcopy(this.engine.params.A_basis_zero, this.engine.params.NWORDS_FIELD * 5, pointProj2.f27238X[1]);
        this.engine.fpx.fpcopy(this.engine.params.A_basis_zero, this.engine.params.NWORDS_FIELD * 6, pointProj2.f27239Z[0]);
        this.engine.fpx.fpcopy(this.engine.params.A_basis_zero, this.engine.params.NWORDS_FIELD * 7, pointProj2.f27239Z[1]);
        Tate2_pairings(pointProj, pointProj2, pointProjFullArr, jArr6);
        this.engine.fpx.fp2correction(jArr6[0]);
        this.engine.fpx.fp2correction(jArr6[1]);
        this.engine.fpx.fp2correction(jArr6[2]);
        this.engine.fpx.fp2correction(jArr6[3]);
        Dlogs2_dual(jArr6, iArr, jArr2, jArr, jArr4, jArr3);
        if (i == 1) {
            Compress_PKB_dual_extended(jArr2, jArr, jArr4, jArr3, jArr7, bArr3, bArr4, bArr2);
        } else {
            Compress_PKB_dual(jArr2, jArr, jArr4, jArr3, jArr7, bArr3, bArr4, bArr2);
        }
        return 0;
    }

    int EphemeralSecretAgreement_A(byte[] bArr, int i, byte[] bArr2, byte[] bArr3) {
        return EphemeralSecretAgreement_A_extended(bArr, i, bArr2, bArr3, 0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int EphemeralSecretAgreement_A_extended(byte[] bArr, int i, byte[] bArr2, byte[] bArr3, int i2) {
        long[][][] jArr;
        long[][] jArr2;
        long[][] jArr3;
        int[] iArr = new int[this.engine.params.MAX_INT_POINTS_ALICE];
        long[][] jArr4 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr5 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        PointProj pointProj = new PointProj(this.engine.params.NWORDS_FIELD);
        PointProj[] pointProjArr = new PointProj[this.engine.params.MAX_INT_POINTS_ALICE];
        long[][] jArr6 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr7 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr8 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][][] jArr9 = (long[][][]) Array.newInstance(long.class, 5, 2, this.engine.params.NWORDS_FIELD);
        if (i2 == 1) {
            jArr = jArr9;
            jArr2 = jArr8;
            jArr3 = jArr7;
            PKBDecompression_extended(bArr, i, bArr2, pointProj, jArr8, bArr3, this.engine.params.FP2_ENCODED_BYTES);
        } else {
            jArr = jArr9;
            jArr2 = jArr8;
            jArr3 = jArr7;
            PKBDecompression(bArr, i, bArr2, pointProj, jArr2);
        }
        this.engine.fpx.fp2copy(jArr2, jArr3);
        this.engine.fpx.fpaddPRIME(this.engine.params.Montgomery_one, this.engine.params.Montgomery_one, jArr5[0]);
        this.engine.fpx.fp2add(jArr3, jArr5, jArr4);
        this.engine.fpx.fpaddPRIME(jArr5[0], jArr5[0], jArr5[0]);
        int i3 = 1;
        if (this.engine.params.OALICE_BITS % 2 == 1) {
            PointProj pointProj2 = new PointProj(this.engine.params.NWORDS_FIELD);
            this.engine.isogeny.xDBLe(pointProj, pointProj2, jArr4, jArr5, this.engine.params.OALICE_BITS - 1);
            this.engine.isogeny.get_2_isog(pointProj2, jArr4, jArr5);
            this.engine.isogeny.eval_2_isog(pointProj, pointProj2);
        }
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        while (i3 < this.engine.params.MAX_Alice) {
            while (i4 < this.engine.params.MAX_Alice - i3) {
                pointProjArr[i5] = new PointProj(this.engine.params.NWORDS_FIELD);
                this.engine.fpx.fp2copy(pointProj.f27238X, pointProjArr[i5].f27238X);
                this.engine.fpx.fp2copy(pointProj.f27239Z, pointProjArr[i5].f27239Z);
                iArr[i5] = i4;
                int i7 = this.engine.params.strat_Alice[i6];
                this.engine.isogeny.xDBLe(pointProj, pointProj, jArr4, jArr5, i7 * 2);
                i4 += i7;
                i5++;
                i6++;
            }
            long[][][] jArr10 = jArr;
            this.engine.isogeny.get_4_isog(pointProj, jArr4, jArr5, jArr10);
            for (int i8 = 0; i8 < i5; i8++) {
                this.engine.isogeny.eval_4_isog(pointProjArr[i8], jArr10);
            }
            int i9 = i5 - 1;
            this.engine.fpx.fp2copy(pointProjArr[i9].f27238X, pointProj.f27238X);
            this.engine.fpx.fp2copy(pointProjArr[i9].f27239Z, pointProj.f27239Z);
            i4 = iArr[i9];
            i5--;
            i3++;
            jArr = jArr10;
        }
        this.engine.isogeny.get_4_isog(pointProj, jArr4, jArr5, jArr);
        this.engine.fpx.fp2add(jArr4, jArr4, jArr4);
        this.engine.fpx.fp2sub(jArr4, jArr5, jArr4);
        this.engine.fpx.fp2add(jArr4, jArr4, jArr4);
        this.engine.isogeny.j_inv(jArr4, jArr5, jArr6);
        this.engine.fpx.fp2_encode(jArr6, bArr3, 0);
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int EphemeralSecretAgreement_B(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        int[] iArr = new int[this.engine.params.MAX_INT_POINTS_BOB];
        long[][] jArr = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr2 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        PointProj pointProj = new PointProj(this.engine.params.NWORDS_FIELD);
        PointProj[] pointProjArr = new PointProj[this.engine.params.MAX_INT_POINTS_BOB];
        long[][] jArr3 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr4 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][][] jArr5 = (long[][][]) Array.newInstance(long.class, 3, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr6 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        PKADecompression_dual(bArr, bArr2, pointProj, jArr6);
        this.engine.fpx.fp2copy(jArr6, jArr4);
        this.engine.fpx.fpaddPRIME(this.engine.params.Montgomery_one, this.engine.params.Montgomery_one, jArr2[0]);
        this.engine.fpx.fp2add(jArr4, jArr2, jArr);
        this.engine.fpx.fp2sub(jArr4, jArr2, jArr2);
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 1; i4 < this.engine.params.MAX_Bob; i4++) {
            int i5 = i2;
            while (i5 < this.engine.params.MAX_Bob - i4) {
                pointProjArr[i3] = new PointProj(this.engine.params.NWORDS_FIELD);
                this.engine.fpx.fp2copy(pointProj.f27238X, pointProjArr[i3].f27238X);
                this.engine.fpx.fp2copy(pointProj.f27239Z, pointProjArr[i3].f27239Z);
                iArr[i3] = i5;
                int i6 = this.engine.params.strat_Bob[i];
                this.engine.isogeny.xTPLe(pointProj, pointProj, jArr2, jArr, i6);
                i5 += i6;
                i3++;
                i++;
            }
            this.engine.isogeny.get_3_isog(pointProj, jArr2, jArr, jArr5);
            for (int i7 = 0; i7 < i3; i7++) {
                this.engine.isogeny.eval_3_isog(pointProjArr[i7], jArr5);
            }
            int i8 = i3 - 1;
            this.engine.fpx.fp2copy(pointProjArr[i8].f27238X, pointProj.f27238X);
            this.engine.fpx.fp2copy(pointProjArr[i8].f27239Z, pointProj.f27239Z);
            i2 = iArr[i8];
            i3--;
        }
        this.engine.isogeny.get_3_isog(pointProj, jArr2, jArr, jArr5);
        this.engine.fpx.fp2add(jArr, jArr2, jArr4);
        this.engine.fpx.fp2add(jArr4, jArr4, jArr4);
        this.engine.fpx.fp2sub(jArr, jArr2, jArr);
        this.engine.isogeny.j_inv(jArr4, jArr, jArr3);
        this.engine.fpx.fp2_encode(jArr3, bArr3, 0);
        return 0;
    }

    protected void FinalExpo3(long[][] jArr, long[][] jArr2) {
        long[][] jArr3 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        this.engine.fpx.fp2copy(jArr2, jArr3);
        this.engine.fpx.fpnegPRIME(jArr3[1]);
        this.engine.fpx.fp2mul_mont(jArr, jArr3, jArr3);
        this.engine.fpx.fp2inv_mont_bingcd(jArr3);
        this.engine.fpx.fpnegPRIME(jArr[1]);
        this.engine.fpx.fp2mul_mont(jArr, jArr2, jArr);
        this.engine.fpx.fp2mul_mont(jArr, jArr3, jArr);
        for (int i = 0; i < this.engine.params.OALICE_BITS; i++) {
            this.engine.fpx.fp2sqr_mont(jArr, jArr);
        }
        for (int i2 = 0; i2 < this.engine.params.OBOB_EXPON - 1; i2++) {
            this.engine.fpx.cube_Fp2_cycl(jArr, this.engine.params.Montgomery_one);
        }
    }

    protected void FinalExpo3_2way(long[][][] jArr, long[][][] jArr2) {
        long[][][] jArr3 = (long[][][]) Array.newInstance(long.class, 2, 2, this.engine.params.NWORDS_FIELD);
        long[][][] jArr4 = (long[][][]) Array.newInstance(long.class, 2, 2, this.engine.params.NWORDS_FIELD);
        for (int i = 0; i < 2; i++) {
            this.engine.fpx.fp2copy(jArr2[i], jArr3[i]);
            this.engine.fpx.fpnegPRIME(jArr3[i][1]);
            this.engine.fpx.fp2mul_mont(jArr[i], jArr3[i], jArr3[i]);
        }
        this.engine.fpx.mont_n_way_inv(jArr3, 2, jArr4);
        for (int i2 = 0; i2 < 2; i2++) {
            this.engine.fpx.fpnegPRIME(jArr[i2][1]);
            this.engine.fpx.fp2mul_mont(jArr[i2], jArr2[i2], jArr[i2]);
            this.engine.fpx.fp2mul_mont(jArr[i2], jArr4[i2], jArr[i2]);
            for (int i3 = 0; i3 < this.engine.params.OALICE_BITS; i3++) {
                this.engine.fpx.fp2sqr_mont(jArr[i2], jArr[i2]);
            }
            for (int i4 = 0; i4 < this.engine.params.OBOB_EXPON - 1; i4++) {
                this.engine.fpx.cube_Fp2_cycl(jArr[i2], this.engine.params.Montgomery_one);
            }
        }
    }

    protected void FirstPoint3n(long[][] jArr, long[][][][] jArr2, long[][] jArr3, PointProjFull pointProjFull, int[] iArr, byte[] bArr, byte[] bArr2) {
        PointProj pointProj = new PointProj(this.engine.params.NWORDS_FIELD);
        long[] jArr4 = new long[this.engine.params.NWORDS_FIELD];
        iArr[0] = 0;
        boolean z = false;
        while (!z) {
            bArr2[0] = 0;
            Elligator2(jArr, iArr, 0, jArr3, bArr2, 0, 0);
            this.engine.fpx.fp2copy(jArr3, pointProj.f27238X);
            this.engine.fpx.fpcopy(this.engine.params.Montgomery_one, 0, pointProj.f27239Z[0]);
            this.engine.fpx.fpcopy(jArr4, 0, pointProj.f27239Z[1]);
            eval_full_dual_4_isog(jArr2, pointProj);
            boolean FirstPoint_dual = FirstPoint_dual(pointProj, pointProjFull, bArr);
            iArr[0] = iArr[0] + 1;
            z = FirstPoint_dual;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void FormatPrivKey_B(byte[] bArr) {
        int i = this.engine.params.SECRETKEY_B_BYTES - 2;
        bArr[i] = (byte) (bArr[i] & this.engine.params.MASK3_BOB);
        int i2 = this.engine.params.SECRETKEY_B_BYTES - 1;
        bArr[i2] = (byte) (bArr[i2] & this.engine.params.MASK2_BOB);
        this.engine.fpx.mul3(bArr);
    }

    protected void FullIsogeny_A_dual(byte[] bArr, long[][][][] jArr, long[][] jArr2, int i) {
        int[] iArr;
        long[][][] jArr3;
        long[][] jArr4;
        long[][] jArr5;
        int i2;
        PointProj[] pointProjArr;
        PointProj pointProj = new PointProj(this.engine.params.NWORDS_FIELD);
        PointProj[] pointProjArr2 = new PointProj[this.engine.params.MAX_INT_POINTS_ALICE];
        long[][] jArr6 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr7 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr8 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr9 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr10 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr11 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][][] jArr12 = (long[][][]) Array.newInstance(long.class, 5, 2, this.engine.params.NWORDS_FIELD);
        int[] iArr2 = new int[this.engine.params.MAX_INT_POINTS_ALICE];
        long[] jArr13 = new long[this.engine.params.NWORDS_ORDER];
        init_basis(this.engine.params.A_gen, jArr6, jArr7, jArr8);
        this.engine.fpx.fpcopy(this.engine.params.Montgomery_one, 0, jArr9[0]);
        this.engine.fpx.fp2add(jArr9, jArr9, jArr9);
        this.engine.fpx.fp2add(jArr9, jArr9, jArr10);
        this.engine.fpx.fp2add(jArr9, jArr10, jArr11);
        this.engine.fpx.fp2add(jArr10, jArr10, jArr9);
        this.engine.fpx.decode_to_digits(bArr, this.engine.params.MSG_BYTES, jArr13, this.engine.params.SECRETKEY_A_BYTES, this.engine.params.NWORDS_ORDER);
        this.engine.isogeny.LADDER3PT(jArr6, jArr7, jArr8, jArr13, this.engine.params.ALICE, pointProj, jArr11);
        this.engine.fpx.fp2inv_mont(pointProj.f27239Z);
        this.engine.fpx.fp2mul_mont(pointProj.f27238X, pointProj.f27239Z, pointProj.f27238X);
        this.engine.fpx.fpcopy(this.engine.params.Montgomery_one, 0, pointProj.f27239Z[0]);
        this.engine.fpx.fpzero(pointProj.f27239Z[1]);
        if (i == 1) {
            this.engine.fpx.fp2_encode(pointProj.f27238X, bArr, this.engine.params.MSG_BYTES + this.engine.params.SECRETKEY_A_BYTES + this.engine.params.CRYPTO_PUBLICKEYBYTES);
        }
        if (this.engine.params.OALICE_BITS % 2 == 1) {
            PointProj pointProj2 = new PointProj(this.engine.params.NWORDS_FIELD);
            iArr = iArr2;
            jArr3 = jArr12;
            jArr4 = jArr10;
            i2 = 0;
            pointProjArr = pointProjArr2;
            this.engine.isogeny.xDBLe(pointProj, pointProj2, jArr9, jArr4, this.engine.params.OALICE_BITS - 1);
            jArr5 = jArr9;
            this.engine.isogeny.get_2_isog(pointProj2, jArr5, jArr4);
            this.engine.isogeny.eval_2_isog(pointProj, pointProj2);
            this.engine.fpx.fp2copy(pointProj2.f27238X, jArr[this.engine.params.MAX_Alice][2]);
            this.engine.fpx.fp2copy(pointProj2.f27239Z, jArr[this.engine.params.MAX_Alice][3]);
        } else {
            iArr = iArr2;
            jArr3 = jArr12;
            jArr4 = jArr10;
            jArr5 = jArr9;
            i2 = 0;
            pointProjArr = pointProjArr2;
        }
        int i3 = 1;
        int i4 = i2;
        int i5 = i4;
        int i6 = i5;
        while (i3 < this.engine.params.MAX_Alice) {
            int i7 = i4;
            int i8 = i5;
            int i9 = i6;
            while (i7 < this.engine.params.MAX_Alice - i3) {
                pointProjArr[i8] = new PointProj(this.engine.params.NWORDS_FIELD);
                this.engine.fpx.fp2copy(pointProj.f27238X, pointProjArr[i8].f27238X);
                this.engine.fpx.fp2copy(pointProj.f27239Z, pointProjArr[i8].f27239Z);
                iArr[i8] = i7;
                int i10 = i9 + 1;
                int i11 = this.engine.params.strat_Alice[i9];
                this.engine.isogeny.xDBLe(pointProj, pointProj, jArr5, jArr4, i11 * 2);
                i7 += i11;
                i8++;
                i9 = i10;
            }
            int i12 = i3 - 1;
            this.engine.fpx.fp2copy(jArr5, jArr[i12][i2]);
            this.engine.fpx.fp2copy(jArr4, jArr[i12][1]);
            get_4_isog_dual(pointProj, jArr5, jArr4, jArr3);
            for (int i13 = i2; i13 < i8; i13++) {
                this.engine.isogeny.eval_4_isog(pointProjArr[i13], jArr3);
            }
            int i14 = i8;
            eval_dual_4_isog_shared(jArr3[2], jArr3[3], jArr3[4], jArr[i12], 2);
            int i15 = i14 - 1;
            this.engine.fpx.fp2copy(pointProjArr[i15].f27238X, pointProj.f27238X);
            this.engine.fpx.fp2copy(pointProjArr[i15].f27239Z, pointProj.f27239Z);
            i4 = iArr[i15];
            i5 = i14 - 1;
            i3++;
            i6 = i9;
        }
        this.engine.fpx.fp2copy(jArr5, jArr[this.engine.params.MAX_Alice - 1][i2]);
        this.engine.fpx.fp2copy(jArr4, jArr[this.engine.params.MAX_Alice - 1][1]);
        get_4_isog_dual(pointProj, jArr5, jArr4, jArr3);
        eval_dual_4_isog_shared(jArr3[2], jArr3[3], jArr3[4], jArr[this.engine.params.MAX_Alice - 1], 2);
        this.engine.fpx.fp2copy(jArr5, jArr[this.engine.params.MAX_Alice][i2]);
        this.engine.fpx.fp2copy(jArr4, jArr[this.engine.params.MAX_Alice][1]);
        this.engine.fpx.fp2inv_mont_bingcd(jArr4);
        this.engine.fpx.fp2mul_mont(jArr5, jArr4, jArr2);
    }

    protected void FullIsogeny_B_dual(byte[] bArr, long[][][][] jArr, long[][] jArr2) {
        PointProj pointProj = new PointProj(this.engine.params.NWORDS_FIELD);
        PointProj pointProj2 = new PointProj(this.engine.params.NWORDS_FIELD);
        PointProj[] pointProjArr = new PointProj[this.engine.params.MAX_INT_POINTS_BOB];
        long[][] jArr3 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr4 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr5 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr6 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr7 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][][] jArr8 = (long[][][]) Array.newInstance(long.class, 3, 2, this.engine.params.NWORDS_FIELD);
        int[] iArr = new int[this.engine.params.MAX_INT_POINTS_BOB];
        long[] jArr9 = new long[this.engine.params.NWORDS_ORDER];
        init_basis(this.engine.params.B_gen, jArr3, jArr4, jArr5);
        long[][][] jArr10 = jArr8;
        this.engine.fpx.fpcopy(this.engine.params.XQB3, 0, pointProj2.f27238X[0]);
        this.engine.fpx.fpcopy(this.engine.params.XQB3, this.engine.params.NWORDS_FIELD, pointProj2.f27238X[1]);
        this.engine.fpx.fpcopy(this.engine.params.Montgomery_one, 0, pointProj2.f27239Z[0]);
        this.engine.fpx.fpcopy(this.engine.params.Montgomery_one, 0, jArr6[0]);
        this.engine.fpx.fp2add(jArr6, jArr6, jArr6);
        this.engine.fpx.fp2add(jArr6, jArr6, jArr7);
        this.engine.fpx.fp2add(jArr6, jArr7, jArr2);
        this.engine.fpx.fp2add(jArr7, jArr7, jArr6);
        this.engine.fpx.decode_to_digits(bArr, 0, jArr9, this.engine.params.SECRETKEY_B_BYTES, this.engine.params.NWORDS_ORDER);
        this.engine.isogeny.LADDER3PT(jArr3, jArr4, jArr5, jArr9, this.engine.params.BOB, pointProj, jArr2);
        int i = 1;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (i < this.engine.params.MAX_Bob) {
            int i5 = i2;
            while (i5 < this.engine.params.MAX_Bob - i) {
                pointProjArr[i3] = new PointProj(this.engine.params.NWORDS_FIELD);
                this.engine.fpx.fp2copy(pointProj.f27238X, pointProjArr[i3].f27238X);
                this.engine.fpx.fp2copy(pointProj.f27239Z, pointProjArr[i3].f27239Z);
                iArr[i3] = i5;
                int i6 = this.engine.params.strat_Bob[i4];
                this.engine.isogeny.xTPLe(pointProj, pointProj, jArr7, jArr6, i6);
                i5 += i6;
                i3++;
                i4++;
            }
            long[][][] jArr11 = jArr10;
            this.engine.isogeny.get_3_isog(pointProj, jArr7, jArr6, jArr11);
            for (int i7 = 0; i7 < i3; i7++) {
                this.engine.isogeny.eval_3_isog(pointProjArr[i7], jArr11);
            }
            this.engine.isogeny.eval_3_isog(pointProj2, jArr11);
            int i8 = i - 1;
            int i9 = i4;
            this.engine.fpx.fp2sub(pointProj2.f27238X, pointProj2.f27239Z, jArr[i8][0]);
            this.engine.fpx.fp2add(pointProj2.f27238X, pointProj2.f27239Z, jArr[i8][1]);
            int i10 = i3 - 1;
            this.engine.fpx.fp2copy(pointProjArr[i10].f27238X, pointProj.f27238X);
            this.engine.fpx.fp2copy(pointProjArr[i10].f27239Z, pointProj.f27239Z);
            i2 = iArr[i10];
            i3--;
            i++;
            i4 = i9;
            jArr10 = jArr11;
        }
        long[][][] jArr12 = jArr10;
        this.engine.isogeny.get_3_isog(pointProj, jArr7, jArr6, jArr12);
        this.engine.isogeny.eval_3_isog(pointProj2, jArr12);
        this.engine.fpx.fp2sub(pointProj2.f27238X, pointProj2.f27239Z, jArr[this.engine.params.MAX_Bob - 1][0]);
        this.engine.fpx.fp2add(pointProj2.f27238X, pointProj2.f27239Z, jArr[this.engine.params.MAX_Bob - 1][1]);
        this.engine.fpx.fp2add(jArr6, jArr7, jArr2);
        this.engine.fpx.fp2sub(jArr6, jArr7, jArr6);
        this.engine.fpx.fp2inv_mont_bingcd(jArr6);
        this.engine.fpx.fp2mul_mont(jArr6, jArr2, jArr2);
        this.engine.fpx.fp2add(jArr2, jArr2, jArr2);
    }

    protected void Ladder3pt_dual(PointProj[] pointProjArr, long[] jArr, int i, PointProj pointProj, long[][] jArr2) {
        PointProj pointProj2 = new PointProj(this.engine.params.NWORDS_FIELD);
        PointProj pointProj3 = new PointProj(this.engine.params.NWORDS_FIELD);
        int i2 = i == this.engine.params.ALICE ? this.engine.params.OALICE_BITS : this.engine.params.OBOB_BITS;
        this.engine.fpx.fp2copy(pointProjArr[1].f27238X, pointProj2.f27238X);
        this.engine.fpx.fp2copy(pointProjArr[1].f27239Z, pointProj2.f27239Z);
        this.engine.fpx.fp2copy(pointProjArr[2].f27238X, pointProj3.f27238X);
        this.engine.fpx.fp2copy(pointProjArr[2].f27239Z, pointProj3.f27239Z);
        this.engine.fpx.fp2copy(pointProjArr[0].f27238X, pointProj.f27238X);
        this.engine.fpx.fp2copy(pointProjArr[0].f27239Z, pointProj.f27239Z);
        int i3 = 0;
        int i4 = 0;
        while (i3 < i2) {
            int i5 = (int) ((jArr[i3 >>> 6] >>> (i3 & 63)) & 1);
            this.engine.isogeny.swap_points(pointProj, pointProj3, 0 - (i4 ^ i5));
            this.engine.isogeny.xDBLADD(pointProj2, pointProj3, pointProj.f27238X, jArr2);
            this.engine.fpx.fp2mul_mont(pointProj3.f27238X, pointProj.f27239Z, pointProj3.f27238X);
            i3++;
            i4 = i5;
        }
        this.engine.isogeny.swap_points(pointProj, pointProj3, 0 - (i4 ^ 0));
    }

    protected void PKADecompression_dual(byte[] bArr, byte[] bArr2, PointProj pointProj, long[][] jArr) {
        long[] jArr2;
        long[] jArr3;
        long[][] jArr4 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        PointProj[] pointProjArr = {new PointProj(this.engine.params.NWORDS_FIELD), new PointProj(this.engine.params.NWORDS_FIELD), new PointProj(this.engine.params.NWORDS_FIELD)};
        long[] jArr5 = new long[this.engine.params.NWORDS_ORDER];
        long[] jArr6 = new long[this.engine.params.NWORDS_ORDER];
        long[] jArr7 = new long[this.engine.params.NWORDS_ORDER];
        long[] jArr8 = new long[this.engine.params.NWORDS_ORDER];
        long[] jArr9 = new long[this.engine.params.NWORDS_ORDER];
        long[] jArr10 = new long[this.engine.params.NWORDS_ORDER];
        long[] jArr11 = new long[this.engine.params.NWORDS_ORDER];
        this.engine.fpx.fp2_decode(bArr2, jArr, this.engine.params.ORDER_B_ENCODED_BYTES * 3);
        jArr9[0] = 1;
        this.engine.fpx.to_Montgomery_mod_order(jArr9, jArr9, this.engine.params.Bob_order, this.engine.params.Montgomery_RB2, this.engine.params.Montgomery_RB1);
        byte b = (byte) ((bArr2[(this.engine.params.ORDER_B_ENCODED_BYTES * 3) + this.engine.params.FP2_ENCODED_BYTES] & 255) >> 7);
        byte[] bArr3 = new byte[3];
        System.arraycopy(bArr2, (this.engine.params.ORDER_B_ENCODED_BYTES * 3) + this.engine.params.FP2_ENCODED_BYTES, bArr3, 0, 3);
        int[] iArr = {bArr3[0] & 65535, bArr3[1] & 65535, bArr3[2] & 65535};
        iArr[0] = iArr[0] & 127;
        this.engine.fpx.fpaddPRIME(jArr[0], this.engine.params.Montgomery_one, jArr4[0]);
        this.engine.fpx.fpcopy(jArr[1], 0, jArr4[1]);
        this.engine.fpx.fpaddPRIME(jArr4[0], this.engine.params.Montgomery_one, jArr4[0]);
        this.engine.fpx.fp2div2(jArr4, jArr4);
        this.engine.fpx.fp2div2(jArr4, jArr4);
        BuildOrdinary3nBasis_Decomp_dual(jArr4, pointProjArr, iArr, iArr, 2);
        this.engine.fpx.fpcopy(this.engine.params.Montgomery_one, 0, pointProjArr[0].f27239Z[0]);
        this.engine.fpx.fpcopy(this.engine.params.Montgomery_one, 0, pointProjArr[1].f27239Z[0]);
        this.engine.isogeny.swap_points(pointProjArr[0], pointProjArr[1], -b);
        this.engine.fpx.decode_to_digits(bArr, 0, jArr11, this.engine.params.SECRETKEY_B_BYTES, this.engine.params.NWORDS_ORDER);
        this.engine.fpx.to_Montgomery_mod_order(jArr11, jArr5, this.engine.params.Bob_order, this.engine.params.Montgomery_RB2, this.engine.params.Montgomery_RB1);
        this.engine.fpx.decode_to_digits(bArr2, 0, jArr10, this.engine.params.ORDER_B_ENCODED_BYTES, this.engine.params.NWORDS_ORDER);
        this.engine.fpx.to_Montgomery_mod_order(jArr10, jArr6, this.engine.params.Bob_order, this.engine.params.Montgomery_RB2, this.engine.params.Montgomery_RB1);
        this.engine.fpx.decode_to_digits(bArr2, this.engine.params.ORDER_B_ENCODED_BYTES, jArr10, this.engine.params.ORDER_B_ENCODED_BYTES, this.engine.params.NWORDS_ORDER);
        this.engine.fpx.to_Montgomery_mod_order(jArr10, jArr7, this.engine.params.Bob_order, this.engine.params.Montgomery_RB2, this.engine.params.Montgomery_RB1);
        this.engine.fpx.decode_to_digits(bArr2, 2 * this.engine.params.ORDER_B_ENCODED_BYTES, jArr10, this.engine.params.ORDER_B_ENCODED_BYTES, this.engine.params.NWORDS_ORDER);
        this.engine.fpx.to_Montgomery_mod_order(jArr10, jArr8, this.engine.params.Bob_order, this.engine.params.Montgomery_RB2, this.engine.params.Montgomery_RB1);
        if (b == 0) {
            jArr3 = jArr7;
            this.engine.fpx.Montgomery_multiply_mod_order(jArr5, jArr3, jArr3, this.engine.params.Bob_order, this.engine.params.Montgomery_RB2);
            this.engine.fpx.mp_add(jArr3, jArr9, jArr3, this.engine.params.NWORDS_ORDER);
            this.engine.fpx.Montgomery_inversion_mod_order_bingcd(jArr3, jArr3, this.engine.params.Bob_order, this.engine.params.Montgomery_RB2, this.engine.params.Montgomery_RB1);
            this.engine.fpx.Montgomery_multiply_mod_order(jArr5, jArr8, jArr8, this.engine.params.Bob_order, this.engine.params.Montgomery_RB2);
            jArr2 = jArr8;
            this.engine.fpx.mp_add(jArr6, jArr2, jArr2, this.engine.params.NWORDS_ORDER);
        } else {
            jArr2 = jArr8;
            jArr3 = jArr7;
            this.engine.fpx.Montgomery_multiply_mod_order(jArr5, jArr2, jArr2, this.engine.params.Bob_order, this.engine.params.Montgomery_RB2);
            this.engine.fpx.mp_add(jArr2, jArr9, jArr2, this.engine.params.NWORDS_ORDER);
            this.engine.fpx.Montgomery_inversion_mod_order_bingcd(jArr2, jArr2, this.engine.params.Bob_order, this.engine.params.Montgomery_RB2, this.engine.params.Montgomery_RB1);
            this.engine.fpx.Montgomery_multiply_mod_order(jArr5, jArr3, jArr3, this.engine.params.Bob_order, this.engine.params.Montgomery_RB2);
            this.engine.fpx.mp_add(jArr6, jArr3, jArr3, this.engine.params.NWORDS_ORDER);
        }
        this.engine.fpx.Montgomery_multiply_mod_order(jArr3, jArr2, jArr3, this.engine.params.Bob_order, this.engine.params.Montgomery_RB2);
        this.engine.fpx.from_Montgomery_mod_order(jArr3, jArr3, this.engine.params.Bob_order, this.engine.params.Montgomery_RB2);
        Ladder3pt_dual(pointProjArr, jArr3, this.engine.params.BOB, pointProj, jArr4);
        this.engine.isogeny.Double(pointProj, pointProj, jArr4, this.engine.params.OALICE_BITS);
    }

    protected void PKBDecompression(byte[] bArr, int i, byte[] bArr2, PointProj pointProj, long[][] jArr) {
        PointProj[] pointProjArr;
        long[][] jArr2 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[] jArr3 = new long[this.engine.params.NWORDS_ORDER * 2];
        long[] jArr4 = new long[this.engine.params.NWORDS_ORDER * 2];
        long[] jArr5 = new long[this.engine.params.NWORDS_ORDER * 2];
        long[] jArr6 = new long[this.engine.params.NWORDS_ORDER];
        long[] jArr7 = new long[this.engine.params.NWORDS_ORDER];
        PointProj[] pointProjArr2 = new PointProj[3];
        long j = (-1) >>> (this.engine.params.MAXBITS_ORDER - this.engine.params.OALICE_BITS);
        jArr5[0] = 1;
        this.engine.fpx.fp2_decode(bArr2, jArr, this.engine.params.ORDER_A_ENCODED_BYTES * 3);
        int i2 = bArr2[(this.engine.params.ORDER_A_ENCODED_BYTES * 3) + this.engine.params.FP2_ENCODED_BYTES] >>> 7;
        BuildEntangledXonly_Decomp(jArr, pointProjArr2, bArr2[(this.engine.params.ORDER_A_ENCODED_BYTES * 3) + this.engine.params.FP2_ENCODED_BYTES] & 1, bArr2[(this.engine.params.ORDER_A_ENCODED_BYTES * 3) + this.engine.params.FP2_ENCODED_BYTES + 1]);
        this.engine.fpx.fpcopy(this.engine.params.Montgomery_one, 0, pointProjArr2[0].f27239Z[0]);
        this.engine.fpx.fpcopy(this.engine.params.Montgomery_one, 0, pointProjArr2[1].f27239Z[0]);
        this.engine.fpx.fpaddPRIME(jArr[0], this.engine.params.Montgomery_one, jArr2[0]);
        this.engine.fpx.fpcopy(jArr[1], 0, jArr2[1]);
        this.engine.fpx.fpaddPRIME(jArr2[0], this.engine.params.Montgomery_one, jArr2[0]);
        this.engine.fpx.fp2div2(jArr2, jArr2);
        this.engine.fpx.fp2div2(jArr2, jArr2);
        this.engine.fpx.decode_to_digits(bArr, i, jArr6, this.engine.params.SECRETKEY_A_BYTES, this.engine.params.NWORDS_ORDER);
        this.engine.isogeny.swap_points(pointProjArr2[0], pointProjArr2[1], 0 - i2);
        if (i2 == 0) {
            pointProjArr = pointProjArr2;
            this.engine.fpx.decode_to_digits(bArr2, this.engine.params.ORDER_A_ENCODED_BYTES, jArr7, this.engine.params.ORDER_A_ENCODED_BYTES, this.engine.params.NWORDS_ORDER);
            this.engine.fpx.multiply(jArr6, jArr7, jArr3, this.engine.params.NWORDS_ORDER);
            this.engine.fpx.mp_add(jArr3, jArr5, jArr3, this.engine.params.NWORDS_ORDER);
            int i3 = this.engine.params.NWORDS_ORDER - 1;
            jArr3[i3] = jArr3[i3] & j;
            this.engine.fpx.inv_mod_orderA(jArr3, jArr4);
            this.engine.fpx.decode_to_digits(bArr2, 2 * this.engine.params.ORDER_A_ENCODED_BYTES, jArr7, this.engine.params.ORDER_A_ENCODED_BYTES, this.engine.params.NWORDS_ORDER);
            this.engine.fpx.multiply(jArr6, jArr7, jArr3, this.engine.params.NWORDS_ORDER);
            this.engine.fpx.decode_to_digits(bArr2, 0, jArr7, this.engine.params.ORDER_A_ENCODED_BYTES, this.engine.params.NWORDS_ORDER);
            this.engine.fpx.mp_add(jArr7, jArr3, jArr3, this.engine.params.NWORDS_ORDER);
            this.engine.fpx.multiply(jArr3, jArr4, jArr5, this.engine.params.NWORDS_ORDER);
            int i4 = this.engine.params.NWORDS_ORDER - 1;
            jArr5[i4] = jArr5[i4] & j;
        } else {
            pointProjArr = pointProjArr2;
            this.engine.fpx.decode_to_digits(bArr2, 2 * this.engine.params.ORDER_A_ENCODED_BYTES, jArr7, this.engine.params.ORDER_A_ENCODED_BYTES, this.engine.params.NWORDS_ORDER);
            this.engine.fpx.multiply(jArr6, jArr7, jArr3, this.engine.params.NWORDS_ORDER);
            this.engine.fpx.mp_add(jArr3, jArr5, jArr3, this.engine.params.NWORDS_ORDER);
            int i5 = this.engine.params.NWORDS_ORDER - 1;
            jArr3[i5] = jArr3[i5] & j;
            this.engine.fpx.inv_mod_orderA(jArr3, jArr4);
            this.engine.fpx.decode_to_digits(bArr2, this.engine.params.ORDER_A_ENCODED_BYTES, jArr7, this.engine.params.ORDER_A_ENCODED_BYTES, this.engine.params.NWORDS_ORDER);
            this.engine.fpx.multiply(jArr6, jArr7, jArr3, this.engine.params.NWORDS_ORDER);
            this.engine.fpx.decode_to_digits(bArr2, 0, jArr7, this.engine.params.ORDER_A_ENCODED_BYTES, this.engine.params.NWORDS_ORDER);
            this.engine.fpx.mp_add(jArr7, jArr3, jArr3, this.engine.params.NWORDS_ORDER);
            this.engine.fpx.multiply(jArr3, jArr4, jArr5, this.engine.params.NWORDS_ORDER);
            int i6 = this.engine.params.NWORDS_ORDER - 1;
            jArr5[i6] = jArr5[i6] & j;
        }
        Ladder3pt_dual(pointProjArr, jArr5, this.engine.params.ALICE, pointProj, jArr2);
        this.engine.fpx.fp2div2(jArr, jArr2);
        this.engine.isogeny.xTPLe_fast(pointProj, pointProj, jArr2, this.engine.params.OBOB_EXPON);
    }

    protected void PKBDecompression_extended(byte[] bArr, int i, byte[] bArr2, PointProj pointProj, long[][] jArr, byte[] bArr3, int i2) {
        long[][] jArr2;
        long[] jArr3;
        long[][] jArr4 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr5 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[] jArr6 = new long[this.engine.params.NWORDS_ORDER * 2];
        long[] jArr7 = new long[this.engine.params.NWORDS_ORDER * 2];
        long[] jArr8 = new long[this.engine.params.NWORDS_ORDER];
        long[] jArr9 = new long[this.engine.params.NWORDS_ORDER * 2];
        long[] jArr10 = new long[this.engine.params.NWORDS_ORDER];
        long[] jArr11 = new long[this.engine.params.NWORDS_ORDER];
        long[] jArr12 = new long[this.engine.params.NWORDS_ORDER];
        long[] jArr13 = new long[this.engine.params.NWORDS_ORDER];
        long[] jArr14 = new long[this.engine.params.NWORDS_ORDER];
        PointProj[] pointProjArr = {new PointProj(this.engine.params.NWORDS_FIELD), new PointProj(this.engine.params.NWORDS_FIELD), new PointProj(this.engine.params.NWORDS_FIELD)};
        long j = (-1) >>> (this.engine.params.MAXBITS_ORDER - this.engine.params.OALICE_BITS);
        this.engine.fpx.fp2_decode(bArr2, jArr, this.engine.params.ORDER_A_ENCODED_BYTES * 4);
        BuildEntangledXonly_Decomp(jArr, pointProjArr, bArr2[(this.engine.params.ORDER_A_ENCODED_BYTES * 4) + this.engine.params.FP2_ENCODED_BYTES] & 1, bArr2[(this.engine.params.ORDER_A_ENCODED_BYTES * 4) + this.engine.params.FP2_ENCODED_BYTES + 1]);
        this.engine.fpx.fpcopy(this.engine.params.Montgomery_one, 0, pointProjArr[0].f27239Z[0]);
        this.engine.fpx.fpcopy(this.engine.params.Montgomery_one, 0, pointProjArr[1].f27239Z[0]);
        this.engine.fpx.fpaddPRIME(jArr[0], this.engine.params.Montgomery_one, jArr4[0]);
        this.engine.fpx.fpcopy(jArr[1], 0, jArr4[1]);
        this.engine.fpx.fpaddPRIME(jArr4[0], this.engine.params.Montgomery_one, jArr4[0]);
        this.engine.fpx.fp2div2(jArr4, jArr4);
        this.engine.fpx.fp2div2(jArr4, jArr4);
        this.engine.fpx.decode_to_digits(bArr, i, jArr10, this.engine.params.SECRETKEY_A_BYTES, this.engine.params.NWORDS_ORDER);
        this.engine.fpx.decode_to_digits(bArr2, 0, jArr11, this.engine.params.ORDER_A_ENCODED_BYTES, this.engine.params.NWORDS_ORDER);
        this.engine.fpx.decode_to_digits(bArr2, this.engine.params.ORDER_A_ENCODED_BYTES, jArr13, this.engine.params.ORDER_A_ENCODED_BYTES, this.engine.params.NWORDS_ORDER);
        this.engine.fpx.decode_to_digits(bArr2, this.engine.params.ORDER_A_ENCODED_BYTES * 2, jArr12, this.engine.params.ORDER_A_ENCODED_BYTES, this.engine.params.NWORDS_ORDER);
        this.engine.fpx.decode_to_digits(bArr2, 3 * this.engine.params.ORDER_A_ENCODED_BYTES, jArr14, this.engine.params.ORDER_A_ENCODED_BYTES, this.engine.params.NWORDS_ORDER);
        if ((jArr11[0] & 1) == 1) {
            this.engine.fpx.multiply(jArr10, jArr14, jArr6, this.engine.params.NWORDS_ORDER);
            this.engine.fpx.mp_add(jArr6, jArr13, jArr6, this.engine.params.NWORDS_ORDER);
            int i3 = this.engine.params.NWORDS_ORDER - 1;
            jArr6[i3] = jArr6[i3] & j;
            this.engine.fpx.multiply(jArr10, jArr12, jArr7, this.engine.params.NWORDS_ORDER);
            this.engine.fpx.mp_add(jArr7, jArr11, jArr7, this.engine.params.NWORDS_ORDER);
            int i4 = this.engine.params.NWORDS_ORDER - 1;
            jArr7[i4] = jArr7[i4] & j;
            jArr3 = jArr8;
            this.engine.fpx.inv_mod_orderA(jArr7, jArr3);
            this.engine.fpx.multiply(jArr6, jArr3, jArr9, this.engine.params.NWORDS_ORDER);
            int i5 = this.engine.params.NWORDS_ORDER - 1;
            jArr9[i5] = jArr9[i5] & j;
            Ladder3pt_dual(pointProjArr, jArr9, this.engine.params.ALICE, pointProj, jArr4);
            jArr2 = jArr5;
        } else {
            jArr2 = jArr5;
            this.engine.fpx.multiply(jArr10, jArr12, jArr6, this.engine.params.NWORDS_ORDER);
            this.engine.fpx.mp_add(jArr6, jArr11, jArr6, this.engine.params.NWORDS_ORDER);
            int i6 = this.engine.params.NWORDS_ORDER - 1;
            jArr6[i6] = jArr6[i6] & j;
            this.engine.fpx.multiply(jArr10, jArr14, jArr7, this.engine.params.NWORDS_ORDER);
            this.engine.fpx.mp_add(jArr7, jArr13, jArr7, this.engine.params.NWORDS_ORDER);
            int i7 = this.engine.params.NWORDS_ORDER - 1;
            jArr7[i7] = jArr7[i7] & j;
            this.engine.fpx.inv_mod_orderA(jArr7, jArr8);
            this.engine.fpx.multiply(jArr8, jArr6, jArr9, this.engine.params.NWORDS_ORDER);
            int i8 = this.engine.params.NWORDS_ORDER - 1;
            jArr9[i8] = jArr9[i8] & j;
            this.engine.isogeny.swap_points(pointProjArr[0], pointProjArr[1], -1L);
            jArr3 = jArr8;
            Ladder3pt_dual(pointProjArr, jArr9, this.engine.params.ALICE, pointProj, jArr4);
        }
        long[][] jArr15 = jArr2;
        this.engine.fpx.fp2div2(jArr, jArr15);
        this.engine.isogeny.xTPLe_fast(pointProj, pointProj, jArr15, this.engine.params.OBOB_EXPON);
        this.engine.fpx.fp2_encode(pointProj.f27238X, bArr3, i2);
        this.engine.fpx.fp2_encode(pointProj.f27239Z, bArr3, this.engine.params.FP2_ENCODED_BYTES + i2);
        this.engine.fpx.encode_to_bytes(jArr3, bArr3, (this.engine.params.FP2_ENCODED_BYTES * 2) + i2, this.engine.params.ORDER_A_ENCODED_BYTES);
    }

    protected void RecoverY(long[][] jArr, PointProj[] pointProjArr, PointProjFull[] pointProjFullArr) {
        long[][] jArr2 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr3 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr4 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr5 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr6 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        this.engine.fpx.fp2mul_mont(pointProjArr[2].f27238X, pointProjArr[1].f27239Z, jArr2);
        this.engine.fpx.fp2mul_mont(pointProjArr[1].f27238X, pointProjArr[2].f27239Z, jArr3);
        this.engine.fpx.fp2mul_mont(pointProjArr[1].f27238X, pointProjArr[2].f27238X, jArr4);
        this.engine.fpx.fp2mul_mont(pointProjArr[1].f27239Z, pointProjArr[2].f27239Z, jArr5);
        this.engine.fpx.fp2sqr_mont(pointProjArr[1].f27238X, jArr6);
        this.engine.fpx.fp2sqr_mont(pointProjArr[1].f27239Z, pointProjFullArr[1].f27240X);
        this.engine.fpx.fp2sub(jArr4, jArr5, pointProjFullArr[1].f27241Y);
        this.engine.fpx.fp2mul_mont(pointProjArr[1].f27238X, pointProjFullArr[1].f27241Y, pointProjFullArr[1].f27241Y);
        this.engine.fpx.fp2add(jArr6, pointProjFullArr[1].f27240X, jArr6);
        this.engine.fpx.fp2mul_mont(pointProjArr[2].f27239Z, jArr6, jArr6);
        this.engine.fpx.fp2mul_mont(jArr, jArr3, pointProjFullArr[1].f27240X);
        this.engine.fpx.fp2sub(jArr2, jArr3, pointProjFullArr[1].f27242Z);
        this.engine.fpx.fp2mul_mont(pointProjFullArr[0].f27240X, pointProjFullArr[1].f27242Z, jArr2);
        this.engine.fpx.fp2add(jArr4, pointProjFullArr[1].f27240X, jArr3);
        this.engine.fpx.fp2add(jArr3, jArr3, jArr3);
        this.engine.fpx.fp2sub(jArr2, jArr3, jArr2);
        this.engine.fpx.fp2mul_mont(pointProjArr[1].f27239Z, jArr2, jArr2);
        this.engine.fpx.fp2sub(jArr2, jArr6, jArr2);
        this.engine.fpx.fp2mul_mont(pointProjFullArr[0].f27240X, jArr2, jArr2);
        this.engine.fpx.fp2add(jArr2, pointProjFullArr[1].f27241Y, pointProjFullArr[1].f27241Y);
        this.engine.fpx.fp2mul_mont(pointProjFullArr[0].f27241Y, jArr5, jArr2);
        this.engine.fpx.fp2mul_mont(pointProjArr[1].f27238X, jArr2, pointProjFullArr[1].f27240X);
        this.engine.fpx.fp2add(pointProjFullArr[1].f27240X, pointProjFullArr[1].f27240X, pointProjFullArr[1].f27240X);
        this.engine.fpx.fp2mul_mont(pointProjArr[1].f27239Z, jArr2, pointProjFullArr[1].f27242Z);
        this.engine.fpx.fp2add(pointProjFullArr[1].f27242Z, pointProjFullArr[1].f27242Z, pointProjFullArr[1].f27242Z);
        this.engine.fpx.fp2inv_mont_bingcd(pointProjFullArr[1].f27242Z);
        this.engine.fpx.fp2mul_mont(pointProjFullArr[1].f27240X, pointProjFullArr[1].f27242Z, pointProjFullArr[1].f27240X);
        this.engine.fpx.fp2mul_mont(pointProjFullArr[1].f27241Y, pointProjFullArr[1].f27242Z, pointProjFullArr[1].f27241Y);
    }

    protected void SecondPoint3n(long[][] jArr, long[][][][] jArr2, long[][] jArr3, PointProjFull pointProjFull, int[] iArr, byte[] bArr, byte[] bArr2) {
        PointProj pointProj = new PointProj(this.engine.params.NWORDS_FIELD);
        long[] jArr4 = new long[this.engine.params.NWORDS_FIELD];
        boolean z = false;
        while (!z) {
            bArr2[0] = 0;
            Elligator2(jArr, iArr, 1, jArr3, bArr2, 0, 0);
            this.engine.fpx.fp2copy(jArr3, pointProj.f27238X);
            this.engine.fpx.fpcopy(this.engine.params.Montgomery_one, 0, pointProj.f27239Z[0]);
            this.engine.fpx.fpcopy(jArr4, 0, pointProj.f27239Z[1]);
            eval_full_dual_4_isog(jArr2, pointProj);
            boolean SecondPoint_dual = SecondPoint_dual(pointProj, pointProjFull, bArr);
            iArr[1] = iArr[1] + 1;
            z = SecondPoint_dual;
        }
    }

    protected void Tate3_proj(PointProjFull pointProjFull, PointProjFull pointProjFull2, long[][] jArr, long[][] jArr2) {
        long[][] jArr3 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr4 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        TripleAndParabola_proj(pointProjFull, jArr4, jArr2);
        this.engine.fpx.fp2sub(pointProjFull2.f27240X, pointProjFull.f27240X, jArr);
        this.engine.fpx.fp2mul_mont(jArr4, jArr, jArr);
        this.engine.fpx.fp2sub(pointProjFull.f27241Y, pointProjFull2.f27241Y, jArr3);
        this.engine.fpx.fp2mul_mont(jArr2, jArr3, jArr3);
        this.engine.fpx.fp2add(jArr, jArr3, jArr);
    }

    void Traverse_w_div_e_fullsigned(long[][] jArr, int i, int i2, int i3, int[] iArr, long[] jArr2, int[] iArr2, int i4, int i5, int i6) {
        int i7;
        long[][] jArr3;
        long[][] jArr4 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr5 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        if (i3 > 1) {
            int i8 = iArr[i3];
            this.engine.fpx.fp2copy(jArr, jArr4);
            int i9 = 0;
            while (true) {
                i7 = i3 - i8;
                if (i9 >= i7) {
                    break;
                }
                if ((i5 & 1) == 0) {
                    for (int i10 = 0; i10 < i6; i10++) {
                        this.engine.fpx.sqr_Fp2_cycl(jArr4, this.engine.params.Montgomery_one);
                    }
                } else {
                    for (int i11 = 0; i11 < i6; i11++) {
                        this.engine.fpx.cube_Fp2_cycl(jArr4, this.engine.params.Montgomery_one);
                    }
                }
                i9++;
            }
            long[][] jArr6 = jArr5;
            Traverse_w_div_e_fullsigned(jArr4, i + i7, i2, i8, iArr, jArr2, iArr2, i4, i5, i6);
            this.engine.fpx.fp2copy(jArr, jArr4);
            int i12 = i2;
            while (true) {
                int i13 = i2 + i8;
                if (i12 >= i13) {
                    Traverse_w_div_e_fullsigned(jArr4, i, i13, i7, iArr, jArr2, iArr2, i4, i5, i6);
                    return;
                }
                if (iArr2[i12] == 0) {
                    jArr3 = jArr6;
                } else if (iArr2[i12] < 0) {
                    jArr3 = jArr6;
                    this.engine.fpx.fp2copy(jArr2, this.engine.params.NWORDS_FIELD * (((i + i12) * (i5 / 2)) + ((-iArr2[i12]) - 1)) * 2, jArr3);
                    this.engine.fpx.fpnegPRIME(jArr3[1]);
                    this.engine.fpx.fp2mul_mont(jArr4, jArr3, jArr4);
                } else {
                    jArr3 = jArr6;
                    this.engine.fpx.fp2mul_mont(jArr4, jArr2, this.engine.params.NWORDS_FIELD * (((i + i12) * (i5 / 2)) + (iArr2[i12] - 1)) * 2, jArr4);
                }
                i12++;
                jArr6 = jArr3;
            }
        } else {
            this.engine.fpx.fp2copy(jArr, jArr4);
            this.engine.fpx.fp2correction(jArr4);
            if (this.engine.fpx.is_felm_zero(jArr4[1]) && Fpx.subarrayEquals(jArr4[0], this.engine.params.Montgomery_one, this.engine.params.NWORDS_FIELD)) {
                iArr2[i2] = 0;
                return;
            }
            int i14 = 1;
            int i15 = 2;
            while (true) {
                int i16 = i5 / 2;
                if (i14 > i16) {
                    return;
                }
                int i17 = (((i4 - 1) * i16) + (i14 - 1)) * i15;
                if (Fpx.subarrayEquals(jArr4, jArr2, this.engine.params.NWORDS_FIELD * i17, this.engine.params.NWORDS_FIELD * i15)) {
                    iArr2[i2] = -i14;
                    return;
                }
                this.engine.fpx.fp2copy(jArr2, this.engine.params.NWORDS_FIELD * i17, jArr5);
                this.engine.fpx.fpnegPRIME(jArr5[1]);
                this.engine.fpx.fpcorrectionPRIME(jArr5[1]);
                if (Fpx.subarrayEquals(jArr4, jArr5, this.engine.params.NWORDS_FIELD * 2)) {
                    iArr2[i2] = i14;
                    return;
                } else {
                    i14++;
                    i15 = 2;
                }
            }
        }
    }

    void Traverse_w_notdiv_e_fullsigned(long[][] jArr, int i, int i2, int i3, int[] iArr, long[] jArr2, long[] jArr3, int[] iArr2, int i4, int i5, int i6, int i7, int i8, int i9) {
        int i10;
        long[][] jArr4;
        long[][] jArr5 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr6 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        if (i3 > 1) {
            int i11 = iArr[i3];
            this.engine.fpx.fp2copy(jArr, jArr5);
            int i12 = i > 0 ? (i3 - i11) * i8 : (i9 % i8) + (((i3 - i11) - 1) * i8);
            for (int i13 = 0; i13 < i12; i13++) {
                if ((i5 & 1) == 0) {
                    this.engine.fpx.sqr_Fp2_cycl(jArr5, this.engine.params.Montgomery_one);
                } else {
                    this.engine.fpx.cube_Fp2_cycl(jArr5, this.engine.params.Montgomery_one);
                }
            }
            int i14 = i3 - i11;
            long[][] jArr7 = jArr6;
            Traverse_w_notdiv_e_fullsigned(jArr5, i + i14, i2, i11, iArr, jArr2, jArr3, iArr2, i4, i5, i6, i7, i8, i9);
            this.engine.fpx.fp2copy(jArr, jArr5);
            int i15 = i2;
            while (true) {
                int i16 = i2 + i11;
                if (i15 >= i16) {
                    Traverse_w_notdiv_e_fullsigned(jArr5, i, i16, i14, iArr, jArr2, jArr3, iArr2, i4, i5, i6, i7, i8, i9);
                    return;
                }
                if (iArr2[i15] == 0) {
                    jArr4 = jArr7;
                } else if (i <= 0) {
                    jArr4 = jArr7;
                    if (iArr2[i15] < 0) {
                        this.engine.fpx.fp2copy(jArr2, this.engine.params.NWORDS_FIELD * (((i + i15) * (i6 / 2)) + ((-iArr2[i15]) - 1)) * 2, jArr4);
                        this.engine.fpx.fpnegPRIME(jArr4[1]);
                        this.engine.fpx.fp2mul_mont(jArr5, jArr4, jArr5);
                    } else {
                        this.engine.fpx.fp2mul_mont(jArr5, jArr2, this.engine.params.NWORDS_FIELD * (((i + i15) * (i6 / 2)) + (iArr2[i15] - 1)) * 2, jArr5);
                    }
                } else if (iArr2[i15] < 0) {
                    jArr4 = jArr7;
                    this.engine.fpx.fp2copy(jArr3, this.engine.params.NWORDS_FIELD * (((i + i15) * 2 * (i6 / 2)) + (((-iArr2[i15]) - 1) * 2)), jArr4);
                    this.engine.fpx.fpnegPRIME(jArr4[1]);
                    this.engine.fpx.fp2mul_mont(jArr5, jArr4, jArr5);
                } else {
                    jArr4 = jArr7;
                    this.engine.fpx.fp2mul_mont(jArr5, jArr3, this.engine.params.NWORDS_FIELD * (((i + i15) * (i6 / 2)) + (iArr2[i15] - 1)) * 2, jArr5);
                }
                i15++;
                jArr7 = jArr4;
            }
        } else {
            this.engine.fpx.fp2copy(jArr, jArr5);
            this.engine.fpx.fp2correction(jArr5);
            if (this.engine.fpx.is_felm_zero(jArr5[1]) && Fpx.subarrayEquals(jArr5[0], this.engine.params.Montgomery_one, this.engine.params.NWORDS_FIELD)) {
                iArr2[i2] = 0;
            } else if (i == 0 && i2 == i4 - 1) {
                for (int i17 = 1; i17 <= i7 / 2; i17++) {
                    int i18 = i6 / 2;
                    int i19 = i17 - 1;
                    if (Fpx.subarrayEquals(jArr5, jArr2, this.engine.params.NWORDS_FIELD * ((i18 * 2 * i10) + (i19 * 2)), this.engine.params.NWORDS_FIELD * 2)) {
                        iArr2[i2] = -i17;
                        return;
                    }
                    this.engine.fpx.fp2copy(jArr2, this.engine.params.NWORDS_FIELD * ((i18 * i10) + i19) * 2, jArr6);
                    this.engine.fpx.fpnegPRIME(jArr6[1]);
                    this.engine.fpx.fpcorrectionPRIME(jArr6[1]);
                    if (Fpx.subarrayEquals(jArr5, jArr6, this.engine.params.NWORDS_FIELD * 2)) {
                        iArr2[i2] = i17;
                        return;
                    }
                }
            } else {
                int i20 = 1;
                while (true) {
                    int i21 = i6 / 2;
                    if (i20 > i21) {
                        return;
                    }
                    int i22 = i4 - 1;
                    int i23 = i20 - 1;
                    if (Fpx.subarrayEquals(jArr5, jArr3, this.engine.params.NWORDS_FIELD * ((i21 * 2 * i22) + (i23 * 2)), this.engine.params.NWORDS_FIELD * 2)) {
                        iArr2[i2] = -i20;
                        return;
                    }
                    this.engine.fpx.fp2copy(jArr3, this.engine.params.NWORDS_FIELD * ((i21 * i22) + i23) * 2, jArr6);
                    this.engine.fpx.fpnegPRIME(jArr6[1]);
                    this.engine.fpx.fpcorrectionPRIME(jArr6[1]);
                    if (Fpx.subarrayEquals(jArr5, jArr6, this.engine.params.NWORDS_FIELD * 2)) {
                        iArr2[i2] = i20;
                        return;
                    }
                    i20++;
                }
            }
        }
    }

    protected void TripleAndParabola_proj(PointProjFull pointProjFull, long[][] jArr, long[][] jArr2) {
        this.engine.fpx.fp2sqr_mont(pointProjFull.f27240X, jArr2);
        this.engine.fpx.fp2add(jArr2, jArr2, jArr);
        this.engine.fpx.fp2add(jArr, jArr2, jArr);
        this.engine.fpx.fpaddPRIME(jArr[0], this.engine.params.Montgomery_one, jArr[0]);
        this.engine.fpx.fp2add(pointProjFull.f27241Y, pointProjFull.f27241Y, jArr2);
    }

    protected void eval_dual_2_isog(long[][] jArr, long[][] jArr2, PointProj pointProj) {
        long[][] jArr3 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        this.engine.fpx.fp2add(pointProj.f27238X, pointProj.f27239Z, jArr3);
        this.engine.fpx.fp2sub(pointProj.f27238X, pointProj.f27239Z, pointProj.f27239Z);
        this.engine.fpx.fp2sqr_mont(jArr3, jArr3);
        this.engine.fpx.fp2sqr_mont(pointProj.f27239Z, pointProj.f27239Z);
        this.engine.fpx.fp2sub(jArr3, pointProj.f27239Z, pointProj.f27239Z);
        this.engine.fpx.fp2mul_mont(jArr, pointProj.f27239Z, pointProj.f27239Z);
        this.engine.fpx.fp2mul_mont(jArr2, jArr3, pointProj.f27238X);
    }

    protected void eval_dual_4_isog(long[][] jArr, long[][] jArr2, long[][][] jArr3, int i, PointProj pointProj) {
        long[][] jArr4 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr5 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr6 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr7 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        this.engine.fpx.fp2add(pointProj.f27238X, pointProj.f27239Z, jArr4);
        this.engine.fpx.fp2sub(pointProj.f27238X, pointProj.f27239Z, jArr5);
        this.engine.fpx.fp2sqr_mont(jArr4, jArr4);
        this.engine.fpx.fp2sqr_mont(jArr5, jArr5);
        this.engine.fpx.fp2sub(jArr4, jArr5, jArr6);
        this.engine.fpx.fp2sub(jArr2, jArr, jArr7);
        this.engine.fpx.fp2mul_mont(jArr6, jArr7, jArr7);
        this.engine.fpx.fp2mul_mont(jArr2, jArr4, jArr6);
        this.engine.fpx.fp2sub(jArr6, jArr7, jArr6);
        this.engine.fpx.fp2mul_mont(jArr6, jArr4, pointProj.f27238X);
        this.engine.fpx.fp2mul_mont(jArr7, jArr5, pointProj.f27239Z);
        this.engine.fpx.fp2mul_mont(jArr3[i + 0], pointProj.f27238X, pointProj.f27238X);
        this.engine.fpx.fp2mul_mont(jArr3[i + 1], pointProj.f27239Z, jArr4);
        this.engine.fpx.fp2add(pointProj.f27238X, jArr4, pointProj.f27238X);
        this.engine.fpx.fp2mul_mont(jArr3[i + 2], pointProj.f27239Z, pointProj.f27239Z);
    }

    protected void eval_dual_4_isog_shared(long[][] jArr, long[][] jArr2, long[][] jArr3, long[][][] jArr4, int i) {
        this.engine.fpx.fp2sub(jArr2, jArr3, jArr4[i + 0]);
        int i2 = i + 1;
        this.engine.fpx.fp2add(jArr2, jArr3, jArr4[i2]);
        int i3 = i + 2;
        this.engine.fpx.fp2sqr_mont(jArr, jArr4[i3]);
        this.engine.fpx.fp2sub(jArr4[i3], jArr4[i2], jArr4[i3]);
    }

    protected void eval_final_dual_2_isog(PointProj pointProj) {
        long[][] jArr = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr2 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[] jArr3 = new long[this.engine.params.NWORDS_FIELD];
        this.engine.fpx.fp2add(pointProj.f27238X, pointProj.f27239Z, jArr);
        this.engine.fpx.fp2mul_mont(pointProj.f27238X, pointProj.f27239Z, jArr2);
        this.engine.fpx.fp2sqr_mont(jArr, pointProj.f27238X);
        this.engine.fpx.fpcopy(pointProj.f27238X[0], 0, jArr3);
        this.engine.fpx.fpcopy(pointProj.f27238X[1], 0, pointProj.f27238X[0]);
        this.engine.fpx.fpcopy(jArr3, 0, pointProj.f27238X[1]);
        this.engine.fpx.fpnegPRIME(pointProj.f27238X[1]);
        this.engine.fpx.fp2add(jArr2, jArr2, pointProj.f27239Z);
        this.engine.fpx.fp2add(pointProj.f27239Z, pointProj.f27239Z, pointProj.f27239Z);
    }

    protected void eval_full_dual_4_isog(long[][][][] jArr, PointProj pointProj) {
        for (int i = 0; i < this.engine.params.MAX_Alice; i++) {
            eval_dual_4_isog(jArr[this.engine.params.MAX_Alice - i][0], jArr[this.engine.params.MAX_Alice - i][1], jArr[(this.engine.params.MAX_Alice - i) - 1], 2, pointProj);
        }
        if (this.engine.params.OALICE_BITS % 2 == 1) {
            eval_dual_2_isog(jArr[this.engine.params.MAX_Alice][2], jArr[this.engine.params.MAX_Alice][3], pointProj);
        }
        eval_final_dual_2_isog(pointProj);
    }

    protected void get_4_isog_dual(PointProj pointProj, long[][] jArr, long[][] jArr2, long[][][] jArr3) {
        this.engine.fpx.fp2sub(pointProj.f27238X, pointProj.f27239Z, jArr3[1]);
        this.engine.fpx.fp2add(pointProj.f27238X, pointProj.f27239Z, jArr3[2]);
        this.engine.fpx.fp2sqr_mont(pointProj.f27239Z, jArr3[4]);
        this.engine.fpx.fp2add(jArr3[4], jArr3[4], jArr3[0]);
        this.engine.fpx.fp2sqr_mont(jArr3[0], jArr2);
        this.engine.fpx.fp2add(jArr3[0], jArr3[0], jArr3[0]);
        this.engine.fpx.fp2sqr_mont(pointProj.f27238X, jArr3[3]);
        this.engine.fpx.fp2add(jArr3[3], jArr3[3], jArr);
        this.engine.fpx.fp2sqr_mont(jArr, jArr);
    }

    protected void init_basis(long[] jArr, long[][] jArr2, long[][] jArr3, long[][] jArr4) {
        this.engine.fpx.fpcopy(jArr, 0, jArr2[0]);
        this.engine.fpx.fpcopy(jArr, this.engine.params.NWORDS_FIELD, jArr2[1]);
        this.engine.fpx.fpcopy(jArr, this.engine.params.NWORDS_FIELD * 2, jArr3[0]);
        this.engine.fpx.fpcopy(jArr, this.engine.params.NWORDS_FIELD * 3, jArr3[1]);
        this.engine.fpx.fpcopy(jArr, this.engine.params.NWORDS_FIELD * 4, jArr4[0]);
        this.engine.fpx.fpcopy(jArr, this.engine.params.NWORDS_FIELD * 5, jArr4[1]);
    }

    protected void makeDiff(PointProjFull pointProjFull, PointProjFull pointProjFull2, PointProj pointProj) {
        long[][] jArr = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr2 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr3 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        int i = this.engine.params.NWORDS_FIELD;
        this.engine.fpx.fp2sub(pointProjFull.f27240X, pointProjFull2.f27240X, jArr);
        this.engine.fpx.fp2sub(pointProjFull.f27241Y, pointProjFull2.f27241Y, jArr2);
        this.engine.fpx.fp2sqr_mont(jArr, jArr);
        this.engine.fpx.fp2sqr_mont(jArr2, jArr2);
        this.engine.fpx.fp2add(pointProjFull.f27240X, pointProjFull2.f27240X, jArr3);
        this.engine.fpx.fp2mul_mont(jArr, jArr3, jArr3);
        this.engine.fpx.fp2sub(jArr2, jArr3, jArr2);
        this.engine.fpx.fp2mul_mont(pointProj.f27239Z, jArr2, jArr2);
        this.engine.fpx.fp2mul_mont(pointProj.f27238X, jArr, jArr);
        this.engine.fpx.fp2correction(jArr);
        this.engine.fpx.fp2correction(jArr2);
        if (Fpx.subarrayEquals(jArr[0], jArr2[0], i) && Fpx.subarrayEquals(jArr[1], jArr2[1], i)) {
            this.engine.fpx.fp2neg(pointProjFull2.f27241Y);
        }
    }

    protected void make_positive(long[][] jArr) {
        int i = this.engine.params.NWORDS_FIELD;
        this.engine.fpx.from_fp2mont(jArr, jArr);
        if (Fpx.subarrayEquals(jArr[0], new long[this.engine.params.NWORDS_FIELD], i) ? (jArr[1][0] & 1) == 1 : (jArr[0][0] & 1) == 1) {
            this.engine.fpx.fp2neg(jArr);
        }
        this.engine.fpx.to_fp2mont(jArr, jArr);
    }

    protected void random_mod_order_A(byte[] bArr, SecureRandom secureRandom) {
        byte[] bArr2 = new byte[this.engine.params.SECRETKEY_A_BYTES];
        secureRandom.nextBytes(bArr2);
        System.arraycopy(bArr2, 0, bArr, 0, this.engine.params.SECRETKEY_A_BYTES);
        bArr[0] = (byte) (bArr[0] & 254);
        int i = this.engine.params.SECRETKEY_A_BYTES - 1;
        bArr[i] = (byte) (bArr[i] & this.engine.params.MASK_ALICE);
    }

    protected void random_mod_order_B(byte[] bArr, SecureRandom secureRandom) {
        byte[] bArr2 = new byte[this.engine.params.SECRETKEY_B_BYTES];
        secureRandom.nextBytes(bArr2);
        System.arraycopy(bArr2, 0, bArr, 0, this.engine.params.SECRETKEY_A_BYTES);
        FormatPrivKey_B(bArr);
    }

    void solve_dlog(long[][] jArr, int[] iArr, long[] jArr2, int i) {
        if (i == 2) {
            if (this.engine.params.OALICE_BITS % this.engine.params.W_2 == 0) {
                Traverse_w_div_e_fullsigned(jArr, 0, 0, this.engine.params.PLEN_2 - 1, this.engine.params.ph2_path, this.engine.params.ph2_T, iArr, this.engine.params.DLEN_2, this.engine.params.ELL2_W, this.engine.params.W_2);
            } else {
                Traverse_w_notdiv_e_fullsigned(jArr, 0, 0, this.engine.params.PLEN_2 - 1, this.engine.params.ph2_path, this.engine.params.ph2_T1, this.engine.params.ph2_T2, iArr, this.engine.params.DLEN_2, i, this.engine.params.ELL2_W, this.engine.params.ELL2_EMODW, this.engine.params.W_2, this.engine.params.OALICE_BITS);
            }
            from_base(iArr, jArr2, this.engine.params.DLEN_2, this.engine.params.ELL2_W);
        } else if (i == 3) {
            if (this.engine.params.OBOB_EXPON % this.engine.params.W_3 == 0) {
                Traverse_w_div_e_fullsigned(jArr, 0, 0, this.engine.params.PLEN_3 - 1, this.engine.params.ph3_path, this.engine.params.ph3_T, iArr, this.engine.params.DLEN_3, this.engine.params.ELL3_W, this.engine.params.W_3);
            } else {
                Traverse_w_notdiv_e_fullsigned(jArr, 0, 0, this.engine.params.PLEN_3 - 1, this.engine.params.ph3_path, this.engine.params.ph3_T1, this.engine.params.ph3_T2, iArr, this.engine.params.DLEN_3, i, this.engine.params.ELL3_W, this.engine.params.ELL3_EMODW, this.engine.params.W_3, this.engine.params.OBOB_EXPON);
            }
            from_base(iArr, jArr2, this.engine.params.DLEN_3, this.engine.params.ELL3_W);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public byte validate_ciphertext(byte[] bArr, byte[] bArr2, byte[] bArr3, int i, byte[] bArr4, int i2) {
        PointProj[] pointProjArr = new PointProj[this.engine.params.MAX_INT_POINTS_BOB];
        PointProj[] pointProjArr2 = {new PointProj(this.engine.params.NWORDS_FIELD), new PointProj(this.engine.params.NWORDS_FIELD), new PointProj(this.engine.params.NWORDS_FIELD)};
        PointProj pointProj = new PointProj(this.engine.params.NWORDS_FIELD);
        PointProj pointProj2 = new PointProj(this.engine.params.NWORDS_FIELD);
        long[][] jArr = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr2 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr3 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr4 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr5 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr6 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr7 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr8 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][][] jArr9 = (long[][][]) Array.newInstance(long.class, 3, 2, this.engine.params.NWORDS_FIELD);
        int[] iArr = new int[this.engine.params.MAX_INT_POINTS_BOB];
        long[] jArr10 = new long[this.engine.params.NWORDS_ORDER];
        long[] jArr11 = new long[this.engine.params.NWORDS_ORDER];
        this.engine.fpx.fpcopy(this.engine.params.Montgomery_one, 0, ((long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD))[0]);
        init_basis(this.engine.params.B_gen, jArr, jArr2, jArr3);
        this.engine.fpx.fp2_decode(bArr3, pointProjArr2[0].f27238X, i);
        this.engine.fpx.fpcopy(this.engine.params.Montgomery_one, 0, pointProjArr2[0].f27239Z[0]);
        this.engine.fpx.fpcopy(this.engine.params.Montgomery_one, 0, jArr4[0]);
        this.engine.fpx.fp2add(jArr4, jArr4, jArr4);
        this.engine.fpx.fp2add(jArr4, jArr4, jArr5);
        this.engine.fpx.fp2add(jArr4, jArr5, jArr6);
        this.engine.fpx.fp2add(jArr5, jArr5, jArr4);
        this.engine.fpx.decode_to_digits(bArr, 0, jArr11, this.engine.params.SECRETKEY_B_BYTES, this.engine.params.NWORDS_ORDER);
        this.engine.isogeny.LADDER3PT(jArr, jArr2, jArr3, jArr11, this.engine.params.BOB, pointProj, jArr6);
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        int i6 = 1;
        while (i6 < this.engine.params.MAX_Bob) {
            int i7 = i3;
            while (i7 < this.engine.params.MAX_Bob - i6) {
                pointProjArr[i4] = new PointProj(this.engine.params.NWORDS_FIELD);
                this.engine.fpx.fp2copy(pointProj.f27238X, pointProjArr[i4].f27238X);
                this.engine.fpx.fp2copy(pointProj.f27239Z, pointProjArr[i4].f27239Z);
                iArr[i4] = i7;
                int i8 = this.engine.params.strat_Bob[i5];
                this.engine.isogeny.xTPLe(pointProj, pointProj, jArr5, jArr4, i8);
                i7 += i8;
                i6 = i6;
                i4++;
                i5++;
            }
            int i9 = i6;
            this.engine.isogeny.get_3_isog(pointProj, jArr5, jArr4, jArr9);
            for (int i10 = 0; i10 < i4; i10++) {
                this.engine.isogeny.eval_3_isog(pointProjArr[i10], jArr9);
            }
            this.engine.isogeny.eval_3_isog(pointProjArr2[0], jArr9);
            int i11 = i4 - 1;
            this.engine.fpx.fp2copy(pointProjArr[i11].f27238X, pointProj.f27238X);
            this.engine.fpx.fp2copy(pointProjArr[i11].f27239Z, pointProj.f27239Z);
            i3 = iArr[i11];
            i4--;
            i6 = i9 + 1;
        }
        this.engine.isogeny.get_3_isog(pointProj, jArr5, jArr4, jArr9);
        this.engine.isogeny.eval_3_isog(pointProjArr2[0], jArr9);
        this.engine.fpx.fp2_decode(bArr2, jArr6, this.engine.params.ORDER_A_ENCODED_BYTES * 4);
        this.engine.fpx.fp2_decode(bArr4, pointProj2.f27238X, i2);
        this.engine.fpx.fp2_decode(bArr4, pointProj2.f27239Z, this.engine.params.FP2_ENCODED_BYTES + i2);
        this.engine.fpx.decode_to_digits(bArr4, i2 + (this.engine.params.FP2_ENCODED_BYTES * 2), jArr10, this.engine.params.ORDER_A_ENCODED_BYTES, this.engine.params.NWORDS_ORDER);
        this.engine.isogeny.Ladder(pointProjArr2[0], jArr10, jArr6, this.engine.params.OALICE_BITS, pointProj);
        this.engine.fpx.fp2mul_mont(pointProj.f27238X, pointProj2.f27239Z, jArr7);
        this.engine.fpx.fp2mul_mont(pointProj.f27239Z, pointProj2.f27238X, jArr8);
        return this.engine.fpx.cmp_f2elm(jArr7, jArr8);
    }
}
