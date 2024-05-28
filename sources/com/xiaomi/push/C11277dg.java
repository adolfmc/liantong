package com.xiaomi.push;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.xiaomi.push.dg */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C11277dg {

    /* renamed from: a */
    private static volatile C11277dg f21861a;

    /* renamed from: a */
    private InterfaceC11276df f21862a;

    /* renamed from: a */
    public static C11277dg m4362a() {
        if (f21861a == null) {
            synchronized (C11277dg.class) {
                if (f21861a == null) {
                    f21861a = new C11277dg();
                }
            }
        }
        return f21861a;
    }

    /* renamed from: a */
    public InterfaceC11276df m4363a() {
        return this.f21862a;
    }

    /* renamed from: a */
    public void m4361a(InterfaceC11276df interfaceC11276df) {
        this.f21862a = interfaceC11276df;
    }
}
