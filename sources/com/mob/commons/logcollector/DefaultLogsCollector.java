package com.mob.commons.logcollector;

import com.mob.tools.log.LogCollector;
import com.mob.tools.log.NLog;
import com.mob.tools.proguard.PublicMemberKeeper;
import java.util.HashMap;

@Deprecated
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class DefaultLogsCollector implements LogCollector, PublicMemberKeeper {

    /* renamed from: a */
    private static DefaultLogsCollector f14427a;

    /* renamed from: b */
    private final HashMap<String, Integer> f14428b = new HashMap<>();

    public static synchronized DefaultLogsCollector get() {
        DefaultLogsCollector defaultLogsCollector;
        synchronized (DefaultLogsCollector.class) {
            if (f14427a == null) {
                f14427a = new DefaultLogsCollector();
            }
            defaultLogsCollector = f14427a;
        }
        return defaultLogsCollector;
    }

    private DefaultLogsCollector() {
    }

    public void addSDK(String str, int i) {
        synchronized (this.f14428b) {
            this.f14428b.put(str, Integer.valueOf(i));
        }
    }

    @Override // com.mob.tools.log.LogCollector
    public final void log(String str, int i, int i2, String str2, String str3) {
        Integer num = this.f14428b.get(str);
        if (num == null) {
            num = -1;
        }
        NLog.getInstance(str, num.intValue(), str).log(i, str3, new Object[0]);
    }
}
