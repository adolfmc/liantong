package com.bytedance.applog;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import org.json.JSONObject;

/* renamed from: com.bytedance.applog.g0 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C3587g0 extends AbstractC3692t {

    /* renamed from: e */
    public final Context f8453e;

    /* renamed from: f */
    public final C3726x f8454f;

    public C3587g0(Context context, C3726x c3726x) {
        super(false, false);
        this.f8453e = context;
        this.f8454f = c3726x;
    }

    @Override // com.bytedance.applog.AbstractC3692t
    /* renamed from: a */
    public boolean mo16995a(JSONObject jSONObject) {
        String str;
        Object obj;
        String str2;
        Object obj2;
        int i;
        String packageName = this.f8453e.getPackageName();
        if (TextUtils.isEmpty(this.f8454f.f8897b.getZiJieCloudPkg())) {
            jSONObject.put("package", packageName);
        } else {
            C3704u2.m17108a("has zijie pkg", (Throwable) null);
            jSONObject.put("package", this.f8454f.f8897b.getZiJieCloudPkg());
            jSONObject.put("real_package_name", packageName);
        }
        try {
            PackageInfo packageInfo = this.f8453e.getPackageManager().getPackageInfo(packageName, 0);
            int i2 = packageInfo.versionCode;
            if (!TextUtils.isEmpty(this.f8454f.f8897b.getVersion())) {
                str = "app_version";
                obj = this.f8454f.f8897b.getVersion();
            } else {
                str = "app_version";
                obj = packageInfo.versionName;
            }
            jSONObject.put(str, obj);
            if (TextUtils.isEmpty(this.f8454f.f8897b.getVersionMinor())) {
                str2 = "app_version_minor";
                obj2 = "";
            } else {
                str2 = "app_version_minor";
                obj2 = this.f8454f.f8897b.getVersionMinor();
            }
            jSONObject.put(str2, obj2);
            if (this.f8454f.f8897b.getVersionCode() != 0) {
                jSONObject.put("version_code", this.f8454f.f8897b.getVersionCode());
            } else {
                jSONObject.put("version_code", i2);
            }
            if (this.f8454f.f8897b.getUpdateVersionCode() != 0) {
                jSONObject.put("update_version_code", this.f8454f.f8897b.getUpdateVersionCode());
            } else {
                jSONObject.put("update_version_code", i2);
            }
            if (this.f8454f.f8897b.getManifestVersionCode() != 0) {
                jSONObject.put("manifest_version_code", this.f8454f.f8897b.getManifestVersionCode());
            } else {
                jSONObject.put("manifest_version_code", i2);
            }
            if (!TextUtils.isEmpty(this.f8454f.f8897b.getAppName())) {
                jSONObject.put("app_name", this.f8454f.f8897b.getAppName());
            }
            if (!TextUtils.isEmpty(this.f8454f.f8897b.getTweakedChannel())) {
                jSONObject.put("tweaked_channel", this.f8454f.f8897b.getTweakedChannel());
            }
            ApplicationInfo applicationInfo = packageInfo.applicationInfo;
            if (applicationInfo == null || (i = applicationInfo.labelRes) <= 0) {
                return true;
            }
            jSONObject.put("display_name", this.f8453e.getString(i));
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            C3704u2.m17108a("U SHALL NOT PASS!", e);
            return false;
        }
    }
}
