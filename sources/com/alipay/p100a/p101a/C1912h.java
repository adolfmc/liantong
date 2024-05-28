package com.alipay.p100a.p101a;

import com.alipay.p100a.p102b.C1917a;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.json.alipay.C13436b;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.alipay.a.a.h */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class C1912h implements InterfaceC1913i, InterfaceC1914j {
    /* renamed from: a */
    private static Map<Object, Object> m21147a(Type type) {
        while (type != Properties.class) {
            if (type == Hashtable.class) {
                return new Hashtable();
            }
            if (type == IdentityHashMap.class) {
                return new IdentityHashMap();
            }
            if (type == SortedMap.class || type == TreeMap.class) {
                return new TreeMap();
            }
            if (type == ConcurrentMap.class || type == ConcurrentHashMap.class) {
                return new ConcurrentHashMap();
            }
            if (type == Map.class || type == HashMap.class) {
                return new HashMap();
            }
            if (type == LinkedHashMap.class) {
                return new LinkedHashMap();
            }
            if (!(type instanceof ParameterizedType)) {
                Class cls = (Class) type;
                if (cls.isInterface()) {
                    throw new IllegalArgumentException("unsupport type " + type);
                }
                try {
                    return (Map) cls.newInstance();
                } catch (Exception e) {
                    throw new IllegalArgumentException("unsupport type " + type, e);
                }
            }
            type = ((ParameterizedType) type).getRawType();
        }
        return new Properties();
    }

    @Override // com.alipay.p100a.p101a.InterfaceC1914j
    /* renamed from: a */
    public final Object mo21145a(Object obj) {
        TreeMap treeMap = new TreeMap();
        for (Map.Entry entry : ((Map) obj).entrySet()) {
            if (!(entry.getKey() instanceof String)) {
                throw new IllegalArgumentException("Map key must be String!");
            }
            treeMap.put((String) entry.getKey(), C1910f.m21148b(entry.getValue()));
        }
        return treeMap;
    }

    @Override // com.alipay.p100a.p101a.InterfaceC1913i
    /* renamed from: a */
    public final Object mo21144a(Object obj, Type type) {
        if (obj.getClass().equals(C13436b.class)) {
            C13436b c13436b = (C13436b) obj;
            Map<Object, Object> m21147a = m21147a(type);
            if (type instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) type;
                Type type2 = parameterizedType.getActualTypeArguments()[0];
                Type type3 = parameterizedType.getActualTypeArguments()[1];
                if (String.class == type2) {
                    Iterator m223a = c13436b.m223a();
                    while (m223a.hasNext()) {
                        String str = (String) m223a.next();
                        m21147a.put(str, C1917a.m21143a((Class<?>) ((Class) type3)) ? c13436b.m221a(str) : C1909e.m21151a(c13436b.m221a(str), type3));
                    }
                    return m21147a;
                }
                throw new IllegalArgumentException("Deserialize Map Key must be String.class");
            }
            throw new IllegalArgumentException("Deserialize Map must be Generics!");
        }
        return null;
    }

    @Override // com.alipay.p100a.p101a.InterfaceC1913i, com.alipay.p100a.p101a.InterfaceC1914j
    /* renamed from: a */
    public final boolean mo21146a(Class<?> cls) {
        return Map.class.isAssignableFrom(cls);
    }
}
