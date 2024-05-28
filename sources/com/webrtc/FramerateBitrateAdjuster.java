package com.webrtc;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
class FramerateBitrateAdjuster extends BaseBitrateAdjuster {
    private static final int INITIAL_FPS = 30;

    @Override // com.webrtc.BaseBitrateAdjuster, com.webrtc.BitrateAdjuster
    public int getCodecConfigFramerate() {
        return 30;
    }

    @Override // com.webrtc.BaseBitrateAdjuster, com.webrtc.BitrateAdjuster
    public void setTargets(int i, int i2) {
        if (this.targetFps == 0) {
            i2 = 30;
        }
        super.setTargets(i, i2);
        this.targetBitrateBps = (this.targetBitrateBps * 30) / this.targetFps;
    }
}
