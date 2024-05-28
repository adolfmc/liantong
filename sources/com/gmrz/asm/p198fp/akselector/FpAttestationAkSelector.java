package com.gmrz.asm.p198fp.akselector;

import android.content.Context;
import com.android.client.asm.sdk.IAKSelector;
import com.android.client.asm.sdk.IAuthenticatorDescriptor;
import com.android.client.asm.sdk.IAuthenticatorKernel;
import com.android.client.asm.sdk.IMatcher;
import com.android.client.asm.sdk.ProtocolType;
import com.gmrz.appsdk.attestation.KeyASecurityType;
import com.gmrz.appsdk.util.FpUtil;
import com.gmrz.appsdk.util.KeyStoreChecker;
import com.gmrz.asm.p198fp.authenticator.kernel.ecc.FpAttestationAuthenticatorKernel;
import com.gmrz.asm.p198fp.utils.Logger;
import com.gmrz.authenticationso.authenticator.AuthenticatorKernel;
import java.util.UUID;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.gmrz.asm.fp.akselector.FpAttestationAkSelector */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class FpAttestationAkSelector implements IAKSelector {
    private static final String TAG = "FpAttestationAkSelector";
    private IAuthenticatorDescriptor.AAIDInfo mAAID;
    private AuthenticatorKernel mAk;

    public FpAttestationAkSelector(Context context, ProtocolType protocolType, IAuthenticatorDescriptor iAuthenticatorDescriptor, IMatcher iMatcher) {
        try {
            KeyStoreChecker keyStoreChecker = KeyStoreChecker.getInstance(context);
            if (!keyStoreChecker.isHardwareDetected()) {
                Logger.m15749i("FpAttestationAkSelector", "Fingerprint hardware not detected, disabling the ASM");
                return;
            }
            String uuid = UUID.randomUUID().toString();
            if (!FpUtil.checkSupport(context, uuid) || FpUtil.getASecurityLevel(uuid) != KeyASecurityType.TEE || !FpUtil.verifySecure(uuid, true)) {
                Logger.m15749i("FpAttestationAkSelector", "current device is not support attestation and support store TEE");
                if (!keyStoreChecker.isHuaweiKeyStoreAvailable().booleanValue()) {
                    return;
                }
            }
            this.mAk = new FpAttestationAuthenticatorKernel(context, iMatcher);
            this.mAAID = iAuthenticatorDescriptor.getAAIDInfo().get("keystore_attestation_label");
            Logger.m15753d("FpAttestationAkSelector", "Loading AKSelector success");
        } catch (Exception e) {
            Logger.m15753d("FpAttestationAkSelector", "Loading AKSelector failed");
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
}
