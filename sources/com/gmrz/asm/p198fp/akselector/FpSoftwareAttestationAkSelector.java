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
import com.gmrz.appsdk.attestation.KeyASecurityType;
import com.gmrz.appsdk.util.FpUtil;
import com.gmrz.asm.p198fp.authenticator.kernel.ecc.FpSoftwareAttestationAuthenticatorKernel;
import com.gmrz.asm.p198fp.utils.Logger;
import com.gmrz.authenticationso.authenticator.AuthenticatorKernel;
import java.util.UUID;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.gmrz.asm.fp.akselector.FpSoftwareAttestationAkSelector */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class FpSoftwareAttestationAkSelector implements IAKSelector {
    private static final String TAG = "FpSoftwareAttestationAk";
    private IAuthenticatorDescriptor.AAIDInfo mAAID;
    private AuthenticatorKernel mAk;

    public FpSoftwareAttestationAkSelector(Context context, ProtocolType protocolType, IAuthenticatorDescriptor iAuthenticatorDescriptor, IMatcher iMatcher) {
        Logger.m15753d("FpSoftwareAttestationAk", "Loading AKSelector for protocol " + protocolType);
        if (protocolType == ProtocolType.OSTP) {
            Logger.m15749i("FpSoftwareAttestationAk", "OSTP not supported, disabling the ASM");
        } else if (Build.VERSION.SDK_INT < 23) {
            Logger.m15749i("FpSoftwareAttestationAk", "Android version should be Marshmallow or above, disabling the ASM");
        } else {
            FingerprintManager fingerprintManager = (FingerprintManager) context.getSystemService(FingerprintManager.class);
            if (fingerprintManager == null || !fingerprintManager.isHardwareDetected()) {
                Logger.m15749i("FpSoftwareAttestationAk", "Fingerprint hardware not detected, disabling the ASM");
                return;
            }
            String uuid = UUID.randomUUID().toString();
            if (!FpUtil.checkSupport(context, uuid) || FpUtil.getASecurityLevel(uuid) != KeyASecurityType.SOFTWARE || !FpUtil.verifySecure(uuid, false)) {
                Logger.m15749i("FpSoftwareAttestationAk", "current device is not support attestation and support store SOFTWARE");
                return;
            }
            try {
                this.mAk = new FpSoftwareAttestationAuthenticatorKernel(context, iMatcher);
            } catch (AKException e) {
                e.printStackTrace();
            }
            this.mAAID = iAuthenticatorDescriptor.getAAIDInfo().get("keystore_software_attestation_label");
            Logger.m15753d("FpSoftwareAttestationAk", "Loading AKSelector success ");
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
