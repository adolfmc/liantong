package github.nisrulz.easydeviceinfo.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresPermission;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import github.nisrulz.easydeviceinfo.common.EasyDeviceInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class EasySimMod {
    private final Context context;

    /* renamed from: tm */
    private final TelephonyManager f24308tm;

    public EasySimMod(Context context) {
        this.context = context;
        this.f24308tm = (TelephonyManager) context.getSystemService("phone");
    }

    public final String getCountry() {
        String lowerCase;
        TelephonyManager telephonyManager = this.f24308tm;
        if (telephonyManager != null && telephonyManager.getSimState() == 5) {
            lowerCase = this.f24308tm.getSimCountryIso().toLowerCase(Locale.getDefault());
        } else {
            Locale locale = Locale.getDefault();
            lowerCase = locale.getCountry().toLowerCase(locale);
        }
        return CheckValidityUtil.checkValidData(CheckValidityUtil.handleIllegalCharacterInResult(lowerCase));
    }

    public final boolean isSimNetworkLocked() {
        TelephonyManager telephonyManager = this.f24308tm;
        return telephonyManager != null && telephonyManager.getSimState() == 4;
    }

    public final String getCarrier() {
        TelephonyManager telephonyManager = this.f24308tm;
        return CheckValidityUtil.checkValidData(CheckValidityUtil.handleIllegalCharacterInResult((telephonyManager == null || telephonyManager.getPhoneType() == 2) ? null : this.f24308tm.getNetworkOperatorName().toLowerCase(Locale.getDefault())));
    }

    @SuppressLint({"HardwareIds"})
    @RequiresPermission("android.permission.READ_PHONE_STATE")
    public final String getIMSI() {
        return CheckValidityUtil.checkValidData((this.f24308tm == null || !PermissionUtil.hasPermission(this.context, "android.permission.READ_PHONE_STATE")) ? null : this.f24308tm.getSubscriberId());
    }

    @SuppressLint({"HardwareIds"})
    @RequiresPermission("android.permission.READ_PHONE_STATE")
    public final String getSIMSerial() {
        return CheckValidityUtil.checkValidData((this.f24308tm == null || !PermissionUtil.hasPermission(this.context, "android.permission.READ_PHONE_STATE")) ? null : this.f24308tm.getSimSerialNumber());
    }

    @RequiresPermission("android.permission.READ_PHONE_STATE")
    public final boolean isMultiSim() {
        return getActiveMultiSimInfo().size() > 1;
    }

    @RequiresPermission("android.permission.READ_PHONE_STATE")
    public final List<SubscriptionInfo> getActiveMultiSimInfo() {
        if (Build.VERSION.SDK_INT >= 22 && PermissionUtil.hasPermission(this.context, "android.permission.READ_PHONE_STATE")) {
            List<SubscriptionInfo> activeSubscriptionInfoList = SubscriptionManager.from(this.context).getActiveSubscriptionInfoList();
            return (activeSubscriptionInfoList == null || activeSubscriptionInfoList.isEmpty()) ? new ArrayList(0) : activeSubscriptionInfoList;
        }
        if (EasyDeviceInfo.debuggable) {
            Log.w("EasyDeviceInfo", "Device is running on android version that does not support multi sim functionality!");
        }
        return new ArrayList(0);
    }

    @RequiresPermission("android.permission.READ_PHONE_STATE")
    public final int getNumberOfActiveSim() {
        return getActiveMultiSimInfo().size();
    }
}
