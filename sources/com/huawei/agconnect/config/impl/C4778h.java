package com.huawei.agconnect.config.impl;

import android.util.Log;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@NBSInstrumented
/* renamed from: com.huawei.agconnect.config.impl.h */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
class C4778h implements InterfaceC4774d {

    /* renamed from: a */
    private final JSONObject f10780a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C4778h(InputStream inputStream, String str) {
        this.f10780a = m15412a(inputStream);
        m15411a(str);
    }

    /* renamed from: a */
    private JSONObject m15412a(InputStream inputStream) {
        String str;
        String str2;
        if (inputStream != null) {
            try {
                return new JSONObject(Utils.toString(inputStream, "UTF-8"));
            } catch (IOException unused) {
                str = "InputStreamReader";
                str2 = "IOException when reading the 'Config' from InputStream.";
                Log.e(str, str2);
                return new JSONObject();
            } catch (JSONException unused2) {
                str = "InputStreamReader";
                str2 = "JSONException when reading the 'Config' from InputStream.";
                Log.e(str, str2);
                return new JSONObject();
            }
        }
        return new JSONObject();
    }

    /* renamed from: a */
    private void m15411a(String str) {
        try {
            JSONObject m15409b = m15409b(str);
            if (m15409b == null) {
                return;
            }
            String mo15400a = mo15400a("/configuration_version", "");
            BigDecimal bigDecimal = new BigDecimal("0.0");
            try {
                bigDecimal = BigDecimal.valueOf(Double.parseDouble(mo15400a));
            } catch (NumberFormatException unused) {
                Log.d("InputStreamReader", "configuration_version to double error");
            }
            if (bigDecimal.compareTo(new BigDecimal("2.0")) == 0) {
                this.f10780a.getJSONObject("client").put("app_id", m15409b.getString("app_id"));
            } else if (bigDecimal.compareTo(new BigDecimal("3.0")) >= 0) {
                Iterator<String> keys = m15409b.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    if (!"package_name".equals(next)) {
                        m15410a(next, m15409b.get(next), this.f10780a);
                    }
                }
            }
        } catch (JSONException unused2) {
            Log.d("InputStreamReader", "JSONException when reading the 'appInfos' from InputStream.");
        }
    }

    /* renamed from: a */
    private void m15410a(String str, Object obj, JSONObject jSONObject) throws JSONException {
        if (str == null || obj == null || jSONObject == null) {
            return;
        }
        if (!(obj instanceof JSONObject)) {
            jSONObject.put(str, obj);
            return;
        }
        JSONObject jSONObject2 = (JSONObject) obj;
        Iterator<String> keys = jSONObject2.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            m15410a(next, jSONObject2.get(next), jSONObject.getJSONObject(str));
        }
    }

    /* renamed from: b */
    private JSONObject m15409b(String str) throws JSONException {
        JSONArray jSONArray = this.f10780a.getJSONArray("appInfos");
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObject = jSONArray.getJSONObject(i);
            if (jSONObject.getString("package_name").equals(str)) {
                return jSONObject;
            }
        }
        return null;
    }

    @Override // com.huawei.agconnect.config.impl.InterfaceC4774d
    /* renamed from: a */
    public String mo15400a(String str, String str2) {
        if (str.endsWith("/")) {
            return str2;
        }
        String[] split = str.split("/");
        try {
            JSONObject jSONObject = this.f10780a;
            for (int i = 1; i < split.length; i++) {
                if (i == split.length - 1) {
                    str = jSONObject.get(split[i]).toString();
                    return str;
                }
                jSONObject = jSONObject.getJSONObject(split[i]);
            }
        } catch (JSONException unused) {
            Log.w("InputStreamReader", "JSONException when reading 'path': " + str);
        }
        return str2;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("InputStreamReader{config=");
        JSONObject jSONObject = this.f10780a;
        sb.append((!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject)).hashCode());
        sb.append('}');
        return sb.toString();
    }
}
