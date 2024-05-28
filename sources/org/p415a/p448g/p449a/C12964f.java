package org.p415a.p448g.p449a;

import java.io.ByteArrayOutputStream;
import org.p415a.p448g.C12975h;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.g.a.f */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12964f {

    /* renamed from: a */
    private static final InterfaceC12962d f26244a = new C12965g();

    /* renamed from: a */
    public static String m418a(byte[] bArr) {
        return m417a(bArr, 0, bArr.length);
    }

    /* renamed from: a */
    public static String m417a(byte[] bArr, int i, int i2) {
        return C12975h.m386b(m415b(bArr, i, i2));
    }

    /* renamed from: a */
    public static byte[] m419a(String str) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            f26244a.mo412a(str, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            throw new C12961c("exception decoding Hex string: " + e.getMessage(), e);
        }
    }

    /* renamed from: b */
    public static byte[] m416b(byte[] bArr) {
        return m415b(bArr, 0, bArr.length);
    }

    /* renamed from: b */
    public static byte[] m415b(byte[] bArr, int i, int i2) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            f26244a.mo411a(bArr, i, i2, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            throw new C12963e("exception encoding Hex string: " + e.getMessage(), e);
        }
    }
}
