package com.baidu.rtc;

import com.webrtc.VideoFrame;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class RTCVideoFrame extends VideoFrame {
    public RTCVideoFrame(VideoFrame.Buffer buffer, int i, long j) {
        super(buffer, i, j);
    }

    public static RTCVideoFrame newfromVideoFrame(VideoFrame videoFrame) {
        return new RTCVideoFrame(videoFrame.getBuffer(), videoFrame.getRotation(), videoFrame.getTimestampNs());
    }
}
