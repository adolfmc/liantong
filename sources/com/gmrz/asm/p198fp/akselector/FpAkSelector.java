package com.gmrz.asm.p198fp.akselector;

import android.content.Context;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import com.android.AKException;
import com.android.client.asm.sdk.IAKSelector;
import com.android.client.asm.sdk.IAuthenticatorDescriptor;
import com.android.client.asm.sdk.IAuthenticatorKernel;
import com.android.client.asm.sdk.IMatcher;
import com.android.client.asm.sdk.ProtocolType;
import com.gmrz.asm.p198fp.authenticator.kernel.ecc.FpAuthenticatorKernel;
import com.gmrz.asm.p198fp.utils.Logger;
import com.gmrz.authenticationso.authenticator.AuthenticatorKernel;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.gmrz.asm.fp.akselector.FpAkSelector */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class FpAkSelector implements IAKSelector {
    private static final String TAG = "FpAkSelector";
    private IAuthenticatorDescriptor.AAIDInfo mAAID;
    private AuthenticatorKernel mAk;

    public FpAkSelector(Context context, ProtocolType protocolType, IAuthenticatorDescriptor iAuthenticatorDescriptor, IMatcher iMatcher) {
        Logger.m15753d("FpAkSelector", "Loading AKSelector for protocol " + protocolType);
        if (protocolType == ProtocolType.OSTP) {
            Logger.m15749i("FpAkSelector", "OSTP not supported, disabling the ASM");
        } else if (Build.VERSION.SDK_INT < 23) {
            Logger.m15749i("FpAkSelector", "Android version should be Marshmallow or above, disabling the ASM");
        } else {
            FingerprintManager fingerprintManager = (FingerprintManager) context.getSystemService(FingerprintManager.class);
            if (fingerprintManager == null || !fingerprintManager.isHardwareDetected()) {
                Logger.m15749i("FpAkSelector", "Fingerprint hardware not detected, disabling the ASM");
                return;
            }
            try {
                this.mAk = new FpAuthenticatorKernel(context, iMatcher);
            } catch (AKException e) {
                e.printStackTrace();
            }
            this.mAAID = iAuthenticatorDescriptor.getAAIDInfo().get("keystore_label");
            Logger.m15753d("FpAkSelector", "Loading AKSelector success ");
        }
    }

    @Override // com.android.client.asm.sdk.IAKSelector
    public IAuthenticatorDescriptor.AAIDInfo getAAIDInfo() {
        return this.mAAID;
    }

    @Override // com.android.client.asm.sdk.IAKSelector
    public IAuthenticatorKernel getAuthenticatorKernel() {
        return this.mAk;
    }
}
