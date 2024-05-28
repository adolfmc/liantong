package com.baidu.p120ar.face.detector;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.face.detector.FacePerfStaticUtil */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class FacePerfStaticUtil {
    private static final boolean PROFILE_LOG_DEBUG = false;
    private double animateFrameAVGTime;
    private double cameraFrameAVGTime;
    private double createFrameAVGTime;
    private double detectFrameAVGTime;
    private double resultFrameAVGTime;
    private double trackFrameAVGTime;
    private int maxFrameCount = 300;
    private int detectFrameTimes = 0;
    private int createFrameTimes = 0;
    private int trackFrameTimes = 0;
    private int animateFrameTimes = 0;
    private int cameraFrameTimes = 0;
    private int resultFrameTimes = 0;

    public void calculateDetectCostTime(double d) {
        int i = this.detectFrameTimes;
        int i2 = this.maxFrameCount;
        if (i >= i2) {
            if (i == i2) {
                this.detectFrameTimes = i + 1;
            }
            this.detectFrameTimes = 0;
            this.detectFrameAVGTime = 0.0d;
            return;
        }
        this.detectFrameTimes = i + 1;
        int i3 = this.detectFrameTimes;
        this.detectFrameAVGTime = (((i3 - 1) * this.detectFrameAVGTime) + d) / i3;
    }

    public void calculateCreateCostTime(double d) {
        int i = this.createFrameTimes;
        int i2 = this.maxFrameCount;
        if (i >= i2) {
            if (i == i2) {
                this.createFrameTimes = i + 1;
            }
            this.createFrameTimes = 0;
            this.createFrameAVGTime = 0.0d;
            return;
        }
        this.createFrameTimes = i + 1;
        int i3 = this.createFrameTimes;
        this.createFrameAVGTime = (((i3 - 1) * this.createFrameAVGTime) + d) / i3;
    }

    public void calculateTrackCostTime(double d) {
        int i = this.trackFrameTimes;
        int i2 = this.maxFrameCount;
        if (i >= i2) {
            if (i == i2) {
                this.trackFrameTimes = i + 1;
            }
            this.trackFrameTimes = 0;
            this.trackFrameAVGTime = 0.0d;
            return;
        }
        this.trackFrameTimes = i + 1;
        int i3 = this.trackFrameTimes;
        this.trackFrameAVGTime = (((i3 - 1) * this.trackFrameAVGTime) + d) / i3;
    }

    public void calculateAnimateCostTime(double d) {
        int i = this.animateFrameTimes;
        int i2 = this.maxFrameCount;
        if (i >= i2) {
            if (i == i2) {
                this.animateFrameTimes = i + 1;
            }
            this.animateFrameTimes = 0;
            this.animateFrameAVGTime = 0.0d;
            return;
        }
        this.animateFrameTimes = i + 1;
        int i3 = this.animateFrameTimes;
        this.animateFrameAVGTime = (((i3 - 1) * this.animateFrameAVGTime) + d) / i3;
    }

    public void calculateCameraCostTime(double d) {
        int i = this.cameraFrameTimes;
        int i2 = this.maxFrameCount;
        if (i >= i2) {
            if (i == i2) {
                this.cameraFrameTimes = i + 1;
            }
            this.cameraFrameTimes = 0;
            this.cameraFrameAVGTime = 0.0d;
            return;
        }
        this.cameraFrameTimes = i + 1;
        int i3 = this.cameraFrameTimes;
        this.cameraFrameAVGTime = (((i3 - 1) * this.cameraFrameAVGTime) + d) / i3;
    }

    public void calculateResultCostTime(double d) {
        int i = this.resultFrameTimes;
        int i2 = this.maxFrameCount;
        if (i >= i2) {
            if (i == i2) {
                this.resultFrameTimes = i + 1;
            }
            this.resultFrameTimes = 0;
            this.resultFrameAVGTime = 0.0d;
            return;
        }
        this.resultFrameTimes = i + 1;
        int i3 = this.resultFrameTimes;
        this.resultFrameAVGTime = (((i3 - 1) * this.resultFrameAVGTime) + d) / i3;
    }

    public void resetTimes() {
        this.detectFrameTimes = 0;
        this.createFrameTimes = 0;
        this.trackFrameTimes = 0;
        this.animateFrameTimes = 0;
        this.cameraFrameTimes = 0;
        this.resultFrameTimes = 0;
    }
}
