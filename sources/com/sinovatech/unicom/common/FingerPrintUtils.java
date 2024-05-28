package com.sinovatech.unicom.common;

import android.support.p083v4.hardware.fingerprint.FingerprintManagerCompat;
import android.support.p083v4.p085os.CancellationSignal;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class FingerPrintUtils {
    private CancellationSignal mCancellationSignal = new CancellationSignal();
    private FingerprintManagerCompat mFingerprintManager = FingerprintManagerCompat.from(App.getInstance());

    public void setFingerPrintListener(FingerprintManagerCompat.AuthenticationCallback authenticationCallback) {
        this.mFingerprintManager.authenticate(null, 0, this.mCancellationSignal, authenticationCallback, null);
    }

    public void reSetFingerPrintListener(FingerprintManagerCompat.AuthenticationCallback authenticationCallback) {
        this.mFingerprintManager.authenticate(null, 0, null, authenticationCallback, null);
    }

    public void stopsFingerPrintListener() {
        try {
            if (this.mCancellationSignal == null) {
                this.mCancellationSignal = new CancellationSignal();
            }
            this.mCancellationSignal.cancel();
            this.mCancellationSignal = null;
        } catch (Exception e) {
            MsLogUtil.m7978e(e.getMessage());
        }
    }
}
