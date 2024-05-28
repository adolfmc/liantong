package com.baidu.cloud.frameprocessor.processor;

import android.graphics.SurfaceTexture;
import com.baidu.cloud.frameprocessor.gles.FullFrameRect;
import com.baidu.cloud.framework.frame.VideoFrameBuffer;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface IFrameProcessor {
    void init(FullFrameRect fullFrameRect, FullFrameRect fullFrameRect2);

    VideoFrameBuffer onFrame(VideoFrameBuffer videoFrameBuffer);

    void release();

    void setProcessorEnable(boolean z);

    void setSurfaceTexture(SurfaceTexture surfaceTexture);

    void setSwitchCameraFlag(boolean z);
}
