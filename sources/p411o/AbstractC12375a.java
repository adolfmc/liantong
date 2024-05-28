package p411o;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Future;
import p411o.InterfaceC12376b;

/* renamed from: o.a */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public abstract class AbstractC12375a<V extends InterfaceC12376b> {

    /* renamed from: a */
    public WeakReference<V> f25033a;

    /* renamed from: b */
    public List<Future<?>> f25034b;

    /* renamed from: a */
    public final V m1798a() {
        WeakReference<V> weakReference = this.f25033a;
        if (weakReference == null) {
            return null;
        }
        return weakReference.get();
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.util.List<java.util.concurrent.Future<?>>, java.util.ArrayList] */
    /* renamed from: a */
    public final void m1797a(Future<?> future) {
        if (future == null) {
            return;
        }
        if (this.f25034b == null) {
            this.f25034b = new ArrayList();
        }
        this.f25034b.add(future);
    }

    /* renamed from: a */
    public final void m1796a(V v) {
        this.f25033a = new WeakReference<>(v);
        this.f25034b = new ArrayList();
    }

    /* renamed from: b */
    public final boolean m1795b() {
        WeakReference<V> weakReference = this.f25033a;
        return (weakReference == null || weakReference.get() == null) ? false : true;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.util.List<java.util.concurrent.Future<?>>, java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.util.List<java.util.concurrent.Future<?>>, java.util.ArrayList] */
    /* renamed from: c */
    public final void m1794c() {
        ?? r0 = this.f25034b;
        if (r0 != 0) {
            Iterator it = r0.iterator();
            while (it.hasNext()) {
                ((Future) it.next()).cancel(true);
            }
            this.f25034b.clear();
            this.f25034b = null;
        }
    }
}
