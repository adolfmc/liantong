package com.baidu.platform.comapi.map.p153b.p155b;

import android.util.Pair;
import android.view.MotionEvent;
import com.baidu.platform.comapi.map.MapController;
import com.baidu.platform.comapi.map.p153b.C3020a;
import com.baidu.platform.comapi.map.p153b.C3038f;
import com.baidu.platform.comapi.map.p153b.p154a.C3027b;
import java.util.Iterator;
import java.util.LinkedList;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.platform.comapi.map.b.b.b */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C3031b implements C3027b.InterfaceC3028a {

    /* renamed from: c */
    private MapController f7811c;

    /* renamed from: e */
    private AbstractC3030a f7813e;

    /* renamed from: a */
    private LinkedList<C3020a.C3021a> f7809a = new LinkedList<>();

    /* renamed from: b */
    private C3038f f7810b = new C3038f();

    /* renamed from: d */
    private boolean f7812d = false;

    /* renamed from: f */
    private int f7814f = this.f7810b.f7846b / 3;

    public C3031b(MapController mapController) {
        this.f7811c = mapController;
    }

    /* renamed from: a */
    private boolean m17900a() {
        int m17903a;
        C3020a.C3024d m17910c;
        C3020a.C3021a c3021a;
        this.f7812d = true;
        Iterator<C3020a.C3021a> it = this.f7809a.iterator();
        while (it.hasNext()) {
            Double valueOf = Double.valueOf(C3020a.C3024d.m17903a(C3020a.f7780a.m17910c(), it.next().m17910c()));
            if (Math.abs(valueOf.doubleValue()) > 45.0d && Math.abs(valueOf.doubleValue()) < 135.0d) {
                return false;
            }
        }
        Pair<C3020a.C3024d, C3020a.C3024d> m17868c = this.f7810b.m17868c();
        C3020a.C3024d c3024d = (C3020a.C3024d) m17868c.first;
        C3020a.C3024d c3024d2 = (C3020a.C3024d) m17868c.second;
        boolean z = Math.abs(c3024d.f7791b) > ((double) this.f7814f) && Math.abs(c3024d2.f7791b) > ((double) this.f7814f);
        C3020a.C3021a first = this.f7809a.getFirst();
        C3020a.C3021a last = this.f7809a.getLast();
        C3020a.C3021a c3021a2 = new C3020a.C3021a(last.f7783a, first.f7783a);
        C3020a.C3021a c3021a3 = new C3020a.C3021a(last.f7784b, first.f7784b);
        if (c3024d.f7791b <= 0.0d || c3024d2.f7791b <= 0.0d) {
            m17903a = (int) C3020a.C3024d.m17903a(c3021a2.m17910c(), C3020a.f7781b.m17910c());
            m17910c = c3021a3.m17910c();
            c3021a = C3020a.f7781b;
        } else {
            m17903a = (int) C3020a.C3024d.m17903a(c3021a2.m17910c(), C3020a.f7782c.m17910c());
            m17910c = c3021a3.m17910c();
            c3021a = C3020a.f7782c;
        }
        return z && (Math.abs(m17903a) < 40 && Math.abs((int) C3020a.C3024d.m17903a(m17910c, c3021a.m17910c())) < 40);
    }

    /* renamed from: c */
    private void m17896c(C3027b c3027b) {
        if (this.f7809a.size() < 5) {
            this.f7809a.addLast(c3027b.f7799c);
            this.f7810b.m17870a(c3027b.f7800d);
        } else if (!this.f7812d && this.f7809a.size() == 5 && m17900a()) {
            m17895d(c3027b);
        }
    }

    /* renamed from: d */
    private void m17895d(C3027b c3027b) {
        if (this.f7811c.isOverlookGestureEnable()) {
            this.f7813e.mo17888a(c3027b, (Pair<C3020a.C3024d, C3020a.C3024d>) null);
            this.f7813e = new C3032c(this.f7811c);
            this.f7813e.mo17889a(c3027b);
        }
    }

    @Override // com.baidu.platform.comapi.map.p153b.p154a.C3027b.InterfaceC3028a
    /* renamed from: a */
    public boolean mo17899a(C3027b c3027b) {
        this.f7809a.clear();
        this.f7810b.m17871a();
        this.f7813e = new C3033d(this.f7811c);
        this.f7812d = false;
        return true;
    }

    @Override // com.baidu.platform.comapi.map.p153b.p154a.C3027b.InterfaceC3028a
    /* renamed from: a */
    public boolean mo17898a(C3027b c3027b, MotionEvent motionEvent) {
        m17896c(c3027b);
        if (this.f7809a.size() == 1) {
            this.f7813e.mo17889a(c3027b);
        }
        this.f7813e.mo17887a(c3027b, motionEvent);
        return true;
    }

    @Override // com.baidu.platform.comapi.map.p153b.p154a.C3027b.InterfaceC3028a
    /* renamed from: b */
    public boolean mo17897b(C3027b c3027b) {
        Pair<C3020a.C3024d, C3020a.C3024d> m17868c = this.f7810b.m17868c();
        this.f7810b.m17869b();
        this.f7813e.mo17888a(c3027b, m17868c);
        return true;
    }
}
