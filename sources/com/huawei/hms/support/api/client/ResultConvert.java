package com.huawei.hms.support.api.client;

import com.huawei.hms.common.internal.Preconditions;
import com.huawei.hms.support.api.client.Result;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public abstract class ResultConvert<R extends Result, S extends Result> {

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public class FailPendingResult extends EmptyPendingResult {
        public FailPendingResult(Status status) {
            setResult(status);
        }
    }

    public final PendingResult newFailedPendingResult(Status status) {
        Preconditions.checkNotNull(status, "The input status cannot be null");
        Preconditions.checkArgument(!status.isSuccess(), "The input status must be call with success status");
        return new FailPendingResult(status);
    }

    public Status onFailed(Status status) {
        Preconditions.checkNotNull(status, "The input status cannot be null");
        return status.getStatusCode() != 0 ? status : Status.CoreException;
    }

    public abstract PendingResult onSuccess(Result result);
}
