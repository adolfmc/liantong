package com.mob.commons.p231cc;

import com.mob.commons.C5855l;
import com.mob.commons.p231cc.C5824w;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/* renamed from: com.mob.commons.cc.r */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5813r {

    /* renamed from: a */
    private static final HashMap<String, Class<?>> f14295a = new HashMap<>();

    /* renamed from: b */
    private final HashMap<String, HashMap<String, String[][]>> f14296b = new HashMap<>();

    /* renamed from: c */
    private final HashMap<Class<?>, InterfaceC5812q<?>> f14297c = new HashMap<>();

    static {
        f14295a.put(C5855l.m12238a("003?fk7gk"), Integer.TYPE);
        f14295a.put(C5855l.m12238a("0060fegffihg<ih"), Double.TYPE);
        f14295a.put("long", Long.TYPE);
        f14295a.put(C5855l.m12238a("005Egh i$gf+fk"), Float.TYPE);
        f14295a.put("boolean", Boolean.TYPE);
        f14295a.put("short", Short.TYPE);
        f14295a.put("byte", Byte.TYPE);
        f14295a.put(C5855l.m12238a("004ejfLfl"), Character.TYPE);
        f14295a.put("void", Void.TYPE);
    }

    public C5813r() {
        m12402a(C5824w.C5826a.class, C5824w.C5826a.class);
    }

    /* renamed from: a */
    public void m12397a(byte[] bArr) throws Throwable {
        ArrayList arrayList = new ArrayList();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(bArr), "utf-8"));
        try {
            try {
                HashMap<String, String[][]> hashMap = null;
                for (String readLine = bufferedReader.readLine(); readLine != null; readLine = bufferedReader.readLine()) {
                    String substring = readLine.substring(0, 2);
                    String substring2 = readLine.substring(2);
                    if (":P".equals(substring)) {
                        arrayList.addAll(Arrays.asList(substring2.split("#")));
                    } else if (":C".equals(substring)) {
                        String str = (String) arrayList.get(Integer.parseInt(substring2));
                        hashMap = this.f14296b.get(str);
                        if (hashMap == null) {
                            hashMap = new HashMap<>();
                            this.f14296b.put(str, hashMap);
                        }
                    } else {
                        String[] split = substring2.split("#");
                        String str2 = (String) arrayList.get(Integer.parseInt(split[0]));
                        String[][] strArr = new String[Integer.parseInt(split[1])];
                        for (int i = 2; i < split.length; i++) {
                            String str3 = split[i].startsWith("+") ? "+" : null;
                            if (split[i].length() > 1) {
                                String[] split2 = split[i].substring(1).split(",");
                                String[] strArr2 = new String[split2.length + 1];
                                strArr2[0] = str3;
                                int i2 = 0;
                                while (i2 < split2.length) {
                                    int i3 = i2 + 1;
                                    strArr2[i3] = (String) arrayList.get(Integer.parseInt(split2[i2]));
                                    i2 = i3;
                                }
                                strArr[i - 2] = strArr2;
                            } else {
                                String[] strArr3 = new String[1];
                                strArr3[0] = str3;
                                strArr[i - 2] = strArr3;
                            }
                        }
                        hashMap.put(str2, strArr);
                    }
                }
            } catch (Throwable unused) {
                this.f14296b.clear();
            }
        } finally {
            bufferedReader.close();
        }
    }

    /* renamed from: a */
    public void m12402a(Class<?> cls, Class<? extends InterfaceC5812q<?>> cls2) {
        try {
            InterfaceC5812q<?> newInstance = cls2.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
            if (this.f14297c.get(cls) == null) {
                this.f14297c.put(cls, newInstance);
            }
        } catch (Throwable unused) {
        }
    }

    /* renamed from: a */
    public boolean m12399a(Object obj, Class<?> cls, String str, Object[] objArr, C5810p c5810p) throws Throwable {
        InterfaceC5812q<?> interfaceC5812q = null;
        for (Class<?> cls2 = cls; interfaceC5812q == null && cls2 != null && cls2 != Object.class; cls2 = cls2.getSuperclass()) {
            interfaceC5812q = this.f14297c.get(cls2);
        }
        if (interfaceC5812q != null) {
            boolean[] zArr = new boolean[1];
            Object[] objArr2 = new Object[1];
            Throwable[] thArr = new Throwable[1];
            boolean mo12341a = interfaceC5812q.mo12341a(obj, cls, str, objArr, zArr, objArr2, thArr);
            if (mo12341a) {
                if (thArr[0] != null) {
                    throw thArr[0];
                }
                if (!zArr[0]) {
                    c5810p.m12418a(objArr2[0]);
                }
            }
            return mo12341a;
        }
        return false;
    }

    /* renamed from: a */
    public boolean[] m12396a(Class<?>[] clsArr, Object[] objArr, boolean[] zArr) {
        zArr[0] = true;
        if (clsArr.length == objArr.length) {
            boolean[] zArr2 = new boolean[clsArr.length];
            for (int i = 0; i < objArr.length; i++) {
                Object obj = objArr[i];
                if (obj != null) {
                    Class<?> cls = clsArr[i];
                    if (cls.isInterface() && (obj instanceof C5824w)) {
                        zArr2[i] = true;
                        zArr[0] = false;
                    } else {
                        Class<?> cls2 = obj.getClass();
                        if (!m12395b(cls, cls2) && !cls.isAssignableFrom(cls2)) {
                            return null;
                        }
                        zArr2[i] = false;
                    }
                }
            }
            return zArr2;
        }
        return null;
    }

    /* renamed from: b */
    private boolean m12395b(Class<?> cls, Class<?> cls2) {
        return (cls == Byte.TYPE && cls2 == Byte.class) || (cls == Short.TYPE && (cls2 == Short.class || cls2 == Byte.class || cls2 == Character.class)) || ((cls == Character.TYPE && (cls2 == Character.class || cls2 == Short.class || cls2 == Byte.class)) || ((cls == Integer.TYPE && (cls2 == Integer.class || cls2 == Short.class || cls2 == Byte.class || cls2 == Character.class)) || ((cls == Long.TYPE && (cls2 == Long.class || cls2 == Integer.class || cls2 == Short.class || cls2 == Byte.class || cls2 == Character.class)) || ((cls == Float.TYPE && (cls2 == Float.class || cls2 == Long.class || cls2 == Integer.class || cls2 == Short.class || cls2 == Byte.class || cls2 == Character.class)) || ((cls == Double.TYPE && (cls2 == Double.class || cls2 == Float.class || cls2 == Long.class || cls2 == Integer.class || cls2 == Short.class || cls2 == Byte.class || cls2 == Character.class)) || (cls == Boolean.TYPE && cls2 == Boolean.class))))));
    }

    /* renamed from: a */
    public Object[] m12403a(C5810p c5810p, Class<?>[] clsArr, Object[] objArr, boolean[] zArr) {
        Object[] objArr2 = new Object[zArr.length];
        for (int i = 0; i < zArr.length; i++) {
            if (objArr[i] != null) {
                if (zArr[i]) {
                    objArr2[i] = c5810p.m12417a(objArr[i], true, clsArr[i]);
                } else {
                    objArr2[i] = objArr[i];
                }
            }
        }
        return objArr2;
    }

    /* renamed from: a */
    public Constructor m12400a(Class<?> cls, Object[] objArr, boolean[][] zArr) throws Throwable {
        String[][] strArr;
        boolean z;
        boolean[] zArr2;
        boolean[] m12396a;
        HashMap<String, String[][]> hashMap = this.f14296b.get(cls.getName());
        if (hashMap == null || (strArr = hashMap.get("<init>")) == null) {
            return null;
        }
        for (String[] strArr2 : strArr) {
            if (strArr2.length - 1 == objArr.length) {
                Class<?>[] clsArr = new Class[objArr.length];
                int i = 0;
                while (true) {
                    if (i >= clsArr.length) {
                        z = false;
                        break;
                    }
                    int i2 = i + 1;
                    clsArr[i] = m12398a(strArr2[i2]);
                    if (clsArr[i] == null) {
                        z = true;
                        break;
                    }
                    i = i2;
                }
                if (!z && (m12396a = m12396a(clsArr, objArr, (zArr2 = new boolean[1]))) != null) {
                    zArr[0] = m12396a;
                    zArr[1] = zArr2;
                    return cls.getDeclaredConstructor(clsArr);
                }
            }
        }
        return null;
    }

    /* renamed from: a */
    public Method m12401a(Class<?> cls, String str, boolean z, Object[] objArr, boolean[][] zArr) throws Throwable {
        String[][] strArr;
        boolean z2;
        boolean[] zArr2;
        boolean[] m12396a;
        HashMap<String, String[][]> hashMap = this.f14296b.get(cls.getName());
        if (hashMap == null || (strArr = hashMap.get(str)) == null) {
            return null;
        }
        for (String[] strArr2 : strArr) {
            if ((z == (strArr2[0] != null)) && strArr2.length - 1 == objArr.length) {
                Class<?>[] clsArr = new Class[objArr.length];
                int i = 0;
                while (true) {
                    if (i >= clsArr.length) {
                        z2 = false;
                        break;
                    }
                    int i2 = i + 1;
                    clsArr[i] = m12398a(strArr2[i2]);
                    if (clsArr[i] == null) {
                        z2 = true;
                        break;
                    }
                    i = i2;
                }
                if (!z2 && (m12396a = m12396a(clsArr, objArr, (zArr2 = new boolean[1]))) != null) {
                    zArr[0] = m12396a;
                    zArr[1] = zArr2;
                    return cls.getDeclaredMethod(str, clsArr);
                }
            }
        }
        return null;
    }

    /* renamed from: a */
    private Class<?> m12398a(String str) {
        Class<?> cls = f14295a.get(str);
        if (cls == null) {
            try {
                return Class.forName(str);
            } catch (Throwable unused) {
                return null;
            }
        }
        return cls;
    }
}
