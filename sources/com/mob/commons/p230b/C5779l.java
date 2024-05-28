package com.mob.commons.p230b;

import android.content.Context;
import com.mob.commons.p229a.C5731l;
import com.mob.commons.p230b.AbstractC5764e;
import com.mob.tools.MobLog;
import java.lang.reflect.Method;

/* renamed from: com.mob.commons.b.l */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5779l extends AbstractC5764e {
    public C5779l(Context context) {
        super(context);
    }

    @Override // com.mob.commons.p230b.AbstractC5764e
    /* renamed from: b */
    protected AbstractC5764e.C5767b mo12493b() {
        Class<?> cls;
        Object obj;
        Method method = null;
        try {
            cls = Class.forName(C5731l.m12674a("034d2feegge(ef0edekfeejedgeejedgeejeg4khCgefjedhlekfeeeejed+g$ekfjeg,kh"));
        } catch (Throwable th) {
            th = th;
            cls = null;
        }
        try {
            obj = cls.newInstance();
        } catch (Throwable th2) {
            th = th2;
            MobLog.getInstance().m11341d(th);
            obj = null;
            boolean z = true;
            if (cls != null) {
                try {
                    method = cls.getMethod(C5731l.m12674a("007Vff;gjXhmfmfjgl"), Context.class);
                } catch (Throwable th3) {
                    MobLog.getInstance().m11341d(th3);
                }
            }
            AbstractC5764e.C5767b c5767b = new AbstractC5764e.C5767b();
            c5767b.f14234b = m12494a(this.f14224a, obj, method);
            if (cls != null) {
            }
            z = false;
            c5767b.f14233a = z;
            return c5767b;
        }
        boolean z2 = true;
        if (cls != null && obj != null) {
            method = cls.getMethod(C5731l.m12674a("007Vff;gjXhmfmfjgl"), Context.class);
        }
        AbstractC5764e.C5767b c5767b2 = new AbstractC5764e.C5767b();
        c5767b2.f14234b = m12494a(this.f14224a, obj, method);
        if (cls != null || obj == null) {
            z2 = false;
        }
        c5767b2.f14233a = z2;
        return c5767b2;
    }

    /* renamed from: a */
    private String m12494a(Context context, Object obj, Method method) {
        if (obj == null || method == null) {
            return null;
        }
        try {
            Object invoke = method.invoke(obj, context);
            if (invoke != null) {
                return (String) invoke;
            }
            return null;
        } catch (Throwable th) {
            MobLog.getInstance().m11341d(th);
            return null;
        }
    }
}
