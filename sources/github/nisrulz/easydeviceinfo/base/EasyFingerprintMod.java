package github.nisrulz.easydeviceinfo.base;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.support.annotation.RequiresPermission;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class EasyFingerprintMod {
    private FingerprintManager fingerprintManager;

    @TargetApi(23)
    @RequiresPermission("android.permission.USE_FINGERPRINT")
    public EasyFingerprintMod(Context context) {
        this.fingerprintManager = null;
        if (Build.VERSION.SDK_INT >= 23) {
            this.fingerprintManager = (FingerprintManager) context.getSystemService("fingerprint");
        }
    }

    @SuppressLint({"NewApi"})
    @RequiresPermission("android.permission.USE_FINGERPRINT")
    public final boolean isFingerprintSensorPresent() {
        FingerprintManager fingerprintManager = this.fingerprintManager;
        return fingerprintManager != null && fingerprintManager.isHardwareDetected();
    }

    @SuppressLint({"NewApi"})
    @RequiresPermission("android.permission.USE_FINGERPRINT")
    public final boolean areFingerprintsEnrolled() {
        FingerprintManager fingerprintManager = this.fingerprintManager;
        return fingerprintManager != null && fingerprintManager.hasEnrolledFingerprints();
    }
}
