package com.baidu.p120ar.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.provider.Settings;
import android.text.TextUtils;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.utils.DeviceUuidFactory */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class DeviceUuidFactory {
    protected static final String PREFS_DEVICE_ID = "device_id";
    protected static final String PREFS_FILE = "bd_plugin_ar_device_id.xml";
    protected static UUID uuid;

    public DeviceUuidFactory(Context context) {
        if (uuid == null) {
            synchronized (DeviceUuidFactory.class) {
                if (uuid == null) {
                    SharedPreferences sharedPreferences = context.getSharedPreferences("bd_plugin_ar_device_id.xml", 0);
                    String string = sharedPreferences.getString("device_id", null);
                    if (string != null) {
                        uuid = UUID.fromString(string);
                    } else {
                        String string2 = Settings.Secure.getString(context.getContentResolver(), "android_id");
                        try {
                            if (!"9774d56d682e549c".equals(string2) && !TextUtils.isEmpty(string2)) {
                                uuid = UUID.nameUUIDFromBytes(string2.getBytes("utf8"));
                            }
                            if (uuid != null) {
                                sharedPreferences.edit().putString("device_id", uuid.toString()).apply();
                            }
                        } catch (UnsupportedEncodingException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        }
    }

    public UUID getDeviceUuid() {
        return uuid;
    }
}
