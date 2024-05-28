package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p049e.p050k;

import com.p201hb.omapi.union.sim.org.bouncycastle.util.encoders.DecoderException;
import com.p201hb.omapi.union.sim.org.bouncycastle.util.encoders.EncoderException;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p049e.C0678j;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: a.a.a.a.a.e.a.e.k.a */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C0679a {

    /* renamed from: a */
    public static final InterfaceC0683e f2034a = new C0680b();

    /* renamed from: a */
    public static byte[] m22434a(byte[] bArr, int i, int i2) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(((i2 + 2) / 3) * 4);
        try {
            f2034a.mo22407a(bArr, i, i2, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            throw new EncoderException("exception encoding base64 string: " + e.getMessage(), e);
        }
    }

    /* renamed from: b */
    public static String m22430b(byte[] bArr, int i, int i2) {
        return C0678j.m22442b(m22434a(bArr, i, i2));
    }

    /* renamed from: c */
    public static String m22429c(byte[] bArr) {
        return m22430b(bArr, 0, bArr.length);
    }

    /* renamed from: b */
    public static byte[] m22431b(byte[] bArr) {
        return m22434a(bArr, 0, bArr.length);
    }

    /* renamed from: a */
    public static int m22432a(byte[] bArr, OutputStream outputStream) {
        return f2034a.mo22407a(bArr, 0, bArr.length, outputStream);
    }

    /* renamed from: a */
    public static int m22433a(byte[] bArr, int i, int i2, OutputStream outputStream) {
        return f2034a.mo22407a(bArr, i, i2, outputStream);
    }

    /* renamed from: a */
    public static byte[] m22435a(byte[] bArr) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream((bArr.length / 4) * 3);
        try {
            f2034a.mo22406b(bArr, 0, bArr.length, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            throw new DecoderException("unable to decode base64 data: " + e.getMessage(), e);
        }
    }

    /* renamed from: a */
    public static byte[] m22437a(String str) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream((str.length() / 4) * 3);
        try {
            f2034a.mo22408a(str, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            throw new DecoderException("unable to decode base64 string: " + e.getMessage(), e);
        }
    }

    /* renamed from: a */
    public static int m22436a(String str, OutputStream outputStream) {
        return f2034a.mo22408a(str, outputStream);
    }
}
