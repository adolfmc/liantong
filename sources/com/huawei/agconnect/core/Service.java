package com.huawei.agconnect.core;

import com.huawei.agconnect.annotation.AutoCreated;
import com.huawei.agconnect.annotation.SharedInstance;
import com.huawei.agconnect.annotation.Singleton;
import java.lang.reflect.Modifier;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class Service {

    /* renamed from: a */
    private final Class<?> f10788a;

    /* renamed from: b */
    private final Class<?> f10789b;

    /* renamed from: c */
    private final Object f10790c;

    /* renamed from: d */
    private boolean f10791d;

    /* renamed from: e */
    private boolean f10792e;

    /* renamed from: f */
    private boolean f10793f;

    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static class Builder {

        /* renamed from: a */
        Class<?> f10794a;

        /* renamed from: b */
        Class<?> f10795b;

        /* renamed from: c */
        Object f10796c;

        /* renamed from: d */
        private boolean f10797d;

        /* renamed from: e */
        private boolean f10798e;

        /* renamed from: f */
        private boolean f10799f;

        public Service build() {
            Class<?> cls = this.f10794a;
            if (cls != null) {
                Class<?> cls2 = this.f10795b;
                if (cls2 == null) {
                    Object obj = this.f10796c;
                    if (obj != null) {
                        Service service = new Service(cls, obj);
                        service.f10791d = this.f10797d;
                        return service;
                    }
                    throw new IllegalArgumentException("the clazz or object parameter must set one");
                } else if (cls2.isInterface() || !Modifier.isPublic(this.f10795b.getModifiers())) {
                    throw new IllegalArgumentException("the clazz parameter cant be interface type or not public");
                } else {
                    Service service2 = new Service((Class) this.f10794a, (Class) this.f10795b);
                    service2.f10791d = this.f10797d;
                    service2.f10792e = this.f10798e;
                    service2.f10793f = this.f10799f;
                    return service2;
                }
            }
            throw new IllegalArgumentException("the interface parameter cannot be NULL");
        }

        public Builder isAutoCreated(boolean z) {
            this.f10799f = z;
            return this;
        }

        public Builder isSharedInstance(boolean z) {
            this.f10798e = z;
            return this;
        }

        public Builder isSingleton(boolean z) {
            this.f10797d = z;
            return this;
        }

        public Builder setClass(Class<?> cls) {
            this.f10795b = cls;
            return this;
        }

        public Builder setInterface(Class<?> cls) {
            this.f10794a = cls;
            return this;
        }

        public Builder setObject(Object obj) {
            this.f10796c = obj;
            return this;
        }
    }

    private Service(Class<?> cls, Class<?> cls2) {
        this.f10788a = cls;
        this.f10789b = cls2;
        this.f10790c = null;
    }

    private Service(Class<?> cls, Object obj) {
        this.f10788a = cls;
        this.f10789b = null;
        this.f10790c = obj;
    }

    public static Builder builder(Class<?> cls) {
        return new Builder().setInterface(cls).setClass(cls).isSingleton(cls.isAnnotationPresent(Singleton.class)).isSharedInstance(cls.isAnnotationPresent(SharedInstance.class)).isAutoCreated(cls.isAnnotationPresent(AutoCreated.class));
    }

    public static Builder builder(Class<?> cls, Class<?> cls2) {
        return new Builder().setInterface(cls).setClass(cls2).isSingleton(cls2.isAnnotationPresent(Singleton.class)).isSharedInstance(cls2.isAnnotationPresent(SharedInstance.class)).isAutoCreated(cls2.isAnnotationPresent(AutoCreated.class));
    }

    public static Builder builder(Class<?> cls, Object obj) {
        return new Builder().setInterface(cls).setObject(obj).isSingleton(true).isSharedInstance(cls.isAnnotationPresent(SharedInstance.class)).isAutoCreated(cls.isAnnotationPresent(AutoCreated.class));
    }

    public Object getInstance() {
        return this.f10790c;
    }

    public Class<?> getInterface() {
        return this.f10788a;
    }

    public Class<?> getType() {
        return this.f10789b;
    }

    public boolean isAutoCreated() {
        return this.f10793f;
    }

    public boolean isSharedInstance() {
        return this.f10792e;
    }

    public boolean isSingleton() {
        return this.f10791d;
    }
}
