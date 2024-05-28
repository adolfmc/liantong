package com.baidu.cloud.rtcbridge.frameprocessor;

import android.content.Context;
import com.baidu.cloud.frameprocessor.p133ar.ARProcessor;
import com.baidu.cloud.frameprocessor.processor.ForegroundProcessor;
import com.baidu.cloud.frameprocessor.processor.IFrameProcessor;
import com.baidu.cloud.frameprocessor.processor.Yuv2RgbProcessor;
import com.webrtc.VideoProcessor;
import java.util.ArrayList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class RtcFrameProcessorImpl implements IRtcFrameProcessor {
    @Override // com.baidu.cloud.rtcbridge.frameprocessor.IRtcFrameProcessor
    public VideoProcessor createVideoProcessor(Context context, BRTCFrameProcessorParams bRTCFrameProcessorParams, boolean z) {
        CameraVideoProcessor cameraVideoProcessorImpl;
        if (z) {
            cameraVideoProcessorImpl = new InnerCameraVideoProcessorImpl();
        } else {
            cameraVideoProcessorImpl = new CameraVideoProcessorImpl();
        }
        cameraVideoProcessorImpl.initialize(context, bRTCFrameProcessorParams);
        return cameraVideoProcessorImpl;
    }

    public static List<IFrameProcessor> buildProcessors(Context context) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new Yuv2RgbProcessor());
        ARProcessor aRProcessor = new ARProcessor(context);
        aRProcessor.setProcessorEnable(false);
        arrayList.add(aRProcessor);
        ForegroundProcessor foregroundProcessor = new ForegroundProcessor(context);
        foregroundProcessor.setProcessorEnable(false);
        arrayList.add(foregroundProcessor);
        return arrayList;
    }
}
