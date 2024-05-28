package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: a.a.a.a.a.e.a.c.m0.c */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C0561c extends C0559b {

    /* renamed from: e */
    public static final int f1812e = 24;

    public C0561c(byte[] bArr) {
        super(bArr);
        if (m22832a(bArr, 0, bArr.length)) {
            throw new IllegalArgumentException("attempt to create weak DESede key");
        }
    }

    /* renamed from: a */
    public static boolean m22832a(byte[] bArr, int i, int i2) {
        while (i < i2) {
            if (C0559b.m22837a(bArr, i)) {
                return true;
            }
            i += 8;
        }
        return false;
    }

    /* renamed from: a */
    public static boolean m22833a(byte[] bArr, int i) {
        return m22832a(bArr, i, bArr.length - i);
    }
}
