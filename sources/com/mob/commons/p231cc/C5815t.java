package com.mob.commons.p231cc;

import com.mob.commons.C5868q;
import java.io.File;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* renamed from: com.mob.commons.cc.t */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5815t {

    /* renamed from: a */
    static final HashMap<String, Class<?>> f14298a = new HashMap<>();

    /* renamed from: b */
    private final ArrayList<C5822v> f14299b;

    /* renamed from: c */
    private final ArrayList<Object> f14300c;

    public C5815t(ArrayList<C5822v> arrayList, ArrayList<Object> arrayList2) {
        this.f14299b = arrayList;
        this.f14300c = arrayList2;
    }

    /* renamed from: a */
    public void m12391a(HashMap<String, Object> hashMap, C5813r c5813r) throws Throwable {
        C5810p c5810p = new C5810p(hashMap, c5813r);
        m12392a(c5810p);
        m12393a(0, this.f14299b.size(), c5810p, null);
    }

    /* JADX WARN: Code restructure failed: missing block: B:7:0x001e, code lost:
        r0.f14333d = true;
     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void m12393a(int r4, int r5, com.mob.commons.p231cc.C5810p r6, java.util.List<java.lang.Object> r7) throws java.lang.Throwable {
        /*
            r3 = this;
            com.mob.commons.cc.v$a r0 = new com.mob.commons.cc.v$a
            r0.<init>()
            r0.f14330a = r4
            r0.f14331b = r6
            r0.f14332c = r7
            java.util.ArrayList<com.mob.commons.cc.v> r4 = r3.f14299b
            r0.f14335f = r4
            java.util.ArrayList<java.lang.Object> r4 = r3.f14300c
            r0.f14336g = r4
        L13:
            int r4 = r0.f14330a     // Catch: java.lang.Throwable -> L4d
            if (r4 >= r5) goto L39
            boolean r4 = r6.m12405f()     // Catch: java.lang.Throwable -> L4d
            r1 = 1
            if (r4 == 0) goto L21
            r0.f14333d = r1     // Catch: java.lang.Throwable -> L4d
            goto L39
        L21:
            java.util.ArrayList<com.mob.commons.cc.v> r4 = r3.f14299b     // Catch: java.lang.Throwable -> L4d
            int r2 = r0.f14330a     // Catch: java.lang.Throwable -> L4d
            java.lang.Object r4 = r4.get(r2)     // Catch: java.lang.Throwable -> L4d
            com.mob.commons.cc.v r4 = (com.mob.commons.p231cc.C5822v) r4     // Catch: java.lang.Throwable -> L4d
            r4.m12367a(r0)     // Catch: java.lang.Throwable -> L4d
            boolean r4 = r0.f14334e     // Catch: java.lang.Throwable -> L4d
            if (r4 == 0) goto L33
            goto L39
        L33:
            int r4 = r0.f14330a     // Catch: java.lang.Throwable -> L4d
            int r4 = r4 + r1
            r0.f14330a = r4     // Catch: java.lang.Throwable -> L4d
            goto L13
        L39:
            boolean r4 = r0.f14333d
            if (r4 != 0) goto L4c
            int r4 = r6.m12407d()
            if (r4 <= 0) goto L4c
            if (r7 == 0) goto L4c
            java.lang.Object r4 = r6.m12419a()     // Catch: java.lang.Throwable -> L4c
            r7.add(r4)     // Catch: java.lang.Throwable -> L4c
        L4c:
            return
        L4d:
            r4 = move-exception
            boolean r5 = r4 instanceof com.mob.commons.p231cc.C5814s
            if (r5 == 0) goto L6a
            java.lang.String r5 = r4.getMessage()
            if (r5 != 0) goto L61
            java.lang.Class r5 = r4.getClass()
            java.lang.String r5 = r5.getSimpleName()
            goto L65
        L61:
            java.lang.String r5 = r4.getMessage()
        L65:
            java.lang.Throwable r4 = r4.getCause()
            goto L8e
        L6a:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "Suba Runtime Error: "
            r5.append(r6)
            java.lang.String r6 = r4.getMessage()
            if (r6 != 0) goto L83
            java.lang.Class r6 = r4.getClass()
            java.lang.String r6 = r6.getSimpleName()
            goto L87
        L83:
            java.lang.String r6 = r4.getMessage()
        L87:
            r5.append(r6)
            java.lang.String r5 = r5.toString()
        L8e:
            java.util.ArrayList<com.mob.commons.cc.v> r6 = r3.f14299b
            int r7 = r0.f14330a
            java.lang.Object r6 = r6.get(r7)
            com.mob.commons.cc.v r6 = (com.mob.commons.p231cc.C5822v) r6
            java.lang.String r6 = r6.f14312b
            java.util.ArrayList<com.mob.commons.cc.v> r7 = r3.f14299b
            int r0 = r0.f14330a
            java.lang.Object r7 = r7.get(r0)
            com.mob.commons.cc.v r7 = (com.mob.commons.p231cc.C5822v) r7
            int r7 = r7.f14313c
            com.mob.commons.cc.s r0 = new com.mob.commons.cc.s
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r5)
            java.lang.String r5 = "\r\n\tat "
            r1.append(r5)
            r1.append(r6)
            java.lang.String r5 = " ("
            r1.append(r5)
            r1.append(r7)
            java.lang.String r5 = ")"
            r1.append(r5)
            java.lang.String r5 = r1.toString()
            r0.<init>(r5, r4)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.commons.p231cc.C5815t.m12393a(int, int, com.mob.commons.cc.p, java.util.List):void");
    }

    /* renamed from: a */
    private void m12392a(C5810p c5810p) {
        c5810p.m12415a("Object", Object.class);
        c5810p.m12415a("Class", Class.class);
        c5810p.m12415a("Method", Method.class);
        c5810p.m12415a("String", String.class);
        c5810p.m12415a("Thread", Thread.class);
        c5810p.m12415a(C5868q.m12203b("0089fgcfOddc7edBfe"), Runnable.class);
        c5810p.m12415a(C5868q.m12203b("006)dicjeg?heUce"), System.class);
        c5810p.m12415a("File", File.class);
        c5810p.m12415a("URL", URL.class);
        c5810p.m12415a("Double", Double.class);
        c5810p.m12415a("Float", Float.class);
        c5810p.m12415a("Long", Long.class);
        c5810p.m12415a("Integer", Integer.class);
        c5810p.m12415a(C5868q.m12203b("005[di?g$dcciSh"), Short.class);
        c5810p.m12415a("Byte", Byte.class);
        c5810p.m12415a("Number", Number.class);
        c5810p.m12415a(C5868q.m12203b("009>fi.gc8ci cbhe'ci"), Character.class);
        c5810p.m12415a("Boolean", Boolean.class);
        c5810p.m12415a(C5868q.m12203b("006[cbdccfedPfe"), Double.TYPE);
        c5810p.m12415a(C5868q.m12203b("005Gde>fHdcPch"), Float.TYPE);
        c5810p.m12415a("long", Long.TYPE);
        c5810p.m12415a(C5868q.m12203b("003$ch@dh"), Integer.TYPE);
        c5810p.m12415a("short", Short.TYPE);
        c5810p.m12415a("byte", Byte.TYPE);
        c5810p.m12415a(C5868q.m12203b("004bgcXci"), Character.TYPE);
        c5810p.m12415a("boolean", Boolean.TYPE);
        c5810p.m12415a("bigInt", BigInteger.class);
        c5810p.m12415a("BigInteger", BigInteger.class);
        c5810p.m12415a("bigDec", BigDecimal.class);
        c5810p.m12415a("BigDecimal", BigDecimal.class);
        c5810p.m12415a("List", List.class);
        c5810p.m12415a("Map", Map.class);
        c5810p.m12415a("Function", C5824w.class);
        c5810p.m12415a("fun", C5824w.class);
        c5810p.m12415a("Range", C5827x.class);
        c5810p.m12415a("Array", Array.class);
        c5810p.m12415a("Suba", C5816u.class);
        c5810p.m12415a("VM", C5816u.class);
        for (Map.Entry<String, Class<?>> entry : f14298a.entrySet()) {
            c5810p.m12415a(entry.getKey(), entry.getValue());
        }
    }

    /* renamed from: a */
    public ArrayList<C5822v> m12394a() {
        return this.f14299b;
    }
}
