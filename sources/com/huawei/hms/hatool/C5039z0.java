package com.huawei.hms.hatool;

import android.content.Context;
import android.text.TextUtils;
import java.util.LinkedHashMap;

/* renamed from: com.huawei.hms.hatool.z0 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5039z0 {

    /* renamed from: a */
    private String f11530a;

    /* renamed from: b */
    public C5003l1 f11531b;

    public C5039z0(String str) {
        this.f11530a = str;
        this.f11531b = new C5003l1(str);
        C5023s.m14511c().m14513a(this.f11530a, this.f11531b);
    }

    /* renamed from: b */
    private C5024s0 m14405b(int i) {
        switch (i) {
            case 0:
                return this.f11531b.m14613c();
            case 1:
                return this.f11531b.m14615b();
            case 2:
                return this.f11531b.m14612d();
            case 3:
                return this.f11531b.m14618a();
            default:
                return null;
        }
    }

    /* renamed from: c */
    private boolean m14402c(int i) {
        if (i == 2) {
            if ("_default_config_tag".equals(this.f11530a)) {
                return true;
            }
            C5029v.m14452e("hmsSdk", "verifyURL(): type: preins. Only default config can report Pre-install data.");
            return false;
        }
        C5024s0 m14405b = m14405b(i);
        if (m14405b == null || TextUtils.isEmpty(m14405b.m14486h())) {
            C5029v.m14452e("hmsSdk", "verifyURL(): URL check failed. type: " + i);
            return false;
        }
        return true;
    }

    /* renamed from: a */
    public void m14409a(int i) {
        C5029v.m14453d("hmsSdk", "onReport. TAG: " + this.f11530a + ", TYPE: " + i);
        C4985g0.m14735a().m14733a(this.f11530a, i);
    }

    /* renamed from: a */
    public void m14408a(int i, String str, LinkedHashMap<String, String> linkedHashMap) {
        C5029v.m14453d("hmsSdk", "onEvent. TAG: " + this.f11530a + ", TYPE: " + i + ", eventId : " + str);
        if (C4980e1.m14746a(str) || !m14402c(i)) {
            C5029v.m14452e("hmsSdk", "onEvent() parameters check fail. Nothing will be recorded.TAG: " + this.f11530a + ", TYPE: " + i);
            return;
        }
        if (!C4980e1.m14742a(linkedHashMap)) {
            C5029v.m14452e("hmsSdk", "onEvent() parameter mapValue will be cleared.TAG: " + this.f11530a + ", TYPE: " + i);
            linkedHashMap = null;
        }
        C4985g0.m14735a().m14732a(this.f11530a, i, str, linkedHashMap);
    }

    /* renamed from: a */
    public void m14407a(Context context, String str, String str2) {
        C5029v.m14453d("hmsSdk", "onEvent(context). TAG: " + this.f11530a + ", eventId : " + str);
        if (context == null) {
            C5029v.m14452e("hmsSdk", "context is null in onevent ");
        } else if (C4980e1.m14746a(str) || !m14402c(0)) {
            C5029v.m14452e("hmsSdk", "onEvent() parameters check fail. Nothing will be recorded.TAG: " + this.f11530a);
        } else {
            if (!C4980e1.m14745a("value", str2, 65536)) {
                C5029v.m14452e("hmsSdk", "onEvent() parameter VALUE is overlong, content will be cleared.TAG: " + this.f11530a);
                str2 = "";
            }
            C4985g0.m14735a().m14731a(this.f11530a, context, str, str2);
        }
    }

    /* renamed from: a */
    public void m14406a(C5024s0 c5024s0) {
        C5029v.m14455c("hmsSdk", "HiAnalyticsInstanceImpl.setMaintConf() is executed.TAG : " + this.f11530a);
        if (c5024s0 != null) {
            this.f11531b.m14617a(c5024s0);
            return;
        }
        C5029v.m14452e("hmsSdk", "HiAnalyticsInstanceImpl.setMaintConf(): config for maint is null!");
        this.f11531b.m14617a((C5024s0) null);
    }

    /* renamed from: b */
    public void m14404b(int i, String str, LinkedHashMap<String, String> linkedHashMap) {
        C5029v.m14453d("hmsSdk", "onStreamEvent. TAG: " + this.f11530a + ", TYPE: " + i + ", eventId : " + str);
        if (C4980e1.m14746a(str) || !m14402c(i)) {
            C5029v.m14452e("hmsSdk", "onStreamEvent() parameters check fail. Nothing will be recorded.TAG: " + this.f11530a + ", TYPE: " + i);
            return;
        }
        if (!C4980e1.m14742a(linkedHashMap)) {
            C5029v.m14452e("hmsSdk", "onStreamEvent() parameter mapValue will be cleared.TAG: " + this.f11530a + ", TYPE: " + i);
            linkedHashMap = null;
        }
        C4985g0.m14735a().m14728b(this.f11530a, i, str, linkedHashMap);
    }

    /* renamed from: b */
    public void m14403b(C5024s0 c5024s0) {
        C5029v.m14455c("hmsSdk", "HiAnalyticsInstanceImpl.setOperConf() is executed.TAG: " + this.f11530a);
        if (c5024s0 != null) {
            this.f11531b.m14614b(c5024s0);
            return;
        }
        this.f11531b.m14614b(null);
        C5029v.m14452e("hmsSdk", "HiAnalyticsInstanceImpl.setOperConf(): config for oper is null!");
    }
}
