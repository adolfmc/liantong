package org.apache.commons.lang3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang3.mutable.MutableObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class ClassUtils {
    public static final char INNER_CLASS_SEPARATOR_CHAR = '$';
    public static final char PACKAGE_SEPARATOR_CHAR = '.';
    private static final Map<String, String> abbreviationMap;
    private static final Map<Class<?>, Class<?>> primitiveWrapperMap;
    private static final Map<String, String> reverseAbbreviationMap;
    private static final Map<Class<?>, Class<?>> wrapperPrimitiveMap;
    public static final String PACKAGE_SEPARATOR = String.valueOf('.');
    public static final String INNER_CLASS_SEPARATOR = String.valueOf('$');
    private static final Map<String, Class<?>> namePrimitiveMap = new HashMap();

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public enum Interfaces {
        INCLUDE,
        EXCLUDE
    }

    static {
        namePrimitiveMap.put("boolean", Boolean.TYPE);
        namePrimitiveMap.put("byte", Byte.TYPE);
        namePrimitiveMap.put("char", Character.TYPE);
        namePrimitiveMap.put("short", Short.TYPE);
        namePrimitiveMap.put("int", Integer.TYPE);
        namePrimitiveMap.put("long", Long.TYPE);
        namePrimitiveMap.put("double", Double.TYPE);
        namePrimitiveMap.put("float", Float.TYPE);
        namePrimitiveMap.put("void", Void.TYPE);
        primitiveWrapperMap = new HashMap();
        primitiveWrapperMap.put(Boolean.TYPE, Boolean.class);
        primitiveWrapperMap.put(Byte.TYPE, Byte.class);
        primitiveWrapperMap.put(Character.TYPE, Character.class);
        primitiveWrapperMap.put(Short.TYPE, Short.class);
        primitiveWrapperMap.put(Integer.TYPE, Integer.class);
        primitiveWrapperMap.put(Long.TYPE, Long.class);
        primitiveWrapperMap.put(Double.TYPE, Double.class);
        primitiveWrapperMap.put(Float.TYPE, Float.class);
        primitiveWrapperMap.put(Void.TYPE, Void.TYPE);
        wrapperPrimitiveMap = new HashMap();
        for (Map.Entry<Class<?>, Class<?>> entry : primitiveWrapperMap.entrySet()) {
            Class<?> key = entry.getKey();
            Class<?> value = entry.getValue();
            if (!key.equals(value)) {
                wrapperPrimitiveMap.put(value, key);
            }
        }
        HashMap hashMap = new HashMap();
        hashMap.put("int", "I");
        hashMap.put("boolean", "Z");
        hashMap.put("float", "F");
        hashMap.put("long", "J");
        hashMap.put("short", "S");
        hashMap.put("byte", "B");
        hashMap.put("double", "D");
        hashMap.put("char", "C");
        HashMap hashMap2 = new HashMap();
        for (Map.Entry entry2 : hashMap.entrySet()) {
            hashMap2.put(entry2.getValue(), entry2.getKey());
        }
        abbreviationMap = Collections.unmodifiableMap(hashMap);
        reverseAbbreviationMap = Collections.unmodifiableMap(hashMap2);
    }

    public static String getShortClassName(Object obj, String str) {
        return obj == null ? str : getShortClassName(obj.getClass());
    }

    public static String getShortClassName(Class<?> cls) {
        return cls == null ? "" : getShortClassName(cls.getName());
    }

    public static String getShortClassName(String str) {
        if (StringUtils.isEmpty(str)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        if (str.startsWith("[")) {
            while (str.charAt(0) == '[') {
                str = str.substring(1);
                sb.append("[]");
            }
            if (str.charAt(0) == 'L' && str.charAt(str.length() - 1) == ';') {
                str = str.substring(1, str.length() - 1);
            }
            if (reverseAbbreviationMap.containsKey(str)) {
                str = reverseAbbreviationMap.get(str);
            }
        }
        int lastIndexOf = str.lastIndexOf(46);
        int indexOf = str.indexOf(36, lastIndexOf != -1 ? lastIndexOf + 1 : 0);
        String substring = str.substring(lastIndexOf + 1);
        if (indexOf != -1) {
            substring = substring.replace('$', '.');
        }
        return substring + ((Object) sb);
    }

    public static String getSimpleName(Class<?> cls) {
        return getSimpleName(cls, "");
    }

    public static String getSimpleName(Class<?> cls, String str) {
        return cls == null ? str : cls.getSimpleName();
    }

    public static String getSimpleName(Object obj) {
        return getSimpleName(obj, "");
    }

    public static String getSimpleName(Object obj, String str) {
        return obj == null ? str : obj.getClass().getSimpleName();
    }

    public static String getName(Class<?> cls) {
        return getName(cls, "");
    }

    public static String getName(Class<?> cls, String str) {
        return cls == null ? str : cls.getName();
    }

    public static String getName(Object obj) {
        return getName(obj, "");
    }

    public static String getName(Object obj, String str) {
        return obj == null ? str : obj.getClass().getName();
    }

    public static String getPackageName(Object obj, String str) {
        return obj == null ? str : getPackageName(obj.getClass());
    }

    public static String getPackageName(Class<?> cls) {
        return cls == null ? "" : getPackageName(cls.getName());
    }

    public static String getPackageName(String str) {
        if (StringUtils.isEmpty(str)) {
            return "";
        }
        while (str.charAt(0) == '[') {
            str = str.substring(1);
        }
        if (str.charAt(0) == 'L' && str.charAt(str.length() - 1) == ';') {
            str = str.substring(1);
        }
        int lastIndexOf = str.lastIndexOf(46);
        return lastIndexOf == -1 ? "" : str.substring(0, lastIndexOf);
    }

    public static String getAbbreviatedName(Class<?> cls, int i) {
        return cls == null ? "" : getAbbreviatedName(cls.getName(), i);
    }

    public static String getAbbreviatedName(String str, int i) {
        if (i > 0) {
            if (str == null) {
                return "";
            }
            int countMatches = StringUtils.countMatches((CharSequence) str, '.');
            String[] strArr = new String[countMatches + 1];
            int length = str.length() - 1;
            int i2 = i;
            for (int i3 = countMatches; i3 >= 0; i3--) {
                int lastIndexOf = str.lastIndexOf(46, length);
                String substring = str.substring(lastIndexOf + 1, length + 1);
                i2 -= substring.length();
                if (i3 > 0) {
                    i2--;
                }
                if (i3 == countMatches) {
                    strArr[i3] = substring;
                } else if (i2 > 0) {
                    strArr[i3] = substring;
                } else {
                    strArr[i3] = substring.substring(0, 1);
                }
                length = lastIndexOf - 1;
            }
            return StringUtils.join((Object[]) strArr, '.');
        }
        throw new IllegalArgumentException("len must be > 0");
    }

    public static List<Class<?>> getAllSuperclasses(Class<?> cls) {
        if (cls == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (Class<? super Object> superclass = cls.getSuperclass(); superclass != null; superclass = superclass.getSuperclass()) {
            arrayList.add(superclass);
        }
        return arrayList;
    }

    public static List<Class<?>> getAllInterfaces(Class<?> cls) {
        if (cls == null) {
            return null;
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        getAllInterfaces(cls, linkedHashSet);
        return new ArrayList(linkedHashSet);
    }

    private static void getAllInterfaces(Class<?> cls, HashSet<Class<?>> hashSet) {
        Class<?>[] interfaces;
        while (cls != null) {
            for (Class<?> cls2 : cls.getInterfaces()) {
                if (hashSet.add(cls2)) {
                    getAllInterfaces(cls2, hashSet);
                }
            }
            cls = cls.getSuperclass();
        }
    }

    public static List<Class<?>> convertClassNamesToClasses(List<String> list) {
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(list.size());
        for (String str : list) {
            try {
                arrayList.add(Class.forName(str));
            } catch (Exception unused) {
                arrayList.add(null);
            }
        }
        return arrayList;
    }

    public static List<String> convertClassesToClassNames(List<Class<?>> list) {
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(list.size());
        for (Class<?> cls : list) {
            if (cls == null) {
                arrayList.add(null);
            } else {
                arrayList.add(cls.getName());
            }
        }
        return arrayList;
    }

    public static boolean isAssignable(Class<?>[] clsArr, Class<?>... clsArr2) {
        return isAssignable(clsArr, clsArr2, SystemUtils.isJavaVersionAtLeast(JavaVersion.JAVA_1_5));
    }

    public static boolean isAssignable(Class<?>[] clsArr, Class<?>[] clsArr2, boolean z) {
        if (ArrayUtils.isSameLength(clsArr, clsArr2)) {
            if (clsArr == null) {
                clsArr = ArrayUtils.EMPTY_CLASS_ARRAY;
            }
            if (clsArr2 == null) {
                clsArr2 = ArrayUtils.EMPTY_CLASS_ARRAY;
            }
            for (int i = 0; i < clsArr.length; i++) {
                if (!isAssignable(clsArr[i], clsArr2[i], z)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public static boolean isPrimitiveOrWrapper(Class<?> cls) {
        if (cls == null) {
            return false;
        }
        return cls.isPrimitive() || isPrimitiveWrapper(cls);
    }

    public static boolean isPrimitiveWrapper(Class<?> cls) {
        return wrapperPrimitiveMap.containsKey(cls);
    }

    public static boolean isAssignable(Class<?> cls, Class<?> cls2) {
        return isAssignable(cls, cls2, SystemUtils.isJavaVersionAtLeast(JavaVersion.JAVA_1_5));
    }

    public static boolean isAssignable(Class<?> cls, Class<?> cls2, boolean z) {
        if (cls2 == null) {
            return false;
        }
        if (cls == null) {
            return !cls2.isPrimitive();
        }
        if (z) {
            if (cls.isPrimitive() && !cls2.isPrimitive() && (cls = primitiveToWrapper(cls)) == null) {
                return false;
            }
            if (cls2.isPrimitive() && !cls.isPrimitive() && (cls = wrapperToPrimitive(cls)) == null) {
                return false;
            }
        }
        if (cls.equals(cls2)) {
            return true;
        }
        if (cls.isPrimitive()) {
            if (cls2.isPrimitive()) {
                if (Integer.TYPE.equals(cls)) {
                    return Long.TYPE.equals(cls2) || Float.TYPE.equals(cls2) || Double.TYPE.equals(cls2);
                } else if (Long.TYPE.equals(cls)) {
                    return Float.TYPE.equals(cls2) || Double.TYPE.equals(cls2);
                } else if (Boolean.TYPE.equals(cls) || Double.TYPE.equals(cls)) {
                    return false;
                } else {
                    if (Float.TYPE.equals(cls)) {
                        return Double.TYPE.equals(cls2);
                    }
                    if (Character.TYPE.equals(cls)) {
                        return Integer.TYPE.equals(cls2) || Long.TYPE.equals(cls2) || Float.TYPE.equals(cls2) || Double.TYPE.equals(cls2);
                    } else if (Short.TYPE.equals(cls)) {
                        return Integer.TYPE.equals(cls2) || Long.TYPE.equals(cls2) || Float.TYPE.equals(cls2) || Double.TYPE.equals(cls2);
                    } else if (Byte.TYPE.equals(cls)) {
                        return Short.TYPE.equals(cls2) || Integer.TYPE.equals(cls2) || Long.TYPE.equals(cls2) || Float.TYPE.equals(cls2) || Double.TYPE.equals(cls2);
                    } else {
                        return false;
                    }
                }
            }
            return false;
        }
        return cls2.isAssignableFrom(cls);
    }

    public static Class<?> primitiveToWrapper(Class<?> cls) {
        return (cls == null || !cls.isPrimitive()) ? cls : primitiveWrapperMap.get(cls);
    }

    public static Class<?>[] primitivesToWrappers(Class<?>... clsArr) {
        if (clsArr == null) {
            return null;
        }
        if (clsArr.length == 0) {
            return clsArr;
        }
        Class<?>[] clsArr2 = new Class[clsArr.length];
        for (int i = 0; i < clsArr.length; i++) {
            clsArr2[i] = primitiveToWrapper(clsArr[i]);
        }
        return clsArr2;
    }

    public static Class<?> wrapperToPrimitive(Class<?> cls) {
        return wrapperPrimitiveMap.get(cls);
    }

    public static Class<?>[] wrappersToPrimitives(Class<?>... clsArr) {
        if (clsArr == null) {
            return null;
        }
        if (clsArr.length == 0) {
            return clsArr;
        }
        Class<?>[] clsArr2 = new Class[clsArr.length];
        for (int i = 0; i < clsArr.length; i++) {
            clsArr2[i] = wrapperToPrimitive(clsArr[i]);
        }
        return clsArr2;
    }

    public static boolean isInnerClass(Class<?> cls) {
        return (cls == null || cls.getEnclosingClass() == null) ? false : true;
    }

    public static Class<?> getClass(ClassLoader classLoader, String str, boolean z) throws ClassNotFoundException {
        try {
            if (namePrimitiveMap.containsKey(str)) {
                return namePrimitiveMap.get(str);
            }
            return Class.forName(toCanonicalName(str), z, classLoader);
        } catch (ClassNotFoundException e) {
            int lastIndexOf = str.lastIndexOf(46);
            if (lastIndexOf != -1) {
                try {
                    return getClass(classLoader, str.substring(0, lastIndexOf) + '$' + str.substring(lastIndexOf + 1), z);
                } catch (ClassNotFoundException unused) {
                    throw e;
                }
            }
            throw e;
        }
    }

    public static Class<?> getClass(ClassLoader classLoader, String str) throws ClassNotFoundException {
        return getClass(classLoader, str, true);
    }

    public static Class<?> getClass(String str) throws ClassNotFoundException {
        return getClass(str, true);
    }

    public static Class<?> getClass(String str, boolean z) throws ClassNotFoundException {
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        if (contextClassLoader == null) {
            contextClassLoader = ClassUtils.class.getClassLoader();
        }
        return getClass(contextClassLoader, str, z);
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x0030  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.reflect.Method getPublicMethod(java.lang.Class<?> r2, java.lang.String r3, java.lang.Class<?>... r4) throws java.lang.SecurityException, java.lang.NoSuchMethodException {
        /*
            java.lang.reflect.Method r0 = r2.getMethod(r3, r4)
            java.lang.Class r1 = r0.getDeclaringClass()
            int r1 = r1.getModifiers()
            boolean r1 = java.lang.reflect.Modifier.isPublic(r1)
            if (r1 == 0) goto L13
            return r0
        L13:
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.util.List r1 = getAllInterfaces(r2)
            r0.addAll(r1)
            java.util.List r2 = getAllSuperclasses(r2)
            r0.addAll(r2)
            java.util.Iterator r2 = r0.iterator()
        L2a:
            boolean r0 = r2.hasNext()
            if (r0 == 0) goto L54
            java.lang.Object r0 = r2.next()
            java.lang.Class r0 = (java.lang.Class) r0
            int r1 = r0.getModifiers()
            boolean r1 = java.lang.reflect.Modifier.isPublic(r1)
            if (r1 != 0) goto L41
            goto L2a
        L41:
            java.lang.reflect.Method r0 = r0.getMethod(r3, r4)     // Catch: java.lang.NoSuchMethodException -> L2a
            java.lang.Class r1 = r0.getDeclaringClass()
            int r1 = r1.getModifiers()
            boolean r1 = java.lang.reflect.Modifier.isPublic(r1)
            if (r1 == 0) goto L2a
            return r0
        L54:
            java.lang.NoSuchMethodException r2 = new java.lang.NoSuchMethodException
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Can't find a public method for "
            r0.append(r1)
            r0.append(r3)
            java.lang.String r3 = " "
            r0.append(r3)
            java.lang.String r3 = org.apache.commons.lang3.ArrayUtils.toString(r4)
            r0.append(r3)
            java.lang.String r3 = r0.toString()
            r2.<init>(r3)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.lang3.ClassUtils.getPublicMethod(java.lang.Class, java.lang.String, java.lang.Class[]):java.lang.reflect.Method");
    }

    private static String toCanonicalName(String str) {
        String deleteWhitespace = StringUtils.deleteWhitespace(str);
        Validate.notNull(deleteWhitespace, "className must not be null.", new Object[0]);
        if (deleteWhitespace.endsWith("[]")) {
            StringBuilder sb = new StringBuilder();
            while (deleteWhitespace.endsWith("[]")) {
                deleteWhitespace = deleteWhitespace.substring(0, deleteWhitespace.length() - 2);
                sb.append("[");
            }
            String str2 = abbreviationMap.get(deleteWhitespace);
            if (str2 != null) {
                sb.append(str2);
            } else {
                sb.append("L");
                sb.append(deleteWhitespace);
                sb.append(";");
            }
            return sb.toString();
        }
        return deleteWhitespace;
    }

    public static Class<?>[] toClass(Object... objArr) {
        if (objArr == null) {
            return null;
        }
        if (objArr.length == 0) {
            return ArrayUtils.EMPTY_CLASS_ARRAY;
        }
        Class<?>[] clsArr = new Class[objArr.length];
        for (int i = 0; i < objArr.length; i++) {
            clsArr[i] = objArr[i] == null ? null : objArr[i].getClass();
        }
        return clsArr;
    }

    public static String getShortCanonicalName(Object obj, String str) {
        return obj == null ? str : getShortCanonicalName(obj.getClass().getName());
    }

    public static String getCanonicalName(Class<?> cls) {
        return getCanonicalName(cls, "");
    }

    public static String getCanonicalName(Class<?> cls, String str) {
        String canonicalName;
        return (cls == null || (canonicalName = cls.getCanonicalName()) == null) ? str : canonicalName;
    }

    public static String getCanonicalName(Object obj) {
        return getCanonicalName(obj, "");
    }

    public static String getCanonicalName(Object obj, String str) {
        String canonicalName;
        return (obj == null || (canonicalName = obj.getClass().getCanonicalName()) == null) ? str : canonicalName;
    }

    public static String getShortCanonicalName(Class<?> cls) {
        return cls == null ? "" : getShortCanonicalName(cls.getName());
    }

    public static String getShortCanonicalName(String str) {
        return getShortClassName(getCanonicalName(str));
    }

    public static String getPackageCanonicalName(Object obj, String str) {
        return obj == null ? str : getPackageCanonicalName(obj.getClass().getName());
    }

    public static String getPackageCanonicalName(Class<?> cls) {
        return cls == null ? "" : getPackageCanonicalName(cls.getName());
    }

    public static String getPackageCanonicalName(String str) {
        return getPackageName(getCanonicalName(str));
    }

    private static String getCanonicalName(String str) {
        String deleteWhitespace = StringUtils.deleteWhitespace(str);
        if (deleteWhitespace == null) {
            return null;
        }
        int i = 0;
        while (deleteWhitespace.startsWith("[")) {
            i++;
            deleteWhitespace = deleteWhitespace.substring(1);
        }
        if (i < 1) {
            return deleteWhitespace;
        }
        if (deleteWhitespace.startsWith("L")) {
            deleteWhitespace = deleteWhitespace.substring(1, deleteWhitespace.endsWith(";") ? deleteWhitespace.length() - 1 : deleteWhitespace.length());
        } else if (deleteWhitespace.length() > 0) {
            deleteWhitespace = reverseAbbreviationMap.get(deleteWhitespace.substring(0, 1));
        }
        StringBuilder sb = new StringBuilder(deleteWhitespace);
        for (int i2 = 0; i2 < i; i2++) {
            sb.append("[]");
        }
        return sb.toString();
    }

    public static Iterable<Class<?>> hierarchy(Class<?> cls) {
        return hierarchy(cls, Interfaces.EXCLUDE);
    }

    public static Iterable<Class<?>> hierarchy(final Class<?> cls, Interfaces interfaces) {
        final Iterable<Class<?>> iterable = new Iterable<Class<?>>() { // from class: org.apache.commons.lang3.ClassUtils.1
            @Override // java.lang.Iterable
            public Iterator<Class<?>> iterator() {
                final MutableObject mutableObject = new MutableObject(cls);
                return new Iterator<Class<?>>() { // from class: org.apache.commons.lang3.ClassUtils.1.1
                    @Override // java.util.Iterator
                    public boolean hasNext() {
                        return mutableObject.getValue() != null;
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // java.util.Iterator
                    public Class<?> next() {
                        Class<?> cls2 = (Class) mutableObject.getValue();
                        mutableObject.setValue(cls2.getSuperclass());
                        return cls2;
                    }

                    @Override // java.util.Iterator
                    public void remove() {
                        throw new UnsupportedOperationException();
                    }
                };
            }
        };
        return interfaces != Interfaces.INCLUDE ? iterable : new Iterable<Class<?>>() { // from class: org.apache.commons.lang3.ClassUtils.2
            @Override // java.lang.Iterable
            public Iterator<Class<?>> iterator() {
                final HashSet hashSet = new HashSet();
                final Iterator it = iterable.iterator();
                return new Iterator<Class<?>>() { // from class: org.apache.commons.lang3.ClassUtils.2.1
                    Iterator<Class<?>> interfaces = Collections.emptySet().iterator();

                    @Override // java.util.Iterator
                    public boolean hasNext() {
                        return this.interfaces.hasNext() || it.hasNext();
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // java.util.Iterator
                    public Class<?> next() {
                        if (this.interfaces.hasNext()) {
                            Class<?> next = this.interfaces.next();
                            hashSet.add(next);
                            return next;
                        }
                        Class<?> cls2 = (Class) it.next();
                        LinkedHashSet linkedHashSet = new LinkedHashSet();
                        walkInterfaces(linkedHashSet, cls2);
                        this.interfaces = linkedHashSet.iterator();
                        return cls2;
                    }

                    private void walkInterfaces(Set<Class<?>> set, Class<?> cls2) {
                        Class<?>[] interfaces2;
                        for (Class<?> cls3 : cls2.getInterfaces()) {
                            if (!hashSet.contains(cls3)) {
                                set.add(cls3);
                            }
                            walkInterfaces(set, cls3);
                        }
                    }

                    @Override // java.util.Iterator
                    public void remove() {
                        throw new UnsupportedOperationException();
                    }
                };
            }
        };
    }
}
