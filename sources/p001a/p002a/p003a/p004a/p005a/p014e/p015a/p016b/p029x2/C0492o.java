package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p029x2;

import java.math.BigInteger;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p047d.p048a.AbstractC0648c;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p047d.p048a.AbstractC0651d;

/* renamed from: a.a.a.a.a.e.a.b.x2.o */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0492o {
    /* renamed from: a */
    public int m23012a(AbstractC0648c abstractC0648c) {
        return (abstractC0648c.mo22604c() + 7) / 8;
    }

    /* renamed from: a */
    public int m23011a(AbstractC0651d abstractC0651d) {
        return (abstractC0651d.mo22592b() + 7) / 8;
    }

    /* renamed from: a */
    public byte[] m23010a(BigInteger bigInteger, int i) {
        byte[] byteArray = bigInteger.toByteArray();
        if (i < byteArray.length) {
            byte[] bArr = new byte[i];
            System.arraycopy(byteArray, byteArray.length - i, bArr, 0, i);
            return bArr;
        } else if (i > byteArray.length) {
            byte[] bArr2 = new byte[i];
            System.arraycopy(byteArray, 0, bArr2, i - byteArray.length, byteArray.length);
            return bArr2;
        } else {
            return byteArray;
        }
    }
}
