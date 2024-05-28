package com.webrtc;

import android.support.annotation.Nullable;
import com.webrtc.VideoEncoderFactory;
import java.util.ArrayList;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class SoftwareVideoEncoderFactory implements VideoEncoderFactory {
    @Override // com.webrtc.VideoEncoderFactory
    @Nullable
    public VideoEncoder createEncoder(VideoCodecInfo videoCodecInfo) {
        return null;
    }

    @Override // com.webrtc.VideoEncoderFactory
    @CalledByNative
    public /* synthetic */ VideoEncoderFactory.VideoEncoderSelector getEncoderSelector() {
        return VideoEncoderFactory.CC.$default$getEncoderSelector(this);
    }

    @Override // com.webrtc.VideoEncoderFactory
    @CalledByNative
    public /* synthetic */ VideoCodecInfo[] getImplementations() {
        VideoCodecInfo[] supportedCodecs;
        supportedCodecs = getSupportedCodecs();
        return supportedCodecs;
    }

    @Override // com.webrtc.VideoEncoderFactory
    public VideoCodecInfo[] getSupportedCodecs() {
        return supportedCodecs();
    }

    static VideoCodecInfo[] supportedCodecs() {
        ArrayList arrayList = new ArrayList();
        return (VideoCodecInfo[]) arrayList.toArray(new VideoCodecInfo[arrayList.size()]);
    }
}
