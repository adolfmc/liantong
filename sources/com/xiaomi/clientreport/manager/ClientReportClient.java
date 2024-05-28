package com.xiaomi.clientreport.manager;

import android.content.Context;
import android.os.Process;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import com.xiaomi.clientreport.data.Config;
import com.xiaomi.clientreport.data.EventClientReport;
import com.xiaomi.clientreport.data.PerfClientReport;
import com.xiaomi.clientreport.processor.C11062a;
import com.xiaomi.clientreport.processor.C11063b;
import com.xiaomi.clientreport.processor.IEventProcessor;
import com.xiaomi.clientreport.processor.IPerfProcessor;
import com.xiaomi.push.C11395g;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class ClientReportClient {
    public static void init(Context context) {
        init(context, Config.defaultConfig(context), new C11062a(context), new C11063b(context));
    }

    public static void init(Context context, Config config) {
        init(context, config, new C11062a(context), new C11063b(context));
    }

    public static void init(Context context, Config config, IEventProcessor iEventProcessor, IPerfProcessor iPerfProcessor) {
        AbstractC11049b.m5270c("init in  pid :" + Process.myPid() + " threadId: " + Thread.currentThread().getId());
        C11053a.m5261a(context).m5260a(config, iEventProcessor, iPerfProcessor);
        if (C11395g.m3723a(context)) {
            AbstractC11049b.m5270c("init in process\u3000start scheduleJob");
            C11053a.m5261a(context).m5263a();
        }
    }

    public static void updateConfig(Context context, Config config) {
        if (config == null) {
            return;
        }
        C11053a.m5261a(context).m5250a(config.isEventUploadSwitchOpen(), config.isPerfUploadSwitchOpen(), config.getEventUploadFrequency(), config.getPerfUploadFrequency());
    }

    public static void reportEvent(Context context, EventClientReport eventClientReport) {
        if (eventClientReport != null) {
            C11053a.m5261a(context).m5259a(eventClientReport);
        }
    }

    public static void reportPerf(Context context, PerfClientReport perfClientReport) {
        if (perfClientReport != null) {
            C11053a.m5261a(context).m5258a(perfClientReport);
        }
    }
}
