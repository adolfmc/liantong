package com.bytedance.applog;

import org.json.JSONArray;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.bytedance.applog.m2 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C3629m2 {

    /* renamed from: a */
    public static InterfaceC3674q2 f8584a;

    static {
        InterfaceC3674q2 interfaceC3674q2;
        try {
            Object invoke = Class.forName("com.bytedance.applog.et_verify.EventVerify").getMethod("inst", new Class[0]).invoke(null, new Object[0]);
            interfaceC3674q2 = invoke instanceof InterfaceC3674q2 ? (InterfaceC3674q2) invoke : null;
        } catch (Exception unused) {
            interfaceC3674q2 = null;
        } catch (Throwable th) {
            C3704u2.m17108a("can't find event verify, should compile with ET", (Throwable) null);
            throw th;
        }
        if (interfaceC3674q2 != null) {
            C3704u2.m17108a(interfaceC3674q2.toString(), (Throwable) null);
            f8584a = interfaceC3674q2;
        }
        C3704u2.m17108a("can't find event verify, should compile with ET", (Throwable) null);
        f8584a = interfaceC3674q2;
    }

    /* renamed from: a */
    public static void m17227a(String str, JSONArray jSONArray) {
        InterfaceC3674q2 interfaceC3674q2 = f8584a;
        if (interfaceC3674q2 != null) {
            interfaceC3674q2.m17138a(str, jSONArray);
        }
    }

    /* renamed from: a */
    public static boolean m17228a() {
        InterfaceC3674q2 interfaceC3674q2 = f8584a;
        if (interfaceC3674q2 != null) {
            return interfaceC3674q2.m17139a();
        }
        return false;
    }
}
