package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p049e;

import java.math.BigInteger;
import java.security.SecureRandom;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: a.a.a.a.a.e.a.e.b */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class C0670b {

    /* renamed from: a */
    public static final int f2031a = 1000;

    /* renamed from: b */
    public static final BigInteger f2032b = BigInteger.valueOf(0);

    /* renamed from: a */
    public static byte[] m22464a(BigInteger bigInteger) {
        byte[] byteArray = bigInteger.toByteArray();
        if (byteArray[0] == 0) {
            int length = byteArray.length - 1;
            byte[] bArr = new byte[length];
            System.arraycopy(byteArray, 1, bArr, 0, length);
            return bArr;
        }
        return byteArray;
    }

    /* renamed from: a */
    public static byte[] m22465a(int i, BigInteger bigInteger) {
        byte[] byteArray = bigInteger.toByteArray();
        if (byteArray[0] == 0) {
            if (byteArray.length - 1 <= i) {
                byte[] bArr = new byte[i];
                System.arraycopy(byteArray, 1, bArr, i - (byteArray.length - 1), byteArray.length - 1);
                return bArr;
            }
            throw new IllegalArgumentException("standard length exceeded for value");
        } else if (byteArray.length == i) {
            return byteArray;
        } else {
            if (byteArray.length <= i) {
                byte[] bArr2 = new byte[i];
                System.arraycopy(byteArray, 0, bArr2, i - byteArray.length, byteArray.length);
                return bArr2;
            }
            throw new IllegalArgumentException("standard length exceeded for value");
        }
    }

    /* renamed from: a */
    public static BigInteger m22463a(BigInteger bigInteger, BigInteger bigInteger2, SecureRandom secureRandom) {
        int compareTo = bigInteger.compareTo(bigInteger2);
        if (compareTo >= 0) {
            if (compareTo <= 0) {
                return bigInteger;
            }
            throw new IllegalArgumentException("'min' may not be greater than 'max'");
        } else if (bigInteger.bitLength() > bigInteger2.bitLength() / 2) {
            return m22463a(f2032b, bigInteger2.subtract(bigInteger), secureRandom).add(bigInteger);
        } else {
            for (int i = 0; i < 1000; i++) {
                BigInteger bigInteger3 = new BigInteger(bigInteger2.bitLength(), secureRandom);
                if (bigInteger3.compareTo(bigInteger) >= 0 && bigInteger3.compareTo(bigInteger2) <= 0) {
                    return bigInteger3;
                }
            }
            return new BigInteger(bigInteger2.subtract(bigInteger).bitLength() - 1, secureRandom).add(bigInteger);
        }
    }
}
