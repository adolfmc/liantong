package com.megvii.lv5;

import com.megvii.lv5.C5668z3;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import org.json.JSONObject;

/* compiled from: Proguard */
@NBSInstrumented
/* renamed from: com.megvii.lv5.v0 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5626v0 implements C5668z3.InterfaceC5670b<JSONObject> {

    /* renamed from: a */
    public final /* synthetic */ InterfaceC5546r0 f13754a;

    public C5626v0(C5658y0 c5658y0, InterfaceC5546r0 interfaceC5546r0) {
        this.f13754a = interfaceC5546r0;
    }

    @Override // com.megvii.lv5.C5668z3.InterfaceC5670b
    /* renamed from: a */
    public void mo12873a(JSONObject jSONObject) {
        JSONObject jSONObject2 = jSONObject;
        boolean z = jSONObject2 instanceof JSONObject;
        C5628v2.m12958b("response Suc", !z ? jSONObject2.toString() : NBSJSONObjectInstrumentation.toString(jSONObject2));
        InterfaceC5546r0 interfaceC5546r0 = this.f13754a;
        if (interfaceC5546r0 != null) {
            interfaceC5546r0.mo12906a(!z ? jSONObject2.toString() : NBSJSONObjectInstrumentation.toString(jSONObject2));
        }
    }
}
