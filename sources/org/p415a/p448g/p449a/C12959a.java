package org.p415a.p448g.p449a;

import java.io.ByteArrayOutputStream;
import org.p415a.p448g.C12975h;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.g.a.a */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12959a {

    /* renamed from: a */
    private static final InterfaceC12962d f26238a = new C12960b();

    /* renamed from: a */
    public static String m428a(byte[] bArr) {
        return m427a(bArr, 0, bArr.length);
    }

    /* renamed from: a */
    public static String m427a(byte[] bArr, int i, int i2) {
        return C12975h.m386b(m425b(bArr, i, i2));
    }

    /* renamed from: b */
    public static byte[] m426b(byte[] bArr) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream((bArr.length / 4) * 3);
        try {
            f26238a.mo410b(bArr, 0, bArr.length, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            throw new C12961c("unable to decode base64 data: " + e.getMessage(), e);
        }
    }

    /* renamed from: b */
    public static byte[] m425b(byte[] bArr, int i, int i2) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(((i2 + 2) / 3) * 4);
        try {
            f26238a.mo411a(bArr, i, i2, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            throw new C12963e("exception encoding base64 string: " + e.getMessage(), e);
        }
    }
}
