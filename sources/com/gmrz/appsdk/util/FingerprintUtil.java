package com.gmrz.appsdk.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.hardware.fingerprint.FingerprintManager;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class FingerprintUtil {
    private static final FingerprintCompat IMPL;
    public static final int STATUS_FINGERS_NO = 2;
    public static final int STATUS_FINGERS_UNCHECKED = 3;
    public static final int STATUS_FINGERS_YES = 1;
    private static final String TAG = "FingerprintUtil_fido";

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public enum FingerHelp {
        SUPPORT,
        NOT_SUPPORT,
        NOT_SUPPORT_CHECK
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    static class FingerInfo {
        private FingerHelp help;

        FingerInfo() {
        }

        public FingerHelp getHelp() {
            return this.help;
        }

        public FingerInfo setHelp(FingerHelp fingerHelp) {
            this.help = fingerHelp;
            return this;
        }
    }

    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    static class FingerprintAndroidMCompat implements FingerprintCompat {
        FingerInfo info = new FingerInfo();

        FingerprintAndroidMCompat() {
        }

        @Override // com.gmrz.appsdk.util.FingerprintUtil.FingerprintCompat
        public FingerInfo findHasEnrolledFingerprints(Context context) {
            if (context == null) {
                Logger.m15757e("FingerprintUtil_fido", "ERROR,CONTEXT IS NULL");
            }
            FingerprintManager fingerManager = getFingerManager(context);
            if (fingerManager != null) {
                if (isHardwareDetected(context)) {
                    Logger.m15757e("FingerprintAndroidMCompat", "findHasEnrolledFingerprints");
                    boolean hasEnrolledFingerprints = fingerManager.hasEnrolledFingerprints();
                    Logger.m15757e("FingerprintAndroidMCompat", "findHasEnrolledFingerprints result:" + hasEnrolledFingerprints);
                    if (hasEnrolledFingerprints) {
                        this.info.setHelp(FingerHelp.SUPPORT);
                    } else {
                        this.info.setHelp(FingerHelp.NOT_SUPPORT);
                    }
                }
            } else {
                this.info.setHelp(FingerHelp.NOT_SUPPORT_CHECK);
            }
            return this.info;
        }

        @Override // com.gmrz.appsdk.util.FingerprintUtil.FingerprintCompat
        @SuppressLint({"NewApi"})
        public boolean isHardwareDetected(Context context) {
            if (context == null) {
                Logger.m15757e("FingerprintUtil_fido", "ERROR,CONTEXT IS NULL");
            }
            FingerprintManager fingerManager = getFingerManager(context);
            if (fingerManager != null) {
                return fingerManager.isHardwareDetected();
            }
            return false;
        }

        @Override // com.gmrz.appsdk.util.FingerprintUtil.FingerprintCompat
        @SuppressLint({"NewApi"})
        public FingerprintManager getFingerManager(Context context) {
            if (context == null) {
                Logger.m15757e("FingerprintUtil_fido", "ERROR,CONTEXT IS NULL");
                return null;
            }
            return (FingerprintManager) context.getSystemService(FingerprintManager.class);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    interface FingerprintCompat {
        FingerInfo findHasEnrolledFingerprints(Context context);

        Object getFingerManager(Context context);

        boolean isHardwareDetected(Context context);
    }

    static {
        if (Compatibility.isAndroidM()) {
            IMPL = new FingerprintAndroidMCompat();
        } else {
            IMPL = null;
        }
    }

    public static int findHasEnrolledFingerprints(Context context) {
        FingerprintCompat fingerprintCompat = IMPL;
        if (fingerprintCompat != null) {
            FingerInfo findHasEnrolledFingerprints = fingerprintCompat.findHasEnrolledFingerprints(context);
            if (findHasEnrolledFingerprints.getHelp() == FingerHelp.SUPPORT) {
                return 1;
            }
            return findHasEnrolledFingerprints.getHelp() == FingerHelp.NOT_SUPPORT ? 2 : 3;
        }
        return 3;
    }

    public static boolean isHardwareDetected(Context context) {
        FingerprintCompat fingerprintCompat = IMPL;
        if (fingerprintCompat != null) {
            return fingerprintCompat.isHardwareDetected(context);
        }
        return false;
    }
}
