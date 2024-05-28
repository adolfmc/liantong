package com.bytedance.applog;

/* renamed from: com.bytedance.applog.z2 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public abstract class AbstractC3749z2<T> {

    /* renamed from: a */
    public volatile T f8999a;

    /* renamed from: a */
    public abstract T mo16989a(Object... objArr);

    /* renamed from: b */
    public final T m16988b(Object... objArr) {
        if (this.f8999a == null) {
            synchronized (this) {
                if (this.f8999a == null) {
                    this.f8999a = mo16989a(objArr);
                }
            }
        }
        return this.f8999a;
    }
}
