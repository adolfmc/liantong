package com.baidu.platform.comapi.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.platform.comapi.util.a */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C3087a {

    /* renamed from: a */
    public static final C3087a f8043a = new C3087a();

    /* renamed from: b */
    private static final String f8044b = "com.baidu.platform.comapi.util.a";

    /* renamed from: c */
    private final Map<Class<?>, CopyOnWriteArraySet<C3088a>> f8045c = new HashMap();

    /* renamed from: d */
    private final Map<Class<?>, Object> f8046d = new HashMap();

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.platform.comapi.util.a$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    class C3088a implements InterfaceC3089b {

        /* renamed from: a */
        private final InterfaceC3089b f8047a;

        @Override // com.baidu.platform.comapi.util.C3087a.InterfaceC3089b
        /* renamed from: a */
        public void mo17692a(Object obj) {
            this.f8047a.mo17692a(obj);
        }

        public boolean equals(Object obj) {
            return this.f8047a.equals(obj);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.platform.comapi.util.a$b */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface InterfaceC3089b {
        /* renamed from: a */
        void mo17692a(Object obj);
    }

    private C3087a() {
    }

    /* renamed from: a */
    public static C3087a m17703a() {
        return f8043a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m17702a(InterfaceC3089b interfaceC3089b, Object obj) {
        interfaceC3089b.mo17692a(obj);
    }

    /* renamed from: a */
    public void m17700a(Object obj) {
        CopyOnWriteArraySet<C3088a> copyOnWriteArraySet;
        if (this.f8045c.containsKey(obj.getClass())) {
            synchronized (this) {
                copyOnWriteArraySet = this.f8045c.get(obj.getClass());
            }
            Iterator<C3088a> it = copyOnWriteArraySet.iterator();
            while (it.hasNext()) {
                C3097i.m17679a(new RunnableC3090b(this, it.next(), obj), 0L);
            }
        }
    }
}
