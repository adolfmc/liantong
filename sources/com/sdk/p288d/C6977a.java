package com.sdk.p288d;

/* renamed from: com.sdk.d.a */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class C6977a<T> {

    /* renamed from: a */
    public boolean f18086a = false;

    /* renamed from: b */
    public C6987e<?> f18087b;

    /* renamed from: c */
    public C6977a<T> f18088c;

    public C6977a(T t) {
        m8180a(t);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [T, com.sdk.d.e, com.sdk.d.e<?>] */
    /* renamed from: a */
    public T m8181a() {
        ?? r0 = (T) this.f18087b;
        if (r0 == 0) {
            return null;
        }
        return this.f18086a ? r0 : (T) r0.f18115b;
    }

    /* renamed from: a */
    public void m8180a(T t) {
        if (t == null) {
            this.f18087b = null;
        } else if (!(t instanceof C6987e)) {
            this.f18087b = new C6987e<>(EnumC6978b.DEFAULT, t);
        } else {
            this.f18087b = (C6987e) t;
            this.f18086a = true;
        }
    }
}
