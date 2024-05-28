package com.huawei.hms.hatool;

import android.content.Context;
import android.text.TextUtils;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.huawei.hms.hatool.i0 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public abstract class AbstractC4991i0 {
    /* renamed from: a */
    public static String m14678a(Context context, String str, String str2) {
        if (TextUtils.isEmpty(AbstractC5038z.m14420a(str, str2))) {
            C5029v.m14455c("hmsSdk", "getAndroidId(): to getConfigByType()");
            return m14676c(context, str, str2);
        }
        return AbstractC5038z.m14420a(str, str2);
    }

    /* renamed from: b */
    public static String m14677b(Context context, String str, String str2) {
        if (!str2.equals("oper") && !str2.equals("maint") && !str2.equals("diffprivacy") && !str2.equals("preins")) {
            C5029v.m14451f("hmsSdk", "getChannel(): Invalid type: " + str2);
            return "";
        }
        return m14675d(context, str, str2);
    }

    /* renamed from: c */
    private static String m14676c(Context context, String str, String str2) {
        if (AbstractC5038z.m14419b(str, str2)) {
            if (TextUtils.isEmpty(AbstractC5020q0.m14536d())) {
                C5023s.m14511c().m14512b().m14723b(AbstractC5011o.m14580a(context));
            }
            return AbstractC5020q0.m14536d();
        }
        return "";
    }

    /* renamed from: d */
    private static String m14675d(Context context, String str, String str2) {
        if (TextUtils.isEmpty(AbstractC4966a1.m14814d(str, str2))) {
            C4986g1 m14512b = C5023s.m14511c().m14512b();
            if (TextUtils.isEmpty(m14512b.m14712h())) {
                String m14574b = AbstractC5011o.m14574b(context);
                if (!C4980e1.m14745a("channel", m14574b, 256)) {
                    m14574b = "";
                }
                m14512b.m14715f(m14574b);
            }
            return m14512b.m14712h();
        }
        return AbstractC4966a1.m14814d(str, str2);
    }
}
