package com.bytedance.applog;

import android.content.Context;
import com.bytedance.applog.InterfaceC3584f3;
import com.bytedance.applog.InterfaceC3645n3;
import java.lang.reflect.Method;

/* renamed from: com.bytedance.applog.w3 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public final class C3725w3 implements InterfaceC3645n3 {

    /* renamed from: a */
    public static Object f8893a;

    /* renamed from: b */
    public static Class<?> f8894b;

    /* renamed from: c */
    public static Method f8895c;

    static {
        try {
            f8894b = Class.forName("com.android.id.impl.IdProviderImpl");
            f8893a = f8894b.newInstance();
            f8895c = f8894b.getMethod("getOAID", Context.class);
        } catch (Exception e) {
            String str = C3630m3.f8586j;
            StringBuilder m17349a = C3535a.m17349a("Api#static reflect exception! ");
            m17349a.append(e.getMessage());
            String sb = m17349a.toString();
            InterfaceC3584f3 interfaceC3584f3 = C3578e3.f8438b;
            if (interfaceC3584f3 == null || C3578e3.f8437a > 6) {
                return;
            }
            ((InterfaceC3584f3.C3585a) interfaceC3584f3).m17298b(str, sb, null);
        }
    }

    /* renamed from: a */
    public static boolean m17058a() {
        return (f8894b == null || f8893a == null || f8895c == null) ? false : true;
    }

    @Override // com.bytedance.applog.InterfaceC3645n3
    /* renamed from: a */
    public InterfaceC3645n3.C3646a mo17057a(Context context) {
        String str;
        Object invoke;
        try {
            InterfaceC3645n3.C3646a c3646a = new InterfaceC3645n3.C3646a();
            Method method = f8895c;
            Object obj = f8893a;
            if (obj != null && method != null) {
                try {
                    invoke = method.invoke(obj, context);
                } catch (Exception unused) {
                }
                if (invoke != null) {
                    str = (String) invoke;
                    c3646a.f8617a = str;
                    return c3646a;
                }
            }
            str = null;
            c3646a.f8617a = str;
            return c3646a;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override // com.bytedance.applog.InterfaceC3645n3
    /* renamed from: b */
    public boolean mo17056b(Context context) {
        return (f8894b == null || f8893a == null || f8895c == null) ? false : true;
    }
}
