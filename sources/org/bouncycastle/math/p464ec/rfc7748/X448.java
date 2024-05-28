package org.bouncycastle.math.p464ec.rfc7748;

import java.security.SecureRandom;
import org.bouncycastle.math.p464ec.rfc8032.Ed448;
import org.bouncycastle.util.Arrays;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.bouncycastle.math.ec.rfc7748.X448 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public abstract class X448 {
    private static final int C_A = 156326;
    private static final int C_A24 = 39082;
    public static final int POINT_SIZE = 56;
    public static final int SCALAR_SIZE = 56;

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: org.bouncycastle.math.ec.rfc7748.X448$F */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    static class C13361F extends X448Field {
        private C13361F() {
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: org.bouncycastle.math.ec.rfc7748.X448$Friend */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class Friend {
        private static final Friend INSTANCE = new Friend();

        private Friend() {
        }
    }

    public static boolean calculateAgreement(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, int i3) {
        scalarMult(bArr, i, bArr2, i2, bArr3, i3);
        return !Arrays.areAllZeroes(bArr3, i3, 56);
    }

    private static int decode32(byte[] bArr, int i) {
        int i2 = i + 1;
        int i3 = i2 + 1;
        return (bArr[i3 + 1] << 24) | (bArr[i] & 255) | ((bArr[i2] & 255) << 8) | ((bArr[i3] & 255) << 16);
    }

    private static void decodeScalar(byte[] bArr, int i, int[] iArr) {
        for (int i2 = 0; i2 < 14; i2++) {
            iArr[i2] = decode32(bArr, (i2 * 4) + i);
        }
        iArr[0] = iArr[0] & (-4);
        iArr[13] = iArr[13] | Integer.MIN_VALUE;
    }

    public static void generatePrivateKey(SecureRandom secureRandom, byte[] bArr) {
        secureRandom.nextBytes(bArr);
        bArr[0] = (byte) (bArr[0] & 252);
        bArr[55] = (byte) (bArr[55] | 128);
    }

    public static void generatePublicKey(byte[] bArr, int i, byte[] bArr2, int i2) {
        scalarMultBase(bArr, i, bArr2, i2);
    }

    private static void pointDouble(int[] iArr, int[] iArr2) {
        int[] create = C13361F.create();
        int[] create2 = C13361F.create();
        C13361F.add(iArr, iArr2, create);
        C13361F.sub(iArr, iArr2, create2);
        C13361F.sqr(create, create);
        C13361F.sqr(create2, create2);
        C13361F.mul(create, create2, iArr);
        C13361F.sub(create, create2, create);
        C13361F.mul(create, 39082, iArr2);
        C13361F.add(iArr2, create2, iArr2);
        C13361F.mul(iArr2, create, iArr2);
    }

    public static void precompute() {
        Ed448.precompute();
    }

    public static void scalarMult(byte[] bArr, int i, byte[] bArr2, int i2, byte[] bArr3, int i3) {
        int[] iArr = new int[14];
        decodeScalar(bArr, i, iArr);
        int[] create = C13361F.create();
        C13361F.decode(bArr2, i2, create);
        int[] create2 = C13361F.create();
        C13361F.copy(create, 0, create2, 0);
        int[] create3 = C13361F.create();
        create3[0] = 1;
        int[] create4 = C13361F.create();
        create4[0] = 1;
        int[] create5 = C13361F.create();
        int[] create6 = C13361F.create();
        int[] create7 = C13361F.create();
        int i4 = 447;
        int i5 = 1;
        while (true) {
            C13361F.add(create4, create5, create6);
            C13361F.sub(create4, create5, create4);
            C13361F.add(create2, create3, create5);
            C13361F.sub(create2, create3, create2);
            C13361F.mul(create6, create2, create6);
            C13361F.mul(create4, create5, create4);
            C13361F.sqr(create5, create5);
            C13361F.sqr(create2, create2);
            C13361F.sub(create5, create2, create7);
            C13361F.mul(create7, 39082, create3);
            C13361F.add(create3, create2, create3);
            C13361F.mul(create3, create7, create3);
            C13361F.mul(create2, create5, create2);
            C13361F.sub(create6, create4, create5);
            C13361F.add(create6, create4, create4);
            C13361F.sqr(create4, create4);
            C13361F.sqr(create5, create5);
            C13361F.mul(create5, create, create5);
            i4--;
            int i6 = (iArr[i4 >>> 5] >>> (i4 & 31)) & 1;
            int i7 = i5 ^ i6;
            C13361F.cswap(i7, create2, create4);
            C13361F.cswap(i7, create3, create5);
            if (i4 < 2) {
                break;
            }
            i5 = i6;
        }
        for (int i8 = 0; i8 < 2; i8++) {
            pointDouble(create2, create3);
        }
        C13361F.inv(create3, create3);
        C13361F.mul(create2, create3, create2);
        C13361F.normalize(create2);
        C13361F.encode(create2, bArr3, i3);
    }

    public static void scalarMultBase(byte[] bArr, int i, byte[] bArr2, int i2) {
        int[] create = C13361F.create();
        int[] create2 = C13361F.create();
        Ed448.scalarMultBaseXY(Friend.INSTANCE, bArr, i, create, create2);
        C13361F.inv(create, create);
        C13361F.mul(create, create2, create);
        C13361F.sqr(create, create);
        C13361F.normalize(create);
        C13361F.encode(create, bArr2, i2);
    }
}
