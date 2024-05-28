package com.baidu.cloud.rtcbridge.frameprocessor;

import java.util.HashMap;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class BRTCFrameProcessorParams {
    public HashMap<RtcBeautyType, Object> beautyValues = new HashMap<>();
    public boolean enableBeauty;
    public boolean enableHumanSeg;
    public boolean enableWatermark;
    public BRTCEffectParams humanSegParams;
    public BRTCWatermarkParams watermarkParams;
}
