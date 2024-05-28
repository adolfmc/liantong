package com.baidu.cloud.media.player.apm;

import android.content.Context;
import android.content.SharedPreferences;
import android.provider.Settings;
import android.telephony.TelephonyManager;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class AndroidIDCache {
    private static final String ANDROID_ID_KEY = "BD_CLOUD_PLAYER_CACHE_ANDROID_ID_KEY";
    private static final String FILE = "BD_CLOUD_PLAYER_CACHE";
    private static final String IMEI_KEY = "BD_CLOUD_PLAYER_CACHE_IMEI_KEY";
    private Context ctx;

    public AndroidIDCache(Context context) {
        this.ctx = null;
        this.ctx = context;
    }

    public String getAndroidID() {
        String cache = getCache("BD_CLOUD_PLAYER_CACHE_ANDROID_ID_KEY");
        if (cache == null || "".equals(cache)) {
            String string = Settings.Secure.getString(this.ctx.getContentResolver(), "android_id");
            putCache("BD_CLOUD_PLAYER_CACHE_ANDROID_ID_KEY", string);
            return string;
        }
        return cache;
    }

    public String getIMEI() {
        String cache = getCache("BD_CLOUD_PLAYER_CACHE_IMEI_KEY");
        if (cache == null || "".equals(cache)) {
            if (this.ctx.checkCallingOrSelfPermission("android.permission.READ_PHONE_STATE") != -1) {
                cache = ((TelephonyManager) this.ctx.getSystemService("phone")).getDeviceId();
            }
            putCache("BD_CLOUD_PLAYER_CACHE_IMEI_KEY", cache);
        }
        return cache;
    }

    private String getCache(String str) {
        return this.ctx.getSharedPreferences("BD_CLOUD_PLAYER_CACHE", 0).getString(str, "");
    }

    private int putCache(String str, String str2) {
        SharedPreferences.Editor edit = this.ctx.getSharedPreferences("BD_CLOUD_PLAYER_CACHE", 0).edit();
        edit.putString(str, str2);
        edit.commit();
        return 0;
    }
}
