package com.baidu.cloud.media.player.apm;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import java.util.Locale;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
class Device {
    private String androidId;
    private String brand;
    private String imei;
    private JSONObject json;
    private String language;
    private String mainABI;
    private String model;
    private String osVersion;
    private int osVersionInt;
    private int screenHeight;
    private int screenWidth;
    private String uniqueId;
    private String osType = "android";
    private String cuid = "";

    public Device(Context context, String str) {
        init(context, str);
    }

    @TargetApi(17)
    private void init(Context context, String str) {
        try {
            this.osVersion = Build.VERSION.RELEASE;
            this.osVersionInt = Build.VERSION.SDK_INT;
            this.model = Build.MODEL;
            this.brand = Build.BRAND;
            this.mainABI = Build.CPU_ABI;
            this.language = Locale.getDefault().getLanguage();
            AndroidIDCache androidIDCache = new AndroidIDCache(context);
            this.androidId = androidIDCache.getAndroidID();
            this.uniqueId = this.androidId;
            DisplayMetrics displayMetrics = new DisplayMetrics();
            if (Build.VERSION.SDK_INT >= 17) {
                ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getRealMetrics(displayMetrics);
            } else {
                ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
            }
            if (displayMetrics.widthPixels > displayMetrics.heightPixels) {
                this.screenWidth = displayMetrics.heightPixels;
                this.screenHeight = displayMetrics.widthPixels;
            } else {
                this.screenWidth = displayMetrics.widthPixels;
                this.screenHeight = displayMetrics.heightPixels;
            }
            if (context.checkCallingOrSelfPermission("android.permission.READ_PHONE_STATE") != -1) {
                this.imei = androidIDCache.getIMEI();
            }
            this.cuid = str;
        } catch (Exception e) {
            Log.d("BaseInfo", "" + e.getMessage());
        }
    }

    public void setCuid(String str) {
        this.cuid = str;
    }

    public JSONObject toJson() {
        if (this.json == null) {
            try {
                this.json = new JSONObject();
                this.json.put("osType", this.osType);
                this.json.put("osVersion", this.osVersion);
                this.json.put("osVersionInt", this.osVersionInt);
                this.json.put("model", this.model);
                this.json.put("brand", this.brand);
                this.json.put("arch", this.mainABI);
                this.json.put("screenWidth", this.screenWidth);
                this.json.put("screenHeight", this.screenHeight);
                this.json.put("language", this.language);
                this.json.put("uniqueId", this.uniqueId);
                this.json.put("imei", this.imei);
                this.json.put("androidId", this.androidId);
                this.json.put("cuid", this.cuid);
            } catch (Exception e) {
                Log.d("BaseInfo", "" + e.getMessage());
            }
        }
        return this.json;
    }
}
