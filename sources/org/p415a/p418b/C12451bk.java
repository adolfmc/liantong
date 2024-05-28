package org.p415a.p418b;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.p415a.p448g.C12957a;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.b.bk */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12451bk extends AbstractC12570t {

    /* renamed from: a */
    private static final char[] f25223a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    /* renamed from: b */
    private final byte[] f25224b;

    public C12451bk(byte[] bArr) {
        this.f25224b = C12957a.m430b(bArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.p415a.p418b.AbstractC12570t
    /* renamed from: a */
    public void mo1597a(C12567r c12567r) {
        c12567r.m1626a(28, m1707d());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.p415a.p418b.AbstractC12570t
    /* renamed from: a */
    public boolean mo1611a() {
        return false;
    }

    @Override // org.p415a.p418b.AbstractC12570t
    /* renamed from: a */
    boolean mo1596a(AbstractC12570t abstractC12570t) {
        if (abstractC12570t instanceof C12451bk) {
            return C12957a.m438a(this.f25224b, ((C12451bk) abstractC12570t).f25224b);
        }
        return false;
    }

    /* renamed from: b */
    public String m1708b() {
        StringBuffer stringBuffer = new StringBuffer("#");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            new C12567r(byteArrayOutputStream).mo1625a((InterfaceC12554f) this);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            for (int i = 0; i != byteArray.length; i++) {
                stringBuffer.append(f25223a[(byteArray[i] >>> 4) & 15]);
                stringBuffer.append(f25223a[byteArray[i] & 15]);
            }
            return stringBuffer.toString();
        } catch (IOException unused) {
            throw new C12569s("internal error encoding BitString");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.p415a.p418b.AbstractC12570t
    /* renamed from: c */
    public int mo1618c() {
        return C12466bz.m1694a(this.f25224b.length) + 1 + this.f25224b.length;
    }

    /* renamed from: d */
    public byte[] m1707d() {
        return C12957a.m430b(this.f25224b);
    }

    @Override // org.p415a.p418b.AbstractC12570t, org.p415a.p418b.AbstractC12562n
    public int hashCode() {
        return C12957a.m441a(this.f25224b);
    }

    public String toString() {
        return m1708b();
    }
}
