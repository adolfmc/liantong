package com.unionpay;

import com.unionpay.utils.C10915b;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.unionpay.k */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
final class C10761k implements InterfaceC10740aa {

    /* renamed from: a */
    final /* synthetic */ UPPayWapActivity f20690a;

    C10761k(UPPayWapActivity uPPayWapActivity) {
        this.f20690a = uPPayWapActivity;
    }

    @Override // com.unionpay.InterfaceC10740aa
    /* renamed from: a */
    public final void mo5871a(String str, InterfaceC10741ab interfaceC10741ab) {
        String m5970b;
        String m5970b2;
        String m5969b;
        try {
            JSONArray jSONArray = new JSONArray(str);
            if (jSONArray.length() <= 0) {
                if (interfaceC10741ab != null) {
                    m5970b2 = UPPayWapActivity.m5970b("1", "Parameter error", (String) null);
                    interfaceC10741ab.mo5826a(m5970b2);
                    return;
                }
                return;
            }
            JSONObject jSONObject = new JSONObject();
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                String string = jSONObject2.getString("packageName");
                jSONObject.put(string, C10915b.m5852b(this.f20690a, string, jSONObject2.getString("packageSign"), jSONObject2.getString("supportVersion")) ? "1" : "0");
            }
            if (interfaceC10741ab != null) {
                m5969b = UPPayWapActivity.m5969b("0", "success", jSONObject);
                interfaceC10741ab.mo5826a(m5969b);
            }
        } catch (Exception e) {
            if (interfaceC10741ab != null) {
                m5970b = UPPayWapActivity.m5970b("1", e.getMessage(), (String) null);
                interfaceC10741ab.mo5826a(m5970b);
            }
        }
    }
}
