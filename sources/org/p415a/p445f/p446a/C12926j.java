package org.p415a.p445f.p446a;

import java.io.ByteArrayOutputStream;
import org.p415a.p424c.C12613j;
import org.p415a.p424c.C12626w;
import org.p415a.p448g.p449a.C12964f;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.f.a.j */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12926j {

    /* renamed from: a */
    private static final byte[] f26175a = C12964f.m419a("416E6F6E796D6F75732053656E64657220202020");

    /* renamed from: a */
    public static byte[] m472a(C12626w c12626w, InterfaceC12903a interfaceC12903a) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        C12613j c12613j = (C12613j) c12626w.m1539c();
        byte[] i = c12613j.m1547e().m1643i();
        byteArrayOutputStream.write(i, 1, i.length - 1);
        byteArrayOutputStream.write(c12626w.m1540b());
        byteArrayOutputStream.write(3);
        byteArrayOutputStream.write(1);
        byteArrayOutputStream.write(c12613j.m1553b());
        byteArrayOutputStream.write(c12613j.m1552c());
        byteArrayOutputStream.write(f26175a);
        byteArrayOutputStream.write(interfaceC12903a.mo504a(c12626w));
        return byteArrayOutputStream.toByteArray();
    }
}
