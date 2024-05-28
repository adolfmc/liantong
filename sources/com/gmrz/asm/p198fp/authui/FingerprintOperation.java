package com.gmrz.asm.p198fp.authui;

import android.content.Context;
import android.hardware.fingerprint.FingerprintManager;
import android.os.CancellationSignal;
import com.gmrz.asm.p198fp.utils.Logger;

/* renamed from: com.gmrz.asm.fp.authui.FingerprintOperation */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class FingerprintOperation {
    private static final String TAG = "FingerprintOperation";
    private final CancellationSignal cancellationSignal = new CancellationSignal();
    private final FingerprintManager fingerprintManager;

    public FingerprintOperation(Context context) {
        this.fingerprintManager = (FingerprintManager) context.getSystemService(FingerprintManager.class);
    }

    public void startListening(FingerprintManager.CryptoObject cryptoObject, FingerprintManager.AuthenticationCallback authenticationCallback) {
        Logger.m15753d(TAG, "startListening called");
        this.fingerprintManager.authenticate(cryptoObject, this.cancellationSignal, 0, authenticationCallback, null);
    }

    public void stopListening() {
        Logger.m15753d(TAG, "stopListening called");
        this.cancellationSignal.cancel();
    }

    public boolean hasEnrolledFingerprints() {
        Logger.m15753d(TAG, "hasEnrolledFingerprints called");
        return this.fingerprintManager.hasEnrolledFingerprints();
    }

    public boolean isHardwareDetected() {
        Logger.m15753d(TAG, "isHardwareDetected called");
        return this.fingerprintManager.isHardwareDetected();
    }

    public CancellationSignal getCancellationSignal() {
        return this.cancellationSignal;
    }
}
