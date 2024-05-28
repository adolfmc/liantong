package org.p415a.p445f;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.p415a.p424c.C12587ai;
import org.p415a.p424c.C12592b;
import org.p415a.p424c.C12626w;
import org.p415a.p445f.p446a.InterfaceC12903a;
import org.p415a.p448g.InterfaceC12970d;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.f.q */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12946q extends AbstractC12935g implements InterfaceC12970d<C12944o> {

    /* renamed from: a */
    List f26222a = new ArrayList();

    public C12946q(InputStream inputStream, InterfaceC12903a interfaceC12903a) {
        C12592b a = m462a(inputStream);
        int m1574b = a.m1574b();
        if (m1574b != 6 && m1574b != 14) {
            throw new IOException("public key ring doesn't start with public key tag: tag 0x" + Integer.toHexString(m1574b));
        }
        C12626w c12626w = (C12626w) a.m1573c();
        C12587ai a2 = m461a(a);
        List b = m459b(a);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        m460a(a, arrayList, arrayList2, arrayList3);
        try {
            this.f26222a.add(new C12944o(c12626w, a2, b, arrayList, arrayList2, arrayList3, interfaceC12903a));
            while (a.m1574b() == 14) {
                this.f26222a.add(m449a(a, interfaceC12903a));
            }
        } catch (C12934f e) {
            throw new IOException("processing exception: " + e.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static C12944o m449a(C12592b c12592b, InterfaceC12903a interfaceC12903a) {
        return new C12944o((C12626w) c12592b.m1573c(), m461a(c12592b), m459b(c12592b), interfaceC12903a);
    }

    /* renamed from: a */
    public C12944o m450a() {
        return (C12944o) this.f26222a.get(0);
    }

    /* renamed from: b */
    public Iterator<C12944o> m448b() {
        return Collections.unmodifiableList(this.f26222a).iterator();
    }

    @Override // java.lang.Iterable
    public Iterator<C12944o> iterator() {
        return m448b();
    }
}
