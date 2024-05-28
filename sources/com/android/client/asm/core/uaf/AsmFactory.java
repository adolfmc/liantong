package com.android.client.asm.core.uaf;

import android.content.Context;
import com.android.client.asm.core.shared.DescriptorLoader;
import com.android.client.asm.sdk.IAuthenticatorDescriptor;
import com.android.uaf.asm.ASM;
import com.gmrz.android.client.asm.api.AsmException;
import com.gmrz.android.client.utils.Logger;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class AsmFactory {
    private static final String TAG = "AsmFactory";
    private final Context mCtx;

    public AsmFactory(Context context) {
        this.mCtx = context;
    }

    public ASM createAsmInstance(String str) {
        Logger.m15889i(TAG, "Creating asm instance");
        ASM asm = new ASM(this.mCtx);
        for (IAuthenticatorDescriptor iAuthenticatorDescriptor : DescriptorLoader.instance(this.mCtx).enumerateAndLoadRegisteredDescriptors()) {
            try {
                AuthenticatorCore authenticatorCore = new AuthenticatorCore(iAuthenticatorDescriptor);
                authenticatorCore.initialize(this.mCtx, str);
                asm.addAuthenticator(authenticatorCore);
            } catch (AsmException unused) {
                String str2 = TAG;
                Logger.m15889i(str2, "Warning to Instantiate Authenticator for descriptor: " + iAuthenticatorDescriptor.getTitle());
            }
        }
        return asm;
    }
}
