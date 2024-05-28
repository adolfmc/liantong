package com.mob.tools.utils;

import com.mob.commons.C5855l;
import com.mob.tools.MobLog;
import com.mob.tools.proguard.PublicMemberKeeper;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex */
public class HashonHelper implements PublicMemberKeeper {

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.mob.tools.utils.HashonHelper$a */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface InterfaceC6162a {
        /* renamed from: a */
        Object m11264a();
    }

    public static <T> HashMap<String, T> fromJson(String str) {
        if (str == null || str.isEmpty()) {
            return new HashMap<>();
        }
        try {
            if (str.startsWith("[") && str.endsWith("]")) {
                str = "{\"fakelist\":" + str + "}";
            }
            return m11267a(new JSONObject(str));
        } catch (Throwable th) {
            MobLog.getInstance().m11326w(str);
            MobLog.getInstance().m11325w(th);
            return new HashMap<>();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: a */
    private static <T> HashMap<String, T> m11267a(JSONObject jSONObject) throws Throwable {
        HashMap<String, T> hashMap = (HashMap<String, T>) new HashMap();
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            Object opt = jSONObject.opt(next);
            if (JSONObject.NULL.equals(opt)) {
                opt = null;
            }
            if (opt != null) {
                if (opt instanceof JSONObject) {
                    opt = m11267a((JSONObject) opt);
                } else if (opt instanceof JSONArray) {
                    opt = m11268a((JSONArray) opt);
                }
                hashMap.put(next, opt);
            }
        }
        return hashMap;
    }

    /* renamed from: a */
    private static ArrayList<Object> m11268a(JSONArray jSONArray) throws Throwable {
        ArrayList<Object> arrayList = new ArrayList<>();
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            Object opt = jSONArray.opt(i);
            if (opt instanceof JSONObject) {
                opt = m11267a((JSONObject) opt);
            } else if (opt instanceof JSONArray) {
                opt = m11268a((JSONArray) opt);
            }
            arrayList.add(opt);
        }
        return arrayList;
    }

    public static <T> String fromHashMap(HashMap<String, T> hashMap) {
        try {
            JSONObject m11269a = m11269a((HashMap) hashMap);
            return m11269a == null ? "" : !(m11269a instanceof JSONObject) ? m11269a.toString() : NBSJSONObjectInstrumentation.toString(m11269a);
        } catch (Throwable th) {
            MobLog.getInstance().m11325w(th);
            return "";
        }
    }

    /* renamed from: a */
    private static <T> JSONObject m11269a(HashMap<String, T> hashMap) throws Throwable {
        JSONObject jSONObject = new JSONObject();
        for (Map.Entry<String, T> entry : hashMap.entrySet()) {
            Object value = entry.getValue();
            if (value instanceof HashMap) {
                value = m11269a((HashMap) value);
            } else if (value instanceof ArrayList) {
                value = m11270a((ArrayList<Object>) value);
            } else if (m11274a(value)) {
                value = m11270a((ArrayList<Object>) m11266b(value));
            }
            jSONObject.put(entry.getKey(), value);
        }
        return jSONObject;
    }

    /* renamed from: a */
    private static boolean m11274a(Object obj) {
        return (obj instanceof byte[]) || (obj instanceof short[]) || (obj instanceof int[]) || (obj instanceof long[]) || (obj instanceof float[]) || (obj instanceof double[]) || (obj instanceof char[]) || (obj instanceof boolean[]) || (obj instanceof String[]);
    }

