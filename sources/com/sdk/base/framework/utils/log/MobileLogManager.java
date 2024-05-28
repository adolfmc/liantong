package com.sdk.base.framework.utils.log;

import android.net.Uri;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.sdk.p290f.C6994b;
import com.sdk.p300p.C7030d;
import com.sdk.p302r.C7037a;
import java.nio.charset.Charset;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex */
public class MobileLogManager {
    private static final String TAG = "MobileLogManager";
    private static C6994b mobileLog;

    public static C6994b getMobileLog() {
        if (mobileLog == null) {
            init();
        }
        return mobileLog;
    }

    private static JSONObject group$101005Logs() {
        JSONObject jSONObject = new JSONObject();
        mobileLog.getClass();
        jSONObject.put("clientTimeOut", 0);
        jSONObject.put("type", 1);
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("url", 2);
        jSONObject2.put("time", 3);
        jSONObject.put("fieldType", jSONObject2);
        JSONArray jSONArray = new JSONArray();
        for (C6994b.C6995a.C6996a c6996a : mobileLog.f18129b.f18131a) {
            JSONObject jSONObject3 = new JSONObject();
            c6996a.getClass();
            jSONObject3.put("url", "");
            jSONObject3.put("time", 0L);
            jSONArray.put(jSONObject3);
        }
        jSONObject.put("content", jSONArray);
        return jSONObject;
    }

    private static JSONObject group$302001Logs() {
        JSONObject jSONObject = new JSONObject();
        mobileLog.getClass();
        jSONObject.put("clientTimeOut", 0);
        jSONObject.put("type", 2);
        JSONArray jSONArray = new JSONArray();
        for (String str : mobileLog.f18129b.f18132b) {
            jSONArray.put(str);
        }
        jSONObject.put("content", jSONArray);
        return jSONObject;
    }

    private static JSONObject group$302002Logs() {
        JSONObject jSONObject = new JSONObject();
        mobileLog.getClass();
        jSONObject.put("clientTimeOut", 0);
        jSONObject.put("type", 2);
        JSONArray jSONArray = new JSONArray();
        for (String str : mobileLog.f18129b.f18133c) {
            jSONArray.put(str);
        }
        jSONObject.put("content", jSONArray);
        return jSONObject;
    }

    private static JSONObject group$302003Logs() {
        JSONObject jSONObject = new JSONObject();
        mobileLog.getClass();
        jSONObject.put("clientTimeOut", 0);
        jSONObject.put("type", 2);
        JSONArray jSONArray = new JSONArray();
        jSONArray.put(mobileLog.f18129b.f18134d);
        jSONObject.put("content", jSONArray);
        return jSONObject;
    }

    public static String groupStatusLogs(int i) {
        JSONObject group$101005Logs;
        if (i != 101005) {
            switch (i) {
                case 302001:
                    group$101005Logs = group$302001Logs();
                    break;
                case 302002:
                    group$101005Logs = group$302002Logs();
                    break;
                case 302003:
                    group$101005Logs = group$302003Logs();
                    break;
                default:
                    group$101005Logs = null;
                    break;
            }
        } else {
            group$101005Logs = group$101005Logs();
        }
        if (group$101005Logs != null) {
            return !(group$101005Logs instanceof JSONObject) ? group$101005Logs.toString() : NBSJSONObjectInstrumentation.toString(group$101005Logs);
        }
        return "";
    }

    public static void init() {
        mobileLog = new C6994b();
    }

    private static void setRequestSeq(String str) {
        if (C7037a.m8129b(str).booleanValue() && C7037a.m8130a(mobileLog.f18130c).booleanValue()) {
            Uri parse = Uri.parse(str);
            String queryParameter = parse.getQueryParameter("sequenceNumber");
            String queryParameter2 = parse.getQueryParameter("ret_url");
            if (C7037a.m8129b(queryParameter2).booleanValue()) {
                queryParameter = Uri.parse(new String(C7030d.m8137a(queryParameter2), Charset.defaultCharset())).getQueryParameter("seq");
            }
            mobileLog.f18130c = queryParameter;
        }
    }

    public static void status101005(String str, long j) {
    }

    public static void status302001(String str) {
        try {
            List<String> list = mobileLog.f18129b.f18132b;
            list.add(str);
            mobileLog.f18129b.f18132b = list;
        } catch (Throwable unused) {
        }
    }

    public static void status302002(String str) {
        try {
            List<String> list = mobileLog.f18129b.f18133c;
            list.add(str);
            mobileLog.f18129b.f18133c = list;
        } catch (Throwable unused) {
        }
    }

    public static void status302003(String str) {
        try {
            mobileLog.f18129b.f18134d = str;
        } catch (Throwable unused) {
        }
    }
}
