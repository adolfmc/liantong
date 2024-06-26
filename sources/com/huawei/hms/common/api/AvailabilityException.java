package com.huawei.hms.common.api;

import com.huawei.hms.api.Api;
import com.huawei.hms.api.ConnectionResult;
import com.huawei.hms.api.HuaweiApiAvailability;
import com.huawei.hms.common.HuaweiApi;
import com.huawei.hms.support.log.HMSLog;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class AvailabilityException extends Exception {
    private static final String TAG = "AvailabilityException";
    private String message = null;

    private ConnectionResult generateConnectionResult(int i) {
        HMSLog.m14110i(TAG, "The availability check result is: " + i);
        setMessage(i);
        return new ConnectionResult(i);
    }

    private void setMessage(int i) {
        if (i != 21) {
            switch (i) {
                case 0:
                    this.message = "success";
                    return;
                case 1:
                    this.message = "SERVICE_MISSING";
                    return;
                case 2:
                    this.message = "SERVICE_VERSION_UPDATE_REQUIRED";
                    return;
                case 3:
                    this.message = "SERVICE_DISABLED";
                    return;
                default:
                    this.message = "INTERNAL_ERROR";
                    return;
            }
        }
        this.message = "ANDROID_VERSION_UNSUPPORT";
    }

    public ConnectionResult getConnectionResult(HuaweiApiCallable huaweiApiCallable) {
        if (huaweiApiCallable != null && huaweiApiCallable.getHuaweiApi() != null) {
            return generateConnectionResult(HuaweiApiAvailability.getInstance().isHuaweiMobileServicesAvailable(huaweiApiCallable.getHuaweiApi().getContext(), 30000000));
        }
        HMSLog.m14112e(TAG, "The huaweiApi is null.");
        return generateConnectionResult(8);
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return this.message;
    }

    public ConnectionResult getConnectionResult(HuaweiApi<? extends Api.ApiOptions> huaweiApi) {
        if (huaweiApi == null) {
            HMSLog.m14112e(TAG, "The huaweiApi is null.");
            return generateConnectionResult(8);
        }
        return generateConnectionResult(HuaweiApiAvailability.getInstance().isHuaweiMobileServicesAvailable(huaweiApi.getContext(), 30000000));
    }
}
