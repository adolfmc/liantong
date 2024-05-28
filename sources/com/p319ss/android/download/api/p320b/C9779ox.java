package com.p319ss.android.download.api.p320b;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.p319ss.android.socialbase.downloader.setting.DownloadSetting;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.ss.android.download.api.b.ox */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C9779ox {
    /* renamed from: mb */
    public static long m7968mb(JSONObject jSONObject, String str) {
        if (jSONObject == null) {
            return 0L;
        }
        try {
            return Long.valueOf(jSONObject.optString(str)).longValue();
        } catch (NumberFormatException unused) {
            return 0L;
        }
    }

    /* renamed from: mb */
    public static boolean m7970mb(DownloadSetting downloadSetting, String str) {
        if (downloadSetting == null || downloadSetting.optInt("apk_update_handler_enable", 1) != 1) {
            return false;
        }
        return "application/ttpatch".equals(str);
    }

    /* renamed from: mb */
    public static JSONObject m7967mb(JSONObject jSONObject, JSONObject jSONObject2) {
        if (jSONObject != null && jSONObject2 != null) {
            try {
                Iterator<String> keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    jSONObject2.put(next, jSONObject.get(next));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return jSONObject2;
    }

    @NonNull
    /* renamed from: mb */
    public static JSONObject m7969mb(JSONObject jSONObject) {
        return m7967mb(jSONObject, new JSONObject());
    }

    @NonNull
    /* renamed from: mb */
    public static JSONObject m7965mb(JSONObject... jSONObjectArr) {
        JSONObject jSONObject = new JSONObject();
        if (jSONObjectArr == null || jSONObjectArr.length == 0) {
            return jSONObject;
        }
        for (JSONObject jSONObject2 : jSONObjectArr) {
            if (jSONObject2 != null) {
                m7967mb(jSONObject2, jSONObject);
            }
        }
        return jSONObject;
    }

    /* renamed from: mb */
    public static String m7966mb(String... strArr) {
        for (String str : strArr) {
            if (!TextUtils.isEmpty(str)) {
                return str;
            }
        }
        return "";
    }
}
