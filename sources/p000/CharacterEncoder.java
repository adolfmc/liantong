package p000;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

/* renamed from: d0  reason: case insensitive filesystem */
/* loaded from: E:\567196_dexfile_execute.dex.fixout.dex */
public abstract class CharacterEncoder {

    /* renamed from: a */
    public PrintStream f24024a;

    /* renamed from: a */
    public abstract int mo2118a();

    /* renamed from: a */
    public void m2115a(OutputStream outputStream) {
        this.f24024a = new PrintStream(outputStream);
    }

    /* renamed from: a */
    public void m2114a(OutputStream outputStream, int i) {
    }

    /* renamed from: a */
    public abstract void mo2113a(OutputStream outputStream, byte[] bArr, int i, int i2);

    /* renamed from: b */
    public abstract int mo2111b();

    /* renamed from: b */
    public void m2110b(OutputStream outputStream) {
    }

    /* renamed from: c */
    public void m2109c(OutputStream outputStream) {
        this.f24024a.println();
    }

    /* renamed from: a */
    public int m2116a(InputStream inputStream, byte[] bArr) {
        for (int i = 0; i < bArr.length; i++) {
            int read = inputStream.read();
            if (read == -1) {
                return i;
            }
            bArr[i] = (byte) read;
        }
        return bArr.length;
    }

    /* renamed from: a */
    public void m2117a(InputStream inputStream, OutputStream outputStream) {
        int m2116a;
        byte[] bArr = new byte[mo2111b()];
        m2115a(outputStream);
        do {
            m2116a = m2116a(inputStream, bArr);
            if (m2116a == 0) {
                break;
            }
            m2114a(outputStream, m2116a);
            int i = 0;
            while (i < m2116a) {
                if (mo2118a() + i <= m2116a) {
                    mo2113a(outputStream, bArr, i, mo2118a());
                } else {
                    mo2113a(outputStream, bArr, i, m2116a - i);
                }
                i += mo2118a();
            }
            m2109c(outputStream);
        } while (m2116a >= mo2111b());
        m2110b(outputStream);
    }

    /* renamed from: a */
    public String m2112a(byte[] bArr) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            m2117a(new ByteArrayInputStream(bArr), byteArrayOutputStream);
            return byteArrayOutputStream.toString();
        } catch (Exception unused) {
            throw new Error("CharacterEncoder.encodeBuffer internal error");
        }
    }
}
