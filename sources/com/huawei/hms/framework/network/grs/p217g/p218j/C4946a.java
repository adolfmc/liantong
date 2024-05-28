package com.huawei.hms.framework.network.grs.p217g.p218j;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.framework.common.StringUtils;
import com.huawei.hms.framework.network.grs.GrsApp;
import com.huawei.hms.framework.network.grs.p217g.p219k.C4950d;
import com.huawei.hms.framework.network.grs.p220h.C4953c;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.huawei.hms.framework.network.grs.g.j.a */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C4946a {

    /* renamed from: a */
    private static final String f11312a = "a";

    /* renamed from: b */
    private static C4950d f11313b;

    /* renamed from: a */
    public static synchronized C4950d m14883a(Context context) {
        synchronized (C4946a.class) {
            C4950d c4950d = f11313b;
            if (c4950d != null) {
                return c4950d;
            }
            String m14857a = C4953c.m14857a(GrsApp.getInstance().getBrand("/") + "grs_sdk_server_config.json", context);
            ArrayList arrayList = null;
            if (TextUtils.isEmpty(m14857a)) {
                return null;
            }
            try {
                JSONObject jSONObject = new JSONObject(m14857a).getJSONObject("grs_server");
                JSONArray jSONArray = jSONObject.getJSONArray("grs_base_url");
                if (jSONArray != null && jSONArray.length() > 0) {
                    arrayList = new ArrayList();
                    for (int i = 0; i < jSONArray.length(); i++) {
                        arrayList.add(jSONArray.get(i).toString());
                    }
                }
                f11313b = new C4950d();
                f11313b.m14870a(arrayList);
                f11313b.m14868b(jSONObject.getString("grs_query_endpoint_1.0"));
                f11313b.m14871a(jSONObject.getString("grs_query_endpoint_2.0"));
                f11313b.m14872a(jSONObject.getInt("grs_query_timeout"));
            } catch (JSONException e) {
                Logger.m15043w(f11312a, "getGrsServerBean catch JSONException: %s", StringUtils.anonymizeMessage(e.getMessage()));
            }
            return f11313b;
        }
    }
}
