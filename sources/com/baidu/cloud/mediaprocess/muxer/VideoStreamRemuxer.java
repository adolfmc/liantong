package com.baidu.cloud.mediaprocess.muxer;

import com.baidu.cloud.framework.frame.VideoFrameBuffer;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public abstract class VideoStreamRemuxer {
    public abstract VideoFrameBuffer remuxNaluTag(VideoFrameBuffer videoFrameBuffer, int i, int i2);

    public abstract VideoFrameBuffer remuxSequenceHeader(byte[] bArr, int i, int i2);
}
