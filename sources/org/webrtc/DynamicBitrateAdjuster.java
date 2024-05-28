package org.webrtc;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
class DynamicBitrateAdjuster extends BaseBitrateAdjuster {
    private static final double BITRATE_ADJUSTMENT_MAX_SCALE = 4.0d;
    private static final double BITRATE_ADJUSTMENT_SEC = 3.0d;
    private static final int BITRATE_ADJUSTMENT_STEPS = 20;
    private static final double BITS_PER_BYTE = 8.0d;
    private int bitrateAdjustmentScaleExp;
    private double deviationBytes;
    private double timeSinceLastAdjustmentMs;

    @Override // org.webrtc.BaseBitrateAdjuster, org.webrtc.BitrateAdjuster
    public void setTargets(int i, int i2) {
        if (this.targetBitrateBps > 0 && i < this.targetBitrateBps) {
            this.deviationBytes = (this.deviationBytes * i) / this.targetBitrateBps;
        }
        super.setTargets(i, i2);
    }

    @Override // org.webrtc.BaseBitrateAdjuster, org.webrtc.BitrateAdjuster
    public void reportEncodedFrame(int i) {
        if (this.targetFps == 0) {
            return;
        }
        this.deviationBytes += i - ((this.targetBitrateBps / 8.0d) / this.targetFps);
        this.timeSinceLastAdjustmentMs += 1000.0d / this.targetFps;
        double d = this.targetBitrateBps / 8.0d;
        double d2 = 3.0d * d;
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
        return Math.pow(4.0d, this.bitrateAdjustmentScaleExp / 20.0d);
    }

    @Override // org.webrtc.BaseBitrateAdjuster, org.webrtc.BitrateAdjuster
    public int getAdjustedBitrateBps() {
        return (int) (this.targetBitrateBps * getBitrateAdjustmentScale());
    }
}
