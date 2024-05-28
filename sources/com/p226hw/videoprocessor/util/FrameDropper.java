package com.p226hw.videoprocessor.util;

/* renamed from: com.hw.videoprocessor.util.FrameDropper */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class FrameDropper {
    private boolean disable;
    private int dropCount;
    private int dstFrameRate;
    private int keepCount;
    private int srcFrameRate;

    public FrameDropper(int i, int i2) {
        this.srcFrameRate = i;
        this.dstFrameRate = i2;
        if (i <= i2) {
            C5140CL.m13758e("原始帧率:" + i + "小于目标帧率:" + i2 + "，不支持补帧", new Object[0]);
            this.disable = true;
        }
    }

    public boolean checkDrop(int i) {
        int i2;
        if (this.disable) {
            return false;
        }
        if (i == 0) {
            this.keepCount++;
            return false;
        }
        float f = (i2 - this.dstFrameRate) / this.srcFrameRate;
        int i3 = this.dropCount;
        int i4 = this.keepCount;
        boolean z = Math.abs((((float) (i3 + 1)) / ((float) (i3 + i4))) - f) < Math.abs((((float) i3) / ((float) ((i3 + i4) + 1))) - f);
        if (z) {
            this.dropCount++;
        } else {
            this.keepCount++;
        }
        return z;
    }

    public void printResult() {
        if (this.disable) {
            return;
        }
        int i = this.dropCount;
        int i2 = this.keepCount;
        int i3 = this.srcFrameRate;
        float f = i2 / ((i + i2) / i3);
        float f2 = (i3 - this.dstFrameRate) / i3;
        C5140CL.m13755i("最终帧率为:" + f, new Object[0]);
        StringBuilder sb = new StringBuilder();
        sb.append("实际丢帧率:");
        int i4 = this.dropCount;
        sb.append(i4 / (i4 + this.keepCount));
        sb.append(" 目标丢帧率:");
        sb.append(f2);
        C5140CL.m13755i(sb.toString(), new Object[0]);
    }
}
