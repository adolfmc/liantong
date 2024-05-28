package com.android.uaf.asmcore;

import com.android.client.asm.sdk.IMatcher;
import com.gmrz.android.client.asm.api.AsmException;
import com.gmrz.android.client.asm.api.uaf.json.GetRegistrationsOut;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public abstract class AuthenticatorDatabase {
    private static final String ANTIHAMMER_STR = "anti_hammer";
    protected static final String ASM_TOKEN = "ASMToken";
    protected static String NNL_AK_CONFIG = "NNL_AK_CONFIG";
    private static final String TAG = "AuthenticatorDatabase";

    protected abstract void addKeyValue(String str, String str2) throws AsmException;

    public abstract void addRegistration(RegistrationRecord registrationRecord) throws AsmException;

    public abstract void eraseDatabase() throws AsmException;

    protected abstract List<RegistrationRecord> getRegistrations(String str) throws AsmException;

    protected abstract String getValue(String str) throws AsmException;

    public abstract boolean hasRegistrations();

    public abstract void removeAllRegistrations() throws AsmException;

    public abstract void removeRegistration(String str, String str2, String str3) throws AsmException;

    public abstract void validateUserRegistrations(IMatcher iMatcher) throws AsmException;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class RegistrationRecord {
        public String appID;
        public String callerID;
        public HashMap<String, String> exts;
        public String keyHandle;
        public String keyID;
        public long timeStamp;
        public String userID;

        public RegistrationRecord() {
            this.exts = new HashMap<>();
        }

        RegistrationRecord(RegistrationRecord registrationRecord) {
            this.exts = new HashMap<>();
            this.callerID = registrationRecord.callerID;
            this.appID = registrationRecord.appID;
            this.keyID = registrationRecord.keyID;
            this.keyHandle = registrationRecord.keyHandle;
            this.userID = registrationRecord.userID;
            this.timeStamp = registrationRecord.timeStamp;
            this.exts = registrationRecord.exts;
        }
    }

    public void storeASMToken(String str) throws AsmException {
        addKeyValue("ASMToken", str);
    }

    public String getASMToken() throws AsmException {
        return getValue("ASMToken");
    }

    public void storeAKConfig(String str) throws AsmException {
        addKeyValue(NNL_AK_CONFIG, str);
    }

    public String getAKConfig() throws AsmException {
        return getValue(NNL_AK_CONFIG);
    }

    public void storeAntiHammeringFailedAttempts(String str) throws AsmException {
        addKeyValue("anti_hammer", str);
    }

    public String getAntiHammeringFailedAttempts() throws AsmException {
        return getValue("anti_hammer");
    }

    public List<RegistrationRecord> getRegistrations(String str, String str2, List<String> list) throws AsmException {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("The callerID is invalid");
        }
        if (str2 == null || str2.isEmpty()) {
            throw new IllegalArgumentException("The appID is invalid");
        }
        List<RegistrationRecord> registrations = getRegistrations(str);
        ArrayList arrayList = new ArrayList();
        for (RegistrationRecord registrationRecord : registrations) {
            if (registrationRecord.callerID.equals(str) && registrationRecord.appID.equals(str2)) {
                arrayList.add(new RegistrationRecord(registrationRecord));
            }
        }
        if (list != null && list.size() != 0) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                if (!list.contains(((RegistrationRecord) it.next()).keyID)) {
                    it.remove();
                }
            }
        }
        return arrayList;
    }

    public GetRegistrationsOut getAppRegistrations(String str) throws AsmException {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("The callerID is invalid");
        }
        ArrayList arrayList = new ArrayList();
        HashMap hashMap = new HashMap();
        GetRegistrationsOut getRegistrationsOut = new GetRegistrationsOut();
        int i = 0;
        for (RegistrationRecord registrationRecord : getRegistrations(str)) {
            Integer num = (Integer) hashMap.get(registrationRecord.appID);
            if (num == null) {
                hashMap.put(registrationRecord.appID, Integer.valueOf(i));
                arrayList.add(new GetRegistrationsOut.AppRegistration(registrationRecord.appID, registrationRecord.keyID));
                i++;
            } else {
                ((GetRegistrationsOut.AppRegistration) arrayList.get(num.intValue())).keyIDs.add(registrationRecord.keyID);
            }
        }
        getRegistrationsOut.appRegs = arrayList;
        return getRegistrationsOut;
    }
}
