package com.gmrz.asm.p198fp.akselector;

import android.content.Context;
import com.android.client.asm.sdk.IAKSelector;
import com.android.client.asm.sdk.IAuthenticatorDescriptor;
import com.android.client.asm.sdk.IAuthenticatorKernel;
import com.android.client.asm.sdk.IMatcher;
import com.android.client.asm.sdk.ProtocolType;
import com.gmrz.appsdk.util.KeyStoreChecker;
import com.gmrz.asm.p198fp.authenticator.kernel.p199gm.FpAttestationAuthenticatorKernel;
import com.gmrz.asm.p198fp.utils.Logger;
import com.gmrz.authenticationso.authenticator.AuthenticatorKernel;

/* renamed from: com.gmrz.asm.fp.akselector.FpGmAttestationAkSelector */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class FpGmAttestationAkSelector implements IAKSelector {
    private static final String TAG = "FpAttestationAkSelector";
    private IAuthenticatorDescriptor.AAIDInfo mAAID;
    private AuthenticatorKernel mAk;

    public FpGmAttestationAkSelector(Context context, ProtocolType protocolType, IAuthenticatorDescriptor iAuthenticatorDescriptor, IMatcher iMatcher) {
        try {
            if (!KeyStoreChecker.getInstance(context).isHardwareDetected()) {
                throw new IllegalStateException("device no fingerprint sensor");
            }
            if (!keyStoreKeyAttestationTestingPassed(context)) {
                throw new IllegalStateException("device not passed keystore testing");
            }
            this.mAk = new FpAttestationAuthenticatorKernel(context, iMatcher);
            this.mAAID = iAuthenticatorDescriptor.getAAIDInfo().get("keystore_GM_keystore_attestation_label");
            Logger.m15753d(TAG, "Loading GM AKSelector success");
        } catch (Exception e) {
            e.printStackTrace();
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

    private boolean keyStoreKeyAttestationTestingPassed(Context context) throws Exception {
        KeyStoreChecker keyStoreChecker = KeyStoreChecker.getInstance(context);
        if (keyStoreChecker.isGoogleKeyStoreAvailable().booleanValue()) {
            return true;
        }
        return keyStoreChecker.isHuaweiKeyStoreAvailable().booleanValue();
    }
}
