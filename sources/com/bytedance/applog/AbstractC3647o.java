package com.bytedance.applog;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.bytedance.applog.o */
/* loaded from: E:\10762272_dexfile_execute.dex */
public abstract class AbstractC3647o {

    /* renamed from: a */
    public HashSet<String> f8619a;

    /* renamed from: b */
    public HashMap<String, HashSet<String>> f8620b;

    public AbstractC3647o(HashSet<String> hashSet, HashMap<String, HashSet<String>> hashMap) {
        this.f8619a = hashSet;
        this.f8620b = hashMap;
    }

    /* renamed from: a */
    public static final AbstractC3647o m17194a(Context context, JSONObject jSONObject) {
        JSONObject optJSONObject;
        try {
            SharedPreferences.Editor edit = context.getSharedPreferences("sp_filter_name", 0).edit();
            edit.clear().commit();
            if (jSONObject == null || !jSONObject.has("event_list") || (optJSONObject = jSONObject.optJSONObject("event_list")) == null) {
                return null;
            }
            int optInt = optJSONObject.optInt("is_block", 0);
            edit.putInt("is_block", optInt);
            HashSet hashSet = new HashSet();
            JSONArray optJSONArray = optJSONObject.optJSONArray("events");
            if (optJSONArray != null && optJSONArray.length() > 0) {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    String optString = optJSONArray.optString(i);
                    if (!TextUtils.isEmpty(optString)) {
                        hashSet.add(optString);
                    }
                }
            }
            if (hashSet.size() > 0) {
                edit.putStringSet("events", hashSet);
            }
            HashMap hashMap = new HashMap();
            JSONObject optJSONObject2 = optJSONObject.optJSONObject("params");
            if (optJSONObject2 != null) {
                Iterator<String> keys = optJSONObject2.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    if (!TextUtils.isEmpty(next)) {
                        HashSet hashSet2 = new HashSet();
                        JSONArray optJSONArray2 = optJSONObject2.optJSONArray(next);
                        if (optJSONArray2 != null && optJSONArray2.length() > 0) {
                            for (int i2 = 0; i2 < optJSONArray2.length(); i2++) {
                                String optString2 = optJSONArray2.optString(i2);
                                if (!TextUtils.isEmpty(optString2)) {
                                    hashSet2.add(optString2);
                                }
                            }
                        }
                        if (hashSet2.size() > 0) {
                            hashMap.put(next, hashSet2);
                        }
                    }
                }
            }
            if (hashMap.size() > 0) {
                for (Map.Entry entry : hashMap.entrySet()) {
                    edit.putStringSet((String) entry.getKey(), (Set) entry.getValue());
                }
            }
            edit.commit();
            return optInt > 0 ? new C3671q(hashSet, hashMap) : new C3657p(hashSet, hashMap);
        } catch (Throwable unused) {
            return null;
        }
    }

    /* renamed from: a */
    public abstract boolean mo17142a(String str);

    /* renamed from: a */
    public abstract boolean mo17141a(HashSet<String> hashSet, String str);
}
