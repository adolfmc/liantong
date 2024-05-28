package com.networkbench.agent.impl.instrumentation;

import com.networkbench.agent.impl.harvest.type.MetricCategory;
import com.networkbench.agent.impl.util.C6653u;
import java.util.ArrayList;
import java.util.Arrays;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class NBSObjectInstrumentation {
    private static final ArrayList<String> categoryParams = new ArrayList<>(Arrays.asList("category", MetricCategory.class.getName(), "JSON"));

    @Deprecated
    /* renamed from: a */
    void m9879a() {
    }

    public static JSONObject init(String str) throws JSONException {
        try {
            NBSTraceEngine.enterMethod(C6653u.m8731b() + "JSONObject#<init>", categoryParams);
            JSONObject jSONObject = new JSONObject(str);
            NBSTraceEngine.exitMethod();
            return jSONObject;
        } catch (JSONException e) {
            NBSTraceEngine.exitMethod();
            throw e;
        }
    }

    @NBSReplaceCallSite(scope = "org.json.JSONObject")
    public static String toString(JSONObject jSONObject) {
        NBSTraceEngine.enterMethod(C6653u.m8731b() + "JSONObject#toString", categoryParams);
        String jSONObject2 = jSONObject.toString();
        NBSTraceEngine.exitMethod();
        return jSONObject2;
    }

    @NBSReplaceCallSite(scope = "org.json.JSONObject")
    public static String toString(JSONObject jSONObject, int i) throws JSONException {
        NBSTraceEngine.enterMethod(C6653u.m8731b() + "JSONObject#toString", categoryParams);
        try {
            String jSONObject2 = jSONObject.toString(i);
            NBSTraceEngine.exitMethod();
            return jSONObject2;
        } catch (JSONException e) {
            NBSTraceEngine.exitMethod();
            throw e;
        }
    }
}
