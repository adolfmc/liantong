package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p047d.p048a;

import java.math.BigInteger;

/* renamed from: a.a.a.a.a.e.a.d.a.g */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0658g implements InterfaceC0654e {
    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p047d.p048a.InterfaceC0654e
    /* renamed from: a */
    public AbstractC0655f mo22506a(AbstractC0655f abstractC0655f, BigInteger bigInteger, InterfaceC0660i interfaceC0660i) {
        BigInteger multiply = bigInteger.multiply(BigInteger.valueOf(3L));
        AbstractC0655f mo22566h = abstractC0655f.mo22566h();
        AbstractC0655f abstractC0655f2 = abstractC0655f;
        for (int bitLength = multiply.bitLength() - 2; bitLength > 0; bitLength--) {
            abstractC0655f2 = abstractC0655f2.mo22565i();
            boolean testBit = multiply.testBit(bitLength);
            if (testBit != bigInteger.testBit(bitLength)) {
                abstractC0655f2 = abstractC0655f2.mo22569a(testBit ? abstractC0655f : mo22566h);
            }
        }
        return abstractC0655f2;
    }
}
