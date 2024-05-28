package com.baidu.p120ar.arplay.component;

import android.content.SharedPreferences;
import android.util.Log;
import java.util.HashMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.arplay.component.DataStore */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class DataStore {
    private static int AR_MEMORY = 1;
    private static int DISK = 3;
    private static int MEMORY = 2;
    private static final String TAG = "DataStore";
    private Map<String, String> mARMemoryDataStore = new HashMap();
    private Map<String, String> mMemoryDataStore = new HashMap();
    private SharedPreferences mPrefsDataStore;

    public void setPrefs(SharedPreferences sharedPreferences) {
        this.mPrefsDataStore = sharedPreferences;
    }

    public void setValue(int i, String str, String str2) {
        if (i == AR_MEMORY) {
            this.mARMemoryDataStore.put(str, str2);
        } else if (i == MEMORY) {
            this.mMemoryDataStore.put(str, str2);
        } else if (i == DISK) {
            SharedPreferences sharedPreferences = this.mPrefsDataStore;
            if (sharedPreferences != null) {
                sharedPreferences.edit().putString(str, str2).commit();
            } else {
                Log.e("TAG", "prefs data store is null");
            }
        }
    }

    public String getValue(int i, String str) {
        String str2;
        if (i == AR_MEMORY) {
            str2 = this.mARMemoryDataStore.get(str);
        } else if (i == MEMORY) {
            str2 = this.mMemoryDataStore.get(str);
        } else {
            if (i == DISK) {
                SharedPreferences sharedPreferences = this.mPrefsDataStore;
                if (sharedPreferences != null) {
                    str2 = sharedPreferences.getString(str, "");
                } else {
                    Log.e("TAG", "prefs data store is null");
                }
            }
            str2 = null;
        }
        return str2 == null ? "" : str2;
    }

    public void clearARMemory() {
        this.mARMemoryDataStore.clear();
    }
}
