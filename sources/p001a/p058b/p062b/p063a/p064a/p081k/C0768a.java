package p001a.p058b.p062b.p063a.p064a.p081k;

import com.baidu.uaq.agent.android.logging.InterfaceC3321a;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.util.zip.Deflater;
import java.util.zip.GZIPOutputStream;
import p001a.p058b.p062b.p063a.p064a.p072d.C0749a;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: a.b.b.a.a.k.a */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C0768a {

    /* renamed from: a */
    public static final InterfaceC3321a f2375a = C0749a.f2299a;

    /* renamed from: a */
    public static void m22245a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
                f2375a.error("Failed to close io.");
            }
        }
    }

    /* renamed from: a */
    public static byte[] m22244a(String str) {
        Throwable th;
        ByteArrayOutputStream byteArrayOutputStream;
        Deflater deflater;
        try {
            deflater = new Deflater();
            deflater.setInput(str.getBytes());
            deflater.finish();
            byteArrayOutputStream = new ByteArrayOutputStream();
        } catch (Throwable th2) {
            th = th2;
            byteArrayOutputStream = null;
        }
        try {
            byte[] bArr = new byte[8192];
            while (!deflater.finished()) {
                int deflate = deflater.deflate(bArr);
                if (deflate <= 0) {
                    f2375a.error("HTTP request contains an incomplete payload");
                }
                byteArrayOutputStream.write(bArr, 0, deflate);
            }
            deflater.end();
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            m22245a(byteArrayOutputStream);
            return byteArray;
        } catch (Throwable th3) {
            th = th3;
            m22245a(byteArrayOutputStream);
            throw th;
        }
    }

    /* renamed from: a */
    public static byte[] m22243a(String str, String str2) {
        ByteArrayOutputStream byteArrayOutputStream;
        if (str == null || str.length() == 0) {
            return null;
        }
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
        } catch (IOException e) {
            e = e;
            byteArrayOutputStream = null;
        } catch (Throwable th) {
            th = th;
            byteArrayOutputStream = null;
            m22245a(byteArrayOutputStream);
            throw th;
        }
        try {
            try {
                GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
                gZIPOutputStream.write(str.getBytes(str2));
                gZIPOutputStream.close();
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                m22245a(byteArrayOutputStream);
                return byteArray;
            } catch (Throwable th2) {
                th = th2;
                m22245a(byteArrayOutputStream);
                throw th;
            }
        } catch (IOException e2) {
            e = e2;
            f2375a.mo17426a("gzip compress exception: ", e);
            m22245a(byteArrayOutputStream);
            return null;
        }
    }
}
