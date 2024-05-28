package com.android.uaf.asmcore;

import com.android.client.asm.sdk.IAuthenticatorKernel;
import com.android.client.asm.sdk.IMatcher;
import com.android.uaf.asmcore.AKProcessor;
import com.android.uaf.asmcore.AuthenticatorDatabase;
import com.gmrz.android.client.asm.api.AsmError;
import com.gmrz.android.client.asm.api.AsmException;
import com.gmrz.android.client.asm.api.uaf.json.GetRegistrationsOut;
import com.gmrz.android.client.utils.Logger;
import java.util.ArrayList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class RoamingAuthenticatorDatabase extends AuthenticatorDatabase {
    private static final String TAG = "RoamingAuthenticatorDatabase";
    private final AKProcessor mAKProcessor;
    private final IAuthenticatorKernel mAk;
    private final short mAuthenticatorIndex;

    @Override // com.android.uaf.asmcore.AuthenticatorDatabase
    protected String getValue(String str) {
        return null;
    }

    @Override // com.android.uaf.asmcore.AuthenticatorDatabase
    public boolean hasRegistrations() {
        return false;
    }

    public RoamingAuthenticatorDatabase(IAuthenticatorKernel iAuthenticatorKernel, short s) {
        this.mAk = iAuthenticatorKernel;
        this.mAuthenticatorIndex = s;
        this.mAKProcessor = new AKProcessor(this.mAk);
    }

    @Override // com.android.uaf.asmcore.AuthenticatorDatabase
    public void eraseDatabase() throws AsmException {
        throw new AsmException(AsmError.NOT_SUPPORTED, "eraseDatabase not supported in roaming authenticator");
    }

    @Override // com.android.uaf.asmcore.AuthenticatorDatabase
    public void removeAllRegistrations() throws AsmException {
        throw new AsmException(AsmError.NOT_SUPPORTED, "removeAllRegistrations not supported in roaming authenticator");
    }

    @Override // com.android.uaf.asmcore.AuthenticatorDatabase
    public void addRegistration(AuthenticatorDatabase.RegistrationRecord registrationRecord) throws AsmException {
        throw new AsmException(AsmError.NOT_SUPPORTED, "addRegistration not supported in roaming authenticator");
    }

    @Override // com.android.uaf.asmcore.AuthenticatorDatabase
    public void removeRegistration(String str, String str2, String str3) throws AsmException {
        throw new AsmException(AsmError.NOT_SUPPORTED, "removeRegistration not supported in roaming authenticator");
    }

    @Override // com.android.uaf.asmcore.AuthenticatorDatabase
    protected void addKeyValue(String str, String str2) throws AsmException {
        throw new AsmException(AsmError.NOT_SUPPORTED, "addKeyValue not supported in roaming authenticator");
    }

    @Override // com.android.uaf.asmcore.AuthenticatorDatabase
    protected List<AuthenticatorDatabase.RegistrationRecord> getRegistrations(String str) throws AsmException {
        AKProcessor.AKResponseParams processAK = this.mAKProcessor.processAK(new AKProcessor.AKRequestParams().setCmd((short) 13321).setAuthenticatorIndex((byte) this.mAuthenticatorIndex));
        if (processAK.statusCode != 0) {
            throw new AsmException(statusCodeToAsmError(processAK.statusCode), "GetRegistrations call to AK failed.");
        }
        ArrayList arrayList = new ArrayList();
        for (GetRegistrationsOut.AppRegistration appRegistration : processAK.appRegistrations) {
            for (String str2 : appRegistration.keyIDs) {
                AuthenticatorDatabase.RegistrationRecord registrationRecord = new AuthenticatorDatabase.RegistrationRecord();
                registrationRecord.appID = appRegistration.appID;
                registrationRecord.keyID = str2;
                arrayList.add(registrationRecord);
                String str3 = TAG;
                Logger.m15895d(str3, "Adding regRecord appID: " + registrationRecord.appID + " keyID: " + registrationRecord.keyID);
            }
        }
        String str4 = TAG;
        Logger.m15895d(str4, "number of registrationREcords: " + arrayList.size());
        return arrayList;
    }

    private AsmError statusCodeToAsmError(short s) {
        AsmError asmError = AsmError.SUCCESS;
        switch (s) {
            case 1:
            case 2:
                return AsmError.FAILURE;
            case 3:
                return AsmError.NOT_REGISTERED;
            case 4:
            case 5:
            default:
                return asmError;
            case 6:
            case 7:
                return AsmError.NOT_SUPPORTED;
        }
    }

    @Override // com.android.uaf.asmcore.AuthenticatorDatabase
    public void validateUserRegistrations(IMatcher iMatcher) throws AsmException {
        throw new AsmException(AsmError.NOT_SUPPORTED, "validateUserRegistrations not supported in roaming authenticator");
    }
}
