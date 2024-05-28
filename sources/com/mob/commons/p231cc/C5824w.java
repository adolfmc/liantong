package com.mob.commons.p231cc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/* renamed from: com.mob.commons.cc.w */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5824w {

    /* renamed from: a */
    private String f14337a;

    /* renamed from: b */
    private int f14338b;

    /* renamed from: c */
    private C5810p f14339c;

    /* renamed from: d */
    private int f14340d;

    /* renamed from: e */
    private int f14341e;

    /* renamed from: f */
    private C5815t f14342f;

    public C5824w(String str, int i, ArrayList<C5822v> arrayList, ArrayList<Object> arrayList2, int i2, int i3, C5810p c5810p) {
        this.f14337a = str;
        this.f14338b = i;
        this.f14342f = new C5815t(arrayList, arrayList2);
        this.f14340d = i2;
        this.f14341e = i3;
        this.f14339c = c5810p;
    }

    /* renamed from: a */
    public C5824w m12348a(C5810p c5810p, String str, int i) {
        if (this.f14338b <= 1) {
            return this;
        }
        ArrayList<C5822v> arrayList = new ArrayList<>();
        m12347a(str, i, arrayList, 0);
        return new C5824w(null, 1, arrayList, new ArrayList(), 0, arrayList.size(), c5810p);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m12347a(String str, int i, ArrayList<C5822v> arrayList, int i2) {
        if (i2 != 0) {
            C5822v c5822v = new C5822v(29);
            c5822v.f14312b = str;
            c5822v.f14313c = i;
            c5822v.f14319i = 1;
            arrayList.add(c5822v);
        }
        C5822v c5822v2 = new C5822v(1);
        c5822v2.f14312b = str;
        c5822v2.f14313c = i;
        StringBuilder sb = new StringBuilder();
        sb.append("arg");
        int i3 = i2 + 1;
        sb.append(i3);
        c5822v2.f14318h = sb.toString();
        arrayList.add(c5822v2);
        int i4 = this.f14338b;
        if (i2 < i4 - 1) {
            m12347a(str, i, arrayList, i3);
            C5822v c5822v3 = new C5822v(28);
            c5822v3.f14312b = str;
            c5822v3.f14313c = i;
            arrayList.add(c5822v3);
        } else {
            for (int i5 = i4 - 1; i5 >= 0; i5 += -1) {
                C5822v c5822v4 = new C5822v(3);
                c5822v4.f14312b = str;
                c5822v4.f14313c = i;
                c5822v4.f14318h = "arg" + (i5 + 1);
                arrayList.add(c5822v4);
            }
            if (this.f14337a == null) {
                C5822v c5822v5 = new C5822v(2);
                c5822v5.f14312b = str;
                c5822v5.f14313c = i;
                c5822v5.f14327q = this;
                arrayList.add(c5822v5);
                C5822v c5822v6 = new C5822v(32);
                c5822v6.f14312b = str;
                c5822v6.f14313c = i;
                c5822v6.f14319i = this.f14338b;
                arrayList.add(c5822v6);
            } else {
                C5822v c5822v7 = new C5822v(31);
                c5822v7.f14312b = str;
                c5822v7.f14313c = i;
                c5822v7.f14318h = this.f14337a;
                c5822v7.f14319i = this.f14338b;
                arrayList.add(c5822v7);
            }
            Iterator<C5822v> it = this.f14342f.m12394a().iterator();
            while (true) {
                if (it.hasNext()) {
                    if (it.next().f14311a == 28) {
                        C5822v c5822v8 = new C5822v(28);
                        c5822v8.f14312b = str;
                        c5822v8.f14313c = i;
                        arrayList.add(c5822v8);
                        break;
                    }
                } else {
                    break;
                }
            }
        }
        if (i2 != 0) {
            C5822v c5822v9 = new C5822v(30);
            c5822v9.f14312b = str;
            c5822v9.f14313c = i;
            arrayList.add(c5822v9);
        }
    }

    /* renamed from: a */
    public C5826a m12345a(Object... objArr) {
        C5826a c5826a = new C5826a();
        try {
            LinkedList<Object> mo12344b = mo12344b(objArr);
            if (!mo12344b.isEmpty()) {
                c5826a.f14344b = mo12344b.get(0);
            }
        } catch (Throwable th) {
            c5826a.f14343a = th;
        }
        return c5826a;
    }

    /* renamed from: b */
    public LinkedList<Object> mo12344b(Object... objArr) throws Throwable {
        C5810p m12411b = this.f14339c.m12411b();
        int i = this.f14338b;
        if (i != 0) {
            if (objArr.length == i) {
                for (int length = objArr.length - 1; length >= 0; length--) {
                    m12411b.m12418a(objArr[length]);
                }
            } else if (objArr.length < i) {
                for (int length2 = objArr.length; length2 < this.f14338b; length2++) {
                    m12411b.m12418a((Object) null);
                }
                for (int length3 = objArr.length - 1; length3 >= 0; length3--) {
                    m12411b.m12418a(objArr[length3]);
                }
            } else {
                ArrayList arrayList = new ArrayList(0);
                for (int i2 = this.f14338b - 1; i2 < objArr.length; i2++) {
                    arrayList.add(objArr[i2]);
                }
                m12411b.m12418a(arrayList);
                for (int i3 = this.f14338b - 2; i3 >= 0; i3--) {
                    m12411b.m12418a(objArr[i3]);
                }
            }
        }
        LinkedList<Object> linkedList = new LinkedList<>();
        this.f14342f.m12393a(this.f14340d, this.f14341e, m12411b, linkedList);
        return linkedList;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.mob.commons.cc.w$1 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    class C58251 extends C5824w {
        /* JADX INFO: Access modifiers changed from: package-private */
        public C58251(String str, int i, ArrayList arrayList, ArrayList arrayList2, int i2, int i3, C5810p c5810p) {
            super(str, i, arrayList, arrayList2, i2, i3, c5810p);
        }

        @Override // com.mob.commons.p231cc.C5824w
        /* renamed from: b */
        public LinkedList<Object> mo12344b(Object... objArr) throws Throwable {
            return new LinkedList<>();
        }
    }

    /* renamed from: a */
    public static C5824w m12346a(String str, int i, ArrayList<C5822v> arrayList, ArrayList<Object> arrayList2, int i2, int i3, C5810p c5810p) {
        return new C58251(str, i, arrayList, arrayList2, i2, i3, c5810p);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.mob.commons.cc.w$a */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static class C5826a implements InterfaceC5812q<C5826a> {

        /* renamed from: a */
        public Throwable f14343a;

        /* renamed from: b */
        public Object f14344b;

        /* renamed from: a */
        public boolean m12343a() {
            return this.f14343a != null;
        }

        @Override // com.mob.commons.p231cc.InterfaceC5812q
        /* renamed from: a  reason: avoid collision after fix types in other method */
        public boolean mo12341a(C5826a c5826a, Class<C5826a> cls, String str, Object[] objArr, boolean[] zArr, Object[] objArr2, Throwable[] thArr) {
            if ("isError".equals(str) && objArr.length == 0) {
                objArr2[0] = Boolean.valueOf(c5826a.m12343a());
                return true;
            } else if ("getError".equals(str) && objArr.length == 0) {
                objArr2[0] = c5826a.f14343a;
                return true;
            } else if ("getResult".equals(str) && objArr.length == 0) {
                objArr2[0] = c5826a.f14344b;
                return true;
            } else {
                return false;
            }
        }
    }
}
