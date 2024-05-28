package com.webrtc;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
class DynamicBitrateAdjuster extends BaseBitrateAdjuster {
    private static final double BITRATE_ADJUSTMENT_MAX_SCALE = 4.0d;
    private static final double BITRATE_ADJUSTMENT_SEC = 3.0d;
    private static final int BITRATE_ADJUSTMENT_STEPS = 20;
    private static final double BITS_PER_BYTE = 8.0d;
    private int bitrateAdjustmentScaleExp;
    private double deviationBytes;
    private double timeSinceLastAdjustmentMs;

    @Override // com.webrtc.BaseBitrateAdjuster, com.webrtc.BitrateAdjuster
    public void setTargets(int i, int i2) {
        if (this.targetBitrateBps > 0 && i < this.targetBitrateBps) {
            this.deviationBytes = (this.deviationBytes * i) / this.targetBitrateBps;
        }
        super.setTargets(i, i2);
    }

    @Override // com.webrtc.BaseBitrateAdjuster, com.webrtc.BitrateAdjuster
    public void reportEncodedFrame(int i) {
        if (this.targetFps == 0) {
            return;
        }
        this.deviationBytes += i - ((this.targetBitrateBps / BITS_PER_BYTE) / this.targetFps);
        this.timeSinceLastAdjustmentMs += 1000.0d / this.targetFps;
        double d = this.targetBitrateBps / BITS_PER_BYTE;
        double d2 = BITRATE_ADJUSTMENT_SEC * d;
        this.deviationBytes = Math.min(this.deviationBytes, d2);
        this.deviationBytes = Math.max(this.deviationBytes, -d2);
        if (this.timeSinceLastAdjustmentMs <= 3000.0d) {
            return;
        }
        double d3 = this.deviationBytes;
        if (d3 > d) {
            this.bitrateAdjustmentScaleExp -= (int) ((d3 / d) + 0.5d);
            this.bitrateAdjustmentScaleExp = Math.max(this.bitrateAdjustmentScaleExp, -20);
            this.deviationBytes = d;
        } else {
            double d4 = -d;
            if (d3 < d4) {
                this.bitrateAdjustmentScaleExp += (int) (((-d3) / d) + 0.5d);
                this.bitrateAdjustmentScaleExp = Math.min(this.bitrateAdjustmentScaleExp, 20);
                this.deviationBytes = d4;
            }
        }
        this.timeSinceLastAdjustmentMs = 0.0d;
    }

    private double getBitrateAdjustmentScale() {
        return Math.pow(BITRATE_ADJUSTMENT_MAX_SCALE, this.bitrateAdjustmentScaleExp / 20.0d);
    }

    @Override // com.webrtc.BaseBitrateAdjuster, com.webrtc.BitrateAdjuster
    public int getAdjustedBitrateBps() {
        return (int) (this.targetBitrateBps * getBitrateAdjustmentScale());
    }
}
