package org.p415a.p445f;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.p415a.p424c.C12579aa;
import org.p415a.p424c.C12580ab;
import org.p415a.p424c.C12587ai;
import org.p415a.p424c.C12592b;
import org.p415a.p424c.C12627x;
import org.p415a.p445f.p446a.InterfaceC12903a;
import org.p415a.p448g.InterfaceC12970d;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.f.t */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12949t extends AbstractC12935g implements InterfaceC12970d<C12948s> {

    /* renamed from: a */
    List f26226a = new ArrayList();

    /* renamed from: b */
    List f26227b = new ArrayList();

    public C12949t(InputStream inputStream, InterfaceC12903a interfaceC12903a) {
        List list;
        Object c12944o;
        C12592b a = m462a(inputStream);
        int m1574b = a.m1574b();
        if (m1574b != 5 && m1574b != 7) {
            throw new IOException("secret key ring doesn't start with secret key tag: tag 0x" + Integer.toHexString(m1574b));
        }
        C12579aa c12579aa = (C12579aa) a.m1573c();
        while (a.m1574b() == 61) {
            a.m1573c();
        }
        C12587ai a2 = m461a(a);
        List b = m459b(a);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        m460a(a, arrayList, arrayList2, arrayList3);
        this.f26226a.add(new C12948s(c12579aa, new C12944o(c12579aa.m1587a(), a2, b, arrayList, arrayList2, arrayList3, interfaceC12903a)));
        while (true) {
            if (a.m1574b() != 7 && a.m1574b() != 14) {
                return;
            }
            if (a.m1574b() == 7) {
                C12580ab c12580ab = (C12580ab) a.m1573c();
                while (a.m1574b() == 61) {
                    a.m1573c();
                }
                C12587ai a3 = m461a(a);
                List b2 = m459b(a);
                list = this.f26226a;
                c12944o = new C12948s(c12580ab, new C12944o(c12580ab.m1587a(), a3, b2, interfaceC12903a));
            } else {
                C12587ai a4 = m461a(a);
                List b3 = m459b(a);
                list = this.f26227b;
                c12944o = new C12944o((C12627x) a.m1573c(), a4, b3, interfaceC12903a);
            }
            list.add(c12944o);
        }
    }

    /* renamed from: a */
    public Iterator<C12948s> m447a() {
        return Collections.unmodifiableList(this.f26226a).iterator();
    }

    @Override // java.lang.Iterable
    public Iterator<C12948s> iterator() {
        return m447a();
    }
}
