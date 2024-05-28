package com.liang.jtablayout.indicator;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class IndicatorPoint {
    public float left = 0.0f;
    public float right = 0.0f;

    public boolean equals(Object obj) {
        if (obj instanceof IndicatorPoint) {
            IndicatorPoint indicatorPoint = (IndicatorPoint) obj;
            return indicatorPoint.left == this.left || indicatorPoint.right == this.right;
        }
        return false;
    }
}