    /* renamed from: b */
    private static ArrayList<?> m11266b(Object obj) {
        int i = 0;
        if (obj instanceof byte[]) {
            ArrayList<?> arrayList = new ArrayList<>();
            byte[] bArr = (byte[]) obj;
            int length = bArr.length;
            while (i < length) {
                arrayList.add(Byte.valueOf(bArr[i]));
                i++;
            }
            return arrayList;
        } else if (obj instanceof short[]) {
            ArrayList<?> arrayList2 = new ArrayList<>();
            short[] sArr = (short[]) obj;
            int length2 = sArr.length;
            while (i < length2) {
                arrayList2.add(Short.valueOf(sArr[i]));
                i++;
            }
            return arrayList2;
        } else if (obj instanceof int[]) {
            ArrayList<?> arrayList3 = new ArrayList<>();
            int[] iArr = (int[]) obj;
            int length3 = iArr.length;
            while (i < length3) {
                arrayList3.add(Integer.valueOf(iArr[i]));
                i++;
            }
            return arrayList3;
        } else if (obj instanceof long[]) {
            ArrayList<?> arrayList4 = new ArrayList<>();
            long[] jArr = (long[]) obj;
            int length4 = jArr.length;
            while (i < length4) {
                arrayList4.add(Long.valueOf(jArr[i]));
                i++;
            }
            return arrayList4;
        } else if (obj instanceof float[]) {
            ArrayList<?> arrayList5 = new ArrayList<>();
            float[] fArr = (float[]) obj;
            int length5 = fArr.length;
            while (i < length5) {
                arrayList5.add(Float.valueOf(fArr[i]));
                i++;
            }
            return arrayList5;
        } else if (obj instanceof double[]) {
            ArrayList<?> arrayList6 = new ArrayList<>();
            double[] dArr = (double[]) obj;
            int length6 = dArr.length;
            while (i < length6) {
                arrayList6.add(Double.valueOf(dArr[i]));
                i++;
            }
            return arrayList6;
        } else if (obj instanceof char[]) {
            ArrayList<?> arrayList7 = new ArrayList<>();
            char[] cArr = (char[]) obj;
            int length7 = cArr.length;
            while (i < length7) {
                arrayList7.add(Character.valueOf(cArr[i]));
                i++;
            }
            return arrayList7;
        } else if (obj instanceof boolean[]) {
            ArrayList<?> arrayList8 = new ArrayList<>();
            boolean[] zArr = (boolean[]) obj;
            int length8 = zArr.length;
            while (i < length8) {
                arrayList8.add(Boolean.valueOf(zArr[i]));
                i++;
            }
            return arrayList8;
        } else if (obj instanceof String[]) {
            return new ArrayList<>(Arrays.asList((String[]) obj));
        } else {
            return null;
        }
    }

    /* renamed from: a */
    private static JSONArray m11270a(ArrayList<Object> arrayList) throws Throwable {
        JSONArray jSONArray = new JSONArray();
        Iterator<Object> it = arrayList.iterator();
        while (it.hasNext()) {
            Object next = it.next();
            if (next instanceof HashMap) {
                next = m11269a((HashMap) next);
            } else if (next instanceof ArrayList) {
                next = m11270a((ArrayList<Object>) next);
            }
            jSONArray.put(next);
        }
        return jSONArray;
    }

    public static String format(String str) {
        try {
            return m11271a("", fromJson(str));
        } catch (Throwable th) {
            MobLog.getInstance().m11325w(th);
            return "";
        }
    }

    /* renamed from: a */
    private static String m11271a(String str, HashMap<String, Object> hashMap) {
        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        String str2 = str + "\t";
        int i = 0;
        for (Map.Entry<String, Object> entry : hashMap.entrySet()) {
            if (i > 0) {
                sb.append(",\n");
            }
            sb.append(str2);
            sb.append('\"');
            sb.append(entry.getKey());
            sb.append("\":");
            Object value = entry.getValue();
            if (value instanceof HashMap) {
                sb.append(m11271a(str2, (HashMap) value));
            } else if (value instanceof ArrayList) {
                sb.append(m11272a(str2, (ArrayList) value));
            } else if (value instanceof String) {
                sb.append('\"');
                sb.append(value);
                sb.append('\"');
            } else {
                sb.append(value);
            }
            i++;
        }
        sb.append('\n');
        sb.append(str);
        sb.append('}');
        return sb.toString();
    }

    /* renamed from: a */
    private static String m11272a(String str, ArrayList<Object> arrayList) {
        StringBuilder sb = new StringBuilder();
        sb.append("[\n");
        String str2 = str + "\t";
        Iterator<Object> it = arrayList.iterator();
        int i = 0;
        while (it.hasNext()) {
            Object next = it.next();
            if (i > 0) {
                sb.append(",\n");
            }
            sb.append(str2);
            if (next instanceof HashMap) {
                sb.append(m11271a(str2, (HashMap) next));
            } else if (next instanceof ArrayList) {
                sb.append(m11272a(str2, (ArrayList) next));
            } else if (next instanceof String) {
                sb.append('\"');
                sb.append(next);
                sb.append('\"');
            } else {
                sb.append(next);
            }
            i++;
        }
        sb.append('\n');
        sb.append(str);
        sb.append(']');
        return sb.toString();
    }

    public static String fromObject(Object obj) {
        Object obj2;
        try {
            obj2 = m11265c(obj);
        } catch (Throwable th) {
            MobLog.getInstance().m11325w(th);
            obj2 = null;
        }
        if (obj2 == null) {
            return "";
        }
        if (obj2 instanceof ArrayList) {
            HashMap hashMap = new HashMap();
            hashMap.put(C5855l.m12238a("004i@fkhjPk"), obj2);
            String fromHashMap = fromHashMap(hashMap);
            return fromHashMap.substring(8, fromHashMap.length() - 1).trim();
        }
        return fromHashMap((HashMap) obj2);
    }

