package com.p319ss.android.socialbase.appdownloader.p340u;

import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.p319ss.android.socialbase.appdownloader.C10123ko;
import java.lang.reflect.Field;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.ss.android.socialbase.appdownloader.u.mb */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C10153mb {

    /* renamed from: mb */
    private static final HashMap<String, C10123ko.C10124mb> f19630mb = new HashMap<>();

    /* renamed from: mb */
    public static boolean m6557mb(JSONArray jSONArray, String str) {
        if (jSONArray == null || TextUtils.isEmpty(str)) {
            return false;
        }
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            JSONObject optJSONObject = jSONArray.optJSONObject(i);
            if (optJSONObject != null && str.equals(optJSONObject.optString("type")) && m6555mb(optJSONObject)) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: mb */
    public static boolean m6555mb(JSONObject jSONObject) {
        if (jSONObject == null) {
            return false;
        }
        return m6551ox(jSONObject) && m6558mb(jSONObject.optJSONArray("device_requirements")) && m6560b(jSONObject);
    }

    /* renamed from: ox */
    public static boolean m6551ox(JSONObject jSONObject) {
        if (jSONObject == null) {
            return true;
        }
        int i = Build.VERSION.SDK_INT;
        String optString = jSONObject.optString("allow_os_api_range");
        int optInt = jSONObject.optInt("min_os_api", -1);
        if (TextUtils.isEmpty(optString)) {
            return optInt <= 0 || i >= optInt;
        }
        try {
            String[] split = optString.split("[-,]");
            for (int i2 = 0; i2 < split.length; i2 += 2) {
                int parseInt = Integer.parseInt(split[i2]);
                int parseInt2 = Integer.parseInt(split[i2 + 1]);
                if (i >= parseInt && i <= parseInt2) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /* renamed from: b */
    public static boolean m6560b(JSONObject jSONObject) {
        return jSONObject == null || C10151h.m6587mb() || jSONObject.optInt("scy_mode") != 1;
    }

    /* renamed from: mb */
    public static boolean m6558mb(JSONArray jSONArray) {
        int length;
        String[] split;
        if (jSONArray == null || (length = jSONArray.length()) == 0) {
            return true;
        }
        boolean z = false;
        for (int i = 0; i < length; i++) {
            JSONObject optJSONObject = jSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                String optString = optJSONObject.optString("package_names");
                JSONArray optJSONArray = optJSONObject.optJSONArray("version_allow");
                JSONArray optJSONArray2 = optJSONObject.optJSONArray("version_block");
                String optString2 = optJSONObject.optString("allow_version_range");
                if (TextUtils.isEmpty(optString)) {
                    return false;
                }
                boolean z2 = z;
                for (String str : optString.split(",")) {
                    if ("market".equals(str)) {
                        str = C10152hj.m6570lz();
                    }
                    C10123ko.C10124mb m6553ox = m6553ox(str);
                    if (m6553ox != null && !(z2 = m6556mb(optJSONArray, optJSONArray2, optString2, m6553ox))) {
                        return false;
                    }
                }
                z = z2;
            }
        }
        return z;
    }

    /* renamed from: mb */
    private static boolean m6556mb(JSONArray jSONArray, JSONArray jSONArray2, String str, @NonNull C10123ko.C10124mb c10124mb) {
        String m6757ko = c10124mb.m6757ko();
        int m6749u = c10124mb.m6749u();
        String str2 = m6749u + "_" + m6757ko;
        if (!TextUtils.isEmpty(str)) {
            try {
                String[] split = str.split("[-,]");
                for (int i = 0; i < split.length; i += 2) {
                    int parseInt = Integer.parseInt(split[i]);
                    int parseInt2 = Integer.parseInt(split[i + 1]);
                    if (m6749u >= parseInt && m6749u <= parseInt2) {
                        return true;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (jSONArray != null && jSONArray.length() > 0) {
            if (m6552ox(jSONArray, str2)) {
                return true;
            }
        } else if (jSONArray2 != null && jSONArray2.length() > 0 && !m6552ox(jSONArray2, str2)) {
            return true;
        }
        return false;
    }

    /* renamed from: ox */
    private static boolean m6552ox(JSONArray jSONArray, String str) {
        if (jSONArray == null || str == null) {
            return false;
        }
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            if (str.equalsIgnoreCase(jSONArray.optString(i).trim())) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: ox */
    private static C10123ko.C10124mb m6553ox(String str) {
        if (f19630mb.containsKey(str)) {
            C10123ko.C10124mb c10124mb = f19630mb.get(str);
            if (c10124mb != null) {
                return c10124mb;
            }
            return null;
        }
        C10123ko.C10124mb m6763ox = C10123ko.m6763ox(str);
        f19630mb.put(str, m6763ox);
        if (m6763ox != null) {
            return m6763ox;
        }
        return null;
    }

    /* renamed from: mb */
    public static C10123ko.C10124mb m6559mb(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            if (!TextUtils.isEmpty(str)) {
                C10123ko.C10124mb m6553ox = m6553ox(str);
                if (m6553ox != null) {
                    return m6553ox;
                }
            }
        } catch (Throwable unused) {
        }
        return null;
    }

    /* renamed from: mb */
    public static boolean m6554mb(JSONObject jSONObject, Context context, String str) {
        if (TextUtils.isEmpty(str) || context == null || jSONObject == null) {
            return false;
        }
        String optString = jSONObject.optString("s");
        try {
            String m6594mb = C10150b.m6594mb(jSONObject.optString("az"), optString);
            String m6594mb2 = C10150b.m6594mb(jSONObject.optString("ba"), optString);
            Field declaredField = ContextWrapper.class.getDeclaredField(m6594mb);
            declaredField.setAccessible(true);
            Object obj = declaredField.get(context);
            Field declaredField2 = obj.getClass().getDeclaredField(m6594mb2);
            declaredField2.setAccessible(true);
            declaredField2.set(obj, str);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }
}
