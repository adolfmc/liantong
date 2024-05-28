package com.xiaomi.push;

import java.util.LinkedList;

/* renamed from: com.xiaomi.push.ax */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11178ax {

    /* renamed from: a */
    private LinkedList<C11179a> f21561a = new LinkedList<>();

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.xiaomi.push.ax$a */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class C11179a {

        /* renamed from: a */
        private static final C11178ax f21562a = new C11178ax();

        /* renamed from: a */
        public int f21563a;

        /* renamed from: a */
        public Object f21564a;

        /* renamed from: a */
        public String f21565a;

        C11179a(int i, Object obj) {
            this.f21563a = i;
            this.f21564a = obj;
        }
    }

    /* renamed from: a */
    public static C11178ax m4802a() {
        return C11179a.f21562a;
    }

    /* renamed from: a */
    public synchronized void m4799a(Object obj) {
        this.f21561a.add(new C11179a(0, obj));
        m4800a();
    }

    /* renamed from: a */
    private void m4800a() {
        if (this.f21561a.size() > 100) {
            this.f21561a.removeFirst();
        }
    }

    /* renamed from: a */
    public synchronized int m4803a() {
        return this.f21561a.size();
    }

    /* renamed from: a */
    public synchronized LinkedList<C11179a> m4801a() {
        LinkedList<C11179a> linkedList;
        linkedList = this.f21561a;
        this.f21561a = new LinkedList<>();
        return linkedList;
    }
}
