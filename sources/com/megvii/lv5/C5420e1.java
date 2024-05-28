package com.megvii.lv5;

import android.text.TextUtils;
import com.megvii.lv5.sdk.listener.MegliveRequestFinishCallback;
import com.megvii.lv5.sdk.manager.MegLiveDetectConfig;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import java.util.Timer;
import org.json.JSONObject;

/* compiled from: Proguard */
@NBSInstrumented
/* renamed from: com.megvii.lv5.e1 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5420e1 implements MegliveRequestFinishCallback {

    /* renamed from: a */
    public final /* synthetic */ String f12539a;

    /* renamed from: b */
    public final /* synthetic */ C5435f1 f12540b;

    public C5420e1(C5435f1 c5435f1, String str) {
        this.f12540b = c5435f1;
        this.f12539a = str;
    }

    @Override // com.megvii.lv5.sdk.listener.MegliveRequestFinishCallback
    public void onFinish(String str) {
        C5435f1 c5435f1 = this.f12540b;
        String str2 = this.f12539a;
        c5435f1.getClass();
        try {
            Timer timer = c5435f1.f12590a;
            if (timer != null) {
                timer.cancel();
                c5435f1.f12590a = null;
            }
            JSONObject optJSONObject = new JSONObject(str).optJSONObject("data");
            if (optJSONObject == null) {
                c5435f1.m13541a(1002, String.format("ILLEGAL_PARAMETER:{%s}", "data_is_null"));
                return;
            }
            String optString = optJSONObject.optString("result", null);
            if (!"null".equals(optString) && !TextUtils.isEmpty(optString)) {
                c5435f1.m13537a(!(optJSONObject instanceof JSONObject) ? optJSONObject.toString() : NBSJSONObjectInstrumentation.toString(optJSONObject), str2, new MegLiveDetectConfig());
                return;
            }
            c5435f1.m13540a(400, (!(optJSONObject instanceof JSONObject) ? optJSONObject.toString() : NBSJSONObjectInstrumentation.toString(optJSONObject)).getBytes());
        } catch (Throwable unused) {
            c5435f1.m13541a(1002, String.format("ILLEGAL_PARAMETER:{%s}", "response_exception:" + str));
        }
    }
}
