package com.huawei.hms.hatool;

import android.content.Context;
import android.text.TextUtils;

/* renamed from: com.huawei.hms.hatool.e0 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public abstract class AbstractC4979e0 {
    /* renamed from: a */
    private C4990i m14751a(int i) {
        String str = "";
        if (i != 0) {
            str = m14747f();
            if (!TextUtils.isEmpty(str)) {
                return new C4990i(EnumC4976d0.UDID, str);
            }
        }
        return new C4990i(EnumC4976d0.EMPTY, str);
    }

    /* renamed from: b */
    private C4990i m14749b(int i) {
        String str = "";
        if ((i & 4) != 0) {
            str = m14747f();
            if (!TextUtils.isEmpty(str)) {
                return new C4990i(EnumC4976d0.UDID, str);
            }
        }
        return new C4990i(EnumC4976d0.EMPTY, str);
    }

    /* renamed from: e */
    private boolean m14748e() {
        C4986g1 m14512b = C5023s.m14511c().m14512b();
        if (TextUtils.isEmpty(m14512b.m14704l())) {
            m14512b.m14711h(AbstractC5011o.m14581a());
        }
        return !TextUtils.isEmpty(m14512b.m14704l());
    }

    /* renamed from: f */
    private String m14747f() {
        C4986g1 m14512b = C5023s.m14511c().m14512b();
        if (TextUtils.isEmpty(m14512b.m14710i())) {
            m14512b.m14717e(C5035x0.m14430c());
        }
        return m14512b.m14710i();
    }

    /* renamed from: a */
    public C4990i m14750a(Context context) {
        String mo14661c = mo14661c();
        if (TextUtils.isEmpty(mo14661c)) {
            String mo14664a = mo14664a();
            if (TextUtils.isEmpty(mo14664a)) {
                boolean m14748e = m14748e();
                String mo14662b = mo14662b();
                return !TextUtils.isEmpty(mo14662b) ? m14748e ? new C4990i(EnumC4976d0.SN, mo14662b) : new C4990i(EnumC4976d0.UDID, mo14663a(mo14662b)) : m14748e ? m14751a(mo14660d()) : m14749b(mo14660d());
            }
            return new C4990i(EnumC4976d0.IMEI, mo14664a);
        }
        return new C4990i(EnumC4976d0.UDID, mo14661c);
    }

    /* renamed from: a */
    public abstract String mo14664a();

    /* renamed from: a */
    public abstract String mo14663a(String str);

    /* renamed from: b */
    public abstract String mo14662b();

    /* renamed from: c */
    public abstract String mo14661c();

    /* renamed from: d */
    public abstract int mo14660d();
}
