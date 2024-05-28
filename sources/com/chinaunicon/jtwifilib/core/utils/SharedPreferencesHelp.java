package com.chinaunicon.jtwifilib.core.utils;

import android.content.Context;
import android.content.SharedPreferences;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class SharedPreferencesHelp {
    public static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1000;
    private static SharedPreferencesHelp mSharedPreferencesHelp;
    private static SharedPreferences sharedPreferences;

    public static SharedPreferencesHelp getInstance(Context context, String str) {
        mSharedPreferencesHelp = new SharedPreferencesHelp();
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(str, 0);
        }
        return mSharedPreferencesHelp;
    }

    public int getInt(String str, int i) {
        try {
            return sharedPreferences != null ? sharedPreferences.getInt(str, i) : i;
        } catch (Exception unused) {
            return i;
        }
    }

    public void putInt(String str, int i) {
        try {
            if (sharedPreferences != null) {
                SharedPreferences.Editor edit = sharedPreferences.edit();
                edit.putInt(str, i);
                edit.commit();
            }
        } catch (Exception unused) {
        }
    }

    public long getLong(String str, long j) {
        try {
            return sharedPreferences != null ? sharedPreferences.getLong(str, j) : j;
        } catch (Exception unused) {
            return j;
        }
    }

    public void putLong(String str, long j) {
        try {
            if (sharedPreferences != null) {
                SharedPreferences.Editor edit = sharedPreferences.edit();
                edit.putLong(str, j);
                edit.commit();
            }
        } catch (Exception unused) {
        }
    }

    public String getString(String str) {
        try {
            if (sharedPreferences != null) {
                return sharedPreferences.getString(str, "");
            }
            return null;
        } catch (Exception unused) {
            return null;
        }
    }

    public String getString(String str, String str2) {
        try {
            if (sharedPreferences != null) {
                return sharedPreferences.getString(str, str2);
            }
            return null;
        } catch (Exception unused) {
            return null;
        }
    }

    public void putString(String str, String str2) {
        try {
            if (sharedPreferences != null) {
                SharedPreferences.Editor edit = sharedPreferences.edit();
                edit.putString(str, str2);
                edit.commit();
            }
        } catch (Exception unused) {
        }
    }

    public boolean getBoolean(String str, boolean z) {
        try {
            return sharedPreferences != null ? sharedPreferences.getBoolean(str, z) : z;
        } catch (Exception unused) {
            return z;
        }
    }

    public void putBoolean(String str, boolean z) {
        try {
            if (sharedPreferences != null) {
                SharedPreferences.Editor edit = sharedPreferences.edit();
                edit.putBoolean(str, z);
                edit.commit();
            }
        } catch (Exception unused) {
        }
    }

    public void remove(String str) {
        try {
            if (sharedPreferences != null) {
                SharedPreferences.Editor edit = sharedPreferences.edit();
                edit.remove(str);
                edit.commit();
            }
        } catch (Exception unused) {
        }
    }

    public void clearData() {
        SharedPreferences sharedPreferences2 = sharedPreferences;
        if (sharedPreferences2 != null) {
            sharedPreferences2.edit().clear().commit();
        }
    }
}
