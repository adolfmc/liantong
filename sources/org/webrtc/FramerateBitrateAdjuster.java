package org.webrtc;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
class FramerateBitrateAdjuster extends BaseBitrateAdjuster {
    private static final int INITIAL_FPS = 30;

    @Override // org.webrtc.BaseBitrateAdjuster, org.webrtc.BitrateAdjuster
    public int getCodecConfigFramerate() {
        return 30;
    }

    @Override // org.webrtc.BaseBitrateAdjuster, org.webrtc.BitrateAdjuster
    public void setTargets(int i, int i2) {
        if (this.targetFps == 0) {
            i2 = 30;
        }
        super.setTargets(i, i2);
        this.targetBitrateBps = (this.targetBitrateBps * 30) / this.targetFps;
    }
}
