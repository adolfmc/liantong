package com.baidu.mapapi.map;

import android.graphics.Point;
import com.baidu.mapapi.map.C2781v.AbstractC2782a;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.mapapi.map.v */
/* loaded from: E:\10201592_dexfile_execute.dex */
class C2781v<T extends AbstractC2782a> {

    /* renamed from: a */
    private final C2771m f6590a;

    /* renamed from: b */
    private final int f6591b;

    /* renamed from: c */
    private List<T> f6592c;

    /* renamed from: d */
    private List<C2781v<T>> f6593d;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.mapapi.map.v$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static abstract class AbstractC2782a {
        abstract Point getPoint();
    }

    private C2781v(double d, double d2, double d3, double d4, int i) {
        this(new C2771m(d, d2, d3, d4), i);
    }

    public C2781v(C2771m c2771m) {
        this(c2771m, 0);
    }

    private C2781v(C2771m c2771m, int i) {
        this.f6593d = null;
        this.f6590a = c2771m;
        this.f6591b = i;
    }

    /* renamed from: a */
    private void m18781a() {
        this.f6593d = new ArrayList(4);
        this.f6593d.add(new C2781v<>(this.f6590a.f6551a, this.f6590a.f6555e, this.f6590a.f6552b, this.f6590a.f6556f, this.f6591b + 1));
        this.f6593d.add(new C2781v<>(this.f6590a.f6555e, this.f6590a.f6553c, this.f6590a.f6552b, this.f6590a.f6556f, this.f6591b + 1));
        this.f6593d.add(new C2781v<>(this.f6590a.f6551a, this.f6590a.f6555e, this.f6590a.f6556f, this.f6590a.f6554d, this.f6591b + 1));
        this.f6593d.add(new C2781v<>(this.f6590a.f6555e, this.f6590a.f6553c, this.f6590a.f6556f, this.f6590a.f6554d, this.f6591b + 1));
        List<T> list = this.f6592c;
        this.f6592c = null;
        for (T t : list) {
            m18780a(t.getPoint().x, t.getPoint().y, t);
        }
    }

    /* renamed from: a */
    private void m18780a(double d, double d2, T t) {
        List<C2781v<T>> list;
        int i;
        if (this.f6593d == null) {
            if (this.f6592c == null) {
                this.f6592c = new ArrayList();
            }
            this.f6592c.add(t);
            if (this.f6592c.size() <= 40 || this.f6591b >= 40) {
                return;
            }
            m18781a();
            return;
        }
        if (d2 < this.f6590a.f6556f) {
            if (d < this.f6590a.f6555e) {
                list = this.f6593d;
                i = 0;
            } else {
                list = this.f6593d;
                i = 1;
            }
        } else if (d < this.f6590a.f6555e) {
            list = this.f6593d;
            i = 2;
        } else {
            list = this.f6593d;
            i = 3;
        }
        list.get(i).m18780a(d, d2, t);
    }

    /* renamed from: a */
    private void m18778a(C2771m c2771m, Collection<T> collection) {
        if (this.f6590a.m18787a(c2771m)) {
            List<C2781v<T>> list = this.f6593d;
            if (list != null) {
                for (C2781v<T> c2781v : list) {
                    c2781v.m18778a(c2771m, collection);
                }
            } else if (this.f6592c != null) {
                if (c2771m.m18786b(this.f6590a)) {
                    collection.addAll(this.f6592c);
                    return;
                }
                for (T t : this.f6592c) {
                    if (c2771m.m18788a(t.getPoint())) {
                        collection.add(t);
                    }
                }
            }
        }
    }

    /* renamed from: a */
    public Collection<T> m18779a(C2771m c2771m) {
        ArrayList arrayList = new ArrayList();
        m18778a(c2771m, arrayList);
        return arrayList;
    }

    /* renamed from: a */
    public void m18777a(T t) {
        Point point = t.getPoint();
        if (this.f6590a.m18790a(point.x, point.y)) {
            m18780a(point.x, point.y, t);
        }
    }
}
