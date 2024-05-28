package com.networkbench.agent.impl.instrumentation;

import com.networkbench.agent.impl.harvest.type.MetricCategory;
import com.networkbench.agent.impl.util.C6653u;
import java.util.ArrayList;
import java.util.Arrays;
import org.json.JSONArray;
import org.json.JSONException;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class NBSJSONArrayInstrumentation {
    private static final ArrayList<String> categoryParams = new ArrayList<>(Arrays.asList("category", MetricCategory.class.getName(), "JSON"));

    @Deprecated
    /* renamed from: a */
    void m9882a() {
    }

    public static JSONArray init(String str) throws JSONException {
        try {
            NBSTraceEngine.enterMethod(C6653u.m8731b() + "JSONArray#<init>", categoryParams);
            JSONArray jSONArray = new JSONArray(str);
            NBSTraceEngine.exitMethod();
            return jSONArray;
        } catch (JSONException e) {
            NBSTraceEngine.exitMethod();
            throw e;
        }
    }

    @NBSReplaceCallSite(scope = "org.json.JSONArray")
    public static String toString(JSONArray jSONArray) {
        NBSTraceEngine.enterMethod(C6653u.m8731b() + "JSONArray#toString", categoryParams);
        String jSONArray2 = jSONArray.toString();
        NBSTraceEngine.exitMethod();
        return jSONArray2;
    }

    @NBSReplaceCallSite(scope = "org.json.JSONArray")
    public static String toString(JSONArray jSONArray, int i) throws JSONException {
        try {
            NBSTraceEngine.enterMethod(C6653u.m8731b() + "JSONArray#toString", categoryParams);
            String jSONArray2 = jSONArray.toString(i);
            NBSTraceEngine.exitMethod();
            return jSONArray2;
        } catch (JSONException e) {
            NBSTraceEngine.exitMethod();
            throw e;
        }
    }
}
