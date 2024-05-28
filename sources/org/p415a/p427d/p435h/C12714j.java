package org.p415a.p427d.p435h;

import org.p415a.p436e.p437a.AbstractC12860g;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.a.d.h.j */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12714j extends C12711g {

    /* renamed from: c */
    private final AbstractC12860g f25908c;

    public C12714j(AbstractC12860g abstractC12860g, C12709e c12709e) {
        super(false, c12709e);
        this.f25908c = m1336a(abstractC12860g);
    }

    /* renamed from: a */
    private AbstractC12860g m1336a(AbstractC12860g abstractC12860g) {
        if (abstractC12860g != null) {
            if (abstractC12860g.m851n()) {
                throw new IllegalArgumentException("point at infinity");
            }
            AbstractC12860g m852m = abstractC12860g.m852m();
            if (m852m.m850o()) {
                return m852m;
            }
            throw new IllegalArgumentException("point not on curve");
        }
        throw new IllegalArgumentException("point has null value");
    }

    /* renamed from: b */
    public AbstractC12860g m1335b() {
        return this.f25908c;
    }
}
