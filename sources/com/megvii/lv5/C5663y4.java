package com.megvii.lv5;

import com.megvii.lv5.C5668z3;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import java.io.UnsupportedEncodingException;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: Proguard */
@NBSInstrumented
/* renamed from: com.megvii.lv5.y4 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5663y4 extends AbstractC5671z4<JSONObject> {
    public C5663y4(int i, String str, JSONObject jSONObject, C5668z3.InterfaceC5670b<JSONObject> interfaceC5670b, C5668z3.InterfaceC5669a interfaceC5669a) {
        super(i, str, jSONObject == null ? null : !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject), interfaceC5670b, interfaceC5669a);
    }

    public C5663y4(String str, JSONObject jSONObject, C5668z3.InterfaceC5670b<JSONObject> interfaceC5670b, C5668z3.InterfaceC5669a interfaceC5669a) {
        this(jSONObject == null ? 0 : 1, str, jSONObject, interfaceC5670b, interfaceC5669a);
    }

    @Override // com.megvii.lv5.AbstractC5652x3
    /* renamed from: a */
    public C5668z3<JSONObject> mo12889a(C5622u3 c5622u3) {
        try {
            return new C5668z3<>(new JSONObject(new String(c5622u3.f13746b, C5527o2.m13243a(c5622u3.f13747c, "utf-8"))), C5527o2.m13248a(c5622u3));
        } catch (UnsupportedEncodingException e) {
            return new C5668z3<>(new C5639w3(e));
        } catch (JSONException e2) {
            return new C5668z3<>(new C5639w3(e2));
        }
    }
}
