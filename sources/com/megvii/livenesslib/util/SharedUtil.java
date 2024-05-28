package com.megvii.livenesslib.util;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.Map;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class SharedUtil {
    private static String TAG = "PushSharePreference";
    private String FileName = "YueSuoPing";
    private Context ctx;

    public SharedUtil(Context context) {
        this.ctx = context;
    }

    public void saveIntValue(String str, int i) {
        SharedPreferences.Editor edit = this.ctx.getSharedPreferences(this.FileName, 0).edit();
        edit.putInt(str, i);
        edit.commit();
    }

    public void saveLongValue(String str, long j) {
        SharedPreferences.Editor edit = this.ctx.getSharedPreferences(this.FileName, 0).edit();
        edit.putLong(str, j);
        edit.commit();
    }

    public void writeDownStartApplicationTime() {
        SharedPreferences sharedPreferences = this.ctx.getSharedPreferences(this.FileName, 0);
        long currentTimeMillis = System.currentTimeMillis();
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putLong("nowtimekey", currentTimeMillis);
        edit.commit();
    }

    public void saveBooleanValue(String str, boolean z) {
        SharedPreferences.Editor edit = this.ctx.getSharedPreferences(this.FileName, 0).edit();
        edit.putBoolean(str, z);
        edit.commit();
    }

    public void removeSharePreferences(String str) {
        SharedPreferences.Editor edit = this.ctx.getSharedPreferences(this.FileName, 0).edit();
        edit.remove(str);
        edit.commit();
    }

    public boolean contains(String str) {
        return this.ctx.getSharedPreferences(this.FileName, 0).contains(str);
    }

    public Map<String, Object> getAllMap() {
        return this.ctx.getSharedPreferences(this.FileName, 0).getAll();
    }

    public Integer getIntValueByKey(String str) {
        return Integer.valueOf(this.ctx.getSharedPreferences(this.FileName, 0).getInt(str, -1));
    }

    public Long getLongValueByKey(String str) {
        return Long.valueOf(this.ctx.getSharedPreferences(this.FileName, 0).getLong(str, -1L));
    }

    public void saveStringValue(String str, String str2) {
        SharedPreferences.Editor edit = this.ctx.getSharedPreferences(this.FileName, 0).edit();
        edit.putString(str, str2);
        edit.commit();
    }

    public String getStringValueByKey(String str) {
        return this.ctx.getSharedPreferences(this.FileName, 0).getString(str, null);
    }

    public Boolean getBooleanValueByKey(String str) {
        return Boolean.valueOf(this.ctx.getSharedPreferences(this.FileName, 0).getBoolean(str, false));
    }

    public Integer getIntValueAndRemoveByKey(String str) {
        Integer intValueByKey = getIntValueByKey(str);
        removeSharePreferences(str);
        return intValueByKey;
    }

    public void setUserkey(String str) {
        saveStringValue("params_userkey", str);
    }

    public String getUserkey() {
        return getStringValueByKey("params_userkey");
    }
}
