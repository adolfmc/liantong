package com.huawei.hms.framework.network.grs.p217g;

import android.text.TextUtils;
import java.util.HashSet;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.huawei.hms.framework.network.grs.g.i */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C4945i {
    /* renamed from: a */
    public static String m14885a(String str, String str2) {
        return !str.equals(str2) ? m14884b(str, str2) : str;
    }

    /* renamed from: b */
    private static String m14884b(String str, String str2) {
        HashSet<String> hashSet = new HashSet();
        if (!TextUtils.isEmpty(str)) {
            JSONArray jSONArray = new JSONObject(str).getJSONArray("services");
            for (int i = 0; i < jSONArray.length(); i++) {
                hashSet.add(jSONArray.getString(i));
            }
        }
        if (!TextUtils.isEmpty(str2)) {
            JSONArray jSONArray2 = new JSONObject(str2).getJSONArray("services");
            for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                hashSet.add(jSONArray2.getString(i2));
            }
        }
        if (hashSet.isEmpty()) {
            return "";
        }
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray3 = new JSONArray();
        for (String str3 : hashSet) {
            jSONArray3.put(str3);
        }
        jSONObject.put("services", jSONArray3);
        return jSONObject.toString();
    }
}
