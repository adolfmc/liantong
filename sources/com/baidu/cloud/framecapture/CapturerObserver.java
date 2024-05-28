package com.baidu.cloud.framecapture;

import com.baidu.cloud.framework.frame.VideoFrameBuffer;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface CapturerObserver {
    void onCapturerStarted(boolean z);

    void onCapturerStopped();

    void onFrameCaptured(VideoFrameBuffer videoFrameBuffer);
}
