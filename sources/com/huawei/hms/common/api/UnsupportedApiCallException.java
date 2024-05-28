package com.huawei.hms.common.api;

import com.huawei.hms.common.Feature;

@Deprecated
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public final class UnsupportedApiCallException extends UnsupportedOperationException {
    private final Feature feature;

    public UnsupportedApiCallException(Feature feature) {
        this.feature = feature;
    }

    @Override // java.lang.Throwable
    public final String getMessage() {
        return this.feature + " is unsupported";
    }
}
