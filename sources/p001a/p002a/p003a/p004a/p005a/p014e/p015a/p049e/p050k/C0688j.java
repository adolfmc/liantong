package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p049e.p050k;

import com.p201hb.omapi.union.sim.org.bouncycastle.util.encoders.DecoderException;
import com.p201hb.omapi.union.sim.org.bouncycastle.util.encoders.EncoderException;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: a.a.a.a.a.e.a.e.k.j */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C0688j {

    /* renamed from: a */
    public static final InterfaceC0683e f2048a = new C0689k();

    /* renamed from: a */
    public static byte[] m22399a(byte[] bArr) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            f2048a.mo22406b(bArr, 0, bArr.length, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            throw new DecoderException("exception decoding URL safe base64 string: " + e.getMessage(), e);
        }
    }

    /* renamed from: b */
    public static byte[] m22397b(byte[] bArr) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            f2048a.mo22407a(bArr, 0, bArr.length, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            throw new EncoderException("exception encoding URL safe base64 data: " + e.getMessage(), e);
        }
    }

    /* renamed from: a */
    public static int m22398a(byte[] bArr, OutputStream outputStream) {
        return f2048a.mo22406b(bArr, 0, bArr.length, outputStream);
    }

    /* renamed from: b */
    public static int m22396b(byte[] bArr, OutputStream outputStream) {
        return f2048a.mo22407a(bArr, 0, bArr.length, outputStream);
    }

    /* renamed from: a */
    public static byte[] m22401a(String str) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            f2048a.mo22408a(str, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            throw new DecoderException("exception decoding URL safe base64 string: " + e.getMessage(), e);
        }
    }

    /* renamed from: a */
    public static int m22400a(String str, OutputStream outputStream) {
        return f2048a.mo22408a(str, outputStream);
    }
}
