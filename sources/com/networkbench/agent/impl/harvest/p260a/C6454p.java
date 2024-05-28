package com.networkbench.agent.impl.harvest.p260a;

import android.text.TextUtils;
import com.networkbench.agent.impl.NBSAgent;
import com.networkbench.agent.impl.harvest.HarvestResponse;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.agent.impl.util.C6653u;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.harvest.a.p */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6454p extends AbstractC6444i {
    @Override // com.networkbench.agent.impl.harvest.p260a.AbstractC6444i
    /* renamed from: a */
    public String mo9948a() {
        return "https://mobile-symbol-upload.tingyun.com/info/android.json";
    }

    public C6454p(String str, boolean z) {
        super(str, z);
        m9968b("UpdateHintUrl");
    }

    @Override // com.networkbench.agent.impl.harvest.p260a.AbstractC6444i
    /* renamed from: a */
    public HarvestResponse mo9947a(String str, HarvestResponse harvestResponse) {
        harvestResponse.setResultMessage(str);
        m9949c(str);
        return super.mo9947a(str, harvestResponse);
    }

    /* renamed from: c */
    private void m9949c(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if ("SUCCESS".equalsIgnoreCase(jSONObject.getString("status"))) {
                JSONObject jSONObject2 = jSONObject.getJSONObject("data");
                if ("android".equalsIgnoreCase(jSONObject2.getString("os"))) {
                    String string = jSONObject2.getString("version");
                    if (TextUtils.isEmpty(string) || !C6653u.m8741a(string, NBSAgent.getVersion())) {
                        return;
                    }
                    InterfaceC6393e interfaceC6393e = this.f16287a;
                    interfaceC6393e.mo10119b("本信息仅在调试模式下显示：\r\n最新SDK版本为" + string + ",请更新。\r\n新版本详情:\r\n" + jSONObject2.getString("releaseNote"));
                }
            }
        } catch (Exception unused) {
        }
    }
}
