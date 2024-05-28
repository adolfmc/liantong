package com.baidu.p120ar.statistic;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.statistic.IPerformanceStatisticApi */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface IPerformanceStatisticApi {
    void onFrameIn();

    void onFrameOut();

    void recordAlgoTimeCost(String str, String str2, long j, int i);

    void switchCase(String str);
}
