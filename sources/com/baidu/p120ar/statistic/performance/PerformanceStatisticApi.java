package com.baidu.p120ar.statistic.performance;

import android.os.SystemClock;
import com.baidu.p120ar.bean.ARConfig;
import com.baidu.p120ar.statistic.IPerformanceStatisticApi;
import com.baidu.p120ar.statistic.StatisticApi;
import com.baidu.p120ar.statistic.performance.PerformanceSummaryReportModel;
import com.baidu.p120ar.utils.CpuMemUtils;
import java.util.Random;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.statistic.performance.PerformanceStatisticApi */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class PerformanceStatisticApi implements IPerformanceStatisticApi {
    private static final int MAX_COUNT_FRAME = 3;
    private static final int SAMPLE_RATE = 20;
    private static final String performanceSummaryEventId = "performance_summary";
    private PerformanceSummaryReportModel.FrameReportModel currentFrameReportModel;
    private int currentFrame = 0;
    private boolean isSample = false;
    private PerformanceSummaryReportModel summaryReportModel = new PerformanceSummaryReportModel();
    private CpuMemUtils.CpuMenModel cpuMenModel = new CpuMemUtils.CpuMenModel();

    @Override // com.baidu.p120ar.statistic.IPerformanceStatisticApi
    public void switchCase(String str) {
        try {
            this.summaryReportModel.caseId = str;
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    @Override // com.baidu.p120ar.statistic.IPerformanceStatisticApi
    public void onFrameIn() {
        try {
            if (this.isSample) {
                renewFrameData();
                this.currentFrameReportModel.frameInTimestamp = SystemClock.elapsedRealtime();
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    @Override // com.baidu.p120ar.statistic.IPerformanceStatisticApi
    public void onFrameOut() {
        try {
            if (this.isSample) {
                renewFrameData();
                this.currentFrameReportModel.frameOutTimestamp = SystemClock.elapsedRealtime();
                if (this.currentFrame >= 3) {
                    this.summaryReportModel.cpuApiTimeCost = this.cpuMenModel.cpuApiTimeCost;
                    this.summaryReportModel.cpuRate = this.cpuMenModel.cpuRate;
                    this.summaryReportModel.memTotal = this.cpuMenModel.memTotal;
                    this.summaryReportModel.memUsed = this.cpuMenModel.memUsed;
                    this.summaryReportModel.caseId = ARConfig.getARKey();
                    StatisticApi.onPerformance("performance_summary", this.summaryReportModel.getSummaryData());
                    this.summaryReportModel.renew();
                    this.currentFrame = 0;
                    this.isSample = false;
                }
                this.currentFrameReportModel = null;
            }
            if (!this.isSample && StatisticApi.isAllowPerformanceEvent("performance_summary") && new Random().nextInt(20) == 1) {
                this.isSample = true;
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    @Override // com.baidu.p120ar.statistic.IPerformanceStatisticApi
    public void recordAlgoTimeCost(String str, String str2, long j, int i) {
        try {
            if (this.isSample) {
                renewFrameData();
                PerformanceSummaryReportModel.FrameReportModel.AlgoReportModel algoReportModel = new PerformanceSummaryReportModel.FrameReportModel.AlgoReportModel();
                algoReportModel.name = str;
                algoReportModel.func = str2;
                algoReportModel.timeCost = j;
                algoReportModel.count = i;
                this.currentFrameReportModel.algoQueue.add(algoReportModel);
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    private void renewFrameData() {
        if (this.currentFrameReportModel == null) {
            this.currentFrameReportModel = new PerformanceSummaryReportModel.FrameReportModel();
            this.currentFrame++;
            this.currentFrameReportModel.currentFrameCount = this.currentFrame;
            this.summaryReportModel.frameQueue.add(this.currentFrameReportModel);
        }
    }
}
