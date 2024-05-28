package com.megvii.lv5;

import com.megvii.lv5.sdk.manager.MegLiveDetectConfig;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: Proguard */
/* renamed from: com.megvii.lv5.c1 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5394c1 implements InterfaceC5546r0 {

    /* renamed from: a */
    public final /* synthetic */ String f12411a;

    /* renamed from: b */
    public final /* synthetic */ C5435f1 f12412b;

    public C5394c1(C5435f1 c5435f1, String str) {
        this.f12412b = c5435f1;
        this.f12411a = str;
    }

    @Override // com.megvii.lv5.InterfaceC5546r0
    /* renamed from: a */
    public void mo12907a(int i, byte[] bArr) {
        String str;
        C5435f1 c5435f1 = this.f12412b;
        c5435f1.f12594e.getBiztoken();
        c5435f1.getClass();
        String str2 = "";
        try {
            str = new String(bArr);
        } catch (Throwable unused) {
        }
        try {
            String str3 = "onFailure: response=" + str;
            String str4 = "authAndConfig onFailure: responseBody = " + new String(bArr);
            C5402d.f12429a = "liveness-sdk";
            String str5 = "fail_get_license_and_config:";
            String biztoken = c5435f1.f12594e.getBiztoken();
            JSONObject jSONObject = null;
            if (!C5402d.f12432d || str5.contains("fail_detect")) {
                try {
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("type", "track");
                    jSONObject2.put("project", C5402d.f12429a);
                    jSONObject2.put("event_id", UUID.randomUUID().toString());
                    jSONObject2.put("time", System.currentTimeMillis());
                    jSONObject2.put("event", str5);
                    JSONObject jSONObject3 = new JSONObject();
                    jSONObject3.put("liveness", 4);
                    jSONObject3.put("biz_token", biztoken);
                    jSONObject3.put("try_times", 0);
                    int i2 = C5402d.f12431c + 1;
                    C5402d.f12431c = i2;
                    jSONObject3.put("index", i2);
                    jSONObject2.put("properties", jSONObject3);
                    C5402d.f12430b = str5;
                    jSONObject = jSONObject2;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            C5399c3.m13606a(jSONObject);
            c5435f1.m13540a(i, bArr);
        } catch (Throwable unused2) {
            str2 = str;
            c5435f1.m13541a(1005, String.format("{%s}", str2));
        }
    }

    @Override // com.megvii.lv5.InterfaceC5546r0
    /* renamed from: a */
    public void mo12906a(String str) {
        this.f12412b.m13537a(str, this.f12411a, new MegLiveDetectConfig());
    }
}
