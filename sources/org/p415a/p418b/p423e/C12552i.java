package org.p415a.p418b.p423e;

import java.math.BigInteger;
import org.p415a.p436e.p437a.AbstractC12856e;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.b.e.i */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12552i {
    /* renamed from: a */
    public int m1662a(AbstractC12856e abstractC12856e) {
        return (abstractC12856e.mo886b() + 7) / 8;
    }

    /* renamed from: a */
    public byte[] m1663a(BigInteger bigInteger, int i) {
        byte[] byteArray = bigInteger.toByteArray();
        if (i < byteArray.length) {
            byte[] bArr = new byte[i];
            System.arraycopy(byteArray, byteArray.length - bArr.length, bArr, 0, bArr.length);
            return bArr;
        } else if (i > byteArray.length) {
            byte[] bArr2 = new byte[i];
            System.arraycopy(byteArray, 0, bArr2, bArr2.length - byteArray.length, byteArray.length);
            return bArr2;
        } else {
            return byteArray;
        }
    }
}
