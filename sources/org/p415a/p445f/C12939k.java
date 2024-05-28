package org.p415a.p445f;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.p415a.p424c.C12592b;
import org.p415a.p445f.p446a.InterfaceC12903a;
import org.p415a.p448g.InterfaceC12970d;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.f.k */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12939k implements InterfaceC12970d {

    /* renamed from: a */
    private C12592b f26201a;

    /* renamed from: b */
    private InterfaceC12903a f26202b;

    public C12939k(InputStream inputStream, InterfaceC12903a interfaceC12903a) {
        this.f26201a = new C12592b(inputStream);
        this.f26202b = interfaceC12903a;
    }

    /* renamed from: a */
    public Object m456a() {
        int m1574b = this.f26201a.m1574b();
        if (m1574b != -1) {
            if (m1574b != 8) {
                if (m1574b == 14) {
                    try {
                        return C12946q.m449a(this.f26201a, this.f26202b);
                    } catch (C12934f e) {
                        throw new IOException("processing error: " + e.getMessage());
                    }
                }
                switch (m1574b) {
                    case 1:
                    case 3:
                        return new C12933e(this.f26201a);
                    case 2:
                        ArrayList arrayList = new ArrayList();
                        while (this.f26201a.m1574b() == 2) {
                            try {
                                arrayList.add(new C12950u(this.f26201a));
                            } catch (C12934f e2) {
                                throw new IOException("can't create signature object: " + e2);
                            }
                        }
                        return new C12951v((C12950u[]) arrayList.toArray(new C12950u[arrayList.size()]));
                    case 4:
                        ArrayList arrayList2 = new ArrayList();
                        while (this.f26201a.m1574b() == 4) {
                            try {
                                arrayList2.add(new C12941l(this.f26201a));
                            } catch (C12934f e3) {
                                throw new IOException("can't create one pass signature object: " + e3);
                            }
                        }
                        return new C12942m((C12941l[]) arrayList2.toArray(new C12941l[arrayList2.size()]));
                    case 5:
                        try {
                            return new C12949t(this.f26201a, this.f26202b);
                        } catch (C12934f e4) {
                            throw new IOException("can't create secret key object: " + e4);
                        }
                    case 6:
                        return new C12946q(this.f26201a, this.f26202b);
                    default:
                        switch (m1574b) {
                            case 10:
                                return new C12938j(this.f26201a);
                            case 11:
                                return new C12936h(this.f26201a);
                            default:
                                switch (m1574b) {
                                    case 60:
                                    case 61:
                                    case 62:
                                    case 63:
                                        return this.f26201a.m1573c();
                                    default:
                                        throw new IOException("unknown object in stream: " + this.f26201a.m1574b());
                                }
                        }
                }
            }
            return new C12902a(this.f26201a);
        }
        return null;
    }

    @Override // java.lang.Iterable
    public Iterator iterator() {
        return new Iterator() { // from class: org.a.f.k.1

            /* renamed from: b */
            private boolean f26204b = false;

            /* renamed from: c */
            private Object f26205c = null;

            /* renamed from: a */
            private Object m455a() {
                try {
                    return C12939k.this.m456a();
                } catch (IOException e) {
                    throw new C12947r("Iterator failed to get next object: " + e.getMessage(), e);
                }
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                if (!this.f26204b) {
                    this.f26204b = true;
                    this.f26205c = m455a();
                }
                return this.f26205c != null;
            }

            @Override // java.util.Iterator
            public Object next() {
                if (hasNext()) {
                    this.f26204b = false;
                    return this.f26205c;
                }
                throw new NoSuchElementException();
            }

            @Override // java.util.Iterator
            public void remove() {
                throw new UnsupportedOperationException("Cannot remove element from factory.");
            }
        };
    }
}
