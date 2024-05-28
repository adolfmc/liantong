package com.networkbench.agent.impl.instrumentation;

import android.webkit.JavascriptInterface;
import com.networkbench.agent.impl.NBSAppAgent;
import com.networkbench.agent.impl.harvest.HarvestConfiguration;
import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.agent.impl.p268n.C6529m;
import com.networkbench.agent.impl.util.C6648q;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class NBSJavaScriptBridge {
    private static InterfaceC6393e log = C6394f.m10150a();
    private static String METHOD = "method";
    private static String PARAMS = "params";

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static class NBSWebViewResult {
        public String resultData;
        public String resultType;
    }

    @JavascriptInterface
    public void logDebug(String str) {
    }

    @JavascriptInterface
    public void parseArguments(String str) {
        try {
            PrintStream printStream = System.out;
            printStream.println(str + "jsonInfo");
            toggleDataCollect(str);
        } catch (Throwable th) {
            InterfaceC6393e interfaceC6393e = log;
            interfaceC6393e.mo10115e("error parseArguments e:" + th.getMessage());
        }
    }

    private void toggleDataCollect(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            String str2 = (String) jSONObject.get(METHOD);
            JSONObject jSONObject2 = jSONObject.getJSONObject(PARAMS);
            if (jSONObject2 == null || !str2.equals("setBusinessLine")) {
                return;
            }
            NBSAppAgent.setBusinessLine((String) jSONObject2.get("key"), (String) jSONObject2.get("value"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    static Map<String, Object> m9880a(JSONObject jSONObject) {
        return JsonToHashMap(jSONObject);
    }

    public static Map<String, Object> JsonToHashMap(JSONObject jSONObject) {
        HashMap hashMap = new HashMap();
        try {
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String valueOf = String.valueOf(keys.next());
                hashMap.put(valueOf, jSONObject.get(valueOf).toString());
            }
        } catch (Exception e) {
            System.out.print(e.getLocalizedMessage());
        }
        return hashMap;
    }

    @JavascriptInterface
    public int its() {
        if (HarvestConfiguration.getDefaultHarvestConfiguration() != null) {
            return HarvestConfiguration.getDefaultHarvestConfiguration().getSlowDomThreshold();
        }
        return 2100;
    }

    @JavascriptInterface
    public int sfp() {
        if (HarvestConfiguration.getDefaultHarvestConfiguration() != null) {
            return HarvestConfiguration.getDefaultHarvestConfiguration().getSlowFirstPaintThreshold();
        }
        return 1400;
    }

    @JavascriptInterface
    public int sfs() {
        if (HarvestConfiguration.getDefaultHarvestConfiguration() != null) {
            return HarvestConfiguration.getDefaultHarvestConfiguration().getSlowFirstScreenThreshold();
        }
        return 2800;
    }

    @JavascriptInterface
    public int spt() {
        if (HarvestConfiguration.getDefaultHarvestConfiguration() != null) {
            return HarvestConfiguration.getDefaultHarvestConfiguration().getSlowPageThreshold();
        }
        return 7000;
    }

    @JavascriptInterface
    public static void logJsError(String str, String str2, String str3, String str4, int i, int i2, String str5, String str6, int i3, int i4, String str7, long j, int i5) {
        C6529m.m9514a(str, str2, str3, str4, i, i2, str5, str6, i3, i4, str7, j, i5);
    }

    @JavascriptInterface
    public static void logJsResult(String str) {
        NBSWebViewResult nBSWebViewResult = new NBSWebViewResult();
        nBSWebViewResult.resultType = "pageInfo";
        nBSWebViewResult.resultData = str;
        C6648q.m8781a(nBSWebViewResult);
    }

    @JavascriptInterface
    public static void postMessage(String str) {
        NBSWebViewResult nBSWebViewResult = new NBSWebViewResult();
        nBSWebViewResult.resultType = "browserData";
        nBSWebViewResult.resultData = str;
        C6648q.m8781a(nBSWebViewResult);
    }
}
