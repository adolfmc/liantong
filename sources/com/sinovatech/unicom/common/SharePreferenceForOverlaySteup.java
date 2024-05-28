package com.sinovatech.unicom.common;

import android.content.Context;
import android.content.SharedPreferences;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class SharePreferenceForOverlaySteup {
    private Context mContext;

    public SharePreferenceForOverlaySteup(Context context) {
        this.mContext = context;
    }

    private SharedPreferences getSharedPreferences() {
        return this.mContext.getSharedPreferences("sinocatech_unicom_sharepreference", 32768);
    }

    public void putString(String str, String str2) {
        SharedPreferences.Editor edit = getSharedPreferences().edit();
        edit.putString(str, str2);
        edit.commit();
    }

    public void putInt(String str, int i) {
        SharedPreferences.Editor edit = getSharedPreferences().edit();
        edit.putInt(str, i);
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

    public int getInt(String str) {
        return getSharedPreferences().getInt(str, 0);
    }

    public boolean getBoolean(String str) {
        return getSharedPreferences().getBoolean(str, false);
    }

    public void remove(String str) {
        SharedPreferences.Editor edit = getSharedPreferences().edit();
        edit.remove(str);
        edit.commit();
    }
}
