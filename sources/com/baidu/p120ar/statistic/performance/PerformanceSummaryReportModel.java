package com.baidu.p120ar.statistic.performance;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.statistic.performance.PerformanceSummaryReportModel */
/* loaded from: E:\10201592_dexfile_execute.dex */
class PerformanceSummaryReportModel {
    String caseId;
    long cpuApiTimeCost;
    double cpuRate;
    BlockingQueue<FrameReportModel> frameQueue = new LinkedBlockingQueue();
    long memTotal;
    long memUsed;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.ar.statistic.performance.PerformanceSummaryReportModel$FrameReportModel */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    static class FrameReportModel {
        static final String reportKey = "frame_data";
        BlockingQueue<AlgoReportModel> algoQueue = new LinkedBlockingQueue();
        int currentFrameCount;
        long frameInTimestamp;
        long frameOutTimestamp;

        /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
        /* renamed from: com.baidu.ar.statistic.performance.PerformanceSummaryReportModel$FrameReportModel$AlgoReportModel */
        /* loaded from: E:\10201592_dexfile_execute.dex */
        static class AlgoReportModel {
            int count;
            String func;
            String name;
            long timeCost;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public JSONObject getSummaryData() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("case_id", this.caseId);
            jSONObject.put("cpu_rate", String.valueOf(this.cpuRate));
            jSONObject.put("cpu_tc", String.valueOf(this.cpuApiTimeCost));
            jSONObject.put("mem_total", String.valueOf(this.memTotal));
            jSONObject.put("mem_used", String.valueOf(this.memUsed));
            JSONArray jSONArray = new JSONArray();
            for (FrameReportModel frameReportModel : this.frameQueue) {
                try {
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("frame_index", frameReportModel.currentFrameCount);
                    jSONObject2.put("frame_in", frameReportModel.frameInTimestamp);
                    jSONObject2.put("frame_out", frameReportModel.frameOutTimestamp);
                    JSONArray jSONArray2 = new JSONArray();
                    for (FrameReportModel.AlgoReportModel algoReportModel : frameReportModel.algoQueue) {
                        try {
                            JSONObject jSONObject3 = new JSONObject();
                            jSONObject3.put("name", algoReportModel.name);
                            jSONObject3.put("func", algoReportModel.func);
                            jSONObject3.put("time_cost", algoReportModel.timeCost);
                            jSONObject3.put("count", algoReportModel.count);
                            jSONArray2.put(jSONObject3);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    jSONObject2.put("algo_data", jSONArray2);
                    jSONArray.put(jSONObject2);
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
            }
            jSONObject.put("frame_data", jSONArray);
        } catch (JSONException e3) {
            e3.printStackTrace();
        }
        return jSONObject;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void renew() {
        this.caseId = null;
        this.cpuRate = 0.0d;
        this.cpuApiTimeCost = 0L;
        this.memTotal = 0L;
        this.memUsed = 0L;
        this.frameQueue = new LinkedBlockingQueue();
    }
}
