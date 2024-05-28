package com.alipay.p100a.p101a;

import com.alipay.p100a.p102b.C1917a;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.TreeSet;
import org.json.alipay.C13435a;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.alipay.a.a.b */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class C1906b implements InterfaceC1913i, InterfaceC1914j {
    /* renamed from: a */
    private static Collection<Object> m21152a(Class<?> cls, Type type) {
        if (cls == AbstractCollection.class) {
            return new ArrayList();
        }
        if (cls.isAssignableFrom(HashSet.class)) {
            return new HashSet();
        }
        if (cls.isAssignableFrom(LinkedHashSet.class)) {
            return new LinkedHashSet();
        }
        if (cls.isAssignableFrom(TreeSet.class)) {
            return new TreeSet();
        }
        if (cls.isAssignableFrom(ArrayList.class)) {
            return new ArrayList();
        }
        if (cls.isAssignableFrom(EnumSet.class)) {
            return EnumSet.noneOf(type instanceof ParameterizedType ? ((ParameterizedType) type).getActualTypeArguments()[0] : Object.class);
        }
        try {
            return (Collection) cls.newInstance();
        } catch (Exception unused) {
            throw new IllegalArgumentException("create instane error, class " + cls.getName());
        }
    }

    @Override // com.alipay.p100a.p101a.InterfaceC1914j
    /* renamed from: a */
    public final Object mo21145a(Object obj) {
        ArrayList arrayList = new ArrayList();
        for (Object obj2 : (Iterable) obj) {
            arrayList.add(C1910f.m21148b(obj2));
        }
        return arrayList;
    }

    @Override // com.alipay.p100a.p101a.InterfaceC1913i
    /* renamed from: a */
    public final Object mo21144a(Object obj, Type type) {
        if (obj.getClass().equals(C13435a.class)) {
            C13435a c13435a = (C13435a) obj;
            Collection<Object> m21152a = m21152a(C1917a.m21142a(type), type);
            if (type instanceof ParameterizedType) {
                Type type2 = ((ParameterizedType) type).getActualTypeArguments()[0];
                for (int i = 0; i < c13435a.m226a(); i++) {
                    m21152a.add(C1909e.m21151a(c13435a.m225a(i), type2));
                }
                return m21152a;
            }
            throw new IllegalArgumentException("Does not support the implement for generics.");
        }
        return null;
    }

    @Override // com.alipay.p100a.p101a.InterfaceC1913i, com.alipay.p100a.p101a.InterfaceC1914j
    /* renamed from: a */
    public final boolean mo21146a(Class<?> cls) {
        return Collection.class.isAssignableFrom(cls);
    }
}