    /* renamed from: c */
    private static Object m11265c(Object obj) throws Throwable {
        Field[] declaredFields;
        if (obj == null || obj.getClass().isPrimitive() || (obj instanceof String) || (obj instanceof Number) || (obj instanceof Character) || (obj instanceof Boolean)) {
            return obj;
        }
        if (obj instanceof InterfaceC6162a) {
            return m11265c(((InterfaceC6162a) obj).m11264a());
        }
        if (obj instanceof Enum) {
            HashMap hashMap = new HashMap();
            hashMap.put(C5855l.m12238a("004hgIfifh"), ((Enum) obj).name());
            return hashMap;
        }
        if (obj.getClass().isArray()) {
            ArrayList arrayList = new ArrayList();
            int length = Array.getLength(obj);
            for (int i = 0; i < length; i++) {
                arrayList.add(m11265c(Array.get(obj, i)));
            }
            return arrayList;
        } else if (obj instanceof Collection) {
            ArrayList arrayList2 = new ArrayList();
            for (Object obj2 : (Collection) obj) {
                arrayList2.add(m11265c(obj2));
            }
            return arrayList2;
        } else if (obj instanceof Map) {
            HashMap hashMap2 = new HashMap();
            for (Map.Entry entry : ((Map) obj).entrySet()) {
                Object key = entry.getKey();
                if (key instanceof String) {
                    hashMap2.put((String) key, m11265c(entry.getValue()));
                }
            }
            return hashMap2;
        } else {
            ArrayList arrayList3 = new ArrayList();
            for (Class<?> cls = obj.getClass(); !cls.equals(Object.class); cls = cls.getSuperclass()) {
                arrayList3.add(0, cls);
            }
            ArrayList arrayList4 = new ArrayList();
            Iterator it = arrayList3.iterator();
            while (it.hasNext()) {
                for (Field field : ((Class) it.next()).getDeclaredFields()) {
                    if (!Modifier.isStatic(field.getModifiers()) && !field.getName().contains("$")) {
                        arrayList4.add(field);
                    }
                }
            }
            HashMap hashMap3 = new HashMap();
            Iterator it2 = arrayList4.iterator();
            while (it2.hasNext()) {
                Field field2 = (Field) it2.next();
                field2.setAccessible(true);
                hashMap3.put(field2.getName(), m11265c(field2.get(obj)));
            }
            return hashMap3;
        }
    }

    public static <T> T fromJson(String str, Class<T> cls) {
        HashMap fromJson = fromJson(str);
        Object obj = fromJson;
        if (str.startsWith("[")) {
            obj = fromJson;
            if (str.endsWith("]")) {
                obj = fromJson.get(C5855l.m12238a("008YghDf7fnShi7fkhj6k"));
            }
        }
        try {
            Type genericSuperclass = cls.getGenericSuperclass();
            return (T) m11273a(obj, cls, genericSuperclass instanceof ParameterizedType ? ((ParameterizedType) genericSuperclass).getActualTypeArguments() : null);
        } catch (Throwable th) {
            MobLog.getInstance().m11325w(th);
            return null;
        }
    }

