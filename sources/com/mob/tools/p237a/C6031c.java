package com.mob.tools.p237a;

import android.content.Context;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: com.mob.tools.a.c */
/* loaded from: E:\567196_dexfile_execute.dex.fixout.dex */
public class C6031c {

    /* renamed from: a */
    private static C6031c f14834a = new C6031c();

    /* renamed from: b */
    private volatile Context f14835b;

    /* renamed from: c */
    private volatile InterfaceC6028a f14836c;

    /* renamed from: d */
    private volatile InterfaceC6028a f14837d;

    /* renamed from: e */
    private volatile InterfaceC6028a f14838e;

    /* renamed from: f */
    private final AtomicBoolean f14839f = new AtomicBoolean(false);

    /* renamed from: a */
    public static C6031c m11708a(Context context) {
        if (f14834a.f14835b == null && context != null) {
            f14834a.f14835b = context.getApplicationContext();
        }
        return f14834a;
    }

    /* renamed from: a */
    public CountDownLatch m11709a() {
        m11706b();
        if (this.f14836c != null) {
            this.f14836c.mo11593X();
        }
        return C6032d.m11700a(this.f14835b).m11702a();
    }

    /* renamed from: b */
    public void m11706b() {
        if (this.f14839f.compareAndSet(false, true)) {
            m11704d();
            m11705c();
            C6034e.m11667a(this.f14835b);
        }
    }

    /* renamed from: c */
    public InterfaceC6028a m11705c() {
        if (this.f14836c == null) {
            this.f14836c = new C6050j(this.f14835b);
        }
        return this.f14836c;
    }

    /* renamed from: d */
    public InterfaceC6028a m11704d() {
        if (this.f14837d == null) {
            this.f14837d = new C6039g(this.f14835b);
        }
        return this.f14837d;
    }

    /* renamed from: a */
    public boolean m11707a(InterfaceC6028a interfaceC6028a) {
        this.f14838e = interfaceC6028a;
        return true;
    }

    /* renamed from: e */
    public InterfaceC6028a m11703e() {
        if (this.f14838e == null) {
            return m11705c();
        }
        return this.f14838e;
    }
}
