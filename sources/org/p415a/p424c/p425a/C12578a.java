package org.p415a.p424c.p425a;

import org.p415a.p424c.C12589ak;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.c.a.a */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12578a extends C12589ak {

    /* renamed from: c */
    private static final byte[] f25540c = new byte[12];

    /* renamed from: d */
    private int f25541d;

    /* renamed from: e */
    private int f25542e;

    /* renamed from: f */
    private int f25543f;

    /* renamed from: g */
    private byte[] f25544g;

    public C12578a(boolean z, byte[] bArr) {
        super(1, z, bArr);
        this.f25541d = ((bArr[1] & 255) << 8) | (bArr[0] & 255);
        this.f25542e = bArr[2] & 255;
        this.f25543f = bArr[3] & 255;
        int length = bArr.length;
        int i = this.f25541d;
        this.f25544g = new byte[length - i];
        byte[] bArr2 = this.f25544g;
        System.arraycopy(bArr, i, bArr2, 0, bArr2.length);
    }
}
