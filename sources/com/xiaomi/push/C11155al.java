package com.xiaomi.push;

import android.content.Context;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import java.lang.reflect.Method;

/* renamed from: com.xiaomi.push.al */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
class C11155al implements InterfaceC11150ai {

    /* renamed from: a */
    private Context f21508a;

    /* renamed from: a */
    private Class<?> f21509a;

    /* renamed from: a */
    private Object f21510a;

    /* renamed from: a */
    private Method f21511a = null;

    /* renamed from: b */
    private Method f21512b = null;

    /* renamed from: c */
    private Method f21513c = null;

    /* renamed from: d */
    private Method f21514d = null;

    public C11155al(Context context) {
        this.f21508a = context;
        m4886a(context);
    }

    /* renamed from: a */
    private void m4886a(Context context) {
        try {
            this.f21509a = C11479r.m2929a(context, "com.android.id.impl.IdProviderImpl");
            this.f21510a = this.f21509a.newInstance();
            this.f21512b = this.f21509a.getMethod("getOAID", Context.class);
        } catch (Exception e) {
            AbstractC11049b.m5279a("miui load class error", e);
        }
    }

    @Override // com.xiaomi.push.InterfaceC11150ai
    /* renamed from: a */
    public boolean mo4862a() {
        return (this.f21509a == null || this.f21510a == null) ? false : true;
    }

    @Override // com.xiaomi.push.InterfaceC11150ai
    /* renamed from: a */
    public String mo4863a() {
        return m4885a(this.f21508a, this.f21512b);
    }

    /* renamed from: a */
    private String m4885a(Context context, Method method) {
        Object obj = this.f21510a;
        if (obj == null || method == null) {
            return null;
        }
        try {
            Object invoke = method.invoke(obj, context);
            if (invoke != null) {
                return (String) invoke;
            }
            return null;
        } catch (Exception e) {
            AbstractC11049b.m5279a("miui invoke error", e);
            return null;
        }
    }
}
