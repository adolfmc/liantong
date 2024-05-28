package com.networkbench.agent.impl.crash;

import android.text.TextUtils;
import com.networkbench.agent.impl.p243c.p244a.C6255f;
import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.C6396h;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.agent.impl.util.C6638h;
import com.networkbench.com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.crash.e */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6325e {

    /* renamed from: a */
    private static Map<String, String> f15924a = new C6326a();

    /* renamed from: b */
    private static InterfaceC6393e f15925b = C6394f.m10150a();

    /* renamed from: c */
    private static volatile List<String> f15926c = new ArrayList();

    /* renamed from: a */
    public static void m10396a(String str, String str2) {
        if (str == null) {
            f15925b.mo10122a("Set User Crash Setting failed, key is null!");
            if (C6638h.m8963w().m8979n()) {
                C6396h.m10140b("Set User Crash Setting failed, key is null!", new Object[0]);
                return;
            }
            return;
        }
        if (TextUtils.isEmpty(str2)) {
            f15924a.put(str, str2);
        } else {
            int length = str2.length();
            if (length > 100) {
                length = 100;
            }
            f15924a.put(str, str2.substring(0, length));
            C6255f.m10808a(str, str2);
        }
        f15925b.mo10122a("setUserCrashSetting : " + f15924a.size());
    }

    /* renamed from: a */
    public static JsonObject m10398a() {
        try {
            InterfaceC6393e interfaceC6393e = f15925b;
            interfaceC6393e.mo10122a("getCustInfo : " + f15924a.size());
            JsonObject jsonObject = new JsonObject();
            for (String str : f15924a.keySet()) {
                jsonObject.addProperty(str, f15924a.get(str));
            }
            return jsonObject;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public static boolean m10397a(String str) {
        return f15926c.contains(str);
    }

    /* renamed from: b */
    public static void m10395b(String str) {
        f15926c.add(str);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.networkbench.agent.impl.crash.e$a */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static class C6326a extends LinkedHashMap<String, String> {

        /* renamed from: a */
        private static final int f15927a = 20;

        @Override // java.util.LinkedHashMap
        protected boolean removeEldestEntry(Map.Entry<String, String> entry) {
            return size() > 20;
        }
    }
}
