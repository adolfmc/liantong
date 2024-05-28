package com.sinovatech.unicom.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class SharePreferenceUtil {
    public static final String FILE_KuaiBao = "sinova_unicom_kuaibao";
    public static final String FILE_LoginInfo = "sinova_unicom_logininfo";
    private Context mContext;

    public SharePreferenceUtil(Context context) {
        this.mContext = context;
    }

    private SharedPreferences getSharedPreferences() {
        return this.mContext.getSharedPreferences("sinovatech_unicom_sharepreference_3", 0);
    }

    private SharedPreferences getSharedPreferences(String str) {
        if (TextUtils.isEmpty(str)) {
            str = "sinova_unicom_other";
        }
        return this.mContext.getSharedPreferences(str, 0);
    }

    public void putString(String str, String str2) {
        SharedPreferences.Editor edit = getSharedPreferences().edit();
        edit.putString(str, str2);
        edit.commit();
    }

    public void putString(String str, String str2, String str3) {
        SharedPreferences.Editor edit = getSharedPreferences(str).edit();
        edit.putString(str2, str3);
        edit.commit();
    }

    public void putInt(String str, int i) {
        SharedPreferences.Editor edit = getSharedPreferences().edit();
        edit.putInt(str, i);
        edit.commit();
    }

    public void putLong(String str, long j) {
        SharedPreferences.Editor edit = getSharedPreferences().edit();
        edit.putLong(str, j);
        edit.commit();
    }

    public void putFloat(String str, float f) {
        SharedPreferences.Editor edit = getSharedPreferences().edit();
        edit.putFloat(str, f);
        edit.commit();
    }

    public void putBoolean(String str, Boolean bool) {
        SharedPreferences.Editor edit = getSharedPreferences().edit();
        edit.putBoolean(str, bool.booleanValue());
        edit.commit();
    }

    public String getString(String str) {
        return getSharedPreferences().getString(str, "");
    }

    public long getLong(String str) {
        return getSharedPreferences().getLong(str, 0L);
    }

    public float getFloat(String str) {
        return getSharedPreferences().getFloat(str, 0.0f);
    }

    public String getString(String str, String str2) {
        return getSharedPreferences(str).getString(str2, "");
    }

    public String getStringAddDefaultData(String str, String str2) {
        return getSharedPreferences().getString(str, str2);
    }

    public int getInt(String str) {
        return getSharedPreferences().getInt(str, 0);
    }

    public int getInt(String str, int i) {
        return getSharedPreferences().getInt(str, i);
    }

    public boolean getBoolean(String str) {
        return getSharedPreferences().getBoolean(str, false);
    }

    public boolean getBoolean(String str, boolean z) {
        return getSharedPreferences().getBoolean(str, z);
    }

    public boolean getBooleanTrue(String str) {
        return getSharedPreferences().getBoolean(str, true);
    }

    public void remove(String str) {
        SharedPreferences.Editor edit = getSharedPreferences().edit();
        edit.remove(str);
        edit.commit();
    }

    public void remove(String str, String str2) {
        SharedPreferences.Editor edit = getSharedPreferences(str).edit();
        edit.remove(str2);
        edit.commit();
    }

    public void clear(String str) {
        try {
            SharedPreferences.Editor edit = getSharedPreferences(str).edit();
            edit.clear();
            edit.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
