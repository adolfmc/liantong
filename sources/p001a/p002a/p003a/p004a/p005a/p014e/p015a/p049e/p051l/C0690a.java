package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p049e.p051l;

import com.p201hb.omapi.union.sim.org.bouncycastle.util.p202io.StreamOverflowException;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: a.a.a.a.a.e.a.e.l.a */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class C0690a {

    /* renamed from: a */
    public static int f2049a = 512;

    /* renamed from: a */
    public static void m22395a(InputStream inputStream) {
        int i = f2049a;
        do {
        } while (inputStream.read(new byte[i], 0, i) >= 0);
    }

    /* renamed from: b */
    public static byte[] m22389b(InputStream inputStream) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        m22392a(inputStream, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    /* renamed from: a */
    public static byte[] m22394a(InputStream inputStream, int i) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        m22393a(inputStream, i, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    /* renamed from: a */
    public static int m22391a(InputStream inputStream, byte[] bArr) {
        return m22390a(inputStream, bArr, 0, bArr.length);
    }

    /* renamed from: a */
    public static int m22390a(InputStream inputStream, byte[] bArr, int i, int i2) {
        int i3 = 0;
        while (i3 < i2) {
            int read = inputStream.read(bArr, i + i3, i2 - i3);
            if (read < 0) {
                break;
            }
            i3 += read;
        }
        return i3;
    }

    /* renamed from: a */
    public static void m22392a(InputStream inputStream, OutputStream outputStream) {
        int i = f2049a;
        byte[] bArr = new byte[i];
        while (true) {
            int read = inputStream.read(bArr, 0, i);
            if (read < 0) {
                return;
            }
            outputStream.write(bArr, 0, read);
        }
    }

    /* renamed from: a */
    public static long m22393a(InputStream inputStream, long j, OutputStream outputStream) {
        int i = f2049a;
        byte[] bArr = new byte[i];
        long j2 = 0;
        while (true) {
            int read = inputStream.read(bArr, 0, i);
            if (read < 0) {
                return j2;
            }
            j2 += read;
            if (j2 <= j) {
                outputStream.write(bArr, 0, read);
            } else {
                throw new StreamOverflowException("Data Overflow");
            }
        }
    }
}
