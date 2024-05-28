package com.unionpay;

import com.unionpay.utils.UPUtils;
import java.util.Iterator;
import org.json.JSONObject;

/* renamed from: com.unionpay.r */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
final class C10768r implements InterfaceC10740aa {

    /* renamed from: a */
    final /* synthetic */ UPPayWapActivity f20697a;

    C10768r(UPPayWapActivity uPPayWapActivity) {
        this.f20697a = uPPayWapActivity;
    }

    @Override // com.unionpay.InterfaceC10740aa
    /* renamed from: a */
    public final void mo5871a(String str, InterfaceC10741ab interfaceC10741ab) {
        String m5970b;
        String m5970b2;
        try {
            JSONObject jSONObject = new JSONObject(str);
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                UPUtils.m5869a(this.f20697a, jSONObject.getString(next), next);
            }
            if (interfaceC10741ab != null) {
                m5970b2 = UPPayWapActivity.m5970b("0", "success", (String) null);
                interfaceC10741ab.mo5826a(m5970b2);
            }
        } catch (Exception e) {
            if (interfaceC10741ab != null) {
                m5970b = UPPayWapActivity.m5970b("1", e.getMessage(), (String) null);
                interfaceC10741ab.mo5826a(m5970b);
            }
        }
    }
}
