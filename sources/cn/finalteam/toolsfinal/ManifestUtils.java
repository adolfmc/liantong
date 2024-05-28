package cn.finalteam.toolsfinal;

import android.content.Context;
import android.content.pm.PackageManager;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ManifestUtils {
    public static String getMetaData(Context context, String str) {
        String str2 = "";
        try {
            str2 = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData.getString(str);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return StringUtils.isEmpty(str2) ? "" : str2;
    }

    public static String getChannelNo(Context context, String str) {
        return getMetaData(context, str);
    }

    public static String getVersionName(Context context) {
        String str = "";
        try {
            str = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return StringUtils.isEmpty(str) ? "" : str;
    }

    public static int getVersionCode(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
