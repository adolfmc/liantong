package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p033e0;

import java.math.BigInteger;
import java.security.SecureRandom;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p047d.p048a.InterfaceC0647b;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: a.a.a.a.a.e.a.c.e0.i */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C0524i {
    /* renamed from: a */
    public static BigInteger m22920a(BigInteger bigInteger, SecureRandom secureRandom) {
        int bitLength = bigInteger.bitLength();
        BigInteger bigInteger2 = new BigInteger(bitLength, secureRandom);
        while (true) {
            if (!bigInteger2.equals(InterfaceC0647b.f1976a) && bigInteger2.compareTo(bigInteger) < 0) {
                return bigInteger2;
            }
            bigInteger2 = new BigInteger(bitLength, secureRandom);
        }
    }
}
