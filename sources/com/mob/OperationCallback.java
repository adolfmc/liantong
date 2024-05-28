package com.mob;

import com.mob.tools.proguard.PublicMemberKeeper;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\10762272_dexfile_execute.dex */
public abstract class OperationCallback<T> implements PublicMemberKeeper {
    public abstract void onComplete(T t);

    public abstract void onFailure(Throwable th);
}