    /* JADX WARN: Type inference failed for: r9v11, types: [T, java.util.Collection] */
    /* JADX WARN: Type inference failed for: r9v9, types: [T, java.util.Map] */
    /* renamed from: a */
    private static <T> T m11273a(Object obj, Class<T> cls, Type[] typeArr) throws Throwable {
        Field field;
        Type type;
        Type type2;
        Object obj2;
        Object obj3;
        int i = 0;
        if (cls.isPrimitive() || Number.class.isAssignableFrom(cls) || cls.equals(Character.class)) {
            if (cls.equals(Boolean.TYPE) || cls.equals(Boolean.class)) {
                return (T) Boolean.valueOf(C5855l.m12238a("004k flfi:h").equals(String.valueOf(obj)));
            }
            if (cls.equals(Character.TYPE) || cls.equals(Character.class)) {
                return (T) Character.valueOf(String.valueOf(obj).charAt(0));
            }
            if (cls.equals(Byte.TYPE) || cls.equals(Byte.class)) {
                return (T) Byte.valueOf(String.valueOf(obj));
            }
            if (cls.equals(Short.TYPE) || cls.equals(Short.class)) {
                return (T) Short.valueOf(String.valueOf(obj));
            }
            if (cls.equals(Integer.TYPE) || cls.equals(Integer.class)) {
                return (T) Integer.valueOf(String.valueOf(obj));
            }
            if (cls.equals(Long.TYPE) || cls.equals(Long.class)) {
                return (T) Long.valueOf(String.valueOf(obj));
            }
            if (cls.equals(Float.TYPE) || cls.equals(Float.class)) {
                return (T) Float.valueOf(String.valueOf(obj));
            }
            return (T) Double.valueOf(String.valueOf(obj));
        } else if (InterfaceC6162a.class.isAssignableFrom(cls)) {
            try {
                return (T) ReflectHelper.invokeStaticMethod(ReflectHelper.importClass(cls.getName()), C5855l.m12238a("007-ffXfi,fi^h+ingh"), obj);
            } catch (Throwable unused) {
                return null;
            }
        } else if (cls.equals(String.class) || cls.equals(Boolean.class)) {
            return obj;
        } else {
            if (cls.isEnum()) {
                return (T) Enum.valueOf(cls, String.valueOf(((HashMap) obj).get(C5855l.m12238a("004hg fifh"))));
            }
            if (cls.isArray()) {
                ArrayList arrayList = (ArrayList) obj;
                Class<?> componentType = cls.getComponentType();
                T t = (T) Array.newInstance(componentType, arrayList.size());
                int size = arrayList.size();
                while (i < size) {
                    Array.set(t, i, m11273a(arrayList.get(i), componentType, null));
                    i++;
                }
                return t;
            } else if (Collection.class.isAssignableFrom(cls)) {
                ?? r9 = (T) ((Collection) cls.newInstance());
                Type type3 = (typeArr == null || typeArr.length <= 0) ? null : typeArr[0];
                ArrayList arrayList2 = (ArrayList) obj;
                int size2 = arrayList2.size();
                while (i < size2) {
                    if (type3 != null && (type3 instanceof Class) && !type3.equals(Object.class)) {
                        r9.add(m11273a(arrayList2.get(i), (Class) type3, null));
                    } else if (type3 != null && (type3 instanceof ParameterizedType)) {
                        ParameterizedType parameterizedType = (ParameterizedType) type3;
                        r9.add(m11273a(arrayList2.get(i), (Class) parameterizedType.getRawType(), parameterizedType.getActualTypeArguments()));
                    } else {
                        r9.add(arrayList2.get(i));
                    }
                    i++;
                }
                return r9;
            } else if (Map.class.isAssignableFrom(cls)) {
                ?? r92 = (T) ((Map) cls.newInstance());
                if (typeArr == null || typeArr.length <= 1) {
                    type = null;
                    type2 = null;
                } else {
                    type2 = typeArr[0];
                    type = typeArr[1];
                }
                HashMap hashMap = (HashMap) obj;
                for (Object obj4 : hashMap.keySet()) {
                    if (type2 != null && (type2 instanceof Class) && !type.equals(Object.class)) {
                        obj2 = m11273a(obj4, (Class) type2, null);
                    } else if (type2 == null || !(type2 instanceof ParameterizedType)) {
                        obj2 = obj4;
                    } else {
                        ParameterizedType parameterizedType2 = (ParameterizedType) type2;
                        obj2 = m11273a(obj4, (Class) parameterizedType2.getRawType(), parameterizedType2.getActualTypeArguments());
                    }
                    if (type != null && (type instanceof Class) && !type.equals(Object.class)) {
                        obj3 = m11273a(hashMap.get(obj4), (Class) type, null);
                    } else if (type != null && (type instanceof ParameterizedType)) {
                        ParameterizedType parameterizedType3 = (ParameterizedType) type;
                        obj3 = m11273a(hashMap.get(obj4), (Class) parameterizedType3.getRawType(), parameterizedType3.getActualTypeArguments());
                    } else {
                        obj3 = hashMap.get(obj4);
                    }
                    r92.put(obj2, obj3);
                }
                return r92;
            } else {
                ArrayList arrayList3 = new ArrayList();
                for (Class<T> cls2 = cls; !cls2.equals(Object.class); cls2 = cls2.getSuperclass()) {
                    arrayList3.add(cls2);
                }
                HashMap hashMap2 = (HashMap) obj;
                HashMap hashMap3 = new HashMap();
                for (String str : hashMap2.keySet()) {
                    if (hashMap2.get(str) != null) {
                        Iterator it = arrayList3.iterator();
                        while (true) {
                            if (it.hasNext()) {
                                try {
                                    field = ((Class) it.next()).getDeclaredField(str);
                                    continue;
                                } catch (Throwable unused2) {
                                    field = null;
                                    continue;
                                }
                                if (field != null) {
                                    hashMap3.put(str, field);
                                    break;
                                }
                            }
                        }
                    }
                }
                T t2 = (T) ReflectHelper.newInstance(ReflectHelper.getName(cls), new Object[0]);
                for (String str2 : hashMap3.keySet()) {
                    Object obj5 = hashMap2.get(str2);
                    Field field2 = (Field) hashMap3.get(str2);
                    Class<?> type4 = field2.getType();
                    Type genericType = field2.getGenericType();
                    Type[] actualTypeArguments = genericType instanceof ParameterizedType ? ((ParameterizedType) genericType).getActualTypeArguments() : null;
                    field2.setAccessible(true);
                    field2.set(t2, m11273a(obj5, type4, actualTypeArguments));
                }
                return t2;
            }
        }
    }
}
