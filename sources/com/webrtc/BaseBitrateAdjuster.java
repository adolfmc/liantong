package com.webrtc;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
class BaseBitrateAdjuster implements BitrateAdjuster {
    protected int targetBitrateBps;
    protected int targetFps;

    @Override // com.webrtc.BitrateAdjuster
    public void reportEncodedFrame(int i) {
    }

    @Override // com.webrtc.BitrateAdjuster
    public void setTargets(int i, int i2) {
        this.targetBitrateBps = i;
        this.targetFps = i2;
    }

    @Override // com.webrtc.BitrateAdjuster
    public int getAdjustedBitrateBps() {
        return this.targetBitrateBps;
    }

    @Override // com.webrtc.BitrateAdjuster
    public int getCodecConfigFramerate() {
        return this.targetFps;
    }
}
