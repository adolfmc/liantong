package com.networkbench.agent.impl.p243c;

import android.os.Looper;
import com.networkbench.agent.impl.instrumentation.NBSUnit;
import com.networkbench.agent.impl.instrumentation.TraceStack;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.c.d */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6268d {

    /* renamed from: a */
    private ThreadLocal<NBSUnit> f15620a = new ThreadLocal<>();

    /* renamed from: b */
    private ThreadLocal<TraceStack<NBSUnit>> f15621b = new ThreadLocal<>();

    /* renamed from: c */
    private NBSUnit f15622c;

    /* renamed from: a */
    public NBSUnit m10732a() {
        return this.f15622c;
    }

    /* renamed from: a */
    public void m10731a(NBSUnit nBSUnit, Boolean bool) {
        if (nBSUnit == null || m10725g() == null) {
            return;
        }
        this.f15621b.set(m10725g());
        if (m10725g().isEmpty() || m10725g().peek() != nBSUnit) {
            m10725g().push(nBSUnit);
        }
        this.f15620a.set(nBSUnit);
        if (bool.booleanValue()) {
            this.f15622c = nBSUnit;
        }
    }

    /* renamed from: b */
    public void m10730b() {
        this.f15620a.remove();
        if (this.f15621b.get() != null) {
            this.f15621b.get().pop();
        }
        m10729c();
    }

    /* renamed from: c */
    public void m10729c() {
        if (this.f15621b.get() == null || this.f15621b.get().isEmpty()) {
            this.f15620a.set(null);
            return;
        }
        NBSUnit peek = this.f15621b.get().peek();
        this.f15620a.set(peek);
        if (Looper.myLooper() == Looper.getMainLooper()) {
            this.f15622c = peek;
        }
    }

    /* renamed from: d */
    public NBSUnit m10728d() {
        return this.f15620a.get();
    }

    /* renamed from: e */
    public int m10727e() {
        return this.f15621b.get().size();
    }

    /* renamed from: f */
    public void m10726f() {
        this.f15620a.remove();
        if (this.f15621b.get() != null) {
            this.f15621b.get().clear();
        }
    }

    /* renamed from: g */
    private TraceStack m10725g() {
        TraceStack<NBSUnit> traceStack = this.f15621b.get();
        return traceStack == null ? new TraceStack() : traceStack;
    }
}
