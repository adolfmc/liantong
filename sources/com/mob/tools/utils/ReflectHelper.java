package com.mob.tools.utils;

import android.content.BroadcastReceiver;
import com.mob.commons.C5868q;
import com.mob.tools.MobLog;
import com.mob.tools.proguard.PublicMemberKeeper;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class ReflectHelper implements PublicMemberKeeper {

    /* renamed from: a */
    private static HashSet<String> f15258a = new HashSet<>();

    /* renamed from: b */
    private static HashMap<String, Class<?>> f15259b;

    /* renamed from: c */
    private static HashMap<Class<?>, String> f15260c;

    /* renamed from: d */
    private static LinkedHashMap<String, Method> f15261d;

    /* renamed from: e */
    private static LinkedHashMap<String, Constructor<?>> f15262e;

    /* JADX WARN: Classes with same name are omitted:
  E:\567196_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.mob.tools.utils.ReflectHelper$a */
    /* loaded from: E:\567196_dexfile_execute.dex */
    public interface InterfaceC6184a<ArgType, RetType> {
        /* renamed from: a */
        RetType mo11056a(ArgType argtype);
    }

    static {
        f15258a.add(C5868q.m12203b("009_ggIc+cc,c4ecYfcd$dd"));
        f15258a.add(C5868q.m12203b("007Pgg!c*cc8c4ecchdc"));
        f15258a.add(C5868q.m12203b("008Ggg>c0ccJc)ecRdOchdc"));
        f15258a.add("java.net");
        f15258a.add(C5868q.m12203b("009AggTcOcc.c]eccf5h*chKf"));
        f15259b = new HashMap<>();
        f15259b.put(C5868q.m12203b("006DcbdccfedNfe"), Double.TYPE);
        f15259b.put(C5868q.m12203b("005)de(f)dcMch"), Float.TYPE);
        f15259b.put("long", Long.TYPE);
        f15259b.put(C5868q.m12203b("003$chAdh"), Integer.TYPE);
        f15259b.put("short", Short.TYPE);
        f15259b.put("byte", Byte.TYPE);
        f15259b.put(C5868q.m12203b("004bgcQci"), Character.TYPE);
        f15259b.put("boolean", Boolean.TYPE);
        f15259b.put("Object", Object.class);
        f15259b.put("String", String.class);
        f15259b.put("Thread", Thread.class);
        f15259b.put(C5868q.m12203b("008)fgcfRddcXedGfe"), Runnable.class);
        f15259b.put(C5868q.m12203b("006Hdicjeg0heLce"), System.class);
        f15259b.put(C5868q.m12203b("0065cbdccfedJfe"), Double.class);
        f15259b.put("Float", Float.class);
        f15259b.put("Long", Long.class);
        f15259b.put("Integer", Integer.class);
        f15259b.put(C5868q.m12203b("005Sdi:g>dcci h"), Short.class);
        f15259b.put("Byte", Byte.class);
        f15259b.put(C5868q.m12203b("009^fi-gcQci*cbheBci"), Character.class);
        f15259b.put("Boolean", Boolean.class);
        f15260c = new HashMap<>();
        for (Map.Entry<String, Class<?>> entry : f15259b.entrySet()) {
            f15260c.put(entry.getValue(), entry.getKey());
        }
        f15261d = new LinkedHashMap<String, Method>() { // from class: com.mob.tools.utils.ReflectHelper.1
            @Override // java.util.LinkedHashMap
            protected boolean removeEldestEntry(Map.Entry<String, Method> entry2) {
                return size() > 10;
            }
        };
        f15262e = new LinkedHashMap<String, Constructor<?>>() { // from class: com.mob.tools.utils.ReflectHelper.2
            @Override // java.util.LinkedHashMap
            protected boolean removeEldestEntry(Map.Entry<String, Constructor<?>> entry2) {
                return size() > 10;
            }
        };
    }

    public static String importClassNoThrow(String str, String str2) {
        try {
            return importClass(str);
        } catch (Throwable th) {
            MobLog.getInstance().m11341d(th);
            return str2;
        }
    }

    public static String importClass(String str) throws Throwable {
        return importClass(null, str);
    }

    public static synchronized String importClass(String str, String str2) throws Throwable {
        synchronized (ReflectHelper.class) {
            if (str2.endsWith(".*")) {
                f15258a.add(str2.substring(0, str2.length() - 2));
                return "*";
            }
            Class<?> cls = Class.forName(str2);
            if (str == null) {
                str = cls.getSimpleName();
            }
            if (f15259b.containsKey(str)) {
                f15260c.remove(f15259b.get(str));
            }
            f15259b.put(str, cls);
            f15260c.put(cls, str);
            return str;
        }
    }

    /* renamed from: a */
    private static synchronized Class<?> m11136a(String str) {
        Class<?> cls;
        synchronized (ReflectHelper.class) {
            cls = f15259b.get(str);
            if (cls == null) {
                Iterator<String> it = f15258a.iterator();
                while (it.hasNext()) {
                    String next = it.next();
                    try {
                        importClass(next + "." + str);
                    } catch (Throwable unused) {
                    }
                    cls = f15259b.get(str);
                    if (cls != null) {
                        break;
                    }
                }
            }
        }
        return cls;
    }

    /* renamed from: a */
    private static Class<?>[] m11129a(Object[] objArr) {
        Class<?>[] clsArr = new Class[objArr.length];
        for (int i = 0; i < objArr.length; i++) {
            if (objArr[i] instanceof BroadcastReceiver) {
                clsArr[i] = BroadcastReceiver.class;
            } else {
                clsArr[i] = objArr[i] == null ? null : objArr[i].getClass();
            }
        }
        return clsArr;
    }

    /* renamed from: a */
    private static boolean m11139a(Class<?> cls, Class<?> cls2) {
        return (cls == Byte.TYPE && cls2 == Byte.class) || (cls == Short.TYPE && (cls2 == Short.class || cls2 == Byte.class || cls2 == Character.class)) || ((cls == Character.TYPE && (cls2 == Character.class || cls2 == Short.class || cls2 == Byte.class)) || ((cls == Integer.TYPE && (cls2 == Integer.class || cls2 == Short.class || cls2 == Byte.class || cls2 == Character.class)) || ((cls == Long.TYPE && (cls2 == Long.class || cls2 == Integer.class || cls2 == Short.class || cls2 == Byte.class || cls2 == Character.class)) || ((cls == Float.TYPE && (cls2 == Float.class || cls2 == Long.class || cls2 == Integer.class || cls2 == Short.class || cls2 == Byte.class || cls2 == Character.class)) || ((cls == Double.TYPE && (cls2 == Double.class || cls2 == Float.class || cls2 == Long.class || cls2 == Integer.class || cls2 == Short.class || cls2 == Byte.class || cls2 == Character.class)) || (cls == Boolean.TYPE && cls2 == Boolean.class))))));
    }

    /* renamed from: a */
    private static boolean m11130a(Class<?>[] clsArr, Class<?>[] clsArr2) {
        if (clsArr.length == clsArr2.length) {
            for (int i = 0; i < clsArr2.length; i++) {
                if (clsArr2[i] != null && !m11139a(clsArr[i], clsArr2[i]) && !clsArr[i].isAssignableFrom(clsArr2[i])) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /* renamed from: b */
    private static boolean m11125b(Class<?>[] clsArr, Class<?>[] clsArr2) {
        boolean z;
        if (clsArr.length - clsArr2.length == 1) {
            int i = 0;
            while (true) {
                if (i >= clsArr2.length) {
                    z = true;
                    break;
                } else if (clsArr2[i] != null && !m11139a(clsArr[i], clsArr2[i]) && !clsArr[i].isAssignableFrom(clsArr2[i])) {
                    z = false;
                    break;
                } else {
                    i++;
                }
            }
            return z && clsArr[clsArr.length - 1].isArray();
        }
        return false;
    }

    public static Object newInstance(String str, Object... objArr) throws Throwable {
        try {
            return m11131a(str, objArr);
        } catch (Throwable th) {
            if (th instanceof NoSuchMethodException) {
                throw th;
            }
            throw new Throwable("className: " + str + ", methodName: <init>", th);
        }
    }

    /* renamed from: a */
    private static Object m11131a(String str, Object... objArr) throws Throwable {
        boolean z;
        if (str.startsWith("[")) {
            return m11126b(str, objArr);
        }
        Class<?> m11136a = m11136a(str);
        String str2 = m11136a.getName() + "#" + objArr.length;
        Constructor<?> constructor = f15262e.get(str2);
        Class<?>[] m11129a = m11129a(objArr);
        if (constructor != null && m11130a(constructor.getParameterTypes(), m11129a)) {
            constructor.setAccessible(true);
            return constructor.newInstance(objArr);
        }
        Constructor<?>[] declaredConstructors = m11136a.getDeclaredConstructors();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (Constructor<?> constructor2 : declaredConstructors) {
            Class<?>[] parameterTypes = constructor2.getParameterTypes();
            if (m11130a(parameterTypes, m11129a)) {
                f15262e.put(str2, constructor2);
                constructor2.setAccessible(true);
                return constructor2.newInstance(objArr);
            }
            if (parameterTypes.length > 0 && parameterTypes[parameterTypes.length - 1].isArray() && m11129a.length >= parameterTypes.length - 1) {
                arrayList.add(constructor2);
                arrayList2.add(parameterTypes);
            }
        }
        for (int i = 0; i < arrayList2.size(); i++) {
            Class[] clsArr = (Class[]) arrayList2.get(i);
            Class<?> componentType = clsArr[clsArr.length - 1].getComponentType();
            if (m11125b(clsArr, m11129a)) {
                Object[] objArr2 = new Object[objArr.length + 1];
                System.arraycopy(objArr, 0, objArr2, 0, objArr.length);
                objArr2[objArr.length] = Array.newInstance(componentType, 0);
                Constructor constructor3 = (Constructor) arrayList.get(i);
                constructor3.setAccessible(true);
                return constructor3.newInstance(objArr);
            }
            int length = clsArr.length - 1;
            while (true) {
                if (length >= m11129a.length) {
                    z = true;
                    break;
                } else if (!m11129a[length].equals(componentType)) {
                    z = false;
                    break;
                } else {
                    length++;
                }
            }
            if (z) {
                int length2 = (m11129a.length - clsArr.length) + 1;
                Object newInstance = Array.newInstance(componentType, length2);
                for (int i2 = 0; i2 < length2; i2++) {
                    Array.set(newInstance, i2, objArr[(clsArr.length - 1) + i2]);
                }
                Object[] objArr3 = new Object[objArr.length + 1];
                System.arraycopy(objArr, 0, objArr3, 0, objArr.length);
                objArr3[objArr.length] = newInstance;
                Constructor constructor4 = (Constructor) arrayList.get(i);
                constructor4.setAccessible(true);
                return constructor4.newInstance(objArr);
            }
        }
        throw new NoSuchMethodException("className: " + str + ", methodName: <init>");
    }

    /* renamed from: b */
    private static Object m11126b(String str, Object... objArr) throws Throwable {
        Class<?> m11136a;
        String str2 = str;
        int i = 0;
        while (str2.startsWith("[")) {
            i++;
            str2 = str2.substring(1);
        }
        int[] iArr = null;
        if (i == objArr.length) {
            int[] iArr2 = new int[i];
            for (int i2 = 0; i2 < i; i2++) {
                try {
                    iArr2[i2] = Integer.parseInt(String.valueOf(objArr[i2]));
                } catch (Throwable unused) {
                }
            }
            iArr = iArr2;
        }
        if (iArr != null) {
            if ("B".equals(str2)) {
                m11136a = Byte.TYPE;
            } else if ("S".equals(str2)) {
                m11136a = Short.TYPE;
            } else if ("I".equals(str2)) {
                m11136a = Integer.TYPE;
            } else if ("J".equals(str2)) {
                m11136a = Long.TYPE;
            } else if ("F".equals(str2)) {
                m11136a = Float.TYPE;
            } else if ("D".equals(str2)) {
                m11136a = Double.TYPE;
            } else if ("Z".equals(str2)) {
                m11136a = Boolean.TYPE;
            } else if ("C".equals(str2)) {
                m11136a = Character.TYPE;
            } else {
                m11136a = m11136a(str2);
            }
            if (m11136a != null) {
                return Array.newInstance(m11136a, iArr);
            }
        }
        throw new NoSuchMethodException("className: [" + str + ", methodName: <init>");
    }

    public static <T> T invokeStaticMethod(String str, String str2, Object[] objArr, Class<?>[] clsArr, T t) {
        try {
            invokeStaticMethod(str, str2, objArr, clsArr);
        } catch (Throwable th) {
            MobLog.getInstance().m11341d(th);
        }
        return t;
    }

    public static <T> T invokeStaticMethod(String str, String str2, Object[] objArr, Class<?>[] clsArr) throws Throwable {
        return (T) m11134a(str, null, str2, objArr, clsArr);
    }

    public static <T> T invokeStaticMethodNoThrow(String str, String str2, T t, Object... objArr) {
        try {
            return (T) invokeStaticMethod(str, str2, objArr);
        } catch (Throwable th) {
            MobLog.getInstance().m11341d(th);
            return t;
        }
    }

    public static <T> T invokeStaticMethod(String str, String str2, Object... objArr) throws Throwable {
        try {
            return (T) m11135a(str, null, str2, objArr);
        } catch (Throwable th) {
            if (th instanceof NoSuchMethodException) {
                throw th;
            }
            throw new Throwable("className: " + str + ", methodName: " + str2, th);
        }
    }

    /* renamed from: a */
    private static <T> T m11134a(String str, Object obj, String str2, Object[] objArr, Class<?>[] clsArr) throws Throwable {
        Class<?> cls;
        if (objArr == null) {
            objArr = new Object[0];
        }
        if (clsArr == null) {
            clsArr = new Class[0];
        }
        if (obj == null) {
            cls = m11136a(str);
        } else {
            cls = obj.getClass();
        }
        String str3 = cls.getName() + "#" + str2 + "#" + objArr.length;
        Method method = f15261d.get(str3);
        if (method != null) {
            method.setAccessible(true);
            try {
                if (method.getReturnType() == Void.TYPE) {
                    method.invoke(obj, objArr);
                    return null;
                }
                return (T) method.invoke(obj, objArr);
            } catch (InvocationTargetException e) {
                throw e;
            }
        }
        while (cls != null) {
            try {
                Method declaredMethod = cls.getDeclaredMethod(str2, clsArr);
                f15261d.put(str3, declaredMethod);
                declaredMethod.setAccessible(true);
                if (declaredMethod.getReturnType() == Void.TYPE) {
                    declaredMethod.invoke(obj, objArr);
                    return null;
                }
                return (T) declaredMethod.invoke(obj, objArr);
            } catch (InvocationTargetException e2) {
                throw e2;
            } catch (Throwable unused) {
                cls = cls.getSuperclass();
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("className: ");
        Class<?> cls2 = str;
        if (obj != null) {
            cls2 = obj.getClass();
        }
        sb.append((Object) cls2);
        sb.append(", methodName: ");
        sb.append(str2);
        throw new NoSuchMethodException(sb.toString());
    }

    /* renamed from: a */
    private static <T> T m11135a(String str, Object obj, String str2, Object... objArr) throws Throwable {
        Class<?> cls;
        Class<?>[] m11129a;
        if (obj == null) {
            cls = m11136a(str);
        } else {
            cls = obj.getClass();
        }
        boolean z = false;
        if (str2.equals(C5868q.m12203b("009:dd_ehZgbFehg%dccb")) && objArr != null && objArr.length == 2) {
            m11129a = new Class[]{String.class, Class[].class};
            if (objArr[1] == String.class) {
                Class[] clsArr = new Class[1];
                clsArr[0] = String.class;
                objArr[1] = clsArr;
            }
        } else if (str2.equals("getDeviceId") && objArr != null && objArr.length == 1) {
            m11129a = new Class[]{Integer.TYPE};
        } else if (str2.equals(C5868q.m12203b("006Ich+d3ccdcckOe")) && objArr != null && objArr.length == 2) {
            m11129a = new Class[]{Object.class, Object[].class};
        } else {
            m11129a = (str2.equals(C5868q.m12203b("013Meg?ehDdk=bbe3egegchedPfe")) && objArr != null && objArr.length == 1) ? new Class[]{Boolean.TYPE} : m11129a(objArr);
        }
        StringBuffer stringBuffer = new StringBuffer();
        int length = m11129a.length;
        for (int i = 0; i < length; i++) {
            Class<?> cls2 = m11129a[i];
            stringBuffer.append(cls2 == null ? "" : cls2.getName());
        }
        String str3 = cls.getName() + "#" + str2 + "#" + objArr.length + stringBuffer.toString();
        Method method = f15261d.get(str3);
        if (method != null) {
            boolean isStatic = Modifier.isStatic(method.getModifiers());
            if (obj == null) {
                z = isStatic;
            } else if (!isStatic) {
                z = true;
            }
            if (z && m11130a(method.getParameterTypes(), m11129a)) {
                method.setAccessible(true);
                try {
                    if (method.getReturnType() == Void.TYPE) {
                        method.invoke(obj, objArr);
                        return null;
                    }
                    return (T) method.invoke(obj, objArr);
                } catch (InvocationTargetException e) {
                    throw e;
                }
            }
        }
        while (cls != null) {
            try {
                Method declaredMethod = cls.getDeclaredMethod(str2, m11129a);
                f15261d.put(str3, declaredMethod);
                declaredMethod.setAccessible(true);
                if (declaredMethod.getReturnType() == Void.TYPE) {
                    declaredMethod.invoke(obj, objArr);
                    return null;
                }
                return (T) declaredMethod.invoke(obj, objArr);
            } catch (InvocationTargetException e2) {
                throw e2;
            } catch (Throwable unused) {
                cls = cls.getSuperclass();
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("className: ");
        Class<?> cls3 = str;
        if (obj != null) {
            cls3 = obj.getClass();
        }
        sb.append((Object) cls3);
        sb.append(", methodName: ");
        sb.append(str2);
        throw new NoSuchMethodException(sb.toString());
    }

    public static <T> T invokeInstanceMethod(Object obj, String str, Object[] objArr, Class<?>[] clsArr, T t) {
        try {
            return (T) invokeInstanceMethod(obj, str, objArr, clsArr);
        } catch (Throwable th) {
            MobLog.getInstance().m11341d(th);
            return t;
        }
    }

    public static <T> T invokeInstanceMethod(Object obj, String str, Object[] objArr, Class<?>[] clsArr) throws Throwable {
        return (T) m11134a(null, obj, str, objArr, clsArr);
    }

    public static <T> T invokeInstanceMethodNoThrow(Object obj, String str, T t, Object... objArr) {
        try {
            return (T) invokeInstanceMethod(obj, str, objArr);
        } catch (Throwable th) {
            MobLog.getInstance().m11341d(th);
            return t;
        }
    }

    public static <T> T invokeInstanceMethod(Object obj, String str, Object... objArr) throws Throwable {
        try {
            return (T) m11135a(null, obj, str, objArr);
        } catch (Throwable th) {
            if (th instanceof NoSuchMethodException) {
                throw th;
            }
            throw new Throwable("className: " + obj.getClass() + ", methodName: " + str, th);
        }
    }

    public static <T> T getStaticField(String str, String str2, T t) {
        try {
            getStaticField(str, str2);
        } catch (Throwable th) {
            MobLog.getInstance().m11341d(th);
        }
        return t;
    }

    public static <T> T getStaticField(String str, String str2) throws Throwable {
        try {
            return (T) m11133a(str, str2);
        } catch (Throwable th) {
            if (th instanceof NoSuchFieldException) {
                throw th;
            }
            throw new Throwable("className: " + str + ", fieldName: " + str2, th);
        }
    }

    /* renamed from: a */
    private static <T> T m11133a(String str, String str2) throws Throwable {
        Field field;
        ArrayList arrayList = new ArrayList();
        for (Class<?> m11136a = m11136a(str); m11136a != null; m11136a = m11136a.getSuperclass()) {
            arrayList.add(m11136a);
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            try {
                field = ((Class) it.next()).getDeclaredField(str2);
            } catch (Throwable unused) {
                field = null;
            }
            if (field != null && Modifier.isStatic(field.getModifiers())) {
                field.setAccessible(true);
                return (T) field.get(null);
            }
        }
        throw new NoSuchFieldException("className: " + str + ", fieldName: " + str2);
    }

    public static void setStaticField(String str, String str2, Object obj) throws Throwable {
        try {
            m11132a(str, str2, obj);
        } catch (Throwable th) {
            if (th instanceof NoSuchFieldException) {
                throw th;
            }
            throw new Throwable("className: " + str + ", fieldName: " + str2 + ", value: " + String.valueOf(obj), th);
        }
    }

    /* renamed from: a */
    private static void m11132a(String str, String str2, Object obj) throws Throwable {
        Field field;
        ArrayList arrayList = new ArrayList();
        for (Class<?> m11136a = m11136a(str); m11136a != null; m11136a = m11136a.getSuperclass()) {
            arrayList.add(m11136a);
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            try {
                field = ((Class) it.next()).getDeclaredField(str2);
            } catch (Throwable unused) {
                field = null;
            }
            if (field != null && Modifier.isStatic(field.getModifiers())) {
                field.setAccessible(true);
                field.set(null, obj);
                return;
            }
        }
        throw new NoSuchFieldException("className: " + str + ", fieldName: " + str2 + ", value: " + String.valueOf(obj));
    }

    public static <T> T getInstanceField(Object obj, String str, T t) {
        try {
            return (T) getInstanceField(obj, str);
        } catch (Throwable th) {
            MobLog.getInstance().m11341d(th);
            return t;
        }
    }

    public static <T> T getInstanceField(Object obj, String str) throws Throwable {
        try {
            return (T) m11138a(obj, str);
        } catch (Throwable th) {
            if (th instanceof NoSuchFieldException) {
                throw th;
            }
            throw new Throwable("className: " + obj.getClass() + ", fieldName: " + str, th);
        }
    }

    /* renamed from: a */
    private static <T> T m11138a(Object obj, String str) throws Throwable {
        if ((obj instanceof List) || obj.getClass().isArray()) {
            return (T) m11128b(obj, str);
        }
        if (obj instanceof Map) {
            return (T) ((Map) obj).get(str);
        }
        ArrayList arrayList = new ArrayList();
        for (Class<?> cls = obj.getClass(); cls != null; cls = cls.getSuperclass()) {
            arrayList.add(cls);
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            Field field = null;
            try {
                field = ((Class) it.next()).getDeclaredField(str);
            } catch (Throwable unused) {
            }
            if (field != null && !Modifier.isStatic(field.getModifiers())) {
                field.setAccessible(true);
                return (T) field.get(obj);
            }
        }
        throw new NoSuchFieldException("className: " + obj.getClass() + ", fieldName: " + str);
    }

    /* renamed from: b */
    private static Object m11128b(Object obj, String str) throws Throwable {
        int i;
        int i2;
        if (obj instanceof List) {
            if (str.startsWith("[") && str.endsWith("]")) {
                try {
                    i2 = Integer.parseInt(str.substring(1, str.length() - 1));
                } catch (Throwable unused) {
                    i2 = -1;
                }
                if (i2 != -1) {
                    return ((List) obj).get(i2);
                }
            }
        } else if (C5868q.m12203b("006fed!dd_hg").equals(str)) {
            return Integer.valueOf(Array.getLength(obj));
        } else {
            if (str.startsWith("[") && str.endsWith("]")) {
                try {
                    i = Integer.parseInt(str.substring(1, str.length() - 1));
                } catch (Throwable unused2) {
                    i = -1;
                }
                if (i != -1) {
                    return Array.get(obj, i);
                }
            }
        }
        throw new NoSuchFieldException("className: " + obj.getClass() + ", fieldName: " + str);
    }

    public static void setInstanceField(Object obj, String str, Object obj2) throws Throwable {
        try {
            m11137a(obj, str, obj2);
        } catch (Throwable th) {
            if (th instanceof NoSuchFieldException) {
                throw th;
            }
            throw new Throwable("className: " + obj.getClass() + ", fieldName: " + str + ", value: " + String.valueOf(obj2), th);
        }
    }

    /* renamed from: a */
    private static void m11137a(Object obj, String str, Object obj2) throws Throwable {
        if ((obj instanceof List) || obj.getClass().isArray()) {
            m11127b(obj, str, obj2);
        } else if (obj instanceof Map) {
            ((Map) obj).put(str, obj2);
        } else {
            ArrayList arrayList = new ArrayList();
            for (Class<?> cls = obj.getClass(); cls != null; cls = cls.getSuperclass()) {
                arrayList.add(cls);
            }
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                Field field = null;
                try {
                    field = ((Class) it.next()).getDeclaredField(str);
                } catch (Throwable unused) {
                }
                if (field != null && !Modifier.isStatic(field.getModifiers())) {
                    field.setAccessible(true);
                    field.set(obj, obj2);
                    return;
                }
            }
            throw new NoSuchFieldException("className: " + obj.getClass() + ", fieldName: " + str + ", value: " + String.valueOf(obj2));
        }
    }

    /* renamed from: b */
    private static void m11127b(Object obj, String str, Object obj2) throws Throwable {
        int i;
        Object valueOf;
        Object valueOf2;
        Object valueOf3;
        Object valueOf4;
        Object valueOf5;
        int i2;
        if (obj instanceof List) {
            if (str.startsWith("[") && str.endsWith("]")) {
                try {
                    i2 = Integer.parseInt(str.substring(1, str.length() - 1));
                } catch (Throwable unused) {
                    i2 = -1;
                }
                if (i2 != -1) {
                    ((List) obj).set(i2, obj2);
                    return;
                }
            }
        } else if (str.startsWith("[") && str.endsWith("]")) {
            try {
                i = Integer.parseInt(str.substring(1, str.length() - 1));
            } catch (Throwable unused2) {
                i = -1;
            }
            if (i != -1) {
                String name = obj.getClass().getName();
                while (name.startsWith("[")) {
                    name = name.substring(1);
                }
                Class<?> cls = obj2.getClass();
                if ("B".equals(name)) {
                    if (cls == Byte.class) {
                        Array.set(obj, i, obj2);
                        return;
                    }
                } else if ("S".equals(name)) {
                    if (cls == Short.class) {
                        valueOf5 = obj2;
                    } else {
                        valueOf5 = cls == Byte.class ? Short.valueOf(((Byte) obj2).byteValue()) : null;
                    }
                    if (valueOf5 != null) {
                        Array.set(obj, i, valueOf5);
                        return;
                    }
                } else if ("I".equals(name)) {
                    if (cls == Integer.class) {
                        valueOf4 = obj2;
                    } else if (cls == Short.class) {
                        valueOf4 = Integer.valueOf(((Short) obj2).shortValue());
                    } else {
                        valueOf4 = cls == Byte.class ? Integer.valueOf(((Byte) obj2).byteValue()) : null;
                    }
                    if (valueOf4 != null) {
                        Array.set(obj, i, valueOf4);
                        return;
                    }
                } else if ("J".equals(name)) {
                    if (cls == Long.class) {
                        valueOf3 = obj2;
                    } else if (cls == Integer.class) {
                        valueOf3 = Long.valueOf(((Integer) obj2).intValue());
                    } else if (cls == Short.class) {
                        valueOf3 = Long.valueOf(((Short) obj2).shortValue());
                    } else {
                        valueOf3 = cls == Byte.class ? Long.valueOf(((Byte) obj2).byteValue()) : null;
                    }
                    if (valueOf3 != null) {
                        Array.set(obj, i, valueOf3);
                        return;
                    }
                } else if ("F".equals(name)) {
                    if (cls == Float.class) {
                        valueOf2 = obj2;
                    } else if (cls == Long.class) {
                        valueOf2 = Float.valueOf((float) ((Long) obj2).longValue());
                    } else if (cls == Integer.class) {
                        valueOf2 = Float.valueOf(((Integer) obj2).intValue());
                    } else if (cls == Short.class) {
                        valueOf2 = Float.valueOf(((Short) obj2).shortValue());
                    } else {
                        valueOf2 = cls == Byte.class ? Float.valueOf(((Byte) obj2).byteValue()) : null;
                    }
                    if (valueOf2 != null) {
                        Array.set(obj, i, valueOf2);
                        return;
                    }
                } else if ("D".equals(name)) {
                    if (cls == Double.class) {
                        valueOf = obj2;
                    } else if (cls == Float.class) {
                        valueOf = Double.valueOf(((Float) obj2).floatValue());
                    } else if (cls == Long.class) {
                        valueOf = Double.valueOf(((Long) obj2).longValue());
                    } else if (cls == Integer.class) {
                        valueOf = Double.valueOf(((Integer) obj2).intValue());
                    } else if (cls == Short.class) {
                        valueOf = Double.valueOf(((Short) obj2).shortValue());
                    } else {
                        valueOf = cls == Byte.class ? Double.valueOf(((Byte) obj2).byteValue()) : null;
                    }
                    if (valueOf != null) {
                        Array.set(obj, i, valueOf);
                        return;
                    }
                } else if ("Z".equals(name)) {
                    if (cls == Boolean.class) {
                        Array.set(obj, i, obj2);
                        return;
                    }
                } else if ("C".equals(name)) {
                    if (cls == Character.class) {
                        Array.set(obj, i, obj2);
                        return;
                    }
                } else if (name.equals(cls.getName())) {
                    Array.set(obj, i, obj2);
                    return;
                }
            }
        }
        throw new NoSuchFieldException("className: " + obj.getClass() + ", fieldName: " + str + ", value: " + String.valueOf(obj2));
    }

    public static Class<?> getClass(String str) throws Throwable {
        Class<?> m11136a = m11136a(str);
        if (m11136a == null) {
            try {
                m11136a = Class.forName(str);
                if (m11136a != null) {
                    f15259b.put(str, m11136a);
                }
            } catch (Throwable unused) {
            }
        }
        return m11136a;
    }

    public static String getName(Class<?> cls) throws Throwable {
        String str = f15260c.get(cls);
        if (str == null) {
            str = cls.getSimpleName();
            if (f15259b.containsKey(str)) {
                f15260c.remove(f15259b.get(str));
            }
            f15259b.put(str, cls);
            f15260c.put(cls, str);
        }
        return str;
    }

    public static Object createProxy(HashMap<String, InterfaceC6184a<Object, Object[]>> hashMap, Class<?>... clsArr) throws Throwable {
        HashMap hashMap2 = new HashMap();
        for (final Map.Entry<String, InterfaceC6184a<Object, Object[]>> entry : hashMap.entrySet()) {
            hashMap2.put(entry.getKey(), new InterfaceC6184a<Object[], Object>() { // from class: com.mob.tools.utils.ReflectHelper.3
                @Override // com.mob.tools.utils.ReflectHelper.InterfaceC6184a
                /* renamed from: a  reason: avoid collision after fix types in other method */
                public Object mo11056a(Object[] objArr) {
                    return ((Object[]) ((InterfaceC6184a) entry.getValue()).mo11056a(objArr))[0];
                }
            });
        }
        return createProxy((Map<String, InterfaceC6184a<Object[], Object>>) hashMap2, clsArr);
    }

    public static Object createProxy(final Map<String, InterfaceC6184a<Object[], Object>> map, Class<?>... clsArr) throws Throwable {
        if (clsArr.length == 0) {
            return null;
        }
        return Proxy.newProxyInstance(clsArr[0].getClassLoader(), clsArr, new InvocationHandler() { // from class: com.mob.tools.utils.ReflectHelper.4
            @Override // java.lang.reflect.InvocationHandler
            public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
                InterfaceC6184a interfaceC6184a = (InterfaceC6184a) map.get(method.getName());
                if (interfaceC6184a != null) {
                    return interfaceC6184a.mo11056a(objArr);
                }
                return null;
            }
        });
    }
}
