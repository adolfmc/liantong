package com.baidu.lbsapi.auth;

import android.content.Context;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* renamed from: com.baidu.lbsapi.auth.c */
/* loaded from: E:\10201592_dexfile_execute.dex */
class C2586c {

    /* renamed from: a */
    private Context f4969a;

    /* renamed from: b */
    private HashMap<String, String> f4970b = null;

    /* renamed from: c */
    private InterfaceC2587a<String> f4971c = null;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.lbsapi.auth.c$a */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface InterfaceC2587a<Result> {
        /* renamed from: a */
        void mo19646a(Result result);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public C2586c(Context context) {
        this.f4969a = context;
    }

    /* renamed from: a */
    private HashMap<String, String> m19662a(HashMap<String, String> hashMap) {
        HashMap<String, String> hashMap2 = new HashMap<>();
        for (String str : hashMap.keySet()) {
            String str2 = str.toString();
            hashMap2.put(str2, hashMap.get(str2));
        }
        return hashMap2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m19663a(String str) {
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
        InterfaceC2587a<String> interfaceC2587a = this.f4971c;
        if (interfaceC2587a != null) {
            interfaceC2587a.mo19646a(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public void m19661a(HashMap<String, String> hashMap, InterfaceC2587a<String> interfaceC2587a) {
        this.f4970b = m19662a(hashMap);
        this.f4971c = interfaceC2587a;
        new Thread(new RunnableC2588d(this)).start();
    }
}
