package com.baidu.p120ar.statistic;

import com.baidu.p120ar.utils.ReflectionUtils;
import java.lang.reflect.Constructor;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.statistic.PerformanceStatisticApiProxy */
/* loaded from: E:\10201592_dexfile_execute.dex */
class PerformanceStatisticApiProxy implements IPerformanceStatisticApi {
    private static final String IMPL_CLASS = "com.baidu.ar.statistic.performance.PerformanceStatisticApi";
    private IPerformanceStatisticApi mImpl;

    public PerformanceStatisticApiProxy() {
        Constructor<?> constructor = ReflectionUtils.getConstructor("com.baidu.ar.statistic.performance.PerformanceStatisticApi", new Class[0]);
        if (constructor != null) {
            this.mImpl = (IPerformanceStatisticApi) ReflectionUtils.getNewInstance(constructor, new Object[0]);
        }
    }

    @Override // com.baidu.p120ar.statistic.IPerformanceStatisticApi
    public void switchCase(String str) {
        IPerformanceStatisticApi iPerformanceStatisticApi = this.mImpl;
        if (iPerformanceStatisticApi != null) {
            iPerformanceStatisticApi.switchCase(str);
        }
    }

    @Override // com.baidu.p120ar.statistic.IPerformanceStatisticApi
    public void onFrameIn() {
        IPerformanceStatisticApi iPerformanceStatisticApi = this.mImpl;
        if (iPerformanceStatisticApi != null) {
            iPerformanceStatisticApi.onFrameIn();
        }
    }

    @Override // com.baidu.p120ar.statistic.IPerformanceStatisticApi
    public void onFrameOut() {
        IPerformanceStatisticApi iPerformanceStatisticApi = this.mImpl;
        if (iPerformanceStatisticApi != null) {
            iPerformanceStatisticApi.onFrameOut();
        }
    }

    @Override // com.baidu.p120ar.statistic.IPerformanceStatisticApi
    public void recordAlgoTimeCost(String str, String str2, long j, int i) {
        IPerformanceStatisticApi iPerformanceStatisticApi = this.mImpl;
        if (iPerformanceStatisticApi != null) {
            iPerformanceStatisticApi.recordAlgoTimeCost(str, str2, j, i);
        }
    }
}
