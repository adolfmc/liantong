package org.p415a.p445f.p446a;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.f.a.g */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12923g {
    /* renamed from: a */
    public static byte[] m478a(byte[] bArr) {
        byte[] bArr2 = new byte[40];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        byte length = (byte) (bArr2.length - bArr.length);
        for (int length2 = bArr.length; length2 != bArr2.length; length2++) {
            bArr2[length2] = length;
        }
        return bArr2;
    }
}
