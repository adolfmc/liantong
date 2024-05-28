package com.baidu.p120ar.bean;

import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.bean.RotationType */
/* loaded from: E:\10201592_dexfile_execute.dex */
public enum RotationType {
    ROTATE_0(0),
    ROTATE_90(90),
    ROTATE_180(180),
    ROTATE_270(SubsamplingScaleImageView.ORIENTATION_270);
    
    private final int mDegree;

    RotationType(int i) {
        this.mDegree = i;
    }

    public int getDegree() {
        return this.mDegree;
    }
}
