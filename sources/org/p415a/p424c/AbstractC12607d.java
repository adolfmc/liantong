package org.p415a.p424c;

import java.io.ByteArrayOutputStream;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.c.d */
/* loaded from: E:\11617560_dexfile_execute.dex */
public abstract class AbstractC12607d {
    /* renamed from: a */
    public abstract void mo1533a(C12608e c12608e);

    /* renamed from: a */
    public byte[] mo1536a() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        C12608e c12608e = new C12608e(byteArrayOutputStream);
        c12608e.m1564a(this);
        c12608e.close();
        return byteArrayOutputStream.toByteArray();
    }
}
