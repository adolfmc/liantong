package com.mob.commons;

import com.mob.tools.proguard.PublicMemberKeeper;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\10762272_dexfile_execute.dex */
public class ForbThrowable extends Throwable implements PublicMemberKeeper {
    public ForbThrowable() {
        super("Service is forbidden currently");
    }

    public ForbThrowable(String str) {
        super(str);
    }
}
