package com.baidu.p120ar.utils;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.utils.CpuMemUtils */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class CpuMemUtils {
    private static volatile boolean isUpdatingCup;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.ar.utils.CpuMemUtils$CpuMenModel */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class CpuMenModel {
        public long cpuApiTimeCost;
        public double cpuRate;
        public long memTotal;
        public long memUsed;
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [com.baidu.ar.utils.CpuMemUtils$1] */
    public static void updateSystemInfo(final CpuMenModel cpuMenModel) {
        if (isUpdatingCup) {
            return;
        }
        new Thread() { // from class: com.baidu.ar.utils.CpuMemUtils.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                try {
                    try {
                        CpuMemUtils.getMemInfo(CpuMenModel.this);
                        CpuMemUtils.getCpuInfo(CpuMenModel.this);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } finally {
                    boolean unused = CpuMemUtils.isUpdatingCup = false;
                }
            }
        }.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void getMemInfo(CpuMenModel cpuMenModel) {
        long maxMemory = Runtime.getRuntime().maxMemory();
        long j = Runtime.getRuntime().totalMemory();
        long freeMemory = Runtime.getRuntime().freeMemory();
        cpuMenModel.memTotal = maxMemory;
        cpuMenModel.memUsed = j - freeMemory;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:105:0x00e4 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:107:0x00bf A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:111:0x00d9 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:118:0x00d3 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:99:0x00ca A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void getCpuInfo(com.baidu.p120ar.utils.CpuMemUtils.CpuMenModel r20) {
        /*
            Method dump skipped, instructions count: 316
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.p120ar.utils.CpuMemUtils.getCpuInfo(com.baidu.ar.utils.CpuMemUtils$CpuMenModel):void");
    }
}
