package com.baidu.cloud.rtcbridge.frameprocessor;

import android.content.Context;
import android.graphics.SurfaceTexture;
import com.webrtc.VideoProcessor;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface CameraVideoProcessor extends IRtcFrameProcessorManager, VideoProcessor {
    void initialize(Context context, BRTCFrameProcessorParams bRTCFrameProcessorParams);

    void setSurfaceTexture(SurfaceTexture surfaceTexture);
}
