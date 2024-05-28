package com.huawei.hms.hatool;

import android.content.Context;
import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* renamed from: com.huawei.hms.hatool.e */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C4978e {

    /* renamed from: b */
    private static C4978e f11377b;

    /* renamed from: c */
    private static Map<String, Long> f11378c = new HashMap();

    /* renamed from: a */
    private Context f11379a;

    /* renamed from: a */
    public static C4978e m14760a() {
        return m14753b();
    }

    /* renamed from: b */
    private static synchronized C4978e m14753b() {
        C4978e c4978e;
        synchronized (C4978e.class) {
            if (f11377b == null) {
                f11377b = new C4978e();
            }
            c4978e = f11377b;
        }
        return c4978e;
    }

    /* renamed from: b */
    private void m14752b(Context context) {
        String str;
        String str2;
        String m14572d = AbstractC5011o.m14572d(context);
        AbstractC5020q0.m14541a(m14572d);
        if (C5021q1.m14518b().m14519a()) {
            String m14767a = C4975d.m14767a(context, "global_v2", "app_ver", "");
            C4975d.m14763b(context, "global_v2", "app_ver", m14572d);
            AbstractC5020q0.m14539b(m14767a);
            if (!TextUtils.isEmpty(m14767a)) {
                if (m14767a.equals(m14572d)) {
                    return;
                }
                C5029v.m14455c("hmsSdk", "the appVers are different!");
                m14760a().m14754a("", "alltype", m14767a);
                return;
            }
            str = "hmsSdk";
            str2 = "app ver is first save!";
        } else {
            str = "hmsSdk";
            str2 = "userManager.isUserUnlocked() == false";
        }
        C5029v.m14455c(str, str2);
    }

    /* renamed from: a */
    public void m14759a(Context context) {
        this.f11379a = context;
        m14752b(context);
        C5023s.m14511c().m14512b().m14711h(AbstractC5011o.m14581a());
    }

    /* renamed from: a */
    public void m14758a(String str, int i) {
        if (this.f11379a == null) {
            C5029v.m14452e("hmsSdk", "onReport() null context or SDK was not init.");
            return;
        }
        C5029v.m14455c("hmsSdk", "onReport: Before calling runtaskhandler()");
        m14754a(str, AbstractC5010n1.m14587a(i), AbstractC5020q0.m14530g());
    }

    /* renamed from: a */
    public void m14757a(String str, int i, String str2, JSONObject jSONObject) {
        long currentTimeMillis = System.currentTimeMillis();
        if (2 == i) {
            currentTimeMillis = AbstractC5010n1.m14585a("yyyy-MM-dd", currentTimeMillis);
        }
        C4968b0.m14793c().m14795a(new C4965a0(str2, jSONObject, str, AbstractC5010n1.m14587a(i), currentTimeMillis));
    }

    /* renamed from: a */
    public void m14756a(String str, int i, String str2, JSONObject jSONObject, long j) {
        new C4992i1(str, AbstractC5010n1.m14587a(i), str2, jSONObject.toString(), j).m14674a();
    }

    /* renamed from: a */
    public void m14755a(String str, String str2) {
        if (!AbstractC4966a1.m14818a(str, str2)) {
            C5029v.m14455c("hmsSdk", "auto report is closed tag:" + str);
            return;
        }
        long m14808j = AbstractC4966a1.m14808j(str, str2);
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - m14808j <= 30000) {
            C5029v.m14451f("hmsSdk", "autoReport timeout. interval < 30s ");
            return;
        }
        C5029v.m14461a("hmsSdk", "begin to call onReport!");
        AbstractC4966a1.m14817a(str, str2, currentTimeMillis);
        m14754a(str, str2, AbstractC5020q0.m14530g());
    }

    /* renamed from: a */
    public void m14754a(String str, String str2, String str3) {
        Context context = this.f11379a;
        if (context == null) {
            C5029v.m14452e("hmsSdk", "onReport() null context or SDK was not init.");
            return;
        }
        String m14516a = C5022r0.m14516a(context);
        if (AbstractC4966a1.m14813e(str, str2) && !"WIFI".equals(m14516a)) {
            C5029v.m14455c("hmsSdk", "strNetworkType is :" + m14516a);
        } else if (TextUtils.isEmpty(m14516a) || "2G".equals(m14516a)) {
            C5029v.m14452e("hmsSdk", "The network is bad.");
        } else {
            C4968b0.m14793c().m14795a(new C5030v0(str, str2, str3));
        }
    }
}
