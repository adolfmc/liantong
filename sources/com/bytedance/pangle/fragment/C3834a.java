package com.bytedance.pangle.fragment;

import android.support.p083v4.app.Fragment;
import android.support.p083v4.util.SimpleArrayMap;
import java.lang.reflect.Field;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.bytedance.pangle.fragment.a */
/* loaded from: E:\10762272_dexfile_execute.dex */
final class C3834a {
    /* renamed from: a */
    public static void m16853a(Class cls) {
        try {
            if (Fragment.class.getName().contains("support")) {
                ((SimpleArrayMap) m16852a(Fragment.class, "sClassMap").get(null)).put(cls.getName(), cls);
                return;
            }
            Field m16852a = m16852a(Fragment.class, "sClassMap");
            if (m16852a != null) {
                ((SimpleArrayMap) m16852a.get(null)).put(cls.getName(), cls);
                return;
            }
            Class m16851a = m16851a("androidx.fragment.app.FragmentFactory");
            if (m16851a == null) {
                return;
            }
            Field m16852a2 = m16852a(m16851a, "sClassMap");
            if (m16852a2 != null) {
                ((SimpleArrayMap) m16852a2.get(null)).put(cls.getName(), cls);
                return;
            }
            Field m16852a3 = m16852a(m16851a, "sClassCacheMap");
            if (m16852a3 != null) {
                SimpleArrayMap simpleArrayMap = new SimpleArrayMap();
                simpleArrayMap.put(cls.getName(), cls);
                ((SimpleArrayMap) m16852a3.get(null)).put(cls.getClassLoader(), simpleArrayMap);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    private static Field m16852a(Class cls, String str) {
        try {
            Field declaredField = cls.getDeclaredField(str);
            if (declaredField != null) {
                declaredField.setAccessible(true);
            }
            return declaredField;
        } catch (NoSuchFieldException unused) {
            return null;
        }
    }

    /* renamed from: a */
    private static Class m16851a(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }
}
