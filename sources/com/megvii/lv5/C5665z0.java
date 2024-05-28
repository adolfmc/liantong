package com.megvii.lv5;

import android.content.Context;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.InvocationTargetException;

/* compiled from: Proguard */
/* renamed from: com.megvii.lv5.z0 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5665z0 {

    /* renamed from: b */
    public static C5665z0 f13946b;

    /* renamed from: a */
    public Class<?> f13947a;

    public C5665z0() {
        this.f13947a = null;
        try {
            Class<?> cls = Class.forName("com.megvii.safe.lv5.CommonProtectorManager");
            this.f13947a = cls;
            AccessibleObject.setAccessible(cls.getDeclaredConstructors(), true);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public static synchronized C5665z0 m12886a() {
        C5665z0 c5665z0;
        synchronized (C5665z0.class) {
            if (f13946b == null) {
                f13946b = new C5665z0();
            }
            c5665z0 = f13946b;
        }
        return c5665z0;
    }

    /* renamed from: a */
    public int m12885a(Context context) {
        Class<?> cls = this.f13947a;
        if (cls != null) {
            try {
                return ((Integer) cls.getMethod("checkCharggingType", Context.class).invoke(null, context)).intValue();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                return -1;
            } catch (NoSuchMethodException e2) {
                e2.printStackTrace();
            } catch (InvocationTargetException e3) {
                e3.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    /* renamed from: b */
    public void m12884b() {
        Class<?> cls = this.f13947a;
        if (cls != null) {
            try {
                cls.getMethod("releaseSenso", new Class[0]).invoke(null, new Object[0]);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e2) {
                e2.printStackTrace();
            } catch (InvocationTargetException e3) {
                e3.printStackTrace();
            }
        }
    }

    /* renamed from: b */
    public boolean m12883b(Context context) {
        Class<?> cls = this.f13947a;
        if (cls != null) {
            try {
                return ((Boolean) cls.getMethod("isVertical", Context.class).invoke(null, context)).booleanValue();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                return true;
            } catch (NoSuchMethodException e2) {
                e2.printStackTrace();
            } catch (InvocationTargetException e3) {
                e3.printStackTrace();
                return true;
            }
        }
        return true;
    }
}
