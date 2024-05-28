package com.baidu.cloud.rtcbridge.frameprocessor;

import com.baidu.cloud.framework.frame.VideoFrameBuffer;
import com.webrtc.VideoFrame;
import com.webrtc.VideoProcessor;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class InnerCameraVideoProcessorImpl extends CameraVideoProcessorImpl implements InnerCameraVideoProcessor {
    @Override // com.baidu.cloud.rtcbridge.frameprocessor.CameraVideoProcessorImpl, com.webrtc.VideoProcessor
    public /* synthetic */ void onFrameCaptured(VideoFrame videoFrame, VideoProcessor.FrameAdaptationParameters frameAdaptationParameters) {
        VideoProcessor.CC.$default$onFrameCaptured(this, videoFrame, frameAdaptationParameters);
    }

    @Override // com.baidu.cloud.rtcbridge.frameprocessor.InnerCameraVideoProcessor
    public VideoFrameBuffer onFrameProcessor(VideoFrameBuffer videoFrameBuffer) {
        if (checkProcess()) {
            onFramePreProcess();
            return this.mProcessorChain.onFrame(videoFrameBuffer);
        }
        return videoFrameBuffer;
    }
}
