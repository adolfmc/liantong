package com.networkbench.agent.impl.util;

import android.text.TextUtils;
import com.networkbench.agent.impl.NBSAgent;
import com.networkbench.agent.impl.harvest.ActionData;
import com.networkbench.agent.impl.harvest.HarvestConfiguration;
import com.networkbench.agent.impl.p243c.C6305i;
import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import java.util.List;
import java.util.regex.Pattern;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.util.t */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6652t {

    /* renamed from: a */
    private static InterfaceC6393e f17247a = C6394f.m10150a();

    /* renamed from: a */
    public static boolean m8759a(String str, int i, List<C6305i.C6306a> list) {
        if (list == null || list.size() <= 0) {
            return false;
        }
        for (C6305i.C6306a c6306a : list) {
            String str2 = c6306a.f15824c;
            int i2 = c6306a.f15822a;
            String str3 = c6306a.f15823b;
            if (i2 == 0) {
                if (str.contains(str3)) {
                    if (str2.contains("" + i)) {
                        return true;
                    }
                } else {
                    continue;
                }
            } else if (i2 == 1 && Pattern.compile(str3, 2).matcher(str).find()) {
                if (str2.contains("" + i)) {
                    return true;
                }
            }
        }
        return false;
    }

    /* renamed from: b */
    public static boolean m8758b(String str, int i, List<C6305i.C6307b> list) {
        InterfaceC6393e interfaceC6393e = f17247a;
        interfaceC6393e.mo10122a("urlFilter:" + str + ", ulrFilterMode:" + i);
        f17247a.mo10122a("urlMatchList:");
        if (list != null) {
            for (C6305i.C6307b c6307b : list) {
                InterfaceC6393e interfaceC6393e2 = f17247a;
                interfaceC6393e2.mo10122a("match mode:" + c6307b.f15825a);
                InterfaceC6393e interfaceC6393e3 = f17247a;
                interfaceC6393e3.mo10122a("match rule:" + c6307b.f15826b);
            }
        }
        if (i != 0) {
            if (list != null) {
                for (C6305i.C6307b c6307b2 : list) {
                    int i2 = c6307b2.f15825a;
                    String str2 = c6307b2.f15826b;
                    if (i2 == 0) {
                        if (str.contains(str2)) {
                            return i == 1;
                        }
                    } else if (i2 == 1 && Pattern.compile(str2, 2).matcher(str).find()) {
                        return i == 1;
                    }
                }
            }
            return i != 1;
        }
        return true;
    }

    /* renamed from: a */
    public static boolean m8761a(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return false;
            }
            NBSAndroidAgentImpl impl = NBSAgent.getImpl();
            HarvestConfiguration m9150m = impl != null ? impl.m9150m() : null;
            if (m9150m != null) {
                return !m8758b(str, m9150m.getUrlFilterMode(), m9150m.getUrlRules());
            }
            return false;
        } catch (Exception e) {
            InterfaceC6393e interfaceC6393e = C6638h.f17124y;
            interfaceC6393e.mo10115e("filterNameList e:" + e.getMessage());
            return false;
        }
    }

    /* renamed from: a */
    public static void m8762a(ActionData actionData) {
        if (actionData == null || m8760a(actionData.getUrl(), actionData.getStatusCode()) != 200) {
            return;
        }
        actionData.setStatusCode(200);
        actionData.setErrorCode(0);
    }

    /* renamed from: a */
    public static int m8760a(String str, int i) {
        NBSAndroidAgentImpl impl = NBSAgent.getImpl();
        HarvestConfiguration m9150m = impl != null ? impl.m9150m() : null;
        if (m9150m == null || !m8759a(str, i, m9150m.getIgnoreErrRules())) {
            return i;
        }
        return 200;
    }
}
