package com.huawei.hms.security;

import android.content.Context;
import android.content.Intent;
import com.huawei.hms.api.HuaweiServicesNotAvailableException;
import com.huawei.hms.api.HuaweiServicesRepairableException;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class SecComponentInstallWizard {
    public static final String PROVIDER_NAME = "HmsCore_OpenSSL";

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface SecComponentInstallWizardListener {
        void onFailed(int i, Intent intent);

        void onSuccess();
    }

    public static void install(Context context) throws HuaweiServicesNotAvailableException, HuaweiServicesRepairableException {
    }
}
