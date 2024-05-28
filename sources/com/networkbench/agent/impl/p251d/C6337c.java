package com.networkbench.agent.impl.p251d;

import android.text.TextUtils;
import com.networkbench.agent.impl.harvest.Harvest;
import com.networkbench.agent.impl.p243c.C6304h;
import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.agent.impl.util.C6638h;
import com.networkbench.agent.impl.util.C6642k;
import java.util.Iterator;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.d.c */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class C6337c {

    /* renamed from: a */
    private static C6337c f15961a;

    /* renamed from: b */
    private final InterfaceC6393e f15962b = C6394f.m10150a();

    private C6337c() {
    }

    /* renamed from: a */
    public static C6337c m10342a() {
        C6337c c6337c = f15961a;
        if (c6337c == null) {
            C6337c c6337c2 = new C6337c();
            f15961a = c6337c2;
            return c6337c2;
        }
        return c6337c;
    }

    /* renamed from: a */
    public String m10341a(String str, String str2) {
        String whiteList = Harvest.getInstance().getConfiguration().getWhiteList();
        if (TextUtils.isEmpty(whiteList) || TextUtils.isEmpty(str2)) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(whiteList);
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                if (str.equalsIgnoreCase(next)) {
                    String[] split = jSONObject.getString(next).split(",");
                    if (split != null && split.length != 0) {
                        for (String str3 : split) {
                            if (str2.equalsIgnoreCase(str3)) {
                                this.f15962b.mo10122a("estimateHijacks---not found hijacking :" + str);
                                return null;
                            }
                        }
                        try {
                            C6304h c6304h = new C6304h();
                            c6304h.m10518a(str);
                            c6304h.m10516b(str2);
                            c6304h.m10519a(C6642k.m8906d(C6638h.m8963w().m9076K()));
                            Harvest.getInstance().getHarvestData().getNetworkPerfMetrics().getHijackData().m10521a(c6304h);
                        } catch (Exception e) {
                            this.f15962b.mo10122a("add Hijacks data happens error!" + e.getMessage());
                        }
                        this.f15962b.mo10122a("found hijack:" + str + " ,redirect ip to:" + split[0]);
                        return split[0];
                    }
                    return null;
                }
            }
        } catch (Exception e2) {
            this.f15962b.mo10122a("estimateHijacks happens error!" + e2.getMessage());
        }
        return null;
    }
}
