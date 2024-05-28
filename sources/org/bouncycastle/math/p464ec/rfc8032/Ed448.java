package org.bouncycastle.math.p464ec.rfc8032;

import java.security.SecureRandom;
import org.bouncycastle.crypto.Xof;
import org.bouncycastle.crypto.digests.SHAKEDigest;
import org.bouncycastle.math.p464ec.rfc7748.X448;
import org.bouncycastle.math.p464ec.rfc7748.X448Field;
import org.bouncycastle.math.raw.Nat;
import org.bouncycastle.util.Arrays;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0.C0567f;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.bouncycastle.math.ec.rfc8032.Ed448 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public abstract class Ed448 {
    private static final int COORD_INTS = 14;
    private static final int C_d = -39081;
    private static final int L4_0 = 43969588;
    private static final int L4_1 = 30366549;
    private static final int L4_2 = 163752818;
    private static final int L4_3 = 258169998;
    private static final int L4_4 = 96434764;
    private static final int L4_5 = 227822194;
    private static final int L4_6 = 149865618;
    private static final int L4_7 = 550336261;
    private static final int L_0 = 78101261;
    private static final int L_1 = 141809365;
    private static final int L_2 = 175155932;
    private static final int L_3 = 64542499;
    private static final int L_4 = 158326419;
    private static final int L_5 = 191173276;
    private static final int L_6 = 104575268;
    private static final int L_7 = 137584065;
    private static final long M26L = 67108863;
    private static final long M28L = 268435455;
    private static final long M32L = 4294967295L;
    private static final int POINT_BYTES = 57;
    private static final int PRECOMP_BLOCKS = 5;
    private static final int PRECOMP_MASK = 15;
    private static final int PRECOMP_POINTS = 16;
    private static final int PRECOMP_RANGE = 450;
    private static final int PRECOMP_SPACING = 18;
    private static final int PRECOMP_TEETH = 5;
    public static final int PREHASH_SIZE = 64;
    public static final int PUBLIC_KEY_SIZE = 57;
    private static final int SCALAR_BYTES = 57;
    private static final int SCALAR_INTS = 14;
    public static final int SECRET_KEY_SIZE = 57;
    public static final int SIGNATURE_SIZE = 114;
    private static final int WNAF_WIDTH = 5;
    private static final int WNAF_WIDTH_BASE = 7;
    private static final byte[] DOM4_PREFIX = {83, 105, 103, 69, 100, 52, 52, 56};

    /* renamed from: P */
    private static final int[] f27058P = {-1, -1, -1, -1, -1, -1, -1, -2, -1, -1, -1, -1, -1, -1};

    /* renamed from: L */
    private static final int[] f27057L = {-1420278541, 595116690, -1916432555, 560775794, -1361693040, -1001465015, 2093622249, -1, -1, -1, -1, -1, -1, 1073741823};
    private static final int[] B_x = {118276190, 40534716, 9670182, 135141552, 85017403, 259173222, 68333082, 171784774, 174973732, 15824510, 73756743, 57518561, 94773951, 248652241, 107736333, 82941708};
    private static final int[] B_y = {36764180, 8885695, 130592152, 20104429, 163904957, 30304195, 121295871, 5901357, 125344798, 171541512, 175338348, 209069246, 3626697, 38307682, 24032956, 110359655};
    private static final Object PRECOMP_LOCK = new Object();
    private static PointAffine[] PRECOMP_BASE_WNAF = null;
    private static int[] PRECOMP_BASE_COMB = null;

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: org.bouncycastle.math.ec.rfc8032.Ed448$Algorithm */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class Algorithm {
        public static final int Ed448 = 0;
        public static final int Ed448ph = 1;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: org.bouncycastle.math.ec.rfc8032.Ed448$F */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    static class C13365F extends X448Field {
        private C13365F() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: org.bouncycastle.math.ec.rfc8032.Ed448$PointAffine */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class PointAffine {

        /* renamed from: x */
        int[] f27059x;

        /* renamed from: y */
        int[] f27060y;

        private PointAffine() {
            this.f27059x = C13365F.create();
            this.f27060y = C13365F.create();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: org.bouncycastle.math.ec.rfc8032.Ed448$PointProjective */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class PointProjective {

        /* renamed from: x */
        int[] f27061x;

        /* renamed from: y */
        int[] f27062y;

        /* renamed from: z */
        int[] f27063z;

        private PointProjective() {
            this.f27061x = C13365F.create();
            this.f27062y = C13365F.create();
            this.f27063z = C13365F.create();
        }
    }

    private static byte[] calculateS(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        int[] iArr = new int[28];
        decodeScalar(bArr, 0, iArr);
        int[] iArr2 = new int[14];
        decodeScalar(bArr2, 0, iArr2);
        int[] iArr3 = new int[14];
        decodeScalar(bArr3, 0, iArr3);
        Nat.mulAddTo(14, iArr2, iArr3, iArr);
        byte[] bArr4 = new byte[114];
        for (int i = 0; i < iArr.length; i++) {
            encode32(iArr[i], bArr4, i * 4);
        }
        return reduceScalar(bArr4);
    }

    private static boolean checkContextVar(byte[] bArr) {
        return bArr != null && bArr.length < 256;
    }

    private static int checkPoint(int[] iArr, int[] iArr2) {
        int[] create = C13365F.create();
        int[] create2 = C13365F.create();
        int[] create3 = C13365F.create();
        C13365F.sqr(iArr, create2);
        C13365F.sqr(iArr2, create3);
        C13365F.mul(create2, create3, create);
        C13365F.add(create2, create3, create2);
        C13365F.mul(create, 39081, create);
        C13365F.subOne(create);
        C13365F.add(create, create2, create);
        C13365F.normalize(create);
        return C13365F.isZero(create);
    }

    private static int checkPoint(int[] iArr, int[] iArr2, int[] iArr3) {
        int[] create = C13365F.create();
        int[] create2 = C13365F.create();
        int[] create3 = C13365F.create();
        int[] create4 = C13365F.create();
        C13365F.sqr(iArr, create2);
        C13365F.sqr(iArr2, create3);
        C13365F.sqr(iArr3, create4);
        C13365F.mul(create2, create3, create);
        C13365F.add(create2, create3, create2);
        C13365F.mul(create2, create4, create2);
        C13365F.sqr(create4, create4);
        C13365F.mul(create, 39081, create);
        C13365F.sub(create, create4, create);
        C13365F.add(create, create2, create);
        C13365F.normalize(create);
        return C13365F.isZero(create);
    }

    private static boolean checkPointVar(byte[] bArr) {
        if ((bArr[56] & Byte.MAX_VALUE) != 0) {
            return false;
        }
        int[] iArr = new int[14];
        decode32(bArr, 0, iArr, 0, 14);
        return !Nat.gte(14, iArr, f27058P);
    }

    private static boolean checkScalarVar(byte[] bArr, int[] iArr) {
        if (bArr[56] != 0) {
            return false;
        }
        decodeScalar(bArr, 0, iArr);
        return !Nat.gte(14, iArr, f27057L);
    }

    private static byte[] copy(byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, i, bArr2, 0, i2);
        return bArr2;
    }

    public static Xof createPrehash() {
        return createXof();
    }

    private static Xof createXof() {
        return new SHAKEDigest(256);
    }

    private static int decode16(byte[] bArr, int i) {
        return ((bArr[i + 1] & 255) << 8) | (bArr[i] & 255);
    }

    private static int decode24(byte[] bArr, int i) {
        int i2 = i + 1;
        return ((bArr[i2 + 1] & 255) << 16) | (bArr[i] & 255) | ((bArr[i2] & 255) << 8);
    }

    private static int decode32(byte[] bArr, int i) {
        int i2 = i + 1;
        int i3 = i2 + 1;
        return (bArr[i3 + 1] << 24) | (bArr[i] & 255) | ((bArr[i2] & 255) << 8) | ((bArr[i3] & 255) << 16);
    }

    private static void decode32(byte[] bArr, int i, int[] iArr, int i2, int i3) {
        for (int i4 = 0; i4 < i3; i4++) {
            iArr[i2 + i4] = decode32(bArr, (i4 * 4) + i);
        }
    }

    private static boolean decodePointVar(byte[] bArr, int i, boolean z, PointProjective pointProjective) {
        byte[] copy = copy(bArr, i, 57);
        if (checkPointVar(copy)) {
            int i2 = (copy[56] & 128) >>> 7;
            copy[56] = (byte) (copy[56] & Byte.MAX_VALUE);
            C13365F.decode(copy, 0, pointProjective.f27062y);
            int[] create = C13365F.create();
            int[] create2 = C13365F.create();
            C13365F.sqr(pointProjective.f27062y, create);
            C13365F.mul(create, 39081, create2);
            C13365F.negate(create, create);
            C13365F.addOne(create);
            C13365F.addOne(create2);
            if (C13365F.sqrtRatioVar(create, create2, pointProjective.f27061x)) {
                C13365F.normalize(pointProjective.f27061x);
                if (i2 == 1 && C13365F.isZeroVar(pointProjective.f27061x)) {
                    return false;
                }
                if (z ^ (i2 != (pointProjective.f27061x[0] & 1))) {
                    C13365F.negate(pointProjective.f27061x, pointProjective.f27061x);
                }
                C13365F.one(pointProjective.f27063z);
                return true;
            }
            return false;
        }
        return false;
    }

    private static void decodeScalar(byte[] bArr, int i, int[] iArr) {
        decode32(bArr, i, iArr, 0, 14);
    }

    private static void dom4(Xof xof, byte b, byte[] bArr) {
        byte[] bArr2 = DOM4_PREFIX;
        int length = bArr2.length;
        int i = length + 2;
        byte[] bArr3 = new byte[bArr.length + i];
        System.arraycopy(bArr2, 0, bArr3, 0, length);
        bArr3[length] = b;
        bArr3[length + 1] = (byte) bArr.length;
        System.arraycopy(bArr, 0, bArr3, i, bArr.length);
        xof.update(bArr3, 0, bArr3.length);
    }

    private static void encode24(int i, byte[] bArr, int i2) {
        bArr[i2] = (byte) i;
        int i3 = i2 + 1;
        bArr[i3] = (byte) (i >>> 8);
        bArr[i3 + 1] = (byte) (i >>> 16);
    }

    private static void encode32(int i, byte[] bArr, int i2) {
        bArr[i2] = (byte) i;
        int i3 = i2 + 1;
        bArr[i3] = (byte) (i >>> 8);
        int i4 = i3 + 1;
        bArr[i4] = (byte) (i >>> 16);
        bArr[i4 + 1] = (byte) (i >>> 24);
    }

    private static void encode56(long j, byte[] bArr, int i) {
        encode32((int) j, bArr, i);
        encode24((int) (j >>> 32), bArr, i + 4);
    }

    private static int encodePoint(PointProjective pointProjective, byte[] bArr, int i) {
        int[] create = C13365F.create();
        int[] create2 = C13365F.create();
        C13365F.inv(pointProjective.f27063z, create2);
        C13365F.mul(pointProjective.f27061x, create2, create);
        C13365F.mul(pointProjective.f27062y, create2, create2);
        C13365F.normalize(create);
        C13365F.normalize(create2);
        int checkPoint = checkPoint(create, create2);
        C13365F.encode(create2, bArr, i);
        bArr[(i + 57) - 1] = (byte) ((create[0] & 1) << 7);
        return checkPoint;
    }

    public static void generatePrivateKey(SecureRandom secureRandom, byte[] bArr) {
        secureRandom.nextBytes(bArr);
    }

    public static void generatePublicKey(byte[] bArr, int i, byte[] bArr2, int i2) {
        Xof createXof = createXof();
        byte[] bArr3 = new byte[114];
        createXof.update(bArr, i, 57);
        createXof.doFinal(bArr3, 0, bArr3.length);
        byte[] bArr4 = new byte[57];
        pruneScalar(bArr3, 0, bArr4);
        scalarMultBaseEncoded(bArr4, bArr2, i2);
    }

    private static int getWindow4(int[] iArr, int i) {
        return (iArr[i >>> 3] >>> ((i & 7) << 2)) & 15;
    }

    private static byte[] getWnafVar(int[] iArr, int i) {
        int[] iArr2 = new int[28];
        int length = iArr2.length;
        int i2 = 0;
        int i3 = 14;
        int i4 = 0;
        while (true) {
            i3--;
            if (i3 < 0) {
                break;
            }
            int i5 = iArr[i3];
            int i6 = length - 1;
            iArr2[i6] = (i4 << 16) | (i5 >>> 16);
            length = i6 - 1;
            iArr2[length] = i5;
            i4 = i5;
        }
        byte[] bArr = new byte[447];
        int i7 = 32 - i;
        int i8 = 0;
        int i9 = 0;
        while (i2 < iArr2.length) {
            int i10 = iArr2[i2];
            while (i8 < 16) {
                int i11 = i10 >>> i8;
                if ((i11 & 1) == i9) {
                    i8++;
                } else {
                    int i12 = (i11 | 1) << i7;
                    bArr[(i2 << 4) + i8] = (byte) (i12 >> i7);
                    i8 += i;
                    i9 = i12 >>> 31;
                }
            }
            i2++;
            i8 -= 16;
        }
        return bArr;
    }

    private static void implSign(Xof xof, byte[] bArr, byte[] bArr2, byte[] bArr3, int i, byte[] bArr4, byte b, byte[] bArr5, int i2, int i3, byte[] bArr6, int i4) {
        dom4(xof, b, bArr4);
        xof.update(bArr, 57, 57);
        xof.update(bArr5, i2, i3);
        xof.doFinal(bArr, 0, bArr.length);
        byte[] reduceScalar = reduceScalar(bArr);
        byte[] bArr7 = new byte[57];
        scalarMultBaseEncoded(reduceScalar, bArr7, 0);
        dom4(xof, b, bArr4);
        xof.update(bArr7, 0, 57);
        xof.update(bArr3, i, 57);
        xof.update(bArr5, i2, i3);
        xof.doFinal(bArr, 0, bArr.length);
        byte[] calculateS = calculateS(reduceScalar, reduceScalar(bArr), bArr2);
        System.arraycopy(bArr7, 0, bArr6, i4, 57);
        System.arraycopy(calculateS, 0, bArr6, i4 + 57, 57);
    }

    private static void implSign(byte[] bArr, int i, byte[] bArr2, byte b, byte[] bArr3, int i2, int i3, byte[] bArr4, int i4) {
        if (!checkContextVar(bArr2)) {
            throw new IllegalArgumentException("ctx");
        }
        Xof createXof = createXof();
        byte[] bArr5 = new byte[114];
        createXof.update(bArr, i, 57);
        createXof.doFinal(bArr5, 0, bArr5.length);
        byte[] bArr6 = new byte[57];
        pruneScalar(bArr5, 0, bArr6);
        byte[] bArr7 = new byte[57];
        scalarMultBaseEncoded(bArr6, bArr7, 0);
        implSign(createXof, bArr5, bArr6, bArr7, 0, bArr2, b, bArr3, i2, i3, bArr4, i4);
    }

    private static void implSign(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, byte b, byte[] bArr4, int i3, int i4, byte[] bArr5, int i5) {
        if (!checkContextVar(bArr3)) {
            throw new IllegalArgumentException("ctx");
        }
        Xof createXof = createXof();
        byte[] bArr6 = new byte[114];
        createXof.update(bArr, i, 57);
        createXof.doFinal(bArr6, 0, bArr6.length);
        byte[] bArr7 = new byte[57];
        pruneScalar(bArr6, 0, bArr7);
        implSign(createXof, bArr6, bArr7, bArr2, i2, bArr3, b, bArr4, i3, i4, bArr5, i5);
    }

    private static boolean implVerify(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, byte b, byte[] bArr4, int i3, int i4) {
        if (checkContextVar(bArr3)) {
            byte[] copy = copy(bArr, i, 57);
            byte[] copy2 = copy(bArr, i + 57, 57);
            if (checkPointVar(copy)) {
                int[] iArr = new int[14];
                if (checkScalarVar(copy2, iArr)) {
                    PointProjective pointProjective = new PointProjective();
                    if (decodePointVar(bArr2, i2, true, pointProjective)) {
                        Xof createXof = createXof();
                        byte[] bArr5 = new byte[114];
                        dom4(createXof, b, bArr3);
                        createXof.update(copy, 0, 57);
                        createXof.update(bArr2, i2, 57);
                        createXof.update(bArr4, i3, i4);
                        createXof.doFinal(bArr5, 0, bArr5.length);
                        int[] iArr2 = new int[14];
                        decodeScalar(reduceScalar(bArr5), 0, iArr2);
                        PointProjective pointProjective2 = new PointProjective();
                        scalarMultStrausVar(iArr, iArr2, pointProjective, pointProjective2);
                        byte[] bArr6 = new byte[57];
                        return encodePoint(pointProjective2, bArr6, 0) != 0 && Arrays.areEqual(bArr6, copy);
                    }
                    return false;
                }
                return false;
            }
            return false;
        }
        throw new IllegalArgumentException("ctx");
    }

    private static void invertZs(PointProjective[] pointProjectiveArr) {
        int length = pointProjectiveArr.length;
        int[] createTable = C13365F.createTable(length);
        int[] create = C13365F.create();
        C13365F.copy(pointProjectiveArr[0].f27063z, 0, create, 0);
        C13365F.copy(create, 0, createTable, 0);
        int i = 0;
        while (true) {
            i++;
            if (i >= length) {
                break;
            }
            C13365F.mul(create, pointProjectiveArr[i].f27063z, create);
            C13365F.copy(create, 0, createTable, i * 16);
        }
        C13365F.invVar(create, create);
        int i2 = i - 1;
        int[] create2 = C13365F.create();
        while (i2 > 0) {
            int i3 = i2 - 1;
            C13365F.copy(createTable, i3 * 16, create2, 0);
            C13365F.mul(create2, create, create2);
            C13365F.mul(create, pointProjectiveArr[i2].f27063z, create);
            C13365F.copy(create2, 0, pointProjectiveArr[i2].f27063z, 0);
            i2 = i3;
        }
        C13365F.copy(create, 0, pointProjectiveArr[0].f27063z, 0);
    }

    private static boolean isNeutralElementVar(int[] iArr, int[] iArr2, int[] iArr3) {
        return C13365F.isZeroVar(iArr) && C13365F.areEqualVar(iArr2, iArr3);
    }

    private static void pointAdd(PointAffine pointAffine, PointProjective pointProjective) {
        int[] create = C13365F.create();
        int[] create2 = C13365F.create();
        int[] create3 = C13365F.create();
        int[] create4 = C13365F.create();
        int[] create5 = C13365F.create();
        int[] create6 = C13365F.create();
        int[] create7 = C13365F.create();
        C13365F.sqr(pointProjective.f27063z, create);
        C13365F.mul(pointAffine.f27059x, pointProjective.f27061x, create2);
        C13365F.mul(pointAffine.f27060y, pointProjective.f27062y, create3);
        C13365F.mul(create2, create3, create4);
        C13365F.mul(create4, 39081, create4);
        C13365F.add(create, create4, create5);
        C13365F.sub(create, create4, create6);
        C13365F.add(pointAffine.f27060y, pointAffine.f27059x, create7);
        C13365F.add(pointProjective.f27062y, pointProjective.f27061x, create4);
        C13365F.mul(create7, create4, create7);
        C13365F.add(create3, create2, create);
        C13365F.sub(create3, create2, create4);
        C13365F.carry(create);
        C13365F.sub(create7, create, create7);
        C13365F.mul(create7, pointProjective.f27063z, create7);
        C13365F.mul(create4, pointProjective.f27063z, create4);
        C13365F.mul(create5, create7, pointProjective.f27061x);
        C13365F.mul(create4, create6, pointProjective.f27062y);
        C13365F.mul(create5, create6, pointProjective.f27063z);
    }

    private static void pointAdd(PointProjective pointProjective, PointProjective pointProjective2) {
        int[] create = C13365F.create();
        int[] create2 = C13365F.create();
        int[] create3 = C13365F.create();
        int[] create4 = C13365F.create();
        int[] create5 = C13365F.create();
        int[] create6 = C13365F.create();
        int[] create7 = C13365F.create();
        int[] create8 = C13365F.create();
        C13365F.mul(pointProjective.f27063z, pointProjective2.f27063z, create);
        C13365F.sqr(create, create2);
        C13365F.mul(pointProjective.f27061x, pointProjective2.f27061x, create3);
        C13365F.mul(pointProjective.f27062y, pointProjective2.f27062y, create4);
        C13365F.mul(create3, create4, create5);
        C13365F.mul(create5, 39081, create5);
        C13365F.add(create2, create5, create6);
        C13365F.sub(create2, create5, create7);
        C13365F.add(pointProjective.f27062y, pointProjective.f27061x, create8);
        C13365F.add(pointProjective2.f27062y, pointProjective2.f27061x, create5);
        C13365F.mul(create8, create5, create8);
        C13365F.add(create4, create3, create2);
        C13365F.sub(create4, create3, create5);
        C13365F.carry(create2);
        C13365F.sub(create8, create2, create8);
        C13365F.mul(create8, create, create8);
        C13365F.mul(create5, create, create5);
        C13365F.mul(create6, create8, pointProjective2.f27061x);
        C13365F.mul(create5, create7, pointProjective2.f27062y);
        C13365F.mul(create6, create7, pointProjective2.f27063z);
    }

    private static void pointAddVar(boolean z, PointAffine pointAffine, PointProjective pointProjective) {
        int[] iArr;
        int[] iArr2;
        int[] iArr3;
        int[] iArr4;
        int[] create = C13365F.create();
        int[] create2 = C13365F.create();
        int[] create3 = C13365F.create();
        int[] create4 = C13365F.create();
        int[] create5 = C13365F.create();
        int[] create6 = C13365F.create();
        int[] create7 = C13365F.create();
        if (z) {
            C13365F.sub(pointAffine.f27060y, pointAffine.f27059x, create7);
            iArr2 = create;
            iArr = create4;
            iArr4 = create5;
            iArr3 = create6;
        } else {
            C13365F.add(pointAffine.f27060y, pointAffine.f27059x, create7);
            iArr = create;
            iArr2 = create4;
            iArr3 = create5;
            iArr4 = create6;
        }
        C13365F.sqr(pointProjective.f27063z, create);
        C13365F.mul(pointAffine.f27059x, pointProjective.f27061x, create2);
        C13365F.mul(pointAffine.f27060y, pointProjective.f27062y, create3);
        C13365F.mul(create2, create3, create4);
        C13365F.mul(create4, 39081, create4);
        C13365F.add(create, create4, iArr3);
        C13365F.sub(create, create4, iArr4);
        C13365F.add(pointProjective.f27062y, pointProjective.f27061x, create4);
        C13365F.mul(create7, create4, create7);
        C13365F.add(create3, create2, iArr);
        C13365F.sub(create3, create2, iArr2);
        C13365F.carry(iArr);
        C13365F.sub(create7, create, create7);
        C13365F.mul(create7, pointProjective.f27063z, create7);
        C13365F.mul(create4, pointProjective.f27063z, create4);
        C13365F.mul(create5, create7, pointProjective.f27061x);
        C13365F.mul(create4, create6, pointProjective.f27062y);
        C13365F.mul(create5, create6, pointProjective.f27063z);
    }

    private static void pointAddVar(boolean z, PointProjective pointProjective, PointProjective pointProjective2) {
        int[] iArr;
        int[] iArr2;
        int[] iArr3;
        int[] iArr4;
        int[] create = C13365F.create();
        int[] create2 = C13365F.create();
        int[] create3 = C13365F.create();
        int[] create4 = C13365F.create();
        int[] create5 = C13365F.create();
        int[] create6 = C13365F.create();
        int[] create7 = C13365F.create();
        int[] create8 = C13365F.create();
        if (z) {
            C13365F.sub(pointProjective.f27062y, pointProjective.f27061x, create8);
            iArr2 = create2;
            iArr = create5;
            iArr4 = create6;
            iArr3 = create7;
        } else {
            C13365F.add(pointProjective.f27062y, pointProjective.f27061x, create8);
            iArr = create2;
            iArr2 = create5;
            iArr3 = create6;
            iArr4 = create7;
        }
        C13365F.mul(pointProjective.f27063z, pointProjective2.f27063z, create);
        C13365F.sqr(create, create2);
        C13365F.mul(pointProjective.f27061x, pointProjective2.f27061x, create3);
        C13365F.mul(pointProjective.f27062y, pointProjective2.f27062y, create4);
        C13365F.mul(create3, create4, create5);
        C13365F.mul(create5, 39081, create5);
        C13365F.add(create2, create5, iArr3);
        C13365F.sub(create2, create5, iArr4);
        C13365F.add(pointProjective2.f27062y, pointProjective2.f27061x, create5);
        C13365F.mul(create8, create5, create8);
        C13365F.add(create4, create3, iArr);
        C13365F.sub(create4, create3, iArr2);
        C13365F.carry(iArr);
        C13365F.sub(create8, create2, create8);
        C13365F.mul(create8, create, create8);
        C13365F.mul(create5, create, create5);
        C13365F.mul(create6, create8, pointProjective2.f27061x);
        C13365F.mul(create5, create7, pointProjective2.f27062y);
        C13365F.mul(create6, create7, pointProjective2.f27063z);
    }

    private static void pointCopy(PointProjective pointProjective, PointProjective pointProjective2) {
        C13365F.copy(pointProjective.f27061x, 0, pointProjective2.f27061x, 0);
        C13365F.copy(pointProjective.f27062y, 0, pointProjective2.f27062y, 0);
        C13365F.copy(pointProjective.f27063z, 0, pointProjective2.f27063z, 0);
    }

    private static void pointDouble(PointProjective pointProjective) {
        int[] create = C13365F.create();
        int[] create2 = C13365F.create();
        int[] create3 = C13365F.create();
        int[] create4 = C13365F.create();
        int[] create5 = C13365F.create();
        int[] create6 = C13365F.create();
        C13365F.add(pointProjective.f27061x, pointProjective.f27062y, create);
        C13365F.sqr(create, create);
        C13365F.sqr(pointProjective.f27061x, create2);
        C13365F.sqr(pointProjective.f27062y, create3);
        C13365F.add(create2, create3, create4);
        C13365F.carry(create4);
        C13365F.sqr(pointProjective.f27063z, create5);
        C13365F.add(create5, create5, create5);
        C13365F.carry(create5);
        C13365F.sub(create4, create5, create6);
        C13365F.sub(create, create4, create);
        C13365F.sub(create2, create3, create2);
        C13365F.mul(create, create6, pointProjective.f27061x);
        C13365F.mul(create4, create2, pointProjective.f27062y);
        C13365F.mul(create4, create6, pointProjective.f27063z);
    }

    private static void pointLookup(int i, int i2, PointAffine pointAffine) {
        int i3 = i * 16 * 2 * 16;
        for (int i4 = 0; i4 < 16; i4++) {
            int i5 = ((i4 ^ i2) - 1) >> 31;
            C13365F.cmov(i5, PRECOMP_BASE_COMB, i3, pointAffine.f27059x, 0);
            int i6 = i3 + 16;
            C13365F.cmov(i5, PRECOMP_BASE_COMB, i6, pointAffine.f27060y, 0);
            i3 = i6 + 16;
        }
    }

    private static void pointLookup(int[] iArr, int i, int[] iArr2, PointProjective pointProjective) {
        int window4 = getWindow4(iArr, i);
        int i2 = (window4 >>> 3) ^ 1;
        int i3 = (window4 ^ (-i2)) & 7;
        int i4 = 0;
        for (int i5 = 0; i5 < 8; i5++) {
            int i6 = ((i5 ^ i3) - 1) >> 31;
            C13365F.cmov(i6, iArr2, i4, pointProjective.f27061x, 0);
            int i7 = i4 + 16;
            C13365F.cmov(i6, iArr2, i7, pointProjective.f27062y, 0);
            int i8 = i7 + 16;
            C13365F.cmov(i6, iArr2, i8, pointProjective.f27063z, 0);
            i4 = i8 + 16;
        }
        C13365F.cnegate(i2, pointProjective.f27061x);
    }

    private static void pointLookup15(int[] iArr, PointProjective pointProjective) {
        C13365F.copy(iArr, 336, pointProjective.f27061x, 0);
        C13365F.copy(iArr, 352, pointProjective.f27062y, 0);
        C13365F.copy(iArr, 368, pointProjective.f27063z, 0);
    }

    private static int[] pointPrecompute(PointProjective pointProjective, int i) {
        PointProjective pointProjective2 = new PointProjective();
        pointCopy(pointProjective, pointProjective2);
        PointProjective pointProjective3 = new PointProjective();
        pointCopy(pointProjective2, pointProjective3);
        pointDouble(pointProjective3);
        int[] createTable = C13365F.createTable(i * 3);
        int i2 = 0;
        int i3 = 0;
        while (true) {
            C13365F.copy(pointProjective2.f27061x, 0, createTable, i2);
            int i4 = i2 + 16;
            C13365F.copy(pointProjective2.f27062y, 0, createTable, i4);
            int i5 = i4 + 16;
            C13365F.copy(pointProjective2.f27063z, 0, createTable, i5);
            i2 = i5 + 16;
            i3++;
            if (i3 == i) {
                return createTable;
            }
            pointAdd(pointProjective3, pointProjective2);
        }
    }

    private static void pointPrecomputeVar(PointProjective pointProjective, PointProjective[] pointProjectiveArr, int i) {
        PointProjective pointProjective2 = new PointProjective();
        pointCopy(pointProjective, pointProjective2);
        pointDouble(pointProjective2);
        pointProjectiveArr[0] = new PointProjective();
        pointCopy(pointProjective, pointProjectiveArr[0]);
        for (int i2 = 1; i2 < i; i2++) {
            pointProjectiveArr[i2] = new PointProjective();
            pointCopy(pointProjectiveArr[i2 - 1], pointProjectiveArr[i2]);
            pointAdd(pointProjective2, pointProjectiveArr[i2]);
        }
    }

    private static void pointSetNeutral(PointProjective pointProjective) {
        C13365F.zero(pointProjective.f27061x);
        C13365F.one(pointProjective.f27062y);
        C13365F.one(pointProjective.f27063z);
    }

    public static void precompute() {
        synchronized (PRECOMP_LOCK) {
            if (PRECOMP_BASE_WNAF == null || PRECOMP_BASE_COMB == null) {
                PointProjective[] pointProjectiveArr = new PointProjective[112];
                PointProjective pointProjective = new PointProjective();
                C13365F.copy(B_x, 0, pointProjective.f27061x, 0);
                C13365F.copy(B_y, 0, pointProjective.f27062y, 0);
                C13365F.one(pointProjective.f27063z);
                pointPrecomputeVar(pointProjective, pointProjectiveArr, 32);
                PointProjective[] pointProjectiveArr2 = new PointProjective[5];
                for (int i = 0; i < 5; i++) {
                    pointProjectiveArr2[i] = new PointProjective();
                }
                int i2 = 32;
                int i3 = 0;
                for (int i4 = 5; i3 < i4; i4 = 5) {
                    int i5 = i2 + 1;
                    PointProjective pointProjective2 = new PointProjective();
                    pointProjectiveArr[i2] = pointProjective2;
                    int i6 = 0;
                    while (true) {
                        if (i6 >= i4) {
                            break;
                        }
                        if (i6 == 0) {
                            pointCopy(pointProjective, pointProjective2);
                        } else {
                            pointAdd(pointProjective, pointProjective2);
                        }
                        pointDouble(pointProjective);
                        pointCopy(pointProjective, pointProjectiveArr2[i6]);
                        if (i3 + i6 != 8) {
                            for (int i7 = 1; i7 < 18; i7++) {
                                pointDouble(pointProjective);
                            }
                        }
                        i6++;
                    }
                    C13365F.negate(pointProjective2.f27061x, pointProjective2.f27061x);
                    int i8 = 0;
                    while (i8 < 4) {
                        int i9 = 1 << i8;
                        int i10 = i5;
                        int i11 = 0;
                        while (i11 < i9) {
                            pointProjectiveArr[i10] = new PointProjective();
                            pointCopy(pointProjectiveArr[i10 - i9], pointProjectiveArr[i10]);
                            pointAdd(pointProjectiveArr2[i8], pointProjectiveArr[i10]);
                            i11++;
                            i10++;
                        }
                        i8++;
                        i5 = i10;
                    }
                    i3++;
                    i2 = i5;
                }
                invertZs(pointProjectiveArr);
                PRECOMP_BASE_WNAF = new PointAffine[32];
                for (int i12 = 0; i12 < 32; i12++) {
                    PointProjective pointProjective3 = pointProjectiveArr[i12];
                    PointAffine[] pointAffineArr = PRECOMP_BASE_WNAF;
                    PointAffine pointAffine = new PointAffine();
                    pointAffineArr[i12] = pointAffine;
                    C13365F.mul(pointProjective3.f27061x, pointProjective3.f27063z, pointAffine.f27059x);
                    C13365F.normalize(pointAffine.f27059x);
                    C13365F.mul(pointProjective3.f27062y, pointProjective3.f27063z, pointAffine.f27060y);
                    C13365F.normalize(pointAffine.f27060y);
                }
                PRECOMP_BASE_COMB = C13365F.createTable(C0567f.f1819h);
                int i13 = 0;
                for (int i14 = 32; i14 < 112; i14++) {
                    PointProjective pointProjective4 = pointProjectiveArr[i14];
                    C13365F.mul(pointProjective4.f27061x, pointProjective4.f27063z, pointProjective4.f27061x);
                    C13365F.normalize(pointProjective4.f27061x);
                    C13365F.mul(pointProjective4.f27062y, pointProjective4.f27063z, pointProjective4.f27062y);
                    C13365F.normalize(pointProjective4.f27062y);
                    C13365F.copy(pointProjective4.f27061x, 0, PRECOMP_BASE_COMB, i13);
                    int i15 = i13 + 16;
                    C13365F.copy(pointProjective4.f27062y, 0, PRECOMP_BASE_COMB, i15);
                    i13 = i15 + 16;
                }
            }
        }
    }

    private static void pruneScalar(byte[] bArr, int i, byte[] bArr2) {
        System.arraycopy(bArr, i, bArr2, 0, 56);
        bArr2[0] = (byte) (bArr2[0] & 252);
        bArr2[55] = (byte) (bArr2[55] | 128);
        bArr2[56] = 0;
    }

    private static byte[] reduceScalar(byte[] bArr) {
        long decode24 = (decode24(bArr, 4) << 4) & 4294967295L;
        long decode32 = decode32(bArr, 7) & 4294967295L;
        long decode242 = (decode24(bArr, 11) << 4) & 4294967295L;
        long decode322 = decode32(bArr, 14) & 4294967295L;
        long decode243 = (decode24(bArr, 18) << 4) & 4294967295L;
        long decode323 = decode32(bArr, 21) & 4294967295L;
        long decode244 = (decode24(bArr, 25) << 4) & 4294967295L;
        long decode324 = decode32(bArr, 28) & 4294967295L;
        long decode245 = (decode24(bArr, 32) << 4) & 4294967295L;
        long decode325 = decode32(bArr, 35) & 4294967295L;
        long decode246 = (decode24(bArr, 39) << 4) & 4294967295L;
        long decode326 = decode32(bArr, 42) & 4294967295L;
        long decode247 = (decode24(bArr, 46) << 4) & 4294967295L;
        long decode327 = decode32(bArr, 49) & 4294967295L;
        long decode248 = (decode24(bArr, 53) << 4) & 4294967295L;
        long decode249 = (decode24(bArr, 74) << 4) & 4294967295L;
        long decode328 = decode32(bArr, 77) & 4294967295L;
        long decode2410 = (decode24(bArr, 81) << 4) & 4294967295L;
        long decode329 = decode32(bArr, 84) & 4294967295L;
        long decode2411 = (decode24(bArr, 88) << 4) & 4294967295L;
        long decode3210 = decode32(bArr, 91) & 4294967295L;
        long decode2412 = (decode24(bArr, 95) << 4) & 4294967295L;
        long decode3211 = decode32(bArr, 98) & 4294967295L;
        long decode2413 = (decode24(bArr, 102) << 4) & 4294967295L;
        long decode3212 = decode32(bArr, 105) & 4294967295L;
        long decode2414 = (decode24(bArr, 109) << 4) & 4294967295L;
        long decode16 = decode16(bArr, 112) & 4294967295L;
        long j = decode2414 + (decode3212 >>> 28);
        long j2 = decode3212 & 268435455;
        long decode3213 = (decode32(bArr, 56) & 4294967295L) + (decode16 * 43969588) + (j * 30366549);
        long decode2415 = ((decode24(bArr, 60) << 4) & 4294967295L) + (decode16 * 30366549) + (j * 163752818);
        long decode3214 = (decode32(bArr, 63) & 4294967295L) + (decode16 * 163752818) + (j * 258169998);
        long decode2416 = ((decode24(bArr, 67) << 4) & 4294967295L) + (decode16 * 258169998) + (j * 96434764);
        long j3 = decode328 + (decode16 * 149865618) + (j * 550336261);
        long j4 = decode327 + (j2 * 43969588);
        long j5 = decode2413 + (decode3211 >>> 28);
        long j6 = decode3211 & 268435455;
        long decode3215 = (decode32(bArr, 70) & 4294967295L) + (decode16 * 96434764) + (j * 227822194) + (j2 * 149865618) + (j5 * 550336261);
        long j7 = decode326 + (j6 * 43969588);
        long j8 = decode2412 + (decode3210 >>> 28);
        long j9 = decode3210 & 268435455;
        long j10 = decode3214 + (j2 * 96434764) + (j5 * 227822194) + (j6 * 149865618) + (j8 * 550336261);
        long j11 = decode325 + (j9 * 43969588);
        long j12 = decode2415 + (j2 * 258169998) + (j5 * 96434764) + (j6 * 227822194) + (j8 * 149865618) + (j9 * 550336261);
        long j13 = decode2411 + (decode329 >>> 28);
        long j14 = decode249 + (decode16 * 227822194) + (j * 149865618) + (j2 * 550336261) + (decode3215 >>> 28);
        long j15 = j3 + (j14 >>> 28);
        long j16 = decode2410 + (decode16 * 550336261) + (j15 >>> 28);
        long j17 = j15 & 268435455;
        long j18 = (decode329 & 268435455) + (j16 >>> 28);
        long j19 = j16 & 268435455;
        long j20 = decode244 + (j19 * 43969588);
        long j21 = decode324 + (j18 * 43969588) + (j19 * 30366549);
        long j22 = decode245 + (j13 * 43969588) + (j18 * 30366549) + (j19 * 163752818);
        long j23 = j11 + (j13 * 30366549) + (j18 * 163752818) + (j19 * 258169998);
        long j24 = decode246 + (j8 * 43969588) + (j9 * 30366549) + (j13 * 163752818) + (j18 * 258169998) + (j19 * 96434764);
        long j25 = j7 + (j8 * 30366549) + (j9 * 163752818) + (j13 * 258169998) + (j18 * 96434764) + (j19 * 227822194);
        long j26 = j4 + (j5 * 30366549) + (j6 * 163752818) + (j8 * 258169998) + (j9 * 96434764) + (j13 * 227822194) + (j18 * 149865618) + (j19 * 550336261);
        long j27 = decode323 + (j17 * 43969588);
        long j28 = j10 + (j12 >>> 28);
        long j29 = decode2416 + (j2 * 227822194) + (j5 * 149865618) + (j6 * 550336261) + (j28 >>> 28);
        long j30 = (decode3215 & 268435455) + (j29 >>> 28);
        long j31 = j29 & 268435455;
        long j32 = (j14 & 268435455) + (j30 >>> 28);
        long j33 = j30 & 268435455;
        long j34 = j24 + (j17 * 227822194) + (j32 * 149865618) + (j33 * 550336261);
        long j35 = j23 + (j17 * 96434764) + (j32 * 227822194) + (j33 * 149865618) + (j31 * 550336261);
        long j36 = decode248 + (j * 43969588) + (j2 * 30366549) + (j5 * 163752818) + (j6 * 258169998) + (j8 * 96434764) + (j9 * 227822194) + (j13 * 149865618) + (j18 * 550336261) + (j26 >>> 28);
        long j37 = decode3213 + (j2 * 163752818) + (j5 * 258169998) + (j6 * 96434764) + (j8 * 227822194) + (j9 * 149865618) + (j13 * 550336261) + (j36 >>> 28);
        long j38 = j36 & 268435455;
        long j39 = (j12 & 268435455) + (j37 >>> 28);
        long j40 = (j28 & 268435455) + (j39 >>> 28);
        long j41 = j39 & 268435455;
        long j42 = decode32 + (j40 * 43969588);
        long j43 = decode242 + (j31 * 43969588) + (j40 * 30366549);
        long j44 = decode322 + (j33 * 43969588) + (j31 * 30366549) + (j40 * 163752818);
        long j45 = decode243 + (j32 * 43969588) + (j33 * 30366549) + (j31 * 163752818) + (j40 * 258169998);
        long j46 = j27 + (j32 * 30366549) + (j33 * 163752818) + (j31 * 258169998) + (j40 * 96434764);
        long j47 = j20 + (j17 * 30366549) + (j32 * 163752818) + (j33 * 258169998) + (j31 * 96434764) + (j40 * 227822194);
        long j48 = j22 + (j17 * 258169998) + (j32 * 96434764) + (j33 * 227822194) + (j31 * 149865618) + (j40 * 550336261);
        long j49 = j21 + (j17 * 163752818) + (j32 * 258169998) + (j33 * 96434764) + (j31 * 227822194) + (j40 * 149865618) + (j41 * 550336261);
        long j50 = j38 & 67108863;
        long j51 = ((j37 & 268435455) * 4) + (j38 >>> 26) + 1;
        long decode3216 = (decode32(bArr, 0) & 4294967295L) + (78101261 * j51);
        long j52 = decode24 + (43969588 * j41) + (141809365 * j51) + (decode3216 >>> 28);
        long j53 = j42 + (30366549 * j41) + (175155932 * j51) + (j52 >>> 28);
        long j54 = j43 + (163752818 * j41) + (64542499 * j51) + (j53 >>> 28);
        long j55 = j53 & 268435455;
        long j56 = j44 + (258169998 * j41) + (158326419 * j51) + (j54 >>> 28);
        long j57 = j45 + (96434764 * j41) + (191173276 * j51) + (j56 >>> 28);
        long j58 = j56 & 268435455;
        long j59 = j46 + (227822194 * j41) + (104575268 * j51) + (j57 >>> 28);
        long j60 = j57 & 268435455;
        long j61 = j47 + (149865618 * j41) + (j51 * 137584065) + (j59 >>> 28);
        long j62 = j49 + (j61 >>> 28);
        long j63 = j48 + (j62 >>> 28);
        long j64 = j35 + (j63 >>> 28);
        long j65 = j63 & 268435455;
        long j66 = j34 + (j64 >>> 28);
        long j67 = j25 + (j17 * 149865618) + (j32 * 550336261) + (j66 >>> 28);
        long j68 = decode247 + (j5 * 43969588) + (j6 * 30366549) + (j8 * 163752818) + (j9 * 258169998) + (j13 * 96434764) + (j18 * 227822194) + (j19 * 149865618) + (j17 * 550336261) + (j67 >>> 28);
        long j69 = (j26 & 268435455) + (j68 >>> 28);
        long j70 = j50 + (j69 >>> 28);
        long j71 = (j70 >>> 26) - 1;
        long j72 = (decode3216 & 268435455) - (j71 & 78101261);
        long j73 = ((j52 & 268435455) - (j71 & 141809365)) + (j72 >> 28);
        long j74 = (j55 - (j71 & 175155932)) + (j73 >> 28);
        long j75 = ((j54 & 268435455) - (j71 & 64542499)) + (j74 >> 28);
        long j76 = (j58 - (j71 & 158326419)) + (j75 >> 28);
        long j77 = (j60 - (j71 & 191173276)) + (j76 >> 28);
        long j78 = ((j59 & 268435455) - (j71 & 104575268)) + (j77 >> 28);
        long j79 = ((j61 & 268435455) - (j71 & 137584065)) + (j78 >> 28);
        long j80 = (j62 & 268435455) + (j79 >> 28);
        long j81 = j65 + (j80 >> 28);
        long j82 = j80 & 268435455;
        long j83 = (j64 & 268435455) + (j81 >> 28);
        long j84 = j81 & 268435455;
        long j85 = (j66 & 268435455) + (j83 >> 28);
        long j86 = j83 & 268435455;
        long j87 = (j67 & 268435455) + (j85 >> 28);
        long j88 = j85 & 268435455;
        long j89 = (j68 & 268435455) + (j87 >> 28);
        long j90 = (j69 & 268435455) + (j89 >> 28);
        byte[] bArr2 = new byte[57];
        encode56(((j73 & 268435455) << 28) | (j72 & 268435455), bArr2, 0);
        encode56(((j75 & 268435455) << 28) | (j74 & 268435455), bArr2, 7);
        encode56((j76 & 268435455) | ((j77 & 268435455) << 28), bArr2, 14);
        encode56((j78 & 268435455) | ((j79 & 268435455) << 28), bArr2, 21);
        encode56(j82 | (j84 << 28), bArr2, 28);
        encode56(j86 | (j88 << 28), bArr2, 35);
        encode56((j87 & 268435455) | ((j89 & 268435455) << 28), bArr2, 42);
        encode56((((j70 & 67108863) + (j90 >> 28)) << 28) | (j90 & 268435455), bArr2, 49);
        return bArr2;
    }

    private static void scalarMult(byte[] bArr, PointProjective pointProjective, PointProjective pointProjective2) {
        int[] iArr = new int[14];
        decodeScalar(bArr, 0, iArr);
        Nat.shiftDownBit(14, iArr, Nat.cadd(14, (~iArr[0]) & 1, iArr, f27057L, iArr));
        int[] pointPrecompute = pointPrecompute(pointProjective, 8);
        PointProjective pointProjective3 = new PointProjective();
        pointLookup15(pointPrecompute, pointProjective2);
        pointAdd(pointProjective, pointProjective2);
        int i = 111;
        while (true) {
            pointLookup(iArr, i, pointPrecompute, pointProjective3);
            pointAdd(pointProjective3, pointProjective2);
            i--;
            if (i < 0) {
                return;
            }
            for (int i2 = 0; i2 < 4; i2++) {
                pointDouble(pointProjective2);
            }
        }
    }

    private static void scalarMultBase(byte[] bArr, PointProjective pointProjective) {
        precompute();
        int[] iArr = new int[15];
        decodeScalar(bArr, 0, iArr);
        iArr[14] = Nat.cadd(14, (~iArr[0]) & 1, iArr, f27057L, iArr) + 4;
        Nat.shiftDownBit(iArr.length, iArr, 0);
        PointAffine pointAffine = new PointAffine();
        pointSetNeutral(pointProjective);
        int i = 17;
        while (true) {
            int i2 = 0;
            int i3 = i;
            while (i2 < 5) {
                int i4 = 0;
                int i5 = i3;
                for (int i6 = 0; i6 < 5; i6++) {
                    i4 = (i4 & (~(1 << i6))) ^ ((iArr[i5 >>> 5] >>> (i5 & 31)) << i6);
                    i5 += 18;
                }
                int i7 = (i4 >>> 4) & 1;
                pointLookup(i2, ((-i7) ^ i4) & 15, pointAffine);
                C13365F.cnegate(i7, pointAffine.f27059x);
                pointAdd(pointAffine, pointProjective);
                i2++;
                i3 = i5;
            }
            i--;
            if (i < 0) {
                return;
            }
            pointDouble(pointProjective);
        }
    }

    private static void scalarMultBaseEncoded(byte[] bArr, byte[] bArr2, int i) {
        PointProjective pointProjective = new PointProjective();
        scalarMultBase(bArr, pointProjective);
        if (encodePoint(pointProjective, bArr2, i) == 0) {
            throw new IllegalStateException();
        }
    }

    public static void scalarMultBaseXY(X448.Friend friend, byte[] bArr, int i, int[] iArr, int[] iArr2) {
        if (friend == null) {
            throw new NullPointerException("This method is only for use by X448");
        }
        byte[] bArr2 = new byte[57];
        pruneScalar(bArr, i, bArr2);
        PointProjective pointProjective = new PointProjective();
        scalarMultBase(bArr2, pointProjective);
        if (checkPoint(pointProjective.f27061x, pointProjective.f27062y, pointProjective.f27063z) == 0) {
            throw new IllegalStateException();
        }
        C13365F.copy(pointProjective.f27061x, 0, iArr, 0);
        C13365F.copy(pointProjective.f27062y, 0, iArr2, 0);
    }

    private static void scalarMultOrderVar(PointProjective pointProjective, PointProjective pointProjective2) {
        byte[] wnafVar = getWnafVar(f27057L, 5);
        PointProjective[] pointProjectiveArr = new PointProjective[8];
        pointPrecomputeVar(pointProjective, pointProjectiveArr, 8);
        pointSetNeutral(pointProjective2);
        int i = 446;
        while (true) {
            byte b = wnafVar[i];
            if (b != 0) {
                int i2 = b >> 31;
                pointAddVar(i2 != 0, pointProjectiveArr[(b ^ i2) >>> 1], pointProjective2);
            }
            i--;
            if (i < 0) {
                return;
            }
            pointDouble(pointProjective2);
        }
    }

    private static void scalarMultStrausVar(int[] iArr, int[] iArr2, PointProjective pointProjective, PointProjective pointProjective2) {
        precompute();
        byte[] wnafVar = getWnafVar(iArr, 7);
        byte[] wnafVar2 = getWnafVar(iArr2, 5);
        PointProjective[] pointProjectiveArr = new PointProjective[8];
        pointPrecomputeVar(pointProjective, pointProjectiveArr, 8);
        pointSetNeutral(pointProjective2);
        int i = 446;
        while (true) {
            byte b = wnafVar[i];
            if (b != 0) {
                int i2 = b >> 31;
                pointAddVar(i2 != 0, PRECOMP_BASE_WNAF[(b ^ i2) >>> 1], pointProjective2);
            }
            byte b2 = wnafVar2[i];
            if (b2 != 0) {
                int i3 = b2 >> 31;
                pointAddVar(i3 != 0, pointProjectiveArr[(b2 ^ i3) >>> 1], pointProjective2);
            }
            i--;
            if (i < 0) {
                return;
            }
            pointDouble(pointProjective2);
        }
    }

    public static void sign(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, byte[] bArr4, int i3, int i4, byte[] bArr5, int i5) {
        implSign(bArr, i, bArr2, i2, bArr3, (byte) 0, bArr4, i3, i4, bArr5, i5);
    }

    public static void sign(byte[] bArr, int i, byte[] bArr2, byte[] bArr3, int i2, int i3, byte[] bArr4, int i4) {
        implSign(bArr, i, bArr2, (byte) 0, bArr3, i2, i3, bArr4, i4);
    }

    public static void signPrehash(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, Xof xof, byte[] bArr4, int i3) {
        byte[] bArr5 = new byte[64];
        if (64 != xof.doFinal(bArr5, 0, 64)) {
            throw new IllegalArgumentException("ph");
        }
        implSign(bArr, i, bArr2, i2, bArr3, (byte) 1, bArr5, 0, bArr5.length, bArr4, i3);
    }

    public static void signPrehash(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, byte[] bArr4, int i3, byte[] bArr5, int i4) {
        implSign(bArr, i, bArr2, i2, bArr3, (byte) 1, bArr4, i3, 64, bArr5, i4);
    }

    public static void signPrehash(byte[] bArr, int i, byte[] bArr2, Xof xof, byte[] bArr3, int i2) {
        byte[] bArr4 = new byte[64];
        if (64 != xof.doFinal(bArr4, 0, 64)) {
            throw new IllegalArgumentException("ph");
        }
        implSign(bArr, i, bArr2, (byte) 1, bArr4, 0, bArr4.length, bArr3, i2);
    }

    public static void signPrehash(byte[] bArr, int i, byte[] bArr2, byte[] bArr3, int i2, byte[] bArr4, int i3) {
        implSign(bArr, i, bArr2, (byte) 1, bArr3, i2, 64, bArr4, i3);
    }

    public static boolean validatePublicKeyFull(byte[] bArr, int i) {
        PointProjective pointProjective = new PointProjective();
        if (decodePointVar(bArr, i, false, pointProjective)) {
            C13365F.normalize(pointProjective.f27061x);
            C13365F.normalize(pointProjective.f27062y);
            C13365F.normalize(pointProjective.f27063z);
            if (isNeutralElementVar(pointProjective.f27061x, pointProjective.f27062y, pointProjective.f27063z)) {
                return false;
            }
            PointProjective pointProjective2 = new PointProjective();
            scalarMultOrderVar(pointProjective, pointProjective2);
            C13365F.normalize(pointProjective2.f27061x);
            C13365F.normalize(pointProjective2.f27062y);
            C13365F.normalize(pointProjective2.f27063z);
            return isNeutralElementVar(pointProjective2.f27061x, pointProjective2.f27062y, pointProjective2.f27063z);
        }
        return false;
    }

    public static boolean validatePublicKeyPartial(byte[] bArr, int i) {
        return decodePointVar(bArr, i, false, new PointProjective());
    }

    public static boolean verify(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, byte[] bArr4, int i3, int i4) {
        return implVerify(bArr, i, bArr2, i2, bArr3, (byte) 0, bArr4, i3, i4);
    }

    public static boolean verifyPrehash(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, Xof xof) {
        byte[] bArr4 = new byte[64];
        if (64 == xof.doFinal(bArr4, 0, 64)) {
            return implVerify(bArr, i, bArr2, i2, bArr3, (byte) 1, bArr4, 0, bArr4.length);
        }
        throw new IllegalArgumentException("ph");
    }

    public static boolean verifyPrehash(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, byte[] bArr4, int i3) {
        return implVerify(bArr, i, bArr2, i2, bArr3, (byte) 1, bArr4, i3, 64);
    }
}
