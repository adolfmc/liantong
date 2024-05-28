package com.baidu.cloud.rtcbridge.frameprocessor;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class RtcFrameProcessorManager implements IRtcFrameProcessorManager {
    public BRTCFrameProcessorParams params = new BRTCFrameProcessorParams();

    @Override // com.baidu.cloud.rtcbridge.frameprocessor.IRtcBeautyManager
    public void setBeautyEnable(boolean z) {
        this.params.enableBeauty = z;
    }

    @Override // com.baidu.cloud.rtcbridge.frameprocessor.IRtcBeautyManager
    public void setBeautyValue(RtcBeautyType rtcBeautyType, float f) {
        this.params.beautyValues.put(rtcBeautyType, Float.valueOf(f));
    }

    @Override // com.baidu.cloud.rtcbridge.frameprocessor.IRtcBeautyManager
    public void setBeautyValue(RtcBeautyType rtcBeautyType, String str) {
        this.params.beautyValues.put(rtcBeautyType, str);
    }

    @Override // com.baidu.cloud.rtcbridge.frameprocessor.IRtcHumanSegManager
    public void enableHumanSeg(boolean z, BRTCEffectParams bRTCEffectParams) {
        BRTCFrameProcessorParams bRTCFrameProcessorParams = this.params;
        bRTCFrameProcessorParams.enableHumanSeg = z;
        bRTCFrameProcessorParams.humanSegParams = bRTCEffectParams;
    }

    @Override // com.baidu.cloud.rtcbridge.frameprocessor.IRtcWatermarkManager
    public void enableWatermark(boolean z, BRTCWatermarkParams bRTCWatermarkParams) {
        BRTCFrameProcessorParams bRTCFrameProcessorParams = this.params;
        bRTCFrameProcessorParams.enableWatermark = z;
        bRTCFrameProcessorParams.watermarkParams = bRTCWatermarkParams;
    }
}
