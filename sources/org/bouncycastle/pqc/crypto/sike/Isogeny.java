package org.bouncycastle.pqc.crypto.sike;

import java.lang.reflect.Array;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class Isogeny {
    SIKEEngine engine;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Isogeny(SIKEEngine sIKEEngine) {
        this.engine = sIKEEngine;
    }

    private void xDBLADD_proj(PointProj pointProj, PointProj pointProj2, long[][] jArr, long[][] jArr2, long[][] jArr3) {
        long[][] jArr4 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr5 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr6 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        this.engine.fpx.fp2add(pointProj.f27238X, pointProj.f27239Z, jArr4);
        this.engine.fpx.fp2sub(pointProj.f27238X, pointProj.f27239Z, jArr5);
        this.engine.fpx.fp2sqr_mont(jArr4, pointProj.f27238X);
        this.engine.fpx.fp2sub(pointProj2.f27238X, pointProj2.f27239Z, jArr6);
        this.engine.fpx.fp2correction(jArr6);
        this.engine.fpx.fp2add(pointProj2.f27238X, pointProj2.f27239Z, pointProj2.f27238X);
        this.engine.fpx.fp2mul_mont(jArr4, jArr6, jArr4);
        this.engine.fpx.fp2sqr_mont(jArr5, pointProj.f27239Z);
        this.engine.fpx.fp2mul_mont(jArr5, pointProj2.f27238X, jArr5);
        this.engine.fpx.fp2sub(pointProj.f27238X, pointProj.f27239Z, jArr6);
        this.engine.fpx.fp2mul_mont(pointProj.f27238X, pointProj.f27239Z, pointProj.f27238X);
        this.engine.fpx.fp2mul_mont(jArr6, jArr3, pointProj2.f27238X);
        this.engine.fpx.fp2sub(jArr4, jArr5, pointProj2.f27239Z);
        this.engine.fpx.fp2add(pointProj2.f27238X, pointProj.f27239Z, pointProj.f27239Z);
        this.engine.fpx.fp2add(jArr4, jArr5, pointProj2.f27238X);
        this.engine.fpx.fp2mul_mont(pointProj.f27239Z, jArr6, pointProj.f27239Z);
        this.engine.fpx.fp2sqr_mont(pointProj2.f27239Z, pointProj2.f27239Z);
        this.engine.fpx.fp2sqr_mont(pointProj2.f27238X, pointProj2.f27238X);
        this.engine.fpx.fp2mul_mont(pointProj2.f27238X, jArr2, pointProj2.f27238X);
        this.engine.fpx.fp2mul_mont(pointProj2.f27239Z, jArr, pointProj2.f27239Z);
    }

    private void xDBL_e(PointProj pointProj, PointProj pointProj2, long[][] jArr, int i) {
        long[][] jArr2 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr3 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr4 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr5 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr6 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr7 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        this.engine.fpx.fp2copy(pointProj.f27238X, pointProj2.f27238X);
        this.engine.fpx.fp2copy(pointProj.f27239Z, pointProj2.f27239Z);
        for (int i2 = 0; i2 < i; i2++) {
            this.engine.fpx.fp2add(pointProj2.f27238X, pointProj2.f27239Z, jArr3);
            this.engine.fpx.fp2sub(pointProj2.f27238X, pointProj2.f27239Z, jArr4);
            this.engine.fpx.fp2sqr_mont(jArr3, jArr6);
            this.engine.fpx.fp2sqr_mont(jArr4, jArr7);
            this.engine.fpx.fp2sub(jArr6, jArr7, jArr5);
            this.engine.fpx.fp2mul_mont(jArr6, jArr7, pointProj2.f27238X);
            this.engine.fpx.fp2mul_mont(jArr, jArr5, jArr2);
            this.engine.fpx.fp2add(jArr2, jArr7, jArr2);
            this.engine.fpx.fp2mul_mont(jArr5, jArr2, pointProj2.f27239Z);
        }
    }

    private void xTPL(PointProj pointProj, PointProj pointProj2, long[][] jArr, long[][] jArr2) {
        long[][] jArr3 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr4 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr5 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr6 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr7 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr8 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr9 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        this.engine.fpx.mp2_sub_p2(pointProj.f27238X, pointProj.f27239Z, jArr3);
        this.engine.fpx.fp2sqr_mont(jArr3, jArr5);
        this.engine.fpx.mp2_add(pointProj.f27238X, pointProj.f27239Z, jArr4);
        this.engine.fpx.fp2sqr_mont(jArr4, jArr6);
        this.engine.fpx.mp2_add(pointProj.f27238X, pointProj.f27238X, jArr7);
        this.engine.fpx.mp2_add(pointProj.f27239Z, pointProj.f27239Z, jArr3);
        this.engine.fpx.fp2sqr_mont(jArr7, jArr4);
        this.engine.fpx.mp2_sub_p2(jArr4, jArr6, jArr4);
        this.engine.fpx.mp2_sub_p2(jArr4, jArr5, jArr4);
        this.engine.fpx.fp2mul_mont(jArr2, jArr6, jArr8);
        this.engine.fpx.fp2mul_mont(jArr6, jArr8, jArr6);
        this.engine.fpx.fp2mul_mont(jArr, jArr5, jArr9);
        this.engine.fpx.fp2mul_mont(jArr5, jArr9, jArr5);
        this.engine.fpx.mp2_sub_p2(jArr5, jArr6, jArr6);
        this.engine.fpx.mp2_sub_p2(jArr8, jArr9, jArr5);
        this.engine.fpx.fp2mul_mont(jArr4, jArr5, jArr4);
        this.engine.fpx.fp2add(jArr6, jArr4, jArr5);
        this.engine.fpx.fp2sqr_mont(jArr5, jArr5);
        this.engine.fpx.fp2mul_mont(jArr7, jArr5, pointProj2.f27238X);
        this.engine.fpx.fp2sub(jArr6, jArr4, jArr4);
        this.engine.fpx.fp2sqr_mont(jArr4, jArr4);
        this.engine.fpx.fp2mul_mont(jArr3, jArr4, pointProj2.f27239Z);
    }

    private void xTPL_fast(PointProj pointProj, PointProj pointProj2, long[][] jArr) {
        long[][] jArr2 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr3 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr4 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr5 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        this.engine.fpx.fp2sqr_mont(pointProj.f27238X, jArr2);
        this.engine.fpx.fp2sqr_mont(pointProj.f27239Z, jArr3);
        this.engine.fpx.fp2add(jArr2, jArr3, jArr4);
        this.engine.fpx.fp2add(pointProj.f27238X, pointProj.f27239Z, jArr5);
        this.engine.fpx.fp2sqr_mont(jArr5, jArr5);
        this.engine.fpx.fp2sub(jArr5, jArr4, jArr5);
        this.engine.fpx.fp2mul_mont(jArr, jArr5, jArr5);
        this.engine.fpx.fp2add(jArr4, jArr5, jArr5);
        this.engine.fpx.fp2sub(jArr2, jArr3, jArr4);
        this.engine.fpx.fp2sqr_mont(jArr4, jArr4);
        this.engine.fpx.fp2mul_mont(jArr2, jArr5, jArr2);
        this.engine.fpx.fp2shl(jArr2, 2, jArr2);
        this.engine.fpx.fp2sub(jArr2, jArr4, jArr2);
        this.engine.fpx.fp2sqr_mont(jArr2, jArr2);
        this.engine.fpx.fp2mul_mont(jArr3, jArr5, jArr3);
        this.engine.fpx.fp2shl(jArr3, 2, jArr3);
        this.engine.fpx.fp2sub(jArr3, jArr4, jArr3);
        this.engine.fpx.fp2sqr_mont(jArr3, jArr3);
        this.engine.fpx.fp2mul_mont(pointProj.f27238X, jArr3, pointProj2.f27238X);
        this.engine.fpx.fp2mul_mont(pointProj.f27239Z, jArr2, pointProj2.f27239Z);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void CompleteMPoint(long[][] jArr, PointProj pointProj, PointProjFull pointProjFull) {
        long[][] jArr2 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr3 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr4 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr5 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr6 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr7 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr8 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr9 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr10 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        this.engine.fpx.fpcopy(this.engine.params.Montgomery_one, 0, jArr3[0]);
        if (Fpx.subarrayEquals(pointProj.f27239Z[0], jArr2[0], this.engine.params.NWORDS_FIELD) && Fpx.subarrayEquals(pointProj.f27239Z[1], jArr2[1], this.engine.params.NWORDS_FIELD)) {
            this.engine.fpx.fp2copy(jArr2, pointProjFull.f27240X);
            this.engine.fpx.fp2copy(jArr3, pointProjFull.f27241Y);
            this.engine.fpx.fp2copy(jArr2, pointProjFull.f27242Z);
            return;
        }
        this.engine.fpx.fp2mul_mont(pointProj.f27238X, pointProj.f27239Z, jArr4);
        this.engine.fpx.fpsubPRIME(pointProj.f27238X[0], pointProj.f27239Z[1], jArr9[0]);
        this.engine.fpx.fpaddPRIME(pointProj.f27238X[1], pointProj.f27239Z[0], jArr9[1]);
        this.engine.fpx.fpaddPRIME(pointProj.f27238X[0], pointProj.f27239Z[1], jArr10[0]);
        this.engine.fpx.fpsubPRIME(pointProj.f27238X[1], pointProj.f27239Z[0], jArr10[1]);
        this.engine.fpx.fp2mul_mont(jArr9, jArr10, jArr6);
        this.engine.fpx.fp2mul_mont(jArr, jArr4, jArr9);
        this.engine.fpx.fp2add(jArr9, jArr6, jArr10);
        this.engine.fpx.fp2mul_mont(jArr4, jArr10, jArr7);
        this.engine.fpx.sqrt_Fp2(jArr7, jArr5);
        this.engine.fpx.fp2copy(pointProj.f27239Z, jArr8);
        this.engine.fpx.fp2inv_mont_bingcd(jArr8);
        this.engine.fpx.fp2mul_mont(pointProj.f27238X, jArr8, pointProjFull.f27240X);
        this.engine.fpx.fp2sqr_mont(jArr8, jArr9);
        this.engine.fpx.fp2mul_mont(jArr5, jArr9, pointProjFull.f27241Y);
        this.engine.fpx.fp2copy(jArr3, pointProjFull.f27242Z);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void CompletePoint(PointProj pointProj, PointProjFull pointProjFull) {
        long[][] jArr = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr2 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr3 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr4 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr5 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr6 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr7 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr8 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        this.engine.fpx.fpcopy(this.engine.params.Montgomery_one, 0, jArr8[0]);
        this.engine.fpx.fp2mul_mont(pointProj.f27238X, pointProj.f27239Z, jArr);
        this.engine.fpx.fpsubPRIME(pointProj.f27238X[0], pointProj.f27239Z[1], jArr6[0]);
        this.engine.fpx.fpaddPRIME(pointProj.f27238X[1], pointProj.f27239Z[0], jArr6[1]);
        this.engine.fpx.fpaddPRIME(pointProj.f27238X[0], pointProj.f27239Z[1], jArr7[0]);
        this.engine.fpx.fpsubPRIME(pointProj.f27238X[1], pointProj.f27239Z[0], jArr7[1]);
        this.engine.fpx.fp2mul_mont(jArr6, jArr7, jArr2);
        this.engine.fpx.fp2mul_mont(jArr, jArr2, jArr3);
        this.engine.fpx.sqrt_Fp2(jArr3, jArr4);
        this.engine.fpx.fp2copy(pointProj.f27239Z, jArr5);
        this.engine.fpx.fp2inv_mont_bingcd(jArr5);
        this.engine.fpx.fp2mul_mont(pointProj.f27238X, jArr5, pointProjFull.f27240X);
        this.engine.fpx.fp2sqr_mont(jArr5, jArr6);
        this.engine.fpx.fp2mul_mont(jArr4, jArr6, pointProjFull.f27241Y);
        this.engine.fpx.fp2copy(jArr8, pointProjFull.f27242Z);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void Double(PointProj pointProj, PointProj pointProj2, long[][] jArr, int i) {
        long[][] jArr2 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr3 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr4 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr5 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr6 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr7 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        this.engine.fpx.fp2copy(pointProj.f27238X, pointProj2.f27238X);
        this.engine.fpx.fp2copy(pointProj.f27239Z, pointProj2.f27239Z);
        for (int i2 = 0; i2 < i; i2++) {
            this.engine.fpx.fp2add(pointProj2.f27238X, pointProj2.f27239Z, jArr3);
            this.engine.fpx.fp2sub(pointProj2.f27238X, pointProj2.f27239Z, jArr4);
            this.engine.fpx.fp2sqr_mont(jArr3, jArr6);
            this.engine.fpx.fp2sqr_mont(jArr4, jArr7);
            this.engine.fpx.fp2sub(jArr6, jArr7, jArr5);
            this.engine.fpx.fp2mul_mont(jArr6, jArr7, pointProj2.f27238X);
            this.engine.fpx.fp2mul_mont(jArr, jArr5, jArr2);
            this.engine.fpx.fp2add(jArr2, jArr7, jArr2);
            this.engine.fpx.fp2mul_mont(jArr5, jArr2, pointProj2.f27239Z);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void LADDER3PT(long[][] jArr, long[][] jArr2, long[][] jArr3, long[] jArr4, int i, PointProj pointProj, long[][] jArr5) {
        PointProj pointProj2 = new PointProj(this.engine.params.NWORDS_FIELD);
        PointProj pointProj3 = new PointProj(this.engine.params.NWORDS_FIELD);
        long[][] jArr6 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        int i2 = i == this.engine.params.ALICE ? this.engine.params.OALICE_BITS : this.engine.params.OBOB_BITS - 1;
        this.engine.fpx.fpcopy(this.engine.params.Montgomery_one, 0, jArr6[0]);
        this.engine.fpx.mp2_add(jArr6, jArr6, jArr6);
        this.engine.fpx.mp2_add(jArr5, jArr6, jArr6);
        this.engine.fpx.fp2div2(jArr6, jArr6);
        this.engine.fpx.fp2div2(jArr6, jArr6);
        this.engine.fpx.fp2copy(jArr2, pointProj2.f27238X);
        this.engine.fpx.fpcopy(this.engine.params.Montgomery_one, 0, pointProj2.f27239Z[0]);
        this.engine.fpx.fp2copy(jArr3, pointProj3.f27238X);
        this.engine.fpx.fpcopy(this.engine.params.Montgomery_one, 0, pointProj3.f27239Z[0]);
        this.engine.fpx.fp2copy(jArr, pointProj.f27238X);
        this.engine.fpx.fpcopy(this.engine.params.Montgomery_one, 0, pointProj.f27239Z[0]);
        this.engine.fpx.fpzero(pointProj.f27239Z[1]);
        int i3 = 0;
        int i4 = 0;
        while (i3 < i2) {
            int i5 = (int) ((jArr4[i3 >>> 6] >>> (i3 & 63)) & 1);
            swap_points(pointProj, pointProj3, 0 - (i4 ^ i5));
            xDBLADD(pointProj2, pointProj3, pointProj.f27238X, jArr6);
            this.engine.fpx.fp2mul_mont(pointProj3.f27238X, pointProj.f27239Z, pointProj3.f27238X);
            i3++;
            i4 = i5;
        }
        swap_points(pointProj, pointProj3, 0 - (i4 ^ 0));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void Ladder(PointProj pointProj, long[] jArr, long[][] jArr2, int i, PointProj pointProj2) {
        PointProj pointProj3 = new PointProj(this.engine.params.NWORDS_FIELD);
        PointProj pointProj4 = new PointProj(this.engine.params.NWORDS_FIELD);
        long[][] jArr3 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        this.engine.fpx.fpcopy(this.engine.params.Montgomery_one, 0, jArr3[0]);
        this.engine.fpx.fpaddPRIME(jArr3[0], jArr3[0], jArr3[0]);
        this.engine.fpx.fp2add(jArr2, jArr3, jArr3);
        this.engine.fpx.fp2div2(jArr3, jArr3);
        this.engine.fpx.fp2div2(jArr3, jArr3);
        int i2 = i - 1;
        long j = jArr[i2 >> 6] >>> (i2 & 63);
        while (((int) (j & 1)) == 0) {
            i2--;
            j = jArr[i2 >> 6] >>> (i2 & 63);
        }
        this.engine.fpx.fp2copy(pointProj.f27238X, pointProj3.f27238X);
        this.engine.fpx.fp2copy(pointProj.f27239Z, pointProj3.f27239Z);
        xDBL_e(pointProj, pointProj4, jArr3, 1);
        int i3 = i2 - 1;
        int i4 = 0;
        while (i3 >= 0) {
            int i5 = (int) ((jArr[i3 >> 6] >>> (i3 & 63)) & 1);
            swap_points(pointProj3, pointProj4, 0 - (i4 ^ i5));
            xDBLADD_proj(pointProj3, pointProj4, pointProj.f27238X, pointProj.f27239Z, jArr3);
            i3--;
            i4 = i5;
        }
        swap_points(pointProj3, pointProj4, 0 - (i4 ^ 0));
        this.engine.fpx.fp2copy(pointProj3.f27238X, pointProj2.f27238X);
        this.engine.fpx.fp2copy(pointProj3.f27239Z, pointProj2.f27239Z);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void eval_2_isog(PointProj pointProj, PointProj pointProj2) {
        long[][] jArr = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr2 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr3 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr4 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        this.engine.fpx.mp2_add(pointProj2.f27238X, pointProj2.f27239Z, jArr);
        this.engine.fpx.mp2_sub_p2(pointProj2.f27238X, pointProj2.f27239Z, jArr2);
        this.engine.fpx.mp2_add(pointProj.f27238X, pointProj.f27239Z, jArr3);
        this.engine.fpx.mp2_sub_p2(pointProj.f27238X, pointProj.f27239Z, jArr4);
        this.engine.fpx.fp2mul_mont(jArr, jArr4, jArr);
        this.engine.fpx.fp2mul_mont(jArr2, jArr3, jArr2);
        this.engine.fpx.mp2_add(jArr, jArr2, jArr3);
        this.engine.fpx.mp2_sub_p2(jArr, jArr2, jArr4);
        this.engine.fpx.fp2mul_mont(pointProj.f27238X, jArr3, pointProj.f27238X);
        this.engine.fpx.fp2mul_mont(pointProj.f27239Z, jArr4, pointProj.f27239Z);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void eval_3_isog(PointProj pointProj, long[][][] jArr) {
        long[][] jArr2 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr3 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr4 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        this.engine.fpx.mp2_add(pointProj.f27238X, pointProj.f27239Z, jArr2);
        this.engine.fpx.mp2_sub_p2(pointProj.f27238X, pointProj.f27239Z, jArr3);
        this.engine.fpx.fp2mul_mont(jArr[0], jArr2, jArr2);
        this.engine.fpx.fp2mul_mont(jArr[1], jArr3, jArr3);
        this.engine.fpx.mp2_add(jArr2, jArr3, jArr4);
        this.engine.fpx.mp2_sub_p2(jArr3, jArr2, jArr2);
        this.engine.fpx.fp2sqr_mont(jArr4, jArr4);
        this.engine.fpx.fp2sqr_mont(jArr2, jArr2);
        this.engine.fpx.fp2mul_mont(pointProj.f27238X, jArr4, pointProj.f27238X);
        this.engine.fpx.fp2mul_mont(pointProj.f27239Z, jArr2, pointProj.f27239Z);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void eval_4_isog(PointProj pointProj, long[][][] jArr) {
        long[][] jArr2 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr3 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        this.engine.fpx.mp2_add(pointProj.f27238X, pointProj.f27239Z, jArr2);
        this.engine.fpx.mp2_sub_p2(pointProj.f27238X, pointProj.f27239Z, jArr3);
        this.engine.fpx.fp2mul_mont(jArr2, jArr[1], pointProj.f27238X);
        this.engine.fpx.fp2mul_mont(jArr3, jArr[2], pointProj.f27239Z);
        this.engine.fpx.fp2mul_mont(jArr2, jArr3, jArr2);
        this.engine.fpx.fp2mul_mont(jArr[0], jArr2, jArr2);
        this.engine.fpx.mp2_add(pointProj.f27238X, pointProj.f27239Z, jArr3);
        this.engine.fpx.mp2_sub_p2(pointProj.f27238X, pointProj.f27239Z, pointProj.f27239Z);
        this.engine.fpx.fp2sqr_mont(jArr3, jArr3);
        this.engine.fpx.fp2sqr_mont(pointProj.f27239Z, pointProj.f27239Z);
        this.engine.fpx.mp2_add(jArr3, jArr2, pointProj.f27238X);
        this.engine.fpx.mp2_sub_p2(pointProj.f27239Z, jArr2, jArr2);
        this.engine.fpx.fp2mul_mont(pointProj.f27238X, jArr3, pointProj.f27238X);
        this.engine.fpx.fp2mul_mont(pointProj.f27239Z, jArr2, pointProj.f27239Z);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void get_2_isog(PointProj pointProj, long[][] jArr, long[][] jArr2) {
        this.engine.fpx.fp2sqr_mont(pointProj.f27238X, jArr);
        this.engine.fpx.fp2sqr_mont(pointProj.f27239Z, jArr2);
        this.engine.fpx.mp2_sub_p2(jArr2, jArr, jArr);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void get_3_isog(PointProj pointProj, long[][] jArr, long[][] jArr2, long[][][] jArr3) {
        long[][] jArr4 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr5 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr6 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr7 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr8 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        this.engine.fpx.mp2_sub_p2(pointProj.f27238X, pointProj.f27239Z, jArr3[0]);
        this.engine.fpx.fp2sqr_mont(jArr3[0], jArr4);
        this.engine.fpx.mp2_add(pointProj.f27238X, pointProj.f27239Z, jArr3[1]);
        this.engine.fpx.fp2sqr_mont(jArr3[1], jArr5);
        this.engine.fpx.mp2_add(pointProj.f27238X, pointProj.f27238X, jArr7);
        this.engine.fpx.fp2sqr_mont(jArr7, jArr7);
        this.engine.fpx.fp2sub(jArr7, jArr4, jArr6);
        this.engine.fpx.fp2sub(jArr7, jArr5, jArr7);
        this.engine.fpx.mp2_add(jArr4, jArr7, jArr8);
        this.engine.fpx.mp2_add(jArr8, jArr8, jArr8);
        this.engine.fpx.mp2_add(jArr5, jArr8, jArr8);
        this.engine.fpx.fp2mul_mont(jArr6, jArr8, jArr);
        this.engine.fpx.mp2_add(jArr5, jArr6, jArr8);
        this.engine.fpx.mp2_add(jArr8, jArr8, jArr8);
        this.engine.fpx.mp2_add(jArr4, jArr8, jArr8);
        this.engine.fpx.fp2mul_mont(jArr7, jArr8, jArr2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void get_4_isog(PointProj pointProj, long[][] jArr, long[][] jArr2, long[][][] jArr3) {
        this.engine.fpx.mp2_sub_p2(pointProj.f27238X, pointProj.f27239Z, jArr3[1]);
        this.engine.fpx.mp2_add(pointProj.f27238X, pointProj.f27239Z, jArr3[2]);
        this.engine.fpx.fp2sqr_mont(pointProj.f27239Z, jArr3[0]);
        this.engine.fpx.mp2_add(jArr3[0], jArr3[0], jArr3[0]);
        this.engine.fpx.fp2sqr_mont(jArr3[0], jArr2);
        this.engine.fpx.mp2_add(jArr3[0], jArr3[0], jArr3[0]);
        this.engine.fpx.fp2sqr_mont(pointProj.f27238X, jArr);
        this.engine.fpx.mp2_add(jArr, jArr, jArr);
        this.engine.fpx.fp2sqr_mont(jArr, jArr);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void get_A(long[][] jArr, long[][] jArr2, long[][] jArr3, long[][] jArr4) {
        long[][] jArr5 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr6 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr7 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        this.engine.fpx.fpcopy(this.engine.params.Montgomery_one, 0, jArr7[0]);
        this.engine.fpx.fp2add(jArr, jArr2, jArr6);
        this.engine.fpx.fp2mul_mont(jArr, jArr2, jArr5);
        this.engine.fpx.fp2mul_mont(jArr3, jArr6, jArr4);
        this.engine.fpx.fp2add(jArr5, jArr4, jArr4);
        this.engine.fpx.fp2mul_mont(jArr5, jArr3, jArr5);
        this.engine.fpx.fp2sub(jArr4, jArr7, jArr4);
        this.engine.fpx.fp2add(jArr5, jArr5, jArr5);
        this.engine.fpx.fp2add(jArr6, jArr3, jArr6);
        this.engine.fpx.fp2add(jArr5, jArr5, jArr5);
        this.engine.fpx.fp2sqr_mont(jArr4, jArr4);
        this.engine.fpx.fp2inv_mont(jArr5);
        this.engine.fpx.fp2mul_mont(jArr4, jArr5, jArr4);
        this.engine.fpx.fp2sub(jArr4, jArr6, jArr4);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void inv_3_way(long[][] jArr, long[][] jArr2, long[][] jArr3) {
        long[][] jArr4 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr5 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr6 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr7 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        this.engine.fpx.fp2mul_mont(jArr, jArr2, jArr4);
        this.engine.fpx.fp2mul_mont(jArr3, jArr4, jArr5);
        this.engine.fpx.fp2inv_mont(jArr5);
        this.engine.fpx.fp2mul_mont(jArr3, jArr5, jArr6);
        this.engine.fpx.fp2mul_mont(jArr6, jArr2, jArr7);
        this.engine.fpx.fp2mul_mont(jArr6, jArr, jArr2);
        this.engine.fpx.fp2mul_mont(jArr4, jArr5, jArr3);
        this.engine.fpx.fp2copy(jArr7, jArr);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void j_inv(long[][] jArr, long[][] jArr2, long[][] jArr3) {
        long[][] jArr4 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr5 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        this.engine.fpx.fp2sqr_mont(jArr, jArr3);
        this.engine.fpx.fp2sqr_mont(jArr2, jArr5);
        this.engine.fpx.fp2add(jArr5, jArr5, jArr4);
        this.engine.fpx.fp2sub(jArr3, jArr4, jArr4);
        this.engine.fpx.fp2sub(jArr4, jArr5, jArr4);
        this.engine.fpx.fp2sub(jArr4, jArr5, jArr3);
        this.engine.fpx.fp2sqr_mont(jArr5, jArr5);
        this.engine.fpx.fp2mul_mont(jArr3, jArr5, jArr3);
        this.engine.fpx.fp2add(jArr4, jArr4, jArr4);
        this.engine.fpx.fp2add(jArr4, jArr4, jArr4);
        this.engine.fpx.fp2sqr_mont(jArr4, jArr5);
        this.engine.fpx.fp2mul_mont(jArr4, jArr5, jArr4);
        this.engine.fpx.fp2add(jArr4, jArr4, jArr4);
        this.engine.fpx.fp2add(jArr4, jArr4, jArr4);
        this.engine.fpx.fp2inv_mont(jArr3);
        this.engine.fpx.fp2mul_mont(jArr3, jArr4, jArr3);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void swap_points(PointProj pointProj, PointProj pointProj2, long j) {
        for (int i = 0; i < this.engine.params.NWORDS_FIELD; i++) {
            long j2 = (pointProj.f27238X[0][i] ^ pointProj2.f27238X[0][i]) & j;
            pointProj.f27238X[0][i] = j2 ^ pointProj.f27238X[0][i];
            pointProj2.f27238X[0][i] = j2 ^ pointProj2.f27238X[0][i];
            long j3 = (pointProj.f27238X[1][i] ^ pointProj2.f27238X[1][i]) & j;
            pointProj.f27238X[1][i] = j3 ^ pointProj.f27238X[1][i];
            pointProj2.f27238X[1][i] = j3 ^ pointProj2.f27238X[1][i];
            long j4 = (pointProj.f27239Z[0][i] ^ pointProj2.f27239Z[0][i]) & j;
            pointProj.f27239Z[0][i] = j4 ^ pointProj.f27239Z[0][i];
            pointProj2.f27239Z[0][i] = j4 ^ pointProj2.f27239Z[0][i];
            long j5 = (pointProj.f27239Z[1][i] ^ pointProj2.f27239Z[1][i]) & j;
            pointProj.f27239Z[1][i] = j5 ^ pointProj.f27239Z[1][i];
            pointProj2.f27239Z[1][i] = j5 ^ pointProj2.f27239Z[1][i];
        }
    }

    protected void xDBL(PointProj pointProj, PointProj pointProj2, long[][] jArr, long[][] jArr2) {
        long[][] jArr3 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr4 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        this.engine.fpx.mp2_sub_p2(pointProj.f27238X, pointProj.f27239Z, jArr3);
        this.engine.fpx.mp2_add(pointProj.f27238X, pointProj.f27239Z, jArr4);
        this.engine.fpx.fp2sqr_mont(jArr3, jArr3);
        this.engine.fpx.fp2sqr_mont(jArr4, jArr4);
        this.engine.fpx.fp2mul_mont(jArr2, jArr3, pointProj2.f27239Z);
        this.engine.fpx.fp2mul_mont(jArr4, pointProj2.f27239Z, pointProj2.f27238X);
        this.engine.fpx.mp2_sub_p2(jArr4, jArr3, jArr4);
        this.engine.fpx.fp2mul_mont(jArr, jArr4, jArr3);
        this.engine.fpx.mp2_add(pointProj2.f27239Z, jArr3, pointProj2.f27239Z);
        this.engine.fpx.fp2mul_mont(pointProj2.f27239Z, jArr4, pointProj2.f27239Z);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void xDBLADD(PointProj pointProj, PointProj pointProj2, long[][] jArr, long[][] jArr2) {
        long[][] jArr3 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr4 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr5 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        this.engine.fpx.mp2_add(pointProj.f27238X, pointProj.f27239Z, jArr3);
        this.engine.fpx.mp2_sub_p2(pointProj.f27238X, pointProj.f27239Z, jArr4);
        this.engine.fpx.fp2sqr_mont(jArr3, pointProj.f27238X);
        this.engine.fpx.mp2_sub_p2(pointProj2.f27238X, pointProj2.f27239Z, jArr5);
        this.engine.fpx.mp2_add(pointProj2.f27238X, pointProj2.f27239Z, pointProj2.f27238X);
        this.engine.fpx.fp2mul_mont(jArr3, jArr5, jArr3);
        this.engine.fpx.fp2sqr_mont(jArr4, pointProj.f27239Z);
        this.engine.fpx.fp2mul_mont(jArr4, pointProj2.f27238X, jArr4);
        this.engine.fpx.mp2_sub_p2(pointProj.f27238X, pointProj.f27239Z, jArr5);
        this.engine.fpx.fp2mul_mont(pointProj.f27238X, pointProj.f27239Z, pointProj.f27238X);
        this.engine.fpx.fp2mul_mont(jArr2, jArr5, pointProj2.f27238X);
        this.engine.fpx.mp2_sub_p2(jArr3, jArr4, pointProj2.f27239Z);
        this.engine.fpx.mp2_add(pointProj2.f27238X, pointProj.f27239Z, pointProj.f27239Z);
        this.engine.fpx.mp2_add(jArr3, jArr4, pointProj2.f27238X);
        this.engine.fpx.fp2mul_mont(pointProj.f27239Z, jArr5, pointProj.f27239Z);
        this.engine.fpx.fp2sqr_mont(pointProj2.f27239Z, pointProj2.f27239Z);
        this.engine.fpx.fp2sqr_mont(pointProj2.f27238X, pointProj2.f27238X);
        this.engine.fpx.fp2mul_mont(pointProj2.f27239Z, jArr, pointProj2.f27239Z);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void xDBLe(PointProj pointProj, PointProj pointProj2, long[][] jArr, long[][] jArr2, int i) {
        this.engine.fpx.copy_words(pointProj, pointProj2);
        for (int i2 = 0; i2 < i; i2++) {
            xDBL(pointProj2, pointProj2, jArr, jArr2);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void xTPLe(PointProj pointProj, PointProj pointProj2, long[][] jArr, long[][] jArr2, int i) {
        this.engine.fpx.copy_words(pointProj, pointProj2);
        for (int i2 = 0; i2 < i; i2++) {
            xTPL(pointProj2, pointProj2, jArr, jArr2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void xTPLe_fast(PointProj pointProj, PointProj pointProj2, long[][] jArr, int i) {
        PointProj pointProj3 = new PointProj(this.engine.params.NWORDS_FIELD);
        this.engine.fpx.copy_words(pointProj, pointProj3);
        for (int i2 = 0; i2 < i; i2++) {
            xTPL_fast(pointProj3, pointProj3, jArr);
        }
        this.engine.fpx.copy_words(pointProj3, pointProj2);
    }
}
