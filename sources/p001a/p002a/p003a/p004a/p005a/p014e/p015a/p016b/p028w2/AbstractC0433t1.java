package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.p028w2;

import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0162j;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0178n;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.C0180n1;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p049e.C0678j;

/* renamed from: a.a.a.a.a.e.a.b.w2.t1 */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public abstract class AbstractC0433t1 {
    /* renamed from: a */
    public abstract AbstractC0258r mo23148a(C0178n c0178n, String str);

    /* renamed from: a */
    public AbstractC0258r m23146a(String str, int i) {
        String m22443b = C0678j.m22443b(str);
        int length = (m22443b.length() - i) / 2;
        byte[] bArr = new byte[length];
        for (int i2 = 0; i2 != length; i2++) {
            int i3 = (i2 * 2) + i;
            char charAt = m22443b.charAt(i3);
            char charAt2 = m22443b.charAt(i3 + 1);
            if (charAt < 'a') {
                bArr[i2] = (byte) ((charAt - '0') << 4);
            } else {
                bArr[i2] = (byte) (((charAt - 'a') + 10) << 4);
            }
            if (charAt2 < 'a') {
                bArr[i2] = (byte) (((byte) (charAt2 - '0')) | bArr[i2]);
            } else {
                bArr[i2] = (byte) (((byte) ((charAt2 - 'a') + 10)) | bArr[i2]);
            }
        }
        return new C0162j(bArr).m24124d();
    }

    /* renamed from: a */
    public boolean m23147a(String str) {
        return C0180n1.m24092b(str);
    }
}
