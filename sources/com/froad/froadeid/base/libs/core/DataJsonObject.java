package com.froad.froadeid.base.libs.core;

import com.p189cn.froad.clouddecodingsdk.utils.p191np.TMKeyLog;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* compiled from: Proguard */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class DataJsonObject extends JSONObject {
    private static final String TAG = "DataJsonObject";

    public DataJsonObject() {
    }

    public DataJsonObject(String str) {
        super(str);
    }

    public static Object get(String str, String str2) {
        TMKeyLog.m16310d("DataJsonObject", "get() called with: json = [" + str + "], key = [" + str2 + "]");
        try {
            return new JSONObject(str).opt(str2);
        } catch (JSONException unused) {
            return "";
        }
    }

    public static DataJsonObject getInstance(String str) {
        try {
            return new DataJsonObject(str);
        } catch (JSONException e) {
            e.printStackTrace();
            return new DataJsonObject();
        }
    }

    public static String getString(String str, String str2) {
        TMKeyLog.m16310d("DataJsonObject", "get() called with: json = [" + str + "], key = [" + str2 + "]");
        try {
            return new JSONObject(str).optString(str2);
        } catch (JSONException unused) {
            return "";
        }
    }

    public static boolean has(String str, String str2) {
        TMKeyLog.m16310d("DataJsonObject", "has() called with: json = [" + str + "], key = [" + str2 + "]");
        try {
            return new JSONObject(str).has(str2);
        } catch (JSONException unused) {
            return false;
        }
    }

    @Override // org.json.JSONObject
    public DataJsonObject put(String str, int i) {
        try {
            super.put(str, i);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return this;
    }

    @Override // org.json.JSONObject
    public DataJsonObject put(String str, Object obj) {
        try {
            super.put(str, obj);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return this;
    }

    public DataJsonObject put(String str, String str2) {
        try {
            super.put(str, (Object) str2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return this;
    }

    @Override // org.json.JSONObject
    public DataJsonObject remove(String str) {
        super.remove(str);
        return this;
    }
}
