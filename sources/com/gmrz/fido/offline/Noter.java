package com.gmrz.fido.offline;

import android.content.Context;
import com.gmrz.appsdk.recorder.api.Record;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* renamed from: com.gmrz.fido.offline.d */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class Noter {
    /* renamed from: a */
    public static void m15736a(Context context, Record.OPERATION operation, Record.ExcType excType, String str, String str2, String str3) {
        RecordUtil.m15733a(context).m15731a(new Record(operation, excType, str, str2, str3));
    }

    /* renamed from: a */
    public static void m15737a(Context context) {
        RecordUtil.m15733a(context).m15734a();
    }

    /* renamed from: a */
    public static String m15735a(Context context, boolean z) {
        JSONArray jSONArray = new JSONArray();
        if (z) {
            List<List<Record>> m15730b = RecordUtil.m15733a(context).m15730b();
            if (m15730b == null || m15730b.size() == 0) {
                return null;
            }
            for (List<Record> list : m15730b) {
                JSONArray jSONArray2 = new JSONArray();
                for (Record record : list) {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("timestamp", record.f10345a);
                    jSONObject.put("operation", record.f10346b);
                    jSONObject.put("type", record.f10347c);
                    jSONObject.put("description", record.f10348d);
                    jSONObject.put("message", record.f10349e);
                    jSONArray2.put(jSONObject);
                }
                jSONArray.put(jSONArray2);
            }
        } else {
            List<Record> m15729c = RecordUtil.m15733a(context).m15729c();
            if (m15729c == null || m15729c.size() == 0) {
                return null;
            }
            JSONArray jSONArray3 = new JSONArray();
            for (Record record2 : m15729c) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("timestamp", record2.f10345a);
                jSONObject2.put("operation", record2.f10346b);
                jSONObject2.put("type", record2.f10347c);
                jSONObject2.put("description", record2.f10348d);
                jSONObject2.put("message", record2.f10349e);
                jSONArray3.put(jSONObject2);
            }
            jSONArray.put(jSONArray3);
        }
        JSONObject jSONObject3 = new JSONObject();
        jSONObject3.put("CERT_SDK_VERSION", "1.3");
        jSONObject3.put("OS", "Android");
        jSONObject3.put("LOG", jSONArray);
        return !(jSONObject3 instanceof JSONObject) ? jSONObject3.toString() : NBSJSONObjectInstrumentation.toString(jSONObject3);
    }
}
