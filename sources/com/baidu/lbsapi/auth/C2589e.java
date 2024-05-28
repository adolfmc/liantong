package com.baidu.lbsapi.auth;

import android.content.Context;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* renamed from: com.baidu.lbsapi.auth.e */
/* loaded from: E:\10201592_dexfile_execute.dex */
class C2589e {

    /* renamed from: a */
    private Context f4973a;

    /* renamed from: b */
    private List<HashMap<String, String>> f4974b = null;

    /* renamed from: c */
    private InterfaceC2590a<String> f4975c = null;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.lbsapi.auth.e$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface InterfaceC2590a<Result> {
        /* renamed from: a */
        void mo19644a(Result result);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public C2589e(Context context) {
        this.f4973a = context;
    }

    /* renamed from: a */
    private List<HashMap<String, String>> m19656a(HashMap<String, String> hashMap, String[] strArr) {
        ArrayList arrayList = new ArrayList();
        if (strArr == null || strArr.length <= 0) {
            HashMap hashMap2 = new HashMap();
            for (String str : hashMap.keySet()) {
                String str2 = str.toString();
                hashMap2.put(str2, hashMap.get(str2));
            }
            arrayList.add(hashMap2);
        } else {
            for (String str3 : strArr) {
                HashMap hashMap3 = new HashMap();
                for (String str4 : hashMap.keySet()) {
                    String str5 = str4.toString();
                    hashMap3.put(str5, hashMap.get(str5));
                }
                hashMap3.put("mcode", str3);
                arrayList.add(hashMap3);
            }
        }
        return arrayList;
    }

    /* renamed from: a */
    private void m19657a(String str) {
        JSONObject jSONObject;
        if (str == null) {
            str = "";
        }
        try {
            jSONObject = new JSONObject(str);
            if (!jSONObject.has("status")) {
                jSONObject.put("status", -1);
            }
        } catch (JSONException unused) {
            jSONObject = new JSONObject();
            try {
                jSONObject.put("status", -1);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        InterfaceC2590a<String> interfaceC2590a = this.f4975c;
        if (interfaceC2590a != null) {
            interfaceC2590a.mo19644a(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m19654a(List<HashMap<String, String>> list) {
        int i;
        C2583a.m19676a("syncConnect start Thread id = " + String.valueOf(Thread.currentThread().getId()));
        if (list == null || list.size() == 0) {
            C2583a.m19674c("syncConnect failed,params list is null or size is 0");
            return;
        }
        ArrayList arrayList = new ArrayList();
        int i2 = 0;
        while (i2 < list.size()) {
            C2583a.m19676a("syncConnect resuest " + i2 + "  start!!!");
            HashMap<String, String> hashMap = list.get(i2);
            C2592g c2592g = new C2592g(this.f4973a);
            if (c2592g.m19653a()) {
                String m19651a = c2592g.m19651a(hashMap);
                if (m19651a == null) {
                    m19651a = "";
                }
                C2583a.m19676a("syncConnect resuest " + i2 + "  result:" + m19651a);
                arrayList.add(m19651a);
                try {
                    JSONObject jSONObject = new JSONObject(m19651a);
                    if (jSONObject.has("status") && jSONObject.getInt("status") == 0) {
                        C2583a.m19676a("auth end and break");
                        m19657a(m19651a);
                        return;
                    }
                } catch (JSONException unused) {
                    C2583a.m19676a("continue-------------------------------");
                }
            } else {
                C2583a.m19676a("Current network is not available.");
                arrayList.add(ErrorMessage.m19698a("Current network is not available."));
            }
            C2583a.m19676a("syncConnect end");
            i2++;
        }
        C2583a.m19676a("--iiiiii:" + i2 + "<><>paramList.size():" + list.size() + "<><>authResults.size():" + arrayList.size());
        if (list.size() <= 0 || i2 != list.size() || arrayList.size() <= 0 || i2 != arrayList.size() || i2 - 1 <= 0) {
            return;
        }
        try {
            JSONObject jSONObject2 = new JSONObject((String) arrayList.get(i));
            if (!jSONObject2.has("status") || jSONObject2.getInt("status") == 0) {
                return;
            }
            C2583a.m19676a("i-1 result is not 0,return first result");
            m19657a((String) arrayList.get(0));
        } catch (JSONException e) {
            m19657a(ErrorMessage.m19698a("JSONException:" + e.getMessage()));
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public void m19655a(HashMap<String, String> hashMap, String[] strArr, InterfaceC2590a<String> interfaceC2590a) {
        this.f4974b = m19656a(hashMap, strArr);
        this.f4975c = interfaceC2590a;
        new Thread(new RunnableC2591f(this)).start();
    }
}
