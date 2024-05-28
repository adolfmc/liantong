package com.baidu.cloud.rtcbridge.framecapture;

import com.baidu.cloud.framework.frame.VideoFrameBuffer;
import com.webrtc.CapturerObserver;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface RtcFrameCapturerObserver extends CapturerObserver {
    VideoFrameBuffer onFrameProcessor(VideoFrameBuffer videoFrameBuffer);
}
