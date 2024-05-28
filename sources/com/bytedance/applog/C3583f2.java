package com.bytedance.applog;

import android.text.TextUtils;
import android.util.Pair;
import com.bytedance.applog.network.INetworkClient;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

@NBSInstrumented
/* renamed from: com.bytedance.applog.f2 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C3583f2 implements INetworkClient {
    @Override // com.bytedance.applog.network.INetworkClient
    public String get(String str, Map<String, String> map) {
        return C3607j1.m17268a(0, str, (HashMap) map, null);
    }

    @Override // com.bytedance.applog.network.INetworkClient
    public String post(String str, List<Pair<String, String>> list) {
        JSONObject jSONObject = new JSONObject();
        if (list != null) {
            try {
                for (Pair<String, String> pair : list) {
                    jSONObject.put((String) pair.first, pair.second);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return post(str, (!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject)).getBytes(), "application/json; charset=utf-8");
    }

    @Override // com.bytedance.applog.network.INetworkClient
    public String post(String str, byte[] bArr, String str2) {
        HashMap hashMap = new HashMap();
        if (!TextUtils.isEmpty(str2)) {
            hashMap.put("Content-Type", str2);
        }
        return post(str, bArr, hashMap);
    }

    @Override // com.bytedance.applog.network.INetworkClient
    public String post(String str, byte[] bArr, Map<String, String> map) {
        return C3607j1.m17268a(1, str, (HashMap) map, bArr);
    }

    @Override // com.bytedance.applog.network.INetworkClient
    public byte[] postStream(String str, byte[] bArr, Map<String, String> map) {
        return C3607j1.m17267a(1, str, (HashMap) map, bArr, 1).f8528b;
    }
}
