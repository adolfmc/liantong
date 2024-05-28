package github.nisrulz.easydeviceinfo.base;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;
import github.nisrulz.easydeviceinfo.common.EasyDeviceInfo;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class EasyAppMod {
    private static final String NAME_NOT_FOUND_EXCEPTION = "Name Not Found Exception";
    private final Context context;

    public EasyAppMod(Context context) {
        this.context = context;
    }

    public final String getActivityName() {
        return CheckValidityUtil.checkValidData(this.context.getClass().getSimpleName());
    }

    public final String getPackageName() {
        return CheckValidityUtil.checkValidData(this.context.getPackageName());
    }

    public final String getStore() {
        return CheckValidityUtil.checkValidData(this.context.getPackageManager().getInstallerPackageName(this.context.getPackageName()));
    }

    public final String getAppName() {
        ApplicationInfo applicationInfo;
        PackageManager packageManager = this.context.getPackageManager();
        try {
            applicationInfo = packageManager.getApplicationInfo(this.context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            if (EasyDeviceInfo.debuggable) {
                Log.d("EasyDeviceInfo", NAME_NOT_FOUND_EXCEPTION, e);
            }
            applicationInfo = null;
        }
        return CheckValidityUtil.checkValidData(applicationInfo != null ? (String) packageManager.getApplicationLabel(applicationInfo) : null);
    }

    public final String getAppVersion() {
        String str;
        try {
            str = this.context.getPackageManager().getPackageInfo(this.context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            if (EasyDeviceInfo.debuggable) {
                Log.e("EasyDeviceInfo", NAME_NOT_FOUND_EXCEPTION, e);
            }
            str = null;
        }
        return CheckValidityUtil.checkValidData(str);
    }

    public final String getAppVersionCode() {
        String str;
        try {
            str = String.valueOf(this.context.getPackageManager().getPackageInfo(this.context.getPackageName(), 0).versionCode);
        } catch (PackageManager.NameNotFoundException e) {
            if (EasyDeviceInfo.debuggable) {
                Log.e("EasyDeviceInfo", NAME_NOT_FOUND_EXCEPTION, e);
            }
            str = null;
        }
        return CheckValidityUtil.checkValidData(str);
    }

    public final boolean isPermissionGranted(String str) {
        return this.context.checkCallingPermission(str) == 0;
    }

    public final boolean isAppInstalled(String str) {
        return this.context.getPackageManager().getLaunchIntentForPackage(str) != null;
    }
}
