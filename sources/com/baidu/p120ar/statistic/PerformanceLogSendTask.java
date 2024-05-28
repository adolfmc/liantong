package com.baidu.p120ar.statistic;

import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.statistic.PerformanceLogSendTask */
/* loaded from: E:\10201592_dexfile_execute.dex */
final class PerformanceLogSendTask extends LogSendTask {
    private static final long MIN_INTERVAL = 1000;
    private long mPreTime;

    public PerformanceLogSendTask(EventRequestCache eventRequestCache, int i, ILogSender iLogSender, boolean z) {
        super(eventRequestCache, i, iLogSender, z);
        this.mPreTime = 0L;
    }

    @Override // com.baidu.p120ar.statistic.LogSendTask
    public List<List<EventData>> getSendBatches() {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.mPreTime < 1000) {
            return null;
        }
        this.mPreTime = currentTimeMillis;
        List<List<EventData>> sendBatches = super.getSendBatches();
        return sendBatches.size() > 1 ? sendBatches.subList(0, 1) : sendBatches;
    }
}
