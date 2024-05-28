package org.p415a.p418b;

import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import org.p415a.p448g.C12957a;
import org.p415a.p448g.p450b.C12967a;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.b.c */
/* loaded from: E:\11617560_dexfile_execute.dex */
public abstract class AbstractC12467c extends AbstractC12570t {

    /* renamed from: c */
    private static final char[] f25245c = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    /* renamed from: a */
    protected final byte[] f25246a;

    /* renamed from: b */
    protected final int f25247b;

    public AbstractC12467c(byte[] bArr, int i) {
        if (bArr == null) {
            throw new NullPointerException("data cannot be null");
        }
        if (bArr.length == 0 && i != 0) {
            throw new IllegalArgumentException("zero length data with non-zero pad bits");
        }
        if (i > 7 || i < 0) {
            throw new IllegalArgumentException("pad bits cannot be greater than 7 or less than 0");
        }
        this.f25246a = C12957a.m430b(bArr);
        this.f25247b = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static AbstractC12467c m1691a(int i, InputStream inputStream) {
        if (i >= 1) {
            int read = inputStream.read();
            byte[] bArr = new byte[i - 1];
            if (bArr.length != 0) {
                if (C12967a.m403a(inputStream, bArr) != bArr.length) {
                    throw new EOFException("EOF encountered in middle of BIT STRING");
                }
                if (read > 0 && read < 8 && bArr[bArr.length - 1] != ((byte) (bArr[bArr.length - 1] & (255 << read)))) {
                    return new C12454bn(bArr, read);
                }
            }
            return new C12426aq(bArr, read);
        }
        throw new IllegalArgumentException("truncated BIT STRING detected");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public static byte[] m1690a(byte[] bArr, int i) {
        byte[] m430b = C12957a.m430b(bArr);
        if (i > 0) {
            int length = bArr.length - 1;
            m430b[length] = (byte) ((255 << i) & m430b[length]);
        }
        return m430b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.p415a.p418b.AbstractC12570t
    /* renamed from: a */
    public abstract void mo1597a(C12567r c12567r);

    @Override // org.p415a.p418b.AbstractC12570t
    /* renamed from: a */
    protected boolean mo1596a(AbstractC12570t abstractC12570t) {
        if (abstractC12570t instanceof AbstractC12467c) {
            AbstractC12467c abstractC12467c = (AbstractC12467c) abstractC12570t;
            return this.f25247b == abstractC12467c.f25247b && C12957a.m438a(m1688d(), abstractC12467c.m1688d());
        }
        return false;
    }

    /* renamed from: b */
    public String m1689b() {
        StringBuffer stringBuffer = new StringBuffer("#");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            new C12567r(byteArrayOutputStream).mo1625a((InterfaceC12554f) this);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            for (int i = 0; i != byteArray.length; i++) {
                stringBuffer.append(f25245c[(byteArray[i] >>> 4) & 15]);
                stringBuffer.append(f25245c[byteArray[i] & 15]);
            }
            return stringBuffer.toString();
        } catch (IOException e) {
            throw new C12569s("Internal error encoding BitString: " + e.getMessage(), e);
        }
    }

    /* renamed from: d */
    public byte[] m1688d() {
        return m1690a(this.f25246a, this.f25247b);
    }

    /* renamed from: e */
    public int m1687e() {
        return this.f25247b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.p415a.p418b.AbstractC12570t
    /* renamed from: f */
    public AbstractC12570t mo1592f() {
        return new C12426aq(this.f25246a, this.f25247b);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.p415a.p418b.AbstractC12570t
    /* renamed from: g */
    public AbstractC12570t mo1591g() {
        return new C12454bn(this.f25246a, this.f25247b);
    }

    @Override // org.p415a.p418b.AbstractC12570t, org.p415a.p418b.AbstractC12562n
    public int hashCode() {
        return this.f25247b ^ C12957a.m441a(m1688d());
    }

    public String toString() {
        return m1689b();
    }
}
