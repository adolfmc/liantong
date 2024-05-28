package org.bouncycastle.math.p464ec.rfc8032;

import java.security.SecureRandom;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.SHA512Digest;
import org.bouncycastle.math.p464ec.rfc7748.X25519;
import org.bouncycastle.math.p464ec.rfc7748.X25519Field;
import org.bouncycastle.math.raw.Interleave;
import org.bouncycastle.math.raw.Nat;
import org.bouncycastle.math.raw.Nat256;
import org.bouncycastle.util.Arrays;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.bouncycastle.math.ec.rfc8032.Ed25519 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public abstract class Ed25519 {
    private static final int COORD_INTS = 8;

    /* renamed from: L0 */
    private static final int f27037L0 = -50998291;

    /* renamed from: L1 */
    private static final int f27038L1 = 19280294;

    /* renamed from: L2 */
    private static final int f27039L2 = 127719000;

    /* renamed from: L3 */
    private static final int f27040L3 = -6428113;

    /* renamed from: L4 */
    private static final int f27041L4 = 5343;
    private static final long M08L = 255;
    private static final long M28L = 268435455;
    private static final long M32L = 4294967295L;
    private static final int POINT_BYTES = 32;
    private static final int PRECOMP_BLOCKS = 8;
    private static final int PRECOMP_MASK = 7;
    private static final int PRECOMP_POINTS = 8;
    private static final int PRECOMP_SPACING = 8;
    private static final int PRECOMP_TEETH = 4;
    public static final int PREHASH_SIZE = 64;
    public static final int PUBLIC_KEY_SIZE = 32;
    private static final int SCALAR_BYTES = 32;
    private static final int SCALAR_INTS = 8;
    public static final int SECRET_KEY_SIZE = 32;
    public static final int SIGNATURE_SIZE = 64;
    private static final int WNAF_WIDTH = 5;
    private static final int WNAF_WIDTH_BASE = 7;
    private static final byte[] DOM2_PREFIX = {83, 105, 103, 69, 100, 50, 53, 53, 49, 57, 32, 110, 111, 32, 69, 100, 50, 53, 53, 49, 57, 32, 99, 111, 108, 108, 105, 115, 105, 111, 110, 115};

    /* renamed from: P */
    private static final int[] f27042P = {-19, -1, -1, -1, -1, -1, -1, Integer.MAX_VALUE};

    /* renamed from: L */
    private static final int[] f27036L = {1559614445, 1477600026, -1560830762, 350157278, 0, 0, 0, 268435456};
    private static final int[] B_x = {52811034, 25909283, 8072341, 50637101, 13785486, 30858332, 20483199, 20966410, 43936626, 4379245};
    private static final int[] B_y = {40265304, 26843545, 6710886, 53687091, 13421772, 40265318, 26843545, 6710886, 53687091, 13421772};
    private static final int[] C_d = {56195235, 47411844, 25868126, 40503822, 57364, 58321048, 30416477, 31930572, 57760639, 10749657};
    private static final int[] C_d2 = {45281625, 27714825, 18181821, 13898781, 114729, 49533232, 60832955, 30306712, 48412415, 4722099};
    private static final int[] C_d4 = {23454386, 55429651, 2809210, 27797563, 229458, 31957600, 54557047, 27058993, 29715967, 9444199};
    private static final Object PRECOMP_LOCK = new Object();
    private static PointPrecomp[] PRECOMP_BASE_WNAF = null;
    private static int[] PRECOMP_BASE_COMB = null;

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: org.bouncycastle.math.ec.rfc8032.Ed25519$Algorithm */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class Algorithm {
        public static final int Ed25519 = 0;
        public static final int Ed25519ctx = 1;
        public static final int Ed25519ph = 2;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: org.bouncycastle.math.ec.rfc8032.Ed25519$F */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    static class C13363F extends X25519Field {
        private C13363F() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: org.bouncycastle.math.ec.rfc8032.Ed25519$PointAccum */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class PointAccum {

        /* renamed from: u */
        int[] f27043u;

        /* renamed from: v */
        int[] f27044v;

        /* renamed from: x */
        int[] f27045x;

        /* renamed from: y */
        int[] f27046y;

        /* renamed from: z */
        int[] f27047z;

        private PointAccum() {
            this.f27045x = C13363F.create();
            this.f27046y = C13363F.create();
            this.f27047z = C13363F.create();
            this.f27043u = C13363F.create();
            this.f27044v = C13363F.create();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: org.bouncycastle.math.ec.rfc8032.Ed25519$PointAffine */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class PointAffine {

        /* renamed from: x */
        int[] f27048x;

        /* renamed from: y */
        int[] f27049y;

        private PointAffine() {
            this.f27048x = C13363F.create();
            this.f27049y = C13363F.create();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: org.bouncycastle.math.ec.rfc8032.Ed25519$PointExtended */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class PointExtended {

        /* renamed from: t */
        int[] f27050t;

        /* renamed from: x */
        int[] f27051x;

        /* renamed from: y */
        int[] f27052y;

        /* renamed from: z */
        int[] f27053z;

        private PointExtended() {
            this.f27051x = C13363F.create();
            this.f27052y = C13363F.create();
            this.f27053z = C13363F.create();
            this.f27050t = C13363F.create();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: org.bouncycastle.math.ec.rfc8032.Ed25519$PointPrecomp */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class PointPrecomp {
        int[] xyd;
        int[] ymx_h;
        int[] ypx_h;

        private PointPrecomp() {
            this.ymx_h = C13363F.create();
            this.ypx_h = C13363F.create();
            this.xyd = C13363F.create();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: org.bouncycastle.math.ec.rfc8032.Ed25519$PointPrecompZ */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class PointPrecompZ {
        int[] xyd;
        int[] ymx_h;
        int[] ypx_h;

        /* renamed from: z */
        int[] f27054z;

        private PointPrecompZ() {
            this.ymx_h = C13363F.create();
            this.ypx_h = C13363F.create();
            this.xyd = C13363F.create();
            this.f27054z = C13363F.create();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: org.bouncycastle.math.ec.rfc8032.Ed25519$PointTemp */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class PointTemp {

        /* renamed from: r0 */
        int[] f27055r0;

        /* renamed from: r1 */
        int[] f27056r1;

        private PointTemp() {
            this.f27055r0 = C13363F.create();
            this.f27056r1 = C13363F.create();
        }
    }

    private static byte[] calculateS(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        int[] iArr = new int[16];
        decodeScalar(bArr, 0, iArr);
        int[] iArr2 = new int[8];
        decodeScalar(bArr2, 0, iArr2);
        int[] iArr3 = new int[8];
        decodeScalar(bArr3, 0, iArr3);
        Nat256.mulAddTo(iArr2, iArr3, iArr);
        byte[] bArr4 = new byte[64];
        for (int i = 0; i < iArr.length; i++) {
            encode32(iArr[i], bArr4, i * 4);
        }
        return reduceScalar(bArr4);
    }

    private static boolean checkContextVar(byte[] bArr, byte b) {
        return (bArr == null && b == 0) || (bArr != null && bArr.length < 256);
    }

    private static int checkPoint(int[] iArr, int[] iArr2) {
        int[] create = C13363F.create();
        int[] create2 = C13363F.create();
        int[] create3 = C13363F.create();
        C13363F.sqr(iArr, create2);
        C13363F.sqr(iArr2, create3);
        C13363F.mul(create2, create3, create);
        C13363F.sub(create3, create2, create3);
        C13363F.mul(create, C_d, create);
        C13363F.addOne(create);
        C13363F.sub(create, create3, create);
        C13363F.normalize(create);
        return C13363F.isZero(create);
    }

    private static int checkPoint(int[] iArr, int[] iArr2, int[] iArr3) {
        int[] create = C13363F.create();
        int[] create2 = C13363F.create();
        int[] create3 = C13363F.create();
        int[] create4 = C13363F.create();
        C13363F.sqr(iArr, create2);
        C13363F.sqr(iArr2, create3);
        C13363F.sqr(iArr3, create4);
        C13363F.mul(create2, create3, create);
        C13363F.sub(create3, create2, create3);
        C13363F.mul(create3, create4, create3);
        C13363F.sqr(create4, create4);
        C13363F.mul(create, C_d, create);
        C13363F.add(create, create4, create);
        C13363F.sub(create, create3, create);
        C13363F.normalize(create);
        return C13363F.isZero(create);
    }

    private static boolean checkPointVar(byte[] bArr) {
        int[] iArr = new int[8];
        decode32(bArr, 0, iArr, 0, 8);
        iArr[7] = iArr[7] & Integer.MAX_VALUE;
        return !Nat256.gte(iArr, f27042P);
    }

    private static boolean checkScalarVar(byte[] bArr, int[] iArr) {
        decodeScalar(bArr, 0, iArr);
        return !Nat256.gte(iArr, f27036L);
    }

    private static byte[] copy(byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, i, bArr2, 0, i2);
        return bArr2;
    }

    private static Digest createDigest() {
        return new SHA512Digest();
    }

    public static Digest createPrehash() {
        return createDigest();
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

    private static boolean decodePointVar(byte[] bArr, int i, boolean z, PointAffine pointAffine) {
        byte[] copy = copy(bArr, i, 32);
        if (checkPointVar(copy)) {
            int i2 = (copy[31] & 128) >>> 7;
            copy[31] = (byte) (copy[31] & Byte.MAX_VALUE);
            C13363F.decode(copy, 0, pointAffine.f27049y);
            int[] create = C13363F.create();
            int[] create2 = C13363F.create();
            C13363F.sqr(pointAffine.f27049y, create);
            C13363F.mul(C_d, create, create2);
            C13363F.subOne(create);
            C13363F.addOne(create2);
            if (C13363F.sqrtRatioVar(create, create2, pointAffine.f27048x)) {
                C13363F.normalize(pointAffine.f27048x);
                if (i2 == 1 && C13363F.isZeroVar(pointAffine.f27048x)) {
                    return false;
                }
                if (z ^ (i2 != (pointAffine.f27048x[0] & 1))) {
                    C13363F.negate(pointAffine.f27048x, pointAffine.f27048x);
                }
                return true;
            }
            return false;
        }
        return false;
    }

    private static void decodeScalar(byte[] bArr, int i, int[] iArr) {
        decode32(bArr, i, iArr, 0, 8);
    }

    private static void dom2(Digest digest, byte b, byte[] bArr) {
        if (bArr != null) {
            byte[] bArr2 = DOM2_PREFIX;
            int length = bArr2.length;
            int i = length + 2;
            byte[] bArr3 = new byte[bArr.length + i];
            System.arraycopy(bArr2, 0, bArr3, 0, length);
            bArr3[length] = b;
            bArr3[length + 1] = (byte) bArr.length;
            System.arraycopy(bArr, 0, bArr3, i, bArr.length);
            digest.update(bArr3, 0, bArr3.length);
        }
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

    private static int encodePoint(PointAccum pointAccum, byte[] bArr, int i) {
        int[] create = C13363F.create();
        int[] create2 = C13363F.create();
        C13363F.inv(pointAccum.f27047z, create2);
        C13363F.mul(pointAccum.f27045x, create2, create);
        C13363F.mul(pointAccum.f27046y, create2, create2);
        C13363F.normalize(create);
        C13363F.normalize(create2);
        int checkPoint = checkPoint(create, create2);
        C13363F.encode(create2, bArr, i);
        int i2 = (i + 32) - 1;
        bArr[i2] = (byte) (((create[0] & 1) << 7) | bArr[i2]);
        return checkPoint;
    }

    public static void generatePrivateKey(SecureRandom secureRandom, byte[] bArr) {
        secureRandom.nextBytes(bArr);
    }

    public static void generatePublicKey(byte[] bArr, int i, byte[] bArr2, int i2) {
        Digest createDigest = createDigest();
        byte[] bArr3 = new byte[createDigest.getDigestSize()];
        createDigest.update(bArr, i, 32);
        createDigest.doFinal(bArr3, 0);
        byte[] bArr4 = new byte[32];
        pruneScalar(bArr3, 0, bArr4);
        scalarMultBaseEncoded(bArr4, bArr2, i2);
    }

    private static int getWindow4(int[] iArr, int i) {
        return (iArr[i >>> 3] >>> ((i & 7) << 2)) & 15;
    }

    private static byte[] getWnafVar(int[] iArr, int i) {
        int[] iArr2 = new int[16];
        int length = iArr2.length;
        int i2 = 0;
        int i3 = 8;
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
        byte[] bArr = new byte[253];
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

    private static void implSign(Digest digest, byte[] bArr, byte[] bArr2, byte[] bArr3, int i, byte[] bArr4, byte b, byte[] bArr5, int i2, int i3, byte[] bArr6, int i4) {
        dom2(digest, b, bArr4);
        digest.update(bArr, 32, 32);
        digest.update(bArr5, i2, i3);
        digest.doFinal(bArr, 0);
        byte[] reduceScalar = reduceScalar(bArr);
        byte[] bArr7 = new byte[32];
        scalarMultBaseEncoded(reduceScalar, bArr7, 0);
        dom2(digest, b, bArr4);
        digest.update(bArr7, 0, 32);
        digest.update(bArr3, i, 32);
        digest.update(bArr5, i2, i3);
        digest.doFinal(bArr, 0);
        byte[] calculateS = calculateS(reduceScalar, reduceScalar(bArr), bArr2);
        System.arraycopy(bArr7, 0, bArr6, i4, 32);
        System.arraycopy(calculateS, 0, bArr6, i4 + 32, 32);
    }

    private static void implSign(byte[] bArr, int i, byte[] bArr2, byte b, byte[] bArr3, int i2, int i3, byte[] bArr4, int i4) {
        if (!checkContextVar(bArr2, b)) {
            throw new IllegalArgumentException("ctx");
        }
        Digest createDigest = createDigest();
        byte[] bArr5 = new byte[createDigest.getDigestSize()];
        createDigest.update(bArr, i, 32);
        createDigest.doFinal(bArr5, 0);
        byte[] bArr6 = new byte[32];
        pruneScalar(bArr5, 0, bArr6);
        byte[] bArr7 = new byte[32];
        scalarMultBaseEncoded(bArr6, bArr7, 0);
        implSign(createDigest, bArr5, bArr6, bArr7, 0, bArr2, b, bArr3, i2, i3, bArr4, i4);
    }

    private static void implSign(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, byte b, byte[] bArr4, int i3, int i4, byte[] bArr5, int i5) {
        if (!checkContextVar(bArr3, b)) {
            throw new IllegalArgumentException("ctx");
        }
        Digest createDigest = createDigest();
        byte[] bArr6 = new byte[createDigest.getDigestSize()];
        createDigest.update(bArr, i, 32);
        createDigest.doFinal(bArr6, 0);
        byte[] bArr7 = new byte[32];
        pruneScalar(bArr6, 0, bArr7);
        implSign(createDigest, bArr6, bArr7, bArr2, i2, bArr3, b, bArr4, i3, i4, bArr5, i5);
    }

    private static boolean implVerify(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, byte b, byte[] bArr4, int i3, int i4) {
        if (checkContextVar(bArr3, b)) {
            byte[] copy = copy(bArr, i, 32);
            byte[] copy2 = copy(bArr, i + 32, 32);
            if (checkPointVar(copy)) {
                int[] iArr = new int[8];
                if (checkScalarVar(copy2, iArr)) {
                    PointAffine pointAffine = new PointAffine();
                    if (decodePointVar(bArr2, i2, true, pointAffine)) {
                        Digest createDigest = createDigest();
                        byte[] bArr5 = new byte[createDigest.getDigestSize()];
                        dom2(createDigest, b, bArr3);
                        createDigest.update(copy, 0, 32);
                        createDigest.update(bArr2, i2, 32);
                        createDigest.update(bArr4, i3, i4);
                        createDigest.doFinal(bArr5, 0);
                        int[] iArr2 = new int[8];
                        decodeScalar(reduceScalar(bArr5), 0, iArr2);
                        PointAccum pointAccum = new PointAccum();
                        scalarMultStrausVar(iArr, iArr2, pointAffine, pointAccum);
                        byte[] bArr6 = new byte[32];
                        return encodePoint(pointAccum, bArr6, 0) != 0 && Arrays.areEqual(bArr6, copy);
                    }
                    return false;
                }
                return false;
            }
            return false;
        }
        throw new IllegalArgumentException("ctx");
    }

    private static void invertDoubleZs(PointExtended[] pointExtendedArr) {
        int length = pointExtendedArr.length;
        int[] createTable = C13363F.createTable(length);
        int[] create = C13363F.create();
        C13363F.copy(pointExtendedArr[0].f27053z, 0, create, 0);
        C13363F.copy(create, 0, createTable, 0);
        int i = 0;
        while (true) {
            i++;
            if (i >= length) {
                break;
            }
            C13363F.mul(create, pointExtendedArr[i].f27053z, create);
            C13363F.copy(create, 0, createTable, i * 10);
        }
        C13363F.add(create, create, create);
        C13363F.invVar(create, create);
        int i2 = i - 1;
        int[] create2 = C13363F.create();
        while (i2 > 0) {
            int i3 = i2 - 1;
            C13363F.copy(createTable, i3 * 10, create2, 0);
            C13363F.mul(create2, create, create2);
            C13363F.mul(create, pointExtendedArr[i2].f27053z, create);
            C13363F.copy(create2, 0, pointExtendedArr[i2].f27053z, 0);
            i2 = i3;
        }
        C13363F.copy(create, 0, pointExtendedArr[0].f27053z, 0);
    }

    private static boolean isNeutralElementVar(int[] iArr, int[] iArr2) {
        return C13363F.isZeroVar(iArr) && C13363F.isOneVar(iArr2);
    }

    private static boolean isNeutralElementVar(int[] iArr, int[] iArr2, int[] iArr3) {
        return C13363F.isZeroVar(iArr) && C13363F.areEqualVar(iArr2, iArr3);
    }

    private static void pointAdd(PointExtended pointExtended, PointExtended pointExtended2, PointExtended pointExtended3, PointTemp pointTemp) {
        int[] iArr = pointExtended3.f27051x;
        int[] iArr2 = pointExtended3.f27052y;
        int[] iArr3 = pointTemp.f27055r0;
        int[] iArr4 = pointTemp.f27056r1;
        C13363F.apm(pointExtended.f27052y, pointExtended.f27051x, iArr2, iArr);
        C13363F.apm(pointExtended2.f27052y, pointExtended2.f27051x, iArr4, iArr3);
        C13363F.mul(iArr, iArr3, iArr);
        C13363F.mul(iArr2, iArr4, iArr2);
        C13363F.mul(pointExtended.f27050t, pointExtended2.f27050t, iArr3);
        C13363F.mul(iArr3, C_d2, iArr3);
        C13363F.add(pointExtended.f27053z, pointExtended.f27053z, iArr4);
        C13363F.mul(iArr4, pointExtended2.f27053z, iArr4);
        C13363F.apm(iArr2, iArr, iArr2, iArr);
        C13363F.apm(iArr4, iArr3, iArr4, iArr3);
        C13363F.mul(iArr, iArr2, pointExtended3.f27050t);
        C13363F.mul(iArr3, iArr4, pointExtended3.f27053z);
        C13363F.mul(iArr, iArr3, pointExtended3.f27051x);
        C13363F.mul(iArr2, iArr4, pointExtended3.f27052y);
    }

    private static void pointAdd(PointPrecomp pointPrecomp, PointAccum pointAccum, PointTemp pointTemp) {
        int[] iArr = pointAccum.f27045x;
        int[] iArr2 = pointAccum.f27046y;
        int[] iArr3 = pointTemp.f27055r0;
        int[] iArr4 = pointAccum.f27043u;
        int[] iArr5 = pointAccum.f27044v;
        C13363F.apm(pointAccum.f27046y, pointAccum.f27045x, iArr2, iArr);
        C13363F.mul(iArr, pointPrecomp.ymx_h, iArr);
        C13363F.mul(iArr2, pointPrecomp.ypx_h, iArr2);
        C13363F.mul(pointAccum.f27043u, pointAccum.f27044v, iArr3);
        C13363F.mul(iArr3, pointPrecomp.xyd, iArr3);
        C13363F.apm(iArr2, iArr, iArr5, iArr4);
        C13363F.apm(pointAccum.f27047z, iArr3, iArr2, iArr);
        C13363F.mul(iArr, iArr2, pointAccum.f27047z);
        C13363F.mul(iArr, iArr4, pointAccum.f27045x);
        C13363F.mul(iArr2, iArr5, pointAccum.f27046y);
    }

    private static void pointAdd(PointPrecompZ pointPrecompZ, PointAccum pointAccum, PointTemp pointTemp) {
        int[] iArr = pointAccum.f27045x;
        int[] iArr2 = pointAccum.f27046y;
        int[] iArr3 = pointTemp.f27055r0;
        int[] iArr4 = pointAccum.f27047z;
        int[] iArr5 = pointAccum.f27043u;
        int[] iArr6 = pointAccum.f27044v;
        C13363F.apm(pointAccum.f27046y, pointAccum.f27045x, iArr2, iArr);
        C13363F.mul(iArr, pointPrecompZ.ymx_h, iArr);
        C13363F.mul(iArr2, pointPrecompZ.ypx_h, iArr2);
        C13363F.mul(pointAccum.f27043u, pointAccum.f27044v, iArr3);
        C13363F.mul(iArr3, pointPrecompZ.xyd, iArr3);
        C13363F.mul(pointAccum.f27047z, pointPrecompZ.f27054z, iArr4);
        C13363F.apm(iArr2, iArr, iArr6, iArr5);
        C13363F.apm(iArr4, iArr3, iArr2, iArr);
        C13363F.mul(iArr, iArr2, pointAccum.f27047z);
        C13363F.mul(iArr, iArr5, pointAccum.f27045x);
        C13363F.mul(iArr2, iArr6, pointAccum.f27046y);
    }

    private static void pointAddVar(boolean z, PointPrecomp pointPrecomp, PointAccum pointAccum, PointTemp pointTemp) {
        int[] iArr;
        int[] iArr2;
        int[] iArr3 = pointAccum.f27045x;
        int[] iArr4 = pointAccum.f27046y;
        int[] iArr5 = pointTemp.f27055r0;
        int[] iArr6 = pointAccum.f27043u;
        int[] iArr7 = pointAccum.f27044v;
        if (z) {
            iArr2 = iArr3;
            iArr = iArr4;
        } else {
            iArr = iArr3;
            iArr2 = iArr4;
        }
        C13363F.apm(pointAccum.f27046y, pointAccum.f27045x, iArr4, iArr3);
        C13363F.mul(iArr, pointPrecomp.ymx_h, iArr);
        C13363F.mul(iArr2, pointPrecomp.ypx_h, iArr2);
        C13363F.mul(pointAccum.f27043u, pointAccum.f27044v, iArr5);
        C13363F.mul(iArr5, pointPrecomp.xyd, iArr5);
        C13363F.apm(iArr4, iArr3, iArr7, iArr6);
        C13363F.apm(pointAccum.f27047z, iArr5, iArr2, iArr);
        C13363F.mul(iArr3, iArr4, pointAccum.f27047z);
        C13363F.mul(iArr3, iArr6, pointAccum.f27045x);
        C13363F.mul(iArr4, iArr7, pointAccum.f27046y);
    }

    private static void pointAddVar(boolean z, PointPrecompZ pointPrecompZ, PointAccum pointAccum, PointTemp pointTemp) {
        int[] iArr;
        int[] iArr2;
        int[] iArr3 = pointAccum.f27045x;
        int[] iArr4 = pointAccum.f27046y;
        int[] iArr5 = pointTemp.f27055r0;
        int[] iArr6 = pointAccum.f27047z;
        int[] iArr7 = pointAccum.f27043u;
        int[] iArr8 = pointAccum.f27044v;
        if (z) {
            iArr2 = iArr3;
            iArr = iArr4;
        } else {
            iArr = iArr3;
            iArr2 = iArr4;
        }
        C13363F.apm(pointAccum.f27046y, pointAccum.f27045x, iArr4, iArr3);
        C13363F.mul(iArr, pointPrecompZ.ymx_h, iArr);
        C13363F.mul(iArr2, pointPrecompZ.ypx_h, iArr2);
        C13363F.mul(pointAccum.f27043u, pointAccum.f27044v, iArr5);
        C13363F.mul(iArr5, pointPrecompZ.xyd, iArr5);
        C13363F.mul(pointAccum.f27047z, pointPrecompZ.f27054z, iArr6);
        C13363F.apm(iArr4, iArr3, iArr8, iArr7);
        C13363F.apm(iArr6, iArr5, iArr2, iArr);
        C13363F.mul(iArr3, iArr4, pointAccum.f27047z);
        C13363F.mul(iArr3, iArr7, pointAccum.f27045x);
        C13363F.mul(iArr4, iArr8, pointAccum.f27046y);
    }

    private static void pointCopy(PointAccum pointAccum, PointExtended pointExtended) {
        C13363F.copy(pointAccum.f27045x, 0, pointExtended.f27051x, 0);
        C13363F.copy(pointAccum.f27046y, 0, pointExtended.f27052y, 0);
        C13363F.copy(pointAccum.f27047z, 0, pointExtended.f27053z, 0);
        C13363F.mul(pointAccum.f27043u, pointAccum.f27044v, pointExtended.f27050t);
    }

    private static void pointCopy(PointAffine pointAffine, PointExtended pointExtended) {
        C13363F.copy(pointAffine.f27048x, 0, pointExtended.f27051x, 0);
        C13363F.copy(pointAffine.f27049y, 0, pointExtended.f27052y, 0);
        C13363F.one(pointExtended.f27053z);
        C13363F.mul(pointAffine.f27048x, pointAffine.f27049y, pointExtended.f27050t);
    }

    private static void pointCopy(PointExtended pointExtended, PointPrecompZ pointPrecompZ) {
        C13363F.apm(pointExtended.f27052y, pointExtended.f27051x, pointPrecompZ.ypx_h, pointPrecompZ.ymx_h);
        C13363F.mul(pointExtended.f27050t, C_d2, pointPrecompZ.xyd);
        C13363F.add(pointExtended.f27053z, pointExtended.f27053z, pointPrecompZ.f27054z);
    }

    private static void pointDouble(PointAccum pointAccum) {
        int[] iArr = pointAccum.f27045x;
        int[] iArr2 = pointAccum.f27046y;
        int[] iArr3 = pointAccum.f27047z;
        int[] iArr4 = pointAccum.f27043u;
        int[] iArr5 = pointAccum.f27044v;
        C13363F.add(pointAccum.f27045x, pointAccum.f27046y, iArr4);
        C13363F.sqr(pointAccum.f27045x, iArr);
        C13363F.sqr(pointAccum.f27046y, iArr2);
        C13363F.sqr(pointAccum.f27047z, iArr3);
        C13363F.add(iArr3, iArr3, iArr3);
        C13363F.apm(iArr, iArr2, iArr5, iArr2);
        C13363F.sqr(iArr4, iArr4);
        C13363F.sub(iArr5, iArr4, iArr4);
        C13363F.add(iArr3, iArr2, iArr);
        C13363F.carry(iArr);
        C13363F.mul(iArr, iArr2, pointAccum.f27047z);
        C13363F.mul(iArr, iArr4, pointAccum.f27045x);
        C13363F.mul(iArr2, iArr5, pointAccum.f27046y);
    }

    private static void pointLookup(int i, int i2, PointPrecomp pointPrecomp) {
        int i3 = i * 8 * 3 * 10;
        for (int i4 = 0; i4 < 8; i4++) {
            int i5 = ((i4 ^ i2) - 1) >> 31;
            C13363F.cmov(i5, PRECOMP_BASE_COMB, i3, pointPrecomp.ymx_h, 0);
            int i6 = i3 + 10;
            C13363F.cmov(i5, PRECOMP_BASE_COMB, i6, pointPrecomp.ypx_h, 0);
            int i7 = i6 + 10;
            C13363F.cmov(i5, PRECOMP_BASE_COMB, i7, pointPrecomp.xyd, 0);
            i3 = i7 + 10;
        }
    }

    private static void pointLookupZ(int[] iArr, int i, int[] iArr2, PointPrecompZ pointPrecompZ) {
        int window4 = getWindow4(iArr, i);
        int i2 = (window4 >>> 3) ^ 1;
        int i3 = (window4 ^ (-i2)) & 7;
        int i4 = 0;
        for (int i5 = 0; i5 < 8; i5++) {
            int i6 = ((i5 ^ i3) - 1) >> 31;
            C13363F.cmov(i6, iArr2, i4, pointPrecompZ.ymx_h, 0);
            int i7 = i4 + 10;
            C13363F.cmov(i6, iArr2, i7, pointPrecompZ.ypx_h, 0);
            int i8 = i7 + 10;
            C13363F.cmov(i6, iArr2, i8, pointPrecompZ.xyd, 0);
            int i9 = i8 + 10;
            C13363F.cmov(i6, iArr2, i9, pointPrecompZ.f27054z, 0);
            i4 = i9 + 10;
        }
        C13363F.cswap(i2, pointPrecompZ.ymx_h, pointPrecompZ.ypx_h);
        C13363F.cnegate(i2, pointPrecompZ.xyd);
    }

    private static void pointPrecompute(PointAffine pointAffine, PointExtended[] pointExtendedArr, int i, PointTemp pointTemp) {
        PointExtended pointExtended = new PointExtended();
        pointExtendedArr[0] = pointExtended;
        pointCopy(pointAffine, pointExtended);
        PointExtended pointExtended2 = new PointExtended();
        pointAdd(pointExtendedArr[0], pointExtendedArr[0], pointExtended2, pointTemp);
        for (int i2 = 1; i2 < i; i2++) {
            PointExtended pointExtended3 = pointExtendedArr[i2 - 1];
            PointExtended pointExtended4 = new PointExtended();
            pointExtendedArr[i2] = pointExtended4;
            pointAdd(pointExtended3, pointExtended2, pointExtended4, pointTemp);
        }
    }

    private static void pointPrecomputeZ(PointAffine pointAffine, PointPrecompZ[] pointPrecompZArr, int i, PointTemp pointTemp) {
        PointExtended pointExtended = new PointExtended();
        pointCopy(pointAffine, pointExtended);
        PointExtended pointExtended2 = new PointExtended();
        pointAdd(pointExtended, pointExtended, pointExtended2, pointTemp);
        int i2 = 0;
        while (true) {
            PointPrecompZ pointPrecompZ = new PointPrecompZ();
            pointPrecompZArr[i2] = pointPrecompZ;
            pointCopy(pointExtended, pointPrecompZ);
            i2++;
            if (i2 == i) {
                return;
            }
            pointAdd(pointExtended, pointExtended2, pointExtended, pointTemp);
        }
    }

    private static int[] pointPrecomputeZ(PointAffine pointAffine, int i, PointTemp pointTemp) {
        PointExtended pointExtended = new PointExtended();
        pointCopy(pointAffine, pointExtended);
        PointExtended pointExtended2 = new PointExtended();
        pointAdd(pointExtended, pointExtended, pointExtended2, pointTemp);
        PointPrecompZ pointPrecompZ = new PointPrecompZ();
        int[] createTable = C13363F.createTable(i * 4);
        int i2 = 0;
        int i3 = 0;
        while (true) {
            pointCopy(pointExtended, pointPrecompZ);
            C13363F.copy(pointPrecompZ.ymx_h, 0, createTable, i2);
            int i4 = i2 + 10;
            C13363F.copy(pointPrecompZ.ypx_h, 0, createTable, i4);
            int i5 = i4 + 10;
            C13363F.copy(pointPrecompZ.xyd, 0, createTable, i5);
            int i6 = i5 + 10;
            C13363F.copy(pointPrecompZ.f27054z, 0, createTable, i6);
            i2 = i6 + 10;
            i3++;
            if (i3 == i) {
                return createTable;
            }
            pointAdd(pointExtended, pointExtended2, pointExtended, pointTemp);
        }
    }

    private static void pointSetNeutral(PointAccum pointAccum) {
        C13363F.zero(pointAccum.f27045x);
        C13363F.one(pointAccum.f27046y);
        C13363F.one(pointAccum.f27047z);
        C13363F.zero(pointAccum.f27043u);
        C13363F.one(pointAccum.f27044v);
    }

    public static void precompute() {
        synchronized (PRECOMP_LOCK) {
            if (PRECOMP_BASE_WNAF == null || PRECOMP_BASE_COMB == null) {
                PointExtended[] pointExtendedArr = new PointExtended[96];
                PointTemp pointTemp = new PointTemp();
                PointAffine pointAffine = new PointAffine();
                int i = 0;
                C13363F.copy(B_x, 0, pointAffine.f27048x, 0);
                C13363F.copy(B_y, 0, pointAffine.f27049y, 0);
                pointPrecompute(pointAffine, pointExtendedArr, 32, pointTemp);
                PointAccum pointAccum = new PointAccum();
                C13363F.copy(B_x, 0, pointAccum.f27045x, 0);
                C13363F.copy(B_y, 0, pointAccum.f27046y, 0);
                C13363F.one(pointAccum.f27047z);
                C13363F.copy(pointAccum.f27045x, 0, pointAccum.f27043u, 0);
                C13363F.copy(pointAccum.f27046y, 0, pointAccum.f27044v, 0);
                int i2 = 4;
                PointExtended[] pointExtendedArr2 = new PointExtended[4];
                for (int i3 = 0; i3 < 4; i3++) {
                    pointExtendedArr2[i3] = new PointExtended();
                }
                PointExtended pointExtended = new PointExtended();
                int i4 = 32;
                int i5 = 0;
                while (i5 < 8) {
                    int i6 = i4 + 1;
                    PointExtended pointExtended2 = new PointExtended();
                    pointExtendedArr[i4] = pointExtended2;
                    int i7 = i;
                    while (i7 < i2) {
                        if (i7 == 0) {
                            pointCopy(pointAccum, pointExtended2);
                        } else {
                            pointCopy(pointAccum, pointExtended);
                            pointAdd(pointExtended2, pointExtended, pointExtended2, pointTemp);
                        }
                        pointDouble(pointAccum);
                        pointCopy(pointAccum, pointExtendedArr2[i7]);
                        if (i5 + i7 != 10) {
                            for (int i8 = 1; i8 < 8; i8++) {
                                pointDouble(pointAccum);
                            }
                        }
                        i7++;
                        i2 = 4;
                    }
                    C13363F.negate(pointExtended2.f27051x, pointExtended2.f27051x);
                    C13363F.negate(pointExtended2.f27050t, pointExtended2.f27050t);
                    i4 = i6;
                    int i9 = 0;
                    while (i9 < 3) {
                        int i10 = 1 << i9;
                        int i11 = i4;
                        int i12 = 0;
                        while (i12 < i10) {
                            pointExtendedArr[i11] = new PointExtended();
                            pointAdd(pointExtendedArr[i11 - i10], pointExtendedArr2[i9], pointExtendedArr[i11], pointTemp);
                            i12++;
                            i11++;
                        }
                        i9++;
                        i4 = i11;
                    }
                    i5++;
                    i2 = 4;
                    i = 0;
                }
                invertDoubleZs(pointExtendedArr);
                PRECOMP_BASE_WNAF = new PointPrecomp[32];
                for (int i13 = 0; i13 < 32; i13++) {
                    PointExtended pointExtended3 = pointExtendedArr[i13];
                    PointPrecomp[] pointPrecompArr = PRECOMP_BASE_WNAF;
                    PointPrecomp pointPrecomp = new PointPrecomp();
                    pointPrecompArr[i13] = pointPrecomp;
                    C13363F.mul(pointExtended3.f27051x, pointExtended3.f27053z, pointExtended3.f27051x);
                    C13363F.mul(pointExtended3.f27052y, pointExtended3.f27053z, pointExtended3.f27052y);
                    C13363F.apm(pointExtended3.f27052y, pointExtended3.f27051x, pointPrecomp.ypx_h, pointPrecomp.ymx_h);
                    C13363F.mul(pointExtended3.f27051x, pointExtended3.f27052y, pointPrecomp.xyd);
                    C13363F.mul(pointPrecomp.xyd, C_d4, pointPrecomp.xyd);
                    C13363F.normalize(pointPrecomp.ymx_h);
                    C13363F.normalize(pointPrecomp.ypx_h);
                    C13363F.normalize(pointPrecomp.xyd);
                }
                PRECOMP_BASE_COMB = C13363F.createTable(192);
                PointPrecomp pointPrecomp2 = new PointPrecomp();
                int i14 = 0;
                for (int i15 = 32; i15 < 96; i15++) {
                    PointExtended pointExtended4 = pointExtendedArr[i15];
                    C13363F.mul(pointExtended4.f27051x, pointExtended4.f27053z, pointExtended4.f27051x);
                    C13363F.mul(pointExtended4.f27052y, pointExtended4.f27053z, pointExtended4.f27052y);
                    C13363F.apm(pointExtended4.f27052y, pointExtended4.f27051x, pointPrecomp2.ypx_h, pointPrecomp2.ymx_h);
                    C13363F.mul(pointExtended4.f27051x, pointExtended4.f27052y, pointPrecomp2.xyd);
                    C13363F.mul(pointPrecomp2.xyd, C_d4, pointPrecomp2.xyd);
                    C13363F.normalize(pointPrecomp2.ymx_h);
                    C13363F.normalize(pointPrecomp2.ypx_h);
                    C13363F.normalize(pointPrecomp2.xyd);
                    C13363F.copy(pointPrecomp2.ymx_h, 0, PRECOMP_BASE_COMB, i14);
                    int i16 = i14 + 10;
                    C13363F.copy(pointPrecomp2.ypx_h, 0, PRECOMP_BASE_COMB, i16);
                    int i17 = i16 + 10;
                    C13363F.copy(pointPrecomp2.xyd, 0, PRECOMP_BASE_COMB, i17);
                    i14 = i17 + 10;
                }
            }
        }
    }

    private static void pruneScalar(byte[] bArr, int i, byte[] bArr2) {
        System.arraycopy(bArr, i, bArr2, 0, 32);
        bArr2[0] = (byte) (bArr2[0] & 248);
        bArr2[31] = (byte) (bArr2[31] & Byte.MAX_VALUE);
        bArr2[31] = (byte) (bArr2[31] | 64);
    }

    private static byte[] reduceScalar(byte[] bArr) {
        long decode24 = (decode24(bArr, 4) << 4) & 4294967295L;
        long decode32 = decode32(bArr, 7) & 4294967295L;
        long decode242 = (decode24(bArr, 11) << 4) & 4294967295L;
        long decode322 = decode32(bArr, 14) & 4294967295L;
        long decode243 = (decode24(bArr, 18) << 4) & 4294967295L;
        long decode323 = decode32(bArr, 21) & 4294967295L;
        long decode324 = decode32(bArr, 28) & 4294967295L;
        long decode325 = decode32(bArr, 49) & 4294967295L;
        long decode244 = (decode24(bArr, 53) << 4) & 4294967295L;
        long decode326 = decode32(bArr, 56) & 4294967295L;
        long decode245 = (decode24(bArr, 60) << 4) & 4294967295L;
        long j = bArr[63] & 255;
        long decode246 = ((decode24(bArr, 46) << 4) & 4294967295L) - (j * 5343);
        long j2 = decode245 + (decode326 >> 28);
        long j3 = decode326 & 268435455;
        long decode247 = (((decode24(bArr, 32) << 4) & 4294967295L) - (j * (-50998291))) - (j2 * 19280294);
        long decode327 = ((decode32(bArr, 35) & 4294967295L) - (j * 19280294)) - (j2 * 127719000);
        long decode328 = ((decode32(bArr, 42) & 4294967295L) - (j * (-6428113))) - (j2 * 5343);
        long decode248 = ((((decode24(bArr, 39) << 4) & 4294967295L) - (j * 127719000)) - (j2 * (-6428113))) - (j3 * 5343);
        long j4 = decode244 + (decode325 >> 28);
        long j5 = decode325 & 268435455;
        long j6 = (decode327 - (j3 * (-6428113))) - (j4 * 5343);
        long j7 = ((decode247 - (j3 * 127719000)) - (j4 * (-6428113))) - (j5 * 5343);
        long j8 = decode246 + (decode328 >> 28);
        long j9 = (decode328 & 268435455) + (decode248 >> 28);
        long j10 = (decode322 - (j8 * (-50998291))) - (j9 * 19280294);
        long j11 = ((decode243 - (j5 * (-50998291))) - (j8 * 19280294)) - (j9 * 127719000);
        long decode249 = ((((((decode24(bArr, 25) << 4) & 4294967295L) - (j3 * (-50998291))) - (j4 * 19280294)) - (j5 * 127719000)) - (j8 * (-6428113))) - (j9 * 5343);
        long j12 = (decode248 & 268435455) + (j6 >> 28);
        long j13 = (decode242 - (j9 * (-50998291))) - (j12 * 19280294);
        long j14 = j10 - (j12 * 127719000);
        long j15 = ((((decode323 - (j4 * (-50998291))) - (j5 * 19280294)) - (j8 * 127719000)) - (j9 * (-6428113))) - (j12 * 5343);
        long j16 = (j6 & 268435455) + (j7 >> 28);
        long j17 = j7 & 268435455;
        long j18 = decode24 - (j16 * (-50998291));
        long j19 = (decode32 - (j12 * (-50998291))) - (j16 * 19280294);
        long j20 = j13 - (j16 * 127719000);
        long j21 = j14 - (j16 * (-6428113));
        long j22 = (j11 - (j12 * (-6428113))) - (j16 * 5343);
        long j23 = (((((decode324 - (j2 * (-50998291))) - (j3 * 19280294)) - (j4 * 127719000)) - (j5 * (-6428113))) - (j8 * 5343)) + (decode249 >> 28);
        long j24 = j23 & 268435455;
        long j25 = j24 >>> 27;
        long j26 = j17 + (j23 >> 28) + j25;
        long decode329 = (decode32(bArr, 0) & 4294967295L) - (j26 * (-50998291));
        long j27 = (j18 - (j26 * 19280294)) + (decode329 >> 28);
        long j28 = decode329 & 268435455;
        long j29 = (j19 - (j26 * 127719000)) + (j27 >> 28);
        long j30 = (j20 - (j26 * (-6428113))) + (j29 >> 28);
        long j31 = (j21 - (j26 * 5343)) + (j30 >> 28);
        long j32 = j22 + (j31 >> 28);
        long j33 = j31 & 268435455;
        long j34 = j15 + (j32 >> 28);
        long j35 = (decode249 & 268435455) + (j34 >> 28);
        long j36 = j24 + (j35 >> 28);
        long j37 = (j36 >> 28) - j25;
        long j38 = j28 + (j37 & (-50998291));
        long j39 = (j27 & 268435455) + (j37 & 19280294) + (j38 >> 28);
        long j40 = (j29 & 268435455) + (j37 & 127719000) + (j39 >> 28);
        long j41 = (j30 & 268435455) + (j37 & (-6428113)) + (j40 >> 28);
        long j42 = j33 + (j37 & 5343) + (j41 >> 28);
        long j43 = (j32 & 268435455) + (j42 >> 28);
        long j44 = (j34 & 268435455) + (j43 >> 28);
        long j45 = (j35 & 268435455) + (j44 >> 28);
        byte[] bArr2 = new byte[32];
        encode56((j38 & 268435455) | ((j39 & 268435455) << 28), bArr2, 0);
        encode56(((j41 & 268435455) << 28) | (j40 & 268435455), bArr2, 7);
        encode56((j42 & 268435455) | ((j43 & 268435455) << 28), bArr2, 14);
        encode56((j44 & 268435455) | ((j45 & 268435455) << 28), bArr2, 21);
        encode32((int) ((j36 & 268435455) + (j45 >> 28)), bArr2, 28);
        return bArr2;
    }

    private static void scalarMult(byte[] bArr, PointAffine pointAffine, PointAccum pointAccum) {
        int[] iArr = new int[8];
        decodeScalar(bArr, 0, iArr);
        Nat.cadd(8, (~iArr[0]) & 1, iArr, f27036L, iArr);
        Nat.shiftDownBit(8, iArr, 1);
        PointPrecompZ pointPrecompZ = new PointPrecompZ();
        PointTemp pointTemp = new PointTemp();
        int[] pointPrecomputeZ = pointPrecomputeZ(pointAffine, 8, pointTemp);
        pointSetNeutral(pointAccum);
        int i = 63;
        while (true) {
            pointLookupZ(iArr, i, pointPrecomputeZ, pointPrecompZ);
            pointAdd(pointPrecompZ, pointAccum, pointTemp);
            i--;
            if (i < 0) {
                return;
            }
            for (int i2 = 0; i2 < 4; i2++) {
                pointDouble(pointAccum);
            }
        }
    }

    private static void scalarMultBase(byte[] bArr, PointAccum pointAccum) {
        precompute();
        int[] iArr = new int[8];
        decodeScalar(bArr, 0, iArr);
        Nat.cadd(8, (~iArr[0]) & 1, iArr, f27036L, iArr);
        Nat.shiftDownBit(8, iArr, 1);
        for (int i = 0; i < 8; i++) {
            iArr[i] = Interleave.shuffle2(iArr[i]);
        }
        PointPrecomp pointPrecomp = new PointPrecomp();
        PointTemp pointTemp = new PointTemp();
        pointSetNeutral(pointAccum);
        int i2 = 28;
        int i3 = 0;
        while (true) {
            int i4 = i3;
            int i5 = 0;
            while (i5 < 8) {
                int i6 = iArr[i5] >>> i2;
                int i7 = (i6 >>> 3) & 1;
                pointLookup(i5, (i6 ^ (-i7)) & 7, pointPrecomp);
                int i8 = i4 ^ i7;
                C13363F.cnegate(i8, pointAccum.f27045x);
                C13363F.cnegate(i8, pointAccum.f27043u);
                pointAdd(pointPrecomp, pointAccum, pointTemp);
                i5++;
                i4 = i7;
            }
            i2 -= 4;
            if (i2 < 0) {
                C13363F.cnegate(i4, pointAccum.f27045x);
                C13363F.cnegate(i4, pointAccum.f27043u);
                return;
            }
            pointDouble(pointAccum);
            i3 = i4;
        }
    }

    private static void scalarMultBaseEncoded(byte[] bArr, byte[] bArr2, int i) {
        PointAccum pointAccum = new PointAccum();
        scalarMultBase(bArr, pointAccum);
        if (encodePoint(pointAccum, bArr2, i) == 0) {
            throw new IllegalStateException();
        }
    }

    public static void scalarMultBaseYZ(X25519.Friend friend, byte[] bArr, int i, int[] iArr, int[] iArr2) {
        if (friend == null) {
            throw new NullPointerException("This method is only for use by X25519");
        }
        byte[] bArr2 = new byte[32];
        pruneScalar(bArr, i, bArr2);
        PointAccum pointAccum = new PointAccum();
        scalarMultBase(bArr2, pointAccum);
        if (checkPoint(pointAccum.f27045x, pointAccum.f27046y, pointAccum.f27047z) == 0) {
            throw new IllegalStateException();
        }
        C13363F.copy(pointAccum.f27046y, 0, iArr, 0);
        C13363F.copy(pointAccum.f27047z, 0, iArr2, 0);
    }

    private static void scalarMultOrderVar(PointAffine pointAffine, PointAccum pointAccum) {
        byte[] wnafVar = getWnafVar(f27036L, 5);
        PointPrecompZ[] pointPrecompZArr = new PointPrecompZ[8];
        PointTemp pointTemp = new PointTemp();
        pointPrecomputeZ(pointAffine, pointPrecompZArr, 8, pointTemp);
        pointSetNeutral(pointAccum);
        int i = 252;
        while (true) {
            byte b = wnafVar[i];
            if (b != 0) {
                int i2 = b >> 31;
                pointAddVar(i2 != 0, pointPrecompZArr[(b ^ i2) >>> 1], pointAccum, pointTemp);
            }
            i--;
            if (i < 0) {
                return;
            }
            pointDouble(pointAccum);
        }
    }

    private static void scalarMultStrausVar(int[] iArr, int[] iArr2, PointAffine pointAffine, PointAccum pointAccum) {
        precompute();
        byte[] wnafVar = getWnafVar(iArr, 7);
        byte[] wnafVar2 = getWnafVar(iArr2, 5);
        PointPrecompZ[] pointPrecompZArr = new PointPrecompZ[8];
        PointTemp pointTemp = new PointTemp();
        pointPrecomputeZ(pointAffine, pointPrecompZArr, 8, pointTemp);
        pointSetNeutral(pointAccum);
        int i = 252;
        while (true) {
            byte b = wnafVar[i];
            if (b != 0) {
                int i2 = b >> 31;
                pointAddVar(i2 != 0, PRECOMP_BASE_WNAF[(b ^ i2) >>> 1], pointAccum, pointTemp);
            }
            byte b2 = wnafVar2[i];
            if (b2 != 0) {
                int i3 = b2 >> 31;
                pointAddVar(i3 != 0, pointPrecompZArr[(b2 ^ i3) >>> 1], pointAccum, pointTemp);
            }
            i--;
            if (i < 0) {
                return;
            }
            pointDouble(pointAccum);
        }
    }

    public static void sign(byte[] bArr, int i, byte[] bArr2, int i2, int i3, byte[] bArr3, int i4) {
        implSign(bArr, i, null, (byte) 0, bArr2, i2, i3, bArr3, i4);
    }

    public static void sign(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, int i3, int i4, byte[] bArr4, int i5) {
        implSign(bArr, i, bArr2, i2, null, (byte) 0, bArr3, i3, i4, bArr4, i5);
    }

    public static void sign(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, byte[] bArr4, int i3, int i4, byte[] bArr5, int i5) {
        implSign(bArr, i, bArr2, i2, bArr3, (byte) 0, bArr4, i3, i4, bArr5, i5);
    }

    public static void sign(byte[] bArr, int i, byte[] bArr2, byte[] bArr3, int i2, int i3, byte[] bArr4, int i4) {
        implSign(bArr, i, bArr2, (byte) 0, bArr3, i2, i3, bArr4, i4);
    }

    public static void signPrehash(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, Digest digest, byte[] bArr4, int i3) {
        byte[] bArr5 = new byte[64];
        if (64 != digest.doFinal(bArr5, 0)) {
            throw new IllegalArgumentException("ph");
        }
        implSign(bArr, i, bArr2, i2, bArr3, (byte) 1, bArr5, 0, bArr5.length, bArr4, i3);
    }

    public static void signPrehash(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, byte[] bArr4, int i3, byte[] bArr5, int i4) {
        implSign(bArr, i, bArr2, i2, bArr3, (byte) 1, bArr4, i3, 64, bArr5, i4);
    }

    public static void signPrehash(byte[] bArr, int i, byte[] bArr2, Digest digest, byte[] bArr3, int i2) {
        byte[] bArr4 = new byte[64];
        if (64 != digest.doFinal(bArr4, 0)) {
            throw new IllegalArgumentException("ph");
        }
        implSign(bArr, i, bArr2, (byte) 1, bArr4, 0, bArr4.length, bArr3, i2);
    }

    public static void signPrehash(byte[] bArr, int i, byte[] bArr2, byte[] bArr3, int i2, byte[] bArr4, int i3) {
        implSign(bArr, i, bArr2, (byte) 1, bArr3, i2, 64, bArr4, i3);
    }

    public static boolean validatePublicKeyFull(byte[] bArr, int i) {
        PointAffine pointAffine = new PointAffine();
        if (decodePointVar(bArr, i, false, pointAffine)) {
            C13363F.normalize(pointAffine.f27048x);
            C13363F.normalize(pointAffine.f27049y);
            if (isNeutralElementVar(pointAffine.f27048x, pointAffine.f27049y)) {
                return false;
            }
            PointAccum pointAccum = new PointAccum();
            scalarMultOrderVar(pointAffine, pointAccum);
            C13363F.normalize(pointAccum.f27045x);
            C13363F.normalize(pointAccum.f27046y);
            C13363F.normalize(pointAccum.f27047z);
            return isNeutralElementVar(pointAccum.f27045x, pointAccum.f27046y, pointAccum.f27047z);
        }
        return false;
    }

    public static boolean validatePublicKeyPartial(byte[] bArr, int i) {
        return decodePointVar(bArr, i, false, new PointAffine());
    }

    public static boolean verify(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, int i3, int i4) {
        return implVerify(bArr, i, bArr2, i2, null, (byte) 0, bArr3, i3, i4);
    }

    public static boolean verify(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, byte[] bArr4, int i3, int i4) {
        return implVerify(bArr, i, bArr2, i2, bArr3, (byte) 0, bArr4, i3, i4);
    }

    public static boolean verifyPrehash(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, Digest digest) {
        byte[] bArr4 = new byte[64];
        if (64 == digest.doFinal(bArr4, 0)) {
            return implVerify(bArr, i, bArr2, i2, bArr3, (byte) 1, bArr4, 0, bArr4.length);
        }
        throw new IllegalArgumentException("ph");
    }

    public static boolean verifyPrehash(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, byte[] bArr4, int i3) {
        return implVerify(bArr, i, bArr2, i2, bArr3, (byte) 1, bArr4, i3, 64);
    }
}
