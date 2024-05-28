package org.bouncycastle.pqc.crypto.sike;

import java.lang.reflect.Array;
import org.bouncycastle.util.Pack;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class Fpx {
    private SIKEEngine engine;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Fpx(SIKEEngine sIKEEngine) {
        this.engine = sIKEEngine;
    }

    private void Montgomery_inversion_mod_order_bingcd_partial(long[] jArr, long[] jArr2, int[] iArr, long[] jArr3) {
        long[] jArr4 = new long[this.engine.params.NWORDS_ORDER];
        long[] jArr5 = new long[this.engine.params.NWORDS_ORDER];
        long[] jArr6 = new long[this.engine.params.NWORDS_ORDER];
        copy_words(jArr, jArr4, this.engine.params.NWORDS_ORDER);
        copy_words(jArr3, jArr5, this.engine.params.NWORDS_ORDER);
        copy_words(jArr6, jArr2, this.engine.params.NWORDS_ORDER);
        jArr2[0] = 1;
        iArr[0] = 0;
        while (!is_zero_mod_order(jArr5)) {
            int i = ((iArr[0] + 1) / 64) + 1;
            if (i < this.engine.params.NWORDS_ORDER) {
                if (is_even_mod_order(jArr5)) {
                    mp_shiftr1(jArr5, this.engine.params.NWORDS_ORDER);
                } else {
                    if (is_even_mod_order(jArr4)) {
                        mp_shiftr1(jArr4, this.engine.params.NWORDS_ORDER);
                    } else if (is_lt_mod_order(jArr5, jArr4)) {
                        mp_sub(jArr4, jArr5, jArr4, this.engine.params.NWORDS_ORDER);
                        mp_shiftr1(jArr4, this.engine.params.NWORDS_ORDER);
                        mp_add(jArr2, jArr6, jArr2, i);
                    } else {
                        mp_sub(jArr5, jArr4, jArr5, this.engine.params.NWORDS_ORDER);
                        mp_shiftr1(jArr5, this.engine.params.NWORDS_ORDER);
                        mp_add(jArr2, jArr6, jArr6, i);
                    }
                    mp_shiftl1(jArr6, i);
                }
                mp_shiftl1(jArr2, i);
            } else {
                if (is_even_mod_order(jArr5)) {
                    mp_shiftr1(jArr5, this.engine.params.NWORDS_ORDER);
                } else {
                    if (is_even_mod_order(jArr4)) {
                        mp_shiftr1(jArr4, this.engine.params.NWORDS_ORDER);
                    } else if (is_lt_mod_order(jArr5, jArr4)) {
                        mp_sub(jArr4, jArr5, jArr4, this.engine.params.NWORDS_ORDER);
                        mp_shiftr1(jArr4, this.engine.params.NWORDS_ORDER);
                        mp_add(jArr2, jArr6, jArr2, this.engine.params.NWORDS_ORDER);
                    } else {
                        mp_sub(jArr5, jArr4, jArr5, this.engine.params.NWORDS_ORDER);
                        mp_shiftr1(jArr5, this.engine.params.NWORDS_ORDER);
                        mp_add(jArr2, jArr6, jArr6, this.engine.params.NWORDS_ORDER);
                    }
                    i = this.engine.params.NWORDS_ORDER;
                    mp_shiftl1(jArr6, i);
                }
                i = this.engine.params.NWORDS_ORDER;
                mp_shiftl1(jArr2, i);
            }
            iArr[0] = iArr[0] + 1;
        }
        if (is_lt_mod_order(jArr3, jArr2)) {
            mp_sub(jArr2, jArr3, jArr2, this.engine.params.NWORDS_ORDER);
        }
    }

    private void digit_x_digit(long j, long j2, long[] jArr) {
        long j3 = j & 4294967295L;
        long j4 = j >>> 32;
        long j5 = j2 & 4294967295L;
        long j6 = j2 >>> 32;
        long j7 = j3 * j5;
        long j8 = j3 * j6;
        long j9 = j5 * j4;
        long j10 = j4 * j6;
        jArr[0] = j7 & 4294967295L;
        long j11 = (j7 >>> 32) + (j9 & 4294967295L) + (j8 & 4294967295L);
        long j12 = j11 >>> 32;
        jArr[0] = (j11 << 32) ^ jArr[0];
        long j13 = (j9 >>> 32) + (j8 >>> 32) + (j10 & 4294967295L) + j12;
        jArr[1] = 4294967295L & j13;
        jArr[1] = (((-4294967296L) & j10) + (j13 & (-4294967296L))) ^ jArr[1];
    }

    private void fpdiv2_PRIME(long[] jArr, long[] jArr2) {
        long j = 0 - (jArr[0] & 1);
        int i = 0;
        for (int i2 = 0; i2 < this.engine.params.NWORDS_FIELD; i2++) {
            long j2 = i;
            long j3 = jArr[i2] + j2;
            jArr2[i2] = (this.engine.params.PRIME[i2] & j) + j3;
            i = is_digit_lessthan_ct(j3, j2) | is_digit_lessthan_ct(jArr2[i2], j3);
        }
        mp_shiftr1(jArr2);
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: CFG modification limit reached, blocks count: 689
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:59)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
        */
    private void fpinv_chain_mont(long[] r17) {
        /*
            Method dump skipped, instructions count: 3044
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bouncycastle.pqc.crypto.sike.Fpx.fpinv_chain_mont(long[]):void");
    }

    private void fpinv_mont(long[] jArr) {
        long[] jArr2 = new long[this.engine.params.NWORDS_FIELD];
        fpcopy(jArr, 0, jArr2);
        fpinv_chain_mont(jArr2);
        fpsqr_mont(jArr2, jArr2);
        fpsqr_mont(jArr2, jArr2);
        fpmul_mont(jArr, jArr2, jArr);
    }

    private void fpinv_mont_bingcd(long[] jArr) {
        long[] jArr2 = new long[this.engine.params.NWORDS_FIELD];
        long[] jArr3 = new long[this.engine.params.NWORDS_FIELD];
        int[] iArr = new int[1];
        if (is_felm_zero(jArr)) {
            return;
        }
        fpinv_mont_bingcd_partial(jArr, jArr2, iArr);
        if (iArr[0] <= this.engine.params.MAXBITS_FIELD) {
            fpmul_mont(jArr2, this.engine.params.Montgomery_R2, jArr2);
            iArr[0] = iArr[0] + this.engine.params.MAXBITS_FIELD;
        }
        fpmul_mont(jArr2, this.engine.params.Montgomery_R2, jArr2);
        power2_setup(jArr3, (this.engine.params.MAXBITS_FIELD * 2) - iArr[0], this.engine.params.NWORDS_FIELD);
        fpmul_mont(jArr2, jArr3, jArr);
    }

    private void fpinv_mont_bingcd_partial(long[] jArr, long[] jArr2, int[] iArr) {
        long[] jArr3 = new long[this.engine.params.NWORDS_FIELD];
        long[] jArr4 = new long[this.engine.params.NWORDS_FIELD];
        long[] jArr5 = new long[this.engine.params.NWORDS_FIELD];
        fpcopy(jArr, 0, jArr3);
        fpcopy(this.engine.params.PRIME, 0, jArr4);
        fpzero(jArr2);
        jArr2[0] = 1;
        fpzero(jArr5);
        iArr[0] = 0;
        while (!is_felm_zero(jArr4)) {
            int i = ((iArr[0] + 1) / 64) + 1;
            if (i < this.engine.params.NWORDS_FIELD) {
                if (is_felm_even(jArr4)) {
                    mp_shiftr1(jArr4);
                } else {
                    if (is_felm_even(jArr3)) {
                        mp_shiftr1(jArr3);
                    } else if (is_felm_lt(jArr4, jArr3)) {
                        mp_sub(jArr3, jArr4, jArr3, this.engine.params.NWORDS_FIELD);
                        mp_shiftr1(jArr3);
                        mp_add(jArr2, jArr5, jArr2, i);
                    } else {
                        mp_sub(jArr4, jArr3, jArr4, this.engine.params.NWORDS_FIELD);
                        mp_shiftr1(jArr4);
                        mp_add(jArr2, jArr5, jArr5, i);
                    }
                    mp_shiftl1(jArr5, i);
                }
                mp_shiftl1(jArr2, i);
            } else {
                if (is_felm_even(jArr4)) {
                    mp_shiftr1(jArr4);
                } else {
                    if (is_felm_even(jArr3)) {
                        mp_shiftr1(jArr3);
                    } else if (is_felm_lt(jArr4, jArr3)) {
                        mp_sub(jArr3, jArr4, jArr3, this.engine.params.NWORDS_FIELD);
                        mp_shiftr1(jArr3);
                        mp_add(jArr2, jArr5, jArr2, this.engine.params.NWORDS_FIELD);
                    } else {
                        mp_sub(jArr4, jArr3, jArr4, this.engine.params.NWORDS_FIELD);
                        mp_shiftr1(jArr4);
                        mp_add(jArr2, jArr5, jArr5, this.engine.params.NWORDS_FIELD);
                    }
                    i = this.engine.params.NWORDS_FIELD;
                    mp_shiftl1(jArr5, i);
                }
                i = this.engine.params.NWORDS_FIELD;
                mp_shiftl1(jArr2, i);
            }
            iArr[0] = iArr[0] + 1;
        }
        if (is_felm_lt(this.engine.params.PRIME, jArr2)) {
            mp_sub(jArr2, this.engine.params.PRIME, jArr2, this.engine.params.NWORDS_FIELD);
        }
    }

    private void from_mont(long[] jArr, long[] jArr2) {
        long[] jArr3 = new long[this.engine.params.NWORDS_FIELD];
        jArr3[0] = 1;
        fpmul_mont(jArr, jArr3, jArr2);
        fpcorrectionPRIME(jArr2);
    }

    private int is_digit_lessthan_ct(long j, long j2) {
        return (int) ((j ^ ((j2 ^ (j - j2)) | (j ^ j2))) >>> 63);
    }

    private int is_digit_nonzero_ct(long j) {
        return (int) ((j | (0 - j)) >>> 63);
    }

    private int is_digit_zero_ct(long j) {
        return is_digit_nonzero_ct(j) ^ 1;
    }

    private boolean is_even_mod_order(long[] jArr) {
        return ((jArr[0] & 1) ^ 1) == 1;
    }

    private static boolean is_felm_even(long[] jArr) {
        return (jArr[0] & 1) == 0;
    }

    private boolean is_felm_lt(long[] jArr, long[] jArr2) {
        for (int i = this.engine.params.NWORDS_FIELD - 1; i >= 0; i--) {
            if (jArr[i] - Long.MIN_VALUE < jArr2[i] - Long.MIN_VALUE) {
                return true;
            }
            if (jArr[i] - Long.MIN_VALUE > jArr2[i] - Long.MIN_VALUE) {
                return false;
            }
        }
        return false;
    }

    private boolean is_lt_mod_order(long[] jArr, long[] jArr2) {
        for (int i = this.engine.params.NWORDS_ORDER - 1; i >= 0; i--) {
            if (jArr[i] - Long.MIN_VALUE < jArr2[i] - Long.MIN_VALUE) {
                return true;
            }
            if (jArr[i] - Long.MIN_VALUE > jArr2[i] - Long.MIN_VALUE) {
                return false;
            }
        }
        return false;
    }

    private boolean is_zero(long[] jArr, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            if (jArr[i2] != 0) {
                return false;
            }
        }
        return true;
    }

    private boolean is_zero_mod_order(long[] jArr) {
        for (int i = 0; i < this.engine.params.NWORDS_ORDER; i++) {
            if (jArr[i] != 0) {
                return false;
            }
        }
        return true;
    }

    private void mp2_sub_p4(long[][] jArr, long[][] jArr2, long[][] jArr3) {
        mp_subPRIME_p4(jArr[0], jArr2[0], jArr3[0]);
        mp_subPRIME_p4(jArr[1], jArr2[1], jArr3[1]);
    }

    private int mp_add(long[] jArr, int i, long[] jArr2, int i2, long[] jArr3, int i3, int i4) {
        int i5 = 0;
        for (int i6 = 0; i6 < i4; i6++) {
            long j = i5;
            long j2 = jArr[i6 + i] + j;
            int i7 = i6 + i3;
            jArr3[i7] = jArr2[i6 + i2] + j2;
            i5 = is_digit_lessthan_ct(jArr3[i7], j2) | is_digit_lessthan_ct(j2, j);
        }
        return i5;
    }

    private int mp_add(long[] jArr, int i, long[] jArr2, long[] jArr3, int i2, int i3) {
        int i4 = 0;
        for (int i5 = 0; i5 < i3; i5++) {
            long j = i4;
            long j2 = jArr[i5 + i] + j;
            int i6 = i5 + i2;
            jArr3[i6] = jArr2[i5] + j2;
            i4 = is_digit_lessthan_ct(jArr3[i6], j2) | is_digit_lessthan_ct(j2, j);
        }
        return i4;
    }

    private void mp_dblsubfast(long[] jArr, long[] jArr2, long[] jArr3) {
        mp_sub(jArr3, jArr, jArr3, this.engine.params.NWORDS_FIELD * 2);
        mp_sub(jArr3, jArr2, jArr3, this.engine.params.NWORDS_FIELD * 2);
    }

    private void mp_shiftl1(long[] jArr, int i) {
        for (int i2 = i - 1; i2 > 0; i2--) {
            jArr[i2] = (jArr[i2] << 1) ^ (jArr[i2 - 1] >>> 63);
        }
        jArr[0] = jArr[0] << 1;
    }

    private void mp_shiftr1(long[] jArr) {
        int i = 0;
        while (i < this.engine.params.NWORDS_FIELD - 1) {
            int i2 = i + 1;
            jArr[i] = (jArr[i] >>> 1) ^ (jArr[i2] << 63);
            i = i2;
        }
        int i3 = this.engine.params.NWORDS_FIELD - 1;
        jArr[i3] = jArr[i3] >>> 1;
    }

    private void mp_shiftr1(long[] jArr, int i) {
        int i2 = 0;
        while (true) {
            int i3 = i - 1;
            if (i2 >= i3) {
                jArr[i3] = jArr[i3] >>> 1;
                return;
            }
            int i4 = i2 + 1;
            jArr[i2] = (jArr[i2] >>> 1) ^ (jArr[i4] << 63);
            i2 = i4;
        }
    }

    private void mp_subPRIME_p2(long[] jArr, long[] jArr2, long[] jArr3) {
        int i = 0;
        int i2 = 0;
        while (i < this.engine.params.NWORDS_FIELD) {
            long j = jArr[i] - jArr2[i];
            int is_digit_lessthan_ct = is_digit_lessthan_ct(jArr[i], jArr2[i]) | (is_digit_zero_ct(j) & i2);
            jArr3[i] = j - i2;
            i++;
            i2 = is_digit_lessthan_ct;
        }
        int i3 = 0;
        for (int i4 = 0; i4 < this.engine.params.NWORDS_FIELD; i4++) {
            long j2 = i3;
            long j3 = jArr3[i4] + j2;
            jArr3[i4] = this.engine.params.PRIMEx2[i4] + j3;
            i3 = is_digit_lessthan_ct(j3, j2) | is_digit_lessthan_ct(jArr3[i4], j3);
        }
    }

    private void mp_subPRIME_p4(long[] jArr, long[] jArr2, long[] jArr3) {
        int i = 0;
        int i2 = 0;
        while (i < this.engine.params.NWORDS_FIELD) {
            long j = jArr[i] - jArr2[i];
            int is_digit_lessthan_ct = is_digit_lessthan_ct(jArr[i], jArr2[i]) | (is_digit_zero_ct(j) & i2);
            jArr3[i] = j - i2;
            i++;
            i2 = is_digit_lessthan_ct;
        }
        int i3 = 0;
        for (int i4 = 0; i4 < this.engine.params.NWORDS_FIELD; i4++) {
            long j2 = i3;
            long j3 = jArr3[i4] + j2;
            jArr3[i4] = this.engine.params.PRIMEx4[i4] + j3;
            i3 = is_digit_lessthan_ct(j3, j2) | is_digit_lessthan_ct(jArr3[i4], j3);
        }
    }

    private void mp_subaddfast(long[] jArr, long[] jArr2, long[] jArr3) {
        long[] jArr4 = new long[this.engine.params.NWORDS_FIELD];
        long mp_sub = 0 - mp_sub(jArr, jArr2, jArr3, this.engine.params.NWORDS_FIELD * 2);
        for (int i = 0; i < this.engine.params.NWORDS_FIELD; i++) {
            jArr4[i] = this.engine.params.PRIME[i] & mp_sub;
        }
        mp_add(jArr3, this.engine.params.NWORDS_FIELD, jArr4, jArr3, this.engine.params.NWORDS_FIELD, this.engine.params.NWORDS_FIELD);
    }

    private void power2_setup(long[] jArr, int i, int i2) {
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            jArr[i4] = 0;
        }
        while (i >= 0) {
            if (i < 64) {
                jArr[i3] = 1 << i;
            }
            i -= 64;
            i3++;
        }
    }

    private void rdc_mont(long[] jArr, long[] jArr2) {
        int is_digit_lessthan_ct;
        long is_digit_lessthan_ct2;
        long j;
        long is_digit_lessthan_ct3;
        long is_digit_lessthan_ct4;
        int is_digit_lessthan_ct5;
        long j2;
        long is_digit_lessthan_ct6;
        long is_digit_lessthan_ct7;
        int i = this.engine.params.PRIME_ZERO_WORDS;
        long[] jArr3 = new long[2];
        for (int i2 = 0; i2 < this.engine.params.NWORDS_FIELD; i2++) {
            jArr2[i2] = 0;
        }
        int i3 = 0;
        long j3 = 0;
        long j4 = 0;
        while (i3 < this.engine.params.NWORDS_FIELD) {
            long j5 = j3;
            long j6 = j4;
            long j7 = 0;
            for (int i4 = 0; i4 < i3; i4++) {
                if (i4 < (i3 - this.engine.params.PRIME_ZERO_WORDS) + 1) {
                    digit_x_digit(jArr2[i4], this.engine.params.PRIMEp1[i3 - i4], jArr3);
                    long j8 = j6 + jArr3[0];
                    j7 += is_digit_lessthan_ct(is_digit_lessthan_ct7, is_digit_lessthan_ct6);
                    j5 += jArr3[1] + is_digit_lessthan_ct(j8, j2);
                    j6 = j8;
                }
            }
            long j9 = jArr[i3];
            long j10 = j6 + j9;
            long is_digit_lessthan_ct8 = j5 + is_digit_lessthan_ct(j10, j9);
            j3 = j7 + (is_digit_lessthan_ct5 & is_digit_zero_ct(is_digit_lessthan_ct8));
            jArr2[i3] = j10;
            i3++;
            j4 = is_digit_lessthan_ct8;
        }
        int i5 = this.engine.params.NWORDS_FIELD;
        long j11 = j3;
        int i6 = i;
        while (i5 < (this.engine.params.NWORDS_FIELD * 2) - 1) {
            if (i6 > 0) {
                i6--;
            }
            int i7 = i6;
            long j12 = j4;
            long j13 = j11;
            long j14 = 0;
            for (int i8 = (i5 - this.engine.params.NWORDS_FIELD) + 1; i8 < this.engine.params.NWORDS_FIELD; i8++) {
                if (i8 < this.engine.params.NWORDS_FIELD - i7) {
                    digit_x_digit(jArr2[i8], this.engine.params.PRIMEp1[i5 - i8], jArr3);
                    long j15 = j12 + jArr3[0];
                    j14 += is_digit_lessthan_ct(is_digit_lessthan_ct4, is_digit_lessthan_ct3);
                    j13 += jArr3[1] + is_digit_lessthan_ct(j15, j);
                    j12 = j15;
                }
            }
            long j16 = jArr[i5];
            long j17 = j12 + j16;
            jArr2[i5 - this.engine.params.NWORDS_FIELD] = j17;
            i5++;
            j4 = j13 + is_digit_lessthan_ct(j17, j16);
            j11 = j14 + (is_digit_lessthan_ct & is_digit_zero_ct(is_digit_lessthan_ct2));
            i6 = i7;
        }
        long j18 = jArr[(this.engine.params.NWORDS_FIELD * 2) - 1];
        long j19 = j4 + j18;
        is_digit_lessthan_ct(j19, j18);
        jArr2[this.engine.params.NWORDS_FIELD - 1] = j19;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static boolean subarrayEquals(long[] jArr, long[] jArr2, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            if (jArr[i2] != jArr2[i2]) {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static boolean subarrayEquals(long[][] jArr, long[] jArr2, int i, int i2) {
        int length = jArr[0].length;
        for (int i3 = 0; i3 < i2; i3++) {
            if (jArr[i3 / length][i3 % length] != jArr2[i3 + i]) {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static boolean subarrayEquals(long[][] jArr, long[][] jArr2, int i) {
        int length = jArr2[0].length;
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = i2 / length;
            int i4 = i2 % length;
            if (jArr[i3][i4] != jArr2[i3][i4]) {
                return false;
            }
        }
        return true;
    }

    protected static boolean subarrayEquals(long[][] jArr, long[][] jArr2, int i, int i2) {
        int length = jArr2[0].length;
        for (int i3 = 0; i3 < i2; i3++) {
            int i4 = i3 + i;
            if (jArr[i3 / length][i3 % length] != jArr2[i4 / length][i4 % length]) {
                return false;
            }
        }
        return true;
    }

    private void to_mont(long[] jArr, long[] jArr2) {
        fpmul_mont(jArr, this.engine.params.Montgomery_R2, jArr2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void Montgomery_inversion_mod_order_bingcd(long[] jArr, long[] jArr2, long[] jArr3, long[] jArr4, long[] jArr5) {
        long[] jArr6 = new long[this.engine.params.NWORDS_ORDER];
        long[] jArr7 = new long[this.engine.params.NWORDS_ORDER];
        int[] iArr = new int[1];
        if (is_zero(jArr, this.engine.params.NWORDS_ORDER)) {
            copy_words(jArr7, jArr2, this.engine.params.NWORDS_ORDER);
            return;
        }
        Montgomery_inversion_mod_order_bingcd_partial(jArr, jArr6, iArr, jArr3);
        if (iArr[0] <= this.engine.params.NBITS_ORDER) {
            Montgomery_multiply_mod_order(jArr6, jArr5, jArr6, jArr3, jArr4);
            iArr[0] = iArr[0] + this.engine.params.NBITS_ORDER;
        }
        Montgomery_multiply_mod_order(jArr6, jArr5, jArr6, jArr3, jArr4);
        power2_setup(jArr7, (this.engine.params.NBITS_ORDER * 2) - iArr[0], this.engine.params.NWORDS_ORDER);
        Montgomery_multiply_mod_order(jArr6, jArr7, jArr2, jArr3, jArr4);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void Montgomery_multiply_mod_order(long[] jArr, long[] jArr2, long[] jArr3, long[] jArr4, long[] jArr5) {
        long[] jArr6 = new long[this.engine.params.NWORDS_ORDER * 2];
        long[] jArr7 = new long[this.engine.params.NWORDS_ORDER * 2];
        long[] jArr8 = new long[this.engine.params.NWORDS_ORDER * 2];
        multiply(jArr, jArr2, jArr6, this.engine.params.NWORDS_ORDER);
        multiply(jArr6, jArr5, jArr7, this.engine.params.NWORDS_ORDER);
        multiply(jArr7, jArr4, jArr8, this.engine.params.NWORDS_ORDER);
        int mp_add = mp_add(jArr6, jArr8, jArr8, this.engine.params.NWORDS_ORDER * 2);
        for (int i = 0; i < this.engine.params.NWORDS_ORDER; i++) {
            jArr3[i] = jArr8[this.engine.params.NWORDS_ORDER + i];
        }
        long mp_sub = mp_add - mp_sub(jArr3, jArr4, jArr3, this.engine.params.NWORDS_ORDER);
        for (int i2 = 0; i2 < this.engine.params.NWORDS_ORDER; i2++) {
            jArr8[i2] = jArr4[i2] & mp_sub;
        }
        mp_add(jArr3, jArr8, jArr3, this.engine.params.NWORDS_ORDER);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void Montgomery_neg(long[] jArr, long[] jArr2) {
        int i = 0;
        int i2 = 0;
        while (i < this.engine.params.NWORDS_ORDER) {
            long j = jArr2[i] - jArr[i];
            int is_digit_lessthan_ct = is_digit_lessthan_ct(jArr2[i], jArr[i]) | (is_digit_zero_ct(j) & i2);
            jArr[i] = j - i2;
            i++;
            i2 = is_digit_lessthan_ct;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public byte cmp_f2elm(long[][] jArr, long[][] jArr2) {
        long[][] jArr3 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        long[][] jArr4 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        fp2copy(jArr, jArr3);
        fp2copy(jArr2, jArr4);
        fp2correction(jArr3);
        fp2correction(jArr4);
        byte b = 0;
        for (int i = this.engine.params.NWORDS_FIELD - 1; i >= 0; i--) {
            b = (byte) (b | (jArr3[0][i] ^ jArr4[0][i]) | (jArr3[1][i] ^ jArr4[1][i]));
        }
        return (byte) ((-b) >>> 7);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void copy_words(PointProj pointProj, PointProj pointProj2) {
        for (int i = 0; i < this.engine.params.NWORDS_FIELD; i++) {
            pointProj2.f27238X[0][i] = pointProj.f27238X[0][i];
            pointProj2.f27238X[1][i] = pointProj.f27238X[1][i];
            pointProj2.f27239Z[0][i] = pointProj.f27239Z[0][i];
            pointProj2.f27239Z[1][i] = pointProj.f27239Z[1][i];
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void copy_words(long[] jArr, long[] jArr2, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            jArr2[i2] = jArr[i2];
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void ct_cmov(byte[] bArr, byte[] bArr2, int i, byte b) {
        for (int i2 = 0; i2 < i; i2++) {
            bArr[i2] = (byte) (bArr[i2] ^ ((bArr2[i2] ^ bArr[i2]) & b));
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public byte ct_compare(byte[] bArr, byte[] bArr2, int i) {
        byte b = 0;
        for (int i2 = 0; i2 < i; i2++) {
            b = (byte) (b | (bArr[i2] ^ bArr2[i2]));
        }
        return (byte) ((-b) >>> 7);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void cube_Fp2_cycl(long[][] jArr, long[] jArr2) {
        long[] jArr3 = new long[this.engine.params.NWORDS_FIELD];
        fpaddPRIME(jArr[0], jArr[0], jArr3);
        fpsqr_mont(jArr3, jArr3);
        fpsubPRIME(jArr3, jArr2, jArr3);
        fpmul_mont(jArr[1], jArr3, jArr[1]);
        fpsubPRIME(jArr3, jArr2, jArr3);
        fpsubPRIME(jArr3, jArr2, jArr3);
        fpmul_mont(jArr[0], jArr3, jArr[0]);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void decode_to_digits(byte[] bArr, int i, long[] jArr, int i2, int i3) {
        jArr[i3 - 1] = 0;
        byte[] bArr2 = new byte[(i2 + 7) & (-8)];
        System.arraycopy(bArr, i, bArr2, 0, i2);
        Pack.littleEndianToLong(bArr2, 0, jArr);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void encode_to_bytes(long[] jArr, byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[((i2 * 4) + 7) & (-8)];
        Pack.longToLittleEndian(jArr, bArr2, 0);
        System.arraycopy(bArr2, 0, bArr, i, i2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void fp2_conj(long[][] jArr, long[][] jArr2) {
        fpcopy(jArr[0], 0, jArr2[0]);
        fpcopy(jArr[1], 0, jArr2[1]);
        if (is_felm_zero(jArr2[1])) {
            return;
        }
        fpnegPRIME(jArr2[1]);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void fp2_decode(byte[] bArr, long[][] jArr, int i) {
        decode_to_digits(bArr, i, jArr[0], this.engine.params.FP2_ENCODED_BYTES / 2, this.engine.params.NWORDS_FIELD);
        decode_to_digits(bArr, i + (this.engine.params.FP2_ENCODED_BYTES / 2), jArr[1], this.engine.params.FP2_ENCODED_BYTES / 2, this.engine.params.NWORDS_FIELD);
        to_fp2mont(jArr, jArr);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void fp2_encode(long[][] jArr, byte[] bArr, int i) {
        long[][] jArr2 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        from_fp2mont(jArr, jArr2);
        encode_to_bytes(jArr2[0], bArr, i, this.engine.params.FP2_ENCODED_BYTES / 2);
        encode_to_bytes(jArr2[1], bArr, i + (this.engine.params.FP2_ENCODED_BYTES / 2), this.engine.params.FP2_ENCODED_BYTES / 2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void fp2add(long[][] jArr, long[][] jArr2, long[][] jArr3) {
        fpaddPRIME(jArr[0], jArr2[0], jArr3[0]);
        fpaddPRIME(jArr[1], jArr2[1], jArr3[1]);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void fp2copy(long[] jArr, int i, long[][] jArr2) {
        fpcopy(jArr, i, jArr2[0]);
        fpcopy(jArr, i + this.engine.params.NWORDS_FIELD, jArr2[1]);
    }

    protected void fp2copy(long[][] jArr, int i, long[][] jArr2) {
        fpcopy(jArr[i + 0], 0, jArr2[0]);
        fpcopy(jArr[i + 1], 0, jArr2[1]);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void fp2copy(long[][] jArr, long[][] jArr2) {
        fpcopy(jArr[0], 0, jArr2[0]);
        fpcopy(jArr[1], 0, jArr2[1]);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void fp2correction(long[][] jArr) {
        fpcorrectionPRIME(jArr[0]);
        fpcorrectionPRIME(jArr[1]);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void fp2div2(long[][] jArr, long[][] jArr2) {
        fpdiv2_PRIME(jArr[0], jArr2[0]);
        fpdiv2_PRIME(jArr[1], jArr2[1]);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void fp2inv_mont(long[][] jArr) {
        long[][] jArr2 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        fpsqr_mont(jArr[0], jArr2[0]);
        fpsqr_mont(jArr[1], jArr2[1]);
        fpaddPRIME(jArr2[0], jArr2[1], jArr2[0]);
        fpinv_mont(jArr2[0]);
        fpnegPRIME(jArr[1]);
        fpmul_mont(jArr[0], jArr2[0], jArr[0]);
        fpmul_mont(jArr[1], jArr2[0], jArr[1]);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void fp2inv_mont_bingcd(long[][] jArr) {
        long[][] jArr2 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        fpsqr_mont(jArr[0], jArr2[0]);
        fpsqr_mont(jArr[1], jArr2[1]);
        fpaddPRIME(jArr2[0], jArr2[1], jArr2[0]);
        fpinv_mont_bingcd(jArr2[0]);
        fpnegPRIME(jArr[1]);
        fpmul_mont(jArr[0], jArr2[0], jArr[0]);
        fpmul_mont(jArr[1], jArr2[0], jArr[1]);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void fp2mul_mont(long[][] jArr, long[] jArr2, int i, long[][] jArr3) {
        long[] jArr4 = new long[this.engine.params.NWORDS_FIELD];
        long[] jArr5 = new long[this.engine.params.NWORDS_FIELD];
        long[] jArr6 = new long[this.engine.params.NWORDS_FIELD * 2];
        long[] jArr7 = new long[this.engine.params.NWORDS_FIELD * 2];
        long[] jArr8 = new long[this.engine.params.NWORDS_FIELD * 2];
        mp_add(jArr[0], jArr[1], jArr4, this.engine.params.NWORDS_FIELD);
        mp_add(jArr2, i, jArr2, i + this.engine.params.NWORDS_FIELD, jArr5, 0, this.engine.params.NWORDS_FIELD);
        mp_mul(jArr2, i, jArr[0], jArr6, this.engine.params.NWORDS_FIELD);
        mp_mul(jArr2, i + this.engine.params.NWORDS_FIELD, jArr[1], jArr7, this.engine.params.NWORDS_FIELD);
        mp_mul(jArr4, jArr5, jArr8, this.engine.params.NWORDS_FIELD);
        mp_dblsubfast(jArr6, jArr7, jArr8);
        mp_subaddfast(jArr6, jArr7, jArr6);
        rdc_mont(jArr8, jArr3[1]);
        rdc_mont(jArr6, jArr3[0]);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void fp2mul_mont(long[][] jArr, long[][] jArr2, int i, long[][] jArr3) {
        long[] jArr4 = new long[this.engine.params.NWORDS_FIELD];
        long[] jArr5 = new long[this.engine.params.NWORDS_FIELD];
        long[] jArr6 = new long[this.engine.params.NWORDS_FIELD * 2];
        long[] jArr7 = new long[this.engine.params.NWORDS_FIELD * 2];
        long[] jArr8 = new long[this.engine.params.NWORDS_FIELD * 2];
        mp_add(jArr[0], jArr[1], jArr4, this.engine.params.NWORDS_FIELD);
        int i2 = i + 0;
        int i3 = i + 1;
        mp_add(jArr2[i2], jArr2[i3], jArr5, this.engine.params.NWORDS_FIELD);
        mp_mul(jArr[0], jArr2[i2], jArr6, this.engine.params.NWORDS_FIELD);
        mp_mul(jArr[1], jArr2[i3], jArr7, this.engine.params.NWORDS_FIELD);
        mp_mul(jArr4, jArr5, jArr8, this.engine.params.NWORDS_FIELD);
        mp_dblsubfast(jArr6, jArr7, jArr8);
        mp_subaddfast(jArr6, jArr7, jArr6);
        rdc_mont(jArr8, jArr3[1]);
        rdc_mont(jArr6, jArr3[0]);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void fp2mul_mont(long[][] jArr, long[][] jArr2, long[][] jArr3) {
        long[] jArr4 = new long[this.engine.params.NWORDS_FIELD];
        long[] jArr5 = new long[this.engine.params.NWORDS_FIELD];
        long[] jArr6 = new long[this.engine.params.NWORDS_FIELD * 2];
        long[] jArr7 = new long[this.engine.params.NWORDS_FIELD * 2];
        long[] jArr8 = new long[this.engine.params.NWORDS_FIELD * 2];
        mp_add(jArr[0], jArr[1], jArr4, this.engine.params.NWORDS_FIELD);
        mp_add(jArr2[0], jArr2[1], jArr5, this.engine.params.NWORDS_FIELD);
        mp_mul(jArr[0], jArr2[0], jArr6, this.engine.params.NWORDS_FIELD);
        mp_mul(jArr[1], jArr2[1], jArr7, this.engine.params.NWORDS_FIELD);
        mp_mul(jArr4, jArr5, jArr8, this.engine.params.NWORDS_FIELD);
        mp_dblsubfast(jArr6, jArr7, jArr8);
        mp_subaddfast(jArr6, jArr7, jArr6);
        rdc_mont(jArr8, jArr3[1]);
        rdc_mont(jArr6, jArr3[0]);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void fp2neg(long[][] jArr) {
        fpnegPRIME(jArr[0]);
        fpnegPRIME(jArr[1]);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void fp2shl(long[][] jArr, int i, long[][] jArr2) {
        fp2copy(jArr, jArr2);
        for (int i2 = 0; i2 < i; i2++) {
            fp2add(jArr2, jArr2, jArr2);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void fp2sqr_mont(long[][] jArr, long[][] jArr2) {
        long[] jArr3 = new long[this.engine.params.NWORDS_FIELD];
        long[] jArr4 = new long[this.engine.params.NWORDS_FIELD];
        long[] jArr5 = new long[this.engine.params.NWORDS_FIELD];
        mp_add(jArr[0], jArr[1], jArr3, this.engine.params.NWORDS_FIELD);
        mp_subPRIME_p4(jArr[0], jArr[1], jArr4);
        mp_add(jArr[0], jArr[0], jArr5, this.engine.params.NWORDS_FIELD);
        fpmul_mont(jArr3, jArr4, jArr2[0]);
        fpmul_mont(jArr5, jArr[1], jArr2[1]);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void fp2sub(long[][] jArr, long[][] jArr2, long[][] jArr3) {
        fpsubPRIME(jArr[0], jArr2[0], jArr3[0]);
        fpsubPRIME(jArr[1], jArr2[1], jArr3[1]);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void fpaddPRIME(long[] jArr, long[] jArr2, long[] jArr3) {
        int i = 0;
        for (int i2 = 0; i2 < this.engine.params.NWORDS_FIELD; i2++) {
            long j = i;
            long j2 = jArr[i2] + j;
            jArr3[i2] = jArr2[i2] + j2;
            i = is_digit_lessthan_ct(j2, j) | is_digit_lessthan_ct(jArr3[i2], j2);
        }
        int i3 = 0;
        int i4 = 0;
        while (i3 < this.engine.params.NWORDS_FIELD) {
            long j3 = jArr3[i3] - this.engine.params.PRIMEx2[i3];
            int is_digit_lessthan_ct = is_digit_lessthan_ct(jArr3[i3], this.engine.params.PRIMEx2[i3]) | (is_digit_zero_ct(j3) & i4);
            jArr3[i3] = j3 - i4;
            i3++;
            i4 = is_digit_lessthan_ct;
        }
        long j4 = 0 - i4;
        int i5 = 0;
        for (int i6 = 0; i6 < this.engine.params.NWORDS_FIELD; i6++) {
            long j5 = i5;
            long j6 = jArr3[i6] + j5;
            jArr3[i6] = (this.engine.params.PRIMEx2[i6] & j4) + j6;
            i5 = is_digit_lessthan_ct(j6, j5) | is_digit_lessthan_ct(jArr3[i6], j6);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void fpcopy(long[] jArr, int i, long[] jArr2) {
        for (int i2 = 0; i2 < this.engine.params.NWORDS_FIELD; i2++) {
            jArr2[i2] = jArr[i2 + i];
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void fpcorrectionPRIME(long[] jArr) {
        int i = 0;
        int i2 = 0;
        while (i < this.engine.params.NWORDS_FIELD) {
            long j = jArr[i] - this.engine.params.PRIME[i];
            int is_digit_lessthan_ct = is_digit_lessthan_ct(jArr[i], this.engine.params.PRIME[i]) | (is_digit_zero_ct(j) & i2);
            jArr[i] = j - i2;
            i++;
            i2 = is_digit_lessthan_ct;
        }
        long j2 = 0 - i2;
        int i3 = 0;
        for (int i4 = 0; i4 < this.engine.params.NWORDS_FIELD; i4++) {
            long j3 = i3;
            long j4 = jArr[i4] + j3;
            jArr[i4] = (this.engine.params.PRIME[i4] & j2) + j4;
            i3 = is_digit_lessthan_ct(j4, j3) | is_digit_lessthan_ct(jArr[i4], j4);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void fpmul_mont(long[] jArr, int i, long[] jArr2, long[] jArr3) {
        long[] jArr4 = new long[this.engine.params.NWORDS_FIELD * 2];
        mp_mul(jArr, i, jArr2, jArr4, this.engine.params.NWORDS_FIELD);
        rdc_mont(jArr4, jArr3);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void fpmul_mont(long[] jArr, long[] jArr2, long[] jArr3) {
        long[] jArr4 = new long[this.engine.params.NWORDS_FIELD * 2];
        mp_mul(jArr, jArr2, jArr4, this.engine.params.NWORDS_FIELD);
        rdc_mont(jArr4, jArr3);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void fpnegPRIME(long[] jArr) {
        int i = 0;
        int i2 = 0;
        while (i < this.engine.params.NWORDS_FIELD) {
            long j = this.engine.params.PRIMEx2[i] - jArr[i];
            int is_digit_lessthan_ct = is_digit_lessthan_ct(this.engine.params.PRIMEx2[i], jArr[i]) | (is_digit_zero_ct(j) & i2);
            jArr[i] = j - i2;
            i++;
            i2 = is_digit_lessthan_ct;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void fpsqr_mont(long[] jArr, long[] jArr2) {
        long[] jArr3 = new long[this.engine.params.NWORDS_FIELD * 2];
        mp_mul(jArr, jArr, jArr3, this.engine.params.NWORDS_FIELD);
        rdc_mont(jArr3, jArr2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void fpsubPRIME(long[] jArr, int i, long[] jArr2, long[] jArr3) {
        int i2 = 0;
        int i3 = 0;
        while (i2 < this.engine.params.NWORDS_FIELD) {
            int i4 = i2 + i;
            long j = jArr[i4] - jArr2[i2];
            int is_digit_lessthan_ct = is_digit_lessthan_ct(jArr[i4], jArr2[i2]) | (is_digit_zero_ct(j) & i3);
            jArr3[i2] = j - i3;
            i2++;
            i3 = is_digit_lessthan_ct;
        }
        long j2 = 0 - i3;
        int i5 = 0;
        for (int i6 = 0; i6 < this.engine.params.NWORDS_FIELD; i6++) {
            long j3 = i5;
            long j4 = jArr3[i6] + j3;
            jArr3[i6] = (this.engine.params.PRIMEx2[i6] & j2) + j4;
            i5 = is_digit_lessthan_ct(j4, j3) | is_digit_lessthan_ct(jArr3[i6], j4);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void fpsubPRIME(long[] jArr, long[] jArr2, int i, long[] jArr3) {
        int i2 = 0;
        int i3 = 0;
        while (i2 < this.engine.params.NWORDS_FIELD) {
            int i4 = i2 + i;
            long j = jArr[i2] - jArr2[i4];
            int is_digit_lessthan_ct = is_digit_lessthan_ct(jArr[i2], jArr2[i4]) | (is_digit_zero_ct(j) & i3);
            jArr3[i2] = j - i3;
            i2++;
            i3 = is_digit_lessthan_ct;
        }
        long j2 = 0 - i3;
        int i5 = 0;
        for (int i6 = 0; i6 < this.engine.params.NWORDS_FIELD; i6++) {
            long j3 = i5;
            long j4 = jArr3[i6] + j3;
            jArr3[i6] = (this.engine.params.PRIMEx2[i6] & j2) + j4;
            i5 = is_digit_lessthan_ct(j4, j3) | is_digit_lessthan_ct(jArr3[i6], j4);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void fpsubPRIME(long[] jArr, long[] jArr2, long[] jArr3) {
        int i = 0;
        int i2 = 0;
        while (i < this.engine.params.NWORDS_FIELD) {
            long j = jArr[i] - jArr2[i];
            int is_digit_lessthan_ct = is_digit_lessthan_ct(jArr[i], jArr2[i]) | (is_digit_zero_ct(j) & i2);
            jArr3[i] = j - i2;
            i++;
            i2 = is_digit_lessthan_ct;
        }
        long j2 = 0 - i2;
        int i3 = 0;
        for (int i4 = 0; i4 < this.engine.params.NWORDS_FIELD; i4++) {
            long j3 = i3;
            long j4 = jArr3[i4] + j3;
            jArr3[i4] = (this.engine.params.PRIMEx2[i4] & j2) + j4;
            i3 = is_digit_lessthan_ct(j4, j3) | is_digit_lessthan_ct(jArr3[i4], j4);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void fpzero(long[] jArr) {
        for (int i = 0; i < this.engine.params.NWORDS_FIELD; i++) {
            jArr[i] = 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void from_Montgomery_mod_order(long[] jArr, long[] jArr2, long[] jArr3, long[] jArr4) {
        long[] jArr5 = new long[this.engine.params.NWORDS_ORDER];
        jArr5[0] = 1;
        Montgomery_multiply_mod_order(jArr, jArr5, jArr2, jArr3, jArr4);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void from_fp2mont(long[][] jArr, long[][] jArr2) {
        from_mont(jArr[0], jArr2[0]);
        from_mont(jArr[1], jArr2[1]);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void inv_mod_orderA(long[] jArr, long[] jArr2) {
        long[] jArr3 = new long[this.engine.params.NWORDS_ORDER];
        long[] jArr4 = new long[this.engine.params.NWORDS_ORDER];
        long[] jArr5 = new long[this.engine.params.NWORDS_ORDER * 2];
        long[] jArr6 = new long[this.engine.params.NWORDS_ORDER];
        long[] jArr7 = new long[this.engine.params.NWORDS_ORDER];
        long j = (-1) >>> (this.engine.params.NBITS_ORDER - this.engine.params.OALICE_BITS);
        jArr7[this.engine.params.NWORDS_ORDER - 1] = 1 << (64 - (this.engine.params.NBITS_ORDER - this.engine.params.OALICE_BITS));
        jArr6[0] = 1;
        mp_sub(jArr, jArr6, jArr3, this.engine.params.NWORDS_ORDER);
        if ((jArr[0] & 1) == 0 || is_zero(jArr3, this.engine.params.NWORDS_ORDER)) {
            copy_words(jArr, jArr2, this.engine.params.NWORDS_ORDER);
            int i = this.engine.params.NWORDS_ORDER - 1;
            jArr2[i] = jArr2[i] & j;
            return;
        }
        mp_sub(jArr7, jArr3, jArr2, this.engine.params.NWORDS_ORDER);
        mp_add(jArr2, jArr6, jArr2, this.engine.params.NWORDS_ORDER);
        copy_words(jArr3, jArr4, this.engine.params.NWORDS_ORDER);
        int i2 = 0;
        while ((jArr4[0] & 1) == 0) {
            i2++;
            mp_shiftr1(jArr4, this.engine.params.NWORDS_ORDER);
        }
        int i3 = this.engine.params.OALICE_BITS / i2;
        for (int i4 = 1; i4 < i3; i4 <<= 1) {
            multiply(jArr3, jArr3, jArr5, this.engine.params.NWORDS_ORDER);
            copy_words(jArr5, jArr3, this.engine.params.NWORDS_ORDER);
            int i5 = this.engine.params.NWORDS_ORDER - 1;
            jArr3[i5] = jArr3[i5] & j;
            mp_add(jArr3, jArr6, jArr4, this.engine.params.NWORDS_ORDER);
            int i6 = this.engine.params.NWORDS_ORDER - 1;
            jArr4[i6] = jArr4[i6] & j;
            multiply(jArr2, jArr4, jArr5, this.engine.params.NWORDS_ORDER);
            copy_words(jArr5, jArr2, this.engine.params.NWORDS_ORDER);
            int i7 = this.engine.params.NWORDS_ORDER - 1;
            jArr2[i7] = jArr2[i7] & j;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean is_felm_zero(long[] jArr) {
        for (int i = 0; i < this.engine.params.NWORDS_FIELD; i++) {
            if (jArr[i] != 0) {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean is_orderelm_lt(long[] jArr, long[] jArr2) {
        for (int i = this.engine.params.NWORDS_ORDER - 1; i >= 0; i--) {
            if (jArr[i] - Long.MIN_VALUE < jArr2[i] - Long.MIN_VALUE) {
                return true;
            }
            if (jArr[i] - Long.MIN_VALUE > jArr2[i] - Long.MIN_VALUE) {
                return false;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean is_sqr_fp2(long[][] jArr, long[] jArr2) {
        long[] jArr3 = new long[this.engine.params.NWORDS_FIELD];
        long[] jArr4 = new long[this.engine.params.NWORDS_FIELD];
        long[] jArr5 = new long[this.engine.params.NWORDS_FIELD];
        long[] jArr6 = new long[this.engine.params.NWORDS_FIELD];
        fpsqr_mont(jArr[0], jArr3);
        fpsqr_mont(jArr[1], jArr4);
        fpaddPRIME(jArr3, jArr4, jArr5);
        fpcopy(jArr5, 0, jArr2);
        for (int i = 0; i < this.engine.params.OALICE_BITS - 2; i++) {
            fpsqr_mont(jArr2, jArr2);
        }
        for (int i2 = 0; i2 < this.engine.params.OBOB_EXPON; i2++) {
            fpsqr_mont(jArr2, jArr6);
            fpmul_mont(jArr2, jArr6, jArr2);
        }
        fpsqr_mont(jArr2, jArr6);
        fpcorrectionPRIME(jArr6);
        fpcorrectionPRIME(jArr5);
        return subarrayEquals(jArr6, jArr5, this.engine.params.NWORDS_FIELD);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int mod3(long[] jArr) {
        int i = 0;
        int[] littleEndianToInt = Pack.littleEndianToInt(Pack.longToLittleEndian(jArr), 0, jArr.length * 2);
        for (int i2 = (this.engine.params.NWORDS_ORDER * 2) - 1; i2 >= 0; i2--) {
            i = (int) (((i << 32) | (littleEndianToInt[i2] & 4294967295L)) % 3);
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void mont_n_way_inv(long[][][] jArr, int i, long[][][] jArr2) {
        long[][] jArr3 = (long[][]) Array.newInstance(long.class, 2, this.engine.params.NWORDS_FIELD);
        fp2copy(jArr[0], jArr2[0]);
        for (int i2 = 1; i2 < i; i2++) {
            fp2mul_mont(jArr2[i2 - 1], jArr[i2], jArr2[i2]);
        }
        int i3 = i - 1;
        fp2copy(jArr2[i3], jArr3);
        fp2inv_mont_bingcd(jArr3);
        while (i3 >= 1) {
            fp2mul_mont(jArr2[i3 - 1], jArr3, jArr2[i3]);
            fp2mul_mont(jArr3, jArr[i3], jArr3);
            i3--;
        }
        fp2copy(jArr3, jArr2[0]);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void mp2_add(long[][] jArr, long[][] jArr2, long[][] jArr3) {
        mp_add(jArr[0], jArr2[0], jArr3[0], this.engine.params.NWORDS_FIELD);
        mp_add(jArr[1], jArr2[1], jArr3[1], this.engine.params.NWORDS_FIELD);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void mp2_sub_p2(long[][] jArr, long[][] jArr2, long[][] jArr3) {
        mp_subPRIME_p2(jArr[0], jArr2[0], jArr3[0]);
        mp_subPRIME_p2(jArr[1], jArr2[1], jArr3[1]);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int mp_add(long[] jArr, long[] jArr2, long[] jArr3, int i) {
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            long j = i2;
            long j2 = jArr[i3] + j;
            jArr3[i3] = jArr2[i3] + j2;
            i2 = is_digit_lessthan_ct(j2, j) | is_digit_lessthan_ct(jArr3[i3], j2);
        }
        return i2;
    }

    protected void mp_mul(long[] jArr, int i, long[] jArr2, long[] jArr3, int i2) {
        long j;
        long is_digit_lessthan_ct;
        long is_digit_lessthan_ct2;
        long j2;
        long is_digit_lessthan_ct3;
        long is_digit_lessthan_ct4;
        long[] jArr4 = new long[2];
        int i3 = 0;
        long j3 = 0;
        long j4 = 0;
        while (i3 < i2) {
            long j5 = j3;
            long j6 = j4;
            int i4 = 0;
            long j7 = 0;
            while (i4 <= i3) {
                digit_x_digit(jArr[i4 + i], jArr2[i3 - i4], jArr4);
                long j8 = j6 + jArr4[0];
                j7 += is_digit_lessthan_ct(is_digit_lessthan_ct4, is_digit_lessthan_ct3);
                i4++;
                j5 += jArr4[1] + is_digit_lessthan_ct(j8, j2);
                j6 = j8;
            }
            jArr3[i3] = j6;
            i3++;
            j4 = j5;
            j3 = j7;
        }
        int i5 = i2;
        while (true) {
            int i6 = (i2 * 2) - 1;
            if (i5 >= i6) {
                jArr3[i6] = j4;
                return;
            }
            long j9 = j3;
            long j10 = j4;
            int i7 = (i5 - i2) + 1;
            long j11 = 0;
            while (i7 < i2) {
                digit_x_digit(jArr[i7 + i], jArr2[i5 - i7], jArr4);
                long j12 = j10 + jArr4[0];
                j11 += is_digit_lessthan_ct(is_digit_lessthan_ct2, is_digit_lessthan_ct);
                i7++;
                j9 += jArr4[1] + is_digit_lessthan_ct(j12, j);
                j10 = j12;
            }
            jArr3[i5] = j10;
            i5++;
            j4 = j9;
            j3 = j11;
        }
    }

    protected void mp_mul(long[] jArr, long[] jArr2, long[] jArr3, int i) {
        long j;
        long is_digit_lessthan_ct;
        long is_digit_lessthan_ct2;
        long j2;
        long is_digit_lessthan_ct3;
        long is_digit_lessthan_ct4;
        long[] jArr4 = new long[2];
        int i2 = 0;
        long j3 = 0;
        long j4 = 0;
        while (i2 < i) {
            long j5 = j3;
            long j6 = j4;
            int i3 = 0;
            long j7 = 0;
            while (i3 <= i2) {
                digit_x_digit(jArr[i3], jArr2[i2 - i3], jArr4);
                long j8 = j6 + jArr4[0];
                j7 += is_digit_lessthan_ct(is_digit_lessthan_ct4, is_digit_lessthan_ct3);
                i3++;
                j5 += jArr4[1] + is_digit_lessthan_ct(j8, j2);
                j6 = j8;
            }
            jArr3[i2] = j6;
            i2++;
            j4 = j5;
            j3 = j7;
        }
        int i4 = i;
        while (true) {
            int i5 = (i * 2) - 1;
            if (i4 >= i5) {
                jArr3[i5] = j4;
                return;
            }
            long j9 = j3;
            long j10 = j4;
            int i6 = (i4 - i) + 1;
            long j11 = 0;
            while (i6 < i) {
                digit_x_digit(jArr[i6], jArr2[i4 - i6], jArr4);
                long j12 = j10 + jArr4[0];
                j11 += is_digit_lessthan_ct(is_digit_lessthan_ct2, is_digit_lessthan_ct);
                i6++;
                j9 += jArr4[1] + is_digit_lessthan_ct(j12, j);
                j10 = j12;
            }
            jArr3[i4] = j10;
            i4++;
            j4 = j9;
            j3 = j11;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int mp_sub(long[] jArr, long[] jArr2, long[] jArr3, int i) {
        int i2 = 0;
        int i3 = 0;
        while (i2 < i) {
            long j = jArr[i2] - jArr2[i2];
            int is_digit_lessthan_ct = is_digit_lessthan_ct(jArr[i2], jArr2[i2]) | (is_digit_zero_ct(j) & i3);
            jArr3[i2] = j - i3;
            i2++;
            i3 = is_digit_lessthan_ct;
        }
        return i3;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void mul3(byte[] bArr) {
        long[] jArr = new long[this.engine.params.NWORDS_ORDER];
        long[] jArr2 = new long[this.engine.params.NWORDS_ORDER];
        decode_to_digits(bArr, 0, jArr, this.engine.params.SECRETKEY_B_BYTES, this.engine.params.NWORDS_ORDER);
        mp_add(jArr, jArr, jArr2, this.engine.params.NWORDS_ORDER);
        mp_add(jArr, jArr2, jArr, this.engine.params.NWORDS_ORDER);
        encode_to_bytes(jArr, bArr, 0, this.engine.params.SECRETKEY_B_BYTES);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void multiply(long[] jArr, long[] jArr2, long[] jArr3, int i) {
        long j;
        long is_digit_lessthan_ct;
        long is_digit_lessthan_ct2;
        long j2;
        long is_digit_lessthan_ct3;
        long is_digit_lessthan_ct4;
        long[] jArr4 = new long[2];
        int i2 = 0;
        long j3 = 0;
        long j4 = 0;
        while (i2 < i) {
            long j5 = j3;
            long j6 = j4;
            int i3 = 0;
            long j7 = 0;
            while (i3 <= i2) {
                digit_x_digit(jArr[i3], jArr2[i2 - i3], jArr4);
                long j8 = j6 + jArr4[0];
                j7 += is_digit_lessthan_ct(is_digit_lessthan_ct4, is_digit_lessthan_ct3);
                i3++;
                j5 += jArr4[1] + is_digit_lessthan_ct(j8, j2);
                j6 = j8;
            }
            jArr3[i2] = j6;
            i2++;
            j4 = j5;
            j3 = j7;
        }
        int i4 = i;
        while (true) {
            int i5 = (i * 2) - 1;
            if (i4 >= i5) {
                jArr3[i5] = j4;
                return;
            }
            long j9 = j3;
            long j10 = j4;
            int i6 = (i4 - i) + 1;
            long j11 = 0;
            while (i6 < i) {
                digit_x_digit(jArr[i6], jArr2[i4 - i6], jArr4);
                long j12 = j10 + jArr4[0];
                j11 += is_digit_lessthan_ct(is_digit_lessthan_ct2, is_digit_lessthan_ct);
                i6++;
                j9 += jArr4[1] + is_digit_lessthan_ct(j12, j);
                j10 = j12;
            }
            jArr3[i4] = j10;
            i4++;
            j4 = j9;
            j3 = j11;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void sqr_Fp2_cycl(long[][] jArr, long[] jArr2) {
        long[] jArr3 = new long[this.engine.params.NWORDS_FIELD];
        fpaddPRIME(jArr[0], jArr[1], jArr3);
        fpsqr_mont(jArr3, jArr3);
        fpsubPRIME(jArr3, jArr2, jArr[1]);
        fpsqr_mont(jArr[0], jArr3);
        fpaddPRIME(jArr3, jArr3, jArr3);
        fpsubPRIME(jArr3, jArr2, jArr[0]);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void sqrt_Fp2(long[][] jArr, long[][] jArr2) {
        long[] jArr3 = new long[this.engine.params.NWORDS_FIELD];
        long[] jArr4 = new long[this.engine.params.NWORDS_FIELD];
        long[] jArr5 = new long[this.engine.params.NWORDS_FIELD];
        long[] jArr6 = new long[this.engine.params.NWORDS_FIELD];
        fpsqr_mont(jArr[0], jArr3);
        fpsqr_mont(jArr[1], jArr4);
        fpaddPRIME(jArr3, jArr4, jArr3);
        fpcopy(jArr3, 0, jArr4);
        for (int i = 0; i < this.engine.params.OALICE_BITS - 2; i++) {
            fpsqr_mont(jArr4, jArr4);
        }
        for (int i2 = 0; i2 < this.engine.params.OBOB_EXPON; i2++) {
            fpsqr_mont(jArr4, jArr3);
            fpmul_mont(jArr4, jArr3, jArr4);
        }
        fpaddPRIME(jArr[0], jArr4, jArr3);
        fpdiv2_PRIME(jArr3, jArr3);
        fpcopy(jArr3, 0, jArr5);
        fpinv_chain_mont(jArr5);
        fpmul_mont(jArr3, jArr5, jArr4);
        fpmul_mont(jArr5, jArr[1], jArr5);
        fpdiv2_PRIME(jArr5, jArr5);
        fpsqr_mont(jArr4, jArr6);
        fpcorrectionPRIME(jArr3);
        fpcorrectionPRIME(jArr6);
        if (subarrayEquals(jArr3, jArr6, this.engine.params.NWORDS_FIELD)) {
            fpcopy(jArr4, 0, jArr2[0]);
            fpcopy(jArr5, 0, jArr2[1]);
            return;
        }
        fpnegPRIME(jArr4);
        fpcopy(jArr5, 0, jArr2[0]);
        fpcopy(jArr4, 0, jArr2[1]);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void to_Montgomery_mod_order(long[] jArr, long[] jArr2, long[] jArr3, long[] jArr4, long[] jArr5) {
        Montgomery_multiply_mod_order(jArr, jArr5, jArr2, jArr3, jArr4);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void to_fp2mont(long[][] jArr, long[][] jArr2) {
        to_mont(jArr[0], jArr2[0]);
        to_mont(jArr[1], jArr2[1]);
    }
}
