package com.bytedance.applog;

/* renamed from: com.bytedance.applog.t2 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C3695t2 {

    /* renamed from: a */
    public Object f8820a;

    public C3695t2() {
        try {
            this.f8820a = Class.forName("android.os.SystemProperties").newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
        } catch (InstantiationException e3) {
            e3.printStackTrace();
        }
    }

    /* renamed from: a */
    public String m17114a(String str) {
        try {
            return (String) this.f8820a.getClass().getMethod("get", String.class).invoke(this.f8820a, str);
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception unused) {
            return "";
        }
    }
}
