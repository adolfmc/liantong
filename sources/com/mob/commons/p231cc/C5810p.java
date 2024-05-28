package com.mob.commons.p231cc;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/* renamed from: com.mob.commons.cc.p */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5810p {

    /* renamed from: a */
    private C5813r f14286a;

    /* renamed from: c */
    private HashMap<String, Object> f14288c;

    /* renamed from: e */
    private C5810p f14290e;

    /* renamed from: f */
    private boolean f14291f;

    /* renamed from: b */
    private LinkedList<Object> f14287b = new LinkedList<>();

    /* renamed from: d */
    private HashMap<String, Class<?>> f14289d = new HashMap<>();

    public C5810p(HashMap<String, Object> hashMap, C5813r c5813r) {
        this.f14286a = c5813r;
        this.f14288c = new HashMap<>(hashMap);
    }

    /* renamed from: a */
    public void m12418a(Object obj) {
        this.f14287b.push(obj);
    }

    /* renamed from: a */
    public Object m12419a() {
        return this.f14287b.pop();
    }

    /* renamed from: a */
    public void m12414a(String str, Object obj) {
        if (this.f14288c.containsKey(str)) {
            throw new RuntimeException("\"" + str + "\" has defined");
        }
        this.f14288c.put(str, obj);
    }

    /* renamed from: b */
    public void m12409b(String str, Object obj) {
        if (this.f14288c.containsKey(str)) {
            this.f14288c.put(str, obj);
            return;
        }
        C5810p c5810p = this.f14290e;
        if (c5810p != null) {
            c5810p.m12409b(str, obj);
            return;
        }
        throw new RuntimeException("\"" + str + "\" has not defined");
    }

    /* renamed from: a */
    public Object m12416a(String str) {
        for (C5810p c5810p = this; c5810p != null; c5810p = c5810p.f14290e) {
            if (c5810p.f14288c.containsKey(str)) {
                return c5810p.f14288c.get(str);
            }
        }
        throw new RuntimeException("Can not find \"" + str + "\"");
    }

    /* renamed from: a */
    public void m12415a(String str, Class<?> cls) {
        this.f14289d.put(str, cls);
    }

    /* renamed from: b */
    public Class<?> m12410b(String str) {
        for (C5810p c5810p = this; c5810p != null; c5810p = c5810p.f14290e) {
            if (c5810p.f14289d.containsKey(str)) {
                return c5810p.f14289d.get(str);
            }
        }
        throw new RuntimeException("Can not find class " + str);
    }

    /* renamed from: b */
    public C5810p m12411b() {
        C5810p c5810p = new C5810p(new HashMap(), this.f14286a);
        c5810p.f14290e = this;
        return c5810p;
    }

    /* renamed from: c */
    public C5810p m12408c() {
        return this.f14290e;
    }

    /* renamed from: d */
    public int m12407d() {
        return this.f14287b.size();
    }

    /* renamed from: e */
    public void m12406e() {
        this.f14291f = true;
    }

    /* renamed from: f */
    public boolean m12405f() {
        return this.f14291f;
    }

    /* renamed from: g */
    public C5813r m12404g() {
        return this.f14286a;
    }

    /* renamed from: a */
    public Object m12417a(final Object obj, final boolean z, Class<?>... clsArr) {
        return Proxy.newProxyInstance(getClass().getClassLoader(), clsArr, new InvocationHandler() { // from class: com.mob.commons.cc.p.1
            @Override // java.lang.reflect.InvocationHandler
            public Object invoke(Object obj2, Method method, Object[] objArr) throws Throwable {
                C5824w c5824w;
                LinkedList<Object> mo12344b;
                try {
                    if (obj == null) {
                        c5824w = null;
                    } else if (obj instanceof C5824w) {
                        c5824w = (C5824w) obj;
                    } else {
                        c5824w = (C5824w) ((Map) obj).get(method.getName());
                    }
                    if (c5824w != null) {
                        if (objArr == null) {
                            objArr = new Object[0];
                        }
                        if (z) {
                            mo12344b = c5824w.mo12344b(objArr);
                        } else {
                            mo12344b = c5824w.mo12344b(objArr);
                        }
                        if (mo12344b.isEmpty()) {
                            return null;
                        }
                        return mo12344b.get(0);
                    }
                } catch (Throwable unused) {
                }
                Throwable th = null;
                if (th == null) {
                    return null;
                }
                throw th;
            }
        });
    }

    /* renamed from: a */
    public void m12413a(Method method, int i) throws Throwable {
        Object[] objArr = new Object[i];
        for (int i2 = 0; i2 < i; i2++) {
            objArr[i2] = m12419a();
        }
        m12412a(method, objArr);
    }

    /* renamed from: a */
    public void m12412a(Method method, Object[] objArr) throws Throwable {
        Object obj;
        if (Modifier.isStatic(method.getModifiers())) {
            obj = null;
        } else if (objArr.length > 0) {
            obj = objArr[0];
            Object[] objArr2 = new Object[objArr.length - 1];
            int i = 0;
            while (i < objArr2.length) {
                int i2 = i + 1;
                objArr2[i] = objArr[i2];
                i = i2;
            }
            objArr = objArr2;
        } else {
            throw new RuntimeException("receiver not found");
        }
        method.setAccessible(true);
        for (int i3 = 0; i3 < objArr.length; i3++) {
            if (method.getParameterTypes()[i3].isInterface() && (objArr[i3] instanceof C5824w)) {
                objArr[i3] = m12417a(objArr[i3], true, method.getParameterTypes()[i3]);
            }
        }
        if (method.getReturnType() == Void.TYPE) {
            method.invoke(obj, objArr);
        } else {
            m12418a(method.invoke(obj, objArr));
        }
    }
}
