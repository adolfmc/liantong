package com.baidu.cloud.media.player.apm;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
class AppInfo {

    /* renamed from: ak */
    private String f4372ak;
    private String appName;
    private JSONObject json;
    private String packageName;
    private String sdkVersion;
    private String versionCode;
    private String versionName;

    public AppInfo(Context context) {
        init(context);
    }

    private void init(Context context) {
        try {
            this.packageName = context.getPackageName();
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(this.packageName, 0);
            this.versionName = packageInfo.versionName;
            this.versionCode = "" + packageInfo.versionCode;
            this.appName = (String) packageManager.getApplicationLabel(packageInfo.applicationInfo);
        } catch (Exception e) {
            Log.d("BaseInfo", "" + e.getMessage());
        }
    }

    public void setAk(String str) {
        if (str != null) {
            this.f4372ak = str;
        }
    }

    public void setSdkVersion(String str) {
        if (str != null) {
            this.sdkVersion = str;
        }
    }

    public JSONObject toJson() {
        try {
            if (this.json == null) {
                this.json = new JSONObject();
                this.json.put("appName", this.appName);
                this.json.put("packageName", this.packageName);
                this.json.put("versionCode", this.versionCode);
                this.json.put("versionName", this.versionName);
            }
            this.json.put("ak", this.f4372ak);
            this.json.put("sdkVersion", this.sdkVersion);
        } catch (Exception e) {
            Log.d("BaseInfo", "" + e.getMessage());
        }
        return this.json;
    }
}
