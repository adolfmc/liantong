package com.mob.commons.logcollector;

import com.mob.tools.log.LogCollector;
import com.mob.tools.proguard.ProtectedMemberKeeper;
import com.mob.tools.proguard.PublicMemberKeeper;

@Deprecated
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public abstract class LogsCollector implements LogCollector, ProtectedMemberKeeper, PublicMemberKeeper {
    protected abstract String getSDKTag();

    protected abstract int getSDKVersion();

    public LogsCollector() {
        DefaultLogsCollector.get().addSDK(getSDKTag(), getSDKVersion());
    }

    @Override // com.mob.tools.log.LogCollector
    public final void log(String str, int i, int i2, String str2, String str3) {
        DefaultLogsCollector.get().log(str, i, i2, str2, str3);
    }
}
