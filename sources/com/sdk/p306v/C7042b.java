package com.sdk.p306v;

import org.bouncycastle.crypto.digests.SM3Digest;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.sdk.v.b */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C7042b {

    /* renamed from: a */
    public static C7043c f18234a = new C7043c();

    /* renamed from: b */
    public static C7044d f18235b = new C7044d();

    /* renamed from: c */
    public static C7045e f18236c = new C7045e();

    /* renamed from: a */
    public static byte[] m8121a(byte[] bArr) {
        f18235b.getClass();
        if (bArr != null) {
            if (bArr.length > 0) {
                byte[] bArr2 = new byte[32];
                SM3Digest sM3Digest = new SM3Digest();
                sM3Digest.update(bArr, 0, bArr.length);
                sM3Digest.doFinal(bArr2, 0);
                sM3Digest.reset();
                return bArr2;
            }
            throw new C7046f(EnumC7041a.E10415);
        }
        throw new C7046f(EnumC7041a.E10400);
    }

    /* renamed from: b */
    public static byte[] m8119b(byte[] bArr, byte[] bArr2, byte[] bArr3, int i) {
        switch (i) {
            case 1:
                return f18236c.m8117a(bArr2, bArr, 1);
            case 2:
                return f18236c.m8116a(bArr2, bArr3, bArr, 1);
            case 3:
                return f18236c.m8116a(bArr2, bArr3, bArr, 1);
            case 4:
                return f18236c.m8116a(bArr2, bArr3, bArr, 1);
            case 5:
                return f18236c.m8116a(bArr2, bArr3, bArr, 1);
            default:
                return f18236c.m8116a(bArr2, bArr3, bArr, 1);
        }
    }

    /* renamed from: a */
    public static byte[] m8120a(byte[] bArr, byte[] bArr2, byte[] bArr3, int i) {
        switch (i) {
            case 1:
                return f18236c.m8117a(bArr2, bArr, 2);
            case 2:
                return f18236c.m8116a(bArr2, bArr3, bArr, 2);
            case 3:
                return f18236c.m8116a(bArr2, bArr3, bArr, 2);
            case 4:
                return f18236c.m8116a(bArr2, bArr3, bArr, 2);
            case 5:
                return f18236c.m8116a(bArr2, bArr3, bArr, 2);
            default:
                return f18236c.m8116a(bArr2, bArr3, bArr, 2);
        }
    }
}
