package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p049e.p050k;

import com.p201hb.omapi.union.sim.org.bouncycastle.util.encoders.DecoderException;
import com.p201hb.omapi.union.sim.org.bouncycastle.util.encoders.EncoderException;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p049e.C0678j;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: a.a.a.a.a.e.a.e.k.f */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C0684f {

    /* renamed from: a */
    public static final InterfaceC0683e f2044a = new C0685g();

    /* renamed from: a */
    public static byte[] m22416a(byte[] bArr, int i, int i2) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            f2044a.mo22407a(bArr, i, i2, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            throw new EncoderException("exception encoding Hex string: " + e.getMessage(), e);
        }
    }

    /* renamed from: b */
    public static String m22412b(byte[] bArr, int i, int i2) {
        return C0678j.m22442b(m22416a(bArr, i, i2));
    }

    /* renamed from: c */
    public static String m22411c(byte[] bArr) {
        return m22412b(bArr, 0, bArr.length);
    }

    /* renamed from: b */
    public static byte[] m22413b(byte[] bArr) {
        return m22416a(bArr, 0, bArr.length);
    }

    /* renamed from: a */
    public static int m22414a(byte[] bArr, OutputStream outputStream) {
        return f2044a.mo22407a(bArr, 0, bArr.length, outputStream);
    }

    /* renamed from: a */
    public static int m22415a(byte[] bArr, int i, int i2, OutputStream outputStream) {
        return f2044a.mo22407a(bArr, i, i2, outputStream);
    }

    /* renamed from: a */
    public static byte[] m22417a(byte[] bArr) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            f2044a.mo22406b(bArr, 0, bArr.length, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            throw new DecoderException("exception decoding Hex data: " + e.getMessage(), e);
        }
    }

    /* renamed from: a */
    public static byte[] m22419a(String str) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            f2044a.mo22408a(str, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            throw new DecoderException("exception decoding Hex string: " + e.getMessage(), e);
        }
    }

    /* renamed from: a */
    public static int m22418a(String str, OutputStream outputStream) {
        return f2044a.mo22408a(str, outputStream);
    }
}
