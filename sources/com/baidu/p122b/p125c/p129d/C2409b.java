package com.baidu.p122b.p125c.p129d;

import java.math.BigInteger;
import java.security.SecureRandom;
import javax.crypto.BadPaddingException;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.b.c.d.b */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class C2409b {

    /* renamed from: a */
    public static final SecureRandom f4239a = new SecureRandom();

    /* renamed from: a */
    public static int m20239a(BigInteger bigInteger) {
        return (bigInteger.bitLength() + 7) >> 3;
    }

    /* renamed from: a */
    private static BigInteger m20235a(byte[] bArr, BigInteger bigInteger) {
        BigInteger bigInteger2 = new BigInteger(1, bArr);
        if (bigInteger2.compareTo(bigInteger) < 0) {
            return bigInteger2;
        }
        throw new BadPaddingException("Message is larger than modulus");
    }

    /* renamed from: a */
    private static byte[] m20238a(BigInteger bigInteger, int i) {
        byte[] byteArray = bigInteger.toByteArray();
        int length = byteArray.length;
        if (length == i) {
            return byteArray;
        }
        if (length == i + 1 && byteArray[0] == 0) {
            byte[] bArr = new byte[i];
            System.arraycopy(byteArray, 1, bArr, 0, i);
            return bArr;
        } else if (length >= i) {
            return null;
        } else {
            byte[] bArr2 = new byte[i];
            System.arraycopy(byteArray, 0, bArr2, i - length, length);
            return bArr2;
        }
    }

    /* renamed from: a */
    public static byte[] m20237a(byte[] bArr, int i, int i2) {
        if (i == 0 && i2 == bArr.length) {
            return bArr;
        }
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, i, bArr2, 0, i2);
        return bArr2;
    }

    /* renamed from: a */
    public static byte[] m20236a(byte[] bArr, InterfaceC2411d interfaceC2411d) {
        return m20234a(bArr, interfaceC2411d.mo20227a(), interfaceC2411d.mo20226b());
    }

    /* renamed from: a */
    private static byte[] m20234a(byte[] bArr, BigInteger bigInteger, BigInteger bigInteger2) {
        return m20238a(m20235a(bArr, bigInteger).modPow(bigInteger2, bigInteger), m20239a(bigInteger));
    }
}
