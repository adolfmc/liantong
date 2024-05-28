package com.android.client.asm.core.uaf;

import com.android.client.asm.sdk.IMatcher;
import com.android.uaf.asmcore.AuthenticatorDatabase;
import com.gmrz.android.client.asm.api.AsmException;
import com.gmrz.android.client.utils.Logger;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class AntiHammering implements IMatcher.MatcherInParams.IAntiHammeringCallback {
    private static final String TAG = "AntiHammering";
    private final String deviceID;
    private final AuthenticatorDatabase mAuthenticatorDb;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public enum AntiHammeringDataType {
        ATTRIBUTE_FAILED_TIMES
    }

    @Override // com.android.client.asm.sdk.IMatcher.MatcherInParams.IAntiHammeringCallback
    public int getFailedCount() {
        return Integer.parseInt(getValue(AntiHammeringDataType.ATTRIBUTE_FAILED_TIMES));
    }

    @Override // com.android.client.asm.sdk.IMatcher.MatcherInParams.IAntiHammeringCallback
    public boolean incrementFailedCount() {
        return updateLockInfo(false);
    }

    @Override // com.android.client.asm.sdk.IMatcher.MatcherInParams.IAntiHammeringCallback
    public boolean resetFailedCount() {
        setDefault();
        return true;
    }

    public AntiHammering(AuthenticatorDatabase authenticatorDatabase, String str, boolean z) {
        this.deviceID = str;
        this.mAuthenticatorDb = authenticatorDatabase;
        if (z) {
            restoreLockStatus();
        }
    }

    private boolean updateLockInfo(boolean z) {
        if (z) {
            cleanLock();
        } else {
            increaseLock();
        }
        return restoreLockStatus();
    }

    private boolean restoreLockStatus() {
        return getFalseAttemptTimes() == 10;
    }

    private int getFalseAttemptTimes() {
        return Integer.parseInt(getValue(AntiHammeringDataType.ATTRIBUTE_FAILED_TIMES));
    }

    private void cleanLock() {
        setDefault();
    }

    private void increaseLock() {
        if (needInitialize()) {
            setDefault();
        }
        setValue(getFalseAttemptTimes() + 1);
    }

    private String getValue(AntiHammeringDataType antiHammeringDataType) {
        try {
            String antiHammeringFailedAttempts = this.mAuthenticatorDb.getAntiHammeringFailedAttempts();
            return antiHammeringFailedAttempts != null ? antiHammeringFailedAttempts : "0";
        } catch (AsmException e) {
            Logger.m15891e(TAG, "Failed to get the AntiHammering data.", e);
            return "0";
        }
    }

    private void setDefault() {
        setValue(0);
    }

    private boolean needInitialize() {
        String value = getValue(AntiHammeringDataType.ATTRIBUTE_FAILED_TIMES);
        return value == null || value.length() == 0;
    }

    private void setValue(int i) {
        try {
            this.mAuthenticatorDb.storeAntiHammeringFailedAttempts(Integer.toString(i));
        } catch (AsmException e) {
            Logger.m15891e(TAG, "Failed to set the count of false pin entries in dbManager", e);
        }
    }
}
