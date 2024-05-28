package org.p415a.p445f;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.p415a.p424c.C12586ah;
import org.p415a.p424c.C12592b;
import org.p415a.p424c.C12618o;
import org.p415a.p424c.C12625v;
import org.p415a.p448g.InterfaceC12970d;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.f.e */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12933e implements InterfaceC12970d {

    /* renamed from: a */
    List f26192a = new ArrayList();

    /* renamed from: b */
    C12618o f26193b;

    public C12933e(C12592b c12592b) {
        List list;
        AbstractC12930c c12945p;
        while (true) {
            if (c12592b.m1574b() != 1 && c12592b.m1574b() != 3) {
                break;
            }
            this.f26192a.add(c12592b.m1573c());
        }
        this.f26193b = (C12618o) c12592b.m1573c();
        for (int i = 0; i != this.f26192a.size(); i++) {
            if (this.f26192a.get(i) instanceof C12586ah) {
                list = this.f26192a;
                c12945p = new C12943n((C12586ah) list.get(i), this.f26193b);
            } else {
                list = this.f26192a;
                c12945p = new C12945p((C12625v) list.get(i), this.f26193b);
            }
            list.set(i, c12945p);
        }
    }

    /* renamed from: a */
    public Iterator m464a() {
        return this.f26192a.iterator();
    }

    @Override // java.lang.Iterable
    public Iterator iterator() {
        return m464a();
    }
}
