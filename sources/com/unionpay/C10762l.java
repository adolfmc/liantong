package com.unionpay;

import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import org.json.JSONObject;

/* renamed from: com.unionpay.l */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
final class C10762l implements InterfaceC10740aa {

    /* renamed from: a */
    final /* synthetic */ UPPayWapActivity f20691a;

    C10762l(UPPayWapActivity uPPayWapActivity) {
        this.f20691a = uPPayWapActivity;
    }

    @Override // com.unionpay.InterfaceC10740aa
    /* renamed from: a */
    public final void mo5871a(String str, InterfaceC10741ab interfaceC10741ab) {
        String m5970b;
        String m5970b2;
        String m5970b3;
        String m5970b4;
        String str2;
        String str3;
        try {
            this.f20691a.f20634i = interfaceC10741ab;
            JSONObject jSONObject = new JSONObject(str);
            String string = jSONObject.getString("packageName");
            String string2 = jSONObject.getString("type");
            String optString = jSONObject.optString("openParams");
            String optString2 = jSONObject.optString("tn");
            if ("0".equals(string2)) {
                if (TextUtils.isEmpty(string) || TextUtils.isEmpty(optString2)) {
                    if (interfaceC10741ab != null) {
                        m5970b4 = UPPayWapActivity.m5970b("1", "Parameter error", (String) null);
                        interfaceC10741ab.mo5826a(m5970b4);
                        return;
                    }
                    return;
                }
                Intent intent = new Intent();
                intent.setClassName(string, "com.unionpay.uppay.PayActivity");
                intent.putExtra("paydata", optString2);
                str2 = UPPayWapActivity.f20626a;
                str3 = this.f20691a.f20631f;
                intent.putExtra(str2, str3);
                this.f20691a.startActivityForResult(intent, 1);
            } else if (!"2".equals(string2)) {
                if (interfaceC10741ab != null) {
                    m5970b2 = UPPayWapActivity.m5970b("1", "Parameter error", (String) null);
                    interfaceC10741ab.mo5826a(m5970b2);
                }
            } else if (TextUtils.isEmpty(optString)) {
                if (interfaceC10741ab != null) {
                    m5970b3 = UPPayWapActivity.m5970b("1", "Parameter error", (String) null);
                    interfaceC10741ab.mo5826a(m5970b3);
                }
            } else {
                Intent intent2 = new Intent("android.intent.action.VIEW");
                intent2.setData(Uri.parse(optString));
                if (!TextUtils.isEmpty(string)) {
                    intent2.setPackage(string);
                }
                this.f20691a.startActivityForResult(intent2, 1);
            }
        } catch (Exception e) {
            if (interfaceC10741ab != null) {
                m5970b = UPPayWapActivity.m5970b("1", e.getMessage(), (String) null);
                interfaceC10741ab.mo5826a(m5970b);
            }
        }
    }
}
