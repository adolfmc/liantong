package com.alipay.p100a.p101a;

import com.alipay.p100a.p102b.C1917a;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import org.json.alipay.C13435a;
import org.json.alipay.C13436b;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.alipay.a.a.e */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class C1909e {

    /* renamed from: a */
    static List<InterfaceC1913i> f3377a;

    static {
        ArrayList arrayList = new ArrayList();
        f3377a = arrayList;
        arrayList.add(new C1916l());
        f3377a.add(new C1908d());
        f3377a.add(new C1907c());
        f3377a.add(new C1912h());
        f3377a.add(new C1915k());
        f3377a.add(new C1906b());
        f3377a.add(new C1905a());
        f3377a.add(new C1911g());
    }

    /* renamed from: a */
    public static final <T> T m21151a(Object obj, Type type) {
        T t;
        for (InterfaceC1913i interfaceC1913i : f3377a) {
            if (interfaceC1913i.mo21146a(C1917a.m21142a(type)) && (t = (T) interfaceC1913i.mo21144a(obj, type)) != null) {
                return t;
            }
        }
        return null;
    }

    /* renamed from: a */
    public static final Object m21150a(String str, Type type) {
        Object c13436b;
        if (str == null || str.length() == 0) {
            return null;
        }
        String trim = str.trim();
        if (trim.startsWith("[") && trim.endsWith("]")) {
            c13436b = new C13435a(trim);
        } else if (!trim.startsWith("{") || !trim.endsWith("}")) {
            return m21151a((Object) trim, type);
        } else {
            c13436b = new C13436b(trim);
        }
        return m21151a(c13436b, type);
    }
}
