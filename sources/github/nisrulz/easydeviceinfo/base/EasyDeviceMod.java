package github.nisrulz.easydeviceinfo.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresPermission;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import github.nisrulz.easydeviceinfo.common.EasyDeviceInfo;
import java.io.File;
import java.util.Locale;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class EasyDeviceMod {
    private final Context context;

    /* renamed from: tm */
    private final TelephonyManager f24306tm;

    public EasyDeviceMod(Context context) {
        this.context = context;
        this.f24306tm = (TelephonyManager) context.getSystemService("phone");
    }

    @PhoneType
    public final int getPhoneType() {
        switch (this.f24306tm.getPhoneType()) {
            case 1:
                return 0;
            case 2:
                return 1;
            default:
                return 2;
        }
    }

    @SuppressLint({"HardwareIds"})
    @RequiresPermission("android.permission.READ_PHONE_STATE")
    public final String getPhoneNo() {
        return CheckValidityUtil.checkValidData((!PermissionUtil.hasPermission(this.context, "android.permission.READ_PHONE_STATE") || this.f24306tm.getLine1Number() == null) ? null : this.f24306tm.getLine1Number());
    }

    public final String getProduct() {
        return CheckValidityUtil.checkValidData(Build.PRODUCT);
    }

    public final String getFingerprint() {
        return CheckValidityUtil.checkValidData(Build.FINGERPRINT);
    }

    public final String getHardware() {
        return CheckValidityUtil.checkValidData(Build.HARDWARE);
    }

    public final String getRadioVer() {
        return CheckValidityUtil.checkValidData(Build.VERSION.SDK_INT >= 14 ? Build.getRadioVersion() : null);
    }

    public final String getDevice() {
        return CheckValidityUtil.checkValidData(Build.DEVICE);
    }

    public final String getBootloader() {
        return CheckValidityUtil.checkValidData(Build.BOOTLOADER);
    }

    public final String getBoard() {
        return CheckValidityUtil.checkValidData(Build.BOARD);
    }

    public final String getDisplayVersion() {
        return CheckValidityUtil.checkValidData(Build.DISPLAY);
    }

    public final String getLanguage() {
        return CheckValidityUtil.checkValidData(Locale.getDefault().getLanguage());
    }

    @DeviceType
    public final int getDeviceType(Activity activity) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        float f = displayMetrics.heightPixels / displayMetrics.ydpi;
        float f2 = displayMetrics.widthPixels / displayMetrics.xdpi;
        double sqrt = Math.sqrt((f2 * f2) + (f * f));
        if (sqrt > 10.1d) {
            return 4;
        }
        if (sqrt > 10.1d || sqrt <= 7.0d) {
            if (sqrt > 7.0d || sqrt <= 6.5d) {
                return (sqrt > 6.5d || sqrt < 2.0d) ? 0 : 1;
            }
            return 2;
        }
        return 3;
    }

    public final String getManufacturer() {
        return CheckValidityUtil.checkValidData(CheckValidityUtil.handleIllegalCharacterInResult(Build.MANUFACTURER));
    }

    public final String getModel() {
        return CheckValidityUtil.checkValidData(CheckValidityUtil.handleIllegalCharacterInResult(Build.MODEL));
    }

    public final String getBuildBrand() {
        return CheckValidityUtil.checkValidData(CheckValidityUtil.handleIllegalCharacterInResult(Build.BRAND));
    }

    public final String getBuildHost() {
        return CheckValidityUtil.checkValidData(Build.HOST);
    }

    public final String getBuildTags() {
        return CheckValidityUtil.checkValidData(Build.TAGS);
    }

    public final long getBuildTime() {
        return Build.TIME;
    }

    public final String getBuildUser() {
        return CheckValidityUtil.checkValidData(Build.USER);
    }

    public final String getBuildVersionRelease() {
        return CheckValidityUtil.checkValidData(Build.VERSION.RELEASE);
    }

    public final String getScreenDisplayID() {
        WindowManager windowManager = (WindowManager) this.context.getSystemService("window");
        if (windowManager != null) {
            return CheckValidityUtil.checkValidData(String.valueOf(windowManager.getDefaultDisplay().getDisplayId()));
        }
        return CheckValidityUtil.checkValidData("");
    }

    public final String getBuildVersionCodename() {
        return CheckValidityUtil.checkValidData(Build.VERSION.CODENAME);
    }

    public final String getBuildVersionIncremental() {
        return CheckValidityUtil.checkValidData(Build.VERSION.INCREMENTAL);
    }

    public final int getBuildVersionSDK() {
        return Build.VERSION.SDK_INT;
    }

    public final String getBuildID() {
        return CheckValidityUtil.checkValidData(Build.ID);
    }

    public final boolean isDeviceRooted() {
        for (String str : new String[]{"/sbin/", "/system/bin/", "/system/xbin/", "/system/sd/xbin/", "/system/bin/failsafe/", "/data/local/xbin/", "/data/local/bin/", "/data/local/"}) {
            if (new File(str + "su").exists()) {
                return true;
            }
        }
        return false;
    }

    @SuppressLint({"HardwareIds"})
    @RequiresPermission("android.permission.READ_PHONE_STATE")
    @Deprecated
    public final String getIMEI() {
        return CheckValidityUtil.checkValidData(PermissionUtil.hasPermission(this.context, "android.permission.READ_PHONE_STATE") ? this.f24306tm.getDeviceId() : null);
    }

    @SuppressLint({"HardwareIds"})
    public final String getSerial() {
        String serial;
        if (Build.VERSION.SDK_INT < 26) {
            serial = Build.SERIAL;
        } else {
            serial = PermissionUtil.hasPermission(this.context, "android.permission.READ_PHONE_STATE") ? Build.getSerial() : null;
        }
        return CheckValidityUtil.checkValidData(serial);
    }

    public final String getOSCodename() {
        switch (Build.VERSION.SDK_INT) {
            case 1:
                return "First Android Version. Yay !";
            case 2:
                return "Base Android 1.1";
            case 3:
                return "Cupcake";
            case 4:
                return "Donut";
            case 5:
            case 6:
            case 7:
                return "Eclair";
            case 8:
                return "Froyo";
            case 9:
            case 10:
                return "Gingerbread";
            case 11:
            case 12:
            case 13:
                return "Honeycomb";
            case 14:
            case 15:
                return "Ice Cream Sandwich";
            case 16:
            case 17:
            case 18:
                return "Jelly Bean";
            case 19:
                return "Kitkat";
            case 20:
                return "Kitkat Watch";
            case 21:
            case 22:
                return "Lollipop";
            case 23:
                return "Marshmallow";
            case 24:
            case 25:
                return "Nougat";
            case 26:
                return "O";
            default:
                return EasyDeviceInfo.notFoundVal;
        }
    }

    public final String getOSVersion() {
        return CheckValidityUtil.checkValidData(Build.VERSION.RELEASE);
    }

    @OrientationType
    public final int getOrientation(Activity activity) {
        switch (activity.getResources().getConfiguration().orientation) {
            case 1:
                return 0;
            case 2:
                return 1;
            default:
                return 2;
        }
    }
}
