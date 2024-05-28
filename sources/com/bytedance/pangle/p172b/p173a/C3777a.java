package com.bytedance.pangle.p172b.p173a;

import com.bytedance.pangle.log.ZeusLogger;
import com.bytedance.pangle.util.FieldUtils;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.bytedance.pangle.b.a.a */
/* loaded from: E:\10762272_dexfile_execute.dex */
public final class C3777a {

    /* renamed from: a */
    private static Map<String, Field> f9026a = new HashMap();

    /* renamed from: b */
    private static Map<String, Method> f9027b = new HashMap();

    /* renamed from: c */
    private static Map<String, Constructor> f9028c = new HashMap();

    /* renamed from: d */
    private static Map<String, Class> f9029d = new HashMap();

    static {
        try {
            FieldUtils.writeField(C3778b.class, "classLoader", (Object) null);
            ZeusLogger.m16788w("Zeus/init_pangle", "HackHelper HackHelperImpl use BootClassLoader");
        } catch (Exception e) {
            ZeusLogger.errReport("Zeus/init_pangle", "HackHelperinit failed", e);
        }
    }

    /* renamed from: a */
    public static Method m16972a(Class<?> cls, String str, Class<?>... clsArr) {
        Method method;
        String m16970b = m16970b(cls, str, clsArr);
        synchronized (f9027b) {
            method = f9027b.get(m16970b);
        }
        if (method != null) {
            if (!method.isAccessible()) {
                method.setAccessible(true);
            }
            return method;
        }
        try {
            Method m16968a = C3778b.m16968a(cls, str, clsArr);
            if (m16968a != null) {
                synchronized (f9027b) {
                    f9027b.put(m16970b, m16968a);
                }
            }
            return m16968a;
        } catch (Throwable th) {
            ZeusLogger.m16787w("Zeus_pangle", "HackHelper" + String.format("getMethod %s#%s failed !!!", cls.getName(), str), th);
            return null;
        }
    }

    /* renamed from: a */
    public static Constructor m16971a(Class<?> cls, Class<?>... clsArr) {
        Constructor constructor;
        String m16970b = m16970b(cls, "clinit", clsArr);
        synchronized (f9028c) {
            constructor = f9028c.get(m16970b);
        }
        if (constructor != null) {
            if (!constructor.isAccessible()) {
                constructor.setAccessible(true);
            }
            return constructor;
        }
        try {
            Constructor m16967a = C3778b.m16967a(cls, clsArr);
            if (m16967a != null) {
                synchronized (f9028c) {
                    f9028c.put(m16970b, m16967a);
                }
            }
            return m16967a;
        } catch (Throwable th) {
            ZeusLogger.m16787w("Zeus_pangle", "HackHelper" + String.format("getConstructor %s failed !!!", cls.getName()), th);
            return null;
        }
    }

    /* renamed from: b */
    private static String m16970b(Class<?> cls, String str, Class<?>... clsArr) {
        StringBuilder sb = new StringBuilder();
        sb.append(cls.getName());
        sb.append("#");
        sb.append(str);
        if (clsArr != null && clsArr.length > 0) {
            for (Class<?> cls2 : clsArr) {
                sb.append(cls2.getName());
                sb.append("#");
            }
        } else {
            sb.append(Void.class.getName());
        }
        return sb.toString();
    }

    /* renamed from: a */
    public static Field m16973a(Class<?> cls, String str) {
        Field field;
        String str2 = cls.getName() + "#" + str;
        synchronized (f9026a) {
            field = f9026a.get(str2);
        }
        if (field != null) {
            if (!field.isAccessible()) {
                field.setAccessible(true);
            }
            return field;
        }
        try {
            Field m16969a = C3778b.m16969a(cls, str);
            if (m16969a != null) {
                synchronized (f9026a) {
                    f9026a.put(str2, m16969a);
                }
            }
            return m16969a;
        } catch (Throwable th) {
            ZeusLogger.m16787w("Zeus_pangle", "HackHelper" + String.format("getField %s#%s failed !!!", cls.getName(), str), th);
            return null;
        }
    }
}
