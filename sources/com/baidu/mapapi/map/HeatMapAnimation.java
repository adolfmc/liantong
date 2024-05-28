package com.baidu.mapapi.map;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class HeatMapAnimation {

    /* renamed from: a */
    private int f6106a;

    /* renamed from: b */
    private AnimationType f6107b;

    /* renamed from: c */
    private boolean f6108c;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public enum AnimationType {
        Linear,
        InQuad,
        OutQuad,
        InOutQuad,
        OutInQuad,
        InCubic,
        OutCubic,
        InOutCubic,
        OutInCubic,
        InQuart,
        OutQuart,
        InOutQuart,
        OutInQuart,
        InQuint,
        OutQuint,
        InOutQuint,
        OutInQuint,
        InSine,
        OutSine,
        InOutSine,
        OutInSine,
        InExpo,
        OutExpo,
        InOutExpo,
        OutInExpo,
        InCirc,
        OutCirc,
        InOutCirc,
        OutInCirc,
        InElastic,
        OutElastic,
        InOutElastic,
        OutInElastic,
        InBack,
        OutBack,
        InOutBack,
        OutInBack,
        InBounce,
        OutBounce,
        InOutBounce,
        OutInBounce,
        InCurve,
        OutCurve,
        SineCurve,
        CosineCurve
    }

    public HeatMapAnimation(boolean z, int i, AnimationType animationType) {
        this.f6106a = 100;
        this.f6107b = AnimationType.Linear;
        if (i >= 0) {
            this.f6106a = i;
        }
        this.f6107b = animationType;
        this.f6108c = z;
    }

    public int getAnimationType() {
        return this.f6107b.ordinal();
    }

    public int getDuration() {
        return this.f6106a;
    }

    public boolean getIsAnimation() {
        return this.f6108c;
    }

    public void setAnimation(boolean z) {
        this.f6108c = z;
    }

    public void setAnimationType(AnimationType animationType) {
        this.f6107b = animationType;
    }

    public void setDuration(int i) {
        this.f6106a = i;
    }
}
