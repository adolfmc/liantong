package com.huawei.hms.framework.common.hianalytics;

import com.huawei.hms.framework.common.Logger;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.RejectedExecutionException;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class InitReport {
    private static final int EVENT_LIMIT = 10;
    private static final String TAG = "HaReport";
    private static List<Runnable> eventsToReport = new CopyOnWriteArrayList();
    private static boolean hasConnectNet;

    public static void reportWhenInit(Runnable runnable) {
        if (hasConnectNet) {
            try {
                HianalyticsHelper.getInstance().getReportExecutor().execute(runnable);
            } catch (RejectedExecutionException unused) {
                Logger.m15052e("HaReport", "the thread submit has rejectedExecutionException!");
            } catch (Throwable unused2) {
                Logger.m15052e("HaReport", "the thread submit has fatal error!");
            }
        } else if (eventsToReport.size() > 10) {
            Logger.m15052e("TAG", "the event to be report when init exceed the limit!");
        } else {
            eventsToReport.add(runnable);
        }
    }

    public static void enableConnectNet() {
        hasConnectNet = true;
        try {
            HianalyticsHelper.getInstance().getReportExecutor().submit(new Runnable() { // from class: com.huawei.hms.framework.common.hianalytics.InitReport.1
                @Override // java.lang.Runnable
                public void run() {
                    InitReport.submitAllEvents();
                }
            });
        } catch (RejectedExecutionException unused) {
            Logger.m15052e("HaReport", "the thread submit has rejectedExecutionException!");
        } catch (Throwable unused2) {
            Logger.m15052e("HaReport", "the thread submit has fatal error!");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void submitAllEvents() {
        try {
            for (Runnable runnable : eventsToReport) {
                HianalyticsHelper.getInstance().getReportExecutor().submit(runnable);
            }
            eventsToReport.clear();
        } catch (NullPointerException unused) {
            Logger.m15052e("HaReport", "event is null occured");
        } catch (RejectedExecutionException unused2) {
            Logger.m15052e("HaReport", "submit failed of rejected execution exception");
        } catch (Exception unused3) {
            Logger.m15052e("HaReport", "submit failed because of some exception");
        }
    }
}
