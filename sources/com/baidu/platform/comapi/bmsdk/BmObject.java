package com.baidu.platform.comapi.bmsdk;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public abstract class BmObject {

    /* renamed from: a */
    protected final long f7543a;

    /* renamed from: b */
    protected final int f7544b;

    /* renamed from: c */
    private String f7545c;

    /* renamed from: d */
    private String f7546d;

    private BmObject() {
        this.f7545c = "";
        this.f7546d = "";
        this.f7544b = 0;
        this.f7543a = 0L;
    }

    public BmObject(int i, long j) {
        this.f7545c = "";
        this.f7546d = "";
        this.f7544b = i;
        this.f7543a = j;
    }

    /* renamed from: b */
    private void m18051b() {
        long j = this.f7543a;
        if (j != 0) {
            nativeFinalizer(j);
        }
    }

    private static native void nativeFinalizer(long j);

    /* renamed from: a */
    public long m18052a() {
        return this.f7543a;
    }

    protected void finalize() throws Throwable {
        try {
            m18051b();
        } catch (Throwable unused) {
        }
        super.finalize();
    }
}
