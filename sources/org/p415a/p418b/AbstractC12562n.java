package org.p415a.p418b;

import java.io.ByteArrayOutputStream;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.b.n */
/* loaded from: E:\11617560_dexfile_execute.dex */
public abstract class AbstractC12562n implements InterfaceC12554f {
    /* renamed from: a */
    public byte[] m1644a(String str) {
        ByteArrayOutputStream byteArrayOutputStream;
        if (str.equals("DER")) {
            byteArrayOutputStream = new ByteArrayOutputStream();
            new C12442bb(byteArrayOutputStream).mo1625a(this);
        } else if (!str.equals("DL")) {
            return m1643i();
        } else {
            byteArrayOutputStream = new ByteArrayOutputStream();
            new C12455bo(byteArrayOutputStream).mo1625a(this);
        }
        return byteArrayOutputStream.toByteArray();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof InterfaceC12554f) {
            return mo1617h().equals(((InterfaceC12554f) obj).mo1617h());
        }
        return false;
    }

    @Override // org.p415a.p418b.InterfaceC12554f
    /* renamed from: h */
    public abstract AbstractC12570t mo1617h();

    public int hashCode() {
        return mo1617h().hashCode();
    }

    /* renamed from: i */
    public byte[] m1643i() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        new C12567r(byteArrayOutputStream).mo1625a(this);
        return byteArrayOutputStream.toByteArray();
    }
}
