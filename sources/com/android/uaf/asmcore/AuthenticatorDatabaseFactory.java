package com.android.uaf.asmcore;

import android.content.Context;
import com.android.client.asm.sdk.IAuthenticatorKernel;
import com.fido.android.framework.p197tm.core.inf.ICryptoModule;
import com.gmrz.android.client.asm.api.AsmException;
import com.gmrz.android.client.utils.Logger;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class AuthenticatorDatabaseFactory {
    private static final String TAG = "AuthenticatorDatabaseFactory";

    public static AuthenticatorDatabase createAuthenticatorStore(boolean z, String str, ICryptoModule iCryptoModule, Context context, IAuthenticatorKernel iAuthenticatorKernel, short s) throws AsmException {
        if (z) {
            Logger.m15895d(TAG, "Initializaing RoamingAuthenticator Database");
            return new RoamingAuthenticatorDatabase(iAuthenticatorKernel, s);
        }
        Logger.m15895d(TAG, "Initializaing BoundAuthenticator Database");
        return new BoundAuthenticatorDatabase(str, iCryptoModule, context);
    }
}
