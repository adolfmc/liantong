package github.nisrulz.easydeviceinfo.base;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.RequiresPermission;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import github.nisrulz.easydeviceinfo.common.EasyDeviceInfo;
import java.util.UUID;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class EasyIdMod {
    private final Context context;

    public EasyIdMod(Context context) {
        this.context = context;
    }

    @SuppressLint({"HardwareIds"})
    @Deprecated
    public final String getAndroidID() {
        return CheckValidityUtil.checkValidData(Settings.Secure.getString(this.context.getContentResolver(), "android_id"));
    }

    @RequiresPermission("android.permission.GET_ACCOUNTS")
    @Deprecated
    public final String[] getAccounts() {
        String[] strArr;
        if (Build.VERSION.SDK_INT >= 26 || !PermissionUtil.hasPermission(this.context, "android.permission.GET_ACCOUNTS")) {
            strArr = null;
        } else {
            Account[] accountsByType = AccountManager.get(this.context).getAccountsByType("com.google");
            strArr = new String[accountsByType.length];
            for (int i = 0; i < accountsByType.length; i++) {
                strArr[i] = accountsByType[i].name;
            }
        }
        return CheckValidityUtil.checkValidData(strArr);
    }

    public final String getUA() {
        String str;
        String property = System.getProperty("http.agent");
        if (Build.VERSION.SDK_INT >= 17) {
            str = WebSettings.getDefaultUserAgent(this.context) + "__" + property;
        } else {
            str = new WebView(this.context).getSettings().getUserAgentString() + "__" + property;
        }
        return CheckValidityUtil.checkValidData(str);
    }

    public final String getPseudoUniqueID() {
        String str;
        String str2 = "35" + (Build.BOARD.length() % 10) + (Build.BRAND.length() % 10);
        if (Build.VERSION.SDK_INT >= 21) {
            str = str2 + (Build.SUPPORTED_ABIS[0].length() % 10);
        } else {
            str = str2 + (Build.CPU_ABI.length() % 10);
        }
        String str3 = str + ((Build.DEVICE.length() % 10) + (Build.MANUFACTURER.length() % 10) + (Build.MODEL.length() % 10) + (Build.PRODUCT.length() % 10));
        try {
            return new UUID(str3.hashCode(), Build.class.getField("SERIAL").get(null).toString().hashCode()).toString();
        } catch (Exception e) {
            if (EasyDeviceInfo.debuggable) {
                Log.e("EasyDeviceInfo", "getPseudoUniqueID: ", e);
            }
            return new UUID(str3.hashCode(), "ESYDV000".hashCode()).toString();
        }
    }

    @RequiresPermission("com.google.android.providers.gsf.permission.READ_GSERVICES")
    public final String getGSFID() {
        Cursor query = this.context.getContentResolver().query(Uri.parse("content://com.google.android.gsf.gservices"), null, null, new String[]{"android_id"}, null);
        if (query == null) {
            return EasyDeviceInfo.notFoundVal;
        }
        if (!query.moveToFirst() || query.getColumnCount() < 2) {
            query.close();
            return EasyDeviceInfo.notFoundVal;
        }
        try {
            String hexString = Long.toHexString(Long.parseLong(query.getString(1)));
            query.close();
            return hexString;
        } catch (NumberFormatException unused) {
            query.close();
            return EasyDeviceInfo.notFoundVal;
        }
    }
}
