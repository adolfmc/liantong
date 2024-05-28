package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p047d.p048a;

import java.math.BigInteger;

/* renamed from: a.a.a.a.a.e.a.d.a.j */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0661j implements InterfaceC0654e {
    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p047d.p048a.InterfaceC0654e
    /* renamed from: a */
    public AbstractC0655f mo22506a(AbstractC0655f abstractC0655f, BigInteger bigInteger, InterfaceC0660i interfaceC0660i) {
        AbstractC0655f mo22603d = abstractC0655f.m22580b().mo22603d();
        int bitLength = bigInteger.bitLength();
        for (int i = 0; i < bitLength; i++) {
            if (bigInteger.testBit(i)) {
                mo22603d = mo22603d.mo22569a(abstractC0655f);
            }
            abstractC0655f = abstractC0655f.mo22565i();
        }
        return mo22603d;
    }
}
