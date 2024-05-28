package com.huawei.hms.hatool;

import android.content.Context;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.huawei.hms.hatool.c1 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C4974c1 {
    /* renamed from: a */
    public static Map<String, List<C4971b1>> m14776a(Context context, String str) {
        if (context == null) {
            return null;
        }
        Map<String, ?> m14769a = C4975d.m14769a(context, str);
        m14770b(m14769a);
        return m14773a(m14769a);
    }

    /* renamed from: a */
    public static Map<String, List<C4971b1>> m14775a(Context context, String str, String str2) {
        Map<String, List<C4971b1>> m14776a;
        Map<String, List<C4971b1>> m14776a2;
        if ("alltype".equals(str2) || TextUtils.isEmpty(str)) {
            C5029v.m14455c("hmsSdk", "read all event records");
            m14776a = m14776a(context, "stat_v2_1");
            m14776a2 = m14776a(context, "cached_v2_1");
        } else {
            String m14584a = AbstractC5010n1.m14584a(str, str2);
            m14776a = m14771b(context, "stat_v2_1", m14584a);
            m14776a2 = m14771b(context, "cached_v2_1", m14584a);
        }
        return m14772a(m14776a, m14776a2);
    }

    /* renamed from: a */
    private static Map<String, List<C4971b1>> m14773a(Map<String, ?> map) {
        HashMap hashMap = new HashMap();
        for (Map.Entry<String, ?> entry : map.entrySet()) {
            String key = entry.getKey();
            if (entry.getValue() instanceof String) {
                m14774a(key, (String) entry.getValue(), hashMap);
            }
        }
        return hashMap;
    }

    /* renamed from: a */
    private static Map<String, List<C4971b1>> m14772a(Map<String, List<C4971b1>> map, Map<String, List<C4971b1>> map2) {
        if (map.size() == 0 && map2.size() == 0) {
            return new HashMap();
        }
        if (map.size() == 0) {
            return map2;
        }
        if (map2.size() == 0) {
            return map;
        }
        HashMap hashMap = new HashMap(map);
        hashMap.putAll(map2);
        return hashMap;
    }

    /* renamed from: a */
    private static void m14774a(String str, String str2, Map<String, List<C4971b1>> map) {
        ArrayList arrayList = new ArrayList();
        try {
            if (TextUtils.isEmpty(str2)) {
                return;
            }
            JSONArray jSONArray = new JSONArray(str2);
            if (jSONArray.length() == 0) {
                return;
            }
            for (int i = 0; i < jSONArray.length(); i++) {
                C4971b1 c4971b1 = new C4971b1();
                try {
                    c4971b1.m14791a(jSONArray.getJSONObject(i));
                    arrayList.add(c4971b1);
                } catch (JSONException unused) {
                    C5029v.m14452e("hmsSdk", "JSON Exception happened when create data for report - readDataToRecord");
                }
            }
            map.put(str, arrayList);
        } catch (JSONException unused2) {
            C5029v.m14452e("hmsSdk", "When events turn to JSONArray,JSON Exception has happened");
        }
    }

    /* renamed from: b */
    public static Map<String, List<C4971b1>> m14771b(Context context, String str, String str2) {
        String m14767a = C4975d.m14767a(context, str, str2, "");
        HashMap hashMap = new HashMap();
        m14774a(str2, m14767a, hashMap);
        return hashMap;
    }

    /* renamed from: b */
    private static void m14770b(Map<String, ?> map) {
        Iterator<Map.Entry<String, ?>> it = map.entrySet().iterator();
        Set<String> m14582a = AbstractC5010n1.m14582a(AbstractC5020q0.m14540b());
        while (it.hasNext()) {
            if (!m14582a.contains(it.next().getKey())) {
                it.remove();
            }
        }
    }
}
