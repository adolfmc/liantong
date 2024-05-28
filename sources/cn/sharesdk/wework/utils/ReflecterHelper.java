package cn.sharesdk.wework.utils;

import java.lang.reflect.Constructor;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: cn.sharesdk.wework.utils.b */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ReflecterHelper {
    /* renamed from: a */
    public static Object m21154a(String str, Object[] objArr) throws Exception {
        Constructor<?> declaredConstructor = Class.forName(str).getDeclaredConstructor(m21153a(objArr));
        declaredConstructor.setAccessible(true);
        return declaredConstructor.newInstance(objArr);
    }

    /* renamed from: a */
    public static Object m21155a(String str) throws Exception {
        return m21154a(str, null);
    }

    /* renamed from: a */
    private static Class<?>[] m21153a(Object[] objArr) {
        if (objArr != null) {
            Class<?>[] clsArr = new Class[objArr.length];
            int length = objArr.length;
            for (int i = 0; i < length; i++) {
                if (objArr[i] != null) {
                    clsArr[i] = objArr[i].getClass();
                } else {
                    clsArr[i] = String.class;
                }
                if (clsArr[i] == Integer.class) {
                    clsArr[i] = Integer.TYPE;
                } else if (clsArr[i] == Boolean.class) {
                    clsArr[i] = Boolean.TYPE;
                } else if (clsArr[i] == Long.class) {
                    clsArr[i] = Long.TYPE;
                }
            }
            return clsArr;
        }
        return null;
    }
}
