package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p047d.p048a;

import java.math.BigInteger;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p047d.p048a.AbstractC0648c;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p047d.p048a.AbstractC0655f;

/* renamed from: a.a.a.a.a.e.a.d.a.o */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0666o implements InterfaceC0654e {
    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p047d.p048a.InterfaceC0654e
    /* renamed from: a */
    public AbstractC0655f mo22506a(AbstractC0655f abstractC0655f, BigInteger bigInteger, InterfaceC0660i interfaceC0660i) {
        if (abstractC0655f instanceof AbstractC0655f.C0656a) {
            AbstractC0655f.C0656a c0656a = (AbstractC0655f.C0656a) abstractC0655f;
            AbstractC0648c.C0649a c0649a = (AbstractC0648c.C0649a) c0656a.m22580b();
            int m22613i = c0649a.m22613i();
            byte byteValue = c0649a.m22622a().mo22584g().byteValue();
            byte m22612j = c0649a.m22612j();
            return m22508a(c0656a, C0663l.m22517a(bigInteger, m22613i, byteValue, c0649a.m22610l(), m22612j, (byte) 10), interfaceC0660i, byteValue, m22612j);
        }
        throw new IllegalArgumentException("Only ECPoint.F2m can be used in WTauNafMultiplier");
    }

    /* renamed from: a */
    private AbstractC0655f.C0656a m22508a(AbstractC0655f.C0656a c0656a, C0668q c0668q, InterfaceC0660i interfaceC0660i, byte b, byte b2) {
        C0668q[] c0668qArr;
        if (b == 0) {
            c0668qArr = C0663l.f2022f;
        } else {
            c0668qArr = C0663l.f2024h;
        }
        return m22507a(c0656a, C0663l.m22525a(b2, c0668q, (byte) 4, BigInteger.valueOf(16L), C0663l.m22529a(b2, 4), c0668qArr), interfaceC0660i);
    }

    /* renamed from: a */
    public static AbstractC0655f.C0656a m22507a(AbstractC0655f.C0656a c0656a, byte[] bArr, InterfaceC0660i interfaceC0660i) {
        AbstractC0655f.C0656a[] m22522a;
        byte byteValue = ((AbstractC0648c.C0649a) c0656a.m22580b()).m22622a().mo22584g().byteValue();
        if (interfaceC0660i != null && (interfaceC0660i instanceof C0667p)) {
            m22522a = ((C0667p) interfaceC0660i).m22505a();
        } else {
            m22522a = C0663l.m22522a(c0656a, byteValue);
            c0656a.m22582a(new C0667p(m22522a));
        }
        AbstractC0655f.C0656a c0656a2 = (AbstractC0655f.C0656a) c0656a.m22580b().mo22603d();
        for (int length = bArr.length - 1; length >= 0; length--) {
            c0656a2 = C0663l.m22523a(c0656a2);
            if (bArr[length] != 0) {
                if (bArr[length] > 0) {
                    c0656a2 = c0656a2.m22573a(m22522a[bArr[length]]);
                } else {
                    c0656a2 = c0656a2.m22571b(m22522a[-bArr[length]]);
                }
            }
        }
        return c0656a2;
    }
}
