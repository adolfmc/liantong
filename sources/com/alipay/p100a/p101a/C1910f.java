package com.alipay.p100a.p101a;

import com.alipay.p100a.p102b.C1917a;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.json.alipay.C13435a;
import org.json.alipay.C13436b;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.alipay.a.a.f */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class C1910f {

    /* renamed from: a */
    private static List<InterfaceC1914j> f3378a;

    static {
        ArrayList arrayList = new ArrayList();
        f3378a = arrayList;
        arrayList.add(new C1916l());
        f3378a.add(new C1908d());
        f3378a.add(new C1907c());
        f3378a.add(new C1912h());
        f3378a.add(new C1906b());
        f3378a.add(new C1905a());
        f3378a.add(new C1911g());
    }

    /* renamed from: a */
    public static String m21149a(Object obj) {
        if (obj == null) {
            return null;
        }
        Object m21148b = m21148b(obj);
        if (C1917a.m21143a(m21148b.getClass())) {
            return C13436b.m218c(m21148b.toString());
        }
        if (Collection.class.isAssignableFrom(m21148b.getClass())) {
            return new C13435a((Collection) ((List) m21148b)).toString();
        }
        if (Map.class.isAssignableFrom(m21148b.getClass())) {
            return new C13436b((Map) m21148b).toString();
        }
        throw new IllegalArgumentException("Unsupported Class : " + m21148b.getClass());
    }

    /* renamed from: b */
    public static Object m21148b(Object obj) {
        Object mo21145a;
        if (obj == null) {
            return null;
        }
        for (InterfaceC1914j interfaceC1914j : f3378a) {
            if (interfaceC1914j.mo21146a(obj.getClass()) && (mo21145a = interfaceC1914j.mo21145a(obj)) != null) {
                return mo21145a;
            }
        }
        throw new IllegalArgumentException("Unsupported Class : " + obj.getClass());
    }
}
